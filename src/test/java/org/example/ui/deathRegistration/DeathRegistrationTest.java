package org.example.ui.deathRegistration;

import io.qameta.allure.*;
import org.example.baseTests.BaseTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.CitizenData;
import org.example.models.DeathRegistrationServiceData;
import org.example.models.UserData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class DeathRegistrationTest extends BaseTest {
    @Owner("Aleksandr")
    @ParameterizedTest(name = "Тест регистрации смерти в {0}")
    @ValueSource(strings = {"chrome", "firefox", "opera"})
    @Tag("user")
    @Epic("ЗАГС")
    @Feature("Регистрация смерти")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация смерти")
    public void testSuccessfulBirthRegistration(String browser) {
        openUrl(browser);

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