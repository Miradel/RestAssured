package com.app.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.json.Json;
import pojos.Instructor;

import java.io.IOException;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class Day6_3JsonPayloadExample2 {

    @Test
    public void test() throws IOException {

        Instructor instructor = new Instructor();
        String firstName = new Faker().name().firstName();
        String lastName = new Faker().name().lastName();
        String subject = new Faker().book().title();
        int batch = new Faker().number().numberBetween(1,12);

     instructor.setFirstName(firstName);
     instructor.setLastName(lastName);
     instructor.setSubject(subject);
     instructor.setBatch(batch);

        System.out.println(instructor);

        RestAssured.baseURI = "http://cybertekchicago.com/";

       Response postResponse = given().contentType(ContentType.JSON).body(instructor)
                .post("/instructor/create/");

//        Response postResponse = given().body(instructor)
//                .post("/instructor/create/");

       postResponse.then().log().all();
       postResponse.then().statusCode(200);

       int id = postResponse.path("id");
       String nnn = postResponse.path("lastName");
        System.out.println(nnn);
        System.out.println("--------------------------------");

       Response getResponse = given().pathParam("id",id)
               .when().get("/instructor/{id}");

       getResponse.then().log().all().assertThat().statusCode(200);

        ObjectMapper objectMapper = new ObjectMapper();
        Instructor instructor1 = objectMapper.readValue(getResponse.asString(),Instructor.class);
        System.out.println(instructor1);

        Assert.assertEquals(instructor1.getFirstName(),instructor.getFirstName());

        assertThat(instructor.getFirstName(),is(instructor1.getFirstName()));


        Gson gson = new Gson();
        Instructor instructor2 = gson.fromJson(getResponse.asString(),Instructor.class);










    }
}
