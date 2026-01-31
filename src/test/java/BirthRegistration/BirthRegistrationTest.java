package BirthRegistration;

import org.example.dataFactory.TestDataFactory;
import org.example.driver.WebDriverSingleton;
import org.example.models.BirthRegistrationServiceData;
import org.example.models.CitizenData;
import org.example.models.MarriageRegistrationServiceData;
import org.example.models.UserData;
import org.example.pages.BirthRegistrationPage;
import org.example.pages.MarriageRegistrationPage;
import org.example.pages.UserRegistrationPage;
import org.junit.jupiter.api.*;

public class BirthRegistrationTest {

    private UserRegistrationPage userRegistrationPage;
    private BirthRegistrationPage birthRegistrationPage;

    @BeforeEach
    public void setup() {
        WebDriverSingleton.getDriver().get("https://user:senlatest@regoffice.senla.eu/");

        userRegistrationPage = new UserRegistrationPage();
        birthRegistrationPage = new BirthRegistrationPage();
    }

    @Test
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