package pages

import com.swacorp.dotcom.webscenarios.air.CreditCard
import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.RRUser
import domain.AirReservation
import domain.Companion
import fixture.stubs.DynaStubsIntegration
import org.apache.commons.lang.WordUtils
import org.jbehave.web.selenium.WebDriverProvider
import org.joda.time.LocalTime
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebElement
import pages.elements.FlightSearchForm
import pages.elements.forms.CompanionForm
import state.AccountManagementData
import state.CarReservationData
import state.Flow
import state.ScenarioState
import util.HotelItineraryData
import util.ItineraryData
import util.RRContactInformation
import util.TripManagement

import java.text.SimpleDateFormat

import static org.junit.Assert.assertEquals
import static org.junit.Assert.fail
import static org.junit.Assert.assertTrue
import steps.conditional.TogglePreferenceCenter

class MyAccountPage extends BasePage {

    static final private String RRMALL_SECTION_ID = "rapidRewardsMallSection"
    static final private String APLUS_MEMBERID_ID = "rapidRewardsLoginMemberId"
    static final private String APLUS_PASSWORD_ID = "rapidRewardsLoginPassword"
    static final private String APLUS_LOGIN_BUTTON_ID = "submitButton"
    static final private String REWARDS_TRANSFER_SUBTITLE = "rewardsTransferSubtitle"
    static final String UPCOMING_TRIP_SERVICE_UNAVAILABLE_MSG = "We are currently unable to display your upcoming trips. Please try again later."
    private static final String UPCOMING_TRIP_SORT_DROP_DOWN = "sortByTripName"
    private static final String NO_UPCOMING_TRIPS_MESSAGE = "There are no Upcoming Trips at this moment."
    private static final String NO_UPCOMING_FLIGHTS_MESSAGE_ACCOUNT_BAR = "There are no upcoming flights at this moment. Book a flight now!"

    private static final By RECEIPT_NUMBER = By.className("receiptNumber")
    private static final By WHAT_HAPPENED_TO_MY_AWARDS_BUTTON = By.id("whatHappenedToMyAwardsButton")
    private static final By POINT_ACTIVITY_BUTTON = By.className("point_Activity_Button")
    private static final By INSTRUCTIONS = By.xpath("//div[@class='instructions']")
    private static final By GREEN_TEXT = By.xpath("//div[@class='green_text']")
    private static final By EDIT_ADD_PERSONAL_INFORMATION = By.xpath("//a[@name='personal']/../div/div[@class='editLink']/a[@title='Add/Edit']")
    private static final By EDIT_ADD_EMAIL_INFORMATION = By.xpath("//a[@name='onlineAccounts']/../div/div[@class='editLink']/a[@title='Add/Edit']")
    private static final By EDIT_ADD_ADDRESS_INFORMATION = By.xpath("//a[@name='address']/../div/div[@class='editLink']/a[@title='Edit']")
    private static final By EDIT_ADD_PHONE_INFORMATION = By.xpath("//a[@name='phone']/../div/div[@class='editLink']/a[@title='Add/Edit']")
    private static final By ADD_EDIT_EMAIL_SUBSCRIPTION = By.xpath("//a[@title='Add/Edit']")
    private static final By ADD_EDIT_CC_INFO = By.xpath("//div/a[@class='submitButtonBlank']")
    private static final By EDIT_COMMUNITY_INFO = By.xpath("//div/a[@title='Edit']")
    private static final By EDIT_USERNAME = By.xpath("//div[@class='editLink']/a")
    private static final By TRAVEL_LINK = By.id("travel")
    private static final By RAPID_REWARDS_TRANSFER_BUTTON = By.id("rewardsTransferButton")
    private static final By VISIBLE_SUBMIT_BUTTON = By.id("visibleSubmitButton")
    private static final By PROMOTIONS_TWISTY = By.id("promotions")
    private static final By PROMOTIONS_VIEW_ALL_LINK = By.id("promotions_view_all_link")
    private static final By VIEW_ALL_PROMOTIONS_LINK = By.id("viewAllPromotionsLink")
    private static final By RAPID_REWARDS_LINK = By.id("right_nav_rapidrewards_link")
    private static final By TRANSFERABLE_AWARDS_AND_CERTIFICATES = By.id("rrAwardsAndCertificatesSections")
    private static final By TRANSFERABLE_CREDITS = By.id("rrCreditsSection")
    private static final By TRANSFERABLE_POINTS = By.id("rrPointsSection")
    private static final By SEE_MORE_AWARDS_LINK = By.id("seeMoreAwards")
    private static final By MY_PREFERENCES = By.id("info")
    private static final By HOME_AIRPORT = By.id("homeAirport1")
    private static final By ACCOUNT_SNAPSHOT_LINK = By.id("accountSnapshotLink")
    private static final By RAPID_REWARDS = By.id("rapidrewards")
    private static final By HOME_AIRPORT_ONE_FIELD = By.id("homeAirport1_displayed")
    private static final By DOLLARS_RADIO_BUTTON_ID = By.id("imgdollars")
    private static final By BOOK_A_TRIP_BUTTON = By.id("bookATripButton")
    private static final By RRMALL_BUTTON_ID = By.id("rapidRewardsMallButton")
    private static final By LOGOUT_LINK = By.xpath("//input[@class='logoutLink']")
    private static final By SUBMIT = By.id("submit")
    private static final By ACCOUNT_CREDENTIAL = By.id("accountCredential")
    private static final By ACCOUNT_PASSWORD = By.id("accountPassword")
    private static final By CURRENT_PASSWORD = By.id("currentPassword")
    private static final By NEW_PASSWORD = By.id("newPassword")
    private static final By PASSWORD_CONFRIMATION = By.id("passwordConfirmation")
    private static final By UPDATE_BUTTON = By.id("update")
    private static final By DETAILS_TIER = By.id("viewDetailsTierLink")
    private static final By SNAPSHOT_LINK = By.id("snapshot")
    private static final By PRIMARY_CREDIT_CARD_IMAGE = By.xpath("//img[@alt='Primary Credit Card']")
    private static final By PROMOTIONAL_BOOK_TRIP_BUTTON = By.xpath("//div[@id='tripSearchLinksContainer']//a[@id='planOrBookNewTripLink']")
    private static final By REWARDS_ACTIVITY = By.xpath("//td[@class='view_points_activity']//a")

