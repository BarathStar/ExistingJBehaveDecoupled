package steps.thenSteps

import pages.AlreadyPurchasedTripPage
import org.jbehave.core.annotations.Then

class AlreadyPurchasedTripThenSteps {

    AlreadyPurchasedTripPage alreadyPurchasedTripPage;

    @Then("I should see the Already Purchased Trip page")
    def verifyInAlreadyPurchasedTripPage() {
        alreadyPurchasedTripPage.verifyPage()
    }
}
