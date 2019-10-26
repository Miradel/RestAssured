package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Day1_2BaseURI_Demo {
    @BeforeClass
    public static void setupClass(){
        // baseURI  used to save the base url for the all resources
        // when we actually make qequests, we only provide the path to specific resources
        RestAssured.baseURI = "https://api.got.show/api/";
    }

    @Test
    public void culturesTest(){
        Response response = RestAssured.get("/cultures/");
        response.prettyPrint();
        Assert.assertTrue(response.asString().contains("circle1"));
    }

    @Test
    public  void citiesTest(){
        Response response = RestAssured.get("/cities/");
        response.prettyPrint();
        Assert.assertTrue(response.asString().contains("loader"));
    }
}
