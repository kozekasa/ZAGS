package org.example.api.positiveTests.user;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.dataFactory.TestDataFactory;
import org.example.models.UserDataAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Epic("API")
@Feature("Позитивные тесты")
@Story("Создание заявки: Регистрация смерти")
public class DeathRegistrationAPITest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешное создание новой заявки: Регистрация смерти")
    @Description("Проверка эндпоинта /sendUserRequest, создание заявки: Регистрация смерти")
    public void testDeathRegistrationAPIRequest() {
        UserDataAPI userRequest = TestDataFactory.createDeathRegistrationAPIRequest();

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