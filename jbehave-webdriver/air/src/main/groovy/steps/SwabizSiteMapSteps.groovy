package steps

import org.jbehave.core.annotations.When
import pages.SwabizBookTravelPage;
import pages.SwabizSiteMapPage

class SwabizSiteMapSteps {

    SwabizSiteMapPage swabizSiteMapPage

    @When("I go to the Site Map page in Swabiz")
    def OpenSiteMapPage() {
        swabizSiteMapPage.open()
    }

    @When("I click on the Business Select link on Site Map Page")
    def clickOnBusinessSelectLink() {
        swabizSiteMapPage.clickOnBusinessSelectLink()
    }

}
