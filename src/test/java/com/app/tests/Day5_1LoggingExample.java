package com.app.tests;

import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Day5_1LoggingExample {

    Logger log = Logger.getLogger(Day5_1LoggingExample.class);

    @Before
    public void before(){
        log.info("this is the before method");
    }

    @Test
    public void test(){
        log.trace("this is the test method: TRACE");
        log.info("this is the test method: INFO");
        log.warn("this is the test method: WARN");
        RestAssured.get("https://uinames.com/api/");

    }

    @After
    public void after(){
        log.info("this is the after method");
    }

}
