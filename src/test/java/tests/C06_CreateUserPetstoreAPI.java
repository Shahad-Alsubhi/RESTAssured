package tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserPojo;

import static io.restassured.RestAssured.given;

public class C06_CreateUserPetstoreAPI
{
/*
  Task: Write an automation test that creates a 'user' using the
  Petstore API at https://petstore.swagger.io
 Requirements:
 1. Review the Petstore API documentation
 2. Identify the endpoint for creating users (/user)
 3. Create User POJO with all required fields
 4. Implement POST request to create user
 5. Validate successful creation with assertions
 */

    @Test
    void test(){
        Faker faker=new Faker();
        UserPojo user = new UserPojo(
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(8, 16),
                faker.phoneNumber().cellPhone()
        );
        Response response=given().baseUri("https://petstore.swagger.io/v2")
                .body(user)
                .contentType(ContentType.JSON)
                .post("/user");

        response.prettyPrint();
        response.then().statusCode(200);



    }

}
