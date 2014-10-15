package pages

import pages.BasePage
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class AlreadyEnrolledAccountPage extends BasePage {
    private static final By VIEW_ACCOUNT_CONFIRMATION_LINK = By.linkText("View My Account Confirmation")
    private static final By VIEW_ACCOUNT_LINK = By.linkText("View My Account")

    @Override
    String getRelativePath() {
        return "account/enroll/already-enrolled.html"
    }

    public AlreadyEnrolledAccountPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    def verifyPage() {
        checkSomethingServed()

        def oopsMessages = ['You have already completed an account enrollment',
                            'Please choose an option from the following',
                            'View My Account Confirmation',
                            'View My Account']
        verifyOopsMessages(oopsMessages)
        testAccountConfirmationLink()
        testMyAccountLink()
    }

    def testAccountConfirmationLink() {
        waitForElement(VIEW_ACCOUNT_CONFIRMATION_LINK).click()
        verifyAccountConfirmationPage()
        clickBrowserBackButton();
    }

    def verifyAccountConfirmationPage() {
        checkSomethingServed()
        shouldBeInPage("account/enroll/confirm-customer");
        shownInPage("Your Account Has Been Created")

    }

    def testMyAccountLink() {
        waitForElement(VIEW_ACCOUNT_LINK).click()
        verifyAccountInfoPage()
        clickBrowserBackButton();
    }

    def verifyAccountInfoPage() {
        checkSomethingServed();
        shouldBeInPage("account/info")
    }
}
