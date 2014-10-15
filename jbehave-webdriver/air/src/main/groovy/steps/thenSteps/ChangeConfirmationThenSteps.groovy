package steps.thenSteps

import pages.ItineraryChangeConfirmationPage
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.Alias

class ChangeConfirmationThenSteps {

    ItineraryChangeConfirmationPage itineraryChangeConfirmationPage

    @Then("I should see the itinerary change confirmation page")
    def viewChangeConfirmation() {
        itineraryChangeConfirmationPage.verifyPage()
        itineraryChangeConfirmationPage.verifyPNRs()

    }

    @Then("I should see the itinerary change confirmation page that has RR number displayed")
    def viewChangeConfirmationWithRRDisplayed() {
        itineraryChangeConfirmationPage.verifyLoginRRNumber()
    }

    @Then("I see that the Air product just changed has the name \$tripName on the Change Confirmation Page")
    @Alias("I see on the Change Confirmation Page that the Air product just changed has \$tripName as name")
    void airBookingHasATripNameChangeOnConfirmation(String tripName) {
        itineraryChangeConfirmationPage.verifyNewTripNameExists(tripName)
    }

    @Then("I see references to other purchases added to the trip on the Change Confirmation page")
    @Alias("I see references to purchases added to the trip on the Confirmation page")
    void referencesToTripManagementDetailsAreShown() {
        itineraryChangeConfirmationPage.shownInPage("New Purchases in Trip")
        itineraryChangeConfirmationPage.verifyConfirmationTitleDetailsOnPage()
        itineraryChangeConfirmationPage.viewDetailsLinkShouldBeDisplayed()
    }

    @Then("I see the hotel product which belongs to the existing trip with its details hidden on the Change Confirmation page")
    def verifyHotelProductHasAssociatedProduct() {
        itineraryChangeConfirmationPage.verifyHotelProductHasAssociatedProduct()
    }
    
    @Then("I see B icon displayed next to the passenger name on Change Flight Confirmation page")
    def verifyBIconDisplayedNextToThePassenger() {
        itineraryChangeConfirmationPage.verifyCurrentPageLocation()
        itineraryChangeConfirmationPage.verifyBIconDisplayed()
    }
}
