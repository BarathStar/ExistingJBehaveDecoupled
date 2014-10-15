/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package steps

import org.jbehave.core.annotations.Given

import pages.SwabizSearchCarsPage

class SwabizCarSearchSteps {

    SwabizSearchCarsPage swabizSearchCarsPage

    @Given("I am on the SWABiz car reservation page")
    def openSwabizCarPage() {
        swabizSearchCarsPage.open()
    }
}
