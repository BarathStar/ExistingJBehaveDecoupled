package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.SeeWhereWeFlyPage

class SeeWhereWeFlyThenSteps {

    SeeWhereWeFlyPage seeWhereWeFlyPage

    @Then("I close the popup window")
    def closeAndSwitchWindow() {
        seeWhereWeFlyPage.closeAndSwitchWindow()
    }

    @Then("I see the list of domestic multi-carrier destinations")
    def viewListOfDestinations() {
        seeWhereWeFlyPage.viewListOfDestinations()
    }

    @Then("I see the list of \$station_list destinations")
    def theseStationsAreDisplayed(String station_list) {
        seeWhereWeFlyPage.findStationsInList(station_list)
    }

    @Then("I see the destination route map")
    void verifyRouteMap() {
        seeWhereWeFlyPage.verifyRouteMap()
    }

}
