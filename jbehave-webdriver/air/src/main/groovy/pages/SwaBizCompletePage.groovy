package pages

import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

public class SwaBizCompletePage extends BasePage {

    private static final PATH = "swabiz/selfEnroll"
    private static final By COMPANY_ID = By.cssSelector(".tableRowOdd b")

    public SwaBizCompletePage(WebDriverProvider driverProvider) {
        super(driverProvider, "/${PATH}")
    }

    String getRelativePath() {
        return PATH
    }

    def String getCompanyId() {
        return waitForElement(COMPANY_ID).text
    }
}
