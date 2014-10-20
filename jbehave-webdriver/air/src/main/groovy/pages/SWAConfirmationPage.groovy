package pages

import com.github.tanob.groobe.GrooBe
import com.swacorp.dotcom.webscenarios.air.Data
import domain.AirReservation
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.AirPriceContent
import pages.elements.FareBreakdownFlyOut
import pages.elements.FeeCalculator
import pages.elements.GuardianChargeTable
import pages.elements.RapidRewardsAccountBar
import pages.elements.StopLogicInfo
import pages.mixins.*
import util.CommonConstants
import util.CustomerInfoData
import util.DollarToBigDecimalConverter
import util.ItineraryData
import util.PageName
import util.PricePageData
import util.RRContactInformation
import util.SelectFlightsPageData
import util.PurchasePageData

import static org.junit.Assert.fail
import state.*
import util.HotelItineraryData

import org.joda.time.DateTime

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import com.google.common.collect.Sets
import com.swacorp.dotcom.webscenarios.air.BillingData

import static util.Locators.BREADCRUMB_IDS
import pages.elements.PriceTable

@Mixin([PricingTableVerifications.class, ConfirmationVerifications.class])
class SWAConfirmationPage extends BasePage {

    public final static PAGE_TITLE = "Southwest Airlines - Purchase Confirmation";
    private static final String AIR_ITINERARY_CONTAINER_CLASS = ".air_itinerary_container_with_image"
    private static final String CONFIRMATION_TRIP_MANAGEMENT_TITLE_SUMMARY_DESCRIPTION = "Confirmation Trip Management Title Sumary"
    private static final By CONFIRMATION_TRIP_MANAGEMENT_TITLE_SUMARY = By.id("confirmation_trip_management_title_summary")
    private static final String NEW_PRODUCT_ADDED_EXISTING_TRIP_MESSAGE = "'New Product Added To Existing Trip' message should not be on the page"
    private static final By CONFIRMATION_TOP_SUMARY_VIEW_DETAILS = By.id("confirmation_top_summary_view_details")
    private static final String CONFIRMATION_TOP_SUMMARY_VIEW_DETAILS_MESSAGE = "Confirmation Top Sumary View Details"
    private static final By TOTAL_POINTS = By.className("totalPoints")
    private static final By TRIP_GRAND_TOTAL = By.id("trip_grand_total_top")
    private static final By AIR_SUBTOTAL = By.id("subtotalForAir")
    private static final By RESEND_LINK = By.className("swa_printAndShare_resendReceiptLink")
    private static final By RR_LINK = By.className("rapid-reward-link")
    private static final By SUBSCRIBE_TO_FLIGHT_LINK = By.xpath("//div[@class='itinerary_container air_itinerary_container_with_vertical_label']/div[3]/a")
    private static final By RAPID_REWARDS_NUMBER = By.className("passenger_row_rr_number")
    private static final By AMOUNT_APPLIED = By.className("form_of_payment_amount_column")
    private static final By BILLING_ADDRESS = By.className("billing_info_address1")
    private static final By BILLING_CITY = By.className("billing_info_city")
    private static final By BILLING_STATE = By.className("billing_info_state")
    private static final By BILLING_COUNTRY = By.className("billing_info_country")
    private static final By BILLING_ZIP_CODE = By.className("billing_info_zip")
    public static final By BILLING_FULL_NAME = By.className("billing_info_full_name")
    private static final By AMOUNT_PAID = By.id("total_paid_now_top")
    private static final String BUSINESS_SELECT = "BusinessSelect"
    private static final By UM_VERBIAGE = By.className("wait_snake_text")
    protected By ITINERARY_HOTEL_CONFIRMATION_NUMBER = By.id("confirmation_itinerary_hotel_confirmation_number")
    private final String ITINERARY_PRODUCT = ".trip_itinerary_products"
    protected static final By EXISTING_PRODUCTS_TITLE = By.className("confirmation_trip_management_existing_products_title")
    public final static CONFIRMATION_TOP_SUMMARY_CAR = By.id('confirmation_top_summary_car')
    private static final String SEAT_SELECTION_TEXT = "Seat selection for AirTran flights only"
    private static final String SEAT_SELECTION_UNAVAILABLE_TEXT = "Seat selection unavailable. Please try again later"
    private static final String UM_PURCHASED_TEXT_CLASS = 'wait_snake_text'
    private static final String ASSISTANCE_REQUESTED_TEXT = 'Assistance Requested. Please Inform Southwest Airlines Representatives upon arrival at the airport'
    private static final String VIEW_PARENT_GUARDIAN_INFO_LINK_ID = 'viewParentGuardianInfo'
    private static final By EARLY_BIRD_CHECK_IN_BUTTON = By.className("earlybirdCheckIn_button")
    private static final By CONFIRMATION_ADDON_INFO_CONTAINER_ERROR = By.className("confirmation_addon_info_container_earlybirdCheckIn_button")
    private static final By CONFIRMATION_TRIP_MANAGEMENT_TITLE_DETAILS = By.className("confirmation_trip_management_title_details")
    private static final By ROW_WITHOUT_DIVISOR = By.className("row_without_divisor")
    private static final By UM_ALERT_MESSAGE_CLASS = By.className("um_alert_message")
    private static final By TOTAL_FARE_BREAK_DOWN_LINK = By.className("totalPriceDetailsText_ADT")
    private static final By SIMPLE_MODAL_CONTAINER_CLASS = By.cssSelector(".simplemodal-container")
    private static final By SPAN_CONFIRMATION_NUMBER = By.cssSelector("span.confirmation_number")
    private static final By CONFIRMATION_TOP_SUMMARY_AIR = By.id("confirmation_top_summary_air")
    private static final By INTERNAL_REFERENCE_NUMBER = By.xpath("//thead[@id='billing_card_holder_tbody']//th[text()='Internal Reference #']")
    private static final By CONFIRMATION_SEARCH_ALTERNATE_SEARCH_RESULTS = By.xpath('//div[@id = "confirmation_search_alternate_hotel"]//div[@class="confirmation_search_alternate_search_result_info"]')
    private static final By OUTBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId0")
    private static final By OUTBOUND_SEAT_SELECTION_TEXT = By.id("seatSelectionTextId0")
    private static final By OUTBOUND_SEAT_SELECTION_UNAVAILABLE_TEXT = By.id("seatSelectionUnableToRetrieveMessageItineraryTable0")
    private static final By INBOUND_SEAT_SELECTION_TEXT = By.id("seatSelectionTextId1")
    private static final By INBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId1")
    private static final By INBOUND_SEAT_SELECTION_UNAVAILABLE_TEXT = By.id("seatSelectionUnableToRetrieveMessageItineraryTable1")
    private static final By CAR_CANCEL_LINK = By.id("carCancelLink")
    protected static final By HOTEL_SUMMARY_CONTAINER = By.id('confirmation_top_summary_hotel')
    private static final By EARLY_BIRD_RESULTS_TABLE = By.id("earlyBirdResultsTable")
    private static final By CANCEL_RESERVATION_LINK = By.id("cancelReservationLink")
    private static final By CHANGE_RESERVATION_LINK = By.id("changeReservationLink")
    private final static By CONFIRMATION_TRIP_NAME = By.id("confirmation_trip_name")
    private static final By VIEW_BILLING_DETAILS_LINK = By.cssSelector(".confirmation_summary_paid_title a")
    private static final By CONFIRMATION_TOP_SUMMARY_FOR_CAR = By.id("confirmation_top_summary_car")
    private static final By HOTEL_CROSS_SELL = By.id("confirmation_search_alternate_hotel")
    private static final By CAR_CROSS_SELL = By.id("confirmation_search_alternate_car")
    private static final By CAR_CROSS_SELL_VENDORS = By.className("carvendor")
    private static final By UM_CHARGE_PRICING_TABLE = By.id('unaccompaniedMinorResultsTable')
    private static final By TRIP_TOTAL = By.id("trip_grand_total_bottom")
    private static final By TOTAL_PAID = By.id("total_paid_now_bottom")
    private static final By PRICE_ITINERARY = By.className("subTotal")
    private static final By TOTAL_PRICING_BREAKDOWN_FLY_OUT = By.id("totalPriceDetails_footer_ADT")
    private static final By CHECKIN_FOR_FLIGHT_BUTTON = By.id('checkInForFlightButton')

    private static final String UM_NOT_ELIGIBLE_FOR_CHECKIN_ONLINE_OOPS = "Sorry, your itinerary is ineligible for checkin online. Please proceed to any Southwest Airlines Ticket Counter to check in for your flight"

