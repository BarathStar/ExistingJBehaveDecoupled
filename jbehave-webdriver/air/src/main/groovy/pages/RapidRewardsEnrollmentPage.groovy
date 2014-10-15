package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class RapidRewardsEnrollmentPage extends BasePage {

    private static final String PATH = '/account/enroll/enroll-upgrade'
    private static final By ENROLL_BUTTON = By.id('submitButton')
    private static final By ACCEPT_RULES = By.cssSelector(".acceptRulesWrapper input")

    public RapidRewardsEnrollmentPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    public String getRelativePath() {
        return PATH
    }

    def clickOnEnrollButton() {
        waitForElement(ENROLL_BUTTON).click()
    }

    def clickOnAcceptRulesCheckbox() {
        waitForElement(ACCEPT_RULES).click()
    }
}
