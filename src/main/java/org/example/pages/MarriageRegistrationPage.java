package org.example.pages;

import org.example.driver.WebDriverSingleton;
import org.example.elements.CustomInput;
import org.example.elements.NavigationButton;
import org.example.elements.StatusField;
import org.example.models.CitizenData;
import org.example.models.MarriageRegistrationServiceData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarriageRegistrationPage extends BasePage {

    public MarriageRegistrationPage() {
        PageFactory.initElements(WebDriverSingleton.getDriver(), this);
    }

    @FindBy(xpath = "//button[text()=\"Регистрация брака\"]")
    private WebElement marriageRegistrationButton;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Фамилия')]/@for)]")
    private WebElement surnameField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Имя')]/@for)]")
    private WebElement nameField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Отчество')]/@for)]")
    private WebElement patronymicField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Дата рождения')]/@for)]")
    private WebElement dateOfBirthField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Номер паспорта')]/@for)]")
    private WebElement passportNumberField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Пол')]/@for)]")
    private WebElement sexField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Адрес прописки')]/@for)]")
    private WebElement registrationAddressField;

    @FindBy(xpath = "//*[text()=\"Далее\"]")
    private WebElement nextPageButton;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Дата регистрации')]/@for)]")
    private WebElement dateOfRegistration;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Новая фамилия')]/@for)]")
    private WebElement newSurname;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Фамилия супруга/и')]/@for)]")
    private WebElement spouseSurname;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Имя супруга/и')]/@for)]")
    private WebElement spouseName;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Отчество супруга/и')]/@for)]")
    private WebElement spousePatronymic;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Дата рождения супруга/и')]/@for)]")
    private WebElement spouseDateOfBirth;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Номер паспорта супруга/и')]/@for)]")
    private WebElement spousePassportNumber;

    @FindBy(xpath = "//*[text()=\"Завершить\"]")
    private WebElement finishButton;

    @FindBy(xpath = "//*[text()=\"На рассмотрении\"]")
    private WebElement statusLabel;

    public void chooseMarriageRegistration() {
        marriageRegistrationButton.click();
    }

    public void fillCitizenForm(CitizenData citizen) {
        new CustomInput(surnameField).fillEndEnter(citizen.getSurname());
        new CustomInput(nameField).fillEndEnter(citizen.getName());
        new CustomInput(patronymicField).fillEndEnter(citizen.getPatronymic());
        new CustomInput(dateOfBirthField).fillEndEnter(citizen.getDateOfBirth());
        new CustomInput(passportNumberField).fillEndEnter(citizen.getPassportNumber());
        new CustomInput(sexField).fillEndEnter(citizen.getSex());
        new CustomInput(registrationAddressField).fillEndEnter(citizen.getRegistrationAddress());
    }

    public NavigationButton nextStep() {
        return new NavigationButton(nextPageButton);
    }

    public void fillMarriageRegistrationServiceForm(MarriageRegistrationServiceData serviceData) {
        new CustomInput(dateOfRegistration).fillEndEnter(serviceData.getDateOfRegistration());
        new CustomInput(newSurname).fillEndEnter(serviceData.getNewSurname());
        new CustomInput(spouseSurname).fillEndEnter(serviceData.getSpouseSurname());
        new CustomInput(spouseName).fillEndEnter(serviceData.getSpouseName());
        new CustomInput(spousePatronymic).fillEndEnter(serviceData.getSpousePatronymic());
        new CustomInput(spouseDateOfBirth).fillEndEnter(serviceData.getSpouseDateOfBirth());
        new CustomInput(spousePassportNumber).fillEndEnter(serviceData.getSpousePassportNumber());
    }

    public NavigationButton finishButton() {
        return new NavigationButton(finishButton);
    }

    public StatusField applicationStatus() {
        return new StatusField(statusLabel);
    }
}
