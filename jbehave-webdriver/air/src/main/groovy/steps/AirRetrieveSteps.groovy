package steps

import com.swacorp.dotcom.webscenarios.air.Data
import domain.AirReservation
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import pages.elements.GlobalNavigationHeader
import state.Flow
import state.ScenarioState
import steps.conditional.ToggleGlobalNav
import util.HotelItineraryData
import util.ItineraryData
import org.jbehave.core.annotations.*
import pages.*
import util.RRContactInformation

class AirRetrieveSteps {

    TravelFundsPage travelFundsPage
    ViewReservationPage viewReservationPage
    CancelAirReservationPage cancelAirReservationPage
    ChangeAirReservationPage changeAirReservationPage
    ScenarioState scenarioState
    Data data
    ItineraryData itineraryData
    Itinerary itinerary
    SearchFlightsPage searchFlightsPage
    SelectFlightsToChangePage selectFlightsToChangePage
    Flow flow
    GlobalNavigationHeader globalNavigationHeader
    CancelReservationConfirmationPage cancelReservationConfirmationPage
    SwaBizPage swaBizPage
    HotelItineraryData hotelItineraryData
    RRContactInformation rrContactInformation

    @Given("I am on the view/share itinerary landing page")
    void openViewShareItineraryPage(){
        viewReservationPage.open()
    }

    @Given("I retrieve my Itinerary using the credit card number \$cc")
    def retrieveItineraryUsingCreditCard(String cc) {
        viewReservationPage.retrieveItineraryByCreditCardNumber(data.firstName, data.lastName, itineraryData.departureStation ,cc)
    }

