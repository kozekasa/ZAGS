package org.example.deathRegistration;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.BaseTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.CitizenData;
import org.example.models.DeathRegistrationServiceData;
import org.example.models.UserData;
import org.junit.jupiter.api.*;


public class DeathRegistrationTest extends BaseTest {
    @Owner("Aleksandr")
    @Test
    @Tag("user")
    @Epic("ЗАГС")
    @Feature("Регистрация брака")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация рождения: успешное заполнение всех форм!")
    public void testSuccessfulBirthRegistration() {

        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        DeathRegistrationServiceData serviceData = TestDataFactory.createDeathServiceData();

        pages.userRegistrationPage().StartRegistration();
        pages.userRegistrationPage().FillUserForm(user);
        pages.userRegistrationPage().nextStep().click();

        pages.deathRegistrationPage().chooseDeathRegistration();
        pages.deathRegistrationPage().fillCitizenForm(citizen);
        pages.deathRegistrationPage().nextStep().click();

        pages.deathRegistrationPage().fillDeathRegistrationServiceForm(serviceData);
        pages.deathRegistrationPage().finishButton().click();

        String actualStatus = pages.deathRegistrationPage().applicationStatus().getText();

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus, "Статус заявки не корректен или заявка не была создана!");
    }
}