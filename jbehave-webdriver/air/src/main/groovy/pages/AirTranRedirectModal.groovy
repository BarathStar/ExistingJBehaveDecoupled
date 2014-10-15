package pages

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import util.ItineraryData

import static org.junit.Assert.fail

class AirTranRedirectModal extends BasePage {
    private static final By MODAL_ORIGIN_STATION = By.id("modalOriginStation")
    private static final By MODAL_DESTINATION_STATION = By.id("modalDestinationStation")

    ItineraryData itineraryData

    public AirTranRedirectModal(WebDriverProvider driverProvider) {
        super(driverProvider);
        GrooBe.activate()
    }
    def viewAirTranRedirectModalWithWcmContent (String schedule = null) {
        waitForElementDisplayed(By.id("airTranRedirectModal"))
        verifyWCMContentPresent(schedule)
    }

    private def verifyWCMContentPresent(String schedule) {
        String textWcm1 = findElement(By.cssSelector("p.redirectModalWCM1")).getText()
        String textRedirectContent = findElement(By.cssSelector("div.content")).getText()
        if (textWcm1.isEmpty()) {
            fail "WCM1 in AirTran International Modal should not be empty"
        }
        String textWcm2 = findElement(By.cssSelector("p.redirectModalWCM2")).getText()
        if (textWcm2.isEmpty()) {
            fail "WCM2 in AirTran International Modal should not be empty"
        }
        if (schedule != null && !textRedirectContent.contains(schedule)) {
            fail "Schedule Redirect Not Available Message is missing"
        }
    }

    def clickContinueButton() {
        waitForElement(By.id('airTranContinueButton')).click()
    }

    def clickContinueButtonOnModalFromSelectFlightsPage() {
        findElements(By.id('airTranContinueButton')).each {it -> it?.click()}
    }

    def clickCancelButton() {
        waitForElement(By.id('airTranRedirectModalCancel')).click()
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    void verifyOneWayOutBoundInboundFlightsDisplayed(String origination, String destination) {
        waitForElement(MODAL_ORIGIN_STATION).text.shouldContain origination, "Departure Station Not Found"
        waitForElement(MODAL_DESTINATION_STATION).text.shouldContain destination, "Arrival Station Not Found"
    }

    void verifyRoundTripOutBoundInboundFlightsDisplayed(String origination, String destination) {
        waitForElement(MODAL_ORIGIN_STATION).text.shouldContain origination, "Departure Station Not Found"
        waitForElement(MODAL_DESTINATION_STATION).text.shouldContain destination, "Arrival Station Not Found"
    }

    def determineTripType(String typeOfTrip) {
        switch (typeOfTrip) {

            case ("One-way"):
                verifyOneWayOutBoundInboundFlightsDisplayed(itineraryData.departureStation, itineraryData.arrivalStation)
                break

            case ("Round-trip"):
                verifyRoundTripOutBoundInboundFlightsDisplayed(itineraryData.departureStation, itineraryData.arrivalStation)
                break

            default:
                fail("Yo did not pass")

                break
        }
    }

}
