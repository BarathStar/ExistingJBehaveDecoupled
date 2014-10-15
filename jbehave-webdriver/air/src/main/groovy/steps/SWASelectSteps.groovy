package steps

import com.swacorp.common.utility.SwaUtils
import com.swacorp.dotcom.webscenarios.air.Data
import org.openqa.selenium.By
import pages.elements.BookATripModal
import pages.elements.FlightSearchForm
import pages.elements.HomePageFlightSearchForm
import pages.elements.RapidRewardsAccountBar
import pages.SWASelectPage
import state.Flow
import state.PassengerData
import steps.conditional.ToggleHomepage2
import util.CustomerInfoData
import util.ItineraryData
import util.ItineraryDateFactory
import util.PassengerType
import static java.util.Calendar.DAY_OF_MONTH
import static java.util.Calendar.YEAR
import org.jbehave.core.annotations.*
import pages.*
import static util.Locators.ELEMENT_IDS
import com.swacorp.dotcom.webscenarios.air.SwaBizData
import fixture.stubs.DynaStubsIntegration
import com.swacorp.dotcom.webscenarios.air.PromoDiscountData

class SWASelectSteps {

    private final static HOME_PAGE_SUBMIT_BUTTON = "booking_widget_content_row_btn_search"
    private final static TRIP_SEARCH_MODAL_SUBMIT_BUTTON = "tripSearchModalSubmit"
    private final static BS = "BusinessSelect"
    private final static WGA = "WannaGetAway"
    private final static AT = "Anytime"
    private final static NS = "Nonstop"
    SearchFlightsPage searchFlightsPage
    SWASelectPage selectPage
    SelectFlightsPage selectFlightsPage
    FlightSearchForm flightSearchForm
    OutboundAndReturnDatesAndPopUp calendarPopUp
    ItineraryData itineraryData
    Itinerary itinerary
    ViewReservationPage viewReservationPage
    Data data
    PassengerData passengerData
    HomePage homePage
    Flow flow
    CustomerInfoData customerInfoData
    RapidRewardsAccountBar rapidRewardsAccountBar


    @Given("select the trip type as \$TripType")
    def selectTrip(@Named("TripType") String trip_type) {
        selectPage.selectTripType(trip_type)
    }


    @Given("select the carrier type \$OutboundCarrierType")
    def outCarrier(@Named("OutboundCarrierType") String outbound_carrier_type) {
        selectPage.selectOutBoundCarrierType(outbound_carrier_type)
    }

    @Given("select the carrier type \$InboundCarrierType carrier type")
    def inCarrier(@Named("InboundCarrierType") String inbound_carrier_type) {
        selectPage.selectInBoundCarrierType(inbound_carrier_type)
    }

    @Given("select an airtran \$tripType flight")
    def airtranTrip(@Named("tripType") String tripType) {
        selectPage.selectAirtranTrip(tripType)

    }

    @Given("select the departure city \$DepartureCity")
    def fromTrip(@Named("DepartureCity") String departure_city) {
        selectPage.selectDepartureLocation(departure_city)
    }

    @Given("select the arrival city \$ArrivalCity")
    def toTrip(@Named("ArrivalCity") String arrival_city) {
        selectPage.selectArrivalLocation(arrival_city)
    }

    @Given("select the return city \$ReturnCity")
    def arrivalTrip(@Named("ReturnCity") String return_city) {
        selectPage.selectReturnLocation(return_city)
    }

    @Given("select the outbound \$OutboundRouteType trip flight")
    def obTrip(@Named("OutboundRouteType") String outbound_route_type) {
        selectPage.selecOutBoundTripType(outbound_route_type)
    }

    @Given("select the inbound \$InboundRouteType trip flight")
    def ibTrip(@Named("InboundRouteType") String inbound_route_type) {
        selectPage.selectInBoundTripType(inbound_route_type)
    }

    @Given("select \$OutboundFareType fare for outbound")
    def selectOutBoundFare(@Named("OutboundFareType") String outbound_fare_type) {
        selectPage.selectOutBoundFareType(outbound_fare_type)
    }

    @Given("select \$InboundFareType fare for inbound")
    def selectOneWayFare(@Named("InboundFareType") String inbound_fare_type) {
        selectPage.selectInBoundFareType(inbound_fare_type)
    }

    @Given("select the \$PassengerCount {passenger|passengers} with passenger type \$PassengerType")
    def selectPassengers(@Named("PassengerCount ") int passenger_count ,@Named("PassengerType") String passenger_type) {
        selectPage.selectPassengers(passenger_count,passengerType)
    }

    @Given("select flight which is eligible for check in")
    def setFlightThatIsEligibleForCheckin(){
        selectPage.setFlightEligibleForCheckin()
    }

    @Given("select the promo code \$PromoCode")
    def specifiedPromoCode(@Named("PromoCode")  String promo_code){
        selectPage.specifiedPromoCode(promo_code)
    }
    @Given("select fare in \$CurrencyType")
    @When("select fare in \$CurrencyType")
    void chooseFaresIn(@Named("CurrencyType")  String currency_type) {
        selectPage.bookedFlightWithCurrency(currency_type)
    }

    @When("I select the flight")
    def selectRoundTripCitiesWithFare()
    {
        selectPage.selectFlight()
    }

}
