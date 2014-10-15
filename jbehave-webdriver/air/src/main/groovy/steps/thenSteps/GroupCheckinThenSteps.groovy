package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.GroupCheckinPage
import state.Flow

public class GroupCheckinThenSteps {

    GroupCheckinPage groupCheckinPage
    Flow flow

    @Then("I see all the selected passengers under the Checkin Results section")
    def verifySelectedPassengersForCheckin() {
        groupCheckinPage.verifySelectedPassengersForCheckin()
    }

    @Then("I see the non-selected passengers available for checkin online")
    def verifyNotSelectedPassengersForCheckin() {
        groupCheckinPage.verifyNotSelectedPassengersForCheckin()
    }

    @Then("I see an error message next to each passenger who failed to checkin")
    def verifyPassengerErrorMessageWhoFailedToCheckIn() {
        groupCheckinPage.verifyPassengerErrorMessageWhoFailedToCheckIn()
    }

    @Then("I should not see any oops message since there were not three fails in a row")
    def verifyOopsMessageNotPresent() {
        groupCheckinPage.verifyOopsMessageNotPresent()
    }

    @Then("I see an oops message since there were three fails in a row")
    def verifyOopsMessagePresent() {
        groupCheckinPage.verifyOopsMessagePresent()
    }

    @Then("I see all the passengers without errors in their checkin under the Checkin Results section")
    def verifyExcludedPassengerIsUnderCheckinResultSection() {
        groupCheckinPage.verifyExcludedPassengerIsUnderCheckinResultSection(flow.exludedPassengerForCheckin)
    }

    @Then("I see the passengers with errors available for checkin online")
    def verifyPassengerWithErrorsAvailableForCheckinOnline() {
        groupCheckinPage.verifyPassengerErrorMessageWhoFailedToCheckIn()
        groupCheckinPage.verifyPassengersWhitErrorsNotShowsUnderCheckInResults()
    }

    @Then("I see a message which informs me that the passenger(s) require airport checkin for the outbound and inbound flight")
    def verifyMessageAboutOnePassengerRequiresAirportCheckIn() {
        groupCheckinPage.verifyMessageAboutPassengerRequiresAirportCheckIn()
    }
}