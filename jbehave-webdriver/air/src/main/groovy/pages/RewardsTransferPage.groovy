package pages

import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.rewardsTransfer.AwardRow
import state.Flow
import static pages.elements.rewardsTransfer.AwardRow.findAwardRowByName

class RewardsTransferPage extends BasePage {

    private static final By SUBMIT_BUTTON = By.className("submitButton")
    private static final By SEE_MORE_AWARDS_LINK = By.id("seeMoreAwards")
    private static final By SEE_FEWER_AWARDS_LINK = By.id("seeFewAwards")
    private static final By REWARDS_TRANSFER_SUBTITLE = By.id("rewardsTransferSubtitle")
    private static final By POINTS_AIRTRAN_CONTAINER = By.id("pointsToCreditsAmount")
    private static final By AWARDS_AIRTRAN_CONTAINER = By.xpath("//div[@class='awardTransferValue airtranInfoSectionMessage' and contains(@style,'display: block')]")
    private static final By REWARDS_TRANSFER_POINTS_DISPLAY = By.id("pointsRewardsDisclaimer")
    private static final By TRANSFERABLE_AWARDS_AND_CERTIFICATES = By.id("rrAwardsAndCertificatesSections")
    private static final By TRANSFERABLE_CREDITS = By.id("rrCreditsSection")
    private static final By TRANSFERABLE_POINTS = By.id("rrPointsSection")
    private static final By FIRST_AWARD_RADIO_BUTTON = By.id("awardRadioButton0")
    private static final By FOURTH_AWARD_RADIO_BUTTON = By.id("awardRadioButton3")
    private static final By AIRTRAN_CREDITS = By.id("airTranCredits3")
    private static final By TRANSFER_BUTTON = By.id("transferButton")
    private static final By TRANSFER_RULES_CHECKBOX = By.id("agreeRulesCreditTransferChecked")
    private static final By CREDITS_REWARDS_DISCLAIMER = By.id("creditsRewardsDisclaimer")
    private static final By RAPID_REWARDS_LOGIN_PASSWORD = By.id("rapidRewardsLoginPassword")
    private static final By CREDITS_REWARDS_BUTTON = By.id("creditsRadioButton")
    private static final By POINTS_TO_BE_TRANSFERRED = By.id("pointsToBeTransferred")
    private static final By CREDITS_TO_BE_TRANSFERRED = By.id("creditsToBeTransferred")
    private static final By AIR_TRAN_CREDITS_RESULTS = By.id("airTranCreditsResults")
    private static final By POINTS_BUTTON = By.id("pointsRadioButton")
    private static final By REWARDS_TRANSFER_HIGHLIGHTED_ROW = By.className("rewardsTransferEntryDisplayHighlightBox")
    private static final By RAPID_REWARDS_LOGIN_USER_NAME = By.id("rapidRewardsLoginMemberId")
    private static final By CONFIRMATION_MESSAGE = By.id("confirmTransferText")
    private static final By AIRTRAN_REWARDS_TRANSFER_RADIO_BUTTON =By.id("transferOrientationAirtran")
    private static final By APLUS_CREDITS_TO_BE_TRANSFERRED = By.id("aplusCreditsToBeTransferred")
    private static final By AIRTRAN_CREDITS_REWARDS_DISCLAIMER = By.id("airtranCreditsRewardsDisclaimer")
    private static final String COLOR_RED = "rgb(255, 0, 0)"

    Flow flow
    Data data

