package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Day3_2TestAssuredLoggingExample {

    @Test
    public void loggingTest(){
        // log is provide logging options for request
        given().log().all()
                .when().get("https://uinames.com/api");

        System.out.println("--------------------------------------------------------------");

        // log response
       Response response = RestAssured.get("https://uinames.com/api");
       response.then().log().all();
    }
}
