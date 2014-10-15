package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.AccountManagementData

class RapidRewardsUnsubscribePage extends BasePage {

    private static final By UNSUBSCRIBE_BUTTON = By.id('submitButton')
    private static final By UNSUBSCRIBE_CONFIRMATION_MESSAGE = By.cssSelector('.unsubscribe_confirmation_message p:first-child')


    public RapidRewardsUnsubscribePage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/subscription/rr-email-unsubscribe.html')
    }

    @Override
    public String getRelativePath() {
        return "flight/subscription/rr-email-unsubscribe.html"
    }

    void clickUnsubscribeButton() {
        waitForElement(UNSUBSCRIBE_BUTTON).click()
    }

    void openWithParams(AccountManagementData accountData) {
        String url = "${domain}/${getRelativePath()}?a=${accountData.emailAddress}&s=${accountData.subscribeList}&v=${accountData.token}"
        get(url)
    }

    void verifyRapidRewardsReportUnsubscriptionMessage(String email){
        waitForElement(UNSUBSCRIBE_CONFIRMATION_MESSAGE).text.shouldContain "Your e-mail address, ${email}, has been removed from The Rapid Rewards Report mailing list."
    }

    void verifyRapidRewardsEmailUpdateUnsubscriptionMessage(String email){
        waitForElement(UNSUBSCRIBE_CONFIRMATION_MESSAGE).text.shouldContain "Your e-mail address, ${email}, has been removed from The Rapid Rewards Email Update mailing list."
    }

    void verifyRapidRewardsReportDoubleUnsubscriptionMessage(String email){
        waitForElement(UNSUBSCRIBE_CONFIRMATION_MESSAGE).text.shouldContain "Your e-mail address, ${email}, is not on The Rapid Rewards Report mailing list."
    }
}