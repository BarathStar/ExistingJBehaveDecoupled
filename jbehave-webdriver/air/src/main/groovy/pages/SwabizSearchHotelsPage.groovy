/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package pages

import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import state.Flow

class SwabizSearchHotelsPage extends BasePage{
    private static final String PATH = '/hotels/search-hotels.html'

    Flow flow

    SwabizSearchHotelsPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def open() {
        flow.isSwabiz = true
        get(Domains.swabizDomain + PATH)
        WaitForPageToLoad
    }
}
