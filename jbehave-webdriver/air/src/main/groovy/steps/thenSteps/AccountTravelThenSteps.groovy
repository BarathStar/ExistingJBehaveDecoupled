package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.AccountTravelPage
import pages.CheckinPage

class AccountTravelThenSteps {

    AccountTravelPage accountTravelPage
    CheckinPage checkinPage

    @Then("I see a message indicating that there are no Upcoming Trips in My Travel Snapshot page")
    def verifyNoUpcomingTripsMessage() {
        accountTravelPage.verifyNoUpcomingTripsMessage()
    }

    @Then("I see that My Account is the only link in the breadcrumb")
    def verifyMyAccountLinkOnBreadcrumb() {
        accountTravelPage.verifyMyAccountLinkOnBreadcrumb()
    }
    
    @Then("I see that My Trip is shown under Upcoming Trips section on My Travel Snapshot page")
    def verifyISeeMyTripUnderUpcomingTripsSection() {
        accountTravelPage.verifyISeeMyTripUnderUpcomingTripsSection()
    }

    @Then("I see the option to check the flight status")
    def verifyCheckFlightOptionStatusIsPresentOnAccountTravelPage(){
        accountTravelPage.verifyCheckFlightOptionStatusIsPresent()
    }

    @Then("I see the Checkin Online page")
    def verifyImOnCheckInPage(){
        checkinPage.verifyPage()
        checkinPage.verifyCurrentPageLocation()
        checkinPage.verifyPageTitle("Southwest Airlines - Checkin Online and Print Boarding Passes")
    }

    @Then("I see a message specifying that my upcoming trips cannot be retrieved on My Travel Snapshot page")
    def verifyUpcomingTripServiceUnavailable() {
        accountTravelPage.verifyUpcomingTripServiceUnavailable()
    }
}
