package pages

import util.DollarToBigDecimalConverter;
import java.util.Iterator;
import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement;

public class TravelFundsPage extends BasePage {

    private static final By FIRST_NAME = By.id("firstName1")
    private static final By LAST_NAME = By.id("lastName1")
    private static final By CONFIRMATION_NUMBER = By.id("confirmationNumber1")
    private static final By TRAVEL_FUNDS_CHECK_FORM_SUBMIT_BUTTON = By.id("travelFunds_check_form_submitButton")
    private static final By LUV_LINK = By.id("checkSouthwestLUVVouchersToggle")
    private static final By GIF_CARD_LINK = By.id("checkGiftCardsToggle")
    private static final By ADD_TRAVEL_FUND_LINK = By.id("addAnotherTicketlessTravelFund")
    private static final By ADD_LUV_VOUCHER_LINK = By.id("addAnotherSouthwestLUVVoucher")
    private static final By ADD_GIFT_CARD_LINK = By.id("addAnotherGiftCard")
    private static final By TRAVEL_FUND_CONFIRMATION_NUMBER_FIELDS = By.cssSelector("input[id^='confirmationNumber']")
    private static final By LUV_VOUCHER_SECURITY_CODE_FIELDS = By.cssSelector("input[id^='voucherSecurityCode']")
    private static final By GIFT_CARD_SECURITY_CARD_FIELDS = By.cssSelector("input[id^='securityCode']")
    private static final By FIRST_NAME_FIELDS = By.cssSelector("input[id^='firstName']")
    private static final By LAST_NAME_FIELDS = By.cssSelector("input[id^='lastName']")
    private static final By VOUCHER_NUMBER_FIELDS = By.cssSelector("input[id^='voucherNumber']")
    private static final By CARD_NUMBER_FIELDS = By.cssSelector("input[id^='cardNumber']")
    private static final By MAXIMUM_VOUCHER_ALLOWED = By.id("addAnotherSouthwestLUVVoucherContainer")
    private static final By MAXIMUM_TRAVELFUND_ALLOWED = By.id("addAnotherTicketlessTravelFundContainer")
    private static final By MAXIMUM_GIFTCARD_ALLOWED = By.id("addAnotherGiftCardContainer")
    private static final String MAXIMUM_FOUR_ALLOWED_TEXT = "Maximum four allowed"

    private static final By CHECK_LUV_VOUCHER_TOGGLE = By.id("checkSouthwestLUVVouchersToggle")
    private static final By CHECK_GIFTCARD_TOGGLE = By.id("checkGiftCardsToggle")
    private static final By ADD_ANOTHER_TRAVEL_FUND = By.id("addAnotherTicketlessTravelFund")
    private static final By ADD_ANOTHER_LUV_VOUCHER = By.id("addAnotherSouthwestLUVVoucher")
    private static final By ADD_ANOTHER_GIFTCARD = By.id("addAnotherGiftCard")

    private static final By TRAVEL_FUNDS_BALANCE_ROWS = By.cssSelector("#travelFunds_results_ticketlessTravelFunds_content td.swa_tables_lastCol.balance")
    private static final By LUV_VOUCHER_BALANCE_ROWS = By.cssSelector("#travelFunds_results_southwestLUVVouchers_container td.swa_tables_lastCol.balance")
    private static final By GIFTCARD_BALANCE_ROWS = By.cssSelector("#travelFunds_results_giftCards_container td.swa_tables_lastCol.balance")

    private static final By TRAVEL_FUNDS_TOTAL_ROW = By.cssSelector("#travelFunds_results_ticketlessTravelFunds_content div.swa_tables_total_rightBorder")
    private static final By LUV_VOUCHER_TOTAL_ROW = By.cssSelector("#travelFunds_results_southwestLUVVouchers_container div.swa_tables_total_rightBorder")
    private static final By GIFTCARD_TOTAL_ROW = By.cssSelector("#travelFunds_results_giftCards_container div.swa_tables_total_rightBorder")

    public def manageReservation = "//a[@href='/travel_center/retrieveItinerary.html?forceNewSession=yes']"

    Data data;

