package org.example.marriageRegistration;

import org.example.dataFactory.TestDataFactory;
import org.example.driver.WebDriverSingleton;
import org.example.models.CitizenData;
import org.example.models.MarriageRegistrationServiceData;
import org.example.models.UserData;
import org.example.pages.MarriageRegistrationPage;
import org.example.pages.UserRegistrationPage;
import org.junit.jupiter.api.*;

public class MarriageRegistrationTest {

    private UserRegistrationPage userRegistrationPage;
    private MarriageRegistrationPage marriageRegistrationPage;

    @BeforeEach
    public void setup() {
        String url = WebDriverSingleton.getEnv("BASE_URL");
        WebDriverSingleton.getDriver().get(url);

        userRegistrationPage = new UserRegistrationPage();
        marriageRegistrationPage = new MarriageRegistrationPage();
    }

    @Test
    @Tag("user")
    @DisplayName("Регистрация брака: успешное заполнение всех форм!")
    public void testSuccessfulMarriageRegistration() {

        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        MarriageRegistrationServiceData serviceData = TestDataFactory.createMarriageServiceData();

        userRegistrationPage.StartRegistration();
        userRegistrationPage.FillUserForm(user);
        userRegistrationPage.nextStep().click();

        marriageRegistrationPage.chooseMarriageRegistration();
        marriageRegistrationPage.fillCitizenForm(citizen);
        marriageRegistrationPage.nextStep().click();

        marriageRegistrationPage.fillMarriageRegistrationServiceForm(serviceData);
        marriageRegistrationPage.finishButton().click();

        String actualStatus = marriageRegistrationPage.applicationStatus().getText();

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus, "Статус заявки не корректен или заявка не была создана!");
    }

    @AfterEach
    public void tearDown() {
        WebDriverSingleton.quit();
    }
}