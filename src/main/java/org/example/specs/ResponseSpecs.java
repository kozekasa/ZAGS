package org.example.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class ResponseSpecs {

    public static ResponseSpecification errorResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification successResponseSpec(int i) {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(5000L))
                .log(LogDetail.ALL)
                .build();
    }
}
