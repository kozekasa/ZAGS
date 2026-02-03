package org.example.admin;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dataFactory.TestDataFactory;
import org.example.driver.WebDriverSingleton;
import org.example.models.AdminData;
import org.example.pages.AdminPage;
import org.junit.jupiter.api.*;

public class GetStatusByApplicationNumberTest {

    private AdminPage adminPage;
    private static final Logger logger = LogManager.getLogger(GetStatusByApplicationNumberTest.class);

    @Step("Открытие главной страницы и подготовка окружения")
    @BeforeEach
    public void setup() {
        logger.info("<<< Подготовка к запуску теста... >>>");

        String url = WebDriverSingleton.getEnv("BASE_URL");
        if (url == null || url.isEmpty()) {
            logger.error("BASE_URL не найден в конфигурации .env!");
        } else {
            logger.info("Открытие базового URL: {}", url);
        }

        try {
            WebDriverSingleton.getDriver().get(url);
            logger.info("Браузер успешно перешел на страницу: {}", url);

            adminPage = new AdminPage();

            logger.info("Страницы инициализированы успешно.");
        } catch (Exception e) {
            logger.error("Ошибка при подготовке к запуску теста: {}", e.getMessage());
            throw e;
        }
        logger.info("=== Подготовка к запуску теста завершена ===");
    }



    @Owner("Aleksandr")
    @Test
    @Tag("admin")
    @Epic("ЗАГС")
    @Feature("Регистрация брака")
    @Story("Проверка статуса заявки по номеру")
    @Severity(SeverityLevel.BLOCKER)
    @Step("Запуск теста. Оформление заявки: Регистрация брака")
    @DisplayName("Регистрация администратора: проверка статуса заявки по её номеру")
    public void testAdminCheck() {
        logger.info("<<< Запуск теста: Проверка статуса в панели администратора >>>");

        AdminData admin = TestDataFactory.createDefaultAdmin();

        //Пока не дошли до API, то номер захардкодил, потом будет просто его сюда передать.
        String applicationNumber = "58007";
        logger.info("Используемые данные: номер заявки '{}'", applicationNumber);

        logger.info("Шаг 1: Авторизация администратора...");
        adminPage.StartRegistration();
        adminPage.FillAdminForm(admin);
        adminPage.nextStep().click();
        logger.info("Авторизация выполнена успешно.");

        logger.info("Шаг 2: Поиск статуса для заявки №{}...", applicationNumber);
        String actualStatus = adminPage.getStatusByApplicationNumber(applicationNumber);

        logger.info("Результат поиска: заявка №{} имеет статус '{}'", applicationNumber, actualStatus);

        Assertions.assertEquals("На рассмотрении", actualStatus,
                "Статус заявки №" + applicationNumber + " не совпадает с ожидаемым!");

        logger.info("=== Завершение теста: статус успешно проверен! ===");
    }

    @Step("Завершение теста. Закрытие браузера")
    @AfterEach
    public void tearDown() {
        logger.info("<<< Закрытие тестового окружения >>>");
        try {
            WebDriverSingleton.quit();
            logger.info("Браузер закрыт корректно.");
        } catch (Exception e) {
            logger.error("Ошибка при закрытии браузера: {}", e.getMessage());
        }
        logger.info("=== Сессия завершена ===");
    }
}
