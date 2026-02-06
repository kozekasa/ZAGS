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

    private static WebDriver driver;
    private static final Dotenv DOTENV = Dotenv.load();
    private static final Logger LOGGER = LogManager.getLogger(WebDriverSingleton.class);

    private WebDriverSingleton() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
            LOGGER.info("Инициализация нового WebDriver Chrome...");
            try {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");

                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                LOGGER.info("WebDriver успешно запущен и будет открыт на всё окно.");
            } catch (Exception e) {
                LOGGER.error("Ошибка запуска WebDriver: {}", e.getMessage());
            }
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            LOGGER.info("Закрытие сессии WebDriver...");
            driver.quit();
            LOGGER.info("WebDriver успешно закрыт.");
            driver = null;
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
