package steps

import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.CheckFlightStatusPage
import pages.FlightStatusPage
import pages.HomePage
import pages.elements.FlightSearchForm
import util.ItineraryData
import util.ItineraryDataBuilder
import util.RandomTicketTypeGroup
import org.jbehave.core.annotations.Then
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import static org.junit.Assert.fail

class CheckFlightStatusSteps {

    CheckFlightStatusPage checkFlightStatusPage
    FlightStatusPage flightStatusPage
    FlightSearchForm flightSearchForm
    ItineraryData itineraryData
    RandomTicketTypeGroup randomTicketTypeGroup
    HomePage homePage
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    @Given("I am on the Check Flight Status page")
    def openFlightStatusFormPage() {
        checkFlightStatusPage.openCheckFlightStatusPage()
    }

    @Given("I am on the Check Another Flight Status page")
    @Alias("I am on the Check Flight Status Results Page")
    def openFlightStatusResultsView() {
        checkFlightStatusPage.openCheckFlightStatusPage()
        //to get to "Check ANOTHER Flight Status Page" we have to enter a station pair
        flightSearchForm.fillInAirports("DAL", "ELP")
        checkFlightStatusPage.submit()
    }

    @Given("I am checking the flight status for a \$carrier flight")
    def chooseCityForFlightStatus(String carrier) {
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup)
            .withOneWayStations(carrier)
            .withDepartingCarrier(carrier)
            .build())
    }

    @When("I check flight status")
    @Alias("I search for the flight status")
    def checkFlightStatus() {
        flightSearchForm.verifyPage()
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        dynaStubsIntegration.prepareWeeklyOrDailyFlightSchedules(itineraryData)
        checkFlightStatusPage.submit()
        flightStatusPage.verifyPage()
     }

    @When("I check flight status with flight number")
    def checkFlightStatusWithFlightNumber() {
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        checkFlightStatusPage.enterFlightNumber(itineraryData.departingFlight_number)
        checkFlightStatusPage.submit()
    }

    @Given("I am in the Southwest Airlines Flight Status Information site")
    def openFlightStatusInformationPage() {
        checkFlightStatusPage.openCheckFlightStatusPage()
    }
}
