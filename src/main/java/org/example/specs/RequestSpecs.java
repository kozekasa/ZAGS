package org.example.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.driver.WebDriverSingleton;

public class RequestSpecs {

    private static RequestSpecBuilder baseSpec() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
    }

    public static RequestSpecification requestSpec() {
        return baseSpec()
                .setBaseUri(WebDriverSingleton.getEnv("BASE_URL"))
                .build();
    }

    public static RequestSpecification unauthorizedRequestSpec() {
        return baseSpec()
                .setBaseUri(WebDriverSingleton.getEnv("UNAUTHORIZED_URL"))
                .build();
    }
}
