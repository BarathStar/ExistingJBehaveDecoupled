package steps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.When
import pages.NonRREmailUnsubscriptionPage
import pages.RapidRewardsUnsubscribePage
import state.AccountManagementData

class EmailUnsubscriptionSteps {

    NonRREmailUnsubscriptionPage nonRREmailUnsubscriptionPage
    RapidRewardsUnsubscribePage rapidRewardsUnsubscribePage
    AccountManagementData accountManagementData

    @When("I confirm to unsubscribe from the mailing list")
    @Alias("I confirm to unsubscribe from the mailing list again")
    def unsubscribeToEmailList() {
        nonRREmailUnsubscriptionPage.openWithParams(accountManagementData)
        nonRREmailUnsubscriptionPage.clickUnsubscribeButton()
        nonRREmailUnsubscriptionPage.verifyPage()
    }

    @When("I confirm to unsubscribe the email address \$email from the \$mailingList mailing list")
    @Alias("I confirm to unsubscribe the email address \$email from the \$mailingList mailing list again")
    def unsubscribeMailFromMailingList(String email, String mailingList) {
        accountManagementData.setAccountData(mailingList, email)
        rapidRewardsUnsubscribePage.openWithParams(accountManagementData)
        rapidRewardsUnsubscribePage.clickUnsubscribeButton()
        rapidRewardsUnsubscribePage.checkSomethingServed()
    }
}