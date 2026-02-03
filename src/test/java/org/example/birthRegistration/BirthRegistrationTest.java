package org.example.birthRegistration;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.BaseTest;
import org.example.dataFactory.TestDataFactory;
import org.example.driver.WebDriverSingleton;
import org.example.marriageRegistration.MarriageRegistrationTest;
import org.example.models.BirthRegistrationServiceData;
import org.example.models.CitizenData;
import org.example.models.UserData;
import org.example.pages.BirthRegistrationPage;
import org.example.pages.MarriageRegistrationPage;
import org.example.pages.UserRegistrationPage;
import org.junit.jupiter.api.*;

public class BirthRegistrationTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(BirthRegistrationTest.class);

    @Owner("Aleksandr")
    @Test
    @Tag("user")
    @Epic("ЗАГС")
    @Feature("Регистрация брака")
    @Severity(SeverityLevel.BLOCKER)
    @Step("Запуск теста. Оформление заявки: Регистрация брака")
    @DisplayName("Регистрация рождения: успешное заполнение всех форм!")
    public void testSuccessfulBirthRegistration() {
        logger.info("<<< Запуск теста: Регистрация рождения >>>");

        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        BirthRegistrationServiceData serviceData = TestDataFactory.createBirthServiceData();
        logger.info("Тестовые данные для регистрации рождения сгенерированы.");

        logger.info("Шаг 1: Регистрация пользователя...");
        userRegistrationPage().StartRegistration();
        userRegistrationPage().FillUserForm(user);
        userRegistrationPage().nextStep().click();
        logger.info("Данные пользователя (родителя) заполнены.");

        logger.info("Шаг 2: Выбор услуги и заполнение данных гражданина...");
        birthRegistrationPage().chooseBirthRegistration();
        birthRegistrationPage().fillCitizenForm(citizen);
        birthRegistrationPage().nextStep().click();
        logger.info("Данные гражданина успешно внесены.");

        logger.info("Шаг 3: Заполнение специфических данных услуги рождения...");
        birthRegistrationPage().fillBirthRegistrationServiceForm(serviceData);
        birthRegistrationPage().finishButton().click();
        logger.info("Заявка на регистрацию рождения отправлена.");

        logger.info("Финальный шаг: Проверка статуса заявки...");
        String actualStatus = birthRegistrationPage().applicationStatus().getText();
        logger.info("Фактический статус из системы: '{}'", actualStatus);

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus,
                "Статус заявки не корректен или заявка не была создана!");

        logger.info("=== Завершение теста: статус подтвержден успешно! ===");
    }
}