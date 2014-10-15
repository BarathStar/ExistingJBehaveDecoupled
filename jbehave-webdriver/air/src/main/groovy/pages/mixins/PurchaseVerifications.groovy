package pages.mixins

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import domain.AirReservation
import domain.Passenger

class PurchaseVerifications {

    private static final By APPLY_TRAVEL_FUNDS = By.id("applyTicketLessFundsButtonId")
    private static final By TICKETLESS_CONFIRMATION_NUMBER = By.id("ticketlessConfirmationNumber")
    private static final By TICKETLESS_FIRST_NAME = By.id("ticketlessFirstName")
    private static final By TICKETLESS_LAST_NAME = By.id("ticketlessLastName")
    private static final By APPLY_TICKETLESS_FUNDS_SUBMIT_BUTTON = By.id("applyTicketLessFundsButton_submitButtonWhite")

    def applyTravelFunds(AirReservation airReservation = scenarioState.getLastAirReservation()) {
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        clickApplyTravelFunds()
        enterTravelFundConfirmationNumber(airReservation.adultPnr, passenger.firstName, passenger.lastName)
        submitTravelFunds()
    }

    def clickApplyTravelFunds() {
        WebElement ticketLessFundsButton = waitForElement(APPLY_TRAVEL_FUNDS, false)
        if (ticketLessFundsButton != null) {
            ticketLessFundsButton.click()
        }
    }

    def enterTravelFundConfirmationNumber(String conNum, String firstName, String lastName) {
        waitForElement(TICKETLESS_CONFIRMATION_NUMBER).sendKeys(conNum)
        waitForElement(TICKETLESS_FIRST_NAME).sendKeys(firstName)
        waitForElement(TICKETLESS_LAST_NAME).sendKeys(lastName)
    }

    def submitTravelFunds() {
        waitForElement(APPLY_TICKETLESS_FUNDS_SUBMIT_BUTTON).click()
    }
}
