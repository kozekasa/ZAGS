package org.example.pages;

import org.example.driver.WebDriverSingleton;
import org.example.elements.NavigationButton;
import org.example.models.UserData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserRegistrationPage extends BasePage {

    public UserRegistrationPage() {
        PageFactory.initElements(WebDriverSingleton.getDriver(), this);
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

    public void StartRegistration() {
        loginAsUserButton.click();
    }

    public void FillUserForm(UserData user) {
        fillField(surnameField, user.getSurname());
        fillField(nameField, user.getName());
        fillField(patronymicField, user.getPatronymic());
        fillField(telephoneNumberField, user.getTelephoneNumber());
        fillField(passportNumberField, user.getPassportNumber());
        fillField(registrationAddressField, user.getRegistrationAddress());
    }

    public NavigationButton nextStep() {
        return new NavigationButton(nextPageButton);
    }
}
