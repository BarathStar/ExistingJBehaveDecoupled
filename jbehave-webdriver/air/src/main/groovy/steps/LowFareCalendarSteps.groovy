package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Then
import pages.LowFareCalendarPage
import util.ItineraryData
import pages.elements.FlightSearchForm
import pages.ShortcutSelectFlightsPage
import fixture.stubs.DynaStubsIntegration
import pages.SearchFlightsPage

class LowFareCalendarSteps {

    LowFareCalendarPage lowFareCalendarPage
    FlightSearchForm flightSearchForm
    ItineraryData itineraryData
    ShortcutSelectFlightsPage shortcutSelectFlightsPage
    SearchFlightsPage searchFlightsPage
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    @Given("I am on the Low Fare Calendar page")
    def openShortcutLandingPage() {
        lowFareCalendarPage.open()
    }

    @When("The Return field is visible on the Low Fare Calendar page")
    def returnFieldIsVisible() {
        lowFareCalendarPage.selectReturnLink()
    }

    @When("I want to return to a different city")
    def expandTheReturnAirportControl() {
        lowFareCalendarPage.clickOnReturnAirportLink()
    }

    @When("I click where we fly link on the low fare calendar page")
    def clickWhereWeFlyLinkOnShortCutPage() {
        lowFareCalendarPage.clickWhereWeFlyLinkOnShortCutPage()
    }

    @When("I search for round-trip flights from the low fare calendar page")
    def searchRoundTripLowFareCalendar() {
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        lowFareCalendarPage.selectMonth(itineraryData)
        lowFareCalendarPage.submit()
        shortcutSelectFlightsPage.verifyPage()
    }
    @When("I search flight that served by WN/FL carrier from low fare calender page")
    def WnFlCarrierFlights() {
        fillOutPage()
        lowFareCalendarPage.submit(false)
    }
    @When("I search for my flight from the low fare calendar page")
    def searchFlight() {
        fillOutPage()
        lowFareCalendarPage.submit()
        shortcutSelectFlightsPage.verifyPage()
    }
    def fillOutPage() {
        if (DynaStubsIntegration.useDynaStubs()) {
             dynaStubsIntegration.prepareLowFareCalendar(itineraryData)
         }
         openShortcutLandingPage()
         flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation, (itineraryData.itineraryType == "Open Jaw")? itineraryData.returnStation : null)
         lowFareCalendarPage.selectDates(itineraryData)


    }
    @Then("I view the Low Fare Calendar page")
    def viewShortcutLandingPage() {
        lowFareCalendarPage.shouldBeInPage("/flight/shortcut/select-flight")
    }
}
