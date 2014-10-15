/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.AccountLogInPage

/**
 * This class represent the Steps for Account Log In Page
 */

class AccountLogInThenSteps {

    AccountLogInPage accountLogInPage

    @Then("I see the My Account Login page")
    def seeAccountLogInPage() {
        accountLogInPage.verifyCurrentPageLocation()
    }
}
