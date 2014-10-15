package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.Flow
import state.ScenarioState
import domain.Passenger

class NonRevCancelConfirmationPage extends BasePage {

    ScenarioState scenarioState
    Flow flow

    private static final String PATH = "/non-rev/cancel-confirmation.html"
    private static final By TITLE_ELEMENT = By.className("swa_page_header_title")

    private static final By UPDATE_OOPS_ELEMENT = By.id("errorFromAjaxCall")

    private final title = "Canceled Itinerary"

    String getRelativePath() {
        return PATH
    }

    public NonRevCancelConfirmationPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH);
    }

    public verifyPage(String pnr, Passenger passenger) {
        verifyPage()
        waitForElement(TITLE_ELEMENT).text.shouldBe(title)
        shownInPage(pnr)
    }
}
