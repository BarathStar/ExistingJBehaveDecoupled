package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.ViewCarReservationPage

class ViewCarReservationThenSteps {

    ViewCarReservationPage viewCarReservationPage

    @Then("I see the car itinerary")
    def seeTheCarItinerary() {
        viewCarReservationPage.viewCarReservation()
    }

    @Then("I see the associated products to the trip on the Car Reservation page")
    void verifyTheAssociatedProductsToTheTripOnCarReservationPage() {
        viewCarReservationPage.verifyAssociatedItemsDisplayed()
    }

    @Then("I am redirected back to the Car Reservation page")
    void verifyIAmOnTheCancelAirReservationPage() {
        viewCarReservationPage.verifyCurrentPageAndTitle()
    }
}
