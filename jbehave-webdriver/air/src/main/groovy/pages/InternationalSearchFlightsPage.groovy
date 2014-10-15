package pages;


import org.jbehave.web.selenium.WebDriverProvider
import util.ItineraryData
import util.ItineraryDateFactory
import state.PassengerData
import org.openqa.selenium.WebElement
import static util.Locators.ELEMENT_IDS
import org.openqa.selenium.By
import fixture.stubs.DynaStubsIntegration

public class InternationalSearchFlightsPage extends BasePage {

    private static final String RELATIVE_PATH = '/flight/international-domestic-airtran-flights.html'

    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By ONE_WAY_BUTTON = By.id("oneWay")
    private static final By ROUND_TRIP_BUTTON = By.id("roundTrip")
    private static final By ADULT_PASSENGER_COUNT = By.id("adultPassengerCount")
    private static final By ADULT_PASSENGER_COUNT_BY_NAME = By.name("adultPassengerCount")
    private static final By SEARCH_FLIGHT_BUTTON = By.id("submitButton")

    PassengerData passengerData
    OutboundAndReturnDatesAndPopUp calendarPopUp

    private WebElement adultPassengerCount = null

    public InternationalSearchFlightsPage(WebDriverProvider driverProvider) {
        super(driverProvider, RELATIVE_PATH);
    }

    String getRelativePath() {
        return RELATIVE_PATH
    }

    private def adultPassengerElement() {
        return adultPassengerCount ?: waitForElement(ADULT_PASSENGER_COUNT_BY_NAME)
    }

    def open() {
        super.open()
    }

    void searchForInternationalFlight(ItineraryData itineraryData) {

        selectAirTripType(itineraryData)

        itineraryData.departureDate = itineraryData.departureDate?: ItineraryDateFactory.getAnyAvailableDepartureDate()
        itineraryData.returnDate = itineraryData.returnDate?:itineraryData.departureDate.plus(1)

        fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        enterDepartureDate(itineraryData.departureDate)

        checkPassengerCount()

        if (!itineraryData.isOneWay()) {
            enterReturnDate(itineraryData.returnDate)
        }
        //dyna stub support
        if (DynaStubsIntegration.useDynaStubs()) {
            final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
            dynaStubsIntegration.prepareWeeklyOrDailyFlightSchedules(itineraryData)
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }
        submit()
    }

    private def checkPassengerCount() {
        if (passengerData.passengers.size() == 0) {
            addAdultPassengers(adultPassengerElement().getAttribute("value").toInteger())
        }
    }

    def selectAirTripType(ItineraryData itineraryData) {
        if(itineraryData.isOneWay()){waitForElement(ONE_WAY_BUTTON).click()}
        if(itineraryData.isRoundTrip()){waitForElement(ROUND_TRIP_BUTTON).click()}
    }

    void fillInAirports(from, to) {
        fillIn(By.id(ELEMENT_IDS["From"]), from)
        fillIn(By.id(ELEMENT_IDS["To"]), to)
    }

    private void addAdultPassengers(int adultPassengers) {
        for (int i = 0; i < adultPassengers; i++) {
            passengerData.addAdultPassenger()
        }
    }

    def submit() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def enterReturnDate(Date returnDate) {
        calendarPopUp.typeInOutboundOrInboundDate(returnDate.format("MM/dd/yyyy"), "returnDateAir")
    }

    def enterDepartureDate(Date departureDate) {
        calendarPopUp.typeInOutboundOrInboundDate(departureDate.format("MM/dd/yyyy"), "outboundDateAir")
    }

    void enterStation(String field, String inputData) {
        fillIn(By.id(ELEMENT_IDS.get(field)),inputData)
    }
    void escFromOutBoundCalenderField() {
        WebElement inputElement = findElement(By.id("outboundDate"))
           inputElement.sendKeys(ESCAPE)
    }

    void clickSearch() {
        findElement(SEARCH_FLIGHT_BUTTON).click()
    }

    def verifyFieldCleared(List <String> field) {
            field.each {it-> verifyInField(ELEMENT_IDS.get(it), "")}
     }
}
