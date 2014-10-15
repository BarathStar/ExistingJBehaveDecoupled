package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

import domain.RapidRewardsAccount
import org.apache.commons.lang.WordUtils
import com.swacorp.dotcom.webscenarios.air.config.Domains


public class CreateAnAccountPage extends BasePage {

    private static final String SECURITY_QUESTION_1_OOPS = "Please select Security Question 1."
    private static final String SECURITY_QUESTION_2_OOPS = "Please select Security Question 2."
    private static final String MAINTENANCE_MODE_OOPS_MESSAGE = "This portion of the Southwest Airlines website is undergoing maintenance and is currently unavailable."
    private static final By EMAIL_ADDRESS = By.id("js-contact-email")
    private static final By CONFIRM_EMAIL_ADDRESS = By.id("js-contact-email-confirmation")
    private static final By USER_NAME = By.id("js-username")
    private static final By ACCOUNT_PASSWORD = By.id("account.password")
    private static final By CONFIRM_PASSWORD = By.id("js-password-confirmation")
    private static final By SECURITY_QUESTION = By.id("js-security-question-1")
    private static final By SECURITY_QUESTION_TWO = By.id("js-security-question-2")
    private static final By SECURITY_ANSWER = By.id("js-security-answer-1")
    private static final By SECURITY_ANSWER_TWO = By.id("js-security-answer-2")
    private static final By FIRST_NAME = By.id("js-customer-first-name")
    private static final By MIDDLE_NAME = By.id("js-customer-middle-name")
    private static final By LAST_NAME = By.id("js-customer-last-name")
    private static final By CONTACT_INFO_ADDRESS_LINE = By.id("js-contact-info-address1")
    private static final By CONTACT_INFO_CITY = By.id("js-contact-info-city")
    private static final By CONTACT_INFO_ZIPCODE = By.id("js-contact-info-zip")
    private static final By CONTACT_INFO_PHONE_NUMBER_AREA_CODE = By.id("js-us-area-code")
    private static final By CONTACT_INFO_PHONE_NUMBER_PREFIX_NUMBER = By.id("js-us-prefix-number")
    private static final By CONTACT_INFO_PHONE_NUMBER_LINE_NUMBER = By.id("js-us-line-number")
    private static final By CONTACT_INFO_PHONE_NUMBER = By.id("js-intl-number")
    private static final By RULES_AND_REGULATIONS = By.id("accept-rules-and-regulations")
    private static final By CUSTOMER_ACCOUNT_TYPE = By.id("accountTypeCustomer")
    private static final By SUBMIT_BUTTON = By.id("js-submit-button")
    private static final By PAGE_TITLE = By.id("page_title")
    private static final Integer BASE_YEAR = 1900
    private static final By CLICK_N_SAVE_CHECKBOX = By.id("customer.receiveClickAndSaveEmail1")
    private static final By IN_A_NUTSHELL_CHECKBOX = By.id("customer.receiveInaNutshellEmail1")
    private static final By RRRP_CHECKBOX = By.id("customer.receiveRapidRewardsReport1")
    private static final By RREU_CHECKBOX = By.id("customer.receiveRapidRewardsUpdate1")
    private static final By ERRORS = By.id("errors")
    private static final By ADDRESS_TYPE_OPTIONS = By.cssSelector(".addressTypeRadioButtons .radio-option")
    private static final By PHONE_TYPE_OPTIONS = By.cssSelector("#js-contact-phone .radio-option")
    private static final By ENROLL_RADIOBUTTON_YES = By.id("js-account-type-member")
    private static final By ENROLL_RADIOBUTTON_NO = By.id("js-account-type-customer")
    private static final String SECURITY_QUESTION_1_ID_STRING = "js-security-question-1"
    private static final String SECURITY_QUESTION_2_ID_STRING = "js-security-question-2"
    private static final String DEFAULT_QUESTION = "Choose Your Security Question"
    private static final String CONTINUE = "Continue"
    private static final String ENROLL_RADIOBUTTON_YES_TEXT = "Yes"
    private static final String ENROLL_RADIOBUTTON_NO_TEXT = "No"

