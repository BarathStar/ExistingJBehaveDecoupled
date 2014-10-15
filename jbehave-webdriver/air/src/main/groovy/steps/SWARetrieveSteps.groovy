package steps

import domain.AirReservation
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.*
import pages.SWARetrievePage
import state.Flow
import util.ItineraryData
import pages.HomePage


class SWARetrieveSteps {
    SWARetrievePage retrievePage
    HomePage homePage
    Flow flow
    ItineraryData itineraryData

    @When("I retrieve my itinerary details")
    def retrieveItinearyDetails() {
        retrievePage.retrieveItineraryUsingPNR()

    }


    @Then("I view my itinerary details")
    def viewItineraryDetails() {
        if (itineraryData.inhibitedPassenger) {
            retrievePage.SWAretrieveItineraryUsingPNR()
            retrievePage.verifyOopsMessageCheckinIneligible()

        } else
        {
            retrievePage.viewItinerary()
    }
    }

}

