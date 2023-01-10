package com.herokuapp.booker.cucumber;

import com.herokuapp.booker.testbase.TestBase;
import com.herokuapp.booker.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/com/herokuapp/booker/resources/feature/")
public class CucumberRunner extends TestBase {

}
