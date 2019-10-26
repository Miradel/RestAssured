package Utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.bytebuddy.asm.Advice;

import static io.restassured.RestAssured.given;

public class BookITRestUtility {

    public static void main(String[] args) {
        System.out.println(getTeacherToken());
    }

    public static String getTeacherToken() {
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";


        Response response = given().log().all().
                param("email", "teacherva5@gmail.com").
                param("password", "maxpayne").
                get("/sign");
        response.then().log().all().
                assertThat().statusCode(200);
        return response.jsonPath().get("accessToken");
    }


    public static String getTeacherToken2() {


        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";

       return given().
               param("email","teacherva5@gmail.com")
               .param("password","maxpayne")
               .get("/sign").jsonPath().get("accessToken");
    }

    public static String getTecherToken3(){

        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";

        return given().param("email","teacherva5@gmail.com")
                .param("password","maxpayne")
                .get("/sign")
                .jsonPath().get("accessToken");
    }


}


