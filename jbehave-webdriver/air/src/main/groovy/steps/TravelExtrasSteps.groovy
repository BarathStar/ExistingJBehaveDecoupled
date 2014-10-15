package steps

import pages.TravelExtrasPage
import org.jbehave.core.annotations.*

class TravelExtrasSteps {

    TravelExtrasPage travelExtrasPage

    @When("I click on the southwest gift card breadcrumb on the Travel Extras Page")
    def clickSouthwestGiftCardBreadcrumb(){
        travelExtrasPage.clickOnSouthwestGiftCardLink()
    }
}