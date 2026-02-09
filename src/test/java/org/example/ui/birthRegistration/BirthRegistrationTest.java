package org.example.ui.birthRegistration;

import io.qameta.allure.*;
import org.example.ui.BaseTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.validData.BirthRegistrationServiceData;
import org.example.models.validData.CitizenData;
import org.example.models.validData.UserData;
import org.junit.jupiter.api.*;

public class BirthRegistrationTest extends BaseTest {
    @Owner("Aleksandr")
    @Test
    @Tag("user")
    @Epic("ЗАГС")
    @Feature("Регистрация рождения")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация рождения")
    public void testSuccessfulBirthRegistration() {
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