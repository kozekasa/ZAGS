package org.example.api.negativeTests.unathorized;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@Epic("API")
@Feature("Негативные тесты")
@Story("Авторизация")
public class SendUnauthorizedGetApplicationsTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Ошибка создания получения списка заявок без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedGetApplicationsTest() {
        Allure.step("Отправка GET запроса на /getApplications", () -> {
            given()
                    .spec(Specs.requestSpec())
                    .when()
                    .get("/getApplications")
                    .then()
                    .spec(Specs.responseSpec401());
        });
    }
}