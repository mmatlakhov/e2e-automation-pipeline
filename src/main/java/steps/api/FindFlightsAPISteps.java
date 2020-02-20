package steps.api;

import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;

public class FindFlightsAPISteps {
    @Step
    public void findFlights() {
        given().params("fromPort", "Paris", "toPort", "Buenos Aires")
                .when()
                .post("http://blazedemo.com/reserve.php");
    }
}