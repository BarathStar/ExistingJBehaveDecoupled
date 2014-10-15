package steps.thenSteps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.Then
import pages.AirTranRedirectModal
import pages.OutboundAndReturnDatesAndPopUp
import pages.SearchFlightsPage
import pages.elements.FlightSearchForm

import static util.Locators.ELEMENT_IDS
import org.jbehave.core.model.ExamplesTable

class SearchFlightsThenSteps {

    FlightSearchForm flightSearchForm
    OutboundAndReturnDatesAndPopUp calendarPopUp
    AirTranRedirectModal airTranRedirectModal
    SearchFlightsPage searchFlightsPage

    @Then("I should have the same Calendar limit")
    def shouldHaveSameCalendarLimit() {
        calendarPopUp.verifyCalendarLimitsAreTheSame()
    }

    @Then("view the AirTran Redirect Modal with WCM Content")
    def viewAirTranRedirectModalWithWcmContent() {
        airTranRedirectModal.viewAirTranRedirectModalWithWcmContent()
    }

    @Then("the station selections on Search Flights page are prepopulated")
    def stationSelectionsOnHomePageArePrePopulated() {
        flightSearchForm.verifyStationsArePrePopulated()
    }

    @Then("I should not see \$station in \$pageField field")
    def verifyStationIsNotInFieldAndFieldIsBlank(@Named("station") String station, @Named("pageField") String pageField) {
        flightSearchForm.verifyStationIsNotInFieldAndFieldIsBlank(ELEMENT_IDS.get(pageField), station)
    }

    @Then("I should see the senior passenger count as 0")
    void seniorPassengerCountShouldBeZero() {
        flightSearchForm.verifyZeroSeniorCount()
    }

    @Then("The return and return date fields are disabled")
    def returnFieldEnabled() {
        flightSearchForm.returnFieldAndDateTimeFieldDisabled()
    }

    @Then("Promo code and Senior fields are disabled")
    def verifyPromoCodeAndSeniorFieldsAreDisabled() {
        flightSearchForm.verifyPromoCodeDisabled()
        flightSearchForm.verifySeniorFieldDisabled()
    }

    @Then("I do not see the points toggle")
    def pointsToggleNotInPage() {
        flightSearchForm.verifyPointsToggleNotOnPage()
    }

    @Then("I see the senior fares displayed")
    def seniorFaresDisplayed() {
        searchFlightsPage.seniorFareDisplayed()
    }

    @Then("Verify that I am on the Search Flights page")
    void shouldBeOnTheSearchFlightspage() {
        searchFlightsPage.verifyCurrentPageLocation()
    }

    @Then("I should see <station> in \$pageField field")
    @Alias("I should see \$station in \$pageField field")
    public def verifyStationInField(@Named("station") String station, @Named("pageField") String pageField) {
        searchFlightsPage.verifyInField(ELEMENT_IDS.get(pageField), station)
    }

    @Then("I should see the \$pageField field is empty")
    def verifyEmptyStationInField(@Named("pageField") String pageField) {
        searchFlightsPage.verifyInField(ELEMENT_IDS.get(pageField),"")
    }

    @Then("I see the PlanTrip Page homie")
    void verifyPlanTripPage() {
        searchFlightsPage.verifyPlanTripPagePopulated()
    }

    @Then("I see Oops message that says: \$tableMessage")
    def verifyOopsMsgDepartureTimeLessOneHour(ExamplesTable tableMessage) {
        def messages = tableMessage.getRows()
        List<String> strMessages = new ArrayList<String>()
        messages.each {
            strMessages.add(it.message)
        }
        searchFlightsPage.verifyOopsMessages(strMessages)

    }

	@Then("I should see the copyright year on the search flight page footer")
	def verifyCopyrightYear() {
		searchFlightsPage.verifyCopyrightYear()
	}

    @Then('I see dollar or points option in Booking Widget')
    def verifyDollarPointsInBookingWidget() {
        flightSearchForm.verifyDollarPointsOption()
    }
}
