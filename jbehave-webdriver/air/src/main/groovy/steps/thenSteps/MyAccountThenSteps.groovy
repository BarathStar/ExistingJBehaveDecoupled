package steps.thenSteps

import org.apache.commons.lang.WordUtils
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Then
import pages.elements.BookATripModal
import state.AccountManagementData
import state.Flow
import pages.*
import steps.conditional.TogglePreferenceCenter

class MyAccountThenSteps {

    MyAccountPage myAccountPage
    DestinationFinderPage destinationFinderPage
    BookATripModal bookATripModal
    SavedTripsPage savedTripsPage
    TripDetailsPage tripDetailsPage
    AccountManagementData accountManagementData
    PurchasePointsConfirmationPage purchasePointsConfirmationPage
    ConfirmCustomerPage confirmCustomerPage
    AccountTravelPastAirDetailsPage accountTravelPastAirDetailsPage
    CertificatesPage certificatesPage
    ReissuePricingPage reissuePricingPage
    MyRapidRewardsPage myRapidRewardsPage
    String DUPLICATE_ACCOUNT_MESSAGE = "You already have a MySouthwest Account"

    Flow flow

    @Then("I should see the Payment Information page")
    def verifyPaymentInformationPage() {
        myAccountPage.verifyPage()
    }

    @Then("I should see the Contact Information page")
    def verifyContactInformationPage() {
        myAccountPage.verifyPage()
    }

    @Then("I should see the Communication Preferences page")
    def verifyCommunicationPreferencesPage() {
        myAccountPage.verifyPage()
    }

    @Then("I am on the My Account page")
    void shouldBeOnTheMyAccountPage() {
        myAccountPage.verifyCurrentPageLocation()
    }

    @Then("I view free trip eligibility")
    def freeTripEligibility() {
        myAccountPage.verifyEligibleForFreeTrip()
    }

    @Then("I view the default fare range in points")
    def defaultPointsRange() {
        destinationFinderPage.defaultPointsView()
    }

    @Then("I view the help page on top of the map")
    def helpPageDisplay() {
        destinationFinderPage.helpPageDisplay()
    }

    @Then("I view the map without a help page on top of the map")
    def noHelpPageDisplay() {
        destinationFinderPage.noHelpPageDisplay()
    }

    @Then("I should see the Snapshot page")
    def snapshotPageVerify() {
        myAccountPage.verifySnapshotPage()
    }

    @Then("I see the book a flight modal")
    @Alias("I see the book a trip modal")
    def verifyBookAFlightModalPresent() {
        bookATripModal.verifyModal()
    }

    @Then("I view my past and upcoming trips")
    def myPastAndUpcomingTrips() {
        myAccountPage.myPastAndUpcomingTrips()
    }

    @Then("I view my upcoming trips")
    def myUpcomingTrips() {
        myAccountPage.myUpcomingTrips()
    }

    @Then("I view my points progress")
    def myPointsProgress() {
        myAccountPage.myPointsProgress()
    }

    @Then("I should see the My Preferences page")
    def myPreferencesVerify() {
        myAccountPage.verifyMyPreferences()
    }

    @Then("I receive my purchase confirmation")
    def pointsPurchaseConfirmation() {
        myAccountPage.pointsPurchaseConfirm()
    }

    @Then("I have no flights in my Saved Trips page")
    void verifyNoSavedTrips() {
        savedTripsPage.verifyNoSavedTrips()
    }

    @Then("I see the modal station fields are populated")
    def VerifyModalStationFieldsPopulated() {
        bookATripModal.VerifyModalStationFieldsPopulated()

    }

    @Then("I see that the credit card was set as primary")
    def verifyCreditCardSetAsPrimary() {
        myAccountPage.verifyPrimaryCreditCardIsPresent()
    }

    @Then("I see that the credit card was added one time")
    def verifyCreditCardWasAddedOneTime() {
        myAccountPage.verifyPrimaryCreditCardIsPresent()
        myAccountPage.verifyCountCreditCardIsEqualsTo(1)
    }

    @Then("I verify trip details")
    void verifyTripDetailsVisible() {
        tripDetailsPage.verifyPageVisible()
    }

    @Then("I see fare in dollars as default option in modal window")
    def verifyFareDollarsChecked() {
        myAccountPage.verifyDollarsRadioButtonIsChecked()
    }

    @Then("I see an oops message which informs that my credentials are not correct")
    def verifyLogInErrorMessage() {
        myAccountPage.getLoginErrorMessage()
    }

    @Then("I see an oops message on the Companion Pass status page with the message: \$message")
    def verifyOopsMessageOnCompanionStatusPage(String message) {
        myAccountPage.shouldBeInPage("companion-status")
        myAccountPage.shouldShowOopsMessage(message)
    }

