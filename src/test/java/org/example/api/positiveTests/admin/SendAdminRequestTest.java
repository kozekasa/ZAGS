package org.example.api.positiveTests.admin;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.dataFactory.TestDataFactory;
import org.example.models.AdminData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("Позитивные тесты")
@Story("Создание администратора")
public class SendAdminRequestTest {

    @Test
    @Owner("Aleksandr")
    @Tag("API")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешное создание администратора (позитивный сценарий)")
    @Description("Проверка создания администратора с валидными данными и получение staffid")
    public void testCreateAdminSuccess() {
        AdminData admin = TestDataFactory.createAdminForAPI();

        Allure.step("Отправка POST запроса на создание администратора", () -> {
            given()
                    .spec(Specs.requestSpec())
                    .body(admin)
                    .when()
                    .post("/sendAdminRequest")
                    .then()
                    .spec(Specs.responseSpecOK200())
                    .body("data.staffid", notNullValue())
                    .body("data.staffid", is(greaterThan(0)));
        });
    }
}