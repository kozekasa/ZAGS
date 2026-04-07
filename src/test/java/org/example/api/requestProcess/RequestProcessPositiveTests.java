package org.example.api.requestProcess;

import io.qameta.allure.*;
import io.restassured.common.mapper.TypeRef;
import org.example.selenium.ApiPreconditions;
import org.example.models.ApplicationData;
import org.example.models.BaseResponse;
import org.example.models.UserDataAPI;
import org.example.selenium.specs.RequestSpecs;
import org.example.selenium.specs.ResponseSpecs;
import org.example.dataFactory.TestDataFactory;
import org.example.models.RequestProcessData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("API")
@Feature("Управление статусом заявки")
public class RequestProcessPositiveTests {

    @Test
    @Owner("Aleksandr")
    @Tag("positive")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Подтверждение заявки")
    @DisplayName("Одобрение заявки администратором")
    @Description("Создание заявки, создание администратора и одобрение этой заявки")
    public void approveRequestProcessAPITest() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest().build();

        int appId = ApiPreconditions.createApplicationAndGetIntId(userRequest);

        int staffId = ApiPreconditions.createStaffAndGetId();

        RequestProcessData approveData = TestDataFactory.createRequestStatus(appId, staffId, "approved");

        ApplicationData response = given()
                .spec(RequestSpecs.requestSpec())
                .body(approveData)
                .when()
                .post("/requestProcess")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .extract()
                .as(new TypeRef<BaseResponse<ApplicationData>>() {})
                .getData();
        Assertions.assertAll("Проверка данных заявки",
                () -> Assertions.assertEquals(appId, response.getApplicationid(), "ID заявки не совпадает"),
                () -> Assertions.assertEquals("approved", response.getStatusofapplication(), "Статус не совпадает")
        );
    }

    @Test
    @Owner("Aleksandr")
    @Tag("positive")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Отклонение заявки")
    @DisplayName("Отклонение заявки администратором")
    @Description("Создание заявки, создание администратора и отклонение этой заявки")
    public void rejectRequestProcessAPITest() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest().build();

        int appId = ApiPreconditions.createApplicationAndGetIntId(userRequest);

        int staffId = ApiPreconditions.createStaffAndGetId();

        RequestProcessData approveData = TestDataFactory.createRequestStatus(appId, staffId, "rejected");

        ApplicationData response = given()
                .spec(RequestSpecs.requestSpec())
                .body(approveData)
                .when()
                .post("/requestProcess")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .extract()
                .as(new TypeRef<BaseResponse<ApplicationData>>() {
                })
                .getData();

        Assertions.assertAll("Проверка данных заявки",
                () -> Assertions.assertEquals(appId, response.getApplicationid(), "ID заявки не совпадает"),
                () -> Assertions.assertEquals("rejected", response.getStatusofapplication(), "Статус не совпадает")
        );
    }
}