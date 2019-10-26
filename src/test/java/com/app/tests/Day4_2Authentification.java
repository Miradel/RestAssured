package com.app.tests;

import io.restassured.RestAssured;
import org.junit.Test;

public class Day4_2Authentification {

    @Test
    public void usingApiKey(){

        RestAssured.given().log().all()
                .queryParam("t","Kung Fury")
                .queryParam("apikey","1e9d7e2d")
                .when().get("http://www.omdbapi.com/").then().log().all()
                .assertThat().statusCode(200);

    }


    @Test
    public void basicAuthentication401Validation(){
        // https://the-internet.herokuapp.com/basic_auth
        // when we try to hit and endpoint without bening authorized
        // we will get 401 not authorized error

        RestAssured.get("https://the-internet.herokuapp.com/basic_auth")
                .then()
                .assertThat().statusCode(401);

    }

    /*
    Two types of basic authentication:

    1. challenged basic authentication. --> in the challenged authentication, rest assured will not send
     use username and password initially. it will only send it once the server asks for it.

    2.Preemptive basic authentication: rest assured sends credentials (username, password) before server
     asks for them
     */

    @Test
    public void basicAuthentification(){
        // auth ==> provides diffrent types of authentication
        // based ==> in the challenged authentication, rest assured will not send
        //     use username and password initially. it will only send it once the server asks for it.
        RestAssured.given()
                .auth().basic("admin","admin")
                .when().get("https://the-internet.herokuapp.com/basic_auth")
                .then().assertThat().statusCode(200);
    }

    @Test
    public void basicPreemtiveAuthentification(){
        /*
         auth --> provides different types of authentication
         Preemptive basic authentication: rest assured sends credentials (username, password) before server
         asks for them
          */

        RestAssured.given().
                auth().preemptive().basic("admin", "admin").
                when().get("https://the-internet.herokuapp.com/basic_auth").
                then().assertThat().statusCode(200);
    }


    @Test
    public void xmlResponceExample(){
        // this os not a athorization example
        // this method is to show request which returns xml body
        RestAssured.get("http://parabank.parasoft.com/parabank/services/bank/customers/12212")
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
