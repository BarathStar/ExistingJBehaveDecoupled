package steps

import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.elements.CarCancellationModal
import state.Flow
import state.ScenarioState

class CarCancelSteps {

    CarCancellationModal carCancellationModal
    CarConfirmationSteps carConfirmationSteps
    ScenarioState scenarioState
    Flow flow

    @Given("I have cancelled the car product which belongs to my trip")
    def cancelCarProduct() {
        if (DynaStubsIntegration.useDynaStubs()) {
            new DynaStubsIntegration().cancelCarReservation(scenarioState.getLastAirReservation().carReservation)
            flow.isCarReservationPresent = false
        } else {
            carConfirmationSteps.clickCancelOnCarConfirmation()
            confirmCarCancellation()
        }
    }

    @When("I accept the car cancellation")
    def confirmCarCancellation() {
        carCancellationModal.acceptCancellation()
    }
}
