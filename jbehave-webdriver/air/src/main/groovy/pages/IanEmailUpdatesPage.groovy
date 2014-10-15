/* Copyright (c) 2012, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

import domain.RapidRewardsAccount
import pages.elements.forms.EmailUpdatesForm

class IanEmailUpdatesPage extends BasePage {

    private static final By EMAIL_SUBSCRIPTION_FORM_CONTAINER = By.id("emailSubscription")

    public IanEmailUpdatesPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/subscription/ian-email-subscribe.html')
    }

    void fillAndSubmitEmailUpdatesForm(RapidRewardsAccount user) {
        EmailUpdatesForm emailUpdatesForm = EmailUpdatesForm.create(waitForElement(EMAIL_SUBSCRIPTION_FORM_CONTAINER))
        emailUpdatesForm.fillForm(user)
        emailUpdatesForm.clickSubscribeButton()
    }

    @Override
    public String getRelativePath() {
        return '/flight/subscription/ian-email-subscribe.html'
    }
}
