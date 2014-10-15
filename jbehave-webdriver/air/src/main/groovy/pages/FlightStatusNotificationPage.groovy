package pages

import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import util.ItineraryData
import org.openqa.selenium.WebElement
import org.joda.time.LocalDateTime
import org.joda.time.DateTimeZone

public class FlightStatusNotificationPage extends BasePage {

    private static final By ARRIVAL_RADIO_BUTTON = By.id("arrival_or_departure_arrival")
    private static final By DEPARTURE_RADIO_BUTTON = By.id("arrival_or_departure_departure")
    private static final By EMAIL_RADIO = By.id("mediaType2")
    private static final By TEXT_RADIO = By.id("mediaType1")
    private static final By EMAIL_ADDRESS = By.id("emailAddress")
    private static final By EMAIL_ADDRESS_CONFIRMATION = By.id("confirmationEmail")
    private static final By FLIGHT_NUMBER = By.id("flightNumber")
    private static final By DEPARTURE_STATION = By.id("originAirport_displayed")
    private static final By ARRIVAL_STATION = By.id("destinationAirport_displayed")
    private static final By REMINDER_SCHEDULE = By.id("reminderSchedule")
    private static final By RAPID_REWARDS_NUMBER = By.className("passenger_row_rr_number")
    private static final By SWA_PAGE_HEADER_TITLE = By.id("swa_page_header_title")
    private static final By CREATE_NOTIFICATION_TAB = By.id("swa_feature_flightStatusNotification_tab_1")
    private static final By VIEW_OR_EDIT_NOTIFICATION_TAB = By.id("swa_feature_flightStatusNotification_tab_2")
    private static final By FLIGHT_INFORMATION_LEGEND = By.cssSelector("fieldset#swa_feature_flightStatusNotification_flightInformation legend")
    private static final By ALERT_SCHEDULE_LEGEND = By.cssSelector("fieldset#swa_feature_flightStatusNotification_alertSchedule legend")
    private static final By LOOK_UP_FLIGHT_NUMBER_LINK = By.xpath("//*[@id='swa_feature_flightStatusNotification_flightInformation']/div[2]/div[2]/a")

    private static final By CREATE_SUBMIT_BUTTON = By.id("submitButton")
    private static final By EDIT_SUBMIT_BUTTON = By.className("swa_buttons_submitButton")
    private static final By FLIGHT_NUMBERS_POP_UP = By.className("swa_links_defaultLink")
    public static final By ALTER_OOPS_CLASS = By.className("oopsError_expandible_wrapper")
    private static final By UPDATE_SUBMIT_BUTTON = By.className("swa_buttons_flightStatus_notification_update")

    private static final By FLIGHT_NUMBER_NOTIFICATION_CONFIRMATION = By.className("swa_feature_flightStatusNotification_results_detailsTop_flightInfo_flight_number")
    private static final By FLIGHT_AIRPORT_NOTIFICATION_CONFIRMATION = By.className("swa_feature_flightStatusNotification_results_detailsTop_flightInfo_flight_airport")
    private static final By REMINDER_NOTIFICATION_CONFIRMATION = By.className("swa_feature_flightStatusNotification_results_detailsTop_notification_header_reminderSchedule")
    private static final By SCHEDULE_NOTIFICATION_CONFIRMATION = By.className("swa_feature_flightStatusNotification_results_detailsTop_notification_scheduledTime")

    private static final String CREATE_TAB_URL = "/flight/flight-notification-subscribe.html"
    private static final String EDIT_TAB_URL = "/flight/flight-notification-retrieve.html"
    private static final String DEPARTURE_DATE = "outboundDate2"
    private static final String DATE_OOPS = "You may set a Flight Status Notification for the next 10 days. If your date is further out, please come back at a later time."
    private static final String DATE_FORMAT = "MM/dd/yyyy"
    private static final String REMINDER_NOTIFICATION_HOURS = "3 Hours"
    private static final String FLIGHT_NOTIFICATION_UPDATE_URL = "/flight/flight-notification-update.html"
    private static final String SCHEDULE_NOTIFICATION_LABEL = "Scheduled Time:"
    private static final String SWA_PAGE_HEADER_TITLE_EXPECTED = "Flight Status Notification"

    private boolean IS_CREATING = true

    ItineraryData itineraryData
    OutboundAndReturnDatesAndPopUp calendarPopUp

    public FlightStatusNotificationPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def openFlightStatusCreateNotification() {
        IS_CREATING = true
        get(Domains.dotcomDomain + CREATE_TAB_URL)
        checkNoOops()
    }

    def openFlightStatusViewOrEditNotification() {
        IS_CREATING = false
        get(Domains.dotcomDomain + EDIT_TAB_URL)
        checkNoOops()
    }

