package org.example.api.positiveTests.admin;

import io.qameta.allure.*;
import org.example.api.UsefulAPI;
import org.example.api.Specs;
import org.example.dataFactory.TestDataFactory;
import org.example.models.validData.RequestProcessData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("Позитивные тесты")
@Story("Одобрение заявки")
public class ApproveRequestProcessAPITest {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Одобрение заявки администратором")
    @Description("Создание заявки, создание администратора и одобрение этой заявки")
    public void approveRequestProcessAPITest() {
        int appId = UsefulAPI.createApplicationAndGetIntId();

        int staffId = UsefulAPI.createStaffAndGetId();

        RequestProcessData approveData = TestDataFactory.approveRequest(appId, staffId);

        Allure.step("Отправка POST запроса на /requestProcess (одобрение)", () -> {
            given()
                    .spec(Specs.requestSpec())
                    .body(approveData)
                    .when()
                    .post("/requestProcess")
                    .then()
                    .spec(Specs.responseSpecOK200())
                    .body("data.applicationid", is(appId))
                    .body("data.statusofapplication", is("approved"));
        });
    }
}