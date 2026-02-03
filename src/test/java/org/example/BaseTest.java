package org.example;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.driver.WebDriverSingleton;
import org.example.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    private UserRegistrationPage userRegistrationPage;
    private MarriageRegistrationPage marriageRegistrationPage;
    private BirthRegistrationPage birthRegistrationPage;
    private DeathRegistrationPage deathRegistrationPage;
    private AdminPage adminPage;

    protected UserRegistrationPage userRegistrationPage() {
        if (userRegistrationPage == null) {
            userRegistrationPage = new UserRegistrationPage();
        }
        return userRegistrationPage;
    }

    protected MarriageRegistrationPage marriageRegistrationPage() {
        if (marriageRegistrationPage == null) {
            marriageRegistrationPage = new MarriageRegistrationPage();
        }
        return marriageRegistrationPage;
    }

    protected BirthRegistrationPage birthRegistrationPage() {
        if (birthRegistrationPage == null) {
            birthRegistrationPage = new BirthRegistrationPage();
        }
        return birthRegistrationPage;
    }

    protected DeathRegistrationPage deathRegistrationPage() {
        if (deathRegistrationPage == null) {
            deathRegistrationPage = new DeathRegistrationPage();
        }
        return deathRegistrationPage;
    }

    protected AdminPage adminPage() {
        if (adminPage == null) {
            adminPage = new AdminPage();
        }
        return adminPage;
    }

    @Step("Подготовка окружения и запуск браузера")
    @BeforeEach
    public void setup() {
        logger.info("[SETUP] <<< Подготовка к запуску теста... >>>");

        String url = WebDriverSingleton.getEnv("BASE_URL");
        if (url == null || url.isEmpty()) {
            logger.error("[SETUP] BASE_URL не найден в конфигурации .env!");
        } else {
            logger.info("[SETUP] Открытие базового URL: {}", url);
        }

        try {
            WebDriverSingleton.getDriver().get(url);
            logger.info("[SETUP] Браузер успешно перешел на страницу: {}", url);

            logger.info("[SETUP] Страницы инициализированы успешно.");
        } catch (Exception e) {
            logger.error("[SETUP] Ошибка при подготовке к запуску теста: {}", e.getMessage());
            throw e;
        }
        logger.info("[SETUP] === Подготовка к запуску теста завершена ===");
    }

    @Step("Закрытие браузера")
    @AfterEach
    public void tearDown() {
        logger.info("[TEARDOWN] <<< Закрытие тестового окружения >>>");
        try {
            WebDriverSingleton.quit();
            logger.info("[TEARDOWN] Браузер закрыт корректно.");
        } catch (Exception e) {
            logger.error("[TEARDOWN] Ошибка при закрытии браузера: {}", e.getMessage());
        }
        logger.info("[TEARDOWN] === Сессия завершена ===");
    }
}