    public static final By PRICE_ROW_RETURN = By.xpath("//table[@id='pricing_ADT']//tr[@class='tableRowEven return priceRow']")
    public static final By PRICE_ROW_DEPART = By.xpath("//table[@id='pricing_ADT']//tr[@class='tableRowOdd depart priceRow']")
    public static final By FLIGHT_ITINERARY = By.id("flightItinerary")
    public static final By FLIGHT_DATES = By.id("flightDates")
    public static final By FLIGHT_NUMBERS = By.className("flightNumberCell")
    public static final String DATE_FORMAT = "MM/dd/yyyy"
    public static final By FLIGHT_NUMBERS_CR1 = By.className("flightNumber")
    public static final By CONFIRMATION_PAGE_TEXT = By.className("success_message")
    private static final By EXISTING_PRODUCTS_SECTION_TITLE = By.className("existing_products_section_title")
    private static final By UPSELL_ITINERARY_DETAILS_TITLE = By.cssSelector(".confirmation_alternate_upsell_itinerary_details div")
    private static final String CAR_CROSS_SELL_SECTION_TITLE = "Great reasons to book your car on southwest.com"
    private static final By AIR_ITINERARY_CONTAINER_VL = By.className("air_itinerary_container_with_vertical_label")
    private static final By CAR_ITINERARY_CONTAINER_VL = By.className("car_itinerary_container_with_vertical_label")
    private static final By ITINERARY_PRODUCT_BY = By.className("trip_itinerary_products")
    private static final By FARE_DISCOUNT = By.id("fareDiscount")
    private static final By OUT_OF_MEMORY_ERROR = By.xpath("//div[@id='errorStackTrace']/pre[contains(text(), 'OutOfMemory')]")
    private static final By OOPS_SWA_CONTENT = By.className("oopsError_list")
    private static final By OOPS_FARE_CHANGE = By.id("error_wrapper")
    private static final By SWARUNTIME_EXCEPTION = By.xpath("//div[@id='errorStackTrace']/pre[contains(text(), 'SWARuntimeException')]")
    private static final By SOCKET_TIMEOUT_EXCEPTION = By.xpath("//div[@id='errorStackTrace']/pre[contains(text(), 'SocketTimeoutException')]")
    private static final By OUTBOUND_FARETYPE = By.xpath("//table[@id='airItinerarydepart']//div[@class='fareProductName']//a[@class='headerText']")
    private static final By INBOUND_FARETYPE = By.xpath("//table[@id='airItineraryreturn']//div[@class='fareProductName']//a[@class='headerText']")
    private static final By AIR_ITINERARY_DEPART_ID = By.id("airItinerarydepart")
    private static final String SEGMENT_TIME_CLASS = 'segmentTime'
    private static final String SEGMENT_TIME_AMPM_CLASS = 'segmentTimeAMPM'
    private static final String DEPARTURE_LONG_DATE_CLASS = 'departureLongDate'
    private static final By POINT_CALC_LINK = By.id("pointsCalcLink")
    private static final By ADD_RAPID_REWARDS_NUMBER_LINK = By.cssSelector(".passenger_row_rr_number a")
    private static final By VIEW_AIR_DETAILS = By.id("airSubSectionLink")
    private static final By AIR_WIDGET_AIR_TOTAL = By.className("confirmation_total_amount")
    private static final By RR_SIGN_UP_MODULE = By.className("rr-module-outer-container")
    private static final By FORM_OF_PAYMENT = By.cssSelector("#billing_payment tbody tr")
    private static final By AIR_WIDGET_STATION = By.cssSelector("#confirmation_top_summary_air div.confirmation_alternate_upsell_itinerary_highlight")
    private static final By HEADER_STATION = By.className("confirmation_top_summary_ond_wrapper")
    private static final By AIR_CONFIRMATION_SUMMARY = By.id("airConfirmationSummary")
    private static final By TOTAL_AMOUNT_CHARGED = By.id("totalAmountCharged")
    private static final By FORM_OF_PAYMENT_TRAVEL_FUND = By.className("form_of_payment_fund_left_container")
    private static final By ORIGINAL_BALANCE = By.className("form_of_payment_fund_right_container_row_header_orig_balance")
    private static final By APPLIED_AMOUNT =  By.cssSelector(".form_of_payment_fund_right_container_row div")
    private static final By UM_PASSENGER_DETAILS = By.cssSelector("#unaccompaniedMinorResultsTable .tableRowOdd td")
    private static final By ADD_A_CAR_VIEW_DETAILS_FIRST = By.xpath("//*[@id='command']/input[@type='submit']")
    private static final By PRINT_PAGE_LINK = By.id("printPageLink")
    private static final By ADD_TO_CALENDAR_LINK = By.id("saveToMyCalendar")
    private static final By FLIGHT_STATUS_LINK = By.cssSelector(".flightStatusLink")
    private static final By HIDE_DETAILS_LINK = By.id("hideSubSectionLink")
    private static final By SHOW_DETAILS_LINK = By.id("showSubSectionLink")
    private static final By CALENDAR_ICON_DEPART = By.cssSelector("#airItinerarydepart .calendarDayOfWeek.depart")
    private static final By CALENDAR_ICON_RETURN = By.cssSelector("#airItineraryreturn .calendarDayOfWeek.return")
    private static final int OUTBOUND_APPLIED_INDEX = 0
    private static final int INBOUND_APPLIED_INDEX = 1
    private static final int ROUND_TRIP_AMOUNT_APPLIED_INDEX = 2
    private static final int ONE_WAY_AMOUNT_APPLIED_INDEX = 1

    Itinerary itinerary
    ItineraryData itineraryData
    Data data
    Flow flow
    ScenarioState scenarioState
    CurrentHotelPNR currentHotelPNR
    PassengerData passengerData
    CarReservationData carItineraryData
    HotelItineraryData hotelItineraryData
    StopLogicInfo stopLogicInfo
    PricePageData pricePageData
    AirPriceContent airPriceContent
    SelectFlightsPageData selectFlightsPageData
    CustomerInfoData customerInfoData
    BillingData billingData
    PurchasePageData purchasePageData
    RRContactInformation rrContactInformation
    PriceTable priceTable
    FeeCalculator feeCalculator
    RapidRewardsAccountBar rapidRewardsAccountBar

    void outOfMemoryOops() {
        if(!System.getProperty("DYNA_STUBS")){
            waitForElement(OUT_OF_MEMORY_ERROR)
        }else {
            waitForElement(OOPS_SWA_CONTENT).text.shouldContain "An error has occurred.", "Oops message not found on out of memory fault injection"
        }
    }

    def fareChangeOopsMessage() {
        waitForElement(OOPS_FARE_CHANGE).text.shouldContain "This fare has expired", "Oops message not found on fare change fault injection"
    }

    void swaRuntimeExceptionOops() {
        waitForElement(SWARUNTIME_EXCEPTION)
    }

    void refresh() {
        navigate().refresh()
        sleep(100)
        switchTo().alert().accept()
    }

    void swaSocketTimeoutExceptionOops() {
        waitForElement(SOCKET_TIMEOUT_EXCEPTION)
    }

    public void verifyStations(By elm) {
        WebElement we = waitForElement(elm);
        String textInPage = we.getText()
        textInPage.shouldContain itineraryData.departureStation, "departure station ${itineraryData.departureStation} was not in the confirmation page summary";
        if (itineraryData.isOpenJaw()) {
            textInPage.shouldContain itineraryData.returnStation, "Return station ${itineraryData.returnStation} was not displayed"
        } else {
            textInPage.shouldContain itineraryData.arrivalStation, "arrival ${itineraryData.arrivalStation} was not in the confirmation summary"
        }
    }

    public void verifyHotelUpsellExists() {
        waitForElement(By.id("confirmation_hotel_search"));
    }

    public void verifyCarUpsellExists() {
        waitForElement(By.id('confirmation_car_search'));
    }

    def verifyPaymentType() {
        List <WebElement> paymentMethods = waitForElements(FORM_OF_PAYMENT)
        String paymentType
        if(flow.useSavedCreditCard) {
            paymentType = rrContactInformation.creditCardType.toLowerCase()
        } else {
            paymentType = customerInfoData.formOfPayment.trim().split(' ')[0].toLowerCase()
        }
        WebElement formOfPayment
        if(flow.isRapidRewardsPointsPurchaseOnly) {
            paymentMethods[0].text.trim().split(' ')[0].toLowerCase().shouldBe "points", "Form of payment is not correct"
            paymentMethods[0].text.shouldContain rrContactInformation.accountNumber, "The RR number in form of payment was incorrect"
            formOfPayment = paymentMethods[1]
        } else {
            formOfPayment = paymentMethods[0]
        }
        formOfPayment.text.trim().split(' ')[0].toLowerCase().shouldBe paymentType, "Form of payment is not correct"
        if (!customerInfoData.formOfPayment?.equalsIgnoreCase("paypal")) {
            String lastNumbers
            if(customerInfoData.formOfPayment.equals("southwestgiftcard")) {
                lastNumbers = data.getGiftCard("500Thousand").cardNumber.subSequence(12, 16)
            } else if(!flow.useSavedCreditCard) {
                String userCC = flow.userCreditCard
                lastNumbers = userCC.subSequence(userCC.size()-4,userCC.size())
            } else {
                lastNumbers = rrContactInformation.creditCardNumber.split(' ')[3]
            }
            formOfPayment.text.shouldContain lastNumbers, "Credit card last 4-digits numbers must match the one used in purchase page"
        }
        if (itineraryData.luvVoucher) {
            if (paymentMethods != null  && paymentMethods.size() > 1){
                paymentMethods[1].text.shouldContain "Southwest LUV Voucher", "Luv Voucher was not present in payment method on the confirmation page"
            } else
                fail("luv voucher was not used as a payment method or luv voucher did not have any funds")
        }
    }

