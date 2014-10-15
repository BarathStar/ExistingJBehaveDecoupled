package steps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.*
import state.ScenarioState
import org.jbehave.core.annotations.Then
import org.openqa.selenium.By

class SwabizHomePageSteps {

    SwaBizHomePage swabizHomePage

    @When("I click on Book Travel on the Swabiz home page")
    def selectBookTravel() {
        swabizHomePage.clickOnBookTravel()
    }

    @When ("I anonymously log into a SWABIz account")
    def anonymousLogIntoSwabiz() {
        swabizHomePage.anonymousLogIn()
    }
}
