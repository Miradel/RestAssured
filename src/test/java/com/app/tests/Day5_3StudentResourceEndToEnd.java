package com.app.tests;

import Utilities.BookITRestUtility;
import Utilities.DatabaseUtility;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.jws.Oneway;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Day5_3StudentResourceEndToEnd {

    Logger log = Logger.getLogger(Day5_3StudentResourceEndToEnd.class);

    @Test
    public void postStudent() throws InterruptedException {
        // Create test data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName.toLowerCase()+lastName.toLowerCase()+"@"+faker.internet().domainName();
        String password = firstName.toLowerCase()+lastName.toLowerCase();
        String role = "student-team-member";
        String campusLocation = "VA";
        String batchNumber = "8";
        String teamName = "CodeHunters";

        String expectedMessage = "user "+firstName+" "+lastName+" has been added to database.";

        String token = BookITRestUtility.getTeacherToken();

        given().
                log().all().
                header("Authorization", token).
                queryParam("first-name", firstName).
                queryParam("last-name", lastName).
                queryParam("password", password).
                queryParam("email", email).
                queryParam("role", role).
                queryParam("campus-location", campusLocation).
                queryParam("batch-number", batchNumber).
                queryParam("team-name", teamName).
                when().
                post("api/students/student").
                then().
                log().all().
                assertThat().statusCode(201).
                body( is(expectedMessage));

        // GET THE NEW USER FROM DB USING JDBC
        Map<String, Object> dbUser = getDBUser(email);
        log.info(dbUser);
        // VERIFY THE DB DATA HAS THE CORRECT INFO
        assertThat(dbUser.get("firstname"), Matchers.<Object>is(firstName));
//        assertThat(dbUser.get("firstname"), is(firstName));
//        assertThat(dbUser.get("lastname"), is(lastName));
//        assertThat(dbUser.get("email"), is(email));
        // verify that id is not empty
        assertThat(dbUser.get("id"), is(notNullValue()));

        // GET THE USER INFO FROM DB USING API
        String id = dbUser.get("id").toString();

        given().
                log().all().
                header("Authorization", token).
                pathParam("id", id).
                when().
                get("/api/students/{id}").
                then().
                log().all().
                assertThat().statusCode(200).
                body("firstName", is(firstName)).
                body("lastName", is(lastName)).
                body("role", is(role));

        // VERIFY USING UI

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://cybertek-reservation-qa.herokuapp.com/sign-in");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password + Keys.ENTER);
        Thread.sleep(5000);
        assertThat(driver.getCurrentUrl(), endsWith("map"));


    }


    public static Map<String, Object> getDBUser(String email){
        DatabaseUtility.createConnection();
        String sql = "select firstname, lastname, role, id, email from users " +
                "where email = '"+email+"';";
        Map<String, Object> rowMap = DatabaseUtility.getRowMap(sql);
        DatabaseUtility.closeConnection();
        return rowMap;
    }

    @Test
    public void postStudent2() throws InterruptedException {

        String token = BookITRestUtility.getTecherToken3();
        String expectedMassage = "user abcde123 xyzzz has been added to database.";

        given().header("Authorization",token)
                .param("first-name","abcde123")
                .param("last-name","xyzzz")
                .param("batch-number","8")
                .param("email","nkjaddnsk@gmail.com")
                .param("password","asdca")
                .param("role","student-team-member")
                .param("campus-location","VA")
                .param("team-name","CodeHunters")
                .when().post("api/students/student")
                .then().assertThat().statusCode(200)
                .body(is(expectedMassage));

        // GET THE NEW USER FROM DB USING JDBC
        Map<String, Object> dbUser = getDBUser("nkjaddnsk@gmail.com");
        // Verify thr DataBase has correct informations
        Assert.assertEquals(dbUser.get("firstname"),"abcde123");


        //Get the user info from DB using API
        String id = dbUser.get("id").toString();
        given().header("Authorization",token)
                .pathParam("id",id)
                .get("api/students/{id}")
                .then().assertThat().statusCode(200)
                .body("firstname",is("abcde123"))
                .body("lastname",is("xyzzz"));


        // Verify using UI
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://cybertek-reservation-qa.herokuapp.com/sign-in");

        driver.findElement(By.name("email")).sendKeys("nkjaddnsk@gmail.com");
        driver.findElement(By.name("password")).sendKeys("asdca"+Keys.ENTER);
        Thread.sleep(4000);
        assertThat(driver.getCurrentUrl(),endsWith("map"));




    }

    /*
    Test Case: User creation and update End to End scenario
  CREATE
	Create a new random user using the Add a Student end point
	verify status code 201
	verify the response body
	get the new user details from the database
	get the new user details from Get a student by id endpoint
	verify both information is accurate
	login to the application using the new user and verify user logged in

UPDATE
	verify the status
	update the user password using the Update a student endpoint
	try to login to the application using the old pass and verify not logged
	login to the application using the new pass and verify logged

DELETE
	verify the status code
	verify that in database user with that email does not exist
	try to login to the application and verify not logged


//  baseURL      resource             ? --> query param
// {{qa1_url}}/ api/students/student  ?first-name=Kunkka&last-name=Admiral&email=admiraklkunka11@somemail.com&
         password=admiralkunka&role=student-team-member&campus-location=VA&batch-number=8&team-name=CodeHunters
     */
}
