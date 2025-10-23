package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

import static utilities.ConfigReader.getProperty;

public class GoRestBaseURL {


    protected RequestSpecification spec;
    @BeforeMethod
    public void setSpec(){
        spec=new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2")
                .setContentType(ContentType.JSON)
                .addHeader("authorization",getProperty("token"))
                .build();
    }


}
