package steps.thenSteps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Then

import pages.CancelAirPage
import pages.CancelAirReservationPage
import pages.CancelBoardingPassPage

import state.Flow
import state.ScenarioState
import util.ItineraryData
import pages.ExternalRedirectPage

class CancelAirThenSteps {
    private static final String OOPS_MYIDTRAVEL_MESSAGE = "The nonrevenue flight listing that you're attempting to access was made through MyID Travel and is not accessible via southwest.com, swabiz.com or mobile.southwest.com. To access your flight listing, please visit the MyID Travel site."

    CancelAirReservationPage cancelAirReservationPage
    CancelAirPage cancelAirPage
    ScenarioState scenarioState
    CancelBoardingPassPage cancelBoardingPassPage
    ExternalRedirectPage externalRedirectPage
    Flow flow

    @Then("I am on the Cancel Air Reservation Page")
    @Alias("I am redirected to the Cancel Air Reservation page")
    void verifyIAmOnTheCancelAirReservationPage() {
        cancelAirReservationPage.verifyTitleCancelAirReservation()
    }

    @Then("I see the Air product associated to my trip on the Cancel Air Reservation Page")
    @Alias("I see the Southwest Airlines - Cancel Air Reservation page")
    void verifyIAmOnTheCancelAirPage(){
        cancelAirPage.verifyCurrentPageLocation()
        cancelAirPage.verifyTitleCancelAir()
    }

    @Then('I see the Associated Products Modal with an error message for each of the products in the trip that could not be retrieved')
    void verifyAssociatedProductsModalCouldNotRetrieved() {
        cancelAirReservationPage.verifyAssociatedProductsModalCouldNotRetrieved()
    }

    @Then("I view the OOPS message for the invalid PNR on the Cancel Air Reservation Page")
    void verifyOopsOnTheCancelAirReservationPage() {
        cancelAirReservationPage.verifyInvalidPnrOopsMessage()
    }

    @Then("I verify the transition to airtran.com")
    void verifyAirTranLinkTransition() {
        cancelAirReservationPage.verifyTransitionToAirTran()
    }

    @Then("I see the boarding pass information for both, the rapid rewards member and his companion passenger")
    void seeBoardingPassInfoForMemberAndCompanion() {
        ItineraryData itineraryData = scenarioState.getLastAirReservation().getItineraryData()
        int rowsSize = (itineraryData.isRoundTrip())? 2 : 1
        cancelBoardingPassPage.getPassengerTableRows().size().shouldBeEqualTo rowsSize, "The number of rows do not match with the itinerary for the RR passenger."
        cancelBoardingPassPage.getCompanionTableRows().size().shouldBeEqualTo rowsSize, "The number of rows do not match with the itinerary for the companion."
    }

    @Then("I see the air product on the Cancel Air Reservation page")
    def seeAirProductOnTheCancelAirReservationPage(){
        String confirmationNumber = scenarioState.firstAirReservation.getAdultPnr()
        cancelAirReservationPage.verifyConfirmationNumberInPageTitle(confirmationNumber)
    }

    @Then("I see a message which informs that the points will be returned to the corresponding rapid rewards account")
    void verifyPointsWillBeReturned() {
        cancelAirReservationPage.verifyPointsText()
        cancelAirReservationPage.verifyPointsNumber(flow.rapidRewardsPoints)
        cancelAirReservationPage.verifyPointsReturningMessage(flow.rrUser.firstName+" "+flow.rrUser.lastName)
    }

    @Then("I see the information of the \$awardsCount award(s) to be returned")
    def seeReturnedAwardsInformation(String awardsCount) {
        cancelAirPage.verifyInformationForAwardsOnTravelFundsSection(flow.rrUser.RRFirstName, flow.rrUser.RRLastName, awardsCount)
    }

    @Then("I see the Cancel Air Reservation Page")
    void verifyISeeCancelAirReservationPage() {
        cancelAirReservationPage.verifyIamOnTheCorrectPage(cancelAirReservationPage.CONFIRMATION_PATH)
        cancelAirReservationPage.verifyTitleCancelAirReservation()
    }

    @Then("I see the My Id Travel Oops message on cancel air page")
    def shouldShowMyIdTravelOopsMessage() {
        cancelAirReservationPage.shouldShowOopsMessage(OOPS_MYIDTRAVEL_MESSAGE)
    }

    @Then("I should see an error message on modal for the senior PNR")
    void verifyAirProductModalNotRetrieved() {
        cancelAirReservationPage.verifyAirPNRAssociatedProductModalCouldNotRetrieved()
    }

    @Then("I should see confirmation number on Cancel Air Reservation page")
    void verifyConfirmationNumberOnCancelAirReservationPage() {
        String confirmationNumber = scenarioState.firstAirReservation.getAdultPnr()
        cancelAirReservationPage.verifyConfirmationNumberOnThePage(confirmationNumber)
    }
}