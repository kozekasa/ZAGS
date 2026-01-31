package org.example.pages;

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

    @FindBy(xpath = "//div/button[text()='Войти как администратор']")
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

    public void StartRegistration() {
        loginAsAdminButton.click();
    }

    public void FillAdminForm(AdminData admin) {
        fillField(surnameField, admin.getSurname());
        fillField(nameField, admin.getName());
        fillField(patronymicField, admin.getPatronymic());
        fillField(telephoneNumberField, admin.getTelephoneNumber());
        fillField(passportNumberField, admin.getPassportNumber());
        fillField(dateOfBirthField, admin.getDateOfBirth());
    }

    public NavigationButton nextStep() {
        return new NavigationButton(nextPageButton);
    }

    public String getStatusByApplicationNumber(String applicationNumber) {
        String xpath = String.format("//tr[td[text()='%s']]//td[contains(., 'рассмотрении')]", applicationNumber);

        WebElement statusCell = WebDriverSingleton.getDriver().findElement(By.xpath(xpath));
        return new StatusField(statusCell).getText();
    }
}

