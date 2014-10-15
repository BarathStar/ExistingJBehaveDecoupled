package pages.elements

import fixture.stubs.DynaStubsIntegration
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.BasePage
import pages.OutboundAndReturnDatesAndPopUp
import util.ItineraryData
import util.ItineraryDateFactory

class FlightSchedulesSearchForm extends BasePage {


    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By ORIGIN_AIRPORT_DISPLAYED = By.id("originAirport_displayed")
    private static final By DESTINATION_AIRPORT_DISPLAYED = By.id("destinationAirport_displayed")

    OutboundAndReturnDatesAndPopUp calendarPopUp
    ItineraryData itineraryData

    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    public String getOutboundDate() {
        return "outboundDate"
    }

    public FlightSchedulesSearchForm(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return "" // not needed for elements
    }

    void searchForFlightSchedule(String origin, String destination, Date departureDate) {
        itineraryData.departureStation = origin
        itineraryData.arrivalStation = destination
        itineraryData.departureDate = departureDate

        fillInFlightInfoForSchedules(itineraryData)
    }

    void searchForWeeklyFlightSchedules(String origin, String destination, Date departureDate) {
        itineraryData.departureStation = origin
        itineraryData.arrivalStation = destination
        itineraryData.departureDate = departureDate

        fillInFlightInfoForSchedules(itineraryData)
        dynaStubsIntegration.prepareWeeklyFlightSchedules(itineraryData)

    }

    def fillInFlightInfoForSchedules(ItineraryData itineraryData) {
        fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        enterDepartureDate(itineraryData.departureDate)
    }

    void fillInAirports(String originAirport, String destinationAirport) {

        waitForElement(ORIGIN_AIRPORT_DISPLAYED).sendKeys(originAirport)
        waitForElement(DESTINATION_AIRPORT_DISPLAYED).sendKeys(destinationAirport)

    }

    def  enterDepartureDate(Date departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate(), String outboundDateId = getOutboundDate()) {
        calendarPopUp.clearTextById(outboundDateId)
        calendarPopUp.typeInOutboundOrInboundDate(departureDate.format("MM/dd/yyyy"), outboundDateId)
    }

}
