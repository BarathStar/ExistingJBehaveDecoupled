package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import state.Flow
import state.ScenarioState
import util.ItineraryData;
import pages.elements.PnrRetrieval
import pages.elements.StopLogicInfo
import util.PageName

class ChangeAirReservationPage extends BasePage {

    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By BREADCRUMB = By.xpath("//div[@id='breadcrumbs']//*[@class='current_step']/img")
    private static final By EXPECTED_OOPS_MESSAGE_SELECTOR = By.id("oopsSuccesses")
    private static final By PRICE_ITINERARY_SUBMIT_ID = By.id("priceItinerarySubmit")
    private static final By PASSENGER_ROW_NAME = By.className("passenger_row_name")
    private static final By CONFIRMATION_NUMBER = By.className("confirmation_number")
    private static final By AIR_ITINERARY = By.className("airItineraryTable")
    private static final int NUMBER_OF_FLIGHTS = 2
    private static final By AIRTRAN_DISCLAIMER_ID = By.id("airtran_disclaimer")
    private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER = By.cssSelector(".airItineraryTable .flightRouting .airItineraryFlightRouting .routingDetailsStops.routingDetailsSeparator")
    private static final By LOOKUP_CONFIRMATION_LINK = By.className("formLookupConfirmation")

    PnrRetrieval pnrRetrieval
    Flow flow
    ScenarioState scenarioState
    ItineraryData itineraryData
    StopLogicInfo stopLogicInfo

    String getRelativePath() {
        return "/flight/change-air-reservation.html"
    }

    def ChangeAirReservationPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, '/flight/change-air-reservation.html')
    }

    def retrieveAndSubmitItineraryToChange(String pnr, String pnr_firstName, String pnr_lastName) {
        pnrRetrieval.retrievePnrAndSubmit(pnr, pnr_firstName, pnr_lastName)
        flow.isAirChange = true;
    }

    def verifyUnableToRetrievePNROOPSMessage() {
        verifyElementPresent("Unable to Retrieve PNR OOPS message", waitForElement(EXPECTED_OOPS_MESSAGE_SELECTOR))
        // Changed search text until DCAIR-5432 has been resolved.
        waitForElement(EXPECTED_OOPS_MESSAGE_SELECTOR).text.shouldContain "We were unable to retrieve your reservation from our database. Verify the following:",
                "Text expected in the OOPS message was not present."
    }

    def verifyInvalidPnrOopsMessage() {
        pnrRetrieval.verifyInvalidPnrOopsMessage()
    }

    def selectOutBoundToChangeItinerary() {
        selectCheckBoxifNotSelected("outbound1")
    }

    def selectInBoundToChangeItinerary() {
        selectCheckBoxifNotSelected("inbound1")
    }

    def continueToChangeItinerary() {
        WebElement submitButton = waitForElement(By.className("submitButton"), false)
        if (submitButton && submitButton.isDisplayed() && submitButton.isEnabled()) {
            submitButton.click()
        }
        else {
            waitForElement(By.id("searchNewFlightButton")).click()
            waitForElement(By.id('continueCancelation')).click()
        }
        verifyPage()
    }

    def continueToRepricePage() {
        waitForElement(PRICE_ITINERARY_SUBMIT_ID).click()
    }

    def continueToReconcilePage() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    private def selectCheckBoxifNotSelected(String field) {
        def checkbox = waitForElement(By.id(field))
        if (!checkbox.isSelected()) {
            checkbox.click()
        }
    }

    def verifyChange() {
        waitForElement(BREADCRUMB).getAttribute("alt").shouldBe "Itinerary Changed"
    }

    def selectItineraryToChange(outbound, inbound) {
        if (outbound) {
            selectOutBoundToChangeItinerary()
        }
        if (inbound) {
            selectInBoundToChangeItinerary()
        }
    }

    def verifyFlightInProgressOopsMessage() {
        getExpectedOopsMessage().shouldContain "You cannot make changes to your current itinerary while your flight is in progress. Please try again later.",
                        "Your flight should be in progress and Oops message should appear."
    }

    def verifyIamOnTheCorrectPage(String url) {
        getCurrentUrl().contains(url).shouldBe true
    }

    def verifyPassengerNames() {
        String passengerName = "${flow.getUser().firstName}" + " " + "${flow.getUser().lastName}"
        WebElement passengerNameElement

        passengerNameElement = waitForElement(PASSENGER_ROW_NAME)
        passengerNameElement.getText().shouldContain passengerName, "The passenger name should be: " + passengerName
    }

    def verifyConfirmationNumber() {
        String confirmationNumber = scenarioState.getLastAirReservation().getAdultPnr()
        WebElement confirmationNumberElement

        confirmationNumberElement = waitForElement(CONFIRMATION_NUMBER)
        confirmationNumberElement.getText().shouldContain confirmationNumber, "The confirmation number should be: " + confirmationNumber
    }

    def verifyTicketForRoundTrip() {
        List airItinerary = waitForElements(AIR_ITINERARY)
        airItinerary.size().shouldBe NUMBER_OF_FLIGHTS, "The itinerary should contain " + NUMBER_OF_FLIGHTS + " flight"
    }

    def verifyAirtranDisclaimerMessage(){
        waitForElement(AIRTRAN_DISCLAIMER_ID)
    }

    def verifyChangePlaneInfoOnDepartureAirItineraryInChangeReservationPage(){
        String changePlaneCityCode = itineraryData.outboundConnectingStation
        stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER, changePlaneCityCode)
    }

    def verifyBasicPage() {
        verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify change air reservation page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify change air reservation page) ---- checking for fault injection"
        }
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.CHANGE_AIR_RESERVATION)
    }

    def verifyLookupConfirmationLink() {
        isElementPresent(LOOKUP_CONFIRMATION_LINK).shouldBe true, "LookUp confirmation link was not present"
    }
}
