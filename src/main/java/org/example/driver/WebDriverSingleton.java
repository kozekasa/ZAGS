package org.example.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WebDriverSingleton {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final Dotenv DOTENV = Dotenv.load();
    private static final Logger LOGGER = LogManager.getLogger(WebDriverSingleton.class);

    private WebDriverSingleton() {}

    public static WebDriver getDriverThreadLocal() {
        return getDriverThreadLocal("chrome");
    }

    public static WebDriver getDriverThreadLocal(String browser) {
        if (DRIVER_THREAD_LOCAL.get() == null) {
            String selenoidUrl = getEnv("SELENOID_URL");
            boolean isJenkins = System.getenv("JENKINS_URL") != null;
            WebDriver driver;

            MutableCapabilities options = configureOptions(browser);

            try {
                if (isJenkins && (selenoidUrl == null || selenoidUrl.isEmpty())) {
                    LOGGER.warn("Jenkins обнаружен без Selenoid. Включаю Headless для {}", browser);
                    addHeadless(options, browser);
                }

                if (selenoidUrl != null && !selenoidUrl.isEmpty()) {
                    LOGGER.info("Инициализация RemoteWebDriver ({}) для Selenoid: {}", browser, selenoidUrl);
                    addSelenoidCapabilities(options);
                    driver = new RemoteWebDriver(new URL(selenoidUrl), options);
                } else {
                    LOGGER.info("Локальный запуск драйвера: {}", browser);
                    driver = createLocalDriver(browser, options);
                }

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().maximize();
                DRIVER_THREAD_LOCAL.set(driver);

            } catch (Exception e) {
                LOGGER.error("Критическая ошибка инициализации WebDriver ({}): {}", browser, e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return DRIVER_THREAD_LOCAL.get();
    }

    private static MutableCapabilities configureOptions(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.setCapability("browserName", "firefox");
                ffOptions.addPreference("intl.accept_languages", "ru-RU, ru");;
                return ffOptions;

            case "opera":
                ChromeOptions operaChromeOptions = new ChromeOptions();
                operaChromeOptions.setBinary("/usr/bin/opera");
                operaChromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*");
                operaChromeOptions.setCapability("browserName", "opera");
                return operaChromeOptions;

            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--lang=ru-RU");
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("intl.accept_languages", "ru-RU,ru");
                chromeOptions.setExperimentalOption("prefs", prefs);
                return chromeOptions;
        }
    }

    private static void addHeadless(MutableCapabilities options, String browser) {
        if (options instanceof ChromeOptions chromeOptions) {
            chromeOptions.addArguments("--headless=new");
        } else if (options instanceof FirefoxOptions firefoxOptions) {
            firefoxOptions.addArguments("-headless");
        } else if (options instanceof EdgeOptions edgeOptions) {
            edgeOptions.addArguments("--headless=new");
        }
    }

    private static void addSelenoidCapabilities(MutableCapabilities options) {
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", false);
        selenoidOptions.put("screenResolution", "1920x1080x24");
        selenoidOptions.put("env", Arrays.asList("LANG=ru_RU.UTF-8", "LANGUAGE=ru_RU", "LC_ALL=ru_RU.UTF-8"));
        options.setCapability("selenoid:options", selenoidOptions);
    }

    private static WebDriver createLocalDriver(String browser, MutableCapabilities options) {
        return switch (browser.toLowerCase()) {
            case "firefox" -> new FirefoxDriver((FirefoxOptions) options);
            case "opera" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                yield new ChromeDriver(chromeOptions);
            }
            default -> new ChromeDriver((ChromeOptions) options);
        };
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