package pages

import com.swacorp.common.utility.SwaUtils
import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.BillingData
import domain.AirReservation
import domain.Guardian
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import fixtures.air.ReservationSpecification
import org.jbehave.core.failures.RestartingScenarioFailure
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import pages.elements.AccountBar
import pages.elements.AutoCompleteWidget
import pages.elements.CreditCardSubForm
import pages.elements.ExchangedTicketFlyout
import pages.elements.FeeCalculator
import pages.elements.TravelFundsFlyout
import pages.elements.PurchaseSubForm
import pages.elements.ShoppingCart
import state.Flow
import state.PassengerData
import util.ItineraryData
import util.PageName
import util.PricePageData
import util.RRContactInformation
import state.ScenarioState
import util.SelectFlightsPageData
import util.TripManagement
import util.PurchasePageData

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static java.util.Calendar.YEAR
import com.swacorp.dotcom.webscenarios.air.CreditCard

import static util.Locators.BREADCRUMB_IDS
import pages.mixins.*
import util.CustomerInfoData
import java.text.DecimalFormat
import steps.conditional.TogglePreferenceCenter

@Mixin(PurchaseVerifications.class)
class PurchasePage extends BasePage {
    public static final String CSS_CLASS_FOR_NON_ERROR_OOPS = "errors_no_image"
    private static final String INFANT_TRAVELING_ALONE_OOPS = "Children under the age of five are not permitted to fly unaccompanied. If the child is traveling with an adult (age 12 or older), please add the passenger to your itinerary or call 1-800-I-FLY-SWA to make a reservation"
    private static final String UM_WITH_CHANGE_PLANES_OOPS = "Unaccompanied Minors can only travel on nonstop flights or flights that do not require a change of planes or flight number. Please update your flight selection to meet this criteria."
    private static final String SENIOR_INVALID_DOB_OOPS = "The date of birth entered indicates that one of the Senior Fare passengers listed is not eligible to purchase a Senior Fare. Please correct the passengers date of birth, otherwise, select a new fare type for the passengers. Learn More."
    private static final String EARLY_BIRD_UM_NOT_ELEGIBLE = "Unaccompanied Minors are not eligible for EarlyBird Check-In since they are required to preboard the flight."
    private static final String APPLY_TRAVEL_FOUNDS_ERROR = "We can only apply travel funds to your airfare. Please enter your credit card information."
    private static final String PAYPAL_ADDONS_MESSAGE = "(Itineraries with add-ons may not use PayPal)"
    private static final By USE_CREDIT_CARD_ERROR_CLASS = By.className("useCreditCard")
    private static final By YOUNG_TRAVELER_MODAL_MESSAGE = By.className("youngTravelerModalMessage")
    private static final By GUARDIAN_POP_UP_CLASS = By.className("popInset")
    private static final By PAYPAL_LABEL_MESSAGE_CLASS = By.className("paypalAlaCarteMessage")
    private static final String UNACCOMPANIED_CHILDREN_OOPS = "Children under the age of five are not permitted to fly unaccompanied or under the care of another unaccompanied minor. If the child(ren) are traveling with an adult (age 12 or older), please add the passenger to your itinerary or call 1-800-I-FLY-SWA to make a reservation"
    private static final By PASSENGER_INFO_FIELDS = By.className("passengerInfoFields")
    private static final String PASSENGER_FIRST_NAME = "firstName"
    private static final String PASSENGER_LAST_NAME = "lastName"
    private static final String PASSENGER_RR_NUMBER = "rapidRewardsNumber"
    private static final String PASSENGER_BIRTH_MONTH = "dobMonth"
    private static final String PASSENGER_BIRTH_DAY = "dobDay"
    private static final String PASSENGER_BIRTH_YEAR = "dobYear"
    private static final String PASSENGER_GENDER = "gender"
    private static final By SHARE_YOUR_ITINERARY_BUDDY_ONE = By.id("js-available-emails0")
    private static final String SHARE_YOUR_ITINERARY_BUDDY_NAME_VALUE = "buddy1"
    private static final String SHARE_YOUR_ITINERARY_BUDDY_EMAIL_VALUE = "buddy1@buddy.com"
    private static final String PURCHASE_MODULE_PHONE_NUM = "352-505-0935"
    private static final By PURCHASE_CONTACT_AREA_CODE = By.id("js-us-phone-area-code")
    private static final By PURCHASE_CONTACT_PREFIX = By.id("js-us-phone-prefix")
    private static final By PURCHASE_CONTACT_PHONE_NUMBER = By.id("js-us-phone-line-number")
    private static final By HOW_CAN_WE_CONTACT_SECTION = By.className("preferred-method-of-contact--header-section")
    private static final By EDIT_PASSENGER_INFO_BUTTON_NAME = By.name("editPassengerInfo")
    private static final By APPLY_LUV_VOUCHER_FUNDS_BUTTON_NAME = By.name("applyLUVVoucherFunds")
    private static final By EARLY_BIRD_UNAVAILABLE_MESSAGE_FOR_AIRTRAN = By.xpath("//div[@id='earlyBirdContainer']//div[@id='ebPromo']")
    private static final By CREDIT_CARD_FIRST_NAME = By.id("creditCardFirstName")
    private static final By CREDIT_CARD_LAST_NAME = By.id("creditCardLastName")
    private static final By CREDIT_CARD_ADDRESS = By.id("creditCardAddress1")
    private static final By CREDIT_CARD_CITY = By.id("creditCardCity")
    private static final By CREDIT_CARD_STATE = By.id("creditCardState")
    private static final By CREDIT_CARD_ZIP1 = By.id("creditCardZip1")
    private static final By CREDIT_CARD_COUNTRY = By.id("creditCardCountry")
    private static final By CREDIT_CARD_BILLER_AREA_CODE = By.id("billerAreaCode")
    private static final By CREDIT_CARD_BILLER_PREFIX = By.id("billerPrefix")
    private static final By CREDIT_CARD_BILLER_USPHONE_LINE_NUMBER = By.id("billerUsPhoneLineNumber")
    private static final By EMAIL_CONFIRMATION_ADDRESS = By.id("emailConfirmationAddress")
    private static final By EARLY_BIRD_YES_RADIO_BUTTON = By.id("addEarlyBirdYes")
    private static final By CAR_DRIVER_LAST_NAME = By.id("carDriverLastName")
    private static final By CREDIT_CARD_INFORMATION = By.id("credit_card_information")
    private static final By CAR_DRIVER_FIRST_NAME = By.id("carDriverFirstName")
    private static final By HOTEL_GUEST_LAST_NAME = By.id("hotelGuestLastName")
    private static final By HOTEL_GUEST_FIRST_NAME = By.id("hotelGuestFirstName")
    private static final By PAY_PAL_RADIO_BUTTON = By.id("payPalRadioButton")
    private static final By STORED_FORM_OF_PAYMENT = By.id("storedFormOfPayment")
    private static final By STORED_PAYMENT_SECURITY_CODE = By.id("storedPaymentSecurityCode")
    private static final By UM_FUNDS_MESSAGE = By.id("earlyBirdAddFundsError")
    private static final By UM_MODAL_MESSAGE = By.className("unaccompaniedMinorModalMessage")
    private static final By YT_MODAL = By.id("youngTravelerModal")
    private static final By UM_MODAL_TITLE = By.cssSelector("#unaccompaniedMinorModal h5")
    private static final By UM_NO_BUTTON = By.id("minorNoButton")
    private static final By YT_NO_BUTTON = By.id("ytNoButton")
    private static final By YT_YES_BUTTON = By.id("ytYesButton")
    private static final By SUBMIT_BUTTON = By.id("visibleSubmitButton")
    private static final By AIRTRAN_LINK = By.id("airTranLink")
    private static final By ADD_EDIT_DISABILITIES = By.id("disability_button0")
    private static final By DISABILITY_OPTIONS_LIST = By.id("disabilityOptionsList0")
    private static final By VIEW_PARENT_GUARDIAN_INFO_LINK = By.id("viewParentGuardianInfo")
    private static final By EDIT_PARENT_GUARDIAN_INFO_LINK = By.id("editParentGuardianInfo")
    private static final By EARLY_BIRD_PROMO = By.id("ebPromo")
    private static final By VOUCHER_NUMBER = By.id("voucherNumber")
    private static final By VOUCHER_SECURITY_CODE = By.id("voucherSecurityCode")
    private static final By UNACCOMPANIED_MINOR_ADD_FOUNDS_ID = By.id("unaccompaniedMinorAddFunds")
    private static final By SAVE_CREDIT_CARD_HOLDER_INFO = By.id("saveCreditCardHolderInfo")
    private static final By CONFIRMATION_NUMBER = By.id("confirmationNumber")
    private static final By FIRST_NAME = By.id("firstName")
    private static final By LAST_NAME = By.id("lastName")
    private static final By RETRIEVE_ITINERARY_SUBMIT_BUTTON = By.id("retrieveItinerarySubmitButton")
    private static final By UM_YES_BUTTON = By.id("minorYesButton")
    private static final By PRICE_SUBMIT_BUTTON = By.id("priceSubmitButton")
    private static final By TRIP_NAME_FIELDS = By.id("trip_name_fields")
    private static final By NAME_THIS_TRIP_FIELD = By.id("nameThisTrip")
    private static final By OPT_INTO_NEW_TRIP_NAME = By.id("optIntoNewTrip")
    private static final By OPT_INTO_EXISTING_TRIP = By.id("optIntoExistingTrip")
    private static final By TRIP_NAME_FIELD = By.id("tripName")
    private static final By EXISTING_TRIP_NAME = By.id("existingTripName")
    private static final By UM_NOTE = By.className("umNote")
    private static final String UM_NOTE_MSG = "Passenger(s) below will be traveling with a parent/guardian age 12 or older on confirmation number "
    private static final By EARLY_BIRD_TABLE_WRAPPER = By.id("earlyBirdTableWrapper")
    private static final By EARLY_BIRD_NOT_AVAILABLE = By.xpath("//div[@id='earlyBirdTableWrapper']//tr[@class='earlyBirdNotAvailable ']")
    private static final By EARLY_BIRD_AVAILABLE = By.xpath("//div[@id='earlyBirdTableWrapper']//tr[@class='earlyBirdAvailable earlyBirdInboundRow']")
    private static final By EARLY_BIRD_ALT_AVAILABLE = By.xpath("//table[@class='earlyBirdPurchaseTable']//*[@alt='EarlyBird Eligible']")
    private static final By CONTACT_AREA_CODE = TogglePreferenceCenter.isOn() ? By.id("js-us-phone-area-code") : By.id("contactAreaCode")
    private static final By CONTACT_PREFIX = TogglePreferenceCenter.isOn() ? By.id("js-us-phone-prefix") : By.id("contactPrefix")
    private static final By CONTACT_PHONE_NUMBER = TogglePreferenceCenter.isOn() ? By.id("js-us-phone-line-number") : By.id("usPhoneLineNumber")
    private static final String PURCHASE_MODULE_TIME_FORMAT_YEAR = "yyyy"
    private static final String PURCHASE_MODULE_TIME_FORMAT_DAY = "d"
    private static final String PURCHASE_MODULE_TIME_FORMAT_MONTH = "M"
    private static final By STORED_FORM_OF_PAYMENT_NAME = By.id("storedFormOfPaymentName")
    private static final By CONTACT_METHOD_DROP_DOWN = TogglePreferenceCenter.isOn() ? By.id("js-preferred-method-of-contact--contact-method") : By.id("contactMethod")
    private static final By SECOND_PASSENGER_MIDDLE_NAME = By.id("middleName1")
    private static final By ALTERNATE_AWARDS_LINK = By.xpath("//fieldset[@id='main_fs'][1]/p/a")
    private static final By A_FREEDOM_CUPON = By.xpath("//table[@class='transitional_awards_table'][2]//tr[@class='tableRowOdd'][1]//input")
    private static final By B_FREEDOM_CUPON = By.xpath("//table[@class='transitional_awards_table'][2]//tr[@class='tableRowOdd'][2]//input")
    private static final By AWARDS_SUBMIT_BUTTON = By.id("submitButton")
    private static final String RELATIVE_PATH = "/reservations/book-reservations.html"
    private static final By NEW_EMAIL_CONFIRMATION_ADDRESS = By.id("newEmailConfirmationAddress")
    private static final By CLICK_AND_SAVE_CHECKBOX = By.id("clickNsave")
	private static final By CHASE_INSTANT_CREDIT_BANNER = By.id("chase_ad")
    private static final By COMPANION_PASS = By.xpath("//ul[@id='companion_pass']")
    private static final By RR_SIGNUP_CHECKBOX = By.id("purchase-rr-signup-checkbox");
    private static final String PURCHASE_MODULE_PHONE_NUMBER = "123-123-1234"
    public static final By PURCHASE_SUMMARY_TOTAL_COST = By.id("purchaseSummaryTotalCost")
    public static final By PURCHASE_SUMMARY_TOTAL_FUNDS_APPLIED = By.id("purchaseSummaryTotalFundsApplied")
    public static final By PURCHASE_SUMMARY_TOTAL_COST_DUE_NOW = By.id("purchaseSummaryTotalCostDueNow")
    public static final By DISPLAY_TRAVEL_FUNDS_DETAILS_QUESTION_MARK = By.xpath("//a[@class='overlay-trigger']")
    public static final By DISPLAY_TRAVEL_FUNDS_DETAILS_FLY_OUT_HEADING = By.xpath("//div[@class='appliedTravelFundsWrapper']//div[@class='heading']")
    public static final By DISPLAY_TRAVEL_FUNDS_DETAILS_FLY_OUT_ARTICLE = By.xpath("//div[@class='appliedTravelFundsWrapper']//div[@class='article']")
    private static final By APPLIED_TOTAL_DUE = By.id("applyTravelFundsTotalAddonsCost")
    private static final By APPLIED_FUNDS_TABLE_TOTALS = By.cssSelector("#appliedFundsTableOther .air .total")
    private static final By SUBMIT_CONTAINER = By.className("submitContainer")
    private static final By PASSENGER_INFO = By.className("passengerInfoRow")
    private static final By GIFT_CARD_NUMBER = By.id("giftCardNumber")
    private static final By GIFT_CARD_SECURITY_CODE = By.id("giftCardSecurityCode")
    private static final By APPLY_GIFT_CARD_BUTTON = By.name("applyGiftCardFunds")
    private static final By TRAVEL_FUNDS_ROUTING_HEADER = By.className("tripRoute")
    private static final By TOTAL_AMOUNT_FOR_NEW_FLIGHT = By.cssSelector("#appliedFundsTableOther thead .total")
    private static final By AIR_CHARGES = By.id("tripBalanceWithoutExternalChangeFees")
    private static final By TRAVEL_FUNDS_TABLE = By.cssSelector("#appliedFundsTableOther .air td p")
    private static final By TRAVEL_FUNDS_FLYOUT = By.cssSelector(".air .overlay-trigger")
    private static final By MONTH_OF_BIRTH = By.id("dobMonth0")
    private static final By DAY_OF_BIRTH = By.id("dobDay0")
    private static final By GENDER = By.id("gender0")
    private static final By YEAR_OF_BIRTH = By.id("dobYear0")
    private static final By TOTAL_POINTS = By.id("purchaseSummaryTotalPoints")
    private static final By DISABILITY_BUTTON = By.id("disability_button0")
    private static final By FIRST_NAME_TEXT = By.id("firstName0")
    private static final By LAST_NAME_TEXT = By.id("lastName0")
    private static final By MIDDLE_NAME_TEXT = By.id("middleName0")
    private static final By SELECTED_OPTIONS = By.className("selected_options")
    private static final By PASSENGER_DOB = By.id("dob0")
    private static final By EDIT_PASSENGER_INFO = By.cssSelector("#passengerInfo .largeSubmitButtonWhite")
    private static final By NON_EDITABLE_DISABILITY_OPTIONS = By.id("disabilityAssistanceOptions0")
    private static final String SELECTED_DISABILITY_OPTIONS  = "Selected Disability Assistance Options:"
    private static final String DATE_FORMAT = "MM/dd/yyyy"
    private static final By TOTAL_DUE_POPUP_LINK = By.id("total_due_popup_link")
    private static final By ARTICLE = By.className("article")
    private static final String BUSINESS_FARE_TYPE = "BusinessSelect"
    private static final By RR_NUMBER = By.id("rapidRewardsNumber0")
    private static final By CC_RADIO_BUTTON = By.id("creditCard")
    private static final By EARLY_BIRD_OPTIONS = By.id("earlyBirdOptions")
    private static final By APPLY_FUNDS_BUTTON = By.id("applyTicketLessFundsButtonId")
    private static final By HOTEL_LOYALTY_NUMBER = By.id("hotelFrequentRenterNumber")
    private static final By PROMOCERT_EXPIRATION = By.cssSelector("#main_fs .expirationDate1")

