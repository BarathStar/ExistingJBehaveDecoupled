package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.GetawayFinderFlightPage

class GetawayFlightPageThenSteps {

    GetawayFinderFlightPage getawayFinderFlightPage

    @Then("I am redirected to Getaway Flight section page on a new window")
    void openGetawayPageInAnotherWindow() {
        getawayFinderFlightPage.verifyGetawayFinderUrl()
    }

    @Then("I am redirected to Getaway Flight section page with Omniture Tag on a new window")
    void openGetawayPageWithOmnitureTagInAnotherWindow() {
        getawayFinderFlightPage.verifyGetawayFinderUrlWithOmnitureTag()
    }
}