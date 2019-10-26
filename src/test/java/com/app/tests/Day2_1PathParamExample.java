package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Day2_1PathParamExample {

    /*
      PATH PARAMETERS is a part of URL and followed by the end of full resource url.
      (Path Param) is basically used to identify a specific resource or resources whereas Query
       Parameter is used to sort/filter those resources.
     */

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://api.got.show/api";
    }

    @Test
    public void getCityByNameTest(){
        // statement where we prepare the request
        given()
                // adding param 'name' with value 'Qarth'
                .pathParam("name", "Braavos").
                // send the request
                        when().
                // {name}  --> will be replaces by value of the path param
                        get("/cities/{name}").
                prettyPrint();
    }

    @Test
    public void getCharacterByname(){
        given()
                .pathParam("name","Aggo")
             .when()
                .get("/characters/{name}")
             .prettyPrint();
    }// https://api.got.show/api/characters/:name

    @Test
    public void getCharacterById(){
//        given()
//                .pathParam("id","56ffc5c00432440819385cfe")
//                .when()
//                .get("characters/byId/{id}")
//                .prettyPrint();

        given().pathParam("id","56ffc5c00432440819385cfe")
                .when().get("characters/byId/{id}")
                .prettyPrint();
    }// https://api.got.show/api/characters/byId/:id
}
