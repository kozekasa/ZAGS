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
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("Регистрация администратора")
public class SendAdminRequestPositiveTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешное создание администратора (позитивный сценарий)")
    @Description("Проверка создания администратора с валидными данными и получение staffid")
    public void sendAdminRequestTest() {
        AdminData admin = TestDataFactory.createAdminForAPI();

        given()
                .spec(RequestSpecs.requestSpec())
                .body(admin)
                .when()
                .post("/sendAdminRequest")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .body("data.staffid", notNullValue())
                .body("data.staffid", is(greaterThan(0)));
    }
}