package pages

import com.swacorp.dotcom.webscenarios.air.Data
import fixture.stubs.DynaStubsIntegration
import pages.elements.AirPriceContent
import pages.elements.PriceTable
import pages.elements.RapidRewardsAccountBar
import pages.elements.ShoppingCart
import pages.UnaccompaniedMinorGuardianPage
import pages.elements.StopLogicInfo
import state.CarReservationData
import state.Flow
import state.PassengerData
import util.BrowserActions
import util.HotelItineraryData
import util.ItineraryData
import util.PricePageData
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.SelectFlightsPageData

class SWAPricePage extends BasePage
{
    private static final By AIR_ITINERARY_CONTAINER_CLASS = By.className("airItinWrapper")

    private static final By CALENDAR_DAY_OF_WEEK = By.className("calendarDayOfWeek")
    private static final By CLASS_DATE = By.className("classdate")
    private static final By RAPID_REWARDS_NUMBER = By.id("account_rr_number")
    private static final By ACCOUNT_PASSWORD = By.id("account_password")
    private static final By LOGIN_MODAL_BUTTON = By.id("loginModalButton")
    private static final By SAVED_TRIP_TITLE = By.className("saved_trip_title")
    private static final By SUCCESS_MODAL_CLOSE = By.id("successModalClose")
    private static final By SIMPLE_MODAL_CLOSE = By.className("simplemodal-close")
    private static final By MODAL_TITLE = By.id("modalTitle")
    private static final By ITINERARY_TABLE = By.className("itinerary_section_content_table_td")
    private static final By CAR_VENDOR_DROPDOWN = By.id("vendors_rgbmultiselect")
    private static final By VEHICLE_TYPE_DROPDOWN = By.id("category")
    private static final By CAR_CROSS_SELL_WIDGET = By.id("price_car_cross_sell")
    private static final By CAR_CROSS_SELL_SUBMIT = By.id("car-cross-sell-submit-button")
    private static final By CAR_CROSS_SELL_AD = By.id("car_cross_sell_ad")
    private static final By HOTEL_CROSS_SELL_SEARCH_BUTTON = By.id("submitButton")
    private static final By CAR_CROSS_SELL_DYNAMIC_HERTZ = By.cssSelector(".pricing_search_result_image .HERTZ")
    private static final By CHASE_INSTANT_CREDIT_BANNER = By.id("chase_ad")
    private static final By CHASE_INSTANT_CREDIT_BANNER_CLASS_NAME = By.className("math-calc-offer")

    private static final String FLIGHT_NUMBER_CELL_CLASS = 'flightNumberCell'
    private static final String FLIGHT_NUMBER_CLASS = 'flightNumber'
    private static final String SEGMENT_TIME_CLASS = 'segmentTime'
    private static final String SEGMENT_TIME_AMPM_CLASS = 'segmentTimeAMPM'
    private static final String DEPARTURE_LONG_DATE_CLASS = 'departureLongDate'
    private static final String HOTEL_ID_HREF = "hotelIdentifier="
    private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_ON = By.cssSelector("#airItinerarydepart .airItineraryFlightRouting .routingDetailsStops.routingDetailsSeparator")
    private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_OFF = By.cssSelector("#airItinerarydepart .withDottedBorder")
    private static final By SWABIZ_COMPANY_NAME = By.className("FD_name")
    private static final By PRICE_SUBMIT_BUTTON = By.id('priceSubmitButton')
    private static final By COMPANION_PASS = By.xpath("//ul[@id='companion_pass']")
    private static final By TRIP_TOTAL = By.className("total_amount")
    private static final By TOTAL_CART_PRICE = By.id("totalCartPrice")
    private static final By BLUE_INFORMATIONAL_ALERT_ICON = By.className("swa-alert--icon")
    private static final By BLUE_INFORMATIONAL_ALERT_MESSAGE = By.className("swa-alert--message")
    private static final By HEADER_TOTAL_AMOUNT = By.className("carhotel_price_header_amount")
    private static final By AIR_TOTAL = By.className("price_table_subtotal_amount_container")

    private static final By AIRTRAN_LOGO_OB = By.cssSelector("tr.tableRowOdd td.flightLogo img[title='Operated by AirTran']")
    private static final By AIRTRAN_LOGO_IB = By.cssSelector("tr.tableRowEven td.flightLogo img[title='Operated by AirTran']")
    private static final By SOUTHWEST_LOGO_OB = By.cssSelector("tr.tableRowOdd td.flightLogo img[title='Operated by Southwest']")
    private static final By SOUTHWEST_LOGO_IB = By.cssSelector("tr.tableRowEven td.flightLogo img[title='Operated by Southwest']")
    private static final By OUTBOUND_TRAVEL_TIME = By.cssSelector("#airItinerary0 .travelFlightDuration")
    private static final By OUTBOUND_ROUTING = By.cssSelector("#airItinerary0 .stops")


    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    ItineraryData itineraryData
    CarReservationData carItineraryData
    Data data
    Flow flow
    StopLogicInfo stopLogicInfo
    SelectFlightsPageData selectFlightsPageData
    ShoppingCart shoppingCart
    PriceTable priceTable
    AirPriceContent airPriceContent
    HotelItineraryData hotelItineraryData
    PricePageData pricePageData
    Itinerary itinerary
    PassengerData passengerData
    PricePage pricePage
    BrowserActions browser
    RepricePage repricePage
    SelectFlightsPage selectFlightsPage
    SearchFlightsPage searchFlightsPage
    RapidRewardsAccountBar rapidRewardsAccountBar
    UnaccompaniedMinorGuardianPage unaccompaniedMinorGuardianPage


