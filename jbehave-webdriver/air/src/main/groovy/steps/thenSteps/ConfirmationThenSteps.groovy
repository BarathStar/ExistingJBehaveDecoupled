package steps.thenSteps

import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.Then

import pages.ConfirmationPage
import pages.elements.PriceTable
import pages.elements.RapidRewardsAccountBar
import state.PassengerData
import state.ScenarioState
import util.ItineraryData
import util.PricePageData
import util.Station
import util.TripManagement
import pages.elements.FareBreakdownFlyOut

import util.JmxMetricsApi
import pages.SelectFlightsPage
import state.Flow
import pages.AirTranRedirectModal
import pages.mixins.ConfirmationVerifications
import domain.AirReservation


class ConfirmationThenSteps {

    ConfirmationPage confirmationPage
    ItineraryData itineraryData
    PriceTable priceTable
    SelectFlightsPage selectFligthsPage
    Flow flow
    AirTranRedirectModal airTranRedirectModal
    ScenarioState scenarioState
    PassengerData passengerData
    ConfirmationVerifications confirmationVerifications
    RapidRewardsAccountBar rapidRewardsAccountBar
    PricePageData pricePageData
    private static final String BUSINESS_FARE_TYPE = "BusinessSelect"

    @Then("I receive an air confirmation number")
    @Alias("I should receive an air confirmation")
    def hasConfirmation() {
        if(flow.verifyConfirmationNumber){
            confirmationPage.verifyPage()
            confirmationPage.verifyPNRs()
        }
    }
    @Then("I verify my stations are displayed on the confirmation page along with the car and hotel cross sell")
    void verifyStationsHotelCar(){
        confirmationPage.verifyStationsOnHeader()
        confirmationPage.verifyHotelUpsellExists();
        confirmationPage.verifyCarUpsellExists();
    }

    @Then("I am able to change and cancel my itinerary")
    void verifyChangeAndCancelLinks(){
        confirmationPage.verifyStationsOnHeader()
        confirmationPage.verifyHotelUpsellExists();
        confirmationPage.verifyCarUpsellExists();
    }


    @Then("I see the car cross sell with destination city rates")
    void carCrossSellDestinationCityRate() {
        confirmationPage.carCrossSellDestinationCityRate(Station.valueOf(Station.class, itineraryData.arrivalStation).getCrossSellCityName())
    }

    @Then("I see the hotel cross sell with destination city rates")
    void hotelCrossSellDestinationCityRate() {
        confirmationPage.hotelCrossSellDestinationCityRate(Station.valueOf(Station.class, itineraryData.arrivalStation).getCrossSellCityName())
    }

    @Then("for each hotel listed there should be a rating")
    def hotelRatingOnConfirmationPage() {
        //TODO: Needs proper verification
        //confirmationPage.hotelRating()
    }

    @Then("I should see the confirmation page")
    verifyPurchasedRoundTripItinerary() {
        if (DynaStubsIntegration.useDynaStubs() && confirmationPage.getCurrentUrl().equalsIgnoreCase("about:blank")) {
            // Allow post-confirmation page actions to ignore confirmationPage validation for Dyna Stubs
            return
        }
        confirmationPage.verifyPage()
        confirmationPage.verifyPNRs()
    }

    @Then("I should see the confirmation page with my compound first and compound last name")
    hasConfirmationWithCompoundNames() {
        confirmationPage.verifyCompoundPassengerName(PassengerData.COMPOUND_PASSENGER_FIRST_NAME + " " + PassengerData.COMPOUND_PASSENGER_LAST_NAME)
    }

    @Then("I see the Early Bird upsell button")
    verifyEarlyBirdUpsellPresent() {
        confirmationPage.verifyAddEarlyBirdCheckInButtonDisplayed()
    }

    @Then("I should not see the Early Bird upsell button")
    @Alias("I should not be able to add Early Bird Check-In to my flight")
    verifyEarlyBirdUpsellNotPresent() {
        confirmationPage.verifyEarlyBirdUpsellButtonNotPresent()
    }

    @Then("Early Bird is present on the confirmation page")
    verifyEarlyBirdSection() {
        confirmationPage.verifyEarlyBirdSectionPresent()
    }

