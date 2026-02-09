package org.example.api.negativeTests.notAllFieldsInRequest;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.dataFactory.TestDataFactory;
import org.example.models.invalidData.AdminDataWithoutName;
import org.example.models.validData.AdminData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("Негативные тесты")
@Story("Отсутствие всех полей в теле запроса")
public class SendAdminRequestWithoutAllFieldsTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Ошибка регистрации администратора из-за отсутствия всех полей в теле запроса")
    @Description("Проверка того, что запрос на регистрацию администратора из-за отсутствия всех полей в теле запроса возвращает 400 статус")
    public void sendAdminRequestTest() {
        AdminDataWithoutName admin = TestDataFactory.createAdminWithoutNameForAPI();

        Allure.step("Отправка POST запроса на создание администратора", () -> {
            given()
                    .spec(Specs.requestSpec())
                    .body(admin)
                    .when()
                    .post("/sendAdminRequest")
                    .then()
                    .spec(Specs.responseSpec400());
        });
    }
}