    def verifyAmountAppliedToCCEqualsAirTotal() {
        waitForElement(AIR_SUBTOTAL).text.replace('$','').toBigDecimal().shouldEqual getCreditCardAmount(), "Credit card amount applied did not match air total"
    }

    def verifySubscribeToFlightMessage() {
        waitForElement(SUBSCRIBE_TO_FLIGHT_LINK, false).text.shouldBe "Subscribe to Flight Status Messaging", "The link to subscribe to flight was not present"
    }


    def verifyAmoutAppliedAmountPaidTripTotalAreEqual() {
        waitForElement(TRIP_TOTAL).text.replace('$','').toBigDecimal().shouldEqual getCreditCardAmount(), "Credit card amount applied did not match Trip total"
        waitForElement(TRIP_TOTAL).text.replace('$','').toBigDecimal().shouldEqual waitForElement(TOTAL_PAID).text.replace('$','').toBigDecimal(), "Total paid amount did not match Trip total"
    }

    def verifyRRNumber() {
        waitForElement(RAPID_REWARDS_NUMBER).text.toBigDecimal().shouldEqual rrContactInformation.accountNumber.toBigDecimal() , "The RR number was incorrect"
    }

    def verifyStationsOnHeader() {
        verifyStations(HEADER_STATION)
    }

    def clickOnAddRRNumberLink() {
        waitForElement(RR_LINK).click()
    }

    def verifyBalanceInformation() {
        List<BigDecimal> balanceList = retireveFundsAppliedBalance()
        balanceList[0].shouldBe purchasePageData.fullBalanceAmount, "Original balance was not equal to the full balance amount in the purchase page"
        balanceList[1].shouldBe purchasePageData.totalTravelFundsApplied, "Applied was not equal to Travel Funds Applied Today in SouthwestGiftCardFlyout"
        balanceList[2].shouldBe purchasePageData.exchangeTicketFundsRemaining, "Remaning was not equal to Travel Funds Remaining in SouthwestGiftCardFlyout"
    }

    def verifyAmountApplied() {
        BigDecimal amountAppliedInPage
        BigDecimal expectedAmountApplied
        if(flow.isRapidRewardsPointsPurchaseOnly) {
            amountAppliedInPage = getPointsApplied()
            if(itineraryData.isOneWay())
                expectedAmountApplied = selectFlightsPageData.outboundFlightPoints
            else
                expectedAmountApplied = selectFlightsPageData.totalOutboundInboundFlightPoints
        } else if(itineraryData.isPromoCertBooking()){
            List<WebElement> amountsApplied = waitForElements(AMOUNT_APPLIED)
            if(itineraryData.isRoundTripOrOpenJaw()){
                amountsApplied[OUTBOUND_APPLIED_INDEX].text.shouldBe "N/A", "Amount applied to awards should have been N/A"
                amountsApplied[INBOUND_APPLIED_INDEX].text.shouldBe "N/A", "Amount applied to awards should have been N/A"
                amountAppliedInPage = amountsApplied[ROUND_TRIP_AMOUNT_APPLIED_INDEX].text.replace('$', '').toBigDecimal()
            } else {
                amountsApplied[OUTBOUND_APPLIED_INDEX].text.shouldBe "N/A", "Amount applied to awards should have been N/A"
                amountAppliedInPage = amountsApplied[ONE_WAY_AMOUNT_APPLIED_INDEX].text.replace('$', '').toBigDecimal()
            }
            expectedAmountApplied = purchasePageData.tripTotal
        } else {
            amountAppliedInPage  = waitForElement(AMOUNT_APPLIED).text.replace('$', '').toBigDecimal()
            expectedAmountApplied = purchasePageData.totalTravelFundsApplied
        }
        amountAppliedInPage.shouldBe expectedAmountApplied, "Amount applied was not equal to Funds Applied in Applied travel funds section of southwest gift card"
    }

    def verifyUMPurchaseVerbiage() {
        waitForElement(UM_VERBIAGE).text.shouldBe "Unaccompanied Minor Purchased", "'Unaccompanied Minor Purchased' was not displayed in air widget in confirmation page"
    }

    enum Name {
        FIRST, LAST
    }

    String getRelativePath() {
        return "/reservations/confirm-reservations.html"
    }

    void verifyEarlyBirdPurchased() {
        if (!isEarlyBirdCheckMarkDisplayed()) {
            fail getUnableToPurchaseEarlyBirdText()
        }
    }

    def verifyConfirmationTripName(String tripName) {
        waitForElement(CONFIRMATION_TRIP_NAME).text.shouldBeEqual tripName, "The trip name in the Confirmation Page should be: $tripName"
    }

    private String getUnableToPurchaseEarlyBirdText() {
        return waitForElement(CONFIRMATION_ADDON_INFO_CONTAINER_ERROR).getText()
    }

    private boolean isEarlyBirdCheckMarkDisplayed() {
        return isElementPresentOnEarlyBirdPurchase(By.xpath("//div[@class='confirmation_summary_subsection']/img[@src='/assets/images/checkmark-green-16px.gif']"))
    }

    private boolean isMsgExistingPurchasesInYourTripDisplayed() {
        return isElementDisplayed(EXISTING_PRODUCTS_TITLE)
    }

    def SWAConfirmationPage(WebDriverProvider webDriverProvider, String relativePath = '/flight/itinerary-changed.html') {
        super(webDriverProvider)
        GrooBe.activate()
    }

    def verifyTwoAssociatedAirProductsModalIsDisplayed() {

        WebElement modalContainer = waitForElement(SIMPLE_MODAL_CONTAINER_CLASS)

        if(modalContainer?.isDisplayed()){
            List<WebElement> products = modalContainer.findElements(By.cssSelector(AIR_ITINERARY_CONTAINER_CLASS))

            if(products.size() != 2){
                fail('The cancellation modal should have two items')
            }
        }
    }

    def verifyBasicPage() {
        super.verifyPage()
        // verifying that we landed on the confirmation page, and if we do not ensuring we get an appropriate error message.
        if (!flow.isFaultInjected) {
            String confirmationPageText = waitForElement(CONFIRMATION_PAGE_TEXT).text
            while(confirmationPageText.contains("Just One Moment Please") || confirmationPageText == ""){
                confirmationPageText = waitForElement(CONFIRMATION_PAGE_TEXT).text
            }
            waitForElement(CONFIRMATION_PAGE_TEXT).text.shouldContain "Thank you for your purchase!", "Confirmation Page did not Load"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["ConfirmationPage"])
        pageVerificationErrorWrapper(CONFIRMATION_PAGE_TEXT, PageName.CONFIRMATION_PAGE)
    }

    def verifyCheckinButtonIsAvailable() {
        //verifyElementPresent("Check-in Button", By.id("checkInForFlightButton"))
        //Ravendra - Strory# -date:10/18/14 - //
        // changed elementname to Check-in
        verifyElementPresent("Check-in", By.id("checkInForFlightButton"))
    }

    def verifyCheckinButtonIsUnavailable() {
        verifyPage()
        verifyElementNotPresent("Check-in Button", By.id("checkInForFlightButton"))
    }

    def getPnr() {
        return waitForElement(By.className("recordLocator")).getText()
    }

    def verifyEarlyBirdUpsellButtonNotPresent() {
        waitForElement(ROW_WITHOUT_DIVISOR).getText().contains("Add EarlyBird Check-In").shouldBe false, "Found EarlyBird Check-In when not expected."
    }

    void clickEarlyBirdUpsellButton() {
        waitForElement(EARLY_BIRD_CHECK_IN_BUTTON).click()
    }

    def verifyEarlyBirdSectionPresent() {
        verifyElementPresent("Early Bird Section", waitForElement(EARLY_BIRD_RESULTS_TABLE, true, 45))
    }

    def verifySwaBizIrnIsPresent() {
        verifyElementPresent("IRN field label", waitForElement(INTERNAL_REFERENCE_NUMBER))
    }

    void clickChange() {
        waitForElement(CHANGE_RESERVATION_LINK).click()
    }

    boolean verifyChangeLinkIsPresent(){
        return isElementDisplayed(CHANGE_RESERVATION_LINK)
    }

    boolean isCurrentPage() {
        getTitle().equals(PAGE_TITLE)
    }

    void verifyNewCarConfirmationPresent() {
        waitForElement(CONFIRMATION_TOP_SUMMARY_FOR_CAR).findElement(By.className("confirmation_number")).shouldNotBe null, "Did not find Car Confirmation"
    }

    void verifyHotelWidget() {
        isElementPresent(HOTEL_SUMMARY_CONTAINER)
    }

