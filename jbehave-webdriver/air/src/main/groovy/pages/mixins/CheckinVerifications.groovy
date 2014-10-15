package pages.mixins

import org.openqa.selenium.By

class CheckinVerifications {

    private static final By ADD_RR_NUMBER_TITLE = By.id("cancelTitle")
    private static final By CONFIRMATION_NUMBER = By.className("confirmation_number")
    private static final By PASSENGER_FIRST_NAME = By.className("firstName")
    private static final By PASSENGER_LAST_NAME = By.className("lastName")


    def verifyTitle() {
        waitForElement(ADD_RR_NUMBER_TITLE).text.shouldBe "Add Rapid Rewards Number", "Add Rapid Rewards Number title did not match the expected value"
    }

    def verifyConfirmationNumber(String confirmationNumber) {
        waitForElement(CONFIRMATION_NUMBER).text.shouldBe confirmationNumber, "Confirmation number did not match the expected value"
    }

    def verifyPassengerName(String firstName, String lastName) {
        waitForElement(PASSENGER_FIRST_NAME).text.toUpperCase().shouldBe firstName.toUpperCase(), "Passenger first name did not match the expected value"
        waitForElement(PASSENGER_LAST_NAME).text.toUpperCase().shouldBe lastName.toUpperCase(), "Passenger last name did not match the expected value"
    }
}
