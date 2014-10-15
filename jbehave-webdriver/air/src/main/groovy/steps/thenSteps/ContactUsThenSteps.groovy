package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.ContactUsPage

class ContactUsThenSteps {

    ContactUsPage contactUsPage

    @Then("I see the Thank you! modal")
    def verifyThankYouModalIsPresent() {
        contactUsPage.verifyThankYouModalIsPresent()
    }

    @Then("I see a Thank You message confirming receipt of the additional information")
    def verifyThankYouModalIsPresentRFI() {
        contactUsPage.verifyThankYouModalIsPresentRFI()
    }

    @Then("I should not see the Thank you! modal")
    def verifyThankYouModalIsNotPresent() {
        contactUsPage.verifyThankYouModalIsNotPresent()
    }

    @Then("I return to the main Contact Us page")
    def verifyIAmOnTheContactUsPage() {
        contactUsPage.verifyCurrentPageLocation()
    }

    @Then("I will see the corresponding error messages for contact us Page")
    def verifyOopsMessageFieldAitran(){
        contactUsPage.verifyAirtranFieldsOopsMessage()
    }

    @Then("I see the No Airport Found message")
    def verifyOriginAirportNotAirportFound(){
        contactUsPage.verifyAcResultsIsPresent()
    }

    @Then("I receive an error message stating that Additional Information Was Not Entered")
    def verifyOopsMessageSupplementInformation(){
        contactUsPage.verifySupplementInformationOopsMessage()
    }
}
