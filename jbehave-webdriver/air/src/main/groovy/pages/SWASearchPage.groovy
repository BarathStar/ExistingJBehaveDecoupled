package pages

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.PromoDiscountData
import com.swacorp.dotcom.webscenarios.air.SwaBizData
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.FlightSearchForm
import pages.elements.AccountBar
import pages.elements.BookATripModal
import pages.elements.FlightSearchForm
import pages.elements.HomePageFlightSearchForm
import pages.elements.RapidRewardsAccountBar
import state.Flow
import state.PassengerData
import steps.AccountSteps
import steps.AirSelectSteps
import steps.conditional.ToggleHomepage2
import util.CustomerInfoData
import state.PassengerData
import util.*
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import static org.junit.Assert.assertTrue
import static util.Locators.ELEMENT_IDS
import util.ItineraryData
import fixture.stubs.DynaStubsIntegration
import com.swacorp.dotcom.webscenarios.air.PromoDiscountData
class SWASearchPage extends BasePage
{

    private final static HOME_PAGE_SUBMIT_BUTTON = "booking_widget_content_row_btn_search"
    private final static TRIP_SEARCH_MODAL_SUBMIT_BUTTON = "tripSearchModalSubmit"
    private final static BS = "BusinessSelect"
    private final static WGA = "WannaGetAway"
    private final static AT = "Anytime"
    private final static NS = "Nonstop"
    private final static WN = "WN"
    private final static FL = "FL"
    ItineraryData itineraryData
    PassengerData passengerData
    PromoDiscountData discountData
    HomePage homepage
    Flow flow


    public SWASearchPage(WebDriverProvider driverProvider) {
        super(driverProvider,"");

    }

    def selectTripType(String trip_type)
    {
        def tType = trip_type.replaceAll('\\s+','').replaceAll('\\-','').toLowerCase()
        switch(tType)
        {
            case "oneway":
                itineraryData.itineraryType = itineraryData.ONE_WAY_ITINERARY
                break
            case "roundtrip":
                itineraryData.itineraryType = itineraryData.ROUND_TRIP_ITINERARY
                break
            case "openjaw":
                itineraryData.itineraryType = itineraryData.OPEN_JAW_ITINERARY
                break
            default:
                throw new RuntimeException("change the tripType it should be something like One Way, Round Trip or Open Jaw")
        }

    }

    def selectOutBoundCarrierType(String outbound_carrier_type) {
        def cType = outbound_carrier_type.replaceAll('\\s+','').replaceAll('\\-','').toLowerCase()
        switch(cType) {
            case "wn":
                itineraryData.departingFlight_carrierCode = WN
                break
            case "fl":
                itineraryData.departingFlight_carrierCode = FL
                break
            default:
                throw new RuntimeException("change the carrierType  it should be something like WN or FL")
        }
    }

    def selectInBoundCarrierType(String inbound_carrier_type) {
        def cType = inbound_carrier_type.replaceAll('\\s+','').replaceAll('\\-','').toLowerCase()
        switch(cType) {
            case "wn":
                itineraryData.arrivingFlight_carrierCode = WN
                break
            case "fl":
                itineraryData.arrivingFlight_carrierCode = FL
                break
            default:
                throw new RuntimeException("change the carrierType it should be something like WN or FL")
        }
    }

    def selectAirtranTrip(String tripType) {
        Map yaml = itineraryData.yamlItineraryData()
        def tType = tripType.replaceAll('\\s+','').replaceAll('\\-','').toLowerCase()
        itineraryData.departureStation = yaml.airtran.departAirport
        itineraryData.arrivalStation = yaml.airtran.arriveAirport
        itineraryData.departingFlight_carrierCode = yaml.airtran.carrier
        itineraryData.isAirTranFlight=true
        switch(tType) {
            case "oneway":
                itineraryData.itineraryType = itineraryData.ONE_WAY_ITINERARY
                break
            case "roundtrip":
                itineraryData.itineraryType = itineraryData.ROUND_TRIP_ITINERARY
                break
            default:
                throw new RuntimeException("change the tripType it should be something like One Way or Round Trip")
        }
    }

    def selectDepartureLocation(String departure_station) {
        itineraryData.departureStation =   departure_station
    }

    def selectArrivalLocation(String arrival_station) {
        itineraryData.arrivalStation = arrival_station
    }

    def selectReturnLocation(String return_station)
    {
        itineraryData.returnStation = return_station
    }

    def selectOutBoundTripType(String outbound_route_type) {
        def tType = outbound_route_type.replaceAll('\\s+','').replaceAll('\\-','').toLowerCase()
        switch(tType) {
            case "nonstop":
                itineraryData.outboundRouting = NS
                break
            case "planechange":
                 itineraryData.outboundConnectingStation = yaml.market.default.outboundConnectingStation
                itineraryData.outboundRouting = "1 stop"
                flow.hasConnectionFlight = true
                break

        case "direct":
                itineraryData.outboundRouting = "1 stop"
            itineraryData.outboundConnectingStation = yaml.market.default.outboundConnectingStation
                break
            default:
                throw new RuntimeException("change the outboundType it should be something like Non Stop or 1 stop")
        }
    }

    def selectInBoundTripType(String inbound_route_type) {
        def tType = inbound_route_type.replaceAll('\\s+','').replaceAll('\\-','').toLowerCase()
        switch(tType) {
            case "nonstop":
                itineraryData.inboundRouting = NS
                break
            case "direct":
                itineraryData.inboundConnectingStation = yaml.market.default.inboundConnectingStation
                itineraryData.inboundRouting = "1 stop"
                break
            case "planechange":
                itineraryData.outboundConnectingStation = yaml.market.default.inboundConnectingStation
                itineraryData.outboundRouting = "1 stop"
                flow.hasConnectionFlight = true
                break
            default:
                throw new RuntimeException("change the inboundType it should be something like Non Stop or 1 Stop")
        }
    }

