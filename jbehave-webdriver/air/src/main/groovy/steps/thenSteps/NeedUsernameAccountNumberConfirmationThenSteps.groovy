/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package steps.thenSteps

import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.Composite
import org.jbehave.core.annotations.Alias
import domain.RapidRewardsAccount
import pages.NeedUsernameAccountNumberConfirmationPage

/**
 * This class represent the Steps for the Security Questions page for GetHealthy project
 */

class NeedUsernameAccountNumberConfirmationThenSteps {

    NeedUsernameAccountNumberConfirmationPage usernameAccountNumberPage

    @Then("I see the username/account number confirmation page")
    def seeUsernameAccountNumberConfirmation() {
        usernameAccountNumberPage.verifyCurrentPageLocation()
        usernameAccountNumberPage.verifyConfirmationMessages(RapidRewardsAccount.createRapidRewardsUser())
    }
}
