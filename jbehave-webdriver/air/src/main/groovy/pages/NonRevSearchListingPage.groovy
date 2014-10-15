package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import state.Flow
import state.ScenarioState

class NonRevSearchListingPage extends BasePage {

    ScenarioState scenarioState
    Flow flow

    private static final String PATH = "/non-rev/search-listing.html"
    private static final By GET_SECURITY_DOCUMENT_BUTTON = By.linkText("Get Security Document")

    private static final By CONFIRMATION_NUMBER_FIELD = By.id("swa_feature_nonRev_retrieveListing_form_passengerConfirmationNumber")
    private static final By FIRST_NAME_FIELD = By.id("swa_feature_nonRev_retrieveListing_form_passenger_firstName")
    private static final By LAST_NAME_FIELD = By.id("swa_feature_nonRev_retrieveListing_form_passengerLastName")
    private static final By SUBMIT_BUTTON = By.id("submitButton")

    private final title = "Retrieve/Cancel Nonrevenue Listing"

    String getRelativePath() {
        return PATH
    }

    public NonRevSearchListingPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH);
    }

    def fillFormAndSubmit(String pnr, String pnr_firstName, String pnr_lastName) {
        waitForElement(CONFIRMATION_NUMBER_FIELD).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(FIRST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_firstName)
        waitForElement(LAST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_lastName)
        waitForElement(SUBMIT_BUTTON).click()
    }
}
