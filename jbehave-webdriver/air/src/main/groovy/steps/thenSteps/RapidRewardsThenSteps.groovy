package steps.thenSteps

import domain.AirReservation
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Then
import pages.AddRapidRewardsPNRConfirmationPage
import pages.AddRapidRewardsPage
import pages.RapidRewardsOverviewPage
import pages.RapidRewardsPartnersPage
import pages.CreateAnAccountPage
import pages.HomePage
import pages.MyRapidRewardsPage
import pages.RecoverUsernamePasswordPage
import pages.RetrieveRapidRewardsAccountNumberPage
import pages.SummaryEmailListPage
import state.AccountManagementData
import pages.SearchFlightsPage
import pages.elements.GlobalNavigationHeader
import pages.elements.GlobalNavigationFooter
import pages.AirTripDetailsPage
import pages.ItineraryChangeConfirmationPage
import state.ScenarioState

class RapidRewardsThenSteps {

    AddRapidRewardsPage addRapidRewardsPage
    RapidRewardsPartnersPage rapidRewardsPartnersPage
    RapidRewardsOverviewPage rapidRewardsOverviewPage
    CreateAnAccountPage createAnAccountPage
    RecoverUsernamePasswordPage recoverUsernamePasswordPage
    RetrieveRapidRewardsAccountNumberPage retrieveRapidRewardsAccountNumberPage
    SummaryEmailListPage summaryEmailListPage
    AccountManagementData accountManagementData
    HomePage homePage
    SearchFlightsPage searchFlightsPage
    MyRapidRewardsPage myRapidRewardsPage
    GlobalNavigationHeader globalNavigationHeader
    GlobalNavigationFooter globalNavigationFooter
    AirTripDetailsPage airTripDetailsPage
    ItineraryChangeConfirmationPage itineraryChangeConfirmationPage
    AddRapidRewardsPNRConfirmationPage addRapidRewardsPNRConfirmationPage
    ScenarioState scenarioState

    @Then("I should be on the Enter Rapid Rewards number page")
    void verifyEnterRapidRewardsPage() {
        addRapidRewardsPage.verifyEnterRapidRewardsPage()
    }

    @Then("I should be in the Add Rapid Rewards Number page")
    void verifyAddRapidRewardsNumberPage() {
        addRapidRewardsPage.verifyAddRapidRewardsNumberPage()
    }

    @Then("I view the OOPS message for the invalid PNR on the Add Rapid Rewards page")
    void verifyInvalidPNROOPSMessage() {
        addRapidRewardsPage.verifyInvalidPnrOopsMessage()
    }

