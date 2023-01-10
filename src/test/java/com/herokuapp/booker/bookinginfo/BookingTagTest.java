package com.herokuapp.booker.bookinginfo;

import com.herokuapp.booker.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class BookingTagTest extends TestBase {
    @WithTags({
            @WithTag("restfulbookerfeature:POSITIVE"),
            @WithTag("restfulbookerfeature:SMOKE")})

    @Title(("Provide a 200 status code when valid request send it to the application"))
    @Test
    public void verifyIfTheStatusCodeIs200() {
        SerenityRest.rest()
                .given()
                .get("/booking")
                .then()
                .statusCode(200)
                .log().all();
    }
    @WithTag("restfulbookerfeature:NEGATIVE")
    @Title("when you send invalid request get statuscode:404")
    @Test
    public void invalidCode(){
        SerenityRest.rest()
                .given()
                .when()
                .patch("/booking/2")
                .then()
                .statusCode(404);
    }
    @WithTags({ @WithTag("restfulbookerfeature:SENITY"),@WithTag("restfulbookerfeature:NEGATIVE")})
    @Title("This Test will give an error code of 403 when user tries to access invalid resource")
    @Test
    public void invalidResource(){
        SerenityRest.rest()
                .given()
                .when()
                .get("/booking/6")
                .then()
                .statusCode(403);
    }

}




