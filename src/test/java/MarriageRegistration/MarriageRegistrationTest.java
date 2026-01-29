package MarriageRegistration;

import org.example.driver.WebDriverSingleton;
import org.example.models.CitizenData;
import org.example.models.MarriageRegistrationServiceData;
import org.example.models.UserData;
import org.example.pages.MarriageRegistrationPage;
import org.example.pages.UserRegistrationPage;
import org.junit.jupiter.api.*;

public class MarriageRegistrationTest {

    @BeforeEach
    public void setup() {
        WebDriverSingleton.getDriver().get("https://user:senlatest@regoffice.senla.eu/");
    }

    @Test
    @DisplayName("Регистрация брака: успешное заполнение всех форм!")
    public void testSuccessfulMarriageRegistration() {
        UserData user = new UserData(
                "Иванов",
                "Иван",
                "Иванович",
                "375297777777",
                "АВ1234567",
                "г. Брест");

        CitizenData citizen = new CitizenData(
                "Иванов",
                "Иван",
                "Иванович",
                "23072004",
                "АВ1234567",
                "male",
                "г. Брест");

        MarriageRegistrationServiceData serviceData = new MarriageRegistrationServiceData(
                "29012026",
                "Иванова",
                "Иванов",
                "Иван",
                "Иванович",
                "27012004",
                "AB1234567");

        UserRegistrationPage userRegistrationPage = new UserRegistrationPage();
        userRegistrationPage.StartRegistration();
        userRegistrationPage.FillUserForm(user);
        userRegistrationPage.nextStep().click();

        MarriageRegistrationPage marriageRegistrationPage = new MarriageRegistrationPage();
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