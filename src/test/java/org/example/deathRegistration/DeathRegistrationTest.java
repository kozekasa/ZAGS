package org.example.deathRegistration;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.BaseTest;
import org.example.dataFactory.TestDataFactory;
import org.example.driver.WebDriverSingleton;
import org.example.marriageRegistration.MarriageRegistrationTest;
import org.example.models.CitizenData;
import org.example.models.DeathRegistrationServiceData;
import org.example.models.UserData;
import org.example.pages.DeathRegistrationPage;
import org.example.pages.MarriageRegistrationPage;
import org.example.pages.UserRegistrationPage;
import org.junit.jupiter.api.*;


public class DeathRegistrationTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(DeathRegistrationTest.class);

    @Owner("Aleksandr")
    @Test
    @Tag("user")
    @Epic("ЗАГС")
    @Feature("Регистрация брака")
    @Severity(SeverityLevel.BLOCKER)
    @Step("Запуск теста. Оформление заявки: Регистрация рождения")
    @DisplayName("Регистрация рождения: успешное заполнение всех форм!")
    public void testSuccessfulBirthRegistration() {
        logger.info("<<< Запуск теста: Регистрация смерти >>>");

        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        DeathRegistrationServiceData serviceData = TestDataFactory.createDeathServiceData();
        logger.info("Тестовые данные успешно сгенерированы.");

        logger.info("Шаг 1: Регистрация пользователя...");
        userRegistrationPage().StartRegistration();
        userRegistrationPage().FillUserForm(user);
        userRegistrationPage().nextStep().click();
        logger.info("Данные пользователя заполнены.");

        logger.info("Шаг 2: Выбор услуги и заполнение данных гражданина (заявителя)...");
        deathRegistrationPage().chooseDeathRegistration();
        deathRegistrationPage().fillCitizenForm(citizen);
        deathRegistrationPage().nextStep().click();
        logger.info("Данные гражданина заполнены.");

        logger.info("Шаг 3: Заполнение формы: данные об умершем и свидетельстве...");
        deathRegistrationPage().fillDeathRegistrationServiceForm(serviceData);
        deathRegistrationPage().finishButton().click();
        logger.info("Заявка на услугу оформлена.");

        logger.info("Финальный шаг: Проверка статуса заявки...");
        String actualStatus = deathRegistrationPage().applicationStatus().getText();
        logger.info("Фактический статус: {}", actualStatus);

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus, "Статус заявки не корректен или заявка не была создана!");
        logger.info("=== Завершение теста: статус подтвержден! ===");
    }
}