package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.AirportInformationPage
import steps.conditional.ToggleGlobalNav

class AirportInformationThenSteps {

    AirportInformationPage airportInformationPage
	private static String AIRPORT_INFORMATION_PAGE_FROM_NAV = "/html/air/airport-information.html?int=GSUBNAV-AIR-AIRPORT-INFO"
    private static String AIRPORT_INFORMATION_PAGE_FROM_NEW_GLOBAL_NAV = "/html/air/airport-information.html?clk=GFOOTER-FLY-AIRPORTINFO"

    @Then("I see the Airport Arrival Times")
    void verifyAirportInformationPage() {
        airportInformationPage.getRelativePath()
    }

	@Then("I see the Airport Information page")
	void verifyAirportInformationPageTitleAndURL() {
		airportInformationPage.verifyAirportInformationPage()

        if (ToggleGlobalNav.isOn()) {
            airportInformationPage.shouldBeInPage(AIRPORT_INFORMATION_PAGE_FROM_NEW_GLOBAL_NAV)
        } else {
            airportInformationPage.shouldBeInPage(AIRPORT_INFORMATION_PAGE_FROM_NAV)
        }
	}
}
