package com.thetestingacademy.ex02_RestAssuredBasics.DELETE;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting012_DELETE_NONBddStyle {


    // PUT
    // token, booking id - A

    // public void get_token(){}// In future
    // public void get_booking_id(){} // In future

    @Description("Verify the DELETE Request for the Restful Booker APIs")
    @Test
    public void test_patch_non_bdd(){

        String token = "722e97875e90c81";
        String bookingid = "2060";


        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        requestSpecification.log().all();

        Response response = requestSpecification.when().delete();

        ValidatableResponse validatableResponse = response.then().log().all();

        validatableResponse.statusCode(201);

    }


}
