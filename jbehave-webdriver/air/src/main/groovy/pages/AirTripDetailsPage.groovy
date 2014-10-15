package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class AirTripDetailsPage extends BasePage {
    private static final String PATH = "/account/travel/upcoming-trips/air-trip-details"
    private static final By CHANGE_RESERVATION_LINK = By.id("changeReservationLink")
    private static final By CANCEL_RESERVATION_LINK = By.id("cancelReservationLink")
    private static final By EARLY_BIRD_BUTTON = By.className("earlybirdCheckIn_button")
    private static final By PNR_DIV = By.className( "confirmation_number" )
    private WebDriver driver

    AirTripDetailsPage(WebDriverProvider driverProvider){
        super(driverProvider,PATH)
        driver = driverProvider.get()
    }

    def verifyAirTranSeatSelectionButtonVisible() {
        String pnr = driver.findElement( PNR_DIV ).getText()
        By OUTBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId_" + pnr + "_0")
        By INBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId_" + pnr + "_1")

        (isElementDisplayed(INBOUND_SEAT_SELECTION_BUTTON) || isElementDisplayed(OUTBOUND_SEAT_SELECTION_BUTTON)).shouldBe true, "No seat selection button found"
    }

    def verifyAirTranSeatSelectionButtonNotVisible() {
        String pnr = driver.findElement( PNR_DIV ).getText()
        By OUTBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId_" + pnr + "_0")
        By INBOUND_SEAT_SELECTION_BUTTON = By.id("seatSelectionButtonId_" + pnr + "_1")

        verifyElementNotPresent("Inbound Seat Selection Button (seatSelectionButtonId1) ", INBOUND_SEAT_SELECTION_BUTTON);
        verifyElementNotPresent("Outbound Seat Selection Button (seatSelectionButtonId0) ", OUTBOUND_SEAT_SELECTION_BUTTON);
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def clickOnChangeReservationLink() {
        waitForElement(CHANGE_RESERVATION_LINK).click()
    }

    def clickOnCancelReservationLink() {
        waitForElement(CANCEL_RESERVATION_LINK).click()
    }

    def clickOnEarlyBirdButton() {
        waitForElement(EARLY_BIRD_BUTTON).click()
    }

    def getChangeReservationLink() {
        waitForElement(CHANGE_RESERVATION_LINK)
    }

    def getCancelReservationLink() {
        waitForElement(CANCEL_RESERVATION_LINK)
    }

    def getEarlyBirdButton() {
        waitForElement(EARLY_BIRD_BUTTON)
    }

    def verifyAirConfirmationNumber() {
        isElementPresent(PNR_DIV).shouldBe true, "Airt confirmation number was not present in the view details page"
    }
}
