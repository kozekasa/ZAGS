package org.example.pages;

import io.qameta.allure.Step;
import org.example.driver.WebDriverSingleton;
import org.example.elements.NavigationButton;
import org.example.models.UserData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserRegistrationPage extends BasePage {

    public UserRegistrationPage() {
        PageFactory.initElements(WebDriverSingleton.getDriverThreadLocal(), this);
    }

    @FindBy(xpath = "//div/button[text()='Войти как пользователь']")
    private WebElement loginAsUserButton;

    @FindBy(xpath = "//label[contains(., 'Фамилия')]/../../input")
    private WebElement surnameField;

    @FindBy(xpath = "//label[contains(., 'Имя')]/../../input")
    private WebElement nameField;

    @FindBy(xpath = "//label[contains(., 'Отчество')]/../../input")
    private WebElement patronymicField;

    @FindBy(xpath = "//label[contains(., 'Телефон')]/../../input")
    private WebElement telephoneNumberField;

    @FindBy(xpath = "//label[contains(., 'Номер паспорта')]/../../input")
    private WebElement passportNumberField;

    @FindBy(xpath = "//label[contains(., 'Адрес прописки')]/../../input")
    private WebElement registrationAddressField;

    @FindBy(xpath = "//*[text()='Далее']")
    private WebElement nextPageButton;

    @Step("Нажатие кнопки: Войти как пользователь")
    public UserRegistrationPage StartRegistration() {
        loginAsUserButton.click();
        return this;
    }

    @Step("Заполнение формы: Данные заявителя")
    public UserRegistrationPage FillUserForm(UserData user) {
        setValue(surnameField, user.getSurname());
        setValue(nameField, user.getName());
        setValue(patronymicField, user.getPatronymic());
        setValue(telephoneNumberField, user.getTelephoneNumber());
        setValue(passportNumberField, user.getPassportNumber());
        setValue(registrationAddressField, user.getRegistrationAddress());
        return this;
    }

    @Step("Нажатие кнопки: Далее")
    public  NavigationButton nextStep() {
        return new NavigationButton(nextPageButton);
    }
}
