package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C20_FakeStoreAPI {

    @Test
    void test() throws IOException {
        ObjectNode payload=(ObjectNode)
                new ObjectMapper()
                        .readTree(new File("src/test/resources/fakestore.json"));

        payload.put("userId","0192836584");

        ArrayNode products = (ArrayNode) payload.get("products");
        ObjectNode firstProduct = (ObjectNode) products.get(0);
        firstProduct.put("description", "add new cart");

        Response response=given()
                .baseUri("https://fakestoreapi.com")
                .contentType(ContentType.JSON)
                .body(payload).post("/carts");

        response.prettyPrint();
        response.then().statusCode(201)
                .body("userId",equalTo(payload.get("userId").asText()));

    }
}
