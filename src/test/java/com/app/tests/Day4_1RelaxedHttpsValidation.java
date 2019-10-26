package com.app.tests;

import io.restassured.RestAssured;
import org.junit.Test;

public class Day4_1RelaxedHttpsValidation {

   @Test
    public void relexYo(){
       // we going to trust connected with bad certificated

      RestAssured.useRelaxedHTTPSValidation();

       RestAssured.get("https://untrusted-root.badssl.com/")
               .then().log().all()
               .statusCode(200);
   }
}
