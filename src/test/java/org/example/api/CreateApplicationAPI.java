package org.example.api;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.dataFactory.TestDataFactory;
import org.example.models.UserDataAPI;
import static io.restassured.RestAssured.given;

public class CreateApplicationAPI {

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
}