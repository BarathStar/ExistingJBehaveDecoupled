package pages

import fixture.stubs.DynaStubsIntegration
import org.joda.time.LocalDate
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import pages.elements.FlightSearchForm
import pages.elements.HomePageFlightSearchForm
import pages.elements.LoginForm
import state.PassengerData
import steps.conditional.ToggleHomepage2
import util.ItineraryData
import util.ItineraryDateFactory
import util.PageName

import static com.thoughtworks.selenium.SeleneseTestBase.fail

public class HomePage extends LoginForm  implements GroovyInterceptable {

    OutboundAndReturnDatesAndPopUp calendarPopUp

    private static final By CONFIRMATION_NUMBER_CHANGE_FLIGHT = By.id("changeFlight_confnum");
    private static final By FIRST_NAME_CHANGE_FLIGHT = By.id("changeFlight_firstname");
    private static final By LAST_NAME_CHANGE_FLIGHT = By.id("changeFlight_lastname");
    private static final By SUBMIT_BUTTON_CHANGE_FLIGHT = By.id("jb-submit-button-change-flight")
    private static final By LOGOUT_LINK = By.className("logoutLink")
    private static final By VACATIONS_TAB = By.id("globalnav_header_primary_link_vacations")
    private static final By AIR_SEARCH_BUTTON_WIDGET = By.id("globalnav_header_primary_link_air")
    private static final By SUBMIT_BUTTON = By.id("jb-booking-form-submit-button")
    private static final By VACATIONS_TAB_SUBMIT_BUTTON = By.id("swa_vacationsLanding_submit")
    private static final By CAR_TAB_WIDGET = By.id("booking_widget_menu_car_anchor")
    private static final By VACATIONS_TAB_WIDGET = By.id("booking_widget_menu_vacation_anchor")
    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By RETURN_DATE = By.id("returnDate")
    private static final By ENROLL_LINK = By.linkText("Enroll")
    private static final By CAR_OFFER = By.id("car_offer")
    private static final By BOOKING_WIDGET_CONTAINER = By.id("booking_widget_content_container")
    private static final By SEARCHBYMAP_BOOKING_WIDGET_ID = By.id("jb-search-by-map-booking")
    private static final By BAGGAGE_AND_OPTIONAL_FEES = By.id("jb-baggage-and-optional-fees")
    private static final By FLIGHT_STATUS_LINK = By.id("jb-flight-status")
    private static final By CHECK_IN_SECTION = By.id("booking-form--check-in-tab")
    private static final By CONTACT_US_LINK = By.cssSelector("a[id='contact-us']")
    private static final By PAGE_FOOTER_MY_ACCOUNT_LINK = By.id("my-account-footer")
    private static final By PAGE_FOOTER_MY_ACCOUNT_LOGIN_LINK = By.cssSelector("a[id='login']")
    private static final By LANGUAGE_SWITCHER_LINK = By.cssSelector(".globalnav_header_utility_link_language a")
    private static final By SOUTHWEST_GIFT_CARD_LINK = By.cssSelector("a[id='southwest-giftcard']")
    private static final By BUSINESS_SELECT_LINK = By.cssSelector('.globalnav_footer_site_links ul li a[id="business-select"]')
    private static final By WHERE_WE_FLY_LINK = By.id("jb-where-we-fly")
    private static final By PROMOTION_CODE = By.id("air-promo-code")
    private static final By ORIGIN_AIRPORT_DISPLAYED = By.id("air-city-departure")
    private static final By DESTINATION_AIRPORT_DISPLAYED = By.id("air-city-arrival")
    private static final By ORIGIN_AIRPORT_DISPLAYED_LABEL = By.cssSelector("label[for=air-city-departure]")
    private static final By DESTINATION_AIRPORT_DISPLAYED_LABEL = By.cssSelector("label[for=air-city-arrival]")
    public static final By CHANGE_RESERVATION_LINK = By.id("jb-change-reservation")
    public static final By FLIGHT_CHECKIN_LINK = By.id("jb-flight-checkin")
    private static final By CHECK_FLIGHT_STATUS_SECTION = By.id("booking-form--flight-status-tab")
    private static final By CHECK_FLIGHT_STATUS_ORIGIN = By.id("originAirport")
    private static final By CHECK_FLIGHT_STATUS_DESTINATION = By.id("destinationAirport")
    private static final By SUBMIT_BUTTON_CHECK_FLIGHT = By.id("jb-button-flight-status")
    private static final By ONE_WAY_RADIO_BUTTON = By.id("trip-type-one-way")
    private static final By ROUND_TRIP_RADIO_BUTTON = By.id("trip-type-round-trip")
    private static final By CONFIRMATION_NUMBER_CHECKIN = By.id("confirmationNumber");
    private static final By FIRST_NAME_CHECKIN = By.id("firstName");
    private static final By LAST_NAME_CHECKIN = By.id("lastName");
    private static final By SUBMIT_BUTTON_CHECK_IN = By.id("jb-button-check-in");

