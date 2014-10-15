package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import pages.InteractiveRouteMapPage

class InteractiveRouteMapSteps {

    InteractiveRouteMapPage interactiveRouteMapPage

    /**
     * Interactive Route Map steps
     */

    @Given("I am on the interactive route map page")
    def openInteractiveRouteMapPage() {
        interactiveRouteMapPage.open()
    }

    @Given("I am on the Modal for See Where We Fly")
    @When("I click on Where we Fly link")
    def verifySeeWhereWeFlyModal(){
        interactiveRouteMapPage.clickWhereWeFlight()
    }

    @When("I am on the Modal for See Where We Fly")
    def verifyModalWhereWeFly(){
        interactiveRouteMapPage.verifyModalWhereWeFly()
    }

    @When("I click on View Route Map")
    def clickOnViewRouteMapLink(){
        interactiveRouteMapPage.clickOnViewRouteMapLink()
    }

	@When("I click on the Search by Map link on the Where We Flight flyout")
	def clickOnSearchByMapLink(){
		interactiveRouteMapPage.clickOnSearchByMapLink()
	}

    @When("I click on Caribbean tab")
    def clickOnCaribbeanTab(){
        interactiveRouteMapPage.clickOnCaribbeanTab()
    }

    @When("I click on Mexico tab")
    def clickOnMexicoTab(){
        interactiveRouteMapPage.clickOnMexicoTab()
    }

    @When("I click on All Cities tab")
    def clickOnAllCitiesTab(){
        interactiveRouteMapPage.clickOnAllCitiesTab()
    }

    @When("I click on Southwest Airlines option")
    def clickOnSouthwestCheckbox(){
        interactiveRouteMapPage.clickOnSouthwestCheckbox()
    }

    @When("I click on Airtran Airways option")
    def clickOnAirtranCheckbox(){
        interactiveRouteMapPage.clickOnAirtranCheckbox()
    }

    @Then("I see I am on the Interactive Route Map page")
    def verifyIAmOnThePage(){
        interactiveRouteMapPage.verifyCurrentPageLocation()
    }
}