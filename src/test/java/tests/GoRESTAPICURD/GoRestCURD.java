package tests.GoRESTAPICURD;

import base_urls.GoRestBaseURL;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;

public class GoRestCURD extends GoRestBaseURL {

    ObjectNode payload=(ObjectNode)getJsonNode("goRestUser");


    @Test(priority = 1)
    void getAllUsers(){
        Response response=given(spec).get("/users");
        response.prettyPrint();

        response.then().statusCode(200);
    }


    @Test(priority = 2)
    void createUser() {

        //email should be unique
        payload.put("email",new Faker().internet().emailAddress());
        Response response=given(spec).body(payload).post("/users");
        response.prettyPrint();

         response.then().statusCode(201)
        .body("email",equalTo(payload.get("email").textValue()))
        .body("name",equalTo(payload.get("name").textValue()));

         payload.put("id",response.jsonPath().getInt("id"));


    }

    @Test(dependsOnMethods = "createUser",priority = 3)
    void getUser() {

        spec.pathParam("id",payload.get("id"));

        Response response=given(spec)
                .get("/users/{id}");

        response.prettyPrint();

        response.then().statusCode(200)
                .body("email",equalTo(payload.get("email").textValue()))
                .body("name",equalTo(payload.get("name").textValue()));

    }


    @Test(dependsOnMethods = "createUser",priority = 4)
    void updateUser() {

        spec.pathParam("id",payload.get("id"));

        Faker faker=new Faker();
        payload.put("name",faker.name().firstName());
        payload.put("email",faker.internet().emailAddress());
        payload.put("status","inactive");
        payload.put("gender","male");

        Response response=given(spec)
                .body(payload)
                .put("/users/{id}");

        response.prettyPrint();

        response.then().statusCode(200)
                .body("email",equalTo(payload.get("email").textValue()))
                .body("name",equalTo(payload.get("name").textValue()));

    }

    @Test(dependsOnMethods = "createUser",priority = 5)
    void updatePartialUser() {

        spec.pathParam("id",payload.get("id"));

        String newName=new Faker().name().firstName();
        Map<String,String> dataTobeUpdated= new HashMap<>();
                dataTobeUpdated.put("name",newName);

        Response response=given(spec)
                .body(dataTobeUpdated)
                .patch("/users/{id}");

        response.prettyPrint();

        response.then().statusCode(200)
                .body("email",equalTo(payload.get("email").textValue()))
                .body("name",equalTo(newName));

    }


    @Test(dependsOnMethods = "createUser",priority = 6)
    void deleteUser() {

        spec.pathParam("id",payload.get("id"));


        Response response=given(spec)
                .delete("/users/{id}");

        response.prettyPrint();

        response.then().statusCode(204);

    }

    @Test(dependsOnMethods = {"deleteUser","createUser"},priority = 7)
    void getUserNegative(){

        spec.pathParam("id",payload.get("id"));

        Response response=given(spec)
                .get("/users/{id}");

        response.prettyPrint();

        response.then().statusCode(404)
                .body("message",equalTo("Resource not found"));
    }



}
