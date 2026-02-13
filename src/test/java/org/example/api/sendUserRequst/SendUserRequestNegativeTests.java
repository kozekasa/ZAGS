package org.example.api.sendUserRequst;

import io.qameta.allure.*;
import io.restassured.response.Response;
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
@Feature("Оформление заявки")
public class SendUserRequestNegativeTests {

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Пустое тело запроса")
    @DisplayName("Ошибка при отправке запроса на создание заявки с пустым телом")
    @Description("Проверка того, что запрос на создание заявки с пустым телом возвращает 400 статус")
    public void sendEmptyUserRequestTest() {

        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .when()
                .post("/sendUserRequest")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(400))
                .extract()
                .response();

        Assertions.assertEquals(400, response.getStatusCode(), "Код ответа должен быть 400 Bad Request");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Неавторизованный запрос")
    @DisplayName("Ошибка создания заявки без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedUserRequestTest() {
        UserDataAPI userRequest = TestDataFactory.createBirthRegistrationAPIRequest().build();

        Response response = given()
                .spec(RequestSpecs.unauthorizedRequestSpec())
                .body(userRequest)
                .when()
                .post("/sendUserRequest")
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
    @Story("Не все поля в теле запроса")
    @DisplayName("Ошибка создания заявки из-за отсутствия всех полей в теле запроса")
    @Description("Проверка того, что запрос на создание заявки из-за отсутствия всех полей в теле запроса возвращает 400 статус")
    public void sendUserRequestWithoutAllFieldsTest() {
        UserDataAPI userRequest = TestDataFactory.createInvalidBirthRegistrationAPIRequest().build();

        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .body(userRequest)
                .when()
                .post("/sendUserRequest")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(400))
                .extract()
                .response();

        Assertions.assertEquals(400, response.getStatusCode(), "Код ответа должен быть 400 Bad Request");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Невалидные данные в поле фамилия")
    @DisplayName("Ошибка создания заявки из-за превышения допустимого количества символов в поле")
    @Description("Проверка того, что запрос на создание заявки из-за превышения допустимой длины символов в поле возвращает 500 статус")
    public void sendUserRequestWithInvalidDataTest() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationWithInvalidDataAPIRequest().build();

        Response response = given()
                .spec(RequestSpecs.requestSpec())
                .body(userRequest)
                .when()
                .post("/sendUserRequest")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(500))
                .extract()
                .response();

        Assertions.assertEquals(500, response.getStatusCode(), "Код ответа должен быть 500 Internal Server Error");
    }
}
