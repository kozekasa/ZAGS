package org.example.api.negativeTests.unathorized;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.api.UsefulAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@Epic("API")
@Feature("Негативные тесты")
@Story("Авторизация")
public class SendUnauthorizedGetApplStatusTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Ошибка создания получения статуса заявки без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedGetApplStatusTest() {

        int appId = UsefulAPI.createApplicationAndGetIntId();

        Allure.step("Запрос статуса заявки №" + appId, () -> {
            given()
                    .spec(Specs.requestSpec())
                    .pathParam("appid", appId)
                    .when()
                    .get("/getApplStatus/{appid}")
                    .then()
                    .spec(Specs.responseSpec401());
        });
    }
}