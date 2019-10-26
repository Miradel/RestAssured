package com.app.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Day2_2QueryParameter_example {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://uinames.com/api";
    }

    @Test
    public void testAmount(){
//        given()
//                .queryParam("amount","2")
//        .when()
//                .get()
//        .prettyPrint();


        given().queryParam("amount","3")
                .when().get().prettyPrint();
    }

    @Test
    public void testRegion(){
//        given()
//                .queryParam("region","Colombia")
//                .when()
//                .get().prettyPrint();


        given().queryParam("region","Japan")
                .queryParam("amount","2")
                .when().get().prettyPrint();
    }


    @Test
    public void test(){
        given()
                .queryParam("region","United States")
                .queryParam("gender","male")
                .queryParam("amount","2")
                .when()
                .get().prettyPrint();
    }
}
