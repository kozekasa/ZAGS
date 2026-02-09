package org.example.api.negativeTests.unathorized;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.dataFactory.TestDataFactory;
import org.example.models.UserDataAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@Epic("API")
@Feature("Негативные тесты")
@Story("Авторизация")
public class SendUnauthorizedUserRequestTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Ошибка создания заявки без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedUserRequestTest() {
        UserDataAPI userRequest = TestDataFactory.createBirthRegistrationAPIRequest();

        Allure.step("Отправка POST запроса на /sendUserRequest", () -> {
            given()
                    .spec(Specs.requestSpec())
                    .body(userRequest)
                    .when()
                    .post("/sendUserRequest")
                    .then()
                    .spec(Specs.responseSpec401());
        });
    }
}