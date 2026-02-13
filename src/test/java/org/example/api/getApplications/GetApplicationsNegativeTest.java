package org.example.api.getApplications;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("API")
@Feature("Получение списка заявок")
public class GetApplicationsNegativeTest {

    @Test
    @Owner("Aleksandr")
    @Tag("negative")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Неавторизованный запрос")
    @DisplayName("Ошибка создания получения списка заявок без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedGetApplicationsTest() {
        Response response = given()
                .spec(RequestSpecs.unauthorizedRequestSpec())
                .when()
                .get("/getApplications")
                .then()
                .spec(ResponseSpecs.errorResponseSpec(401))
                .extract()
                .response();

        Assertions.assertEquals(401, response.getStatusCode(), "Код ответа должен быть 401 Unauthorized");
    }
}