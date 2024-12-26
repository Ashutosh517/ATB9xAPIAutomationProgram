package com.thetestingacademy.ex02_RestAssuredBasics.GET;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting008_GET_NonBDDStyle {

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    @Description("Verify the GET Req Positive")
    @Test
    public void test_GET_NonBDDStyle(){

        // Rest Assured Interfaces

        String pincode = "560016";
                r =  RestAssured
                .given();
                r.baseUri("https://api.zippopotam.us");
                r.basePath("/IN/"+pincode);

                response = r.when().log().all().get();

                vr =response.then()
                .log()
                .all()
                .statusCode(200);


    }

    @Description("Verify the GET Req Negative : -1 Input")
    @Test
    public void test_NonBDDStyleGET_negative(){

        String pincode = "-1";
        r =  RestAssured
                .given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/"+pincode);

        response = r.when().log().all().get();

        vr =response.then()
                .log()
                .all()
                .statusCode(200);

    }

    @Test
    public void test_NonBDDStyleGET_negative2(){

        //String pin_code = "@@@!!##";
        // String pin_code = "null";

        String pincode = "10000000000000000000000";
        r =  RestAssured
                .given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/"+pincode);

        response = r.when().log().all().get();

        vr =response.then()
                .log()
                .all()
                .statusCode(200);

    }

}