    def selectOutBoundFareType(String outbound_fare_type) {
        def fType = outbound_fare_type.replaceAll('\\s+','').toLowerCase()
        switch(fType) {
            case "businessselect":
                itineraryData.departingFlight_fareClass = BS
                break
            case "wannagetaway":
                itineraryData.isValidForWGA = Boolean.TRUE
                itineraryData.departingFlight_fareClass = WGA
                break
            case "anytime":
                itineraryData.departingFlight_fareClass = AT
                break
            default:
                throw new RuntimeException("change the fareType it should be something like Business Select , Wana Get Away or 1 Anytime")
        }
    }

    def selectInBoundFareType(String inbound_fare_type) {
        def fType = inbound_fare_type.replaceAll('\\s+','').toLowerCase()
        switch(fType) {
            case "businessselect":
                itineraryData.arrivingFlight_fareClass = BS
                break
            case "wannagetaway":
                itineraryData.isValidForWGA = Boolean.TRUE
                itineraryData.arrivingFlight_fareClass = WGA
                break
            case "anytime":
                itineraryData.arrivingFlight_fareClass = AT
                break
            default:
                throw new RuntimeException("change the fareType it should be something like Business Select , Wana Get Away or 1 Anytime")
        }
    }

    def selectPassengers(int passenger_count,String passenger_type) {
        def pType = passenger_type.replaceAll('\\s+','').toLowerCase()
        if(itineraryData.adultPassengerCount == null)
        itineraryData.adultPassengerCount = 0
        if(itineraryData.seniorPassengerCount == null)
        itineraryData.seniorPassengerCount = 0
        switch(pType) {
            case "adult":
            case "adults":
                itineraryData.adultPassengerCount = itineraryData.adultPassengerCount+passenger_count
                passengerData.addAdultPassenger(passenger_count)
                break
            case "rradult":
            case "rradults":
                itineraryData.adultPassengerCount = itineraryData.adultPassengerCount+passenger_count
                passengerData.addAdultPassengerWithRapidRewards(passenger_count)
                break
            case "senior":
            case "seniors":
                itineraryData.seniorDepartingFlight_fareClass = "Senior"
                itineraryData.seniorPassengerCount = itineraryData.seniorPassengerCount+passenger_count
                passengerData.addSeniorPassengers(passenger_count)
                break
            case "inhibited":
                itineraryData.inhibitedPassenger=true
                itineraryData.adultPassengerCount = passenger_count
                passengerData.addInhibitPassenger()
                break

            case "passengerofsize":
            itineraryData.passengerOfSize=true
            itineraryData.adultPassengerCount = passenger_count*2
                passengerData.addPassengerOfSize()
            break
            case "child":
            case "infant":
                itineraryData.adultPassengerCount = itineraryData.adultPassengerCount+passenger_count
                passengerData.addPassengers([PassengerType.INFANT])
                break
            case "minor":
                itineraryData.adultPassengerCount = itineraryData.adultPassengerCount+passenger_count
                passengerData.addPassengers([PassengerType.MINOR])
                break
            case "youth":
            case "young":
                itineraryData.adultPassengerCount = itineraryData.adultPassengerCount+passenger_count
                passengerData.addPassengers([PassengerType.YT])
                break
            case "disabled":
                itineraryData.hasDisabilities=true
                itineraryData.adultPassengerCount = itineraryData.adultPassengerCount+passenger_count
                passengerData.addAdultPassenger(passenger_count)
                break
            default:
                throw new RuntimeException("change the passengerType or pax_number . Passenger type should be something like Senoir,Senoirs,Adult,Adults,RR Adult,RR Adults, Inhibitted")
        }
    }

    def setFlightEligibleForCheckin(){
        itineraryData.isValidForCheckin = true;
    }

    def searchFlight() {
        //homepage.fillInFlightSearchAndClick(itineraryData, true)
        homepage.fillInFlightSearchInfoAndClick(itineraryData, true)
        flow.hasAir = true;
    }

    def specifiedPromoCode(String promo_code)
    {
        if(promo_code != null)
        {
            new DynaStubsIntegration().prepareDiscount(discountData.getDiscount(promo_code))
        }

    }

    def bookedFlightWithCurrency(String currency)
   {
        def currencyType=currency.replaceAll('\\s+','').replaceAll('\\-','').toLowerCase()
        switch(currencyType)
        {
            case "points":
            case "point":
                homepage.clickPointsRadioButton()
                break
            case "dollars":
            case "dollar":
                homepage.clickDollarsRadioButton()
                break
            case "certificate":
            case "certificates":
                airSelectSteps.selectCertificateRadioButton()
                break
            case "promocode":
            case "promocodes":
                airSelectSteps.selectCertificateRadioButton()
                break
            case "southwestgiftcard":
            case "southwestgiftcards":
            case "giftcards":
            case "giftcard":
                airSelectSteps.selectCertificateRadioButton()
                break
            case "luv voucher":
            case "luv vouchers":
            case "voucher":
            case "vouchers":
            case "luvvouchers":
            case "luvvoucher":
                airSelectSteps.selectCertificateRadioButton()
                break
            case "paypal":
                airSelectSteps.selectCertificateRadioButton()
                break
            case "travel funds":
            case "travelfund":
            case "travelfunds":
            case "travel fund":
                airSelectSteps.selectCertificateRadioButton()
                break
            case "creditcard":
            case "creditcards":
            case "cc":
                customerInfoData.formOfPayment = paymentMethod
                break
            default:
                customerInfoData.formOfPayment = currencyType
        }
    }

    def setAirtranFlight() {
        itineraryData.isAirTranFlight=true
    }
}
