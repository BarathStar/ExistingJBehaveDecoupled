/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package steps

import org.jbehave.core.annotations.Then
import pages.SiteMapPage

class SiteMapThenSteps {

    SiteMapPage siteMapPage

    @Then("I should see the section title as 'Vacations'")
    def verifyVacationsSectionTitle() {
        siteMapPage.verifyVacationsSectionTitle()
    }
}