    void verifyCarWidget() {
        isElementPresent(CONFIRMATION_TOP_SUMMARY_CAR)
    }

    void verifyHotelCrossSellIsNotDisplayed() {
        $(".buttonWhiteWidth126").text().shouldNotBe "Find a Hotel", "Hotel cross sell is visible"
    }

    void verifyNumberOfExistingAirProductsIs(int expectedNumberOfAirProducts) {
        def actualNumberOfAirProducts = waitForElement(ITINERARY_PRODUCT_BY).findElements(AIR_ITINERARY_CONTAINER_VL).size()
        actualNumberOfAirProducts.shouldBe expectedNumberOfAirProducts, "Expected Trip To have $expectedNumberOfAirProducts existing product"
    }

    void verifyNumberOfExistingHotelProductsIs(int expectedNumberOfHotelProducts) {
        $(ITINERARY_PRODUCT).find(".hotel_itinerary_container_with_vertical_label").size().shouldBe expectedNumberOfHotelProducts, "Expected Trip To have $expectedNumberOfHotelProducts existing product"
    }

    void verifyNumberOfExistingCarProductsIs(int expectedNumberOfCarProducts) {
        waitForElement(ITINERARY_PRODUCT_BY).findElements(CAR_ITINERARY_CONTAINER_VL).size().shouldBe expectedNumberOfCarProducts, "Expected Trip To have $expectedNumberOfCarProducts existing product"
    }

    void verifyTripName(String tripName) {
        findElement(By.id("confirmation_trip_name")).text.shouldBe tripName.trim(), "Expected Trip to be $tripName"
    }

    void verifyCompoundPassengerName(String passengerName){
        findElement(By.className("passenger_row_name")).text.shouldBe passengerName.toUpperCase(), "Expected passenger name to be $passengerName"
    }

    void verifyTripDetailsPresence() {
        isElementPresent(CONFIRMATION_TRIP_MANAGEMENT_TITLE_DETAILS).shouldBe true, "Did not find 'New Product Added To Existing Trip' message"
    }

    boolean verifyNewCarProductExpanded() {
        findElement(By.id("car_confirmation")).findElement(By.className("collapsibleSection")).displayed.shouldBe true, "New Car product was not expanded"
    }

    boolean verifyNewAirProductExpanded() {
        findElement(By.id("air_confirmation")).findElement(By.className("collapsibleSection")).displayed.shouldBe true, "New Air product was not expanded"
    }

    boolean verifyNewCarProductIsCollapsed() {
        findElement(By.id("car_confirmation")).findElement(By.className("collapsibleSection")).displayed.shouldBe false, "New Car Product when adding with Air was not collapsed"
    }

    void verifyAllExistingProductsAreCollapsed() {
        def collapsedElements = waitForElement(By.className("trip_itinerary_products")).findElements(By.className("collapsibleSection"))
        for (collapsedElement in collapsedElements) {
            if (collapsedElement.displayed) {
                fail "Expected all existing trip products to be collapsed"
            }
        }
    }

    void flightCheckIn() {
        findElement(By.id("checkInForFlightButton")).click()
    }

    WebElement hotelCrossSell() {
        waitForElement(CONFIRMATION_SEARCH_ALTERNATE_SEARCH_RESULTS)
        WebElement hotelCrossSell = waitForElement(HOTEL_CROSS_SELL)
        WebElement textFromHotelCrossSellHeader = hotelCrossSell.findElement(By.className("confirmation_search_alternate_header"))
        return textFromHotelCrossSellHeader
    }

    WebElement carCrossSell(){
        waitForElement(CONFIRMATION_SEARCH_ALTERNATE_SEARCH_RESULTS)
        //waiting for ajax to finish loading before we interact with the cross sell
        WebElement carCrossSell = waitForElement(CAR_CROSS_SELL)
        WebElement textFromCarCrossSellHeader = carCrossSell.findElement(By.className("confirmation_search_alternate_header"))
        return textFromCarCrossSellHeader
    }

    void verifyCarVendorsAreUnique() {
        List<WebElement> carVendors = waitForElements(CAR_CROSS_SELL_VENDORS)
        Set<String> carVendorNames = Sets.newHashSet()
        String name = null
        for (WebElement carVendor : carVendors){
            name = carVendor.getAttribute("class")
            if (!carVendorNames.contains(name)) {
                carVendorNames.add(name)
            } else {
                fail("Repeated car vendor name ${name}")
            }
        }
    }

    void carCrossSellDestinationCityRate(String arrivalCityCode) {
        carCrossSell().getText().shouldContain arrivalCityCode, "Did not find Car Cross Sell for arrival city"
    }

    void hotelCrossSellDestinationCityRate(String arrivalCityCode) {
        hotelCrossSell().getText().shouldContain arrivalCityCode, "Did not find hotel Cross Sell for arrival city"
    }

    void verifyViewBillingDetailsLinkIsPresent() {
        waitForElement(VIEW_BILLING_DETAILS_LINK).isDisplayed().shouldBe true, "Did not find view billing details link on confirmation page"
        waitForElement(VIEW_BILLING_DETAILS_LINK).getAttribute("title").shouldContain "View Billing Details", "Did not find view billing details link on confirmation page"
    }

    def tripManagementTitleSumaryIsNotPresent(){
        verifyElementNotPresent(CONFIRMATION_TRIP_MANAGEMENT_TITLE_SUMMARY_DESCRIPTION, CONFIRMATION_TRIP_MANAGEMENT_TITLE_SUMARY)
    }

    def tripManagementDetailsIsNotPresent(){
        verifyElementNotPresent(NEW_PRODUCT_ADDED_EXISTING_TRIP_MESSAGE,CONFIRMATION_TRIP_MANAGEMENT_TITLE_DETAILS)
    }

    def verifyTripNameIsFormattedByDefault(String tripName) {
        waitForElement(CONFIRMATION_TRIP_NAME).getText().shouldContain tripName, "Default trip name is not correctly formatted"
    }

    def viewDetailsLinkShouldNotBeDisplayed() {
        verifyElementNotPresent(CONFIRMATION_TOP_SUMMARY_VIEW_DETAILS_MESSAGE,CONFIRMATION_TOP_SUMARY_VIEW_DETAILS)
    }

    def verifyMsgExistingPurchasesInYourTripDisplayed() {
        isMsgExistingPurchasesInYourTripDisplayed().shouldBe true, 'The Existing purchases in your trip message should be visible'
    }

    private boolean isConfirmationSummaryCarDisplayed() {
        return isElementDisplayed(CONFIRMATION_TOP_SUMMARY_CAR)
    }

    def verifyConfirmationSummaryCarNotDisplayed() {
        isConfirmationSummaryCarDisplayed().shouldBe false, 'The Confirmation summary Car Cross Sell should not be visible'
    }

    void clickViewReservationFromConfirmationPage() {
        findElement(By.linkText("View Reservation.")).click()
    }

    def clickCancelReservation() {
        waitForElement(CAR_CANCEL_LINK).click()
    }

    void verifySeatSelectionVerbiage(String outBoundInBound) {
        if (outBoundInBound.equalsIgnoreCase("outbound")){
            waitForElement(OUTBOUND_SEAT_SELECTION_TEXT).text.shouldContain SEAT_SELECTION_TEXT
        } else if(outBoundInBound.equalsIgnoreCase("inbound")){
            waitForElement(INBOUND_SEAT_SELECTION_TEXT).text.shouldContain SEAT_SELECTION_TEXT
        } else {
            fail("Message did not find ${outBoundInBound}")
        }
    }

    void verifySeatSelectionUnavailableVerbiage(String outBoundInBound) {
        sleep( 10000 )
        if (outBoundInBound.equalsIgnoreCase("outbound")){
            WebElement element = waitForElement(OUTBOUND_SEAT_SELECTION_UNAVAILABLE_TEXT)
            element.text.shouldContain SEAT_SELECTION_UNAVAILABLE_TEXT
            element.getAttribute('style').shouldContain "block"
        } else if(outBoundInBound.equalsIgnoreCase("inbound")){
            WebElement element = waitForElement(INBOUND_SEAT_SELECTION_UNAVAILABLE_TEXT)
            element.text.shouldContain SEAT_SELECTION_UNAVAILABLE_TEXT
            element.getAttribute('style').shouldContain "block"
        } else {
            fail("Message did not find ${outBoundInBound}")
        }
    }


    void clickSeatSelectionButton(String outboundInbound) {
        if (outboundInbound.equalsIgnoreCase("outbound")){
            waitForElement(OUTBOUND_SEAT_SELECTION_BUTTON).click()
        } else if(outboundInbound.equalsIgnoreCase("inbound")){
            waitForElement(INBOUND_SEAT_SELECTION_BUTTON).click()
        } else {
            fail("Message did not find ${outboundInbound}")
        }
    }

    void verifySeatSelectionButton() {
        (isElementDisplayed(INBOUND_SEAT_SELECTION_BUTTON) || isElementDisplayed(OUTBOUND_SEAT_SELECTION_BUTTON)).shouldBe true, "No seat selection button found"
    }

