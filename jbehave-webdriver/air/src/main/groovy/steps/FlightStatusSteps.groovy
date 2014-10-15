package steps

import org.jbehave.core.annotations.When
import pages.FlightStatusPage
import org.jbehave.core.annotations.Alias

class FlightStatusSteps {

    FlightStatusPage flightStatusPage

    @When("I click a station with Airport Information")
    def clickStationAirportInfo() {
        flightStatusPage.clickStationAirportInfo()
    }

    @When("I select the link for \$link")
    void selectLinkOnAirportInfoPopUp(String link){
        flightStatusPage.selectLinkOnAirportInfoPopUp(link)
    }

    @When ("I am back on the flight status page")
    void returnBackToFlightStatusPage(){
        flightStatusPage.returnBackToFlightStatusPage()
    }

    @When("I look up flight Details")
    def lookUpDetails(){
        flightStatusPage.findFlightDetailsLink().click()
    }

    @When("I check flight status details for a delayed flight")
    @Alias("I check flight status details for a flight with WILL ADVISE status")
    def checkDelayedFlightStatusDetails() {
        flightStatusPage.findViewStatusLink().click()
        flightStatusPage.verifySomeDelayedFlightIsPresent()
    }
}
