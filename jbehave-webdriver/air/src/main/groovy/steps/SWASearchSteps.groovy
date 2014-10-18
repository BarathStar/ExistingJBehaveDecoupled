package steps

import com.swacorp.dotcom.webscenarios.air.Data
import pages.elements.FlightSearchForm
import pages.elements.RapidRewardsAccountBar
import pages.SWASearchPage
import state.Flow
import state.PassengerData
import util.CustomerInfoData
import util.ItineraryData
import org.jbehave.core.annotations.*
import pages.*
import util.ItineraryDateFactory

class SWASearchSteps {

    private final static HOME_PAGE_SUBMIT_BUTTON = "booking_widget_content_row_btn_search"
    private final static TRIP_SEARCH_MODAL_SUBMIT_BUTTON = "tripSearchModalSubmit"
    private final static BS = "BusinessSelect"
    private final static WGA = "WannaGetAway"
    private final static AT = "Anytime"
    private final static NS = "Nonstop"
    SearchFlightsPage searchFlightsPage
    SWASearchPage searchPage
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

    /**
     * Allows selection of trip type.  Will take roundtrip, oneway, openjaw
     * @param trip_type
     */
    @Given("I want to book a \$TripType flight")
    def selectTrip(@Named("TripType") String trip_type) {
        searchPage.selectTripType(trip_type)
    }

    /**
     * Allows for selection of a date within 24 hours works with either departure or retrun
     * @param stationType
     */
    @Given("a \$StationType within 24 hours")
    def departWithin24Hrs(@Named("StationType") String stationType) {
        if(stationType.equals("departure")) {
            itineraryData.departureDate = ItineraryDateFactory.chooseDayEligibleForDepartureCheckin()
        }
        if(stationType.equals("return")) {
            itineraryData.returnDate = ItineraryDateFactory.chooseDayEligibleForReturnCheckin()
        }
    }

    /**
     * Allows for selection of an AirTran flight
     */
    @Given("The flight is an airtran flight")
    def airtranFlight() {
        searchPage.setAirtranFlight()
    }

    /**
     * Allows for selection of the carrier type.  Takes either "wn" or "fl"
     * @param outbound_carrier_type
     */
    @Given("The outbound carrier type is \$OutboundCarrierType")
    def outCarrier(@Named("OutboundCarrierType") String outbound_carrier_type) {
        searchPage.selectOutBoundCarrierType(outbound_carrier_type)
    }

    /**
     * Allows for selection of inbound carrier type.  Takes either "wn" or "fl"
     * @param inbound_carrier_type
     */
    @Given("The inbound carrier type is \$InboundCarrierType")
    def inCarrier(@Named("InboundCarrierType") String inbound_carrier_type) {
        searchPage.selectInBoundCarrierType(inbound_carrier_type)
    }

    /**
     * Searches for an AirTran flight.  tripType takes roundtrip, oneway, openjaw
     * @param tripType
     * @return
     */
    @Given("The flight is an airtran \$tripType flight")
    def airtranTrip(@Named("tripType") String tripType) {
        searchPage.selectAirtranTrip(tripType)
    }

    /**
     * Sets the departure city in the search page
     * @param departure_city
     * @return
     */
    @Given("The departure city is \$DepartureCity")
    def fromTrip(@Named("DepartureCity") String departure_city) {
        searchPage.selectDepartureLocation(departure_city)
    }

    /**
     * Sets the arrival city in the search page
     * @param arrival_city
     */
    @Given("The arrival city is \$ArrivalCity")
    def toTrip(@Named("ArrivalCity") String arrival_city) {
        searchPage.selectArrivalLocation(arrival_city)
    }

    /**
     * Sets the return city in the search page
     * @param return_city
     */
    @Given("The return city is \$ReturnCity")
    def arrivalTrip(@Named("ReturnCity") String return_city) {
        searchPage.selectReturnLocation(return_city)
    }

    /**
     * Selects a flight that matches outbound routing type ie nonstop or number of stops
     * @param outbound_route_type
     */
    @Given("The outbound trip type is \$OutboundRouteType")
    def obTrip(@Named("OutboundRouteType") String outbound_route_type) {
        searchPage.selectOutBoundTripType(outbound_route_type)
    }

    /**
     * Selects outbound trip type takes nonstop, roundtrip, openjaw
     * @param inbound_route_type
     */
    @Given("The inbound trip type is \$InboundRouteType")
    def ibTrip(@Named("InboundRouteType") String inbound_route_type) {
        searchPage.selectInBoundTripType(inbound_route_type)
    }

    /**
     * Selects the fare type for outbound ie points or dollars
     * @param outbound_fare_type
     */
    @Given("The outbound fare type is \$OutboundFareType")
    def selectOutBoundFare(@Named("OutboundFareType") String outbound_fare_type) {
        searchPage.selectOutBoundFareType(outbound_fare_type)
    }

    /**
     * Selects the fare type for inbound ie points or dollars
     * @param inbound_fare_type
     */
    @Given("The inbound fare type is \$InboundFareType")
    def selectOneWayFare(@Named("InboundFareType") String inbound_fare_type) {
        searchPage.selectInBoundFareType(inbound_fare_type)
    }

    /**
     * Selects the number and type of passengers
     * @param passenger_count
     * @param passenger_type
     */
    @Given("Book the ticket for \$PassengerCount {passenger|passengers} with passenger type \$PassengerType")
    def selectPassengers(@Named("PassengerCount ") int passenger_count ,@Named("PassengerType") String passenger_type)
    {
        searchPage.selectPassengers(passenger_count,passenger_type)
    }

    /**
     * Selects a flight that is eligible for checkin
     */
    @Given("The flight is eligible for check in")
    def setFlightThatIsEligibleForCheckin(){
        searchPage.setFlightEligibleForCheckin()
    }

    /**
     * Applies the specified promo code
     * @param promo_code
     */
    @Given("Book the ticket using promocode \$PromoCode")
    def specifiedPromoCode(@Named("PromoCode")  String promo_code){
        searchPage.specifiedPromoCode(promo_code)
    }

    /**
     * Selects the currency type when searching for a flight
     * @param currency_type
     */
    @Given("Book the ticket using fare in \$CurrencyType")
    @When("Book the ticket using fare in \$CurrencyType")
    void chooseFaresIn(@Named("CurrencyType")  String currency_type) {
        searchPage.bookedFlightWithCurrency(currency_type)
    }

    /**
     * Searches for flight
     */
    @Given("I search the flight")
    def selectRoundTripCitiesWithFare() {
        searchPage.searchFlight()
    }

}
