/*
 * Copyright (c) 2014, Southwest Airlines Co.  All rights reserved.
 */
package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.CharterPage

class CharterServiceThenSteps {

    CharterPage charterPage

    @Then("I should see a message saying that the Charter Service Request Form was successfully sent")
    def verifyCharterServiceConfirmationPage() {
        charterPage.verifyTitle()
    }
}
