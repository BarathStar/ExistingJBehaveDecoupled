/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class AddCompanionPage extends BasePage {

    private static final PATH = "account/rapidrewards/bookCompanion.html"

    String getRelativePath() {
        PATH
    }

    AddCompanionPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, PATH)
    }

    def getWrapper() {
        waitForElement(By.id("companion_pass_wrapper"))
    }
}
