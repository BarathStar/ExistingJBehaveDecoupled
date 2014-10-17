package steps

import com.swacorp.dotcom.webscenarios.air.Data
import fixture.stubs.DynaStubsIntegration
import pages.AirTranSeatSelectPage
import pages.CancelAirReservationPage
import pages.CancelReservationConfirmationPage
import pages.ChangeAirReservationPage
import pages.ConfirmationPage
import pages.DisabilitiesPage
import pages.HomePage
import pages.Itinerary
import pages.LowFareCalendarPage
import pages.MyAccountPage
import pages.NonRevLandingPage
import pages.PayPalPage
import pages.RepricePage
import pages.SearchFlightsPage
import pages.SelectFlightsPage
import pages.SwaBizPage
import pages.elements.BookATripModal
import pages.elements.CreditCardSubForm
import pages.elements.FlightSearchForm
import pages.elements.GlobalNavigationHeader
import pages.elements.HomePageFlightSearchForm
import pages.elements.PassengerInfo
import pages.elements.PriceTable
import pages.elements.RapidRewardsAccountBar
import pages.elements.ShoppingCart
import pages.SWAPurchasePage
import state.Flow
import state.PassengerData
import state.ScenarioState
import steps.AccountSteps
import steps.AirCancelSteps
import steps.AirGuardianPurchaseSteps
import steps.AirPricingSteps
import steps.AirPurchaseSteps
import steps.AirSearchSteps
import steps.AirSelectSteps
import steps.CommonSteps
import steps.CrossBookingSteps
import steps.NavigationSteps
import steps.RapidRewardsSteps
import steps.thenSteps.ConfirmationThenSteps
import util.CustomerInfoData
import util.ItineraryData
import util.PricePageData
import org.jbehave.core.annotations.*
import util.RRContactInformation

class SWAPurchaseSteps
{
    private final static HOME_PAGE_SUBMIT_BUTTON = "booking_widget_content_row_btn_search"
    private static final String INBOUND_AVAILABLE_FLIGHT = "55"

    SearchFlightsPage searchFlightsPage
    SelectFlightsPage selectFlightsPage
    RepricePage repricePage
    pages.PricePage pricePage
    PriceTable priceTable
    pages.PurchasePage purchasePage
    ConfirmationPage confirmationPage
    CancelReservationConfirmationPage cancelReservationConfirmationPage
    ChangeAirReservationPage changeAirReservationPage
    SwaBizPage swaBizPage
    AirTranSeatSelectPage airTranSeatSelectPage
    ItineraryData itineraryData
    Itinerary itinerary
    NavigationSteps navigationSteps
    AirSearchSteps airSearchSteps
    AirSelectSteps airSelectSteps
    AirPurchaseSteps airPurchaseSteps
    AirCancelSteps airCancelSteps
    CommonSteps commonSteps
    FlightSearchForm flightSearchForm
    PassengerData passengerData
    AirPricingSteps airPricingSteps
    CancelAirReservationPage cancelAirReservationPage
    Flow flow
    Data data
    GlobalNavigationHeader globalNavigationHeader
    AirGuardianPurchaseSteps airGuardianPurchaseSteps
    ScenarioState scenarioState
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
    HomePage homePage
    HomePageFlightSearchForm homePageFlightSearchForm
    CrossBookingSteps crossBookingSteps
    LowFareCalendarPage lowFareCalendarPage
    BookATripModal bookATripModal
    MyAccountPage myAccountPage
    RapidRewardsSteps rapidRewardsSteps
    AccountSteps accountSteps
    NonRevLandingPage nonRevLandingPage
    PayPalPage payPal;
    ShoppingCart shoppingCart
    RRContactInformation rrContact
    PricePageData pricePageData
    CustomerInfoData customerInfoData
    RapidRewardsAccountBar rapidRewardsAccountBar
    DisabilitiesPage disabilitiesPage
    CreditCardSubForm creditCardSubForm
    ConfirmationThenSteps confirmationThenSteps
    PayPalPage payPalPage
    PassengerInfo passengerInfo
    SWAPurchasePage purchasePages


    @When("I purchase the ticket")
    def purchaseTicket()
     {
         purchasePages.purchaseTicket()
     }

    @When("I choose the disabilities option for \$PaxId passenger")
    def addDisabilitiesPassengerCount(int pax_id)
    {
        pax_id = pax_id - 1;
        String pax_id_value = String.valueOf(pax_id)
        purchasePages.addDisabilityPassengerCount(pax_id_value)
    }

    @When("I select the \$DisOption with battery count \$BatteryCount")
    def addAllDisabilitiesOptions(String dis_option,String battery_count)
    {
        purchasePages.addDisabledOptions(dis_option,battery_count)

    }

    @When("I select the disabilities option as \$DisOption")
    def addAllDisabilitiesOptions(String dis_option)
    {
        purchasePages.addDisabledOptions(dis_option)


    }

    @When("I submit the disabilities options")
    def submitOptions()
    {
        purchasePages.submitDisabledOptions()

    }



}