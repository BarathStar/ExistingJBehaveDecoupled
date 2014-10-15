/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package steps.thenSteps

import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.Composite
import org.jbehave.core.annotations.Alias
import domain.RapidRewardsAccount
import pages.NeedPasswordConfirmationPage

/**
 * This class represent the Steps for Need Password Confirmation Page for GetHealthy project
 */

class NeedPasswordConfirmationThenSteps {

    NeedPasswordConfirmationPage needPasswordConfirmationPage

    @Then("I see the password reset confirmation page")
    def seeNeedPasswordConfirmationPage() {
        needPasswordConfirmationPage.verifyCurrentPageLocation()
        needPasswordConfirmationPage.verifyConfirmationMessages()
    }
}
