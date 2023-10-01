package com.jsonServer.api.test;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
                .log().body()
                .body("id",equalTo(1))
                .body("title",equalTo("json-server"))
                .body("author",equalTo("typicode"));
    }
}
