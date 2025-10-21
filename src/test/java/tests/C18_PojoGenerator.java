package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.UserPojo;
import pojos.randomuserApiPojos.ResponsePojo;

public class C18_PojoGenerator {
    /*
     Objective: Write an automation test that validates user data from a random user API endpoint.
 Requirements:
 Send a GET request to
https://randomuser.me/api
 The response will contain random user information in nested JSON structure
 Deserialize the response into a POJO class
 Assert that the following fields are NOT null:
 Email
 Username
 Password
 Medium picture URL
     */

    @Test
    void test(){
        Response response= RestAssured.get("https://randomuser.me/api");
        response.prettyPrint();

        ResponsePojo responsePojo=response.as(ResponsePojo.class);
        Assert.assertNotNull(responsePojo.getResults().get(0).getEmail());
        Assert.assertNotNull(responsePojo.getResults().get(0).getLogin().getUsername());
        Assert.assertNotNull(responsePojo.getResults().get(0).getLogin().getPassword());

    }

}
