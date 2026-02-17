package org.example.api.requestProcess;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.models.UserDataAPI;
import org.example.specs.RequestSpecs;
import org.example.ApiPreconditions;
import org.example.dataFactory.TestDataFactory;
import org.example.models.RequestProcessData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@Epic("API")
@Feature("Управление статусом заявки")
public class RequestProcessNegativeTest {

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Неавторизованный запрос")
    @DisplayName("Ошибка изменения статуса заявки без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedRequestProcessTest() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest().build();

        int appId = ApiPreconditions.createApplicationAndGetIntId(userRequest);

        int staffId = ApiPreconditions.createStaffAndGetId();

        RequestProcessData approveData = TestDataFactory.createRequestStatus(appId, staffId, "approved");

        Response response = given()
                .spec(RequestSpecs.unauthorizedRequestSpec())
                .body(approveData)
                .when()
                .post("/requestProcess")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(401, response.getStatusCode(),"Код ответа должен быть 401 Unauthorized");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Пустое тело запроса")
    @DisplayName("Ошибка при отправке запроса на изменение статуса заявки с пустым телом")
    @Description("Проверка того, что запрос на изменение статуса заявки с пустым телом возвращает 400 статус")
    public void sendEmptyRequestProcessTest() {
        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .when()
                .post("/requestProcess")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(400, response.getStatusCode(), "Код ответа должен быть 400 Bad Request");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Не все поля в теле запроса")
    @DisplayName("Ошибка изменения статуса заявки из-за отсутствия всех полей в теле запроса")
    @Description("Проверка того, что запрос на изменение статуса заявки из-за отсутствия всех полей в теле запроса возвращает 500 статус")
    public void sendRequestProcessWithoutAllFieldsTest() {

        int staffId = ApiPreconditions.createStaffAndGetId();

        RequestProcessData requestData = TestDataFactory.createRequestWithoutApplidBuilder(staffId, "approved").build();

        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .body(requestData)
                .when()
                .post("/requestProcess")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(500, response.getStatusCode(), "Код ответа должен быть 500 Internal Server Error");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Невалидные данные в теле запроса")
    @DisplayName("Ошибка изменения статуса заявки из-за невалидных данных в теле запроса")
    @Description("Проверка того, что запрос на изменение статуса заявки из-за невалидного статуса заявки в теле запроса возвращает 500 статус")
    public void sendRequestProcessWithInvalidDataTest() {

        int staffId = ApiPreconditions.createStaffAndGetId();

        RequestProcessData requestData = TestDataFactory.createRequestWithoutApplidBuilder(staffId, "not approved").build();

        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .body(requestData)
                .when()
                .post("/requestProcess")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(500, response.getStatusCode(), "Код ответа должен быть 500 Internal Server Error");
    }
}