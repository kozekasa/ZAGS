package org.example.birthRegistration;

import org.example.dataFactory.TestDataFactory;
import org.example.driver.WebDriverSingleton;
import org.example.models.BirthRegistrationServiceData;
import org.example.models.CitizenData;
import org.example.models.UserData;
import org.example.pages.BirthRegistrationPage;
import org.example.pages.UserRegistrationPage;
import org.junit.jupiter.api.*;

public class BirthRegistrationTest {

    private UserRegistrationPage userRegistrationPage;
    private BirthRegistrationPage birthRegistrationPage;

    @BeforeEach
    public void setup() {
        String url = WebDriverSingleton.getEnv("BASE_URL");
        WebDriverSingleton.getDriver().get(url);

        userRegistrationPage = new UserRegistrationPage();
        birthRegistrationPage = new BirthRegistrationPage();
    }

    @Test
    @Tag("user")
    @DisplayName("Регистрация рождения: успешное заполнение всех форм!")
    public void testSuccessfulBirthRegistration() {
        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen = TestDataFactory.createDefaultCitizen();
        BirthRegistrationServiceData serviceData = TestDataFactory.createBirthServiceData();

        userRegistrationPage.StartRegistration();
        userRegistrationPage.FillUserForm(user);
        userRegistrationPage.nextStep().click();

        birthRegistrationPage.chooseBirthRegistration();
        birthRegistrationPage.fillCitizenForm(citizen);
        birthRegistrationPage.nextStep().click();

        birthRegistrationPage.fillBirthRegistrationServiceForm(serviceData);
        birthRegistrationPage.finishButton().click();

        String actualStatus = birthRegistrationPage.applicationStatus().getText();

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus, "Статус заявки не корректен или заявка не была создана!");
    }

    @AfterEach
    public void tearDown() {
        WebDriverSingleton.quit();
    }
}