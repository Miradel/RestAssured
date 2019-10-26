package Homework;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static io.restassured.RestAssured.given;

public class HomeWork {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://api.github.com";
    }

    @Test
    public void printAllDefault(){
        given().pathParam("users","")
                .when().get("/users{users}")
                .prettyPrint();
    }


    @Test
    public void printMine(){
        given()
                .pathParam("users","mojombo")
                .when().get("/users/{users}")
                .prettyPrint();
    }

    @Test
    public void printMineRepo(){
        given()
                .pathParam("users","marufjan")
                .queryParam("type","owner")
                .queryParam("sort","created")
                .when().get("/users/{users}/repos")
                .prettyPrint();
    }

    @Test
    public void test1(){

        given().pathParam("user","miradel")
                .when().
                get("/users/{user}")
                .prettyPrint();

    }

    @Test
    public void rowlistShowTest(){

        List<List<Object>> rowList = new ArrayList<>();

        List<Object> sin = new ArrayList<>();
        sin.add(2);
        sin.add(5); sin.add(7);  sin.add(19);

        List<Object> sin2 = new ArrayList<>();
        sin2.add("abc"); sin2.add("kkk");  sin2.add("lol");

        rowList.add(sin);  rowList.add(sin2);

        System.out.println(rowList);

    }

    @Test
    public void mapOfList(){
      //  Map<Map<String, Object>> map = new HashMap<>();

        List<Map<String,Object>> lsmap = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();

        map.put("name","Adil");  map.put("Gender","Male") ; map.put("age",29);


        Map<String, Object> map2 = new HashMap<>();
        map2.put("name","Zhangsaya");  map2.put("Gender","Female") ; map2.put("age",39);

        lsmap.add(map);  lsmap.add(map2);

        System.out.println(lsmap);


    }


    

}
