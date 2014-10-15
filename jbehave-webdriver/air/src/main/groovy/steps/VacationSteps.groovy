package steps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.When
import pages.VacationPage

public class VacationSteps {

    VacationPage vacationPage

    @Given("I am on the Jackpot page")
    def openJackpotPage() {
        vacationPage.openJackpotPage()
    }

    @Given("I am on the Vacations page")
    @When("I am on the Vacations page")
    @Alias("I go to the Vacations page")
    def openVacationsPage() {
        vacationPage.openVacationsPage()
    }

    @When("I clear the departure and return dates")
    def clearTheDepartureAndReturnDates() {
        vacationPage.clearDepartAndReturnDates()
    }

    @When("I clear the package departure and return dates")
    def clearThePackageDepartureAndReturnDates() {
        vacationPage.clearPackageDepartAndReturnDates()
    }

    @When("I click on the search button")
    def submitSearch() {
        vacationPage.clickOnSearchButton()
    }

    @When("I click on the package search button")
    def submitPackageSearch() {
        vacationPage.clickOnPackageSearchButton()
    }
}
