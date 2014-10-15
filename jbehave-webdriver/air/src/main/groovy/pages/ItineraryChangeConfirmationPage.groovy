package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement
import com.github.tanob.groobe.GrooBe
import domain.HotelReservation
import org.openqa.selenium.By
import pages.elements.StopLogicInfo
import pages.mixins.*
import util.HotelItineraryData

import static util.Locators.BREADCRUMB_IDS

@Mixin(ConfirmationVerifications.class)
class ItineraryChangeConfirmationPage extends ConfirmationPage {

    private static final By SAME_PAX_CONFIRMATION_NUMBER = By.cssSelector("span.confirmation_number")
    private static final By HOTEL_ITINERARY_CONTAINER = By.className("hotel_itinerary_container_with_vertical_label")
    private static final By SHOW_SUB_SECTION_LINK = By.id("showSubSectionLink")
    private static final By HOTEL_NAME = By.cssSelector(".itineraries_header_data p")
    private static final By REFUND_REQUESTED = By.className( "refund_amount_column")
    private static final By BUSINESS_SELECT_ICON = By.cssSelector(".business_select_icon")
    private static final By EXISTING_PRODUCTS_SECTION_TITLE = By.className("existing_products_section_title")
    private static final By HOTEL_SUMMARY_CONTAINER = By.id('confirmation_top_summary_hotel')
    private static final By EXISTING_PRODUCTS_TITLE = By.className("confirmation_trip_management_existing_products_title")
    private static final By FORM_OF_PAYMENT = By.className("form_of_payment_fund_left_container")
    private static final By PAYMENT_TABLE = By.cssSelector(".form_of_payment_fund_right_container_row div")
    private static final By AMOUNT_APPLIED = By.className("form_of_payment_amount_column")
    private static final By THANK_YOU_MESSAGE = By.className("success_message")
    private static final By POINTS_RETURNED_DETAILS = By.cssSelector("#billing_payment .tableRowOdd td")
    private static final By POINTS_USED_DETAILS = By.cssSelector("#billing_payment .tableRowEven td")
    private static final By POINTS_RETURNED_FOOTNOTE = By.className("points_returned_footnote")
    private By ITINERARY_HOTEL_CONFIRMATION_NUMBER = By.id("confirmation_itinerary_hotel_confirmation_number")
    private static final By HOTEL_ITINERARY_DATE = By.className("hotel_itinerary_hotel_date")
    private static final By OUTBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId0")
    private static final By INBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId1")


    Itinerary itinerary
    StopLogicInfo stopLogicInfo
    HotelItineraryData hotelItineraryData

    def ItineraryChangeConfirmationPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, "/flight/itinerary-changed.html")
        GrooBe.activate()
    }

    String getRelativePath() {
        return "/flight/itinerary-changed.html"
    }

    void verifyChange() {
        verifyPageBreadcrumb(BREADCRUMB_IDS["ItineraryChangedPage"])
    }

    def verifyPage() {
        super.verifyPage()
        if (!flow.isFaultInjected) {
            verifyPageBreadcrumb(BREADCRUMB_IDS["ItineraryChangedPage"])
            String confirmationText = waitForElement(SAME_PAX_CONFIRMATION_NUMBER).getText()
            confirmationText.shouldNotBe null, "Confirmation number is null when it should not be."
            confirmationText.shouldNotBeEmpty "Confirmation number is empty when it should not be."
            confirmationText.size().shouldBeEqual 6, "Confirmation number is not the proper length of 6 alphanumeric characters."
        }
        waitForElement(THANK_YOU_MESSAGE).text.shouldContain "Thank you", "Thank you message was not present"
    }


    def verifyHotelProductHasAssociatedProduct() {
        isElementDisplayed(EXISTING_PRODUCTS_SECTION_TITLE).shouldBe true, "The existing products title should be present"
        isElementDisplayed(HOTEL_SUMMARY_CONTAINER).shouldBe true, "The associated hotel product should be present"
        isElementDisplayed(EXISTING_PRODUCTS_TITLE).shouldBe true, "The Existing purchases in your trip title should be present"
        WebElement hotelItinerary = waitForElement(HOTEL_ITINERARY_CONTAINER)
        HotelReservation reservation = scenarioState.getLastAirReservation().hotelReservation
        String reservationDates = reservation.getCheckInDateAsString()+" - "+reservation.getCheckOutDateAsString()
        hotelItinerary.findElement(SHOW_SUB_SECTION_LINK).getText().shouldBe "Show Details"
        hotelItinerary.findElement(ITINERARY_HOTEL_CONFIRMATION_NUMBER).getText().shouldBe reservation.getConfirmationNumber()
        hotelItinerary.findElement(HOTEL_NAME).getText().shouldContain reservation.getHotelName()
        hotelItinerary.findElement(HOTEL_ITINERARY_DATE).getText().shouldBe reservationDates
    }

    def verifyAirTranSeatSelectionButtonVisible() {
        (isElementDisplayed(INBOUND_SEAT_SELECTION_BUTTON) || isElementDisplayed(OUTBOUND_SEAT_SELECTION_BUTTON)).shouldBe true, "No seat selection button found"
    }

    def verifyAirTranSeatSelectionButtonIsNotVisible() {
        (isElementDisplayed(INBOUND_SEAT_SELECTION_BUTTON) || isElementDisplayed(OUTBOUND_SEAT_SELECTION_BUTTON)).shouldBe false, "AirTran Seat Selection Button found"
    }

    def verifyRefundAmount( String reconcileRefundAmount ) {
        assertEquals( "The refund amounts were not correct.", stripParentheses( reconcileRefundAmount), waitForElement(REFUND_REQUESTED).getText() )
    }
    
    def verifyBIconDisplayed() {
        isElementDisplayed(BUSINESS_SELECT_ICON)
    }

    private String stripParentheses( String original ) {
        return original.replaceAll(/\(/, '').replaceAll(/\)/, '')
    }

    def verifyPaymentType() {
        String formOfPaymentInPage = waitForElement(FORM_OF_PAYMENT).text
        formOfPaymentInPage.shouldContain "Travel Funds", "Travel funds was not displayesd in the form of payment"
        formOfPaymentInPage.shouldContain scenarioState.getLastAirReservation().getAdultPnr()
        if (!pricePageData.additionalAmountDue == BigDecimal.ZERO){
            verifyCreditCardAmountApplied()
            super.verifyPaymentType()
        }
    }

    def verifyFundsApplied() {
        List<WebElement> paymentTable = waitForElements(PAYMENT_TABLE)
        BigDecimal exchangedTicketOriginalBalance = paymentTable[3].text.replace('\\s', '').replace('$', '').toBigDecimal()
        BigDecimal exchangedTicketApplied = paymentTable[4].text.replace('\\s', '').replace('$', '').toBigDecimal()
        BigDecimal exchangedTicketRemaining = paymentTable[5].text.replace('\\s', '').replace('$', '').toBigDecimal()
        exchangedTicketOriginalBalance.shouldEqual scenarioState.lastAirReservation.price, "Exchanged ticket original balance did not match the one of the Exchanged Ticket Flyout"
        exchangedTicketApplied.shouldEqual purchasePageData.exchangeTicketFundsApplied, "Exchanged ticket applied did not match the one of the Exchanged Ticket Flyout"
        exchangedTicketRemaining.shouldEqual purchasePageData.exchangeTicketFundsRemaining, "Exchanged ticket remaining did not match the one of the Exchanged Ticket Flyout"
        List<WebElement> amountsApplied = waitForElements(AMOUNT_APPLIED)
        BigDecimal exchangeTicketTotalApplied
        if(flow.isRapidRewardsPointsPurchaseOnly) {
            BigDecimal totalAmountApplied = amountsApplied[2].text.replace('\\s','').replace('$','').replace(',','').toBigDecimal()
            totalAmountApplied.shouldEqual purchasePageData.totalTravelFundsApplied, "Total amount applied did not match funds applied in the itinerary change page"
        } else {
            if(pricePageData.additionalAmountDue == BigDecimal.ZERO) {
                exchangeTicketTotalApplied = amountsApplied[0].text.replace('\\s', '').replace('$', '').toBigDecimal()
                exchangeTicketTotalApplied.shouldEqual purchasePageData.totalTravelFundsApplied, "Exchanged ticket Amount applied did not match funds applied in reconcile page"
            } else {
                BigDecimal creditCardAmount = amountsApplied[0].text.replace('\\s', '').replace('$', '').toBigDecimal()
                creditCardAmount.shouldEqual pricePageData.additionalAmountDue, "Credit card amount did not match additional credit due in reconcile page"
                exchangeTicketTotalApplied = amountsApplied[1].text.replace('\\s','').replace('$','').toBigDecimal()
                exchangeTicketTotalApplied.shouldEqual purchasePageData.exchangeTicketFundsApplied, "Exchanged ticket Amount applied did not match funds applied in reconcile page"
            }
        }
        if (itineraryData.hasTravelFunds) {
            BigDecimal travelFundsOriginalBalance = paymentTable[9].text.replace('\\s', '').replace('$', '').toBigDecimal()
            BigDecimal travelFundsApplied = paymentTable[10].text.replace('\\s', '').replace('$', '').toBigDecimal()
            BigDecimal travelFundsRemaining = paymentTable[11].text.replace('\\s', '').replace('$', '').toBigDecimal()
            BigDecimal travelFundsTotalAmount = amountsApplied[2].text.replace('\\s', '').replace('$', '').toBigDecimal()
            travelFundsOriginalBalance.shouldEqual scenarioState.firstAirReservation.price, "travel funds original balance did not match the flight price"
            travelFundsApplied.shouldEqual purchasePageData.travelFundsApplied, "Travel funds applied did not match the one of the reconcile page"
            travelFundsRemaining.shouldEqual purchasePageData.travelFundsRemaining, "Travel funds remaining did not match the one of the reconcile page"
            travelFundsTotalAmount.shouldEqual purchasePageData.travelFundsApplied, "Travel funds Amount applied did not match funds applied in reconcile page"
        }
    }

    def verifyUsedAndReturnedPoints() {
        List<WebElement> paymentTable = waitForElements(AMOUNT_APPLIED)
        BigDecimal originalBalance = paymentTable[0].text.replace('pts','').replace(',','').replace('(','').replace(')','').replace('*','').toBigDecimal()
        BigDecimal travelFundsApplied = paymentTable[1].text.replace('pts','').replace(',','').toBigDecimal()
        originalBalance.shouldEqual selectFlightsPageData.previousOutboundFlightPoints, "The original balance did not match the full balance in the intinerary change page"
        travelFundsApplied.shouldEqual selectFlightsPageData.outboundFlightPoints, "Travel funds applied did not match the one of the reconcile page in the intinerary change page"
    }
    
    def verifyRRAccountNumberOnFormOfPayment() {
        waitForElements(POINTS_RETURNED_DETAILS)[0].text.shouldContain rrContactInformation.accountNumber, "RR account number was not correct in the points returned row of the Form of Payment table on the itinerary changed page"
        waitForElements(POINTS_USED_DETAILS)[0].text.shouldContain rrContactInformation.accountNumber, "RR account number was not correct in the points used row of the Form of Payment table on the itinerary changed page"
    }
    
    def verifyPurchaserName() {
        if(pricePageData.additionalAmountDue == BigDecimal.ZERO && flow.isRapidRewardsPointsPurchaseOnly) {
            String billingFullName = waitForElement(BILLING_FULL_NAME).text
            billingFullName.shouldBe rrContactInformation.firstName + " " + rrContactInformation.lastName, "Billing fullname did not match the one entered on itinerary change page"
        } else {
            super.verifyPurchaserName()
        }
    }

    def verifyFootNoteTextIsPresent() {
        waitForElement(POINTS_RETURNED_FOOTNOTE).text.shouldBe "* Points have been returned to the member's account.", "Points returned footnote was not correct in the itinerary change page"
    }
}
