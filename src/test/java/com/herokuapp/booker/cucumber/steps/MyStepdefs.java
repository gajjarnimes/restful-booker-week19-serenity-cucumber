package com.herokuapp.booker.cucumber.steps;


import com.herokuapp.booker.bookinginfo.BookingSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;



public class MyStepdefs {
    static ValidatableResponse response;
    static String firstName;
    static String lastName;
    static  String checkIn;
    static String checkOut;
    static String additionalNeeds;
    static String token;


    @Steps
    BookingSteps bookingSteps;


    @When("^User sends a GET request to booking endpoint$")
    public void userSendsAGETRequestToBookingEndpoint() { response = bookingSteps.getAllBookingIds();
    }

    @Then("^User must get back a valid status code 200$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }

    @When("^User sends a GET request for booking/10 endpoint$")
    public void userSendsAGETRequestForBookingEndpoint() { response = bookingSteps.getBookingWithBookingId(10);
    }

    @When("^User update booking details of bookingId 10 endpoint$")
    public void userUpdateBookingDetailsOfBookingIdEndpoint(int bookingId) { response = bookingSteps.getBookingWithBookingId(10);
    }

    @When("^User delete booking details of bookingId 10 endpoint$")
    public void userDeleteBookingDetailsOfBookingIdEndpoint(int bookingId) {response = bookingSteps.deleteBookingWithBookingId(10,token);

    }

    @Then("^User must get back a valid status code 204$")
    public void userMustGetBackAValidStatusCode(int bookingId ) {response.statusCode(204);
    }
}

