/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package steps

import domain.RapidRewardsAccount
import org.jbehave.core.annotations.When
import pages.SecurityQuestionsPage

/**
 * This class represent the Steps for the Security Questions page for GetHealthy project
 */

class SecurityQuestionsSteps {

    SecurityQuestionsPage securityQuestionsPage

    @When("I complete the Security Question page")
    def completeSecurityQuestionPage() {
        securityQuestionsPage.fillForm(RapidRewardsAccount.createRapidRewardsUser())
        securityQuestionsPage.clickSubmit()
    }
}
