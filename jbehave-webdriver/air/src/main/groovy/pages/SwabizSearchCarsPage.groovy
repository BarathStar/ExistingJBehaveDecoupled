/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad

import state.Flow
import com.swacorp.dotcom.webscenarios.air.config.Domains;

public class SwabizSearchCarsPage extends BasePage {

    private static final By SHUTTLES_TOP_BAR_LINK = By.cssSelector('#secondaryNavContainer a[href="http://southwest.iseatz.com/"]')
    private static final String PATH = '/car-rentals/search-car-rentals.html'

    Flow flow

    SwabizSearchCarsPage(WebDriverProvider driverProvider) {
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