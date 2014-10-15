package steps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.SwabizBookTravelPage
import state.ScenarioState
import org.jbehave.core.annotations.Then
import org.openqa.selenium.By

public class SwabizBookTravelSteps {

    SwabizBookTravelPage swabizBooktravelPage

    @When("I click on Site Map link")
    def clickOnCarTabInWidget() {
        swabizBooktravelPage.clickOnSiteMapLink()
    }

}
