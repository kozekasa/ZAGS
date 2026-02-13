package org.example.api.getApplStatus;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.ApiPreconditions;
import org.example.dataFactory.TestDataFactory;
import org.example.models.UserDataAPI;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@Epic("API")
@Feature("Получение статуса заявки")
public class GetApplStatusNegativeTest {

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Пустое тело запроса")
    @DisplayName("Ошибка при отправке запроса на получение статуса заявки с пустым телом")
    @Description("Проверка того, что запрос на получение статуса заявки с пустым телом возвращает 404 статус")
    public void sendEmptyGetApplStatusTest() {
        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .when()
                .get("/getApplStatus")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(404))
                .extract()
                .response();

        Assertions.assertEquals(404, response.getStatusCode(), "Код ответа должен быть 404 Not found");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Неавторизованный запрос")
    @DisplayName("Ошибка создания получения статуса заявки без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedGetApplStatusTest() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest().build();

        int appId = ApiPreconditions.createApplicationAndGetIntId(userRequest);

        Response response = given()
                .spec(RequestSpecs.unauthorizedRequestSpec())
                .pathParam("appid", appId)
                .when()
                .get("/getApplStatus/{appid}")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(401))
                .extract()
                .response();

        Assertions.assertEquals(401, response.getStatusCode(), "Код ответа должен быть 401 Unauthorized");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Невалидные данные в теле запроса")
    @DisplayName("Ошибка при отправке запроса на получение статуса заявки с невалидными данными в теле запроса")
    @Description("Проверка того, что запрос на получение статуса заявки с текстом вместо номера заявки в теле запроса возвращает 500 статус")
    public void sendEmptyGetApplStatusWithInvalidDataTest() {
        String appId = "number";

        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .pathParam("appid", appId)
                .when()
                .get("/getApplStatus/{appid}")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(500))
                .extract()
                .response();

        Assertions.assertEquals(500, response.getStatusCode(), "Код ответа должен быть 500 Internal Server Error");
    }
}