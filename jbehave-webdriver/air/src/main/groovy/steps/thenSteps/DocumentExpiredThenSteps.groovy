package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.DocumentExpiredPage

class DocumentExpiredThenSteps {

    DocumentExpiredPage documentExpiredPage;

    @Then("I should see the document expired warning page")
    def verifyDocumentExpiredWarningPage() {
        documentExpiredPage.verifyErrorInformation()
    }
}
