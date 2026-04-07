package org.example.playwright;

import org.example.dataFactory.TestDataFactory;
import org.example.models.BirthRegistrationServiceData;
import org.example.models.CitizenData;
import org.example.models.UserData;
import org.example.playwright.baseTest.PWBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PWBirthRegistrationTest extends PWBaseTest {

    @Test
    public void testBirthRegistration() {
        openUrl("chrome");

        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        BirthRegistrationServiceData data = TestDataFactory.createBirthServiceData();

        pages.userRegistrationPage()
                .startRegistration()
                .fillUserForm(user)
                .clickNext();

        pages.birthRegistrationPage()
                .chooseBirthRegistration()
                .fillCitizenForm(citizen)
                .clickNext()
                .fillBirthRegistrationServiceForm(data)
                .clickFinishButton();

        String actualStatus = pages.birthRegistrationPage().getApplicationStatus();

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus,
                "Статус заявки не корректен или заявка не была создана!");
    }
}
