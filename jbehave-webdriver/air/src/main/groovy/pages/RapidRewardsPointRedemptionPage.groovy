package pages;


import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

public class RapidRewardsPointRedemptionPage extends BasePage {

    private static final By REDEEM_REWARDS_BUTTON = By.id("redeemRewardsButton")

    public RapidRewardsPointRedemptionPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    void selectPromotionalCertificate() {
        waitForElement(REDEEM_REWARDS_BUTTON).click()
    }
}