    private static final By DOLLARS_RADIO_BUTTON = By.id("price-type-dollars")
    private static final By POINTS_RADIO_BUTTON = By.id("price-type-points")
    public static final String AIR_DATE_DEPARTURE = "air-date-departure"
    public static final String AIR_DATE_RETURN = "air-date-return"
    public static final String MAIN_PAGE_ID = "jb-main-page"
    public static final String HOTEL_DATE_CHECK_IN = "hotel-date-check-in"
    public static final String HOTEL_DATE_CHECK_OUT = "hotel-date-check-out"
    public static final String VACATION_DATE_DEPART = "vacations-date-departure"
    public static final String VACATION_DATE_RETURN = "vacations-date-return"
    public static final String HOTEL_DESTINATION = "hotel-city-destination"
    public static final String VACATION_CITY_DEPARTURE = "vacations-city-departure"
    public static final String VACATION_CITY_ARRIVAL = "vacations-city-arrival"



    ContactUsPage contactUsPage
    HomePageOld homepageOld
    FlightSearchForm flightSearchForm
    HomePageFlightSearchForm homePageFlightSearchForm
    PassengerData passengerData
    ItineraryData itineraryData

    private static By AIR_PAX_COUNT_ADULTS = By.id("air-pax-count-adults")
    private static By AIR_PAX_COUNT_SENIORS = By.id("air-pax-count-seniors")
    private static By HOTEL_PAX_COUNT_ADULTS = By.id("hotel-guest-count-adults")
    private static By HOTEL_PAX_COUNT_CHILDREN = By.id("hotel-guest-count-children")
    private static By VACATION_PAX_COUNT_ADULTS = By.id("vacations-pax-count-adults")
    private static By VACATION_PAX_COUNT_CHILDREN = By.id("vacations-pax-count-children")
    private static By NUMBER_SELECTOR_MORE = By.id("jb-number-selector-more")
    private static By AIR_TAB_LINK = By.className("js-booking-menu-air")
    private static By HOTEL_TAB_LINK = By.className("js-booking-menu-hotel")
    private static By CAR_TAB_LINK = By.className("js-booking-menu-car")
    private static By VACATIONS_TAB_LINK = By.className("js-booking-menu-vacations")

    private static By CAR_LOCATION_PICK_UP = By.id("car-location-pick-up")
    private static By CAR_LOCATION_DROP_OFF = By.id("car-location-drop-off")
    private static By CAR_DATE_PICK_UP = By.id("car-date-pick-up")
    private static By CAR_DATE_DROP_OFF = By.id("car-date-drop-off")
    private static By CAR_TIME_PICK_UP = By.id("car-time-pick-up")
    private static By CAR_TIME_DROP_OFF = By.id("car-time-drop-off")

    private static By CAR_VENDORS = By.id("vendors")
    private static By CAR_SIZE = By.id("car-size")

    public HomePage(WebDriverProvider driverProvider) {
        super(driverProvider, '');
        homepageOld = new HomePageOld(driverProvider)
        println(getRelativePath())
    }

    def invokeMethod(String name, args) {
        def metaMethod = metaClass.getMetaMethod(name, args)
        def result
        if(ToggleHomepage2.isOn()) {
            result = metaMethod.invoke(this, args)
        } else {
            result = homepageOld."$name"(*args)
        }
        return result
    }

