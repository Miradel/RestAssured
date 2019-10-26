package com.app.tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
//import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Person;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Day6_2ResponseToPojoExample {

    @Test
    public void test() throws IOException  {

        Response response = given().get("https://uinames.com/api/");

        /*
         We use to use in this way traditionally:
        String name = response.path("name");
        System.out.println("name = " + name);
         */


        /*
         Now we can Ue REST ASSURED instead:
          values from the json response is automatically mapped to corresponding fields
          in the person object
         */

        Person p1 = response.as(Person.class);
        System.out.println("p1.getName() = " + p1.getName());
        System.out.println("p1.getSurname() = " + p1.getSurname());
        System.out.println("p1.getGender() = " + p1.getGender());
        System.out.println("p1.getRegion() = " + p1.getRegion());
        System.out.println(p1);

        // USING GSON
        Gson gson = new Gson();
        Person p2 = gson.fromJson(response.asString(), Person.class);
        System.out.println("p2 = " + p2);
//
//        // USING JACKSON DATABIND
//
        ObjectMapper objectMapper = new ObjectMapper();
        Person p3 = objectMapper.readValue(response.asString(), Person.class);
        System.out.println("p3 = " + p3);

    }

    @Test
    public void testPractice(){
        Response response = given()
                .queryParam("region","Canada")
                .when()
                .get("https://uinames.com/api/");
        Person pr = response.as(Person.class);
        System.out.println("pr.getName() = "+ pr.getName());
        System.out.println("region:  "+ pr.getRegion());
        System.out.println("pr gender "+ pr.getGender());
        System.out.println("pr surname " + pr.getSurname());

        // We built our Jason above , so we need to adjust it to java version in the button

        Gson gson = new Gson();
        Person pr2 = gson.fromJson(response.asString(),Person.class);
        System.out.println("pr2 :"+ pr2);

    }


    @Test
    public void responseListOfObjects() {
        Response response = given().
                queryParam("amount", "5").
                queryParam("region","Turkey")
                .when().get("https://uinames.com/api/");

        List<Person> people = response.jsonPath().getList("", Person.class);

        for (Person person : people) {
            System.out.println(person);
        }

    }

    @Test
    public void listPractice(){

        Response response = given().queryParam("amount",6)
                .get("https://uinames.com/api/");

        List<Person> pl = response.jsonPath().getList(".",Person.class);

        for (Person pe: pl) {
            System.out.println(pe);
        }
    }
}
