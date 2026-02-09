package org.example.pages;

import io.qameta.allure.Step;
import org.example.driver.WebDriverSingleton;
import org.example.elements.NavigationButton;
import org.example.elements.StatusField;
import org.example.models.validData.CitizenData;
import org.example.models.validData.MarriageRegistrationServiceData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarriageRegistrationPage extends BasePage {

    public MarriageRegistrationPage() {
        PageFactory.initElements(WebDriverSingleton.getDriverThreadLocal(), this);
    }

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

    @FindBy(xpath = "//label[contains(., 'Дата регистрации')]/../../input")
    private WebElement dateOfRegistration;

    @FindBy(xpath = "//label[contains(., 'Новая фамилия')]/../../input")
    private WebElement newSurname;

    @FindBy(xpath = "//label[contains(., 'Фамилия супруга/и')]/../../input")
    private WebElement spouseSurname;

    @FindBy(xpath = "//label[contains(., 'Имя супруга/и')]/../../input")
    private WebElement spouseName;

    @FindBy(xpath = "//label[contains(., 'Отчество супруга/и')]/../../input")
    private WebElement spousePatronymic;

    @FindBy(xpath = "//label[contains(., 'Дата рождения супруга/и')]/../../input")
    private WebElement spouseDateOfBirth;

    @FindBy(xpath = "//label[contains(., 'Номер паспорта супруга/и')]/../../input")
    private WebElement spousePassportNumber;

    @FindBy(xpath = "//*[text()='Завершить']")
    private WebElement finishButton;

    @FindBy(xpath = "//*[text()='На рассмотрении']")
    private WebElement statusLabel;

    @FindBy(xpath = "//span[contains(., 'Ваша заявка №')]")
    private WebElement orderNumber;

    @Step("Нажатие кнопки: Регистрация брака")
    public MarriageRegistrationPage chooseMarriageRegistration() {
        marriageRegistrationButton.click();
        return this;
    }

    @Step("Заполнение формы: Данные гражданина")
    public MarriageRegistrationPage fillCitizenForm(CitizenData citizen) {
        setValue(surnameField, citizen.getSurname());
        setValue(nameField, citizen.getName());
        setValue(patronymicField, citizen.getPatronymic());
        setValue(dateOfBirthField, citizen.getDateOfBirth());
        setValue(passportNumberField, citizen.getPassportNumber());
        setValue(sexField, citizen.getSex());
        setValue(registrationAddressField, citizen.getRegistrationAddress());
        return this;
    }

    @Step("Нажатие кнопки: Далее")
    public NavigationButton nextStep() {
        return new NavigationButton(nextPageButton);
    }

    @Step("Заполнение формы: Данные услуги")
    public MarriageRegistrationPage fillMarriageRegistrationServiceForm(MarriageRegistrationServiceData serviceData) {
        setValue(dateOfRegistration, serviceData.getDateOfRegistration());
        setValue(newSurname, serviceData.getNewSurname());
        setValue(spouseSurname, serviceData.getSpouseSurname());
        setValue(spouseName, serviceData.getSpouseName());
        setValue(spousePatronymic, serviceData.getSpousePatronymic());
        setValue(spouseDateOfBirth, serviceData.getSpouseDateOfBirth());
        setValue(spousePassportNumber, serviceData.getSpousePassportNumber());
        return this;
    }

    @Step("Нажатие кнопки: Завершить")
    public NavigationButton finishButton() {
        return new NavigationButton(finishButton);
    }

    @Step("Получение статуса оформленной заявки")
    public StatusField applicationStatus() {
        return new StatusField(statusLabel);
    }
}
