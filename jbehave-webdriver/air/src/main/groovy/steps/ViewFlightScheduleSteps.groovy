package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Alias
import pages.FlightSchedulesPage
import pages.elements.FlightSchedulesSearchForm
import pages.elements.FlightSearchForm
import util.ItineraryData

public class ViewFlightScheduleSteps {

    FlightSchedulesPage flightSchedulePage
    FlightSchedulesSearchForm flightSchedulesSearchForm
    FlightSearchForm flightSearchForm
    ItineraryData itineraryData

    @Given("I am on the flight schedule page")
    @When("I am on the flight schedule page")
    def goToFlightsSchedulePage() {
        flightSchedulePage.goToFlightSchedulePage()
        flightSchedulePage.verifyPage()
    }

    @When("I search for the flight schedule")
    @Alias("I search for a daily flight schedule")
    void checkFlightSchedules() {
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        flightSearchForm.fillInFlightDates(itineraryData);
        flightSchedulePage.submit()
        flightSchedulePage.verifyPage()
    }

    @When("I search for a weekly flight schedule")
    void checkWeeklyFlightSchedules() {
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        if(itineraryData.departureDate != null) {//outBoundDate was provided from table data as departureDate
          flightSearchForm.enterDepartureDate(itineraryData.departureDate)
        }
        flightSchedulePage.searchWeekly()
    }

    @When("I search for a daily schedule from \$origin to \$destination \$numberOfDays days from today")
    def searchDailyFlightSchedule(String origin, String destination, int numberOfDays) {
        flightSchedulesSearchForm.searchForFlightSchedule(origin, destination, util.ItineraryDateFactory.getNumberDaysOutDate(numberOfDays))
        flightSchedulePage.submit()
    }

    @When("I search for a weekly schedule from \$origin to \$destination \$numberOfDays days from today")
    def searchWeeklyFlightSchedule(String origin, String destination, int numberOfDays) {
        flightSchedulesSearchForm.searchForWeeklyFlightSchedules(origin, destination, util.ItineraryDateFactory.getNumberDaysOutDate(numberOfDays))
        flightSchedulePage.searchWeekly()
    }
}
