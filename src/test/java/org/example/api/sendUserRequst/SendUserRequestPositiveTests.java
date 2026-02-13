package org.example.api.sendUserRequst;

import io.qameta.allure.*;
import io.restassured.common.mapper.TypeRef;
import org.example.dataFactory.TestDataFactory;
import org.example.models.ApplicationData;
import org.example.models.BaseResponse;
import org.example.models.UserDataAPI;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Epic("API")
@Feature("Оформление заявки")
public class SendUserRequestPositiveTests {


    @Test
    @Owner("Aleksandr")
    @Tag("positive")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Создание заявки: Регистрация рождения")
    @DisplayName("Успешное создание новой заявки: Регистрация рождения")
    @Description("Проверка эндпоинта /sendUserRequest, создание заявки: Регистрация рождения")
    public void testBirthRegistrationAPIRequest() {
        UserDataAPI userRequest = TestDataFactory.createBirthRegistrationAPIRequest().build();

        ApplicationData response = given()
                .spec(RequestSpecs.requestSpec())
                .body(userRequest)
                .when()
                .post("/sendUserRequest")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .extract()
                .as(new TypeRef<BaseResponse<ApplicationData>>() {})
                .getData();

        Assertions.assertNotNull(response.getApplicationid(), "ID заявки не должен быть равен 0");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("positive")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Создание заявки: Регистрация брака")
    @DisplayName("Успешное создание новой заявки: Регистрация брака")
    @Description("Проверка эндпоинта /sendUserRequest, создание заявки: Регистрация брака")
    public void testMarriageRegistrationAPIRequest() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest().build();

        ApplicationData response = given()
                .spec(RequestSpecs.requestSpec())
                .body(userRequest)
                .when()
                .post("/sendUserRequest")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .extract()
                .as(new TypeRef<BaseResponse<ApplicationData>>() {})
                .getData();

        Assertions.assertNotNull(response.getApplicationid(), "ID заявки не должен быть равен 0");
    }

    @Test
    @Owner("Aleksandr")
    @Tag("positive")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Создание заявки: Регистрация смерти")
    @DisplayName("Успешное создание новой заявки: Регистрация смерти")
    @Description("Проверка эндпоинта /sendUserRequest, создание заявки: Регистрация смерти")
    public void testDeathRegistrationAPIRequest() {
        UserDataAPI userRequest = TestDataFactory.createDeathRegistrationAPIRequest().build();

        ApplicationData response = given()
                .spec(RequestSpecs.requestSpec())
                .body(userRequest)
                .when()
                .post("/sendUserRequest")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .extract()
                .as(new TypeRef<BaseResponse<ApplicationData>>() {})
                .getData();

        Assertions.assertNotNull(response.getApplicationid(), "ID заявки не должен быть равен 0");
    }
}