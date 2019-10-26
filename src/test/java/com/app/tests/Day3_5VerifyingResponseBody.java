package com.app.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class Day3_5VerifyingResponseBody {


    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://uinames.com/api";

    }


    @Test
    public void testBody(){

        given()
                .when()
                .get()
                // body("name" --> first param is locator,get the value from the response
                //second value will be the matcher
                .then().assertThat().body("name", notNullValue());
    }

    @Test
    public void verifyRegion(){
        String region = "Mexico";
        given().queryParam("region",region)
                .when().get()
                .then().log().body()
                .assertThat().body("region",is(region));
    }


    @Test
    public void verifyNumberOfResults(){

        given().queryParam("amount",2)
                .when().get()
                .then().assertThat().body("amount",hasSize(2));


    }

    }