    @Then("I see the cancellation modal with two air products associated to it")
    def verifyAssociatedAirProductsModalIsDisplayed() {
        confirmationPage.verifyTwoAssociatedAirProductsModalIsDisplayed()
    }

    @Then("I see the verbiage 'Existing purchases in your trip'")
    def verifyMsgExistingPurchasesInYourTrip() {
        confirmationPage.verifyMsgExistingPurchasesInYourTripDisplayed()
    }

    @Then("I should not see the Confirmation summary Car Cross Sell")
    def verifyConfirmationSummaryCarNotDisplayed() {
        confirmationPage.verifyConfirmationSummaryCarNotDisplayed()
    }

    @Then("I see EarlyBird Check-In Purchased on air product on Existing purchases in trip")
    @Alias("I see an informative text indicating that the existing air product has purchased the EB check-in on the Confirmation Page")
    def verifyEarlyBirdPurchased() {
        confirmationPage.verifyEarlyBirdPurchased()
    }

    @Then("I see confirmation trip name \$tripName")
    def verifyConfirmationTripName(String tripName) {
        confirmationPage.verifyConfirmationTripName(tripName)
    }

    @Then("I see my booked flight on the confirmation page")
    def verifyConfirmationPage() {
        confirmationPage.verifyPage()
    }

    @Then("I see the seat selection button")
    def verifySeatSelectionButton() {
        confirmationPage.verifySeatSelectionButton()
    }

    @Then("I should not see the seat selection button")
    def verifySeatSelectionButtonNotShown() {
        confirmationPage.verifySeatSelectionButtonNotShown()
    }


    @Then("I see seat selection message for the \$outBoundInBound AirTran flight")
    def verifySeatSelectionVerbiage(String outBoundInBound) {
        confirmationPage.verifySeatSelectionVerbiage(outBoundInBound)
    }

    @Then("I see the seat selection unavailable message for the \$outBoundInBound AirTran flight")
    def verifySeatSelectionErrorVerbiage(String outBoundInBound) {
        confirmationPage.verifySeatSelectionUnavailableVerbiage(outBoundInBound)
    }


    @Then("I should see that the price table on the confirmation page has the revised column headers")
    def verifyPriceTableColumnsPriceToggle_newLayout() {
        priceTable.revisedColumnHeaders().shouldBe true
    }

    @Then("I should see that the price table on the confirmation page has the old column headers")
    def verifyPriceTableColumnsPriceToggle_oldLayout() {
        priceTable.oldColumnsHeaders().shouldBe true
    }

    @Then("I should see fare type descriptions in the price table rows on the confirmation page")
    def verifyFareTypeDescriptionsPriceToggle_shouldBeThere() {
        priceTable.anyPriceTableRowContainsFareTypeDescription().shouldBe true
    }

    @Then("I should not see fare type descriptions in the price table rows on the confirmation page")
    def verifyFareTypeDescriptionsPriceToggle_shouldNotBe() {
        priceTable.anyPriceTableRowContainsFareTypeDescription().shouldBe false
    }

    @Then("individual fare type links in the fare type column on the confirmation page open the fare rules in a new window")
    def verifyPriceRowFareTypeLink() {
        priceTable.fareTypeLinkPresentWithTargetInEachPriceRow().shouldBe true
    }

    @Then("the fare types on the confirmation page should have matching descriptions")
    def verifyFareTypesHaveMatchingDescriptions() {
        priceTable.eachRowContainsMatchingFareTypeDescription().shouldBe true
    }

    @Then("the price table fare type column header on the confirmation page should have a link that opens the fare rules in a new window")
    def tableHeaderFareTypeLinkPresent() {
        priceTable.headerFareTypeLinkPresent().shouldBe true
    }

    @Then("I should not see a fare rules link in the price table fare type column header on the confirmation page")
    def tableHeaderFareTypeLinkNotPresent() {
        priceTable.headerFareTypeLinkPresent().shouldBe false
    }

    @Then("the price table footer on the confirmation page has a link that opens the fare rules in a new window")
    def tableFooterFareTypeLinkPresent() {
        priceTable.footerFareTypeLinkPresent().shouldBe true
    }

