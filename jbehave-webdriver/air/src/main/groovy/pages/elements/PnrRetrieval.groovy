package pages.elements

import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import pages.BasePage
import state.Flow
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import static org.junit.Assert.fail
import com.github.tanob.groobe.GrooBe
import org.openqa.selenium.By

public class PnrRetrieval extends BasePage {

    private static final By AIRTRAN_LINK = By.id("airTranLink")
    private final static By CONFIRMATION_NUBMER = By.id("confirmationNumber")
    private final static By FIRST_NAME = By.id("firstName")
    private final static By LAST_NAME = By.id("lastName")
    private final static By SUBMIT_BUTTON = By.id("submitButton")

    public PnrRetrieval(WebDriverProvider driverProvider) {
        super(driverProvider)
        GrooBe.activate()
    }

    String getRelativePath() {
        return "" //Not for the element; just satisfy the contract
    }

    void retrievePnrAndSubmit(String pnr, String pnrFirstName, String pnrLastName) {
        fillIn(CONFIRMATION_NUBMER, pnr)
        fillIn(FIRST_NAME, pnrFirstName)
        fillIn(LAST_NAME, pnrLastName)
        waitForElement(SUBMIT_BUTTON).click()
    }

    void verifyInvalidPnrOopsMessage() {
        shouldShowOopsMessage("We were unable to retrieve your reservation from our database. Verify the following:")
        String oopsText = getOopsElement().getText()
        oopsText.shouldNotContain "We're sorry,", "We're sorry text is still present at the top of the Oops message."
        oopsText.shouldNotContain ", airtran.com,", "Third bullet in the Oops message still contains the airtran.com text."
        oopsText.shouldContain "Travel Agencies cannot be accessed on southwest.com.", "Third bullet in the Oops message still contains the airtran.com text."
        oopsText.shouldNotContain "Travel Agencies cannot be accessed online", "Third bullet text at end was NOT removed/changed"
        waitForElement(AIRTRAN_LINK).getText().shouldEqual "airtran.com"
    }

    void clickAirTranLink() {
        waitForElement(AIRTRAN_LINK).click()
    }

    void verifyTransitionToAirTran() {
        Boolean found = false
        webDriver().windowHandles.each {windowHandle ->
            webDriver().switchTo().window(windowHandle)
            if (getTitle().contains("AirTran Reservations - Find Itinerary")) {
                found = true
                return
            }
        }

        found.shouldBe true, "The airtran.com link did not open the 'AirTran Reservations -  Find Itinerary' page."
    }
}
