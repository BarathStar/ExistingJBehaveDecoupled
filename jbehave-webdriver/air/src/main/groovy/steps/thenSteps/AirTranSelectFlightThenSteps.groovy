package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.AirTranSelectFlightsPage
import pages.AirTranSeatSelectPage

class AirTranSelectFlightThenSteps {
    AirTranSelectFlightsPage airTranSelectFlightsPage
    AirTranSeatSelectPage airTranSeatSelectPage

    @Then("I should see the AirTran search results page")
    def viewAirTranSearchFlightsPage() {
        airTranSelectFlightsPage.isDisplayed()
    }

    @Then("I see seat selection navitaire maintenance message")
    public void seatSelectionMessage() {
        airTranSeatSelectPage.verifyOutboundSeatSelectionNavitaireModeMessageForConfirmationPage()
    }
}
