package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class C04_Groovy2 {
//    Given
//    https://dummy.restapiexample.com/api/v1/employees
//    When
//    User sends GET request
//    Then
//    Status code is 200
//    And
//    There are 24 employees
//            And
//"Tiger Nixon" and "Garrett Winters" are among them
//            And
//    Highest age = 66
//    And
//            Youngest = "Tatyana Fitzpatrick"
//    And
//    Total salary = 6,644,770


   @Test
    void test(){
       Response response= RestAssured.get("https://dummy.restapiexample.com/api/v1/employees");

       response.then().statusCode(200);

       List<Object> employees=response.jsonPath().getList("data");
       Assert.assertEquals(employees.size(),24);

       int highestAge=response.jsonPath().getInt("data.max{it.employee_age}.employee_age");
       Assert.assertEquals(highestAge,66);

       boolean assertName=response.jsonPath().getBoolean("data.any{it.employee_name == 'Tiger Nixon'}");
       Assert.assertTrue(assertName);

       String youngestName=response.jsonPath().getString("data.min{it.employee_age}.employee_name");
       Assert.assertEquals(youngestName,"Tatyana Fitzpatrick");

       int sallarySum=response.jsonPath().getInt("data.sum{it.employee_salary.toInteger()}");
       Assert.assertEquals(sallarySum,6644770);



   }



}
