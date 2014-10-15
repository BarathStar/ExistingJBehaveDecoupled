package steps.thenSteps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Then
import pages.BaggageAndOptionalFees
import pages.OptionalTravelChargesPage
import pages.elements.AccountBar
import pages.elements.ForgotRRNumberModal
import pages.elements.GlobalNavigationHeader
import state.Flow
import steps.conditional.ToggleHomepage2

class AccountBarThenSteps {

    AccountBar accountBar
    ForgotRRNumberModal forgotRRNumberModal
    Flow flow
    OptionalTravelChargesPage optionalTravelChargesPage
    BaggageAndOptionalFees baggageAndOptionalFees
    GlobalNavigationHeader globalNavigationHeader

    @Then("I will see a Quick Air Link to \"\$link\"")
    def checkQuickLink(String link) {
        accountBar.hasQuickLinkWithLabel(link)
    }

    @Then("I will not see any Quick Air Links")
    def verifyQuickAirLinksAreNotPresent() {
        accountBar.areQuickLinksPresent.shouldBe false
    }

    @Then("I will not see an option to log in using the account bar")
    def verifyLogInFormIsNotPresent() {
        accountBar.verifyLogInFormIsNotPresent()
    }

    @Then("I will see my Preferred Name on the account bar")
    def verifyPreferredNameIsOnAccountBar() {
        accountBar.verifyPreferredNameIsOnAccountBar()
    }

    @Then("I see a login error message that says \"\$message\"")
    def verifyLogInErrorMessage(String message) {
        accountBar.verifyLogInErrorMessage(message)
    }

    @Then("I see a failed login error")
    def verifyLogInErrorMessage2()  {
        accountBar.verifyLogInErrorMessage2()
    }

    @Then("I will see the \"Need help logging in?\" modal")
    def verifyForgotRRNumberModal() {
        forgotRRNumberModal.verifyForgotRRNumberModal()
    }

    @Then("I will see a drawer with the title \"\$drawerName\" on the account bar")
    def verifyDrawerTitle(String drawerName) {
        accountBar.verifyDrawerTitle(drawerName)
    }

    @Then("I will not see a drawer with the title \"\$drawerName\" on the account bar")
    def verifyDrawerDoesntShow(String drawerName) {
        accountBar.verifyDrawerTitleNotPresent(drawerName)
    }

    @Then("I will see a section with the title \"\$sectionName\" in My Travel Drawer")
    def verifySectionTitleInManageTravelDrawer(String sectionName) {
        accountBar.verifySectionTitleInManageTravelDrawer(sectionName)
    }


    @Then("I will not see a section with the title \"\$sectionName\" in My Travel Drawer")
    def verifySectionTitleNotInManageTravelDrawer(String sectionName) {
        accountBar.verifySectionTitleNotInManageTravelDrawer(sectionName)
    }

    @Then("I see a warning message alerting me of my account status")
    def verifyAccountStatusWarningMessage() {
        accountBar.verifyAccountStatusWarningMessage()
    }

    @Then("I see a message encouraging me to set up a dream trip")
    def verifyDreamTripsEncouragingMessage() {
        accountBar.verifyDreamTripsEncouragingMessage()
    }

    @Then("I see the dream trips module on the account sidebar")
    def verifyDreamTripsTitleSection() {
        accountBar.verifyDreamTripsTitleSection()
    }

    @Then("I should not see the option to check dates for the trip")
    def verifyCheckDatesButtonNotPresent() {
        accountBar.verifyCheckDatesButtonIsNotPresent()
    }

    @Then("I see the name of the unachieved dream trip displayed below the title")
    @Alias("I see the name of the achieved dream trip displayed below the title")
    def verifyDreamTripsName() {
        accountBar.verifyDreamTripsName(flow.dreamTripName)
    }

    @Then("I see a mustard progress bar displaying my progress towards achieving that dream trip")
    def verifyMustardProgressBarUnderDreamTripSection() {
        accountBar.verifyMustardProgressBarUnderDreamTripSection()
    }

    @Then("I see the percentage complete displayed at the end of the progress bar")
    def verifyPercentageOfProgressUnderDreamTripSection() {
        accountBar.verifyPercentageOfProgressUnderDreamTripSection()
    }

    @Then("I see the option to check dates for the trip")
    def verifyCheckDatesButtonIsPresent() {
        accountBar.verifyCheckDatesButtonIsPresent()
    }

    @Then("I see a full, green progress bar representing my achievement")
    def verifyGreenProgressBarUnderDreamTripSection() {
        accountBar.verifyGreenProgressBarUnderDreamTripSection()
    }

    @Then("I see 100% displayed at the end of the percent progress bar")
    def verifyFullPercentageOfProgressUnderDreamTripSection() {
        accountBar.verifyFullPercentageOfProgressUnderDreamTripSection()
    }

    @Then("I see the Rapid Rewards Credit Card section on the account sidebar")
    def seeRapidRewardsCreditCardSection() {
        accountBar.verifyLearnMoreLinkIsPresent()
        accountBar.verifyRapidRewardsCreditCardTitleSection()
    }

    @Then("I see the Rapid Rewards Credit Card holder section on the account sidebar")
    def seeRapidRewardsCreditCArdHolder() {
        accountBar.verifyMoreRewardsLinkIsPresent()
        accountBar.verifyRedeemForMoreRewardsTitleSection()
    }

    @Then("I see my promotion with its expiration date")
    def seePromotionWithExpirationDate() {
        accountBar.verifyPromotionExpirationDate()
    }

    @Then("I am informed that I have available promotions")
    def seePromotionAvailable() {
        accountBar.verifyPromotionAvailable()
    }

    @Then("I should not see any promotional award")
    def seePromotionNotAvailable() {
        accountBar.verifyPromotionNotPresent()
    }

    @Then("I see the Account Bar")
    def seeAccountBar() {
        accountBar.isLogInFormPresent().shouldBe true;
    }
    
    @Then("I see the Optional Travel Charges page")
    def verifyAccessThankYouPage(){
        if (ToggleHomepage2.isOn()){
            baggageAndOptionalFees.verifyBaggageAndOptionalFees()
        }else{
            optionalTravelChargesPage.verifyPageOptionalTravelChargesPage()
        }
    }

    @Then("I will see my account number populated and remember me check box checked")
    def accountNumberPopulatedAndRememberMeChecked() {
        if (ToggleHomepage2.isOn()
                && globalNavigationHeader.isHomePage()) {
            globalNavigationHeader.openLoginDropdown()
        }
        accountBar.verifyRapidRewardsNumberIsFilled()
        accountBar.verifyRememberMeCheckBoxIsChecked()
    }
}