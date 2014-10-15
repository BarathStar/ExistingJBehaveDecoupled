package pages

import domain.Passenger
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import state.Flow
import state.PassengerData

import static com.thoughtworks.selenium.SeleneseTestBase.fail

class GroupCheckinPage extends BasePage {

    private static final String PATH = "/flight/selectPrintDocument.html"
    private static final String CHECK_IN_ERROR_MSG = "An error occurred while checking in this passenger. Please try again"
    private static final String OOPS_MESSAGE = "We are currently unable to complete your request due to an undefined error. If this is the first time you have seen this message, please click here to try again. If you continue to have difficulties, please contact a Southwest Airlines Customer Representative for assistance at 1-800-I-FLY-SWA"
    private static final By CONTINUE_BUTTON = By.id("continueButton")
    private static final By CHECKED_IN_PASSENGERS_NAMES_COLUMN = By.cssSelector("td.passengerNameColumn")
    private static final By CHECK_IN_AVAILABLE_PASSENGER_TABLE = By.id("checkinAvailableTable")
    private static final By ERROR_WRAPPER = By.id("error_wrapper")
    private static final By ALL_CHECKBOX = By.id("selectAllCheckbox")
    private static final String AIRPORT_CHECK_IN_REQUIRED_MESSAGE = "Airport Checkin Required"
    private static final By OUTBOUND_ROW = By.cssSelector("#checkedInPassengersTable td:nth-child(2)")
    private static final By INBOUND_ROW = By.cssSelector("#checkedInPassengersTable td:nth-child(3)")
    private static final By CHECK_IN_AVAILABLE_MESSAGE = By.id("checkin_available_message")
    PassengerData passengerData
    Flow flow

    public GroupCheckinPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return PATH
    }

    def selectSomePassengersAvailableForCheckin(int passengersCount) {
        passengersCount.times {
            Passenger passenger = passengerData.getPassengers().get(it)
            waitForElement(By.xpath("//tr[contains(td,'${passenger.toString()}')]//input")).click()
            passenger.setSelectedForCheckin(true)
        }
    }

    def clickOnContinueButton() {
        WebElement continueButton;
        boolean elementEnabled = false
        while(!elementEnabled) {
            continueButton = waitForElement(CONTINUE_BUTTON)
            elementEnabled = continueButton.enabled
        }
        continueButton.click()
    }

    def verifySelectedPassengersForCheckin() {
        waitForElements(CHECKED_IN_PASSENGERS_NAMES_COLUMN).each { checkedInPax ->
            String pax = checkedInPax.getText()
            Passenger passenger = passengerData.getCheckedInPassengers().find { it ->
                pax.contains(it.toString())
            }
            if (passenger == null) {
                fail "I should see a passenger with name: " + passenger.toString() + " on the checked in table"
            }
        }
    }

    def verifyNotSelectedPassengersForCheckin() {
        WebElement checkInAvailablePassengersTable = waitForElement(CHECK_IN_AVAILABLE_PASSENGER_TABLE)
        String checkInTable = checkInAvailablePassengersTable.getText()
        for(Passenger passenger in passengerData.getNotCheckedInPassengers()) {
            checkInTable.shouldContain passenger.toString(), "I should see a passenger with name: " + passenger.toString() + " on the available passenger table"
        }
    }

    def verifyPassengerErrorMessageWhoFailedToCheckIn() {
       flow.passengersWithCheckinErrors.each {
           String firstName = it.trim()
           WebElement errorMsg = waitForElement(By.xpath("//tr[contains(td,'$firstName')]//span"))
           boolean elementDisplayed = false
           while(!elementDisplayed) {
               elementDisplayed = errorMsg.isDisplayed()
           }
           errorMsg.getText().shouldContain CHECK_IN_ERROR_MSG, "I should see an error message: " + CHECK_IN_ERROR_MSG
       }
    }

    def verifyOopsMessageNotPresent() {
        verifyElementNotPresent("Oops message", ERROR_WRAPPER)
    }

    def selectAllPassengersAvailableForCheckIn() {
        waitForElement(ALL_CHECKBOX).click()
        for(Passenger passenger in passengerData.getPassengers()) {
            passenger.setSelectedForCheckin(true)
        }
    }

    def selectAllPassengersAvailableForCheckinExceptForOne(String excludedPassenger) {
        selectAllPassengersAvailableForCheckIn()
        waitForElement(By.xpath("//tr[contains(td,'$excludedPassenger')]//input")).click()
        Passenger excludedPass = passengerData.getPassengersByName(excludedPassenger)
        excludedPass.setSelectedForCheckin(false)
    }

    def verifyOopsMessagePresent() {
        List<String> oopsMsg = new ArrayList<String>()
        oopsMsg.add(OOPS_MESSAGE)
        verifyOopsMessages(oopsMsg)
    }

    def verifyExcludedPassengerIsUnderCheckinResultSection(String excludedPassenger) {
        verifyElementPresent("$excludedPassenger Leapfrog under checkin results section", By.xpath("//table[@id='checkedInPassengersTable']//tbody//tr[contains(td,'$excludedPassenger')]"))
        verifyElementNotPresent("$excludedPassenger Leapfrog under checkin available section", By.xpath("//table[@id='checkinAvailableTable']//tbody//tr[contains(td,'$excludedPassenger')]"))
    }

    def verifyPassengersWhitErrorsNotShowsUnderCheckInResults() {
        flow.passengersWithCheckinErrors.each {
            String firstName = it.trim()
            verifyElementNotPresent("$firstName Leapfrog under checkin results section", By.xpath("//table[@id='checkedInPassengersTable']//tbody//tr[contains(td,'$firstName')]"))
        }
    }

    def verifyMessageAboutPassengerRequiresAirportCheckIn() {
        verifyAirportCheckInRequired(OUTBOUND_ROW)
        verifyAirportCheckInRequired(INBOUND_ROW)
    }

    private void verifyAirportCheckInRequired(By rowContainer) {
        waitForElements(rowContainer).each { row ->
            row.getText().shouldContain AIRPORT_CHECK_IN_REQUIRED_MESSAGE, "The message ${AIRPORT_CHECK_IN_REQUIRED_MESSAGE} should be present"
        }
    }
}