    @Then("I should not see a fare rules link in the price table footer on the confirmation page")
    def tableFooterFareTypeLinkNotPresent() {
        priceTable.footerFareTypeLinkPresent().shouldBe false
    }

    @Then("I see that the Base Fare, Taxes and Total are the same as the ones displayed on the Price Page")
    void verifyTotalsOnYourPricingBreakDownFlyOut() {
        confirmationPage.verifyTotalsOnFareBreakDownFlyOut()
    }

    @Then("I see the confirmation Fare Breakdown flyout has a fare discount of \$amount")
    def verifyFareDiscountAmountMatches(String amount) {
        FareBreakdownFlyOut flyout = confirmationPage.openFareBreakDownFlyOut()
        flyout.fareDiscount.shouldBe amount.toBigDecimal(), "Discount not equal to specified amount"
        flyout.closeFlyout()
    }

    @Then("I see the confirmation Fare Breakdown flyout has a fare discount greater than \$amount")
    def verifyFareDiscountAmountAboveThreshold(String amount) {
        FareBreakdownFlyOut flyout = confirmationPage.openFareBreakDownFlyOut()
        flyout.fareDiscount.shouldBeGreaterThan amount.toBigDecimal(), "Discount not greater than specified amount"
        flyout.closeFlyout()
    }

    @Then("I see the confirmation Fare Breakdown flyout has no fare discount")
    def verifyFlyoutHasNoFareDiscount() {
        confirmationPage.verifyFareDiscountNotPresent()
    }

    @Then("I see information about the UM and the applied charges for a \$itinerary with \$passengers minor on the Confirmation page")
    def verifyUMInfoAndChargesOnConfirmationPage(@Named("itinerary") String itinerary, @Named("passengers") Integer passengers) {
        confirmationPage.verifyUMPurchaseVerbiageOnConfirmationPage()
        confirmationPage.verifyAssistanceRequestedText()
        confirmationPage.verifyViewParentGuardianInfoLink()
        verifyEarlyBirdUpsellNotPresent()
        confirmationPage.verifyUMChargePricingSectionShowCorretValues(itinerary, passengers)
        confirmationPage.verifyTripAndAirTotals()
    }

    @Then("I see the text '\$text' on the Confirmation page")
    void textIsShownInPage(String text) {
        confirmationPage.shownInPage(text)
    }

    @Then("I should not see the text '\$text' on the Confirmation page")
    void textIsNoShownInPage(String text) {
        confirmationPage.notShownInPage(text)
    }

    @Then("I see the view details link on the Confirmation page")
    void viewDetailsLinkIsPresent() {
        confirmationPage.viewDetailsLinkShouldBeDisplayed()
    }

    @Then("I see the verbiage 'New purchases added to your trip'")
    void verifyConfirmationDetailsAsTitle() {
        confirmationPage.verifyConfirmationTitleDetailsOnPage()
    }

    @Then("I should not see any references to other purchases as the Air product is not added to a trip")
    void referencesToOtherPurchasesAreNotShown() {
        confirmationPage.tripManagementTitleSumaryIsNotPresent()
        confirmationPage.tripManagementDetailsIsNotPresent()
        confirmationPage.viewDetailsLinkShouldNotBeDisplayed()
    }

    @Then("I see references to the product added to the trip on the Confirmation page")
    void referencesToTripManagementDetailsAreShown() {
        confirmationPage.shownInPage("New Purchases in Trip")
        confirmationPage.verifyConfirmationTitleDetailsOnPage()
        confirmationPage.viewDetailsLinkShouldBeDisplayed()
    }

    @Then("I view an Oops message which indicates that Unaccompanied Minors are not eligible for check-in online")
    def shouldShowOopsMessageAboutUMNotEligibleForCheckinOnline() {
        confirmationPage.verifyUMNotEligibleForCheckinOnlineOopsMessage()
    }

    @Then("I see that the Air product just booked has the name \$tripName on the Confirmation Page")
    void airBookingHasATripNameSetOnConfirmation(String tripName) {
        confirmationPage.verifyNewTripNameExists(tripName)
    }

    @Then("I see that the air product just booked has no trip name provided by the user")
    void iSeeDefaultTripNameOnConfirmation() {
        String tripName = TripManagement.getDefaultTripName(itineraryData.departureDate, itineraryData.arrivalStation)
        confirmationPage.verifyTripNameIsFormattedByDefault(tripName)
    }

