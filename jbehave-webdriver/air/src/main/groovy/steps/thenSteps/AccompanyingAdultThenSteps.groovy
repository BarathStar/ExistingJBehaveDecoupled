package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.AccompanyingAdultPage

class AccompanyingAdultThenSteps {
    AccompanyingAdultPage accompanyingAdultPage

    @Then("I see the Oops message indicating that the mandatory fields were not entered")
    void verifyAccompanygTravelerFormMandatoryFields() {
        accompanyingAdultPage.verifyAccompanygTravelerFormMandatoryFields()
    }

    @Then("I see the Oops message indicating that the itinerary retrieved does not match the current itinerary")
    void verifyItineraryNotMatchCurrentItinerary() {
        accompanyingAdultPage.verifyItineraryNotMatchCurrentItinerary()
    }

    @Then("I see the matching itinerary on the Accompanying Traveler form")
    void verifyAccompayingItinerarySection() {
        accompanyingAdultPage.verifyAccompayingItinerarySection()
    }
}