    def clickAndSwitchLookupFlightNumberLink() {
        waitForElement(By.partialLinkText("Flight Number")).click()
        switchTo().window("flightStatusLookup")
        checkNoOops()
    }

    void fillFormAndSubmitCreation() {
        fillInEmail()
        fillInFlightNumber()
        fillInAirports()
        fillInDepartureDate()
        clickCreateSubmit()
    }

    void fillFormAndSubmitForDelayedFlight() {
        fillInEmail()
        fillInDelayedFlightNumber()
        fillInAirports()
        fillInDelayedDepartureDate()
        clickCreateSubmit()
    }

    void fillFormAndSubmitEdition() {
        fillInEmail()
        fillInFlightNumber()
        fillInAirports()
        fillInDepartureDate()
        clickEditSubmit()
    }

    private void clickCreateSubmit() {
        waitForElement(CREATE_SUBMIT_BUTTON).click()
    }

    private void clickDepartureRadio() {
        waitForElement(DEPARTURE_RADIO_BUTTON).click()
    }

    private void clickEditSubmit() {
        waitForElement(EDIT_SUBMIT_BUTTON).click()
    }

    private void fillInEmail() {
        String testEmail = "test@test.com"

        waitForElement(EMAIL_RADIO).click()
        waitForElement(EMAIL_ADDRESS).sendKeys DELETE_EXISTING + testEmail
        if(IS_CREATING) waitForElement(EMAIL_ADDRESS_CONFIRMATION).sendKeys DELETE_EXISTING + testEmail
    }

    private void fillInFlightNumber() {
        waitForElement(FLIGHT_NUMBER).sendKeys DELETE_EXISTING + "11"
    }

    private void fillInDelayedFlightNumber() {
        waitForElement(FLIGHT_NUMBER).sendKeys DELETE_EXISTING + "6884"
    }

    private fillInAirports() {
        fillIn(DEPARTURE_STATION, itineraryData.departureStation, false)
        fillIn(ARRIVAL_STATION, itineraryData.arrivalStation, false)
    }

	private void fillInDelayedDepartureDate() {
		LocalDateTime currentDatePlusFourOurs = LocalDateTime.now(DateTimeZone.UTC).plusHours(4)
		calendarPopUp.typeInOutboundOrInboundDate(currentDatePlusFourOurs.toDate().format(DATE_FORMAT), DEPARTURE_DATE)
		waitForElement(By.id(DEPARTURE_DATE)).sendKeys(Keys.ESCAPE)
	}

    private void fillInDepartureDate() {
        calendarPopUp.typeInOutboundOrInboundDate(itineraryData.departureDate.format(DATE_FORMAT), DEPARTURE_DATE)
        waitForElement(By.id(DEPARTURE_DATE)).sendKeys(Keys.ESCAPE)
    }

    private String getExpectedAlterOopsMessage() {
        WebElement oopsElement = waitForElement(ALTER_OOPS_CLASS,false,DEFAULT_WAIT_LIMIT_FOR_ELEMENT_THAT_MAY_EXIST)
        oopsElement.shouldNotBe null, "Oops element was not found"
        oopsElement.getText().shouldNotBe null, "Oops element text was null"
        return oopsElement.getText()
    }

    def verifyDateOopsPresentOnCreate() {
        shouldShowOopsMessage(DATE_OOPS)
    }

    def verifyDateOopsPresentOnEdit() {
        String expectedOops = getExpectedAlterOopsMessage()
        expectedOops.shouldContain DATE_OOPS, "The Oops message '$expectedOops' has not been found within the text" + expectedOops
    }

    def verifyCalendarLimit() {
        Date maxDate = calendarPopUp.getMaxDateLimit()
        (itineraryData.departureDate <= maxDate).shouldBe true, "Only dates within the 10 day notification window should be enabled for selection"
    }

    def lookUpFlightNumber() {
        clickAndSwitchLookupFlightNumberLink()
        fillInAirports()
        openCalendar()
    }

    def openCalendar() {
	calendarPopUp.openCalendarPopUp()
	}

    void fillFormAndSubmitForOnTimeFlight() {
        fillInEmail()
        fillOnTimeFlightNumber()
        fillInAirports()
        fillInDepartureDate()
        clickEditSubmit()
    }
    
    private void fillOnTimeFlightNumber() {
           waitForElement(FLIGHT_NUMBER).sendKeys DELETE_EXISTING + itineraryData.departingFlight_number
    }
 
    private void clickUpdateSubmit() {
        waitForElement(UPDATE_SUBMIT_BUTTON).click()
    }

