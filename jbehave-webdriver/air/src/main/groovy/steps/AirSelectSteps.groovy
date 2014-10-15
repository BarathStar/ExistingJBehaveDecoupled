package steps

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.Route
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Then
import pages.SearchFlightsPage
import pages.SelectFlightsPage
import pages.elements.AutoCompleteWidget
import pages.elements.FlightSearchForm
import pages.elements.RapidRewardsAccountBar
import state.PassengerData;
import util.ItineraryData
import util.ItineraryDateFactory
import fixture.stubs.DynaStubsIntegration
import pages.NonRevLandingPage
import org.jbehave.core.annotations.Given
import org.joda.time.LocalDate
import state.Flow
import org.joda.time.Days

class AirSelectSteps{
    SelectFlightsPage selectFlightsPage
    SearchFlightsPage searchFlightsPage
    ItineraryData itineraryData
    Data data
    FlightSearchForm flightSearchForm
    AutoCompleteWidget autoCompleteWidget
    NavigationSteps navigationSteps
    PassengerData passengerData
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
    NonRevLandingPage nonRevLandingPage
    Flow flow
    RapidRewardsAccountBar rapidRewardsAccountBar


    @When("I change fares from dollars to points")
    def changeFareToPoints() {
        flightSearchForm.chooseFaresInPoints()
    }

    @When("I click the \$numberofstops link for \$airline on the select flight page")
    def clickFirstFlightDetailsLinkOnTAGPage(@Named("numberofstops") String stops, @Named("airline") String airline) {
        selectFlightsPage.clickFirstFlightDetailsLink(airline, stops)
    }

    @When("I click the flyout close button")
    def closeFlightDetailFlyOut() {
        selectFlightsPage.closeFlightDetailFlyOut()
    }

    @When("I change my search to dollars")
    @Alias("I toggle fare type to dollars")
    def selectDollarsRadioButton() {
        selectFlightsPage.toggleDollars()
    }

    @When("I change my search to points")
    @Alias("I toggle from dollars to points")
    def selectPointsRadioButton() {
        selectFlightsPage.togglePoints()
    }

    @When("I change my search to certificate")
    def selectCertificateRadioButton() {
        selectFlightsPage.toggleCertificate()
        selectFlightsPage.toggleCertificate()
    }

    @When("I search for round-trip flights using a modified search with points")
    def selectRoundTripFlightForModifiedSearchUsingPoints() {
        selectFlightsPage.togglePoints()
        selectFlightsPage.toggleModifiedSearch()

        itineraryData.departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate()
        flightSearchForm.enterDepartureDate(itineraryData.departureDate)

        dynaStubsIntegration.prepareShoppingSchedules(itineraryData)

        flightSearchForm.submit(selectFlightsPage.MODIFIED_SUBMIT_BUTTON_ID)
    }

    @When("I search for round trip flights using points")
    def searchRoundTripFlightUsingPoints() {
        searchFlightsPage.open()
        flightSearchForm.chooseFaresInPoints()
        def Route route = data.getRoute()
        flightSearchForm.fillInAirports(route.from, route.to)
        itineraryData.verifyValidDates()
        flightSearchForm.enterDepartureDate(itineraryData.departureDate)
        flightSearchForm.enterReturnDate(itineraryData.returnDate)
        flightSearchForm.fillInAdult(1)
        flightSearchForm.submit()
    }

    @When("I select a round trip flight using points")
    def selectRoundTripFlightUsingPoints() {
        searchRoundTripFlightUsingPoints()
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinue()
    }

    @Given("I select flights and continue")
    @When("I select flights and continue")
    def selectFlightsAndContinue() {
        selectFlightsPage.verifyModifySearchWidgetIsCollapsed();
        selectFlightsPage.selectFlights()
        if (passengerData.getSeniors().size() > 0) {
            selectFlightsPage.clickContinueForAdultAndSenior()
            selectFlightsPage.selectFlights(true)
        }
        selectFlightsPage.clickContinue()
        navigationSteps.acceptNewPriceAndContinue()
    }

