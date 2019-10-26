package com.app.tests;

import Utilities.BookITRestUtility;
import Utilities.DatabaseUtility;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.*;
import com.github.javafaker.Faker;
import org.apache.log4j.Logger;

import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

public class Day4_4PostRequestExample {

    Logger log = Logger.getLogger(Day4_4PostRequestExample.class);


    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";
    }


    @Test
    public void createTeam() {

        String token = BookITRestUtility.getTeacherToken();

        String team = new Faker().team().name();
        String batch = "8";
        RestAssured.given().
                header("Authorization", token).
                queryParam("campus-location", "VA").
                queryParam("batch-number", batch).
                queryParam("team-name", team).
                when().post("/api/teams/team").
                then().log().all().
                statusCode(201).
                body(is("team "+team+" has been added to the batch "+batch+"."));

        verifyTeamExists(team);
    }

    public static void verifyTeamExists(String team) {
        DatabaseUtility.createConnection();
        String sql = "select * from team where name = '" + team + "';";
        System.out.println(sql);
        Map<String, Object> result = DatabaseUtility.getRowMap(sql);
        Assert.assertNotNull(result);
    }


    public static void veryTeamExist(String team){

       DatabaseUtility.createConnection();
       String qyry = "Select * from team where name = '" +team+ "';";
        System.out.println(qyry);

        Map<String, Object> result = DatabaseUtility.getRowMap(qyry);

        Assert.assertNotNull(result);
    }
}
