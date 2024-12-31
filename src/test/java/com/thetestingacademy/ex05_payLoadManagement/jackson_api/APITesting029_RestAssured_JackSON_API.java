package com.thetestingacademy.ex05_payLoadManagement.jackson_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.thetestingacademy.ex05_payLoadManagement.Booking;
import com.thetestingacademy.ex05_payLoadManagement.BookingDates;
import com.thetestingacademy.ex05_payLoadManagement.gson_demo.BookingResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class APITesting029_RestAssured_JackSON_API {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    @Test
   public void test_Create_Booking_Positive() throws JsonProcessingException {
        //Step1 - POST
        // URL -> Base URL + base Path
        // HEADER
        // BODY
        // Auth - NO

        // Step 2
        // Prepare the Payload (Object -> JSON String)
        // Send the request

        // Step 3
        // Validate Response ( JSON String -> Object)
        // FirstName
        // Status Code
        // Time Response


        Booking booking = new Booking();
        booking.setFirstname("Ashutosh11");
        booking.setLastname("Mall11");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

//        // Java Object -> JSON
//        Gson gson = new Gson();
//        String jsonStringBooking = gson.toJson(booking);
//        System.out.println(jsonStringBooking);

        ObjectMapper objectMapper;
        objectMapper = new ObjectMapper();
        String payload;
        try {
            payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

        System.out.println(payload);


        requestSpecification  = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload).log().all();

        Response response = requestSpecification.when().post();
        String jsonResponseString = response.asString();

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Case1 - extract()

        String firstname1 = response.then().extract().path("booking.firstname");
        System.out.println(firstname1);

        // Case2 - jsonPath.getString("")

        JsonPath jsonPath = new JsonPath(response.asString());
       String bookingId = jsonPath.getString("bookingid");
        String firstname = jsonPath.getString("booking.firstname");
        System.out.println(bookingId);
         System.out.println(firstname);

         // Case 3 - DeSer - Extraction

      //  BookingResponse bookingResponse = gson.fromJson(jsonResponseString,BookingResponse.class);

        BookingResponse bookingResponse = null;
        try {

            bookingResponse = objectMapper.readValue(response.asString(), BookingResponse.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

        System.out.println(bookingResponse.getBookingid());
        System.out.println(bookingResponse.getBooking().getFirstname());
        System.out.println(bookingResponse.getBooking().getLastname());

        assertThat(bookingResponse.getBookingid()).isNotZero().isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Ashutosh11").isNotNull().isNotEmpty();









    }
}