    @Then("I see my account snapshot")
    def verifySnapshotTabIsSelected() {
        myAccountPage.verifySnapshotTabSelected()
        myAccountPage.verifyMyAccountSnapshotPageTitle()
    }

    @Then("I see the My Account Snapshot page")
    def verifyImOnMyAccountSnapshotPage() {
        myAccountPage.verifyPage()
        myAccountPage.verifyCurrentPageLocation()
        myAccountPage.verifyPageTitle("My Account Snapshot")
    }

    @Then("I should not see my e-mail subscribed to the Click 'N Save E-mail Updates")
    def verifyEmailNotPresentOnCns() {
        myAccountPage.verifyEmailNotPresentOnCns(accountManagementData.emailAddress)
    }

    @Then("I should not see my e-mail subscribed to In a Nutshell Email Updates")
    def verifyEmailNotPresentOnIan() {
        myAccountPage.verifyEmailNotPresentOnIan(accountManagementData.emailAddress)
    }

    @Then("I should not see my e-mail subscribed to the Rapid Rewards Report and the Rapid Rewards E-mail Update")
    void verifyReturningGuardianIsNotPresent() {
        myAccountPage.verifyEmailNotPresentOnRreu(accountManagementData.emailAddress)
        myAccountPage.verifyEmailNotPresentOnRrrp(accountManagementData.emailAddress)
    }

    @Then("I see my first and second e-mail subscribed to the Rapid Rewards Report")
    def verifyEmailaArePresentOnRrrp(){
        myAccountPage.verifyEmailArePresentOnRrrp(accountManagementData.emailAddress, accountManagementData.secondEmailAddress)
    }

    @Then("I see my first and third e-mail subscribed to the Rapid Rewards E-mail Update")
    def verifyEmailaArePresentOnRreu(){
        myAccountPage.verifyEmailArePresentOnRreu(accountManagementData.emailAddress, accountManagementData.thirdEmailAddress)
    }

    @Then("I see the breadcrumbs shown in the following order: \"My Account -> My Preferences -> \$page\"")
    def verifyBreadcrumb(String page) {
        myAccountPage.verifyBreadcrumb(page)
    }

    @Then("I see the \"Username & Password\" page")
    @Alias("I see the \"Username & Password\" page is not editable")
    def verifyIamOnTheUserNameAndPasswordPage() {
        myAccountPage.verifyPage()
        myAccountPage.verifyIamOnTheCorrectPage(myAccountPage.MY_PREFERENCES_URL)
        myAccountPage.verifyPageTitle("Username & Password")
    }

    @Then("I see the \"Contact Information\" page")
    def verifyIamOnTheContactInformationPage() {
        myAccountPage.verifyIamOnTheCorrectPage("account/info/view-contact-info")
    }

    @Then("I see the \"Communication Preferences\" page")
    def verifyIamOnTheCommunicationPreferencesPage() {
        myAccountPage.verifyIamOnTheCorrectPage("account/info/view-communication-prefs")
    }

    @Then("I see the \"Payment Information\" page")
    def verifyIamOnThePaymentInformationPage() {
        myAccountPage.verifyIamOnTheCorrectPage("account/info/view-payment-info")
    }

    @Then("I see my first name in the Congratulations message")
    def verifyFirstNameInTheCongratulationMessage(){
        confirmCustomerPage.verifyFirstNameInTheCongratulationMessage(WordUtils.capitalize(flow.getRrUser().firstName))
    }

    @Then("I see the Purchase Points Confirmation page")
    def verifyIamOnThePurchasePointsConfirmationPage() {
        purchasePointsConfirmationPage.verifyPage()
        purchasePointsConfirmationPage.verifyCurrentPageLocation()
        purchasePointsConfirmationPage.verifyPageTitle("Purchase Points Confirmation")
    }

    @Then('I see the Purchase Points page')
    def verifyIamOnThe() {
        myAccountPage.verifyPage()
        myAccountPage.verifyIamOnTheCorrectPage("account/rapidrewards/purchase-points")
        myAccountPage.verifyPageTitle("Purchase Points")
    }

    @Then("I see the option to update my account's information")
    def verifyOptionToUpdateMyAccountLink(){
        confirmCustomerPage.verifyUpdateLinkIsPresent()
    }

    @Then("I see the Contact Information shown matches the one entered at account creation time")
    def verifyContactInformationMatchesTheAccount(){
        myAccountPage.verifyPersonalInformation()
        myAccountPage.verifyAccountEMailAddresses()
        myAccountPage.postalAddresses()
        myAccountPage.phoneNumbers()
    }
    @Then("I see the My Rapid Rewards Account page")
    def verifyIamOnTheMyRapidRewardsAccountPage() {
        myAccountPage.verifyPage()
        myAccountPage.verifyPageTitle("My Rapid Rewards Account")
        myAccountPage.verifyIamOnTheCorrectPage("account/rapidrewards")
    }

