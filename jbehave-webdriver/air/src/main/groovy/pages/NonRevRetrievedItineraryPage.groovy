package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.Flow
import state.ScenarioState

class NonRevRetrievedItineraryPage extends BasePage {

    ScenarioState scenarioState
    Flow flow

    private static final String PATH = "/non-rev/retrieve-detail.html"

    private static final By CANCEL_BUTTON = By.id("cancelButton")
    private static final By DO_NOT_CANCEL_BUTTON = By.id("doNot_cancelButton")
    private static final By TITLE_SELECTOR = By.className("swa_page_header_title")

    private final title = "Retrieved Itinerary"

    String getRelativePath() {
        return PATH
    }

    public NonRevRetrievedItineraryPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH);
    }

    def verify(String pnr, String firstName, String lastName) {
        verifyPage()

        waitForElement(TITLE_SELECTOR).text.shouldBeEqual(title)
        shownInPage(pnr)
        shownInPage(firstName)
        shownInPage(lastName)
    }

    def cancelReservation() {
        waitForElement(CANCEL_BUTTON).click()
    }

    def doNotCancelReservation() {
        waitForElement(DO_NOT_CANCEL_BUTTON).click()
    }
}
