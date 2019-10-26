package com.app.tests;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.github.bonigarcia.wdm.SeleniumServerStandaloneManager;
import org.junit.Test;
import pojos.Person;

import java.io.IOException;

public class Day6_1PojoExamples {

    @Test
    public void testGSon(){



        // TODO serialization example
        Person person = new Person();

        person.setName("Daniel");
        person.setSurname("Jackson");
        person.setGender("male");
        person.setRegion("Earth");

        System.out.println("printing the person object: "+person);

        // Gson is a class used to convert objects to json
        Gson gson = new Gson();

        // toJson --> method which converts its parameter to json
        String jsonString = gson.toJson(person);

        System.out.println("printing the json: " + jsonString);


        // TODO deserialization

        String myJsonString = "{\"name\":\"Jack\"," +
                "\"surname\":\"Busio\"," +
                "\"gender\":\"male\"," +
                "\"region\":\"Earth\"}";

        // fromJson()  --> converts the input to given type
        Person myPerson = gson.fromJson(myJsonString, Person.class);
        System.out.println("Printing the new person object:" + myPerson);

    }

    @Test
    public void testJackson() throws IOException {
        // TODO serialization example
        Person person = new Person();

        person.setName("Daniel");
        person.setSurname("Jackson");
        person.setGender("male");
        person.setRegion("Earth");

        System.out.println("printing the person object: "+person);

        // ObjectMapper --> class which converts from and to pojos
        ObjectMapper objectMapper = new ObjectMapper();

        // writeValueAsString()  --> takes the input and converts to json
        String json = objectMapper.writeValueAsString(person);
        System.out.println("printing the json string: "+json);


        // TODO deserialization example
        String myJsonString = "{\"name\":\"Jack\"," +
                "\"surname\":\"ONeil\"," +
                "\"gender\":\"male\"," +
                "\"region\":\"Earth\"}";
        // readValue() --> converts the input to given type
        Person myPerson = objectMapper.readValue(myJsonString, Person.class);
        System.out.println("Printing the new object:" + myPerson);
    }


    @Test
    public void jackson_VS_gson() throws JsonProcessingException {

        // TODO serialization example
        Person person = new Person();

        person.setName("Daniel");
        person.setSurname("Jackson");

        Gson gson = new Gson();
        String j1 = gson.toJson(person);

        ObjectMapper objectMapper = new ObjectMapper();
        String j2 = objectMapper.writeValueAsString(person);

        System.out.println(j1);
        System.out.println(j2);

    }

}