    private static final def ERROR_TYPE = [
            "security check": "The security check solution entered did not match. Please try again or if you continue to have difficulties, please contact a Southwest Airlines Customer Service Representative at 1-800-I-FLY-SWA (1-800-435-9792) (SW105000)",
            "incomplete confirmation number": "The confirmation number entered is invalid. Southwest Airlines confirmation numbers contain six alpha-numeric characters. (SW104000)",
            "credit card denied": "The credit card was denied by your financial institution. Please contact your financial institution or try an alternate form of payment"
    ]

    public TravelFundsPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/travel-funds-search.html');
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def open() {
        super.open()
        checkSomethingServed()
    }

    def verifyAirTranMessage() {
        verifyTextPresent("AirTran travel credits cannot be used on southwest.com.", By.xpath("//p[@class='swa_page_description']"))
    }

    def fillInTravelFundForm(String pnr, String firstName, String lastName) {
        fillIn(FIRST_NAME, firstName)
        fillIn(LAST_NAME, lastName)
        fillIn(CONFIRMATION_NUMBER, pnr)
    }

    def enterPNROnViewTravelFundsPage(String pnr, String firstName, String lastName) {
        fillInTravelFundForm(pnr, firstName, lastName)
        clickOnCheckNowButton()
    }

    def clickOnCheckNowButton() {
        waitForElement(TRAVEL_FUNDS_CHECK_FORM_SUBMIT_BUTTON).click()
    }

    def verifyRemainingTravelFundBalance() {
        waitForElement(By.xpath("//td[@class='swa_tables_lastCol balance']")).getText()
    }

    def verifyErrorMessageOnTravelFundsPage(String travel_funds_error_type) {
        verifyTextPresent(ERROR_TYPE[travel_funds_error_type], By.id("errors"))
    }

    void verifyInvalidTravelFundsPnrRetrieval() {
        if (getOopsElement().getText().startsWith("We were unable to process your request")) {
            checkNoOops()
        }

        def oopsMessages = [
            'We were unable to retrieve your funds from the information provided. Please verify the following:',
            'The confirmation number is entered correctly.',
            'The passenger name on the reservation is entered correctly.',
            'If all information above has been verified, either we were unable to find the funds or they have expired.',
            'The reservation was purchased online via southwest.com or swabiz.com or mobile.southwest.com or Southwest Vacations. Purchases made through Southwest Groups,Southwest Ticket counters, or other Travel Agencies cannot be accessed on southwest.com.',
            'The passenger name on the reservation is entered correctly.'
        ]
        verifyOopsMessages(oopsMessages)
    }

    def verifyMessageAboutConfirmationNumberAndPassengerNames() {
        def oopsMessages = [
                "Confirmation Number is required.",
                "The passenger's first name was not entered.",
                "The passenger's last name was not entered."
        ]
        verifyOopsMessages(oopsMessages)
    }

    def clickOnLUVLink() {
        waitForElement(LUV_LINK).click()
    }

    def clickOnGiftCardLink() {
        waitForElement(GIF_CARD_LINK).click()
    }

    def addTravelFundsLUVsAndGiftCards(int amount) {
       amount.times { waitForElement(ADD_TRAVEL_FUND_LINK).click() }
       amount.times { waitForElement(ADD_LUV_VOUCHER_LINK).click() }
       amount.times { waitForElement(ADD_GIFT_CARD_LINK).click() }
    }

    def fillAllTravelFundsConfirmationNumber(String number = "") {
        fillInputs(TRAVEL_FUND_CONFIRMATION_NUMBER_FIELDS, number)
    }

    def fillAllLUVVoucherSecurityCode(String number = "") {
        fillInputs(LUV_VOUCHER_SECURITY_CODE_FIELDS, number)
    }

    def fillAllGiftCardSecurityCode(String number = "") {
        fillInputs(GIFT_CARD_SECURITY_CARD_FIELDS, number)
    }

    def fillAllCardNumberFields(String content) {
        fillInputs(CARD_NUMBER_FIELDS, content)
    }

    def fillAllVoucherNumberFields(String content) {
        fillInputs(VOUCHER_NUMBER_FIELDS, content)
    }

    def fillAllFirstNameFields(String content) {
        fillInputs(FIRST_NAME_FIELDS, content)
    }

