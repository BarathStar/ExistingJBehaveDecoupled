package steps

import org.jbehave.core.annotations.When
import pages.AccountTravelPage
import pages.UpcomingTripsPage

class AccountTravelSteps {

    AccountTravelPage accountTravelPage
    UpcomingTripsPage upcomingTripsPage

    @When("I decide to check all the Upcoming Trips from my account's Travels")
    def clickOnViewAllUpcomingTripsLink() {
        accountTravelPage.clickOnViewAllUpcomingTripsLink()
        upcomingTripsPage.verifyPage()
    }

    @When("I attempt to check-in the flight")
    def clickOnUpComingTripCheckInButtonOnAccountTravelPage(){
        accountTravelPage.clickOnUpComingTripCheckInButton()
    }

    @When("I attempt to change the Air product on My Travel Snapshot page")
    def attemptToChangeAirProductOnMyTravelSnapshotPage() {
        accountTravelPage.attemptToChangeAirProductOnMyTravelSnapshotPage()
    }
}
