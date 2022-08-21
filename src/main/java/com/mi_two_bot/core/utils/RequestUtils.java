package com.mi_two_bot.core.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestUtils {
    public static Response get(String url) {
        return given()
                .relaxedHTTPSValidation()
                .log()
                .all()
                .get(url);
    }
}
