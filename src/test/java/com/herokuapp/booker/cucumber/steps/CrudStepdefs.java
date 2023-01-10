package com.herokuapp.booker.cucumber.steps;

import com.herokuapp.booker.bookinginfo.AuthSteps;
import com.herokuapp.booker.bookinginfo.BookingSteps;
import com.herokuapp.booker.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class CrudStepdefs {
    static String username = "admin";
    static String password = "password123";
    static int bookingId;
    static String token;
    static ValidatableResponse response;
    static String firstName1="Keshav"+ TestUtils.getRandomValue();
    @Steps
    BookingSteps bookingSteps;
    @Steps
    AuthSteps authSteps;
    @Given("^URL is given$")
    public void urlIsGiven() {

    }

    @When("^I create booking with firstName \"([^\"]*)\"  lastName\"([^\"]*)\" totalPrice\"([^\"]*)\" depositPaid\"([^\"]*)\" checkin\"([^\"]*)\" checkout\"([^\"]*)\" additionalNeeds\"([^\"]*)\"$")
    public void iCreateBookingWithFirstNameLastNameTotalPriceDepositPaidCheckinCheckoutAdditionalNeeds(String firstName, String lastName, int totalPrice, boolean depositPaid, String checkin, String checkout, String additionalNeeds) {
        response=bookingSteps.createBooking(firstName,lastName,totalPrice,depositPaid,checkin,checkout,additionalNeeds);
        bookingId=(int) response.extract().path("bookingid");
    }

    @Then("^I verify that booking is created$")
    public void iVerifyThatBookingIsCreated() {
        response.statusCode(200);
    }

    @And("^I update booking with firstName \"([^\"]*)\"  lastName\"([^\"]*)\" totalPrice\"([^\"]*)\" depositPaid\"([^\"]*)\" checkin\"([^\"]*)\" checkout\"([^\"]*)\" additionalNeeds\"([^\"]*)\"$")
    public void iUpdateBookingWithFirstNameLastNameTotalPriceDepositPaidCheckinCheckoutAdditionalNeeds(String firstName1, String lastName, int totalPrice, boolean depositpaid, String checkin, String checkout, String additionalNeeds)  {
        token=authSteps.getAuthToken(username,password);
        firstName1=firstName1+"_updated";
        totalPrice=450;
        response= bookingSteps.updateBooking(bookingId,firstName1,lastName,totalPrice,depositpaid,checkin,checkout,additionalNeeds,token);
    }

    @And("^I check user updated successfully$")
    public void iCheckUserUpdatedSuccessfully() {
        response.statusCode(200);
    }

    @And("^I delete booking with bookingId$")
    public void iDeleteBookingWithBookingId() {
        response=bookingSteps.deleteBookingWithBookingId(bookingId,token).statusCode(204);
    }

    @Then("^I verify that booking is deleted successfully$")
    public void iVerifyThatBookingIsDeletedSuccessfully() {
        response=bookingSteps.getBookingWithBookingId(bookingId).statusCode(404);
    }
}
