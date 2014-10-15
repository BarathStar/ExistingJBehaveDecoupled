package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.InteractiveRouteMapPage
import org.jbehave.core.model.ExamplesTable

class InteractiveRouteMapThenSteps {

    InteractiveRouteMapPage interactiveRouteMapPage

    /**
     * Interactive Route Map Then Steps
     */

    @Then("I see the Interactive Route Map Page")
    def verifyRouteMapPage(){
        interactiveRouteMapPage.verifyPage()
    }

    @Then("I see All Cities tab active")
    def verifyAllCitiesTab(){
        interactiveRouteMapPage.verifyAllCitiesTab()
    }

    @Then("I see \$cityName in the cities list")
    def verifyCityOnModalList(String cityName){
        interactiveRouteMapPage.verifyCityOnModalList(cityName)
    }

    @Then("I see Southwest Airlines option checked")
    def verifySouthwestCheckbox(){
        interactiveRouteMapPage.verifySouthwestCheckbox()
    }

    @Then("I see Airtran Airways option checked")
    def verifyAirtranCheckbox(){
        interactiveRouteMapPage.verifyAirtranCheckbox()
    }

    @Then("I see Southwest Airlines option not checked")
    def verifyNotSouthwestCheckbox(){
        interactiveRouteMapPage.verifyNotSouthwestCheckbox()
    }

    @Then("I see Airtran option not checked")
    def verifyNotAirtranCheckbox(){
        interactiveRouteMapPage.verifyNotAirtranCheckbox()
    }

    @Then("I see a message indicating that there are no destinations for the selected airlines")
    def verifyNoDestinationForSelectedAirlines(){
        interactiveRouteMapPage.verifyNoDestinationForSelectedAirlines()
    }

    @Then("I see a message indicating that should select an airline to see results")
    def verifySelectAirlineForResults(){
        interactiveRouteMapPage.verifySelectAirlinesForResults()
    }
}
