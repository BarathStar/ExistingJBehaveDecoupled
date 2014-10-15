package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.FlightStatusNotificationPage

class FlightStatusNotificationSteps {

    FlightStatusNotificationPage flightStatusNotificationPage

    @Given("I am on the Create Notification tab on Flight Status page")
    def openFlightStatusCreateNotification() {
        flightStatusNotificationPage.openFlightStatusCreateNotification()
    }

    @Given("I select to subscribe to departure notifications")
    def selectDepartureRadioButton() {
        flightStatusNotificationPage.clickDepartureRadio()
    }

    @Given("I am on the View or Edit Notification tab on Flight Status page")
    def openFlightStatusViewNotification() {
        flightStatusNotificationPage.openFlightStatusViewOrEditNotification()
    }

    @Given("I open Lookup Flight Number window")
    def openLookupFlightNumberWindow() {
        flightStatusNotificationPage.clickAndSwitchLookupFlightNumberLink()
    }

    @Given("I have retrieved an exiting arrival notification")
    def retrievedExitingArrivalNotification() {
        flightStatusNotificationPage.fillFormAndSubmitForOnTimeFlight()
    }
    
    @Given("I enter the information corresponding to an ontime flight within 5 minutes of the flight's scheduled arrival time")
    def enterInformationWithFiveMinutesScheduledArrivalTime() {
        flightStatusNotificationPage.clickUpdateSubmit()
    }

    @When("I attempt to create a notification for a date further than 10 days")
    def createNotificationFurtherTenDays() {
        flightStatusNotificationPage.fillFormAndSubmitCreation()
    }

    @When("I attempt to edit a notification further than 10 days")
    def editNotificationFurtherTenDays() {
        flightStatusNotificationPage.fillFormAndSubmitEdition()
    }

    @When("I attempt to create a notification within 10 days")
    def createNotificationWithinTenDays() {
        flightStatusNotificationPage.openCalendar()
    }

    @When("I enter the information for a delayed flight beyond its scheduled time and submit it")
    def fillDataNotificationBeyondScheduledTime() {
        flightStatusNotificationPage.fillFormAndSubmitForDelayedFlight()
    }

    @When("I select 'Lookup Flight Number' and attempt to select a date within 10 days")
    def lookUpFlightNumberWithinThenDays() {
        flightStatusNotificationPage.lookUpFlightNumber()
    }

}