    @Given("I select flights and continue to Price page")
    def selectFlightsAndContinueToPrice() {
        if(flow.isRapidRewards){
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                verifyRRacountNumber()
                verifyTier()
            }
        }
        selectFlightsPage.with {
            verifyBasicPage()
            verifyModifySearchWidgetIsCollapsed()
            verifyTripTypeIsCorrect()
            verifyStations()
            verifySectionHeaders()
            toggleModifiedSearch()
            verifyDates()
            verifyPassengers()
            verifyFareTypesNames()
            verifyCalendarCarouselContainsTenDates()
            verifyDateSelectedIsHighlighted()
            if (itineraryData.promoCode != null) {
                verifyDiscountedPromotionalMessage()
                verifyPromoCodePrepopulated()
            }
            if(!flow.isRapidRewardsPointsPurchaseOnly){
                verifyDollarsOptionCheckedByDefault()
            }

            selectFlights()
            if (passengerData.containsSeniorPassenger()) {
                clickContinueForAdultAndSenior()
                selectFlights(true)
            }
            saveOutboundRouteTypeAndDetails()
            if (itineraryData.isRoundTripOrOpenJaw()) {
                saveInboundRouteTypeAndDetails()
            }
            clickContinue()
        }
        navigationSteps.acceptNewPriceAndContinue()
    }

    @When("I select a flight in the adult flight page and continue to select senior flight page")
    def selectFlightAndContinueToSelectSeniorFlightPage() {
        selectFlightsPage.with {
            verifyAdultBasicPage()
            verifyModifySearchWidgetIsCollapsed()
            verifyTripTypeIsCorrect()
            toggleModifiedSearch()
            verifySectionHeaders()
            verifyPassengers()
            verifyStations()
            verifyReturnCityDisabled()
            verifyDates()
            verifyFareTypesNames()
            verifyCalendarCarouselContainsTenDates()
            verifyDateSelectedIsHighlighted()
            verifyDollarFareIsNotPresent()
            selectFlights()
            verifySeniorFareAvailable()
            clickContinueForAdultAndSenior()
        }

    }

    @When("I select a flight in the senior flight page and continue to the price page")
    def selectSeniorFlightAndContinueToPricePage() {
        selectFlightsPage.with {
            verifySeniorBasicPage()
            verifySearchWidgetIsNotPresent()
            verifyCalendarCarouselDayOfTravelEnabledOnly()
            verifyDateSelectedIsHighlighted()
            verifyDollarFareIsNotPresent()
            verifyOneFlightAvailableForSenior()
            verifySeniorFlightNumberEqualToAdultFlightNumber()
            verifySeniorDepartureAndArrivalTime()
            verifySeniorTravelTime()
            verifySeniorFlightDate()
            verifySeniorRouting()
            selectFlights(true)
            clickContinue()
        }
        navigationSteps.acceptNewPriceAndContinue()
    }

    @When("I select the new inbound flight and continue")
    def selectChangedInboundAndContinue()  {
        selectFlightsPage.selectSpecificRowFlight("outbound", "1")
        selectFlightsPage.clickContinue()
        navigationSteps.acceptNewPriceAndContinue()
    }

    @Given("I only search a flight")
    @When("I only search a flight")
    def onlySearchAFlight() {
        searchFlightsPage.open()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectFlightsPage.verifyPage()
    }

    @Given("I modify my search for new stations")
    @When("I modify my search for new stations")
    def selectStationAndSelectSenior() {
        autoCompleteWidget.collapseSearchWidget()
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        selectFlightsPage.clickModifySearchSubmitButton()
    }

    @When("I sort the outbound routing column in descending order")
    def sortOutboundRoutingDescending() {
        selectFlightsPage.sortOutboundRoutingColumnInDescendingOrder()
    }

    @When ("I modify my search to a round-trip \$carrier1 \$carrier2 flight")
    def modifySearchToNewCarriers (String carrier1, String carrier2) {
        itineraryData.changeRoundTripFlightCarrier(carrier1, carrier2)
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        selectFlightsPage.selectItineraryType(itineraryData)
        selectFlightsPage.clickModifySearchSubmitButton()
    }

    @Given("I click continue to the Price page")
    @When("I click continue to the Price page")
    def continueToPricePage() {
        selectFlightsPage.clickContinue()
    }

    @When("I select an outbound Anytime flight that qualifies for upgrade to Business Select")
    def clickFirstAnytimeFlightUpgrade() {
        selectFlightsPage.clickFirstDisplayedAnytimeFlightRowWithUpgrade()
    }

    @When("I click the outbound upsell drawer no thanks text link")
    def clickOutboundUpsellDrawerCloseLink() {
        selectFlightsPage.clickCloseUpsellDrawerTextLink()
    }

    @When("I click the outbound upsell drawer close button")
    def clickOutboundUpsellDrawerCloseButton() {
        selectFlightsPage.clickCloseUpsellDrawerButton()
    }

    @When("I click the upgrade button in the outbound Anytime upsell drawer")
    def clickOutboundUpsellDrawerUpgradeButton() {
        selectFlightsPage.clickUpsellDrawerUpgradeButton()
    }

    @When("I click on the Add Another Flight hyperlink")
    def clickAddAnotherFlight() {
        searchFlightsPage.clickAddAnotherFlight()
    }

    @When("I select \$adults adult(s) and \$seniors senior(s) and search")
    def bookMixedPaxFlight(def int adults, def int seniors) {
        flightSearchForm.selectAdultAndSeniorPassengers(adults, seniors)
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }
        selectFlightsPage.clickModifySearchSubmitButton()
    }

    @When("I select \$origin and \$destination as origin and destination with \$departureDays day(s) more for departure flight and \$returndays day(s) more for the return flight")
    def selectAnotherOriginDestination(def String origin, def String destination, def int departureDays, def int returnDays) {
        flightSearchForm.selectDepartureStation(origin);
        flightSearchForm.selectArrivalStation(destination);
        flightSearchForm.enterDepartureDate(itineraryData.departureDate.plus(departureDays))
        flightSearchForm.enterReturnDate(itineraryData.returnDate.plus(returnDays))
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }
        selectFlightsPage.clickModifySearchSubmitButton()
    }

    @When("I select an AT flight eligible for upselling on the select flights page with \$revenueString")
    def selectFlightUpsellEligible(String revenueString) {
        boolean revenue = (revenueString.equalsIgnoreCase("dollars"))
        searchAndGoToSelectFlightsPage(revenue)
        if(revenue) {
            clickFirstAnytimeFlightUpgrade()
        } else {
            selectFlightsPage.selectFlights()
        }
    }

    @When("I select \$days day(s) more for return flight")
    def selectAnotherDatesForFlights(def int days) {
        if (DynaStubsIntegration.useDynaStubs()) {
            itineraryData.returnDate = itineraryData.returnDate.plus(days)
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }
        selectFlightsPage.selectReturnDay(days)
        selectFlightsPage.verifyPage()
    }
    @When("I attempt to get to the price page")
    def attemptGetPricePage() {
        selectFlightsPage.clickContinueToUpsellModal()
    }

    @When("I search and select my flight and attempt to continue to the Price page")
    def searchSelectFlightAndAttemptPricePage() {
        searchAndGoToSelectFlightsPage(true)
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinueToUpsellModal()
    }

    @Then("I see that the select flights page displays the total price correct")
    def verifyPriceWithTaxesForAFlight() {
        selectFlightsPage.verifyPriceWithTaxesForAFlight()
    }

    private searchAndGoToSelectFlightsPage(boolean revenue) {
        searchFlightsPage.open()
        if(!revenue){
            flightSearchForm.chooseFaresInPoints()
        }
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectFlightsPage.verifyPage()
    }

    @When("I click on the first \$routingType flight number link on the select flight page")
    def clickOnFlightNumberLink(String routingType) {
        selectFlightsPage.clickFirstFlightNumberLink(routingType)
    }

    @When ("I select my flights such that the Outbound arrival is less than the MCT")
    def selectMCTFlights() {
        selectFlightsPage.selectSpecificRowFlight("outbound","3")
        selectFlightsPage.selectSpecificRowFlight("inbound","10")
    }

    @When("I search and select my flight")
    def searchMyFlightAndSelect() {
        searchAndGoToSelectFlightsPage(true)
        selectFlightsPage.selectFlights()
    }

    @When("I search for my flight")
    def searchMyFlight() {
        searchAndGoToSelectFlightsPage(true)
    }

    @Then("I verify that the \$station is displayed as valid origin station")
    def verifyOriginStation(String station){
        nonRevLandingPage.verifyValidOriginStation(station)
    }

    @Then("I verify the Business Select fare Column is displayed")
    def verifyBSColumn(){
        selectFlightsPage.verifyBSColumn()
    }

    @When("I click on Points option")
    @Given("I click on Points option")
    def clickOnPointsInSelectFlight() {
        selectFlightsPage.clickOnPoints()
        flow.isRapidRewardsPointsPurchaseOnly = true
    }

    @Given("the Promo Code field is enabled on the Air Select page")
    @Then("I see Promo Code field is enabled on the Air Select page")
    def promoCodeIsEnabled() {
        flightSearchForm.promoCodeIsEnabled()
    }

    @Given("the Senior Passenger Count field is enabled on the Air Select page")
    def seniorPassengerCountFieldIsEnabled() {
        flightSearchForm.seniorPassengerCountIsEnabled()
    }

    @Then("I see Senior Passenger Count field is disabled on the Air Select page")
    def seniorPassengerCountFieldIsDisabled() {
        flightSearchForm.seniorPassengerCountIsDisabled()
    }

    @Given("I enter \$promoCode into the Promo Code field")
    def enterPromoCodeIntoField(String promoCode) {
        flightSearchForm.fillInPromoCode(promoCode)
        selectFlightsPage.clickModifySearchSubmitButton()
    }

    @When("I see dollar-points-certificate toggle")
    @Then("I should see dollar-points-certificate toggle")
    def verifyFareTypeToggle(){
        selectFlightsPage.verifyDollarPointCertificateToggleOn()
    }

    @Given("I select an outbound flight and continue to upsell modal")
    @Alias("I select flights and continue to upsell modal")
    def selectOutboundFlightAndContinueToPrice() {
        selectFlightsPage.verifyBasicPage()
        selectFlightsPage.verifySectionHeaders()
        selectFlightsPage.verifyModifySearchWidgetIsCollapsed()
        selectFlightsPage.verifyTripTypeIsCorrect()
        selectFlightsPage.verifyStations()
        selectFlightsPage.verifyDates()
        selectFlightsPage.verifyPassengers()
        selectFlightsPage.verifyDollarsOptionCheckedByDefault()
        selectFlightsPage.verifyFareTypesNames()
        selectFlightsPage.verifyCalendarCarouselContainsTenDates()
        selectFlightsPage.verifyDateSelectedIsHighlighted()
        selectFlightsPage.selectFlights()
        selectFlightsPage.saveOutboundRouteTypeAndDetails()
        if (itineraryData.isRoundTripOrOpenJaw()) {
            selectFlightsPage.saveInboundRouteTypeAndDetails()
        }
        if(flow.isCustomer){
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
        }
        selectFlightsPage.clickContinueToUpsellModal()
    }

    @When("I select \$fareType for the outbound flight")
    void selectFareForOutboundFlight (@Named("fareType")String fareType) {
        itineraryData.departingFlight_fareClass = fareType.replaceAll('\\s+','')
    }

    @When("I select \$fareType for the inbound flight")
    void selectFareForInboundFlight(@Named("fareType")String fareType){
        itineraryData.arrivingFlight_fareClass = fareType.replaceAll('\\s+','')
    }

    @When("I select flights with points and continue")
    def selectFlightsWithPointsAndContinue() {
        selectFlightsPage.clickOnPoints()
        flow.isRapidRewardsPointsPurchaseOnly = true
        selectFlightsAndContinue()
    }
}
