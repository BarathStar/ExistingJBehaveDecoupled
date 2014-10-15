package pages

import com.swacorp.dotcom.webscenarios.air.BillingData
import domain.Passenger
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.AirPriceContent
import pages.elements.PnrRetrieval
import pages.elements.StopLogicInfo
import pages.mixins.*
import state.Flow
import state.ScenarioState
import util.AlteaUrlValidationHelper
import util.ItineraryData
import util.PageName
import util.PurchasePageData
import util.RRContactInformation
import util.SelectFlightsPageData

class CancelAirReservationPage extends BasePage {

    private static final By CONFIRM_CANCELLATION_BUTTON_ID = By.id("confirmCancellationButton")
    private static final By CONTINUE_CANCELLATION_ID = By.id("continueCancelation")
    private static final By CANCEL_CANCELLATION_BUTTON = By.name("cancelCancellation")
    private static final By FUTURE = By.id("future")
    private static final By AIR_MODAL_ERROR = By.cssSelector(".trip_itinerary_detail_table_error_message")
    private static final By HOTEL_MODAL_ERROR = By.cssSelector(".itineraries_header.hotel_itinerary_container_with_image")
    private static final By CAR_MODAL_ERROR = By.cssSelector(".itineraries_header.car_itinerary_container_with_image")
    private static final By AWARDS_DETAILS_HEADING = By.cssSelector("#awardDetails .heading")
    private static final By AWARDS_DETAILS_NUMBER = By.cssSelector("#awardDetails .cancelTravelFundsCenter")
    private static final By AWARDS_DETAILS_RETURNING_MESSAGE = By.cssSelector("#awardDetails label")
    private static final String POINTS_TEXT = "Points"
    private static final By PAGE_TITLE = By.id("page_title")
    public static final String CONFIRMATION_PATH =  "flight/cancel-reservation.html"
    private static final By AIRTRAN_DISCLAIMER_ID = By.id("airtran_disclaimer")
    private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER = By.cssSelector(".airItineraryTable .flightRouting .airItineraryFlightRouting .routingDetailsStops.routingDetailsSeparator")
    private static final By DOMESTIC_CANCELLATION_LINK_ID = By.id("domesticCancelReservationLink")
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By LOOKUP_CONFIRMATION = By.className("formLookupConfirmation")
    private static final By PASSENGER_NAME = By.className("passenger_row_name")
    private static final By CONFIRMATION_NUMBER = By.id("confirmationNumber")
    private static final By FIRST_NAME = By.id("firstName")
    private static final By LAST_NAME = By.id("lastName")

    util.Navigation navigation
    PnrRetrieval pnrRetrieval
    Flow flow
    ScenarioState scenarioState
    ItineraryData itineraryData
    StopLogicInfo stopLogicInfo
    RRContactInformation rrContactInformation
    AirPriceContent airPriceContent
    SelectFlightsPageData selectFlightsPageData

