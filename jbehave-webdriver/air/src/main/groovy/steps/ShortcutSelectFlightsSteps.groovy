package steps

import org.jbehave.core.annotations.When
import pages.ShortcutSelectFlightsPage
import util.ItineraryData

class ShortcutSelectFlightsSteps {

    ShortcutSelectFlightsPage shortcutSelectFlightsPage
    ItineraryData itineraryData

    @When("I select the previous month for my returning flight")
    def selectPreviousMonthForReturnFlight() {
        shortcutSelectFlightsPage.changeMonth(-1, "RETURN")
        itineraryData.returnDate.month = itineraryData.returnDate.month - 1
    }

    @When("I select the following month for my departing flight")
    def selectFollowingMonthForDepartureFlight() {
        shortcutSelectFlightsPage.changeMonth(1, "DEPARTURE")
        itineraryData.departureDate.month = itineraryData.departureDate.month + 1
    }

    @When("I select a departure month greater than the returning month")
    def selectDepartureGreaterThanReturn() {
        Integer monthsToUpdate = shortcutSelectFlightsPage.selectDepartureAfterReturn(itineraryData.departureDate, itineraryData.returnDate)
        itineraryData.departureDate.month = itineraryData.departureDate.month + monthsToUpdate
        itineraryData.returnDate.month = itineraryData.returnDate.month + 1
    }

    @When("I select a return month less than the departure month")
    def selectReturnLessThanDeparture() {
        Integer monthsToUpdate = shortcutSelectFlightsPage.selectReturnBeforeDeparture(itineraryData.departureDate, itineraryData.returnDate)
        itineraryData.returnDate.month = itineraryData.returnDate.month - monthsToUpdate
        itineraryData.departureDate.month = itineraryData.departureDate.month - 1
    }

    @When("I get the select flights page from dream trips")
    def openTheSelectFlightsPageFromDreamTrips() {
        shortcutSelectFlightsPage.openFromDreamTrips();
    }
}
