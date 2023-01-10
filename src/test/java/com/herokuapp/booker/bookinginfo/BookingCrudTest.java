package com.herokuapp.booker.bookinginfo;

import com.herokuapp.booker.bookinginfo.AuthSteps;
import com.herokuapp.booker.bookinginfo.BookingSteps;
import com.herokuapp.booker.testbase.TestBase;
import com.herokuapp.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class BookingCrudTest extends TestBase {

    static String firstName = "Susan" + TestUtils.getRandomValue();
    static String lastName = "Jackson" + TestUtils.getRandomValue();
    static int totalPrice = 299;
    static boolean depositPaid = false;
    static String checkIn = "2021-11-18";
    static String checkOut = "2022-06-02";
    static String additionalNeeds = "Breakfast";
    static String username = "admin";
    static String password = "password123";
    static int bookingId;
    static String token;

    @Steps
    BookingSteps bookingSteps;
    @Steps
    AuthSteps authSteps;

    @Title("Create a new booking")
    @Test
    public void test001() {
        ValidatableResponse response = bookingSteps.createBooking(firstName, lastName, totalPrice, depositPaid, checkIn,
                checkOut, additionalNeeds);
        response.statusCode(200).log().ifValidationFails();
        bookingId = response.extract().path("bookingid");
    }

    @Title("Get the booking")
    @Test
    public void test002() {
        ValidatableResponse response = bookingSteps.getBookingWithBookingId(bookingId);
        response.statusCode(200).log().ifValidationFails();
        response.body("firstname", equalTo(firstName), "lastname", equalTo(lastName),
                "totalprice", equalTo(totalPrice), "depositpaid", equalTo(depositPaid),
                "bookingdates.checkin", equalTo(checkIn), "bookingdates.checkout", equalTo(checkOut),
                "additionalneeds", equalTo(additionalNeeds));
    }

    @Title("Update the booking")
    @Test
    public void test003() {
        firstName = firstName + "_updated";
        lastName = lastName + "_updated";
        additionalNeeds = "Vegetarial meal";
        token = authSteps.getAuthToken(username, password);
        ValidatableResponse response = bookingSteps.updateBooking(bookingId, firstName, lastName, totalPrice,
                depositPaid, checkIn, checkOut, additionalNeeds, token);
        response.statusCode(200).log().ifValidationFails();
        response.body("firstname", equalTo(firstName), "lastname", equalTo(lastName),
                "additionalneeds", equalTo(additionalNeeds));
    }

    @Title("Update the partial booking")
    @Test
    public void test004() {
        firstName = firstName + "_partial";
        lastName = lastName + "_partial";
        token = authSteps.getAuthToken(username, password);
        ValidatableResponse response = bookingSteps.updatePartialBooking(bookingId, firstName, lastName, null,
                null, null, null, null, token);
        response.log().ifValidationFails().statusCode(200);
        response.body("firstname", equalTo(firstName), "lastname", equalTo(lastName),
                "additionalneeds", equalTo(additionalNeeds));
    }

    @Title("Delete the booking")
    @Test
    public void test005() {
        ValidatableResponse response = bookingSteps.deleteBookingWithBookingId(bookingId, token);
        response.statusCode(201).log().ifValidationFails();
        bookingSteps.getBookingWithBookingId(bookingId).log().all().statusCode(404);
    }
}
