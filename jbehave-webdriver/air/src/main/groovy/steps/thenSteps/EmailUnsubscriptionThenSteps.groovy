package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.NonRREmailUnsubscriptionPage
import state.AccountManagementData

class EmailUnsubscriptionThenSteps {

    NonRREmailUnsubscriptionPage nonRREmailUnsubscriptionPage
    AccountManagementData accountManagementData

    @Then("I see a message which informs me that my e-mail was removed from the Click 'N Save mailing list")
    def verifyCNSunsubscription(){
        nonRREmailUnsubscriptionPage.verifyCnsUnsubscriptionMessage(accountManagementData.emailAddress)
    }

    @Then("I see a message which informs me that my e-mail was removed from the In a Nutshell mailing list")
    def verifyIANunsubscription(){
        nonRREmailUnsubscriptionPage.verifyIanUnsubscriptionMessage(accountManagementData.emailAddress)
    }

    @Then("I see a message which informs me that my e-mail is not included in the Click 'N Save mailing list")
    def errorUnsubscribeTwice() {
        nonRREmailUnsubscriptionPage.verifyCnsUnsubscriptionTwiceErrorMessage(accountManagementData.emailAddress)
    }
}