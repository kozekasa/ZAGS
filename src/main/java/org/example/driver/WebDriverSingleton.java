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
            // Отключаем лишние логи Selenium
            java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

            try {
                LOGGER.info("Настройка WebDriverManager...");

                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                if (System.getenv("JENKINS_HOME") != null || System.getProperty("os.name").toLowerCase().contains("linux")) {
                    LOGGER.info("Обнаружена серверная среда. Запуск в Headless режиме...");
                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                } else {
                    options.addArguments("--start-maximized");
                    options.addArguments("--remote-allow-origins=*");
                }


                LOGGER.info("Инициализация ChromeDriver...");
                WebDriver driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                DRIVER_THREAD_LOCAL.set(driver);
                LOGGER.info("WebDriver успешно запущен.");

            } catch (Exception e) {
                LOGGER.error("КРИТИЧЕСКАЯ ОШИБКА: Не удалось запустить WebDriver! Причина: {}", e.getMessage());
                throw new RuntimeException("Ошибка инициализации драйвера: " + e.getMessage(), e);
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
