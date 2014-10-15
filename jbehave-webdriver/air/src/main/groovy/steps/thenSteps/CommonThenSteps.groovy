package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.BasePage


class CommonThenSteps{

    BasePage basePage

    @Then("I see an oops with the message: \$message")
    def verifyOopsMessageAppearWith(String message){
        basePage.shouldShowOopsMessage(message)
    }
}
