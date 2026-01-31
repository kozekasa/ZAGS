package org.example.pages;

import org.example.driver.WebDriverSingleton;
import org.example.elements.CustomInput;
import org.example.elements.NavigationButton;
import org.example.elements.StatusField;
import org.example.models.BirthRegistrationServiceData;
import org.example.models.CitizenData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirthRegistrationPage extends BasePage {

    public BirthRegistrationPage() {
        PageFactory.initElements(WebDriverSingleton.getDriver(), this);
    }

    @FindBy(xpath = "//button[text()='Регистрация рождения']")
    private WebElement birthRegistrationButton;

    @FindBy(xpath = "//button[text()='Регистрация брака']")
    private WebElement marriageRegistrationButton;

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

    @FindBy(xpath = "//label[contains(., 'Место рождения')]/../../input")
    private WebElement placeOfBirth;

    @FindBy(xpath = "//label[contains(., 'Мать')]/../../input")
    private WebElement mother;

    @FindBy(xpath = "//label[contains(., 'Отец')]/../../input")
    private WebElement father;

    @FindBy(xpath = "//label[contains(., 'Бабушка')]/../../input")
    private WebElement grandma;

    @FindBy(xpath = "//label[contains(., 'Дедушка')]/../../input")
    private WebElement grandpa;


    @FindBy(xpath = "//*[text()='Завершить']")
    private WebElement finishButton;

    @FindBy(xpath = "//*[text()='На рассмотрении']")
    private WebElement statusLabel;

    public void chooseBirthRegistration() {
        birthRegistrationButton.click();
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

    public void fillBirthRegistrationServiceForm(BirthRegistrationServiceData serviceData) {
        fillField(placeOfBirth, serviceData.getPlaceOfBirth());
        fillField(mother, serviceData.getMother());
        fillField(father, serviceData.getFather());
        fillField(grandma, serviceData.getGrandma());
        fillField(grandpa, serviceData.getGrandpa());
    }

    public NavigationButton finishButton() {
        return new NavigationButton(finishButton);
    }

    public StatusField applicationStatus() {
        return new StatusField(statusLabel);
    }
}

