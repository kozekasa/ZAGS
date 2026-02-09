package org.example.api.negativeTests.emptyRequest;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@Epic("API")
@Feature("Негативные тесты")
@Story("Пустое тело запроса")
public class SendEmptyRequestProcessTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Ошибка при отправке запроса на изменение статуса заявки с пустым телом")
    @Description("Проверка того, что запрос на изменение статуса заявки с пустым телом возвращает 400 статус")
    public void sendEmptyRequestProcessTest() {
        Allure.step("Отправка пустого POST запроса на изменение статуса заявки", () -> {
            given()
                    .spec(Specs.requestSpec())
                    .when()
                    .post("/requestProcess")
                    .then()
                    .spec(Specs.responseSpec400());
        });
    }
}