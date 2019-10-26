package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Day3_4HeaderValidation {
    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://uinames.com/api";

    }


    @Test
    public void headerTest(){


        given().when().get(). then()
                .header("Content-Type","application/json; charset=utf-8");
    }
    }
