package admin;

import org.example.dataFactory.TestDataFactory;
import org.example.driver.WebDriverSingleton;
import org.example.models.AdminData;
import org.example.pages.AdminPage;
import org.junit.jupiter.api.*;

public class GetStatusByApplicationNumberTest {

    private AdminPage adminPage;

    @BeforeEach
    public void setup() {
        WebDriverSingleton.getDriver().get("https://user:senlatest@regoffice.senla.eu/");

        adminPage = new AdminPage();
    }

    @Test
    @DisplayName("Регистрация администратора: проверка статуса заявки по её номеру")
    public void testAdminCheck() {

        AdminData admin = TestDataFactory.createDefaultAdmin();

        //Пока не дошли до API, то номер захардкодил, потом будет просто его сюда передать.
        String applicationNumber = "58007";

        adminPage.StartRegistration();
        adminPage.FillAdminForm(admin);
        adminPage.nextStep().click();

        String actualStatus = adminPage.getStatusByApplicationNumber(applicationNumber);

        Assertions.assertEquals("На рассмотрении", actualStatus,
                "Статус заявки №" + applicationNumber + " не совпадает!");
    }

    @AfterEach
    public void tearDown() {
        WebDriverSingleton.quit();
    }
}
