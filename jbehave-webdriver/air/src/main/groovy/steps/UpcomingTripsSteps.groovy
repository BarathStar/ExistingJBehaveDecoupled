package steps

import org.jbehave.core.annotations.When
import pages.TripDetailsPage
import pages.UpcomingTripsPage
import state.ScenarioState

class UpcomingTripsSteps {

    UpcomingTripsPage upcomingTripsPage
    TripDetailsPage tripDetailsPage
    ScenarioState scenarioState

    @When("I select an Air reservation which is not part of a trip from Upcoming Trips page")
    def clickOnViewAirDetailsLink() {
        upcomingTripsPage.clickOnViewAirDetailsLink()
    }

    @When("I select a Car reservation which is not part of a trip from Upcoming Trips page")
    def clickOnViewCarDetailsLink() {
      upcomingTripsPage.clickOnViewCarDetailsLink()
      tripDetailsPage.verifyPage()
    }

    @When("I click Add Rapid Rewards Number Link in the page")
    def clickAddRapidRewardsNumberLink(){
        tripDetailsPage.clickAddRapidRewardsNumberLink()
    }

    @When ("I click on my last reservation link to see the flight details")
    def clickViewTripDetailsForPNR(){
        upcomingTripsPage.clickViewDetailFlightForPNRWithCities(scenarioState.getLastAirReservation().getItineraryData().arrivalStation.toString(), scenarioState.getLastAirReservation().getItineraryData().departureStation.toString())
    }
}
