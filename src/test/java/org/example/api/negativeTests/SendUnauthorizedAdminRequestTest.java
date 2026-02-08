package org.example.api.negativeTests;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.dataFactory.TestDataFactory;
import org.example.models.AdminData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


@Epic("API")
@Feature("Негативные тесты")
@Story("Авторизация")
public class SendUnauthorizedAdminRequestTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Ошибка создания администратора без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void testCreateAdminSuccess() {
        AdminData admin = TestDataFactory.createAdminForUI();

        Allure.step("Отправка POST запроса на создание администратора", () -> {
            given()
                    .spec(Specs.unauthorizedRequestSpec())
                    .body(admin)
                    .when()
                    .post("/sendAdminRequest")
                    .then()
                    .spec(Specs.responseSpec401());
        });
    }
}