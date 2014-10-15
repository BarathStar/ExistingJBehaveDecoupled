package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.AdvertiseWithSouthwestPage

class AdvertiseWithSouthwestPageThenSteps {

    AdvertiseWithSouthwestPage advertiseWithSouthwestPage

    @Then("I see the Audience Targeting with Southwest page")
    void verifyAirportInformationPage() {
        advertiseWithSouthwestPage.verifyAirportInformationPage()

    }

}
