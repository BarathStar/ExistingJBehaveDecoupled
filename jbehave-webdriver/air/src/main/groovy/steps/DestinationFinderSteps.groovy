package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.DestinationFinderPage

// TODO: This class is causing PicoContainer exceptions in all JBehaves that utilize it. Currently all of these stories
// are marked as "not_passing draft".

/**
 * Controller class for DestinationFinderPage
 */
class DestinationFinderSteps {

    /**
     * Destination Finder steps
     */
    DestinationFinderPage destinationFinderPage

    @Given("I am on the destination finder page")
    @When("I am on the Destination Finder Page")
    def openDestinationFinderPage() {
        destinationFinderPage.open()
    }

    @Given("I am on I Need My Password tab from destination finder entry point")
    def openDestinationFinderFromEntryPointPage(){
        openDestinationFinderPage()
        destinationFinderPage.clickOnTrackIt()
        destinationFinderPage.clickOnNeedHelploggoingIn()
    }

    @When("I do a mouseover action on \$airportCode city")
    def mouseoverCityAction(String airportCode){
        destinationFinderPage.mouseoverCityAction(airportCode)
    }

    @When("I select \$airportCode as departing city")
    def selectCityAsDeparting(String airportCode){
        destinationFinderPage.selectCityAsDeparting(airportCode)
    }

    @When("I click on Check Dates option")
    def clickOnCheckDates(){
        destinationFinderPage.clickOnCheckDates()
    }

    @When("I click on Track It option")
    def clickOnTrackIt(){
        destinationFinderPage.clickOnTrackIt()
    }
}