    @Then("I see that the itinerary displayed matches the itinerary of my booked flight on the Confirmation Page")
    def verifyItineraryMatchesBookedFlight() {
        confirmationPage.verifyItineraryMatchesBookedFlight()
    }

    @Then("I should see the confirmation page that has RR number displayed")
    def verifyPNRandLoginRRnumber() {
        confirmationPage.verifyPage()
        confirmationPage.verifyPNRs()
        confirmationPage.verifyLoginRRNumber()
    }

    @Then("I should get an oops message that these fares are not combinable")
    def verifyDingAndSeniorFaresAreNotCombinable() {
//        confirmationPage.verifyDingAndSeniorFaresAreNotCombinableOopsMessage()
    }

    @Then("I will see an Oops message telling me that I am not eligible for checking in online")
    def verifyCheckinUnavailable() {
        confirmationPage.verifyNotEligibleForCheckin()
    }

    @Then("I verify that check-in is available")
    void verifyCheckinAvailable() {
        confirmationPage.verifyCheckinButtonIsAvailable()
    }

    @Then("I verify that check-in is not available")
    void verifyCheckinNotAvailable() {
        confirmationPage.verifyCheckinButtonIsUnavailable()
    }

    @Then("I get a confirmation of Air & Car added to new trip")
    void airCarAddedToNewTrip() {
        confirmationPage.verifyTripDetailsPresence()
        confirmationPage.verifyNewCarConfirmationPresent()
        confirmationPage.verifyNewAirConfirmationPresent()
        confirmationPage.verifyTripName(trip.name)
        confirmationPage.verifyNewAirProductExpanded()
        confirmationPage.verifyNewCarProductIsCollapsed()
    }

    @Then("the hotel cross sell is not displayed")
    void hotelCrossSellNotDisplay() {
        confirmationPage.verifyHotelCrossSellIsNotDisplayed()
    }

    @Then("I get a confirmation that the car has been added to that trip")
    void verifyCarConfirmationNumberOnExistingNamedTrip() {
        confirmationPage.verifyNewCarConfirmationPresent()
        confirmationPage.verifyNewCarProductExpanded()
        confirmationPage.verifyNumberOfExistingHotelProductsIs(1)
        confirmationPage.verifyTripName(trip.name)
        confirmationPage.verifyTripDetailsPresence()
        confirmationPage.verifyAllExistingProductsAreCollapsed()
    }

    @Then("I should see a car confirmation number")
    def verifyCarConfirmationPresent() {
        confirmationPage.verifyCarPnr()
    }

    @Then("I receive a hotel confirmation number")
    def receiveHotelConfirmationNumber() {
        confirmationPage.verifyPage()
        confirmationPage.confirmHotel();
    }

    @Then("I see the products which belong to the existing trip with their details hidden on the Confirmation page")
    void verifyExistingTripsDetailsAndTitles(){
        confirmationPage.verifyNewAirConfirmationPresent()
        confirmationPage.isMsgExistingPurchasesInYourTripDisplayed()

        confirmationPage.verifyExistingProductsSectionTitle()
        confirmationPage.verifyNewCarConfirmationPresent()

        confirmationPage.verifyNumberOfExistingAirProductsIs(1)
        confirmationPage.verifyNumberOfExistingCarProductsIs(1)
    }

    @Then('I should not see the Car Cross Sell Section on the Confirmation Page')
    void verifyCarProductCrossSellNotDisplayed() {
        confirmationPage.verifyCarCrossSellSectionNotPresent()
    }

    @Then("I see the Business Select text on the Confirmation Page")
    void verifyBusinessSelectText(){
        confirmationPage.verifyBusinessSelectText()
    }

    @Then("I see out of memory oops")
    void outOfMemoryOops() {
        JmxMetricsApi jmxMetricsApi = null
        if (!DynaStubsIntegration.useDynaStubs()) {
            jmxMetricsApi = new JmxMetricsApi()
        }

        confirmationPage.outOfMemoryOops()

        if (!DynaStubsIntegration.useDynaStubs()) {
            jmxMetricsApi.retrieveCreateReservationMetricValue().shouldBeLessThan 2
        }
    }

