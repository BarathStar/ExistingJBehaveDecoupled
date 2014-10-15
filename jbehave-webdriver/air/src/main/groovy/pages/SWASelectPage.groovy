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
import pages.SelectFlightsPage
import pages.elements.AccountBar
import pages.elements.BookATripModal
import pages.*
import pages.elements.HomePageFlightSearchForm
import pages.elements.RapidRewardsAccountBar
import state.Flow
import state.PassengerData
import steps.AccountSteps
import steps.AirSelectSteps
import steps.NavigationSteps
import steps.conditional.ToggleHomepage2
import util.CustomerInfoData
import state.PassengerData
import util.PassengerType
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import fixture.stubs.DynaStubsIntegration
import com.swacorp.dotcom.webscenarios.air.PromoDiscountData

import static org.junit.Assert.assertTrue
import static util.Locators.ELEMENT_IDS
import util.ItineraryData
import org.openqa.selenium.Keys

class SWASelectPage extends BasePage
 {

    private final static HOME_PAGE_SUBMIT_BUTTON = "booking_widget_content_row_btn_search"
    private final static TRIP_SEARCH_MODAL_SUBMIT_BUTTON = "tripSearchModalSubmit"
     private static final By MODIFY_SEARCH_SUBMIT_BUTTON = By.id("modifySearchSubmitButton")
     private final static BS = "BusinessSelect"
    private final static WGA = "WannaGetAway"
    private final static AT = "Anytime"
    private final static NS = "Nonstop"
    private final static WN = "WN"
    private final static FL = "FL"
    FlightSearchForm flightSearchForm
    SelectFlightsPage selectFlightsPage
    CustomerInfoData customerInfoData
    ItineraryData itineraryData
    PassengerData passengerData
     RepricePage repricePage
    Flow flow
    PromoDiscountData discountData
     RapidRewardsAccountBar rapidRewardsAccountBar

    private static final String FLIGHT_NUMBER = "flightNumber"
    private static final String SUBMIT_BUTTON_ID = "submitButton"
    private static final String SELECT_NEW_FLIGHT ="selectNewFlightBtn"
    private WebElement adultPassengerCount = null
    private WebElement seniorPassengerCount = null
    private static final By FARE_TOGGLE = By.className("fareToggle")
    private static final By ADD_ANOTHER_FLIGHT = By.id("add-another-flight")
    private static final By ONE_WAY_RADIO_BUTTON = By.id("oneWay")
    private static final By ROUND_TRIP_RADIO_BUTTON = By.id("roundTrip")
    private static final By RETURN_AIRPORT = By.id("returnAirport_displayed")
    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By RETURN_DATE = By.id("returnDate")
    private static final By POINTS = By.id("points")
    private static final By DOLLARS = By.id("dollars")
    private static final By ADULT_PASSENGER_COUNT = By.id("adultPassengerCount")
    private static final By IMG_POINTS = By.id("imgpoints")
    private static final By IMG_DOLLARS = By.id("imgdollars")
    private static final By SENIOR_PASSENGER_COUNT = By.id("seniorPassengerCount")
    private static final By ORIGIN_AIRPORT_DISPLAYED = By.id("originAirport_displayed")
    private static final By DESTINATION_AIRPORT_DISPLAYED = By.id("destinationAirport_displayed")
    private static final By PROMOTION_CODE = By.id("promocode")
    private static final String ID_ORIGIN_AIRPORT_DISPLAYED = "originAirport_displayed"
    private static final String ID_DESTINATION_AIRPORT_DISPLAYED = "destinationAirport_displayed"
    private static final By RECENT_SEARCH = By.id("air-recent-search")
    private static final String CLEAR_RECENT_SEARCHES = "__clear"


     public SWASelectPage(WebDriverProvider driverProvider) {
         super(driverProvider,"");

     }

    def selectTripType(String trip_type)
    {
        def tType = trip_type.replaceAll('\\s+','').replaceAll('\\-','').toLowerCase()
        switch(tType)
        {
            case "oneway":
                itineraryData.itineraryType = itineraryData.ONE_WAY_ITINERARY
                flightSearchForm.selectItineraryType(itineraryData)
                break
            case "roundtrip":
                itineraryData.itineraryType = itineraryData.ROUND_TRIP_ITINERARY
                flightSearchForm.selectItineraryType(itineraryData)
                break
            case "openjaw":
                itineraryData.itineraryType = itineraryData.OPEN_JAW_ITINERARY
                flightSearchForm.selectItineraryType(itineraryData)
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

     def selectReturnLocation(String return_city)
     {
         itineraryData.returnStation = return_city
         WebElement returnAirportField = flightSearchForm.waitForElement(RETURN_AIRPORT, false)
         if (returnAirportField?.isDisplayed() && return_city == null) {
             returnAirportField.sendKeys(Keys.TAB)
         }
         if(return_city != null){
             flightSearchForm.fillIn(RETURN_AIRPORT, return_city)
             modifySearchButton()  // nithya
             itineraryData.itineraryType == "Open Jaw"
         }
     }


     private void modifySearchButton() { // nithya
         waitForElement(MODIFY_SEARCH_SUBMIT_BUTTON).click()
     }


     def selectDepartureLocation(String departure_city) {
        flightSearchForm.fillInWithAutocomplete(ORIGIN_AIRPORT_DISPLAYED, departure_city, 3)
     }

    def selectArrivalLocation(String arrival_city) {
        flightSearchForm.fillInWithAutocomplete(DESTINATION_AIRPORT_DISPLAYED, arrival_city, 3)
    }

    def selecOutBoundTripType(String outbound_route_type)
    {
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
            case "planechange":
                itineraryData.inboundConnectingStation = yaml.market.default.inboundConnectingStation
                itineraryData.inboundRouting = "1 stop"
                flow.hasConnectionFlight = true
                break
            case "direct":
                itineraryData.inboundRouting = "1 stop"
                itineraryData.outboundConnectingStation = yaml.market.default.inboundConnectingStation
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
                break
            case "rradult":
            case "rradults":
                itineraryData.adultPassengerCount = itineraryData.adultPassengerCount+passenger_count
                flightSearchForm.addAdultPassengersWithRapidRewards(passenger_count)
                break
            case "senior":
            case "seniors":
                itineraryData.seniorDepartingFlight_fareClass = "Senior"
                itineraryData.seniorPassengerCount = passenger_count
                //itineraryData.seniorPassengerCount = itineraryData.seniorPassengerCount+passenger_count
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
                itineraryData.adultPassengerCount = itineraryData.adultPassengerCount+passenger_count
                selectFlightsPage.addChildPassengers(passenger_count)
                break
                case "minor":
                    itineraryData.adultPassengerCount = itineraryData.adultPassengerCount+passenger_count
                    passengerData.addPassengers([PassengerType.MINOR])
            case "youth":
                itineraryData.adultPassengerCount = itineraryData.adultPassengerCount+passenger_count
                selectFlightsPage.addYouthPassengers(passenger_count)
                    break
            default:
                throw new RuntimeException("change the passengerType or pax_number . Passenger type should be something like Senoir,Senoirs,Adult,Adults,RR Adult,RR Adults, Inhibitted")
        }
    }

    def setFlightEligibleForCheckin(){
        itineraryData.isValidForCheckin = true;
    }

    def selectFlight()
    {

        def totalPassengerCount=itineraryData.seniorPassengerCount+itineraryData.adultPassengerCount
        if (totalPassengerCount>8)
        throw new RuntimeException("Total passenger count is : "+totalPassengerCount + " can not be booked , Select maximum 8 passengers")
        if(itineraryData.seniorPassengerCount >0)
            flightSearchForm.fillInSenior(itineraryData.seniorPassengerCount)
       // flightSearchForm.addSeniorPassengers(itineraryData.seniorPassengerCount)

        if(itineraryData.adultPassengerCount >0)
            flightSearchForm.fillInAdult(itineraryData.adultPassengerCount)
        // flightSearchForm.addAdultPassengers(itineraryData.seniorPassengerCount)
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
//            verifySectionHeaders()
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
        acceptNewPriceAndContinue()
    }

     void acceptNewPriceAndContinue() {
         if (repricePage?.isRepricingPage()) {
             if (!DynaStubsIntegration.useDynaStubs()) {
                 repricePage.init()
             }
             repricePage.clickContinue()
         }
     }


    def specifiedPromoCode(String promo_code)
    {
        if(promo_code != null)
        {
            new DynaStubsIntegration().prepareDiscount(discountData.getDiscount(promo_code))
        }

    }

    def bookedFlightWithCurrency(String currency){
        def currencyType=currency.replaceAll('\\s+','').replaceAll('\\-','').toLowerCase()
        switch(currencyType)
        {
            case "points":
            case "point":
                flow.isRapidRewardsPointsPurchaseOnly = true;
                flightSearchForm.chooseFaresInPoints()
                itineraryData.points=true
                break
            case "dollars":
            case "dollar":
                flightSearchForm.chooseFaresInDollars()
                //customerInfoData.formOfPayment=currencyType      //aruna
                break
            case "certificate":
            case "certificates":
                airSelectSteps.selectCertificateRadioButton()
                customerInfoData.formOfPayment = currencyType
                break
            case "promocode":
            case "promocodes":
                itineraryData.promoCode = "PODBUGS"
                itineraryData.isValidForWGA = true
                break
            case "giftcard":
            case "southwestgiftcards":
            case "giftcards":
            case "southwestgiftcard":
                airSelectSteps.selectCertificateRadioButton()
                break
            case "creditcard":
            case "creditcards":
            case "cc":
                customerInfoData.formOfPayment = currencyType
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
            default:
                customerInfoData.formOfPayment = currencyType
        }


    }



    def setAirtranFlight() {
        itineraryData.isAirTranFlight=true
    }


    def addSeniorPassengers(int seniorPassengers) {
        for (int i = 0; i < seniorPassengers; i++) {
            passengerData.addSeniorPassenger()
        }
    }

    def addAdultPassengers(int adultPassengers) {
        for (int i = 0; i < adultPassengers; i++) {
            if(flow.isLoggedIn && i == 0){
                passengerData.addAdultLoggedInPassenger(flow.userLoggedInFirstName, flow.userLoggedInLastName,
                        flow.userLoggedInGender, flow.userLoggedInRapidRewardsNumber,
                        flow.userLoggedInRapidRewardsAccountType)
            }else{
                passengerData.addAdultPassenger()
            }
        }
    }

    protected void addAdultPassengersWithRapidRewards(int adultPassengers) {
        for (int i = 0; i < adultPassengers; i++) {
            if(flow.isLoggedIn && i == 0){
                passengerData.addAdultLoggedInPassenger(flow.userLoggedInFirstName, flow.userLoggedInLastName,
                        flow.userLoggedInGender, flow.userLoggedInRapidRewardsNumber,
                        flow.userLoggedInRapidRewardsAccountType)
            }else{
                passengerData.addAdultPassengerWithRapidRewards()
            }
        }
    }

    private void addYouthPassengers(int youthPassengers) {
        for (int i = 0; i < youthPassengers; i++) {
            passengerData.addYouthPassenger()
        }
    }

    private void addYoungChildPassengers(int youngChildPassengers) {
        for (int i = 0; i < youngChildPassengers; i++) {
            passengerData.addYoungChildPassenger()
        }
    }

    private void addChildPassengers(int childPassengers) {
        for (int i = 0; i < childPassengers; i++) {
            passengerData.addChildPassenger()
        }
    }

}
