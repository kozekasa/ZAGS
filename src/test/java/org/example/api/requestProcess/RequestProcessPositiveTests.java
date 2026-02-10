package org.example.api.requestProcess;

import io.qameta.allure.*;
import org.example.UsefulAPI;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.example.dataFactory.TestDataFactory;
import org.example.models.RequestProcessData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API")
@Feature("Управление статусом заявки")
public class RequestProcessPositiveTests {

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Подтверждение заявки")
    @DisplayName("Одобрение заявки администратором")
    @Description("Создание заявки, создание администратора и одобрение этой заявки")
    public void approveRequestProcessAPITest() {
        int appId = UsefulAPI.createApplicationAndGetIntId();

        int staffId = UsefulAPI.createStaffAndGetId();

        RequestProcessData approveData = TestDataFactory.createRequestStatus(appId, staffId, "approved");

        given()
                .spec(RequestSpecs.requestSpec())
                .body(approveData)
                .when()
                .post("/requestProcess")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .body("data.applicationid", is(appId))
                .body("data.statusofapplication", is("approved"));
    }

    @Test
    @Owner("Aleksandr")
    @Tag("api")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Отклонение заявки")
    @DisplayName("Отклонение заявки администратором")
    @Description("Создание заявки, создание администратора и отклонение этой заявки")
    public void rejectRequestProcessAPITest() {
        int appId = UsefulAPI.createApplicationAndGetIntId();

        int staffId = UsefulAPI.createStaffAndGetId();

        RequestProcessData approveData = TestDataFactory.createRequestStatus(appId, staffId, "rejected");

        given()
                .spec(RequestSpecs.requestSpec())
                .body(approveData)
                .when()
                .post("/requestProcess")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .body("data.applicationid", is(appId))
                .body("data.statusofapplication", is("rejected"));
    }
}