package steps

import pages.StaticWidgetAirTranRedirectPage
import org.jbehave.core.annotations.Given

class StaticWidgetAirTranRedirectSteps {

    StaticWidgetAirTranRedirectPage staticWidgetAirTranRedirectPage

    @Given("I am on the static widget Air Tran redirect page")
    def openAirTranRedirectPage() {
        staticWidgetAirTranRedirectPage.openPage()
    }

}
