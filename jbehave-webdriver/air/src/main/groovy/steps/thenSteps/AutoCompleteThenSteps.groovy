package steps.thenSteps

import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.Aliases
import pages.elements.AutoCompleteWidget
import static util.AutoCompleteData.*
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.When

class AutoCompleteThenSteps {

    AutoCompleteWidget autoCompleteWidget

    @When("I should see the auto-complete list for <stationInfo>")
    @Then("I should see the auto-complete list for <stationInfo>")
    @Aliases(values = ["I should see the auto-complete list for \$stationInfo",
    "I should see a drop down of the <stationInfo> in the area",
    "I should see a drop down of \$stationInfo in the area"])
    def verifyAutoCompleteContent(@Named("stationInfo") String stationInfo) {
        autoCompleteWidget.verifyAutoCompleteWidgetContent(AUTOCOMPLETE_DETAILS.get(stationInfo))
    }

    @Then("for route \$routeInfo, I should see auto-complete list for To field")
    @Alias("for route \$routeInfo, I should see error in To field")
    def verifyAutoCompleteContentWithRoute(String routeInfo) {
        def expectedResult = ROUTE_DETAILS.get(routeInfo)
        autoCompleteWidget.verifyAutoCompleteWidgetContent(expectedResult)
    }



    @Then("I see that the full city name for \$station has been autocompleted")
    def verifyFullCityNameInOutboundCityField(String station) {
        autoCompleteWidget.verifyAutoCompleteCityFullName(AUTOCOMPLETE_FULL_CITY_TEXT.get(station))
    }

    @Then("I should not see the auto-complete list")
    def verifyAutoCompleteWidgetNotPresent() {
        autoCompleteWidget.verifyAutoCompleteDropDownNoPresent();
    }

    @Then("I view Airtrans redirect")
    def airTranRedirectVerify() {
        autoCompleteWidget.airTranRedirectVerify()
        autoCompleteWidget.checkAirTranRedirectVerify()
    }

    @Then("I should see the drop down close")
    def verifyDropDownCollapsed() {
        autoCompleteWidget.verifyDropDownCollapsed()
    }

    @Then("I should see \$station as the first option in the auto-complete list")
    def verifyStationInAutoCompleteFirstOption(String station) {
        autoCompleteWidget.verifyAutoCompleteListStartsWith(station)
    }

    @Then("I should see the auto complete dropdown for airtran \$station from Marketingpage")
    def verifyAutocompleteContentForMarketingPage(String station) {
        autoCompleteWidget.verifyAutoCompleteWidgetContent(InternationalAndDomesticTravelOnAirTran.get(station))
    }

    @Then("the search widget expands")
    def verifySearchWidgetExpands() {
        autoCompleteWidget.verifySearchWidgetExpands()
    }

    @Then("the search widget expands and collapses")
    def verifySearchWidgetCollapsed() {
        autoCompleteWidget.verifySearchWidgetCollapsed()
    }
}
