package org.example.api.positiveTests;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.api.UsefulAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("Позитивные тесты")
@Story("Просмотр статуса заявки")
public class GetApplStatusAPITest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Получение статуса конкретной заявки по ID")
    @Description("Проверка эндпоинта /getApplStatus/{appid} для свежесозданной заявки")
    public void getApplStatusAPITest() {

        int appId = UsefulAPI.createApplicationAndGetIntId();

        Allure.step("Запрос статуса заявки №" + appId, () -> {
            given()
                    .spec(Specs.requestSpec())
                    .pathParam("appid", appId)
                    .when()
                    .get("/getApplStatus/{appid}")
                    .then()
                    .spec(Specs.responseSpecOK200())
                    .body("data.statusofapplication", is("under consideration"));
        });
    }
}