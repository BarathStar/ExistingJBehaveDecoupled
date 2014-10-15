package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.RewardsTransferPage
import pages.RewardsTransferConfirmationPage
import pages.RewardsActivityPage

class RewardsTransferThenSteps {
    RewardsTransferPage rewardsTransferPage
    RewardsTransferConfirmationPage rewardsTransferConfirmationPage
    RewardsActivityPage rewardsActivityPage

    @Then("I view the Rewards Transfer login page")
    def verifyRewardsLandingPage() {
        rewardsTransferPage.verifyPage()
        rewardsTransferPage.verifyRewardsTransferLoginPage()
    }

    @Then("I see a name mismatch Oops message")
    def verifyNameMismatchOopsMessage() {
        rewardsTransferPage.verifyNameMismatchOopsMessage()
    }

    @Then("I view the Rewards Transfer page")
    def viewRewardsTransferPage(){
        rewardsTransferPage.verifyPage()
        rewardsTransferPage.verifyOnRewardsTransferPage()
        rewardsTransferPage.verifyRewardsTransferSubtitle()
    }

    @Then("I will see invalid A+ credentials Oops message")
    def verifyInvalidAPlusCredentialsOopsMessage(){
        rewardsTransferPage.verifyInvalidAPlusCredentialsOopsMessage()
    }

    @Then("I should see the read and agree to the transfer rules oops message")
    def verifyAgreeRulesToTransferCreditOopsMessage() {
         rewardsTransferPage.verifyAgreeRulesToTransferCreditOopsMessage()
    }

    @Then("I should not see the read and agree to the transfer rules oops message")
    def verifyAgreeRulesToTransferCreditOopsMessageNotShown() {
         rewardsTransferPage.verifyAgreeRulesToTransferCreditOopsMessageNotShown()
    }

    @Then("I see the credit conversion amount on the AirTran transfer side of \$airtranCredits credits")
    void verifySouthwestPointsToAirTranCreditConversionAmount(String airtranCredits) {
        rewardsTransferPage.verifySouthwestPointsToAirTranCreditConversionAmount(airtranCredits)
    }
    @Then("I see the award conversion amount on the AirTran transfer side of \$airtranCredits credits")
    void verifySouthwestAwardsToAirTranCreditConversionAmount(String airtranCredits) {
        rewardsTransferPage.verifySouthwestAwardsToAirTranCreditConversionAmount(airtranCredits)
    }

    @Then("I see a message displaying the correct conversion of \$southwestPoints points to \$airtranCredits credits in the \"Confirm Transfer Action\" section")
    void verifySouthwestPointsToAirTranCreditConfirmationMessage(String southwestPoints, String airtranCredits) {
         rewardsTransferPage.verifySouthwestPointsToAirTranCreditConfirmationMessage(southwestPoints, airtranCredits)
    }

    @Then("I see a message displaying the correct conversion of \$soutwestCredits Rapid Rewards credits to \$airTranCredits A+ credits in the \"Confirm Transfer Action\" section")
    public void verifyTransferZeroCreditAmounts(String southwestCredits, String airTranCredits){
        rewardsTransferPage.verifyAirTranConversionAmount(airTranCredits)
        rewardsTransferPage.verifySouthwestCreditsToAirTranCreditsConfirmationMessage(southwestCredits, airTranCredits)
    }

    @Then("I see a message displaying the correct conversion of \$airTranCredits A+ credits to \$southwestCredits Rapid Rewards credits in the \"Confirm Transfer Action\" section")
    public void verifyTransferAPlusCreditAmountsInConfirmation(String airTranCredits, String southwestCredits){
        rewardsTransferPage.verifyAirTranCreditsToSouthWestCreditsConfirmationMessage(airTranCredits, southwestCredits)
    }

    @Then("I see the correct error for transferring an invalid points amount")
    void verifyErrorForEnteringInvalidAmount() {
        rewardsTransferPage.verifyInvalidAmountErrorMessageForSouthwestPoints()
        rewardsTransferPage.verifyPointsTransferFieldHasErrorTreatment()
    }

    @Then("I see the correct error for transferring an invalid credit amount")
    void verifyErrorForEnteringInvalidCreditAmount() {
        rewardsTransferPage.verifyInvalidAmountErrorMessageForSouthwestCredits()
        rewardsTransferPage.verifyCreditsTransferFieldHasErrorTreatment()
    }

    @Then("I see the correct error for transferring an invalid A+ credit amount")
    void verifyErrorForEnteringInvalidAplusCreditAmount() {
        rewardsTransferPage.verifyInvalidAmountErrorMessageForAplusCredits()
        rewardsTransferPage.verifyAplusCreditsTransferFieldHasErrorTreatment()
    }

    @Then("I see the correct error for exceeding the transferable amount")
    void verifyErrorForExceedingAmount() {
        rewardsTransferPage.verifyPointsEnteredExceedAvailablePointsMessage()
        rewardsTransferPage.verifyPointsTransferFieldHasErrorTreatment()
    }

    @Then("I see the correct error for exceeding the transferable credit amount")
    void verifyErrorForExceedingCreditAmount() {
        rewardsTransferPage.verifyAmountExceededErrorMessageForSouthwestCredits()
        rewardsTransferPage.verifyCreditsTransferFieldHasErrorTreatment()
    }

    @Then("I see the correct error for exceeding the transferable A+ credit amount")
    void verifyErrorForExceedingAplusCreditAmount() {
        rewardsTransferPage.verifyAmountExceededErrorMessageForAplusCredits()
        rewardsTransferPage.verifyAplusCreditsTransferFieldHasErrorTreatment()
    }

