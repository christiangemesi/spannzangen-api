package com.regofix.spannzangen;


import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class ColletControllerTest {

    @Test
    public void testGetAllCollets() {
        given()
                .when().get("/spannzangen")
                .then()
                .statusCode(200);
    }
    @Test
    public void testAddCollet() {
        String colletJson = "{\"size\": 10, \"type\": \"MB\", \"articleNumber\": \"1234.56789\"}";

        given()
                .contentType("application/json")
                .body(colletJson)
                .when().post("/spannzange")
                .then()
                .statusCode(201)
                .body("size", equalTo(10))
                .body("type", equalTo("MB"))
                .body("articleNumber", equalTo("1234.56789"));
    }

    @Test
    public void testAddInvalidColletSize() {
        String colletJson = "{\"size\": 50, \"type\": \"MB\", \"articleNumber\": \"1234.56789\"}";

        given()
                .contentType("application/json")
                .body(colletJson)
                .when().post("/spannzange")
                .then()
                .statusCode(400)
                .body(containsString("Invalid size: 50. Valid sizes are [6, 10, 15, 25, 32]"));
    }

    @Test
    public void testAddInvalidColletType() {
        String colletJson = "{\"size\": 10, \"type\": \"XX\", \"articleNumber\": \"1234.56789\"}";

        given()
                .contentType("application/json")
                .body(colletJson)
                .when().post("/spannzange")
                .then()
                .statusCode(400)
                .body(containsString("Invalid type: XX. Valid types are [MB, Std, CF, L, S, T, SG, TAP, MQL]"));
    }

    @Test
    public void testAddInvalidColletArticleNumber() {
        String colletJson = "{\"size\": 10, \"type\": \"MB\", \"articleNumber\": \"123456789\"}";

        given()
                .contentType("application/json")
                .body(colletJson)
                .when().post("/spannzange")
                .then()
                .statusCode(400)
                .body(containsString("Invalid article number: 123456789. It should follow the format xxxx.xxxxx"));
    }


    @Test
    public void testGetColletByIdNotFound() {
        given()
                .when().get("/spannzange/999")
                .then()
                .statusCode(404)
                .body(containsString("Collet with ID 999 not found"));
    }

}
