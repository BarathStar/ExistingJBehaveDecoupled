package pages

import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.jbehave.web.selenium.WebDriverProvider
import org.joda.time.DateTime
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import util.ItineraryData
import org.openqa.selenium.WebElement
import org.joda.time.LocalDateTime
import org.joda.time.DateTimeZone

public class FlightStatusConfirmationPage extends BasePage {

    private static final By FLIGHT_NUMBER_LABEL = By.className("swa_feature_flightStatusNotification_results_detailsTop_flightInfo_flight_number")
    private static final By DEPARTURE_AIRPORT_LABEL = By.className("swa_feature_flightStatusNotification_results_detailsTop_flightInfo_flight_airport")
    private static final By ALERT_SCHEDULE_LABEL = By.className("swa_feature_flightStatusNotification_results_detailsTop_notification_header_reminderSchedule")
    private static final By DEPARTURE_DATE_LABEL = By.className("swa_feature_flightStatusNotification_results_detailsTop_notification_scheduledTime")

    private static final String FLIGHT_NUMBER_VALUE = "6884"
    private static final String DEPARTURE_AIRPORT_VALUE = "Dallas, TX(DAL)"
    private static final String ALERT_SCHEDULE_VALUE = "1 Hour Before"

    private static final String FLIGHT_STATUS_CONFIRMATION_URL = "/flight/flight-notification-confirm.html"

    ItineraryData itineraryData
    OutboundAndReturnDatesAndPopUp calendarPopUp

    public FlightStatusConfirmationPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return FLIGHT_STATUS_CONFIRMATION_URL  //TODO change as needed for page validation.
    }

    def verifyOnFlightStatusConfirmationPage() {
        verifyCurrentPageLocation()
        verifyFlightNumber()
        verifyDepartureAirport()
        verifyAlertSchedule()
        verifyEstimatedTime()
    }
	
	def verifyOnDelayedFlightStatusConfirmationPage() {
		verifyCurrentPageLocation()
		verifyFlightNumber()
		verifyDepartureAirport()
		verifyAlertSchedule()
		verifyDelayedEstimatedTime()
	}

    def verifyFlightNumber() {
        verifyTextPresent(FLIGHT_NUMBER_VALUE, FLIGHT_NUMBER_LABEL)
    }

    def verifyDepartureAirport() {
        verifyTextPresent(DEPARTURE_AIRPORT_VALUE, DEPARTURE_AIRPORT_LABEL)
    }

    def verifyAlertSchedule() {
        verifyTextPresent(ALERT_SCHEDULE_VALUE, ALERT_SCHEDULE_LABEL)
    }

    def verifyEstimatedTime() {
        verifyTextPresent(new DateTime().toString("EEEE, MMMM d, YYYY") + "    Estimated Time:", DEPARTURE_DATE_LABEL)
    }
	
	def verifyDelayedEstimatedTime() {
		verifyTextPresent(LocalDateTime.now(DateTimeZone.UTC).plusHours(4).toString("EEEE, MMMM d, YYYY") + "    Estimated Time:", DEPARTURE_DATE_LABEL)
	}
}