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
        BirthRegistrationServiceData serviceData = TestDataFactory.createBirthServiceData();

        pages.userRegistrationPage().StartRegistration();
        pages.userRegistrationPage().FillUserForm(user);
        pages.userRegistrationPage().nextStep().click();

        pages.birthRegistrationPage().chooseBirthRegistration();
        pages.birthRegistrationPage().fillCitizenForm(citizen);
        pages.birthRegistrationPage().nextStep().click();

        pages.birthRegistrationPage().fillBirthRegistrationServiceForm(serviceData);
        pages.birthRegistrationPage().finishButton().click();

        String actualStatus = pages.birthRegistrationPage().applicationStatus().getText();

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus,
                "Статус заявки не корректен или заявка не была создана!");
    }
}