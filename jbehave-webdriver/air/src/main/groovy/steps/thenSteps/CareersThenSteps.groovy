package steps.thenSteps

import org.jbehave.core.annotations.Then

import pages.CareersPage

class CareersThenSteps {

    CareersPage careersPage

    @Then("I see the Southwest Airlines Careers page is open")
    def verifyOpenCareersPage() {
		careersPage.verifyOpenCareersPage()
    }
}
