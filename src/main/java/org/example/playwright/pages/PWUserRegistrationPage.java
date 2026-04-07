package org.example.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.example.models.UserData;

public class PWUserRegistrationPage extends PWBasePage {

    private final Locator loginAsUserButton;
    private final Locator nextButton;

    private final String surname = "//label[contains(., 'Фамилия')]/../../input";
    private final String name = "//label[contains(., 'Имя')]/../../input";
    private final String patronymic = "//label[contains(., 'Отчество')]/../../input";
    private final String phone = "//label[contains(., 'Телефон')]/../../input";
    private final String passport = "//label[contains(., 'Номер паспорта')]/../../input";
    private final String address = "//label[contains(., 'Адрес прописки')]/../../input";

    public PWUserRegistrationPage(Page page) {
        super(page);
        this.loginAsUserButton = page.locator("//div/button[text()='Войти как пользователь']");
        this.nextButton = page.locator("//*[text()='Далее']");
    }

    @Step("[PW] Нажатие кнопки: Войти как пользователь")
    public PWUserRegistrationPage startRegistration() {
        loginAsUserButton.click();
        return this;
    }

    @Step("[PW] Заполнение формы: Данные заявителя")
    public PWUserRegistrationPage fillUserForm(UserData user) {
        setValue(surname, user.getSurname());
        setValue(name, user.getName());
        setValue(patronymic, user.getPatronymic());
        setValue(phone, user.getTelephoneNumber());
        setValue(passport, user.getPassportNumber());
        setValue(address, user.getRegistrationAddress());
        return this;
    }

    @Step("[PW] Нажатие кнопки: Далее")
    public PWUserRegistrationPage clickNext() {
        nextButton.click();
        return this;
    }
}