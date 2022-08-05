package com.binance_bot.requests;

import com.binance_bot.core.ApplicationManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestBase {
    protected static ApplicationManager app = ApplicationManager.get();

    public static Response get(String url) {
        return given()
                .relaxedHTTPSValidation()
                .log()
                .all()
                .get(url);
    }
}
