package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.ShortcutSelectFlightsPage
import util.ItineraryData

class ShortcutSelectFlightsThenSteps {

    ShortcutSelectFlightsPage shortcutSelectFlightsPage
    ItineraryData itineraryData

    @Then("I should see the Shortcut Select Flight page")
    def verifyShortcutSelectFlightsPage() {
        shortcutSelectFlightsPage.verifyIamOnShortcutSelectFlightsPage()
    }

    @Then("I click continue button in the Shortcut Select Flight page")
    def clickContinueButton() {
        shortcutSelectFlightsPage.clickContinueButton()
    }

    @Then("I verify that departure month has not changed")
    def verifyDepartureMonthNotChanged() {
        shortcutSelectFlightsPage.verifyMonthPosition(itineraryData.departureDate, "DEPARTURE").shouldBe true, "Departure month should not change when selecting a later one for return."
    }

    @Then("I verify that return month has not changed")
    def verifyReturnMonthNotChanged() {
        shortcutSelectFlightsPage.verifyMonthPosition(itineraryData.returnDate, "RETURN").shouldBe true, "Return month should not change when selecting a later one for departure."
    }

    @Then("I verify that return month is the same as my departure month")
    def verifySameReturnAndDepartureMonths() {
        shortcutSelectFlightsPage.verifyDepartureAndReturnOnSameMonth().shouldBe true, "Departure and return months should be the same when changing departure month."
    }

    @Then("I see an invalid session message")
    def verifyInvalidSessionMessage() {
        shortcutSelectFlightsPage.verifyInvalidSessionMessage();
    }
    @Then("I should see schedule not started until \$oopsMsg")
    def scheduleNotStartedUntil (String oopsMsg) {
        shortcutSelectFlightsPage.scheduleNotStartedUntil(oopsMsg)

    }

    @Then("I should see the fare type toggle with dollars-points-certificates options")
    def verifyFareTypeToggleWithCertificates() {
        shortcutSelectFlightsPage.verifyFareTypeToggleWithCertificates()
    }

    @Then("I should see the fare type toggle with dollars-points options")
    def verifyFareTypeToggle() {
        shortcutSelectFlightsPage.verifyFareTypeToggle()
    }
}
