/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package steps

import pages.SwabizTravelToolsPage
import org.jbehave.core.annotations.When

class SwabizTravelToolsSteps {

    SwabizTravelToolsPage swabizTravelToolsPage

    @When("I go to the SWABiz Travel Tools page")
    def openSwabizTravelToolsPage() {
        swabizTravelToolsPage.open()
    }
}
