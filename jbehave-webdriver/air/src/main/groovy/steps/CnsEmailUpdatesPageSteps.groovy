/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package steps

import org.jbehave.core.annotations.When
import pages.CnsEmailUpdatesPage
import org.jbehave.core.annotations.Given

public class CnsEmailUpdatesPageSteps {

    CnsEmailUpdatesPage cnsEmailUpdatesPage

    @Given("I go to the Click 'N Save Email Updates page")
    @When("I go to the Click 'N Save Email Updates page")
    def openCnsEmailUpdatesPage() {
        cnsEmailUpdatesPage.clickSingUpAndSave()
    }

    @When("I complete the Click 'N Save form with an invalid email and submit")
    def fillFormInvalidEmail(){
        cnsEmailUpdatesPage.fillAndSubmitEmailUpdatesFormWithInvalidEmail()
    }
}