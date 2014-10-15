/* Copyright (c) 2012, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By


public class ConfirmationEmailSubscriptionPage extends BasePage {

    private static final By CLICK_N_SAVE_CONFIRMATION_MESSAGE = By.cssSelector('#email_subscriptions_confirm p:first-child')

    public ConfirmationEmailSubscriptionPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/subscription/confirm-email-subscription')
    }

    void verifyCnsSubscriptionMessage(){
        waitForElement(CLICK_N_SAVE_CONFIRMATION_MESSAGE).text.shouldContain 'Thank you for signing up for Click \'N Save®! You will start receiving e-mails with our very next send'
    }

    void verifyIanSubscriptionMessage(){
        waitForElement(CLICK_N_SAVE_CONFIRMATION_MESSAGE).text.shouldContain 'Thank you for signing up for Southwest In a Nutshell! You will start receiving e-mails with our very next send'
    }

    @Override
    public String getRelativePath() {
        return '/flight/subscription/confirm-email-subscription'
    }
}
