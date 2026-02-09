package org.example.api.negativeTests.notAllFieldsInRequest;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.api.UsefulAPI;
import org.example.dataFactory.TestDataFactory;
import org.example.models.invalidData.RequestProcessDataWithoutApplid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


@Epic("API")
@Feature("Негативные тесты")
@Story("Отсутствие всех полей в теле запроса")
public class SendRequestProcessWithoutAllFieldsTest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Ошибка изменения статуса заявки из-за отсутствия всех полей в теле запроса")
    @Description("Проверка того, что запрос на изменение статуса заявки из-за отсутствия всех полей в теле запроса возвращает 400 статус")
    public void sendRequestProcessWithoutAllFieldsTest() {

        int staffId = UsefulAPI.createStaffAndGetId();

        RequestProcessDataWithoutApplid approveData = TestDataFactory.approveRequestWithoutApplid(staffId);

        Allure.step("Отправка POST запроса на /requestProcess (одобрение)", () -> {
            given()
                    .spec(Specs.requestSpec())
                    .body(approveData)
                    .when()
                    .post("/requestProcess")
                    .then()
                    .spec(Specs.responseSpec400());
        });
    }
}