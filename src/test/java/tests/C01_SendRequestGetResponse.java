package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class C01_SendRequestGetResponse {

    public static void main(String[] args) {

        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/209");

        response.prettyPrint();


    }

}
