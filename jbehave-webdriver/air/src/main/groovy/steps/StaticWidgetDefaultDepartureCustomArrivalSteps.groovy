package steps

import org.jbehave.core.annotations.Given
import pages.StaticWidgetDefaultDepartureCustomArrivalPage

class StaticWidgetDefaultDepartureCustomArrivalSteps {

    StaticWidgetDefaultDepartureCustomArrivalPage staticWidgetDefaultDepartureCustomArrivalPage

    @Given("I am on the default departure and custom arrival page")
    def openStaticDefaultDepartureCustomArrivalPage() {
        staticWidgetDefaultDepartureCustomArrivalPage.openPage()
    }

}
