package pages

import org.jbehave.web.selenium.WebDriverProvider
import state.Flow
import util.ItineraryData
import pages.elements.FlightSearchForm
import org.openqa.selenium.By
import fixture.stubs.DynaStubsIntegration
import util.PageName

import static org.junit.Assert.fail
import static util.Locators.BREADCRUMB_IDS

class ChangeTripPage extends BasePage {

    private static final By SELECT_NEW_FLIGHT_BUTTON = By.id("selectNewFlightBtn")
    private static final By RETURN_AIRPORT_DISPLAYED = By.id("returnAirport_displayed")
    private static final By CONTINUE_SODA_MODAL = By.cssSelector("div#sodaValidationModal.simplemodal-data a.submitLink")
    private static final String DATE_FORMAT = "MM/dd/yyyy"
    private static final By WHERE_TO_FLY_LINK = By.cssSelector(".routeMap a")
    private static final By TIME_ANYTIME = By.id("outboundDepartTimeOfDayAnytime")
    private static final By DEPARTURE_CITY = By.id("originAirport_displayed")
    private static final By ARRIVAL_CITY = By.id("destinationAirport_displayed")
    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By PAGE_TITLE = By.cssSelector(".topTitle strong")

    Flow flow

    def verifyBasicPage() {
        verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify change air reservation page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify change air reservation page) ---- checking for fault injection"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["ChangeTripPage"])
        pageVerificationErrorWrapper(SELECT_NEW_FLIGHT_BUTTON, PageName.CHANGE_TRIP)
        waitForElement(PAGE_TITLE).text.shouldBe "Search for New Flights", "Search for new flight title was not present in the change trip page"
    }

    def ChangeTripPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, '/flight/change-trip.html')
    }

    String getRelativePath() {
        return "/flight/change-trip.html"
    }

    ChangeAirReservationPage changeAirReservationPage
    OutboundAndReturnDatesAndPopUp calendarPopUp
    ItineraryData itineraryData
    FlightSearchForm flightSearchForm
    SearchFlightsPage searchFlightsPage
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    def changeAirTravelDates(){
        calendarPopUp.typeInOutboundOrInboundDate(itineraryData.departureDate.format(DATE_FORMAT), 'outboundDate')
        if (itineraryData.returnDate && itineraryData.itineraryType == "Round Trip") {
           calendarPopUp.typeInOutboundOrInboundDate(itineraryData.returnDate.format(DATE_FORMAT), 'returnDate')
        }
    }

    def changeAirTravelInBoundDates(){
        calendarPopUp.typeInOutboundOrInboundDate(itineraryData.returnDate.format(DATE_FORMAT), 'outboundDate')
    }

    def searchForNewFlights(boolean verifyPage = true) {
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        if(itineraryData.itineraryType == "Open Jaw"){
            fillIn(RETURN_AIRPORT_DISPLAYED, itineraryData.returnStation)
        }
        flightSearchForm.saveStations()
        dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        submit(verifyPage)
    }

    def submit(boolean isVerifyPage = true){
        waitForElement(SELECT_NEW_FLIGHT_BUTTON).click()
        clickOnSODAModalIfIsPresent()
        if(isVerifyPage) {
            verifyPage()
        }
    }

    def clickOnSODAModalIfIsPresent(){
        if(isElementPresent(CONTINUE_SODA_MODAL)){
            waitForElement(CONTINUE_SODA_MODAL).click()
        }
    }
    def verifyOopsMessagesOnChangeTripPage(String oopsMsg){
        String textFromErrorWrapper = getExpectedOopsMessage(By.id("attention_wrapper"))
        if (!textFromErrorWrapper.contains(oopsMsg)) {
            fail("The Oops message '$oopsMsg' has not been found within the text " + textFromErrorWrapper)
        }
    }

    def verifyWhereToFlyLink() {
        waitForElement(WHERE_TO_FLY_LINK).getAttribute("title").shouldBe "See Where We Fly", "See where we fly link was not present"
    }

    def verifyAnyTimeSelected() {
        waitForElement(TIME_ANYTIME).getAttribute("selected").shouldBe "true", "Anytime was not selected"
    }

    def verifyStations() {
        waitForElement(DEPARTURE_CITY).getAttribute("value").shouldContain itineraryData.departureCity, "The arrival city was not populated correctly"
        waitForElement(ARRIVAL_CITY).getAttribute("value").shouldContain itineraryData.arrivalCity, "The arrival city was not populated correctly"
    }

    def verifyDate() {
        waitForElement(OUTBOUND_DATE).getAttribute("value").shouldBe itineraryData.departureDate.format("MM/dd/yyyy")
    }
}
