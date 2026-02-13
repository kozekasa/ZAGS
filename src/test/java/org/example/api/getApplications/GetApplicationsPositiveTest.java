package org.example.api.getApplications;

import io.qameta.allure.*;
import io.restassured.common.mapper.TypeRef;
import org.example.models.ApplicationData;
import org.example.models.BaseResponse;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("Получение списка заявок")
public class GetApplicationsPositiveTest {

    @Test
    @Owner("Aleksandr")
    @Tag("positive")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Неавторизованный запрос")
    @DisplayName("Получение списка всех заявок")
    @Description("Проверка эндпоинта /getApplications на получение всех заявок")
    public void getApplicationsAPITest() {
        BaseResponse<List<ApplicationData>> response = given()
                .spec(RequestSpecs.requestSpec())
                .when()
                .get("/getApplications")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .extract()
                .as(new TypeRef<BaseResponse<List<ApplicationData>>>() {});

        List<ApplicationData> applications = response.getData();

        Assertions.assertTrue(Integer.parseInt(response.getTotal()) >= 1, "Total должен быть больше 1");
        Assertions.assertFalse(applications.isEmpty(), "Количество заявок в списке должно быть больше 1");
        Assertions.assertNotNull(applications.getFirst().getApplicationid(), "ID первой заявки не должен быть null");
        Assertions.assertNotNull(applications.getFirst().getStatusofapplication(), "Статус первой заявки не должен быть null");
    }
}