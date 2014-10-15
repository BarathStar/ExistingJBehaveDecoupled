package pages

import org.jbehave.web.selenium.WebDriverProvider
import steps.conditional.ToggleGlobalNav

public class CareersPage extends BasePage {

    private static final String PATH = "/html/about-southwest/careers/index.html?int=GFOOTER-ABOUT-CAREERS"
    private static final String GLOBAL_NAV_PATH = "/html/about-southwest/careers/index.html?clk=GFOOTER-ABOUT-CAREERS"
    private static final String TITLE = "Southwest Airlines Careers"

    def CareersPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    def open() {
        super.open()
        checkSomethingServed()
    }

    void verifyOpenCareersPage() {
        if (ToggleGlobalNav.isOn()) {
            shouldBeInPage(GLOBAL_NAV_PATH)
        } else {
            shouldBeInPage(PATH)
        }
        verifyPageTitle(TITLE)
    }
}