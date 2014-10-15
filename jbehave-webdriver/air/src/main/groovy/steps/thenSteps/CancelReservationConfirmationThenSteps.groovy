package steps.thenSteps

import domain.Passenger
import domain.AirReservation
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.Alias
import pages.CancelReservationConfirmationPage
import pages.elements.RapidRewardsAccountBar
import state.ScenarioState
import state.Flow
import util.CustomerInfoData

class CancelReservationConfirmationThenSteps {

    CancelReservationConfirmationPage cancelReservationConfirmationPage
    ScenarioState scenarioState
    Flow flow
    RapidRewardsAccountBar rapidRewardsAccountBar
    CustomerInfoData customerInfoData

    @Then("I view the flight cancellation confirmation")
    @Alias("I see an informative message which states that my reservation has been cancelled on the Cancel Confirmation Page")
    def viewCancellationConfirmation() {
        cancelReservationConfirmationPage.verifyCancellation()
    }

    @Then("I see funds been held for future use")
    void viewTravelFundsHelpForFutureUse(){
        cancelReservationConfirmationPage.verifyFundsHeldForFutureUse()
    }

	@Then('I should not see any associated product on the Cancel Reservation Confirmation Page')
	def viewRemainingItemsVisible() {
		cancelReservationConfirmationPage.viewRemainingItemsVisible()
	}

	@Then("I should not see the Car Product as an associated item")
	def verifyCarProductAssociatedItemNotDisplayed() {
		cancelReservationConfirmationPage.verifyCarProductAssociatedNotDisplayed()
	}

    @Then("I should see the Car Product as an associated item")
    @Alias("I see the car product as an item associated to my named trip on the Cancel Confirmation Page")
    def verifyCarProductAssociatedItemIsDisplayed() {
        cancelReservationConfirmationPage.verifyCarProductAssociatedIsDisplayed()
    }

    @Then("I should not see the hotel product as an associated product on the Cancel Confirmation Page")
    void verifyHotelProductInformationNotDisplayed() {
        cancelReservationConfirmationPage.verifyIsHotelInfoNotDisplayed()
    }

    @Then("I should not be able to rename the trip")
    void verifyTripNameAndRenameButtonNotDisplayed() {
        cancelReservationConfirmationPage.verifyRenameButtonDisable()
    }

	@Then("I see on the Cancel Confirmation Page that points will be returned to passenger's account")
	def verifyPointsRecipient() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
		Passenger passenger = airReservation.getAdultPassengers().get(0)
		cancelReservationConfirmationPage.passengerFirstName().toUpperCase().shouldBe passenger.firstName.toUpperCase(), "Expected passenger firstname to be $passenger.firstName"
		cancelReservationConfirmationPage.passengerLastName().toUpperCase().shouldBe passenger.lastName.toUpperCase(), "Expected passenger name to be $passenger.lastName"
	}

    @Then('I see my air product on the Air Cancellation page')
    void verifyIAmOnTheCancelReservationConfirmationPage() {
        cancelReservationConfirmationPage.verifyCurrentPageLocation()
    }

    @Then("I verify that my car reservation has been cancelled")
    @Alias("I see an informative message which states that my Car reservation has been cancelled on the Cancel Confirmation Page")
    def verifyCarCancellationMessageIsDisplayed() {
        cancelReservationConfirmationPage.verifyPage()
        cancelReservationConfirmationPage.verifyCarCancellationMessageIsDisplayed()
    }

    @Then("I see the Air Products as items associated to my named trip on the Cancel Confirmation Page")
    void verifyAssociatedAirProductsToTheTripOnCancelConfirmationPage() {
        cancelReservationConfirmationPage.verifyTripAssociatedItemsVerbiage()
        cancelReservationConfirmationPage.verifyTripName(flow.tripName)
        cancelReservationConfirmationPage.verifyAirProductsAssociatedCount(2)
    }

    @Then("I see the information of my Car on the Cancel Confirmation Page")
    def verifyCarItineraryInformation() {
        cancelReservationConfirmationPage.verifyCarItineraryInformation()
    }

    @Then("I see the Air Product as associated item of my named trip on the Cancel Confirmation Page")
    def verifyAssociatedAirProduct() {
        cancelReservationConfirmationPage.verifyAssociatedAirProduct()
    }

    @Then("I see the Cancellation Confirmation Page")
    def seeCancellationConfirmation() {
        cancelReservationConfirmationPage.verifyPage()
        if (flow.isRapidRewards) {
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                verifyTier()
                verifyRRacountNumber()
                if (flow.isRapidRewardsPointsPurchaseOnly) {
                    verifyAvailablePointsAfterCancel()
                }
            }
        }
        cancelReservationConfirmationPage.with {
            verifyTopPageLinks()
            if (!DynaStubsIntegration.useDynaStubs()) {
                verifyTravelFundsInformationTable()
                if(flow.isRapidRewardsPointsPurchaseOnly) {
                    verifyReturnedPointsDetail()
                }
            }
            verifyBookAFlightButton()
        }
        customerInfoData.formOfPayment = null
        viewCancellationConfirmation()
    }

    @Then("I see a message which informs that \$awardsCount award(s) will be returned to the corresponding rapid rewards account")
    def seeMessageAboutAwardsReturned(int awardsCount) {
        cancelReservationConfirmationPage.verifyAwardsReturnedInformation(awardsCount)
    }
}