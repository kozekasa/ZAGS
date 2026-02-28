package org.example.ui.birthRegistration;

import io.qameta.allure.*;
import org.example.baseTests.BaseTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.BirthRegistrationServiceData;
import org.example.models.CitizenData;
import org.example.models.UserData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BirthRegistrationTest extends BaseTest {
    @Owner("Aleksandr")
    @ParameterizedTest(name = "Тест регистрации рождения в {0}")
    @ValueSource(strings = {"chrome", "firefox", "opera"})
    @Tag("user")
    @Epic("ЗАГС")
    @Feature("Регистрация рождения")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация рождения")
    public void testSuccessfulBirthRegistration(String browser) {
        openUrl(browser);

        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        BirthRegistrationServiceData serviceData = TestDataFactory.createBirthServiceData();

        pages.userRegistrationPage().StartRegistration()
                                    .FillUserForm(user)
                                    .nextStep().click();

        pages.birthRegistrationPage().chooseBirthRegistration()
                                     .fillCitizenForm(citizen)
                                     .nextStep().click();

        pages.birthRegistrationPage().fillBirthRegistrationServiceForm(serviceData)
                                     .finishButton().click();

        String actualStatus = pages.birthRegistrationPage().applicationStatus().getText();

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus,
                "Статус заявки не корректен или заявка не была создана!");
    }
}