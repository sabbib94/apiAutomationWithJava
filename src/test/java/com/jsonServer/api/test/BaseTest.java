package com.jsonServer.api.test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
        RestAssured.basePath = "";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
