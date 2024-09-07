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

    /**
     * Tests adding a collet with valid data
     */
    @Test
    public void testAddColletWithValidData() {
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

    /**
     * Tests adding a collet with invalid data
     */
    @Test
    public void testAddColletWithInvalidSize() {
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
    public void testAddColletWithInvalidType() {
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
    public void testAddColletWithInvalidArticleNumber() {
        String colletJson = "{\"size\": 10, \"type\": \"MB\", \"articleNumber\": \"123456789\"}";

        given()
                .contentType("application/json")
                .body(colletJson)
                .when().post("/spannzange")
                .then()
                .statusCode(400)
                .body(containsString("Invalid article number: 123456789. It should follow the format xxxx.xxxxx containing only Numbers"));
    }


    /**
     * Tests getting a collet by ID that does not exist
     */
    @Test
    public void testGetColletByIdNotFound() {
        given()
                .when().get("/spannzange/999")
                .then()
                .statusCode(404)
                .body(containsString("Collet with ID 999 not found"));
    }

    /**
     * Tests deleting a collet by ID that exists
     */
    @Test
    public void testDeleteColletByValidId() {
        // Add a collet with valid data first
        String colletJson = "{\"size\": 10, \"type\": \"MB\", \"articleNumber\": \"1234.56789\"}";

        // Add the collet
        given()
                .contentType("application/json")
                .body(colletJson)
                .when().post("/spannzange")
                .then()
                .statusCode(201);

        // Now delete the collet with ID 1
        given()
                .when().delete("/spannzange/1")
                .then()
                .statusCode(200);
    }

}
