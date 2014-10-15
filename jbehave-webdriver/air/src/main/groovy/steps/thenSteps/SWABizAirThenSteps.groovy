package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.ConfirmationPage
import pages.SwaBizHomePage
import pages.SwaBizTravelAccountLoginPage
import pages.SwaBizLinkWithCompanyPage
import pages.TravelCenterBusinessUpgradePage
import pages.PurchasePage
import pages.SearchFlightsPage
import pages.SwabizSearchHotelsPage
import pages.SwabizSearchCarsPage
import pages.elements.FlightSearchForm
import pages.BasePage
import pages.CreateASwabizAccountPage

class SWABizAirThenSteps {

    ConfirmationPage confirmationPage
    SwaBizTravelAccountLoginPage swaBizTravelAcoountLoginPage
    SwaBizLinkWithCompanyPage swaBizLinkWithCompanyPage
    TravelCenterBusinessUpgradePage travelCenterBusinessUpgradePage
    PurchasePage purchasePage
    SearchFlightsPage searchFlightsPage
    FlightSearchForm flightSearchForm
    SwaBizHomePage swaBizHomePage
    CreateASwabizAccountPage createAnAccountPage
    SwabizSearchHotelsPage swabizSearchHotelsPage
    SwabizSearchCarsPage swabizSearchCarsPage

    @Then("I should see the SWABiz confirmation page")
    thenIShouldSeeTheSWABizConfirmationPage() {
        confirmationPage.verifySwaBizIrnIsPresent()
    }

    @Then("I see the oops message")
    void oopsMessageForUMYT() {
        purchasePage.verifyOopsMessageForUmYTOnSwaBiz()
    }

    @Then("I view the OOPS message for the invalid PNR on the SWABIZ Business Select Upgrade page")
    void verifyInvalidTravelFundsPnrRetrieval() {
        travelCenterBusinessUpgradePage.verifyInvalidPnrOopsMessage()
    }

    @Then("I see the Swabiz My Account Login page")
    def seeSwabizAccountLogInPage() {
        swaBizTravelAcoountLoginPage.verifyCurrentPageLocation()
    }

    @Then("I see a page which requires the association with the company")
    def seeSwabizAssociateWithCompanyPage() {
        swaBizLinkWithCompanyPage.verifyPage()
        swaBizLinkWithCompanyPage.verifyCurrentPageLocation()
        swaBizLinkWithCompanyPage.verifyPageTitle("Associate With Company")
    }

    @Then("I see the swabiz PlanTrip Page homie")
    void seeSwaBizPlanTripPageHomie() {
        searchFlightsPage.verifySwaBizPlanTripPagePopulated()
    }

    @Then("I see the departure station description '\$description'")
    void validateDepartureStationDescription(String description){
        flightSearchForm.validateDepartureStationDescription(description)
    }

    @Then("I see the arrival station description '\$description'")
    void validateArrivalStationDescription(String description){
        flightSearchForm.validateArrivalStationDescription(description)
    }

    @Then("I see option to save credit card in swabiz")
    void seeSwabizCreditCardOption() {
        purchasePage.verifySaveCreditCardOptionIsPresent()
    }

    @Then("I should see the copyright year on the swabiz home page footer")
	def verifyCopyrightYear() {
		swaBizHomePage.verifyCopyrightYear()
	}

    @Then("I should see search flight page")
    def verifySearchFlightPage() {
        swaBizHomePage.getCurrentUrl().contains("flight/search-flight.html").shouldBe true, "Error: don't display search flight page"
    }

    @Then ("I see the oops message with invalid companyId text on the SWABIZ create account page")
    def verifyOopsMessageOnSwabizCreateAccountPage() {
        createAnAccountPage.verifyHasInvalidCompanyMessage()
    }

    @Then ("I am in the SWABiz Hotel search page")
    def verifySwabizHotelSearchPage(){
        swabizSearchHotelsPage.verifyPage()
    }

    @Then ("I am in the SWABiz Car search page")
    def verifySwabizCarSearchPage(){
        swabizSearchCarsPage.verifyPage()
    }
}