    @Then("I verify web metrics value")
    void verifyWebMetricsValue() {
        JmxMetricsApi jmxMetricsApi = new JmxMetricsApi()
        confirmationPage.outOfMemoryOops()
        jmxMetricsApi.retrieveWebReservationMetricValue().shouldBe 1
    }

    @Then("I verify the fare change oops message")
    void verifyFareHasChangedMessage() {
        JmxMetricsApi jmxMetricsApi = new JmxMetricsApi()
        confirmationPage.fareChangeOopsMessage()
        jmxMetricsApi.retrieveWebReservationMetricValue().shouldBe 1
    }

    @Then("I verify hibernate fault results")
    void verifyHibernateFaultResults() {
        JmxMetricsApi jmxMetricsApi = null
        if (!DynaStubsIntegration.useDynaStubs()) {
            jmxMetricsApi = new JmxMetricsApi()
        }

        confirmationPage.swaRuntimeExceptionOops()

        if (!DynaStubsIntegration.useDynaStubs() ) {
            jmxMetricsApi.retrieveCreateReservationMetricValue().shouldEqual 1
        }
    }

    @Then("I verify the purchase flow is uninterrupted by an OOPS message")
    void verifyPurchaseFlowUninterruptedByOops() {
        JmxMetricsApi jmxMetricsApi = null
        if (!DynaStubsIntegration.useDynaStubs() ) {
            jmxMetricsApi = new JmxMetricsApi()
        }

        confirmationPage.checkNoOops ()

        if (!DynaStubsIntegration.useDynaStubs() ) {
            jmxMetricsApi.retrieveCreateReservationMetricValue().shouldEqual 1
        }
    }

    @Then("I verify socketTimeout fault results")
    void verifySocketTimeoutFaultResults() {
        sleep(30000)
        JmxMetricsApi jmxMetricsApi = null
        if (!DynaStubsIntegration.useDynaStubs()) {
            jmxMetricsApi = new JmxMetricsApi()
        }
        confirmationPage.swaSocketTimeoutExceptionOops()
        if (!DynaStubsIntegration.useDynaStubs()) {
            jmxMetricsApi.retrieveCreateReservationMetricValue().shouldEqual 0
        }
        confirmationPage.refresh()
        System.out.println("Page refresh done let all services complete....")
        sleep(130000)
        System.out.println("Services should be complete, verify PNRs....")
        confirmationPage.verifyPage()
        confirmationPage.verifyPNRs()
        if (!DynaStubsIntegration.useDynaStubs()) {
            System.out.println("Verify count....")
            jmxMetricsApi.retrieveCreateReservationMetricValue().shouldEqual 1
        }
    }

    @Then ("I should see \$fareType fare for outbound flight")
    void verifyOutboundFareType(String fareType) {
        confirmationPage.verifyOutboundFareType(fareType)
    }

    @Then ("I should see \$fareType fare for inbound flight")
    void verifyInboundFareType(String fareType) {
        confirmationPage.verifyInboundFareType(fareType)
    }

    @Then("I should see the same departure date and time in confirmation page that I selected in Select flights page")
    def verifyDepartureDateAndTime(){
        confirmationPage.verifyDepartureFlightDateAndTimeEquality()
    }

    @Then("I should see the same arrival date and time in confirmation page that I selected in Select flights page")
    def verifyArrivalDateAndTime(){
        confirmationPage.verifyArrivalDateAndTimeEquality()
    }

    @Then("I should see car results reflecting different car vendors among results inside Car Cross-Sell")
    void verifyCarVendorsAreUniqueOnCarCrossSell(){
        confirmationPage.verifyCarVendorsAreUnique()
    }

