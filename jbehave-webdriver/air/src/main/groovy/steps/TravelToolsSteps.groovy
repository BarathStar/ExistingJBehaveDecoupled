package steps

import pages.TravelToolsPage
import org.jbehave.core.annotations.*

class TravelToolsSteps {

    TravelToolsPage travelToolsPage

    @When("I click on the travel extras link on the Travel Tools Page")
    def clickTravelExtrasLink(){
        travelToolsPage.clickTravelExtrasLink()
    }

    @Given("I am on the Travel Tools page")
    @When("I go to the Travel Tools page")
    def openTravelToolsPage() {
        travelToolsPage.open()
    }

    @When("I click on the Book Air Log into MySouthwest link")
    def clickOnBookAirLogIntoMySouthwestLink() {
        travelToolsPage.clickOnBookAirLogIntoMySouthwestLink()
    }

    @When("I click on the Book Car Log into MySouthwest link")
    def clickOnBookCarLogIntoMySouthwestLink() {
        travelToolsPage.clickOnBookCarLogIntoMySouthwestLink()
    }

    @When("I click on the Book Hotel Log into MySouthwest link")
    def clickOnBookHotelLogIntoMySouthwestLink() {
        travelToolsPage.clickOnBookHotelLogIntoMySouthwestLink()
    }

}
