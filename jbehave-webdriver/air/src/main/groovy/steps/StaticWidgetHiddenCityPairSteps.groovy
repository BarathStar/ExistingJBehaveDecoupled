package steps

import org.jbehave.core.annotations.Given

import pages.StaticWidgetHiddenCityPairPage

class StaticWidgetHiddenCityPairSteps {

    StaticWidgetHiddenCityPairPage staticWidgetHiddenCityPairPage

    @Given("I am on the static widget with hidden input fields containing the origin and destination page")
    def openAirConsolidationThree() {
        staticWidgetHiddenCityPairPage.openPage()
    }

}
