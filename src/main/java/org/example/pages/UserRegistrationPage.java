package org.example.pages;

import org.example.driver.WebDriverSingleton;
import org.example.elements.CustomInput;
import org.example.elements.NavigationButton;
import org.example.models.UserData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserRegistrationPage {

    public UserRegistrationPage() {
        PageFactory.initElements(WebDriverSingleton.getDriver(), this);
    }

    @FindBy(xpath = "//div/button[text()=\"Войти как пользователь\"]")
    private WebElement loginAsUserButton;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Фамилия')]/@for)]")
    private WebElement surnameField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Имя')]/@for)]")
    private WebElement nameField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Отчество')]/@for)]")
    private WebElement patronymicField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Телефон')]/@for)]")
    private WebElement telephoneNumberField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Номер паспорта')]/@for)]")
    private WebElement passportNumberField;

    @FindBy(xpath = "//input[@id=(//label[contains(., 'Адрес прописки')]/@for)]")
    private WebElement registrationAddressField;

    @FindBy(xpath = "//*[text()=\"Далее\"]")
    private WebElement nextPageButton;

    public void StartRegistration() {
        loginAsUserButton.click();
    }

    public void FillUserForm(UserData user) {
        new CustomInput(surnameField).fillEndEnter(user.getSurname());
        new CustomInput(nameField).fillEndEnter(user.getName());
        new CustomInput(patronymicField).fillEndEnter(user.getPatronymic());
        new CustomInput(telephoneNumberField).fillEndEnter(user.getTelephoneNumber());
        new CustomInput(passportNumberField).fillEndEnter(user.getPassportNumber());
        new CustomInput(registrationAddressField).fillEndEnter(user.getRegistrationAddress());
    }

    public NavigationButton nextStep() {
        return new NavigationButton(nextPageButton);
    }
}