    def RewardsTransferPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, "/account/rapidrewards/rewards-transfer")
    }

    private WebElement getMainContainer() {
        findElement(By.id("sw_content"))
    }

    @Override
    String getRelativePath() {
        return "/account/rapidrewards/rewards-transfer"
    }


    def loginAPlusCustomerWithValidMemberId() {
        def user = flow.getUser();
        fillIn(RAPID_REWARDS_LOGIN_USER_NAME, user.getAPlusUser())
        waitForElement(RAPID_REWARDS_LOGIN_PASSWORD).click()
        fillIn(RAPID_REWARDS_LOGIN_PASSWORD, user.getRRPassword())
        waitForElement(SUBMIT_BUTTON).click()
        verifyPage()

        flow.isRewardsTransfer = true
    }

    def enterAPlusMemberIdAndPassword(String memberId, String password) {
        fillIn(RAPID_REWARDS_LOGIN_USER_NAME, memberId)
        waitForElement(RAPID_REWARDS_LOGIN_PASSWORD).click()
        fillIn(RAPID_REWARDS_LOGIN_PASSWORD, password)
        waitForElement(SUBMIT_BUTTON).click()
    }

    def verifyOnRewardsTransferPage() {
       verifyCurrentPageLocation()
    }

    def verifyRewardsTransferLoginPage() {
        getCurrentUrl().contains("rewards-transfer-login")
    }

    def verifyInvalidAPlusCredentialsOopsMessage(){
        getOopsElement().isDisplayed().shouldBe true, "Invalid A+ credentials Oops message is not displayed"
        getExpectedOopsMessage().shouldContain "This AirTran Member ID and/or Password are incorrect.",
                                                 "Invalid A+ credentials Oops message does not contain correct text"
    }

    def verifyNameMismatchOopsMessage() {
        getOopsElement().isDisplayed().shouldBe true, "Name mismatch Oops message is not displayed"
        getExpectedOopsMessage().shouldContain "The name on your A+ account does not match the name on your Southwest Rapid Rewards account.",
                                                 "Name mismatch Oops message text is incorrect"
    }

    def verifyRewardsTransferSubtitle(){
        waitForElement(REWARDS_TRANSFER_SUBTITLE).isDisplayed().shouldBe true, "Rewards transfer Page subtitle is not displayed"
    }

    def myAPlusRapidRewardsQuickLink() {
        chooseInDropDownByValue("quickLinks", "/account/rapidrewards/rewards-transfer-login")
    }

    def verifyAgreeRulesToTransferCreditOopsMessage() {
        getExpectedOopsMessage().shouldContain "Please check the box stating that you have read and agree to the Transfer Rules.",
                                                 "Agree Rules to Transfer credit oops message does not contain correct text"
    }

    def verifyAgreeRulesToTransferCreditOopsMessageNotShown() {
        checkNoOops()
    }

    void selectPointsRadioButton() {
        waitForElement(POINTS_BUTTON).click()
    }

    void selectCreditsRadioButton() {
        waitForElement(CREDITS_REWARDS_BUTTON).click()
    }

    void enterPointsToTransfer(String pointsToBeTransferred) {
        fillIn(POINTS_TO_BE_TRANSFERRED, pointsToBeTransferred)
    }

    void verifySouthwestPointsToAirTranCreditConversionAmount(String airTranCredits) {
       waitForElement(POINTS_AIRTRAN_CONTAINER).getText().shouldContain "Transfer into " + airTranCredits + " A+ Rewards Credits"
    }

    void verifySouthwestAwardsToAirTranCreditConversionAmount(String airTranCredits) {
       findElement(AWARDS_AIRTRAN_CONTAINER).getText().shouldContain "Transfer into " + airTranCredits + " A+ Rewards Credits"
    }

    void verifySouthwestPointsToAirTranCreditConfirmationMessage(String points, String credits) {
        waitForElement(CONFIRMATION_MESSAGE).getText().shouldContain points + " Rapid Rewards Points transfer to " + credits + " A+ Rewards Credits"
    }

    void verifySouthwestCreditsToAirTranCreditsConfirmationMessage(String southwestCreditsToTransfer, String airtranCredits) {
        waitForElement(CONFIRMATION_MESSAGE).getText().shouldContain southwestCreditsToTransfer + " Rapid Rewards Credits transfer to " + airtranCredits + " A+ Rewards Credits",
                "Transferring credits for A+ Credits was not: " + southwestCreditsToTransfer + " , as was expected from the credits transfer field."
    }

    void verifyAirTranCreditsToSouthWestCreditsConfirmationMessage(String airTranAplusCredits, String southwestCredits) {
        waitForElement(CONFIRMATION_MESSAGE).getText().shouldContain airTranAplusCredits + " A+ Rewards Credits transfer to " + southwestCredits + " Rapid Rewards Credits",
                "Transferring credits for A+ Credits was not: " + airTranAplusCredits + " , as was expected from the credits transfer field."
    }

    void verifyInvalidAmountErrorMessageForSouthwestPoints() {
        waitForElement(REWARDS_TRANSFER_POINTS_DISPLAY).getText().shouldContain "Points must be transferred in increments of 300."
        waitForElement(REWARDS_TRANSFER_POINTS_DISPLAY).getCssValue("color").shouldContain COLOR_RED, "Text Box is not red"
    }

    void verifyPointsTransferFieldHasErrorTreatment() {
        waitForElement(POINTS_TO_BE_TRANSFERRED).getAttribute("class").shouldContain "fieldError", "No Error Message Found"
    }

    void verifyCreditsTransferFieldHasErrorTreatment() {
        waitForElement(CREDITS_TO_BE_TRANSFERRED).getAttribute("class").shouldContain "fieldError", "No Error Message Found"
    }

    void verifyAplusCreditsTransferFieldHasErrorTreatment() {
        waitForElement(APLUS_CREDITS_TO_BE_TRANSFERRED).getAttribute("class").shouldContain "fieldError", "No Error Message Found"
    }

    void verifyPointsEnteredExceedAvailablePointsMessage() {
        waitForElement(REWARDS_TRANSFER_POINTS_DISPLAY).getText().shouldContain "You have exceeded the transferable amount."
        waitForElement(REWARDS_TRANSFER_POINTS_DISPLAY).getCssValue("color").shouldContain COLOR_RED, "Text Box is not red"
    }

    void verifyAmountExceededErrorMessageForSouthwestCredits(){
        waitForElement(CREDITS_REWARDS_DISCLAIMER).getText().shouldContain "You have exceeded the transferable amount.", "Credits error disclaimer does not contain correct message."
        verifyCreditsErrorDisclaimerIsHighlightedInRed()
    }

    void verifyAmountExceededErrorMessageForAplusCredits(){
        waitForElement(AIRTRAN_CREDITS_REWARDS_DISCLAIMER).getText().shouldContain "You have exceeded the transferable amount.", "Credits error disclaimer does not contain correct message."
        verifyAirTranCreditsErrorDisclaimerIsHighlightedInRed()
    }

    void verifyInvalidAmountErrorMessageForSouthwestCredits(){
        waitForElement(CREDITS_REWARDS_DISCLAIMER).getText().shouldContain "Credits must be transferred in increments of 0.25", "Credits error disclaimer does not contain correct increments message."
        verifyCreditsErrorDisclaimerIsHighlightedInRed()
    }

    void verifyInvalidAmountErrorMessageForAplusCredits(){
        waitForElement(AIRTRAN_CREDITS_REWARDS_DISCLAIMER).getText().shouldContain "Credits must be transferred in increments of 0.25", "Credits error disclaimer does not contain correct increments message."
        verifyAirTranCreditsErrorDisclaimerIsHighlightedInRed()
    }

    def verifyCreditsErrorDisclaimerIsHighlightedInRed() {
        waitForElement(CREDITS_REWARDS_DISCLAIMER).getCssValue("color").equals(COLOR_RED).shouldBe true, "Credits error disclaimer is not highlighted red."
    }

    def verifyAirTranCreditsErrorDisclaimerIsHighlightedInRed() {
        waitForElement(AIRTRAN_CREDITS_REWARDS_DISCLAIMER).getCssValue("color").equals(COLOR_RED).shouldBe true, "Credits error disclaimer is not highlighted red."
    }

    def verifyThatViewSeeMoreAwardsLinkIsNotVisible() {
        waitForElement(SEE_MORE_AWARDS_LINK).isDisplayed().shouldBe false, "The 'See More Awards' link is displayed."
    }

    def verifyThatViewSeeFewerAwardsLinkIsNotVisible() {
        waitForElement(SEE_FEWER_AWARDS_LINK).isDisplayed().shouldBe false, "The 'See Fewer Awards' link is displayed."
    }

    def verifyFirstAwardIsSelectedOnPageLoad() {
        waitForElement(FIRST_AWARD_RADIO_BUTTON).isSelected().shouldBe true, "The first award is not selected on page load."
    }

    def verifyConfirmationSectionMessageIsDisplayedWhenAwardIsSelected() {
        waitForElement(CONFIRMATION_MESSAGE).getText().shouldContain "A+ Rewards Credits", "The confirm section message does not contain correct text"
    }

    def clickFourthAwardRadioButton() {
        waitForElement(FOURTH_AWARD_RADIO_BUTTON).click();
    }

    def verifyAirtranTransferRateIsDisplayedWhenAwardIsSelected() {
        waitForElement(AIRTRAN_CREDITS).getText().isNumber().shouldBe true, "The AirTran transfer rate does not contain Airtran credits."
    }

    def transfer() {
        waitForElement(TRANSFER_BUTTON).click();
    }

    def agreeToTransferRules() {
        waitForElement(TRANSFER_RULES_CHECKBOX).click()
    }

    def goDirectlyToRewardsTransferPage(){
        super.open()
        checkSomethingServed()
    }

    def verifyTransferableAwardsAndCertificatesIsDisplayed(){
        waitForElement(TRANSFERABLE_AWARDS_AND_CERTIFICATES).isDisplayed().shouldBe true, "Transferable Awards and Certificates is not displayed."
    }

    def verifyTransferableCreditsIsDisplayed(){
        waitForElement(TRANSFERABLE_CREDITS).isDisplayed().shouldBe true, "Transferable Credits is not displayed."
    }

    def verifyTransferablePointsIsDisplayed() {
        waitForElement(TRANSFERABLE_POINTS).isDisplayed().shouldBe true, "Transferable Points is not displayed."
    }

    def verifySeeMoreAwardsLinkIsDisplayed() {
        waitForElement(SEE_MORE_AWARDS_LINK).isDisplayed().shouldBe true, "See More Awards link is not displayed"
    }

    def verifySeeFewerAwardsLinkIsDisplayed() {
        waitForElement(SEE_FEWER_AWARDS_LINK).isDisplayed().shouldBe true, "See Fewer Awards link is not displayed"
    }

    def verifyTransferablePointsIsNotDisplayed() {
        checkIfElementExists(TRANSFERABLE_POINTS).shouldBe false, "Transferable Points is displayed."
    }

    def verifyTransferableCreditsIsNotDisplayed() {
        checkIfElementExists(TRANSFERABLE_CREDITS).shouldBe false, "Transferable Credits is displayed."
    }

    def verifyTransferableAwardsAndCertificatesIsNotDisplayed() {
        checkIfElementExists(TRANSFERABLE_AWARDS_AND_CERTIFICATES).shouldBe false, "Transferable Awards and Certificates is displayed."
    }

    def clickSeeMoreAwardsLink() {
        waitForElement(SEE_MORE_AWARDS_LINK).click()
    }

    def selectACertificate(String awardName) {
        AwardRow awardRow = findAwardRowByName(awardName, mainContainer)
        awardRow.shouldNotBe null, "Could not find a " + awardName + " on the Rewards Transfer page"
        awardRow.select()
    }

    def verifyConfirmationMessageMatchesSelectedAward() {
        waitForElement(CONFIRMATION_MESSAGE).getText().shouldContain waitForElement(REWARDS_TRANSFER_HIGHLIGHTED_ROW).findElement(By.className("awardName")).getText(),
                "The award name is not displayed in the confirmation section"
        waitForElement(CONFIRMATION_MESSAGE).getText().shouldContain waitForElement(REWARDS_TRANSFER_HIGHLIGHTED_ROW).findElement(By.className("awardCertificateNumber")).getText(),
                "The award number is not displayed in the confirmation section"
        waitForElement(CONFIRMATION_MESSAGE).getText().shouldContain waitForElement(REWARDS_TRANSFER_HIGHLIGHTED_ROW).findElement(By.className("awardExpirationDate")).getText(),
                        "The award expiration date is not displayed in the confirmation section"
    }

    void enterCreditsToTransfer(String creditsToBeTransferred) {
        fillIn(CREDITS_TO_BE_TRANSFERRED, creditsToBeTransferred)
    }

    void clearCreditTransferAmount() {
        waitForElement(CREDITS_TO_BE_TRANSFERRED).sendKeys(DELETE_EXISTING)
    }

    void verifyAirTranConversionAmount(def expectedResultingAirTranCredits) {
        waitForElement(AIR_TRAN_CREDITS_RESULTS).getText().shouldBeEqual expectedResultingAirTranCredits.toString(), "Resulting AirTran credits did not display as $expectedResultingAirTranCredits"
    }

    void enterAPlusCreditsToTransfer(String aPlusCreditsToBeTransferred) {
        fillIn(APLUS_CREDITS_TO_BE_TRANSFERRED, aPlusCreditsToBeTransferred)
    }

    void selectToTransferAirTranRewards() {
        waitForElement(AIRTRAN_REWARDS_TRANSFER_RADIO_BUTTON).click()
    }

    void verifyAirTranOrientationButtonIsPresent() {
        checkIfElementExists(AIRTRAN_REWARDS_TRANSFER_RADIO_BUTTON).shouldBe true
    }

    void verifyAirTranOrientationButtonIsNotPresent() {
        checkIfElementExists(AIRTRAN_REWARDS_TRANSFER_RADIO_BUTTON).shouldBe false
    }

}
