package pages

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.Route
import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import state.Flow
import state.CurrentHotelPNR;
import util.HotelItineraryData
import domain.HotelReservation
import fixture.stubs.DynaStubsIntegration
import org.joda.time.LocalDate

class SearchHotelsPage extends BasePage {

    private static final By CONFIRMATION_NUMBER_TEXT_FIELD = By.id("confirmationNumber")
    private static final By CHECK_IN_DATE_CALENDAR = By.id("lookupDate")
    private static final By FIRST_NAME_TEXT_FIELD = By.id("firstName")
    private static final By LAST_NAME_TEXT_FIELD = By.id("lastName")
    private static final By SUBMIT_BUTTON = By.id("submitButton")

    private static final By CAR_HOTEL_CANCELLATION_LINK = By.className("carHotelCancellationLink")
    private static final By SELECT_HOTEL_BUTTON = By.className("submitButton")
    private static final By AVAILABLE_HOTEL_RATE = By.xpath("//table[@id='basic_room_info_table']//a[contains(text(),'Select')]")

    private static final By RETURN_DATE = By.id("returnDate")
    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By MARKET_SEARCH_KEYWORD = By.id("marketSearchKeyword")
    private static final By VISIBLE_SUBMIT_BUTTON = By.id("visibleSubmitButton")
    private static final By CANCEL_RESERVATION_BUTTON = By.id("cancelReservationButton")
    private static final By HOTEL_CONFIRMATION_NUMBER = By.id("confirmation_itinerary_hotel_confirmation_number")
    private static final By CANCEL_HOTEL_CONFIRMATION_NUMBER = By.className("confirmation_number")
    private static final By HOTEL_LINK = By.id("globalnav_header_primary_link_hotel")
    private static final By PROMO_CODE = By.id("promoCode");
    private static final By ADULT_NUMBER = By.id("adults");
    private static final By CHILDREN_NUMBER = By.id("children");
    private static final By ROOMS_NUMBER = By.id("rooms")
    private static final By CLOSE_TO = By.id("hotelFilterCloseTo")
    private static final By AIRPORT_CLOSE_TO = By.id("airportCloseToHotel")

    private static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy";
    private static final String RETRIEVE_HOTEL_URL = "/hotels/retrieve-hotel-reservation.html"

    public static final String CONFIRMATION_LABEL_PREFIX = "Confirmation # "
    private static final By HOTEL_LIST_CONTAINER = By.id("hotelList")
    private static final By HOTEL_ROW = By.className("hotel_description_outer_container")
    private static final By HOTEL_NAME = By.className("hotelName")
    private static final By FIRST_HOTEL_NAME = By.cssSelector("#hotelName_0 a.hotel_select_hotel_name_link span.hotel_select_hotel_name_container span.hotelName")
    private static final By SECOND_HOTEL_NAME = By.cssSelector("#hotelName_1 a.hotel_select_hotel_name_link span.hotel_select_hotel_name_container span.hotelName")
    private static final By THIRD_HOTEL_NAME = By.cssSelector("#hotelName_2 a.hotel_select_hotel_name_link span.hotel_select_hotel_name_container span.hotelName")

    ConfirmationPage confirmationPage
    OutboundAndReturnDatesAndPopUp calendarPopUp

    util.Navigation navigation
    Data data

    HotelItineraryData hotelItineraryData
    Flow flow

    public SearchHotelsPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/hotels/search-hotels.html');
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    def open() {
        super.open()
        checkSomethingServed()
    }

    def fillInHotelRequiredField(boolean withMarketSearchKey = true) {
        if(withMarketSearchKey){
            enterMarketSearchKeyword(hotelItineraryData.destination)
        }

        enterCheckInDate(hotelItineraryData.checkInDate.format(DATE_FORMAT_PATTERN))
        enterCheckoutDate(hotelItineraryData.checkOutDate.format(DATE_FORMAT_PATTERN))

        if(hotelItineraryData.rooms != null) {
            chooseInDropDownByValue(ROOMS_NUMBER, hotelItineraryData.rooms)
        }

        if(hotelItineraryData.adults != null) {
            chooseInDropDownByValue(ADULT_NUMBER, hotelItineraryData.adults)
        }

        if(hotelItineraryData.children != null) {
            chooseInDropDownByValue(CHILDREN_NUMBER, hotelItineraryData.children)
        }
    }

