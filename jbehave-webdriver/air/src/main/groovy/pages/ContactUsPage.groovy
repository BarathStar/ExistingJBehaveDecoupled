package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import com.swacorp.dotcom.webscenarios.air.config.Domains
import util.PageName



class ContactUsPage extends BasePage {


    private static final String PATH = "/contact-us/contact-us.html"
    private static final String PATH_AIRTRAN = "/flight/emailUsAirTran/emailUs.html"
    private static final String PATH_AIRTRAN_SUPPLEMENT_INFORMATION = "/flight/emailUsAirTran/feedbackRFIEntry.html"
    private static final String ORIGIN_AIPORT_DISPLAYED = "originAirport_displayed"
    private static final String ADITIONAL_INFORMATION_OOPS = "Additional Information was not entered."
    private static final String THANK_YOU_MESSAGE = "Thank You! modal"
    private static final By OOPS_CLASS = By.className("oopsError_list");
    private static final By EMAIL_US_LINK = By.id("emailUs_emailUs_button")
    private static final By EMAIL_US_BUTTON = By.id("emailUs_emailUs_button")
    private static final By NATURE_OF_COMMUNICATION = By.id("emailNatureCommunication")
    private static final By EMAIL_CLASSIFICATION = By.id("emailClassification")
    private static final By EMAIL_FIRST_NAME = By.id("emailFirstName")
    private static final By EMAIL_LAST_NAME = By.id("emailLastName")
    private static final By EMAIL = By.id("email")
    private static final By CONFIRM_EMAIL = By.id("confirmEmail")
    private static final By EMAIL_AREA_CODE = By.id("emailAreaCode")
    private static final By EMAIL_CONTACT_PREFIX = By.id("emailContactPrefix")
    private static final By EMAIL_LINE_NUMBER = By.id("emailLineNumber")
    private static final By EMAIL_ADDRESS1 = By.id("emailAddress1")
    private static final By EMAIL_CITY = By.id("emailCity")
    private static final By EMAIL_STATE = By.id("emailState")
    private static final By EMAIL_ZIP1 = By.id("emailZip1")
    private static final By EMAIL_COMMENT = By.id("emailComment")
    private static final By EMAIL_SUBMIT_BUTTON = By.className("submitButton")
    private static final By OUTSIDE_US_RADIO_BUTTON = By.id("emailWithinOutsideUs2")

    private static final By STATE_OUTSIDE_US_FIELD = By.id("emailStateProvinceRegion")
    private static final By POSTAL_OUTSIDE_US_FIELD = By.id("emailPostalCode")
    private static final By COUNTRY_OUTSIDE_US_FIELD = By.id("emailCountry")
    private static final By COUNTRY_CODE_NUMBER_OUTSIDE_US_FIELD = By.id("emailPhoneCountryCode")
    private static final By LINE_PHONE_NUMBER_OUTSIDE_US_FIELD = By.id("emailIntlPhoneLineNumber")

    private static final By SUPPLEMENTAL_INFORMATION_SUBMITBUTTON = By.id("supplementalInformation_form_submitButton")
    private static final By THANK_YOU_MODAL = By.id("swa_feature_emailUs_thankYouMesssage_container")
    private static final By THANK_YOU_MODAL_RFI = By.id("swa_page_header_title")
    private static final By CLOSE_BUTTON = By.className("closeButton")
    private static final By AC_RESULTS = By.className("ac_results")
    private static final By CONTACT_US_CONTAINER = By.id("emailUs_landing_container")

