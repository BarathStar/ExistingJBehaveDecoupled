package steps.thenSteps

import org.jbehave.core.annotations.Then

import pages.FlightSchedulesPage
import pages.FlightStatusPage
import util.ItineraryData

class FlightScheduleThenSteps {

    FlightSchedulesPage flightSchedulePage
    FlightStatusPage flightStatusPage
    ItineraryData itineraryData

    @Then("I view a table of flight options from \$origin to \$destination for \$numberOfDays days from today")
    def viewScheduleResult(String origin, String destination, int numberOfDays) {
        flightSchedulePage.verifyRouteInformationInTitle(origin, destination)
        flightSchedulePage.verifyOneActiveDayDisplayed(util.ItineraryDateFactory.getNumberDaysOutDate(numberOfDays))
    }

    @Then("I view the daily schedule with flights from \$origin to \$destination for \$numberOfDays days from today")
    def viewDailyScheduleResult(String origin, String destination, int numberOfDays) {
        viewScheduleResult(origin, destination, numberOfDays)
        flightSchedulePage.shouldHaveAtLeastOneFlightNumber()
    }

    @Then("I view a table of flight options from \$origin to \$destination week starting \$numberOfDays days from today")
    def viewWeeklyScheduleResult(String origin, String destination, int numberOfDays) {
        flightSchedulePage.verifyRouteInformationInTitle(origin, destination)
        flightSchedulePage.verifyOneWeekDisplayedFrom(util.ItineraryDateFactory.getNumberDaysOutDate(numberOfDays))
    }

    @Then("I should see itineraries matching my search on the flight schedule page")
    def viewFlightItineraries() {
        flightSchedulePage.verifyPage()
        flightStatusPage.verifyStationsInTable(itineraryData.departureStation, itineraryData.arrivalStation)
        flightStatusPage.verifyFlightStatusIsCollapsed()
    }

    @Then("I should see that flight number \$flightNumber has departure time of \$departureTime and arrival time of \$arrivalTime")
    def verifyFlightScheduleDateAndTime(String flightNumber, String departureTime, String arrivalTime){
        flightSchedulePage.verifyDepartureTimeByFightNumber(flightNumber,departureTime)
        flightSchedulePage.verifyArrivalTimeByFightNumber(flightNumber,arrivalTime)
    }

    @Then("I see the daily flight schedules results for the given itinerary")
    def verifyDailyScheduleResultsTablePresence() {
        flightSchedulePage.verifyDailyFlightSchedulesTableDisplayed()
    }

    @Then("I see the weekly flight schedules results for the given itinerary")
    def verifyWeeklyScheduleResultsTablePresence() {
        flightSchedulePage.verifyWeeklyFlightSchedulesTableDisplayed()
    }

    @Then("I should see daily flight \$flightNumber Operated by \$operatedBy")
    def verifyDailyOperatedBy(String flightNumber, String operatedBy) {
        flightSchedulePage.verifyDailyFlightNumberOperatedBy(flightNumber,operatedBy)
    }
    @Then("I should see weekly flight \$flightNumber Operated by \$operatedBy")
    def verifyWeeklyOperatedBy(String flightNumber, String operatedBy) {
        flightSchedulePage.verifyWeeklyFlightNumberOperatedBy(flightNumber,operatedBy)
    }
}