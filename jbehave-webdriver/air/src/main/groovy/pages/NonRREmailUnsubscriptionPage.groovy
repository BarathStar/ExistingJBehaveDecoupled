/* Copyright (c) 2012, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

import state.AccountManagementData

public class NonRREmailUnsubscriptionPage extends BasePage {

    private static final By UNSUBSCRIBE_BUTTON = By.id('submitButton')
    private static final By UNSUBSCRIBE_CONFIRMATION_MESSAGE = By.className('unsubscribe_confirmation_message')

    public NonRREmailUnsubscriptionPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/subscription/non-rr-email-unsubscribe.html')
    }

    @Override
    public String getRelativePath() {
        return '/flight/subscription/non-rr-email-unsubscribe.html'
    }

    void clickUnsubscribeButton() {
        waitForElement(UNSUBSCRIBE_BUTTON).click()
    }

    void openWithParams(AccountManagementData accountData) {
        String url = "${domain}/${getRelativePath()}?a=${accountData.emailAddress}&s=${accountData.subscribeList}&v=${accountData.token}"
        get(url)
    }

    void verifyCnsUnsubscriptionMessage(String email){
        waitForElement(UNSUBSCRIBE_CONFIRMATION_MESSAGE).text.shouldContain "Your e-mail address, ${email}, has been removed from The Click \'N Save® mailing list."
    }

    void verifyIanUnsubscriptionMessage(String email){
        waitForElement(UNSUBSCRIBE_CONFIRMATION_MESSAGE).text.shouldContain "Your e-mail address, ${email}, has been removed from The Southwest In a Nutshell mailing list."
    }

    void verifyCnsUnsubscriptionTwiceErrorMessage(String email){
        waitForElement(UNSUBSCRIBE_CONFIRMATION_MESSAGE).text.shouldContain  "Your e-mail address, ${email}, is not on The Click \'N Save® mailing list."
    }
}