    Data data
    Flow flow
    ScenarioState scenarioState
    PassengerData passengerData
    DisabilitiesPage disabilitiesPage
    PurchaseSubForm[] subForms
    PurchaseCardPage purchaseCardPage
    AccountBar accountBar
    CreditCardSubForm creditCardSubForm
    ShoppingCart shoppingCart
    PurchasePageData purchasePageData
    PricePageData pricePageData
    ItineraryData itineraryData
    RRContactInformation rrAccountData
    SelectFlightsPageData selectFlightsPageData
    CustomerInfoData customerInfoData
    BillingData billingData
    FeeCalculator feeCalculator
    AutoCompleteWidget autoCompleteWidget

    String getRelativePath() {
        return RELATIVE_PATH
    }

    def clickSaveCreditCardHolderInfo() {
       waitForElement(SAVE_CREDIT_CARD_HOLDER_INFO).click()
    }

    def PurchasePage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, "/reservations/book-reservations.html");
    }

    def fillInAllInformation(String ccType = "", CreditCard creditCard = null, String email = null) {

        def currentPage = getCurrentUrl()
        if (!currentPage.contains("book-reservations.html")) {
            if (getPageSource().contains("The itinerary selected does not allow enough time to make your connection")) {
                throw new RestartingScenarioFailure("Ooops error: The itinerary selected does not allow enough time to make your connection")
            }
            checkNoOops();
        }

        Arrays.sort(subForms)
        subForms.each {
            if (!ccType.isEmpty() && it instanceof CreditCardSubForm) {
                it.fillForm(ccType, creditCard)
            } else {
                it.fillForm()
            }
        }
        fillEmail(email)
    }

    def fillInAllInformationBasedOn(ReservationSpecification specification) {
        Arrays.sort(subForms)
        subForms.each {
            it.fillFormBasedOn(specification)
        }
        fillEmail()
    }

    def fillEmail(String email = null) {
        email=(email != null) ? email : "tester@wnco.com"
        if (!flow.isLoggedIn && !(flow.isRapidRewardsPointsPurchaseOnly || flow.isRapidRewards)) {
            waitForElement(EMAIL_CONFIRMATION_ADDRESS).click()
            waitForElement(EMAIL_CONFIRMATION_ADDRESS).sendKeys DELETE_EXISTING + email
        }
    }

    def clickVisibleSubmit(boolean isUM = false) {
        String title = getTitle()
        waitForElement(SUBMIT_BUTTON).click()
        if (!isUM) {
            waitForPageTitleToChangeOrOops(title)
        }
    }

    def clickVisibleSubmitAsUnaccompaniedMinor() {
        waitForElement(SUBMIT_BUTTON).click()
        checkSomethingServed()
    }

    def umModalShouldBeDisplayed() {
        retryUntilPresent(UM_MODAL_TITLE).shouldBe true, "Unaccompanied Minor Modal is not displayed."
    }

    def clickVisibleSubmitAsYoungTraveler() {
        waitForElement(SUBMIT_BUTTON).click()
        verifyPage()
        retryUntilPresent(YT_MODAL).shouldBe true, "Young Traveler Modal is not displayed."
    }

    def clickContinue() {
        clickVisibleSubmit()
    }

    def clickAddEarlyBird() {
        waitForElement(EARLY_BIRD_YES_RADIO_BUTTON).click()
        assertEquals("Appears that the purchase early bird radio button was not clicked since this did not display",true, waitForElement(EARLY_BIRD_TABLE_WRAPPER).isDisplayed())
    }

    def verifyTravelFundsFlyOut() {
        waitForElement(DISPLAY_TRAVEL_FUNDS_DETAILS_QUESTION_MARK).click()
        verifyElementPresent('Applied Travel Funds FlyOut Heading', DISPLAY_TRAVEL_FUNDS_DETAILS_FLY_OUT_HEADING)
        verifyElementPresent('Applied Travel Funds FlyOut Article', DISPLAY_TRAVEL_FUNDS_DETAILS_FLY_OUT_ARTICLE)
   }

    def verifyTravelFundsApplied() {
        verifyElementPresent('Applied Travel Funds', By.xpath("//h6[@class=\'appliedTravelFundsHeader_v2\' and contains (text(), \'Applied Travel Funds\')]"))
    }

    def verifyErrorTextPresent(String error) {
        verifyTextPresent(error, By.id("errors"))
    }

    def verifyCreditCardHolderInfoIsNotPresent() {
        WebElement creditCardInfo = waitForElement(CREDIT_CARD_INFORMATION)
        creditCardInfo.findElement(CREDIT_CARD_FIRST_NAME).getAttribute('value').shouldBeEmpty "Credit Card First Name should be empty"
        creditCardInfo.findElement(CREDIT_CARD_LAST_NAME).getAttribute('value').shouldBeEmpty "Credit Card Last Name should be empty"
        creditCardInfo.findElement(CREDIT_CARD_ADDRESS).getAttribute('value').shouldBeEmpty "Credit Card Address should be empty"
        creditCardInfo.findElement(CREDIT_CARD_CITY).getAttribute('value').shouldBeEmpty "Credit Card City should be empty"
        creditCardInfo.findElement(CREDIT_CARD_STATE).getAttribute('value').shouldBe "","Credit Card State should not be selected"
        creditCardInfo.findElement(CREDIT_CARD_ZIP1).getAttribute('value').shouldBeEmpty "Credit Card ZIP1 should be empty"
        creditCardInfo.findElement(CREDIT_CARD_COUNTRY).getAttribute('value').shouldBe "US","Credit Card Country should be selected"
        creditCardInfo.findElement(CREDIT_CARD_BILLER_AREA_CODE).getAttribute('value').shouldBeEmpty "Credit Card Biller Area Code should be empty"
        creditCardInfo.findElement(CREDIT_CARD_BILLER_PREFIX).getAttribute('value').shouldBeEmpty "Credit Card Biller Prefix should be empty"
        creditCardInfo.findElement(CREDIT_CARD_BILLER_USPHONE_LINE_NUMBER).getAttribute('value').shouldBeEmpty "Credit Card Biller US Phone Line Number should be empty"
    }

    def verifyCreditCardHolderInfoIsPresent() {
        WebElement creditCardInfo = waitForElement(CREDIT_CARD_INFORMATION)
        creditCardInfo.findElement(CREDIT_CARD_FIRST_NAME).getAttribute('value').shouldNotBeEmpty "Credit Card First Name should not be empty"
        creditCardInfo.findElement(CREDIT_CARD_LAST_NAME).getAttribute('value').shouldNotBeEmpty "Credit Card Last Name should not be empty"
        creditCardInfo.findElement(CREDIT_CARD_ADDRESS).getAttribute('value').shouldNotBeEmpty "Credit Card Address should not be empty"
        creditCardInfo.findElement(CREDIT_CARD_CITY).getAttribute('value').shouldNotBeEmpty "Credit Card City should not be empty"
        creditCardInfo.findElement(CREDIT_CARD_STATE).getAttribute('value').shouldNotBe "","Credit Card State should be selected"
        creditCardInfo.findElement(CREDIT_CARD_ZIP1).getAttribute('value').shouldNotBeEmpty "Credit Card ZIP1 should be empty"
        creditCardInfo.findElement(CREDIT_CARD_COUNTRY).getAttribute('value').shouldBe "US","Credit Card Country should be selected"
        creditCardInfo.findElement(CREDIT_CARD_BILLER_AREA_CODE).getAttribute('value').shouldNotBeEmpty "Credit Card Biller Area Code should not be empty"
        creditCardInfo.findElement(CREDIT_CARD_BILLER_PREFIX).getAttribute('value').shouldNotBeEmpty "Credit Card Biller Prefix should not be empty"
        creditCardInfo.findElement(CREDIT_CARD_BILLER_USPHONE_LINE_NUMBER).getAttribute('value').shouldNotBeEmpty "Credit Card Biller US Phone Line Number should not be empty"
    }

    def verifyInvalidConfirmationErrorMessages() {
        verifyTextPresent("We're sorry, we were unable to retrieve your funds from the information provided. Please verify the following:", By.id("errors"))
        verifyTextPresent("The confirmation number is entered correctly. Travel funds from Southwest flights booked through partner sites are not eligible for use on southwest.com.", By.id("errors"))
        verifyTextPresent("The passenger name on the reservation is entered correctly.", By.id("errors"))
        verifyTextPresent("If all information above has been verified, either we were unable to find the funds or they have expired.", By.id("errors"))
        verifyTextPresent("(SW500105)", By.id("errors"))
    }

    def verifyEarlyBirdNAForAirTran() {
        verifyElementPresent("EB n/a for AirTran segment", EARLY_BIRD_NOT_AVAILABLE)
    }

    def verifyEarlyBirdNotAvailable() {
        verifyElementNotPresent("EB not available", EARLY_BIRD_ALT_AVAILABLE)
    }

    def verifyEarlyBirdAvailableForSouthwest() {
        verifyElementPresent("EB for Southwest segment", EARLY_BIRD_AVAILABLE)
    }

    def verifyEarlyBirdAvailable() {
        verifyElementPresent("EB available segment", EARLY_BIRD_ALT_AVAILABLE)
    }

    def verifyEarlyBirdUnavailableMsgForAirTran() {
        waitForElement(EARLY_BIRD_UNAVAILABLE_MESSAGE_FOR_AIRTRAN).getText().shouldContain "EarlyBird Check-In is unavailable for purchase on flights operated by AirTran."
    }

    def enterWhoIsCheckingIn(String fName = data.firstName, String lName = data.lastName) {
        waitForElement(HOTEL_GUEST_FIRST_NAME).sendKeys(DELETE_EXISTING)
        waitForElement(HOTEL_GUEST_FIRST_NAME).sendKeys(fName) + TAB
        waitForElement(HOTEL_GUEST_LAST_NAME).sendKeys(DELETE_EXISTING)
        waitForElement(HOTEL_GUEST_LAST_NAME).sendKeys(lName) + TAB
    }

    def enterWhoIsDriving(String fName = data.firstName, String lName = data.lastName) {
        waitForElement(CAR_DRIVER_FIRST_NAME).sendKeys(DELETE_EXISTING)
        waitForElement(CAR_DRIVER_FIRST_NAME).sendKeys(fName) + TAB
        waitForElement(CAR_DRIVER_LAST_NAME).sendKeys(DELETE_EXISTING)
        waitForElement(CAR_DRIVER_LAST_NAME).sendKeys(lName) + TAB
    }

    public void fillInFirstLastName(String firstName, String lastName) {
        fillInFirstName(getWebElementForFirstName(0), firstName)
        fillInLastName(getWebElementForLastName(0), lastName)
    }

    def fillInFirstName(WebElement webElement, String firstName) {
        webElement.sendKeys DELETE_EXISTING + firstName
    }

    def fillInLastName(WebElement webElement, String lastName) {
        webElement.sendKeys DELETE_EXISTING + lastName
    }

    def fillInRapidRewardsNumber(String rapidRewardsNumber) {
        getWebElementForRapidRewardsNumber(0).sendKeys DELETE_EXISTING + rapidRewardsNumber
    }

    def getWebElementForFirstName(def firstNameIndex) {
        waitForElement(By.id("firstName${firstNameIndex}"))
    }

    def getWebElementForLastName(def lastNameIndex) {
        waitForElement(By.id("lastName${lastNameIndex}"))
    }

    def getWebElementForRapidRewardsNumber(def rapidRewardsNumberIndex) {
        waitForElement(By.id("rapidRewardsNumber${rapidRewardsNumberIndex}"))
    }

    void verifyRRPassengerInfo(def passengerIndex = "0") {
        WebElement passengerInfo = waitForElement(PASSENGER_INFO_FIELDS)
        passengerInfo.findElement(By.id(PASSENGER_FIRST_NAME + passengerIndex)).getAttribute("value").shouldBe flow.rrUser.firstName, "Passenger's first name is different than Rapid Rewards member's first name."
        passengerInfo.findElement(By.id(PASSENGER_LAST_NAME + passengerIndex)).getAttribute("value").shouldBe flow.rrUser.lastName, "Passenger's last name is different than Rapid Rewards member's last name."
        passengerInfo.findElement(By.id(PASSENGER_RR_NUMBER + passengerIndex)).getAttribute("value").shouldBe flow.rrUser.number, "Passenger's Rapid Rewards number is different than Rapid Rewards member's number."
        passengerInfo.findElement(By.id(PASSENGER_BIRTH_MONTH + passengerIndex)).getAttribute("value").shouldBe flow.getRrUser().getDateOfBirth().format(PURCHASE_MODULE_TIME_FORMAT_MONTH), "Passenger's month of birth is different than Rapid Rewards member's month of birth."
        passengerInfo.findElement(By.id(PASSENGER_BIRTH_DAY + passengerIndex)).getAttribute("value").shouldBe flow.getRrUser().getDateOfBirth().format(PURCHASE_MODULE_TIME_FORMAT_DAY), "Passenger's day of birth is different than Rapid Rewards member's day of birth."
        passengerInfo.findElement(By.id(PASSENGER_BIRTH_YEAR + passengerIndex)).getAttribute("value").shouldBe flow.getRrUser().getDateOfBirth().format(PURCHASE_MODULE_TIME_FORMAT_YEAR), "Passenger's year of birth is different than Rapid Rewards member's year of birth."
        passengerInfo.findElement(By.id(PASSENGER_GENDER + passengerIndex)).getAttribute("value").toUpperCase().shouldBe flow.rrUser.gender.toUpperCase(), "Passenger's gender is different than Rapid Rewards member's gender."
    }

    def payPalIsEnabled() {
        waitForElement(PAY_PAL_RADIO_BUTTON).isEnabled()
    }

    def changeYearForUM(boolean under5 = false) {
        def date = new Date()
        def age = date[YEAR] - (under5 ? 4 : 7)
        chooseInDropDownByValue("dobYear0", age.toString())
    }

    def selectPayPal() {
        waitForElement(PAY_PAL_RADIO_BUTTON).click()
    }

    def creditCardInformationIsDisplayed() {
        waitForElement(CREDIT_CARD_INFORMATION).isDisplayed()
    }

    def umFundsMsgIsDisplayed() {
        waitForElement(UM_FUNDS_MESSAGE).isDisplayed()
    }

    def storedFormOfPaymentIsSelected() {
        waitForElement(STORED_FORM_OF_PAYMENT).isSelected()
    }

    void verifyUm5To11OopsMessage() {
        shouldShowOopsMessage("At least one passenger traveling must be 12 years or older if flying on itinerary including AirTran.")
    }

    void verifyYoungTravelersModalAndClickNoButton() {
        verifyYoungTravelersModalMessage()
        waitForElement(YT_NO_BUTTON).click()
    }

    void verifyYoungTravelersModalMessage() {//TODO review this - modal message should be a child of modal
        retryUntilPresent(YT_MODAL).shouldBe true, "The modal for passengers under age 18 traveling without an adult was expected, but is not present."
        waitForElement(YOUNG_TRAVELER_MODAL_MESSAGE).getText().shouldContain "Will there be a person age 18 or older traveling with the Young Traveler(s)?"
    }

    void verifyUmUnder5OopsMessage() {
        if (retryUntilPresent(UM_MODAL_TITLE)) {
            waitForElement(UM_NO_BUTTON).click()
        }
        shouldShowOopsMessage("Children under the age of five are not permitted to fly unaccompanied. If the child is traveling with an adult (age 12 or older), please add the passenger to your itinerary or call 1-800-I-FLY-SWA to make a reservation.")
    }

    void verifyUnaccompaniedChildrenOopsMessage() {
        shouldShowOopsMessage(UNACCOMPANIED_CHILDREN_OOPS)
    }

    void selectAddAccompanyingTravelerToMinor() {
        waitForElement(UM_YES_BUTTON).click()
    }

    void enterNewTripName(String tripName) {
        ensureNameThisTripCheckBoxIsChecked()
        waitForElement(TRIP_NAME_FIELD).sendKeys DELETE_EXISTING + tripName
    }

    void selectExistingTripName(String tripName) {
        ensureNameThisTripCheckBoxIsChecked()
        waitForElement(OPT_INTO_EXISTING_TRIP).click()
        chooseInDropDownByText("existingTripName", tripName)
    }

    void verifyNameThisTripIsChecked() {
        isCheckboxChecked(NAME_THIS_TRIP_FIELD).shouldBe true, "The name this trip checkbox was expected selected"
    }

    void verifyNameThisTripIsNotChecked() {
        isCheckboxChecked(NAME_THIS_TRIP_FIELD).shouldBe false, "The name this trip checkbox was expected not selected"
    }

    void verifyNameThisTripOptionIsNotOffered(){
        WebElement container = waitForElement(TRIP_NAME_FIELDS)

        container.findElement(NAME_THIS_TRIP_FIELD).isSelected().shouldBe false,
                    "New Trip Name Field should not be selected"
        container.findElement(TRIP_NAME_FIELD).isDisplayed().shouldBe false, "The trip name field should not be visible"
        container.findElement(OPT_INTO_NEW_TRIP_NAME).isDisplayed().shouldBe false, "The new trip name radio button should not be visible"
    }

    void verifyNewTripNameIsChecked() {
        isCheckboxChecked(OPT_INTO_NEW_TRIP_NAME).shouldBe true, "The new trip name radio button is not selected"
    }

    void verifyTripNameFieldIsNotPresent() {
        waitForElement(TRIP_NAME_FIELD).isDisplayed().shouldBe false, "The trip name field was no expected"
    }

    void verifyExistingTripsNotPresent() {
        isElementPresent(EXISTING_TRIP_NAME).shouldBe false, "The existing trip name drop down was no expected"
    }

    void verifyExistingTripsIsPresent() {
        isElementPresent(EXISTING_TRIP_NAME).shouldBe true, "The existing trip name drop down is not displayed"
    }

    boolean isDefaultTripName() {
        return waitForElement(TRIP_NAME_FIELD).getAttribute("value").matches(TripManagement.DEFAULT_TRIP_NAME)
    }

    public def ensureNameThisTripCheckBoxIsChecked() {
        ensureCheckboxIsChecked(NAME_THIS_TRIP_FIELD)
    }

    public void verifyTripNameSectionIsEnabled() {
        waitForElement(By.id("tripNameContainer")).getAttribute("style").shouldContain "block", "name trip was not enabled"
    }

    public void verifyNoNeedForExtraSeatForPassengerOfSizeMessagePresent() {
        String errorBoxClassName =  getOopsElement().findElement(By.xpath("ul[@id='errors']")).getAttribute("class")
        assertEquals(CSS_CLASS_FOR_NON_ERROR_OOPS, errorBoxClassName)

        String messageText = getOopsElement().getText().toLowerCase()
        messageText.shouldContain "one seat", "Oops box message text doesn't talk about one seat"
        messageText.shouldContain "business", "Oops box message text doesn't talk about business"
        messageText.shouldContain "middle name", "Oops box message text doesn't talk about middle name"
    }

    public void pageMessage(List<String> oopsMessage) {
        for (msg in oopsMessage) {
            getOopsElement().getText().contains(msg).shouldBe true
        }
    }

    void bookFlightAsUm(String payment = "Credit_Card", boolean under5 = false) {
        if (payment == "Travel_Funds") {
            subForms.each {if (it instanceof pages.elements.PassengerInfo) {it.fillInDobAndGender()}}
            subForms.find({it instanceof pages.elements.BillingInfo}).fillForm()
            chooseInDropDownByValue("creditCardState", "TX")
        } else {
            subForms.each {if (it instanceof pages.elements.PassengerInfo) {it.fillForm()}}
        }

        changeYearForUM(under5)
        subForms.each {if (it instanceof pages.elements.ContactInfo) {it.fillForm()}}

        if (payment == "Credit_Card") {
            subForms.each {
                if (it instanceof CreditCardSubForm || it instanceof pages.elements.BillingInfo) {
                    it.fillForm()
                }
            }
        } else {
            if (payment == "PayPal") {
                selectPayPal()
            }
        }
        fillEmail()
        clickVisibleSubmitAsUnaccompaniedMinor()
    }

    def verifyPayPalIsEnabled() {
        payPalIsEnabled().shouldBe true
    }

    def verifyUmTravelFundsMsgIsVisible() {
        umFundsMsgIsDisplayed().shouldBe true
    }

    def clickNoUMModal() {
        waitForElement(UM_NO_BUTTON).click()
    }

    def fillInCreditCardOwner(String firstName, String lastName) {
        WebElement creditCardInformation = waitForElement(CREDIT_CARD_INFORMATION)
        creditCardInformation.findElement(CREDIT_CARD_FIRST_NAME).sendKeys DELETE_EXISTING + firstName
        creditCardInformation.findElement(CREDIT_CARD_LAST_NAME).sendKeys DELETE_EXISTING + lastName
    }

    def verifyCreditCardInformationIsVisible() {
        creditCardInformationIsDisplayed().shouldBe true
    }

    def verifyStoredFormOfPaymentIsSelected() {
        storedFormOfPaymentIsSelected().shouldBe true
    }

    def shouldNotBookFlightAsUm() {
        bookFlightAsUm("Credit_Card", false)
    }

    def shouldBookFlightAsUm() {
        bookFlightAsUm("Credit_Card", true)
    }

    void retrieveAccompanyingAdultPNR(String pnr, String pnrFirstName, String pnrLastName) {
        fillIn(CONFIRMATION_NUMBER, pnr)
        fillIn(FIRST_NAME, pnrFirstName)
        fillIn(LAST_NAME, pnrLastName)
        waitForElement(RETRIEVE_ITINERARY_SUBMIT_BUTTON).click()
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

    def verifyTripNameIsFormattedByDefault() {
        isDefaultTripName().shouldBe true, "Default trip name is not correctly formatted"
    }

    String getTripNameFieldValue() {
        return waitForElement(TRIP_NAME_FIELD).getAttribute("value")
    }

    def verifyExistingTripNameOnDropDown(String tripName) {
        isOptionInDropDown("existingTripName", tripName).shouldBe true, tripName + " was expected in existing trips"
    }

    def addDisability() {
        waitForElement(ADD_EDIT_DISABILITIES).click()
        disabilitiesPage.addAssistanceWithWheelchair()
        disabilitiesPage.submit()
    }

    def verifyDisabilityInPassengerInformation() {
        WebElement disabilityElement = waitForElement(DISABILITY_OPTIONS_LIST)
        disabilityElement.getText().shouldContain "Can walk but need assistance to and from gate", "Disability options not displayed."
    }

    def verifyBlindDisability() {
        WebElement disabilityElement = waitForElement(DISABILITY_OPTIONS_LIST)
        disabilityElement.getText().shouldContain "Blind or have low vision", "Blind or low vision Disability option not displayed."
    }

    def verifyAssistanceRequestedText(){
        waitForElement(SELECTED_OPTIONS).text.shouldContain  SELECTED_DISABILITY_OPTIONS,
                "Assistance Requested text not present on Purchase page"
    }

    def addSSROptions(String paxIdx, String options) {
        waitForElement(By.id("disability_button"+paxIdx)).click()
        disabilitiesPage.addSSROptions(options)
        disabilitiesPage.submit()
    }

    def verifyOopsMessageForUmYTOnSwaBiz() {
        shouldShowOopsMessage("At least one passenger traveling must be 18 years or older if booking an itinerary on swabiz.com")
    }

    def uncheckNameThisTripOption() {
        if (waitForElement(NAME_THIS_TRIP_FIELD)?.isSelected()) {
            waitForElement(NAME_THIS_TRIP_FIELD).click()
        }
    }

    def clickNoButtonOnUmModal() {
        retryUntilPresent(UM_NO_BUTTON)
        waitForElement(UM_NO_BUTTON).click()
    }

    def clickYesButtonOnUmModal() {
        retryUntilPresent(UM_YES_BUTTON)
        waitForElement(UM_YES_BUTTON,true,60,360).click()
        verifyPage()
    }

    def verifyUmTranvelAloneOopsMessage() {
        getExpectedOopsMessage().shouldContain INFANT_TRAVELING_ALONE_OOPS, "The oops message should be " + INFANT_TRAVELING_ALONE_OOPS
    }

    def verifyUmWithChangePlanesOopsMessage() {
        String oopsText = getExpectedOopsMessage()
        oopsText.shouldContain UM_WITH_CHANGE_PLANES_OOPS
    }

    void clickOnTheNoButtonInYTModal() {
        retryUntilPresent(YT_NO_BUTTON)
        waitForElement(YT_NO_BUTTON).click()
        verifyPage()
    }

    void clickOnTheYesButtonInYTModal() {
        waitForElement(YT_YES_BUTTON).click()
        waitForUrlToChangeOrOops(getCurrentUrl())
    }

    void clickOnViewParentGuardianInfoLink() {
        waitForElement(VIEW_PARENT_GUARDIAN_INFO_LINK).click()
        verifyPage()
    }

    void clickOnEditParentGuardianInfoLink() {
        waitForElement(EDIT_PARENT_GUARDIAN_INFO_LINK).click()
    }

    boolean isEditPassengerInfoButtonDisplayed() {
        isElementPresent(EDIT_PASSENGER_INFO_BUTTON_NAME, 25)
    }

    void clickOnEditPassengerInfoButton() {
        waitForElement(EDIT_PASSENGER_INFO_BUTTON_NAME).click()
        verifyPage()
    }

    void verifyUMGuardianInfoPopUp(Guardian dropOffGuardian, Guardian pickUpGuardian, Guardian alternateGuardian) {
        switchToWindow("Southwest Airlines - Parent/Guardian Information")
        GuardianPopup popup = new GuardianPopup(waitForElement(GUARDIAN_POP_UP_CLASS))
        popup.verifyTitle()
        popup.verifyUMDescription()
        popup.verifyUMGuardiansInfo(dropOffGuardian, pickUpGuardian, alternateGuardian)
        switchToWindow("Southwest Airlines - Payment Information")
    }

    void verifyYTGuardianInfoPopUp(Guardian guardian) {
        switchToWindow("Southwest Airlines - Parent/Guardian Information")
        GuardianPopup popup = new GuardianPopup(waitForElement(GUARDIAN_POP_UP_CLASS))
        popup.verifyTitle()
        popup.verifyYTDescription()
        popup.verifyYTGuardianInfo(guardian)
        switchToWindow("Southwest Airlines - Payment Information")
    }

    void verifyUMNotElegibleForEBCheckIn() {
        waitForElement(EARLY_BIRD_PROMO).getText().shouldBe EARLY_BIRD_UM_NOT_ELEGIBLE, "The message shoold be ${EARLY_BIRD_UM_NOT_ELEGIBLE}"
    }

    def goToConfirmationPage() {
        waitForElement(By.id("visibleSubmitButton")).click()
    }

    def goToCarPurchasePage() {
        flow.isCarReservationPresent = true
        waitForElement(PRICE_SUBMIT_BUTTON).click()
        verifyPage()
    }

    void verifyRadioButtonClickOnCarPurchasePage(String tripType) {
        if (tripType == "New Trip") {

            waitForElement(OPT_INTO_NEW_TRIP_NAME).getAttribute("checked").shouldBe "true",
                    "New Trip Name Field is not Selected"
        } else {

            waitForElement(OPT_INTO_EXISTING_TRIP).getAttribute("checked").shouldBe "true",
                    "Add to Existing Trip Field is not Selected"
        }

    }

    void verifyFieldIsVisibleOnCarPurchasePage(String fieldVisible) {
        if (fieldVisible == "New Trip") {

            waitForElement(TRIP_NAME_FIELD).getAttribute("disabled").shouldBe "false",
                    "New Trip Name field Not Visible"
            waitForElement(EXISTING_TRIP_NAME).getAttribute("disabled").shouldBe "true",
                    "Add to Exisiting Trip field is not Greyed out"
        } else {

            waitForElement(EXISTING_TRIP_NAME).getAttribute("disabled").shouldBe "false",
                    "Add to Existing Trip field Not Visible"
            waitForElement(TRIP_NAME_FIELD).getAttribute("disabled").shouldBe "true",
                    "New Trip Name field is not Greyed out"
        }
    }

    void clickAddToExistingTrip() {
        waitForElement(OPT_INTO_EXISTING_TRIP).click()
    }

    void fillVoucherAndApply() {
        waitForElement(VOUCHER_NUMBER).sendKeys DELETE_EXISTING + data.getLuvVoucher("500Thousand").voucherNumber ?: "9100000014263206"
        waitForElement(VOUCHER_SECURITY_CODE).sendKeys DELETE_EXISTING + data.getLuvVoucher("500Thousand").securityCode ?:"8041"
        waitForElement(APPLY_LUV_VOUCHER_FUNDS_BUTTON_NAME).click()
    }

    void verifyErrorAboutApplyTravelFounds() {
        waitForElement(USE_CREDIT_CARD_ERROR_CLASS).getText().shouldBe APPLY_TRAVEL_FOUNDS_ERROR, "The error should be ${APPLY_TRAVEL_FOUNDS_ERROR}"
    }

    void verifyUMChargeOnApplyTravelFounds() {
        String umCharge = waitForElement(UNACCOMPANIED_MINOR_ADD_FOUNDS_ID).getText()
        umCharge.shouldContain "Unaccompanied Minor Charge", "The text 'Unaccompanied Minor Charge' should be present"
        umCharge.shouldContain "100.00", "The charge '100.00' should be present"
    }

    void verifyPayPalIsDisabled() {
        payPalIsEnabled().shouldBe false, "The PayPal option should be disabled"
    }

    void verifyPayPalMessageAboutAddOns() {
        waitForElement(PAYPAL_LABEL_MESSAGE_CLASS).getText().shouldBe PAYPAL_ADDONS_MESSAGE, "The '${PAYPAL_ADDONS_MESSAGE}' should be present"
    }

    void fillInInformationForUserLoggedIn() {
        def currentPage = getCurrentUrl()
        if (!currentPage.contains("book-reservations.html")) {
            if (getPageSource().contains("The itinerary selected does not allow enough time to make your connection")) {
                throw new RestartingScenarioFailure("Ooops error: The itinerary selected does not allow enough time to make your connection")
            }
            checkNoOops();
        }
        subForms.each {if (it instanceof pages.elements.ContactInfo) {it.fillForm()}}
        subForms.each {if (it instanceof CreditCardSubForm) {it.fillForm()}}
        fillEmail()
    }

    void verifyAdultCompanionNote() {
        String umNote = waitForElement(UM_NOTE).getText()
        umNote.shouldContain UM_NOTE_MSG + scenarioState.lastAirReservation.adultPnr, "The UM " + UM_NOTE_MSG + " should be present"
    }

    void verifyDefaultTripNameText(String tripName){
        getTripNameFieldValue().shouldBeEqual tripName, "Default trip name is not correctly formatted"
    }

    void verifyExistingTripsIsPresentAndDisabled() {
        verifyExistingTripsIsPresent()
        waitForElement(EXISTING_TRIP_NAME).isEnabled().shouldBe false, "The Existing Trips drop down list should be disable."
    }

    def fillPhone() {
        if (flow.isCarReservationPresent) {
            fillIn(CONTACT_AREA_CODE,"972", false)
            fillIn(CONTACT_PREFIX,"312", false)
            fillIn(CONTACT_PHONE_NUMBER,"1111", false)
        }
    }

    private boolean retryUntilPresent(By by, int retry = 3) {
        boolean isPresent = false
        for (int i = 0; i < retry && !isPresent; i++) {
            isPresent = isElementPresent(by)
        }
        return isPresent
    }

    def fillInPurchaseWithGhostCard() {
        subForms.each {if (it instanceof pages.elements.ContactInfo) {it.fillForm()}}
        selectFromDropDownByIndex(STORED_FORM_OF_PAYMENT_NAME,1)
        passengerData.clearPassengers()
        passengerData.addPassengerWithName(waitForElement(FIRST_NAME_TEXT).getAttribute("value"),waitForElement(LAST_NAME_TEXT).getAttribute("value"))
    }

    def fillSecondPassengerMiddleName(String name) {
         fillIn(SECOND_PASSENGER_MIDDLE_NAME, name)
    }

    def verifyCreditCardDefaultValues(){
        purchaseCardPage.verifyDefaultCreditCardType()
        purchaseCardPage.verifyDefaultExpirationMonth()
        purchaseCardPage.verifyDefaultExpirationYear()
        purchaseCardPage.verifySecurityCodeIsEmpty()
        purchaseCardPage.verifyCreditCardNumberIsEmpty()
    }

    def selectTextMeAsContactMethod() {
        chooseInDropDownByValue(CONTACT_METHOD_DROP_DOWN, "Text")
    }

    def clickAlternateAwards() {
        waitForElement(ALTERNATE_AWARDS_LINK).click()
    }

    def selectAlternateFreedomAwards() {
        waitForElement(A_FREEDOM_CUPON).click()
        waitForElement(B_FREEDOM_CUPON).click()
    }

    def clickOnSelectAwards() {
        waitForElement(AWARDS_SUBMIT_BUTTON).click()
    }

    def selectCreditCarFromStoredFormOfPayment() {
        selectFromDropDownByIndex(STORED_FORM_OF_PAYMENT_NAME, 2)
    }

    void verifyCyberArkOOPSMessage () {
        shouldShowOopsMessage("We are unable to complete your transaction using your approved instant credit card at this time. Please provide an alternate method of payment to complete your transaction. We apologize for the inconvenience")
    }

    def clickOnClickAndSaveCheckbox() {
        waitForElement(CLICK_AND_SAVE_CHECKBOX).click()
    }

    def clickOnRRSignupCheckbox() {
        waitForElement(RR_SIGNUP_CHECKBOX).click()
    }

    def verifyRRModuleNotPresent() {
        verifyElementNotPresent("RR Sign up Module",RR_SIGNUP_CHECKBOX)
    }

    def verifyRRModulePresent() {
        verifyElementPresent("RR Sign up Module", RR_SIGNUP_CHECKBOX)
    }

    def verifyClickAndSaveCheckBox(boolean isPresent = true){
        if(isPresent){
            verifyElementPresent("Click And Save checkBox",CLICK_AND_SAVE_CHECKBOX)
        }else{
            verifyElementNotPresent("Click And Save checkBox",CLICK_AND_SAVE_CHECKBOX)
        }
    }

    def verifySaveCreditCardOptionIsPresent() {
        verifyElementPresent("Credit Car Info",SAVE_CREDIT_CARD_HOLDER_INFO)
        assertTrue(waitForElement(SAVE_CREDIT_CARD_HOLDER_INFO).isDisplayed())
    }

    def verifyRapidRewardsAwardsSection() {
        waitForElement(By.xpath("//fieldset[@id='main_fs']"))
        verifyElementPresent('Rapid Rewards Awards', By.xpath("//h4[@class=\'bookingOptionsHeader\' and contains (text(), \'Rapid Rewards Awards\')]"))
    }

    def verifyRapidRewardsAwardsSectionWithExpectedCertificates(String certificateNumbers) {
        waitForElement(By.xpath("//fieldset[@id='main_fs']"))
        verifyElementPresent('Rapid Rewards Awards', By.xpath("//h4[@class=\'bookingOptionsHeader\' and contains (text(), \'Rapid Rewards Awards\')]"))
        verifyCertificates(certificateNumbers)
    }

    def verifyCompanionBooking() {
        verifyElementPresent("Companion pass", COMPANION_PASS)
    }

    def verifyBillingInformationDisplayed() {
        verifyElementPresent('Your Billing Information', By.xpath("//div[@class=\'billingInfoHeadingText\' and contains (text(), \'Your Billing Information\')]"))
    }

    def verifyReviewPurchaseSummaryDisplayed() {
        verifyElementPresent('Trip Total', PURCHASE_SUMMARY_TOTAL_COST)
        verifyElementPresent('Funds Applied', PURCHASE_SUMMARY_TOTAL_FUNDS_APPLIED)
        verifyElementPresent('Total Due Now', PURCHASE_SUMMARY_TOTAL_COST_DUE_NOW)
    }

    def bookCompanion() {
        fillInInformationForUserLoggedIn()
        if (DynaStubsIntegration.useDynaStubs()) {
            new DynaStubsIntegration().createCompanionFormOfPayment(flow.getUser().getNumber())
        }
        clickVisibleSubmit()
    }

    boolean isEmailConfirmationAddressEmpty() {
        return waitForElement(EMAIL_CONFIRMATION_ADDRESS).getAttribute("value").isEmpty();
    }

    void openPayPalWindow(){
        def executor = (JavascriptExecutor) this.getDriverProvider().get()
        executor.executeScript("window.open('https://developer.paypal.com/')");
    }

    public void switchBackToDotCom() {
        switchToWindow("Southwest Airlines")
    }

    def verifyRRContact(RRContactInformation rrContactInformation) {
        waitForElement(FIRST_NAME_TEXT)?.getAttribute("value").shouldBe rrContactInformation.firstName, "Purchase Page First Name did not match RR account info"
        waitForElement(LAST_NAME_TEXT)?.getAttribute("value").shouldBe rrContactInformation.lastName, "Purchase Page Last Name did not match RR account info"
        waitForElement(By.id("rapidRewardsNumber0"))?.getAttribute("value").shouldBe rrContactInformation.accountNumber, "Purchase Page account number did not match RR account info"
        waitForElement(GENDER)?.getAttribute("value").shouldBe rrContactInformation.gender, "Purchase Page Gender did not match RR account info"
        waitForElement(By.id("emailConfirmationAddress"))?.getAttribute("value").shouldBe rrContactInformation.email, "Purchase Page Email did not match RR account info"
        verifyDOB(rrContactInformation.dateOfBirth)
        verifyPhoneNumber(rrContactInformation.phoneNumber)
    }

    public def fillInCreditCardSubFormIfBlank() {
        creditCardSubForm.fillForm()
    }

    def void fillModulePassenger(Integer paxIndex){
        Passenger passenger = passengerData.getPassengers().get(paxIndex)
        fillFormFirstName(paxIndex, passenger.getFirstName())
        fillFormLastName(paxIndex, passenger.getLastName())
        fillFormGender(paxIndex, passenger.getGender())
        fillFormDOBMonth(paxIndex, passenger.getDateOfBirth().format(PURCHASE_MODULE_TIME_FORMAT_MONTH))
        fillFormDOBDay(paxIndex, passenger.getDateOfBirth().format(PURCHASE_MODULE_TIME_FORMAT_DAY))
        fillFormDOBYear(paxIndex, passenger.getDateOfBirth().format(PURCHASE_MODULE_TIME_FORMAT_YEAR))
        fillContactMethodCallMe(PURCHASE_MODULE_PHONE_NUMBER)
    }

    private void verifyPhoneNumber(String phoneNumber) {
        waitForElement(CONTACT_AREA_CODE)?.getAttribute("value").shouldBe phoneNumber[0..2], "Purchase Page Phone Number area code did not match RR account info"
        waitForElement(CONTACT_PREFIX)?.getAttribute("value").shouldBe  phoneNumber[3..5], "Purchase Page Phone Number prefix did not match RR account info"
        waitForElement(CONTACT_PHONE_NUMBER)?.getAttribute("value").shouldBe  phoneNumber[6..9], "Purchase Page Phone Number usPhoneNumber did not match RR account info"
    }

    private void verifyDOB(String dob) {
        List<String> dobTokens = dob.split("/")
        for (int idx = 0; idx < dobTokens.size(); ++idx) {
            if (idx == 0) {
                String modMonth = dobTokens.get(idx)
                if (dobTokens.get(idx).startsWith("0")) {
                    modMonth = dobTokens.get(idx).substring(1)
                }
                waitForElement(MONTH_OF_BIRTH)?.getAttribute("value").shouldBe modMonth, "Purchase Page DOB month did not match RR account info"
            } else if (idx == 1) {
                String modDay = dobTokens.get(idx)
                if(dobTokens.get(idx).startsWith("0")){
                    modDay = dobTokens.get(idx).substring(1)
                }
                waitForElement(DAY_OF_BIRTH)?.getAttribute("value").shouldBe modDay, "Purchase Page DOB day did not match RR account info"
            } else if (idx == 2) {
                waitForElement(YEAR_OF_BIRTH)?.getAttribute("value").shouldBe  dobTokens.get(idx), "Purchase Page DOB year did not match RR account info"
            } else {
                fail("Date format did not match expected format, expected MM/DD/YYYY, got -> ${dob}")
            }
        }
    }

    private void verifyCertificates(String certificateNumbers) {
        List<String> certNumbersList = transformCertNumbersToList(certificateNumbers)
        for (int i = 1; i <= certNumbersList.size(); i++) {
            String rowClass = getRowClass(i)
            String certNumber = certNumbersList.get(i-1)
            verifyElementPresent('Certificate Number', By.xpath("//tr[@class= \'" + rowClass + "\']//td[@class= \'certificateNumber\' and contains (text(), \'" + certNumber.trim() + "\')]"))
        }
    }

    private String getRowClass(int i) {
        isEven(i) ? "second_row" : "first_row"
    }

    private List<String> transformCertNumbersToList(String certificateNumbers) {
        List<String> certNumbersList = new ArrayList<String>()
        if (SwaUtils.isNotEmpty(certificateNumbers)) {
            certNumbersList.addAll(certificateNumbers.split(","))
        }
        certNumbersList
    }

    private boolean isEven(int i) {
        return i % 2 == 0
    }

    private void fillFormFirstName(Integer paxIndex, String firstName) {
        getFirstName(paxIndex).sendKeys DELETE_EXISTING + firstName
    }

    private void fillFormLastName(Integer paxIndex, String lastName){
        getLastName(paxIndex).sendKeys DELETE_EXISTING + lastName
    }

    private void fillFormGender(Integer paxIndex, String option){
        chooseInDropDownByText(PASSENGER_GENDER + paxIndex, option)
    }

    private void fillFormDOBDay(Integer paxIndex, String option){
        chooseInDropDownByValue(PASSENGER_BIRTH_DAY + paxIndex, option)
    }

    private void fillFormDOBMonth(Integer paxIndex, String option){
        chooseInDropDownByValue(PASSENGER_BIRTH_MONTH + paxIndex, option)
    }

    private void fillFormDOBYear(Integer paxIndex, String option){
        chooseInDropDownByValue(PASSENGER_BIRTH_YEAR + paxIndex, option)
    }

    private void fillContactMethodCallMe(String number){
        chooseInDropDownByValue(CONTACT_METHOD_DROP_DOWN, "Call")

        String[] numbers = number.split("-")
        getContactAreaCode().sendKeys DELETE_EXISTING + numbers[0]
        getContactPrefix().sendKeys DELETE_EXISTING + numbers[1]
        getContactPhoneNumber().sendKeys DELETE_EXISTING + numbers[2]
    }

    private WebElement getFirstName(int paxIndex) {
        return waitForElement(By.id(PASSENGER_FIRST_NAME + paxIndex))
    }

    private WebElement getLastName(int paxIndex) {
        return waitForElement(By.id(PASSENGER_LAST_NAME + paxIndex))
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

    private BigDecimal getTripTotal(){
        purchasePageData.tripTotal = waitForElement(PURCHASE_SUMMARY_TOTAL_COST).text.replace('$','').toBigDecimal()
        return purchasePageData.tripTotal
    }

    def saveTripTotal(){
        getTripTotal()
    }

    def verifyBasicPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify purchase page) ---- waiting for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify purchase page) ---- waiting for fault injection"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["PurchasePage"])
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.PURCHASE_PAGE)
    }

    def verifyTripTotalAndPurchaseTripTotalAreEqual(BigDecimal tripTotal) {
        BigDecimal purchaseTotalTrip = getTripTotal()
        BigDecimal umFee = 0
        if(flow.isUM){
            umFee = feeCalculator.calculateGuardianCharge()
        }
        (tripTotal + umFee).shouldBe purchaseTotalTrip, "Trip total: ${tripTotal} was not equal to purchase page trip total: ${purchaseTotalTrip}"
    }

    def verifyLuvVoucherApplied() {
        verifyLuvVoucherAmountDeductedFromTotal()
        verifyAppliedFundsAirTotalEqualsShoppingCartTotal()
        verifyLuvVoucherBalance()
        verifyPaymentMethod()
        verifyAirChargesWithLuvVoucher()

    }

    def verifyPaymentMethod() {
        waitForElement(By.id("main_fs")).shouldNotBe null, "Payment Method was not displayed"
    }

    def verifyAppliedFundsAirTotalEqualsShoppingCartTotal() {
        List<BigDecimal> totalList = retrieveAirTotalAndLuvVoucherTotal()
        BigDecimal airTotal = totalList[0]
        BigDecimal shoppingCartAirTotal = shoppingCart.getAirTotal()
        airTotal.shouldEqual shoppingCartAirTotal, "Air total in applied funds ${airTotal} did not equal air total in shopping cart ${shoppingCartAirTotal}"
    }

    def verifyLuvVoucherAmountDeductedFromTotal() {
        BigDecimal total = waitForElement(APPLIED_TOTAL_DUE).text.replace('$', '').toBigDecimal()
        purchasePageData.totalDueAmount = total
        List<BigDecimal> totalList = retrieveAirTotalAndLuvVoucherTotal()
        BigDecimal airTotal = totalList[0]
        BigDecimal luvVoucherTotal = totalList[1]
        purchasePageData.luvVocherAmount = luvVoucherTotal
        (airTotal - luvVoucherTotal).shouldBe total, "Air total ${airTotal} minus Luv Voucher total ${luvVoucherTotal} did not match total due ${total}"
    }

    List<BigDecimal> retrieveAirTotalAndLuvVoucherTotal() {
        List<WebElement> subTotals = waitForElements(APPLIED_FUNDS_TABLE_TOTALS)
        BigDecimal airTotal = waitForElement(TOTAL_AMOUNT_FOR_NEW_FLIGHT).text.replace('$', '').toBigDecimal()
        List<BigDecimal> totalList = new ArrayList<BigDecimal>()
        totalList.add(airTotal)
        totalList.add(subTotals[0].text.replace('$', '').replace('(', '').replace(')', '').toBigDecimal())
        return totalList
    }

    def verifyLuvVoucherBalance() {
        waitForElement(TRAVEL_FUNDS_FLYOUT).click()
        ExchangedTicketFlyout ticketFlyout = new ExchangedTicketFlyout(waitForElement(By.className("article")))
        purchasePageData.fullBalanceAmount = ticketFlyout.totalAmount
        BigDecimal fullBalance = ticketFlyout.totalAmount
        purchasePageData.luvVoucherAppliedAmount = ticketFlyout.travelFundsAppliedAmount
        BigDecimal fundsApplied = ticketFlyout.travelFundsAppliedAmount
        purchasePageData.luvVoucherRemainingAmount = ticketFlyout.travelFundsRemainingAmount
        BigDecimal fundsRemaining = ticketFlyout.travelFundsRemainingAmount
        (fullBalance - fundsApplied).shouldEqual fundsRemaining, "Full balance ${fullBalance} - funds applied ${fundsApplied} did not match funds remaining ${fundsRemaining}"
    }

    def verifyCreditCardSelectedByDefault() {
        WebElement creditCard = waitForElement(By.id("storedFormOfPayment"), false, 5)?:waitForElement(CC_RADIO_BUTTON)
        creditCard.getAttribute("checked").shouldBe "true", "Credit Card was not selected By default"
    }

    def verifyPayPalIsSelected() {
        waitForElement(By.id("payPalRadioButton")).getAttribute("checked").shouldBe "true", "Credit Card was not selected"
    }
    
    def verifyPayPalTripTotal() {
        waitForElement(By.className("paypalAirFareTotal")).text.split(" ")[2].replace("\$","").replace(")","").toBigDecimal().shouldEqual pricePageData.getAirTotal(), "Air total for PayPal payment method was not equal to air total from price page in the purchase page"
    }

    def verifyFormOfPaymentHeader() {
        waitForElement(By.className("paymentMethodHeadingText"), false).shouldNotBe null, "The header is not present"
    }

    def verifyCreditCardDataNotDisplayed() {
        waitForElement(CREDIT_CARD_INFORMATION).getAttribute("style").shouldContain "none", "The card information waas displayed"
    }

    def verifyTravelersInfoIsDisplayed() {
        List<WebElement> travelersInfo = waitForElements(PASSENGER_INFO)
        travelersInfo.size().shouldBe passengerData.passengers.size(), "there was not travelers info for all the passengers"
    }

    def fillGiftCardAndApply() {
        waitForElement(GIFT_CARD_NUMBER).sendKeys DELETE_EXISTING + data.getGiftCard("500Thousand").cardNumber
        waitForElement(GIFT_CARD_SECURITY_CODE).sendKeys DELETE_EXISTING + data.getGiftCard("500Thousand").securityCode
        waitForElement(APPLY_GIFT_CARD_BUTTON).click()
    }

    def verifyTravelFundsRoutingSubheader() {
        StringBuilder expectedHeader = new StringBuilder()
        expectedHeader.append(itineraryData.departureCity)
                .append(" (").append(itineraryData.departureStation)
                .append(") to ")
                .append(itineraryData.arrivalCity)
                .append(" (").append(itineraryData.arrivalStation)
                .append(") ").append(itineraryData.itineraryType)
        waitForElement(TRAVEL_FUNDS_ROUTING_HEADER).text.shouldBe expectedHeader.toString(), "The header was not correct"
    }

    def verifyTotalAmountForNewFlight() {
        BigDecimal amountInPage = waitForElement(TOTAL_AMOUNT_FOR_NEW_FLIGHT).text.replace(' ','').replace(',','').replace('$','').toBigDecimal()
        amountInPage.shouldEqual pricePageData.airTotal, "The price was not the same as in price page"
    }

    def verifyAirCharges() {
        waitForElement(AIR_CHARGES).text.shouldBe "\$0.00", "Air charges was not 0"
    }

    def verifyAirChargesApplied() {
        BigDecimal airCharges = waitForElement(AIR_CHARGES).text.replace('$','').toBigDecimal()
        BigDecimal amountInPage = waitForElement(TOTAL_AMOUNT_FOR_NEW_FLIGHT).text.replace(' ','').replace(',','').replace('$','').toBigDecimal()
        AirReservation airReservation = scenarioState.getLastAirReservation()
        BigDecimal previousAirReservation = airReservation.getPrice()
        if (airReservation.getItineraryData().departingFlight_fareClass == BUSINESS_FARE_TYPE){
            airCharges.shouldBe 0.00,"Air Charges in Applied Travel Funds Summary of Purchase Page was not 0"
        } else {
            (amountInPage - previousAirReservation).shouldEqual airCharges, "The difference between the Amount for new flight and the Amount of the cancelled PNR on Purchase Page was not equal to the Air Charges Applied"
        }
    }

    def verifyTravelFundsTable() {
        List<WebElement> travelFundsTable = waitForElements(TRAVEL_FUNDS_TABLE)
        String travelFundType = travelFundsTable[0].text
        String travelFundNumber = travelFundsTable[1].text
        String travelFundRemaining = travelFundsTable[2].text
        String travelFundsAppliedToday = travelFundsTable[6].text
        String travelFundsTotalApplied = travelFundsTable[8].text
        travelFundType.shouldContain "southwestgiftcard", "SouthwestGiftcar was nos present in the travel funds table"
        travelFundNumber.shouldContain data.getGiftCard("500Thousand").cardNumber.subSequence(12, 16)
        travelFundRemaining.shouldContain "Funds Remaining"
        travelFundRemaining.shouldContain purchasePageData.exchangeTicketFundsRemaining.toString(), "Travel funds remaining did not match the one of the Exchanged Ticket Flyout"
        travelFundsAppliedToday.replace('$','').replace(',','').replace(' ','').toBigDecimal().shouldEqual purchasePageData.totalTravelFundsApplied, "Travel funds applied did not match the one of the Exchanged Ticket Flyout"
        StringBuilder expectedFundsApplied = new StringBuilder()
        expectedFundsApplied.append("(").append(travelFundsAppliedToday.trim()).append(")")
        travelFundsTotalApplied.shouldBe expectedFundsApplied.toString()
    }

    def verifyTravelFundsFlyoutData() {
        waitForElement(TRAVEL_FUNDS_FLYOUT).click()
        ExchangedTicketFlyout ticketFlyout = new ExchangedTicketFlyout(waitForElement(By.className("article")))
        ticketFlyout.title.shouldContain "southwestgiftcard"
        ticketFlyout.flyoutSubtitle.shouldContain data.getGiftCard("500Thousand").cardNumber.subSequence(12, 16)
        ticketFlyout.totalAmount.shouldNotBe null
        purchasePageData.fullBalanceAmount = ticketFlyout.totalAmount
        ticketFlyout.travelFundsAppliedTitle.shouldBe "Travel Funds Applied Today"
        DecimalFormat priceFormat =  new DecimalFormat("0.00")
        priceFormat.format(ticketFlyout.travelFundsAppliedAmount).shouldEqual priceFormat.format(pricePageData.tripTotal), "Funds applied amount was not the same as the price of the trip in the travel funds flyout in purchase page"
        purchasePageData.totalTravelFundsApplied = ticketFlyout.travelFundsAppliedAmount
        ticketFlyout.travelFundsRemainingTitle.shouldBe "Travel Funds Remaining"
        (ticketFlyout.totalAmount - ticketFlyout.travelFundsAppliedAmount).shouldEqual ticketFlyout.travelFundsRemainingAmount
        purchasePageData.exchangeTicketFundsRemaining = ticketFlyout.travelFundsRemainingAmount
    }

    def verifyTravelFundsTotalDueNow() {
        BigDecimal totalDueNow = waitForElement(APPLIED_TOTAL_DUE).text.replace('$','').toBigDecimal()
        (pricePageData.airTotal - purchasePageData.totalTravelFundsApplied).shouldEqual totalDueNow, "Total due now was not the difference between total for new flight and funds applied"
    }

    def verifyFundsApplied() {
        BigDecimal fundsApplied = waitForElement(PURCHASE_SUMMARY_TOTAL_FUNDS_APPLIED).text.replace('$','').replace("(","").replace(")","").toBigDecimal()
        getTripTotal().shouldBe fundsApplied, "Funds Applied was not equal to trip total"
    }

    def verifyTotalDueNow() {
        BigDecimal totalDueNow = waitForElement(PURCHASE_SUMMARY_TOTAL_COST_DUE_NOW).text.replace('$','').toBigDecimal()
        (pricePageData.airTotal - purchasePageData.totalTravelFundsApplied).shouldEqual totalDueNow, "Total due now was not the difference between total for new flight and funds applied"
    }

    def verifyNonEditablePaxDetails(){
        Passenger passenger = passengerData.getPassengers()[0]
        waitForElement(FIRST_NAME_TEXT).text.toLowerCase().shouldBe passenger.getFirstName().toLowerCase() ,"FirstName was not retained on return to Purchase Page"
        waitForElement(LAST_NAME_TEXT).text.toLowerCase().shouldBe passenger.getLastName().toLowerCase() ,"LastName was not retained on return to Purchase Page"
        Date dateOfBirth = null
        if (flow.isUM) {
            dateOfBirth = itineraryData.umDateOfBirth
        } else {
            dateOfBirth = passenger.getDateOfBirth()
        }
        if (!itineraryData.hasDisabilities) {
            waitForElement(NON_EDITABLE_DISABILITY_OPTIONS).text.shouldContain "- None Entered -", "Passenger disability options was not correct"
        }
        waitForElement(PASSENGER_DOB).text.shouldBe dateOfBirth.format("M/d/yyyy"), "Passenger date of birth was not retained on return to Purchase Page"
        waitForElement(GENDER).text.shouldBe passenger.getGender(), "Passenger gender was not retained on return to Purchase Page"
    }

    def verifyUnaccompaniedMinorDialog() {
        String umTitle = "Unaccompanied Minor"
        String umMessage = "Will there be a person age 12 or older traveling with the Unaccompanied Minor(s)?"
        waitForElement(UM_MODAL_TITLE).text.shouldBe umTitle, "UM Modal title was not equal to $umTitle"
        waitForElement(UM_MODAL_MESSAGE).text.shouldBe umMessage, "UM message was not equal to $umMessage"
        isElementPresent(UM_YES_BUTTON).shouldBe true, "UM Yes button was not present in the dialog"
        isElementPresent(UM_NO_BUTTON).shouldBe true, "UM No button was not present in the dialog"
    }

    def verifyEditPassengerInfoButton() {
        isElementPresent(EDIT_PASSENGER_INFO).shouldBe true, "Edit passenger information button was not present"
    }

    def verifyViewParentGuardianInfoLink() {
        isElementPresent(VIEW_PARENT_GUARDIAN_INFO_LINK).shouldBe true, "View parent guardian info link was not present"
    }

    def verifyEditParentGuardianInfoLink() {
        isElementPresent(EDIT_PARENT_GUARDIAN_INFO_LINK).shouldBe true, "Edit parent guardian info link was not present"
    }

    def verifySavedCreditCardSelectedByDefault() {
        waitForElement(STORED_FORM_OF_PAYMENT).getAttribute("checked").shouldBe "true", "Saved credit card was not selected by default"
    }

    def verifyPrePopulatedPassengerInfo() {
        String firstNameInPage = waitForElement(FIRST_NAME_TEXT).getAttribute("value")
        String lastNameInPage = waitForElement(LAST_NAME_TEXT).getAttribute("value")
        String middleNameInPage = waitForElement(MIDDLE_NAME_TEXT).getAttribute("value")
        int dobMonth = waitForElement(MONTH_OF_BIRTH).getAttribute("value").toInteger()-1
        int dobYear = waitForElement(YEAR_OF_BIRTH).getAttribute("value").toInteger()-1900
        int dobDay = waitForElement(DAY_OF_BIRTH).getAttribute("value").toInteger()
        String genderOnPage = waitForElement(GENDER).getAttribute("value")
        Date dateOfBirth = new Date(dobYear, dobMonth, dobDay)
        firstNameInPage.shouldBe rrAccountData.firstName
        lastNameInPage.shouldBe rrAccountData.lastName
        middleNameInPage.shouldBe rrAccountData.middleName
        genderOnPage.shouldBe rrAccountData.gender
        dateOfBirth.format("MM/dd/yyyy").shouldBe rrAccountData.dateOfBirth
    }

    def verifySavedCreditCardName() {
        String creditCardName = waitForElement(STORED_FORM_OF_PAYMENT_NAME).text
        creditCardName.shouldContain rrAccountData.creditCardName
    }

    def verifyTotalPoints() {
        BigDecimal pointsOnPage = waitForElement(TOTAL_POINTS).text.replace(',','').replace('pts','').toBigDecimal()
        pointsOnPage.shouldEqual selectFlightsPageData.totalOutboundInboundFlightPoints, "The total points were not the same as in the price page"
        purchasePageData.totalPoints = pointsOnPage
    }

    def addDisabilities() {
        waitForElement(ADD_EDIT_DISABILITIES).click()
    }

    def verifyAddEditDisabilitiesButtonIsPresent() {
        isElementPresent(ADD_EDIT_DISABILITIES).shouldBe true, "Add/Edit Disability Options Button was not present in Purchase page"
    }

    def verifyPaxDetails(){
        Passenger passenger = passengerData.getPassengers()[0]
        waitForElement(FIRST_NAME_TEXT).getAttribute("value").shouldBe passenger.getFirstName() ,"FirstName was not retained returning from the disabilities options page"
        waitForElement(LAST_NAME_TEXT).getAttribute("value").shouldBe passenger.getLastName() ,"LastName was not retained returning from the disabilities options page"
        Calendar calendar = passenger.getDateOfBirth().toCalendar()
        waitForElement(DAY_OF_BIRTH).getAttribute("value").toInteger().shouldBe calendar.get(Calendar.DAY_OF_MONTH), "Passenger Birth Day was not retained returning from the disabilities options page"
        waitForElement(MONTH_OF_BIRTH).getAttribute("value").toInteger().shouldBe calendar.get(Calendar.MONTH) + 1, "Passenger Birth Day was not retained returning from the disabilities options page"
        waitForElement(YEAR_OF_BIRTH).getAttribute("value").toInteger().shouldBe calendar.get(Calendar.YEAR),"Passenger Birth Month was not retained returning from the disabilities options page"
    }

    def verifySelectedDisabilities(){
         verifyAssistanceRequestedText()
         verifyDisabilityInPassengerInformation()
         verifyBlindDisability()
    }

    def verifyTravelFundsTableForCancelledPNR() {
        List<WebElement> travelFundsTable = waitForElements(TRAVEL_FUNDS_TABLE)
        String travelFundFirstLastName = travelFundsTable[0].text
        String travelFundNumber = travelFundsTable[1].text
        String travelFundExpirationDate = travelFundsTable[2].text
        String travelFundRemaining = travelFundsTable[3].text
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        travelFundFirstLastName.shouldContain passenger.getFirstName().toUpperCase(), "First name of Travel Fund is different from the cancelled PNR"
        travelFundFirstLastName.shouldContain passenger.getLastName().toUpperCase(), "Last name of Travel Fund is different from the cancelled PNR"
        travelFundNumber.shouldContain "Travel Fund", "Travel Fund text was not displayed in the Reconcile page"
        travelFundNumber.shouldContain airReservation.getAdultPnr(), "Travel fund did match the cancelled PNR"
        Calendar nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR,1)
        travelFundExpirationDate.shouldContain "Expiration:", "Expiration text was not displayed in the Reconcile page"
        travelFundExpirationDate.shouldContain nextYear.time.format(DATE_FORMAT), "Expiration date in Reconcile Page is not a year from today"
        travelFundRemaining.shouldContain "Funds Remaining", "Funds Remaining text was not displayed in the Reconcile page"
        if (airReservation.getItineraryData().departingFlight_fareClass == BUSINESS_FARE_TYPE){
            BigDecimal fundsRemaining = airReservation.getPrice() - waitForElement(TOTAL_AMOUNT_FOR_NEW_FLIGHT).text.replace('$','').toBigDecimal()
            travelFundRemaining.split(" ")[2].replace('$','').toBigDecimal().shouldEqual fundsRemaining, "Funds remaining on Purchase Page was not the difference between total amount of cancelled PNR and the total amount of the New Flight"
        }else{
            travelFundRemaining.shouldContain "\$0.00", "Funds remaining on Purchase Page was not 0"
        }
    }


    def verifyTravelFundsFlyoutDataforCancelledPNR() {
        waitForElement(TRAVEL_FUNDS_FLYOUT).click()
        TravelFundsFlyout ticketFlyout = new TravelFundsFlyout(waitForElement(ARTICLE))
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        ticketFlyout.firstLastName.shouldContain passenger.getFirstName().toUpperCase(), "First name of Travel Fund Flyout is different from the cancelled PNR"
        ticketFlyout.firstLastName.shouldContain passenger.getLastName().toUpperCase(), "Last name of Travel Fund Flyout is different from the cancelled PNR"
        ticketFlyout.travelFund.shouldContain "Travel Fund", "Travel Fund text not found in Travel Fund Flyout"
        ticketFlyout.travelFund.shouldContain airReservation.getAdultPnr(), "Travel fund in Flyout did match the cancelled PNR"
        Calendar nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR,1)
        ticketFlyout.expiration.shouldContain "Expiration", "Expiration text was not displayed in Travel Funds Flyout"
        ticketFlyout.expiration.shouldContain nextYear.time.format(DATE_FORMAT), "Expiration date in Travel Funds Flyout is not a year from today"
        ticketFlyout.travelFundsAppliedTitle.shouldContain "Travel Funds Applied Today", "Travel Funds Applied Today text not found in Travel Funds Flyout"
        ticketFlyout.travelFundsRemainingTitle.shouldBe "Travel Funds Remaining", "Travel Funds Remaining text was not found in Travel Funds Flyout"
        if (airReservation.getItineraryData().departingFlight_fareClass == BUSINESS_FARE_TYPE){
            ticketFlyout.travelFundsAppliedAmount.shouldBeEqual pricePageData.tripTotal, "The funds applied in the Exchanged Ticket Flyout Detail of the Purchase page did not match the total amount for the new flight in the Price page"
        }else{
            ticketFlyout.travelFundsAmount.shouldBe airReservation.price, "ExchangeTicket/TravelFund Amount Flyout Detail of the Purchase page did not match the amount of the cancelled PNR"
            ticketFlyout.travelFundsAppliedAmount.shouldBeEqual airReservation.getPrice(), "The funds applied in the Exchanged Ticket Flyout Detail of the Purchase page did not match the amount of the cancelled PNR"
            ticketFlyout.travelFundsRemainingAmount.shouldBe 0.00, "Funds remaining in Travel Funds Flyout of Purchase Page was not 0"
        }
        purchasePageData.exchangeTicketFundsApplied = ticketFlyout.travelFundsAppliedAmount
        purchasePageData.exchangeTicketFundsRemaining = ticketFlyout.travelFundsRemainingAmount
    }

    def verifyTravelFundsAppliedToday(){
        TravelFundsFlyout ticketFlyout = new TravelFundsFlyout(waitForElement(ARTICLE))
        List<WebElement> travelFundsTable = waitForElements(TRAVEL_FUNDS_TABLE)
        BigDecimal travelFundsApplied = travelFundsTable[8].text.replace('$','').replace("(","").replace(")","").toBigDecimal()
        travelFundsApplied.shouldBeEqual ticketFlyout.travelFundsAppliedAmount, "The funds applied on the purchase page did not match the funds applied today in the flyout"
        purchasePageData.totalTravelFundsApplied = travelFundsApplied
    }

    def verifyTotalAndExchangedTicket(){
        List<WebElement> travelFundsTable = waitForElements(TRAVEL_FUNDS_TABLE)
        BigDecimal travelFundsAppliedToday = travelFundsTable[8].text.replace('\\s','').replace('$','').toBigDecimal()
        BigDecimal totalFunds = travelFundsTable[10].text.replace('$','').replace("(","").replace(")","").toBigDecimal()
        totalFunds.shouldBeEqual travelFundsAppliedToday, "Total Funds did not match the funds applied"
    }

    def verifyAirTotalDueNow() {
        BigDecimal airCharges = waitForElement(AIR_CHARGES).text.replace('$','').toBigDecimal()
        BigDecimal totalDueNow = waitForElement(APPLIED_TOTAL_DUE).text.replace('$','').toBigDecimal()
        totalDueNow.shouldEqual airCharges, "Total due now on Applied Travel Funds Summary of Purchase Page was not equal to air charges"
        AirReservation airReservation = scenarioState.getLastAirReservation()
        if (airReservation.getItineraryData().departingFlight_fareClass != BUSINESS_FARE_TYPE){
            totalDueNow.shouldBeGreaterThan 0.00, "Total due now on Applied Travel Funds Summary of Purchase Page was not greater than 0"
        }
        purchasePageData.totalDueNow = totalDueNow
    }

    def verifySummaryFundsApplied() {
        BigDecimal summaryFundsApplied = waitForElement(PURCHASE_SUMMARY_TOTAL_FUNDS_APPLIED).text.replace('$','').replace("(","").replace(")","").toBigDecimal()
        List<WebElement> travelFundsTable = waitForElements(TRAVEL_FUNDS_TABLE)
        BigDecimal fundsApplied = travelFundsTable[10].text.replace('$','').replace("(","").replace(")","").toBigDecimal()
        summaryFundsApplied.shouldBe fundsApplied, "Funds Applied on Summary section was not equal to funds applied"
    }

    def verifyTotalDueNowLink() {
        isElementPresent(TOTAL_DUE_POPUP_LINK).shouldBe true, "Total Due Now link not present in Review Purchase Summary section"
    }

    def verifySummaryTotalCostDue() {
        BigDecimal appliedTotalDue = waitForElement(APPLIED_TOTAL_DUE).text.replace('$','').toBigDecimal()
        BigDecimal summaryTotalCostDue = waitForElement(PURCHASE_SUMMARY_TOTAL_COST_DUE_NOW).text.replace('$','').toBigDecimal()
        summaryTotalCostDue.shouldBe appliedTotalDue, "Total Due Now on Review Purchase Summary did not match the Total Due Now in Apply Travel Funds Section"
    }

    def verifyRRNumber() {
        waitForElement(RR_NUMBER).getAttribute("value").shouldBe flow.rrUser.number, "Passenger's Rapid Rewards number is different than Rapid Rewards member's number."
    }

    def verifyCCPaypalOptionsArePresent() {
        isElementPresent(CC_RADIO_BUTTON).shouldBe true, "Credit card radio button was not displayed in the purchase page"
        isElementPresent(PAY_PAL_RADIO_BUTTON).shouldBe true, "Paypal radio button was not displayed in the purchase page"
    }

    def verifyAirChargesWithLuvVoucher() {
        BigDecimal airCharges = waitForElement(TOTAL_AMOUNT_FOR_NEW_FLIGHT).text.replace('$','').replace(',','').toBigDecimal()
        BigDecimal luvVoucherApplied = waitForElement(APPLIED_FUNDS_TABLE_TOTALS).text.replace('$','').replace(',','').replace('(','').replace(')','').toBigDecimal()
        BigDecimal totalDueNow = waitForElement(APPLIED_TOTAL_DUE).text.replace('$','').replace(',','').toBigDecimal()
        luvVoucherApplied.shouldEqual purchasePageData.luvVoucherAppliedAmount, "total amount applied did not equal Luv Voucher total amount applied"
        totalDueNow.shouldEqual airCharges - luvVoucherApplied, "Total due now is not the diference between the price of the flight and the total amount applied"

    }

    def clickShareYourItinerary() {
        waitForElement(SHARE_YOUR_ITINERARY_BUDDY_ONE).click()
    }

    def verifyShareYourItinerary() {
        autoCompleteWidget.verifyIfContentExistsInAutoCompleteList(SHARE_YOUR_ITINERARY_BUDDY_NAME_VALUE + " <" + SHARE_YOUR_ITINERARY_BUDDY_EMAIL_VALUE + ">")
    }

    def VerifyHowCanWeContactYouSection() {
        verifyPage()
        verifyPMOCPhoneNumber(PURCHASE_MODULE_PHONE_NUM)
    }

    def verifyPMOCPhoneNumber(String phoneNumber) {
        String[] numbers = phoneNumber.split("-")
        waitForElement(PURCHASE_CONTACT_AREA_CODE)?.getAttribute("value").shouldBe numbers[0], "Purchase Page Phone Number area code did not match RR account info"
        waitForElement(PURCHASE_CONTACT_PREFIX)?.getAttribute("value").shouldBe  numbers[1], "Purchase Page Phone Number prefix did not match RR account info"
        waitForElement(PURCHASE_CONTACT_PHONE_NUMBER)?.getAttribute("value").shouldBe  numbers[2], "Purchase Page Phone Number usPhoneNumber did not match RR account info"
    }

    void checkNotEarlyBirdOptions(){
        waitForElement(EARLY_BIRD_OPTIONS,false,10).shouldBe null, "Early Bird options should not be present."
    }

    void checkApplyFundsButtonPresent() {
        isElementPresent(APPLY_FUNDS_BUTTON).shouldBe false, "The Apply Funds Button shouldn't be Present."
    }

    def verifyHotelLoyaltyNumberField() {
        waitForElement(HOTEL_LOYALTY_NUMBER).isDisplayed().shouldBe true, "Hotel Loyalty number field is not displayed"
    }

    def verifyHotelLoyaltyNumberFieldIsNotDisplayed() {
        isElementPresent(HOTEL_LOYALTY_NUMBER, 15).shouldBe false, "Hotel Loyalty number field should not be displayed"
    }

    def verifySeniorInvalidDateOfBirthOops() {
        shouldShowOopsMessage(SENIOR_INVALID_DOB_OOPS)
        (findLinkInSection(getOopsElement(), "select a new fare type").getAttribute("href")).shouldContain "/flight/select-flight.html"
        (findLinkInSection(getOopsElement(), "Learn More.").getAttribute("href")).shouldBe "http://www.southwest.com/html/customer-service/unique-travel-needs/seniors/index-pol.html"
        waitForElement(By.id(PASSENGER_BIRTH_DAY + "0")).getAttribute("class").shouldContain "fieldError"
        waitForElement(By.id(PASSENGER_BIRTH_MONTH + "0")).getAttribute("class").shouldContain "fieldError"
        waitForElement(By.id(PASSENGER_BIRTH_YEAR + "0")).getAttribute("class").shouldContain "fieldError"
    }

    def verifyStoredPaymentSecurityCodeFieldIsNotDisplayed(){
        waitForElement(STORED_PAYMENT_SECURITY_CODE).isDisplayed().shouldBe false, "Stored Payment Security Code field is displayed"
    }

    def savePurchaserInfo(){
        billingData.firstName = waitForElement(CREDIT_CARD_FIRST_NAME).getAttribute("value")
        billingData.lastName = waitForElement(CREDIT_CARD_LAST_NAME).getAttribute("value")
        billingData.address = waitForElement(CREDIT_CARD_ADDRESS).getAttribute("value")
        billingData.city = waitForElement(CREDIT_CARD_CITY).getAttribute("value")
        billingData.state = waitForElement(CREDIT_CARD_STATE).getAttribute("value")
        billingData.country = waitForElement(CREDIT_CARD_COUNTRY).getAttribute("value")
        billingData.zipCode = waitForElement(CREDIT_CARD_ZIP1).getAttribute("value")
    }

    def savePromoCertExpirationDate(){
        purchasePageData.promoCertExpirationDate = waitForElement(PROMOCERT_EXPIRATION).text.trim()
    }
}