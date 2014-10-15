package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.Flow
import state.ScenarioState

class NonRevSecurityDocumentLandingPage extends BasePage {

    ScenarioState scenarioState
    Flow flow

    private static final String PATH = "/non-rev/security-document-landing.html"
    private static final By GET_SECURITY_DOCUMENT_BUTTON = By.linkText("Get Security Document")

    private static final By CONFIRMATION_NUMBER_FIELD = By.id("swa_feature_nonRev_securityDocument_form_passengerConfirmationNumber")
    private static final By FIRST_NAME_FIELD = By.id("swa_feature_nonRev_securityDocument_form_passenger_firstName")
    private static final By LAST_NAME_FIELD = By.id("swa_feature_nonRev_securityDocument_form_passenger_LastName")
    private static final By SUBMIT_BUTTON = By.id("submitButton")

    private final title = "Security Document"

    String getRelativePath() {
        return PATH
    }

    public NonRevSecurityDocumentLandingPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH);
    }

    def fillFormAndSubmit(String pnr, String pnr_firstName, String pnr_lastName) {
        waitForElement(FIRST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_firstName)
        waitForElement(LAST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_lastName)
        waitForElement(CONFIRMATION_NUMBER_FIELD).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(SUBMIT_BUTTON).click()
    }
}
