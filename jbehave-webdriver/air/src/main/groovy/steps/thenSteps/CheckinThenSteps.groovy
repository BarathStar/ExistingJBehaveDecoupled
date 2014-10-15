package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.CheckinPage
import pages.NewCheckinPage
import pages.NewPricePage
import pages.UpgradeToBusinessSelectPage
import state.Flow
import state.PassengerData
import state.ScenarioState
import util.ItineraryData

class CheckinThenSteps {
    private static final String OOPS_MYIDTRAVEL_MESSAGE = "The nonrevenue flight listing that you're attempting to access was made through MyID Travel and is not eligible for online checkin via southwest.com, swabiz.com or mobile.southwest.com. Please visit the Southwest Airlines Ticket Counter upon arrival at the airport to obtain the necessary documentation for your trip"

    CheckinPage checkinPage
    ItineraryData itineraryData
    ScenarioState scenarioState
    UpgradeToBusinessSelectPage upgradeToBusinessSelectPage
    NewCheckinPage newCheckinPage
    NewPricePage newPricePage
    Flow flow

    @Then("I view the OOPS message for the invalid PNR on the Check-in Page")
    void verifyOopsOnTheCheckinAirReservationPage() {
        checkinPage.verifyInvalidPnrOopsMessage()
    }

    @Then("I should see the Oops message Checkin is currently unavailable for AirTran operated flights because our system to retrieve AirTran itineraries is down")
    void verifyOopsOnTheCheckinForAirTranFlightsWhenNavitaireMaintenanceIsOn() {
        checkinPage.verifySystemDownForAirTranFlightsCheckin()
    }

    @Then("I should not see the business select upgrade button")
    void verifyBusinessSelectUpgradeButtonDoesNotShow() {
        checkinPage.verifyUpgradeToBusinessSelectButtonNotDisplayed()
    }

    @Then("I should see the checkin button is disabled")
    void verifyCheckinButtonIsDisabled() {
        checkinPage.verifyCheckinButtonIsDisabled()
    }

    @Then("I should receive my security document")
    def iShouldReceiveMySecurityDocument() {
        //TODO: Needs proper verification

    }

    @Then("I am sent to the checkin landing page")
    def verifyCheckInLandingPage() {
        checkinPage.verifyCheckinLandingPage()
    }

    @Then("I verify that business select could be available")
    void verifyBusinessSelectIsAvailable() {
        upgradeToBusinessSelectPage.verifyPage()
        upgradeToBusinessSelectPage.verifyAirTranFlightsNotAvailableMessageDoesNotShow()
    }

    @Then("I verify that business select is unavailable")
    void verifyBusinessSelectIsUnavailable() {
        upgradeToBusinessSelectPage.verifyPage()
        upgradeToBusinessSelectPage.verifyBusinessSelectIsUnavailable()
    }

    @Then("the Rapid Rewards update message is displayed")
    void rapidRewardsUpdateMsgIsDisplayed() {
        checkinPage.checkRapidRewardsUpdateMessage()
    }

    @Then("I see the invalid Rapid Rewards number Oops message")
    def shouldShowOopsMessage() {
        checkinPage.verifyUpdateOopsMessage()
    }

    @Then("I see the help popup box")
    def helpPopupBoxDisplayed() {
        checkinPage.verifyHelpPopupBox()
    }

    @Then("I should see the Check In page with my compound first and compound last name on")
    void CheckInShouldDisplayCompoundNames() {
        checkinPage.verifyPassengerName(PassengerData.COMPOUND_PASSENGER_FIRST_NAME, PassengerData.COMPOUND_PASSENGER_LAST_NAME)
    }

    @Then("I see seat selection message on the checkin page")
    public void verifyWCMSeatSelectionMessageOnCheckinPage() {
        checkinPage.verifyWCMSeatSelectionMessageOnCheckinPage()
    }

    @Then("I view the Fly by banner with Ticket Counter and Security Checkpoint information")
    public void verifyBothFlyBy() {
        newCheckinPage.verifyBothFlyBy()
    }

    @Then("I view the Fly by banner with only the Security Checkpoint information")
    public void verifySecurityCheckpointFlyBy() {
        newCheckinPage.verifySecurityCheckpointFlyBy()
    }

