package steps

import builders.ReservationSpecificationBuilder
import fixture.stubs.DynaStubsIntegration
import fixtures.air.ReservationSpecification
import fixtures.air.enums.ItineraryType
import fixtures.air.enums.FareType
import org.jbehave.core.annotations.When
import org.joda.time.LocalDateTime
import pages.PurchasePage
import pages.SearchFlightsPage
import pages.SelectFlightsPage
import pages.elements.FlightSearchForm
import pages.elements.LoginForm
import state.Flow
import util.ItineraryData
import static fixtures.air.enums.DirectionType.INBOUND
import static fixtures.air.enums.DirectionType.OUTBOUND
import static fixtures.air.enums.PaymentType.POINTS

class AirBookingDataConsumptionSteps {

    ReservationSpecificationBuilder reservationBuilder
    FlightSearchForm flightSearchForm
    SearchFlightsPage searchFlightsPage
    SelectFlightsPage selectFlightsPage
    NavigationSteps navigationSteps
    PurchasePage purchasePage
    LoginForm loginForm
    Flow flow
    ItineraryData itineraryData

    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    @When("I choose a flight that matches my criteria")
    def iChooseAFlightThatMatchesMyCriteria() {
        ReservationSpecification reservationSpecification = reservationBuilder.build()
        addAirBasedOnSpecificationToShoppingCart(reservationSpecification)
    }

    @When("I book a flight that matches my criteria")
    def iBookAFlightThatMatchesMyCriteria() {
        ReservationSpecification reservationSpecification = reservationBuilder.build()
        addAirBasedOnSpecificationToShoppingCart(reservationSpecification)

        navigationSteps.continueToPurchasePage()
        purchasePage.fillInAllInformationBasedOn(reservationSpecification)

        if (flow.completePurchase) {
            navigationSteps.clickContinueToComplete()
        } else {
            purchasePage.clickVisibleSubmit()
            flow.completePurchase = true // for next scenario
        }
    }

    @When("I search and select my flights and attempt to continue to the Price page")
    def searchAndSelectFlightsAndStayInUpsellModal() {
        reservationBuilder.withSeniorPassengerCount(itineraryData.seniorPassengerCount.toInteger())
        reservationBuilder.withAdultPassengerCount(itineraryData.adultPassengerCount.toInteger())
        reservationBuilder.withItineraryType(ItineraryType.byLabel(itineraryData.itineraryType))
        reservationBuilder.withOriginStation(itineraryData.departureStation)
        reservationBuilder.withDestinationStation(itineraryData.arrivalStation)
        reservationBuilder.withOutboundDepartureDateTime(new LocalDateTime().fromDateFields(itineraryData.departureDate), null)
        reservationBuilder.withInboundDepartureDateTime(new LocalDateTime().fromDateFields(itineraryData.returnDate), null)
        reservationBuilder.withOutboundCarrier(itineraryData.departingFlight_carrierCode)
        reservationBuilder.withInboundCarrier(itineraryData.arrivingFlight_carrierCode)
        reservationBuilder.withFareType(OUTBOUND, FareType.byLabel(itineraryData.departingFlight_fareClass))
        reservationBuilder.withFareType(INBOUND, FareType.byLabel(itineraryData.arrivingFlight_fareClass))

        ReservationSpecification reservationSpecification = reservationBuilder.build()
        addAirBasedOnSpecificationToShoppingCartAndStayInUpsellModal(reservationSpecification)
    }

    private void addAirBasedOnSpecificationToShoppingCart(ReservationSpecification reservationSpecification) {
        addAirBasedOnSpecificationFromReservationSpecification(reservationSpecification)
        selectFlightsPage.selectFlightsBasedOn(reservationSpecification)
    }

    private void addAirBasedOnSpecificationToShoppingCartAndStayInUpsellModal(ReservationSpecification reservationSpecification) {
        addAirBasedOnSpecificationFromReservationSpecification(reservationSpecification)
        selectFlightsPage.selectFlightsAndStayInModalBasedOn(reservationSpecification)
    }

    private void addAirBasedOnSpecificationFromReservationSpecification(ReservationSpecification reservationSpecification) {
        dynaStubsIntegration.prepareShoppingSchedules(reservationSpecification)
        flow.hasAir = true

        if (reservationSpecification.hasDingFare()) {
            goToSelectFlightPageWithDingFares(reservationSpecification)
        } else {
            searchFlightsPage.open()
            flightSearchForm.searchBasedOn(reservationSpecification)
        }

        if (reservationSpecification.paymentType == POINTS) {
            flow.isRapidRewards = true
            flow.isRapidRewardsPointsPurchaseOnly = true
            loginForm.loginRapidRewardsMember("AlistPreferred")
            selectFlightsPage.togglePoints()
        }
    }

    private void goToSelectFlightPageWithDingFares(ReservationSpecification reservationSpecification) {
        def seniorPassengersCount = reservationSpecification.getSeniorPassengers().size()
        def adultPassengersCount = reservationSpecification.getAdultPassengers().size()

        selectFlightsPage.openDingSelectFlightPage(reservationSpecification)
        if (adultPassengersCount > 1 || seniorPassengersCount > 0) {
            selectFlightsPage.modifySearchSetNumberOfPassenger(adultPassengersCount, seniorPassengersCount)
        }
    }
}
