package org.example.api.sendAdminRequest;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.dataFactory.TestDataFactory;
import org.example.models.AdminData;
import org.example.selenium.specs.RequestSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("API")
@Feature("Регистрация администратора")
public class SendAdminRequestNegativeTests {

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Не все поля в теле запроса")
    @DisplayName("Ошибка регистрации администратора из-за отсутствия всех полей в теле запроса")
    @Description("Проверка того, что запрос на регистрацию администратора из-за отсутствия всех полей в теле запроса возвращает 400 статус")
    public void sendAdminRequestWithoutAllFieldsTest() {
        AdminData admin = TestDataFactory.createAdminWithoutNameForAPI().build();

        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .body(admin)
                .when()
                .post("/sendAdminRequest")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(400, response.getStatusCode(), "Код ответа должен быть 400 Bad Request");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Пустое тело запроса")
    @DisplayName("Ошибка при отправке запроса на регистрацию администратора с пустым телом")
    @Description("Проверка того, что запрос на регистрацию админа с пустым телом возвращает 400 статус")
    public void sendEmptyAdminRequestTest() {
        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .when()
                .post("/sendAdminRequest")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(400, response.getStatusCode(), "Код ответа должен быть 400 Bad Request");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Неавторизованный запрос")
    @DisplayName("Ошибка создания администратора без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedAdminRequestTest() {
        AdminData admin = TestDataFactory.createAdminForAPI();

        Response response = given()
                .spec(RequestSpecs.unauthorizedRequestSpec())
                .body(admin)
                .when()
                .post("/sendAdminRequest")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(401, response.getStatusCode(), "Код ответа должен быть 401 Unauthorized");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Невалидные данные в теле запроса")
    @DisplayName("Ошибка регистрации администратора из-за неверного формата данных в теле запроса")
    @Description("Проверка того, что запрос на регистрацию администратора из-за из-за неверного формата данных в поле дата рождения в теле запроса возвращает 500 статус")
    public void sendAdminRequestWithInvalidDataTest() {
        AdminData admin = TestDataFactory.createAdminWithInvalidDataForAPI().build();

        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .body(admin)
                .when()
                .post("/sendAdminRequest")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(500, response.getStatusCode(), "Код ответа должен быть 500 Internal Server Error");
    }
}