package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Day1_3warmUp {

    /*
    EX 1.
    get all continents from the https://api.got.show/api
    verify continent "Westeros" exists in results.

    EX 2.
        get all episodes from the https://api.got.show/api
        verify episode "Rains of Castamere" exists in resutls

        Create a new class, and do both exercises in same class, different methods
     */

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://api.got.show/api/";
    }

    @Test
    public void test1(){
        Response response = RestAssured.get("/continents/");
        Assert.assertEquals(200,response.statusCode());
        response.prettyPrint();
        Assert.assertTrue(response.asString().contains("Westeros"));
    }

    @Test
    public void test2(){
        Response response = RestAssured.get("/episodes/");
        Assert.assertEquals(200,response.statusCode());
        response.prettyPrint();
        Assert.assertTrue(response.asString().contains("Rains of Castamere"));
    }

}
