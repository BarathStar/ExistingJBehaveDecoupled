package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.SwaInternationalSelectFlightPage
import state.Flow
import state.ScenarioState
import util.AlteaUrlValidationHelper

class SwaInternationalSelectFlightThenSteps {
    SwaInternationalSelectFlightPage swaInternationalSelectFlightPage;
    Flow flow
    ScenarioState scenarioState

    @Then("Verify that correct redirect url is created for Altea web site")
    def isDisplayed() {
        swaInternationalSelectFlightPage.getCurrentUrl()?. shouldContain "DATE_1=", "Path should contain required query parameter - DATE_1."
        swaInternationalSelectFlightPage.getCurrentUrl()?. shouldContain "TRIP_TYPE=", "Path should contain required query parameter - TRIP_TYPE."
        swaInternationalSelectFlightPage.getCurrentUrl()?. shouldContain "B_LOCATION_1=", "Path should contain required query parameter - B_LOCATION_1."
        swaInternationalSelectFlightPage.getCurrentUrl()?. shouldContain "E_LOCATION_1=", "Path should contain required query parameter - E_LOCATION_1."
        swaInternationalSelectFlightPage.getCurrentUrl()?. shouldContain "NB_ADT=", "Path should contain required query parameter - NB_ADT."
        swaInternationalSelectFlightPage.getCurrentUrl()?. shouldContain "NB_YCD=", "Path should contain required query parameter - NB_YCD."
    }

    @Then("I am redirected to the Altea Check In page")
    def verifyAlteaCheckInPage() {
        swaInternationalSelectFlightPage.waitForElement(AlteaUrlValidationHelper.PROXY_RESPONSE)
        AlteaUrlValidationHelper.verifyCheckInLink(swaInternationalSelectFlightPage.getCurrentUrl(), scenarioState.getLastAirReservation(), flow.isRapidRewards)
    }
}