    public CreateAnAccountPage(WebDriverProvider driverProvider,
                               String pagePath,
                               String domain) {
        super(driverProvider, pagePath ?: '/account/enroll/enroll-member' , domain ?: Domains.dotcomDomain);
    }

    def open() {
        super.open()
        checkSomethingServed()
    }

    /* This method should not be use, use AcountSteps.fillAndConfirmNewRapidRewardsAccount() instead.*/

    def fillInRequiredInformation(RapidRewardsAccount rrAccount) {
        fillIn(EMAIL_ADDRESS, rrAccount.email)
        fillIn(CONFIRM_EMAIL_ADDRESS, rrAccount.email)
        fillIn(USER_NAME, rrAccount.username)
        fillIn(ACCOUNT_PASSWORD, rrAccount.password)
        fillIn(CONFIRM_PASSWORD, rrAccount.password)
        selectFromDropDownRandom(SECURITY_QUESTION, DEFAULT_QUESTION)
        selectFromDropDownRandom(SECURITY_QUESTION_TWO, DEFAULT_QUESTION)
        fillIn(SECURITY_ANSWER, rrAccount.securityAnswer)
        fillIn(SECURITY_ANSWER_TWO, rrAccount.securityAnswer2)
        fillIn(FIRST_NAME, rrAccount.firstName)
        fillIn(LAST_NAME, rrAccount.lastName)
        chooseInDropDownByValue("js-customer-birth-month", (rrAccount.birthMonth + 1).toString())
        chooseInDropDownByValue("js-customer-birth-day", rrAccount.birthDay.toString())
        chooseInDropDownByValue("js-customer-birth-year", (rrAccount.birthYear + BASE_YEAR).toString())
        chooseInDropDownByText("js-customer-gender", rrAccount.gender)
        chooseInDropDownByText("js-country", rrAccount.country)
        chooseInDropDownByText("js-contact-info-state", rrAccount.state)
        fillIn(CONTACT_INFO_ADDRESS_LINE, rrAccount.address)
        fillIn(CONTACT_INFO_CITY, rrAccount.city)
        fillIn(CONTACT_INFO_ZIPCODE, rrAccount.zipCode)
        fillIn(CONTACT_INFO_PHONE_NUMBER_AREA_CODE, rrAccount.areaCode)
        fillIn(CONTACT_INFO_PHONE_NUMBER_PREFIX_NUMBER, rrAccount.prefixNumber)
        fillIn(CONTACT_INFO_PHONE_NUMBER_LINE_NUMBER, rrAccount.lineNumber)
        checkAddressType(rrAccount.addressType)
        checkPhoneType(rrAccount.phoneType)
    }

    def fillInMiddleName(String middleName){
        fillIn(MIDDLE_NAME,middleName)
    }

    def clickOnCreateACustomerAccount() {
        waitForElement(CUSTOMER_ACCOUNT_TYPE).click()
    }

