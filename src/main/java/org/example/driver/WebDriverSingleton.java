package org.example.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;



public class WebDriverSingleton {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final Dotenv DOTENV = Dotenv.load();
    private static final Logger LOGGER = LogManager.getLogger(WebDriverSingleton.class);

    private WebDriverSingleton() {}

    public static WebDriver getDriverThreadLocal() {
        if (DRIVER_THREAD_LOCAL.get() == null) {
            String selenoidUrl = getEnv("SELENOID_URL");

            boolean isJenkins = System.getenv("JENKINS_URL") != null;

            WebDriver driver;

            try {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");

                if (isJenkins && (selenoidUrl == null || selenoidUrl.isEmpty())) {
                    LOGGER.warn("Jenkins обнаружен, но SELENOID_URL пуст! Использую локальный Headless Chrome.");
                    options.addArguments("--headless=new");
                }

                if (selenoidUrl != null && !selenoidUrl.isEmpty()) {
                    LOGGER.info("Инициализация RemoteWebDriver для Selenoid: {}", selenoidUrl);

                    Map<String, Object> selenoidOptions = new HashMap<>();
                    selenoidOptions.put("enableVNC", true);
                    selenoidOptions.put("enableVideo", false);
                    options.setCapability("selenoid:options", selenoidOptions);

                    driver = new RemoteWebDriver(new URL(selenoidUrl), options);
                } else {
                    LOGGER.info("Локальный запуск ChromeDriver (GUI/Headless)");
                    driver = new ChromeDriver(options);
                }

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                DRIVER_THREAD_LOCAL.set(driver);

            } catch (Exception e) {
                LOGGER.error("Критическая ошибка инициализации WebDriver: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return DRIVER_THREAD_LOCAL.get();
    }

    public static String getEnv(String key) {
        String value = System.getenv(key);
        if (value == null || value.isEmpty()) {
            value = DOTENV.get(key);
        }
        return value;
    }

    public static void quit() {
        if (DRIVER_THREAD_LOCAL.get() != null) {
            DRIVER_THREAD_LOCAL.get().quit();
            DRIVER_THREAD_LOCAL.remove();
            LOGGER.info("Сессия WebDriver успешно закрыта.");
        }
    }
}