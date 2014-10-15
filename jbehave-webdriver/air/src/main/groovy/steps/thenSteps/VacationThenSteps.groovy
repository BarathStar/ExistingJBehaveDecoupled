package steps.thenSteps

import org.jbehave.core.annotations.Then
import org.openqa.selenium.By
import pages.VacationPage
import org.jbehave.core.model.ExamplesTable

class VacationThenSteps {

    VacationPage vacationPage

    @Then("I should see all fields completed")
    def verifyFieldsAreNotCleared() {
        vacationPage.verifyFieldsAreCompleted()
    }

    @Then("I should not see an oops message")
    def verifyNoOopsMessage() {
        vacationPage.checkNoOops()
    }

    @Then("I see the special offers and packages available")
    def verifyVacationPage() {
        vacationPage.verifyPageTitle(vacationPage.PAGE_TITLE)
    }

    @Then("I see the following fields have errors: \$fields")
    def verifyFieldsHaveErrors(List<String> fields) {
        vacationPage.verifyFieldsHaveErrors(fields)
    }

    @Then("I see the following fields do not have errors: \$fields")
    def verifyFieldsDoNotHaveErrors(List<String> fields) {
        vacationPage.verifyFieldsDoNotHaveErrors(fields)
    }

    @Then("I see the following package fields have errors: \$fields")
    def verifyPackageFieldsHaveErrors(List<String> fields) {
        vacationPage.verifyPackageFieldsHaveErrors(fields)
    }

    @Then("I see the following package fields do not have errors: \$fields")
    def verifyPackageFieldsDoNotHaveErrors(List<String> fields) {
        vacationPage.verifyPackageFieldsDoNotHaveErrors(fields)
    }
}
