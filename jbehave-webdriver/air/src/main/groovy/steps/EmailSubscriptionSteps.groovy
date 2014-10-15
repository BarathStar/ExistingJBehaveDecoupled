/* Copyright (c) 2012, Southwest Airlines Co.  All rights reserved.*/
package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.RRUser

import pages.CnsEmailUpdatesPage
import pages.IanEmailUpdatesPage
import state.AccountManagementData
import state.Flow
import domain.RapidRewardsAccount
import fixture.stubs.DynaStubsIntegration
import pages.ConfirmationEmailSubscriptionPage

class EmailSubscriptionSteps {

    CnsEmailUpdatesPage cnsEmailUpdatesPage
    IanEmailUpdatesPage ianEmailUpdatesPage
    ConfirmationEmailSubscriptionPage confirmationEmailSubscriptionPage
    AccountManagementData accountManagementData
    Data data
    Flow flow

    @Given("I am a Rapid Rewards Member not logged-in located in the page Click 'N Save Email Updates")
    def createRRMemberForCns() {
        createRRMember()
        cnsEmailUpdatesPage.open()
        cnsEmailUpdatesPage.verifyPage()
    }

    @Given("I am a Rapid Rewards Member not logged-in located in the page In A Nutshell Email Updates")
    def createRRMemberForIan() {
        createRRMember()
        ianEmailUpdatesPage.open()
        ianEmailUpdatesPage.verifyPage()
    }

    @When("I subscribe to \$mailingList using an address email and the data from my account")
    def subscribeToMailingList(String mailingList) {
        RapidRewardsAccount user = new RapidRewardsAccount(flow.getRrUser())
        accountManagementData.generateEmailAndToken(mailingList)
        cnsEmailUpdatesPage.fillAndSubmitEmailUpdatesForm(user)
        confirmationEmailSubscriptionPage.verifyPage()
    }

    private createRRMember() {
        RRUser rrUser = data.getUser("withAwards")
        flow.setRrUser(rrUser)
        if (DynaStubsIntegration.useDynaStubs()) {
            new DynaStubsIntegration().createRRMember(rrUser)
        }
    }
}