    @Given("I retrieve my itinerary")
    @When("I retrieve my itinerary")
    @Aliases(values = ["I retrieve the Air reservation to change it",
        "I retrieve my Air itinerary",
        "I retrieve my Adult itinerary"])
    def retrieveItineraryUsingPNR() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        String firstName
        String lastName
        if((flow.isRapidRewards || flow.isCustomer) && !DynaStubsIntegration.useDynaStubs()) {
            firstName = rrContactInformation.firstName
            lastName = rrContactInformation.lastName
        } else {
            Passenger passenger  = airReservation.getPassengers().get(0)
            firstName = passenger.firstName
            lastName = passenger.lastName
        }
        searchFlightsPage.open()
        searchFlightsPage.verifyPage()
        searchFlightsPage.clickManageReservationLink()
        viewReservationPage.retrieveItineraryByPnr(airReservation.adultPnr, firstName, lastName)
    }

    @When("I retrieve my Senior itinerary")
    def retrieveSeniorItineraryUsingPNR() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger  = airReservation.getSeniorPassengers().get(0)
        searchFlightsPage.open()
        searchFlightsPage.verifyPage()
        searchFlightsPage.clickManageReservationLink()
        viewReservationPage.retrieveItineraryByPnr(airReservation.seniorPnr, passenger.firstName, passenger.lastName)
    }

    @When("I retrieve my SwaBiz itinerary")
    def retrieveSwabizItineraryUsingPNR() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger  = airReservation.getPassengers().get(0)
        swaBizPage.openViewItineraryTravelPage()
        viewReservationPage.retrieveItineraryByPnr(airReservation.adultPnr, passenger.firstName, passenger.lastName)
    }

    @When("I retrieve the Air reservation to attempt to change it")
    def retrieveItineraryToAttemptToChangeIt() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger  = airReservation.getAdultPassengers().get(0)
        searchFlightsPage.open()
        searchFlightsPage.verifyPage()
        searchFlightsPage.clickManageReservationLink()
        searchFlightsPage.clickChangeReservationLink()

        viewReservationPage.retrieveItineraryByPnrForChangeReservation(airReservation.adultPnr, passenger.firstName, passenger.lastName)
        selectFlightsToChangePage.verifyPage()
    }

    @When("I enter <dummy_pnr> <dummy_fname> <dummy_lname>")
    def retrieveItineraryUsingDummyPNR(@Named("dummy_pnr") String pnr,
                                    @Named("dummy_fname") String firstName,
                                    @Named("dummy_lname") String lastName) {

        viewReservationPage.retrieveItineraryByPnr(pnr, firstName, lastName)
    }

    @When("I retrieve my Air itinerary by Credit Card")
	@Alias("I retrieve my air itinerary by Credit Card")
    def retrieveItineraryUsingCurrentCreditCard() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger  = airReservation.getAdultPassengers().get(0)
        viewReservationPage.open()
        viewReservationPage.checkSomethingServed()
        viewReservationPage.retrieveItineraryByCreditCardNumber(passenger.firstName, passenger.lastName, itineraryData.departureStation, airReservation.creditCard,itineraryData.departureDate)
    }

    @When("I enter PNR on view travel funds page")
    def enterPNROnViewTravelFundsPage() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger  = airReservation.getAdultPassengers().get(0)
        travelFundsPage.enterPNROnViewTravelFundsPage(airReservation.adultPnr, passenger.firstName, passenger.lastName)
    }

    @When("I attempt to retrieve an itinerary using an invalid PNR on the Travel Funds Page")
    def enterInvalidPnrOnViewTravelFundsPage() {
        travelFundsPage.enterPNROnViewTravelFundsPage("ABC123", "Sam", "Leapfrog")
    }

    @When("I click business select link")
    void clickBusinessSelectLink(){
        viewReservationPage.clickBusinessSelectLink()
    }

    @When('I change the trip name for \$tripName')
    void renameTripOnItinerary(String tripName) {
        flow.tripName = tripName
        viewReservationPage.renameTrip(tripName)
    }

    @When("I attempt to retrieve an itinerary using an invalid PNR from the View/Share Itinerary page")
    @Alias("I attempt to retrieve an itinerary using an inexistent PNR from the View/Share Itinerary page")
    void attemptInvalidPnrRetrievalOnViewReservationPage() {
        viewReservationPage.enterPnrAndSubmit("ABC123", "Sam", "Leapfrog")
    }

    @When("I attempt to retrieve an itinerary with an unassociated credit card")
    void attemptInvalidCreditCardRetrievalOnViewReservationPage() {
        viewReservationPage.retrieveItineraryWithAnUnassociatedCreditCardNumber("Sam", "Leapfrog", "AMA", "5555555555555555")
    }

    @When("I attempt to retrieve an itinerary using an invalid PNR on the Cancel Air Reservation Page")
    void retrieveInvalidPNROnCancelAirReservationPage() {
        cancelAirReservationPage.retrievePNR("ABC123", "Sam", "Leapfrog")
    }

    @When("I attempt to retrieve an itinerary using an invalid PNR on the Change Air Reservation Page")
    void retrieveInvalidPNROnChangeAirReservationPage() {
        changeAirReservationPage.retrieveAndSubmitItineraryToChange("ABC123", "Sam", "Leapfrog")
    }

    @When("I click the airtran.com link in the Oops message")
    void clickAirTranLink() {
        cancelAirReservationPage.clickAirTranLink()
    }

    @When("I click on the cancel link on retrieve reservation")
    void clickCancelLink() {
        viewReservationPage.clickCancelReservationLink()
    }

    @When("I click on the cancel reservation breadcrumb")
    void clickCancelReservationBreadcrumb() {
        viewReservationPage.clickCancelReservationBreadcrumb()
        viewReservationPage.checkSomethingServed()
    }

    @When("I click the seat selection button on the view itinerary page")
    def clickSeatSelectionButton() {
       viewReservationPage.clickSeatSelectionButton()
    }

    @When("I check the details of the first Air reservation listed")
    void selectFirstDetailsLinkOnDisambiguation() {
        viewReservationPage.clickOnTheFirstDetailsLinkOnDisambiguation()
    }

    @When ("I click on the continue button on the view reservation page")
    def retrieveItineraryWithEmptyFields(){
        viewReservationPage.clickOnContinueButton()
    }

    @When ("I click on the continue button on the cancellation modal")
    def clickOnContinueButtonOnTheCancellationModal(){
        viewReservationPage.clickOnContinueButtonOnModal()
        cancelReservationConfirmationPage.verifyPage()
    }

    @When ("I select the option Credit Card on the View Reservation Page")
    def clickOnCreditCardRadioButton(){
        viewReservationPage.clickOnCardRadioButton()
    }

    @When('I complete the renaming process by deleting the existing name and without entering any character')
    void renameTripOnItineraryByEmptyName(){
        renameTripOnItinerary("")
    }

    @When('I complete the renaming process by entering a new name with valid and invalid characters')
    void renameTripOnItineraryWithValidAndInvalidCharacters() {
        renameTripOnItinerary('!My Trip?')
    }

    @When("I send the itinerary via email")
    def sendItineraryViaEmail(){
        viewReservationPage.sendItineraryViaEmail()
    }

    @When("I go to the View Reservation page through the Air menu")
    def goToViewReservationPageThroughTheAirMenu() {
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openNewHeaderMenuPlanATripPartOne()
            globalNavigationHeader.clickOnManageAirReservationLink()
        } else {
            globalNavigationHeader.openAirSubMenu().clickOnViewReservationLink()
        }
        viewReservationPage.checkSomethingServed()
    }

    @When("I go to the Checkin online page through the Air menu")
    @Given("I go to the Checkin online page through the Air menu")
    def goToCheckinPageThroughTheAirMenu() {
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openNewHeaderMenuPlanATripPartOne()
            globalNavigationHeader.clickOnCheckinFlightLink()
        } else {
            globalNavigationHeader.openAirSubMenu().clickOnCheckInLink()
        }
    }

    @When("I click on the View Travel Funds link")
    def clickOnTheViewTravelFundsLink() {
        viewReservationPage.clickOnTheViewTravelFundsLink()
        travelFundsPage.verifyPage()
    }

    @When("I retrieve my air itinerary by Credit Card on Swabiz")
    def retrieveItineraryUsingCurrentCreditCardOnSwabiz() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger  = airReservation.getAdultPassengers().get(0)
        swaBizPage.openViewItineraryTravelPage()
        viewReservationPage.retrieveItineraryByCreditCardNumber(passenger.firstName, passenger.lastName, itineraryData.departureStation, airReservation.creditCard,itineraryData.departureDate)
    }

    @When("I attempt to retrieve my itinerary with a wrong passenger")
    def retrieveItineraryUsingPNRAndWrongPassenger() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        searchFlightsPage.open()
        searchFlightsPage.verifyPage()
        searchFlightsPage.clickManageReservationLink()
        viewReservationPage.retrieveItineraryByPnr(airReservation.adultPnr, "WrongName", "WrongLastName")
    }

    @When("I select a specific hotel on Hotel Dynamic Cross-Sell")
    def selectHotelFromCrossSell() {
        hotelItineraryData.setValueFromItineraryData(itineraryData,"0")
        viewReservationPage.selectHotelfromCrossSell(0)
    }

    @When("I select to view more hotels")
    def selectViewMoreHotelsFromCrossSell() {
        hotelItineraryData.setValueFromItineraryData(itineraryData,"0")
        hotelItineraryData.destination = itineraryData.arrivalCity
        viewReservationPage.clickOnViewMoreHotelsButton()
    }

    @When ("I retrieve the air reservation")
    def fillAndContinueOnViewReservation(){
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String pnr = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr
        String firstName = passenger.getFirstName()
        String lastName = passenger.getLastName()
        viewReservationPage.enterPnrAndSubmit(pnr, firstName, lastName)
    }

    @When ("I fill the air retrieve form")
    def fillAirForm(){
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String pnr = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr
        String firstName = passenger.getFirstName()
        String lastName = passenger.getLastName()
        viewReservationPage.enterPnr(pnr, firstName, lastName)
    }

    @When ("I click on continue to retrieve the air reservation")
    def clickOnContinueButton(){
        viewReservationPage.submitRetrieveAirForm()
    }

    @When("I click Add Rapid Rewards Number Link in the view Reservation page")
    def clickAddRapidRewardsNumberLink(){
        viewReservationPage.clickAddRapidRewardsNumberLink()
    }

    @When("I enter my information to retrieve my reservation")
    def enterRRInfoToRetrieveReservation() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        def promoCertUser = data.getUser("promoCertUser")
        viewReservationPage.enterPnrAndSubmit(airReservation.adultPnr, promoCertUser.firstName, promoCertUser.lastName)
    }

    @Then("I can see my itinerary booked with a promo cert")
    def seeItineraryPromoCert() {
        viewReservationPage.verifyDate()
        viewReservationPage.verifyDepartureAndArrivalTime()
        viewReservationPage.verifyTravelTime()
        viewReservationPage.verifyFlightNumber()
        viewReservationPage.verifyDepartureAndArrivalCities()

        viewReservationPage.verifyResendReceipt()
        viewReservationPage.verifyPrintAndShare()
        viewReservationPage.verifyChangeReservationLink()
        viewReservationPage.verifyCancelReservationLink()
        viewReservationPage.verifyItineraryNote()

        def promoUser = data.getUser("promoCertUser")
        viewReservationPage.itineraryDisplayed(promoUser.firstName, promoUser.lastName)
        viewReservationPage.verifyRrNumber(promoUser.number)

        AirReservation airReservation = scenarioState.getLastAirReservation()
        viewReservationPage.verifyConfirmationNumber(airReservation.adultPnr)
        viewReservationPage.verifySectionTitle(airReservation.itineraryData.departureDate)
        viewReservationPage.verifyDateInTripDetailsHeader(airReservation.itineraryData.departureDate)
    }
}
