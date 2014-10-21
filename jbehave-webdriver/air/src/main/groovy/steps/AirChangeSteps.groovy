package steps

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.ReservationToRetrieve
import domain.AirReservation
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Aliases
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.jbehave.core.model.ExamplesTable
import org.joda.time.DateTime
import org.joda.time.LocalDate
import pages.*
import pages.elements.AccountBar
import pages.elements.AirCancellationModal
import pages.elements.FlightSearchForm
import pages.elements.GlobalNavigationHeader
import pages.elements.PriceTable
import pages.elements.RapidRewardsAccountBar
import pages.elements.ShoppingCart
import state.Flow
import state.PassengerData
import state.ScenarioState
import steps.conditional.ToggleGlobalNav
import steps.conditional.ToggleHomepage2
import util.FareClass
import util.ItineraryData
import util.ItineraryDataBuilder
import util.RRContactInformation
import util.RandomTicketTypeGroup

class AirChangeSteps {

    HomePage homePage
    ChangeAirReservationPage changeAirReservationPage
    SwaBizPage swaBizPage
    SelectFlightsToChangePage selectFlightsToChangePage
    SelectFlightsPage selectFlightsPage
    ScenarioState scenarioState
    FlightSearchForm flightSearchForm
    RepricePage repricePage
    PricePage pricePage
    NewPricePage newPricePage
    ReconcilePage reconcilePage
    ConfirmationPage confirmationPage
    ItineraryChangeConfirmationPage itineraryChangeConfirmationPage
    AccountBar accountBar
    ViewReservationPage viewReservationPage
    ChangeTripPage changeTripPage
    Data data
    Flow flow
    ItineraryData itineraryData
    Itinerary itinerary
    AirCancellationModal airCancellationModal
	CommonSteps commonSteps
    AirTripDetailsPage airTripDetailsPage
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
    SearchFlightsPage searchFlightsPage
    PurchasePage purchasePage
    NavigationSteps navigationSteps
    AirConfirmationSteps airConfirmationSteps
    private String reconcileAmount
    HomePage homepage
    RapidRewardsAccountBar rapidRewardsAccountBar
    RRContactInformation rrContactInformation
    SelectNewFlightsPage selectNewFlightsPage
    PriceTable priceTable
    PassengerData passengerData
    ShoppingCart shoppingCart
    GlobalNavigationHeader globalNavigationHeader

    @Given("I am on the Change Air Reservation Page")
    void openChangeAirReservationPage() {
        changeAirReservationPage.open()
    }

    @Given("I retrieve my reservation for change")
    @When("I retrieve my reservation for change")
    public retrieveReservationForChange() {
        openChangeFlightsPageForLiveOrDynaStubs()
    }

    @When("I am on the change flights page")
    @Aliases(values = ["I want to change my itinerary",
        "I retrieve the Air reservation to change it on the Change Air Reservation Page"])
    public void attemptToOpenChangeFlightsPage() {

        attemptToChangeFlightsAndContinue()
        selectFlightsToChangePage.verifyPage()
    }

    @When("I attempt to change my flight itinerary")
    public attemptToChangeFlightsAndContinue() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        ReservationToRetrieve reservation = new ReservationToRetrieve().getAdultOrSeniorReservation(airReservation)

