package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import pages.elements.AutoCompleteWidget
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

class CheckFlightStatusPage extends BasePage {

    private static final By FLIGHT_STATUS_INFORMATION_SUBMIT_BUTTON = By.id("flightStatusInformationForm_submitButton")

    AutoCompleteWidget autoCompletePage

    def CheckFlightStatusPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/flight-status-select.html')
    }

    def openCheckFlightStatusPage() {
        super.open()
        verifyPage()
    }

    String getRelativePath() {
        return "flight-status-select.html"
    }

    public void submit() {
        waitForElement(FLIGHT_STATUS_INFORMATION_SUBMIT_BUTTON).click()
        verifyPage()
    }

    void enterFlightNumber(String flightNumber) {
        fillIn("flightNumber", flightNumber)
    }
}
