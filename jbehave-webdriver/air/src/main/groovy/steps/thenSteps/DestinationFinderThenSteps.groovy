package steps.thenSteps

import pages.DestinationFinderPage
import pages.ShortcutSelectFlightsPage
import org.jbehave.core.annotations.Then

class DestinationFinderThenSteps {

    DestinationFinderPage destinationFinderPage
    ShortcutSelectFlightsPage shortcutSelectFlightsPage

    @Then("I see Destination Finder page")
    def verifyDestinationFinderPage() {
        destinationFinderPage.verifyPage()
    }

    @Then("I see the option to filter the departing cities")
    def verifyDepartingCityFilter() {
        destinationFinderPage.verifyOriginAirportIsDisplayed()
    }

    @Then("I see the option to filter the destination cities")
    def verifyDestinationCityFilter() {
        destinationFinderPage.verifyDestinationAirportIsDisplayed()
    }

    @Then("I see the slider points range")
    def verifySliderPointsRange() {
        destinationFinderPage.verifySliderPointsRangeIsDisplayed()
    }

    @Then("I see the points range section")
    def verifyPointsRangeSection() {
        destinationFinderPage.verifyPointsRangeTitleIsDisplayed()
        destinationFinderPage.verifyPointsRangeAmount()
    }

    @Then("I see the destination finder map")
    def verifyDestinationFinderMapIsPresent() {
        destinationFinderPage.verifyDestinationFinderMapIsDisplayed()
    }

    @Then("I do not see the help popup")
    def verifyHelpPopupIsNotPresent() {
        destinationFinderPage.noHelpPageDisplay()
    }

    @Then("I see the help popup")
    def verifyHelpPopupIsPresent() {
        destinationFinderPage.helpPageDisplay()
    }

    @Then("I see Destination Finder Page well done")
    def verifyDFP(){
        destinationFinderPage.verifySliderPointsRangeIsDisplayed()
    }

    @Then("I  see the \$airportCode More Info fly out message")
    def verifyCityFlyout(String airportCode){
        destinationFinderPage.verifyCityFlyout(airportCode)
    }

    @Then("I see \$cityName Info container fly out message")
    def verifyCityFlyoutMessage(String cityName){
        destinationFinderPage.verifyCityFlyoutMessage(cityName)
    }

    @Then("I see the Select Dates page")
    def verifyCheckDatesPage(){
        shortcutSelectFlightsPage.verifyPage()
    }

    @Then("I see the Flight Tracker pop up window")
    def verifyFlightTrackerPopUp(){
        destinationFinderPage.verifyFlightTrackerPopUpIsDisplayed()
    }
}
