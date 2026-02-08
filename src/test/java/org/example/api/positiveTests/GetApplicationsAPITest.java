package org.example.api.positiveTests;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("Позитивные тесты")
@Story("Просмотр заявок")
public class GetApplicationsAPITest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Получение списка всех заявок")
    @Description("Проверка эндпоинта /getApplications на получение всех заявок")
    public void getApplicationsAPITest() {
        Allure.step("Отправка GET запроса на /getApplications", () -> {
            given()
                    .spec(Specs.requestSpec())
                    .when()
                    .get("/getApplications")
                    .then()
                    .spec(Specs.responseSpecOK200())
                    .body("total.toInteger()", is(greaterThan(1)))
                    .body("data.size()", is(greaterThan(1)))
                    .body("data[0].applicationid", notNullValue())
                    .body("data[0].statusofapplication", notNullValue());
        });
    }
}