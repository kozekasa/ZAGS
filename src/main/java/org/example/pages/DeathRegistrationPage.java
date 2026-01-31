package org.example.pages;

import org.example.driver.WebDriverSingleton;
import org.example.elements.NavigationButton;
import org.example.elements.StatusField;
import org.example.models.CitizenData;
import org.example.models.DeathRegistrationServiceData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeathRegistrationPage extends BasePage {

    public DeathRegistrationPage() {
        PageFactory.initElements(WebDriverSingleton.getDriver(), this);
    }

    @FindBy(xpath = "//button[text()='Регистрация смерти']")
    private WebElement deathRegistrationButton;

    @FindBy(xpath = "//label[contains(., 'Фамилия')]/../../input")
    private WebElement surnameField;

    @FindBy(xpath = "//label[contains(., 'Имя')]/../../input")
    private WebElement nameField;

    @FindBy(xpath = "//label[contains(., 'Отчество')]/../../input")
    private WebElement patronymicField;

    @FindBy(xpath = "//label[contains(., 'Дата рождения')]/../../input")
    private WebElement dateOfBirthField;

    @FindBy(xpath = "//label[contains(., 'Номер паспорта')]/../../input")
    private WebElement passportNumberField;

    @FindBy(xpath = "//label[contains(., 'Пол')]/../../input")
    private WebElement sexField;

    @FindBy(xpath = "//label[contains(., 'Адрес прописки')]/../../input")
    private WebElement registrationAddressField;

    @FindBy(xpath = "//*[text()='Далее']")
    private WebElement nextPageButton;

    @FindBy(xpath = "//label[contains(., 'Дата смерти')]/../../input")
    private WebElement dateOfDeath;

    @FindBy(xpath = "//label[contains(., 'Место смерти')]/../../input")
    private WebElement placeOfDeath;

    @FindBy(xpath = "//*[text()='Завершить']")
    private WebElement finishButton;

    @FindBy(xpath = "//*[text()='На рассмотрении']")
    private WebElement statusLabel;

    public void chooseDeathRegistration() {
        deathRegistrationButton.click();
    }

    public void fillCitizenForm(CitizenData citizen) {
        fillField(surnameField, citizen.getSurname());
        fillField(nameField, citizen.getName());
        fillField(patronymicField, citizen.getPatronymic());
        fillField(dateOfBirthField, citizen.getDateOfBirth());
        fillField(passportNumberField, citizen.getPassportNumber());
        fillField(sexField, citizen.getSex());
        fillField(registrationAddressField, citizen.getRegistrationAddress());
    }

    public NavigationButton nextStep() {
        return new NavigationButton(nextPageButton);
    }

    public void fillDeathRegistrationServiceForm(DeathRegistrationServiceData serviceData) {
        fillField(dateOfDeath, serviceData.getDateOfDeath());
        fillField(placeOfDeath, serviceData.getPlaceOfDeath());
    }

    public NavigationButton finishButton() {
        return new NavigationButton(finishButton);
    }

    public StatusField applicationStatus() {
        return new StatusField(statusLabel);
    }
}

