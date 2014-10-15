package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.ConfirmationEmailSubscriptionPage

class EmailSubscriptionThenSteps {

    ConfirmationEmailSubscriptionPage confirmationEmailSubscriptionPage

    @Then("I see a message which confirms my Click 'N Save subscription")
    def verifyCNSSubscription(){
        confirmationEmailSubscriptionPage.verifyPage()
        confirmationEmailSubscriptionPage.verifyCnsSubscriptionMessage()
    }

    @Then("I see a message which confirms my In a Nutshell subscription")
    def verifyIANSubscription(){
        confirmationEmailSubscriptionPage.verifyPage()
        confirmationEmailSubscriptionPage.verifyIanSubscriptionMessage()
    }
}
