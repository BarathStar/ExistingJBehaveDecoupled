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



}