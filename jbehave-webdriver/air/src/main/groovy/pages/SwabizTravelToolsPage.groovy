/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import state.Flow;

class SwabizTravelToolsPage extends BasePage {

    private static final String PATH = "/html/travel-tools/index.html"

    Flow flow

    @Override
    String getRelativePath() {
        return PATH
    }

    def SwabizTravelToolsPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, PATH)
    }

    def open() {
        flow.isSwabiz = true
        get(Domains.swabizDomain + PATH)
        WaitForPageToLoad
    }
}