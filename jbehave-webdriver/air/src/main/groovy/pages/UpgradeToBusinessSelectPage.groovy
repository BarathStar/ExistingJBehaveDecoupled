package pages

import com.github.tanob.groobe.GrooBe
import domain.AirReservation
import domain.Passenger
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.AirPriceContent
import pages.elements.StopLogicInfo
import state.Flow
import state.ScenarioState;
import util.ItineraryData
import util.PageName
import util.SelectFlightsPageData;

class UpgradeToBusinessSelectPage extends BasePage {

    private static final By BUSINESS_SELECT_BANNER_CONTENT = By.className("bizSelBannerContent")
    private static final By SUBMIT_BUTTON = By.className("submitButton")

    private static final By STOPS_TEXT_DEPARTURE_CONTAINER_BS = By.cssSelector(".airItineraryFlightRouting .tableRowOdd .stopInfo .stopCityMessage")
    private static final By STOPS_CITIES_LIST_DEPARTURE_CONTAINER_BS = By.cssSelector(".airItineraryFlightRouting .tableRowOdd .stopInfo .stopCityList")
    private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER = By.cssSelector(".airItineraryFlightRouting .tableRowOdd .routingDetailsStops .routingDetailsSeparator")
    private static final By PAGE_TITLE = By.cssSelector("#page_title strong")
    private static final By PASSENGER_NAME = By.className("passenger_name")
    private static final By ACCOUNT_NUMBER = By.className("passenger_accountnumber")
    private static final By CONFIRMATION_NUMBER = By.className("passenger_conf")
    private static final By CANCEL_BUTTON = By.id("upgrade-air-cancel")
    private static final By ROUTING_TIMES = By.className("routingDetailsTimes")
    private static final By CITIES = By.className("routingDetailsStops")
    private static final By FLIGHT_NUMBER = By.className("flightNumber")
    private static final By STRONG_TAG = By.tagName("strong")
    private static final By TRAVEL_TIME = By.className("travelFlightDuration")
    private static final By FARE_TYPE = By.className("fareProductName")
    private static final By UPGRADE_TITLE = By.className("upgradeAvailable")
    private static final By UPGRADE_CHECKBOX = By.id("outbound1")
    private static final By ROUTING = By.className("stops")

    StopLogicInfo stopLogicInfo
    ItineraryData itineraryData
    Flow flow
    ScenarioState scenarioState
    SelectFlightsPageData selectFlightsPageData
    AirPriceContent airPriceContent

    WebElement findBizSelectBannerContent(){
        waitForElement(BUSINESS_SELECT_BANNER_CONTENT)
    }

    public UpgradeToBusinessSelectPage(WebDriverProvider driverProvider) {
        super(driverProvider);
        GrooBe.activate()
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    void verifyAirTranFlightsNotAvailableMessageDoesNotShow() {
        findBizSelectBannerContent().getText().shouldNotContain "AirTran Airways operated flights are not able to be retrieved and displayed at this time. We apologize for the inconvenience, please try again",
                "Unexpected text that states that AirTran flights are not available for upgrade."
        // Upgrade check boxes may or may not be available, due to availability in inventory
    }

    void verifyBusinessSelectIsUnavailable() {
        findBizSelectBannerContent().getText().shouldContain "AirTran Airways operated flights are not able to be retrieved and displayed at this time. We apologize for the inconvenience, please try again",
                "Missing text that states that AirTran flights are not available for Business Select upgrade."

        List<WebElement> upgradeCheckboxes = waitForElements(By.className('upgradeCheckbox'))
        upgradeCheckboxes.each {
            it.getText().shouldContain "Upgrade Unavailable", "A flight was not identified as 'Upgrade Unvailable', when this was expected to be for this case."
        }

    }

    void clickToContinue() {
        itineraryData.departingFlight_fareClass = "BusinessSelect"
        flow.isUpgradingFromCheckin = true
        performClickAction(SUBMIT_BUTTON)
    }

    def verifyStopsCitiesInfoOnDepartureAirItineraryOnBsPage() {
        String[] stopsCitiesCodes = itineraryData.getOutboundStopCitiesCodes()
        stopLogicInfo.verifyStopLogicInfo(STOPS_TEXT_DEPARTURE_CONTAINER_BS, STOPS_CITIES_LIST_DEPARTURE_CONTAINER_BS, stopsCitiesCodes)
    }

    def verifyChangePlaneInfoOnDepartureAirItineraryOnBsPage(){
        String changePlaneCityCode = itineraryData.outboundConnectingStation
        stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER, changePlaneCityCode)
    }

