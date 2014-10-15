/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider

class UnaccompaniedMinorPage extends BasePage {

    def UnaccompaniedMinorPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    String getRelativePath() {
        return "/customer-service/family/unaccompanied-minors-pol.html"
    }

    def switchToUMPageWindow() {
        switchToWindow("Unaccompanied Minor")
    }
}
