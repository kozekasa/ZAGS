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
    @Owner("Aleksandr")
    @Test
    @Tag("user")
    @Epic("ЗАГС")
    @Feature("Регистрация брака")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация брака: успешное заполнение всех форм!")
    public void testSuccessfulMarriageRegistration() {
        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        MarriageRegistrationServiceData serviceData = TestDataFactory.createMarriageServiceData();

        pages.userRegistrationPage().StartRegistration();
        pages.userRegistrationPage().FillUserForm(user);
        pages.userRegistrationPage().nextStep().click();

        pages.marriageRegistrationPage().chooseMarriageRegistration();
        pages.marriageRegistrationPage().fillCitizenForm(citizen);
        pages.marriageRegistrationPage().nextStep().click();

        pages.marriageRegistrationPage().fillMarriageRegistrationServiceForm(serviceData);
        pages.marriageRegistrationPage().finishButton().click();

        String actualStatus = pages.marriageRegistrationPage().applicationStatus().getText();

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus, "Статус заявки не корректен или заявка не была создана!");
    }
}