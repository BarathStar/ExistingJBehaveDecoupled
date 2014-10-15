package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.CheckFlightStatusPage
import pages.FlightStatusPage
import util.ItineraryData

import static org.junit.Assert.fail

class FlightStatusThenSteps {
    CheckFlightStatusPage checkFlightStatusPage
    FlightStatusPage flightStatusPage
    ItineraryData itineraryData

    @Then("I will be asked for details of the flight to check")
    def checkCheckFlightStatusPage() {
        checkFlightStatusPage.verifyPage()
    }

    @Then("I should see the specific flight number")
    void verifyFlightStatusIsNotCollapsed() {
        flightStatusPage.verifyFlightStatusDisplays()
        flightStatusPage.verifyFlightNumberInTable(itineraryData.departingFlight_number)
    }

    @Then("I check operated by AirTran status")
    def verifyIfOperatedByAirTran() {
        flightStatusPage.verifyOperationByAirTran()
    }

    @Then("I should see an error on the Flight Status page")
    def verifyNoFlightsForStationsOnDateError() {
        flightStatusPage.verifyNoFlightsForStationsOnDateError(itineraryData.departureStation,
                itineraryData.arrivalStation)
    }

    @Then("I see the Airport Information pop-up")
    void verifyAirportInfoPopOut() {
        flightStatusPage.verifyAirportInfoPopOut()
    }

    @Then("I should see itineraries matching my search on the flight status page")
    def viewFlightItineraries() {
        flightStatusPage.verifyPage()
        flightStatusPage.verifyStationsInTable(itineraryData.departureStation, itineraryData.arrivalStation)
        flightStatusPage.verifyFlightStatusIsCollapsed()
    }

    @Then("I see the flight status results for the given itinerary")
    def verifyFlightStatusTableIsPresent() {
        flightStatusPage.verifyFlightStatusTableIsPresent()
    }

    @Then("I should see Estimated: HH:MM AM/PM inside flight # details row in the Departure or Arrival Time columns")
    def verifyDelayedFlightStatusDetailHasEstimatedTime() {
        //
        // Webdriver have no way to distinguish through the UI whether the service tier have replied DELAYED or else
        // WILL ADVISE for a given leg status. The only visual clue is Estimated: HH:MM AM/PM or else Estimated: TBD,
        // but that is precisely the post condition we want to check, so we cannot use it.
        //
        // Instead we rely on the stubbed response set by requestFlightLegsByScheduleDateFlightNumberStub.xml.
        // The jBehave test assume the stubbed service answer with a flight with two legs with the following statuses:
        //
        // - the first leg have status DELAYED
        // - the second leg have status WILL ADVISE
        //
        // Hence in the flight # details row (the one displayed when user clicks View Status link) the Departure Time
        // corresponds to a Delayed leg and the Arrival Time to a Will Advise leg.
        //
        String estimation = flightStatusPage.findDepartureTime().getText()
        if (! estimation.matches("Estimated:\\s*\\d\\d?:\\d\\d (AM|PM)")) {
            fail("Details for a delayed flight should contains and estimated time as: "
                + "\"Estimated: HH:MM [AM|PM]\" but instead we got " + estimation)
        }
    }

    @Then("I should see Estimated: TBD inside flight # details row in the Departure or Arrival Time columns")
    def verifyWillAdviseFlightStatusDetailHasTBDEstimatedTime() {
        // idem verifyDelayedFlightStatusDetailHasEstimatedTime(), see comments on that method for explanation
        // on why using arrival time here
        String estimation = flightStatusPage.findArrivalTime().getText()
        if (! estimation.matches("Estimated:\\s*TBD\\s*")) {
            fail("Details for a \"will advise\" flight should contains and estimated time as: "
                    + "\"Estimated: TBD\" but instead we got " + estimation)
        }
    }
}
