package pages

import com.github.tanob.groobe.GrooBe
import com.swacorp.dotcom.webscenarios.air.Data
import domain.AirReservation
import domain.Passenger
import pages.elements.AirPriceContent
import state.Flow
import util.PageName
import util.SelectFlightsPageData
import java.text.SimpleDateFormat
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import pages.elements.AccountBar
import pages.elements.StopLogicInfo
import state.ScenarioState
import util.HotelItineraryData
import util.ItineraryData
import util.Station
import util.TripManagement
import static org.junit.Assert.fail
import static util.Locators.BREADCRUMB_IDS
import pages.mixins.*
import state.PassengerData
import util.RRContactInformation

@Mixin([PricingTableVerifications.class, ConfirmationVerifications.class])
class ViewReservationPage extends BasePage {

    private final static String ITINERARY_VERIFICATION_MESSAGE =
    "Enter your trip details below to view an upcoming itinerary, check in online and print boarding passes, share itinerary details, and more. Only Southwest Airlines confirmation numbers can be viewed, for bookings completed on Air Tran Airways please visit airtran.com"
    private final static String CREDIT_CARD_ITINERARY_NOT_FOUND_MESSAGE = "SW107024"
    private final static String WRONG_CREDIT_CARD_LOOKUP = "credit card"
    private final static String SOUTHWEST_ONLY_ITINERARY = "southwest only itinerary"
    private final static String AIRTRAN_DISCLAIMER = "airtran disclaimer"
    private static final String DAY_OF_WEEK_DATE_FORMAT = "EEEE MM/dd/yyyy"
    private static final String CAR_ITINERARY_RENTAL_INFO_CLASS = ".car_itinerary_rental_info"
    private static final String CAR_ITINERARY_DATE_CLASS = ".car_itinerary_car_date"
    private static final String CAR_ITINERARY_CONFIRMATION_NUMBER_CLASS = ".itineraries_header_confirmation_number_container"
    private static final String POPUP_FLIGHT_ITINERARY_XPATH = "//div[@id = 'cancellation_modal_product_of_interest']/div[@class='itineraries_header air_itinerary_container_with_image']/div[@class='itineraries_header_data']/p[@id='flightItinerary']"
    private static final String POPUP_FLIGHT_DATES_XPATH = "//div[@id = 'cancellation_modal_product_of_interest']/div[@class='itineraries_header air_itinerary_container_with_image']/div[@class='itineraries_header_data']/p[@id='flightDates']"
    private static final String POPUP_FLIGHT_CONFIRMATION_NUMBER_XPATH = "//div[@id = 'cancellation_modal_product_of_interest']/div[@class='itineraries_header air_itinerary_container_with_image']/div[@class='itineraries_header_confirmation']/p[@class='itineraries_header_confirmation_number_container']"
    private static final String POPUP_FLIGHT_CONTINUE_CANCELATION_ID = "continueCancelation"
    private static final String POPUP_CAR_ITINERARY_CLASS = "p.car_itinerary_rental_info"
    private static final String POPUP_CAR_DATES_CLASS = "p.car_itinerary_car_date"
    private static final String POPUP_CAR_CONFIRMATION_NUMBER_CLASS = ".car_itinerary_container_with_image .itineraries_header_confirmation_number_container"
    private static final String DISAMBIGUATION_TABLE_FLIGHT_DETAILS_LINK = "a.swa_feature_flightStatus_results_table_data_flightNumber_detailsLink"
    private static final String AIR_ITINERARY_DEPART_ID = "airItinerarydepart"
    private static final String TRIP_ASSOCIATED_PRODUCT_VERBIAGE_CLASS = "h3.trip_associated_products"
    private static final String ITINERARY_HEADER_DATA_CLASS = ".itineraries_header_data"
    private static final String SHOW_SUB_SECTION_LINK_ID = "showSubSectionLink"
    private static final String HOTEL_ITINERARY_HEADER_CLASS = ".itineraries_header_data"
    private static final String HOTEL_ITINERARY_DATES_CLASS = ".hotel_itinerary_hotel_date"
    private static final String HOTEL_ITINERARY_CONFIRMATION_NUMBER_ID = "confirmation_itinerary_hotel_confirmation_number"
    private static final String RETRIEVE_ANOTHER_AIR_SECTION_CLASS = "div.retrieve_add_another_air_section"
    private static final String RETRIEVE_ANOTHER_CAR_SECTION_CLASS = "div.retrieve_add_another_car_section"
    private static final String RETRIEVE_ANOTHER_HOTEL_SECTION_CLASS = "div.view_reservation_upsell_hotel_table"
    private static final String SEAT_SELECTION_TEXT = "Seat selection for AirTran flights only"
    private static final String INTERNATIONAL_TEXT = "International flights are not available to change on southwest.com"
    private static final String RESERVATION_CANCELLED_MESSAGE = "Your reservation has been cancelled"
    private static final String UM_ICON_TITLE = "Unaccompanied Minor"

