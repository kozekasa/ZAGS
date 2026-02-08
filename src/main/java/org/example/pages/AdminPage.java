package org.example.pages;

import io.qameta.allure.Step;
import org.example.driver.WebDriverSingleton;
import org.example.elements.NavigationButton;
import org.example.elements.StatusField;
import org.example.models.AdminData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends BasePage {

    public AdminPage() {
        PageFactory.initElements(WebDriverSingleton.getDriver(), this);
    }

    @FindBy(xpath = "//button[text()='Войти как администратор']")
    private WebElement loginAsAdminButton;

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

    @FindBy(xpath = "//label[contains(., 'Дата рождения')]/../../input")
    private WebElement dateOfBirthField;

    @FindBy(xpath = "//*[text()='Далее']")
    private WebElement nextPageButton;

    @Step("Нажатие кнопки: Войти как администратор")
    public AdminPage StartRegistration() {
        loginAsAdminButton.click();
        return this;
    }

    @Step("Заполнение формы: Данные регистрации")
    public AdminPage FillAdminForm(AdminData admin) {
        setValue(surnameField, admin.getSurname());
        setValue(nameField, admin.getName());
        setValue(patronymicField, admin.getPatronymic());
        setValue(telephoneNumberField, admin.getTelephoneNumber());
        setValue(passportNumberField, admin.getPassportNumber());
        setValue(dateOfBirthField, admin.getDateOfBirth());
        return this;
    }

    @Step("Нажатие кнопки: Далее")
    public NavigationButton nextStep() {
        return new NavigationButton(nextPageButton);
    }

    @Step("Поиск статуса заявки по её номеру")
    public String getStatusByApplicationNumber(String applicationNumber) {
        String xpath = String.format("//tr[td[text()='%s']]//td[contains(., 'рассмотрении')]", applicationNumber);

        WebElement statusCell = WebDriverSingleton.getDriver().findElement(By.xpath(xpath));
        return new StatusField(statusCell).getText();
    }
}