    def fillAllLastNameFields(String content) {
        fillInputs(LAST_NAME_FIELDS, content)
    }

    def fillGiftCardNumberFieldByIndex(int index, String content) {
        By cardNumberField = By.id("cardNumber${index}")
        waitForElement(cardNumberField).sendKeys(BasePage.DELETE_EXISTING, content)
    }

    def fillGiftCardCodeFieldByIndex(int index, String content) {
        By cardNumberField = By.id("securityCode${index}")
        waitForElement(cardNumberField).sendKeys(BasePage.DELETE_EXISTING, content)
    }

    def fillVoucherNumberFieldByIndex(int index, String content) {
        By voucherNumberField = By.id("voucherNumber${index}")
        waitForElement(voucherNumberField).sendKeys(BasePage.DELETE_EXISTING, content)
    }

    def fillVocuherCodeFieldByIndex(int index, String content) {
        By voucherCodeField = By.id("voucherSecurityCode${index}")
        waitForElement(voucherCodeField).sendKeys(BasePage.DELETE_EXISTING, content)
    }

    private void fillInputs(By by, String content) {
        waitForElements(by).each {
            it.sendKeys(BasePage.DELETE_EXISTING, content)
        }
    }

    def fillConfirmationNumberAndSecurityCodeWithInvalidLength() {
        clickOnLUVLink()
        clickOnGiftCardLink()
        addTravelFundsLUVsAndGiftCards(3)
        fillAllTravelFundsConfirmationNumber("1")
        fillAllLUVVoucherSecurityCode("1")
        fillAllGiftCardSecurityCode("1")
    }

    def verifyMessageAboutWrongAndMissingData() {
        def oopsMessages = [
                "The Card Number was not entered" : 4,
                "Invalid Security Code length." : 8,
                "The Voucher Number was not entered." : 4,
                "The confirmation number entered is invalid. Southwest Airlines confirmation numbers contain six alpha-numeric characters." : 4,
                "The passenger's first name was not entered." : 4,
                "The passenger's last name was not entered." : 4
        ]
        String message = getExpectedOopsMessage()
        oopsMessages.each {key, value -> verifyNumberOfOccurrences(message, key, value) }
    }

    private def verifyNumberOfOccurrences(String text, String searchText, int amount) {
        int occurrences =  text.count(searchText)
        occurrences.shouldBe amount, "The message: '${searchText}' should apear ${amount} times, got: ${occurrences}"
    }

    def verifyMessageAboutDataIsMissing() {
        def oopsMessages = [
                "The Card Number was not entered" : 4,
                "The Voucher Number was not entered." : 4,
                "The passenger's first name was not entered." : 4,
                "The passenger's last name was not entered." :4
        ]
        String message = getExpectedOopsMessage()
        oopsMessages.each {key, value -> verifyNumberOfOccurrences(message, key, value) }
    }

    def verifyMessageAboutWrongDataAndMissedFields() {
        def oopsMessages = [
                "Invalid Card length." : 4,
                "The Security Code was not entered." : 8,
                "The passenger's last name is not valid. Please enter a valid passenger's last name." : 4,
                "Confirmation Number is required." : 4
        ]
        String message = getExpectedOopsMessage()
        oopsMessages.each {key, value -> verifyNumberOfOccurrences(message, key, value) }
    }

    def verifyMessageAboutFieldMissing() {
        def oopsMessages = [
                "The Security Code was not entered." : 8,
                "Confirmation Number is required." : 4
        ]
        String message = getExpectedOopsMessage()
        oopsMessages.each {key, value -> verifyNumberOfOccurrences(message, key, value) }
    }

    def verifyMessageAbout() {
        def oopsMessages = [
                "We were unable to retrieve your funds from the information provided. Verify the following:",
                "The confirmation number is entered correctly.",
                "The passenger name on the reservation is entered correctly.",
                "The reservation was purchased online via southwest.com or swabiz.com or mobile.southwest.com or Southwest Vacations. Purchases made through Southwest Groups, Southwest ticket counters, or other Travel Agencies cannot be accessed on southwest.com.",
                "The reservation was not made with airtran.com.",
                "If you have questions or require further assistance, please contact a Southwest Airlines Customer Representative at 1-800-I-FLY-SWA (1-800-435-9792).",
                "We were unable to retrieve your travel funds from the information provided. Please try again or if you continue to have difficulties, please contact a Southwest Airlines Customer Service Representative at 1-800-I-FLY-SWA (1-800-435-9792)"
        ]
        verifyOopsMessages(oopsMessages)
    }

