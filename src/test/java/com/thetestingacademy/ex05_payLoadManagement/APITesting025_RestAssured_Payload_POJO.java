package com.thetestingacademy.ex05_payLoadManagement;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class APITesting025_RestAssured_Payload_POJO {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    String token;
    Integer bookingId;


    @Test
    public void test_POST(){
//        String payloadPOST = "{\n" +
//                "    \"firstname\" : \"Ashutosh11\",\n" +
//                "    \"lastname\" : \"Mall11\",\n" +
//                "    \"totalprice\" : 111,\n" +
//                "    \"depositpaid\" : true,\n" +
//                "    \"bookingdates\" : {\n" +
//                "        \"checkin\" : \"2018-01-01\",\n" +
//                "        \"checkout\" : \"2019-01-01\"\n" +
//                "    },\n" +
//                "    \"additionalneeds\" : \"Breakfast\"\n" +
//                "}";
//        Map<String, Object> jsonBodyUsingMap = new LinkedHashMap();
//        jsonBodyUsingMap.put("firstname","Ashutosh11");
//        jsonBodyUsingMap.put("lastname","Mall1");
//        jsonBodyUsingMap.put("totalprice", "111");
//        jsonBodyUsingMap.put("depositpaid",true);
//
//        Map<String,Object> bookingDateMap = new LinkedHashMap();
//        bookingDateMap.put("checkin","2018-01-01");
//        bookingDateMap.put("checkout","2019-01-01");
//
//        jsonBodyUsingMap.put("bookingdates","bookingDateMap");
//        jsonBodyUsingMap.put("additionalneeds","Breakfast");
//        System.out.println(jsonBodyUsingMap);

        // Map -> JSON? (GSON,Jackson API)
        //{firstname=Ashutosh11, lastname=Mall1, totalprice=111, depositpaid=true, bookingdates=bookingDateMap, additionalneeds=Breakfast}

        // POJO

        Booking booking = new Booking();
        booking.setFristname("Ashutosh11");
        booking.setLastname("Mall11");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);




        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/");
        requestSpecification.contentType(ContentType.JSON);
       requestSpecification.body(booking).log().all();

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

       // String s = "";//Empty
        // String s2 = " ";// Blank



    }

}
