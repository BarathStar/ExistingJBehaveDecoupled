package steps

import domain.AirReservation
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.When
import pages.SearchCarsPage
import pages.ViewCarReservationPage
import state.ScenarioState
import pages.elements.GlobalNavigationHeader

class CarRetrieveSteps {

    ViewCarReservationPage viewCarReservationPage
    SearchCarsPage searchCarsPage
    ScenarioState scenarioState

    @When("I retrieve the car itinerary")
    def retrieveCarItinerary() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        searchCarsPage.open()
        searchCarsPage.verifyPage()
        searchCarsPage.clickManageCarReservation()
        viewCarReservationPage.verifyPage()
        viewCarReservationPage.fillInCarRetrievalByReservationNumber(airReservation.carReservation)
        viewCarReservationPage.submitRetrieveCarForm()
    }

    @When("I click on the Cancel link for the air product on the Car Reservation page")
    @Alias('I select to cancel the Air product associated to my trip on the Car Reservation page')
    void clickOnCancelAirProduct() {
        viewCarReservationPage.clickOnCancelAirProductLink()
    }

    @When("I attempt to cancel the Car")
    void clickOnCancelCarProduct() {
        viewCarReservationPage.clickOnCancelCarProductLink()
    }
}
