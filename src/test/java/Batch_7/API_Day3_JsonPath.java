package Batch_7;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class API_Day3_JsonPath {


    @Test
    public void testItemsCountFromResponsebody(){

        given().accept(ContentType.JSON)
                .when()
                .get("https://api.github.com/users/miradel/repos")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType(ContentType.JSON)
                .and().assertThat().body("owner.id",hasSize(10))
                .and().assertThat().body("owner.login",hasItem("Miradel"))
                .and().assertThat().body("owner.login",hasItems("Miradel","Miradel"))
                .and().assertThat().body("owner.id",hasItem(46764882));

    }

    @Test
    public void test(){
        given().accept(ContentType.JSON).when()
                .and().params("amount",5).log().body()
                .get("https://uinames.com/api")
                .then().assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .and().assertThat().body("$",hasSize(5));
    }


    @Test
    public void test2(){
        given().accept(ContentType.JSON).when()
                .and().pathParam("id","56ffc5c00432440819385cfe")
                .get("https://api.got.show/api/characters/byid/{id}").then()
               .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .and().assertThat().body("data._id",equalTo("56ffc5c00432440819385cfe"))
                .and().assertThat().body("data.books",hasItem("A Game of Thrones"))
                .and().assertThat().body("data.male",equalTo(true))
               ;
     /*
    "message": "Success",
    "data": {
        "_id": "56ffc5c00432440819385cfe",
        "imageLink": "/misc/images/characters/Pypar.jpeg",
        "male": true,
        "house": "Night's Watch",
        "slug": "Pypar",
        "name": "Pypar",
        "__v": 0,
        "pageRank": 35,
        "books": [
            "A Game of Thrones",
            "A Clash of Kings",
                */
    }

    @Test
    public void jsonPath(){
        Response response =  given().accept(ContentType.JSON).when()
                .and().pathParam("id","56ffc5c00432440819385cfe")
                .get("https://api.got.show/api/characters/byid/{id}");


       JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getInt("data.pageRank"));

        System.out.println(jsonPath.getBoolean("data.male"));

        List<String> ids = jsonPath.getList("_id");

        System.out.println(ids);
    }

    @Test
    public void jsonPath2(){
        Response response =  given().accept(ContentType.JSON).when()
                .get("https://api.got.show/api/characters");


        JsonPath jsonPath = response.jsonPath();

//        System.out.println(jsonPath.getInt("data.pageRank"));
//
//        System.out.println(jsonPath.getBoolean("data.male"));

        List<String> ids = jsonPath.getList("_id");
        System.out.println(ids);

        List<String> names = jsonPath.getList("name");
        System.out.println(names);


        List<String> nameWithCondition = jsonPath.getList("items.findAll{it.name > b}.name");
        System.out.println(nameWithCondition);


    }




}