    def verifyFlightNotificationConfirmation() {
        getCurrentUrl().shouldContain FLIGHT_NOTIFICATION_UPDATE_URL, "The URL should be ${FLIGHT_NOTIFICATION_UPDATE_URL}"
        waitForElement(FLIGHT_NUMBER_NOTIFICATION_CONFIRMATION).text.shouldContain itineraryData.departingFlight_number, "The Flight number notification is not valid"
        waitForElement(FLIGHT_AIRPORT_NOTIFICATION_CONFIRMATION).text.shouldContain itineraryData.arrivalStation, "The Airport notification is not valid"
        waitForElement(REMINDER_NOTIFICATION_CONFIRMATION).text.shouldContain REMINDER_NOTIFICATION_HOURS, "The Reminder notification is not valid"
        waitForElement(SCHEDULE_NOTIFICATION_CONFIRMATION).text.shouldContain SCHEDULE_NOTIFICATION_LABEL, "The Schedule notification is not valid"
    }

    def verifyCreateTabURL(){
        getCurrentUrl().shouldContain CREATE_TAB_URL, "The URL should be ${CREATE_TAB_URL}"
    }

    def verifyDepartureDate(){
        waitForElement(By.id(DEPARTURE_DATE)).getAttribute("value").shouldBe itineraryData.departureDate.format(DATE_FORMAT), "The Departure date is not valid"
    }

    def verifyFlightNumber(){
        waitForElement(FLIGHT_NUMBER).getAttribute("value").shouldBe itineraryData.flightNumbers.get(0).replaceAll('#',''), "The Flight number is not valid"
    }

    def verifyEmailRadioIsChecked(){
        waitForElement(EMAIL_RADIO).getAttribute("checked").shouldBe "true", "The E-mail radio should be checked"
    }

    def verifyDepartureRadioIsChecked(){
        waitForElement(DEPARTURE_RADIO_BUTTON).getAttribute("checked").shouldBe "true", "The Departure radio should be checked"
    }

    def verifyDepartureStation(){
        waitForElement(DEPARTURE_STATION).getAttribute("value").shouldContain itineraryData.departureStation, "The Departure station is not valid"
    }

    def verifyArrivalStation(){
        waitForElement(ARRIVAL_STATION).getAttribute("value").shouldContain itineraryData.arrivalStation, "The Arrival station is not valid"
    }

    def verifySWAPageHeaderTitle(){
        waitForElement(SWA_PAGE_HEADER_TITLE).text.shouldContain SWA_PAGE_HEADER_TITLE_EXPECTED, "The Page header is not \$SWA_PAGE_HEADER_TITLE_EXPECTED"
    }

    def verifyCreateNotificationTabIsSelected(){
        waitForElement(CREATE_NOTIFICATION_TAB).getAttribute("class").shouldBe "swa_tabs_menu_item_active_link", "The Create Notification tab should be selected"
    }

    def verifyViewOrEditNotificationTabIsPresent() {
        isElementPresent(VIEW_OR_EDIT_NOTIFICATION_TAB).shouldBe true, "The View or Edit Notification Tab is not present"
    }

    def verifyCreateSubmitButtonIsPresent() {
        isElementPresent(CREATE_SUBMIT_BUTTON).shouldBe true, "The Create Submit Button is not present"
    }

    def verifyFlightInformationLegend() {
        waitForElement(FLIGHT_INFORMATION_LEGEND).text.shouldContain "Flight Information", "The Flight Information Legend is not present"
    }

    def verifyAlertScheduleLegend() {
        waitForElement(ALERT_SCHEDULE_LEGEND).text.shouldContain "Alert Schedule", "The Alert Schedule Legend is not present"
    }

    def verifyArrivalRadioIsPresent(){
        isElementPresent(ARRIVAL_RADIO_BUTTON).shouldBe true, "The Arrival Radio Button is not present"
    }

    def verifyTextRadioIsPresent() {
        isElementPresent(TEXT_RADIO).shouldBe true, "The Text Radio is not present"
    }

    def verifyReminderSchedule(){
        isElementPresent(REMINDER_SCHEDULE).shouldBe true, "The Reminder Schedule dropdown is not present"
        waitForElement(REMINDER_SCHEDULE).findElements(By.tagName("option")).size().shouldBe 4, "The Reminder Schedule dropdown does not have 4 options"
        waitForElement(REMINDER_SCHEDULE).findElements(By.tagName("option"))[0].getProperties()["selected"].shouldBe true, "The Reminder Schedule dropdown should select 1 hour before by default"
        waitForElement(REMINDER_SCHEDULE).findElements(By.tagName("option"))[0].getProperties()["text"].shouldBe "1 hour before", "The Reminder Schedule dropdown selected option should be 1 hour before"
    }
    def verifyLookUpFlightNumberLink(){
        waitForElement(LOOK_UP_FLIGHT_NUMBER_LINK).text.shouldContain "Lookup\nFlight Number", "The Lookup Flight Number link is not valid"
    }
}