package steps

import org.jbehave.core.annotations.Given
import pages.StaticWidgetCustomDropdownAirTranRedirectPage

class StaticWidgetCustomDropdownAirTranRedirectSteps {

    StaticWidgetCustomDropdownAirTranRedirectPage staticWidgetCustomDropdownAirTranRedirectPage

    @Given("I am on the Static Widget Air page with a custom dropdown with a city pair that redirects to FL")
    def openPage() {
        staticWidgetCustomDropdownAirTranRedirectPage.openPage()
    }

}
