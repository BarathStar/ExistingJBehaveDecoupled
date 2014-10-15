package steps

import org.jbehave.core.annotations.*
import pages.SeeWhereWeFlyPage

public class SeeWhereWeFlySteps {

    SeeWhereWeFlyPage seeWhereWeFlyPage

    @When("I click list view tab")
    def clickListViewTab() {
        seeWhereWeFlyPage.clickListViewTab()
    }

    @When("I click route map tab")
    def clickRouteMapTab() {
        seeWhereWeFlyPage.clickRouteMapTab()
    }

    @When("I choose to view \$filter_type destinations")
    def selectFilterButton(String filter_type) {
        seeWhereWeFlyPage.selectFilterButton(filter_type)
    }
}
