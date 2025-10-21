package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.petPojos.PetPojo;

import static json_data.petData.petData;

public class C19_PetstorePojoPayload {

    /*
 Requirements:
 Reference the API documentation at
https://petstore.swagger.io/
 Create a POJO class representing a Pet object with properties like:
 id
name
 category
 photoUrls
 status
 tags
 Send a POST request to the create pet endpoint with your POJO as the body
 Assert that the response status code is successful (200 or 201)
 Assert that the returned pet object contains the data you sent

     */

    @Test
    void test() throws JsonProcessingException {

        PetPojo payload=new ObjectMapper().readValue(petData,PetPojo.class);
        Response response= RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/pet");

        response.then().statusCode(200);
        PetPojo actualData=response.as(PetPojo.class);
        Assert.assertEquals(actualData.getName(),payload.getName());
        Assert.assertEquals(actualData.toString(),payload.toString());
        Assert.assertEquals(actualData.getStatus(),payload.getStatus());


    }
}
