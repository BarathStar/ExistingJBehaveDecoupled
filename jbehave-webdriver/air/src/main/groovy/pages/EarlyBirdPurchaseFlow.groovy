package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.PurchaseSubForm
import state.Flow
import static org.junit.Assert.fail

public class EarlyBirdPurchaseFlow extends BasePage {

    private def findEligibleEarlyBirdOND = "//table[@id='airItinerary']//img[contains(@src, '_wn_')]/ancestor::tr// input[@type='checkbox']"
    private def findNonEligibleEarlyBirdOND = "//table[@id='airItinerary']//img[contains(@src, '_fl_')]/ancestor::tr// span[@class='notAvailable']"
    private def earlyBirdBadge = "//table[contains(@id, 'airItinerary')]//img[@alt='earlybird icon']"

    private static final By AIRTRAN_MESSAGE = By.xpath("//div[@class='earlyBirdAirTranNotAvailable']")
    private static final By CONFIRMATION_NUMBER = By.id("confirmationNumber")
    private static final By FIRST_NAME = By.id("firstName")
    private static final By LAST_NAME = By.id("lastName")
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By AIRTRAN_LINK = By.id("airTranLink")
    private static final By AIRTRAN_DISCLAIMER_ID = By.id("airtran_disclaimer")
    private static final list_window_title = ["A-list":"Southwest Airlines - A-List FAQ",
                                              "Business_Select":"Southwest Airlines - Business Select FAQ",
                                              "Unaccompanied_Minor":"Southwest Airlines - Unaccompanied Minors FAQ",
                                              "EarlyBird_Check-In":"Southwest Airlines - EarlyBird Check-in FAQ"]

    Flow flow
    PurchaseSubForm[] subForms

    public EarlyBirdPurchaseFlow(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/early-bird-retrieve-reservation.html');
    }

    def isDisplayed() {
        getCurrentUrl().contains("early-bird-choose-flight")
    }

    def verifyAirTranMessage() {
        def msg = (waitForElement(AIRTRAN_MESSAGE).getText())
        if (!msg.contains("AirTran")) {
            fail "AirTran Message not found"
        }
    }

    def retrievePNRForEarlyBirdCheckIn(String pnr, String fName, String lName) {
        waitForElement(CONFIRMATION_NUMBER).sendKeys DELETE_EXISTING + pnr
        waitForElement(FIRST_NAME).sendKeys DELETE_EXISTING + fName
        waitForElement(LAST_NAME).sendKeys DELETE_EXISTING + lName
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

    void verifySwaBizNotPurchaseFromDotComOops() {
        String oopsMessage = "We apologize, but EarlyBird Check-In for SWABIZ reservations must be purchased via swabiz.com. "+
                             "We are working towards offering this service on southwest.com in the future. "+
                             "Please make your EarlyBird Check-In purchase through SWABIZ. (SW900001)"
        shouldShowOopsMessage(oopsMessage)
    }

    def verifyEligibleEarlyBird() {
        verifyElementPresent("SouthWest EB Eligibility", By.xpath(findEligibleEarlyBirdOND))
    }

    def verifyEarlyBirdBadge(){
        verifyElementPresent("EarlyBird Badge",By.xpath(earlyBirdBadge))
    }

    def verifyNonEligibleEarlyBird() {
        verifyElementPresent("AirTran N/A", By.xpath(findNonEligibleEarlyBirdOND))
    }

    def verifyEarlyBirdNotEligibleMessageForAirTran() {
       def expectedAirTranEarlyBirdNotAvailableMessage = "Thank you for your interest in EarlyBird Check-In, unfortunately this itinerary does not qualify for EarlyBird Check-In."
       verifyTextPresent(expectedAirTranEarlyBirdNotAvailableMessage, By.id("airTranThankYouForInterest"))
    }

    def verifyEarlyBirdNotEligibleMessage() {
        def expectedEarlyBirdNotAvailableMessage = "Thank you for your interest in EarlyBird Check-In. The passengers associated with this itinerary do not qualify or have already purchased EarlyBird Check-In."
        verifyTextPresent(expectedEarlyBirdNotAvailableMessage, By.id("thankYouForInterest"))
    }

    def clickOnLinkInEarlyBird(String linkTitle) {
        String winHandleBefore = webDriver().getWindowHandle()
        waitForElement(By.xpath("//a[@title='$linkTitle']")).click()
        switchToOpenWindow(winHandleBefore,list_window_title[linkTitle])
    }

    def verifyEarlyBirdNoContinueText() {
        verifyElementNotPresent("Continue to pricing and payment", By.xpath("//h4[@class='aboveSubmitButtonText']"))
    }

    void clickSubmitButton() {
        waitForElement(SUBMIT_BUTTON).click()
        verifyPage()
    }

    void clickSubmitButtonWithoutVerifyPage() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def verifyContinueButtonNotPresent() {
            WebElement cancel = waitForElement(By.id("cancelButton"), false)
            WebElement submit = waitForElement(By.id("submitButton"), false)
            if((submit != null)||(cancel!=null)) {
                fail("cancel and submit button found")
            }
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def verifyAirtranDisclaimerMessage(){
        waitForElement(AIRTRAN_DISCLAIMER_ID)
    }
}
