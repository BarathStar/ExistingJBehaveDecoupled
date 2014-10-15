package steps

import org.jbehave.core.annotations.When
import pages.AirTranRedirectModal
import pages.WebReferralPage
import pages.elements.FlightSearchForm
import util.ItineraryData

class WebReferralSteps {

    AirTranRedirectModal airTranRedirectModal
    ItineraryData itineraryData
    FlightSearchForm flightSearchForm

    @When("I view the Web Referral Redirect Modal for \$typeOfTrip")
    def viewWebReferralRedirectModal(String typeOfTrip) {
        airTranRedirectModal.viewAirTranRedirectModalWithWcmContent()
        airTranRedirectModal.determineTripType(typeOfTrip)
    }

}
