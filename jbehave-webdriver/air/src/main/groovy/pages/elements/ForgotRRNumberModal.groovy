package pages.elements

import pages.BasePage
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class ForgotRRNumberModal extends BasePage {

    public ForgotRRNumberModal(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def getLookupRRAccountLink() {
        findLinkInSection(findElement(modal()), "Lookup Rapid Rewards Account #")
    }

    private By modal() {
        return By.className("popup_help_box_forgot_rr")
    }

    def verifyForgotRRNumberModal() {
       isElementDisplayed(modal()).shouldBe true
    }


}
