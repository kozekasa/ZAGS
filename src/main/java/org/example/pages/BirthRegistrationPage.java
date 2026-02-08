package org.example.pages;

import io.qameta.allure.Step;
import org.example.driver.WebDriverSingleton;
import org.example.elements.NavigationButton;
import org.example.elements.StatusField;
import org.example.models.BirthRegistrationServiceData;
import org.example.models.CitizenData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirthRegistrationPage extends BasePage {

    public BirthRegistrationPage() {
        PageFactory.initElements(WebDriverSingleton.getDriverThreadLocal(), this);
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

    @Step("Нажатие кнопки: Регистрация рождения")
    public BirthRegistrationPage chooseBirthRegistration() {
        birthRegistrationButton.click();
        return this;
    }

    @Step("Заполнение формы: Данные гражданина")
    public BirthRegistrationPage fillCitizenForm(CitizenData citizen) {
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
    public BirthRegistrationPage fillBirthRegistrationServiceForm(BirthRegistrationServiceData serviceData) {
        setValue(placeOfBirth, serviceData.getPlaceOfBirth());
        setValue(mother, serviceData.getMother());
        setValue(father, serviceData.getFather());
        setValue(grandma, serviceData.getGrandma());
        setValue(grandpa, serviceData.getGrandpa());
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

