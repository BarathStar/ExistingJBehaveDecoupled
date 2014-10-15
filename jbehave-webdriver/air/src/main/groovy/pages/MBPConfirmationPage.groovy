/*
 * Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.
 */
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

public class MBPConfirmationPage extends BasePage {

    private static final String PATH = "/flight/viewCheckinConfirmation.html"
    private static final By RETURN_BUTTON = By.cssSelector(".doNotPrintFlag input[name=Return]")

    def MBPConfirmationPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    String getRelativePath() {
        return PATH
    }
}
