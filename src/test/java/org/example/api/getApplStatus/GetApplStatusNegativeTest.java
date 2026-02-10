package org.example.api.getApplStatus;

import io.qameta.allure.*;
import org.example.UsefulAPI;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@Epic("API")
@Feature("Получение статуса заявки")
public class GetApplStatusNegativeTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Пустое тело запроса")
    @DisplayName("Ошибка при отправке запроса на получение статуса заявки с пустым телом")
    @Description("Проверка того, что запрос на получение статуса заявки с пустым телом возвращает 400 статус")
    public void sendEmptyGetApplStatusTest() {
        given()
                .spec(RequestSpecs.requestSpec())
                .when()
                .post("/getApplStatus")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(404));
    }

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Неавторизованный запрос")
    @DisplayName("Ошибка создания получения статуса заявки без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedGetApplStatusTest() {

        int appId = UsefulAPI.createApplicationAndGetIntId();

        given()
                .spec(RequestSpecs.unauthorizedRequestSpec())
                .pathParam("appid", appId)
                .when()
                .get("/getApplStatus/{appid}")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(401));
    }
}