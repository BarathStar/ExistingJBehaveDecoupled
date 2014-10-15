package steps.thenSteps

import pages.AlreadyPurchasedTripPage
import org.jbehave.core.annotations.Then
import pages.AlreadyEnrolledAccountPage

class AlreadyEnrolledAccountThenSteps {

    AlreadyEnrolledAccountPage alreadyEnrolledAccountPage;

    @Then("I should see the Already Enrolled Account page")
    def verifyInAlreadyPurchasedTripPage() {
        alreadyEnrolledAccountPage.verifyPage()
    }
}
