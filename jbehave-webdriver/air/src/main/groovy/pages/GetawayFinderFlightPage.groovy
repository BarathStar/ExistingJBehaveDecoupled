package pages

import org.jbehave.web.selenium.WebDriverProvider
import steps.conditional.ToggleGlobalNav

public class GetawayFinderFlightPage extends BasePage {

    private static final String GETAWAY_FLIGHT_PAGE_URL = "getawayfinder.southwest.com/flights"
    private static final String GETAWAY_FINDER_PAGE_TITLE = "The Southwest Airlines Flight Guide | Southwest Airlines"

    public GetawayFinderFlightPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return GETAWAY_FLIGHT_PAGE_URL
    }

    def verifyOnGetawayFinderFlightPage() {
        verifyCurrentPageLocation()
    }

    void verifyGetawayFinderUrl() {
        String winHandleBefore = webDriver().getWindowHandle()
        switchToOpenWindow(winHandleBefore, GETAWAY_FINDER_PAGE_TITLE)
        getCurrentUrl().shouldBe "http://getawayfinder.southwest.com/flights", "The current page should be \"GetAway Finder flights section\""
    }

    void verifyGetawayFinderUrlWithOmnitureTag() {
        String winHandleBefore = webDriver().getWindowHandle()
        switchToOpenWindow(winHandleBefore, GETAWAY_FINDER_PAGE_TITLE)
        if (ToggleGlobalNav.isOn()) {
            getCurrentUrl().shouldBe "http://getawayfinder.southwest.com/flights?clk=GSUBNAV-AIR-MAP", "The current page should be \"GetAway Finder flights section\""
        } else {
            getCurrentUrl().shouldBe "http://getawayfinder.southwest.com/flights?clk=HOME-BOOKING-WIDGET-MAP-SEARCH", "The current page should be \"GetAway Finder flights section\""
        }
    }
}