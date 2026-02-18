package org.example.ui.marriageRegistration;

import io.qameta.allure.*;
import org.example.baseTests.BaseTest;
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
    @DisplayName("Регистрация брака")
    public void testSuccessfulMarriageRegistration() {
        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        MarriageRegistrationServiceData serviceData = TestDataFactory.createMarriageServiceData();

        pages.userRegistrationPage().StartRegistration()
                                    .FillUserForm(user)
                                    .nextStep().click();

        pages.marriageRegistrationPage().chooseMarriageRegistration()
                                        .fillCitizenForm(citizen)
                                        .nextStep().click();

        pages.marriageRegistrationPage().fillMarriageRegistrationServiceForm(serviceData)
                                        .finishButton().click();

        String actualStatus = pages.marriageRegistrationPage().applicationStatus().getText();

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus, "Статус заявки не корректен или заявка не была создана!");
    }
}