    public SWAPricePage(WebDriverProvider driverProvider)
    {
        super(driverProvider,"");

    }

    /**
     * Verifies all common assertions in the Price page and proceed to purchase page if there are no Oops messages.
     *
     */
    def verifyPriceDetails()
    {

        acceptNewPriceAndContinue()
        if (!DynaStubsIntegration.useDynaStubs()) {
            if (itineraryData.points&&flow.isCustomer) {
                pricePage.clickContinue()
                pricePage.verifyOopsPointsBookingNonRRUser()
            }
           else
            {
                if (flow.isRapidRewards || flow.isCustomer) {

                    rapidRewardsAccountBar.with {
                        verifyRRGreeting()
                        verifyLogOutLink()
                        verifyRRName()
                        verifyMyAccountLink()
                        if (flow.isRapidRewards) {
                            verifyRRacountNumber()
                            verifyTier()
                        }
                    }
                }
                pricePage.with
                        {
                            verifyBasicPage()
                            //verifyPageHeader()
                            verifyAirTotalAndWidgetHeather()
                            shoppingCart.verifyDepartureAndArrivalTime()
                            shoppingCart.verifyFlightNumber()
                            shoppingCart.verifyDepartureAndArrivalCities()   //mangalyan comment
                            //unaccompaniedMinorGuardianPage.
                            //verifyTravelTime()             m-comment
                            shoppingCart.verifyFareType()
                            //unaccompaniedMinorGuardianPage.
                            verifyRoutingType()
                            shoppingCart.verifyDate()
                            verifyAirTotalIsEqualToTripTotal()
                            verifyPriceMatchInPriceTableAndShoppingCart();
                            verifyAirTotalMatchInPriceTableAndShoppingCart()
                        }

                priceTable.with
                        {
                            verifyPassengerType()
                            verifyTripTypes()
                            verifyRouting()
                            verifyFareType()
                            verifyNumberOfPassengers()
                            verifySubTotal()
                            verifyPriceFareBreakDown()
                            verifyRedeemableMemberPoints()

                            if (passengerData.containsSeniorAndAdultPassengers()) {
                                verifyMixedPaxSubTotalFromSelectFlightPage()
                            }
                            verifyAirTotalIsPresent()
                            verifyAdultSeniorCount()
                            verifyOutboundAndInboundTotalAmounts()
                            verifyAirTotalAndFareBreakDownTotal()
                            verifyPageAndShoppingCartTotal()
                            verifyTotalOnPage()
                            verifyBaseFarePlusTax()
                            verifyTaxesAndBaseFareInFareBreakDown()
                            verifyTotalPlusPassengerCount()
                            verifyTotalOnPageWithTotalInFareBreakDown()
                        }

                shoppingCart.with
                        {
                            airInShoppingCartIsExpanded()
                            modifyAndRemoveLinks()
                            verifyDate()
                            verifyFlightNumber()
                            verifyDepartureAndArrivalTime()
                            verifyOutboundAndInboundTotals()
                            verifyTotalCostBreakdown()
                            verifyTripTotal()
                            verifySaveAndCheckoutButton()
                            verifySaveTripButton()
                            verifyDepartureAndArrivalCities()
                            verifyGovtTaxesFeesLinkIsPresent()
                            verifyFareType()
                        }
                pricePage.clickContinue()
            }

        }
    }

    void acceptNewPriceAndContinue()
    {
        if (repricePage?.isRepricingPage()) {
            if (!DynaStubsIntegration.useDynaStubs()) {
                repricePage.init()
            }
            repricePage.clickContinue()
        }
    }

    def verifyTravelTime()
    {
        waitForElement(OUTBOUND_TRAVEL_TIME).text.replaceAll("\\s", "").replaceFirst("TravelTime", "").shouldContain selectFlightsPageData.outboundTravelTime.replaceAll("\\s", ""), "Outbound Travel time did not match travel time from the Bug page"
    }

    def verifyRoutingType() {
        waitForElement(OUTBOUND_ROUTING)?.text.shouldContain selectFlightsPageData.outboundFlyoutRouting, "Routing type did not match the selected one"
    }


}