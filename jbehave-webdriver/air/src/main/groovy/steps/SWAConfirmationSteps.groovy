package steps

import pages.SWAConfirmationPage
import org.jbehave.core.annotations.*

class SWAConfirmationSteps
{

    SWAConfirmationPage swaConfirmationPage

    @Then("I view my itinerary on the confirmation page")
    def verifySwaConfirmationPage()
    {
        swaConfirmationPage.verifySwaCompleteItinerary()
    }

    /**
     * TODO refactor and move the code from existing framework
     */
    @Then("I should see my itinerary on confirmation page")
    def verifyConfirmationPageItinerary() {
        // to implement by using the existing code.
    }

}