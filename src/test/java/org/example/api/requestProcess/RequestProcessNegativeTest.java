package org.example.api.requestProcess;

import io.qameta.allure.*;
import org.example.specs.RequestSpecs;
import org.example.UsefulAPI;
import org.example.dataFactory.TestDataFactory;
import org.example.models.RequestProcessData;
import org.example.specs.ResponseSpecs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


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
        int appId = UsefulAPI.createApplicationAndGetIntId();

        int staffId = UsefulAPI.createStaffAndGetId();

        RequestProcessData approveData = TestDataFactory.createRequestStatus(appId, staffId, "approved");

        given()
                .spec(RequestSpecs.unauthorizedRequestSpec())
                .body(approveData)
                .when()
                .post("/requestProcess")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(401));
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Пустое тело запроса")
    @DisplayName("Ошибка при отправке запроса на изменение статуса заявки с пустым телом")
    @Description("Проверка того, что запрос на изменение статуса заявки с пустым телом возвращает 400 статус")
    public void sendEmptyRequestProcessTest() {
        given()
                .spec(RequestSpecs.requestSpec())
                .when()
                .post("/requestProcess")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(400));
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Не все поля в теле запроса")
    @DisplayName("Ошибка изменения статуса заявки из-за отсутствия всех полей в теле запроса")
    @Description("Проверка того, что запрос на изменение статуса заявки из-за отсутствия всех полей в теле запроса возвращает 400 статус")
    public void sendRequestProcessWithoutAllFieldsTest() {

        int staffId = UsefulAPI.createStaffAndGetId();

        RequestProcessData requestData = TestDataFactory.createRequestWithoutApplidBuilder(staffId, "approved").build();

        given()
                .spec(RequestSpecs.requestSpec())
                .body(requestData)
                .when()
                .post("/requestProcess")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(500));
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Невалидные данные в теле запроса")
    @DisplayName("Ошибка изменения статуса заявки из-за невалидных данных в теле запроса")
    @Description("Проверка того, что запрос на изменение статуса заявки из-за невалидного статуса заявки в теле запроса возвращает 500 статус")
    public void sendRequestProcessWithInvalidDataTest() {

        int staffId = UsefulAPI.createStaffAndGetId();

        RequestProcessData requestData = TestDataFactory.createRequestWithoutApplidBuilder(staffId, "not approved").build();

        given()
                .spec(RequestSpecs.requestSpec())
                .body(requestData)
                .when()
                .post("/requestProcess")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(500));
    }
}