    /**
     * Allows for selecting the close to option and if the option is an airport setting the airport name
     * @param closeTo
     * @param destination
     */
    void selectCloseTo(String closeTo, String destination) {
        this.chooseInDropDownByValue(CLOSE_TO, closeTo.toUpperCase())
        if(closeTo.equals("Airport")){
            waitForElement(AIRPORT_CLOSE_TO).sendKeys(destination)
        }


    }

    /**
     * Allows for entering the Destination of where the hotel is located to be entered
     * @param destination
     * @return
     */
    def enterDestination(String destination)
    {
        enterMarketSearchKeyword(destination)

    }

    /**
     * Allows for entering the number of rooms wanted
     * @param numberOfRooms
     * @return
     */
    def enterNumberOfRooms(int numberOfRooms )
    {
        chooseInDropDownByValue(ROOMS_NUMBER, numberOfRooms.toString())
    }

    /**
     * Allows for entering the number of adults
     * @param numberOfAdults
     * @return
     */
    def enterNumberOfAdults(int numberOfAdults){
        chooseInDropDownByValue(ADULT_NUMBER, numberOfAdults.toString())
    }

    /**
     * Allows for entering the number of children
     * @param numberOfChildren
     * @return
     */
    def enterNumberOfChildren(int numberOfChildren) {
        chooseInDropDownByValue(CHILDREN_NUMBER, numberOfChildren.toString())
    }

    private def enterCheckoutDate(String checkOut) {
        if (!checkOut.isEmpty()) {
            fillIn(RETURN_DATE, checkOut)
        }
    }

    private def enterCheckInDate(String checkIn) {
        if (checkIn.isEmpty()) {
            calendarPopUp.selectOutboundWithin24Hours()
        } else {
            fillIn(OUTBOUND_DATE, checkIn)
        }
    }

    private def enterMarketSearchKeyword(String destination) {
        WebElement fld = waitForElement(MARKET_SEARCH_KEYWORD)
        fld.sendKeys(DELETE_EXISTING)
        if (destination.isEmpty()) {
            Route route = data.getRoute()
            fld.sendKeys(route.from)
        } else {
            fld.sendKeys(destination) + TAB
        }
        fld.sendKeys(Keys.ENTER)
    }

    void clickSubmit() {
        waitForElement(SUBMIT_BUTTON).click()
        checkNoOops() //TODO This may need to migrate to a step(s) class(es)
    }

    /**
     * Clicks the Find Hotels button
     */
    void attemptToSearch() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def selectAvailableHotel() {
        WebElement hotelList = waitForElement(HOTEL_LIST_CONTAINER)
        List<WebElement> hotels = hotelList.findElements(HOTEL_ROW)

        WebElement hotelRow = null
        for (WebElement hotel : hotels) {
            String hotelName = hotel.findElement(HOTEL_NAME).text
            if(null != hotelItineraryData.hotelName && hotelName.contains(hotelItineraryData.hotelName)){
                hotelRow = hotel
                break
            }
        }

        if(hotelRow == null) {
            hotelRow = hotels.get(0)
        }


        hotelItineraryData.hotelName = hotelRow.findElement(HOTEL_NAME).text
        hotelRow.findElement(SELECT_HOTEL_BUTTON).click()
    }

    def selectAvailableHotelRate() {
        waitForElement(AVAILABLE_HOTEL_RATE).click()
    }

    def continueToPurchase() {
        clickOnOneOfThese(By.id('priceSubmitButton'), By.id('priceSubmitButtonBottom'), By.id('priceItinerarySubmit'))
    }

    def openRetrieveHotelReservation() {
        get(Domains.dotcomDomain + RETRIEVE_HOTEL_URL)
    }

    def submitRetrieveHotelForm() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    /**
     * @deprecated replaced by {@link #fillRetrieveHotelForm(CurrentHotelPNR currentHotelPNR)}
     */
    def fillRetrieveHotelForm(pnr, bookedDate, firstName, lastName) {
        waitForElement(By.id("confirmationNumber")).sendKeys(pnr) + TAB
        def dateFld = waitForElement(By.id("lookupDate"));
        def lookUpDate = formatDate(bookedDate, DATE_FORMAT_PATTERN)
        def bookDate = getmmddyyyyDateFormat(lookUpDate)
        dateFld.sendKeys(DELETE_EXISTING)
        dateFld.sendKeys(bookDate) + ENTER
        dateFld.sendKeys(TAB);
        waitForElement(By.id("firstName")).sendKeys(firstName) + TAB
        waitForElement(By.id("lastName")).sendKeys(lastName) + TAB
    }


