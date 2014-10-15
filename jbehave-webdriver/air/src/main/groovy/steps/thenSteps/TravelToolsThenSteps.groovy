/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package steps.thenSteps

import pages.TravelToolsPage
import org.jbehave.core.annotations.Then

class TravelToolsThenSteps {

    TravelToolsPage travelToolsPage

    @Then("I see the section title text as 'Vacations'")
    def verifyVacationsSectionTitle() {
        travelToolsPage.verifyVacationsSectionTitle()
    }
}