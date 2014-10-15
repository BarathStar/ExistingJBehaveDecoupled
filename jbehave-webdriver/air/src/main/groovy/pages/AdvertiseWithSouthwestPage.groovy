package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import steps.conditional.ToggleGlobalNav

class AdvertiseWithSouthwestPage extends BasePage {

    public static final By SWA_PAGE_TITLE = By.className("swa_page_title")
    public static final String AUDIENCE_TARGETING_PAGE_URL = "/html/about-southwest/advertise/index.html?int=GFOOTER-ABOUT-ADVERTISE"
    public static final String NEW_AUDIENCE_TARGETING_PAGE_URL = "/html/about-southwest/advertise/index.html?clk=GFOOTER-ABOUT-ADVERTISE"
    public static final String AUDIENCE_TARGETING_TITLE = "Advertise with Southwest Airlines"

    public AdvertiseWithSouthwestPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return AUDIENCE_TARGETING_PAGE_URL
    }

    void verifyAirportInformationPage() {
        if (ToggleGlobalNav.isOn()) {
            shouldBeInPage(NEW_AUDIENCE_TARGETING_PAGE_URL)
        } else {
            shouldBeInPage(AUDIENCE_TARGETING_PAGE_URL)
        }
        verifyPageTitle(AUDIENCE_TARGETING_TITLE)
    }
}