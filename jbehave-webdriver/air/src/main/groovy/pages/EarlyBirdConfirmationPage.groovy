package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.elements.StopLogicInfo
import util.ItineraryData

public class EarlyBirdConfirmationPage extends BasePage {

    private static final By EARLY_BIRD_CONFIRMATION_TITLE = By.className("earlyBirdConfirmationTitle")
	StopLogicInfo stopLogicInfo
	ItineraryData itineraryData

	private static final By STOPS_COUNT_CONTAINER = By.cssSelector(".airItineraryTable .tableRowOdd .stops")
	private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_ON = By.cssSelector(".airItineraryTable .airItineraryFlightRouting .routingDetailsSeparator .routingDetailsStops")
    private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_OFF = By.cssSelector(".airItineraryTable .airItineraryFlightRouting .routingDetailsStops.routingDetailsSeparator")
    private static final By AIRTRAN_SEAT_SELECTION_BUTTON = By.id( "seatSelectionButtonId" )

    String getRelativePath() {
        return "/flight/early-bird-confirmation.html"
    }

    def EarlyBirdConfirmationPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, "/flight/early-bird-confirmation.html");
    }

    def confirmPurchaseSuccessfulMessage() {
       waitForElement(EARLY_BIRD_CONFIRMATION_TITLE).text.shouldContain("EarlyBird Check-In Option Purchase")
    }

	def verifyStopsCountInfoOnDepartureAirItineraryInEarlyBirdPage() {
		int notConnectingStopsCount = itineraryData.getOutboundNumberOfNotConnectingStops()
		int connectingStopsCount = itineraryData.getOutboundNumberOfConnectingStops()
		stopLogicInfo.verifyStopLogicAndPlaneChangeCount(STOPS_COUNT_CONTAINER, notConnectingStopsCount, connectingStopsCount)
	}

	def verifyChangePlaneInfoOnDepartureAirItineraryInEarlyBirdPage(){
        String changePlaneCityCode = itineraryData.outboundConnectingStation
        try {
            stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_ON, changePlaneCityCode)
        } catch (Exception e) {
            stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_OFF, changePlaneCityCode)
        }

    }

    def verifyAirTranSeatSelectionButton() {
        isElementDisplayed(AIRTRAN_SEAT_SELECTION_BUTTON).shouldBe true, "No seat selection button found"
    }

    def verifyAirTranSeatSelectionButtonNotDisplayed() {
        isElementDisplayed(AIRTRAN_SEAT_SELECTION_BUTTON).shouldBe false, "AirTran Seat Selection Button found"
    }
}