    def verifyCheckLuvVoucherToggleLinkIsNotPresent() {
        isElementPresent(CHECK_LUV_VOUCHER_TOGGLE, 10).shouldBe false, "The link 'Check Southwest LUV Voucher' should not be displayed"
    }

    def verifyCheckGiftcardToggleLinkIsNotPresent() {
        isElementPresent(CHECK_GIFTCARD_TOGGLE, 10).shouldBe false, "The link 'Check southwestgiftcard' should not be displayed"
    }

    def verifyAddAnotherTravelFundLinkIsNotPresent() {
        isElementPresent(ADD_ANOTHER_TRAVEL_FUND, 10).shouldBe false, "The link 'Add another Travel Fund' should not be displayed"
    }

    def verifyAddAnotherLuvVoucherLinkIsNotPresent() {
        isElementPresent(ADD_ANOTHER_LUV_VOUCHER, 10).shouldBe false, "The link 'Add another Southwest LUV Voucher' should not be displayed"
    }

    def verifyAddAnotherGiftcardLinkIsNotPresent() {
        isElementPresent(ADD_ANOTHER_GIFTCARD, 10).shouldBe false, "The link 'Add another southwestgiftcard' should not be displayed"
    }

    def verifyMaximumAllowedControls() {
        waitForElement(MAXIMUM_GIFTCARD_ALLOWED).getText().shouldBe MAXIMUM_FOUR_ALLOWED_TEXT, "The text ${MAXIMUM_FOUR_ALLOWED_TEXT} was expected for the giftcards"
        waitForElement(MAXIMUM_TRAVELFUND_ALLOWED).getText().shouldBe MAXIMUM_FOUR_ALLOWED_TEXT, "The text ${MAXIMUM_FOUR_ALLOWED_TEXT} was expected for the travel fund"
        waitForElement(MAXIMUM_VOUCHER_ALLOWED).getText().shouldBe MAXIMUM_FOUR_ALLOWED_TEXT, "The text ${MAXIMUM_FOUR_ALLOWED_TEXT} was expected for the LUV Vouchers"
    }

    private void verifyBalanceAndTotal(By balanceSection, By totalSection) {
        BigDecimal expectedTotal = 0
        waitForElements(balanceSection).each { row ->
            expectedTotal += DollarToBigDecimalConverter.toBigDecimal(row)
        }
        BigDecimal currentTotal  = waitForElement(totalSection).getText().trim().minus("Total: \$").toBigDecimal()
        currentTotal.shouldBeEqual expectedTotal, "The current balance ${expectedTotal} is not equal to the total ${currentTotal}"
    }

    def verifyCorrectBalanceAndTotalDisplayed() {
        verifyBalanceAndTotal(TRAVEL_FUNDS_BALANCE_ROWS, TRAVEL_FUNDS_TOTAL_ROW)
        //verifyBalanceAndTotal(LUV_VOUCHER_BALANCE_ROWS, LUV_VOUCHER_TOTAL_ROW)
        verifyBalanceAndTotal(GIFTCARD_BALANCE_ROWS, GIFTCARD_TOTAL_ROW)
    }

    def verifyCorrectBalanceAndTotalTravelFunds() {
        verifyBalanceAndTotal(TRAVEL_FUNDS_BALANCE_ROWS, TRAVEL_FUNDS_TOTAL_ROW)
    }

    def verifyInvalidTravelFundsPnr() {
        def oopsMessages = [
                "Please remove this fund and re-enter the information confirming the following:",
                "The confirmation number is entered correctly.",
                "The passenger name on the reservation is entered correctly.",
                "The reservation was purchased online via southwest.com or swabiz.com or mobile.southwest.com or Southwest Vacations. Purchases made through Southwest Groups, Southwest ticket counters, or other Travel Agencies cannot be accessed on southwest.com.",
                "The reservation was not made with airtran.com."
        ]
        verifyOopsMessages(oopsMessages)
    }
}
