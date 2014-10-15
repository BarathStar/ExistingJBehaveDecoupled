package steps

import org.jbehave.core.annotations.Given
import pages.StaticWidgetCustomDepartureDefaultArrivalPage

class StaticWidgetCustomDepartureDefaultArrivalSteps {

    StaticWidgetCustomDepartureDefaultArrivalPage staticWidgetCustomDepartureDefaultArrivalPage

    @Given("I am on the custom departure and default arrival page")
    def openStaticCustomDepartureDefaultArrivalPage() {
        staticWidgetCustomDepartureDefaultArrivalPage.openPage()
    }

}