    @Then("I view my complete itinerary on the confirmation page")
    void verifyCompleteItinerary() {
        confirmationPage.verifyBasicPage()
        confirmationPage.verifyStationsOnHeader()
        confirmationPage.verifyNewAirConfirmationPresent()
        confirmationPage.verifyStationsInAirWidget()
        confirmationPage.verifyAirTotalInAirWidgetWithTotalFromPricePage()
        confirmationPage.verifyAirViewDetailsLinkHref()
        confirmationPage.verifyAirTotalWidgetEqualsTripTotal()
        confirmationPage.verifyHotelWidget()
        confirmationPage.verifyCarWidget()
        confirmationPage.verifyViewBillingDetailsLinkIsPresent()
        //System.out.println("***********************->verifying trip name")  //aruna
        //confirmationPage.verifyTripName(itineraryData.getDefaultTripName())
        confirmationPage.verifyChangeAndCancelLinksPresent()
        confirmationPage.verifyStationsInDetails()
        confirmationPage.verifyConfirmationNumbers()
        confirmationPage.verifyPassengerName()
        confirmationPage.verifyRRLinkPresent()
        confirmationPage.verifyAddEarlyBirdCheckInButtons()
        confirmationPage.verifySubscribeToFlightMessage()
        confirmationPage.verifyDate()
        confirmationPage.verifyDepartureAndArrivalCities()
        confirmationPage.verifyDepartureAndArrivalTime()
        confirmationPage.verifyFlightNumber()
        confirmationPage.verifyTravelTime(true)
        confirmationPage.verifyRoutingType()
        confirmationPage.verifyFareType(true)
        confirmationPage.verifyItinerary()
        if (passengerData.containsSeniorAndAdultPassengers()) {
            priceTable.verifyMixedPaxSubTotalFromSelectFlightPage()
        }
        priceTable.verifyPassengerType()
        priceTable.verifyTripTypes()
        priceTable.verifyRouting()
        priceTable.verifyNumberOfPassengers()
        priceTable.verifyTotalOnPage()
        priceTable.verifyPriceFareBreakDown()
        if(itineraryData.promoCode == null){
            priceTable.verifyBaseFarePlusTax()
        }
        priceTable.verifyTaxesAndBaseFareInFareBreakDown()
        priceTable.verifyAirTotalAndFareBreakDownTotal()
        priceTable.verifyTotalPlusPassengerCount()
        priceTable.verifyRedeemableMemberPoints()
        confirmationPage.verifyAirTotalAndTripTotalAreEqual()
        if(!pricePageData.availableFunds.equals(pricePageData.airTotal)) {
            confirmationPage.verifyPurchaserName()
            confirmationPage.verifyBillingAddress()
        }
        AirReservation airReservation = scenarioState.getLastAirReservation()
        if (!itineraryData.hasTravelFunds || airReservation.getItineraryData().departingFlight_fareClass != BUSINESS_FARE_TYPE){
            confirmationPage.verifyPaymentType()
        }
        if (!itineraryData.hasTravelFunds && !itineraryData.luvVoucher){
            confirmationPage.verifyAmoutAppliedAmountPaidTripTotalAreEqual()
        }
        confirmationPage.verifyAmountPaidEqualsTripTotal()
        confirmationPage.verifyTripTotal()
        if(flow.isLoggedIn && (flow.isRapidRewards || flow.isCustomer)) {
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
            if(flow.isRapidRewards) {
                rapidRewardsAccountBar.verifyRRacountNumber()
                rapidRewardsAccountBar.verifyTier()
                confirmationPage.verifyRRNumber()
            }
        }
        if(flow.isUM){
            confirmationPage.with {
                verifyUMPurchaseVerbiage()
                verifyViewParentGuardianInfoLink()
                verifyAssistanceRequestedText()
                verifyUMPassengerDetails()
            }
        }
        priceTable.verifyOutboundAndInboundTotalAmounts()
        priceTable.verifyFareType()
        priceTable.verifySumSubtotalEqualsAirTotal()
        priceTable.verifySubTotal()
        if(itineraryData.luvVoucher) {
            confirmationPage.verifyFundApplied()
            confirmationPage.verifyTotalAmountPaid()
            confirmationPage.verifyPaymentAmount()
            confirmationPage.verifyLuvVoucherInformation()
        } else if (itineraryData.giftCard){
            confirmationPage.verifyBalanceInformation()
            confirmationPage.verifyAmountApplied()
        } else if (itineraryData.hasTravelFunds){
            confirmationPage.with {
                verifyAmountPaidEqualsAirTotalWidget()
                if (airReservation.getItineraryData().departingFlight_fareClass != BUSINESS_FARE_TYPE){
                    verifyCreditCardAmountApplied()
                }
                verifyTravelFundsPaymentType()
                verifyOriginalBalance()
                verifyAppliedAmount()
                verifyRemainingFunds()
                verifyAmountAppliedInPage()
                verifyAmountPaid()
                verifyAmountPaidAndTripTotalAreEqual()
            }
        } else {
            confirmationPage.verifyAmountAppliedToCCEqualsAirTotal()
        }
        flow.hasAir = true
        confirmationPage.storeCurrentPNR()
    }