    @Then("I should not see the See More Awards link")
    public void doNotViewSeeMoreAwardsLink() {
        rewardsTransferPage.verifyThatViewSeeMoreAwardsLinkIsNotVisible()
    }

    @Then("I should not see the See Fewer Awards link")
    public void doNotViewSeeFewerAwardsLink() {
        rewardsTransferPage.verifyThatViewSeeFewerAwardsLinkIsNotVisible()
    }

    @Then("I see the first award is selected and highlighted")
    public void verifyFirstAwardIsSelectedOnPageLoad() {
        rewardsTransferPage.verifyFirstAwardIsSelectedOnPageLoad()
    }

    @Then("I should see the Airtran transfer rate in the confirmation section")
    public void verifyConfirmationSectionMessageIsDisplayedWhenAwardIsSelected() {
        rewardsTransferPage.verifyConfirmationSectionMessageIsDisplayedWhenAwardIsSelected()
    }

    @Then("I should see the AirTran transfer rate in the credit info section")
    public void verifyAirtranInfoSectionIsDisplayedWhenAwardIsSelected() {
        rewardsTransferPage.verifyAirtranTransferRateIsDisplayedWhenAwardIsSelected()
    }

    @Then("I should see the option to transfer A+ Credits")
    def void verifyAPlusCreditsAvailableToTransfer() {
        rewardsTransferPage.verifyAirTranOrientationButtonIsPresent()
    }

    @Then("I should not see the option to transfer A+ Credits")
    def void verifyAPlusCreditsNotAvailableToTransfer() {
        rewardsTransferPage.verifyAirTranOrientationButtonIsNotPresent()
    }

    @Then("I view the default transfer amount of 0.25 in the transfer field")
    public void verifyDefaultCreditsToTransfer() {
        rewardsTransferPage.verifyAirTranConversionAmount(0.25)
    }

    @Then("I view my transferable Awards and Certificates")
    public void viewTransferableAwardsAndCertificates(){
        rewardsTransferPage.verifyTransferableAwardsAndCertificatesIsDisplayed()
    }

    @Then("I view my transferable Credits")
    public void viewTransferableCredits(){
        rewardsTransferPage.verifyTransferableCreditsIsDisplayed()
    }

    @Then("I view my transferable Points")
    public void viewTransferablePoints() {
        rewardsTransferPage.verifyTransferablePointsIsDisplayed()
    }

    @Then("I view the See More Awards link")
    public void viewSeeMoreAwardsLink(){
        rewardsTransferPage.verifySeeMoreAwardsLinkIsDisplayed()
    }

    @Then("I should see the See Fewer Awards link")
    public void viewSeeLessAwardsLink(){
        rewardsTransferPage.verifySeeFewerAwardsLinkIsDisplayed()
    }

    @Then("I do not see transferable Points")
    public void verifyTransferablePointsIsNotDisplayed(){
        rewardsTransferPage.verifyTransferablePointsIsNotDisplayed()
    }

    @Then("I do not see transferable Credits")
    public void verifyTransferableCreditsIsNotDisplayed(){
        rewardsTransferPage.verifyTransferableCreditsIsNotDisplayed()
    }

    @Then("I do not see transferable Awards and Certificates")
    public void verifyTransferableAwardsAndCertificatesIsNotDisplayed(){
        rewardsTransferPage.verifyTransferableAwardsAndCertificatesIsNotDisplayed()
    }

    @Then("I should see a confirmation message that matches the selected award")
    public void verifyConfirmationMessageMatchesSelectedAward(){
        rewardsTransferPage.verifyConfirmationMessageMatchesSelectedAward()
    }

    @Then("I should see the Transfer Confirmation page")
    public void verifyRedirectToTransferConfirmationPage(){
        rewardsTransferConfirmationPage.verifyPage()
        rewardsTransferConfirmationPage.verifySuccessMessage()
    }

    @Then("I should see the please do not use your back button from the Rewards Transfer Confirmation page oops message")
    public void verifyPleaseDoNotUseYourBackButtonFromTheRewardsTransferConfirmationPageOopsMessage() {
        rewardsTransferConfirmationPage.verifyBackButtonOopsMessageIsOnPage()
    }

    @Then("I should see a failure message on the Transfer Confirmation page")
    public void verifyFailureMessage() {
        rewardsTransferConfirmationPage.verifyFailureMessage()
    }

    @Then("I should see a APlus debit transfer transaction for 2 credits")
    public void verifyRewardsTransferTransactionHistoryWithAPlusDebitTransfer() {
        rewardsActivityPage.verifyAPlusDebitTransactionInActivityDetails()
    }

    @Then("I should see a Reward transfer reversal transaction for 300 points")
    public void verifyRewardsTransferTransactionHistoryWithAPlusPointsDebitTransfer() {
        rewardsActivityPage.verifyPointsReversalTransactionInActivityDetails()
    }

    @Then("I should see a Reward transfer reversal transaction for 8 credits")
    public void verifyRewardsTransferTransactionHistoryWithAPlusCreditsDebitTransfer() {
        rewardsActivityPage.verifyAwardsReversalTransactionInActivityDetails()
    }

    @Then("I should see values on the Rewards Activity Details table")
    public void verifyElementsOnActivityTable() {
        rewardsActivityPage.verifyElementsOnActivityTable()
    }
}