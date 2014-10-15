package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class RewardsTransferConfirmationPage extends BasePage {

    private static final String relativePath = "/account/rapidrewards/rewards-transfer-confirmation"

    private static final By CONFIRMATION_PAGE_SUCCESS = By.id("rewardsTransferSuccessTitle")
    private static final By CONFIRMATION_PAGE_FAILURE = By.id("rewardsTransferFailureTitle")

    def RewardsTransferConfirmationPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, relativePath)
    }

    @Override
    String getRelativePath() {
        return relativePath
    }

    void verifySuccessMessage() {
        waitForElement(CONFIRMATION_PAGE_SUCCESS).isDisplayed().shouldBe true, "Transfer Confirmation Success Message is not displayed."
    }

    void verifyBackButtonOopsMessageIsOnPage() {
        def backButtonOopsMessage = getExpectedOopsMessage()
        backButtonOopsMessage.shouldContain "Please do not use the back button.", "Oops message does not contain the proper wording"
        backButtonOopsMessage.shouldContain "Choose an option from the following:", "Oops message does not contain the proper wording"
        backButtonOopsMessage.shouldContain "View your confirmation again", "Oops message does not contain the proper wording"
        backButtonOopsMessage.shouldContain "Transfer more Points, Credits, or Awards", "Oops message does not contain the proper wording"
        backButtonOopsMessage.shouldContain "Return to My Rapid Rewards Overview", "Oops message does not contain the proper wording"
    }

    void verifyFailureMessage() {
        waitForElement(CONFIRMATION_PAGE_FAILURE).isDisplayed().shouldBe true, "Transfer Confirmation Page Failure is not displayed."
    }
}
