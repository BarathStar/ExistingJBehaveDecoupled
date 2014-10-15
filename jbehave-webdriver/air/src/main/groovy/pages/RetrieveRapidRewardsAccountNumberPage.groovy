package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By


class RetrieveRapidRewardsAccountNumberPage extends BasePage{

    RetrieveRapidRewardsAccountNumberPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }


    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    def isCurrentPage() {
        isElementWithTextPresent(By.id("swa_page_header_title"), "Retrieve Rapid Rewards/Account Number")
    }

     def verifyOnCurrentPage() {
        isCurrentPage().shouldBe true
    }
}
