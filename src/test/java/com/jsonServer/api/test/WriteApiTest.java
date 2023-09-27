package com.jsonServer.api.test;

import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class WriteApiTest extends BaseTest {
    @Test
    public void createPostWithHashMapAssertionShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        HashMap<String, Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title", title);
        jsHashMap.put("author", author);

        given()
                .header("Content-Type", "application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("id", notNullValue());
    }

    @Test
    public void createPostWithJsonObjectShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("id", notNullValue());
    }

    @Test
    public void updatePostWithHashMapShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        HashMap<String, Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title", title);
        jsHashMap.put("author", author);

        int id = given()
                .header("Content-Type", "application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");

        title = LoremIpsum.getInstance().getTitle(3);
        author = LoremIpsum.getInstance().getName();

        HashMap<String, Object> jsHashMapUpdate = new HashMap<>();
        jsHashMapUpdate.put("title", title);
        jsHashMapUpdate.put("author", author);

        given()
                .header("Content-Type", "application/json")
                .body(jsHashMapUpdate)
                .log().uri()
                .log().body()
                .when()
                .put("/posts/" + id)
                .then()
                .statusCode(200)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("id", notNullValue());
    }

    @Test
    public void updateSinglePostWithHashMapShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        HashMap<String, Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title", title);
        jsHashMap.put("author", author);

        int id = given()
                .header("Content-Type", "application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");

        author = LoremIpsum.getInstance().getName();

        HashMap<String, Object> jsHashMapUpdate = new HashMap<>();
        jsHashMapUpdate.put("author", author);

        given()
                .header("Content-Type", "application/json")
                .body(jsHashMapUpdate)
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/" + id)
                .then()
                .statusCode(200)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("id", notNullValue());
    }

    @Test
    public void deletePostWithHashMapShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        HashMap<String, Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title", title);
        jsHashMap.put("author", author);

        int id = given()
                .header("Content-Type", "application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");

        given()
                .header("Content-Type", "application/json")
                .log().uri()
                .log().body()
                .when()
                .delete("/posts/" + id)
                .then()
                .statusCode(200)
                .log().body();
    }
}
