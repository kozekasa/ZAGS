package org.example.api.sendAdminRequest;

import io.qameta.allure.*;
import io.restassured.common.mapper.TypeRef;
import org.example.dataFactory.TestDataFactory;
import org.example.models.AdminData;
import org.example.models.ApplicationData;
import org.example.models.BaseResponse;
import org.example.specs.RequestSpecs;
import org.example.specs.ResponseSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@Epic("API")
@Feature("Регистрация администратора")
public class SendAdminRequestPositiveTest {

    @Test
    @Owner("Aleksandr")
    @Tag("positive")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Успешное создание администратора (позитивный сценарий)")
    @Description("Проверка создания администратора с валидными данными и получение staffid")
    public void sendAdminRequestTest() {
        AdminData admin = TestDataFactory.createAdminForAPI();

        ApplicationData response = given()
                .spec(RequestSpecs.requestSpec())
                .body(admin)
                .when()
                .post("/sendAdminRequest")
                .then()
                .spec(ResponseSpecs.successResponseSpec(200))
                .extract()
                .as(new TypeRef<BaseResponse<ApplicationData>>() {})
                .getData();

        Assertions.assertAll("Проверка данных заявки",
                () -> Assertions.assertNotNull(response.getStaffid(), "Staff ID не должен быть null"),
                () -> Assertions.assertTrue(response.getStaffid() > 0,"Staff ID должен быть больше 0")
        );
    }
}