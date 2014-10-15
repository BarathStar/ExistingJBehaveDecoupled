/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

/**
 * This class represents a Need Password Confirmation Page for GetHealthy project
 */

public class NeedPasswordConfirmationPage extends BasePage {

    private static final By CONFIRMATION_TITLE = By.cssSelector("div.swa_rrgh_recoveryConfirmation h2")

    public NeedPasswordConfirmationPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/recovery/confirmation')
    }

    String getRelativePath() {
        return "/account/recovery/confirmation"
    }

    def verifyConfirmationMessages() {
        waitForElement(CONFIRMATION_TITLE).getText().shouldBeEqual "We have e-mailed your login information."
    }
}