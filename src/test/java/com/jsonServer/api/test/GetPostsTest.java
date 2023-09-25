package com.jsonServer.api.test;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetPostsTest extends BaseTest{
        @Test
        public void getPostsShouldSucceed() {
            given()
                    .contentType(ContentType.JSON)
                    .log().uri()
                    .when()
                    .get("/posts")
                    .then()
                    .statusCode(200)
                    .log().body();
        }

    @Test
    public void getPostDetailShouldSucceed() {
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .log().body();
    }
}
