package pages

import com.swacorp.dotcom.webscenarios.air.BillingData
import domain.Passenger
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.AirPriceContent
import pages.mixins.*
import state.Flow
import state.ScenarioState
import util.*
import state.PassengerData

@Mixin(PricingTableVerifications.class)
class CancelAirReservationRetrievedPage extends BasePage {

    private static final By CONFIRM_CANCELLATION_BUTTON_ID = By.id("confirmCancellationButton")
    private static final By PAGE_TITLE = By.id("page_title")
    private static final By FLIGHT_ITINERARY = By.id("flightItinerary")
    private static final By FLIGHT_DATE = By.id("flightDates")
    private static final By PASSENGER_NAME = By.className("passenger_row_name")
    private static final By CONFIRMATION_NUMBER = By.className("confirmation_number")
    private static final By RR_NUMBER = By.className("passenger_row_rr_number")
    private static final By BILLING_INFORMATION = By.cssSelector("#billing_card_holder_tbody td")
    private static final By TRAVEL_FUNDS_INFORMATION = By.cssSelector(".cancelTravelFunds .heading")
    private static final By HOLD_FOR_FUTURE_OPTION = By.id("hold")
    private static final By REQUEST_A_FUND_OPTION = By.id("future")
    private static final By POINTS_REFUND_DETAIL = By.cssSelector("#awardDetails label")
    private static final By CANCEL_BUTTON_ID = By.cssSelector("input.largeSubmitButtonWhite")
    private static final By POINT_REFUND_AMOUNT = By.cssSelector("#awardDetails .cancelTravelFundsCenter")

    Flow flow
    ScenarioState scenarioState
    ItineraryData itineraryData
    RRContactInformation rrContactInformation
    BillingData billingData
    PurchasePageData purchasePageData
    AirPriceContent airPriceContent
    SelectFlightsPageData selectFlightsPageData
    PassengerData passengerData

    def CancelAirReservationRetrievedPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, '/flight/cancel-reservation.html')
    }

    void finishCancelingFlight() {
        waitForElement(CONFIRM_CANCELLATION_BUTTON_ID).click()
        verifyPage()
    }

    def verifyConfirmationNumberInPageTitle(String confirmationNumber){
        waitForElement(PAGE_TITLE).getText().shouldContain "Cancel Air Reservation #"+ confirmationNumber,
            "The message Cancel Air Reservation # "+ confirmationNumber + " should be present"
    }

    def verifyBasicPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify cancel reservation retrieved page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify cancel reservation retrieved page) ---- checking for fault injection"
        }
        System.getProperty("domainToTest") ? getCurrentUrl().shouldContain (System.getProperty("domainToTest")) : getCurrentUrl().shouldContain ("local.swacorp.com")
        pageVerificationErrorWrapper(CONFIRM_CANCELLATION_BUTTON_ID, PageName.CANCEL_RESERVATION_RETRIEVED)
    }

    def verifyFlightItinerary() {
        String flightItinerary = waitForElement(FLIGHT_ITINERARY).text
        flightItinerary.shouldContain itineraryData.departureStation, "Departure station did not match departure station from the PNR"
        flightItinerary.shouldContain itineraryData.arrivalStation, "Arrival station did not match arrival station from the the PNR"
    }

    def verifyFlightDate() {
        if (itineraryData.isOneWay()) {
            waitForElement(FLIGHT_DATE).text.shouldBe itineraryData.departureDate.format("MM/dd/yyyy"), "Flight date did not match the expected value"
        }
    }

    def verifyPassengerName() {
        String firstName
        String lastName
        if (flow.isRapidRewards) {
            firstName = rrContactInformation.firstName.split(" ")[0].toUpperCase()
            lastName = rrContactInformation.lastName.toUpperCase()
        } else {
            Passenger passenger = scenarioState.getLastAirReservation().getPassengers()[0]
            firstName = passenger.firstName.split(" ")[0].toUpperCase()
            lastName = passenger.lastName.toUpperCase()
        }
        waitForElement(PASSENGER_NAME).text.shouldBe firstName + " " + lastName, "Passenger name did not match the expected value"
    }

    def verifyConfirmationNumberOnItineraryTable(String confirmationNumber) {
        waitForElement(CONFIRMATION_NUMBER).text.shouldBe confirmationNumber, "Confirmation number on itinerary table did not match the expected value"
    }

    def verifyRRNumber() {
        waitForElement(RR_NUMBER).text.shouldBe rrContactInformation.accountNumber, "RR Number did not match the expected value"
    }

    def verifyBillingDetails() {
        List<WebElement> billingDetails = waitForElements(BILLING_INFORMATION)
        billingDetails[0].text.shouldBe billingData.firstName.toUpperCase() + " " + billingData.lastName.toUpperCase(), "Purchaser's name did not match the expected value"
        billingDetails[1].text.split("\n")[0].shouldBe billingData.address.toUpperCase(), "Billing address did not match the expected value"
    }

    def verifyTravelFunds() {
        List<WebElement> travelFundsTitles = waitForElements(TRAVEL_FUNDS_INFORMATION)
        travelFundsTitles[0].text.shouldBe "Refundable", "Refundable text was not present in the Travel Funds section"
        travelFundsTitles[1].text.shouldBe "Nonrefundable", "Nonrefundable text was not present in the Travel Funds section"
        List<WebElement> travelFundsAmount = waitForElements(By.className("cancelTravelFundsCenter"))
        travelFundsAmount[0].text.replace('$','').toBigDecimal().shouldBe purchasePageData.tripTotal, "Refundable amount did not match the expected value"
        travelFundsAmount[1].text.replace('$','').toBigDecimal().shouldBe new BigDecimal("0.00"), "Nonrefundable amount did not match the expected value"

        waitForElement(HOLD_FOR_FUTURE_OPTION).getAttribute("checked").shouldBe "true", "Hold for future use option was not selected by default"
        waitForElement(REQUEST_A_FUND_OPTION).getAttribute("checked").shouldBe null, "Request a refund of the refundable balance option was not unselected by default"

        WebElement nonrefundableTravelFundsRow = waitForElements(By.cssSelector(".cancelTravelFunds tr"))[2]
        WebElement nonrefundableTravelFundsOptions = nonrefundableTravelFundsRow.findElements(By.cssSelector("td"))[2]
        nonrefundableTravelFundsOptions.text.shouldBe "N/A", "Nonrefundable N/A option was not displayed"
    }

    def verifyPointsRefundDetail() {
        waitForElement(POINTS_REFUND_DETAIL).text.shouldContain "Points will be returned to ${rrContactInformation.firstName.toUpperCase()} ${rrContactInformation.lastName.toUpperCase()}'s Rapid Rewards account.", "Travel funds's points refund text was not correct in the Cancel Reservation Retrieved Page"
        BigDecimal pointsOnPage = waitForElement(POINT_REFUND_AMOUNT).text.replace(',','').toBigDecimal()
        pointsOnPage.shouldEqual selectFlightsPageData.outboundFlightPoints, "The points being returned in cancel reservation page were not the same as the previous booking"
    }

    def verifyCancelButtonIsPresent() {
        isElementPresent(CONFIRM_CANCELLATION_BUTTON_ID).shouldBe true, "Confirmation button was not present in the Cancel Reservation Retrieved Page"
    }

    def verifyDoNotCancelButtonIsPresent() {
        isElementPresent(CANCEL_BUTTON_ID).shouldBe true, "Cancelation button was not present in the Cancel Reservation Retrieved Page"
    }
}
