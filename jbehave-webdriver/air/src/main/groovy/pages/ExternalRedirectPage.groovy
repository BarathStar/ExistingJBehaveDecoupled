/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class ExternalRedirectPage extends BasePage {

    private static final By EXTERNAL_REDIRECT_URL = By.cssSelector("#url")

    private static final PATH = "/flight/externalRedirect"

    String getRelativePath() {
        PATH
    }

    ExternalRedirectPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, PATH)
    }

    void verifyAlteaLowFareCalendar(){
       verifyTextPresent("global.southwest.com/southwest/dyn/air/booking/availability", EXTERNAL_REDIRECT_URL)
       verifyTextPresent("IS_FLEXIBLE=TRUE", EXTERNAL_REDIRECT_URL)

    }

    def getRedirectURL() {
        waitForElement(EXTERNAL_REDIRECT_URL).getText()
    }
}