    void verifySeatSelectionButtonNotShown() {
        verifyElementNotPresent("Inbound Seat Selection Button (seatSelectionButtonId1) ", INBOUND_SEAT_SELECTION_BUTTON);
        verifyElementNotPresent("Outbound Seat Selection Button (seatSelectionButtonId0) ", OUTBOUND_SEAT_SELECTION_BUTTON);
    }

    void verifyUMPurchaseVerbiageOnConfirmationPage() {
        verifyPage()
        isElementPresent(By.className(UM_PURCHASED_TEXT_CLASS)).shouldBe true, "The 'Unaccompanied Minor Purchased' should be present"
    }

    void verifyAssistanceRequestedText() {
        waitForElement(UM_ALERT_MESSAGE_CLASS).getText().shouldContain ASSISTANCE_REQUESTED_TEXT, "The text '${ASSISTANCE_REQUESTED_TEXT}' should be present"
    }

    void verifyViewParentGuardianInfoLink() {
        isElementPresent(By.id(VIEW_PARENT_GUARDIAN_INFO_LINK_ID)).shouldBe true, "The 'View Parent/Guardian Information link' should be present"
    }

    void verifyUMChargePricingSectionShowCorretValues(String itineraryType, Integer passengers) {
        Integer chargesRequired = umChargesRequired(itineraryType, passengers)
        GuardianChargeTable table = new GuardianChargeTable(waitForElement(UM_CHARGE_PRICING_TABLE))
        table.unaccompaniedMinorCharges().size().shouldBe chargesRequired, "The UM charges should be $chargesRequired"
        table.unaccompaniedMinorCharges().each{
            it.shouldBe CommonConstants.UM_FEE, "The Unaccompanied Minor charge should be ${CommonConstants.UM_FEE}"
        }
    }

    private int umChargesRequired(String itineraryType, Integer passengers) {
        Integer segments
        if(itineraryType == "RT") {
            segments = 2
        } else if(itineraryType == "OW") {
            segments = 1
        }
        Integer chargesRequired = passengers * segments
        return chargesRequired
    }

    void verifyTripAndAirTotals() {
        BigDecimal tripTotal = DollarToBigDecimalConverter.toBigDecimal(waitForElement(TRIP_TOTAL))
        BigDecimal airTotal = waitForElement(PRICE_ITINERARY).getText().minus('$').minus('Fare Breakdown').trim().toBigDecimal()
        GuardianChargeTable table = new GuardianChargeTable(waitForElement(UM_CHARGE_PRICING_TABLE))
        table.unaccompaniedMinorCharges().each { airTotal += it }
        tripTotal.shouldBe airTotal, "The Trip Total should be the Itinerary Price plus the UM Fee"
    }

    void clickOnFareBreakdownLink() {
        waitForElement(TOTAL_FARE_BREAK_DOWN_LINK).click()
    }

    void verifyTotalsOnFareBreakDownFlyOut() {
        FareBreakdownFlyOut flyout = openFareBreakDownFlyOut()
        itineraryData.totalBaseFare.shouldBe flyout.baseFare, "The Base Fare is not the same as the one displayed on the Price Page"
        itineraryData.totalTaxes.shouldBe flyout.taxes, "The Taxes total is not the same as the one displayed on the Price Page"
        itineraryData.totalCharge.shouldBe flyout.total, "The total is not the same as the one displayed on the Price Page"
        itineraryData.segmentFee.shouldBe flyout.segmentFee, "segmentFee differs from the Price Page to the Confirmation Page"
        itineraryData.passengerFacilityFee.shouldBe flyout.passengerFacilityFee, "passengerFacilityFee differs from the Price Page to the Confirmation Page"
        itineraryData.securityFee.shouldBe flyout.securityFee, "securityFee differs from the Price Page to the Confirmation Page"
        itineraryData.totalPerPassenger.shouldBe flyout.totalPerPassenger , "totalPerPassenger differs from the Price Page to the Confirmation Page"
        itineraryData.passengerCount.shouldBe flyout.passengerCount, "passengerCount differs from the Price Page to the Confirmation Page"
        itineraryData.dollarTotal.shouldBe flyout.dollarTotal(), "dollarTotal differs from the Price Page to the Confirmation Page"
        flyout.closeFlyout()
    }

    FareBreakdownFlyOut openFareBreakDownFlyOut() {
        waitForElement(TOTAL_FARE_BREAK_DOWN_LINK).click()
        FareBreakdownFlyOut flyout = new FareBreakdownFlyOut(waitForElement(TOTAL_PRICING_BREAKDOWN_FLY_OUT))
        return flyout
    }

    void verifyFareDiscountNotPresent() {
        FareBreakdownFlyOut flyout = openFareBreakDownFlyOut()
        verifyElementNotPresent("Fare Discount", FARE_DISCOUNT)
        flyout.closeFlyout()
    }

    void clickOnCheckInForFlightButton() {
        //Ravendra - Strory# -date:10/18/14 - //
        verifyCheckinButtonIsAvailable()
        waitForElement(CHECKIN_FOR_FLIGHT_BUTTON).click()
    }

    void verifyCarPnr() {
        waitForElement(SPAN_CONFIRMATION_NUMBER).getText().shouldNotBe null, "Car PNR was Not Found"
    }

    def verifyUMNotEligibleForCheckinOnlineOopsMessage() {
        getExpectedOopsMessage().shouldContain UM_NOT_ELIGIBLE_FOR_CHECKIN_ONLINE_OOPS, "Did not find expected oops message ${UM_NOT_ELIGIBLE_FOR_CHECKIN_ONLINE_OOPS}"
    }

    void verifyItineraryMatchesBookedFlight() {
        WebElement flightItinerary =  waitForElement(FLIGHT_ITINERARY)
        String flightItineraryText = flightItinerary.getText()
        flightItineraryText.shouldContain scenarioState.lastAirReservation.itineraryData.departureStation
        flightItineraryText.shouldContain scenarioState.lastAirReservation.itineraryData.returnStation
        WebElement flightDates =  waitForElement(FLIGHT_DATES)
        String flightDatesText = flightDates.getText()
        flightDatesText.shouldContain scenarioState.firstAirReservation.itineraryData.departureDate.format(DATE_FORMAT)
        flightDatesText.shouldContain scenarioState.firstAirReservation.itineraryData.returnDate.format(DATE_FORMAT)
        List<WebElement> flightNumbers
        flightNumbers =  waitForElements(FLIGHT_NUMBERS_CR1)
        flightNumbers.get(0).getText().shouldContain scenarioState.lastAirReservation.itineraryData.flightNumbers[0]
        flightNumbers.get(1).getText().shouldContain scenarioState.lastAirReservation.itineraryData.flightNumbers[1]
    }

    void verifyLoginRRNumber() {
        String loginRRNumber = flow.userLoggedInRapidRewardsNumber;
        findElement(By.className("passenger_row_rr_number")).getText().shouldContain loginRRNumber, "RR Number was Not Found"
    }

    void verifyExistingProductsSectionTitle() {
        waitForElement(EXISTING_PRODUCTS_SECTION_TITLE).getText().shouldBeEqual "Existing Purchases in Trip", "should be equal to 'Existing Purchases in Trip'"
    }

    void verifyCarCrossSellSectionNotPresent(){
        List<WebElement> sections = waitForElements(UPSELL_ITINERARY_DETAILS_TITLE)
        for (WebElement element : sections) {
            element.getText().shouldNotBe CAR_CROSS_SELL_SECTION_TITLE, "The Car cross section was not expected"
        }
    }

    def storeRRPointsUsedOnPurchase() {
        flow.rapidRewardsPoints = waitForElement(TOTAL_POINTS).getText().trim().minus("pts").minus(",") as int
    }

    void verifyBusinessSelectText() {
        waitForElement(By.cssSelector("div.fareProductName a.headerText")).getText().shouldContain "Business Select"
    }

    void verifyOutboundFareType(String fareType) {
        waitForElement(OUTBOUND_FARETYPE).getText().shouldContain fareType
    }

    void verifyInboundFareType(String fareType) {
        waitForElement(INBOUND_FARETYPE).getText().shouldContain fareType
    }
    def verifyDepartureFlightDateAndTimeEquality(){
        flow.getDateAndTimeFlightDeparts().equals(getDateAndTimeThisFlightLeaves())  ? true : fail(" Departure DateTime in confirmation page does not match with DateTime in select page ")

    }

