package org.example.api.positiveTests.user;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.dataFactory.TestDataFactory;
import org.example.models.validData.UserDataAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Epic("API")
@Feature("Позитивные тесты")
@Story("Создание заявки: Регистрация рождения")
public class BirthRegistrationAPITest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешное создание новой заявки: Регистрация рождения")
    @Description("Проверка эндпоинта /sendUserRequest, создание заявки: Регистрация рождения")
    public void testBirthRegistrationAPIRequest() {
        UserDataAPI userRequest = TestDataFactory.createBirthRegistrationAPIRequest();

        Allure.step("Отправка POST запроса на /sendUserRequest", () -> {
            given()
                    .spec(Specs.requestSpec())
                    .body(userRequest)
                    .when()
                    .post("/sendUserRequest")
                    .then()
                    .spec(Specs.responseSpecOK200())
                    .body("data.applicationid", notNullValue());
        });
    }
}