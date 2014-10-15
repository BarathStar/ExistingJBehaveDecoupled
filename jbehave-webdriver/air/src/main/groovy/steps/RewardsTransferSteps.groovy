package steps

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.RRUser
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.MyAccountPage
import pages.RewardsActivityPage
import pages.RewardsTransferConfirmationPage
import pages.RewardsTransferPage
import state.Flow
import steps.thenSteps.RewardsTransferThenSteps

class RewardsTransferSteps {

    RewardsTransferPage rewardsTransferPage
    RewardsTransferConfirmationPage rewardsTransferConfirmationPage
    MyAccountPage myAccountPage
    RewardsActivityPage rewardsActivityPage
    AccountSteps accountSteps
    Flow flow
    Data data
    RewardsTransferThenSteps thenSteps

    @Given("I am on the Rewards Transfer page")
    void goToRewardsTransferPage() {
        accountSteps.loggedInAsRapidRewardsMember()
        accountSteps.myRapidRewardLink()
        clickTransferAplusRapidRewardsQuickLinkOption()
        thenSteps.verifyRewardsLandingPage()
        loggedInAPlusMember()
        thenSteps.viewRewardsTransferPage()
    }

    @Given("I am on the Rewards Transfer page with A+ credits")
    void goToRewardsTransferPageWithAPlusCredits() {
        accountSteps.loggedInAsRapidRewardsMember()
        accountSteps.myRapidRewardLink()
        clickTransferAplusRapidRewardsQuickLinkOption()
        thenSteps.verifyRewardsLandingPage()
        loggedInAPlusMember()
        thenSteps.viewRewardsTransferPage()
    }

    @Given("I am on the Rewards Transfer Confirmation page after a successful transfer")
    void goToTheRewardsTransferConfirmationPageAfterASuccessfulTransfer() {
        goToRewardsTransferPageWithAPlusCredits()
        enterAPlusCreditsToBeTransferred("0.25")
        agreeToRulesandTransfer();
        rewardsTransferConfirmationPage.verifyPage()
    }

    @Given("I navigate to the Rewards Transfer page as an A+ user with a failing account")
    void navigateToRewardsTransferPage() {
        accountSteps.myRapidRewardLink()
        clickTransferAplusRapidRewardsQuickLinkOption()
        thenSteps.verifyRewardsLandingPage()
        RRUser rrUser = data.getUser("noDebitRapidRewardsAccount")
        rewardsTransferPage.enterAPlusMemberIdAndPassword(rrUser.getAPlusUser(), "ok");
        thenSteps.viewRewardsTransferPage()
    }

    @Given("I have transferred \$aplusCredits A+ credits to Rapid Rewards credits")
    void transferAPlusCreditsToRapidRewards(String aplusCredits) {
        enterAPlusCreditsToBeTransferred(aplusCredits)
        agreeToRulesandTransfer();
        rewardsTransferConfirmationPage.verifyPage()
    }


    @Given("I am on the Rapid Rewards history page to view APlus credit transfer activity")
    public void navigateToRewardsHistoryPageToViewAPlusCreditTransferActivity() {

        (new DynaStubsIntegration()).createRewardActivity(flow.getRrUser());
        myAccountPage.logOut().submit()
        myAccountPage.loginWithUserNameAndPassword(flow.rrUser.getRRNumber(), flow.rrUser.getRRPassword())
        navigateToRewardsHistoryPage();
    }

    @When("I try to go directly to the Rewards Transfer Page by entering the URL")
    void tryToGoDirectlyToRewardsTransferPage(){
        rewardsTransferPage.goDirectlyToRewardsTransferPage()
    }

    @When("I login with an invalid A+ member id and password")
    def enterInvalidAPlusMemberId() {
        rewardsTransferPage.enterAPlusMemberIdAndPassword("12345", "wrong")
    }

    @When("I enter a member id that does not match my Rapid Rewards name")
    void enterNameThatDoesntMatchRapidRewardsName() {
        rewardsTransferPage.enterAPlusMemberIdAndPassword("11", "correct")
    }

    @When("I login with a valid A+ member id and password")
    def loggedInAPlusMember() {
        rewardsTransferPage.loginAPlusCustomerWithValidMemberId()
    }

