package Batch_7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.*;

public class API_Day3_Gson {
    @Test
    public void testWithJsonWithHashMap(){

        Response response = given().accept(ContentType.JSON).when()
                .get("https://api.got.show/api/characters/byid/56ffc5c00432440819385cfe");

        HashMap<String,String> map = response.as(HashMap.class);

        System.out.println(map.keySet());
        System.out.println(map.values());

        Assert.assertEquals(map.get("message"),"Success");



    }
}