    @Then("I see the My Travel Snapshot page")
    def verifyIamOnTheMyTravelSnapshotPage() {
        myAccountPage.verifyPage()
        myAccountPage.verifyPageTitle("My Travel Snapshot")
        myAccountPage.verifyIamOnTheCorrectPage("account/travel")
    }

    @Then("I see the Tier Earning Details page")
    def verifyIamOnTheTierEarningDetailsPage() {
        myAccountPage.verifyPage()
        myAccountPage.verifyPageTitle("Tier Earning Details")
        myAccountPage.verifyIamOnTheCorrectPage("account/rapidrewards/tier-status")
    }

    @Then("I see the Companion Pass Status page")
    def verifyIamOnTheCompanionPassStatusPage() {
        myAccountPage.verifyPage()
        myAccountPage.verifyPageTitle("Companion Pass Status")
        myAccountPage.verifyIamOnTheCorrectPage("account/rapidrewards/companion-status")
    }

    @Then("I see the Username & Password section can be edited")
    def verifyUserNameAndPasswordEditLinkIsPresent() {
        myAccountPage.verifyUserNameAndPasswordEditLinkIsPresent()
    }

    @Then("I see oops message for blacklisted password \$blacklistPassword in my account page")
    def verifyOopsMessageForBlacklistOnMyAccountPage(String blacklistPassword) {
        myAccountPage.verifyOopsMessageForBlacklist(blacklistPassword)
    }

    @Then("I see oops message for invalid password in my account page")
    def verifyOopsMessageForInvalidPasswordOnMyAccountPage() {
        myAccountPage.verifyOopsMessageForInvalidPassword()
    }

    @Then("I see oops message for a short password in my account page")
    def verifyOopsMessageForShortPasswordOnMyAccountPage() {
        myAccountPage.verifyOopsMessageForShortPassword()
    }

    @Then("I see the Contact Information page is not editable")
    def verifyIamOnTheContactInformationPageIsNotEditable() {
        myAccountPage.verifyIamOnTheContactInformationPageIsNotEditable()
    }

    @Then("I see the Contact Information section can be edited")
    def verifyTheContactInformationSectionCanBeEdited(){
        myAccountPage.verifyTheContactInformationSectionCanBeEdited(4)
    }

    @Then("I see the Communication Preferences page is not editable")
    def verifyCommunicationPreferencePageIsNotEditable() {
        myAccountPage.verifyCommunicationPreferencePageIsNotEditable()
    }

    @Then("I see I'm subscribed to all the Email options with the email address used at account creation time")
    def verifyEmailSubscribedToAllTheEmailOptions() {
        myAccountPage.verifyEmailSubscribedToAllTheEmailOptions()
    }

    @Then("I see the Communication Preferences section can be edited")
    def verifyCommunicationPreferenceSectionIsNotEditable() {
        if(TogglePreferenceCenter.isOn()) {
            myAccountPage.verifyTheCommunicationPreferencesSectionCanBeEdited(5)
        } else {
            myAccountPage.verifyTheCommunicationPreferencesSectionCanBeEdited(1)
        }
    }

    @Then("I see I'm subscribed to Click 'n Save E-mail Updates with the email address used at account creation time")
    def verifyClickAndSaveEmail() {
        myAccountPage.verifyClickAndSaveEmail()
    }

    @Then("I see I'm subscribed to Southwest In a Nutshell Email Updates with the email address used at account creation time")
    def verifyNutshellEmail() {
        myAccountPage.verifyNutshellEmail()
    }

    @Then("I see that I can access all the past flights from account's Snapshot")
    def verifyViewAllPastFlightsLinkIsPresent() {
        myAccountPage.verifyViewAllPastFlightsLinkIsPresent()
    }

    @Then("I see the Companion Pass details on the account sidebar")
    def verifyCompanionPassDetailsOnAccountSidebar(){
        myAccountPage.verifyCompanionPassFormatDate().shouldBe true, "The Companion Pass date should be formated like mm.dd.yy"
    }

    @Then("I see that the trip and the Air reservation are listed by date earliest to latest on Upcoming Trips page")
    def verifyAirProductsListedByEarliestDate() {
        myAccountPage.verifyDefaultNamedAirProductIsFirstAndNamedTripIsSecond()
    }
    @Then("I see that the trip and the Air reservation are listed by date latest to earliest on Upcoming Trips page")
    def verifyAirProductsListedByLatestDate() {
        myAccountPage.verifyNamedTripIsFirstAndDefaultNamedAirProductIsSecond()
    }

