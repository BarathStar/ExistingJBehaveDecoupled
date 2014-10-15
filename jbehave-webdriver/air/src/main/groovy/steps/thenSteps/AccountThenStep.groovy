package steps.thenSteps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Then
import pages.AccountSetupPage
import pages.CreateAnAccountPage
import pages.IntroducingNewRapidRewardsPage
import pages.MyAccountPage
import pages.ViewReservationPage
import pages.AddressValidationReviewPage
import pages.ConfirmCustomerPage

class AccountThenStep {

    MyAccountPage myAccountPage
    IntroducingNewRapidRewardsPage introducingNewRapidRewardsPage
    CreateAnAccountPage createAnAccountPage
    ViewReservationPage viewReservationPage
    AddressValidationReviewPage addressValidationReviewPage
    ConfirmCustomerPage confirmCustomerPage
    AccountSetupPage accountSetupPage

    @Then('I see a message indicating that the accounts have been successfully consolidated')
    def verifyConsolidationSuccess() {
        myAccountPage.verifyPage()
        myAccountPage.verifyConsolidationSuccess()
    }

    @Then('I see the Introducing the All-New Rapid Rewards page')
    def verifyImOnIntroducingNewRRPage() {
        introducingNewRapidRewardsPage.verifyPage()
        introducingNewRapidRewardsPage.verifyCurrentPageLocation()
        introducingNewRapidRewardsPage.verifyPageTitle("Introducing Rapid Rewards")
    }

    @Then("I see Click N' Save and In a Nutshell email subscription options are not selected")
    def verifyClickNSaveAndNutshellAreNotSelected() {
        createAnAccountPage.verifyClickAndSaveAndInANutshellAreNotSelected()
    }

    @Then("I see The Rapid Rewards Report and The Rapid Rewards E-mail Update email subscription options are selected")
    def verifyRRReportAndRREmailUpdateAreSelected() {
        createAnAccountPage.verifyRapidRewardsReportAndUpdateAreSelected()
    }

    @Then("I see an error message for each mandatory field that was not entered")
    def verifyMandatoryFieldsOopsMessagesIsPresent() {
        createAnAccountPage.verifyMandatoryFieldsOopsMessagesIsPresent()
    }

    @Then("I see Click N' Save and In a Nutshell email subscription options are selected")
    def verifyClickNSaveAndNutshellAreSelected() {
        createAnAccountPage.verifyClickAndSaveAndNutshellAreSelected()
    }

    @Then("I see The Rapid Rewards Report and The Rapid Rewards E-mail Update email subscription options are not selected")
    def verifyRRReportAndRREmailUpdateAreNotSelected() {
        createAnAccountPage.verifyRapidRewardsReportAndUpdateAreNotSelected()
    }

    @Then("I should not see a message indicating that Promotional Certificates can not be retrieved on My Account Snapshot page")
    def verifyNotSeePromotionalCertificatesMessage() {
        myAccountPage.verifyNotSeePromotionalCertificatesMessage()
    }

    @Then("I see a message indicating that Promotions can not be retrieved on My Account Snapshot page")
    @Alias("I see a message indicating that Promotions can not be retrieved on My Rapid Rewards Account page")
    def verifySeePromotionalMessage() {
        myAccountPage.verifyPromotionalMessage()
    }

    @Then("I see a message indicating that Promotional Certificates can not be retrieved on My Rapid Rewards Account page")
    def verifySeePromotionalCertificatesUnavailableMessage() {
        myAccountPage.verifySeePromotionalCertificatesUnavailableMessage()
    }

    @Then("I see that I have promo certs")
    def verifyIHavePromoCerts() {
        myAccountPage.verifySeePromotionalCertificates()
    }

    @Then("I see that My Trip is shown under Upcoming Trips section on My Account Snapshot page")
    def verifyISeeMyTripUnderUpcomingTripsSection() {
        myAccountPage.verifyISeeMyTripUnderUpcomingTripsSection()
    }

    @Then("I see a message specifying that my upcoming trips cannot be retrieved on My Account Snapshot page")
    def verifyUpcomingTripServiceUnavailable() {
        myAccountPage.verifyUpcomingTripServiceUnavailable()
    }

    @Then("I am informed that my round trip flight was booked by using standard awards")
    def verifyUpcomingTripsBookedUsingStandardAwards() {
        viewReservationPage.verifyFareClassLink("Standard Award")
    }


    @Then("I should not see my security question 1 choice on my security question 2 options")
    def verifySelectedQuestion1NotInSelectedQuestion2() {
        createAnAccountPage.verifySelectedElementNotInAnotherSelect1()
    }

    @Then("I should not see my security question 2 choice on my security question 1 options")
    def verifySelectedQuestion2NotInSelectedQuestion1() {
        createAnAccountPage.verifySelectedElementNotInAnotherSelect2()
    }

    @Then("I should get an Oops message requiring me to select both security questions")
    def verifyBothSecurityQuestionIsPresent(){
        createAnAccountPage.verifyBothSecurityQuestions()
    }

    @Then("I should get an Oops message requiring me to select security question 1")
    def verifySecurityQuestion1IsPresent(){
        createAnAccountPage.verifySecurityQuestion1()
    }

    @Then("I should not get an Oops message requiring me to select security question 1")
    def verifySecurityQuestion1IsNotPresent(){
        createAnAccountPage.verifySecurityQuestion1IsNotPresent();
    }

    @Then("I should get an Oops message requiring me to select security question 2")
    def verifySecurityQuestion2IsPresent(){
        createAnAccountPage.verifySecurityQuestion2()
    }

