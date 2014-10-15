package pages

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class AirTranSeatSelectPage extends BasePage {

    private static final String AIR_TRAN_SEAT_SELECTION_URL = "/reservations/seatSelection.html"

    private static final By OUTBOUND_SEAT_SELECTION_UNABLE_TO_RETRIEVE_MESSAGE_ITINERARY_TABLE = By.id("seatSelectionUnableToRetrieveMessageItineraryTable0")
    private static final By SEAT_SELECTION_BUTTON = By.id("seatSelectionButton")

    public AirTranSeatSelectPage(WebDriverProvider driverProvider) {
        super(driverProvider);
        GrooBe.activate()
    }

    void verifySeatSelectionElementNotPresentFor(String outboundOrInbound ) {
        if (outboundOrInbound == "outbound") {
            verifyElementNotPresent("seatSelectionTextId1", By.id("seatSelectionTextId1"))
        } else if (outboundOrInbound == "inbound") {
            verifyElementNotPresent("seatSelectionTextId0", By.id("seatSelectionTextId0"))
        }
    }

    void clickSeatSelectionButtonOnCheckInPageOnly(){
        waitForElement(SEAT_SELECTION_BUTTON).click()
    }

    String getRelativePath() {
        return AIR_TRAN_SEAT_SELECTION_URL
    }

    void verifySeatSelectionUrl() {
        getCurrentUrl().shouldContain getRelativePath()
    }

    public void verifyOutboundSeatSelectionNavitaireModeMessageForConfirmationPage(){
        sleep( 10000 )
        waitForElement(OUTBOUND_SEAT_SELECTION_UNABLE_TO_RETRIEVE_MESSAGE_ITINERARY_TABLE).text.shouldContain "Seat selection unavailable. Please try again later", "Did not find Navitaire Mode message"
    }

}
