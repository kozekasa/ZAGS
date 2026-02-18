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

    @Step("Подготовка окружения и запуск браузера")
    @BeforeEach
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