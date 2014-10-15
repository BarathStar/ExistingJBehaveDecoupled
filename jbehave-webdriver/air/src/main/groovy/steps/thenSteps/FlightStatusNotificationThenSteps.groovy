package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.FlightStatusConfirmationPage
import pages.FlightStatusNotificationPage
import pages.elements.RapidRewardsAccountBar
import state.Flow
import util.AlteaUrlValidationHelper

class FlightStatusNotificationThenSteps {

    FlightStatusNotificationPage flightStatusNotificationPage
    FlightStatusConfirmationPage flightStatusConfirmationPage
    Flow flow
    RapidRewardsAccountBar rapidRewardsAccountBar

    @Then("I should not be able to create the flight status notification")
    def verifyCannotCreateFlightStatusNotification() {
        flightStatusNotificationPage.verifyDateOopsPresentOnCreate()
    }

    @Then("I should not be able to edit the flight status notification")
    def verifyCannotEditFlightStatusNotification() {
        flightStatusNotificationPage.verifyDateOopsPresentOnEdit()
    }

    @Then("I should be able to pick the date for my flight status notification")
    def verifyCanPickupDate() {
        flightStatusNotificationPage.verifyCalendarLimit()
    }
    
    @Then("I see that my arrival notification was successfully updated")
    def verifyArrivalNotification() {
        flightStatusNotificationPage.verifyFlightNotificationConfirmation()
    }
    
    @Then("I should see my departure notification successfully created based on the estimated departure time of my flight")
    def verifysuccessfulCreateDepartureNotification() {
        flightStatusConfirmationPage.verifyOnFlightStatusConfirmationPage()
    }

	@Then("I should see my departure notification successfully created based on the estimated departure time of my delayed flight")
	def verifysuccessfulCreateDepartureNotificationDelayed() {
		flightStatusConfirmationPage.verifyOnDelayedFlightStatusConfirmationPage()
	}


    @Then("I should see default options selected on the flight status notification page")
    def verifyDefaultOptionsSelected() {
        rapidRewardsAccountBar.with{
            if(flow.isLoggedIn && (flow.isRapidRewards || flow.isCustomer)) {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()

                if(flow.isRapidRewards) {
                    verifyRRacountNumber()
                    verifyTier()
                }
            }
        }

        flightStatusNotificationPage.with{
            verifyCreateTabURL()
            verifySWAPageHeaderTitle()
            verifyCreateNotificationTabIsSelected()
            verifyViewOrEditNotificationTabIsPresent()
            verifyFlightInformationLegend()
            verifyDepartureRadioIsChecked()
            verifyArrivalRadioIsPresent()
            verifyEmailRadioIsChecked()
            verifyTextRadioIsPresent()
            verifyDepartureStation()
            verifyArrivalStation()
            verifyLookUpFlightNumberLink()
            verifyDepartureDate()
            verifyAlertScheduleLegend()
            verifyReminderSchedule()
            verifyCreateSubmitButtonIsPresent()
            fillInEmail()
        }
    }
}
