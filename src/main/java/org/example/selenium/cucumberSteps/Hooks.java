package org.example.selenium.cucumberSteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.selenium.baseTests.BaseTest;
import org.example.selenium.driver.WebDriverSingleton;
import org.example.selenium.pages.PageManager;

public class Hooks {

    protected static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
    protected PageManager pages = new PageManager();

    @Before
    public void setup() {
        String url = WebDriverSingleton.getEnv("BASE_URL");
        if (url == null || url.isEmpty()) {
            LOGGER.error("[SETUP] BASE_URL не найден в конфигурации .env!");
        } else {
            LOGGER.info("[SETUP] Открытие URL: {}", url);
        }

        try {
            WebDriverSingleton.getDriverThreadLocal().get(url);
        } catch (Exception e) {
            LOGGER.error("[SETUP] Ошибка при подготовке к запуску теста: {}", e.getMessage());
            throw e;
        }
        LOGGER.info("[SETUP] === Подготовка к запуску теста завершена ===");
    }

    @After
    public void tearDown() {
        pages.resetPages();
        try {
            WebDriverSingleton.quit();
        } catch (Exception e) {
            LOGGER.error("[TEARDOWN] Ошибка при закрытии браузера: {}", e.getMessage());
        }
        LOGGER.info("[TEARDOWN] === Сессия завершена ===");
    }
}