    def clickOnCreateARapidRewardAccount() {
        waitForElement(RULES_AND_REGULATIONS).click()
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    def submit() {
        performClickAction(SUBMIT_BUTTON)
    }

    boolean isCurrentPage() {
        isElementWithTextPresent(PAGE_TITLE, "Create an Account")
    }

    def verifyOnCurrentPage() {
        isCurrentPage().shouldBe true, "You should be on Create an Account Page"
    }

    def clickOnClickNSaveCheckbox() {
        selectOnCheckBox(CLICK_N_SAVE_CHECKBOX)
    }

    def clickOnInANutshellCheckbox() {
        selectOnCheckBox(IN_A_NUTSHELL_CHECKBOX)
    }

    def clickOnRrrpCheckBox() {
        selectOnCheckBox(RRRP_CHECKBOX)
    }

    def clickOnRreuCheckBox() {
        selectOnCheckBox(RREU_CHECKBOX)
    }

    def selectOnCheckBox(By checkBox) {
        WebElement checkboxElement = waitForElement(checkBox)
        if(!checkboxElement.isSelected()) {
            checkboxElement.click()
        }
    }

    def checkAddressType(final String optionToSelect){
        selectOptionButton(optionToSelect,"value",ADDRESS_TYPE_OPTIONS)
    }

    def checkPhoneType(final String optionToSelect){
        selectOptionButton(optionToSelect,"title",PHONE_TYPE_OPTIONS)
    }

    private selectOptionButton(String optionToSelect, final String attribute, By radioButtonsSet) {
        List<WebElement> optionsList = waitForElements(radioButtonsSet)
        optionsList.each {option ->
            if (option.getAttribute(attribute) == optionToSelect) {
                option.click()
            }
        }
    }

    def uncheckOption(By checkBox) {
        WebElement checkboxElement = waitForElement(checkBox)
        if(checkboxElement.isSelected()) {
            checkboxElement.click()
        }
    }

    def verifyClickAndSaveAndInANutshellAreNotSelected() {
        waitForElement(CLICK_N_SAVE_CHECKBOX).isSelected().shouldBe false, "Click N' Save email suscription should not be selected"
        waitForElement(IN_A_NUTSHELL_CHECKBOX).isSelected().shouldBe false, "In a Nutshell email suscription should not be selected"
    }

    def verifyRapidRewardsReportAndUpdateAreSelected() {
        waitForElement(RRRP_CHECKBOX).isSelected().shouldBe true, "The Rapid Rewards Report email suscription should be selected"
        waitForElement(RREU_CHECKBOX).isSelected().shouldBe true, "The Rapid Rewards E-mail Update email suscription should be selected"
    }

    def verifyMandatoryFieldsOopsMessagesIsPresent() {
        isElementDisplayed(ERRORS).shouldBe true, "An error message for each mandatory field should be present"
    }

    def verifyInvalidPasswordOopsMessagesIsPresent() {
        def oopsMessages = ['The Password you entered is not valid. A Password is case sensitive and cannot contain spaces or special characters.']
        verifyOopsMessages(oopsMessages)
       }

    def verifyShortPasswordOopsMessagesIsPresent() {
        def oopsMessages = ['The Password you entered is not valid. A Password must have a minimum of 6 characters and maximum of 16 characters.']
        verifyOopsMessages(oopsMessages)
       }

    def verifyBlackListedPasswordOopsMessagesIsPresent(String password) {
        def oopsMessages = ['The Password you entered is not valid. A Password cannot contain "'+password+'".']
        verifyOopsMessages(oopsMessages)
       }

    def verifyClickAndSaveAndNutshellAreSelected() {
        waitForElement(CLICK_N_SAVE_CHECKBOX).isSelected().shouldBe true, "Click N' Save email suscription should be selected"
        waitForElement(IN_A_NUTSHELL_CHECKBOX).isSelected().shouldBe true, "In a Nutshell email suscription should be selected"
    }

    def verifyRapidRewardsReportAndUpdateAreNotSelected() {
        waitForElement(RRRP_CHECKBOX).isSelected().shouldBe false, "The Rapid Rewards Report email suscription should not be selected"
        waitForElement(RREU_CHECKBOX).isSelected().shouldBe false, "The Rapid Rewards E-mail Update email suscription should not be selected"
    }

    def uncheckRRReportOption() {
        uncheckOption(RRRP_CHECKBOX)
    }

    def uncheckRRUpdateOption() {
        uncheckOption(RREU_CHECKBOX)
    }

    def chooseSecurityQuestion1() {
        selectFromDropDownRandom(SECURITY_QUESTION, DEFAULT_QUESTION)
    }

    def chooseSecurityQuestion2() {
        selectFromDropDownRandom(SECURITY_QUESTION_TWO, DEFAULT_QUESTION)
    }

    def verifySelectedElementNotInAnotherSelect1() {
        verifySelectedElementNotInAnotherSelect(SECURITY_QUESTION,SECURITY_QUESTION_2_ID_STRING).shouldBe false, "Security questions cannot be selected twice"
    }

    def verifySelectedElementNotInAnotherSelect2() {
        verifySelectedElementNotInAnotherSelect(SECURITY_QUESTION_TWO, SECURITY_QUESTION_1_ID_STRING).shouldBe false, "Security questions cannot be selected twice"
    }

    private boolean verifySelectedElementNotInAnotherSelect(By securityQuestion1, String securityQuestion2) {
        WebElement aux = waitForElement(securityQuestion1)
        Select select = new Select(aux)
        WebElement option = select.getFirstSelectedOption()
        String optionText = option.getText()

        return isOptionInDropDown(securityQuestion2, optionText)
    }

    private def deSelectSecurityQuestion(By securityQuestion) {
        selectFromDropDownByIndex(securityQuestion, 0)
    }

    def deselectSecurityQuestion1() {
        deSelectSecurityQuestion(SECURITY_QUESTION)
    }

    def deselectSecurityQuestion2() {
        deSelectSecurityQuestion(SECURITY_QUESTION_TWO)
    }

    def verifySecurityQuestion1(){
        shouldShowOopsMessage(SECURITY_QUESTION_1_OOPS)
    }

    def verifySecurityQuestion1IsNotPresent(){
        shouldNotShowOopsMessage(SECURITY_QUESTION_1_OOPS)
    }

    def verifySecurityQuestion2(){
        shouldShowOopsMessage(SECURITY_QUESTION_2_OOPS)
    }

    def verifySecurityQuestion2IsNotPresent(){
        shouldNotShowOopsMessage(SECURITY_QUESTION_2_OOPS)
    }

    def verifyBothSecurityQuestions(){
        shouldShowOopsMessage(SECURITY_QUESTION_1_OOPS)
        shouldShowOopsMessage(SECURITY_QUESTION_2_OOPS)
    }

    def verifySubmitButtonPresent(){
        isElementDisplayed(SUBMIT_BUTTON).shouldBe true, "Submit button on Create an Account page should be displayed and is not."
    }

    def verifySubmitButtonTextIsContinue(){
        verifyTextPresent(CONTINUE,SUBMIT_BUTTON,"Submit button text should be ${CONTINUE}")
    }

    def areAddressAndNameCapitalized() {
        String addressLineOne = waitForElement(CONTACT_INFO_ADDRESS_LINE).getAttribute("value")
        String firstName = waitForElement(FIRST_NAME).getAttribute("value")
        String lastName = waitForElement(LAST_NAME).getAttribute("value")
        String concatenatedFields = addressLineOne.concat(firstName).concat(lastName)

        return concatenatedFields.compareTo(WordUtils.capitalize(concatenatedFields))==0
    }

    def verifyInformationCleansed(){
        areAddressAndNameCapitalized().shouldBe true,"The customer information Should be cleansed"
    }

    def verifyInformationNotCleansed(){
        areAddressAndNameCapitalized().shouldBe false, "The customer information  Should'nt  be cleansed"
    }

    def fillInPassword(RapidRewardsAccount rrAccount) {
        waitForElement(ACCOUNT_PASSWORD).click()   // This is necessary to allow the password field to enter the data. - Remove and die; minimally you will make the test fail
        waitForElement(ACCOUNT_PASSWORD).sendKeys(rrAccount.password)
        fillIn(CONFIRM_PASSWORD, rrAccount.password)
    }

    void verifyMaintenanceModeOopsMessageIsDisplayed() {
        shouldShowOopsMaintenanceMessage(MAINTENANCE_MODE_OOPS_MESSAGE)
    }

    def verifyRadiobuttonYesLabelForEnrollmentSection(){
        waitForElement(ENROLL_RADIOBUTTON_YES).getAttribute("title").shouldBeEqual ENROLL_RADIOBUTTON_YES_TEXT, "The label should not be empty or different from: " + ENROLL_RADIOBUTTON_YES_TEXT
    }

    def verifyRadiobuttonNoLabelForEnrollmentSection(){
        waitForElement(ENROLL_RADIOBUTTON_NO).getAttribute("title").shouldBeEqual ENROLL_RADIOBUTTON_NO_TEXT, "The label should not be empty or different from: " + ENROLL_RADIOBUTTON_NO_TEXT
    }

    def fillAndConfirmNewRapidRewardsAccountWithInvalidEmail(RapidRewardsAccount rrAccount) {
        fillInRequiredInformation(rrAccount)
        clickOnCreateARapidRewardAccount()
        submit()
    }

    def attemptFillInUsername(String username) {
        attemptFillIn(USER_NAME,username,false)
    }
}
