package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By


class AlistFaqPage extends BasePage {

    private static final String PATH = "/fragments/app/messages/messages_eb_eligibility_alist_swacom.shtml"
    private static final By A_LIST_MESSAGE = By.tagName("strong")

    AlistFaqPage(WebDriverProvider driverProvider) {
        super(driverProvider,PATH)
    }

    String getRelativePath() {
        return PATH
    }

    def verifyAlistFaqMessage() {
        waitForElement(A_LIST_MESSAGE).getText().shouldBe "Simply fly 25 qualifying one-way flights or earn 35,000 Tier Qualifying Points* in a calendar year."
    }

}
