package org.example.marriageRegistration;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.BaseTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.CitizenData;
import org.example.models.MarriageRegistrationServiceData;
import org.example.models.UserData;
import org.junit.jupiter.api.*;

public class MarriageRegistrationTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(MarriageRegistrationTest.class);

    @Owner("Aleksandr")
    @Test
    @Tag("user")
    @Epic("ЗАГС")
    @Feature("Регистрация брака")
    @Severity(SeverityLevel.BLOCKER)
    @Step("Запуск теста. Оформление заявки: Регистрация брака")
    @DisplayName("Регистрация брака: успешное заполнение всех форм!")
    public void testSuccessfulMarriageRegistration() {

        logger.info("<<< Запуск теста: Регистрация брака >>>");

        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        MarriageRegistrationServiceData serviceData = TestDataFactory.createMarriageServiceData();
        logger.info("Тестовые данные успешно сгенерированы.");

        logger.info("Шаг 1: Регистрация пользователя...");
        pages.userRegistrationPage().StartRegistration();
        pages.userRegistrationPage().FillUserForm(user);
        pages.userRegistrationPage().nextStep().click();
        logger.info("Данные пользователя заполнены.");

        logger.info("Шаг 2: Выбор услуги и заполнение данных гражданина...");
        pages.marriageRegistrationPage().chooseMarriageRegistration();
        pages.marriageRegistrationPage().fillCitizenForm(citizen);
        pages.marriageRegistrationPage().nextStep().click();
        logger.info("Данные гражданина заполнены.");

        logger.info("Шаг 3: Заполнение формы: данные услуги...");
        pages.marriageRegistrationPage().fillMarriageRegistrationServiceForm(serviceData);
        pages.marriageRegistrationPage().finishButton().click();
        logger.info("Заявка на услугу оформлена");

        logger.info("Финальный шаг: Проверка статуса заявки...");
        String actualStatus = pages.marriageRegistrationPage().applicationStatus().getText();
        logger.info(actualStatus);

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus, "Статус заявки не корректен или заявка не была создана!");
        logger.info("=== Завершение теста: статус подтвержден! ===");
    }
}