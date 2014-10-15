package steps

import org.jbehave.core.annotations.Given
import pages.StaticWidgetRadioButtonandOutboundDateDisabledPage

class StaticWidgetRadioButtonandOutboundDateDisabledSteps {

    StaticWidgetRadioButtonandOutboundDateDisabledPage staticWidgetRadioButtonandOutboundDateDisabledPage

    @Given("I am on the air ctd test page with a static booking widget featuring radio button and date disabled")
    def openStaticWidgetRadioButtonandOutboundDateDisabledPage() {
       staticWidgetRadioButtonandOutboundDateDisabledPage.openPage()
    }

}
