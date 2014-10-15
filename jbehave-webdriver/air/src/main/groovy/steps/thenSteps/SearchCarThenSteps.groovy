package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.SearchCarsPage

class SearchCarThenSteps {

    SearchCarsPage searchCarsPage

    @Then("I verify that what I chosen it is selected")
    def verifyChosenIsSelected() {
        searchCarsPage.verifyLocationSelected()
    }
}
