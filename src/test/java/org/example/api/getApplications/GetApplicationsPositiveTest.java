package org.example.api.getApplications;

import io.qameta.allure.*;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("Получение списка заявок")
public class GetApplicationsPositiveTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Получение списка всех заявок")
    @Description("Проверка эндпоинта /getApplications на получение всех заявок")
    public void getApplicationsAPITest() {
        given()
                .spec(RequestSpecs.requestSpec())
                .when()
                .get("/getApplications")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .body("total.toInteger()", is(greaterThan(1)))
                .body("data.size()", is(greaterThan(1)))
                .body("data[0].applicationid", notNullValue())
                .body("data[0].statusofapplication", notNullValue());
    }
}