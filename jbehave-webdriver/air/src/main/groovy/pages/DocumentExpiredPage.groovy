package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class DocumentExpiredPage extends BasePage {

    private static final By ERROR_TITLE = By.id("errorTitle")

    public DocumentExpiredPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    @Override
    String getRelativePath() {
        return ""
    }

    def verifyErrorInformation() {
        waitForElement(ERROR_TITLE).getText().shouldContain "Document Expired"
    }
}
