package steps

import org.jbehave.core.annotations.Then
import pages.AwardHQMartizPage

class AwardHQMaritzSteps {

    AwardHQMartizPage awardHQMartizPage

    @Then("I am redirected to AwardHQ page")
    def verifyAwardHQPage(){
        String winHandleBefore = awardHQMartizPage.webDriver().getWindowHandle()
        awardHQMartizPage.switchToOpenWindow(winHandleBefore, "AwardHQ")
        awardHQMartizPage.verifyAwardHQPageUrl()
    }
}
