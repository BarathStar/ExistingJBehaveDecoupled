package pages.elements

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class BookATripModal extends FlightSearchForm {

    private static final By POINTS_LINK = By.id("imgpoints")

    public BookATripModal(WebDriverProvider driverProvider) {
        super(driverProvider);
        GrooBe.activate()
    }

    def verifyModal() {
        verifyElementPresent("Book a flight modal", By.id("tripSearchModalSubmit"))
    }

    def clickSearchButton() {
        waitForElement(By.id("tripSearchModalSubmit"),false).click()

    }

    def clickCloseLink() {
        waitForElement(By.id('close')).click()
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def  VerifyModalStationFieldsPopulated() {
        findElement(By.id("originAirport_displayed")).getText().shouldNotBe null, "No data found "
        findElement(By.id("destinationAirport_displayed")).getText().shouldNotBe null, "No data found "
    }

    def closeModal() {
        findElement(By.xpath("//div[@id='close']")).click()
    }

    def clickOnPoints() {
        waitForElement(POINTS_LINK).click()
    }
}
