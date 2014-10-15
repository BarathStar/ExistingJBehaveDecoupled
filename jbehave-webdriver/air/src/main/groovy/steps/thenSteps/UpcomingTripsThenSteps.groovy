package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.UpcomingTripsPage
import util.HotelItineraryData
import util.ItineraryData
import state.ScenarioState

class UpcomingTripsThenSteps {

    UpcomingTripsPage upcomingTripsPage
    ItineraryData itineraryData
    HotelItineraryData hotelItineraryData
    ScenarioState scenarioState

    @Then("I see the Upcoming Trips page")
    def verifyUpcomingTripsPage() {
        upcomingTripsPage.verifyCurrentPageLocation()
        upcomingTripsPage.verifyPageTitle("Upcoming Trips")
    }

    @Then("I see a message indicating that there are no Upcoming Trips in Upcoming Trips page")
    def verifyNoUpcomingTripsMessage() {
        upcomingTripsPage.verifyNoUpcomingTripsMessage()
    }

    @Then('I see the details of the products from the trip are listed by date first and then by type on the Upcoming Trips page')
    def verifyProductsDetails(){
        String[] productsOrder = ["car","air","hotel"]
        upcomingTripsPage.verifyProductsOrder(productsOrder)
        upcomingTripsPage.verifyDataCarReservation()
        upcomingTripsPage.verifyDataAirReservation()
        upcomingTripsPage.verifyDataHotelReservation()
    }

    @Then("I see on the Trip Details page that the Hotel reservation has no trip name provided by the user")
    def verifyTripNamePattern() {
        upcomingTripsPage.verifyISeeDefaultTripNameUnderTripDetailsSection()
    }

    @Then("I see the details of the Hotel reservation on the Trip Details page")
    def verifyHotelDetails() {
        upcomingTripsPage.verifyISeeHotelNameUnderTripDetailsSection()
        upcomingTripsPage.verifyISeeHotelCheckInCheckOutDatesUnderTripDetailsSection(hotelItineraryData.checkInDate, hotelItineraryData.checkOutDate)
        upcomingTripsPage.verifyISeeHotelConfirmationNumberUnderTripDetailsSection(hotelItineraryData.confirmationNumber)
    }

    @Then("I see only the domestic trip and not the international trip")
    def verifyOnlyDomesticTripsAreDisplayed() {
        upcomingTripsPage.verifyISeeAirConfirmationNumberUnderTripDetailsSection(scenarioState.getFirstAirReservation().getAdultPnr());
        upcomingTripsPage.verifyIDoNotSeeAirConfirmationNumberUnderTripDetailsSection(scenarioState.getLastAirReservation().getAdultPnr());
    }

    @Then("I see the Upcoming Trip Details Page")
    def verifyDetailsUpcomingTrip() {
        upcomingTripsPage.verifyPage()
        upcomingTripsPage.verifyPageTitle("Upcoming Trip Details")
    }
}
