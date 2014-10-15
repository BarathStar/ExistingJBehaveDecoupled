package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import pages.elements.FlightSearchForm
import pages.elements.GlobalNavigationHeader
import pages.elements.HomePageFlightSearchFormOld
import pages.elements.LoginForm
import state.PassengerData
import util.ItineraryData
import util.PageName

import static com.thoughtworks.selenium.SeleneseTestBase.fail

public class HomePageOld extends LoginForm {

    OutboundAndReturnDatesAndPopUp calendarPopUp
    public static final outBoundId = "outboundDateAir"
    public static final returnId = "returnDateAir"
    public static final String SUBMIT_BUTTON = "booking_widget_content_row_btn_search"

    private static final By LOGOUT_LINK = By.className("logoutLink")
    private static final By VACATIONS_TAB = By.id("globalnav_header_primary_link_vacations")
    private static final By CAR_SEARCH_BUTTON_WIDGET = By.name("carSelectHome")
    private static final By AIR_SEARCH_BUTTON_WIDGET = By.id("globalnav_header_primary_link_air")
    private static final By VACATIONS_TAB_SUBMIT_BUTTON = By.id("swa_vacationsLanding_submit")
    private static final By ONE_WAY_RADIO_BUTTON = By.id("oneWay")
    private static final By CAR_TAB_WIDGET = By.id("booking_widget_menu_car_anchor")
    private static final By VACATIONS_TAB_WIDGET = By.id("booking_widget_menu_vacation_anchor")
    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By RETURN_DATE = By.id("returnDate")
    private static final By ENROLL_LINK = By.linkText("Enroll")
    private static final By CAR_OFFER = By.id("car_offer")
    private static final By BOOKING_WIDGET_CONTAINER = By.id("booking_widget_content_container")
    private static final By SEARCHBYMAP_BOOKING_WIDGET_ID = By.id("search_by_map_booking")
    private static final By ENROLL_RR_BUTTON = By.id("enrollRr")
    private static final By FLIGHT_STATUS_LINK = By.id("air-check-flight-status")
    private static final By CONTACT_US_LINK = By.cssSelector("a[id='contact-us']")
    private static final By PAGE_FOOTER_MY_ACCOUNT_LINK = By.id("my-account-footer")
    private static final By PAGE_FOOTER_MY_ACCOUNT_LOGIN_LINK = By.cssSelector("a[id='login']")
    private static final By LANGUAGE_SWITCHER_LINK = By.cssSelector(".globalnav_header_utility_link_language a")
    private static final By SOUTHWEST_GIFT_CARD_LINK = By.cssSelector("a[id='southwest-giftcard']")
    private static final By BUSINESS_SELECT_LINK = By.cssSelector('.globalnav_footer_site_links ul li a[id="business-select"]')

    ContactUsPage contactUsPage
    GlobalNavigationHeader globalNavigationHeader
    FlightSearchForm flightSearchForm
    HomePageFlightSearchFormOld homePageFlightSearchFormOld

    public HomePageOld(WebDriverProvider driverProvider) {
        super(driverProvider, '');
        flightSearchForm = new FlightSearchForm(driverProvider)
        println(getRelativePath())
    }

    String getRelativePath() {
        return ""
    }

    def open() {
        super.open()
        verifyPage()
    }

    def verifyPage() {

        System.getProperty("domainToTest") ? getCurrentUrl().shouldContain (System.getProperty("domainToTest")) : getCurrentUrl().shouldContain ("local.swacorp.com")
        pageVerificationErrorWrapper(By.id(outBoundId), PageName.HOME)
        super.verifyPage()
    }

    def submit(boolean verify = true) {
        waitForElement(By.id(SUBMIT_BUTTON)).click()
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

    def clickCarSearchButtonWidget() {
        waitForElement(CAR_SEARCH_BUTTON_WIDGET).click()
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
        waitForElement(BOOKING_WIDGET_CONTAINER).sendKeys(Keys.CONTROL + Keys.ALT + "a")
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
        globalNavigationHeader.openAirSubMenu().clickOnCheckInLink();
    }

    def clickOnChangeReservationLink() {
        globalNavigationHeader.openAirSubMenu().clickOnChangeReservationLink();
    }

    void fillInAirports(String originAirport, String destinationAirport, String returnAirport = null) {
        flightSearchForm.fillInAirports(originAirport, destinationAirport);
    }

    def enterDepartureDate(Date departureDate) {
        flightSearchForm.enterDepartureDate(departureDate, outBoundId)
    }

    def selectAdultAndSeniorPassengers(ItineraryData itineraryData, PassengerData passengerData) {
        flightSearchForm.selectAdultAndSeniorPassengers(itineraryData.adultPassengerCount.toInteger(), itineraryData.seniorPassengerCount.toInteger())
    }

    def fillInHomePageSearchDataAndSubmit(ItineraryData itineraryData, boolean verifyNoOops = true) {
        homePageFlightSearchFormOld.fillInHomePageSearchDataAndSubmit(itineraryData, verifyNoOops)
    }

    def fillInFlightSearchInfoAndClick(ItineraryData itineraryData, boolean createData, String buttonId = SUBMIT_BUTTON) {
        homePageFlightSearchFormOld.fillInFlightSearchInfoAndClick(itineraryData, createData, buttonId);
    }

    def verifyDatesForAirTab() {
        throw new UnsupportedOperationException("This operation is not permitted on old home page");
    }
}