    String getRelativePath() {
        return ""
    }

    def open() {
        super.open()
        verifyPage()
    }

    def verifyPage() {

        System.getProperty("domainToTest") ? getCurrentUrl().shouldContain(System.getProperty("domainToTest")) : getCurrentUrl().shouldContain ("local.swacorp.com")
        pageVerificationErrorWrapper(By.id(MAIN_PAGE_ID), PageName.HOME2)
        super.verifyPage()
    }

    def submit(boolean verify = true) {
        waitForElement(SUBMIT_BUTTON).click()
        if (verify) {
            super.verifyPage()
        }
    }

    def vacationsSubmit(boolean verify = true) {
        waitForElement(VACATIONS_TAB_SUBMIT_BUTTON).click()
        if (verify) {
            verifyPage()
        }
    }

    def clickOneWay() {
        waitForElement(ONE_WAY_RADIO_BUTTON).click()
    }

    def clickWhereWeFlyLink() {
        waitForElement(WHERE_WE_FLY_LINK).click()
    }

    def clickRoundTrip() {
        waitForElement(ROUND_TRIP_RADIO_BUTTON).click()
    }

    def clickFlightTab() {
        waitForElement(AIR_TAB_LINK).click()
    }

    def clickHotelTab() {
        waitForElement(HOTEL_TAB_LINK).click()
    }

    def clickCarTab() {
        waitForElement(CAR_TAB_LINK).click()
    }

    def clickVacationsTab() {
        waitForElement(VACATIONS_TAB_LINK).click()
    }

    def onHomePage() {
        checkSomethingServed();
        try {
            waitForElement(By.id("booking_widget_menu"))
        } catch (NoSuchElementException) {
            fail("Not on the home page, on: " + getCurrentUrl() + " instead")
        }
    }

    def clearOutboundDate() {
        super.clearText(waitForElement(OUTBOUND_DATE))
    }

    def clearReturnDate() {
        super.clearText(waitForElement(RETURN_DATE))
    }

    def clearDates() {
        clearOutboundDate()
        clearReturnDate()
    }

    def clickEnrollNow() {
        waitForElement(ENROLL_LINK).click()
    }

    def clickOnBaggageAndOptionalFees() {
        waitForElement(BAGGAGE_AND_OPTIONAL_FEES).click()
    }

    def verifyEnrollLinkIsNotPresent() {
        isElementPresent(ENROLL_LINK) shouldBe false, "The Enroll link should not be present"
    }

    def verifyEnrollLinkIsPresent() {
        isElementPresent(ENROLL_LINK) shouldBe true, "The Enroll link should be present"
    }

    def clickAirSearchButtonWidget() {
        String title = getTitle()
        waitForElement(AIR_SEARCH_BUTTON_WIDGET).click()
        waitForPageTitleToChangeOrOops(title)
    }

    def clickCarTabWidget() {
        waitForElement(CAR_TAB_WIDGET).click()
    }

    def clickVacationsTabWidget() {
        waitForElement(VACATIONS_TAB_WIDGET).click()
    }

    def clickOnVacationTab() {
        waitForElement(VACATIONS_TAB).click()
    }

    def pageIsLoaded() {
        waitForElement(CAR_OFFER, true, 60)
    }

    def clickOnFlightStatusLink() {
        waitForElement(FLIGHT_STATUS_LINK).click()
    }

    def clickOnContactUsLink() {
        waitForElement(CONTACT_US_LINK).click()
        contactUsPage.verifyPage()
    }

    def clickOnMyAccountLinkInFooter() {
        waitForElement(PAGE_FOOTER_MY_ACCOUNT_LINK).click()
    }

    def clickOnMyAccountLoginLinkInFooter() {
        waitForElement(PAGE_FOOTER_MY_ACCOUNT_LOGIN_LINK).click()
    }

    def clickOnSouthwestGiftCardLink() {
        waitForElement(SOUTHWEST_GIFT_CARD_LINK).click()
    }

    def clickOnLanguageSwitcherLink() {
        waitForElement(LANGUAGE_SWITCHER_LINK).click()
    }