    @Then("I see the confirmation for my PromoCert purchase")
    def promoCertPurchaseConfirmation(){
        confirmationPage.with{
            verifyConfirmationLinks()
            verifyViewBillingDetailsLinkIsPresent()
            verifyAirTotalAndTripTotalAreEqual()
            verifyShowHideDetailsLinks()
            verifyCalendarIcons()
        }
        confirmationPage.verifyPromoCertPaymentType()

        verifyCommonItinerary()
    }

    @Then("I see the confirmation for my RR points purchase")
    def rrPointsPurchaseConfirmation() {
        if (!DynaStubsIntegration.useDynaStubs()) {
            rapidRewardsAccountBar.verifyAvailablePts()
        }

        confirmationPage.with {
            verifyRRNumber()
            verifyAirViewDetailsLinkHref()
            verifyPaymentType()
            verifyAmountPaidEqualsAirTotalWidget()
        }

        priceTable.with {
            verifyTotalPoints()
            verifyAirTotalAndFareBreakDownTotal()
            verifyGovTaxesFeesLink()
        }

        verifyCommonItinerary()
    }

    def verifyCommonItinerary(){
        confirmationPage.verifyBasicPage()
        rapidRewardsAccountBar.with{
            verifyRRGreeting()
            verifyLogOutLink()
            verifyRRName()
            verifyMyAccountLink()
            verifyTier()
            verifyRRacountNumber()
        }
        confirmationPage.with{
            verifyStationsOnHeader()
            verifyNewAirConfirmationPresent()
            verifyStationsInAirWidget()
            verifyAirViewDetailsLinkHref()
            verifyAirTotalInAirWidgetWithTotalFromPricePage()
            verifyAirTotalWidgetEqualsTripTotal()
            verifyHotelWidget()
            verifyCarWidget()
            verifyTripName(itineraryData.getDefaultTripName())
            verifyChangeAndCancelLinksPresent()
            verifyItinerary()
            verifyConfirmationNumbers()
            verifyPassengerName()
            verifyRRNumber()
            verifySubscribeToFlightMessage()
            verifyDate()
            verifyDepartureAndArrivalTime()
            verifyDepartureAndArrivalCities()
            verifyFlightNumber()
            verifyTravelTime()
            verifyRoutingType()
            verifyFareType()
        }
        priceTable.with {
            verifyPassengerType()
            verifyTripTypes()
            verifyRouting()
            verifyFareType()
            verifyNumberOfPassengers()
            verifyOutboundAndInboundTotalAmounts()
            verifySubTotal()
            verifyPriceFareBreakDown()
        }

        confirmationPage.with{
            verifyPurchaserName()
            verifyBillingAddress()
            verifyAmountApplied()
            verifyAmountAppliedToCCEqualsAirTotal()
            verifyTotalAmountPaid()
            verifyTripTotal()
        }
        flow.hasAir = true
        confirmationPage.storeCurrentPNR()
    }

    @Then("I view RR Sign Up Module in Confirmation Page")
    void verifyRRSignUpInConfirmationPage() {
        confirmationPage.verifyRRSignupModule()
    }

    @Then("I verify RR Sign Up Module is not present in Confirmation Page")
    void verifyRRSignUpModuleNotPresentInConfirmationPage() {
        confirmationPage.verifyRRSignupModuleNotPresent()
    }

    @Then("I see a fare discount on confirmation flyout equal to \$amount")
    def verifyFareDiscountEqual(String amount) {
        FareBreakdownFlyOut flyout = confirmationPage.openFareBreakDownFlyOut()
        flyout.fareDiscount.shouldBe amount.toBigDecimal(), "Discount not equal to specified amount"
        flyout.closeFlyout()
    }
}
