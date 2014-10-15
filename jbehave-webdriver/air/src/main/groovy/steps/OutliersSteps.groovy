package steps

import domain.AirReservation
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Given
import pages.ChangeAirReservationPage
import state.ScenarioState

class OutliersSteps {

    ScenarioState scenarioState
    ChangeAirReservationPage changeAirReservationPage

    @Given("I have missed the outbound flight of my itinerary")
    def createMissedFlightReservation() {
        AirReservation myCurrentPNR = new DynaStubsIntegration().createNoShowedReservation()
        updateCurrentPNR(myCurrentPNR)
    }

    @Given("I have missed the outbound flight of my itinerary and it is in progress")
    def createFlightInProgressReservation() {
        AirReservation myCurrentPNR = new DynaStubsIntegration().createNoShowedReservationForFlightInProgress()
        updateCurrentPNR myCurrentPNR
    }

    @Given("I have checked in for my SWABiz itinerary and it is in progress")
    def createSWABizFlightInProgressReservation() {
        AirReservation myCurrentPNR = new DynaStubsIntegration().createSwabizReservationForFlightInProgress()
        updateCurrentPNR myCurrentPNR
    }

    private void updateCurrentPNR(AirReservation anotherPNR) {
        scenarioState.add(anotherPNR)
    }
}
