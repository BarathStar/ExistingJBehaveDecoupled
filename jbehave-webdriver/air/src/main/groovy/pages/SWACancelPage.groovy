package pages

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.ReservationToRetrieve
import domain.AirReservation
import fixture.stubs.DynaStubsIntegration
import pages.elements.ShoppingCart
import pages.mixins.PricingTableVerifications
import pages.CancelAirPage
import pages.CancelAirReservationPage
import pages.CancelAirReservationRetrievedPage
import pages.CancelBoardingPassPage
import pages.HomePage
import pages.Itinerary
import pages.MyAccountPage
import pages.NonRevCancelConfirmationPage
import pages.NonRevLandingPage
import pages.NonRevRetrievedItineraryPage
import pages.NonRevSearchListingPage
import pages.ProductPage
import pages.RapidRewardsPartnersPage
import pages.RepricePage
import pages.SearchFlightsPage
import pages.SelectFlightsPage
import pages.SwaBizPage
import pages.TravelFundsPage
import pages.UnaccompaniedMinorGuardianPage
import pages.ViewReservationPage
import pages.elements.AirPriceContent
import pages.elements.GlobalNavigationFooter
import pages.elements.GlobalNavigationHeader
import pages.elements.PriceTable
import pages.elements.RapidRewardsAccountBar
import pages.elements.StopLogicInfo
import state.CarReservationData
import state.Flow
import state.PassengerData
import state.ScenarioState
import steps.AccountSteps
import steps.AirCancelSteps
import util.BrowserActions
import util.HotelItineraryData
import util.ItineraryData
import util.PricePageData
import util.RRContactInformation
import org.jbehave.web.selenium.WebDriverProvider
import util.SelectFlightsPageData


class SWACancelPage extends BasePage
{

    CancelAirPage cancelAirPage
    CancelAirReservationPage cancelAirReservationPage
    CancelBoardingPassPage cancelBoardingPassPage
    NonRevLandingPage nonRevLandingPage
    NonRevSearchListingPage nonRevSearchListingPage
    NonRevRetrievedItineraryPage nonRevRetrievedItineraryPage
    NonRevCancelConfirmationPage nonRevCancelConfirmationPage
    SwaBizPage swaBizPage
    CancelAirReservationRetrievedPage cancelAirReservationRetrievedPage
    DynaStubsIntegration dynaStubsIntegration
    ScenarioState scenarioState
    PassengerData passengerData
    Data data
    Flow flow
    AccountSteps accountSteps
    RapidRewardsAccountBar rapidRewardsAccountBar
    ShoppingCart shoppingCart
    UnaccompaniedMinorGuardianPage unaccompaniedMinorGuardianPage
    ItineraryData itineraryData
    CarReservationData carItineraryData
    StopLogicInfo stopLogicInfo
    SelectFlightsPageData selectFlightsPageData
    PriceTable priceTable
    AirPriceContent airPriceContent
    HotelItineraryData hotelItineraryData
    PricePageData pricePageData
    Itinerary itinerary
    HomePage homePage
    ViewReservationPage viewReservationPage
    pages.PurchasePage purchasePage
    pages.ConfirmationPage confirmationPage
    pages.PricePage pricePage
    TravelFundsPage travelFundsPage
    BrowserActions browser
    RepricePage repricePage
    SelectFlightsPage selectFlightsPage
    SearchFlightsPage searchFlightsPage
    ProductPage productPage
    GlobalNavigationHeader globalNavHeaderPage
    GlobalNavigationFooter globalNavFooterPage
    MyAccountPage myAccountPage
    RapidRewardsPartnersPage rapidRewardsPartnersPage
    RRContactInformation rrContact
    PricingTableVerifications pricingTableVerifications
    AirCancelSteps airCancelSteps

    public SWACancelPage(WebDriverProvider driverProvider) {
        super(driverProvider,"");

    }
    void confirmAirCancellation()
    {
        cancelAirReservationRetrievedPage.verifyPage()
        if (flow.isRapidRewards) {
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                verifyTier()
                verifyRRacountNumber()
                saveAvailablePoints()
            }
            cancelAirReservationRetrievedPage.verifyRRNumber()
        }
        cancelAirReservationRetrievedPage.with {
            if (passengerData.containsAdultPassenger()) {
                String AdultPnr = scenarioState.getLastAirReservation().getAdultPnr()
                verifyConfirmationNumberInPageTitle(AdultPnr)
                verifyConfirmationNumberOnItineraryTable(AdultPnr)
            }
            verifyFlightItinerary()
            verifyFlightDate()
            if (!DynaStubsIntegration.useDynaStubs()) {
                verifyPassengerName()
                shoppingCart.verifyDepartureAndArrivalTime()
                shoppingCart.verifyFlightNumber()
                unaccompaniedMinorGuardianPage..verifyTravelTime()
                verifyBillingDetails()
                verifyTravelFunds()
                verifyCancelButtonIsPresent()
                verifyDoNotCancelButtonIsPresent()
                if(flow.isRapidRewardsPointsPurchaseOnly) {
                    verifyPointsRefundDetail()
                }
                priceTable.verifyFareType()
                pricingTableVerifications.verifyRoutingType()
            }
            shoppingCart.verifyDate()
            shoppingCart.verifyDepartureAndArrivalCities()
            finishCancelingFlight()
        }
    }


    def cancelFlight(boolean returnFunds, boolean withOutVerify = false) {
        AirReservation airReservation = scenarioState.getLastAirReservation()

        flow.canceledPNR = airReservation.containsSeniorPassenger() ? airReservation.seniorPnr : airReservation.adultPnr

        ReservationToRetrieve reservation = new ReservationToRetrieve().getAdultOrSeniorReservation(airReservation)

        String firstName
        String lastName

        if (flow.isLoggedIn) {
            firstName = flow.rrUser.RRFirstName
            lastName = flow.rrUser.RRLastName
        } else {
            firstName = reservation.getPassengerFirstName()
            lastName = reservation.getPassengerLastName()
        }
        if(withOutVerify)
        {
            airCancelSteps.cancelReservationWithoutVerify(reservation.getRecordLocator(), firstName, lastName)
        }
        else
        {
            cancelReservation(reservation.getRecordLocator(), firstName, lastName, returnFunds)
        }
        airReservation.adultPnrCanceled = airReservation.containsAdultPassenger() ? true : false
        airReservation.seniorPnrCanceled = airReservation.containsSeniorPassenger() ? true : false
    }


    private void cancelReservation(String pnr, String firstName, String lastName, boolean returnFunds) {

        AirReservation airReservation = scenarioState.getLastAirReservation()
        flow.canceledPNR = airReservation.adultPnr
        if (!flow.isSwabiz)
        {
            cancelAirReservationPage.open()
        } else
        {
            swaBizPage.openCancelTravelPage()
        }
        cancelAirReservationPage.submitReservationToCancel(pnr, firstName, lastName)
        cancelAirReservationPage.verifyPage()

        if (cancelBoardingPassPage.isOnCancelBoardingPassPage())
        {
            cancelBoardingPassPage.cancelBoardingPass()
        }
        if(returnFunds)
        {
            cancelAirReservationPage.requestForARefund()
        }

        cancelAirReservationPage.finishCancelingFlight()
    }





}