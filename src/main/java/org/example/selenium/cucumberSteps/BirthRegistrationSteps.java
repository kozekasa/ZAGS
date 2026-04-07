package org.example.selenium.cucumberSteps;


import io.cucumber.java.ru.*;
import lombok.extern.slf4j.Slf4j;
import org.example.dataFactory.TestDataFactory;
import org.example.models.*;
import org.example.selenium.pages.PageManager;
import org.junit.jupiter.api.Assertions;

@Slf4j
public class BirthRegistrationSteps {

    protected PageManager pages = new PageManager();

    private UserData user;
    private CitizenData citizen;
    private BirthRegistrationServiceData serviceData;

    @Дано("открыта страница регистрации пользователя")
    public void openRegistration() {
        user = TestDataFactory.createDefaultUser();
        citizen = TestDataFactory.createDefaultCitizen();
        serviceData = TestDataFactory.createBirthServiceData();

        log.info("Начало теста: Переход на страницу регистрации пользователя");
        pages.userRegistrationPage().StartRegistration();
    }

    @И("заполнены данные пользователя для перехода к выбору услуги")
    public void fillUserStep() {
        log.info("Заполнение формы пользователя для: {} {}", user.getSurname(), user.getPatronymic());
        pages.userRegistrationPage()
                .FillUserForm(user)
                .nextStep().click();
    }

    @Когда("выбрана регистрация рождения и заполнены данные гражданина")
    public void fillCitizenStep() {
        log.info("Выбор услуги 'Регистрация рождения' и ввод данных гражданина");
        pages.birthRegistrationPage()
                .chooseBirthRegistration()
                .fillCitizenForm(citizen)
                .nextStep().click();
    }

    @И("внесены данные свидетельства о рождении и завершена регистрация")
    public void fillServiceStep() {
        log.info("Заполнение формы услуги и нажатие кнопки Финиш");
        pages.birthRegistrationPage()
                .fillBirthRegistrationServiceForm(serviceData)
                .finishButton().click();
    }

    @Тогда("отображается статус заявки {string}")
    public void verifyStatus(String expectedStatus) {
        log.info("Проверка финального статуса заявки. Ожидаем: {}", expectedStatus);
        String actualStatus = pages.birthRegistrationPage().applicationStatus().getText();

        log.debug("Получен текст из элемента статуса: '{}'", actualStatus);

        Assertions.assertEquals(expectedStatus, actualStatus,
                "Статус заявки некорректен или заявка не была создана!");
        log.info("Тест успешно завершен. Статус подтвержден.");
    }
}