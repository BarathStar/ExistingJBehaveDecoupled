package pages

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;


class SummaryEmailListPage extends BasePage {
    WebDriverProvider webDriverProvider
    private static final By FIRST_MAIL = By.id("emailAddress0")
    private static final By SECOND_MAIL = By.id("emailAddress1")
    private static final By THIRD_MAIL = By.id("emailAddress2")
    private static final By FIRST_STATUS_RREU_LIST = By.id("receiveRapidRewardsUpdate0")
    private static final By FIRST_STATUS_RRRP_LIST = By.id("receiveRapidRewardsReport0")
    private static final By SECOND_STATUS_RRRP_LIST = By.id("receiveRapidRewardsReport1")
    private static final By THIRD_STATUS_RREU_LIST = By.id("receiveRapidRewardsUpdate2")
    private static final String SUBSCRIBED_TEXT = "Subscribed"

    def SummaryEmailListPage(WebDriverProvider theWebDriverProvider) {
        super(theWebDriverProvider, '/flight/subscription/rr-email-subscribe.html')
        this.webDriverProvider = theWebDriverProvider
    }

    def verifyFirstEmailSignedInRrrpAndRreu(String email){
        waitForElement(FIRST_MAIL).text.shouldContain email
        waitForElement(FIRST_STATUS_RREU_LIST).text.shouldContain SUBSCRIBED_TEXT
        waitForElement(FIRST_STATUS_RRRP_LIST).text.shouldContain SUBSCRIBED_TEXT
    }

    def verifySecondEmailSignedInRrrp(String email){
        waitForElement(SECOND_MAIL).text.shouldContain email
        waitForElement(SECOND_STATUS_RRRP_LIST).text.shouldContain SUBSCRIBED_TEXT
    }

    def verifyThirdEmailSignedInRreu(String email){
        waitForElement(THIRD_MAIL).text.shouldContain email
        waitForElement(THIRD_STATUS_RREU_LIST).text.shouldContain SUBSCRIBED_TEXT
    }

    @Override
    public String getRelativePath() {
        return "/flight/subscription/rr-email-subscribe.html"
    }

}
