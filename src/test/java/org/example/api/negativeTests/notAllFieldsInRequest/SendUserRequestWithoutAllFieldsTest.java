package org.example.api.negativeTests.notAllFieldsInRequest;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.dataFactory.TestDataFactory;
import org.example.models.invalidData.UserDataWithoutNameFieldAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@Epic("API")
@Feature("Негативные тесты")
@Story("Отсутствие всех полей в теле запроса")
public class SendUserRequestWithoutAllFieldsTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Ошибка создания заявки из-за отсутствия всех полей в теле запроса")
    @Description("Проверка того, что запрос на создание заявки из-за отсутствия всех полей в теле запроса возвращает 500 статус")
    public void sendUserRequestWithoutAllFieldsTest() {
        UserDataWithoutNameFieldAPI userRequest = TestDataFactory.createInvalidBirthRegistrationAPIRequest();

        Allure.step("Отправка POST запроса на /sendUserRequest", () -> {
            given()
                    .spec(Specs.requestSpec())
                    .body(userRequest)
                    .when()
                    .post("/sendUserRequest")
                    .then()
                    .spec(Specs.responseSpec500());
        });
    }
}