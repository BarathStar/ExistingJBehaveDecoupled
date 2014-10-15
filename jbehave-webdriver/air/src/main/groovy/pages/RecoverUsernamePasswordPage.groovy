package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class RecoverUsernamePasswordPage extends BasePage{

    public RecoverUsernamePasswordPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    def isCurrentPage() {
        isElementWithTextPresent(By.id("page_title"), "Recover your Username or Password")
    }

     def verifyOnCurrentPage() {
        isCurrentPage().shouldBe true
    }
}
