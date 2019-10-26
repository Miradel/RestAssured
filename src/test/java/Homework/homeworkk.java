package Homework;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class homeworkk {
    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "https://api.github.com";
    }

    @Test
    public void userTest(){
        // given --> prepare our request
        // submit it to url
        given().pathParam("username", "miradel").
                when().get("/users/{username}").
                prettyPrint();

    }
}