    def verifyArrivalDateAndTimeEquality(){
        flow.getDateAndTimeDepartingFlightArrives().equals(getDateAndTimeThisFlightArrives()) ? true : fail("Arrival DateTime in confirmation page does not match with DateTime in select page")

    }
    /**
     * Determines the departure time for a one way flight
     *  @return departure time
     * TODO:Write a improved version to handle different scenarios for RoundTrip and OpenJaw
     *
     */
    public Date getDateAndTimeThisFlightLeaves(String timeClassName = SEGMENT_TIME_CLASS,String meridianClassName = SEGMENT_TIME_AMPM_CLASS,String dateClassName = DEPARTURE_LONG_DATE_CLASS) {
        def flightTime = waitForElement(AIR_ITINERARY_DEPART_ID).findElements(By.className(timeClassName)).first().getText().trim()
        def flightDate = waitForElement(AIR_ITINERARY_DEPART_ID).findElements(By.className(dateClassName)).first().getText().trim()
        def meridian = waitForElement(AIR_ITINERARY_DEPART_ID).findElements(By.className(meridianClassName)).first().getText().trim()
        return formatFlightDate(flightDate, flightTime, meridian)
    }
    /**
     * Determines the arrival time for a one way flight
     * @return arrival time
     * TODO:Write a improved version to handle different scenarios for RoundTrip and OpenJaw
     *
     */
    private Date getDateAndTimeThisFlightArrives(String timeClassName = SEGMENT_TIME_CLASS,String meridianClassName = SEGMENT_TIME_AMPM_CLASS,String dateClassName = DEPARTURE_LONG_DATE_CLASS) {
        def flightTime = waitForElement(AIR_ITINERARY_DEPART_ID).findElements(By.className(timeClassName)).last().getText().trim()
        def flightDate = waitForElement(AIR_ITINERARY_DEPART_ID).findElements(By.className(dateClassName)).last().getText().trim()
        def meridian   = waitForElement(AIR_ITINERARY_DEPART_ID).findElements(By.className(meridianClassName)).last().getText().trim()

        return formatFlightDate(flightDate, flightTime, meridian)
    }


    static Date formatFlightDate(String date, String time, String meridian){
        DateTimeFormatter fmt = DateTimeFormat.forPattern("EEEE, MMM dd, yyyy")
        String dateAsString =  DateTime.parse(date,fmt).toString("yyyy M d") + " " + time.trim() + " " + meridian
        return Date.parse("yyyy M d h:m a", dateAsString)
    }

    def clickAddRapidRewardsNumberLink(){
        waitForElement(ADD_RAPID_REWARDS_NUMBER_LINK).click()
    }

    def verifyAirViewDetailsLinkHref() {
        waitForElement(VIEW_AIR_DETAILS).getAttribute("href").shouldContain "#air_subsection_purchased", "View Details link did not have correct link"
    }

    def verifyAirTotalInAirWidgetWithTotalFromPricePage(){
        waitForElement(AIR_WIDGET_AIR_TOTAL).text.shouldContain pricePageData.tripTotal.toString()
    }

    def verifyAirTotalWidgetEqualsTripTotal() {
        waitForElement(AIR_WIDGET_AIR_TOTAL).text.shouldBe waitForElement(TRIP_GRAND_TOTAL).text
    }

    def verifyChangeAndCancelLinksPresent() {
        isElementPresent(CHANGE_RESERVATION_LINK)
        isElementPresent(CANCEL_RESERVATION_LINK)
        if(itineraryData.isRoundTrip()){
            isElementPresent(RESEND_LINK)
        }
    }

    def verifyStationsInAirWidget() {
        verifyStations(AIR_WIDGET_STATION)

    }

    def verifyStationsInDetails() {
        verifyStations(FLIGHT_ITINERARY)
    }

    def verifyConfirmationNumbers() {
        if(passengerData.containsSeniorAndAdultPassengers()) {
            List<WebElement> airWidgetConfirmationNumbers = waitForElements(By.cssSelector("#confirmation_top_summary_air .confirmation_number"))
            List<WebElement> airConfirmationNumbers = waitForElements(By.cssSelector(".air_itinerary_passengers_table .confirmation_number"))
            airWidgetConfirmationNumbers[0].text.shouldContain airConfirmationNumbers[0].text, "Senior confirmation number did not match"
            airWidgetConfirmationNumbers[1].text.shouldContain airConfirmationNumbers[1].text, "Adult confirmation number did not match"
        } else {
            String airWidgetConfirmationNumber = waitForElement(CONFIRMATION_TOP_SUMMARY_AIR).findElement(By.className("confirmation_number")).text
            String airConfirmationNumber = waitForElement(AIR_CONFIRMATION_SUMMARY).findElement(By.className("confirmation_number")).text
            airWidgetConfirmationNumber.shouldContain airConfirmationNumber, "confirmation numbers did not match"
        }
    }

    def verifyRRSignupModule() {
        isElementPresent(RR_SIGN_UP_MODULE)
    }

    def verifyRRSignupModuleNotPresent() {
        verifyElementNotPresent("RR Sign Up Module",RR_SIGN_UP_MODULE)
    }

    def verifyAmountPaidEqualsTripTotal() {
        waitForElement(AMOUNT_PAID).text.shouldBe waitForElement(TRIP_GRAND_TOTAL).text
    }

    def verifyPurchaserName() {
        StringBuilder billingFullName = new StringBuilder()
        if(!flow.useSavedCreditCard) {
            billingFullName.append(billingData.firstName).append(" ").append(billingData.lastName)
        } else {
            billingFullName.append(rrContactInformation.creditCardFirstName).append(" ").append(rrContactInformation.creditCardLastName)
        }
        waitForElement(BILLING_FULL_NAME).text.shouldBe billingFullName.toString(), "Billing name did not match the one entered on purchase page"
    }

    def verifyBillingAddress() {
        String address
        String city
        String state
        String country
        String zipCode

        if(!flow.useSavedCreditCard) {
            address = billingData.address
            city = billingData.city
            state = billingData.state
            country = billingData.country
            zipCode = billingData.zipCode
        } else {
            address = rrContactInformation.creditCardBillingAddress
            city = rrContactInformation.creditCardBillingCity
            state = rrContactInformation.creditCardBillingState
            country = rrContactInformation.creditCardBillingCountry
            zipCode = rrContactInformation.creditCardBillingZipCode
        }

        address.toLowerCase().shouldContain waitForElement(BILLING_ADDRESS).text.toLowerCase() , "Billing address did not match the one entered on purchase page"
        waitForElement(BILLING_CITY).text.shouldBe city, "Billing city did not match the one entered on purchase page"
        waitForElement(BILLING_STATE).text.shouldBe state, "Billing state did not match the one entered on purchase page"
        waitForElement(BILLING_COUNTRY).text.shouldBe country, "Billing country did not match the one entered on purchase page"
        waitForElement(BILLING_ZIP_CODE).text.shouldBe zipCode, "Billing Zip code did not match the one entered on purchase page"
    }

    def verifyFundApplied() {
        List<BigDecimal> balanceList = retireveFundsAppliedBalance()
        BigDecimal fundsApplied = balanceList[1]
        BigDecimal totalAmountApplied = getAmountApplied()
        totalAmountApplied.shouldEqual fundsApplied, "The total amount applied ${totalAmountApplied} did not equal the amount applied ${fundsApplied}"

    }

    def List<BigDecimal> retireveFundsAppliedBalance() {
        List<WebElement> list = waitForElements(By.xpath("//div[@class='form_of_payment_fund_right_container_row']/div"))
        List<BigDecimal> balanceList = new ArrayList<BigDecimal>()
        balanceList.add(list[0].text.replace('$', '').toBigDecimal())
        balanceList.add(list[1].text.replace('$', '').toBigDecimal())
        balanceList.add(list[2].text.replace('$', '').toBigDecimal())

        return balanceList
    }

    def verifyTotalAmountPaid() {
        BigDecimal expectedTotalAmount = getCreditCardAmount()
        if(!flow.isRapidRewardsPointsPurchaseOnly && !itineraryData.isPromoCertBooking()) {
            expectedTotalAmount += getAmountApplied()
        }
        BigDecimal totalAmountPaid = waitForElement(TOTAL_PAID).text.replace('$', "").toBigDecimal()
        expectedTotalAmount.shouldEqual totalAmountPaid, "Total amount paid ${totalAmountPaid} was not correct"
    }

    def BigDecimal getAmountApplied() {
        BigDecimal totalAmountApplied = waitForElements(AMOUNT_APPLIED)[1].text.replace('$', "").toBigDecimal()
        totalAmountApplied
    }

    def BigDecimal getCreditCardAmount() {
        if(flow.isRapidRewardsPointsPurchaseOnly){
            waitForElements(AMOUNT_APPLIED)[1].text.replace('$', "").toBigDecimal()
        } else if(itineraryData.isPromoCertBooking()){
            if(itineraryData.isRoundTripOrOpenJaw()){
                waitForElements(AMOUNT_APPLIED)[2].text.replace('$', "").toBigDecimal()
            }
            else {
                waitForElements(AMOUNT_APPLIED)[1].text.replace('$', "").toBigDecimal()
            }
        } else {
            waitForElements(AMOUNT_APPLIED)[0].text.replace('$', "").toBigDecimal()
        }
    }

