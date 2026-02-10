package org.example.api.sendAdminRequest;

import io.qameta.allure.*;
import org.example.dataFactory.TestDataFactory;
import org.example.models.AdminData;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("API")
@Feature("Регистрация администратора")
public class SendAdminRequestNegativeTests {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Не все поля в теле запроса")
    @DisplayName("Ошибка регистрации администратора из-за отсутствия всех полей в теле запроса")
    @Description("Проверка того, что запрос на регистрацию администратора из-за отсутствия всех полей в теле запроса возвращает 400 статус")
    public void sendAdminRequestWithoutAllFieldsTest() {
        AdminData admin = TestDataFactory.createAdminWithoutNameForAPI().build();

        given()
                .spec(RequestSpecs.requestSpec())
                .body(admin)
                .when()
                .post("/sendAdminRequest")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(400));
    }

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Пустое тело запроса")
    @DisplayName("Ошибка при отправке запроса на регистрацию администратора с пустым телом")
    @Description("Проверка того, что запрос на регистрацию админа с пустым телом возвращает 400 статус")
    public void sendEmptyAdminRequestTest() {
        given()
                .spec(RequestSpecs.requestSpec())
                .when()
                .post("/sendAdminRequest")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(400));
    }

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Неавторизованный запрос")
    @DisplayName("Ошибка создания администратора без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedAdminRequestTest() {
        AdminData admin = TestDataFactory.createAdminForAPI();

        given()
                .spec(RequestSpecs.unauthorizedRequestSpec())
                .body(admin)
                .when()
                .post("/sendAdminRequest")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(401));
    }
}