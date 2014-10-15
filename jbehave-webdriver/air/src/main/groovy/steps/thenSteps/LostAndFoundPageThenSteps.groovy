package steps.thenSteps

import org.jbehave.core.annotations.Then

import pages.LostAndFoundPage


class LostAndFoundPageThenSteps {

    LostAndFoundPage lostAndFoundPage

    @Then("I see Lost and Found page")
    def verifyOnSouthwestFreedomShopHomePage() {
        lostAndFoundPage.verifyOnLostAndFoundPage()
    }
}