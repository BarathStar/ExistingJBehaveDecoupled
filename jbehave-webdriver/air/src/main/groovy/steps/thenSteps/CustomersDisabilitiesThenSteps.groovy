/*
 * Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.
 */

package steps.thenSteps

import pages.CustomersDisabilitiesPage
import org.jbehave.core.annotations.Then

class CustomersDisabilitiesThenSteps {

    CustomersDisabilitiesPage customersDisabilities

    @Then("I see the Customers with Disabilities page with Assistant Animals tab selected")
    def verifyAssistantAnimalsSection(){
        customersDisabilities.verifyAssistantAnimalsSection()
    }

}
