package org.example.api.negativeTests.unathorized;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.api.UsefulAPI;
import org.example.dataFactory.TestDataFactory;
import org.example.models.validData.RequestProcessData;
import org.example.models.validData.UserDataAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


@Epic("API")
@Feature("Негативные тесты")
@Story("Авторизация")
public class SendUnauthorizedRequestProcessTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Ошибка изменения статуса заявки без авторизации")
    @Description("Проверка того, что запрос к защищенному эндпоинту без токена возвращает 401 статус")
    public void sendUnauthorizedRequestProcessTest() {
        int appId = UsefulAPI.createApplicationAndGetIntId();

        int staffId = UsefulAPI.createStaffAndGetId();

        RequestProcessData approveData = TestDataFactory.approveRequest(appId, staffId);

        Allure.step("Отправка POST запроса на /requestProcess (одобрение)", () -> {
            given()
                    .spec(Specs.unauthorizedRequestSpec())
                    .body(approveData)
                    .when()
                    .post("/requestProcess")
                    .then()
                    .spec(Specs.responseSpec401());
        });
    }
}