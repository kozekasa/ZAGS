package org.example.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.example.models.BirthRegistrationServiceData;
import org.example.models.CitizenData;

public class PWBirthRegistrationPage extends PWBasePage {

    private final Locator chooseBirthRegistrationButton;
    private final Locator nextButton;
    private final Locator finishButton;

    private final String statusLabelSelector = "//*[text()='На рассмотрении']";

    private final String surname = "//label[contains(., 'Фамилия')]/../../input";
    private final String name = "//label[contains(., 'Имя')]/../../input";
    private final String patronymic = "//label[contains(., 'Отчество')]/../../input";
    private final String dateOfBirth = "//label[contains(., 'Дата рождения')]/../../input";
    private final String passport = "//label[contains(., 'Номер паспорта')]/../../input";
    private final String sex = "//label[contains(., 'Пол')]/../../input";
    private final String registrationAddress = "//label[contains(., 'Адрес прописки')]/../../input";

    private final String placeOfBirth = "//label[contains(., 'Место рождения')]/../../input";
    private final String mother = "//label[contains(., 'Мать')]/../../input";
    private final String father = "//label[contains(., 'Отец')]/../../input";
    private final String grandma = "//label[contains(., 'Бабушка')]/../../input";
    private final String grandpa = "//label[contains(., 'Дедушка')]/../../input";

    public PWBirthRegistrationPage(Page page) {
        super(page);
        this.chooseBirthRegistrationButton = page.locator("//button[text()='Регистрация рождения']");
        this.nextButton = page.locator("//*[contains(text(), 'Далее')]");
        this.finishButton = page.locator("//*[text()='Завершить']");
    }

    @Step("[PW] Нажатие кнопки: Регистрация рождения")
    public PWBirthRegistrationPage chooseBirthRegistration() {
        chooseBirthRegistrationButton.click();
        return this;
    }

    @Step("[PW] Заполнение формы: Данные гражданина")
    public PWBirthRegistrationPage fillCitizenForm(CitizenData citizen) {
        setValue(surname, citizen.getSurname());
        setValue(name, citizen.getName());
        setValue(patronymic, citizen.getPatronymic());
        setValue(dateOfBirth, citizen.getDateOfBirth());
        setValue(passport, citizen.getPassportNumber());
        setValue(sex, citizen.getSex());
        setValue(registrationAddress, citizen.getRegistrationAddress());
        return this;
    }

    @Step("[PW] Нажатие кнопки: Далее")
    public PWBirthRegistrationPage clickNext() {
        nextButton.click();
        return this;
    }

    @Step("[PW] Заполнение формы: Данные услуги")
    public PWBirthRegistrationPage fillBirthRegistrationServiceForm(BirthRegistrationServiceData data) {
        setValue(placeOfBirth, data.getPlaceOfBirth());
        setValue(mother, data.getMother());
        setValue(father, data.getFather());
        setValue(grandma, data.getGrandma());
        setValue(grandpa, data.getGrandpa());
        return this;
    }

    @Step("[PW] Нажатие кнопки: Завершить")
    public PWBirthRegistrationPage clickFinishButton() {
        finishButton.click();
        return this;
    }

    @Step("[PW] Получение статуса оформленной заявки")
    public String getApplicationStatus() {
        return page.locator(statusLabelSelector).innerText().trim();
    }
}