    def CancelAirReservationPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, '/flight/cancel-air-reservation.html')
    }

    def retrievePNR(String pnr, String pnr_firstName, String pnr_lastName) {
        pnrRetrieval.retrievePnrAndSubmit(pnr, pnr_firstName, pnr_lastName)
    }

    def verifyInvalidPnrOopsMessage() {
        pnrRetrieval.verifyInvalidPnrOopsMessage()
    }

    void clickAirTranLink() {
        pnrRetrieval.clickAirTranLink()
    }

    void verifyTransitionToAirTran() {
        pnrRetrieval.verifyTransitionToAirTran()
    }

    def submitReservationToCancel(String pnr, String pnr_firstName, String pnr_lastName, def doVerify=true) {

        def confNumber = waitForElement(CONFIRMATION_NUMBER)
        confNumber.sendKeys(DELETE_EXISTING + pnr)

        def firstName = waitForElement(FIRST_NAME)
        firstName.sendKeys(DELETE_EXISTING + pnr_firstName)

        def lastName = waitForElement(LAST_NAME)
        lastName.sendKeys(DELETE_EXISTING + pnr_lastName)

        waitForElement(SUBMIT_BUTTON).click()

        if (doVerify)  {
            verifyPage()
        }
    }

    def clickConfirmCancellation() {
        waitForElement(CONFIRM_CANCELLATION_BUTTON_ID).click()
    }

    def clickCancelLinkFromOopsMessageForInternationalRoute() {
        waitForElement(DOMESTIC_CANCELLATION_LINK_ID).click()
    }

    def clickContinueCancellation() {
        waitForElement(CONTINUE_CANCELLATION_ID).click()
        verifyPage()
    }

    def requestForARefund() {
        waitForElement(FUTURE).click()
    }

    String getRelativePath() {
        return "/flight/cancel-air-reservation.html"
    }

    void clickDoNotCancelButton() {
        waitForElement(CANCEL_CANCELLATION_BUTTON).click()
        verifyPage()
    }

    void finishCancelingFlight() {
        waitForElement(CONFIRM_CANCELLATION_BUTTON_ID).click()
        verifyPage()
    }

    def verifyTitleCancelAirReservation() {
        title.shouldContain 'Southwest Airlines - Cancel Air Reservation', "The title of the current page doesn't match with the expected one"
    }

    def verifyAssociatedProductsModalCouldNotRetrieved() {
        waitForElement(HOTEL_MODAL_ERROR).getText().shouldNotBe null
        waitForElement(CAR_MODAL_ERROR).getText().shouldNotBe null
    }

    def verifyConfirmationNumberInPageTitle(String confirmationNumber){
        waitForElement(PAGE_TITLE).getText().shouldContain "Cancel Air Reservation #"+ confirmationNumber,
            "The message Cancel Air Reservation # "+ confirmationNumber + " should be present"
    }

    def verifyPointsText() {
        waitForElement(AWARDS_DETAILS_HEADING).getText().shouldBe POINTS_TEXT, "The text " + POINTS_TEXT + " should be present"
    }

    def verifyPointsNumber(int points) {
        String pointsFormatted = String.format("%,d",points);
        waitForElement(AWARDS_DETAILS_NUMBER).getText().shouldBe pointsFormatted, "The number of points " + pointsFormatted + " should be present"
    }

    def verifyPointsReturningMessage(String name) {
        String current = waitForElement(AWARDS_DETAILS_RETURNING_MESSAGE).getText()
        String expected = "Points will be returned to ${name}'s Rapid Rewards account."
        current.toUpperCase().shouldContain expected.toUpperCase(), "The returning points message should be present"
    }

    def verifyIamOnTheCorrectPage(String url) {
        getCurrentUrl().contains(url).shouldBe true
    }

    def verifyAirtranDisclaimerMessage(){
        waitForElement(AIRTRAN_DISCLAIMER_ID)
    }

    def verifyChangePlaneInfoOnDepartureAirItineraryInCancelReservationPage(){
        String changePlaneCityCode = itineraryData.outboundConnectingStation
        stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER, changePlaneCityCode)
    }

    def verifyAirPNRAssociatedProductModalCouldNotRetrieved() {
        waitForElement(AIR_MODAL_ERROR).getText().shouldNotBe null
    }

    def verifyConfirmationNumberOnThePage(String confirmationNumber) {
        waitForElement(PAGE_TITLE).getText().shouldContain  confirmationNumber,
             confirmationNumber + " should be present"
    }

    def verifyBasicPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify cancel air reservation page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify cancel air reservation page) ---- checking for fault injection"
        }
        System.getProperty("domainToTest") ? getCurrentUrl().shouldContain (System.getProperty("domainToTest")) : getCurrentUrl().shouldContain ("local.swacorp.com")
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.CANCEL_AIR_RESERVATION)
    }

    def verifyLookUpConfirmationLink() {
        isElementPresent(LOOKUP_CONFIRMATION).shouldBe true, "LookUp confirmation link was not present"
    }

    def verifyPassengerName() {
        String firstName
        String lastName
        if (flow.isRapidRewards) {
            firstName = rrContactInformation.firstName.toUpperCase()
            lastName = rrContactInformation.lastName.toUpperCase()
        } else {
            Passenger passenger = scenarioState.getLastAirReservation().getPassengers()[0]
            firstName = passenger.firstName.toUpperCase()
            lastName = passenger.lastName.toUpperCase()
        }
        waitForElement(PASSENGER_NAME).text.shouldBe firstName + " " + lastName, "Passenger name did not match the expected value"
    }
}
