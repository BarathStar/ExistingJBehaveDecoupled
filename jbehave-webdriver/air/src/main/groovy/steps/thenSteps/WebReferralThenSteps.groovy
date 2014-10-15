package steps.thenSteps

import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.Alias
import pages.WebReferralPage
import pages.elements.FlightSearchForm

class WebReferralThenSteps {

    WebReferralPage webReferralPage
    FlightSearchForm flightSearchForm

    @Then("I verify I am on the Marketingpage")
    void verifyInternationalPartnerRoutesPage(){
        webReferralPage.verifyInternationalPartnerRoutesPage()
    }

    @Then ("the station selections on marketing page are prepopulated")
    void marketingPageStationSelectionsArePrepopulated() {
        flightSearchForm.verifyStationsArePrePopulated()
    }
}
