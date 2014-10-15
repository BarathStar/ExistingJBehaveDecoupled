package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import state.Flow

public class SwabizTermsAndConditionsPage extends BasePage{

    private static final String PATH = "swabiz/selfEnroll"
    private static final By ACCEPT_TERMS_CHECKBOX = By.id("txtAcceptTerms")
    private static final By SUBMIT_BUTTON = By.id("btnSubmit")

    Flow flow

    public SwabizTermsAndConditionsPage(WebDriverProvider driverProvider) {
        super(driverProvider, "/${PATH}")
    }

    @Override
    public String getRelativePath() {
        return PATH
    }

    def open() {
        flow.isSwabiz = true
        get("${Domains.swabizDomain}/${PATH}")
        WaitForPageToLoad
    }

    def selectAcceptTermsCheckbox() {
        waitForElement(ACCEPT_TERMS_CHECKBOX).click()
    }

    def clickSubmitButton() {
        waitForElement(SUBMIT_BUTTON).click()
    }
}