    ContactUsPage(WebDriverProvider driverProvider) {
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

    def verifyPage(){
        getCurrentUrl().shouldContain ("contact-us")
        pageVerificationErrorWrapper(CONTACT_US_CONTAINER, PageName.CONTACT_US)
        super.verifyPage()
    }

    def openContactUsPageAirtran() {
        get(Domains.dotcomDomain + PATH_AIRTRAN)
    }

    def openSupplementInformationPageAirtran(){
        get(Domains.dotcomDomain + PATH_AIRTRAN_SUPPLEMENT_INFORMATION)
    }

    def clickOnEMailUsButton() {
        waitForElement(EMAIL_US_BUTTON).click()
    }

    def selectNatureOfCommunication(String value) {
        chooseInDropDownByValue(NATURE_OF_COMMUNICATION, value)
    }

    def selectEmailClassification(String value) {
        chooseInDropDownByValue(EMAIL_CLASSIFICATION, value)
    }

    def selectOutsideUSCountry(String value) {
        chooseInDropDownByValue(COUNTRY_OUTSIDE_US_FIELD, value)
    }

    def fillEmailFirstName(String value) {
        fillIn(EMAIL_FIRST_NAME, value)
    }

    def fillEmailLastName(String value) {
        fillIn(EMAIL_LAST_NAME, value)
    }

    def fillEmail(String value) {
        fillIn(EMAIL, value)
    }

    def fillConfirmEmail(String value) {
        fillIn(CONFIRM_EMAIL, value)
    }

    def fillEmailAreaCode(String value) {
        fillIn(EMAIL_AREA_CODE, value)
    }

    def fillEmailContactPrefix(String value) {
        fillIn(EMAIL_CONTACT_PREFIX, value)
    }

    def fillEmailLineNumber(String value) {
        fillIn(EMAIL_LINE_NUMBER, value)
    }

    def fillEmailOutsideUSCountryCode(String value) {
        fillIn(COUNTRY_CODE_NUMBER_OUTSIDE_US_FIELD, value)
    }

    def fillEmailOutsideUSLineNumber(String value) {
        fillIn(LINE_PHONE_NUMBER_OUTSIDE_US_FIELD, value)
    }

    def fillEmailAddress(String value) {
        fillIn(EMAIL_ADDRESS1, value)
    }

    def fillEmailCity(String value) {
        fillIn(EMAIL_CITY, value)
    }

    def selectEmailState(String value) {
        chooseInDropDownByValue(EMAIL_STATE, value)
    }

    def fillEmailOutsideUSState(String value) {
        fillIn(STATE_OUTSIDE_US_FIELD, value)
    }

    def fillEmailZip(String value) {
        fillIn(EMAIL_ZIP1, value)
    }

    def fillEmailOutsideUSPostal(String value) {
        fillIn(POSTAL_OUTSIDE_US_FIELD, value)
    }

    def fillEmailComment(String value) {
        fillIn(EMAIL_COMMENT, value)
    }

    def fillOriginAiport(String originAiport){
        setValue(ORIGIN_AIPORT_DISPLAYED,originAiport)
    }

    def clickOnSubmitButton() {
        waitForElement(EMAIL_SUBMIT_BUTTON).click()
    }

    def clickOnSubmitButtonSupplementInformation(){
        waitForElement(SUPPLEMENTAL_INFORMATION_SUBMITBUTTON).click()
    }

    def verifyThankYouModalIsPresent() {
        verifyElementPresent(THANK_YOU_MESSAGE, THANK_YOU_MODAL)
    }

    def verifyThankYouModalIsPresentRFI() {
        verifyElementPresent(THANK_YOU_MESSAGE, THANK_YOU_MODAL_RFI)
    }

    def clickOnCloseButton() {
        waitForElement(CLOSE_BUTTON).click()
    }

    def verifyThankYouModalIsNotPresent() {
        isElementDisplayed(THANK_YOU_MODAL).shouldBe false, "The Thank You! modal should not be present"
    }

    def verifyAcResultsIsPresent(){
        isElementDisplayed(AC_RESULTS).shouldBe true, "Not airport found on field called " + AC_RESULTS
    }

    def verifySupplementInformationOopsMessage(){
        shouldShowOopsMessage(ADITIONAL_INFORMATION_OOPS,OOPS_CLASS)
    }

    def verifyAirtranFieldsOopsMessage() {
        List<String> oopsMessage = getAllOopsMessageAirtran()
        def expectedOopsMessages = getExpectedOopsMessage(OOPS_CLASS)
        for (msg in oopsMessage) {
           expectedOopsMessages.shouldContain msg, "The Oops message '$msg' has not been found within the text '$expectedOopsMessages'"
        }
    }

    def clickOutsideUSOption() {
        waitForElement(OUTSIDE_US_RADIO_BUTTON).click()
    }

    def verifyOutsideUSRequiredFieldsIsPresent() {
        waitForElement(STATE_OUTSIDE_US_FIELD).isDisplayed().shouldBe true, "The input field for State/Province/Region under Address Information was expected, but is not present."
        waitForElement(POSTAL_OUTSIDE_US_FIELD).isDisplayed().shouldBe true, "The input field for Postal Code under Address Information was expected, but is not present."
        waitForElement(COUNTRY_OUTSIDE_US_FIELD).isDisplayed().shouldBe true, "The input field for Country under Address Information was expected, but is not present."
        waitForElement(COUNTRY_CODE_NUMBER_OUTSIDE_US_FIELD).isDisplayed().shouldBe true, "The input field for Country Code under Address Information was expected, but is not present."
        waitForElement(LINE_PHONE_NUMBER_OUTSIDE_US_FIELD).isDisplayed().shouldBe true, "The input field for Phone Line under Address Information was expected, but is not present."
    }

    private List<String> getAllOopsMessageAirtran(){
        List<String> oopsMessages = new ArrayList<String>();
        oopsMessages.add("Nature of Communication was not entered")
        oopsMessages.add("Classification was not entered")
        oopsMessages.add("First Name was not entered")
        oopsMessages.add("Last Name was not entered")
        oopsMessages.add("E-mail was not entered")
        oopsMessages.add("Confirm E-mail was not entered")
        oopsMessages.add("The Address was not entered")
        oopsMessages.add("The City was not entered")
        oopsMessages.add("The Comment was not entered")
        oopsMessages.add("The Zip Code was not entered")
        oopsMessages.add("The Area code for the Phone Number was not entered")
        oopsMessages.add("The Prefix code for the Phone Number was not entered")
        oopsMessages.add("The Phone Number was not entered")
        oopsMessages.add("The State was not entered")
        return oopsMessages;
    }
}
