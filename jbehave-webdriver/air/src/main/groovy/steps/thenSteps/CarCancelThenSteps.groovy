package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.elements.CarCancellationModal
import state.Flow

class CarCancelThenSteps {

    CarCancellationModal carCancellationModal
    Flow flow

    @Then("I should see the single car product cancellation modal")
    def checkSingleCarCancellationModal() {
        carCancellationModal.verifyItIsDisplayed()
    }

    @Then("I should see the multiple product car cancellation modal")
    void checkMultiProductCarCancellationModal() {
        carCancellationModal.verifyMultiProductCarCancellationModal()
    }

    @Then("I see that the Associated Products Modal shows separate Air products")
    void verifyAssociatedAirProductsToTheTripOnCarReservationPage() {
        carCancellationModal.verifyModalTitle()
        carCancellationModal.verifyAirProductsAssociatedCount(2)
    }
}
