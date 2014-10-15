/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package steps

import org.jbehave.core.annotations.When
import pages.SiteMapPage

class SiteMapSteps {

    SiteMapPage siteMapPage

    @When("I go to the Site Map page")
    def OpenSiteMapPage() {
        siteMapPage.openSiteMapPage()
    }
}
