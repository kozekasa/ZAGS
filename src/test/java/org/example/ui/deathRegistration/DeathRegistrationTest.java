package org.example.ui.deathRegistration;

import io.qameta.allure.*;
import org.example.baseTests.BaseTest;
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
    @Feature("Регистрация смерти")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация смерти")
    public void testSuccessfulBirthRegistration() {

        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        DeathRegistrationServiceData serviceData = TestDataFactory.createDeathServiceData();

        pages.userRegistrationPage().StartRegistration()
                                    .FillUserForm(user)
                                    .nextStep().click();

        pages.deathRegistrationPage().chooseDeathRegistration()
                                     .fillCitizenForm(citizen)
                                     .nextStep().click();

        pages.deathRegistrationPage().fillDeathRegistrationServiceForm(serviceData)
                                     .finishButton().click();

        String actualStatus = pages.deathRegistrationPage().applicationStatus().getText();

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus, "Статус заявки не корректен или заявка не была создана!");
    }
}