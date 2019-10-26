package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class Day1_1SimpleRestDemo {


    @Test
    public void test1(){
        /*
         RestAssured ==> is class from rest assured library, it contains method and
         objects used for creating and sending requests.
          */

        //get method for making a get request, accept the resource uri as parameter
        // response --> represent the response that came back from server based on out request
       Response response = RestAssured.get("https://uinames.com/api/");

       response.prettyPrint();
      // response.print();

        String resString = response.asString();
        Assert.assertTrue(resString.contains("name"));

        // statusCode returns the respond code
        int code = response.getStatusCode();
        Assert.assertEquals(200,code);
    }

    @Test
    public void headersTest(){
        Response response = RestAssured.get("https://uinames.com/api/");
        // returns the headers of the respones
        System.out.println(response.headers());

        // header --. return the value of header
        String contentType = response.header("Content-Type");

        System.out.println(contentType);
        Assert.assertEquals("application/json; charset=utf-8",contentType);
    }

    @Test
    public void statusLineTest(){
        Response response = RestAssured.get("https://uinames.com/api/");
        //Status line --> returns the all information in the status line of yhe response
        String statusLine = response.statusLine();
        System.out.println(statusLine);
        Assert.assertTrue(statusLine.contains("508"));

    }
}
