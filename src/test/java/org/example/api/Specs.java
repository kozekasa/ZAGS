package org.example.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.driver.WebDriverSingleton;

import static org.hamcrest.Matchers.lessThan;

public class Specs {
    public static RequestSpecification requestSpec() {
        String url = WebDriverSingleton.getEnv("BASE_URL");
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification unauthorizedRequestSpec() {
        String url = WebDriverSingleton.getEnv("UNAUTHORIZED_URL");
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(5000L))
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpec400() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .log(LogDetail.ALL)
                .build();
    }
    public static ResponseSpecification responseSpec401() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .log(LogDetail.ALL)
                .build();
    }
    public static ResponseSpecification responseSpec404() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpec500() {
        return new ResponseSpecBuilder()
                .expectStatusCode(500)
                .log(LogDetail.ALL)
                .build();
    }
}