    private static final By VIEW_ALL_UP_COMING_TRIP = By.id("viewAllUpcomingTripsLink")
    private static final By Add_A_COMPANION = By.xpath("//a[@class='addCompanion']")
    private static final By COMPANION_LIST = By.xpath("//a[@title = 'Add a Companion']")
    private static final By SNAPSHOT_SELECTED = By.cssSelector(".selected.snapshot.snapshot")
    private static final By CNS_MAILING_LIST = By.id("CNS")
    private static final By IAN_MAILING_LIST = By.id("IAN")
    private static final By RREU_MAILING_LIST = By.id("RREU")
    private static final By RRRP_MAILING_LIST = By.id("RRRP")
    private static final By BREADCRUMB = By.id("sw_breadcrumb")
    private static final By NOTIFICATIONS = By.id("notifications")
    private static final By TRAVEL_PROFILE_HOME_AIRPORT_1 = By.id("homeAirport1")
    private static final By TRAVEL_PREFERENCES_LINK_BUTTON = By.className("submitButtonLargest")
    private static final By GET_POINTS_BUTTON = By.xpath("//a[@title='Get Points']")
    private static final By SHORT_ON_POINTS_LINK = By.xpath("//a[@title='Short on points?']")
    private static final By VIEW_DETAILS_COMPANION_LINK = By.id("viewDetailsCompanionLink")
    private static final By CONTACT_INFORMATION_TABLE = By.className("formContent")
    private static final By SECOND_COLUMN_SELECTOR = By.cssSelector("td:nth-child(2)")
    private static final By FIRST_COLUMN_SELECTOR = By.cssSelector("td:nth-child(1)")
    private static final By EDIT_LINK = By.linkText("Edit")
    private static final By FORM_BOX = By.id("formBbox")
    private static final By FORM_SECTION = By.className("formSection")
    private static final By PREFS_SECTION = By.id("myPrefsSection")
    private static final By ADD_EDIT_LINK = By.className('submitButtonBlank')
    private static final By ADD_EDIT_LINK_COMMUNICATION_PREFERENCES = By.className('communication-preferences--add-edit-link')
    private static final By RR_ENROLL = By.cssSelector("#rapidReportPrefs .enroll_now a")
    private static final By EDIT_PREFERENCES = By.className('mediumSubmitButton')
    private static final By EDIT_CONTACT_INFORMATION_LINK = By.className("submitButtonBlank")
    private static final By PAST_FLIGHT_LINK = By.id("pastTrips")
    private static final By TRIP_UNDER_PAST_FLIGHTS_CONTAINER = By.className("trip_information_outer_container")
    private static final By VIEW_ALL_PAST_FLIGHTS_CONTAINER = By.cssSelector("#past_trip_view_all_link .viewAll")
    private static final By VIEW_ALL_PAST_FLIGHTS_LINK = By.id("viewAllPastTripsLink")
    private static final By COMPANION_PASS_DETAILS = By.id("companionPassDetails")
    private static final String EXPIRATION_DATE_FORMAT = ~/^EXP \d\d\.\d\d\.\d\d$/
    private static final String STATUS_ACHIEVED = "Achieved"
    private static final By UPCOMING_TRIPS_AIR_PRODUCTS_NAME = By.className("trip_name_wrapper")
    private static final By VIEW_TRIP_DETAILS = By.id("viewTripDetails1")
    private static final By AIR_ITINERARY = By.xpath("//div[@class='upcoming-trip-itinerary-rounded-container'][2]//div[@class='trip_information_outer_container accountSectionContentCenter']//a[@id='viewTripDetails1']")
    private static final By DATE_RANGE_TRIP_DETAILS = By.cssSelector(".dateRange.trip_information_trip_details_data")
    private static final By UPCOMING_HOTEL_NAME = By.id("viewTripDetails2_hotel2")
    private static final By UPCOMING_HOTEL_DATES = By.cssSelector(".trip_information_trip_product_header_details.hotel_upcoming_trip_container p")
    private static final By UPCOMING_CAR_DATES = By.cssSelector(".trip_information_trip_product_header_details.car_upcoming_trip_container p")
    private static final By UPCOMING_CAR_DETAILS = By.id("viewTripDetails2_car3")
    private static final By TRIP_INFORMATION_CONTAINER = By.cssSelector(".promoHolder div.trip_information_outer_container.accountSectionContentCenter")
    private static final By TRIP_PRODUCTS_ICON = By.cssSelector(".trip_information_trip_product_icons img")
    private static final By CREATE_FREE_FLIGHT_TRACKER_LINK = By.cssSelector("#content_footer a")
    private static final String PROMOTIONAL_MESSAGE = "We are currently unable to retrieve Promotions. Please try again."
    private static final By CHANGE_RESERVATION_LINK = By.id("changeReservationLink")
    private static final By CLCS_PROMOTIONS_ERROR = By.className("clcsOutagePromotionsError")
    private static final By CLCS_PROMOTIONS_CERTIFICATES_ERROR = By.className("clcsOutageCertificatesError")
    private static final By PROMOTIONS_LINK = By.id("promotions")
    private static final By CAR_PRODUCT_LINK = By.id("viewTripDetails1_car1")
    private static final By CAR_CANCEL_LINK = By.id("carCancelLink")
    private static final By AIR_CANCEL_LINK = By.id("airCancelLink")
    private static final By CANCEL_CONFIRMATION = By.id("cancelReservationButton")
    private static final By CANCEL_CONFIRMATION_MODAL = By.id("simplemodal-container")
    private static final By UPCOMING_TRIP_CHANGE_RESERVATION_LINK = By.xpath("//a[@title='Change Reservation']")
    private static final By ACCOUNT_SNAPSHOT_CHANGE_RESERVATION_LINK = By.className("changeReservation")
    private static final String PROMOTIONAL_CERTIFICATES_MESSAGE = "We are currently unable to retrieve Promotional Certificates. Please try again"
    private static final By AWARDED_PROMOTIONAL_CONTAINER = By.id("awardedPromotionSnapshot")
    private static final By VIEW_ALL_ACTIVE_PROMOTIONS_LINK = By.id("viewAllActivePromotionsLink")
    private static final By ACCOUNT_SECTION_TITLE = By.cssSelector(".accountSectionWrapper h3")
    private static final String ACTIVE_PROMOTIONS_VERBIAGE = "Active Promotions"
    private static final By TRIP_NAME = By.className("trip_name_wrapper")
    private static final By UPCOMING_TRIP_CONTENT = By.id("upcomingTripsContent")
    private static final By NO_UPCOMING_TRIP_CONTENT = By.id("noUpcomingTrips")

    private static final By MY_ACCOUNT_PAGE_SNAPSHOT_TITLE = By.id("myAccountSnapshotPageTitle")
    private static final By RAPID_REWARDS_USER_NAME = By.className("global_account_bar_login_form_name")
    private static final By REISSUE_AWARD_SUCCESS_MESSAGE = By.cssSelector(".processSuccessMessage h5")
    static final String AWARD_SUCCESSFULLY_REISSUED = "Your payment of \$50.00 has successfully been processed. One award was reissued."
    public static int DEFAULT_WAIT_RETRY = 300
    public static int DEFAULT_WAIT_PAGE = 10000
    public final static String ITINERARY_DATES = ~/^\d\d\/\d\d\/\d\d\d\d – \d\d\/\d\d\/\d\d\d\d$/
    private static final String AIR_PRODUCT = "flight"
    private static final String CAR_PRODUCT = "car"
    private static final String HOTEL_PRODUCT = "hotel"
    public static final String MY_PREFERENCES_URL = "account/info/view-credentials"
    private static final By VIEW_AWARDS_LINK = By.cssSelector(".view_awards a")
    private static final By OLD_AWARDS_LINK = By.className("right_nav_awards_image")
    private static final By CREATE_AWARD_BUTTON = By.id("send")
    private static final By COMPLETE_CONVERSION_SUBMIT_BUTTON = By.className("submitButton")
    private static final By XPATH_MAKE_PRIMARY = By.xpath("//input[@class='makePrimaryContact submitButtonBlank']")
    private static final By SUBMIT_UPDATE_BILLING_INFO_FORM = By.xpath("//div[@class='submitForm']/input[@id='submitForm']")
    private static final By BILLING_INFORMATION = By.xpath("//div[@class='lastFormSection']")
    private static final By TRAVELER_ACCOUNT_LOGOUT = By.id("traveler-account-logout")
    private static final By DESCRIPTION_IRN_OPTION = By.id("irnPresenter.alternateIrns1.description")
    private static final By COMPANION_SECTION_SNAPSHOT = By.xpath("//div[@id='companionTripsSelectorRow']//a[@class='noScriptAlternative']")
    private static final By COMPANION_TRIPS_SELECTOR_ROW = By.id("companionTripsSelectorRow")
    private static final By COMPANION_TRIPS_DETAILS_BUTTON = By.className("noScriptAlternative")
    private static final By VIEW_DETAIL_BUTTON_SNAPSHOT = By.xpath("//a[@href='/account/rapidrewards/companion-status']")
    private static final By SUBMIT_BUTTON = By.id("submit")
    private static final By COMPANION_SECTION_MY_RR = By.id("rapidRewardsCompanionPassContainer")
    private static final By HEADER_FORM_CREDIT_CARD = By.className("headerForm")
    private static final By COMPANION_CONFIRMATION_NUMBER = By.className("companion_confirmation_number")