    def verifyTripTotal() {
        BigDecimal totalAmountPaid = waitForElement(TOTAL_PAID).text.replace('$', "").toBigDecimal()
        BigDecimal totalTrip = waitForElement(TRIP_TOTAL).text.replace('$', "").toBigDecimal()
        totalAmountPaid.shouldEqual totalTrip, "Trip total ${totalTrip} did not equal the amount paid ${totalAmountPaid}"
        BigDecimal umFee = 0
        if(flow.isUM){
            umFee = feeCalculator.calculateGuardianCharge()
        }
        totalTrip.shouldEqual pricePageData.tripTotal + umFee, "Trip total ${totalTrip} was not the same as in the other pages ${pricePageData.tripTotal}"
        totalTrip.shouldEqual pricePageData.airTotal, "Trip total ${totalTrip} was not the same as in the other pages ${pricePageData.airTotal}"
    }

    def verifyPaymentAmount() {
        getCreditCardAmount().shouldEqual purchasePageData.totalDueAmount, "Credit card amount did not match the one in the purchase page"
        getAmountApplied().shouldEqual purchasePageData.luvVocherAmount, "Luv Voucher amount did not match the one in the purchase page"
    }

    def verifyLuvVoucherInformation() {
        List<BigDecimal> luvVoucherInformation = retireveFundsAppliedBalance()
        luvVoucherInformation[0].shouldEqual purchasePageData.fullBalanceAmount, "Luv Voucher balance did not match the one in the purchase page"
        luvVoucherInformation[1].shouldEqual purchasePageData.luvVoucherAppliedAmount, "Applied Luv Voucher did not match the one in the purchase page"
        luvVoucherInformation[2].shouldEqual purchasePageData.luvVoucherRemainingAmount, "Remaining Luv Voucher did not match the one in the purchase page"
    }

    def verifyRRLinkPresent() {
        if (!flow.isRapidRewards && !flow.isCustomer) {
            waitForElements(RR_LINK, false).size() shouldEqual passengerData.passengers.size(), "Add Rapid Rewards Number link missing in the view reservation page"
        }
    }

    void verifyItinerary() {
        WebElement flightItinerary =  waitForElement(FLIGHT_ITINERARY)
        String flightItineraryText = flightItinerary.getText()
        flightItineraryText.shouldContain itineraryData.departureStation, "Departure Station was not displayed in confirmation page"
        if(itineraryData.lessThan4HourLayover){
            flightItineraryText.shouldContain itineraryData.returnStation, "Return Station was not displayed in confirmation page"
        }
        else {
            flightItineraryText.shouldContain itineraryData.arrivalStation, "Arrival Station was not displayed in confirmation page"
        }

        WebElement flightDates =  waitForElement(FLIGHT_DATES)
        String flightDatesText = flightDates.getText()
        flightDatesText.shouldContain itineraryData.departureDate.format(DATE_FORMAT)

        if (itineraryData.isRoundTripOrOpenJaw()) {
            flightItineraryText.shouldContain itineraryData.returnStation
            flightDatesText.shouldContain itineraryData.returnDate.format(DATE_FORMAT)
        }
    }

    def verifyAirTotalAndTripTotalAreEqual() {
        BigDecimal airTotal = priceTable.airTotal()
        airTotal.shouldEqual waitForElement(TRIP_TOTAL).text.replace('$', '').toBigDecimal(), "Air total and Trip total are not equal"
        airTotal.shouldEqual purchasePageData.tripTotal, "Air total and Trip total from purchase are not equal"
    }

    def verifyAmountPaidEqualsAirTotalWidget(){
        BigDecimal amountPaid = waitForElement(AMOUNT_PAID).text.replace('$', '').toBigDecimal()
        BigDecimal widgetAirTotal =  waitForElement(AIR_WIDGET_AIR_TOTAL).text.replace('$', '').toBigDecimal()
        amountPaid.shouldBe widgetAirTotal, "Amount paid was not same as Widget air total"
    }

    def verifyCreditCardAmountApplied(){
        BigDecimal totalAmountCharged = waitForElement(TOTAL_AMOUNT_CHARGED).text.replace('$', "").toBigDecimal()
        totalAmountCharged.shouldEqual purchasePageData.totalDueNow, "Amount was not the same as Total Due Now in AppliedTravelFundsSummary section of PurchasePage"
    }

    def verifyTravelFundsPaymentType(){
        waitForElement(FORM_OF_PAYMENT_TRAVEL_FUND).text.shouldContain "Travel Funds", "Travel Funds form of payment was not present"
        waitForElement(FORM_OF_PAYMENT_TRAVEL_FUND).text.shouldContain scenarioState.lastAirReservation.adultPnr , "Confirmation Number of cancelled PNR was not the numnber of the Travel Funds"
    }

    def verifyOriginalBalance(){
        waitForElements(ORIGINAL_BALANCE)[1].text.replace('$', '').toBigDecimal().shouldBe scenarioState.lastAirReservation.getPrice(), "The original balance in Payment Table of Confirmation Page did not match the amount held during cancel PNR"
    }

    def verifyAppliedAmount(){
        waitForElements(APPLIED_AMOUNT)[4].text.replace('$', '').toBigDecimal().shouldBe purchasePageData.getTotalTravelFundsApplied(), "Applied amount in Payment Table of Confirmation Page did not match the Travel Funds Applied Today in Exchanged TicketFlyout Details of Purchase Page"
    }

    def verifyRemainingFunds(){
        waitForElements(APPLIED_AMOUNT)[5].text.replace('$', '').toBigDecimal().shouldBe purchasePageData.getExchangeTicketFundsRemaining(), "Remaining Funds in Payment table of Confirmation Page did not match Travel Funds Remaining in Exchanged TicketFlyout Details of Purchase Page"
    }

    def verifyAmountAppliedInPage(){
        BigDecimal amountApplied
        if (scenarioState.getLastAirReservation().itineraryData.departingFlight_fareClass == BUSINESS_SELECT){
            amountApplied = waitForElement(AMOUNT_APPLIED).text.replace('$', '').toBigDecimal()
        }else{
            amountApplied = waitForElements(AMOUNT_APPLIED)[1].text.replace('$', '').toBigDecimal()
        }
        amountApplied.shouldBe waitForElements(APPLIED_AMOUNT)[4].text.replace('$', '').toBigDecimal(), "Amount Applied in Payment table of Confirmation Page did not match the Applied Amount of TravelFunds in Form of Payment table"
    }

    def verifyAmountPaid(){
        BigDecimal amountPaid = waitForElement(TOTAL_PAID).text.replace('$','').toBigDecimal()
        if (scenarioState.getLastAirReservation().itineraryData.departingFlight_fareClass == BUSINESS_SELECT){
            BigDecimal travelFundsSum = waitForElement(AMOUNT_APPLIED).text.replace('$','').toBigDecimal()
            amountPaid.shouldBe travelFundsSum,"Amount Paid on the Confirmation Page did not match the TravelFunds"
        } else {
            BigDecimal creditCardAndTravelFundsSum = waitForElement(TOTAL_AMOUNT_CHARGED).text.replace('$','').toBigDecimal() +  waitForElements(AMOUNT_APPLIED)[1].text.replace('$','').toBigDecimal()
            amountPaid.shouldBe creditCardAndTravelFundsSum,"Amount Paid on the Confirmation Page did not match the sum of Credit card and TravelFunds"
        }
        amountPaid.shouldBe waitForElement(AIR_WIDGET_AIR_TOTAL).text.replace('$','').toBigDecimal(),"Amount Paid did not match the Amount Paid in the Air widget"
    }

    def verifyAmountPaidAndTripTotalAreEqual() {
        BigDecimal amountPaid = waitForElement(TOTAL_PAID).text.replace('$','').toBigDecimal()
        amountPaid.shouldEqual purchasePageData.tripTotal, "Amount Paid and Trip total from purchase are not equal"
    }

    def verifyUMPassengerDetails() {
        List <WebElement> passengerDetails = waitForElements(UM_PASSENGER_DETAILS)
        String passengerName = passengerDetails[0].text.toUpperCase()
        String routeDetails = passengerDetails[1].text
        String receipt = passengerDetails[2].text
        String umFee = passengerDetails[3].text
        passengerName.toUpperCase() shouldBe passengerData.passengers[0].toString().toUpperCase(), "UM passenger name was not correct"
        routeDetails.toUpperCase() shouldBe itineraryData.departureStation + "-" + itineraryData.arrivalStation, "UM passenger details was not correct"
        receipt.shouldNotBe "", "Receipt # was empty in confirmation page"
        umFee.replace("\$", "").toBigDecimal() shouldEqual purchasePageData.guardianFee, "UM passenger fee was not correct"
    }

    def clickOnCarRentalOption(){
        waitForElement(ADD_A_CAR_VIEW_DETAILS_FIRST).click()
    }

    def getPointsApplied() {
        waitForElement(AMOUNT_APPLIED).text.replace(',','').replace('pts','').toBigDecimal()
    }

    def verifyConfirmationLinks(){
        isElementDisplayed(PRINT_PAGE_LINK).shouldBe true, "Print Page link is not displayed"
        isElementDisplayed(ADD_TO_CALENDAR_LINK).shouldBe true, "Add To Calendar link is not displayed"
        isElementDisplayed(FLIGHT_STATUS_LINK).shouldBe true, "Flight Status link is not displayed"
    }