    @When("I login with a valid A+ email and password")
    def enterAPlusEmailAndPassword() {
        rewardsTransferPage.enterAPlusMemberIdAndPassword("hpotter@gmail.com", "password");
    }

    @When("I login with a valid A+ email and password without A+ credits")
    def enterAPlusEmailAndPasswordWithoutCredits() {
        rewardsTransferPage.enterAPlusMemberIdAndPassword("44", "password");
    }

    @When("I login to an A+ account that cannot be debited")
    def loggedInAPlusMemberNoDebit() {
        rewardsTransferPage.enterAPlusMemberIdAndPassword('73', "ok");
    }

    @When("I select the Transfer A+ Rewards/Rapid Rewards quick links option")
    void clickTransferAplusRapidRewardsQuickLinkOption() {
        rewardsTransferPage.myAPlusRapidRewardsQuickLink()
    }

    @When("I select the Transfer between A+ Rewards and Rapid Rewards button")
    void clickTransferAplusRapidRewardsButton() {
        myAccountPage.clickTransferAplusRapidRewardsButton()
    }

    @When("I continue without agreeing to rules of credit transfer")
    def continueWithoutCheckingAgreementCheckbox() {
        rewardsTransferPage.transfer()
    }

    @When("I select the Credits radio button")
    void selectCreditsRadioButton() {
        rewardsTransferPage.selectCreditsRadioButton()
    }

    @When("I select the Points radio button")
    private def selectPointsButton() {
        rewardsTransferPage.selectPointsRadioButton()
    }

    @When("I enter \$pointsToBeTransferred points")
    void enterTransferValueAndRecalculate(String pointsToBeTransferred) {
        selectPointsButton()
        rewardsTransferPage.enterPointsToTransfer(pointsToBeTransferred)
    }

    @When("I type a transfer amount of \$creditsToBeTransferred credits")
    void enterCreditsTransferAmount(String creditsToBeTransferred) {
         rewardsTransferPage.enterCreditsToTransfer(creditsToBeTransferred)
    }

    @When("I enter \$aPlusCreditsToBeTransferred A+ credits")
    void enterAPlusCreditsToBeTransferred(String aPlusCreditsToBeTransferred) {
        rewardsTransferPage.selectToTransferAirTranRewards()
        rewardsTransferPage.enterAPlusCreditsToTransfer(aPlusCreditsToBeTransferred)
    }

    @When("I select the fourth transferable Certificate")
    def clickPromotionalAwardRadioButton() {
        rewardsTransferPage.clickFourthAwardRadioButton();
    }

    @When("I click on the See More Awards link")
    public void clickSeeMoreAwardsLink(){
        rewardsTransferPage.clickSeeMoreAwardsLink()
    }

    @When("I select a Promo Certificate")
    public void selectAPromoCertificate(){
        rewardsTransferPage.selectACertificate("Promo Certificate")
    }

    @When("I select a Standard Certificate")
    public void selectAStandardCertificate(){
        rewardsTransferPage.selectACertificate("Standard Certificate")
    }

    @When("I select a Freedom Certificate")
    public void selectAFreedomCertificate(){
        rewardsTransferPage.selectACertificate("Freedom Certificate")
    }

    @When("I select a Standard Award")
    public void selectAStandardAward(){
        rewardsTransferPage.selectACertificate("Standard Award")
    }

    @When("I select a Freedom Award")
    public void selectAFreedomAward(){
        rewardsTransferPage.selectACertificate("Freedom Award")
    }

    @When("I clear the Credit transfer amount")
    public void clearCreditTransferAmount(){
        rewardsTransferPage.clearCreditTransferAmount()
    }

    @When("I agree to the transfer rules and continue to transfer")
    public void agreeToRulesandTransfer(){
        rewardsTransferPage.agreeToTransferRules()
        rewardsTransferPage.transfer()
    }

    @When("I am on the Rapid Rewards history page")
    public void navigateToRewardsHistoryPage() {
        accountSteps.myRapidRewardLink()
        myAccountPage.clickMyRewardsActivityLink()
        rewardsActivityPage.verifyPage()
    }

    @When("I am on Rewards Activity Details page to view appropriate information")
    public void navigateToRewardsActivityDetails() {

        if (DynaStubsIntegration.useDynaStubs()) {
            (new DynaStubsIntegration()).createRewardActivity(flow.getRrUser())
        }
        navigateToRewardsHistoryPage()
    }

}