package org.example.api.getApplStatus;

import io.qameta.allure.*;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.example.UsefulAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("Получение статуса заявки")
public class GetApplStatusPositiveTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Получение статуса конкретной заявки по ID")
    @Description("Проверка эндпоинта /getApplStatus/{appid} для свежесозданной заявки")
    public void getApplStatusAPITest() {

        int appId = UsefulAPI.createApplicationAndGetIntId();

        given()
                .spec(RequestSpecs.requestSpec())
                .pathParam("appid", appId)
                .when()
                .get("/getApplStatus/{appid}")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .body("data.statusofapplication", is("under consideration"));
    }
}