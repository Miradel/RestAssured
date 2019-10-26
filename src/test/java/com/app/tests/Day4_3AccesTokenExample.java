package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Day4_3AccesTokenExample {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI= "https://cybertek-reservation-api-qa.herokuapp.com/";
    }


    @Test
    public void getTokenTest() {
        Response response = given().log().all().
                param("email", "teacherva5@gmail.com").
                param("password", "maxpayne").
                get("sign");
        response.then().log().all().
                assertThat().statusCode(200);
        // used to parse through json response easily

        String accessToken = response.jsonPath().get("accessToken");

        System.out.println(accessToken);

        // trying to get the my campus
        // we are passing our token as a part of the request header
        RestAssured.given().header("Authorization", accessToken).
                get("/api/campuses").then().log().all().
                assertThat().statusCode(200);

    }



    @Test
    public void getTokenTest2(){

        Response response = given().log().all()
                .param("email","teacherva5@gmail.com")
                .param("password","maxpayne")
                .get("sign");

              response.then().log().all()
                .assertThat().statusCode(200);

//              String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMDY0" +
//                      "IiwiYXVkIjoidGVhY2hlciJ9.japWZkmQ3ExAJA6HxXEMSm92h5ZfYARx1Ci8sSiLgzM";

              String token2 = response.jsonPath().get("accessToken");

//              RestAssured.given().header("Authorization",token2).
//                      get("/api/campuses").then().log().all()
//                      .assertThat().statusCode(200);


        RestAssured.given().header("Authorization",token2).
                get("/api/campuses").then().assertThat().statusCode(200);
    }


}
