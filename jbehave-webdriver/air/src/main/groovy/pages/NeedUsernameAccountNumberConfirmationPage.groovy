/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import domain.RapidRewardsAccount
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

/**
 * This class represents a Need Password Confirmation Page for GetHealthy project
 */

public class NeedUsernameAccountNumberConfirmationPage extends BasePage {

    private static final By ACCOUNT_NUMBER = By.cssSelector("label.swa_rrgh_credentials_accountNumber")
    private static final By USERNAME = By.cssSelector("label.swa_rrgh_credentials_username")
    private static final By CONFIRMATION_TITLE = By.cssSelector("div#sw_content div.swa_jsOnly h1#page_title strong")

    public NeedUsernameAccountNumberConfirmationPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/recoverAccountNumber')
    }

    String getRelativePath() {
        return "/account/recoverAccountNumber"
    }

    def verifyConfirmationMessages(RapidRewardsAccount member) {
        waitForElement(CONFIRMATION_TITLE).getText().shouldBeEqual "Here's your information!"
        waitForElement(USERNAME).getText().shouldBeEqual member.username
        waitForElement(ACCOUNT_NUMBER).getText().shouldBeEqual member.accountNumberField
    }
}