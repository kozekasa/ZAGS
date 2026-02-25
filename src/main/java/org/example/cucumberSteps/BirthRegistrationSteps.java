package org.example.cucumberSteps;

import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.example.dataFactory.TestDataFactory;
import org.example.models.*;
import org.example.pages.PageManager;
import org.junit.jupiter.api.Assertions;

@Slf4j
public class BirthRegistrationSteps {

    protected PageManager pages = new PageManager();

    private UserData user;
    private CitizenData citizen;
    private BirthRegistrationServiceData serviceData;

    @Given("Я нахожусь на странице регистрации пользователя")
    public void openRegistration() {
        user = TestDataFactory.createDefaultUser();
        citizen = TestDataFactory.createDefaultCitizen();
        serviceData = TestDataFactory.createBirthServiceData();

        log.info("Начало теста: Переход на страницу регистрации пользователя");
        pages.userRegistrationPage().StartRegistration();
    }

    @Given("Я заполняю данные пользователя и перехожу к выбору услуги")
    public void fillUserStep() {
        log.info("Заполнение формы пользователя для: {} {}", user.getSurname(), user.getPatronymic());
        pages.userRegistrationPage()
                .FillUserForm(user)
                .nextStep().click();
    }

    @When("Я выбираю регистрацию рождения и заполняю данные гражданина")
    public void fillCitizenStep() {
        log.info("Выбор услуги 'Регистрация рождения' и ввод данных гражданина");
        pages.birthRegistrationPage()
                .chooseBirthRegistration()
                .fillCitizenForm(citizen)
                .nextStep().click();
    }

    @When("Я заполняю данные свидетельства о рождении и завершаю регистрацию")
    public void fillServiceStep() {
        log.info("Заполнение формы услуги (ServiceData) и нажатие кнопки Финиш");
        pages.birthRegistrationPage()
                .fillBirthRegistrationServiceForm(serviceData)
                .finishButton().click();
    }

    @Then("Я должен увидеть статус заявки {string}")
    public void verifyStatus(String expectedStatus) {
        log.info("Проверка финального статуса заявки на UI. Ожидаем: {}", expectedStatus);
        String actualStatus = pages.birthRegistrationPage().applicationStatus().getText();

        log.debug("Получен текст из элемента статуса: '{}'", actualStatus);

        Assertions.assertEquals(expectedStatus, actualStatus,
                "Статус заявки не корректен или заявка не была создана!");
        log.info("Тест успешно завершен. Статус подтвержден.");
    }
}