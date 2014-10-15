/* Copyright (c) 2014, Southwest Airlines Co.  All rights reserved.*/
package steps


import org.jbehave.core.annotations.Given

import pages.SwabizSearchHotelsPage


class SwabizHotelSearchSteps{
    SwabizSearchHotelsPage swabizSearchHotelsPage

    @Given("I am on the SWABiz hotel reservation page")
    def openSwabizHotelPage(){
        swabizSearchHotelsPage.open()
    }
}