    private static final By ACCOUNT_BAR_TOOLS_MY_TRAVEL_HEADER = By.id("global_account_bar_tools_mytravel_header")
    private static final By ACCOUNT_BAR_MY_TRAVEL_CHECK_IN_ONLINE = By.partialLinkText("Check In Online")
    private static final By UPCOMING_TRIP_SUBMIT_BUTTON = By.className("submitButton")
    private static final By SPAM_UPCOMING_TRIPS = By.xpath("//div[@id='upcomingTrips']//span[@class='miniHeader']")
    private static final By LINK_DEPARTURE_AND_ARRIVAL_CITIES_NAMES = By.xpath("//div[@class='graphBlockCp_blue graphBlock_myTravel']//div[@class='right_nav_tools_my_travel_subheader']//a")
    private static final By UPCOMING_TRIP_DATE_AND_CONFIRMATION = By.id("upcomingTripDateAndConfirmation")
    private static final By UPCOMING_TRIP_DEP_AND_ARR_TIME = By.id("upcomingTripDepAndArrTime")
    private static final String TRIP_DETAILS_URL = "/account/travel/upcoming-trips/trip-details"
    private static final By LAST_ACTIVITY_DATE = By.className("global-account-bar-login-last-activity")
    private static final By LAST_ACTIVTY_DATE_HELP_ICON = By.className("icon-help")
    private static final By LAST_ACTIVITY_HELP_POPUP = By.className("last_activity_date")
    private static final By VIEW_DETAILS_ICON = By.className("buttonWhiteWidth126")
    private static final By ACCOUNT_EXPIRATION_DATE = By.className("global-account-bar-login-last-activity")
    private static final By ADD_ANOTHER_PHONE_NUMBER = By.xpath("//input[contains(@value, 'Add another phone number')]")
    private static final By FIRST_ANOTHER_PHONE_NUMBER_INPUT_TEXT_BOX = By.id("phones1")
    private static final By CONTACT_INFORMATION = By.xpath("//div[@class='formWrapper']//td")
    private static final By ACCOUNT_SIDE_BAR = By.id("account_bar_rr_number")
    private static final By BILLING_ADDRESS = By.id("billingAddress0")
    private static final By CREDIT_CARD_CITY = By.id("paymentInfos.list0.address.city")
    private static final By CREDIT_CARD_STATE = By.id("paymentInfos.list0.address.state")
    private static final By CREDIT_CARD_ZIP_CODE = By.id("paymentInfos.list0.address.zipOrPostalCode")
    private static final By CREDIT_CARD_COUNTRY = By.id("paymentInfos.list0.address.country")
    private static final By BUDDY_NAME0 = By.id("js-buddy-name0")
    private static final By BUDDY_EMAIL0 = By.id("js-buddy-email0")
    private static final By SHARE_YOUR_ITINERARY_BUDDY_ONE = By.id("js-available-emails0")
    private static final String SHARE_YOUR_ITINERARY_BUDDY_NAME_VALUE = "buddy1"
    private static final String SHARE_YOUR_ITINERARY_BUDDY_EMAIL_VALUE = "buddy1@buddy.com"
    private static final By CONTACT_AREA_CODE = By.id("js-phone-area-code")
    private static final By CONTACT_PREFIX = By.id("js-contact-prefix")
    private static final By CONTACT_PHONE_NUMBER = By.id("js-us-phone-line-number")
    private static final By CONTACT_COUNTRY_PREFIX = By.id("js-phone-country")
    private static final By CONTACT_INTL_PHONE_NUMBER = By.id("js-international-phone-line-number")
    private static final By HOW_CAN_WE_CONTACT_SECTION = By.className("preferred-method-of-contact--header-section")
    private static final String PURCHASE_MODULE_PHONE_NUMBER = "352-505-0935"
    private static final By PURCHASE_CONTACT_AREA_CODE = By.id("js-us-phone-area-code")
    private static final By PURCHASE_CONTACT_PREFIX = By.id("js-us-phone-prefix")
    private static final By PURCHASE_CONTACT_PHONE_NUMBER = By.id("js-us-phone-line-number")
    private static final By CONTACT_METHOD_DROP_DOWN = By.id("js-preferred-method-of-contact--contact-method")

    RRContactInformation rrContactInformation
    AccountManagementData accountManagementData
    ItineraryData itineraryData
    HotelItineraryData hotelItineraryData
    CarReservationData carReservationData
    private static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy"

    CompanionForm companionForm

    FlightSearchForm flightSearchForm
    Data data;
    Flow flow
    ScenarioState scenarioState

    public List<WebElement> retrieveContactInformationElements() {
        return waitForElements(CONTACT_INFORMATION)
    }

    public String retrieveAccountNumber() {
        return waitForElement(ACCOUNT_SIDE_BAR).text
    }

    private WebElement receiptNumber() {
        waitForElement(RECEIPT_NUMBER)
    }

    private WebElement viewCreditsAndAwardsButton() {
        waitForElement(WHAT_HAPPENED_TO_MY_AWARDS_BUTTON)
    }

    private WebElement viewPointsActivity() {
        waitForElement(POINT_ACTIVITY_BUTTON)
    }

    private WebElement findTransferableAwardsAndCertificates() {
        waitForElement(TRANSFERABLE_AWARDS_AND_CERTIFICATES)
    }

