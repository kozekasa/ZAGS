package org.example.api.positiveTests.admin;

import io.qameta.allure.*;
import org.example.api.Specs;
import org.example.api.UsefulAPI;
import org.example.dataFactory.TestDataFactory;
import org.example.models.RequestProcessData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Epic("API")
@Feature("Позитивные тесты")
@Story("Отклонение заявки")
public class RejectRequestProcessAPI {

    @Test
    @Owner("Aleksandr")
    @Tag("API")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Отклонение заявки администратором")
    @Description("Создание заявки, создание администратора и отклонение этой заявки")
    public void approveRequestProcessTest() {
        int appId = UsefulAPI.createApplicationAndGetIntId();

        int staffId = UsefulAPI.createStaffAndGetId();

        RequestProcessData approveData = TestDataFactory.approveRequest(appId, staffId);

        Allure.step("Отправка POST запроса на /requestProcess (отклонение)", () -> {
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