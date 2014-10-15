package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.Flow
import state.ScenarioState

class NonRevSecurityDocumentViewPage extends BasePage {

    ScenarioState scenarioState
    Flow flow

    private static final String PATH = "/non-rev/security-document-view.html"

    private static final By CHECK_IN_BUTTON = By.id("printDocumentsButton")
    private static final By TITLE_SELECTOR = By.className("swa_page_header_title")


    private final title = "Security Document"

    String getRelativePath() {
        return PATH
    }

    public NonRevSecurityDocumentViewPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH);
    }

    def verify(String pnr, String firstName, String lastName) {
        verifyPage()
        waitForElement(TITLE_SELECTOR).text.shouldBeEqual(title)
        shownInPage(firstName)
        shownInPage(lastName)
    }

    def clickCheckinButton() {
        waitForElement(CHECK_IN_BUTTON).click()
    }
}
