package org.example.playwright.driver;

import com.microsoft.playwright.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PWDriver {
    private static final Logger LOGGER = LogManager.getLogger(PWDriver.class);
    private static final Dotenv DOTENV = Dotenv.load();

    private static final ThreadLocal<Playwright> PLAYWRIGHT_THREAD = new ThreadLocal<>();
    private static final ThreadLocal<Browser> BROWSER_THREAD = new ThreadLocal<>();
    private static final ThreadLocal<Page> PAGE_THREAD = new ThreadLocal<>();

    private PWDriver() {}

    public static Page getPage(String browserName) {
        if (PAGE_THREAD.get() == null) {
            try {
                Playwright playwright = Playwright.create();
                PLAYWRIGHT_THREAD.set(playwright);

                BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false);

                Browser browser = switch (browserName.toLowerCase()) {
                    case "firefox" -> playwright.firefox().launch(options);
                    case "webkit" -> playwright.webkit().launch(options);
                    default -> playwright.chromium().launch(options);
                };
                BROWSER_THREAD.set(browser);

                // Создаем контекст и страницу сразу (аналог сессии драйвера)
                BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize(1920, 1080));

                PAGE_THREAD.set(context.newPage());
                LOGGER.info("[PW DRIVER] Браузер {} успешно инициализирован", browserName);
            } catch (Exception e) {
                LOGGER.error("[PW DRIVER] Ошибка при создании Playwright: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return PAGE_THREAD.get();
    }

    public static String getEnv(String key) {
        String value = System.getenv(key);
        return (value == null || value.isEmpty()) ? DOTENV.get(key) : value;
    }

    public static void quit() {
        if (PAGE_THREAD.get() != null) {
            BROWSER_THREAD.get().close();
            PLAYWRIGHT_THREAD.get().close();

            PAGE_THREAD.remove();
            BROWSER_THREAD.remove();
            PLAYWRIGHT_THREAD.remove();
        }
    }
}