    def sendHotKeys() {
        fail("New home page does not support Hot Keys")
    }

    def verifyComboBoxOriginDestination() {
        waitForElement(BOOKING_WIDGET_CONTAINER).getAttribute("style").contains("display: inline-block")
    }

    def clickBusinessSelect() {
        waitForElement(BUSINESS_SELECT_LINK).click()
    }

    def verifyLoggedIn() {
        verifyCurrentPageLocation()
        checkNoOops()
        verifyElementNotPresent("accountNumber", ACCOUNT_NUMBER_FIELD)
        verifyElementNotPresent("accountPassword", ACCOUNT_PASSWORD_FIELD)
        verifyElementPresent("logoutLink", LOGOUT_LINK)
    }

    def clickOnLogoutLink() {
        waitForElement(LOGOUT_LINK).click()
    }

    def verifyLoggedOut() {
        verifyCurrentPageLocation()
        checkNoOops()
        verifyElementPresent("accountNumber", ACCOUNT_NUMBER_FIELD)
        verifyElementPresent("accountPassword", ACCOUNT_PASSWORD_FIELD)
        verifyElementNotPresent("logoutLink", LOGOUT_LINK)
    }

    def verifyIfAccountNumberIsCompleted(String partialNumber) {
        waitForElement(ACCOUNT_NUMBER_FIELD).getAttribute("value").equals(partialNumber).shouldBe true, "Autocomplete active"
    }

    def clickSearchByMapLink() {
        waitForElement(SEARCHBYMAP_BOOKING_WIDGET_ID).click()
    }

    def clickOnCheckinLink() {
        waitForElement(FLIGHT_CHECKIN_LINK).click()
    }

    def clickOnChangeReservationLink() {
        waitForElement(CHANGE_RESERVATION_LINK).click()
    }

    def clickOnCheckInTab() {
        waitForElement(CHECK_IN_SECTION).click()
    }

    def clickOnCheckFlightStatusTab() {
        waitForElement(CHECK_FLIGHT_STATUS_SECTION).click()
    }

    def fillInAirports(String originAirport, String destinationAirport, String returnAirport = null) {
        homePageFlightSearchForm.fillInAirports(originAirport, destinationAirport)
    }

    def fillInHotelDestination(String hotelDestination) {
        fillInWithAutocomplete(By.id(HOTEL_DESTINATION), hotelDestination, 2, false)
    }

    def selectAdultAndSeniorPassengers(ItineraryData itineraryData, PassengerData passengerData) {
        homePageFlightSearchForm.selectAdultAndSeniorPassengers(itineraryData.adultPassengerCount.toInteger(),itineraryData.seniorPassengerCount.toInteger())
    }

    def fillInAdult(int adults) {
        homePageFlightSearchForm.fillInAdultPassenger(adults)
    }

    def fillInSenior(int seniors) {
        homePageFlightSearchForm.fillInSeniorPassenger(seniors)
    }

    def fillInAdultForHotel(int adults) {
        waitForElement(HOTEL_PAX_COUNT_ADULTS).click()
        waitForElement(NUMBER_SELECTOR_MORE).sendKeys(adults.toString());
    }

    def fillInChildrenForHotel(int children) {
        waitForElement(HOTEL_PAX_COUNT_CHILDREN).click()
        waitForElement(NUMBER_SELECTOR_MORE).sendKeys(children.toString());
    }

    def fillInAdultForVacations(int adults) {
        waitForElement(VACATION_PAX_COUNT_ADULTS).click()
        waitForElement(NUMBER_SELECTOR_MORE).sendKeys(adults.toString());
    }

    def fillInVacationCities(String vacationCityDepart, String vacationCityArrival) {
        fillInWithAutocomplete(By.id(VACATION_CITY_DEPARTURE), vacationCityDepart, 2, false)
        fillInWithAutocomplete(By.id(VACATION_CITY_ARRIVAL), vacationCityArrival, 2, false)
    }

    def fillInVacationsPAXChild(int children) {
        waitForElement(VACATION_PAX_COUNT_CHILDREN).click()
        waitForElement(NUMBER_SELECTOR_MORE).sendKeys(children.toString());
    }

