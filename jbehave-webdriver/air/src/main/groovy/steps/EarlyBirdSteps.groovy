package steps

import domain.AirReservation
import domain.Passenger
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Then
import pages.EarlyBirdPurchaseFlow
import pages.EarlyBirdPurchasePage
import pages.SwaBizPage;
import state.ScenarioState

class EarlyBirdSteps {

    ScenarioState scenarioState

    EarlyBirdPurchaseFlow earlyBirdCheckinPage
    EarlyBirdPurchasePage earlyBirdPurchasePage
    SwaBizPage swaBizPage

    @Given("I am on the early bird page")
    @When("I am on the early bird page")
    def selectEarlyBirdFromHomePage() {
        earlyBirdCheckinPage.open()
        earlyBirdCheckinPage.verifyPage()
    }

    @Given("I retrieve itinerary on the Early Bird check-in page")
    @When("I retrieve itinerary on the Early Bird check-in page")
    def retrievePNRForEarlyBirdCheckIn() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        earlyBirdCheckinPage.retrievePNRForEarlyBirdCheckIn(airReservation.adultPnr, passenger.firstName, passenger.lastName)
        earlyBirdCheckinPage.clickSubmitButton()

    }
    @When("I try to retrieve itinerary on the Early Bird check-in page")
    def retrievePNRForEarlyBirdCheckInWithoutVerify() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        earlyBirdCheckinPage.retrievePNRForEarlyBirdCheckIn(airReservation.adultPnr, passenger.firstName, passenger.lastName)
        earlyBirdCheckinPage.clickSubmitButtonWithoutVerifyPage()

    }

    @When("I click on \$link link on EarlyBird page")
    def clickOnLinkEarlyBirdPage(String link) {
        earlyBirdCheckinPage.clickOnLinkInEarlyBird(link)
    }

    @When("I purchase earlybird for my flight")
    def purchaseEarlyBirdForExistingReservation() {
        earlyBirdCheckinPage.clickSubmitButton()
        earlyBirdPurchasePage.fillCreditCardInfo()
    }

    @When("I purchase earlybird for my flight with UATP Credit Card")
    def purchaseEarlyBirdForExistingReservationUsingUATP() {
        earlyBirdCheckinPage.clickSubmitButton()
        earlyBirdPurchasePage.fillCreditCardUATPInfo()
    }

    @When("I attempt to retrieve an itinerary using an invalid PNR on the EarlyBird Check-in Page")
    def attemptToRetrieveReservationWithInvalidPnrOnEarlyBirdCheckinPage() {
        earlyBirdCheckinPage.retrievePNRForEarlyBirdCheckIn("ABC123", "Sam", "Leapfrog")
        earlyBirdCheckinPage.clickSubmitButton()
    }

	@Given("I am on the early bird page on Swabiz")
    @When("I am on the early bird page on Swabiz")
    def selectSwabizEarlyBirdFromHomePage() {
        swaBizPage.openEarlyBirdPage()
        earlyBirdCheckinPage.verifyPage()
    }

    @Then("I view the message for airtran disclaimer")
    def pageMessage() {
        earlyBirdCheckinPage.verifyAirtranDisclaimerMessage()
    }

    @When( "I retrieve my reservation through Early Bird" )
    def iRetrieveMyReservationThroughEarlyBird() {
        earlyBirdCheckinPage.open()
        retrievePNRForEarlyBirdCheckIn()
    }

    @When( "I purchase an Early Bird Check-in" )
    def iPurchaseAnEarlyBirdCheckIn() {
        earlyBirdCheckinPage.clickSubmitButton()
        earlyBirdPurchasePage.fillCreditCardInfo()
    }
}