    @Then("I see my Rapid Rewards Number added to my itinerary")
    void verifyRapidRewardsNumberAdded() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        addRapidRewardsPNRConfirmationPage.verifyPage()
        addRapidRewardsPNRConfirmationPage.verifyTitle()
        addRapidRewardsPNRConfirmationPage.verifyConfirmationNumber(airReservation.adultPnr)
        addRapidRewardsPNRConfirmationPage.verifyPassengerName(passenger.firstName, passenger.lastName)
        addRapidRewardsPNRConfirmationPage.verifySingInToMySouthwestLink()
        addRapidRewardsPNRConfirmationPage.verifyUpdatedReservationInformationText()
        addRapidRewardsPNRConfirmationPage.verifyUpgradeBusinessSelectButton()
        if (!DynaStubsIntegration.useDynaStubs()) {
            addRapidRewardsPNRConfirmationPage.verifyRapidRewardsNumberAdded()
        }
    }

    @Then("I click on the add Rapid Rewards number link")
    void iAmOnTheRapidRewardsNumberPage() {
        if (DynaStubsIntegration.useDynaStubs() && addRapidRewardsPage.getCurrentUrl().equalsIgnoreCase("about:blank")) {
            addRapidRewardsPage.open()
            return
        }

        addRapidRewardsPage.clickAddRapidRewardsLink()
        //TODO make hover over Air tab work to click on Add Rapid Rewards link
        //viewAddRapidRewardsNumberPage()
    }

    @Then("I view more ways to earn")
    def verifyEarnPageRendered() {
        rapidRewardsOverviewPage.verifyPage()
        rapidRewardsOverviewPage.verifyHowToEarnSubNavigation()
    }

    @Then("I view how many points per dollar I earn")
    def aListPreferredDisplayed() {
        rapidRewardsOverviewPage.selectAlistPreferred()
    }

    @Then("I view alist status and benefits")
    def tiersAndMoreAlistIsDisplayed() {
        rapidRewardsOverviewPage.verifyPage()
    }

    @Then("I view status and benefits")
    def aListDisplayed() {
        rapidRewardsOverviewPage.checkSomethingServed()
        rapidRewardsOverviewPage.checkNoOops()
        //TODO: Needs proper verifications
    }

    @Then("I view the AirTran Message on the Add Rapid Rewards Number Page")
    void verifyAirTranMessageOnRapidRewardsNumberPage() {
        addRapidRewardsPage.verifyAirTranMessage()
    }

    @Then("I should see the oops message for invalid Rapid Rewards Number")
    void oopsRewardsNumberInvalid() {
        addRapidRewardsPage.shouldShowOopsMessage("Rapid Rewards Account Number(s) for the following Passenger(s) is/are invalid")
    }

    @Then("I should see the oops message for invalid Rapid Rewards Number on the Add Rapid Rewards page")
    @Alias("I should see the oops message for valid AirTran Loyalty number on the Add Rapid Rewards page")
    void oopsRewardsNumberPageInvalidRapidRewardNumber() {
        addRapidRewardsPage.shouldShowOopsMessage("The Rapid Rewards Number entered does not match the Member Name on record")
    }

    @Then("I should see the oops message for invalid Passenger Name")
    void oopsRewardsNumberInvalidPassengerName() {
        addRapidRewardsPage.shouldShowOopsMessage("The passenger name entered does not match one of the passenger names listed under the confirmation number. Check the confirmation number and passenger's name to make sure they are correct")
    }

    @Then("I see my first e-mail subscribed to Rapid Rewards E-mail Update and The Rapid Rewards Report")
    def verifyFirstEmailSignedInRrrpAndRreu(){
        summaryEmailListPage.verifyFirstEmailSignedInRrrpAndRreu(accountManagementData.emailAddress)
    }

    @Then("I see my second e-mail only subscribed to The Rapid Rewards Report")
    def verifySecondEmailSignedInRrrp(){
        summaryEmailListPage.verifySecondEmailSignedInRrrp(accountManagementData.secondEmailAddress)
    }

    @Then("I see my third e-mail only subscribed to Rapid Rewards E-mail Update")
    def verifyThirdEmailSignedInRreu(){
        summaryEmailListPage.verifyThirdEmailSignedInRreu(accountManagementData.thirdEmailAddress)
    }

    @Then("I am redirected to Create an Account page")
    def verifyCreateAccountPage() {
        createAnAccountPage.verifyOnCurrentPage()
    }

    @Then("I am redirected to Recover your Username or Password page")
    def verifyRecoverUsernamePasswordPage() {
        recoverUsernamePasswordPage.verifyOnCurrentPage()
    }

    @Then("I am redirected to Retrieve Rapid Rewards/Account Number page")
    def verifyRapidRewardsAccountNumberPage() {
        retrieveRapidRewardsAccountNumberPage.verifyOnCurrentPage()
    }

    @Then("I see Enroll Now button on the account sidebar under My Rapid Rewards section not logged in")
    def verifyEnrollRrButtonIsPresentNotLoggedIn() {
        searchFlightsPage.expandMyRapidRewards()
        verifyEnrollButtonIsPresent()
        searchFlightsPage.verifyEnrollRrButtonHRefMember()
    }

    @Then("I see Enroll Now button on the account sidebar under My Rapid Rewards section logged in")
    def verifyEnrollRrButtonIsPresentLoggedIn() {
        verifyEnrollButtonIsPresent()
        searchFlightsPage.verifyEnrollRrButtonHRefUpgrade()
    }

    @Then("I should not see enroll links")
    def verifyEnrollLinksAreNotPresent() {
        homePage.verifyEnrollLinkIsNotPresent()
        globalNavigationHeader.openRapidRewardsSubMenu()
        globalNavigationHeader.verifyEnrollLinkIsNotPresent()
        globalNavigationFooter.verifyEnrollLinkIsNotPresent()
    }

    @Then("I should see enroll links")
    def verifyEnrollLinksArePresent() {
        homePage.verifyEnrollLinkIsPresent()
        globalNavigationHeader.openRapidRewardsSubMenu()
        globalNavigationHeader.verifyEnrollLinkIsPresent()
        globalNavigationFooter.verifyEnrollLinkIsPresent()
    }

    private verifyEnrollButtonIsPresent() {
        searchFlightsPage.verifyEnrollRrButtonIsPresent()
    }

    @Then("I see all the information of my Awards From Transition Program")
    def verifyAwardsFromTransitionProgram() {
        myRapidRewardsPage.verifyAwardsFromTransitionProgram()
    }

    @Then("I see the options which allow me to book a flight by using my awards")
    def verifyFlightBookingWithAwards() {
        myRapidRewardsPage.verifyFlightBookingWithAwards()
    }

    @Then("I see the information, terms and conditions of my Awards From Transition Program")
    def verifyTermsAndConditionsFromTrasitionProgramPage() {
        myRapidRewardsPage.verifyTermsAndConditionsFromTrasitionProgramPage()
    }

    @Then("I see all the information of my standard awards")
    def verifyStandardAwardsInformation() {
        myRapidRewardsPage.verifyStandardAwardsInformation()
    }

    @Then("I see that some standard awards are available for reissue")
    @Alias("I see that some freedom awards are available for reissue")
    def verifyAwardsAvailableForReissue() {
        myRapidRewardsPage.verifyAwardsAvailableForReissue()
    }

    @Then("I see the information, terms and conditions of my standard awards")
    def verifyTermsAndConditionsOfStandardAwardsPage() {
        myRapidRewardsPage.verifyTermsAndConditionsOfStandardAwardsPage()
    }

    @Then("I see all the information of my freedom awards")
    def verifyFreedomAwardsInformation() {
        myRapidRewardsPage.verifyFreedomAwardsInformation()
    }

    @Then("I see the information, terms and conditions of my freedom awards")
    def verifyTermsAndConditionsFromFreedomAwardsPage() {
        myRapidRewardsPage.verifyTermsAndConditionsFromFreedomAwardsPage()
    }

    @Then("I see Buy Points page")
    def verifyBuyPointsPage() {
        rapidRewardsPartnersPage.verifyOnRRPoints()
    }

    @Then("I see Rapid Rewards Shopping page")
    def verifyRapidRewardsShoppingPage() {
        rapidRewardsPartnersPage.verifyOnRRShopping()
    }


    @Then("I see More Rewards page")
    def verifyMoreRewardsPage() {
        rapidRewardsPartnersPage.verifyOnMoreRewardsPage()
    }

    @Then ( "I see the Upcoming Trips Details Page" )
    def iSeeTheUpcomingTripsDetailsPage() {
        airTripDetailsPage.verifyAirConfirmationNumber()
    }

    @Then( "I see the AirTran Seat Selection button on the Upcoming Trips Details Page" )
    def iSeeTheAirTranSeatSelectionButtonOnTheUpcomingTripsDetailsPage() {
        airTripDetailsPage.verifyAirTranSeatSelectionButtonVisible()
    }

    @Then("I see the Rapid Rewards Itinerary Changed Page")
    def iSeeTheRapidRewardsItineraryChangedPage() {
        itineraryChangeConfirmationPage.verifyPage()
    }

    @Then( "I see the seat selection button on the Rapid Rewards Itinerary Changed Page" )
    def iSeeTheSeatSelectionButtonOnTheRapidRewardsItineraryChangedPage() {
        itineraryChangeConfirmationPage.verifyAirTranSeatSelectionButtonVisible()
    }

    @Then( "I DO NOT see the seat selection button on the Rapid Rewards Itinerary Changed Page" )
    def iDoNotSeeTheSeatSelectionButtonOnTheRapidRewardsItineraryChangedPage() {
        itineraryChangeConfirmationPage.verifyAirTranSeatSelectionButtonIsNotVisible()
    }

    @Then("I should not see the AirTran Seat Selection button on the Upcoming Trips Details Page")
    def iShouldNotSeeTheAirTranSeatSelectionButtonOnTheUpcomingTripsDetailsPage() {
        airTripDetailsPage.verifyAirTranSeatSelectionButtonNotVisible()
    }

    @Then("I see confirmation number and name pre populated in Add Rapid Rewards Number page")
    def verifyPrePopulatedValueInAddRapidRewardsNumberPage(){
        addRapidRewardsPage.verifyPrePopulatedValueInAddRapidRewardsPage()
    }

    @Then("I see first name and last name populated in Add Rapid Rewards Number page")
    def verifyFirstNameAndLastName(){
        addRapidRewardsPage.verifyFirstNameAndLastName()
    }
}
