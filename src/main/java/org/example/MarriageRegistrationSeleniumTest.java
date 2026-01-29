package org.example;

import org.example.driver.WebDriverSingleton;
import org.example.elements.NavigationButton;
import org.example.models.CitizenData;
import org.example.models.MarriageRegistrationServiceData;
import org.example.models.UserData;
import org.example.pages.MarriageRegistrationPage;
import org.example.pages.UserRegistrationPage;

public class MarriageRegistrationSeleniumTest {

    public static void main(String[] args) {
        var driver = WebDriverSingleton.getDriver();

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

        try {
            driver.get("https://user:senlatest@regoffice.senla.eu/");

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

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            WebDriverSingleton.quit();
        }
    }
}