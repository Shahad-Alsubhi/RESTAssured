package tests;

import base_urls.JPHBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class C03_Groovy extends JPHBaseUrl {
//    Example 2
//    Given
//    https://jsonplaceholder.typicode.com/todos
//    When
//    I send GET request
//    Then
//1) Status code = 200
//2) Print all ids > 190 (10 total)
//            3) Print userIds with ids < 5 (4 total)
//            4) Verify title “quis eius est sint explicabo”
//            5) Find id where title = "quo adipisci enim quam ut ab"


   @Test
    void test(){

      Response response= given(spec).when().get("/todos");

      response.then().statusCode(200);

       JsonPath jsonPath=response.jsonPath();
       List<Integer> ids=jsonPath.getList("findAll{it.id>190}.id");
       Assert.assertEquals(ids.size(), 10);
       ids.forEach(t-> System.out.println(t));

       List<Integer> userIds=jsonPath.getList("findAll{it.id<5}.id");
       Assert.assertEquals(userIds.size(), 4);
       userIds.forEach(id-> System.out.println(id));

       Object verifyTitle = jsonPath.get("find{it.title == 'quis eius est sint explicabo'}");
       Assert.assertNotNull(verifyTitle);

       boolean verifyTitle2 = jsonPath.getBoolean("any{it.title == 'quis eius est sint explicabo'}");
       Assert.assertTrue(verifyTitle2);

       int idForTitle=jsonPath.getInt("find{it.title=='quo adipisci enim quam ut ab'}.id");
       System.out.println(idForTitle);

   }

}