    @Then("I should not get an Oops message requiring me to select security question 2")
    def verifySecurityQuestion2IsNotPresent(){
        createAnAccountPage.verifySecurityQuestion2IsNotPresent();
    }

    @Then("I should see an Oops message for session expired")
    def verifyMasterSessionTimeoutErrorPage(){
        myAccountPage.shouldSeeOopsMsgAboutMasterSessionTimeout()
    }

    @Then("I see that the text of the submit button is Continue")
    def verifySubmitButtonTextIsContinue(){
        createAnAccountPage.verifySubmitButtonPresent()
    }

    @Then("I should be redirected to the Review Page")
    def verifyIamOnReviewPage(){
        addressValidationReviewPage.verifyOnCurrentPage()
    }

    @Then("I am redirected to the Enrollment page")
    def verifyIamOnEnrollmentPage(){
        createAnAccountPage.verifyOnCurrentPage()
    }

    @Then ("I see my address and name information cleansed on the Enrollment page")
    def verifyCleansedInformationOnEnrollmentPage(){
        createAnAccountPage.verifyInformationCleansed()
    }

    @Then ("I see my address and name information NOT cleansed on the Enrollment page")
    def verifyNonCleansedInformationOnEnrollmentPage(){
        createAnAccountPage.verifyInformationNotCleansed()
    }

    @Then("I should see the maintenance mode oops message")
    void verifyMaintenanceModeOopsMessageIsDisplayed() {
        createAnAccountPage.verifyMaintenanceModeOopsMessageIsDisplayed()
    }

    @Then("I will see that I do not have any message about Companion Pass in My Snapshot Page")
    def verifyCompanionPassSectionNotOnSnapshotPage() {
        myAccountPage.clickSnapshotLink()
        myAccountPage.verifyCompanionSectionNotPresentOnSnapshotPage()
    }

    @Then("I will see that I do not have any message about Companion Pass in My Rapid Rewards Page")
    def verifyCompanionPassSectionNotOnMyRRPage() {
        myAccountPage.clickMyRapidRewardsLink()
        myAccountPage.verifyCompanionSectionNotPresentOnMyRRPage()
    }

    @Then("I should see YES and NO labels for radio buttons under Rapid Rewards Enrollment section")
    def verifyRadiobuttonLabelsForEnrollmentSection(){
        createAnAccountPage.verifyRadiobuttonYesLabelForEnrollmentSection()
        createAnAccountPage.verifyRadiobuttonNoLabelForEnrollmentSection()
    }

    @Then("I see a message indicating that there are no upcoming flights in my Account Bar")
    def verifyAccountBarDisplaysNoUpcomingFlightsMessage() {
        myAccountPage.clickAccountBarMyTravelLink()
        myAccountPage.verifyPage()
        myAccountPage.shouldSeeLabelUpcomingTrips()
        myAccountPage.verifyNoUpcomingFlightsMessageOnAccountBar()
    }

    @Then("I see that the account bar displays the information of my entire outbound flight as an upcoming trip")
    def verifyAccountBarDisplaysUpcomingTripInformationForOutBoundFlight() {
        myAccountPage.viewTripDetails()
        myAccountPage.clickAccountBarMyTravelLink()
        myAccountPage.verifyPage()
        myAccountPage.shouldSeeLabelUpcomingTrips()
        myAccountPage.shouldSeeLinkDepartureArrivalCitiesOfOutboundFlight()
        myAccountPage.shouldSeeDepartureDateAndConfirmationOfCurrentFlight()
        myAccountPage.shouldSeeDepartureAndArrivalTime()
    }

    @Then("I see that the account bar displays the information of my entire inbound flight as an upcoming trip")
    def verifyAccountBarDisplaysUpcomingTripInformationForInBoundFlight() {
        myAccountPage.viewTripDetails()
        myAccountPage.clickAccountBarMyTravelLink()
        myAccountPage.verifyPage()
        myAccountPage.shouldSeeLabelUpcomingTrips()
        myAccountPage.shouldSeeLinkDepartureArrivalCitiesOfInboundFlight()
        myAccountPage.shouldSeeDepartureDateAndConfirmationOfCurrentFlight()
        myAccountPage.shouldSeeDepartureAndArrivalTime()
    }

    @Then("I am redirected to the Trip Details Page")
    def verifyIamOnTheTripDetailsPagePage() {
        myAccountPage.verifyPage()
        myAccountPage.verifyIamOnTheTripDetailsPagePage()

    }

    @Then("I see the upcoming flight being showed in My Travel in the account bar")
    def verifyUpcomingTripInMyTravel(){
        myAccountPage.upcomingTripConfirmation()
    }

    @Then("I see a message indicating that there are no Upcoming Trips")
    def verifyNoUpcomingTripsMessage() {
        myAccountPage.verifyNoUpcomingTripsMessage();
    }

    @Then("I see rapid rewards communication preferences with accept rules checkbox")
    def seeRapidRewardsCommunicationPreferences() {
        accountSetupPage.verifyRapidRewardsOptionsInCommunicationPreferences()
        accountSetupPage.verifyAcceptTheRulesAndRegulationsCheckbox()
    }

    @Then("I see Customer communication preferences")
    def seeCustomerCommunicationPreferences() {
        accountSetupPage.verifyCustomerOptionsInCommunicationPreferences()
        accountSetupPage.verifyNoAcceptTheRulesAndRegulationsCheckbox()
    }
}
