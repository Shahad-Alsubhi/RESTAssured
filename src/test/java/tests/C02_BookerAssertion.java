package tests;

import base_urls.BookersBaseUrl;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C02_BookerAssertion extends BookersBaseUrl {
//    Given
//    https://restful-booker.herokuapp.com/booking/32
//    When
//    User sends GET request
//    Then
//    Status Code: 200
//    And
//    Content Type: application/json
//            And
//    firstname: "Josh"
//    lastname: "Allen"
//    totalprice: 111




    @Test
    void test(){

       RequestSpecification spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com/booking/32")
                .setContentType(ContentType.JSON)
                .build();

       given(spec).then().statusCode(200).contentType("application/json").body("firstname",equalTo("John"));

    }

    @Test
    public void verifyBookingDetails() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/booking/32")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("John"))
                .body("lastname", equalTo("Smith"))
                .body("totalprice", equalTo(111));
    }

    @Test
    public void getRequest() {
        spec.pathParams("first", "booking","second", 32);
        Response response = given(spec).get("{first}/{second}");

        response.prettyPrint();
        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", equalTo("John"),
                        "lastname", equalTo("Smith"),
                        "totalprice", equalTo(111));

    }

}