    def verifyShowHideDetailsLinks(){
        (isElementDisplayed(HIDE_DETAILS_LINK) || isElementDisplayed(SHOW_DETAILS_LINK)).shouldBe true, "Hide/Show details link is not displayed"
    }

    def verifyCalendarIcons(){
        isElementDisplayed(CALENDAR_ICON_DEPART).shouldBe true, "Calendar Icon depart is not displayed"
        waitForElement(CALENDAR_ICON_DEPART).findElement(By.cssSelector(".month")).shouldNotBe null, "Month in Calendar Icon is not displayed"
        waitForElement(CALENDAR_ICON_DEPART).findElement(By.cssSelector(".dayOfWeek")).shouldNotBe null, "Day of Week in Calendar Icon is not displayed"
        itineraryData.departureDate.format("MMM d").toString().toLowerCase().shouldContain waitForElement(CALENDAR_ICON_DEPART).findElement(By.cssSelector(".month")).getText().toLowerCase(), "Calendar Icon does not contain the correct month"
        itineraryData.departureDate.format("E").toString().toLowerCase().shouldContain waitForElement(CALENDAR_ICON_DEPART).findElement(By.cssSelector(".dayOfWeek")).getText().toLowerCase(), "Calendar Icon does not contain the correct day of week"
        if(itineraryData.isRoundTripOrOpenJaw()){
            isElementDisplayed(CALENDAR_ICON_RETURN).shouldBe true, "Calendar Icon depart is not displayed"
            waitForElement(CALENDAR_ICON_RETURN).findElement(By.cssSelector(".month")).shouldNotBe null, "Month in Calendar Icon is not displayed"
            waitForElement(CALENDAR_ICON_RETURN).findElement(By.cssSelector(".dayOfWeek")).shouldNotBe null, "Day of Week in Calendar Icon is not displayed"
            itineraryData.returnDate.format("MMM d").toString().toLowerCase().shouldContain waitForElement(CALENDAR_ICON_RETURN).findElement(By.cssSelector(".month")).getText().toLowerCase(), "Calendar Icon does not contain the correct month"
            itineraryData.returnDate.format("E").toString().toLowerCase().shouldContain waitForElement(CALENDAR_ICON_RETURN).findElement(By.cssSelector(".dayOfWeek")).getText().toLowerCase(), "Calendar Icon does not contain the correct day of week"
        }
    }

    def verifyPromoCertPaymentType() {
        List <WebElement> paymentMethods = waitForElements(FORM_OF_PAYMENT)
        String paymentType
        if(flow.useSavedCreditCard) {
            paymentType = rrContactInformation.creditCardType.toLowerCase()
        } else {
            paymentType = customerInfoData.formOfPayment.trim().split(' ')[0].toLowerCase()
        }
        WebElement formOfPayment
        paymentMethods[0].text.trim().split(' ')[0].toLowerCase().shouldBe "award", "Form of payment is not correct"
        paymentMethods[0].text.trim().split(' ')[2][0].shouldBe "A", "The award number in form of payment was incorrect"
        paymentMethods[0].text.trim().split(' ')[2].endsWith("000").shouldBe true, "The award number in form of payment was incorrect"
        if(itineraryData.isRoundTripOrOpenJaw()){
            paymentMethods[1].text.trim().split(' ')[0].toLowerCase().shouldBe "award", "Second form of payment is not correct"
            paymentMethods[1].text.trim().split(' ')[2][0].shouldBe "A", "The second award number in form of payment was incorrect"
            paymentMethods[1].text.trim().split(' ')[2].endsWith("000").shouldBe true, "The second award number in form of payment was incorrect"
            formOfPayment = paymentMethods[2]
        } else {
            formOfPayment = paymentMethods[1]
        }
        formOfPayment.text.trim().split(' ')[0].toLowerCase().shouldBe paymentType, "Form of payment is not correct"
    }

    def openFlightStatusNotificationPageByLinkClick(){
        waitForElement(SUBSCRIBE_TO_FLIGHT_LINK).click();
        switchToOpenWindow(getWindowHandle(), "Flight Status Notification Page")
    }

    //Ravendra - Strory# -date:10/19/14 - //
    //Ravendra: refactor method below to make sure method has only required verifications not ALL.
    void verifySWACompleteItinerary()
    {
        verifyBasicPage()  //ok
        verifyStationsOnHeader()
        //verifyNewAirConfirmationPresent()   //ok - This is available under "ConfirmationVerfication.groovy" file. move to ConfirmationPage??
        verifyStationsInAirWidget()   //ok
        verifyAirTotalInAirWidgetWithTotalFromPricePage() //ok --not clear.
        verifyAirViewDetailsLinkHref() //ok
        verifyAirTotalWidgetEqualsTripTotal()    //ok
        verifyHotelWidget() //ok - can be specific to a story
        verifyCarWidget()   //ok - can be specific to a story
        verifyViewBillingDetailsLinkIsPresent()  //ok
        verifyTripName(itineraryData.getDefaultTripName())   //ok
        verifyChangeAndCancelLinksPresent()  //ok - need to revisit this method to verify the presence resend and share link.
        verifyStationsInDetails() //ok - But,  "verifyStationsInAirWidget" method is performing same verification on page at different place.
        verifyConfirmationNumbers()   //ok
        verifyPassengerName() //Do we need this here? if yes, method should call "viewReservationPage.itineraryDisplayed(<<fname>>,<<lname>>)
        verifyRRLinkPresent()
        verifyAddEarlyBirdCheckInButtons()
        verifySubscribeToFlightMessage()
        verifyDate()
        verifyDepartureAndArrivalCities()
        verifyDepartureAndArrivalTime()
        verifyFlightNumber()
        verifyTravelTime(true)
        verifyRoutingType()
        verifyFareType(true)
        verifyItinerary()
        if (passengerData.containsSeniorAndAdultPassengers())
        {
            priceTable.verifyMixedPaxSubTotalFromSelectFlightPage()
        }
        priceTable.verifyPassengerType()
        priceTable.verifyTripTypes()
        priceTable.verifyRouting()
        priceTable.verifyNumberOfPassengers()
        priceTable.verifyTotalOnPage()
        priceTable.verifyPriceFareBreakDown()
        if(itineraryData.promoCode == null){
            priceTable.verifyBaseFarePlusTax()
        }
        priceTable.verifyTaxesAndBaseFareInFareBreakDown()
        priceTable.verifyAirTotalAndFareBreakDownTotal()
        priceTable.verifyTotalPlusPassengerCount()
        priceTable.verifyRedeemableMemberPoints()
        verifyAirTotalAndTripTotalAreEqual()
        if(!pricePageData.availableFunds.equals(pricePageData.airTotal))
        {
            verifyPurchaserName()
            verifyBillingAddress()
        }
        AirReservation airReservation = scenarioState.getLastAirReservation()
        if (!itineraryData.hasTravelFunds || airReservation.getItineraryData().departingFlight_fareClass != BUSINESS_FARE_TYPE){
            verifyPaymentType()
        }
        if (!itineraryData.hasTravelFunds && !itineraryData.luvVoucher){
            verifyAmoutAppliedAmountPaidTripTotalAreEqual()
        }
        verifyAmountPaidEqualsTripTotal()
        verifyTripTotal()
        if(flow.isLoggedIn && (flow.isRapidRewards || flow.isCustomer)) {
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
            if(flow.isRapidRewards) {
                rapidRewardsAccountBar.verifyRRacountNumber()
                rapidRewardsAccountBar.verifyTier()
                verifyRRNumber()
            }
        }
        if(flow.isUM){
            swaConfirmationPage.with {
                verifyUMPurchaseVerbiage()
                verifyViewParentGuardianInfoLink()
                verifyAssistanceRequestedText()
                verifyUMPassengerDetails()
            }
        }
        priceTable.verifyOutboundAndInboundTotalAmounts()
        priceTable.verifyFareType()
        priceTable.verifySumSubtotalEqualsAirTotal()
        priceTable.verifySubTotal()
        if(itineraryData.luvVoucher) {
            verifyFundApplied()
            verifyTotalAmountPaid()
            verifyPaymentAmount()
            verifyLuvVoucherInformation()
        } else if (itineraryData.giftCard){
            verifyBalanceInformation()
            verifyAmountApplied()
        } else if (itineraryData.hasTravelFunds){
            swaConfirmationPage.with {
                verifyAmountPaidEqualsAirTotalWidget()
                if (airReservation.getItineraryData().departingFlight_fareClass != BUSINESS_FARE_TYPE){
                    verifyCreditCardAmountApplied()
                }
                verifyTravelFundsPaymentType()
                verifyOriginalBalance()
                verifyAppliedAmount()
                verifyRemainingFunds()
                verifyAmountAppliedInPage()
                verifyAmountPaid()
                verifyAmountPaidAndTripTotalAreEqual()
            }
        } else {
            verifyAmountAppliedToCCEqualsAirTotal()
        }
        flow.hasAir = true
        storeCurrentPNR()
    }
}
