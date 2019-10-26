package com.app.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Day3_3StatusCodeValidation {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://uinames.com/api";

    }


    @Test
    public void testOK(){
        // given ==> perpare request
        given().

        // the submit request
        when().get().

        // then verify response
        // statusCode ==> verify the status code
        then().statusCode(200);

    }

    @Test
    public void notFoundTest(){

        given()
                .when()
                .get("sfdsdas")
                .then()
                .statusCode(404);

    }

}
