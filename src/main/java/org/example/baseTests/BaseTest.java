package org.example.baseTests;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.driver.WebDriverSingleton;
import org.example.pages.PageManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    protected PageManager pages = new PageManager();

    @Step("Открытие базового URL в браузере {browser}")
    protected void openUrl(String browser) {
        String url = WebDriverSingleton.getEnv("BASE_URL");

        if (url == null || url.isEmpty()) {
            LOGGER.error("[SETUP] BASE_URL не найден!");
            throw new RuntimeException("BASE_URL is missing in .env or system variables");
        }

        try {
            WebDriverSingleton.getDriverThreadLocal(browser).get(url);
            LOGGER.info("[SETUP] Браузер {} запущен, URL открыт: {}", browser, url);
        } catch (Exception e) {
            LOGGER.error("[SETUP] Ошибка при старте браузера {}: {}", browser, e.getMessage());
            throw e;
        }
    }

    @Step("Закрытие браузера")
    @AfterEach
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