        if (flow.isSwabiz) {
            swaBizPage.openChangeTravelPage()
        }
        else {
            homepage.open()

            if (ToggleGlobalNav.isOn()) {
                globalNavigationHeader.openNewHeaderMenuPlanATripPartOne()
                globalNavigationHeader.clickOnChangeFlightLink()
            } else {
                globalNavigationHeader.openAirSubMenu().clickOnChangeReservationLink()
            }
        }
        changeAirReservationPage.verifyBasicPage()
        changeAirReservationPage.retrieveAndSubmitItineraryToChange(reservation.recordLocator, reservation.passengerFirstName,
                reservation.passengerLastName)
    }

    @When("I enter the confirmation number, first name, last name, and click continue")
    def enterConfirmationNumberFirstNameLastNameAndClickChangeFlight() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr

        if(ToggleHomepage2.isOn()){
            homePage.retrieveReservationToChangeFlight(confirmationNumber, passenger.firstName, passenger.lastName)
        }else{
            accountBar.retrieveReservationToChangeFlight(confirmationNumber, passenger.firstName, passenger.lastName)
        }
    }

    /**
     * Loads the Change Reservation page and input PNR/Firstname & Lastname and go to next change page
     * //TODO need more refactor
     * @return
     */
    @Given("I go to the change air reservation section from home page")
    public clickOkChangeFlightThroughHomeMenu() {

        // Test data. To be cleaned up
        /*String firstName    = "SamFifteen"
        String lastName     = "LeapFrog"
        String PNR          = "MY96FS"*/

        // Load change reservation page
        changeAirReservationPage.open()

        AirReservation airReservation = scenarioState.getLastAirReservation()
        String firstName
        String lastName

        Passenger passenger  = airReservation.getPassengers().get(0)
        firstName = passenger.firstName
        lastName = passenger.lastName

        //input PNR/FN/Ln and go to the Change reservation page
        //changeAirReservationPage.retrieveAndSubmitItineraryToChange(PNR, firstName, lastName)
        changeAirReservationPage.retrieveAndSubmitItineraryToChange(airReservation.adultPnr, firstName, lastName)

    }

    @Given("I go to the change air reservation page")
    public clickOkChangeFlightThroughAirMenu() {
        homepage.clickOnChangeReservationLink()
        changeAirReservationPage.verifyBasicPage()

        AirReservation airReservation = scenarioState.getLastAirReservation()
        String firstName
        String lastName
        if(flow.isRapidRewards) {
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
            rapidRewardsAccountBar.verifyTier()
            rapidRewardsAccountBar.verifyRRacountNumber()

            if(!DynaStubsIntegration.useDynaStubs()) {
                firstName = rrContactInformation.firstName
                lastName = rrContactInformation.lastName
            }

        } else {
            Passenger passenger  = airReservation.getPassengers().get(0)
            firstName = passenger.firstName
            lastName = passenger.lastName
        }

        passengerData.passengers = airReservation.getPassengers()
        itineraryData.promoCode = null
        changeAirReservationPage.verifyLookupConfirmationLink()
        changeAirReservationPage.retrieveAndSubmitItineraryToChange(airReservation.adultPnr, firstName, lastName)
    }

    /**
     * Selects the outbound flight for change reservation
     * @return
     */
    @Given("I select the flight to change")
    public selectFlightToChange() {
        selectFlightsToChangePage.selectFlightsToChangeAndContinueOneWay(itineraryData)

    }

    @Given("I select the flight to change and continue")
    public selectFlights() {
        if(flow.isRapidRewards) {
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                verifyTier()
                verifyRRacountNumber()
                if(flow.isRapidRewardsPointsPurchaseOnly) {
                    verifyAvailablePts()
                }
            }
        }
        selectFlightsToChangePage.with {
            if(flow.isRapidRewards) {
                verifyRRnumber()
            }
            verifyBasicPage()
            verifyFlightLogo()
            verifyAirHeader()
            verifyPassengerName()
            verifyConfirmationNumber()
            verifyDate()
            verifyDepartureAndArrivalTime()
            verifyTravelTime()
            verifyFlightNumber()
            verifyDepartureAndArrivalCities()
            verifyRoutingType()
            verifyFareType()
            if(!flow.isRapidRewardsPointsPurchaseOnly) {
                verifySeniorFareIsNotChecked()
            }
            verifyCancelChangeLinkIsPresent()
            selectFlightsToChangeAndContinue(itineraryData)
        }
    }

    @Given("I search for a new flight on the next day")
    public void searchForNewFlightsNextDay() {
        changeTripPage.verifyBasicPage()
        if(flow.isRapidRewards) {
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
            rapidRewardsAccountBar.verifyTier()
            rapidRewardsAccountBar.verifyRRacountNumber()
        }

        changeTripPage.verifyWhereToFlyLink()
        changeTripPage.verifyAnyTimeSelected()
        changeTripPage.verifyStations()
        changeTripPage.verifyDate()

        itineraryData.departureDate = itineraryData.departureDate.plus(1)
        changeTripPage.changeAirTravelDates()

        changeTripPage.submit(false)

    }

    @Given("I search for a new flight on the next day with \$fareType")
    public void searchForNewFlightsWithFareType(String fareType) {
        changeTripPage.verifyBasicPage()
        if(flow.isRapidRewards) {
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
            rapidRewardsAccountBar.verifyTier()
            rapidRewardsAccountBar.verifyRRacountNumber()
        }

        changeTripPage.verifyWhereToFlyLink()
        changeTripPage.verifyAnyTimeSelected()
        changeTripPage.verifyStations()
        changeTripPage.verifyDate()

        itineraryData.departingFlight_fareClass = fareType.replaceAll('\\s+','')
        changeTripPage.changeAirTravelDates()

        changeTripPage.submit(false)

    }

    public void searchForNewFlightsWithFareTypeAndArrivalCity(String fareType, String arrivalCity) {
        changeTripPage.verifyBasicPage()
        if(flow.isRapidRewards) {
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
            rapidRewardsAccountBar.verifyTier()
            rapidRewardsAccountBar.verifyRRacountNumber()
        }

        changeTripPage.verifyWhereToFlyLink()
        changeTripPage.verifyAnyTimeSelected()
        changeTripPage.verifyStations()
        changeTripPage.verifyDate()

        itineraryData.departingFlight_fareClass = fareType.replaceAll('\\s+','')
        itineraryData.arrivalStation = arrivalCity
        changeTripPage.searchForNewFlights(false)
    }

    @Given("I select a new flight")
    public void selectANewFlightAndContinue() {
        selectANewFlight()
        selectNewFlightsPage.clickContinue()
        navigationSteps.acceptNewPriceAndContinue()
    }

    private selectANewFlight() {
        selectNewFlightsPage.with {
            verifyBasicPage()
            verifyStations()
            verifySectionHeaders()
            verifyDates()
            verifySearchButtonIsDisplayed()
            verifyFareTypesNames()
            verifyCalendarCarouselContainsTenDates()
            verifyDateSelectedIsHighlighted()
            selectFlights()
            saveOutboundRouteTypeAndDetails()
            assignShouldBSUpsellModalShow()
        }
        if (flow.isRapidRewards) {
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                verifyTier()
                verifyRRacountNumber()
            }
        }
    }

    @Given ("I select a new flight and accept Upsell modal")
    public void selectANewFlightAndAcceptUpsellModal(){
        selectANewFlight()
        selectNewFlightsPage.clickContinueToUpsellModal()
        selectFlightsPage.with {
            verifyUpsellModal()
            dismissBusinessUpgradeModalIfPresent()
        }
        navigationSteps.acceptNewPriceAndContinue()
    }

     @When("I fill the reconcile page and continue")
     public void continueFromReconcilePage() {
         reconcilePage.with {
             verifyPage()
             verifyPassengerInfoPopulated()
             verifyPassengerFootnote()
             verifyTravelFundsAirCharges()
             verifyTravelFundsRoutingSubHeader()
             verifyTotalAmountForNewFlight()
             if (itineraryData.hasTravelFunds) {
                 applyTravelFunds(scenarioState.getFirstAirReservation())
             }
             verifyTravelFundsInformation()
             verifyTravelFundsInformationFlyOut()
             verifyFundsInfo()
             verifyPurchaseSummary()
             verifyPayment()
             if(flow.isRapidRewardsPointsPurchaseOnly) {
                 verifyTotalPoints()
             }
             if (verifyRefundableTravelFundsSectionPresent()) {
                 verifyTravelFundsCreditDue()
             }
         }
         if(flow.isRapidRewards) {
             rapidRewardsAccountBar.with {
                 verifyRRGreeting()
                 verifyLogOutLink()
                 verifyRRName()
                 verifyMyAccountLink()
                 verifyTier()
                 verifyRRacountNumber()
                 if (flow.isRapidRewardsPointsPurchaseOnly) {
                     verifyAvailablePts()
                 }
             }
         }
         shoppingCart.with {
             verifyShoppingCartExpanded()
             if(!flow.isUpgradingFromCheckin){
                 verifyModifyLink()
             }
             verifyDateInContainer()
             verifyFlightNumberOnReconcilePage()
             verifyDepartureAndArrivalCitiesOnReconcilePage()
             verifyDepartureAndArrivalTimeOnReconcilePage()
             verifyFareBreakdown()
             verifyAirTotal()
             verifyGrandTotal()
             if(!flow.isRapidRewardsPointsPurchaseOnly){
                 verifyOutboundAndInboundTotals()
             } else {
                 verifyShoppingCartAirTotal()
             }
         }
         reconcilePage.enterReconcilePageInformationAndSubmitWithoutVerify()
     }

    @When("I attempt to change my Swabiz itinerary")
    public void attemptToRetrieveSwabizReservationToChange() {

        AirReservation airReservation = scenarioState.getLastAirReservation()
        ReservationToRetrieve reservation = new ReservationToRetrieve().getAdultOrSeniorReservation(airReservation)

        swaBizPage.openChangeTravelPage()
        changeAirReservationPage.retrieveAndSubmitItineraryToChange(reservation.recordLocator, reservation.passengerFirstName,
                                                                    reservation.passengerLastName)
    }

    @When("I change to a one-way \$carrier flight")
    def changeOneWayCarrierFlight(String carrier) {
        itineraryData.changeOneWayFlightCarrier(carrier)

        openChangeFlightsPageForLiveOrDynaStubs()
        changeFlight()
    }

    @When("I change my flight to use \$city as my departure city")
    def changeDepartureCityFlight(String city) {
        itineraryData.departureStation = city

        openChangeFlightsPageForLiveOrDynaStubs()
        changeFlight()
    }

    @When("I change my flight to use \$city as my arrival city")
    def changeArrivalCityFlight(String city) {
        itineraryData.arrivalStation = city

        openChangeFlightsPageForLiveOrDynaStubs()
        changeFlight()
    }

    @When("I change my departure city to \$city1 and my arrival city to \$city2")
    def changeDepartureAndArrivalCityFlight(String city1, String city2) {
        itineraryData.departureStation = city1
        itineraryData.arrivalStation = city2

        openChangeFlightsPageForLiveOrDynaStubs()
        changeFlight()
    }

    @When("I change to a round-trip \$carrier1 \$carrier2 flight")
    def changeRoundTripCarrierFlight(String carrier1, String carrier2) {
        itineraryData.changeRoundTripFlightCarrier(carrier1, carrier2)

        openChangeFlightsPageForLiveOrDynaStubs()
        changeFlight()
    }

    @When("I change my flight to use \$routingType segments")
    def changeFlightRoutingType(String routingType) {
        itineraryData.outboundRouting = routingType
        itineraryData.inboundRouting = routingType

        openChangeFlightsPageForLiveOrDynaStubs()
        changeFlight()
    }

    @When("I change the flight to a later date")
    def changeFlightToLaterDate() {
        itineraryData = delayByTwoDays(itineraryData)

        openChangeFlightsPageForLiveOrDynaStubs()
        changeFlight()
    }

    @When("I change selected flight to a later date")
    def changeSelectedFlightToLaterDate() {
        itineraryData = delayByTwoDays(itineraryData)

        openChangeFlightsPageForLiveOrDynaStubs()
        selectFlightsToChangePage.selectFlightsToChangeAndContinue(itineraryData)
        if (!DynaStubsIntegration.useDynaStubs()) {
            selectFlightsToChangePage.checkForMixedPNRsPopUp(itineraryData)
        }
        changeTripPage.changeAirTravelDates()
        changeTripPage.searchForNewFlights()
        selectFlightsPage.verifyPage()
    }

    @When("I change the flight to a later date with no E-mail address input in the first try")
    def changeFlightToLaterDateInTwoTries() {
        itineraryData = delayByTwoDays(itineraryData)

        openChangeFlightsPageForLiveOrDynaStubs()
        changeFlightWithTwoTriesWithFirstOneFailedWithoutEmailAddress()
    }

    @When("I select to change my entire itinerary and continue to Reconcile page")
    def changeFlightAndGoToReconcilePage() {
        fromConfirmationPageChangeEntireItinerary()
        changeTripPage.changeAirTravelDates()
        changeTripPage.searchForNewFlights()

        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinue()
        newPricePage.verifyPage()
        if (newPricePage.hasBoardingPass()) {
            newPricePage.deleteBoardingPass()
        }
        if (repricePage?.isRepricingPage()) {
            repricePage.clickContinue()
        }
        newPricePage.submit()
        reconcilePage.verifyPage()
    }

    @When("I change the flight and verify that the original fare was a senior fare")
    def changeFlightAndVerifyForSenior() {
        openChangeFlightsPageForLiveOrDynaStubs()
        selectFlightsToChangePage.verifyIfSeniorFare(itineraryData.departingFlight_fareClass)

        changeFlight()
    }

    @When("I change the flight")
    def changeFlight() {
        changeFlightTripPage()
        completeChangingProcessFromChangeTripPage()
    }

    private def changeFlightTripPage(){
        selectFlightsToChangePage.selectFlightsToChangeAndContinue(itineraryData)
        if (!DynaStubsIntegration.useDynaStubs()) {
           selectFlightsToChangePage.checkForMixedPNRsPopUp(itineraryData)
        }
        changeTripPage.verifyPage()
    }
    @When("I select and enter departure/arrival station on change itinerary page")
    def enterDepartureAndArrivalStationOnSouthwestAirlinesChangeItineraryPage() {
        selectFlightsToChangePage.selectOriginationAndDestinationCheckBox(itineraryData)
        selectFlightsToChangePage.getChangeFlightPageSubmitButton().click()
        changeTripPage.searchForNewFlights(false)
    }

    @When("I retrieve my reservation for change and select a new price")
    def viewUpModalBusineesSelectOnPricePage(){
        retrieveReservationForChange()
        changeFlightTripPage()
        beginChangingProcessFromChangeTripPageUpSellModal()
    }

    @When("I complete the changing process")
    def completeChangeWithoutSettingATripName() {
        flow.tripName = null
        completeChangingProcessFromChangeTripPage()
    }

    def changeFlightWithTwoTriesWithFirstOneFailedWithoutEmailAddress() {

        selectFlightsToChangePage.selectFlightsToChangeAndContinue(itineraryData)
        if (!DynaStubsIntegration.useDynaStubs()) {
           selectFlightsToChangePage.checkForMixedPNRsPopUp(itineraryData)
        }
        changeTripPage.verifyPage()
        completeChangingProcessFromChangeTripPageWithTwoTriesWhereFirstOneFailedWithoutEmailAddress()
    }

    def completeChangingProcessFromChangeTripPageWithTwoTriesWhereFirstOneFailedWithoutEmailAddress() {

        changeTripPage.changeAirTravelDates()

        viewReservationPage.internationalMessageSearchFlightToChange()

        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }
        changeTripPage.searchForNewFlights()
        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinue()
        repricePage.verifyPage()
        if (repricePage?.isRepricingPage()) {
            repricePage.clickContinue()
        }
        newPricePage.verifyPage()
        newPricePage.submit()
        reconcilePage.verifyPage()
        reconcilePage.enterReconcilePageInformationWithoutEmailAndSubmit()
        reconcilePage.verifyOopsMessageForEmailIsPresented()
        reconcilePage.enterReconcilePageInformationAndSubmitWithoutVerify()
        itineraryChangeConfirmationPage.verifyPage()
        itineraryChangeConfirmationPage.storeCurrentPNR()
    }

    def completeChangingProcessFromChangeTripPage(){
		beginChangingProcessFromChangeTripPage()
        repricePage.verifyPage()
        if (repricePage?.isRepricingPage()) {
            repricePage.clickContinue()
        }
        newPricePage.verifyPage()

        newPricePage.submit()
        reconcilePage.verifyPage()

        reconcilePage.enterReconcilePageInformationAndSubmitWithoutVerify()
        itineraryChangeConfirmationPage.verifyPage()

        itineraryChangeConfirmationPage.storeCurrentPNR()
    }

    @When("I change the flight from search flight page")
    def changeFlightFromFlightSearch() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        accountBar.getQuickLink("Change Flight").click()
        changeAirReservationPage.retrieveAndSubmitItineraryToChange(airReservation.adultPnr, passenger.firstName, passenger.lastName)
        selectAllItinerariesListed()
        changeTripPage.verifyPage()
        changeTripPage.changeAirTravelDates()
        changeTripPage.searchForNewFlights()
        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinue()
        newPricePage.verifyPage()
        if (newPricePage.hasBoardingPass()) {
            newPricePage.deleteBoardingPass()
        }
        if (repricePage?.isRepricingPage()) {
            repricePage.clickContinue()
        }

        newPricePage.submit()
        reconcilePage.enterReconcilePageInformationAndSubmit()
    }

    @When("I select to change my entire itinerary")
    def selectAllItinerariesListed() {
        selectFlightsToChangeAndContinue()
        clickContinueOnChangeAirReservationModal()

    }

    @When("I click continue on the Change Air Reservation Modal")
    def clickContinueOnChangeAirReservationModal() {
        if (airCancellationModal.isAirCancelModalPresent()) {
            airCancellationModal.clickContinueButton()
        }
    }

    @When("I click cancel on oops message cancel link for international route")
    def clickCancelOnOopsMessageForInternationalRoute() {
        commonSteps.cancelAirReservationPage.clickCancelLinkFromOopsMessageForInternationalRoute()

    }


    @When("I select to change my entire itinerary and continue")
    def selectFlightsToChangeAndContinue() {
        selectFlightsToChangePage.verifyPage()
        selectFlightsToChangePage.selectOutBoundToChangeItinerary()
        if (!itineraryData.isOneWay()) {
            selectFlightsToChangePage.selectInBoundToChangeItinerary()
        }
        selectFlightsToChangePage.continueToChangeItinerary()
        flow.isAirChange = true
    }

    @When("I select to change my entire itinerary and continue to the Select New Flight page")
    def selectFlightsToChangeAndContinueToSelectNewFlightPage() {
        selectFlightsToChangePage.selectOutBoundToChangeItinerary()
        if (!itineraryData.isOneWay()) {
            selectFlightsToChangePage.selectInBoundToChangeItinerary()
            itineraryData.returnDate = itineraryData.returnDate.plus(3)
        }
        selectFlightsToChangePage.continueToChangeItinerary()
        changeTripPage.verifyPage()
        itineraryData.departureDate = itineraryData.departureDate.plus(2)
        changeTripPage.changeAirTravelDates()
        changeTripPage.submit()
        selectFlightsPage.verifyPage()
    }

    @When("I select to change the inbound flight and continue to the Select New Flight page")
    def selectInBoundFlightToChangeAndContinueToSelectNewFlightPage() {
        selectFlightsToChangePage.verifyPage()
        selectFlightsToChangePage.selectInBoundToChangeItinerary()
        selectFlightsToChangePage.continueToChangeItinerary()
        changeTripPage.verifyPage()
        itineraryData.returnDate = itineraryData.returnDate.plus(1)
        changeTripPage.changeAirTravelInBoundDates()
        changeTripPage.submit()
    }

    @When("I select to change my entire itinerary from the confirmation page")
    def fromConfirmationPageChangeEntireItinerary() {
        if (DynaStubsIntegration.useDynaStubs()) {
            openChangeFlightsPageForLiveOrDynaStubs()
        }else {
            confirmationPage.clickChange()
        }
        selectAllItinerariesListed()
        changeTripPage.verifyPage()
    }

    @When("I upgrade to \$fareClass fare and reach the reconcile page")
    def upgradeFareAndReachReconcile(String fareClass) {
        itineraryData.departingFlight_fareClass = fareClass
        itineraryData.arrivingFlight_fareClass = fareClass
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinue()
        newPricePage.submit()
    }

    @Given("I select the \$direction flight to change to \$fareType \$WebOnly fare")
    @When("I select the \$direction flight to change to \$fareType \$WebOnly fare")
    def selectFlightForChange(String direction, String fareType, String WebOnly) {

        if (direction == 'outbound') {
            changeAirReservationPage.selectOutBoundToChangeItinerary()
        }
        if (direction == 'inbound') {
            changeAirReservationPage.selectInBoundToChangeItinerary()
        }
        changeAirReservationPage.continueToChangeItinerary()
        flightSearchForm.clickSelectNewFlight()
        itineraryData.departingFlight_fareClass = fareType
        itineraryData.isWebOnly = isWebOnly(WebOnly)
        dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        selectFlightsPage.selectFlight('outbound', false)
    }

    @Given("I fill reconcile payment information and confirm")
    @When("I fill reconcile payment information and confirm")
    def fillReconcilePaymentAndConfirm() {
        fillReconcilePayment()
        submitReconcile()
    }

    @When("I fill reconcile payment information")
    def fillReconcilePayment() {
        flow.isAirChange = true
        reconcilePage.fillInPassengerContactInformation()
        reconcilePage.fillInPaymentInformation()
    }

    @When("I change the inbound flight")
    def changeOutboundAndPurchase() {
        changeAirReservationPage.selectInBoundToChangeItinerary()
        changeAirReservationPage.continueToChangeItinerary()
        flightSearchForm.clickSelectNewFlight()

    }

    @When("I only fill payment information and confirm on the Reconcile page")
    def fillOnlyReconcilePayment() {
        flow.isAirChange = true
        reconcilePage.fillInPaymentInformation()
        submitReconcile()
    }

    @When("I click on submit to confirm the change")
    def submitReconcile() {
        reconcilePage.submit()
    }

    @Given("I click Continue to the Reconcile page")
    @When("I click Continue to the Reconcile page")
    def continueToReconcilePage() {
        newPricePage.verifyPage()

        if(!DynaStubsIntegration.useDynaStubs()) {
            if(flow.isRapidRewards) {
                rapidRewardsAccountBar.with {
                    verifyRRGreeting()
                    verifyLogOutLink()
                    verifyRRName()
                    verifyMyAccountLink()
                    verifyTier()
                    verifyRRacountNumber()
                    if (flow.isRapidRewardsPointsPurchaseOnly) {
                        verifyAvailablePts()
                    }
                }
            }
            newPricePage.with {
                verifyDate()
                verifyDepartureAndArrivalTime()
                verifyCities()
                verifyFlightNumber()
                verifyTravelTime()
                verifyRoutingType()
                verifyFareType()
                if(flow.isRapidRewardsPointsPurchaseOnly) {
                    verifyNewTicket()
                    verifyExchangedTicket()
                    verifyDifferenceInPoints()
                } else {
                    verifyAirTotal()
                }
            }
            priceTable.with {
                if(!flow.isRapidRewardsPointsPurchaseOnly) {
                    verifyPendingAirItinerary()
                    verifyBaseFarePlusTaxOnReconcile()
                    verifyAdditionalCredit()
                    verifyRedeemableMemberPointsByFlyoutId()
                    verifyAvailableFunds()
                } else {
                    verifyFareBreakdownPointsTotal()
                }
                verifyFareBreakDownByBreakdownById()
                verifyPassengerType()
                verifyTripTypes()
                verifyRouting()
                verifyFareType()
                verifyNumberOfPassengers()
            }
            if(!flow.isUpgradingFromCheckin) {
                priceTable.verifyOutboundAndInboundTotalAmounts()
            } else {
                newPricePage.verifyCancelButton()
            }
        }
        newPricePage.with {
            verifyContinueButton()
            submit()
        }
        reconcilePage.verifyPage()
    }

    @When("I click Continue to the New Select Flight Page")
    def continueToNewFlightPage() {
        changeTripPage.submit()
    }

    @When("I click Continue to Reprice Flight Page")
    def continueToRepriceFlightPage() {
        changeAirReservationPage.continueToRepricePage()
    }

    @When("I continue to the select flight page")
    def continueToSelectFlightPage() {
        flightSearchForm.clickSelectNewFlight()
    }

    @When("I change the departure date as of \$date")
    def changeFlightToThisDate(String plusDate){
        LocalDate date = new LocalDate()
        if(flow.forcePlusDayToSearch) {
            date = date.plusDays(1)
        }
        if(plusDate.equals("tomorrow")){
            date = date.plusDays(1)
        } else if(!plusDate.equals("today")){
            try{
            date = date.plusDays(plusDate.toInteger())
            }catch(NumberFormatException exception){
                println "You should use \"today\", \"tomorrow\", or a number."
            }
        }
        itineraryData.departureDate = date.toDate()
        changeTripPage.changeAirTravelDates()
    }

    private openChangeFlightsPageForLiveOrDynaStubs() {
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
            if((itineraryData.isWebOnly) && (DateTime.now().getHourOfDay() > 19)) {
                itineraryData.departureDate.setDate(itineraryData.departureDate.getDate()+1)
                dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
            }
            attemptToOpenChangeFlightsPage()
            return
        }

        if (confirmationPage.isCurrentPage() && confirmationPage.verifyChangeLinkIsPresent()) {
            confirmationPage.clickChange()
            selectFlightsToChangePage.verifyPage()
        }
        else {
            AirReservation airReservation = scenarioState.getLastAirReservation()
            changeAirReservationPage.open()
            accountBar.getQuickLink("Change Flight").click()
            if (airReservation.adultPnr == null) {
                Passenger passenger = airReservation.getSeniorPassengers().get(0)
                changeAirReservationPage.retrieveAndSubmitItineraryToChange(airReservation.seniorPnr, passenger.firstName, passenger.lastName)
            }
            else {
                Passenger passenger = airReservation.getAdultPassengers().get(0)
                changeAirReservationPage.retrieveAndSubmitItineraryToChange(airReservation.adultPnr, passenger.firstName, passenger.lastName)
            }
        }
     }

    private FareClass getFareClass(String fareType) {
        return FareClass.from(fareType.split('-')[0])
    }

    private ItineraryData delayByTwoDays(ItineraryData itineraryData) {
        itineraryData.departureDate = itineraryData.departureDate.plus(2)
        if (itineraryData.returnDate != null) {
            itineraryData.returnDate = itineraryData.returnDate.plus(2)
        }
        return itineraryData;
    }

    private boolean isWebOnly(String isWebOnly) {
        return isWebOnly.equalsIgnoreCase("WebOnly");
    }

    @Then("I view the message for airtran disclaimer")
    def pageMessage() {
        changeAirReservationPage.verifyAirtranDisclaimerMessage()
    }
    @When ("I verify changeTripPage oops \$message")
    @Then ("I verify changeTripPage oops \$message")
    def lookForOopsMessageOnNewChangeTripSearchPage(String message) {
        changeTripPage.verifyOopsMessagesOnChangeTripPage(message)

    }

	@When("I change the flight with the following itinerary data: \$itineraryTable")
	def changeFlightToSJUDestination(ExamplesTable itineraryTable) {
		commonSteps.loadItineraryTable(itineraryTable)
		openChangeFlightsPageForLiveOrDynaStubs()
		 if (DynaStubsIntegration.useDynaStubs()) {
			 dynaStubsIntegration.retrievePriceOopsMessageSanJuanRestriction()
		 }
		attemptToChangeFlight()
	}

	@When("I select to change my entire  with the following itinerary data: \$itineraryTable")
	def changeEntireItineraryToSJUDestinationOnSwabiz(ExamplesTable itineraryTable) {
		commonSteps.loadItineraryTable(itineraryTable)
		if (DynaStubsIntegration.useDynaStubs()) {
            openChangeFlightsPageForLiveOrDynaStubs()
        }else {
            confirmationPage.clickChange()
        }
        selectAllItinerariesListed()
        changeTripPage.verifyPage()
	}

	@When("I attempt to change the flight")
	def attemptToChangeFlight() {

		selectFlightsToChangePage.selectFlightsToChangeAndContinue(itineraryData)
		if (!DynaStubsIntegration.useDynaStubs()) {
		   selectFlightsToChangePage.checkForMixedPNRsPopUp(itineraryData)
		}
		beginChangingProcessFromChangeTripPage()
	}

    @When( "I retrieve my reservation to change")
    def iRetrieveMyReservationToChange() {
        /**
         *  place holder method for proper story syntax, this step occurs in
         *   changeRoundTripCarrierFlight
         */
    }

	def beginChangingProcessFromChangeTripPage(){
        beginChangingProcessFromChangeTripPageToSelectFlight()
		selectFlightsPage.clickContinue()
	}

    def beginChangingProcessFromChangeTripPageUpSellModal(){
        beginChangingProcessFromChangeTripPageToSelectFlight()
        selectFlightsPage.clickContinueToUpsellModal()
    }

    private def beginChangingProcessFromChangeTripPageToSelectFlight(){
        changeTripPage.changeAirTravelDates()

        viewReservationPage.internationalMessageSearchFlightToChange()

        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }
        changeTripPage.searchForNewFlights()
        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlights()
    }

    @When( "I modify my Upcoming Trip" )
    def iModifyMyUpcomingTrip() {
        airTripDetailsPage.clickOnChangeReservationLink()
        changeAirReservationPage.selectInBoundToChangeItinerary()
        changeAirReservationPage.selectOutBoundToChangeItinerary()
        changeAirReservationPage.continueToChangeItinerary()
        completeChangingProcessFromChangeTripPage()
    }

    @Given( "I have booked a 3 pax Business Select PNR" )
    def iHaveBookedA3PAXBusinessSelectPNR() {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(new RandomTicketTypeGroup()).withDepartingCarrier("Southwest").withArrivalCarrier("Southwest").withRoundTripStations("Southwest", "Southwest").withFareClass("BusinessSelect").build())
        searchFlightsPage.open()
        flightSearchForm.selectAdultAndSeniorPassengers(3, 0);
        itineraryData.adultPassengerCount = 3
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)

        selectFlightsPage.verifyModifySearchWidgetIsCollapsed();
        selectFlightsPage.selectFlights()
        airConfirmationSteps.priceAndPurchaseFlight()
    }

    @Given("I change my reservation to Wanna Get Away")
    def givenIChangeMyReservationToWannaGetAway() {
        itineraryData.departingFlight_fareClass = "WannaGetAway"
        itineraryData.arrivingFlight_fareClass = "WannaGetAway"
        itineraryData.returningFlight_fareClass = "WannaGetAway"
        openChangeFlightsPageForLiveOrDynaStubs()
        changeFlightTripPage()
    }

    @When("I complete my reservation change")
    def whenICompleteMyReservationChange() {
        beginChangingProcessFromChangeTripPage()
        repricePage.verifyPage()
        if (repricePage?.isRepricingPage()) {
            repricePage.clickContinue()
        }
        newPricePage.verifyPage()
        newPricePage.submit()
        reconcilePage.selectRequestRefundOfRefundableBalance()
        reconcileAmount = reconcilePage.getRefundableAmount()
        println( "Reconcile Amount:  " + reconcileAmount )
        reconcilePage.enterReconcilePageInformationAndSubmit()
        itineraryChangeConfirmationPage.verifyPage()
    }

    @Then("I see the Original Balance on the Itinerary Change Confirmation Page")
    def thenISeeTheOriginalBalanceOnTheItineraryChangeConfirmationPage(){
        itineraryChangeConfirmationPage.verifyChange()
        itineraryChangeConfirmationPage.verifyRefundAmount( reconcileAmount )
    }

    @When("I {upgrade|downgrade} the flight to \$fareType fare")
    def changeFlightWithFareType(String fareType) {
        clickOkChangeFlightThroughAirMenu()
        selectFlights()
        searchForNewFlightsWithFareType(fareType)
        if (fareType.equals("Anytime")) {
            selectANewFlightAndAcceptUpsellModal()
        } else {
            selectANewFlightAndContinue()
        }
        continueToReconcilePage()
        continueFromReconcilePage()
    }

    @When("I upgrade the flight to \$fareType fare with travel funds and \$city as arrival city")
    def changeFlightWithFareTypeAndFormOfPayment(String fareType, String arrivalCity) {
        itineraryData.hasTravelFunds = true
        clickOkChangeFlightThroughAirMenu()
        selectFlights()
        searchForNewFlightsWithFareTypeAndArrivalCity(fareType, arrivalCity)
        selectANewFlightAndAcceptUpsellModal()
        continueToReconcilePage()
        continueFromReconcilePage()
    }

    @Then("I see strike through for original dollar fare")
    def verifyStrikeThroughOnOriginalPoints(){
        selectNewFlightsPage.isStrikeThroughOriginalDollarDisplayed()
    }

    @When("I downgrade the flight to \$fareType fare and \$city as arrival city")
    def changeFlightWithFareTypeAndArrivalCity(String fareType, String arrivalCity) {
        clickOkChangeFlightThroughAirMenu()
        selectFlights()
        searchForNewFlightsWithFareTypeAndArrivalCity(fareType, arrivalCity)
        selectANewFlightAndAcceptUpsellModal()
        continueToReconcilePage()
        continueFromReconcilePage()
    }
}