    def verifyBasicPage() {
        verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify select flights to upgrade page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify select flights to upgrade page) ---- checking for fault injection"
        }
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.SELECT_FLIGHTS_TO_UPGRADE)
        waitForElement(PAGE_TITLE).text.shouldBe "Select Flights to Upgrade", "title was not displayed correctly"

    }

    def verifyNameAccNumberAndConfirmationNumber() {
        String nameInPage = waitForElement(PASSENGER_NAME).text.split(':')[1]
        String accountNumber = waitForElement(ACCOUNT_NUMBER).text.split(':')[1]
        String confirmationNumberInPage = waitForElement(CONFIRMATION_NUMBER).text.split('# ')[1]
        AirReservation airReservation = scenarioState.getLastAirReservation()
        confirmationNumberInPage.shouldBe airReservation.adultPnr, "Confirmation Number was incorrect"
        Passenger passenger = airReservation.getPassengers().get(0)
        String firstName = passenger.firstName
        String lastName = passenger.lastName
        nameInPage.shouldContain firstName.toUpperCase(), "First name was not displayed correctly"
        nameInPage.shouldContain lastName.toUpperCase(), "Last name was not displayed correctly"
        if(!flow.isLoggedIn){
            accountNumber.shouldContain "None Entered", "The accoun number was not None Entered"
        }
    }

    def verifyContinueButton() {
        isElementPresent(SUBMIT_BUTTON).shouldBe true, "Continue button was nos displayed"
    }

    def verifyCancelButton() {
        isElementPresent(CANCEL_BUTTON).shouldBe true, "Cancel button was not displayed"
    }

    def verifyDate() {
        List<String> ddateBreakDown = itineraryData.departureDate.toString().split(" ")
        List<String> airPriceContentDepartureDate = airPriceContent.travelDepartDate().split(" ")
        airPriceContentDepartureDate[0].shouldContain ddateBreakDown[0], "Departure day was not correct"
        airPriceContentDepartureDate[1].shouldContain ddateBreakDown[1], "Departure month was not correct"
        airPriceContentDepartureDate[2].replace(',','').toInteger().shouldEqual ddateBreakDown[2].replace(',','').toInteger(), "Departure date was not correct"
        airPriceContentDepartureDate[3].shouldContain ddateBreakDown[5], "Departure year was not correct"
        if (itineraryData.isRoundTripOrOpenJaw()) {
            List<String> rdateBreakDown = itineraryData.returnDate.toString().split(" ")
            List<String> airPriceContentReturnDate = airPriceContent.travelReturnDate().split(" ")
            airPriceContentReturnDate[0].shouldContain rdateBreakDown[0], "Departure day was not correct"
            airPriceContentReturnDate[1].shouldContain rdateBreakDown[1], "Departure month was not correct"
            airPriceContentReturnDate[2].replace(',','').toInteger().shouldEqual rdateBreakDown[2].replace(',','').toInteger(), "Departure date was not correct"
            airPriceContentReturnDate[3].shouldContain rdateBreakDown[5], "Departure year was not correct"
        }
    }

    def verifyDepartureAndArrivalTime() {
        List<WebElement> outboundTimes = waitForElements(ROUTING_TIMES)
        outboundTimes[0].text.replaceAll("\\s", "").shouldContain selectFlightsPageData.outboundDepartureTime.replaceAll("\\s", "").toUpperCase(), "Outbound flight departure time did not match flight time on Bug Page"
        outboundTimes[1].text.replaceAll("\\s", "").shouldContain selectFlightsPageData.outboundArrivalTime.replaceAll("\\s", "").toUpperCase(), "Outbound flight arrival time did not match flight time on Bug Page"
        if(flow.hasConnectionFlight) {
            outboundTimes[2].text.replaceAll("\\s", "").shouldContain selectFlightsPageData.outboundConnectionDepartureTime.toUpperCase(), "Outbound connection flight depart time did not match flight time on Bug Page"
            outboundTimes[3].text.replaceAll("\\s", "").shouldContain selectFlightsPageData.outboundConnectionArrivalTime.toUpperCase(), "Outbound connection flight arrival time did not match flight time on Bug Page"
        }
    }

    def verifyDepartureAndArrivalCities() {
        List<WebElement> outboundCities = waitForElements(CITIES)
        outboundCities[0].text.shouldContain itineraryData.departureStation, "Departure station did not match departure station from the Bug page"
        outboundCities[1].text.shouldContain itineraryData.outboundConnectingStation, "Arrival station did not match arrival station from the Bug page"
        outboundCities[2].text.shouldContain itineraryData.outboundConnectingStation, "Connection departure station did not match connection departure station from the Bug page"
        outboundCities[3].text.shouldContain itineraryData.arrivalStation, "Connection arrival station did not match connection arrival station from the Bug page"
    }

    def verifyFlightNumber() {
        List<WebElement> flightNumbers = waitForElements(FLIGHT_NUMBER)
        flightNumbers[0].findElement(STRONG_TAG).text.shouldContain selectFlightsPageData.departingFlight_number, "Outbound flight number did not match flight number from the Bug page"
        if (flow.hasConnectionFlight){
            flightNumbers[1].findElement(STRONG_TAG).text.shouldContain selectFlightsPageData.departingConnectingFlight_number, "Outbound connection flight number did not match flight number from the Bug page"
        }
    }

    def verifyTravelTime() {
        String timeOnPage = waitForElement(TRAVEL_TIME).text.replaceAll("\\s", "").replaceFirst("TravelTime", "")
        selectFlightsPageData.outboundTravelTime.replaceAll("\\s", "").shouldContain timeOnPage, "Outbound Travel time did not match travel time from the Bug page"
    }

    def verifyFareType() {
        waitForElement(FARE_TYPE)?.getText().replaceAll(" ", "").shouldBe itineraryData.departingFlight_fareClass, "Fare type did not mach the selected one"
    }

    def verifyRoutingType() {
        waitForElement(ROUTING)?.text.shouldContain selectFlightsPageData.outboundFlyoutRouting, "Routing type did not match the selected one"
    }

    def verifyUpgradeCheckbox() {
        waitForElement(UPGRADE_TITLE).text.shouldContain "Upgrade", "Upgrade Text was not present"
        waitForElement(UPGRADE_CHECKBOX).getAttribute("checked").shouldBe "true", "the checkbox was not checked"
    }
}
