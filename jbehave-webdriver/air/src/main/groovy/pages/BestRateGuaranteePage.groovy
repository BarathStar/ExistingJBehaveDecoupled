package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.joda.time.LocalDate
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class BestRateGuaranteePage extends BasePage {

    private static final PAGE_TITLE = "Best Rate Guarantee"
    private static final String PATH = "/hotels/best-rate-guarantee-claim.html"
    private static final By OOPS_CLASS = By.className("oopsError_wrapper")
    private static final By BRG_GUEST_NAME = By.id("hotelGuestName")
    private static final By BRG_EMAIL_ADDRESS = By.id("emailAddress")
    private static final By BRG_PHONE_NUMBER = By.id("phoneNumber")
    private static final By BRG_HOTEL_NAME = By.id("hotelName")
    private static final By BRG_HOTEL_CITY_STATE = By.id("cityOrState")
    private static final By BRG_HOTEL_CONFIRMATION_NUMBER = By.id("hotelConfirmationNumber")
    private static final By BRG_HOTEL_CHECKIN = By.id("checkInDate")
    private static final By BRG_HOTEL_CHECKOUT = By.id("checkOutDate")
    private static final By BRG_HOTEL_AMOUNT_PAID = By.id("amountPaid")
    private static final By BRG_HOTEL_LOWERPRICE_INFO = By.id("lowerPriceInformation")
    private static final By SUBMIT_BUTTON = By.name("submitButton")
    private static final By TERMS_CONDITIONS_LINK = By.cssSelector("a[href='/html/customer-service/faqs.html?topic=hotel_best_rate_guarantee_program']")
    private static final String TERMS_CONDITIONS_PATH = "/customer-service/faqs.html?topic=hotel_best_rate_guarantee_program"
    private static final By BRG_THANK_YOU =  By.className("wcm_page_header_title")
    public static final String INVALID_EMAIL = "invalidemailatinvaliddomaindotcom"
    private static final String HOTEL_GUEST_NAME = "Mike"
    private static final String EMAIL_ADDRESS = "email@wnco.com"
    private static final String PHONE_NUMBER = "1234567890"
    private static final String HOTEL_NAME = "Hotel Name"
    private static final String CITY_OR_STATE = "Dallas"
    private static final String HOTEL_CONFIRMATION_NUMBER = "TESTCNF"
    private static final String AMOUNT_PAID = "100"
    private static final String LOWER_PRICE_INFORMATION = "Information and more information"
    public static final String INVALID_DATE = "11232012"
    public static final String INVALID_CHARACTER = "#"
    public static final String INVALID_AMOUNT_PAID = INVALID_CHARACTER
    private static final String DATE_FORMAT = "MM/dd/yyyy"
    public static final String INVALID_PHONE_NUMBER = "123ABD123"
    public static final String INVALID_CITY_STATE = "123 Dallas Texas"
    public static final String INVALID_NAME = "123InvalidName"
    public static final String INVALID_CONFIRMATION_NUMBER = "123-123-ABD"

    BestRateGuaranteePage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    @Override
    def open() {
        super.open()
        verifyPage()
    }

    def fillAllFields() {
        fillName(HOTEL_GUEST_NAME)
        fillEmail(EMAIL_ADDRESS)
        fillPhoneNumber(PHONE_NUMBER)
        fillHotelName(HOTEL_NAME)
        fillHotelCityState(CITY_OR_STATE)
        fillHotelConfirmationNumber(HOTEL_CONFIRMATION_NUMBER)
        fillHotelCheckIn(new LocalDate().toDate())
        fillHotelCheckOut(new LocalDate().plusDays(1).toDate())
        fillHotelAmountPaidForStay(AMOUNT_PAID)
        fillHotelLowerPriceAdvertisePrice(LOWER_PRICE_INFORMATION)
    }

    def fillName(String value) {
        fillIn(BRG_GUEST_NAME, value)
    }

    def fillEmail(String value) {
        fillIn(BRG_EMAIL_ADDRESS, value)
    }

    def fillPhoneNumber(String value) {
        fillIn(BRG_PHONE_NUMBER, value)
    }

    def fillHotelName(String value) {
        fillIn(BRG_HOTEL_NAME, value)
    }

    def fillHotelCityState(String value) {
        fillIn(BRG_HOTEL_CITY_STATE, value)
    }

    def fillHotelConfirmationNumber(String value) {
        fillIn(BRG_HOTEL_CONFIRMATION_NUMBER, value)
    }

    def fillHotelCheckIn(Date value) {
        fillIn(BRG_HOTEL_CHECKIN, value.format(DATE_FORMAT))
    }

    def fillHotelCheckIn(String value) {
        fillIn(BRG_HOTEL_CHECKIN, value)
    }

    def fillHotelCheckOut(Date value) {
        fillIn(BRG_HOTEL_CHECKOUT, value.format(DATE_FORMAT))
    }

    def fillHotelCheckOut(String value) {
        fillIn(BRG_HOTEL_CHECKOUT, value)
    }

    def fillHotelAmountPaidForStay(String value) {
        fillIn(BRG_HOTEL_AMOUNT_PAID, value)
    }

    def fillHotelLowerPriceAdvertisePrice(String value) {
        fillIn(BRG_HOTEL_LOWERPRICE_INFO, value)
    }

    def clickOnSubmitButton() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def clickOnTermsConditionsLink() {
        String winHandleBefore = webDriver().getWindowHandle()
        waitForElement(TERMS_CONDITIONS_LINK).click()
        switchToOpenWindow(winHandleBefore, "Terms Conditions")
    }

    def verifyFieldsOopsMessage() {
        verifyOopsMessage(getAllOopsMessages())
    }

    private verifyOopsMessage(List<String> oopsMessage) {
        def expectedOopsMessages = getExpectedOopsMessage(OOPS_CLASS)
        for (msg in oopsMessage) {
           expectedOopsMessages.shouldContain msg, "The Oops message '$msg' has not been found within the text '$expectedOopsMessages'"
        }
    }

    def verifyEmailAndDatesOopsMessages() {
        verifyOopsMessage(getInvalidEmailAndDatesOopsMessages())
    }

    def verifyDatesOopsMessagesWhenAreBeyondElevenMonths(){
        verifyOopsMessage(getInvalidDatesOopsMessagesWhenAreBeyondElevenMonths())
    }

    def verifyInvalidAmountPaidAndDatesOopsMessages(){
        verifyOopsMessage(getInvalidAmountPaidAndDatesOopsMessages())
    }

    def verifyOopsMessagesOnInvalidFieldsInputs(){
        verifyOopsMessage(getInvalidFieldsOopsMessages())
    }

    private List<String> getAllOopsMessages(){
        List<String> oopsMessages = new ArrayList<String>()
        oopsMessages.add("Please enter Name.")
        oopsMessages.add("Please enter E-mail.")
        oopsMessages.add("Please enter Phone Number.")
        oopsMessages.add("Please enter Hotel Name.")
        oopsMessages.add("Please enter City, State of Hotel.")
        oopsMessages.add("Please enter a Hotel Confirmation Number.")
        oopsMessages.add("Please enter Check-In date.")
        oopsMessages.add("Please enter Check-Out date.")
        oopsMessages.add("Please enter Amount Paid.")
        oopsMessages.add("Please enter Price Information.")
        return oopsMessages
    }

    private List<String> getInvalidEmailAndDatesOopsMessages(){
        List<String> invalidEmailAndDatesOopsMessages = new ArrayList<String>()
        invalidEmailAndDatesOopsMessages.add("The email address entered " + INVALID_EMAIL + " is in an invalid format or contains characters our system does not currently support. Please provide a different e-mail address that contains only letters, numbers, periods (.), hyphens (-), and/or underscores(_) and is in the format: myname@youremailprovider.com")
        invalidEmailAndDatesOopsMessages.add("Your Check-In date is in the past.")
        invalidEmailAndDatesOopsMessages.add("Your Check-Out date must be in the future.")
        return invalidEmailAndDatesOopsMessages
    }

    private List<String> getInvalidDatesOopsMessagesWhenAreBeyondElevenMonths(){
        List<String> invalidDatesOopsMessagesWhenAreBeyondElevenMonths = new ArrayList<String>()
        invalidDatesOopsMessagesWhenAreBeyondElevenMonths.add("Your Check-In date is beyond our maximum booking date. Please adjust your Check-In date.")
        invalidDatesOopsMessagesWhenAreBeyondElevenMonths.add("Your Check-Out date is beyond our maximum booking date. Please adjust your Check-Out date.")
        return invalidDatesOopsMessagesWhenAreBeyondElevenMonths
    }

    private List<String> getInvalidAmountPaidAndDatesOopsMessages(){
        List<String> invalidAmountPaidAndDatesOopsMessages = new ArrayList<String>()
        invalidAmountPaidAndDatesOopsMessages.add("The Amount paid contains invalid characters. The invalid characters are: '" + INVALID_CHARACTER + "'" )
        invalidAmountPaidAndDatesOopsMessages.add("Sorry, we didn't understand your Check-In date. Please enter it in the format of MM/DD/YYYY and try again.")
        invalidAmountPaidAndDatesOopsMessages.add("Sorry, we didn't understand your Check-Out date. Please enter it in the format of MM/DD/YYYY and try again.")
        return invalidAmountPaidAndDatesOopsMessages
    }

    private List<String> getInvalidFieldsOopsMessages(){
        List<String> invalidFieldsOopsMessages = new ArrayList<String>()
        invalidFieldsOopsMessages.add("The Phone Number contains invalid character(s)")
        invalidFieldsOopsMessages.add("The City, State of Hotel contains invalid characters.")
        invalidFieldsOopsMessages.add("The Amount paid contains invalid characters. The invalid characters are: '" + INVALID_CHARACTER + "'")
        invalidFieldsOopsMessages.add("The Name contains invalid characters.")
        invalidFieldsOopsMessages.add("The Hotel Confirmation Number contains invalid characters. Only letters and numbers are allowed.")
        invalidFieldsOopsMessages.add("The email address entered " + INVALID_EMAIL + " is in an invalid format or contains characters our system does not currently support. Please provide a different e-mail address that contains only letters, numbers, periods (.), hyphens (-), and/or underscores(_) and is in the format: myname@youremailprovider.com")
        return invalidFieldsOopsMessages
    }

    def verifyTitleAndURL() {
        verifyPage()
        verifyPageTitle(PAGE_TITLE)
        shouldBeInPage(PATH)
    }

    def verifyOpenThankYouPage(){
        verifyElementPresent("Thank You Page", BRG_THANK_YOU)
    }

    def verifyTermsConditionsPage(){
        shouldBeInPage(TERMS_CONDITIONS_PATH)
    }
}
