package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.SearchCarsPage

class CarSearchThenSteps {

    SearchCarsPage searchCarsPage

    @Then("I should see my selections populated on the select car page")
    void verifyCarBannerDataPopulated() {
        searchCarsPage.verifyDataPopulated()
    }
}
