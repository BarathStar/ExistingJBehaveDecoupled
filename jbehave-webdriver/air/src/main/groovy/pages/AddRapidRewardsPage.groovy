package pages

import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import state.Flow
import util.PageName

public class AddRapidRewardsPage extends BasePage {

    private static final By TOP_PAGE_INSTRUCTIONS = By.cssSelector("div.topPageInstructions")
    private static final By ADD_RAPID_REWARDS_LINK = By.xpath("//a[@title='Add Rapid Rewards']")
    private static final By CONFIRMATION_NUMBER_FIELD = By.id("confirmationNumber")
    private static final By FIRST_NAME_FIELD = By.id("firstName")
    private static final By LAST_NAME_FIELD = By.id("lastName")
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By AIRTRAN_LINK = By.id("airTranLink")
    private static final By AIRTRAN_DISCLAIMER_ID = By.id("airtran_disclaimer")
    private static final By ADD_RAPID_REWARDS_NUMBER_LINK = By.xpath("//*[@id='sw_content']/div[2]/div[2]/div/div[1]/div[4]/div[2]/a")
    private static final By ENROLL_IN_RAPID_REWARDS_IMAGE = By.className("swa_panels_guest_tip_container")
    private static final By ADD_RAPID_REWARDS_NUMBER_CRUMB = By.className("swa_nav_globalNav_horizontal_span")
    private static final By LOOKUP_CONFIRMATION_LINK = By.cssSelector(".swa_feature_addRREntry_lookupConfirmationNumber a")
    private static final By ADD_RAPID_REWARDS_TITLE = By.id("cancelTitle")
    private static final String ADD_RAPID_REWARDS_NUMBER_FIELD_ID = "rapidRewardsNumber"
    static String DELETE_EXISTING = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE

    Data data
    Flow flow

    String getRelativePath() {
        return "addRRNumPnrEntry.html"
    }

    public AddRapidRewardsPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/addRRNumPnrEntry.html');
    }

    def open() {
        super.open()
        checkSomethingServed()
    }

    void enterReservationInfo(String pnr, String pnr_firstName, String pnr_lastName){
        waitForElement(CONFIRMATION_NUMBER_FIELD).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(FIRST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_firstName)
        waitForElement(LAST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_lastName)
        waitForElement(SUBMIT_BUTTON).click()
    }

    void verifyEnterRapidRewardsPage(){
        waitForElement(By.className("swa_feature_addRRconfirmation_header"),false).getText().shouldContain "Enter", "Did not find enter rapid rewards page"
        super.verifyPage()
    }

    void verifyAddRapidRewardsNumberPage(){
        waitForElement(By.className("swa_feature_addRREntry_header"),false).getText().shouldEqual "Enter Your Reservation Info"
        super.verifyPage()
    }

    def verifyAirTranMessage(){
        waitForElement(TOP_PAGE_INSTRUCTIONS).getText().shouldContain "AirTran"
    }

    void enterRapidRewardsNumber(int rrFieldIndex = 0) {
        waitForElement(By.id(ADD_RAPID_REWARDS_NUMBER_FIELD_ID + rrFieldIndex)).sendKeys(data.getUser("goodUser").getRRNumber())
        waitForElement(SUBMIT_BUTTON).click()
        super.verifyPage()
    }

     void enterRapidRewardsNumber(String rrNumber, int rrFieldIndex = 0) {
        waitForElement(By.id(ADD_RAPID_REWARDS_NUMBER_FIELD_ID + rrFieldIndex)).sendKeys(rrNumber)
        waitForElement(SUBMIT_BUTTON).click()
    }

    void clickAddRapidRewardsLink() {
        waitForElement(ADD_RAPID_REWARDS_LINK).click()
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

    def verifyAirtranDisclaimerMessage(){
        waitForElement(AIRTRAN_DISCLAIMER_ID)
    }

    void verifyPrePopulatedValueInAddRapidRewardsPage(){
        waitForElement(CONFIRMATION_NUMBER_FIELD).getAttribute("value").shouldNotBeEmpty " Confirmation Number Not Found"
        waitForElement(FIRST_NAME_FIELD).getAttribute("value").shouldNotBeEmpty " First Name Not Found"
        waitForElement(LAST_NAME_FIELD).getAttribute("value").shouldNotBeEmpty " Last Name Not Found"
    }

    def clickAddRapidRewardsNumberLink(){
        waitForElement(ADD_RAPID_REWARDS_NUMBER_LINK).click()
    }

    def clickRREnrollImage() {
        waitForElement(ENROLL_IN_RAPID_REWARDS_IMAGE).click()
    }

    void verifyFirstNameAndLastName(){
        waitForElement(FIRST_NAME_FIELD).getAttribute("value").shouldNotBeEmpty " First Name Not Found"
        waitForElement(LAST_NAME_FIELD).getAttribute("value").shouldNotBeEmpty " Last Name Not Found"
    }

    def verifyPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify add rapid rewards page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify add rapid rewards page) ---- checking for fault injection"
        }
        System.getProperty("domainToTest") ? getCurrentUrl().shouldContain (System.getProperty("domainToTest")) : getCurrentUrl().shouldContain ("local.swacorp.com")
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.ADD_RAPID_REWARDS)
        waitForElement(ADD_RAPID_REWARDS_NUMBER_CRUMB).text.shouldContain "Add Rapid Rewards Number", "Page crumb did not match the expected value"
    }

    def verifyTitle() {
        waitForElement(ADD_RAPID_REWARDS_TITLE).text.shouldBe "Add Rapid Rewards Number", "Add Rapid Rewards Number title did not match the expected value"
    }

    def verifyLookupConfirmationLink() {
        isElementPresent(LOOKUP_CONFIRMATION_LINK).shouldBe true, "Lookup confirmation link was not present in the page"
    }
}