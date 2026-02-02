package org.example.deathRegistration;

import org.example.dataFactory.TestDataFactory;
import org.example.driver.WebDriverSingleton;
import org.example.models.CitizenData;
import org.example.models.DeathRegistrationServiceData;
import org.example.models.UserData;
import org.example.pages.DeathRegistrationPage;
import org.example.pages.UserRegistrationPage;
import org.junit.jupiter.api.*;

public class DeathRegistrationTest {

    private UserRegistrationPage userRegistrationPage;
    private DeathRegistrationPage deathRegistrationPage;

    @BeforeEach
    public void setup() {
        String url = WebDriverSingleton.getEnv("BASE_URL");
        WebDriverSingleton.getDriver().get(url);

        userRegistrationPage = new UserRegistrationPage();
        deathRegistrationPage = new DeathRegistrationPage();
    }

    @Test
    @Tag("user")
    @DisplayName("Регистрация рождения: успешное заполнение всех форм!")
    public void testSuccessfulBirthRegistration() {
        UserData user = TestDataFactory.createDefaultUser();
        CitizenData citizen =TestDataFactory.createDefaultCitizen();
        DeathRegistrationServiceData serviceData = TestDataFactory.createDeathServiceData();

        userRegistrationPage.StartRegistration();
        userRegistrationPage.FillUserForm(user);
        userRegistrationPage.nextStep().click();

        deathRegistrationPage.chooseDeathRegistration();
        deathRegistrationPage.fillCitizenForm(citizen);
        deathRegistrationPage.nextStep().click();

        deathRegistrationPage.fillDeathRegistrationServiceForm(serviceData);
        deathRegistrationPage.finishButton().click();

        String actualStatus = deathRegistrationPage.applicationStatus().getText();

        Assertions.assertEquals("Статус заявки: На рассмотрении.", actualStatus, "Статус заявки не корректен или заявка не была создана!");
    }

    @AfterEach
    public void tearDown() {
        WebDriverSingleton.quit();
    }
}