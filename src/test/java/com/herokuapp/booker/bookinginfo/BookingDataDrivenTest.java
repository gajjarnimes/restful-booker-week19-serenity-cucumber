package com.herokuapp.booker.bookinginfo;

import com.herokuapp.booker.bookinginfo.BookingSteps;
import com.herokuapp.booker.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/java/com/herokuapp/booker/resources/testdata/bookinginfo.csv")
@Concurrent(threads = "4x")

public class BookingDataDrivenTest extends TestBase {
    private String firstName;
    private String lastName;
    private int totalPrice;
    private boolean depositpaid;
    private String checkIn;
    private String chekcOut;
    private String additionalneeds;

    @Steps
    BookingSteps bookingSteps;

    @Title(("Create booking info for multiple booking on the application"))
    @Test
    public void createMultiplebooking() {
        List<String> booking = new ArrayList<>();
        booking.add(firstName);
        booking.add(lastName);
        booking.add(additionalneeds);
        booking.add(checkIn);
        booking.add(chekcOut);

        bookingSteps.createBooking(firstName, lastName, totalPrice, depositpaid, checkIn, chekcOut, additionalneeds).statusCode(201);
    }
}
