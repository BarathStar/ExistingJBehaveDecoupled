package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

public class SwaBizLinkWithCompanyPage extends BasePage {

    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By CANCEL_BUTTON = By.id("declineMergeLink")

    public SwaBizLinkWithCompanyPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/link-with-company')
    }

    String getRelativePath() {
        return "flight/link-with-company"
    }

    def clickSubmit() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def clickCancel() {
        waitForElement(CANCEL_BUTTON).click()
    }
}