    def fillRetrieveHotelForm(HotelReservation hotelReservation) {
        fillIn(CONFIRMATION_NUMBER_TEXT_FIELD, hotelReservation.confirmationNumber, true)
        fillIn(CHECK_IN_DATE_CALENDAR, hotelReservation.checkInDateAsString, true)
        if (DynaStubsIntegration.useDynaStubs()) {
            fillIn(FIRST_NAME_TEXT_FIELD, hotelReservation.customerFirstName, true)
            fillIn(LAST_NAME_TEXT_FIELD, hotelReservation.customerLastName, true)
        } else {
            fillIn(FIRST_NAME_TEXT_FIELD, hotelReservation.billingFirstName, true)
            fillIn(LAST_NAME_TEXT_FIELD, hotelReservation.billingLastName, true)
        }
    }

    /**
     * @deprecated replaced by {@link #fillRetrieveHotelForm(HotelReservation hotelReservation)}
     */
    def fillRetrieveHotelForm(CurrentHotelPNR currentHotelPNR) {
        fillIn(CONFIRMATION_NUMBER_TEXT_FIELD, currentHotelPNR.pnr, true)
        fillIn(CHECK_IN_DATE_CALENDAR, currentHotelPNR.checkInDate, true)
        fillIn(FIRST_NAME_TEXT_FIELD, currentHotelPNR.guestFirstName, true)
        fillIn(LAST_NAME_TEXT_FIELD, currentHotelPNR.guestLastName, true)
    }

    def goToPurchaseConfirmedPage() {
        waitForElement(VISIBLE_SUBMIT_BUTTON).click()
        confirmationPage.verifyPage()
    }

    def cancelHotelLink() {
        waitForElement(CAR_HOTEL_CANCELLATION_LINK).click()
    }

    def cancelHotelReservation() {
        waitForElement(CANCEL_RESERVATION_BUTTON).click()
    }

    def verifyConfirmationNumber(pnr) {
        waitForElement(HOTEL_CONFIRMATION_NUMBER).getText().equalsIgnoreCase(pnr)
    }

    def verifyHotelCancellation() {
        waitForElement(CANCEL_HOTEL_CONFIRMATION_NUMBER).getText().equalsIgnoreCase("1234567890")
    }

    def navigateToHotelSearchPage() {
        waitForElement(HOTEL_LINK).click()
        verifyPage()
    }

    def verifyHotelSearchData(){
        waitForElement(MARKET_SEARCH_KEYWORD).getText().equalsIgnoreCase(hotelItineraryData.destination)

        Date outboundDateSearch =  new Date().parse(DATE_FORMAT_PATTERN, waitForElement(OUTBOUND_DATE).getAttribute("value"))
        Date expectedOutboundDate = hotelItineraryData.checkInDate
        Date returnDateSearch = new Date().parse(DATE_FORMAT_PATTERN, waitForElement(RETURN_DATE).getAttribute("value"))
        Date expectedReturnDate = hotelItineraryData.checkOutDate

        (outboundDateSearch.format(DATE_FORMAT_PATTERN) == expectedOutboundDate.format(DATE_FORMAT_PATTERN))
                .shouldBe true, "Outbound date expected $expectedOutboundDate is not the same as the one shown in the search widget $outboundDateSearch"

        (returnDateSearch.format(DATE_FORMAT_PATTERN) == expectedReturnDate.format(DATE_FORMAT_PATTERN))
                .shouldBe true, "Return date expected $expectedReturnDate is not the same as the one shown in the search widget $returnDateSearch"

        waitForElement(PROMO_CODE).getText().equalsIgnoreCase(hotelItineraryData.promoCode)
        waitForElement(ADULT_NUMBER).getText().equalsIgnoreCase(hotelItineraryData.adults)
        waitForElement(CHILDREN_NUMBER).getText().equalsIgnoreCase(hotelItineraryData.children)
    }
}