package tests;

import base_urls.ActivityBaseUrl;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Activity;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasKey;

public class C17_CRUDoperations extends ActivityBaseUrl {

    Activity activity=new Activity(786,"random title", "2025-10-20T13:34:01.696Z",true);


    @Test(priority = -1)
    void postTest(){

        Response response=given(spec)
                .body(activity)
                .post("/activities");

       response.prettyPrint();
       response.then().statusCode(200);
       Activity actualData=response.as(Activity.class);
       Assert.assertEquals(actualData.getTitle(),activity.getTitle());
       Assert.assertEquals(actualData.isCompleted(),activity.isCompleted());


    }

    @Test
    void getTest(){
        spec.pathParam("id",1);
        Response response=given(spec)
                .get("/activities/{id}");

        response.prettyPrint();
        response.then().statusCode(200)
                .body("",allOf(hasKey("title"),hasKey("id")
                        ,hasKey("completed"),hasKey("dueDate")));


    }

    @Test(priority = 2)
    void updateTest(){
        spec.pathParam("id",activity.getId());
        activity.setTitle("newTitle");
        Response response=given(spec)
                .body(activity)
                .put("/activities/{id}");

        response.prettyPrint();
        response.then().statusCode(200);
        Activity actualData=response.as(Activity.class);
        Assert.assertEquals(actualData.getTitle(),activity.getTitle());
        Assert.assertEquals(actualData.isCompleted(),activity.isCompleted());


    }

    @Test(priority = 3)
    void deleteTest(){
        spec.pathParam("id",activity.getId());
        activity.setTitle("newTitle");
        Response response=given(spec)
                .body(activity)
                .delete("/activities/{id}");

        response.prettyPrint();
        response.then().statusCode(200);

    }


}
