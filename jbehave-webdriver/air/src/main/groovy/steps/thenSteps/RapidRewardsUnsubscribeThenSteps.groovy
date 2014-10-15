package steps.thenSteps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Then
import pages.RapidRewardsUnsubscribePage
import state.AccountManagementData

class RapidRewardsUnsubscribeThenSteps {

    RapidRewardsUnsubscribePage rapidRewardsUnsubscribePage
    AccountManagementData accountManagementData

    @Then("I see a message which informs me that my e-mail was removed from the Rapid Rewards Email Update mailing list")
    def verifyEmailRemovedFromRapidRewardsEmailListMailingList() {
        rapidRewardsUnsubscribePage.verifyRapidRewardsEmailUpdateUnsubscriptionMessage(accountManagementData.emailAddress)
    }

    @Then("I see a message which informs me that my e-mail was removed from the Rapid Rewards Report mailing list")
    def verifyEmailRemovedFromRapidRewardsReportMailingList() {
        rapidRewardsUnsubscribePage.verifyRapidRewardsReportUnsubscriptionMessage(accountManagementData.emailAddress)
    }

    @Then("I see a message which informs me that my e-mail is not included in the Rapid Rewards Report mailing list")
    def verifyEmailNotInFromRapidRewardsReportMailingList() {
        rapidRewardsUnsubscribePage.verifyRapidRewardsReportDoubleUnsubscriptionMessage(accountManagementData.emailAddress)
    }
}
