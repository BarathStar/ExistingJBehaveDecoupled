package pages

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.ScenarioState

class UpgradeToBusinessSelectFormPage extends BasePage {

    ScenarioState scenarioState

    private final By SUBMIT_BUTTON = By.id("submitButton")
    private final By PNR_INPUT = By.id("confirmationNumber")
    private final By FIRST_NAME_INPUT = By.id("firstName")
    private final By LAST_NAME_INPUT = By.id("lastName")

    public UpgradeToBusinessSelectFormPage(WebDriverProvider driverProvider) {
        super(driverProvider)
        GrooBe.activate()
    }

    void clickToContinue() {
        performClickAction(SUBMIT_BUTTON)
    }

    void fillForm(){
        waitForElement(PNR_INPUT).sendKeys DELETE_EXISTING + scenarioState.getLastAirReservation().getAdultPnr()
        waitForElement(FIRST_NAME_INPUT).sendKeys DELETE_EXISTING + scenarioState.getLastAirReservation().passengers.get(0).firstName
        waitForElement(LAST_NAME_INPUT).sendKeys DELETE_EXISTING + scenarioState.getLastAirReservation().passengers.get(0).lastName
    }
}