    public MyAccountPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/login')
    }

    String getRelativePath() {
        return "/account/snapshot";
    }

    def open() {
        super.open()
        checkSomethingServed()
        checkNoOops()
    }

    def logIn(String userType = "goodUser", RRUser rrUser = null) {
        if (rrUser.equals(null)) {
            rrUser = data.getUser(userType)
        }

        flow.setRrUser(rrUser)

        if (DynaStubsIntegration.useDynaStubs() && !userType.equals("invalid")) {
            new DynaStubsIntegration().createRRMember(rrUser)
        }

        fillIn(ACCOUNT_CREDENTIAL, rrUser.getRRNumber())
        fillIn(ACCOUNT_PASSWORD, rrUser.getRRPassword())
        waitForElement(SUBMIT).click()
        verifyPage()
        flow.isRapidRewards = true
        flow.isLoggedIn = true
        flow.userLoggedInRapidRewardsNumber = rrUser.getRRNumber()
    }

    def logOut() {
        waitForElement(LOGOUT_LINK)
    }

    def logOutClick() {
        waitForElement(LOGOUT_LINK).click()
    }


    def goToMyPreferences() {
        performClickAction(MY_PREFERENCES)
    }

    def clickSnapshotLink() {
        findElement(SNAPSHOT_LINK).click()
    }

    def gotoContactInformation() {
        selectLinkInParent("contactInformation", "a", "Contact Information")
    }

    def gotoCommunicationPreferences() {
        selectLinkInParent("communicationPreferences", "a", "Communication Preferences")
    }

    def gotoPaymentInformationPage() {
        selectLinkInParent("paymentInformation", "a", "Payment Information")
    }

    def clickMyAccount() {
        performClickAction(ACCOUNT_SNAPSHOT_LINK)
        verifyPage()
    }

    def verifySnapshotPage() {
        getPageSource().shouldContain "Recent Points Activity"
    }

    def clickMyTravelLink() {
        waitForElement(TRAVEL_LINK).click()
    }

    def myPastAndUpcomingTrips() {
        try {
            waitForElement(By.xpath("//div[@id='firstUpcomingTrip']"))
        } catch (NoSuchElementException e) {
            fail " My Past and Upcoming Trips are not visible"
        }
    }

    def myUpcomingTrips() {
        try {
            waitForElement(By.xpath("//div[@class='trip_information_trip_header']"))
        } catch (NoSuchElementException e) {
            fail "My Upcoming Trips are not visible"
        }
    }

    def myPointsProgress() {
        try {
            waitForElement(By.xpath("//div[@id='memberStatusChartWidget']"))
            waitForElement(By.xpath("//div[@id='companionStatusChartWidget']"))
            waitForElement(By.xpath("//div[@id='statusChartTier']"))
        } catch (NoSuchElementException e) {
            fail " My Points Progress are not visible"
        }
    }

    void clickMyRapidRewardsLink() {
        waitForElement(RAPID_REWARDS).click()
    }

    void clickMyRewardsActivityLink() {
        waitForElement(REWARDS_ACTIVITY).click()
    }

    def verifyMyPreferences() {
        waitForElement(INSTRUCTIONS).getText().shouldContain "Your username, password"
    }

    void clickOnMyPreferencesPage() {
        waitForElement(MY_PREFERENCES).click();
    }

    def purchasePoints() {
        chooseInDropDownByValue("quickLinks", "/account/rapidrewards/purchase-points")
    }

    def pointsSelection(String points = "40,000") {
        //by default we are purchasing 40,000 points
        flow.isRapidRewardsPointsPurchaseOnly = true
        def elements = waitForElements(By.xpath("//div[@class = 'blockCount']"))
        for (element in elements) {
            def text = element.getText()
            text.trim()
            if (text.equals(points)) {
                element.click()
                return
            }
        }
    }

    def pointsSubmitButton() {
        waitForElement(By.id("submitForm")).click()
    }

    def pointsPurchaseSubmitButton() {
        waitForElement(VISIBLE_SUBMIT_BUTTON).click()
    }

    def pointsPurchaseConfirm() {
        def text = receiptNumber().getText()
        if (!text.contains("Receipt #")) {
            fail("------------Receipt # not found------------")
        } else {
            flow.isRapidRewardsPointsPurchaseOnly = false
        }
    }

    void verifyEligibleForFreeTrip() {
        isEligibleForFreeTrip().shouldBe true, "not Eligible for free flight yet!!!!!!!!!!!!!!!!!!!!"
    }

    boolean isEligibleForFreeTrip() {
        WebElement greenText = waitForElement(GREEN_TEXT)
        String text = greenText.getText()
        text = text.substring(0, text.length() - 1)
        if (text.contains("100")) {
            return true
        }
        return false
    }

    def closeBookAFlightModal() {
        waitForElement(By.xpath("//div[@id='simplemodal-container']//img[@alt='x']")).click()
    }

    def enterStationOnMyPreferencePage(String station) {
        WebElement homeAirport1 = waitForElement(HOME_AIRPORT_ONE_FIELD)
        homeAirport1.sendKeys(DELETE_EXISTING + station)
    }

    void viewCreditsAndAwards() {
        String title = getTitle()
        viewCreditsAndAwardsButton().click()
        waitForPageTitleToChangeOrOops(title)
    }

    void viewPromotions() {
          clickOnPromotions()
        // This is a bug on the page because there should not
        // not be 2 "View All" text links after the twistie is selected
        try {
            waitForElement(PROMOTIONS_VIEW_ALL_LINK).click()
        } catch (ElementNotVisibleException) {
            waitForElement(VIEW_ALL_PROMOTIONS_LINK).click()
        }
    }

    void clickOnPromotions() {
         waitForElement(PROMOTIONS_TWISTY).click()
    }

    void getLoginErrorMessage() {
        waitForElement(By.id("errors")).text.shouldContain "The Username/Account Number and/or Password are incorrect",
                "Did not find error loging in"
    }

    void viewMyPointTransactions() {
        viewPointsActivity().click()
    }

    void addCompanionToPurchase() {
        String pnr = scenarioState.getLastAirReservation().adultPnr
        clickOnViewAllUpcomingTrips()
        clickAddCompanionLink(pnr)
    }

    def verifyDollarsRadioButtonIsChecked() {
        waitForElement(DOLLARS_RADIO_BUTTON_ID).getAttribute("class").shouldBe "radioInput radioChecked"
    }

    def selectPointsFareOption() {
        JavascriptExecutor javascript = (JavascriptExecutor) this
        javascript.executeScript("\$('#imgpoints').click()")
    }

    def clickBookATrip() {
        waitForElement(BOOK_A_TRIP_BUTTON, true, 55).click()
    }

    def clickPromotionBookTripButton() {
        waitForElement(PROMOTIONAL_BOOK_TRIP_BUTTON)?.click()
    }

    def clickOnRRMallButton() {
        waitForElement(RRMALL_BUTTON_ID).click()
    }

    boolean verifyRRMallButton() {
        return isElementDisplayed(RRMALL_BUTTON_ID)
    }

    boolean verifyRRMallSection() {
        return isElementDisplayed(By.id(RRMALL_SECTION_ID))
    }

    def verifyURL(String expected) {
        waitForElement(RRMALL_BUTTON_ID).getAttribute("href").shouldContain expected, "Expected URL is rapidrewardsshopping-ist.swacorp.com"
    }

    def viewTierStatus() {
        waitForElement(DETAILS_TIER).click()
    }

    def clickTransferAplusRapidRewardsButton() {
        waitForElement(RAPID_REWARDS_TRANSFER_BUTTON).click()
    }

    def verifyPrimaryCreditCardIsPresent() {
        verifyElementPresent("Primary Credit Card Image", PRIMARY_CREDIT_CARD_IMAGE)
    }

    void viewTripDetails() {
        String title = getTitle()
        waitForElement(VIEW_TRIP_DETAILS).click()
        waitForPageTitleToChangeOrOops(title)
        verifyPage()
    }

    void storeUpcomingTripDetail() {
        String upcomingTripName = waitForElement(By.className("trip_name_wrapper")).getText()
        flow.tripName = upcomingTripName
    }

    void verifySnapshotTabSelected() {
        verifyElementPresent("Snapshot Tab", SNAPSHOT_SELECTED)
    }

    void verifyMyAccountSnapshotPageTitle() {
        String userName = waitForElement(RAPID_REWARDS_USER_NAME).text
        String cleanedUpUserName = userName.substring(0, userName.size() - 1)
        String accountSnapshotPageTitle = waitForElement(MY_ACCOUNT_PAGE_SNAPSHOT_TITLE).text

        accountSnapshotPageTitle.shouldContain cleanedUpUserName
        accountSnapshotPageTitle.shouldContain "'s Rapid Rewards Account"
    }

    def loginWithUserNameAndPassword(String userName, String password) {
        fillIn(ACCOUNT_CREDENTIAL, userName)
        fillIn(ACCOUNT_PASSWORD, password)
        String title = getTitle()
        waitForElement(SUBMIT).click()
        waitForPageTitleToChangeOrOops(title)
    }

    void verifyEmailNotPresentOnCns(String email) {
        verifyEmailNotPresent(waitForElement(CNS_MAILING_LIST), email)
    }

    void verifyEmailNotPresentOnIan(String email) {
        verifyEmailNotPresent(waitForElement(IAN_MAILING_LIST), email)
    }

    def verifyBreadcrumb(String page) {
        String breadcrumbText = "My AccountMy Preferences" + page
        waitForElement(BREADCRUMB).text.shouldBe breadcrumbText
    }

    def verifyIamOnTheCorrectPage(String url) {
        getCurrentUrl().contains(url).shouldBe true, "The url should be: " + getCurrentUrl() + " and was: " + url
    }

    def verifyEmailNotPresentOnRreu(String email) {
        verifyEmailNotPresent(waitForElement(RREU_MAILING_LIST), email)
    }

    def verifyEmailNotPresentOnRrrp(String email) {
        verifyEmailNotPresent(waitForElement(RRRP_MAILING_LIST), email)
    }

    def verifyEmailArePresentOnRreu(String firstEmail, String secondEmail) {
        verifyEmailsArePresent(waitForElement(RREU_MAILING_LIST), firstEmail, secondEmail)
    }

    def verifyEmailArePresentOnRrrp(String firstEmail, String secondEmail) {
        verifyEmailsArePresent(waitForElement(RRRP_MAILING_LIST), firstEmail, secondEmail)
    }

    def verifyEmailSubscribedToAllTheEmailOptions() {
        isElementWithTextPresent(RREU_MAILING_LIST, accountManagementData.emailAddress).shouldBe true, "The email adress on RREU_MAILING_LIST should be " + accountManagementData.emailAddress
        isElementWithTextPresent(RRRP_MAILING_LIST, accountManagementData.emailAddress).shouldBe true, "The email adress on RRRP_MAILING_LIST should be " + accountManagementData.emailAddress
        isElementWithTextPresent(CNS_MAILING_LIST, accountManagementData.emailAddress).shouldBe true, "The email adress on CNS_MAILING_LIST should be " + accountManagementData.emailAddress
        isElementWithTextPresent(IAN_MAILING_LIST, accountManagementData.emailAddress).shouldBe true, "The email adress on IAN_MAILING_LIST should be " + accountManagementData.emailAddress
    }

    private verifyEmailNotPresent(WebElement cnsMailingList, String email) {
        if(TogglePreferenceCenter.isOn()) {
            List<WebElement> emailsList = cnsMailingList.findElements(By.className('communication-preferences--layout-right-column-email-selection'))
            if (!emailsList.isEmpty()) {
                emailsList.each { element ->
                    element.isSelected().shouldNotBe true
                }
            }
        } else {
            List<WebElement> emailsList = cnsMailingList.findElements(By.tagName('label'))
            if (!emailsList.isEmpty()) {
                emailsList.each { element ->
                    element.text.shouldNotContain email
                }
            }
        }
    }

    private verifyEmailsArePresent(WebElement mailingList, String[] mails) {
        def emailsList = mailingList.findElements(TogglePreferenceCenter.isOn() ? By.className('communication-preferences--layout-right-column-email-address') : By.tagName('label')).collect { it.text.trim() }
        mails.each { element ->
            assertTrue("The email ${element} is not present in the subscription list",emailsList.contains(element))
        }
    }

    def verifyConsolidationSuccess() {
        waitForElement(NOTIFICATIONS).getText().shouldContain flow.user.userName, "The notification should contains " + flow.user.userName
    }

    def verifyPersonalInformation() {
        List<WebElement> personalInformation = contactInformationTableSelector(0, SECOND_COLUMN_SELECTOR)
        personalInformation.get(0).text.shouldContain WordUtils.capitalize(flow.getUser().getRRFirstName())
        personalInformation.get(2).text.shouldContain WordUtils.capitalize(flow.getUser().getRRLastName())
        personalInformation.get(5).text.shouldContain flow.getUser().getRRDateOfBirth().format(DATE_FORMAT_PATTERN)
        personalInformation.get(7).text.shouldContain flow.getUser().getGender()
    }

    def verifyAccountEMailAddresses() {
        List<WebElement> emailAddress = contactInformationTableSelector(1, FIRST_COLUMN_SELECTOR)
        emailAddress.get(0).text.shouldContain flow.getUser().getEmail()
    }

    def postalAddresses() {
        List<WebElement> postalAddresses = contactInformationTableSelector(2, SECOND_COLUMN_SELECTOR)
        WordUtils.capitalize(postalAddresses.get(0).text).shouldContain WordUtils.capitalize(flow.getUser().getAddress())
        postalAddresses.get(1).text.shouldContain flow.getUser().getCity()
        postalAddresses.get(2).text.shouldContain flow.getUser().getState()
        postalAddresses.get(3).text.shouldContain flow.getUser().getZipCode()
        postalAddresses.get(4).text.shouldContain flow.getUser().getCountry()
        postalAddresses.get(5).text.toUpperCase().shouldContain flow.getUser().getAddressType().toUpperCase()
    }

    def phoneNumbers() {
        List<WebElement> phoneNumbers = contactInformationTableSelector(3, SECOND_COLUMN_SELECTOR)
        phoneNumbers.get(0).text.shouldContain flow.getUser().getPhoneType()
        phoneNumbers.get(1).text.shouldContain flow.getUser().getPhoneNumber().toString().replaceAll(" ", "")
    }

    private List<WebElement> contactInformationTableSelector(Integer tableNumber, By rowSelector) {
        List<WebElement> contactInformation = waitForElements(CONTACT_INFORMATION_TABLE)
        WebElement tableColumns = contactInformation.get(tableNumber)
        List<WebElement> rows = tableColumns.findElements(rowSelector)
        return rows
    }

    def clickOnEditContactInformationLink() {
        waitForElement(EDIT_CONTACT_INFORMATION_LINK).click()
    }

    def clickOnGetPointsButton() {
        waitForElement(GET_POINTS_BUTTON).click()
    }

    def clickShortOnPointsLink() {
        waitForElement(SHORT_ON_POINTS_LINK).click()
    }

    def clickOnViewDetailsCompanionLink() {
        waitForElement(VIEW_DETAILS_COMPANION_LINK).click()
    }

    def verifyUserNameAndPasswordEditLinkIsPresent() {
        isElementPresent(EDIT_LINK).shouldBe true, "The link edit are not prentent"
    }

    def clickOnUserNameAndPasswordEditLink() {
        waitForElement(EDIT_LINK).click()
    }

    def verifyOopsMessageForBlacklist(String blacklistedWord) {
        def oopsMessage = ["The Password you entered is not valid. A Password cannot contain \"" + blacklistedWord + "\"."]
        verifyOopsMessages(oopsMessage)
    }

    def changePassword(String oldPassword, String newPassword) {
        fillIn(CURRENT_PASSWORD, oldPassword)
        fillIn(NEW_PASSWORD, newPassword)
        fillIn(PASSWORD_CONFRIMATION, newPassword)
        waitForElement(UPDATE_BUTTON).click()
    }

    def verifyOopsMessageForInvalidPassword() {
        def oopsMessage = ["The New Password you entered is not valid. A Password is case sensitive and cannot contain spaces or special characters."]
        verifyOopsMessages(oopsMessage)
    }

    def verifyOopsMessageForShortPassword() {
        def oopsMessage = ["The New Password you entered is not valid. A Password must have a minimum of 6 characters and maximum of 16 characters."]
        verifyOopsMessages(oopsMessage)
    }

    def verifyIamOnTheContactInformationPageIsNotEditable() {
        verifyAnyLinkIsPresent(FORM_BOX, "Update").shouldBe false, "The link Update Should not be"
        verifyAnyLinkIsPresent(FORM_BOX, "Cancel").shouldBe false, "The link Cancel Should not be"
    }

    def verifyTheContactInformationSectionCanBeEdited(int numberOfLinks) {
        verifyAddEditLinksArePresent(numberOfLinks).shouldBe true, "The links Add/Edit Should not be"
    }

    def verifyTheCommunicationPreferencesSectionCanBeEdited(int numberOfLinks) {
        verifyAddEditLinksExist(numberOfLinks).shouldBe true, "The links Add/Edit Should not be"
    }

    def verifyCommunicationPreferencePageIsNotEditable() {
        verifyAnyLinkIsPresent(PREFS_SECTION, "Update").shouldBe false, "The link Update Should not be"
        verifyAnyLinkIsPresent(PREFS_SECTION, "Cancel").shouldBe false, "The link Cancel Should not be"
    }

    private boolean verifyAnyLinkIsPresent(By formBox, String linkText) {
        List<WebElement> links = waitForElement(formBox).findElements(By.cssSelector("a"))
        boolean isLinkDisplayed = links.any() { link ->
            link.getText().contains(linkText)
        }
        return isLinkDisplayed
    }

    private boolean verifyAddEditLinksArePresent(int numberOfLinks) {
        List<WebElement> links = waitForElements(ADD_EDIT_LINK)
        return links.size == numberOfLinks
    }

    private boolean verifyAddEditLinksExist(int numberOfLinks) {
        List<WebElement> links = waitForElements(ADD_EDIT_LINK_COMMUNICATION_PREFERENCES)
        return links.size == numberOfLinks
    }

    def verifyClickAndSaveEmail() {
        waitForElement(CNS_MAILING_LIST).text.shouldContain flow.getRrUser().getEmail(), 'the email address should be the same that was used at account creation time'
    }

    def verifyNutshellEmail() {
        waitForElement(IAN_MAILING_LIST).text.shouldContain flow.getRrUser().getEmail(), 'the email address should be the same that was used at account creation time'
    }

    def clickOnAddEditLink(String option="CC") {
        if(option.equals("IRN")){
            waitForElement(By.className("lastFormSection")).findElement(ADD_EDIT_LINK).click()
        } else {
            waitForElement(ADD_EDIT_LINK).click()
        }
    }

    def clickOnRREnroll() {
        waitForElement(RR_ENROLL).click()
    }

    def clickOnEditPreferencesButton() {
        waitForElement(EDIT_PREFERENCES).click()
    }

    def clickOnPastFlightsLink() {
        waitForElement(PAST_FLIGHT_LINK).click()
    }

    boolean validateFormatDate(By element, String format) {
        waitForElement(element).getText().trim().matches(format)
    }

    def verifyMyTripIsUnderPastFlightsSection() {
        verifyElementPresent("My Trip", TRIP_UNDER_PAST_FLIGHTS_CONTAINER)
    }

    boolean verifyCompanionPassFormatDate() {
        validateFormatDate(COMPANION_PASS_DETAILS, EXPIRATION_DATE_FORMAT)
    }

    def verifyViewAllPastFlightsLinkIsPresent() {
        verifyElementPresent("View All", VIEW_ALL_PAST_FLIGHTS_CONTAINER)
    }

    def clickOnPromotionsLink() {
        waitForElement(PROMOTIONS_LINK).click()
        verifyPage()
    }

    def verifyNotSeePromotionalCertificatesMessage() {
        verifyElementNotPresent("CLCS Promotions Certificates Error", CLCS_PROMOTIONS_CERTIFICATES_ERROR)
    }

    def verifyPromotionalMessage() {
        waitForElement(CLCS_PROMOTIONS_ERROR).text.shouldContain PROMOTIONAL_MESSAGE, "The ${PROMOTIONAL_MESSAGE} should be present"
    }

    def clickOnViewAllLinkInsideThePastFlightsSection() {
        WebElement viewAll = waitForElement(VIEW_ALL_PAST_FLIGHTS_LINK)
        boolean isDisplayed = false
        while (!isDisplayed) {
            isDisplayed = viewAll.isDisplayed()
            sleep(DEFAULT_WAIT_RETRY)
        }
        viewAll.click()
        verifyPage()
    }

    def clickOnViewAllUpcomingTrips() {
        waitForElement(VIEW_ALL_UP_COMING_TRIP).click()
    }

    def verifyCompanionPassIsAchieved() {
        waitForElement(COMPANION_PASS_DETAILS).text.shouldBeEqual STATUS_ACHIEVED, "The status of Companion Pass is not " + STATUS_ACHIEVED
    }

    def clickAddCompanionLink(String pnr) {
        List<WebElement> webElementList = waitForElements(Add_A_COMPANION)
        for (WebElement webElement : webElementList) {
            if (webElement.getAttribute("href").contains("recordLocator=" + pnr)) {
                webElement.click();
                return
            }
        }
        fail("Add a companion link not found so we can not add companion ")
    }

    private verifyNamedTripIsFirstAndDefaultNamedAirProductIsSecond() {
        List<WebElement> airProducts = waitForElements(UPCOMING_TRIPS_AIR_PRODUCTS_NAME)
        String tripName = TripManagement.getDefaultTripName(itineraryData.departureDate, itineraryData.arrivalStation)
        airProducts.get(0).getText().shouldContain flow.tripName
        airProducts.get(1).getText().shouldContain tripName, "Default trip name is not correctly formatted"
    }

    private verifyDefaultNamedAirProductIsFirstAndNamedTripIsSecond() {
        List<WebElement> airProducts = waitForElements(UPCOMING_TRIPS_AIR_PRODUCTS_NAME)
        String tripName = TripManagement.getDefaultTripName(itineraryData.departureDate, itineraryData.arrivalStation)
        airProducts.get(0).getText().shouldContain tripName, "Default trip name is not correctly formatted"
        airProducts.get(1).getText().shouldContain flow.tripName
    }

    def verifyUpcomingTripSortDropDownOptions() {
        isOptionInDropDownSelected(UPCOMING_TRIP_SORT_DROP_DOWN, "Trip Date (Earliest to Latest)").shouldBe true, "Trip Date (Earliest to Latest) should be selected by default"
        isOptionInDropDown(UPCOMING_TRIP_SORT_DROP_DOWN, "Trip Date (Latest to Earliest)").shouldBe true, "Trip Date (Latest to Earliest) should be present in drop down"
        isOptionInDropDown(UPCOMING_TRIP_SORT_DROP_DOWN, "Trip Name (A-Z)").shouldBe true, "Trip Name (A-Z) should be present in drop down"
        isOptionInDropDown(UPCOMING_TRIP_SORT_DROP_DOWN, "Trip Name (Z-A)").shouldBe true, "Trip Name (Z-A) should be present in drop down"
    }

    def changeSortingOptionDescendingByName() {
        chooseInDropDownByText(UPCOMING_TRIP_SORT_DROP_DOWN, "Trip Name (Z-A)")
        verifyPage()
    }

    def changeSortingOptionAscendingByName() {
        chooseInDropDownByText(UPCOMING_TRIP_SORT_DROP_DOWN, "Trip Name (A-Z)")
        verifyPage()
    }

    def verifyTripProductsListedByDateAndType() {
        verifyProductTypeSort()
        verifyAirItinerary()
        verifyHotelItinerary()
        verifyCarItinerary()
    }

    private void verifyCarItinerary() {
        validateDatesFormat(waitForElement(UPCOMING_CAR_DATES).getText().replace("-", "–"), ITINERARY_DATES, CAR_PRODUCT)
        TripManagement.validateCarRentalInfo(waitForElement(UPCOMING_CAR_DETAILS), carReservationData)
    }

    private void verifyHotelItinerary() {
        String hotelReservationName = waitForElement(UPCOMING_HOTEL_NAME).getText()
        TripManagement.validateHotelReservationName(hotelReservationName, hotelItineraryData)
        validateDatesFormat(waitForElement(UPCOMING_HOTEL_DATES).getText().replace("-", "–"), ITINERARY_DATES, HOTEL_PRODUCT)
    }

    private void verifyAirItinerary() {
        String flightReservationName = waitForElement(AIR_ITINERARY).getText()
        TripManagement.validateFlightReservationName(flightReservationName, scenarioState.firstAirReservation.itineraryData)
        WebElement secondTripDateRange = waitForElements(DATE_RANGE_TRIP_DETAILS).get(1)
        validateDatesFormat(secondTripDateRange.getText(), ITINERARY_DATES, AIR_PRODUCT)
    }

    private void verifyProductTypeSort() {
        List tripInformationContainers = waitForElements(TRIP_INFORMATION_CONTAINER)
        WebElement secondTripInfoContainer = tripInformationContainers.get(1)
        List<WebElement> tripProductsIcon = secondTripInfoContainer.findElements(TRIP_PRODUCTS_ICON)
        tripProductsIcon.get(0).getAttribute("alt").shouldContain "air", "The air product should be listed first"
        tripProductsIcon.get(1).getAttribute("alt").shouldContain "hotel", "The hotel product should be listed second"
        tripProductsIcon.get(2).getAttribute("alt").shouldContain "car", "Then car product should be listed third"
    }

    def void changePrimaryIrn() {
        WebElement parent = waitForElement(BILLING_INFORMATION)
        List elements = parent.findElements(By.name("makeIrnPrimary"))
        if (elements.size() > 0) {
            WebElement description = waitForElement(DESCRIPTION_IRN_OPTION)
            flow.primaryIRNSelected = description.getAttribute("value")
            elements.get(1).click()
        } else {
            println "There are not IRN to choose !!"
        }
    }

    def void clickOnUpdateBillingInfo() {
        waitForElement(SUBMIT_UPDATE_BILLING_INFO_FORM).click()
    }

    def void clickOnTravelerAccountLogout() {
        waitForElement(TRAVELER_ACCOUNT_LOGOUT).click()
    }

    def clickOnCreateFreeFlightTracker() {
        waitForElement(CREATE_FREE_FLIGHT_TRACKER_LINK).click()
    }

    def clickOnChangeReservationLink() {
        waitForElement(CHANGE_RESERVATION_LINK).click()
        verifyPage()
    }

    def clickOnCarProductFromUpcomingTrips() {
        waitForElement(CAR_PRODUCT_LINK).click()
        verifyPage()
    }

    def cancelCarProductFromUpcomingTrip() {
        waitForElement(CAR_CANCEL_LINK).click()
    }

    def cancelAirProductFromUpcomingTrip() {
        waitForElement(AIR_CANCEL_LINK).click()
    }

    def confirmCancelReservation() {
        waitForElement(CANCEL_CONFIRMATION).click()
    }

    def verifyConfirmCancellationInformation() {
        isElementWithTextPresent(CANCEL_CONFIRMATION_MODAL, "Please Confirm Your Cancellation").shouldBe true, "Please Confirm Your Cancellation label should be present"
        isElementWithTextPresent(CANCEL_CONFIRMATION_MODAL, "Things come up, and we understand that.\nSo, we'll cancel your reservation — free of charge. Just say the word.").shouldBe true, "The cancel confirmation message should be present"
        isElementWithTextPresent(CANCEL_CONFIRMATION_MODAL, "No, Do Not Cancel").shouldBe true, "The No button should be present"
        waitForElement(CANCEL_CONFIRMATION).getAttribute("alt").shouldBe "Yes, Cancel My Car", "The Yes button shuld be present"
    }

    def clickOnConfirmCancellation() {
        waitForElement(CANCEL_CONFIRMATION).click()
    }

    def clickOnChangeReservationFromUpcomingTrip() {
        waitForElement(UPCOMING_TRIP_CHANGE_RESERVATION_LINK).click()
        checkSomethingServed()
    }

    def clickOnChangeReservationFromAccountSnapshot() {
        waitForElement(ACCOUNT_SNAPSHOT_CHANGE_RESERVATION_LINK).click()
    }

    def verifySeePromotionalCertificatesUnavailableMessage() {
        waitForElement(AWARDED_PROMOTIONAL_CONTAINER).text.shouldContain PROMOTIONAL_CERTIFICATES_MESSAGE, "The ${PROMOTIONAL_CERTIFICATES_MESSAGE} should be present"
    }

    def verifySeePromotionalCertificates() {
        String text = waitForElement(AWARDED_PROMOTIONAL_CONTAINER)?.text
        assertTrue("Promo Certificates not found.", !text.empty)
    }

    def clickOnViewAllRegisteredPromotionsLink() {
        waitForElement(VIEW_ALL_ACTIVE_PROMOTIONS_LINK).click()
    }

    def verifyAccountSectionTitle(String title) {
        waitForElement(ACCOUNT_SECTION_TITLE).getText().shouldContain title, "The account section title should contain ${title}"
    }

    def verifyISeeMyTripUnderUpcomingTripsSection() {
        waitForElement(TRIP_NAME).getText().shouldContain flow.tripName, "The trip name should be ${flow.tripName}"
    }

    def changeSortingOptionDescendingByDate() {
        chooseInDropDownByText(UPCOMING_TRIP_SORT_DROP_DOWN, "Trip Date (Latest to Earliest)")
        verifyPage()
    }

    def verifyUpcomingTripServiceUnavailable() {
        isElementWithTextPresent(UPCOMING_TRIP_CONTENT, UPCOMING_TRIP_SERVICE_UNAVAILABLE_MSG).shouldBe true, "the Upcoming Trips box on My Travel Snapshot page should specify that my trips cannot be retrieved"
    }

    def verifyNoUpcomingTripsMessage() {
        isElementWithTextPresent(UPCOMING_TRIP_CONTENT, NO_UPCOMING_TRIPS_MESSAGE).shouldBe true, "The 'No Upcoming Trips message' is not present"
    }

    def verifyNoUpcomingFlightsMessageOnAccountBar() {
        isElementWithTextPresent(NO_UPCOMING_TRIP_CONTENT, NO_UPCOMING_FLIGHTS_MESSAGE_ACCOUNT_BAR).shouldBe true, "The 'No Upcoming flights message' is not present"
    }

    def verifyReissueAwardSuccessMessage() {
        verifyTextPresent(AWARD_SUCCESSFULLY_REISSUED, REISSUE_AWARD_SUCCESS_MESSAGE)
    }

    def clickViewAwardsLink() {
        waitForElement(VIEW_AWARDS_LINK).click()
        verifyPage()
    }

    def clickOnCreateAwardButton() {
        waitForElement(CREATE_AWARD_BUTTON).click()
    }

    def clickCompleteConversionSubmitButton() {
        waitForElement(COMPLETE_CONVERSION_SUBMIT_BUTTON).click()
    }

    def clickEditUserName() {
        waitForElement(EDIT_USERNAME).click()
    }

    def clickAddEditLink(String sectionName) {
        switch (sectionName) {
            case "personal":
                waitForElement(EDIT_ADD_PERSONAL_INFORMATION).click()
                break
            case "email":
                waitForElement(EDIT_ADD_EMAIL_INFORMATION).click()
                break
            case "address":
                waitForElement(EDIT_ADD_ADDRESS_INFORMATION).click()
                break
            case "phone":
                waitForElement(EDIT_ADD_PHONE_INFORMATION).click()
                break
            case "email subscription":
                waitForElement(ADD_EDIT_EMAIL_SUBSCRIPTION).click()
                break
            case "credit card information":
                waitForElement(ADD_EDIT_CC_INFO).click()
                break
            case "community info":
                waitForElement(EDIT_COMMUNITY_INFO).click()
                break
        }
    }

    def unfoldCompanionSection() {
        waitForElement(COMPANION_TRIPS_SELECTOR_ROW).findElement(COMPANION_TRIPS_DETAILS_BUTTON).click()
    }

    def viewCompanionPassDetails() {
        waitForElement(VIEW_DETAIL_BUTTON_SNAPSHOT).click()
    }

    def fillForm(Companion companion) {
        companionForm.fillForm(companion)
    }

    def submitForm() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def verifyCompanionSectionNotPresentOnSnapshotPage() {
        verifyElementNotPresent("Companion Pass section", COMPANION_SECTION_SNAPSHOT)
    }

    def verifyCompanionSectionNotPresentOnMyRRPage() {
        verifyElementNotPresent("Companion Pass achieved section", COMPANION_SECTION_MY_RR)
    }

    def clickUpdateButton() {
        waitForElement(By.id("submitButton")).click()
    }

    def verifyCountCreditCardIsEqualsTo(int i) {
        List<WebElement> elements = waitForElements(HEADER_FORM_CREDIT_CARD)
        assertEquals(elements.size(), i)
    }

    def clickCompanionConfirmationNumber() {
        waitForElement(COMPANION_CONFIRMATION_NUMBER).click()
    }

    def shouldSeeOopsMsgAboutMasterSessionTimeout(){
        WebElement oopsElement = getOopsElement()
        oopsElement.shouldNotBe null, "Oops element was not found"
        oopsElement.getText().shouldNotBe null, "Oops element text was null"
        oopsElement.getText().contains("session has expired")
    }

    def clickAccountBarMyTravelLink(){
        waitForElement(ACCOUNT_BAR_TOOLS_MY_TRAVEL_HEADER).click()
    }

    def clickAccountBarMyTravelCheckInOnline() {
        waitForElement(ACCOUNT_BAR_TOOLS_MY_TRAVEL_HEADER).click()
        waitForElement(ACCOUNT_BAR_MY_TRAVEL_CHECK_IN_ONLINE).click()
    }

    def clickCheckInButtonUnderUpcomingTripsOnMyAccount() {
        waitForElement(UPCOMING_TRIP_SUBMIT_BUTTON).click()
    }

    def shouldSeeLabelUpcomingTrips() {
        isElementWithTextPresent(SPAM_UPCOMING_TRIPS, "Upcoming Trips")
    }

    def shouldSeeLinkDepartureArrivalCitiesOfOutboundFlight() {
        isElementWithTextPresent(LINK_DEPARTURE_AND_ARRIVAL_CITIES_NAMES, "Dallas, TX to Chicago, IL")
    }

    def shouldSeeLinkDepartureArrivalCitiesOfInboundFlight() {
        isElementWithTextPresent(LINK_DEPARTURE_AND_ARRIVAL_CITIES_NAMES, "Houston, TX to Dallas, TX")
    }

    def shouldSeeDepartureDateAndConfirmationOfCurrentFlight() {

        AirReservation airReservation = scenarioState.getLastAirReservation()
        String confirmationNumber = airReservation.adultPnr

        StringBuffer departureDateText = new StringBuffer()
        departureDateText.append(new SimpleDateFormat("MM/dd").format(itineraryData.departureDate))
        departureDateText.append(" - Confirmation #")
        departureDateText.append(confirmationNumber)

        isElementWithTextPresent(UPCOMING_TRIP_DATE_AND_CONFIRMATION, departureDateText.toString())
    }

    def shouldSeeDepartureAndArrivalTime() {

        LocalTime departureTime = LocalTime.parse(itineraryData.departureTime.toString())
        LocalTime returnTime = LocalTime.parse(itineraryData.returnTime.toString())

        StringBuffer departureAndArrivalTimeText = new StringBuffer()
        departureAndArrivalTimeText.append("Dep ")
        departureAndArrivalTimeText.append(departureTime.toString("HH:mm aa"))
        departureAndArrivalTimeText.append(" | ")
        departureAndArrivalTimeText.append("Arr ")
        departureAndArrivalTimeText.append(returnTime.toString("HH:mm aa"))

        isElementWithTextPresent(UPCOMING_TRIP_DEP_AND_ARR_TIME, departureAndArrivalTimeText.toString())
    }

    def clickLinkDepartureArrivalCitiesOfInboundFlight() {
        waitForElement(LINK_DEPARTURE_AND_ARRIVAL_CITIES_NAMES).click()
    }

    def verifyIamOnTheTripDetailsPagePage() {
        verifyIamOnTheCorrectPage(TRIP_DETAILS_URL)
    }

    def shouldSeeLastActivityDate() {
        waitForElement(LAST_ACTIVITY_DATE).getText().contains("Last Activity: ")
    }

    def shouldSeeAccountExpirationDate(String text) {
        waitForElement(ACCOUNT_EXPIRATION_DATE).getText().equalsIgnoreCase text
    }

    def clickOnLastActivityDateHelpIcon() {
        waitForElement(LAST_ACTIVTY_DATE_HELP_ICON).click()
    }

    def shouldSeeLastActivityDatePopUp() {
        isElementDisplayed(LAST_ACTIVITY_HELP_POPUP)
    }

    def clickOnViewDetailsIcon(){
        waitForElement(VIEW_DETAILS_ICON).click()
    }

    def clickOnAddAnotherPhoneNumber() {
        waitForElement(ADD_ANOTHER_PHONE_NUMBER).click()
    }

    def verifyTextBoxForFirstNewPhoneNumberIsEmpty() {
        waitForElement(FIRST_ANOTHER_PHONE_NUMBER_INPUT_TEXT_BOX).getText().isEmpty().shouldBe true, "ERROR: text box filled with something and is expected empty"
    }

    public List<WebElement> retrieveCCinformation() {
        return waitForElements(CONTACT_INFORMATION)
    }

    def storeCreditCardBillingAdress() {
        rrContactInformation.creditCardBillingAddress = waitForElement(BILLING_ADDRESS).getAttribute("value")
        rrContactInformation.creditCardBillingCity = waitForElement(CREDIT_CARD_CITY).getAttribute("value")
        rrContactInformation.creditCardBillingState = waitForElement(CREDIT_CARD_STATE).getAttribute("value")
        rrContactInformation.creditCardBillingZipCode = waitForElement(CREDIT_CARD_ZIP_CODE).getAttribute("value")
        rrContactInformation.creditCardBillingCountry = waitForElement(CREDIT_CARD_COUNTRY).getAttribute("value")
    }

    def upcomingTripConfirmation(String confirmation="Confirmation"){
        isElementWithTextPresent(UPCOMING_TRIP_DATE_AND_CONFIRMATION,confirmation).shouldBe(true,"There are not upcoming trips")
    }

    def addBuddyData() {
        fillIn(BUDDY_NAME0, SHARE_YOUR_ITINERARY_BUDDY_NAME_VALUE)
        fillIn(BUDDY_EMAIL0, SHARE_YOUR_ITINERARY_BUDDY_EMAIL_VALUE)
    }

    def clickOnUpdateCommunicationPreferences() {
        waitForElement(UPDATE_BUTTON).click()
    }

    def fillPreferredMethodOfContactSection(){
        fillContactMethodCallMe()
    }

    private void fillContactMethodCallMe() {
        chooseInDropDownByValue(CONTACT_METHOD_DROP_DOWN, "CALL_ME")
        chooseInDropDownByValue(CONTACT_COUNTRY_PREFIX, "US")
        waitForElement(CONTACT_COUNTRY_PREFIX).displayed
        fillUSPhoneNumber(PURCHASE_MODULE_PHONE_NUMBER)
    }

    def updateCommunicationPreferences(){
        waitForElement(UPDATE_BUTTON).click()
        verifyPage()
    }

    private void fillUSPhoneNumber(String number) {
        String[] numbers = number.split("-")
        getContactAreaCode().sendKeys DELETE_EXISTING + numbers[0]
        getContactPrefix().sendKeys DELETE_EXISTING + numbers[1]
        getContactPhoneNumber().sendKeys DELETE_EXISTING + numbers[2]
    }

    private WebElement getContactAreaCode(){
        return waitForElement(CONTACT_AREA_CODE)
    }

    private WebElement getContactPrefix(){
        return waitForElement(CONTACT_PREFIX)
    }

    private WebElement getContactPhoneNumber(){
        return waitForElement(CONTACT_PHONE_NUMBER)
    }
}
