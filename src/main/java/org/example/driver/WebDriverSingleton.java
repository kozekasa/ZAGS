package org.example.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.cdimascio.dotenv.Dotenv;
import java.time.Duration;
import java.util.logging.Level;


public class WebDriverSingleton {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final Dotenv DOTENV = Dotenv.load();
    private static final Logger LOGGER = LogManager.getLogger(WebDriverSingleton.class);

    private WebDriverSingleton() {}

    public static WebDriver getDriverThreadLocal() {
        if (DRIVER_THREAD_LOCAL.get() == null) {
            java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
            LOGGER.info("Инициализация нового WebDriver Chrome...");
            try {
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");

                if (System.getenv("JENKINS_HOME") != null) {
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                }

                DRIVER_THREAD_LOCAL.set(new ChromeDriver(options));
                DRIVER_THREAD_LOCAL.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                LOGGER.info("WebDriver успешно запущен и будет открыт на всё окно.");
            } catch (Exception e) {
                LOGGER.error("Ошибка запуска WebDriver: {}", e.getMessage());
            }
        }
        return DRIVER_THREAD_LOCAL.get();
    }

    public static void quit() {
        if (DRIVER_THREAD_LOCAL.get() != null) {
            LOGGER.info("Закрытие сессии WebDriver...");
            DRIVER_THREAD_LOCAL.get().quit();
            LOGGER.info("WebDriver успешно закрыт.");
            DRIVER_THREAD_LOCAL.remove();
        }
    }

    public static String getEnv(String key) {
        String value = DOTENV.get(key);
        if (value == null) {
            LOGGER.warn("Переменная окружения '{}' не найдена в .env файле!", key);
        }
        return value;
    }
}