    @Then("I see the breadcrumb shown in the following order: \$breadcrumbs")
    def validateBreadcrumb(String breadcrumb){
        String[] breadcrumbsArray = breadcrumb.split("->")
        myAccountPage.verifyBreadcrumb(breadcrumbsArray)
    }

    @Then("I see the Companion Pass status Achieved on the account sidebar")
    def verifyCompanionPassStatusAchievedOnAccountSidebar(){
        myAccountPage.verifyCompanionPassIsAchieved()
    }
    
    @Then("I see that the results can be sorted by different criteria offering by default Trip Date (Earliest to Latest)")
    def verifyUpcomingTripSortDropDownOptions() {
        myAccountPage.verifyUpcomingTripSortDropDownOptions()
    }

    @Then("I see the products from the trip are listed by date first and then by type on the Upcoming Trips page")
    def verifyTripProductsListedByDateAndType() {
        myAccountPage.verifyTripProductsListedByDateAndType()
    }

    @Then("I see that the trip and the Air reservation are listed by name in descending order on Upcoming Trips page")
    def verifyAirProductsListedByDescendingName() {
        myAccountPage.verifyNamedTripIsFirstAndDefaultNamedAirProductIsSecond()
    }

    @Then("I see that the trip and the Air reservation are listed by name in ascending order on Upcoming Trips page")
    def verifyAirProductsListedByAscendingName() {
        myAccountPage.verifyDefaultNamedAirProductIsFirstAndNamedTripIsSecond()
    }

    @Then("I see a window that requests me to confirm the cancellation")
    def verifyConfirmCanllelationInformation() {
        myAccountPage.verifyConfirmCancellationInformation()
    }

    @Then("I see the Past Trip Details page")
    def verifyPageUrlOnPastTripDetailsPage(){
        accountTravelPastAirDetailsPage.verifyCurrentPageLocation()
    }

    @Then("I see the ticket for the Round Trip Air product")
    @Alias("I see the ticket for the Open Jaw Air product")
    def verifyTwoTicketsArePresentOnPastTripDetailsPage(){
        accountTravelPastAirDetailsPage.verifyNumberOfPresentTickets(2)
    }

    @Then("I see the ticket for the One Way nonstop Air product")
    def verifyOneTicketIsPresentOnPastTripDetailsPage(){
        accountTravelPastAirDetailsPage.verifyNumberOfPresentTickets(1)
    }

    @Then("I view my active promotions in my account")
    def verifyActivePromotions() {
        myAccountPage.verifyAccountSectionTitle(MyAccountPage.ACTIVE_PROMOTIONS_VERBIAGE)
        myAccountPage.verifyIamOnTheCorrectPage("account/rapidrewards/promotions-list")
    }

    @Then("I see my awards at the Certificates Page")
    def seeCertificatesPage(){
        certificatesPage.verifyCurrentPageLocation()
        certificatesPage.verifyPageTitle("Awards")
        validateBreadcrumb("My Account -> My Rapid Rewards -> Certificates")
    }

    @Then("I see the Reissue Pricing Page")
    def seeReissuePricingPage(){
        reissuePricingPage.verifyPageTitle("Reissue Pricing")
        reissuePricingPage.verifyCurrentPageLocation()
        verifyConfirmatinoBreadcrumb()
    }

    @Then("I see an oops message stating that the credit card information was not completed")
    def seeOopsCreditCardMandatoryFields(){
        reissuePricingPage.checkOopsCreditCardMandatoryFields()
    }

    @Then("I am confirmed that my award was reissued on the Reissue Awards Confirmation Page")
    def seeReissueAwardsConfirmationPage(){
        myAccountPage.verifyIamOnTheCorrectPage("account/rapidrewards/reissue-awards-confirmation")
        myAccountPage.verifyReissueAwardSuccessMessage()
        verifyConfirmatinoBreadcrumb()
    }

    private def verifyConfirmatinoBreadcrumb(){
        String[] breadcrumbsArray = ["Reissue Awards","Price & Purchase","Confirmation"]
        myAccountPage.verifyBreadcrumbNoLink(breadcrumbsArray)
    }

    @Then("I should be redirected to the Enrollment Confirmation page")
    def verifyImOnConfirmationPage(){
       verifyFirstNameInTheCongratulationMessage()
    }

   @Then("I should see input text box for new phone number is empty")
    def verifyInputTextForNewPhoneNumberIsEmpty() {
        myAccountPage.verifyTextBoxForFirstNewPhoneNumberIsEmpty()
    }

    @Then("I should not be able to create the account")
    def verifyDuplicateAccountMessage(){
        myAccountPage.shouldShowOopsMessage(DUPLICATE_ACCOUNT_MESSAGE)
    }
}