    private static final By PASSENGER_ROW_NAME = By.cssSelector('td.passenger_row_name')
    private static final By TRIP_ITINERARY_DETAIL_TABLE_CONTAINER_CLASS = By.cssSelector(".trip_itinerary_detail_table_container")
    private static final By ITINERARY_MESSAGE = By.className("swa_page_description")
    private static final By AIRTRAN_DISCLAIMER_ID = By.id("airtran_disclaimer")
    private static final By POPUP_CONTENT_CONTAINER_CLASS = By.cssSelector(".popup_content_container")
    private static final By CAR_ITINERARY_CONTAINER_CLASS = By.cssSelector(".car_itinerary_container_with_vertical_label")
    private static final By HOTEL_ITINERARY_CONTAINER_CLASS = By.cssSelector(".hotel_itinerary_container_with_vertical_label")
    private static final By TRIP_ITINERARY_TITLE_CLASS = By.cssSelector('h2.trip_itinerary_title')
    private static final By RENAME_TRIP_BUTTON_CLASS = By.cssSelector("input.rename_trip_button")
    private static final By TRIP_ITINERARY_PRODUCTS_CLASS = By.cssSelector(".trip_itinerary_products")
    private static final By SAVE_NEW_TRIP_NAME_BUTTON = By.cssSelector('input.save_new_trip_name_button')
    private static final By TRIP_NAME_FIELD = By.cssSelector("input.trip_name_field")
    private static final By AIR_ITINERARY_PASSENGERS_TABLE = By.cssSelector("table.air_itinerary_passengers_table")
    private static final By BUSINESS_SELECT_ICON = By.cssSelector("img.business_select_icon")
    private static final By A_LIST_ICON = By.cssSelector("img.a-list_icon")
    private static final By DISAMBIGUATION_TABLE = By.cssSelector("div.swa_pnrFriendlyLookup_viewShare_results_table_container")
    private static final By BREADCRUMB_ADD_RAPID_REWARDS_NUMBER_LINK = By.xpath("//a[@id='Add Rapid Rewards Number']")
    private static final By CANCEL_RESERVATION_LINK_ID = By.id("cancelReservationLink")
    private static final By CANCEL_RESERVATION_BREAD_CRUMB = By.xpath("//a[@id='Cancel Reservation']")
    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By EARLY_BIRD_ICON = By.id("early_bird_icon")
    private static final By FLIGHT_DATES = By.id("flightDates")
    private static final By FLIGHT_ITINERARY = By.id("flightItinerary")
    private static final By AIRTRAN_LINK = By.id("airTranLink")
    private static final By INTERNATIONAL_TEXT_ON_CHANGE_PAGE = By.className("topPageInstructions")
    private static final By FIRST_ADULT_PASSENGER_NAME = By.id("passenger_adult_0")
    private static final By CREDIT_CARD_NUMBER = By.id("creditCardNumber")
    private static final By ORIGIN_AIRPORT_DISPLAYED = By.id("originAirport_displayed")
    private static final By CREDIT_CARD_FIRST_NAME = By.id("creditCardFirstName")
    private static final By CREDIT_CARD_LAST_NAME = By.id("creditCardLastName")
    private static final By LOOKUP_CONFIRMATION_NUMBER = By.id("confirmationNumber")
    private static final By LOOKUP_CONFIRMATION_NUMBER_FIRST_NAME = By.id("confirmationNumberFirstName")
    private static final By LOOKUP_CONFIRMATION_NUMBER_LAST_NAME = By.id("confirmationNumberLastName")
    private final static By SUBMIT_BUTTON = By.id("pnrFriendlyLookup_check_form_submitButton")
    private static final By SWACONTINUE_SUBMIT_BUTTON = By.id("checkInForFlightButton")
    private final static By SUBMIT_BUTTON_CANCEL_MODAL = By.id("continueCancelation")

    private final static By CREDIT_CARD_RADIO_BUTTON = By.id("pnrFriendlyLookup_option_creditCard")
    private final static By UM_ICON = By.className("unaccompanied_minor_icon")
    private final static By PRINT_AND_SHARE = By.className("swa_printAndShare_link_expandable")
    private final static By EMAIL_LINK = By.className("swa_feature_pnrEmailItineraryLink_emailLink")
    private final static By EMAIL_ADDRESS_BOX = By.id("swa_feature_pnrLookup_emailItinerary_emailField_1")
    private final static By CONFIRMATION_POPUP = By.className("swa_errors_okMessage_body")
    private final static By PNR_ON_DISAMBIGUATION_TABLE = By.className("swa_text_flightNumber")
    private final static By DISAMBIGUATION_TABLE_HEADER = By.className("swa_feature_flightStatus_results_table_head_bottom")
    private final static By AIR_MODAL_ERROR = By.cssSelector(".trip_itinerary_detail_table_error_message")
    private static final By HOTEL_MODAL_ERROR = By.cssSelector(".itinerary_container.hotel_itinerary_container_with_vertical_label")
    private static final By CAR_MODAL_ERROR = By.cssSelector(".itinerary_container.car_itinerary_container_with_vertical_label")
    private static final String AIR_MODAL_ERROR_MESSAGE = "We are currently unable to complete your request. If this is the first time you have seen this message, please go back and try again later. If you continue to have difficulties, please contact a Southwest Airlines Customer Representative for assistance at 1-800-I-FLY-SWA (1-800-435-9792)."
    private static final String HOTEL_MODAL_ERROR_MESSAGE = "We are currently unable to complete your request. If this is the first time you have seen this message, please go back and try again later. If you continue to have difficulties, please contact a Southwest Airlines Customer Representative for assistance at 1-800-I-FLY-SWA (1-800-435-9792)."
    private static final String CAR_MODAL_ERROR_MESSAGE = "We are currently unable to complete your request. If this is the first time you have seen this message, please go back and try again later. If you continue to have difficulties, please contact a Southwest Airlines Customer Representative for assistance at 1-800-I-FLY-SWA (1-800-435-9792)."
    private static final By FIRST_NAME_FIELD = By.id("firstName")
    private static final By LAST_NAME_FIELD = By.id("lastName")
    private static final By CONTINUE_SUBMIT_BUTTON = By.id("submitButton")
    private static final By VIEW_TRAVEL_FOUNDS_LINK = By.cssSelector("a[title='View Travel Funds']")
    private static final String ASSOCIATED_PRODUCTS_MODAL_DESCRIPTION = "Associated Products Modal"
    private static final By ASSOCIATED_PRODUCTS_MODAL = By.id("trip_products_associated_with_air_being_cancelled_modal")
    private static final By CHANGE_OPTION = By.id("changeReservationLink")
    private static final By RESEND_RECEIPT_OPTION = By.cssSelector("a.swa_printAndShare_resendReceiptLink")
    private static final By DEPART_RETURN_OPTION = By.cssSelector("div.confirmDepartOrReturn")
    private static final By FARE_PRODUCT_OPTION = By.id("fareProductName")
    private static final By EARLY_BIRD_OPTION = By.id("a.earlybirdCheckIn_button")
    private static final By CHECK_IN_OPTION = By.id("checkInForFlightButton")
    private static final By SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId_")
    private static final By OUTBOUND_SEAT_SELECTION_UNAVAILABLE_TEXT = By.id("seatSelectionUnavailableTextId0")
    private static final By INBOUND_SEAT_SELECTION_UNAVAILABLE_TEXT = By.id("seatSelectionUnavailableTextId1")
    private static final String SEAT_SELECTION_UNAVAILABLE_TEXT = "Seat selection unavailable. Please try again later"
    private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER = By.cssSelector("table#airItinerarydepart")
    private static final By STOPS_COUNT_CONTAINER = By.cssSelector("#airItinerarydepart .stops")
    private static final By VIEW_MORE_HOTELS_BUTTON = By.className("view_search_alternate_footer_submit")
    private static final String HOTEL_ID_HREF = "hotelIdentifier="
	private static final By HOTEL_DYNAMIC_CROSS_SELL = By.id("view_search_results")
    private static final By ADD_RAPID_REWARDS_NUMBER_LINK = By.cssSelector(".passenger_row_rr_number a")
    private static final By ITINERARY_NOTE = By.className("itineraryNote")
    private static final By RAPID_REWARDS_NUMBER = By.cssSelector(".passenger_row_rr_number")
    private static final By CONFIRMATION_NUMBER =  By.className("confirmation_number")
    private static final By PAGE_TITLE =  By.className("swa_page_title")
    private static final By TRIP_DETAILS_HEADER_DATE = By.cssSelector("#tripDetailsHeader .calendarDayOfWeek")
    private static final By RETRIEVE_TRIP_PRODUCT = By.className("trip_retrieved_product")
    private static final By CHECK_IN_BUTTON = By.id("checkInForFlightButton")
    private static final By CONFIRMATION_NOTE = By.cssSelector("#swa_page_header p")
    private static final String CONFIRMATION_NOTE_MESSAGE = "This is your confirmation and itinerary.  Please print this page."
    private static final By DISABILITY_BUTTON = By.id("disability_button1")
    private static final By PASSENGER_ASSIST = By.className("passengerAssistanceRequested")
    private static final String ASSISTANCE_REQUESTED_TEXT = "Assistance Requested. Please Inform Southwest Airlines Representatives upon arrival at the airport."
    private static final By OUTBOUND_FARE_TYPE = By.cssSelector("#airItinerarydepart .fareProductName")

