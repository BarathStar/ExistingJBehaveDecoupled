package pages

import org.openqa.selenium.By
import org.jbehave.web.selenium.WebDriverProvider
import state.Flow

class IntroducingNewRapidRewardsPage extends BasePage {

    private static final By CONTINUE_BUTTON = By.id("continueButton")

    Flow flow

    public IntroducingNewRapidRewardsPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/intro-splash')
    }

    public String getRelativePath() {
        return "/account/intro-splash"
    }

    def clickOnContinueButton() {
        waitForElement(CONTINUE_BUTTON).click()
    }
}
