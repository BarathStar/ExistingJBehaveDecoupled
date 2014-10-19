package pages

import com.swacorp.dotcom.webscenarios.air.Data
import domain.AirReservation
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import pages.elements.GlobalNavigationHeader
import pages.elements.RapidRewardsAccountBar
import pages.mixins.ConfirmationVerifications
import pages.mixins.PricingTableVerifications
import state.Flow
import state.PassengerData
import state.ScenarioState
import util.HotelItineraryData
import util.ItineraryData
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.RRContactInformation
import util.SelectFlightsPageData
import steps.conditional.ToggleHomepage2

class SWARetrievePage extends BasePage

{

    private static final By OUTBOUND_TRAVEL_TIME = By.cssSelector("#airItinerary0 .travelFlightDuration")
    private static final By OUTBOUND_FARE_TYPE = By.cssSelector("#airItinerary0 .fareProductName")
    private static final By OUTBOUND_ROUTING = By.cssSelector("#airItinerary0 .stops")

    TravelFundsPage travelFundsPage
    ViewReservationPage viewReservationPage
    CancelAirReservationPage cancelAirReservationPage
    ConfirmationVerifications confirmationVerifications
    ScenarioState scenarioState
    Data data
    ItineraryData itineraryData
    Itinerary itinerary
    SearchFlightsPage searchFlightsPage

    Flow flow
    GlobalNavigationHeader globalNavigationHeader

    SwaBizPage swaBizPage
    HotelItineraryData hotelItineraryData
    RRContactInformation rrContactInformation
    ConfirmationPage confirmationPage
    RapidRewardsAccountBar rapidRewardsAccountBar
    PassengerData passengerData
    PricingTableVerifications pricingTableVerifications
    ChangeTripPage changeTripPage
    CheckinPage checkinPage
    SelectFlightsPageData selectFlightsPageData

    public SWARetrievePage(WebDriverProvider driverProvider)
    {
        super(driverProvider,"");

    }

    def retrieveItineraryUsingPNR()
    {
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
        //TODO open() method internally calls verifyPage() method. Do we need to call verifyPage() again here?
        searchFlightsPage.verifyPage()
        searchFlightsPage.clickManageReservationLink()
        viewReservationPage.retrieveItineraryByPnr(airReservation.adultPnr, firstName, lastName)

    }

    def SWAretrieveItineraryUsingPNR()
    {
        viewReservationPage.SWAretrieveItineraryByPnr()
    }


    def viewItinerary()
    {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        passengerData.passengers = airReservation.passengers
        viewReservationPage.verifyPage()
        viewReservationPage.verifyItineraryNote()
        viewReservationPage.verifyHideDetailsExpanded()
        viewReservationPage.verifyStationInDetails()
        //confirmationVerifications.verifyAdultPassengerName()
        if(flow.isLoggedIn && (flow.isRapidRewards || flow.isCustomer)) {
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
            if(flow.isRapidRewards)
            {
                rapidRewardsAccountBar.verifyTier()
                rapidRewardsAccountBar.verifyRRacountNumber()
            }
        } else {
            //confirmationVerifications.verifyAdultRRLinkPresent()
        }
        //pricingTableVerifications.verifyAddEarlyBirdCheckInButtonDisplayed()
        if (!itineraryData.pnrSource) {
            viewReservationPage.verifyResendReceipt()
            viewReservationPage.verifyChangeReservationLink()
            viewReservationPage.verifyCancelReservationLink()
        }
        viewReservationPage.verifyPrintAndShare()
        viewReservationPage.verifyDisabilityLinkPresent()
        if(itineraryData.isValidForCheckin.toBoolean()) {
            viewReservationPage.verifyCheckInButtonDisplayed()
        }
        viewReservationPage.verifyConfirmationNumber(airReservation.adultPnr)
        if (!DynaStubsIntegration.useDynaStubs()) {
            viewReservationPage.verifySectionTitle(itineraryData.departureDate)
            //viewReservationPage.verifyTripName(itineraryData.getDefaultTripName())
           // changeTripPage.verifyDate()
            //checkinPage.verifyDepartureAndArrivalTime()
            //verifyTravelTime()
           // checkinPage.verifyFlightNumber()
           // checkinPage.verifyDepartureAndArrivalCities()
           /// verifyFareType()
          // verifyRoutingType()
            if (flow.isRapidRewards) {
                viewReservationPage.verifyRrNumber(rrContactInformation.accountNumber)
            }
            if (flow.isUM) {
                viewReservationPage.with {
                    verifyUnaccompainedMinorIconIsDisplayed()
                    verifyPassengerAssistanceRequested()
                }
            }
        }
        if (itineraryData.hasDisabilities){
            viewReservationPage.verifyAddEditDisabilitiesButtonIsPresent()
            viewReservationPage.verifyAssistanceRequestedText()
        }
        passengerData.passengers = new ArrayList<Passenger>()
    }



    def verifyTravelTime()
    {
        waitForElement(OUTBOUND_TRAVEL_TIME).text.replaceAll("\\s", "").replaceFirst("TravelTime", "").shouldContain selectFlightsPageData.outboundTravelTime.replaceAll("\\s", ""), "Outbound Travel time did not match travel time from the Bug page"
    }

    def verifyFareType() {
        waitForElement(OUTBOUND_FARE_TYPE)?.getText().replaceAll(" ", "").shouldBe itineraryData.departingFlight_fareClass, "Fare type did not mach the selected one"
    }

    def verifyRoutingType() {
        waitForElement(OUTBOUND_ROUTING)?.text.shouldContain selectFlightsPageData.outboundFlyoutRouting, "Routing type did not match the selected one"
    }


    void verifySLoggedInInformation() {
        if (ToggleHomepage2.isOn()) {
            globalNavigationHeader.with {
                verifyRRGreetingAndName()
                verifyUserLoggedIn()
                if (flow.isRapidRewards && flow.userLoggedInRapidRewardsAccountType.equals("A-List")) {
                    verifyTierStatusFlagInHotState(flow.userLoggedInRapidRewardsAccountType)
                }
            }
        } else {
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                if (flow.isRapidRewards) {
                    verifyTier()
                    verifyRRacountNumber()
                }
            }
        }
    }
    //sruthi
        def verifyOopsMessageCheckinIneligible() {
        def oopsMessage = [
                "Sorry, your itinerary is ineligible for checkin online. Please proceed to any Southwest Airlines Ticket Counter to check in for your flight."
        ]
        verifyOopsMessages(oopsMessage)
    }


}