    def fillInHomePageSearchDataAndSubmit(ItineraryData itineraryData, boolean verifyNoOpps = true) {
        homePageFlightSearchForm.fillInHomePageSearchDataAndSubmit(itineraryData, verifyNoOpps)
    }

    void selectItineraryType(ItineraryData itineraryData) {
        homePageFlightSearchForm.selectItineraryType(itineraryData)
    }

    def fillInFlightSearchInfoAndClick(ItineraryData itineraryData, boolean createData) {
        homePageFlightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, createData)
    }
    def fillInFlightSearchAndClick(ItineraryData itineraryData, boolean createData) {
        homePageFlightSearchForm.fillInFlightSearchAndClick(itineraryData, createData)
    }

    def fillInPromoCode(String promoCode) {
        homePageFlightSearchForm.fillInPromoCode(promoCode)
    }

    def enterDepartureDate(Date departureDate) {
        homePageFlightSearchForm.enterDepartureDate(departureDate, AIR_DATE_DEPARTURE)
    }

    def enterReturnDate(Date returnDate) {
        homePageFlightSearchForm.enterReturnDate(returnDate, AIR_DATE_RETURN)
    }

    def clickDollarsRadioButton() {
        waitForElement(DOLLARS_RADIO_BUTTON).click()
    }

    def clickPointsRadioButton() {
        waitForElement(POINTS_RADIO_BUTTON).click()
    }

    def verifyBookingWidgetForAir() {
        waitForElement(ROUND_TRIP_RADIO_BUTTON).getAttribute("checked").shouldBe "true", "Round trip radio button is not selected by default."
        waitForElement(By.id(AIR_DATE_DEPARTURE)).getAttribute("value").shouldBe LocalDate.now().plusDays(1).toString("MM/dd"), "Wrong Departure date found."
        waitForElement(By.id(AIR_DATE_RETURN)).getAttribute("value").shouldBe LocalDate.now().plusDays(4).toString("MM/dd"), "Wrong Return date found."
        waitForElement(DOLLARS_RADIO_BUTTON).getAttribute("checked").shouldBe "true", "Dollars radio button is not selected by default"

        clickOneWay()
        waitForElement(By.id(AIR_DATE_RETURN)).getAttribute("disabled").shouldBe "true", "Return Date should be disabled."

        waitForElement(DOLLARS_RADIO_BUTTON).getAttribute("checked").shouldBe "true", "Dollars radio button is not selected by default."
        waitForElement(AIR_PAX_COUNT_ADULTS).getAttribute("value").shouldBe "1", "The default adult pax count is not one."
        waitForElement(AIR_PAX_COUNT_SENIORS).getAttribute("value").shouldBe "0", "The default senior pax count is not one."

        clickPointsRadioButton()
        waitForElement(AIR_PAX_COUNT_SENIORS).getAttribute("disabled").shouldBe "true", "The senior pax count should be disabled for Points."

        clickWhereWeFlyLink()
        waitForElement(By.className("js-show-map")).getText().shouldBe "Map view", "Map view button is missing from Where We Fly popup"
        waitForElement(By.className("js-show-list")).getText().shouldBe "List view", "List view button is missing from Where We Fly popup"
        waitForElement(By.linkText("Visit map search")).getAttribute("href").contains("//getawayfinder.southwest.com/flights").shouldBe true, "The \"Visit map search\" link is invalid."
        waitForElement(By.className("overlay--close-icon")).click()
    }

    def verifyBookingWidgetForHotel() {
        waitForElement(By.id(HOTEL_DATE_CHECK_IN)).getAttribute("value").shouldBe LocalDate.now().plusDays(1).toString("MM/dd"), "Wrong Check-in date found."
        waitForElement(By.id(HOTEL_DATE_CHECK_OUT)).getAttribute("value").shouldBe LocalDate.now().plusDays(4).toString("MM/dd"), "Wrong Check-out Date found."
        waitForElement(HOTEL_PAX_COUNT_ADULTS).getAttribute("value").shouldBe "2", "Wrong hotel guest count for adults."
        waitForElement(HOTEL_PAX_COUNT_CHILDREN).getAttribute("value").shouldBe "0", "Wrong hotel guest count for adults."

    }

    def verifyBookingWidgetForCar() {
        waitForElement(CAR_DATE_PICK_UP).getAttribute("value").shouldBe LocalDate.now().plusDays(1).toString("MM/dd"), "Wrong Pick-up date found."
        waitForElement(CAR_TIME_PICK_UP).getAttribute("value").shouldBe "11:00", "Wrong Pick-up time found."
        waitForElement(CAR_DATE_DROP_OFF).getAttribute("value").shouldBe LocalDate.now().plusDays(4).toString("MM/dd"), "Wrong Drop-off Date found."
        waitForElement(CAR_TIME_DROP_OFF).getAttribute("value").shouldBe "11:00", "Wrong Drop-off time found."
        waitForElement(CAR_VENDORS).getAttribute("value").shouldBe "All Car Companies", "Wrong default selection for Vehicle size drop down."
    }


    def verifyBookingWidgetForVacations() {
        waitForElement(By.id(VACATION_DATE_DEPART)).getAttribute("value").shouldBe LocalDate.now().plusDays(1).toString("MM/dd"), "Wrong Departure date found."
        waitForElement(By.id(VACATION_DATE_RETURN)).getAttribute("value").shouldBe LocalDate.now().plusDays(4).toString("MM/dd"), "Wrong Return date found."

        waitForElement(VACATION_PAX_COUNT_ADULTS).getAttribute("value").shouldBe "2", "Wrong guest count for adults."
        waitForElement(VACATION_PAX_COUNT_CHILDREN).getAttribute("value").shouldBe "0", "Wrong guest count for children."
    }


    def verifyDataRetentionForAirTab() {
        clickFlightTab()
        def originAirport = "DAL"
        def destinationAirport = "DEN"
        def departDate = LocalDate.now().plusDays(10).toString("MM/dd")
        def returnDate = LocalDate.now().plusDays(12).toString("MM/dd")
        fillInAirports(originAirport, destinationAirport)
        waitForElement(By.id(AIR_DATE_DEPARTURE)).sendKeys(DELETE_EXISTING + departDate + TAB)
        waitForElement(By.id(AIR_DATE_RETURN)).sendKeys(DELETE_EXISTING + returnDate + TAB)
        fillInAdult(2)
        fillInSenior(2)

        clickHotelTab()
        verifyFlightTabForDataRetention(originAirport, destinationAirport, departDate, returnDate)
        clickCarTab()
        verifyFlightTabForDataRetention(originAirport, destinationAirport, departDate, returnDate)
        clickVacationsTab()
        verifyFlightTabForDataRetention(originAirport, destinationAirport, departDate, returnDate)
    }

    private void verifyFlightTabForDataRetention(String originAirport, String destinationAirport, String departDate, String returnDate) {
        clickFlightTab()
        waitForElement(ORIGIN_AIRPORT_DISPLAYED).getAttribute("value").shouldBe originAirport, "The data was not retained in Air Tab for Departure City."
        waitForElement(DESTINATION_AIRPORT_DISPLAYED).getAttribute("value").shouldBe destinationAirport, "The data was not retained in Air Tab for Arrival City."
        waitForElement(By.id(AIR_DATE_DEPARTURE)).getAttribute("value").shouldBe departDate, "Wrong Departure date found."
        waitForElement(By.id(AIR_DATE_RETURN)).getAttribute("value").shouldBe returnDate, "Wrong Return date found."
        waitForElement(AIR_PAX_COUNT_ADULTS).getAttribute("value").shouldBe "2", "The default adult pax count is not one."
//        waitForElement(AIR_PAX_COUNT_SENIORS).getAttribute("value").shouldBe "2", "The default senior pax count is not one."
    }

    def verifyDataRetentionForHotelTab() {

        def hotelDestination = "Dallas"
        def checkInDate = LocalDate.now().plusDays(10).toString("MM/dd")
        def checkOutDate = LocalDate.now().plusDays(12).toString("MM/dd")
        clickHotelTab()
        fillInHotelDestination(hotelDestination)
        waitForElement(By.id(HOTEL_DATE_CHECK_IN)).sendKeys(DELETE_EXISTING + checkInDate + TAB)
        waitForElement(By.id(HOTEL_DATE_CHECK_OUT)).sendKeys(DELETE_EXISTING + checkOutDate + TAB)
        fillInAdultForHotel(3)
        fillInChildrenForHotel(2)

        clickFlightTab()
        verifyHotelTabForDataRetention(hotelDestination, checkInDate, checkOutDate)
        clickCarTab()
        verifyHotelTabForDataRetention(hotelDestination, checkInDate, checkOutDate)
        clickVacationsTab()
        verifyHotelTabForDataRetention(hotelDestination, checkInDate, checkOutDate)
    }

    private void verifyHotelTabForDataRetention(String hotelDestination, String checkInDate, String checkOutDate) {
        clickHotelTab()
        waitForElement(By.id(HOTEL_DESTINATION)).getAttribute("value").contains(hotelDestination).shouldBe true, "Wrong retention found for hotel destination."
        waitForElement(By.id(HOTEL_DATE_CHECK_IN)).getAttribute("value").shouldBe checkInDate, "Wrong Check-In date found."
        waitForElement(By.id(HOTEL_DATE_CHECK_OUT)).getAttribute("value").shouldBe checkOutDate, "Wrong Check-Out date found."
        waitForElement(HOTEL_PAX_COUNT_ADULTS).getAttribute("value").shouldBe "3", "Wrong retention hotel guest count for adults."
        waitForElement(HOTEL_PAX_COUNT_CHILDREN).getAttribute("value").shouldBe "2", "Wrong retention hotel guest count for children."
    }

    def verifyDataRetentionForCarTab() {
        def pickUpDate = LocalDate.now().plusDays(10).toString("MM/dd")
        def dropOffDate = LocalDate.now().plusDays(12).toString("MM/dd")
        def pickUpAirport = "DAL"
        def dropOffAirport = "DEN"
        clickCarTab()

        fillInWithAutocomplete(CAR_LOCATION_PICK_UP, pickUpAirport, 2, false)
        waitForElement(CAR_DATE_PICK_UP).sendKeys(DELETE_EXISTING + pickUpDate + TAB)
        waitForElement(CAR_TIME_PICK_UP).click()
        waitForElement(By.xpath("//ul[@class='menu booking-form--car-pickup-time-menu']/li[9]")).click()
        waitForElement(CAR_VENDORS).click()
        waitForElement(By.xpath("//ul[@class='menu booking-form--car-vendor-menu']/li[5]")).click()


        fillInWithAutocomplete(CAR_LOCATION_DROP_OFF, dropOffAirport, 2, false)
        waitForElement(CAR_DATE_DROP_OFF).sendKeys(DELETE_EXISTING + dropOffDate + TAB)
        waitForElement(CAR_TIME_DROP_OFF).click()
        waitForElement(By.xpath("//ul[@class='menu booking-form--car-dropoff-time-menu']/li[9]")).click()
        waitForElement(CAR_SIZE).click()
        waitForElement(By.xpath("//ul[@class='menu booking-form--car-size-menu']/li[5]")).click()

        clickFlightTab()
        verifyCarTabForDataRetention(pickUpDate, dropOffDate)
        clickHotelTab()
        verifyCarTabForDataRetention(pickUpDate, dropOffDate)
        clickVacationsTab()
        verifyCarTabForDataRetention(pickUpDate, dropOffDate)
    }

    private void verifyCarTabForDataRetention(String pickUpDate, String dropOffDate) {
        clickCarTab()
        waitForElement(CAR_DATE_PICK_UP).getAttribute("value").shouldBe pickUpDate, "Wrong Pick-Up date found."
        waitForElement(CAR_DATE_DROP_OFF).getAttribute("value").shouldBe dropOffDate, "Wrong Drop-Off date found."
        waitForElement(CAR_TIME_PICK_UP).getAttribute("value").shouldBe "09:00", "Wrong Pick-Up time found."
        waitForElement(CAR_TIME_DROP_OFF).getAttribute("value").shouldBe "09:00", "Wrong Drop-Off time found."
        waitForElement(CAR_VENDORS).getAttribute("value").shouldBe "Budget", "Wrong retention value for Vehicle Vendor drop down."
        waitForElement(CAR_SIZE).getAttribute("value").shouldBe "Premium", "Wrong retention value for Vehicle size drop down."
    }

    def verifyDataRetentionForVacationsTab() {

        def vacationCityDepart = "DAL"
        def vacationCityArrive = "DEN"
        def vacationDateDepart = LocalDate.now().plusDays(10).toString("MM/dd")
        def vacationDateReturn = LocalDate.now().plusDays(12).toString("MM/dd")
        clickVacationsTab()
        fillInVacationCities(vacationCityDepart, vacationCityArrive)
        waitForElement(By.id(VACATION_DATE_DEPART)).sendKeys(DELETE_EXISTING + vacationDateDepart + TAB)
        waitForElement(By.id(VACATION_DATE_RETURN)).sendKeys(DELETE_EXISTING + vacationDateReturn + TAB)
        fillInAdultForVacations(3)
        fillInVacationsPAXChild(2)

        clickFlightTab()
        verifyVacationsTabForDataRetention(vacationDateDepart, vacationDateReturn)
        clickHotelTab()
        verifyVacationsTabForDataRetention(vacationDateDepart, vacationDateReturn)
        clickCarTab()
        verifyVacationsTabForDataRetention(vacationDateDepart, vacationDateReturn)
    }

    private void verifyVacationsTabForDataRetention(String vacationDateDepart, String vacationDateReturn) {
        clickVacationsTab()
        waitForElement(By.id(VACATION_CITY_DEPARTURE)).getAttribute("value").contains("Dallas").shouldBe true, "Wrong Depart city in retention for Vacation."
        waitForElement(By.id(VACATION_CITY_ARRIVAL)).getAttribute("value").contains("Denver").shouldBe true, "Wrong Arrival city in retention for Vacation."
        waitForElement(By.id(VACATION_DATE_DEPART)).getAttribute("value").shouldBe vacationDateDepart, "Wrong Depart date in retention for Vacation Departure."
        waitForElement(By.id(VACATION_DATE_RETURN)).getAttribute("value").shouldBe vacationDateReturn, "Wrong Return date in retention for Vacation Return."
        waitForElement(VACATION_PAX_COUNT_ADULTS).getAttribute("value").shouldBe "3", "Wrong retention vacation count for adults."
        waitForElement(VACATION_PAX_COUNT_CHILDREN).getAttribute("value").shouldBe "2", "Wrong retention vacation count for children."
    }

    def retrieveReservationToCheckin(String pnr, String pnr_firstName, String pnr_lastName) {
        waitForElement(CONFIRMATION_NUMBER_CHECKIN).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(FIRST_NAME_CHECKIN).sendKeys(DELETE_EXISTING + pnr_firstName)
        waitForElement(LAST_NAME_CHECKIN).sendKeys(DELETE_EXISTING + pnr_lastName)
        waitForElement(SUBMIT_BUTTON_CHECK_IN).click()
    }

    def retrieveReservationToChangeFlight(String pnr, String pnr_firstName, String pnr_lastName) {
        waitForElement(CONFIRMATION_NUMBER_CHANGE_FLIGHT).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(FIRST_NAME_CHANGE_FLIGHT).sendKeys(DELETE_EXISTING + pnr_firstName)
        waitForElement(LAST_NAME_CHANGE_FLIGHT).sendKeys(DELETE_EXISTING + pnr_lastName)
        waitForElement(SUBMIT_BUTTON_CHANGE_FLIGHT).click()
    }

    def enterOriginDestinationDateAndClickCheckFlightStatus(String origin, String destination) {
        waitForElement(CHECK_FLIGHT_STATUS_ORIGIN).sendKeys(DELETE_EXISTING + origin + Keys.ARROW_DOWN + Keys.TAB)
        waitForElement(CHECK_FLIGHT_STATUS_DESTINATION).sendKeys(DELETE_EXISTING + destination +  Keys.ARROW_DOWN + Keys.TAB)
        waitForElement(SUBMIT_BUTTON_CHECK_FLIGHT).click()
    }

}
