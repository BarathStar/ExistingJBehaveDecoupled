package steps.thenSteps

import org.jbehave.core.annotations.Then

import pages.ChangeAirReservationPage
import pages.ConfirmationPage
import pages.ItineraryChangeConfirmationPage
import pages.ReconcilePage
import pages.SelectFlightsPage
import pages.AirTranSeatSelectPage
import pages.NewPricePage
import pages.elements.PriceTable
import pages.elements.RapidRewardsAccountBar
import state.Flow
import util.ItineraryData
import util.PricePageData

class AirChangeThenSteps {

    private static final String OOPS_MYIDTRAVEL_MESSAGE = "The nonrevenue flight listing that you're attempting to access was made through MyID Travel and is not accessible via southwest.com, swabiz.com or mobile.southwest.com. To access your flight listing, please visit the MyID Travel site."

    ChangeAirReservationPage changeAirReservationPage
    ReconcilePage reconcilePage
    ConfirmationPage confirmationPage
    AirTranSeatSelectPage airTranSeatSelectPage
    NewPricePage newPricePage
    SelectFlightsPage selectFlightsPage
    Flow flow
    RapidRewardsAccountBar rapidRewardsAccountBar
    ItineraryChangeConfirmationPage itineraryChangeConfirmationPage
    ItineraryData itineraryData
    PriceTable priceTable
    PricePageData pricePageData

    @Then("I will be asked for the details of the trip I wish to change")
    def checkChangeReservationPage() {
        changeAirReservationPage.verifyPage()
    }

    @Then("I should view AirTran messaging on the Reconcile page")
    def verifyAirTranMessagingOnReconcilePage() {
        reconcilePage.verifyAirTranTravelFundMsg()
    }

    @Then("I should not be able to change the name of the trip on the Reconcile Page")
    void verifyTripNameSectionNotDisplayed() {
        reconcilePage.verifyPage()
        reconcilePage.verifyTripNameSectionNotDisplayed()
    }

    @Then("I am able to add a trip name to the Air product reservation on the Reconcile Page")
    void verifyTripNameSectionIsDisplayed() {
        reconcilePage.verifyPage()
        reconcilePage.verifyTripNameSectionIsDisplayed()
    }

    @Then("I don't see the seat selection button for my \$outboundOrInbound flight")
    public void verifySeatSelectionElementNotPresentFor(String outboundOrInbound) {
        airTranSeatSelectPage.verifySeatSelectionElementNotPresentFor(outboundOrInbound)
    }

    @Then("I should see the Price Page with the new price")
    def shouldSeePricePage() {
        newPricePage.verifyPage()
    }

    @Then("I should be informed that my flight is in progress")
    def verifyCallSouthwestErrorMessageIsDisplayed() {
        changeAirReservationPage.verifyFlightInProgressOopsMessage()
    }

    @Then("I view the OOPS message for the invalid PNR on the Change Air Reservation Page")
    void verifyOopsOnTheChangeAirReservationPage() {
        changeAirReservationPage.verifyInvalidPnrOopsMessage()
    }

    @Then("I should not be able to add the trip to an existing trip on the Reconcile page")
    void existingTripsDropDownNotPresent() {
        reconcilePage.verifyNameThisTripIsChecked()
    }

    @Then("I should see the existing trips drop down on the Reconcile page")
    void existingTripsDropDownIsPresent() {
        reconcilePage.verifyExistingTripsIsPresent()
    }

    @Then("the default trip name is correctly formatted on the Reconcile page")
    void verifyDefaultTripNameFormat() {
        reconcilePage.verifyTripNameIsFormattedByDefault()
    }

    @Then("I see the new trip name radio button selected on the Reconcile page")
    void newTripNameSelected() {
        reconcilePage.verifyNewTripNameIsChecked()
    }

    @Then("I see the name this trip option checked on the Reconcile page")
    void nameThisTripSelected() {
        reconcilePage.verifyNameThisTripIsChecked()
    }

    @Then("I should not see the red asterisk inside the contact info area")
    void verifyContactInfoIsNotRequired() {
        reconcilePage.verifyRedAsteriskContactInfo()
    }

    @Then("I should see the travel funds that has been applied in a correct manner")
    def verifyAppliedFoundChange(){
        reconcilePage.verifyAppliedFunds()
    }

    @Then("I see the trip section where the option to name the trip is offered by default on the Reconcile page")
    def verifyTripSection(){
        reconcilePage.verifyNameThisTripIsPresent()
        reconcilePage.verifyNameThisTripIsChecked()
        reconcilePage.verifyNewTripNameIsChecked()
        reconcilePage.verifyTripNameIsFormattedByDefault()
    }

    @Then("I see the option to add the Air product to an existing trip on the Reconcile page")
    def verifyAddToExistingTripIsPresent(){
        reconcilePage.verifyExistingTripsIsPresent()
        reconcilePage.verifyExistingTripNameDisabled()
    }

    @Then("I see Southwest Airlines - Change Itinerary page")
    def verifyIamOnTheChangeItineraryPage() {
        changeAirReservationPage.verifyPage()
        changeAirReservationPage.verifyIamOnTheCorrectPage("flight/view-reservation-to-change.html")
        changeAirReservationPage.verifyPageTitle("Southwest Airlines - Change Itinerary")
    }

