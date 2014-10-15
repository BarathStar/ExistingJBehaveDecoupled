package steps

import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.When
import pages.SelectFlightsPage
import util.ItineraryData

public class DingSteps {

    SelectFlightsPage selectFlightsPage
    ItineraryData itineraryData
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    @When("I go to the Ding select flights page")
    void openDingSelectFlightsPage() {
        dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        selectFlightsPage.openDingSelectFlightPage()
    }

    @When("I book a Ding flight with points")
    void bookDingFlightWithPoints() {
        selectFlightsPage.togglePoints()
        selectFlightsPage.selectFlights()
    }
}
