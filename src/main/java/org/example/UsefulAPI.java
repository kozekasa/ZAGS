package org.example;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.dataFactory.TestDataFactory;
import org.example.models.AdminData;
import org.example.models.UserDataAPI;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;

import static io.restassured.RestAssured.given;

public class UsefulAPI {

    @Step("API: Создание заявки и получение её ID")
    public static String createApplicationAndGetId() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest().build();

        return Allure.step("Отправка POST запроса на /sendUserRequest", () -> {
            String appId = given()
                    .spec(RequestSpecs.requestSpec())
                    .body(userRequest)
                    .when()
                    .post("/sendUserRequest")
                    .then()
                    .spec(ResponseSpecs.successResponseSpec(200))
                    .extract()
                    .path("data.applicationid").toString();
            return appId;
        });
    }

    public static int createApplicationAndGetIntId() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest().build();

        return Allure.step("API: Создание заявки и получение её ID", () -> {
            return given()
                    .spec(RequestSpecs.requestSpec())
                    .body(userRequest)
                    .when()
                    .post("/sendUserRequest")
                    .then()
                    .spec(ResponseSpecs.successResponseSpec(200))
                    .extract()
                    .path("data.applicationid");
        });
    }

    public static int createStaffAndGetId() {
        AdminData admin = TestDataFactory.createAdminForAPI();

        return Allure.step("API: Создание администратора и получение его ID", () -> {
            return given()
                    .spec(RequestSpecs.requestSpec())
                    .body(admin)
                    .when()
                    .post("/sendAdminRequest")
                    .then()
                    .spec(ResponseSpecs.successResponseSpec(200))
                    .extract().path("data.staffid");
        });
    }
}