    @Then("I see the passenger's name on Change Itinerary page")
    def verifyPassengerNames() {
        changeAirReservationPage.verifyPassengerNames()
    }

    @Then("I see the confirmation number of the Air product on Change Itinerary page")
    def verifyConfirmationNumber() {
        changeAirReservationPage.verifyConfirmationNumber()
    }

    @Then("I see the ticket for the Round Trip (nonstop) Air product on Change Itinerary page")
    def verifyTicketForRoundTrip() {
        changeAirReservationPage.verifyTicketForRoundTrip()
    }

    @Then("I see a message which informs that Freedom Awards cannot be combined with tickets purchased with dollars")
    def verifyFreedomAwardsConnotBeConbinedWithDollards() {
        selectFlightsPage.verifyFreedomAwardsConnotBeConbinedWithDollards()
    }

    @Then("I see a message which informs that Standard and Freedom Awards cannot be combined with tickets purchased with dollars")
    def verifyStandardAndFreedomAwardsCannotBeCombinedWithDollars() {
        selectFlightsPage.verifyStandardAndFreedomAwardsCannotBeCombinedWithDollars()
    }

    @Then("I see the expiration date of the freedom award used when the flight was booked")
    def verifyFreedomAwardsExpirationDateBooking() {
        selectFlightsPage.verifyFreedomAwardsExpirationDateBooking()
    }

    @Then("I should not see the dollar-points toggle")
    def verifyDollarPointToggleOff() {
        selectFlightsPage.verifyDollarPointToggleOff()
    }

    @Then("I see Freedom Award as the only fare available for both outbound and inbound flight")
    def verifyFreedomAwardIsOnlyAvailableFare() {
        selectFlightsPage.verifyFreedomAwardIsOnlyAvailableFare()
    }

    @Then("I see Freedom Award as the only fare available for the inbound flight")
    def verifyFreedomAwardIsOnlyAvailableFareForInboundFlight() {
        selectFlightsPage.verifyFreedomAwardIsOnlyAvailableFareForInboundFlight()
    }

    @Then("I see the My Id Travel Oops message on Change page")
    def shouldShowMyIdTravelOopsMessage() {
        changeAirReservationPage.shouldShowOopsMessage(OOPS_MYIDTRAVEL_MESSAGE)
    }

    @Then("I see the itinerary changed page")
    def verifyItineraryChangedPage() {
        itineraryChangeConfirmationPage.verifyPage()
        if(flow.isRapidRewards) {
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                verifyTier()
                verifyRRacountNumber()
                if (flow.isRapidRewardsPointsPurchaseOnly) {
                    verifyAvailablePointsAfterChange()
                }
            }
            itineraryChangeConfirmationPage.verifyRRNumber()
        }
        itineraryChangeConfirmationPage.with {
            verifyStationsOnHeader()
            verifyNewAirConfirmationPresent()
            verifyStationsInAirWidget()
            verifyAirViewDetailsLinkHref()
            verifyViewBillingDetailsLinkIsPresent()
            verifyAirTotalInAirWidgetWithTotalFromPricePage()
            verifyAmountPaidEqualsTripTotal()
            verifyAirTotalWidgetEqualsTripTotal()
            verifyHotelWidget()
            verifyCarWidget()
            verifyTripName(itineraryData.getDefaultTripName())
            verifyChangeAndCancelLinksPresent()
            verifyItinerary()
            verifyConfirmationNumbers()
            verifyPassengerName()
            verifyAddEarlyBirdCheckInButtonDisplayed()
            verifySubscribeToFlightMessage()
            verifyRRLinkPresent()
            verifyRoutingType()
            verifyFareType()
            verifyPaymentType()
            verifyFundsApplied()
            if(!purchasePageData.totalTravelFundsApplied.equals(pricePageData.airTotal) || flow.isRapidRewardsPointsPurchaseOnly){
                verifyPurchaserName()
                verifyBillingAddress()
            }
            if(flow.isRapidRewardsPointsPurchaseOnly) {
                verifyUsedAndReturnedPoints()
                verifyRRAccountNumberOnFormOfPayment()
                verifyFootNoteTextIsPresent()
            }
            verifyAmountPaidEqualsTripTotal()
            verifyTripTotal()
        }
        newPricePage.with {
            verifyDate()
            verifyDepartureAndArrivalTime()
            verifyDepartureAndArrivalCities()
            verifyFlightNumber()
            verifyTravelTime()
        }
        priceTable.with {
            if(!flow.isRapidRewardsPointsPurchaseOnly){
                verifyBaseFarePlusTax()
                verifyTaxesAndBaseFareInFareBreakDown()
                verifyRedeemableMemberPoints()
            }
            if(!flow.isUpgradingFromCheckin) {
                verifyOutboundAndInboundTotalAmounts()
            }
            verifyPassengerType()
            verifyTripTypes()
            verifyRouting()
            verifyFareType()
            verifyNumberOfPassengers()
            verifySubTotal()
            verifyPriceFareBreakDown()
            verifyAirTotalAndFareBreakDownTotal()
        }
    }

}