    Data data
    ItineraryData itineraryData
    AccountBar accountBar
    ScenarioState scenarioState
    StopLogicInfo stopLogicInfo
    HotelItineraryData hotelItineraryData
    AirPriceContent airPriceContent
    SelectFlightsPageData selectFlightsPageData
    Flow flow
    Itinerary itinerary
    PassengerData passengerData
    RRContactInformation rrContactInformation


    private WebElement outboundSeatSelectionButton() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
        waitForElement(By.id("seatSelectionButtonId_${airReservation.adultPnr}_0"))
    }

    private WebElement inboundSeatSelectionButton() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
        waitForElement(By.id("seatSelectionButtonId_${airReservation.adultPnr}_1"))
    }

    private WebElement findOutBoundSeatSelectionText() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
        waitForElement(By.id("seatSelectionTextId_${airReservation.adultPnr}_0"))
    }

    void clickSeatSelectionButton(String outboundInbound) {
        String buttonName = SEAT_SELECTION_BUTTON;
        if (outboundInbound.equalsIgnoreCase("outbound")){
            outboundSeatSelectionButton().click()
        } else if(outboundInbound.equalsIgnoreCase("inbound")){
            inboundSeatSelectionButton().click()
        } else {
            fail("Message did not find ${outboundInbound}")
        }
    }

    void verifySeatSelectionUnavailableVerbiage(String outBoundInBound) {
        sleep 3000
        if (outBoundInBound.equalsIgnoreCase("outbound")){
            WebElement element = waitForElement(OUTBOUND_SEAT_SELECTION_UNAVAILABLE_TEXT)
            element.text.shouldContain SEAT_SELECTION_UNAVAILABLE_TEXT
            element.getAttribute('style').shouldContain "inline"
        } else if(outBoundInBound.equalsIgnoreCase("inbound")){
            WebElement element = waitForElement(INBOUND_SEAT_SELECTION_UNAVAILABLE_TEXT)
            element.text.shouldContain SEAT_SELECTION_UNAVAILABLE_TEXT
            element.getAttribute('style').shouldContain "inline"
        } else {
            fail("Message did not find ${outBoundInBound}")
        }
    }



    private WebElement flightItinerary() {
        waitForElement(FLIGHT_ITINERARY)
    }

    ViewReservationPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, '/flight/lookup-air-reservation.html')
        GrooBe.activate()
    }

    def retrieveItineraryByPnr(String pnr, String pnr_firstName, String pnr_lastName) {
        enterPnrAndSubmit(pnr, pnr_firstName, pnr_lastName)
    }

    def retrieveItineraryByPnrForChangeReservation(String pnr, String pnr_firstName, String pnr_lastName) {
        enterPnrAndSubmitForChangeReservation(pnr, pnr_firstName, pnr_lastName)
    }
    String getRelativePath() {
        return "/flight/lookup-air-reservation.html"
    }

    def verifyChangeTripBreadcrumb() {
        verifyPageBreadcrumb(BREADCRUMB_IDS["ChangeTrip"])
    }

    def associatedProductsModalIsNotPresent() {
        verifyElementNotPresent(ASSOCIATED_PRODUCTS_MODAL_DESCRIPTION,ASSOCIATED_PRODUCTS_MODAL)
    }

    def itineraryDisplayed(String firstName, String lastName) {
        WebElement fullName = waitForElement(FIRST_ADULT_PASSENGER_NAME)
        firstName.equalsIgnoreCase(fullName.text.split(" ").first()) shouldBe true, "Customer first name does not match "
        lastName.equalsIgnoreCase(fullName.text.split(" ").last()) shouldBe true, "Customer last name does not match "
    }

    def verifyRrNumber(String expectedRrNumber) {
        String actualRrNumber = waitForElement(RAPID_REWARDS_NUMBER).text
        withoutLeadingZeros(expectedRrNumber).shouldBeEqualTo withoutLeadingZeros(actualRrNumber)
    }

    private def withoutLeadingZeros(String str) {
        return str?.replaceFirst(/^0+/, "")
    }

    def open() {
        super.open()
    }

    def verifyItineraryMessage(String msg) {
        switch (msg) {

            case SOUTHWEST_ONLY_ITINERARY:
                waitForElement(ITINERARY_MESSAGE).getText().shouldContain(ITINERARY_VERIFICATION_MESSAGE)
                break

            case WRONG_CREDIT_CARD_LOOKUP:
                getOops().shouldContain(CREDIT_CARD_ITINERARY_NOT_FOUND_MESSAGE)
                break

            case AIRTRAN_DISCLAIMER:
                waitForElement(AIRTRAN_DISCLAIMER_ID)
                break

            default:
                fail("Message did not return ${msg}")
        }
    }

    void clickAddRapidRewardsNumber() {
        def currentTitle = getTitle()
        waitForElement(BREADCRUMB_ADD_RAPID_REWARDS_NUMBER_LINK).click()
        waitForPageTitleToChangeOrOops(currentTitle)
    }

    def internationalMessageSearchFlightToChange() {
        waitForElement(INTERNATIONAL_TEXT_ON_CHANGE_PAGE).text.shouldContain INTERNATIONAL_TEXT,
                "${INTERNATIONAL_TEXT} not found on Change Trip page"
    }

    def retrieveItineraryByCreditCardNumber(String firstNameInput, String lastNameInput, String from, String creditCardNumber = data.getRandomCreditCard().getWholeNumber(), Date departureData = null) {
        clickOnCardRadioButton()
        fillInCreditCardFields(creditCardNumber, from, firstNameInput, lastNameInput, departureData)
        def currentUrl = getCurrentUrl()
        clickOnContinueButton()
        waitForUrlToChangeOrOops(currentUrl)
    }

    def retrieveItineraryWithAnUnassociatedCreditCardNumber(String firstNameInput, String lastNameInput, String from, String creditCardNumber = data.getRandomCreditCard().getWholeNumber(), Date departureData = null) {
        clickOnCardRadioButton()
        fillInCreditCardFields(creditCardNumber, from, firstNameInput, lastNameInput, departureData)
        clickOnContinueButton()
    }

    def clickOnContinueButton() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def clickOnContinueButtonOnModal() {
        waitForElement(SUBMIT_BUTTON_CANCEL_MODAL).click()
    }

    def clickOnCardRadioButton() {
        waitForElement(CREDIT_CARD_RADIO_BUTTON).click()
    }

    private fillInCreditCardFields(String creditCardNumber, String from, String firstNameInput, String lastNameInput, Date departureData) {
        fillIn(CREDIT_CARD_NUMBER, creditCardNumber)
        fillIn(ORIGIN_AIRPORT_DISPLAYED, from)
        fillIn(CREDIT_CARD_FIRST_NAME, firstNameInput)
        fillIn(CREDIT_CARD_LAST_NAME, lastNameInput)
        if (departureData) {
            String dayDepartureData = departureData.format(DAY_OF_WEEK_DATE_FORMAT)
            new Select(waitForElement(OUTBOUND_DATE)).selectByValue(dayDepartureData)
        }
    }

    def verifyEBIndicator() {
        verifyElementPresent("Early Bird Indicator On View Itinerary Page", waitForElement(EARLY_BIRD_ICON))
    }

    def verifyEBIndicatorText() {
        waitForElement(EARLY_BIRD_ICON).getAttribute("title").shouldContain "EarlyBird Check-In"
    }

    def verifyBusinessSelectIconIsNotPresent() {
        isBusinessSelectIconDisplayed().shouldBe false, 'The Business Select icon should not be present'
    }

    void verifyFareClassLink(String verifyFareClassLink) {
        def rows = waitForElements(By.cssSelector("a.headerText"))
        boolean found = false
        String linkToTest = (verifyFareClassLink == "BusinessSelect") ? "Business Select" : verifyFareClassLink
        for (row in rows) {
            if (row.getText().equals(linkToTest)) {
                found = true
                break
            }
        }
        if (!found) fail "${verifyFareClassLink} not found"
    }

    void clickBusinessSelectLink() {
        waitForElement(By.cssSelector("a.headerText")).click()
        waitForPageTitleToChangeOrOops(getTitle())
        def handle = getWindowHandles()
        switchTo().window(handle.toArray()[1])
    }

    void clickCancelReservationLink() {
        waitForElement(CANCEL_RESERVATION_LINK_ID).click()
    }

    void clickCancelReservationBreadcrumb() {
        waitForElement(CANCEL_RESERVATION_BREAD_CRUMB).click()
    }

    void verifyFareInformationPage() {
        //        waitForElement(By.cssSelector("h1.swa_page_title")).getText().shouldContain "Fare Information" ,"Fair Information page is not present"
        waitForElement(By.cssSelector("h1.swa_page_title")).getText().shouldContain "Fare Information", "Fair Information page is not present"
    }

    void verifyBusinessSelectText() {
        waitForElement(By.cssSelector("div.fareProductName a.headerText")).getText().shouldContain "Business Select"
    }

    def verifyUnableToRetrievePNROOPSMessage() {
        verifyOopsMessages(fetchOopsMessageRetrievingReservationWithInvalidPNR())
        verifyElementPresent("Air Tran Link", AIRTRAN_LINK)
    }

    def enterPnrAndSubmit(String pnr, String first, String last) {
        String Url = getCurrentUrl()
        fillIn(LOOKUP_CONFIRMATION_NUMBER, pnr)
        fillIn(LOOKUP_CONFIRMATION_NUMBER_FIRST_NAME, first)
        fillIn(LOOKUP_CONFIRMATION_NUMBER_LAST_NAME, last)
        waitForElement(SUBMIT_BUTTON).click()
        waitForUrlToChangeOrOops(Url)
    }

    def enterPnr(String pnr, String first, String last) {
        String Url = getCurrentUrl()
        fillIn(LOOKUP_CONFIRMATION_NUMBER, pnr)
        fillIn(LOOKUP_CONFIRMATION_NUMBER_FIRST_NAME, first)
        fillIn(LOOKUP_CONFIRMATION_NUMBER_LAST_NAME, last)
    }

    def submitRetrieveAirForm(){
        waitForElement(SUBMIT_BUTTON).click()
    }

    def enterPnrAndSubmitForChangeReservation(String pnr, String first, String last) {
        String url = getCurrentUrl()
        fillIn(LOOKUP_CONFIRMATION_NUMBER, pnr)
        fillIn(FIRST_NAME_FIELD, first)
        fillIn(LAST_NAME_FIELD, last)
        waitForElement(CONTINUE_SUBMIT_BUTTON).click()
        waitForUrlToChangeOrOops(url)
    }
    boolean isDefaultTripName() {
        return waitForElement(TRIP_ITINERARY_TITLE_CLASS).getText().matches(TripManagement.DEFAULT_TRIP_NAME)
    }

    def verifyTripNameIsFormattedByDefault(String tripName) {
        waitForElement(TRIP_ITINERARY_TITLE_CLASS).getText().shouldContain tripName, "Default trip name is not correctly formatted"
    }

    boolean isRenameButtonDisplayed() {
         return isElementDisplayed(RENAME_TRIP_BUTTON_CLASS)
    }

    /**
     * WARNING: the NO_NAME value for name is to simulate an empty string
     */
    void renameTrip(String name) {
        waitForElement(RENAME_TRIP_BUTTON_CLASS).click()
        if (name == "NO_NAME") {
            waitForElement(TRIP_NAME_FIELD).sendKeys DELETE_EXISTING
        } else {
            waitForElement(TRIP_NAME_FIELD).sendKeys DELETE_EXISTING + name
        }
        waitForElement(SAVE_NEW_TRIP_NAME_BUTTON).click()
        checkSomethingServed()
    }

    boolean isAirProductDetailsDisplayed() {
        return isElementPresent(By.id(AIR_ITINERARY_DEPART_ID), 10)
    }

    boolean isAirCrossSellDisplayed() {
        return isElementDisplayed(By.cssSelector(RETRIEVE_ANOTHER_AIR_SECTION_CLASS))
    }

    boolean isCarCrossSellDisplayed() {
        return isElementDisplayed(By.cssSelector(RETRIEVE_ANOTHER_CAR_SECTION_CLASS))
    }

    boolean isHotelCrossSellDisplayed() {
        return isElementDisplayed(By.cssSelector(RETRIEVE_ANOTHER_HOTEL_SECTION_CLASS))
    }

    boolean isAssociatedItemsDisplayed() {
        return isElementDisplayed(By.cssSelector(TRIP_ASSOCIATED_PRODUCT_VERBIAGE_CLASS))
    }

    boolean isDisambiguationPage() {
        return isElementDisplayed(DISAMBIGUATION_TABLE)
    }

    boolean isAddEarlyBirdCheckInButtonDisplayed() {
        return isElementDisplayed(By.cssSelector("a.earlybirdCheckIn_button"))
    }

    boolean isPassengerTitleDisplayed() {
        return isElementDisplayed(By.cssSelector("td.pax_name_header"))
    }
    boolean isItemsAssociatedVerbiageModalDisplayed() {
        return isElementDisplayed(By.cssSelector("p.cancellation_modal_associated_products_title"))
    }

    boolean isHotelInfoDisplayed() {
        return isElementDisplayed(By.cssSelector(HOTEL_ITINERARY_CONTAINER_CLASS))
    }

    boolean isCancelationModalHotelItemPresent() {
        return isElementDisplayed(By.cssSelector("div.hotel_itinerary_container_with_image"))
    }

    boolean isCancelationModalAirItemPresent() {
        return isElementDisplayed(By.xpath("//div[@class = 'popup_content_container']/div[@class='itineraries_header air_itinerary_container_with_image']"))
    }

    def verifyItemsAssociatedVerbiageDisplayed() {
        isAssociatedItemsDisplayed().shouldBe true, 'Items associated with this Trip Verbiage is visible'
    }

    void verifyAssociatedItemsNotDisplayed() {
        isAssociatedItemsDisplayed().shouldBe false, "The Associated Items should be not visible"
    }

    def verifyItemsAssociatedVerbiageModal()    {
        isItemsAssociatedVerbiageModalDisplayed().shouldBe true, 'Items associated with this Verbiage on cancel modal is visible'
    }

    def verifyCarProductAssociatedInfo() {
        WebElement carItineraryContainer = waitForElement(CAR_ITINERARY_CONTAINER_CLASS)
        carItineraryContainer.findElement(By.cssSelector(CAR_ITINERARY_RENTAL_INFO_CLASS)).isDisplayed().shouldBe true, 'Car Itinerary Rental Info is visible'
        carItineraryContainer.findElement(By.cssSelector(CAR_ITINERARY_DATE_CLASS)).isDisplayed().shouldBe true, 'Car Itinerary Dates is visible'
        carItineraryContainer.findElement(By.cssSelector(CAR_ITINERARY_CONFIRMATION_NUMBER_CLASS)).isDisplayed().shouldBe true, 'Car Itinerary Confirmation number is visible'
    }

    def verifyAirInformationOnCancelationModal() {
        WebElement popUpContentContainer = waitForElement(POPUP_CONTENT_CONTAINER_CLASS)
        popUpContentContainer.findElement(By.xpath(POPUP_FLIGHT_ITINERARY_XPATH)).isDisplayed().shouldBe true, 'Cancel Modal Air Itinerary is visible'
        popUpContentContainer.findElement(By.xpath(POPUP_FLIGHT_DATES_XPATH)).isDisplayed().shouldBe true, 'Cancel Modal Flight Dates is visible'
        popUpContentContainer.findElement(By.xpath(POPUP_FLIGHT_CONFIRMATION_NUMBER_XPATH)).isDisplayed().shouldBe true, 'Cancel Modal Flight Confirmation Number is visible'
        popUpContentContainer.findElement(By.id(POPUP_FLIGHT_CONTINUE_CANCELATION_ID)).isDisplayed().shouldBe true, 'Cancel Modal Continue button is visible'
    }

    def verifyCarProductOnCancelationModal() {
        WebElement popUpContentContainer = waitForElement(POPUP_CONTENT_CONTAINER_CLASS)
        popUpContentContainer.findElement(By.cssSelector(POPUP_CAR_ITINERARY_CLASS)).isDisplayed().shouldBe true, 'Cancel Modal Car Itinerary Rental Info is visible'
        popUpContentContainer.findElement(By.cssSelector(POPUP_CAR_DATES_CLASS)).isDisplayed().shouldBe true, 'Cancel Modal Car Itinerary Date is visible'
        popUpContentContainer.findElement(By.cssSelector(POPUP_CAR_CONFIRMATION_NUMBER_CLASS)).isDisplayed().shouldBe true, 'Cancel Modal Car Itinerary Confirmation number is visible'
    }

     def verifyHotelProductAssociatedItem() {
        WebElement hotelItineraryContainer = waitForElement(HOTEL_ITINERARY_CONTAINER_CLASS)
        hotelItineraryContainer.findElement(By.cssSelector(HOTEL_ITINERARY_HEADER_CLASS)).isDisplayed().shouldBe true, 'Hotel Itinerary Rental Info is visible'
        hotelItineraryContainer.findElement(By.cssSelector(HOTEL_ITINERARY_DATES_CLASS)).isDisplayed().shouldBe true, 'Hotel Itinerary Dates is visible'
        hotelItineraryContainer.findElement(By.id(HOTEL_ITINERARY_CONFIRMATION_NUMBER_ID)).isDisplayed().shouldBe true, 'Hotel Itinerary Confirmation number is visible'
    }

    def verifyCancelationModalHotelItemIsNotPresent() {
        isCancelationModalHotelItemPresent().shouldBe false, 'Cancellation Modal Hotel Item should not be visible'
    }

    def verifyCancelationModalAirItemIsNotPresent() {
        isCancelationModalAirItemPresent().shouldBe false, 'Cancellation Modal Air Item should not be visible'
    }

    def verifyIsHotelInfoNotDisplayed() {
        isHotelInfoDisplayed().shouldBe false, 'Associate Hotel Information should not be visible'
    }

    def verifyAssociatedItemsSortedByEarliestDate() {
        Date mostRecentDate = null //for comparison reasons
        List<WebElement> products = waitForElement(TRIP_ITINERARY_PRODUCTS_CLASS).findElements(By.cssSelector(ITINERARY_HEADER_DATA_CLASS))
        for (int i = 1; i < products.size(); i++) {
            WebElement product = products.get(i)
            Date itineraryDate = getItineraryDate(product)
            if(mostRecentDate == null) {
                mostRecentDate = itineraryDate
            }else {
                if(itineraryDate.before(mostRecentDate)){
                    fail('The associated items should be sorted by the earliest date')
                }
                else{
                    mostRecentDate = itineraryDate
                }
            }
        }
    }

    private Date getItineraryDate(WebElement product) {
        List<WebElement> productItinerary = product.findElements(By.cssSelector('p'))
        String itineraryDateAsString = productItinerary.get(1).getText()
        return new SimpleDateFormat('MM/dd/yy').parse(itineraryDateAsString.split("-")[0])
    }

    def verifyAssociatedItemsCollapsed() {
        List<WebElement> associatedItems = waitForElement(TRIP_ITINERARY_PRODUCTS_CLASS).findElements(TRIP_ITINERARY_DETAIL_TABLE_CONTAINER_CLASS)
        for (int i = 1; i < associatedItems.size(); i++) {
            WebElement associatedItem = associatedItems.get(i)
            WebElement plusButton = associatedItem.findElement(By.id(SHOW_SUB_SECTION_LINK_ID))
            if(!plusButton.isDisplayed()){
                fail('The associated items should be collapsed')
            }
        }
    }

    boolean verifyPassengersName() {
        for (WebElement paxRow in waitForElement(AIR_ITINERARY_PASSENGERS_TABLE).findElements(PASSENGER_ROW_NAME)) {
            boolean paxFound = false
            String paxName
            for (Passenger pax in scenarioState.lastAirReservation.passengers) {
                paxName = pax.firstName + " " + pax.lastName
                paxFound = paxName.equalsIgnoreCase(paxRow.text)
                if (paxFound) {
                    break
                }
            }
            paxFound.shouldBe true, "The passenger $paxName should be in the list"
        }
    }

    boolean isBusinessSelectIconDisplayed() {
        isElementDisplayed(By.cssSelector("img.business_select_icon"))
    }

    boolean isBusinessSelectToolTipDisplayed() {
        waitForElement(BUSINESS_SELECT_ICON).getAttribute("title").isEmpty()
    }

    void verifyInvalidCreditCardOOPSMessage() {
        verifyUnableToRetrievePNROOPSMessage()
        String oopsText = getOopsElement().getText()
        def oopsMessages = [
            'The departure city for the reservation is entered correctly.',
            'The departure date for the reservation is entered correctly.',
            'The credit card number is entered correctly.'
        ]
        verifyOopsMessages(oopsMessages)
        oopsText.shouldContain "The reservation was made with", "The airtran bullet was not added"
    }

    void verifyInvalidPnrOopsMessage() {
        shouldShowOopsMessage("We were unable to retrieve your reservation from our database. Verify the following:")
        String oopsText = getOopsElement().getText()
        oopsText.shouldNotContain "We're sorry,", "We're sorry text is still present at the top of the Oops message."
        oopsText.shouldNotContain ", airtran.com,", "Third bullet in the Oops message still contains the airtran.com text."
        oopsText.shouldContain "Travel Agencies cannot be accessed on southwest.com.", "Third bullet in the Oops message still contains the airtran.com text."
        oopsText.shouldNotContain "Travel Agencies cannot be accessed online", "Third bullet text at end was NOT removed/changed"
        waitForElement(AIRTRAN_LINK).getText().shouldEqual "airtran.com"
    }

    void verifyEmptyCreditCardOOPSMessage() {
        waitForOops()
        String oopsText = getOopsElement().getText()
        oopsText.shouldNotContain "We're sorry,", "We're sorry text is still present at the top of the Oops message."
        def oopsMessages = [
            "The passenger's first name was not entered.",
            "The passenger's last name was not entered.",
            "No departure airport selected for the outbound flight.",
            "The credit card number was not entered."
            ]
        verifyOopsMessages(oopsMessages)
    }

    void verifyEmptyPNROOPSMessage() {
        waitForOops()
        String oopsText = getOopsElement().getText()
        oopsText.shouldNotContain "We're sorry,", "We're sorry text is still present at the top of the Oops message."
        def oopsMessages = [
            "The passenger's first name was not entered.",
            "The passenger's last name was not entered.",
            "The confirmation number was not entered."
            ]
        verifyOopsMessages(oopsMessages)
    }

    def verifyTripName(String tripName) {
        waitForElement(TRIP_ITINERARY_TITLE_CLASS).text.shouldBeEqual tripName.trim(), "The trip name in the View Reservation Page should be: $tripName"
    }

    def verifyRenameButtonEnable() {
        waitForElement(RENAME_TRIP_BUTTON_CLASS).isDisplayed().shouldBe true, 'The Rename Trip Button should be visible'
        waitForElement(RENAME_TRIP_BUTTON_CLASS).isEnabled().shouldBe true, 'The Rename Trip Button should be enable'
    }

    def verifyDefaultTripName() {
        isDefaultTripName().shouldBe true, 'The trip name in the View Reservation Page should be: MM/dd/yy - Arrival City Name'
    }

    def verifyRenameButtonNotDisplayed() {
        isRenameButtonDisplayed().shouldBe false, 'The Rename Trip Button should not be visible'
    }

    def verifyAirProductExpandedDetails() {
        isAirProductDetailsDisplayed().shouldBe true, "The Air product details should be visible"
    }

    void verifyCarProductCrossSellNotDisplayed() {
        isCarCrossSellDisplayed().shouldBe false, "The Car Cross Sell Section should be not visible"
    }

    void verifyHotelProductCrossSellNotDisplayed() {
        isHotelCrossSellDisplayed().shouldBe false, "The Hotel Cross Sell Section should be not visible"
    }

    void verifyCarProductCrossSellDisplayed() {
        isCarCrossSellDisplayed().shouldBe true, "The Car Cross Sell Section should be visible"
    }

    void verifyHotelProductCrossSellDisplayed() {
        isHotelCrossSellDisplayed().shouldBe true, "The Hotel Cross Sell Section should be visible"
    }
    void verifyINotSeeDisambiguationPage() {
        isDisambiguationPage().shouldBe false, "I should be not see Disambiguation Page"
    }

    void verifyAirProductCrossSellNotDisplayed() {
        isAirCrossSellDisplayed().shouldBe false, "The Air Cross Sell Section should be not visible"
    }

    void verifyPassengerTitleDisplayed() {
        isPassengerTitleDisplayed().shouldBe true, "The Passenger(s) title should be visible"
    }

    boolean isEarlyBirdIconDisplayed() {
        isElementDisplayed(EARLY_BIRD_ICON)
    }

    boolean isEarlyBirdToolTipDisplayed() {
        waitForElement(EARLY_BIRD_ICON).getAttribute("title").isEmpty()
    }

    boolean isAListIconDisplayed() {
        isElementDisplayed(A_LIST_ICON)
    }

    boolean isAListToolTipDisplayed() {
        waitForElement(A_LIST_ICON).getAttribute("title").isEmpty()
    }

    boolean isItineraryDatesCorrectlyFormatted() {
        waitForElement(FLIGHT_DATES).getText().matches(TripManagement.ITINERARY_DATES)
    }

    def isOpenJawCitiesCorrectlyDisplayed() {
       String itineraryCities = waitForElement(FLIGHT_ITINERARY).getText()

       String departureCity = itineraryCities?.split(" to ")[0].trim()
       String expectedDepartureCity = Station.valueOf(Station.class, itineraryData.departureStation).getCityFormat()

       departureCity.shouldBeEqual expectedDepartureCity, "The Departure city is not correctly formatted, should be ${expectedDepartureCity}"

       itineraryCities.shouldContain itineraryData.departureStation, "The departure station should be "+itineraryData.departureStation
       itineraryCities.shouldContain itineraryData.arrivalStation, "The arrival station should be "+itineraryData.arrivalStation
       itineraryCities.shouldContain itineraryData.returnStation, "The return station should be "+itineraryData.returnStation
    }

    void verifyISeeDisambiguationPage() {
        isDisambiguationPage().shouldBe true, "I should be see the Disambiguation Page"
    }

    void clickOnTheFirstDetailsLinkOnDisambiguation() {
        List detailsLink = getDetailsLinksOnDisambiguation()
        if(detailsLink.size() > 0) {
            WebElement firstLink = detailsLink.get(0)
            firstLink.click()
            checkSomethingServed()
        }else {
            fail("There is no link to click")
        }

    }

    List<WebElement> getDetailsLinksOnDisambiguation() {
        def linkList = []
        for (WebElement link in waitForElement(DISAMBIGUATION_TABLE).findElements(By.cssSelector(DISAMBIGUATION_TABLE_FLIGHT_DETAILS_LINK))) {
            linkList.add(link)
        }
        return linkList
    }

     void verifySeatSelectionVerbiage() {
        findOutBoundSeatSelectionText().text.shouldContain SEAT_SELECTION_TEXT
    }

    void clickSeatSelectionButton() {
        outboundSeatSelectionButton().click()
    }

    def verifyReservationHasBeenCancelledOopsMessage() {
        getExpectedOopsMessage().shouldContain RESERVATION_CANCELLED_MESSAGE, "Did not find expected oops message ${RESERVATION_CANCELLED_MESSAGE}"
    }

    void verifyUnaccompainedMinorIconIsDisplayed(){
        isElementDisplayed(UM_ICON).shouldBe true, "I should be see the unaccompained minor icon on the view reservation page"
    }

    void verifyUnaccompainedMinorIconTooltip(){
        waitForElement(UM_ICON).getAttribute("title").shouldContain UM_ICON_TITLE, "The UM icon title should be " + UM_ICON_TITLE
    }

    def sendItineraryViaEmail(){
        waitForElement(PRINT_AND_SHARE).click()
        waitForElement(EMAIL_LINK).click()
        waitForElement(EMAIL_ADDRESS_BOX).sendKeys("test@example.com")
        findElement(By.id("itineraryEmailsForm")).findElement(By.className("swa_buttons_submitButton")).click()
    }

    def verifyConfirmationPopup(){
        waitForElement(CONFIRMATION_POPUP, true, 45).getText().shouldContain "Your itinerary e-mails have been sent to the following addresses"
    }

    def verifyAirReservations() {
        List<String> pnrList = []
        for(WebElement pnr in waitForElement(DISAMBIGUATION_TABLE).findElements(PNR_ON_DISAMBIGUATION_TABLE)) {
            pnrList.add(pnr.getText())
        }
        pnrList.contains(scenarioState.getAirReservationAt(0).adultPnr).shouldBe true
        pnrList.contains(scenarioState.getAirReservationAt(1).adultPnr).shouldBe true
    }

    def verifyDisambiguationTableColumns() {
        String disambiguationHeader = waitForElement(DISAMBIGUATION_TABLE_HEADER).getText()
        disambiguationHeader.shouldContain "Confirmation Number","The Confirmation Number column should be present"
        disambiguationHeader.shouldContain "Date","The Date column should be present"
        disambiguationHeader.shouldContain "Departure","The Departure column should be present"
        disambiguationHeader.shouldContain "Arrival","The Arrival column should be present"
        disambiguationHeader.shouldContain "Stops","The Stops column should be present"
    }

    def verifyAssociatedProductsCouldNotRetrieved() {
        waitForElement(HOTEL_MODAL_ERROR).getText().shouldContain HOTEL_MODAL_ERROR_MESSAGE, "The message " + HOTEL_MODAL_ERROR_MESSAGE + " should be present"
        waitForElement(CAR_MODAL_ERROR).getText().shouldContain CAR_MODAL_ERROR_MESSAGE, "The message " + CAR_MODAL_ERROR_MESSAGE + " should be present"
    }

    def clickOnTheViewTravelFundsLink() {
        waitForElement(VIEW_TRAVEL_FOUNDS_LINK).click()
    }

    def shouldNotShowChangeOption(){
        isElementDisplayed(CHANGE_OPTION).shouldBe false, "I should not see the Change option on the view reservation page"
    }

    def shouldNotShowResendReceiptOption(){
        isElementDisplayed(RESEND_RECEIPT_OPTION).shouldBe false, "I should not see the Resend Receipt option on the view reservation page"
    }

    def shouldNotShowDepartReturnData(){
        isElementDisplayed(DEPART_RETURN_OPTION).shouldBe false, "I should not see the Depart/Return option on the view reservation page"
    }

    def shouldNotShowFareProductData(){
        isElementDisplayed(FARE_PRODUCT_OPTION).shouldBe false, "I should not see the Fare Product data on the view reservation page"
    }

    def shouldNotShowEarlyBirdOption(){
        isElementDisplayed(EARLY_BIRD_OPTION).shouldBe false, "I should not see the Early Bird option on the view reservation page"
    }

    def shouldNotShowCheckinOption(){
        isElementDisplayed(CHECK_IN_OPTION).shouldBe false, "I should not see the Check In option on the view reservation page"
    }

    def shouldShowCheckinOption(){
        isElementDisplayed(CHECK_IN_OPTION).shouldBe true, "I should see the Check In option on the view reservation page"
    }

    def List<String> fetchOopsMessageRetrievingReservationWithInvalidPNR(){
        List<String> oopsMessage = ["We were unable to retrieve your reservation from our database. Verify the following:",
            "The confirmation number is entered correctly.",
            "The passenger name on the reservation is entered correctly.",
            "The reservation was purchased online via southwest.com or swabiz.com or mobile.southwest.com or Southwest Vacations. Purchases made through Southwest Groups, Southwest ticket counters, or other Travel Agencies cannot be accessed on southwest.com.",
            "The reservation was not made with airtran.com.",
            "If you have questions or require further assistance, please contact a Southwest Airlines Customer Representative at 1-800-I-FLY-SWA (1-800-435-9792)."]
        return oopsMessage
    }

    def verifyChangePlaneInfoOnDepartureAirItinerary(){
        String changePlaneCityCode = itineraryData.outboundConnectingStation
        stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER, changePlaneCityCode)
    }

    def verifySeatSelectionButton() {
        String pnr = scenarioState.getLastAirReservation().adultPnr
        By OUTBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId_" + pnr + "_0")
        By INBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId_" + pnr + "_1")

        (isElementDisplayed(INBOUND_SEAT_SELECTION_BUTTON) || isElementDisplayed(OUTBOUND_SEAT_SELECTION_BUTTON)).shouldBe true, "No seat selection button found"
    }

    def verifySeatSelectionButtonNotShown() {
        String pnr = scenarioState.getLastAirReservation().adultPnr
        By OUTBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId_" + pnr + "_0")
        By INBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId_" + pnr + "_1")

        verifyElementNotPresent("Inbound Seat Selection Button (seatSelectionButtonId1) ", INBOUND_SEAT_SELECTION_BUTTON);
        verifyElementNotPresent("Outbound Seat Selection Button (seatSelectionButtonId0) ", OUTBOUND_SEAT_SELECTION_BUTTON);
    }

	def clickOnViewMoreHotelsButton() {
        sleep 5000
        checkSomethingServed()
        waitForElement(VIEW_MORE_HOTELS_BUTTON).submit()
    }

    def selectHotelfromCrossSell(Integer index) {
        By HOTEL_FROM_CROSS_SELL = By.id("view_search_alternate_search_result_link_" + index)
        WebElement link = waitForElement(HOTEL_FROM_CROSS_SELL)
        hotelItineraryData.hotelId = getHotelIdFromUrl(link.getAttribute("href"))
        link.click()
    }

    public static getHotelIdFromUrl(String url) {
        url = url.substring(url.indexOf(HOTEL_ID_HREF)+HOTEL_ID_HREF.length())
        return url.substring(0, url.indexOf("&"))
    }

	def shouldSeeHotelDynamicCrossSell() {
        verifyElementPresent("view_search_results", HOTEL_DYNAMIC_CROSS_SELL)
    }

    def shouldNotSeeHotelDynamicCrossSell() {
        verifyElementNotPresent("view_search_results", HOTEL_DYNAMIC_CROSS_SELL)
    }

    def verifyAirAssociatedProductCouldNotRetrieved() {
        waitForElement(AIR_MODAL_ERROR).getText().shouldContain AIR_MODAL_ERROR_MESSAGE, "The message " + AIR_MODAL_ERROR_MESSAGE + " should be present"
    }

    def clickAddRapidRewardsNumberLink(){
        waitForElement(ADD_RAPID_REWARDS_NUMBER_LINK).click()
    }

    def verifyPrintAndShare() {
        verifyElementPresent("print_and_share", PRINT_AND_SHARE)
    }

    def verifyResendReceipt() {
        verifyElementPresent("resend_receipt", RESEND_RECEIPT_OPTION)
    }

    def verifyChangeReservationLink() {
        verifyElementPresent("change_reservation_link", CHANGE_OPTION)
    }

    def verifyCancelReservationLink() {
        verifyElementPresent("cancel_reservation_link", CANCEL_RESERVATION_LINK_ID)
    }

    def verifyItineraryNote() {
        verifyElementPresent("itinerary_note_container", ITINERARY_NOTE)
    }
    
    
    def verifyConfirmationNote() {
        verifyElementPresent("confirmation_note_container", CONFIRMATION_NOTE)
        waitForElement(CONFIRMATION_NOTE).text.shouldContain CONFIRMATION_NOTE_MESSAGE, "Confirmation note message does not match expected: ${CONFIRMATION_NOTE_MESSAGE}"
    }

    def verifyConfirmationNumber(String expectedPnr) {
        expectedPnr.shouldBe waitForElement(CONFIRMATION_NUMBER).text, "Confirmation numbers did not match."
    }

    def verifySectionTitle(Date date) {
        String expectedDateAndTitle = new SimpleDateFormat("EEEE, MMMM d, yyyy").format(date)
        String actualDateAndTitle = waitForElement(PAGE_TITLE).text
        "Your Flight for ${expectedDateAndTitle}".toString().shouldEqual actualDateAndTitle, "Page Title did not match."
    }

    def verifyDateInTripDetailsHeader(Date date) {
        String expectedDate = new SimpleDateFormat("MMM d EEE").format(date).toUpperCase()
        String actualDate = waitForElement(TRIP_DETAILS_HEADER_DATE).text?.replaceAll(/\n/, " ")
        expectedDate.shouldEqual actualDate, "Dates in Trip Details do not match.  Expected -> ${expectedDate} , Actual -> ${actualDate}"
    }

    def verifyPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify view reservation page) ---- waiting for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify view reservation page) ---- waiting for fault injection"
        }
        if (!itineraryData.pnrSource) {
            waitForElement(By.className("swa_nav_globalNav_horizontal_span")).text.shouldContain "Manage Reservation"
        }
        pageVerificationErrorWrapper(RETRIEVE_TRIP_PRODUCT, PageName.VIEW_RESERVATION_PAGE)
    }

    def verifyHideDetailsExpanded() {
        waitForElement(By.id("hideSubSectionLink")).getAttribute("style").shouldBe "display: inline;", "Hide details was not expanded"
    }

    def verifyStationInDetails() {
        String flightItinerary = waitForElement(FLIGHT_ITINERARY).text
        flightItinerary.shouldContain itineraryData.departureStation, "Departure station did not match departure station from the reservation"
        flightItinerary.shouldContain itineraryData.arrivalStation, "Arrival station did not match arrival station from the the reservation"
    }

    def verifyDisabilityLinkPresent() {
        for (int i= 1; i<=passengerData.adults.size(); i++){
            waitForElement(By.id("disability_button${i}"), false).shouldNotBe null, "Desability link for passenger ${i} was not visible in the view reservation page"
        }
    }

    public verifySeniorPassengerNameOnReservation() {
        String seniorPassengerName = waitForElement(FIRST_ADULT_PASSENGER_NAME).text
        String fullName = passengerData.getSeniors()[0].getFirstName().toUpperCase() + " " + passengerData.getSeniors()[0].getLastName().toUpperCase()
        seniorPassengerName.toUpperCase().shouldBe fullName, "Senior Passenger Full name did not match"
    }

    def verifyCheckInButtonDisplayed() {
        waitForElement(CHECK_IN_BUTTON).isDisplayed().shouldBe true, "Checkin button was not displayed"
    }

    def verifyPassengerAssistanceRequested() {
        isElementPresent(PASSENGER_ASSIST).shouldBe true, "Passenger Assistance Requested message was not present in the page"
    }

    def verifyAddEditDisabilitiesButtonIsPresent() {
        waitForElement(DISABILITY_BUTTON).getAttribute("value").shouldBe "Add/Edit Disability Options", "Add/Edit Disability Options Button was not present in ViewReservation page"
    }
    
    def verifyAssistanceRequestedText(){
        waitForElement(PASSENGER_ASSIST).text.shouldContain ASSISTANCE_REQUESTED_TEXT,
                "Passenger Assistance Requested text not present on View Reservation page"
    }

    def verifySeniorFareType() {
        waitForElement(OUTBOUND_FARE_TYPE)?.getText().replaceAll(" ", "").shouldBe itineraryData.seniorDepartingFlight_fareClass, "Senior fare type did not mach the selected one for outbound in ViewReservation page"
    }
    //added by sruthi
    def SWAretrieveItineraryByPnr() {
        waitForElement(SWACONTINUE_SUBMIT_BUTTON).click()
    }

}
