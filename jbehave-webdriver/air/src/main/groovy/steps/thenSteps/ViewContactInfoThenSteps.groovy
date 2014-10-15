package steps.thenSteps

import pages.ViewContactInfoPage
import org.jbehave.core.annotations.Then

class ViewContactInfoThenSteps {
    ViewContactInfoPage viewContactInfoPage

    @Then("I see one Postal Address")
    def verifyCommunicationPreferencesPage() {
        viewContactInfoPage.verifyNumberOfAddresses(1)
    }
}