    @Then("I will not see the Fly by banner")
    public void verifyNoneFlyBy() {
        newCheckinPage.verifyNoneFlyBy()
    }

    @Then("I will see my passenger information")
    public void verifyPassengerInformation() {
        verifyNumberOfPassengers()
        verifyAirportCheckinRequired()
        newCheckinPage.verifyRRLinkDisplayed()
    }

    @Then("I will see my Rapid Rewards passenger information")
    public void verifyRapidRewardsPassengerInformation() {
        verifyNumberOfPassengers()
        verifyAirportCheckinRequired()
        newCheckinPage.verifyRRNumberDisplayed()
    }

    @Then("I will see my checkin flight information")
    public void verifyCheckinFlightInformation() {
        verifyNumberOfFlightsDisplayed()
        if (itineraryData.departingFlight_fareClass == "Anytime") {
            newCheckinPage.verifyBusinessSelectDisplayed().shouldBe true, "The Business Select upsell is being shown in the new checkin page when it shouldn't"
            newCheckinPage.verifyPrintDrinksDisplayed().shouldBe false, "The print drinks coupon option is not being shown when it should"
        }
        else {
            newCheckinPage.verifyBusinessSelectDisplayed().shouldBe false, "The Business Select upsell is not being shown in the new checkin page when it should"
            newCheckinPage.verifyPrintDrinksDisplayed().shouldBe true, "The print drinks coupon option is being shown when it shouldn't"
        }
    }

    private void verifyNumberOfPassengers() {
        int numPass = scenarioState.getLastAirReservation().passengers.size()
        newCheckinPage.verifyPassengersDisplayed(numPass)
    }

    private void verifyNumberOfFlightsDisplayed() {
        int numFlights = (itineraryData.outboundRouting.equalsIgnoreCase("Nonstop"))? 1 : 2
        newCheckinPage.verifyFlightNumbersDisplayed(numFlights)
        newCheckinPage.verifyFlightTimesDisplayed(numFlights)
    }

    private void verifyAirportCheckinRequired() {
        if(flow.hasAirportCheckinRequiredPassenger) {
            newCheckinPage.verifyDocTypeDisplayed().shouldBe true, "The user is not being required to do an airport check in when it should."
        }
        else {
            newCheckinPage.verifyDocTypeDisplayed().shouldBe false, "The user is being required to do an airport check in when it shouldn't."
        }
    }

    @Then("I see the My Id Travel Oops message on check in page")
    def shouldShowMyIdTravelOopsMessage() {
        checkinPage.shouldShowOopsMessage(OOPS_MYIDTRAVEL_MESSAGE)
    }

    @Then("I don't see a button to upgrade neither any message informing me that I am able to upgrade to business select")
    def shouldNotShowUpgradeToBusinessSelect() {
        checkinPage.verifyUpgradeToBusinessSelectButtonNotDisplayed()
    }

    @Then("I see Business Select product available")
    void verifyBusinessFare() {
        newPricePage.verifyBusinessSelectProductAvailable()
    }

    @Then("I see the View / Select Seat(s) button on the check in page")
    def iSeeTheViewSelectSeatsButtonOnTheCheckInPage() {
        checkinPage.verifyAirtranSeatSelectionButtonVisible()
    }

    @Then("I view an oops message that says is unable to check-in using this confirmation number")
    def verifyUnableCheckinPNR(){
        checkinPage.verifyOopsMessageUnableToCheckin()
    }

    @Then ( "I see the AirTran Seat Selection Button on the Checkin and Print Boarding Documents Page" )
    def iSeeTheAirTranSeatSelectionButtonOnTheCheckInAndPrintBoardingDocumentsPage() {
        checkinPage.verifyAirtranSeatSelectionButtonVisible()
    }

    @Then ( "I DO NOT see the AirTran Seat Selection Button on the Checkin and Print Boarding Documents Page" )
    def iDoNotSeeTheAirTranSeatSelectionButtonOnTheCheckInAndPrintBoardingDocumentsPage() {
        checkinPage.verifyAirtranSeatSelectionButtonNotDisplayed()
    }

    @Then("I view an Oops message which indicates that my altea reservation may have been cancelled")
    def verifyAlteaReservationMayHaveBeenCancelledOopsMessage() {
        checkinPage.verifyReservationMayHaveBeenCancelledOopsMessage()
    }
}
