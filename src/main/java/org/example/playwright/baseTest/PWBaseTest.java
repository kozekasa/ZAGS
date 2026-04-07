package org.example.playwright.baseTest;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.playwright.driver.PWDriver;
import org.example.playwright.pages.PWPageManager;
import org.junit.jupiter.api.AfterEach;
import com.microsoft.playwright.Page;

public class PWBaseTest {

    protected static final Logger LOGGER = LogManager.getLogger(PWBaseTest.class);
    protected PWPageManager pages;

    @Step("[PW] Открытие базового URL в браузере {browser}")
    protected void openUrl(String browser) {
        String url = PWDriver.getEnv("BASE_URL");

        if (url == null || url.isEmpty()) {
            LOGGER.error("[PW SETUP] BASE_URL не найден!");
            throw new RuntimeException("BASE_URL is missing in .env or system variables");
        }

        try {
            Page page = PWDriver.getPage(browser);
            pages = new PWPageManager(page); // Инициализируем страницы для этой сессии
            page.navigate(url);
            LOGGER.info("[PW SETUP] Браузер {} запущен, URL открыт: {}", browser, url);
        } catch (Exception e) {
            LOGGER.error("[PW SETUP] Ошибка при старте Playwright в {}: {}", browser, e.getMessage());
            throw e;
        }
    }

    @Step("[PW] Закрытие браузера")
    @AfterEach
    public void tearDown() {
        pages.resetPages();

        try {
            PWDriver.quit();
            LOGGER.info("[PW TEARDOWN] Сессия Playwright успешно закрыта.");
        } catch (Exception e) {
            LOGGER.error("[PW TEARDOWN] Ошибка при закрытии Playwright: {}", e.getMessage());
        }
        LOGGER.info("[PW TEARDOWN] === Сессия завершена ===");
    }
}