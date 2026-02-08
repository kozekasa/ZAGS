package org.example.api;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.dataFactory.TestDataFactory;
import org.example.models.AdminData;
import org.example.models.UserDataAPI;
import static io.restassured.RestAssured.given;

public class UsefulAPI {

    @Step("API: Создание заявки и получение её ID")
    public static String createApplicationAndGetId() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest();

        return Allure.step("Отправка POST запроса на /sendUserRequest", () -> {
            String appId = given()
                    .spec(Specs.requestSpec())
                    .body(userRequest)
                    .when()
                    .post("/sendUserRequest")
                    .then()
                    .spec(Specs.responseSpecOK200())
                    .extract()
                    .path("data.applicationid").toString();
            return appId;
        });
    }

    public static int createApplicationAndGetIntId() {
        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest();

        return Allure.step("API: Создание заявки и получение её ID", () -> {
            return given()
                    .spec(Specs.requestSpec())
                    .body(userRequest)
                    .when()
                    .post("/sendUserRequest")
                    .then()
                    .spec(Specs.responseSpecOK200())
                    .extract()
                    .path("data.applicationid");
        });
    }

    public static int createStaffAndGetId() {
        AdminData admin = TestDataFactory.createAdminForAPI();

        return Allure.step("API: Создание администратора и получение его ID", () -> {
            return given()
                    .spec(Specs.requestSpec())
                    .body(admin)
                    .when()
                    .post("/sendAdminRequest")
                    .then()
                    .spec(Specs.responseSpecOK200())
                    .extract().path("data.staffid");
        });
    }
}