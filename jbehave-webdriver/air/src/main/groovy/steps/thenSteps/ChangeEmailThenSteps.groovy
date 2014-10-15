package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.ChangeEmailPage

class ChangeEmailThenSteps {
    ChangeEmailPage changeEmailPage

    @Then("I see the Old e-mail page")
    def seeOldEmailPage() {
        changeEmailPage.verifyOldEmailPage()
    }

    @Then("I see the New e-mail page")
    def seeNewEmailPage() {
        changeEmailPage.verifyNewEmailPage()
    }

    @Then("I see the Change e-mail confirmation page")
    def seeChangeEmailConfirmationPage() {
        changeEmailPage.verifyConfirmationMessages()
    }
}
