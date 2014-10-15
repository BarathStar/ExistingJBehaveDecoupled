package steps.thenSteps

import pages.SecurityQuestionsPage
import org.jbehave.core.annotations.Then

class SecurityQuestionsThenSteps {

    SecurityQuestionsPage securityQuestionsPage

    @Then("I see the Security Questions page")
    def seeSecurityQuestionPage() {
        securityQuestionsPage.getTitle().shouldBeEqual "Security Questions"
    }
}
