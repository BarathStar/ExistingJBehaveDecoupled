package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.AlistFaqPage
import pages.EarlyBirdConfirmationPage
import pages.EarlyBirdPurchaseFlow
import pages.EarlyBirdPurchasePage

class EarlyBirdThenSteps {

    EarlyBirdPurchaseFlow earlyBirdCheckinPage
    EarlyBirdConfirmationPage earlyBirdConfirmationPage
    EarlyBirdPurchasePage earlyBirdPurchasePage
    AlistFaqPage alistFaqPage

    private static final String OOPS_MYIDTRAVEL_MESSAGE = "The nonrevenue flight listing that you're attempting to access was made through MyID Travel and is not accessible via southwest.com, swabiz.com or mobile.southwest.com. To access your flight listing, please visit the MyID Travel site."

    @Then("I am taken to the EB direct purchase path")
    verifyEarlyBirdChooseFlightsPageVisible() {
        earlyBirdCheckinPage.isDisplayed()
    }

    @Then("I should see the A list FAQ message")
    def verifyIShouldSeeA_List_Faq_Message() {
        alistFaqPage.verifyAlistFaqMessage()
    }

    @Then("I see AirTran message in page")
    def verifyAirTranMessageOnEarlyBirdCheckIn() {
        earlyBirdCheckinPage.verifyAirTranMessage()
    }

    @Then("I see all eligible flights with check box fields")
    def verifyEligibleFlights() {
        earlyBirdCheckinPage.verifyEligibleEarlyBird()
    }

    @Then("I see all non eligible flights")
    def verifyNonEligibleFlights() {
        earlyBirdCheckinPage.verifyNonEligibleEarlyBird()
    }

    @Then("I see the non eligible AirTran message")
    def verifyEarlyBirdNotEligibleForAirTranText() {
        earlyBirdCheckinPage.verifyEarlyBirdNotEligibleMessageForAirTran()
        earlyBirdCheckinPage.verifyEarlyBirdNoContinueText()
        earlyBirdCheckinPage.verifyContinueButtonNotPresent()
    }

    @Then("I see the non eligible message")
    def verifyEarlyBirdNotEligibleText() {
        earlyBirdCheckinPage.verifyEarlyBirdNotEligibleMessage()
    }

    @Then("I see Early Bird badge on check-in")
    def verifyEarlyBirdBadge() {
        earlyBirdCheckinPage.verifyEarlyBirdBadge()
    }

    @Then("I see earlybird purchase successful page")
    def earlyBirdPurchaseSuccessful() {
        earlyBirdConfirmationPage.confirmPurchaseSuccessfulMessage()
    }

    @Then("I view the OOPS message for the invalid PNR on the EarlyBird Check-in Page")
    void verifyOopsOnTheEarlyBirdCheckinAirReservationPage() {
        earlyBirdCheckinPage.verifyInvalidPnrOopsMessage()
    }

    @Then("I view the OOPS message for attempting to buy a EarlyBird in .com for a booking purchased in swabiz")
    void verifyOopsWhenTryPurchaseEarlyBird() {
        earlyBirdCheckinPage.verifySwaBizNotPurchaseFromDotComOops()
    }

    @Then("I see the My Id Travel Oops message on EarlyBird page")
    def shouldShowMyIdTravelOopsMessage() {
        earlyBirdCheckinPage.shouldShowOopsMessage(OOPS_MYIDTRAVEL_MESSAGE)
    }

    @Then("I see my stored credit card preselected on the EarlyBird Purchase Page")
    def verifyEarlyBirdCreditCard() {
        earlyBirdCheckinPage.isDisplayed()
        earlyBirdCheckinPage.clickSubmitButton()
        earlyBirdPurchasePage.verifyCreditCard()
    }

    @Then ( "I see the Early Bird Check-in Confirmation Page" )
    def iSeeTheEarlyBirdCheckInConfirmationPage() {
        earlyBirdConfirmationPage.confirmPurchaseSuccessfulMessage()
    }

    @Then ( "I see the seat selection button on the Early Bird Check-in Confirmation Page" )
    def iSeeTheSeatSelectionButtonOnTheEarlyBirdCheckInConfirmationPage() {
        earlyBirdConfirmationPage.verifyAirTranSeatSelectionButton()
    }

    @Then( "I do not see the seat selection button on the Early Bird Check-in Confirmation Page" )
    def iDoNotSeeTheSeatSelectionButtonOnTheEarlyBirdCheckInConfirmationPage() {
        earlyBirdConfirmationPage.verifyAirTranSeatSelectionButtonNotDisplayed()
    }
}
