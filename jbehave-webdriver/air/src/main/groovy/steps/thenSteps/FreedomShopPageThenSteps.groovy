package steps.thenSteps

import org.jbehave.core.annotations.Then

import pages.FreedomShopPage


class FreedomShopPageThenSteps {

    FreedomShopPage freedomShopPage

    @Then("I see the Southwest Freedom Shop home page")
    def verifyOnSouthwestFreedomShopHomePage() {
        freedomShopPage.verifyOnSouthwestFreedomShopHomePage()
    }
}