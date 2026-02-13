package org.example.api.getApplStatus;

import io.qameta.allure.*;
import io.restassured.common.mapper.TypeRef;
import org.example.dataFactory.TestDataFactory;
import org.example.models.ApplicationData;
import org.example.models.BaseResponse;
import org.example.models.UserDataAPI;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.example.ApiPreconditions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("API")
@Feature("Получение статуса заявки")
public class GetApplStatusPositiveTest {

    @Test
    @Owner("Aleksandr")
    @Tag("positive")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Получение статуса конкретной заявки по ID")
    @Description("Проверка эндпоинта /getApplStatus/{appid} для свежесозданной заявки")
    public void getApplStatusAPITest() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest().build();

        int appId = ApiPreconditions.createApplicationAndGetIntId(userRequest);

        ApplicationData response = given()
                .spec(RequestSpecs.requestSpec())
                .pathParam("appid", appId)
                .when()
                .get("/getApplStatus/{appid}")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .extract()
                .as(new TypeRef<BaseResponse<ApplicationData>>() {})
                .getData();

        Assertions.assertEquals(response.getStatusofapplication(), "under consideration", "Статус заявки не соответствует ожидаемому");
    }
}