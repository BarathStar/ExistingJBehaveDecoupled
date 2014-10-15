package steps.thenSteps

import pages.SelectFlightsToUpgradePage
import org.jbehave.core.annotations.Then


class SelectFlightsToUpgradeThenSteps {

    SelectFlightsToUpgradePage selectFlightsToUpgradePage

    @Then("I see \"Select Flights to Upgrade\" page")
    def seeSelectFlightsToUpgradePage() {
        selectFlightsToUpgradePage.verifyPage()
    }

    @Then("I verify that I can upgrade my segments")
    def canIUpgradeMySegments() {
        selectFlightsToUpgradePage.verifyBothSegmentsAreUpgradable()
    }

    @Then("I should see the seat fees verbiage")
    def verifySeatFeesVerbiageExists() {
        selectFlightsToUpgradePage.verifySeatFeesVerbiage(true)
    }

    @Then("I should not see the seat fees verbiage")
    def verifySeatFeesVerbiageNotExist() {
        selectFlightsToUpgradePage.verifySeatFeesVerbiage(false)
    }
}
