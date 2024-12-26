package com.thetestingacademy.ex02_RestAssuredBasics.GET;
import io.restassured.RestAssured;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITesting007_GET_BDDStyle {


        @Test
                public void test_GET_Req_POSITIVE(){

            String pincode = "560016";
            RestAssured.given()
                    .baseUri("https://api.zippopotam.us")
                    .basePath("/IN/"+pincode)
                    .when()
                    .log()
                    .all()
                    .get()
                    .then()
                    .log()
                    .all()
                    .statusCode(200);

        }

    @Test
    public void test_GET_Req_NEGATIVE(){

        String pincode = "-1";
        RestAssured.given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
                .when()
                .log()
                .all()
                .get()
                .then()
                .log()
                .all()
                .statusCode(200);

    }



}
