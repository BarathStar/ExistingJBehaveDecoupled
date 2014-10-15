package steps.thenSteps

import pages.SelectFlightsToChangePage
import org.jbehave.core.annotations.Then

class SelectFlightsToChangeThenSteps {
    SelectFlightsToChangePage selectFlightsToChangePage

    @Then("I should not see an early bird upsell on the select a flight to change page")
    def verifyEarlyBirdUpsellLinkNotAvailable() {
        selectFlightsToChangePage.verifyEarlyBirdUpsellNotPresent()
    }

    @Then("I am able to select the inbound flight for change")
    void verifyCanSelectInboundFlightForChange() {
        selectFlightsToChangePage.verifyCheckBoxToSelecInBoundToChangeIsVisible()
    }

    @Then("I am not able to select the missed flight for change")
    void verifyCanNotSelectOutboundFlightForChange() {
        selectFlightsToChangePage.verifyCheckBoxToSelectOutBoundToChangeIsNotVisible()
    }

    @Then("I see the information of my retrieved flight on the Air Associated Products Modal")
    def verifyAirInfoOnAirChangeModal() {
        selectFlightsToChangePage.verifyAirInfoOnAirChangeModal()
    }

    @Then("I see the hotel product as an item associated to my trip on the Air Associated Products Modal")
    def verifyAssociatedHotelProductOnAirChangeModal() {
        selectFlightsToChangePage.verifyAssociatedHotelProductOnAirChangeModal()
    }

    @Then("I should not see the car product as an associated item on the Air Associated Products Modal")
    def verifyAssociatedCarProductIsNotPresentOnAirChangeModal() {
        selectFlightsToChangePage.verifyAssociatedCarProductIsNotPresentOnAirChangeModal()
    }

    @Then('I see "Southwest Airlines - Change Itinerary" page')
    void verifySelectFlightsToChangePage(){
        selectFlightsToChangePage.verifyCurrentPageLocation()
        selectFlightsToChangePage.verifyPageAndTitle()
    }
}
