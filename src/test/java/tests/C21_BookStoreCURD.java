package tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;

public class C21_BookStoreCURD {
    RequestSpecification spec = new RequestSpecBuilder()
                .setBaseUri("https://bookstore.demoqa.com")
                .setContentType(ContentType.JSON)
                .build();

    ObjectNode payload=(ObjectNode)getJsonNode("bookstore_userData");


    @Test
    void getAllBooks(){

        Response response=given(spec).get("/BookStore/v1/Books");
        response.prettyPrint();
        response.then().statusCode(200);

    }

    @Test
    void createUser(){

      payload.put("userName",new Faker().name().firstName());

       Response response=given(spec)
                .body(payload)
                .post("/Account/v1/User");

        response.prettyPrint();
        response.then().statusCode(201);

        payload.put("userID",response.jsonPath().getString("userID"));


    }


    @Test(dependsOnMethods = {"createUser","generateToken"})
    void getUserdata() {

        Response response=given(spec)
                .header("authorization","Bearer "+payload.get("token").asText())
                .pathParam("userId", payload.get("userID").asText())
                .get("/Account/v1/User/{userId}");

        response.prettyPrint();
        response.then().statusCode(200)
                .body("userId",equalTo(payload.get("userID").asText()));


    }

    @Test(dependsOnMethods = "createUser")
    void generateToken(){

        Response response=given(spec)
                .body(payload)
                .post("/Account/v1/GenerateToken");
        response.prettyPrint();

        response.then().statusCode(200);
        payload.put("token",response.jsonPath().getString("token"));

    }


    @Test(dependsOnMethods = {"createUser","generateToken"})
    void assignBookToUser(){

        String bookpayload = """
            {
                "userId": "%s",
                "collectionOfIsbns": [
                    { "isbn": "9781449331818" },
                    { "isbn": "9781449325862" },
                    { "isbn": "9781491904244" }
                ]
            }
            """.formatted(payload.get("userID").asText());

        Response response=given(spec)
                .body(bookpayload)
                .header("authorization","Bearer "+payload.get("token").asText())
                .post("BookStore/v1/Books");
        response.prettyPrint();

        response.then()
                .statusCode(201);

        }

    }








