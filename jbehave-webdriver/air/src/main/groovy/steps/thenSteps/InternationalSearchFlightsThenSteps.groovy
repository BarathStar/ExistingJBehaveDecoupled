package steps.thenSteps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Then
import pages.InternationalSearchFlightsPage

class InternationalSearchFlightsThenSteps {

    InternationalSearchFlightsPage internationalSearchFlightsPage

    @Then("I verify \$field is cleared")
    @Alias("I verify \$fields are cleared")
    def verifyFieldCleared(List<String> field) {
        internationalSearchFlightsPage.verifyFieldCleared(field)
    }
}
