package org.example.admin;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.BaseTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.AdminData;
import org.junit.jupiter.api.*;

public class GetStatusByApplicationNumberTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(GetStatusByApplicationNumberTest.class);

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
        adminPage().StartRegistration();
        adminPage().FillAdminForm(admin);
        adminPage().nextStep().click();
        logger.info("Авторизация выполнена успешно.");

        logger.info("Шаг 2: Поиск статуса для заявки №{}...", applicationNumber);
        String actualStatus = adminPage().getStatusByApplicationNumber(applicationNumber);

        logger.info("Результат поиска: заявка №{} имеет статус '{}'", applicationNumber, actualStatus);

        Assertions.assertEquals("На рассмотрении", actualStatus,
                "Статус заявки №" + applicationNumber + " не совпадает с ожидаемым!");

        logger.info("=== Завершение теста: статус успешно проверен! ===");
    }
}
