package com.thetestingacademy.ex01_sampleCheck;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.when;
//import static org.hamcrest.Matchers.*;

public class APITesting002 {
    public static void main(String[] args) {

        // Gherkins Syntax
        // Given() -> Pre Req - URL, Headers , Auth, Body ...
        // When() --> HTTP method ? - GET/POST/PUT/PATCH/DELETE
        // Then() --> Validation - 200 ok, Firstname = Pramod

        // Full URL - https://api.zippopotam.us/IN/560003
        // Base URI - https://api.zippopotam.us
        //base Path - /IN/560003

        RestAssured
                .given()
                    .baseUri("https://api.zippopotam.us")
                    .basePath("/IN/560003")
        .when()
                .get()
                .then().log().all().statusCode(200); // Expected status code <404> but was <200>.



    }
}
