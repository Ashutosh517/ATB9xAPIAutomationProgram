package com.thetestingacademy.ex03_Assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class APITesting024_RestAssured_TestNG_AssertJ_Assertions {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    String token;
    Integer bookingId;


    @Test
    public void test_POST(){
        String payloadPOST = "{\n" +
                "    \"firstname\" : \"Ashutosh11\",\n" +
                "    \"lastname\" : \"Mall11\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";


        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payloadPOST).log().all();

        Response response = requestSpecification.when().post();

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Rest Assured -> import org.hamcrest.Matchers;
        // Matchers.equalto()


        validatableResponse.body("booking.firstname",Matchers.equalTo("Ashutosh11"));
        validatableResponse.body("booking.lastname",Matchers.equalTo("Mall11"));
        validatableResponse.body("booking.depositpaid",Matchers.equalTo(true));
        validatableResponse.body("bookingid",Matchers.notNullValue());

        // TestNG Assertion

        // SoftAssert vs
        // HardAssert -
        // This means that if any assertion fails,
        // the remaining statements in that test method will not be executed.

        bookingId = response.then().extract().path("bookingid");
        String firstname = response.then().extract().path("booking.firstname");
        String lastname = response.then().extract().path("booking.lastname");

        // TestNG Assertions

        Assert.assertNotNull(bookingId);
        Assert.assertEquals(firstname,"Ashutosh11");
        Assert.assertEquals(lastname,"Mall11");

        // AssertJ( 3rd- Lib to Assertions)

        assertThat(bookingId).isNotNull().isNotZero().isPositive();
        assertThat(firstname).isEqualTo("Ashutosh11").isNotEmpty().isNotNull().isAlphanumeric().isNotBlank();

        String s = "";//Empty
        String s2 = " ";// Blank



    }

}
