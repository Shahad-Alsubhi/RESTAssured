package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertion {

     /*
         API Endpoint:
         https://fakerestapi.azurewebsites.net/api/v1/Users
         Objective: Validate API response headers and status
         Test Requirements:
         Send GET request to the endpoint
         Validate response status code
         Validate response headers
         Verify server information
    */

    @Test
    void assertionTest() {

        Response response = RestAssured.get("https://fakerestapi.azurewebsites.net/api/v1/Users");

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertTrue(response.contentType().contains( "application/json"));

        Assert.assertEquals(response.header("server"), "Kestrel");

        Assert.assertEquals(response.header("Transfer-Encoding"), "chunked");

    }

}
