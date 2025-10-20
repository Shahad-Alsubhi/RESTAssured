package tests;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.core.AllOf;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static groovy.xml.Entity.not;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C05_POSTRequestwithAuthentication {
    /*
Given: Base URL: https://reqres.in/api/users
Request Body:
{
"name": "morpheus",
"job": "leader"
}
When: Send a POST request to the URL
Then: Assert the status code is 201
Verify the response body matches the structure:
{
"name": "morpheus",
"job": "leader",
"id": "496",
"createdAt": "2022-10-04T15:18:56.372Z"
}
*/


    @Test
    void test(){

        Map<String,String> payload=new HashMap<>();
        payload.put("name", "morpheus");
        payload.put("job","leader");

        Response response= given()
                .baseUri("https://reqres.in")
                .header("x-api-key","reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/api/users");

        response.prettyPrint();

        response.then()
                .statusCode(201)
                .body("name",equalTo(payload.get("name")),
                        "job",equalTo(payload.get("job")))
                .body("$", allOf(hasKey("id"), hasKey("createdAt")))
                .body("createdAt",notNullValue())
        ;

    }
}
