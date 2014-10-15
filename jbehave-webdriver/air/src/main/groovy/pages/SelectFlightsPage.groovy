package pages

import com.github.tanob.groobe.GrooBe
import com.swacorp.dotcom.webscenarios.air.SWARemoteWebDriver
import fixture.ding.DingSession
import fixture.stubs.DynaStubsIntegration
import fixtures.air.ReservationSpecification
import fixtures.air.enums.DirectionType
import org.apache.commons.lang.StringUtils
import org.jbehave.web.selenium.WebDriverProvider
import org.joda.time.LocalDate
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.Select
import pages.elements.BugRoutingFlyout
import state.Flow
import state.PassengerData
import state.ScenarioState
import util.BookingCode
import util.FareClass
import util.ItineraryData
import util.PageName
import util.SelectFlightsPageData
import util.Station

import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

import static com.google.common.collect.Lists.newArrayList
import static fixtures.air.enums.DirectionType.INBOUND
import static fixtures.air.enums.DirectionType.OUTBOUND
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.notNullValue
import static util.Locators.BREADCRUMB_IDS
import java.text.DecimalFormat
import org.openqa.selenium.interactions.Actions

public class SelectFlightsPage extends BasePage {

    private static final By CALENDAR_CAROUSEL_DATES = By.className("carouselBody")
    private static final By CALENDAR_CAROUSEL_DATES_DISABLE = By.className("carouselDisabled")
    private static final By CALENDAR_CAROUSEL_DATE_ELIGIBLE = By.className("carouselTodaySodaIneligible")
    private static final By MODIFY_DATES_FORM = By.id("modifyDatesForm")
    private static final By SENIOR_FLIGHT_NUMBER = By.cssSelector(".swa_text_flightNumber .bugLinkText")
    private static final By SENIOR_FLIGHT_TIMES = By.cssSelector(".bugText .time")
    private static final By SENIOR_TRAVEL_TIME = By.cssSelector(".duration")
    private static final By SENIOR_CAROUSEL_DEPART_DATE = By.id("carouselTodayDepart")
    private static final By ROUTING = By.className("bugLinkRouting")
    private static final By FLIGHT_TABLE_ROW = By.className("bugTableRow")
    private static final By OUTBOUND_FLYOUT = By.id("routingHoverTable")
    private static final By UPGRADE_BUSINESS_SELECT_TITLE = By.cssSelector(".wcm_air_bs_upsell_subtitle strong")
    private static final By CALENDAR_CAROUSEL_DEPART_DATE_HIGHLIGHTED= By.cssSelector("#carouselTodayDepart.carouselTodaySodaIneligible")
    private static final By CALENDAR_CAROUSEL_RETURN_DATE_HIGHLIGHTED= By.cssSelector("#carouselTodayReturn.carouselTodaySodaIneligible")
    private static final By DISCOUNTED_PROMOTIONAL_MESSAGE = By.id("notifications")
    private static final By PROMOCODE = By.id("promoCode")
    private final String EXPIRATION_DATE_FORMAT = ~/^.* \d{2}\/\d{2}\/\d{4}.$/
    private final Pattern TIME_PATTERN = Pattern.compile("\\d{1,2}:\\d{2}\\s[AP]M");
    public static final String MODIFIED_SUBMIT_BUTTON_ID = "modifySearchSubmitButton"
    public static final int DEPART_TIME = 8
    private static final String ITINERARY_WITH_CHANGE_PLANES_XPATH = "//tr[contains(., 'Change Planes')]"
    private static final String ITINERARY_WITHOUT_CHANGE_PLANES_XPATH = "//tr[contains(., 'Nonstop') or contains(., 'No Plane Change')]"
    private static final By UPSELL_MODAL = By.id("business_select_upgrade_modal")
    private static final By UPSELL_MODAL_SUBTITLE = By.className("wcm_air_bs_upsell_subtitle")
    private static final String UPSELL_MODAL_SUBTITLE_TEXT = "BUSINESS SELECT"
    private static final By STANDARD_AWARD_HEADER = By.className("standardAwardHeader")
    private static final By FREEDOM_AWARD_HEADER = By.className("freedomAwardHeader")
    private static final By PROMO_CERTIFICATE_HEADER = By.className("promoCertificateHeader")
    private static final By TRANSITIONAL_HEADER = By.className("transitionalHeaderText")
    private static final By BUSINESS_SELECT_HEADER = By.className("businessSelectHeader")
    private static final By WANNA_GET_AWAY_HEADER = By.className("wannaGetAwayHeader")
    private static final By ANY_TIME_HEADER = By.className("anytimeHeader")
    private static final By DING_HEADER = By.className("dingHeader")
    private static final By SENIOR_MESSAGE = By.className("senior_message")
    private static final By TOGGLE_FOR_FARE_IN_POINTS = By.xpath("//*[@id='baseButtonPoints']/input")
    private static final By TOGGLE_FOR_INBOUD_FARE_IN_DOLLARS = By.xpath("//div[@id='inboundFilterStopsAndPromoContainer']/div/span/span[@class='baseButton dollars']/input[@class='fareToggleRadio']")
    private static final By TOGGLE_FOR_FARE_IN_DOLLARS = By.xpath("//div[@id='outboundFilterStopsAndPromoContainer']/div/span/span[@class='baseButton dollars']/input[@class='fareToggleRadio']")
    private static final By TOGGLE_FOR_FARE_IN_CERTIFICATE = By.xpath("//div[@id='outboundFilterStopsAndPromoContainer']//span[@class='baseButton certificate']/a")
    private static final By FARE_CERTIFICATE_RADIO_BUTTON = By.cssSelector("#outboundFilterStopsAndPromoContainer span.baseButton.certificate input")
    private static final By FARE_CERTIFICATE_OPTION = By.xpath("//div[@id='outboundFilterStopsAndPromoContainer']//span[@class='baseButton certificate']//a[@class='radioChecked']")
    private static final By FARE_POINTS_OPTION = By.xpath("//*[@id='baseButtonPoints']/input")
    private static final By MODIFY_SEARCH_TOGGLE = By.id("modifySearchTitleText")
    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By RETURN_DATE = By.id("returnDate")
    private static final By ONE_WAY_RADIO_BUTTON = By.id("one-way")
    private static final By ROUND_TRIP_RADIO_BUTTON = By.id("round-trip")
    private static final By FARES_OUTBOUND = By.id("faresOutbound")
    private static final By FARES_RETURN = By.id("faresReturn")
    private static final By OUTBOUND_UPSELL_DRAWER = By.id("OutboundUpsellDrawerDiv")
    private static final By ALTERNATE_AIRPORT_MESSAGE_CONTAINER = By.className("alternate-airport-msg-container")
    private static final By SWA_BUTTONS_SUBMIT_BUTTON = By.className("swa_buttons_submitButton")
    private static final By HEADER_TEXT = By.cssSelector("a.headerText")
    private static final By PRICE_ITINERARY_SUBMIT = By.id("priceItinerarySubmit")
    private static final By MODIFY_SEARCH_SUBMIT_BUTTON = By.id("modifySearchSubmitButton")
    private static final By SEARCH_RESULTS = By.id("searchResults")
    private static final By OUTBOUND_RESULTS = By.id("outbound_results")
    private static final By INBOUND_RESULTS = By.id("inbound_results")
    private static final By HEADERS = By.cssSelector("th.searchResultsHoverHeader")
    private static final By SW_CONTAINER = By.id("sw_content")
    private static final By HIGHLIGHT_COLUMN = By.className("businessSelectHeaderBestValue")
    private static final By BEST_VALUE_STAR_BUTTON = By.className("businessSelectBestValueIconImage")
    private static final By BEST_VALUE_MSG_BOX = By.id("bestValueMessageDepart")
    private static final By FLIGHT_NUMBER = By.className("flight_column")
    private static final By BOOK_TOGETHER_BUTTON = By.id("ezrez_upsell_container")
    private def bugText = By.cssSelector('div.bugText')
    private def bugLinkText = By.cssSelector('a.bugLinkText')
    private codeShareAirline = By.cssSelector('div.codeShareAirline')
    private static final By CHANGE_PLANES_ROWS = By.cssSelector(".swa_td_flightNumber .bugLinkText")
    private static final By PRE_CR1_CHANGE_PLANES_ROWS = By.xpath("//tr[contains(@class, 'bugTableRow')]//a[@class='bugLinkText' and contains(text(), '/')]")
    private static final By DIRECT_PLANES_ROWS = By.xpath("//tr[contains(@class, 'nonstop')]//a[@class='bugLinkText']")
    private static final By BLACKED_OUT_ROWS = By.xpath("//td[contains(@class, 'blackedOutCell')]");
    private static final By SINGLE_FLYOUT_TITLE = By.cssSelector("#routingHoverTable div.single div.flightDetails h5")
    private static final By CONNECTING_FLYOUT_TITLE = By.cssSelector("div#routingHoverTable div.double_multi div.flightDetails h5")
    private static final By ON_TIME_PERFORMANCE_TITLE = By.cssSelector("div#routingHoverTable div.single div.flightInformation h6")
    private static final By OPERATED_BY_AIRTRAN = By.xpath("//div[@id='routingHoverTable']//p[@id='airTran_message']")
    private static final By SINGLE_FLYOUT_FLY_NUMBER = By.cssSelector("div#routingHoverTable div.single div.flightInformation table.flightInformationDetailsContainer tbody tr td strong")
    private static final By AIRCRAFT_INFORMATION_WCM_FRAGMENT = By.cssSelector("div#routingHoverTable div.single div.flightInformation div.aircraft_information")
    private static final By CONNECTING_FLYOUT_FLY_NUMBER = By.cssSelector("#routingHoverTable div.double_multi div.flightInformation table.flightInformationDetailsContainer tbody tr td.tab_flight_title strong")
    private static final By BEST_VALUE_FLYBY_TEXT = By.id("is_flyby")
    private static final By FLIGHT_PLUS_HOTEL_BUTTON = By.id("ezRezUpsellLink")
    private static Map FARE_TYPE_DESCRIPTIONS = ["Business Select": "Superior Benefits", "Anytime": "Great Flexibility",
        "Senior": "Dedicated Pricing", "Wanna Get Away": "Excellent Value", "Ding": "Exclusive Offers"]

    private static Map FARE_TYPE_POINTS_DESCRIPTIONS = ["Business Select": "Maximum Rewards Points", "Anytime": "More Rewards Points",
        "Senior": "Base Rewards Points", "Wanna Get Away": "Base Rewards Points", "Ding": "Base Rewards Points"]
    private static final By STEP_TEXT = By.className("step_text")
    private static final By OUTBOUND_UPSELL_ON_MODAL = By.id("upgrade_bs_flights_outbound")
    private static final By INBOUND_UPSELL_ON_MODAL = By.id("upgrade_bs_flights_inbound")
    private static final By GLOBAL_NAVIGATION_RAPID_REWARDS_HEADER = By.id("global_account_bar_section_rapid_rewards_header")
    private static final By AVAILABLE_RETURN_CAROUSEL_DATES = By.xpath("//div[@id='newReturnDateCarousel']//a/div[@class='carouselDateContainer']/div[@class='carouselBody']");
    private static final By FREEDOM_AWARDS_CHANGE = By.className("small")
    private static final By FARE_TOGGLE = By.className("fareToggle")
    private static final By FIRST_BOUND_FARES_AVAILABLE = By.cssSelector("#faresOutbound .searchResultsHoverHeader.transitionalHeader")
    private static final By SECOND_BOUND_FARES_AVAILABLE = By.cssSelector("#faresReturn .searchResultsHoverHeader.transitionalHeader")
    private static final String FREEDOM_AWARDS_CHANGE_MSG = "Freedom Awards cannot be combined with tickets purchased with dollars. See the blackout dates as listed in the Terms and Conditions."
    private static final String STANDARD_FREEDOM_AWARDS_CHANGE_MSG = "Standard Awards and Freedom Awards cannot be combined with tickets purchased with dollars. See the blackout dates as listed in the Terms and Conditions"
    private static final By PRODUCT_PRICE = By.className("product_price");
    private static final By SEARCH_FIRST_OUTBOUND_ROW = By.id("outbound_flightRow_1")
    private static final By SEARCH_A_OUTBOUND_ROW_CONTAINER = By.id("Out1AContainer")
    private static final By ACS_DRAWER = By.id("OutboundUpsellArrowNew")
    private static final By STOP_LOGIC_INFO_STOPS = By.cssSelector("#routingHoverTable div.additionalStop")
    private static final By STOP_LOGIC_INFO_STOPS_CITIES = By.cssSelector("#routingHoverTable div.additionalStopList")
    private static final By CHANGE_PLANE_CITIES_INFO = By.cssSelector("#routingHoverTable tr.second td.flightOrigin.departDetails")
    private static final By NO_APPLY_UPSELL_MODAL = By.cssSelector("span.swa_feature_air_options_upgrade_unavailable")
    private static final By OUT_BOUND_ROW = By.cssSelector("tr.swa_feature_air_options_outboundRow")
    private static final By IN_BOUND_ROW = By.cssSelector("tr.swa_feature_air_options_inboundRow")
    private static final String NO_APPLY_UPSELL_MODAL_TEXT = "n/a"
    private static final By ORIGIN_AIRPORT = By.id("originAirport")
    private static final By DESTINATION_AIRPORT = By.id("destinationAirport")
    private static final By PROMO_CODE = By.id("promoCode")
    private static final By OOPS_CLASS = By.className("oopsError_wrapper")
    private static final By OUTBOUND_HEADER_WANNAGETAWAY_PRICE = By.xpath("//table[@id='faresOutbound']//th[@class='searchResultsHoverHeader wannaGetAwayHeader']/div")
    private static final By INBOUND_HEADER_WANNAGETAWAY_PRICE = By.xpath("//table[@id='faresReturn']//th[@class='searchResultsHoverHeader wannaGetAwayHeader']/div")
    private static final By OUTBOUND_WANNAGETWAY_FARE_PRICES = By.xpath("//table[@id='faresOutbound']//td[@class='price_column'][2]//div[@class='productPricing']/div[@class='product_info']/label[@class='product_price']")
    private static final By INBOUND_WANNAGETWAY_FARE_PRICES = By.xpath("//table[@id='faresReturn']//td[@class='price_column'][2]//div[@class='productPricing']/div[@class='product_info']/label[@class='product_price']")
    private static final By EARN_POINTS = By.xpath("//div[@id=\"OutboundUpsellDrawerDiv\"]//div[@class=\"wcm_upsell_drawer_fare_points\"]/span")
    private static final By DEPARTURE_STATION = By.id("originAirport_displayed")
    private static final By ARRIVAL_STATION = By.id("destinationAirport_displayed")
    private static final By RETURN_STATION = By.id("returnAirport_displayed")
    private static final By ADULT_PASSENGER_COUNT = By.id("adultPassengerCount")
    private static final By SENIOR_PASSENGER_COUNT = By.id("seniorPassengerCount")
    private static final By OUTBOUND_HEADER_BUSINESSSELECT_PRICE = By.xpath("//table[@id='faresOutbound']//th[@class='searchResultsHoverHeader businessSelectHeader']/div")
    private static final By OUTBOUND_HEADER_BUSINESS_SELECT = By.cssSelector('#faresOutbound .businessSelectHeader .headerText')
    private static final By OUTBOUND_HEADER_ANYTIME = By.cssSelector('#faresOutbound .anytimeHeader .headerText')
    private static final By OUTBOUND_HEADER_WANNA_GET_AWAY = By.cssSelector('#faresOutbound .wannaGetAwayHeader .headerText ')
    private static final By INBOUND_HEADER_BUSINESS_SELECT = By.cssSelector('#faresReturn .businessSelectHeader .headerText')
    private static final By INBOUND_HEADER_ANYTIME = By.cssSelector('#faresReturn .anytimeHeader .headerText')
    private static final By INBOUND_HEADER_WANNA_GET_AWAY = By.cssSelector('#faresReturn .wannaGetAwayHeader .headerText ')
    private static final By OUTBOUND_HEADER_SENIOR = By.cssSelector('#faresOutbound .seniorHeader .headerText ')
    private static final By STRIKE_THROUGH_REDEMPTION_POINTS = By.cssSelector(".original-point")
    private static final By BLUE_INFORMATIONAL_ALERT_MESSAGE = By.cssSelector(".swa-alert--icon")
    private static final By ADULT_PASSENGER_OPTIONS = By.cssSelector("#adultPassengerCount option")
    private static final By PREF_FLIGHT_INFO = By.id("js-foPrefFlightInfo")

    private static final int ANYTIME_BS_UPGRADE_RANGE = 40
    private static final int WGA_BS_UPGRADE_RANGE = 75
    private static final int FLIGHT_PRICE_ARRAY_LOCATION = 3

    Flow flow
    SelectFlightsPageData selectFlightsPageData
    ItineraryData itineraryData
    ScenarioState scenarioState
    PassengerData passengerData
    PageName pageName
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    private boolean isFareCertificateOptionSelected() {
        waitForElement(FARE_CERTIFICATE_RADIO_BUTTON).getProperties()["selected"] != null
    }

    public SelectFlightsPage(WebDriverProvider driverProvider) {
        super(driverProvider,"");
        GrooBe.activate()
    }

    def verifyBasicPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            sleep(30000)
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["SelectFlightsPage"])
        pageVerificationErrorWrapper(PRICE_ITINERARY_SUBMIT, PageName.SELECT_FLIGHTS)
    }

    def open() {
        try {
            super.open()
            checkSomethingServed()
        } catch (UnhandledAlertException uae) {
            try {
                Alert alert = webDriver().switchTo().alert()
                alert.dismiss()
            } catch (NoAlertPresentException) {}
        }
    }

    String getRelativePath() {
        return "/flight/select-flight.html"
    }

    def openDingSelectFlightPage() {
        DingSession dingSession = new DingSession(domain, itineraryData.departureStation, itineraryData.returnStation);

        //TODO Mike Rylander- add support for OneWay
        String return_arrive_airport = "";
        if  (itineraryData.ROUND_TRIP_ITINERARY.equalsIgnoreCase(itineraryData.itineraryType)){
            return_arrive_airport = "RoundTrip";
        }

        String dingURL = domain + "/flight/classic-select-flight.html?"
        dingURL += "installId=" + dingSession.installID
        dingURL += "&" + "cksum=" + dingSession.checksum
        dingURL += "&" + "headlineId=" + dingSession.headlineID
        dingURL += "&" + "buildItinerary=1"
        dingURL += "&" + "resolution=1"
        dingURL += "&" + "linkEntry=true"
        dingURL += "&" + "s0m3valu3=" + itineraryData.departureStation
        dingURL += "&" + "out_arr_airport=" + itineraryData.arrivalStation
        dingURL += "&" + "DING_OFFER=1"
        dingURL += "&" + "CalendarCurrentDate=" + new SimpleDateFormat("MM/dd/yyyy").format(new Date())
        dingURL += "&" + "CalendarFirstBookableDate=" + new SimpleDateFormat("MM/dd/yyyy").format(new LocalDate().minusMonths(1).toDateMidnight().toDate())
        dingURL += "&" + "CalendarLastBookableDate=" + new SimpleDateFormat("MM/dd/yyyy").format(new LocalDate().plusMonths(3).toDateMidnight().toDate())
        dingURL += "&" + "return_arrive_airport=" + return_arrive_airport
        dingURL += "&" + "PAX_COUNT=1"
        dingURL += "&" + "outbound_depart_month=" + new SimpleDateFormat("MMMMM").format(itineraryData.departureDate)
        dingURL += "&" + "outbound_depart_day=" + new SimpleDateFormat("d").format(itineraryData.departureDate)
        dingURL += "&" + "outbound_depart_time=" + new SimpleDateFormat("H").format(itineraryData.departureDate)
        dingURL += "&" + "return_depart_month=" + new SimpleDateFormat("MMMMM").format(itineraryData.returnDate)
        dingURL += "&" + "return_depart_day=" + new SimpleDateFormat("d").format(itineraryData.returnDate)
        dingURL += "&" + "return_depart_time=" + new SimpleDateFormat("H").format(itineraryData.returnDate)

        println("ding url: " + dingURL);


        get(dingURL)

        verifyPage()
    }

    def openDingSelectFlightPage(ReservationSpecification reservationSpecification) {
        String origin = reservationSpecification.outbound.origin
        String destination = reservationSpecification.outbound.destination
        Date departureDate = reservationSpecification.outbound.departureDate.toDateMidnight().toDate()
        Date returnDate = reservationSpecification.inbound.departureDate.toDateMidnight().toDate()

        DingSession dingSession = new DingSession(domain, origin, destination);

        //TODO Mike Rylander- add support for OneWay
        String return_arrive_airport = "";
        if  (reservationSpecification.roundTrip) {
            return_arrive_airport = "RoundTrip";
        }

        String dingURL = domain + "/flight/classic-select-flight.html?"
        dingURL += "installId=" + dingSession.installID
        dingURL += "&" + "cksum=" + dingSession.checksum
        dingURL += "&" + "headlineId=" + dingSession.headlineID
        dingURL += "&" + "buildItinerary=1"
        dingURL += "&" + "resolution=1"
        dingURL += "&" + "linkEntry=true"
        dingURL += "&" + "s0m3valu3=" + origin
        dingURL += "&" + "out_arr_airport=" + destination
        dingURL += "&" + "DING_OFFER=1"
        dingURL += "&" + "CalendarCurrentDate=" + new SimpleDateFormat("MM/dd/yyyy").format(new Date())
        dingURL += "&" + "CalendarFirstBookableDate=" + new SimpleDateFormat("MM/dd/yyyy").format(new LocalDate().minusMonths(1).toDateMidnight().toDate())
        dingURL += "&" + "CalendarLastBookableDate=" + new SimpleDateFormat("MM/dd/yyyy").format(new LocalDate().plusMonths(3).toDateMidnight().toDate())
        dingURL += "&" + "return_arrive_airport=" + return_arrive_airport
        dingURL += "&" + "PAX_COUNT=1"
        dingURL += "&" + "outbound_depart_month=" + new SimpleDateFormat("MMMMM").format(departureDate)
        dingURL += "&" + "outbound_depart_day=" + new SimpleDateFormat("d").format(departureDate)
        dingURL += "&" + "outbound_depart_time=" + new SimpleDateFormat("H").format(departureDate)
        dingURL += "&" + "return_depart_month=" + new SimpleDateFormat("MMMMM").format(returnDate)
        dingURL += "&" + "return_depart_day=" + new SimpleDateFormat("d").format(returnDate)
        dingURL += "&" + "return_depart_time=" + new SimpleDateFormat("H").format(returnDate)

        println("ding url: " + dingURL);


        get(dingURL)

        verifyPage()
    }

    def verifyOutboundFlightsPresent() {
        verifyElementPresent("Outbound Flights", By.xpath("//td//input[@name='outboundTrip']"))
    }

    def verifyInboundFlightsPresent() {
        verifyElementPresent("Inbound Flights", By.xpath("//td//input[@name='inboundTrip']"))
    }

    def verifyUnavailableToRedeemPromotion() {
        return isElementPresent(BLACKED_OUT_ROWS)
    }

    def verifyFlightsPresent() {
        verifyOutboundFlightsPresent()
        if (itineraryData.isRoundTrip()) {
            verifyInboundFlightsPresent()
        }
    }

    def chooseDates(def daysAhead) {
        List tabs = findElements(By.xpath("//li[@class='carouselEnabledSodaIneligible']"))
        if (tabs > 0) {
            tabs.get(daysAhead).click()
        }
    }

    private SelectFlightRow findValidFlight(String direction, String fareClass, Boolean isSeniorFare = false) {
        Collection flightRowsByDirection = findFlightRowsByDirection(direction)
        if (!DynaStubsIntegration.useDynaStubs())
        {
            Collections.shuffle(flightRowsByDirection)
        }

        String fareClassCode
        String routing
        String[] stopsCitiesCodes
        String stopChangePlaneCityCodes

        fareClassCode = FareClass.from(fareClass).fareClassCode()

        if (direction == "outbound") {
            routing = itineraryData.outboundRouting
            stopsCitiesCodes = itineraryData.getOutboundStopCitiesCodes()
            if (itineraryData.outboundConnectingStation?.equalsIgnoreCase("any")) {
                stopChangePlaneCityCodes = ""
            } else {
                stopChangePlaneCityCodes = itineraryData.outboundConnectingStation
            }
        } else {
            routing = itineraryData.inboundRouting
            stopsCitiesCodes = itineraryData.getInboundStopCitiesCodes()
            if (itineraryData.inboundConnectingStation?.equalsIgnoreCase("any")) {
                stopChangePlaneCityCodes = ""
            } else {
                stopChangePlaneCityCodes = itineraryData.inboundConnectingStation
            }
        }

        List<String> airlineCodes = returnAirlineCodesForFlight(direction)
        String[] stopCitiesNames = returnStopsCitiesNames(stopsCitiesCodes)
        WebElement selectedRowElement = flightRowsByDirection.find { flightRowElement ->
            SelectFlightRow flightRow = new SelectFlightRow(flightRowElement, direction, fareClassCode, flow, selectFlightsPageData)
            return flightRow.checkIfValid(airlineCodes, routing, itineraryData.isWebOnly, stopCitiesNames, stopChangePlaneCityCodes)
        }

        assertThat("No flight selected for " + direction + " flight with the fare product " + fareClassCode + " with the airline code " + airlineCodes, selectedRowElement, notNullValue())
        SelectFlightRow selectedFlightRow = new SelectFlightRow(selectedRowElement, direction, fareClassCode, flow, selectFlightsPageData)
        if (direction == "outbound") {
            selectedFlightRow.saveDateAndTimeFlightDeparts()
            selectedFlightRow.saveDateAndTimeDepartingFlightArrives()
            selectedFlightRow.getFlightOutBoundPrice(isSeniorFare)
        } else {
            selectedFlightRow.getFlightInBoundPrice(isSeniorFare)
        }
        selectedFlightRow.saveFlightRowInformation()
        return selectedFlightRow
    }

    private SelectFlightRow findValidInboundWithLessThenFourHourLayoverFrom(SelectFlightRow outboundFlightRow, boolean isConnectionLessThanFourHours = false ){
        String direction = "inbound";
        outboundFlightRow.saveDateAndTimeDepartingFlightArrives()
        String fareClassCode = FareClass.from(itineraryData.arrivingFlight_fareClass).fareClassCode();
        String routing = itineraryData.inboundRouting;
        Collection flightRowsByDirection = findFlightRowsByDirection(direction);

        List<String> airlineCodes = returnAirlineCodesForFlight(direction);

        WebElement selectedRowElement;

        for (flightRowElement in flightRowsByDirection){
            SelectFlightRow flightRow = new SelectFlightRow(flightRowElement, direction, fareClassCode, flow, selectFlightsPageData)
            if (flightRow.checkIfValidForPriceThough(airlineCodes, routing, itineraryData.isWebOnly, isConnectionLessThanFourHours)){
                selectedRowElement = flightRowElement;
                break;
            }
        }

        if (selectedRowElement == null){
            return null;
        }

        SelectFlightRow selectedFlightRow = new SelectFlightRow(selectedRowElement, direction, fareClassCode, flow, selectFlightsPageData);
        return selectedFlightRow;
    }

    private SelectFlightRow findValidInboundWithLessThenTwentyFiveMinutesLayoverFrom(SelectFlightRow outboundFlightRow){
        String direction = "inbound";
        outboundFlightRow.saveDateAndTimeDepartingFlightArrives()
        String fareClassCode = FareClass.from(itineraryData.arrivingFlight_fareClass).fareClassCode();
        String routing = itineraryData.inboundRouting;
        Collection flightRowsByDirection = findFlightRowsByDirection(direction);

        List<String> airlineCodes = returnAirlineCodesForFlight(direction);

        WebElement selectedRowElement;

        for (flightRowElement in flightRowsByDirection){
            SelectFlightRow flightRow = new SelectFlightRow(flightRowElement, direction, fareClassCode, flow, selectFlightsPageData)
            if (flightRow.checkIfValidFlightWithLessThenTwentyFiveMinutesLayover(airlineCodes, routing, itineraryData.isWebOnly)){
                selectedRowElement = flightRowElement;
                break;
            }
        }

        if (selectedRowElement == null){
            return null;
        }

        SelectFlightRow selectedFlightRow = new SelectFlightRow(selectedRowElement, direction, fareClassCode, flow, selectFlightsPageData);
        return selectedFlightRow;
    }

    private List<SelectFlightRow> findValidFlightsForOutbound() {
        String direction = "outbound"
        String fareClassCode = FareClass.from(itineraryData.departingFlight_fareClass).fareClassCode()
        String routing = itineraryData.outboundRouting
        Collection flightRowsByDirection = findFlightRowsByDirection(direction)

        List<String> airlineCodes = returnAirlineCodesForFlight(direction)
        SelectFlightRow flightRow

        SelectFlightRow selectedFlightRow

        List<SelectFlightRow> selectFlightRowList = newArrayList();


        for (flightRowElement in flightRowsByDirection){
            flightRow = new SelectFlightRow(flightRowElement, direction, fareClassCode, flow, selectFlightsPageData)
            if ( flightRow.checkIfValid(airlineCodes, routing, itineraryData.isWebOnly) ){
                selectedFlightRow = new SelectFlightRow(flightRowElement, direction, fareClassCode, flow, selectFlightsPageData)
                selectedFlightRow.saveDateAndTimeDepartingFlightArrives()
                selectFlightRowList.add(selectedFlightRow)
            }
        }

        return selectFlightRowList
    }

    private Collection findFlightRowsWithPlaneChange(String direction) {
        Collection selectFlightRows
        if (direction.equals("outbound")) {
            selectFlightRows = waitForElement(FARES_OUTBOUND).findElements(By.xpath(ITINERARY_WITH_CHANGE_PLANES_XPATH))
        } else {
            selectFlightRows = waitForElement(FARES_RETURN).findElements(By.xpath(ITINERARY_WITH_CHANGE_PLANES_XPATH))
        }
        return selectFlightRows
    }

    private Collection findFlightRowsWithoutPlaneChange(String direction) {
        Collection selectFlightRows
        if (direction.equals("outbound")) {
            selectFlightRows = waitForElement(FARES_OUTBOUND).findElements(By.xpath(ITINERARY_WITHOUT_CHANGE_PLANES_XPATH))
        } else {
            selectFlightRows = waitForElement(FARES_RETURN).findElements(By.xpath(ITINERARY_WITHOUT_CHANGE_PLANES_XPATH))
        }
        return selectFlightRows
    }

    private Collection findFlightRowsByDirection(String direction) {
        waitForElements(By.xpath("//tr[contains(@id, '${direction}_flightRow')]"))
    }

    def chooseFlightsEligibleForCheckin(ItineraryData itineraryData) {
        if (waitFor({isJavaScriptsDoneLoading()})) {
            SelectFlightRow foundOutboundRow = findFlightEligibleForCheckin("outbound", FareClass.from(itineraryData.departingFlight_fareClass))
            foundOutboundRow.getFlightNumber()
            foundOutboundRow.getTravelTime()
            foundOutboundRow.getDepartureAndArrivalTimes()
            foundOutboundRow.getFlightOutBoundPrice()
            foundOutboundRow.selectAvailableFlight()
            if (!itineraryData.isOneWay()) {
                SelectFlightRow foundInboundRow = findFlightEligibleForCheckin("inbound", FareClass.from(itineraryData.arrivingFlight_fareClass))
                foundInboundRow.getFlightNumber()
                foundInboundRow.getTravelTime()
                foundInboundRow.getDepartureAndArrivalTimes()
                foundInboundRow.getFlightInBoundPrice()
                foundInboundRow.selectAvailableFlight()
            }
        }
    }

    private SelectFlightRow findFlightEligibleForCheckin(String direction, FareClass fareClass) {
        List<String> airlineCodes = returnAirlineCodesForFlight(direction)
        WebElement selectedRowElement = selectEligibleForCheckinFlightRows(direction, airlineCodes, fareClass)
        if (selectedRowElement == null && direction == "outbound") {
            modifySearchDepartureDateToTomorrow()
            selectedRowElement = selectEligibleForCheckinFlightRows(direction, airlineCodes, fareClass)
        }
        else if (selectedRowElement == null && direction == "inbound") {
            modifySearchReturnDateToTomorrow()
            selectedRowElement = selectEligibleForCheckinFlightRows(direction, airlineCodes, fareClass)
        }

        selectedRowElement.shouldNotBe null, "No flight selected for ${direction} flight with the fare class ${fareClass.fareClassCode()} with the airline code(s) ${airlineCodes}."
        SelectFlightRow selectedFlightRow = new SelectFlightRow(selectedRowElement, direction, fareClass.fareClassCode(), flow, selectFlightsPageData)
        if (direction == "outbound") {
            selectedFlightRow.saveDateAndTimeDepartingFlightArrives()
        }
        return selectedFlightRow
    }

    def modifySearchSetNumberOfPassenger(int adultPassengersCount, int seniorPassengersCount) {
        toggleModifiedSearch()
        (new Select(waitForElement(ADULT_PASSENGER_COUNT))).selectByValue(String.valueOf(adultPassengersCount))
        (new Select(waitForElement(SENIOR_PASSENGER_COUNT))).selectByValue(String.valueOf(seniorPassengersCount))
        clickModifySearchSubmitButton()
    }

    private def modifySearchDepartureDateToTomorrow() {
        toggleModifiedSearch()
        itineraryData.departureDate = util.ItineraryDateFactory.getTomorrowDate(itineraryData.departureDate)
        waitForElement(OUTBOUND_DATE).sendKeys(DELETE_EXISTING + itineraryData.departureDate.format("MM/dd/yyyy") + TAB)
        waitForElement(MODIFY_SEARCH_SUBMIT_BUTTON).click()
        checkSomethingServed()
    }

    private def void modifySearchReturnDateToTomorrow() {
        toggleModifiedSearch()
        itineraryData.returnDate = util.ItineraryDateFactory.getTomorrowDate(itineraryData.returnDate)
        waitForElement(RETURN_DATE).sendKeys(DELETE_EXISTING + itineraryData.returnDate.format("MM/dd/yyyy") + TAB)
        waitForElement(MODIFY_SEARCH_SUBMIT_BUTTON).click()
        checkSomethingServed()
    }

    private WebElement selectEligibleForCheckinFlightRows(String direction, List<String> airlineCodes, FareClass fareClass) {
        Collection selectFlightRows = findElements(By.xpath("//tr[contains(@id, '${direction}_flightRow')]"))

        if (direction.equals("inbound")) {
            selectFlightRows = selectFlightRows.reverse()
        }

        WebElement selectedRowElement = selectFlightRows.find { flightRowElement ->
            def flightRow = new SelectFlightRow(flightRowElement, direction, fareClass.fareClassCode(), flow, selectFlightsPageData)
            flightRow.hasAvailableFlightForFareClass() && flightRow.isCorrectCarrier(airlineCodes) && flightRow.getRowElement().isDisplayed() && flightRow.isWithinCheckinTimeLimit(direction)
        }
        return selectedRowElement
    }

    private int getTime(String flightValue) {
        Matcher matcher = TIME_PATTERN.matcher(flightValue);
        if (matcher.find()) {
            String[] timeStrs = matcher.group().replace(":", " ").split(" ");
            int time = Integer.parseInt(timeStrs[0]) * 100;
            if (timeStrs[2].endsWith("PM")) {
                time = time + 1200
            }
            time = time + Integer.parseInt(timeStrs[1])
            return time;
        }
        throw new AssertionError((Object) "could not calculate outbound time from 'value' attribute of flight radio button")
    }

    def clickContinue() {
        String currentTitle = getTitle();
        try {
            waitForElement(PRICE_ITINERARY_SUBMIT).click()
        } catch (StaleElementReferenceException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################  waitForElement(PRICE_ITINERARY_SUBMIT).click()")
            waitForElement(PRICE_ITINERARY_SUBMIT).click()
        }
        dismissBusinessUpgradeModalIfPresent()
        waitForPageTitleToChangeOrOops(currentTitle);
        if (!isElementPresent(SENIOR_MESSAGE, 10)) {
            checkSomethingServed()
        }
        if(flow.isFaultInjected) {
            println "Entry (on clickContinue) ---- checking no oops for fault injection"
            sleep(30000)
            println "Exit (on clickContinue) ---- checking no oops for fault injection"
        }
    }


    def dismissBusinessUpgradeModalIfPresent() {
        WebElement businessUpgradeSubmitButton = waitForElement(SWA_BUTTONS_SUBMIT_BUTTON, false)
        if(businessUpgradeSubmitButton != null && businessUpgradeSubmitButton.isDisplayed() && businessUpgradeSubmitButton.isEnabled() ) {
            verifyBusinessUpgradePage()
            businessUpgradeSubmitButton.click()
        }
    }

    def verifyBusinessUpgradePage() {
        pageVerificationErrorWrapper(SWA_BUTTONS_SUBMIT_BUTTON, PageName.SELECT_FLIGHTS)
        waitForElement(UPGRADE_BUSINESS_SELECT_TITLE).text.toLowerCase().replaceAll("\n", " ").shouldContain "upgrade to business select", "Upgrade to beusiness select modal title was not equal to Upgrade to Business Select®"
    }

    def clickContinueForAdultAndSenior() {
        dismissBusinessUpgradeModalIfPresent()
        verifyPage()
        waitForElement(SENIOR_MESSAGE).isDisplayed().shouldBe true, "Senior Message on Select Flights Page is not displayed"
        //String title = getTitle()
        String currentUrl = getCurrentUrl();

        try {
            waitForElement(PRICE_ITINERARY_SUBMIT).click()
            //waitForPageTitleToChangeOrOops(title)
            waitForUrlToChangeOrOops(currentUrl)
        } catch (UnhandledAlertException uae ) {
            try {
                webDriver().switchTo().alert().dismiss()
            } catch (NoAlertPresentException nape) {
            }
        }
    }

    def selectOutboundFareByFareClassAndTime(String fareClass, String time) {
        def List radios = findElements(By.xpath("//input[@class = 'upsellOutboundRadio']"))
        selectFare(radios, fareClass, time)
    }

    def selectInboundFareByFareClassAndTime(String fareClass, String time) {
        def List radios = findElements(By.xpath("//input[@class = 'upsellInboundRadio']"))
        selectFare(radios, fareClass, time)
    }

    def verifyNoFareInDollars() {
        List<org.openqa.selenium.WebElement> symbols = findElements(By.xpath("//div/*/span[@class='currency_symbol']"))
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getText().equals("\$")) {
                fail "Dollars were found on the page"
            }
        }
        return true
    }

    private def selectFare(List<WebElement> radios, String fareClass, String time) {
        for (int i = 0; i < radios.size(); i++) {
            WebElement radio = radios.get(i);
            def String[] valArray = radio.getAttribute("value").split(",")
            if (radio.getAttribute("id").endsWith(fareClass) && valArray[DEPART_TIME].equals(time)) {
                radio.setSelected()
            }
        }
    }

    def clickFirstFlightDetailsLink(String airlineCode, String numberOfStops) {
        try {

            List<WebElement> stopLinks = findElements(By.xpath("//span[@class='bugText']//a[contains(@href, 'firstOperCarrier=${airlineCode}') and contains(text(), '${numberOfStops}')]"));
            stopLinks.get(0).click();
        }
        catch (NoSuchElementException e) {
            fail "Did not find any flight details link for ${airlineCode} ${numberOfStops} flight"
        }
    }

    def verifyRoutingPopsUp() {
        def verify = findElement(By.id("routingHoverTable"))
        def text = verify.getAttribute("style")
        if (text.contains("display: block;")) {
        } else {
            fail("Flight Details box not displayed")
        }
    }

    def selectViewNearByAirports() {
        def elements = findElements(By.xpath("//a[@class = 'viewNearbyAirportLink']"))
        for (element in elements) {
            if (element.getText().equals("View nearby airports")) {
                element.click();
                return By.id("originAirport_displayed")
            }
        }
        return null
    }

    def verifyLogoInSelectFlightFlyout() {
        String airlines = itineraryData.departingFlight_carrierCode
        verifyElementPresent("Airline ${airlines} logo", By.xpath("//div[@id='routingHoverTable']//img[contains(@src,'logo_${airlines.toLowerCase()}')]"))
    }

    def closeFlightDetailFlyOut() {
        def xString = "//div[@id='routingHoverTable' and contains(@style,'display: block')]//span[@class='close_link']"
        findElement(By.xpath(xString)).click()
    }

    def verifyFlightDetailFlyOutClosed() {
        def xString = "//div[@id='routingHoverTable' and contains(@style,'display: none')]//span[@class='close_link']"
        findElement(By.xpath(xString))
    }

    def clickFlightNumber(WebElement webElement, def attributeToCheck, def matchText, def linkToClick, def errormsg) {
        if (webElement.getAttribute(attributeToCheck).equals(matchText)) {
            webElement.findElement(By.cssSelector(linkToClick)).click()
        } else {
            fail errormsg
        }
    }

    def findAirTranAndSouthWestConnectingFlight() {
        def flightCells = findElements(By.cssSelector("div.swa_tables_td_inner"))
        for (flightCell in flightCells) {
            if (flightCell.findElements(By.xpath("div")).size() > 1) {
                def divs = flightCell.findElements(By.xpath("div"))
                return divs
            }
        }
    }

    def verifyFlightNumberWithMessage() {
        List<WebElement> verifyFlights = findAirTranAndSouthWestConnectingFlight()
        if (verifyFlights == null) fail("No Southwest-AirTran-Connection info found")
        if (verifyFlights[0].getText().contains("AirTran")) {
            fail("Southwest flight should not contain Operated by AirTran ")
        }
        if (!verifyFlights[1].getText().contains("AirTran")) {
            fail "Airtran flight should contain Operated by AirTran"
        }
    }

    def List<WebElement> clickFlightNumber() {
        List<WebElement> verifyFlights = findAirTranAndSouthWestConnectingFlight()
        clickFlightNumber(verifyFlights[1], "class", "secondFlightDiv", "a.bugLinkText", "no AirTran OPT Found")
        clickFlightNumber(verifyFlights[0], "class", "swa_text_flightNumber", "a.bugLinkText", "no WN OPT Found")
    }

    def verifyOptDisplayed() {
        List<WebElement> webElements = findAirTranAndSouthWestConnectingFlight()
        def otpPopUp = findElement(By.id("routingHoverTable")).findElements(By.tagName("strong"))
        def firstFlightInOpt = otpPopUp[0].getText()
        def secondFlightInOpt = otpPopUp[1].getText()

        if (firstFlightInOpt.substring(firstFlightInOpt.indexOf("#") + 1).trim() != webElements[0].getText()) {
            fail("flight ${webElements[1].getText()} did not match")
        } else if (secondFlightInOpt.substring(secondFlightInOpt.indexOf("#") + 1).trim() != webElements[1].getText().find(/\d+/)) {
            fail("flight ${webElements[0].getText()} did not match")
        }
    }

    void clickModifySearchSubmitButton() {
        waitForElement(MODIFY_SEARCH_SUBMIT_BUTTON).click()
    }

    void toggleModifiedSearch() {
        if (waitFor({isJavaScriptsDoneLoading()})) {
            waitForElement(MODIFY_SEARCH_TOGGLE).click()
        }
    }

    void verifyCertificateRadioButtonIsSelected() {
        assertTrue("Certificate Available radio button is NOT selected", isFareCertificateOptionSelected())
    }

    void verifyStandardAndFreedomAwards() {
        assertTrue("Standard Awards column NOT available", waitForElement(STANDARD_AWARD_HEADER).isDisplayed())
        assertTrue("Freedom Awards column NOT available", waitForElement(FREEDOM_AWARD_HEADER).isDisplayed())
        List<WebElement> webElements = findOutboundHeaders()
        int expectedHeaderSize = 2;
        int actualHeaderSize = webElements.size();
        assertEquals( expectedHeaderSize, actualHeaderSize )
    }

    void verifyDingColumnIsPresent() {
        assertTrue("Ding column is not visible", waitForElement(DING_HEADER).isDisplayed())
    }

    def verifyDollarsOptionCheckedByDefault() {
        waitForElement(TOGGLE_FOR_FARE_IN_DOLLARS).getAttribute("checked").shouldBe "true", "Dollar fare was not selected by default"
        if (!itineraryData.oneWay) {
            waitForElement(TOGGLE_FOR_INBOUD_FARE_IN_DOLLARS).getAttribute("checked").shouldBe "true", "Dollar fare was not selected by default"
        }
    }

    def verifyFarePointsChecked() {
        waitForElement(FARE_POINTS_OPTION).getAttribute("checked").shouldBe "true", "Fare Points should be selected"
    }

    def togglePoints() {
        waitForElement(TOGGLE_FOR_FARE_IN_POINTS, true, 60).click()
        verifyNoFareInDollars()
    }

    def toggleDollars() {
        waitForElement(TOGGLE_FOR_FARE_IN_DOLLARS).click()
    }

    def toggleCertificate() {
        waitForElement(FARE_CERTIFICATE_OPTION).click()
    }

    void displayOutboundFareDetails() {
        waitForElement(HEADER_TEXT).click()
        List<WebElement> fareClassHeaders = waitForElement(By.id("faresOutbound")).findElements(By.className("searchResultsHoverHeader"))
        WebElement seniorFairMessage = waitForElement(By.className(("seniorTravelMessage")), false)
        int fareClassesThatShouldBeOnPage = seniorFairMessage ? 4 : 3

        fareClassHeaders.size.shouldBeEqual fareClassesThatShouldBeOnPage, "Mismatch of fare class availability for " +
                (seniorFairMessage ? "adult passengers." : "senior passengers")

        String fareClass
        fareClassHeaders.each {
            fareClass = it.findElement(By.className("headerText")).getText()
            assertTrue("Fare class not expected: " + fareClass, FareClass.FARE_CLASS_NAMES.contains(fareClass))
        }

        //     click(By.cssSelector("a.fareProductHoverClose"))
    }

    public void selectFlights(boolean isSeniorFare = false) {
        selectFlight("outbound", isSeniorFare)
        if (itineraryData.itineraryType != "One Way") {
            selectFlight("inbound", isSeniorFare)
        }
    }

    public void selectFlightsForSeniorFare() {
        selectFlights(true)
    }

    public void selectFlightsWithLessThanFourHourLayover(boolean isConnectionLessThanFourHours = false ) {
        SelectFlightRow validInbound
        List<SelectFlightRow> flightRowList = findValidFlightsForOutbound()
        for (SelectFlightRow flightRow in flightRowList){
            validInbound = findValidInboundWithLessThenFourHourLayoverFrom(flightRow, isConnectionLessThanFourHours);
            if (validInbound != null){
                flightRow.selectAvailableFlight()
                validInbound.selectAvailableFlight()
                flightRow.getFlightNumber()
                flightRow.getTravelTime()
                flightRow.getDepartureAndArrivalTimes()
                validInbound.getFlightNumber()
                validInbound.getTravelTime()
                validInbound.getDepartureAndArrivalTimes()
                return
            }
        }
    }

    public void selectFlightsWithLessThenTwentyFiveMinutesLayover() {
        SelectFlightRow validInbound
        List<SelectFlightRow> flightRowList = findValidFlightsForOutbound()
        for (SelectFlightRow flightRow in flightRowList){
            validInbound = findValidInboundWithLessThenTwentyFiveMinutesLayoverFrom(flightRow);
            if (validInbound != null){
                flightRow.selectAvailableFlight();
                validInbound.selectAvailableFlight();
                return
            }
        }
    }

    public void selectFlightsByNumber(ItineraryData previousItinerary) {
        String flightNumber = selectFlightNumberByRouting("outbound", previousItinerary)
        selectFlightByNumber("outbound",flightNumber)
        if (previousItinerary.itineraryType != "One Way") {
            flightNumber = selectFlightNumberByRouting("inbound", previousItinerary)
            selectFlightByNumber("inbound",flightNumber)
        }
    }

    private String selectFlightNumberByRouting(String direction, ItineraryData previousItinerary) {
        String flightNumber = ""
        if (DynaStubsIntegration.useDynaStubs()) {
            if(direction == "outbound") {
                flightNumber = previousItinerary.departingFlight_number
            } else {
                flightNumber = previousItinerary.returningFlight_number
            }
        } else {
            if(direction == "outbound") {
                flightNumber = previousItinerary.flightNumbers.get(0)
            } else {
                flightNumber = previousItinerary.flightNumbers.get(1)
            }
            flightNumber = flightNumber.substring(1)
        }
        return flightNumber
    }

    def void selectFlight(String direction, boolean isSeniorFare) {
        SelectFlightRow foundRow = selectFlightRow(direction, isSeniorFare)
        foundRow.getFlightNumber()
        foundRow.getDepartureAndArrivalTimes()
        foundRow.getTravelTime()
        foundRow.selectAvailableFlight()
        foundRow.earlyBirdEligibilityCheck(direction)
        if(passengerData.containsSeniorPassenger()) {
            foundRow.getSeniorPrice()
        }
        if (direction == "outbound") {
            selectFlightsPageData.selectedOutboundFlightDetailsLink = foundRow.returnCurrentFlightDetailLink()
        } else {
            selectFlightsPageData.selectedInboundFlightDetailsLink = foundRow.returnCurrentFlightDetailLink()
        }
        if (itineraryData.promoCode != null && itineraryData.promoCode.equals("PODBUGS")) {
            foundRow.verifyDiscountedFare()
        }
    }

    public void selectFlightByNumber(String direction, String flightNumber) {
        SelectFlightRow foundRow = findValidFlightByDirectionAndNumber(direction, flightNumber)
        foundRow.selectAvailableFlight()
    }

    def returnAirlineCodesForFlight(String flightDirection) {
        List<String> airlineCodes = newArrayList();
        if (flightDirection == "outbound") {
            airlineCodes.add(itineraryData.departingFlight_carrierCode)
            if(!StringUtils.isBlank(itineraryData.outbound_connection_carrierCode)) {
                airlineCodes.add(itineraryData.outbound_connection_carrierCode)
            }
        }
        else {
            airlineCodes.add(itineraryData.arrivingFlight_carrierCode)
            if(!StringUtils.isBlank(itineraryData.inbound_connection_carrierCode)) {
                airlineCodes.add(itineraryData.inbound_connection_carrierCode)
            }
        }

        airlineCodes
    }

    def isAnyWhatYouSeeIsWhatYouPayLinkPresentWithTarget() {
        List<WebElement> links = findElements(By.xpath("//a[contains(@href, 'pop_fullFareDisclosure.html')]"))
        return links.any() { WebElement link -> link.isDisplayed() && link.getAttribute("target").length() > 0 }
    }

    def verifyWeightAndSizeLimitsLink() {
        WebElement searchResultsForm = waitForElement(By.id("searchResults"))
        WebElement bagsAndFareInfo = searchResultsForm.findElement(By.className("bagsAndFareInfo"))
        if (bagsAndFareInfo.isDisplayed()) {
            bagsAndFareInfo.findElement(By.cssSelector("a[href='/html/customer-service/baggage/checked-bags-pol.html']")).getAttribute("target").contains("_").shouldBe true
        } else {
            fail "bag and fare info - not displayed"
        }
    }

    def verifyWhatYouSeeIsWhatYouPyLink() {
        WebElement fullFareLinkDeparting = waitForElement(By.id("fullFareLinkDeparting"))
        if (fullFareLinkDeparting.isDisplayed()) {
            fullFareLinkDeparting.getAttribute("href").contains("/flight/pop_fullFareDisclosure.html")
            fullFareLinkDeparting.getAttribute("target").contains("_").shouldBe true
        } else {
            fail "full fare departing - not displayed"
        }
    }

    private def clickDirectionAlternateAirportLink(String direction, String alternateAirportLinkText) {
        WebElement searchResults = waitForElement(SEARCH_RESULTS)
        List<WebElement> links = searchResults.findElements(By.cssSelector("a"))
        links.find() { WebElement link ->
            link.isDisplayed() &&
                    link.getAttribute("href").contains(direction) &&
                    link.getText().contains(alternateAirportLinkText)
        }.click()
        verifyPage()
    }

    def outboundUpsellDrawerHidden() {
        return !outboundUpsellDrawerDisplayed()
    }

    def clickFirstDisplayedAnytimeFlightRowWithUpgrade() {
        String firstUpgradable = findFirstUpgradableRadioButtonId()
        firstUpgradable.shouldNotBe null, "No upgradable outbound flights found."
        WebElement selectTable = waitForElement(FARES_OUTBOUND)
        List<WebElement> radios = selectTable.findElements(By.cssSelector("input.upsellOutboundRadio"))
        radios.find { radio -> radio.getAttribute("id") == firstUpgradable && radio.isDisplayed() }.click()
    }

    private def findFirstUpgradableRadioButtonId() {
        String pageSource = getPageSource()
        Matcher matcher = pageSource =~ /(?s)<div id="(Out\d{1,2}B)Div">.*?showUpgradeDrawer">(\w*)/
        def firstUpgradable = matcher.find { it[2] == 'true' }
        if (firstUpgradable == null) {
            return null
        } else {
            return firstUpgradable[1]
        }
    }

    def outboundUpsellDrawerDisplayed() {
        return waitForElement(OUTBOUND_UPSELL_DRAWER).isDisplayed()
    }

    def closeUpsellDrawerTextLink() {
        WebElement buttonContainer = findElement(By.id("OutboundUpsellDrawerDiv")).findElement(By.className("upsellButton"))
        return buttonContainer.findElement(By.className("closeUpsellDrawer"))
    }

    def closeUpsellLinkTextDisplayed() {
        return closeUpsellDrawerTextLink().getText().contains("No thanks, keep Anytime")
    }

    def clickCloseUpsellDrawerTextLink() {
        closeUpsellDrawerTextLink().click()
    }

    def clickCloseUpsellDrawerButton() {
        findElement(By.id("OutboundUpsellDrawerDiv")).findElement(By.className("closeUpsellDrawer")).click()
    }

    def clickUpsellDrawerUpgradeButton() {
        WebElement upsellDrawer = waitForElement(OUTBOUND_UPSELL_DRAWER)
        WebElement blueButton = upsellDrawer.findElement(By.className("upsellButton")).findElement(By.className("buttonContainer")).findElement(By.className("blueButton"))
        blueButton.getText().contains("Upgrade").shouldBe true
        blueButton.click()
    }

    def upsellWelcomeDrawer() {
        WebElement upsellDrawer = waitForElement(OUTBOUND_UPSELL_DRAWER)
        WebElement welcomeDrawer = upsellDrawer.findElement(By.className("upsellInfo"))
        return welcomeDrawer
    }

    def outboundWelcomeToBusinessSelectDrawerDisplayed() {
        WebElement welcomeDrawer = upsellWelcomeDrawer()
        WebElement fareType = welcomeDrawer.findElement(By.className("fareTypeContainer")).findElement(By.className("fareTypeSubContainer"))
        String fareTypeName = fareType.findElement(By.className("fareTypeName")).getText()
        (fareTypeName.contains("Welcome to Business Select")).shouldBe true
        return welcomeDrawer.isDisplayed()
    }

    def welcomeDrawerReturnFlightButtonDisplayed() {
        WebElement welcomeDrawer = upsellWelcomeDrawer()
        WebElement upsellButton = welcomeDrawer.findElement(By.className("upsellButton")).findElement(By.className("buttonContainer"))
        String buttonName = upsellButton.findElement(By.className("blueButton")).getText()
        (buttonName.contains("Select return flight")).shouldBe true
        return upsellButton.isDisplayed()
    }

    void clickOneWay() {
        waitForElement(ONE_WAY_RADIO_BUTTON).click()
    }

    void selectItineraryType(ItineraryData itineraryData) {
        if (itineraryData.itineraryType == "One Way") {
            clickOneWay()
        }
        else {
            waitForElement(ROUND_TRIP_RADIO_BUTTON).click()
        }
    }

    def getOutboundSortColumnHeaderIdAndOrder() {
        WebElement outboundTable = waitForElement(FARES_OUTBOUND)
        List<WebElement> headers = outboundTable.findElements(By.className("staticHeader"))
        WebElement header = headers.find() { header ->
            header.isDisplayed() && header.getAttribute("class").contains("sortedHeader")
        }
        if (header.getAttribute("className").contains("sortedHeaderDesc")) {
            return ["id": header.getAttribute('id'), "order": "descending"]
        } else {
            return ["id": header.getAttribute('id'), "order": "ascending"]
        }
    }

    def displayedOutboundRoutingColumnHeader() {
        WebElement outboundTable = waitForElement(FARES_OUTBOUND)
        List<WebElement> headers = outboundTable.findElements(By.id("out_stops_header"))
        WebElement header = headers.find() { header -> header.isDisplayed() }
        return header
    }

    def isOutboundRoutingOrderDescending() {
        return getOutboundSortColumnHeaderIdAndOrder().id == "out_stops_header" && getOutboundSortColumnHeaderIdAndOrder().order == "descending"
    }

    def sortOutboundRoutingColumnInDescendingOrder() {
        try {
            if (!isOutboundRoutingOrderDescending()) {
                displayedOutboundRoutingColumnHeader().click()
            }
            if (getOutboundSortColumnHeaderIdAndOrder().order == "ascending") {
                displayedOutboundRoutingColumnHeader().click()
            }
        } catch (NotFoundException exception) {
            fail(exception.message)
        }
    }

    def firstOutboundRowWithPlaneChange() {
        WebElement outboundTable = waitForElement(FARES_OUTBOUND)
        List<WebElement> outboundRows = outboundTable.findElements(By.cssSelector("tr.bugTableRow"))
        WebElement outboundRow = outboundRows.find() { outboundRow ->
            WebElement routingColumn = outboundRow.findElement(By.className("routing_column"))
            routingColumn.isDisplayed() &&
                    routingColumn.findElement(By.cssSelector("a.bugLinkRouting")).getAttribute("href").contains("+plane+change") &&
                    !routingColumn.findElement(By.cssSelector("a.bugLinkRouting")).getAttribute("href").contains("no+plane+change")
        }
        return outboundRow
    }

    def changePlanesHasText() {
        WebElement changePlanesRow = firstOutboundRowWithPlaneChange()
        WebElement routingColumn = changePlanesRow.findElement(By.className("routing_column"))
        return routingColumn.findElement(By.className("bugText")).getText().contains("Change Planes")
    }

    def changePlanesHasIcon() {
        WebElement changePlanesRow = firstOutboundRowWithPlaneChange()
        WebElement routingColumn = changePlanesRow.findElement(By.className("routing_column"))
        return routingColumn.getAttribute("class").contains("change-planes")
    }

    def List<WebElement> findOutboundHeaders() {
        WebElement outboundTable = waitForElement(FARES_OUTBOUND)
        List<WebElement> outboundHeaders = outboundTable.findElements(HEADERS)
        return outboundHeaders
    }

    def eachFareTypeHeaderContainsMatchingFareTypeDescription() {
        return findOutboundHeaders().every { WebElement header ->
            String fareTypeName = header.findElement(HEADER_TEXT).getAttribute("title")
            List<String> fareTypeHeaderContents = header.getText().split("\\n")
            String fareTypeDescription = FARE_TYPE_DESCRIPTIONS.get(fareTypeName)
            String fareTypePointsDescription = FARE_TYPE_POINTS_DESCRIPTIONS.get(fareTypeName)
            fareTypeHeaderContents[0].trim().contains(fareTypeName) &&
                    fareTypeHeaderContents[1].trim().contains(fareTypeDescription) &&
                    fareTypeHeaderContents[2].trim().contains(fareTypePointsDescription)
        }
    }

    def SelectFlightRow findValidFlightByDirectionAndNumber(String direction, String flightNumber) {
        List<WebElement> flightRowsByDirection = findFlightRowsByDirection(direction)

        WebElement selectedRowElement
        for (WebElement selectedRow in flightRowsByDirection) {
            if (selectedRow.findElement(FLIGHT_NUMBER).getText().contains(flightNumber)) {
                selectedRowElement = selectedRow
                break
            }
        }

        selectedRowElement.shouldNotBe null, "No flight selected for " + direction + " flight with flight number " + flightNumber

        String fareClassCode
        if (direction == "outbound") {
            fareClassCode = FareClass.from(itineraryData.departingFlight_fareClass).fareClassCode()
        } else {
            fareClassCode = FareClass.from(itineraryData.arrivingFlight_fareClass).fareClassCode()
        }

        SelectFlightRow selectedFlightRow = new SelectFlightRow(selectedRowElement, direction, fareClassCode, flow, selectFlightsPageData)
        if (direction == "outbound") {
            selectedFlightRow.saveDateAndTimeDepartingFlightArrives()
        }

        return selectedFlightRow
    }

    def void clickRoutingLink() {
        WebElement routingLink = waitForElement(By.className("bugLinkRouting"))
        routingLink.click()
    }

    def boolean verifyFlightDetailsPopup() {
        WebElement flightDetails = waitForElement(By.id("routingHoverTable"))
        flightDetails.isDisplayed()
    }

    def void clickFareTypes() {
        WebElement anytimeFare = waitForElement(By.className("anytimeHeader")).findElement(By.className("headerText"))
        anytimeFare.click()
    }

    def boolean verifyFareDetailsPopup() {
        WebElement fareDetails = waitForElement(By.id("fareProductHover"))
        fareDetails.isDisplayed()
    }

    def void verifyOopsReturnDate() {
        String oopsMessage = 'You did not enter a return date. (SW900001)'
        shouldShowOopsMessage(oopsMessage)
    }

    def boolean isDisplayed() {
        getCurrentUrl().shouldContain getRelativePath()
    }

    def boolean isBSFareColumnHighlighted() {
        return isElementPresent(HIGHLIGHT_COLUMN)
    }

    def boolean isACSDrawerDisplayed() {
        return isElementPresent(ACS_DRAWER)
    }

    def void isFlyByBestValueBoxPresent() {
        WebElement Container = waitForElement(SW_CONTAINER)

        if(!Container.findElement(BEST_VALUE_MSG_BOX).isDisplayed()) {
            Container.findElement(BEST_VALUE_STAR_BUTTON).click()
        }
        Container.findElement(BEST_VALUE_FLYBY_TEXT).isDisplayed().shouldBe true, "FlyBy text within the Best Value message box should be displayed."

    }

    def void clickContinueToUpsellModal() {
        waitForElement(PRICE_ITINERARY_SUBMIT).click()
        this.verifyPage()
    }
    
    void verifyUpsellModal() {
        if (selectFlightsPageData.shouldShowUpsell) {
            isUpsellModalDisplayed().shouldBe true, "The upsell modal is not being shown."
            waitForElement(UPSELL_MODAL_SUBTITLE).text.shouldContain UPSELL_MODAL_SUBTITLE_TEXT, "Upsell modal does not contain the subtitle."
        }
    }

    def assignShouldBSUpsellModalShow() {
        if (flow.isRapidRewardsPointsPurchaseOnly || itineraryData.isOpenJaw() || itineraryData.seniorPassengerCount > 0) {
            selectFlightsPageData.shouldShowUpsell = false
        } else if (itineraryData.isRoundTripOrOpenJaw()) {
            if (isFareClassEligibleForBSUpsell()) {
                selectFlightsPageData.shouldShowUpsell = isFlightPriceEligibleForBSUpsell()
            } else {
                selectFlightsPageData.shouldShowUpsell = false
            }
        } else {
            selectFlightsPageData.shouldShowUpsell = true
        }
    }

    private boolean isFareClassEligibleForBSUpsell() {
        if (itineraryData.isRoundTrip()) {
            return (itineraryData.departingFlight_fareClass == FareClass.Anytime.name()
                    || itineraryData.arrivingFlight_fareClass == FareClass.Anytime.name())
        } else {
            return itineraryData.departingFlight_fareClass == FareClass.Anytime.name()
        }
    }

    private boolean isFlightPriceEligibleForBSUpsell() {
        BigDecimal outboundFlightPrice = getBusinessSelectPriceForARow(selectFlightsPageData.outboundRow + "A")
        boolean isWithinOutboundBSUpsellRange = isWithinBSUpsellPriceRange(outboundFlightPrice, selectFlightsPageData.outboundFlightPrice, FareClass.from(selectFlightsPageData.departingFlight_fareClass))
        if (itineraryData.isRoundTrip()) {
            BigDecimal inboundFlightPrice = getBusinessSelectPriceForARow(selectFlightsPageData.inboundRow + "A")
            return isWithinOutboundBSUpsellRange || isWithinBSUpsellPriceRange(inboundFlightPrice, selectFlightsPageData.inboundFlightPrice, FareClass.from(selectFlightsPageData.arrivingFlight_fareClass))
        } else {
            return isWithinOutboundBSUpsellRange
        }
    }

    private BigDecimal getBusinessSelectPriceForARow(String inboundOrOutboundRow) {
        String outboundFlightPriceWithDollarSymbol = waitForElement(By.id(inboundOrOutboundRow)).getAttribute("title").split(' ')[FLIGHT_PRICE_ARRAY_LOCATION]
        return new BigDecimal(outboundFlightPriceWithDollarSymbol.replace('$', ''))
    }

    private boolean isWithinBSUpsellPriceRange(BigDecimal selectedPrice, BigDecimal businessSelectPrice, FareClass fareClass) {
        if(fareClass == FareClass.Anytime) {
            return businessSelectPrice.subtract(selectedPrice) <= ANYTIME_BS_UPGRADE_RANGE
        } else {
            return businessSelectPrice.subtract(selectedPrice) <= WGA_BS_UPGRADE_RANGE
        }
    }

    def boolean isUpsellModalDisplayed() {
        return isElementPresent(UPSELL_MODAL)
    }

    def void verifyNoApplySectionOnUpsellModalToOutBound(){
        this.isInboundUpsellAvailable().shouldBe true, "The inbound checkbox should be shown"
        verifyNoApplySectionOnUpsellModal(OUT_BOUND_ROW)
    }

    def void verifyNoApplySectionOnUpsellModalToInBound(){
        this.isOutboundUpsellAvailable().shouldBe true, "The outbound checkbox should be shown"
        verifyNoApplySectionOnUpsellModal(IN_BOUND_ROW)
    }

    def private void verifyNoApplySectionOnUpsellModal(By rowTable){
        WebElement webElement = waitForElement(rowTable).findElement(NO_APPLY_UPSELL_MODAL)
        webElement.getText().shouldContain NO_APPLY_UPSELL_MODAL_TEXT
    }

    def void isBookTogetherButtonNotDisplayed() {
        verifyElementNotPresent("Book together button", BOOK_TOGETHER_BUTTON)
    }

    def void isBookTogetherButtonDisplayed() {
        verifyElementPresent("Book together button", BOOK_TOGETHER_BUTTON)
    }

    def clickFirstFlightNumberLink(String routingType) {
        getFirstFlightNumberLink(routingType).click()
    }

    def verifySingleFlyoutInformation() {
        waitForElement(SINGLE_FLYOUT_TITLE).getText().shouldBeEqual "Flight Information"
        waitForElement(SINGLE_FLYOUT_FLY_NUMBER).getText().shouldContain getFirstFlightNumberLink("nonstop").getText()
        waitForElement(ON_TIME_PERFORMANCE_TITLE)
        if (DynaStubsIntegration.useDynaStubs()) {
            waitForElement(AIRCRAFT_INFORMATION_WCM_FRAGMENT).isDisplayed().shouldBe true
        }
    }

    def verifyAirTranSingleFlyoutInformation() {
        verifyFlightDetailsPopup()
        verifySingleFlyoutInformation()

        List<WebElement> webElements = findElements(OPERATED_BY_AIRTRAN)
        webElements.get(0).getText().shouldContain "Operated by AirTran"
    }

    def verifyConnectingFlyoutInformation() {
        waitForElement(CONNECTING_FLYOUT_TITLE).getText().shouldBeEqual "Flight Information"
        List<WebElement> flightNumbers = waitForElements(CONNECTING_FLYOUT_FLY_NUMBER)

        ArrayList<String> eachFlightNumber;

        eachFlightNumber = getFlightNumbersFromLinks("Change Planes")

        for(int i = 0; i < flightNumbers.size() ; i++ ) {
            flightNumbers.get(i).getText().shouldContain eachFlightNumber.get(i)
        }
    }

    def WebElement getFirstFlightNumberLink(String routingType) {
        try {
            List<WebElement> links = findFlightNumberLinks(routingType)
            return links.get(0)
        }
        catch (NoSuchElementException e) {
            fail "Did not find any flight number link for ${routingType} flights"
        }
    }

    def ArrayList<String> getFlightNumbersFromLinks(String routingType) {
        try {
            List<WebElement> links = findFlightNumberLinks(routingType)
            ArrayList<String> flightNumbers = newArrayList()
            for(WebElement link : links) {
                flightNumbers.add(link.getText())
            }
            return flightNumbers
        }
        catch (NoSuchElementException e) {
            fail "Did not find any flight number link for ${routingType} flights"
        }
    }

    def List<WebElement> findFlightNumberLinks(String routingType) {
        By selector;
        if (! routingType.equalsIgnoreCase("Change Planes")) {
            selector = DIRECT_PLANES_ROWS
        }

        selector = CHANGE_PLANES_ROWS

        return findElements(selector)
    }

    def verifyIfAircraftInformationSuppressed() {
        if (DynaStubsIntegration.useDynaStubs()) {
            isElementPresent(AIRCRAFT_INFORMATION_WCM_FRAGMENT).shouldBe false, "The aircraft information should not be present"
        }
    }

    def void waitForSeniorFareSelectFlightPage() {
        WebElement stepTextElement = waitForElement(STEP_TEXT)
        while (stepTextElement.getText().contains("Senior") == false) {
            sleep(10)
            waitFor(stepTextElement = waitForElement(STEP_TEXT))
        }
    }

    def void verifyOopsInboundOutboundShouldBeSelected(){
        def oopsMessages = [
            'No Outbound Flight selected.',
            'No Inbound Flight selected.'
        ]
        verifyOopsMessages(oopsMessages)
    }

    def void selectReturnDay(int d){
        List<WebElement> lreturn = waitForElements(AVAILABLE_RETURN_CAROUSEL_DATES)
        lreturn.get(d+1).click()
    }

    def boolean isOutboundUpsellAvailable() {
        return (waitForElement(OUTBOUND_UPSELL_ON_MODAL).isEnabled() && waitForElement(OUTBOUND_UPSELL_ON_MODAL).isDisplayed())
    }

    def boolean isInboundUpsellAvailable() {
        return (waitForElement(INBOUND_UPSELL_ON_MODAL).isEnabled() && waitForElement(INBOUND_UPSELL_ON_MODAL).isDisplayed())
    }

    def void verifyBothSegmentsUpsellModal() {
        (isOutboundUpsellAvailable() && isInboundUpsellAvailable()).shouldBe true, "Both flight segments on the modal should be available for upsell."
    }

    def void selectFlightsBasedOn(ReservationSpecification specification) {
        selectFlight(specification.outbound.fareClass, OUTBOUND)
        if (!specification.oneWay) {
            selectFlight(specification.inbound.fareClass, INBOUND)
        }
        clickContinue()
    }

    def void selectFlightsAndStayInModalBasedOn(ReservationSpecification specification) {
        selectFlight(specification.outbound.fareClass, OUTBOUND)
        if (!specification.oneWay) {
            selectFlight(specification.inbound.fareClass, INBOUND)
        }
        waitForElement(PRICE_ITINERARY_SUBMIT).click()
    }

    def void selectFlight(String fareClass, DirectionType direction) {
        Collection flightRowsByDirection = findFlightRowsByDirection(direction.lowerCaseName())

        WebElement selectedRowElement = flightRowsByDirection.find { flightRowElement ->
            SelectFlightRow flightRow = new SelectFlightRow(flightRowElement, direction.lowerCaseName(), fareClass, flow, selectFlightsPageData)
            return flightRow.hasAvailableFlightForFareClass()
        }

        SelectFlightRow selectedFlightRow = new SelectFlightRow(selectedRowElement, direction.lowerCaseName(), fareClass, flow, selectFlightsPageData)
        selectedFlightRow.selectAvailableFlight()
    }

    public void selectSpecificRowFlight(String direction, String row) {
        SelectFlightRow foundRow = findValidSpecificFlightRow(direction, row)
        foundRow.selectAvailableFlight()
    }

    private SelectFlightRow findValidSpecificFlightRow(String direction, String row) {
        Collection flightRowsByDirection = findFlightSpecificRowByDirection(direction, row)
        Collections.shuffle(flightRowsByDirection)

        String fareClassCode
        String routing

        if (direction == "outbound") {
            fareClassCode = FareClass.from(itineraryData.departingFlight_fareClass).fareClassCode()
            routing = itineraryData.outboundRouting
        } else {
            fareClassCode = FareClass.from(itineraryData.arrivingFlight_fareClass).fareClassCode()
            routing = itineraryData.inboundRouting
        }

        List<String> airlineCodes = returnAirlineCodesForFlight(direction)
        WebElement selectedRowElement = flightRowsByDirection.find { flightRowElement ->
            SelectFlightRow flightRow = new SelectFlightRow(flightRowElement, direction, fareClassCode, flow, selectFlightsPageData)
            return flightRow
        }

        SelectFlightRow selectedFlightRow = new SelectFlightRow(selectedRowElement, direction, fareClassCode, flow, selectFlightsPageData)

        if (direction == "outbound") {
            selectedFlightRow.saveDateAndTimeDepartingFlightArrives()
        }
        return selectedFlightRow
    }

    private Collection findFlightSpecificRowByDirection(String direction, String row) {
        waitForElements(By.xpath("//tr[contains(@id, '${direction}_flightRow_${row}')]"))
    }

    def verifyFreedomAwardsConnotBeConbinedWithDollards() {
        isElementWithTextPresent(FREEDOM_AWARDS_CHANGE, FREEDOM_AWARDS_CHANGE_MSG).shouldBe true, "The freedom awards message should contain: " + FREEDOM_AWARDS_CHANGE_MSG
    }

    def verifyStandardAndFreedomAwardsCannotBeCombinedWithDollars() {
        isElementWithTextPresent(FREEDOM_AWARDS_CHANGE, STANDARD_FREEDOM_AWARDS_CHANGE_MSG).shouldBe true, "The freedom awards message should contain: " + STANDARD_FREEDOM_AWARDS_CHANGE_MSG
    }

    def verifyFreedomAwardsExpirationDateBooking() {
        String expirationDateMsg = waitForElement(FREEDOM_AWARDS_CHANGE).getText()
        expirationDateMsg.matches(EXPIRATION_DATE_FORMAT).shouldBe true, "Expiration date is not correctly formatted"
    }

    def verifyDollarPointToggleOff() {
        verifyElementNotPresent("Fare Toggle", FARE_TOGGLE)
    }

    def verifyDollarPointToggleOn() {
        verifyElementPresent("Fare Toggle Dollars", TOGGLE_FOR_FARE_IN_DOLLARS)
        verifyElementPresent("Fare Toggle Points", TOGGLE_FOR_FARE_IN_POINTS)
    }

    def verifyCertificateToggleOff() {
        verifyElementNotPresent("Fare Toggle Certificate", TOGGLE_FOR_FARE_IN_CERTIFICATE)
    }

    def verifyDollarPointCertificateToggleOn() {
        verifyElementPresent("Fare Toggle Dollars", TOGGLE_FOR_FARE_IN_DOLLARS)
        verifyElementPresent("Fare Toggle Points", TOGGLE_FOR_FARE_IN_POINTS)
        verifyElementPresent("Fare Toggle Certificate", TOGGLE_FOR_FARE_IN_CERTIFICATE)
    }

    def verifyFreedomAwardIsOnlyAvailableFare() {
        verifyAvailableFareBySegment(FIRST_BOUND_FARES_AVAILABLE)
        verifyAvailableFareBySegment(SECOND_BOUND_FARES_AVAILABLE)
    }

    def verifyFreedomAwardIsOnlyAvailableFareForInboundFlight() {
        verifyAvailableFareBySegment(FIRST_BOUND_FARES_AVAILABLE)
    }

    private verifyAvailableFareBySegment(By segment) {
        List<WebElement> segmentFaresAvailable = waitForElements(segment)
        segmentFaresAvailable.size().shouldBe 1, "There should be one fare available"
        segmentFaresAvailable.get(0).getText().shouldContain "Freedom Award", "The column Freedom Award should be displayed"
    }

    public void verifyPriceWithTaxesForAFlight() {
        WebElement selectedRow = waitForElement(SEARCH_FIRST_OUTBOUND_ROW)
        WebElement rowContainerA = selectedRow.findElement(SEARCH_A_OUTBOUND_ROW_CONTAINER)
        WebElement priceColumn = rowContainerA.findElement(PRODUCT_PRICE)
        Integer price = new Integer(priceColumn.getText().substring(1))
        Integer totalBasePrice = itineraryData.getTotalBasePriceRounded()
        totalBasePrice.shouldBe price, "Total Price should be equals to Price with Taxes"
    }

    def clickContinueForSanJuan() {
        String title = getTitle()
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.retrievePriceOopsMessageSanJuanRestriction()
        }
        waitForElement(PRICE_ITINERARY_SUBMIT).click()
        dismissBusinessUpgradeModalIfPresent()
        if(!isElementPresent(SENIOR_MESSAGE, 10)) {
            waitForPageTitleToChangeOrOops(title)

        }
    }

    public void verifyBusinessSelectFareForOutboundWithWNFLSegments() {
        List<WebElement> flightRowsByDirection = findFlightRowsByDirection("outbound")

        for (WebElement flightRowElement in flightRowsByDirection){
            SelectFlightRow flightRow = new SelectFlightRow(flightRowElement, "outbound", FareClass.from(itineraryData.departingFlight_fareClass).fareClassCode(), flow, selectFlightsPageData)
            List<String> airlineCodes = new ArrayList<String>(){{add("WN"); add("FL");}}

            if (flightRow.checkIfValid(airlineCodes, itineraryData.outboundRouting, itineraryData.isWebOnly))
            {
                return;
            }
        }

        fail("Did not find Business Select Fare for Outbound with WN/FL Segments")
    }

    public void verifyMultipleFareClassColumnsDisplayed() {

        if (waitFor({isJavaScriptsDoneLoading()})) {
            WebElement businessSelectHeader;
            try {
                businessSelectHeader = findElement(BUSINESS_SELECT_HEADER);
            } catch (Exception e) {
                businessSelectHeader = null;
            }
            WebElement wannaGetAwayHeader;
            try {
                wannaGetAwayHeader = findElement(WANNA_GET_AWAY_HEADER);
            } catch (Exception e) {
                wannaGetAwayHeader = null;
            }
            WebElement anytimeHeader;
            try {
                anytimeHeader = findElement(ANY_TIME_HEADER);
            } catch (Exception e) {
                anytimeHeader = null;
            }
            if ((businessSelectHeader == null) && (wannaGetAwayHeader == null) && (anytimeHeader == null)) {
                fail("Can't find BS or WGA or AT header")
            }
        }
    }

    public void verifySinglePromoCertFareClassColumnDisplayed() {
        if (waitFor({isJavaScriptsDoneLoading()})) {
            WebElement promoCert = findElement(PROMO_CERTIFICATE_HEADER)
            if (promoCert == null) {
                fail("Can't find single promo cert")
            }
        }
    }

    public void clickOnValidFlightDetailLink(String direction, boolean isSeniorFare) {
        SelectFlightRow foundRow = selectFlightRow(direction, isSeniorFare)
        foundRow.returnCurrentFlightDetailLink().click()
    }

    public SelectFlightRow selectFlightRow(String direction, boolean isSeniorFare) {
        String fareClass =  fareClass(direction, isSeniorFare)
        SelectFlightRow foundRow = findValidFlight(direction, fareClass, isSeniorFare)
        return foundRow
    }

    private String fareClass(String direction, boolean isSeniorFare) {
        String fareClass = (isSeniorFare)? FareClass.Senior : itineraryData.departingFlight_fareClass
        if(direction == "inbound") {
            fareClass = (isSeniorFare)? FareClass.Senior : itineraryData.arrivingFlight_fareClass
        }
        return fareClass
    }

    private String[] returnStopsCitiesNames(String[] stopCitiesCodes){
        String [] stopCitiesNames = null
        if(stopCitiesCodes != null){
            stopCitiesNames = new String[stopCitiesCodes.length]
            for (int i=0; i < stopCitiesCodes.length && !stopCitiesCodes[i]?.equals(""); i++) {
                Station station = Enum.valueOf(Station.class,stopCitiesCodes[i])
                stopCitiesNames[i] = station.getCityName()
            }
        }
        return stopCitiesNames
    }

    def verifyChangePlaneInfo(){
        String changePlaneCityCode = itineraryData.outboundConnectingStation
        super.verifyChangePlaneInfo(CHANGE_PLANE_CITIES_INFO, changePlaneCityCode)
    }

    def verifyLinkSwaVacationButton(String fareType) {
        def webDriver = webDriver()
        Actions actions = new Actions(webDriver)
        actions.moveToElement(webDriver.findElement(FLIGHT_PLUS_HOTEL_BUTTON)).perform()
        String prefFlightInfo = waitForElement(PREF_FLIGHT_INFO).getAttribute("value")
        String outboundFlightNumber =  padFlightNumber("${itineraryData.departingFlight_number}")
        String inboundFlightNumber =  padFlightNumber("${itineraryData.returningFlight_number}")
        prefFlightInfo.contains("WN" + outboundFlightNumber + "${BookingCode.from(itineraryData.departingFlight_fareClass).bookingClassCode()}${fareType}").shouldBe true, "The outbound flight info was not found or was invalid"
        prefFlightInfo.contains("WN" + inboundFlightNumber + "${BookingCode.from(itineraryData.arrivingFlight_fareClass).bookingClassCode()}${fareType}").shouldBe true, "The inbound flight info was not found or was invalid"
    }

    private def padFlightNumber(def flightInfo) {
        for (def x = 0; x < 4 - flightInfo.length(); x++) {
           flightInfo = 0 + flightInfo
        }
        return flightInfo
    }

    private def verifyHeadersPricesAreConsistentFor(WebElement[] headers, WebElement[] fares) {
        Integer minFare = new Integer(headers[0].getText().split("-")[0].substring(1).trim())
        Integer maxFare = new Integer(headers[0].getText().split("-")[1].substring(2).trim())
        Integer min = minFare
        Integer max = maxFare
        fares.each {
            min = (min > new Integer(it.getText().substring(1).trim())) ? new Integer(it.getText().substring(1).trim()):min
            max = (max < new Integer(it.getText().substring(1).trim())) ? new Integer(it.getText().substring(1).trim()):max
        }
        (maxFare < max).shouldBe false,"The maximum base fare is wrong"
        (minFare > min).shouldBe false,"The minimum base fare is wrong"
    }

    void verifyModifySearchWidgetIsCollapsed() {
        verifyElementNotPresent("Modify Search Widget Collapased", (By.xpath("//h6[@class='modifySearchTitleText modifySearchTitleTextExpanded']")))
    }

    def verifyTripTypeIsCorrect() {
        if (itineraryData.isRoundTripOrOpenJaw()) {
            waitForElement(ROUND_TRIP_RADIO_BUTTON).getAttribute("checked").shouldBe "true", "RoundTrip was not checked on the Select Flights Page"
        } else {
            waitForElement(ONE_WAY_RADIO_BUTTON).getAttribute("checked").shouldBe "true", "One-way radio button was not checked on the Select Flights Page"
        }
    }

    def verifyStations() {
        waitForElement(DEPARTURE_STATION).getAttribute("value").shouldContain itineraryData.departureStation, "Departure station was not found on the select flights page"
        waitForElement(ARRIVAL_STATION).getAttribute("value").shouldContain itineraryData.arrivalStation, "Arrival station was not found on the select flights page"
        if (itineraryData.itineraryType == itineraryData.OPEN_JAW_ITINERARY) {
            waitForElement(RETURN_STATION).getAttribute("value").shouldContain itineraryData.returnStation, "Return station was not found on the select flights page"
        }
    }

    def verifyDates() {
        waitForElement(OUTBOUND_DATE).getAttribute("value").shouldContain itineraryData.departureDate.format("MM/dd/yyyy"), "Departure date did not match date searched"
        if (itineraryData.isRoundTripOrOpenJaw()) {
            waitForElement(RETURN_DATE).getAttribute("value").shouldContain itineraryData.returnDate.format("MM/dd/yyyy"), "Return date did not match date searched"
        } else if (itineraryData.isOneWay()) {
            waitForElement(RETURN_DATE).getAttribute("disabled").shouldBe "true", "Return date was not disabled"
        }
    }

    def verifyPassengers()
    {
        waitForElement(ADULT_PASSENGER_COUNT).getAttribute("value").toInteger().shouldEqual passengerData.adults.size(), "Adult Passenger Count did not match count entered"
        if (passengerData.containsSeniorPassenger())
        {
            waitForElement(SENIOR_PASSENGER_COUNT).getAttribute("value").toInteger().shouldEqual passengerData.seniors.size(), "Senior Passenger Count did not match count entered"
        }
    }

    public def verifyPromoCertNotificationContainer() {
        checkNotificationServed()
    }

    def verifyBSColumn(){
        waitForElement(OUTBOUND_HEADER_BUSINESSSELECT_PRICE,false).equals(null).shouldBe false,"Business Select Fare column is not displayed"
    }

    def clickOnPoints() {
        waitForElement(FARE_POINTS_OPTION).click()
    }

    def isStrikeThroughRedemptionPointsDisplayed() {
        waitForElement(STRIKE_THROUGH_REDEMPTION_POINTS).getText().equals("").shouldBe false, "Strike through is not displayed for discount"
    }

    def boolean isBlueInformationalAlertDisplayed() {
        return waitForElement(BLUE_INFORMATIONAL_ALERT_MESSAGE).isDisplayed()
    }

    def verifyFareTypesNames() {
        waitForElement(OUTBOUND_HEADER_BUSINESS_SELECT).getAttribute("title").shouldBe "Business Select", "Business Select was not displayed on the BUG page"
        waitForElement(OUTBOUND_HEADER_ANYTIME).getAttribute("title").shouldBe "Anytime", "Anytime was not displayed on the BUG page"
        waitForElement(OUTBOUND_HEADER_WANNA_GET_AWAY).getAttribute("title").shouldBe "Wanna Get Away", "Wanna Get Away was not displayed on the BUG page"
        if(passengerData.containsSeniorPassenger()) {
            waitForElement(OUTBOUND_HEADER_SENIOR).getAttribute("title").shouldBe "Senior", "Senior was not displayed on the BUG page"
        }
        if(!itineraryData.oneWay) {
            waitForElement(INBOUND_HEADER_BUSINESS_SELECT).getAttribute("title").shouldBe "Business Select", "Business Select was not displayed on the BUG page"
            waitForElement(INBOUND_HEADER_ANYTIME).getAttribute("title").shouldBe "Anytime", "Anytime was not displayed on the BUG page"
            waitForElement(INBOUND_HEADER_WANNA_GET_AWAY).getAttribute("title").shouldBe "Wanna Get Away", "Wanna Get Away was not displayed on the BUG page"
        }
    }

    void verifyCalendarCarouselContainsTenDates() {
        int datesCalendarShouldHave = 11
        List <WebElement> departDates = waitForElement(By.id("newDepartDateCarousel")).findElements(CALENDAR_CAROUSEL_DATES)
        departDates.size().shouldBe datesCalendarShouldHave, "BUG page depart calendar carousel did not contain ${datesCalendarShouldHave} days"
        if(itineraryData.isRoundTripOrOpenJaw()) {
            List <WebElement> returnDates = waitForElement(By.id("newReturnDateCarousel")).findElements(CALENDAR_CAROUSEL_DATES)
            returnDates.size().shouldBe datesCalendarShouldHave, "BUG page return calendar carousel did not contain ${datesCalendarShouldHave} days"
        }
    }

    def verifySectionHeaders() {
        String outbound_header = itineraryData.departureCity + " to " + itineraryData.arrivalCity


        waitForElement(OUTBOUND_RESULTS).text.shouldContain outbound_header, "The cities in the outbound header were not displayed correctly"
        if(itineraryData.roundTrip)
        {
            String inbound_header = itineraryData.arrivalCity +" to " + itineraryData.departureCity
            waitForElement(INBOUND_RESULTS).text.shouldContain inbound_header, "The cities in the inbound header were not displayed correctly"
        }else if(itineraryData.openJaw)
        {
            itineraryData.returnCity = waitForElement(RETURN_STATION).getAttribute("value").split(" -")[0]
            StringBuilder inbound_header = new StringBuilder()
            inbound_header.append(itineraryData.arrivalCity).append(" to ").append(itineraryData.returnCity)
            waitForElement(INBOUND_RESULTS).text.shouldContain inbound_header.toString(), "The cities in the inbound header were not displayed correctly"
        }
    }

    void verifyCalendarCarouselDayOfTravelEnabledOnly() {
        int datesCalendarDisabled = 10
        List <WebElement> dates = waitForElements(CALENDAR_CAROUSEL_DATES_DISABLE)
        dates.size().shouldBe datesCalendarDisabled, "BUG page calendar carousel did not contain ${datesCalendarDisabled} days disabled"
        isElementPresent(CALENDAR_CAROUSEL_DATE_ELIGIBLE).shouldBe true, "Date of travel was not present in the BUG page"
    }

    def verifyAdultBasicPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify Adult BUG page) ---- checking no oops"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify Adult BUG page) ---- checking no oops"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["SelectFlightsPage"])
        waitForElement(SENIOR_MESSAGE).text.shouldContain "Select Flight and Fare For Adult Passenger"
    }

    def verifyReturnCityDisabled() {
        waitForElement(RETURN_STATION).getAttribute("disabled").shouldBe "true", "Return city was not disabled"
    }

    def verifyDollarFareIsNotPresent() {
        isElementPresent(TOGGLE_FOR_FARE_IN_DOLLARS).shouldBe false, "Dollar fare was present in the adult Bug page"
    }

    def verifySeniorBasicPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify Senior BUG page) ---- checking no oops"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify Senior BUG page) ---- checking no oops"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["SelectFlightsPage"])
        if (passengerData.containsSeniorAndAdultPassengers()) {
            waitForElement(SENIOR_MESSAGE).text.shouldContain "Select Fare For Senior Passenger"
        } else {
            waitForElement(SENIOR_MESSAGE).text.shouldContain "Select Senior Fare (age 65+)"
        }

    }

    def verifySearchWidgetIsNotPresent() {
        isElementPresent(MODIFY_DATES_FORM).shouldNotBe true, "Search widget was present"
    }

    def verifySeniorFlightNumberEqualToAdultFlightNumber() {
        waitForElement(SENIOR_FLIGHT_NUMBER).text.shouldBe selectFlightsPageData.departingFlight_number, "Senior flight number is different from adult flight number"
    }

    def verifySeniorDepartureAndArrivalTime() {
        List<WebElement> flightTimes = waitForElements(SENIOR_FLIGHT_TIMES)
        flightTimes[0].text.shouldBe selectFlightsPageData.outboundDepartureTime, "Adult departure time was not equal to senior departure time"
        flightTimes[1].text.shouldBe selectFlightsPageData.outboundArrivalTime, "Adult arrival time was not equal to senior departure time"
    }

    def verifySeniorTravelTime() {
        waitForElement(SENIOR_TRAVEL_TIME).text.shouldBe selectFlightsPageData.outboundTravelTime, "Senior Travel time is not equal to Adult Travel time"
    }

    def verifySeniorFlightDate() {
        waitForElement(SENIOR_CAROUSEL_DEPART_DATE).getAttribute("carouselfulldate").shouldBe itineraryData.departureDate.format("yyyy/MM/dd"), "Senior Travel date is not equal to Adult Travel date"
    }

    def verifySeniorRouting() {
        waitForElement(ROUTING).text.shouldBe selectFlightsPageData.outboundRouting, "Senior Travel routing is not equal to Adult Travel routing"
    }

    def verifySeniorFareAvailable() {
        selectFlightsPageData.outboundSeniorPrice.shouldBe "Available", "Senior fare was not available for the selected flight in the Adult Bug Page"
    }

    def verifyOneFlightAvailableForSenior() {
        waitForElements(FLIGHT_TABLE_ROW).size().shouldEqual 1, "There was not one flight available in the senior Bug page"
    }

    def saveOutboundRouteTypeAndDetails() {
        BugRoutingFlyout flyout = openFlightDetailFlyout(selectFlightsPageData.selectedOutboundFlightDetailsLink)
        selectFlightsPageData.outboundFlyoutRouting = flyout.retrieveRouting()
        selectFlightsPageData.outboundFlyoutDeparure = flyout.retrieveOutboundDeparture().split(",")[1].split("\\(")[1].substring(0,3)
        selectFlightsPageData.outboundFlyoutArrival = flyout.retrieveOutboundArrival().split(",")[1].split("\\(")[1].substring(0,3)
        if(flow.hasConnectionFlight) {
            selectFlightsPageData.outboundConnectingStation = itineraryData.outboundConnectingStation = flyout.retrieveOutboundConnectionDeparture().substring(flyout.retrieveOutboundConnectionDeparture().length()-4, flyout.retrieveOutboundConnectionDeparture().length()).replace(")","")
            selectFlightsPageData.outboundDepartureTime = flyout.retrieveOutboundDepartureTime()
            selectFlightsPageData.outboundArrivalTime = flyout.retrieveOutboundArrivalTime()
            selectFlightsPageData.outboundConnectionDepartureTime = flyout.retrieveOutboundConnectionDepartureTime()
            selectFlightsPageData.outboundConnectionArrivalTime = flyout.retrieveOutboundConnectionArrivalTime()
            selectFlightsPageData.departingConnectingFlight_number = flyout.retrieveOutboundConnectionFlight().replaceAll("#","")
            selectFlightsPageData.outboundConnectingCity = flyout.retrieveOutboundArrival().split(",")[0]
        }
    }

    def openFlightDetailFlyout(WebElement link) {
        link.click()
        return new BugRoutingFlyout(waitForElement(OUTBOUND_FLYOUT))
    }


    def saveInboundRouteTypeAndDetails() {
        BugRoutingFlyout flyout = openFlightDetailFlyout(selectFlightsPageData.selectedInboundFlightDetailsLink)
        selectFlightsPageData.inboundFlyoutRouting = flyout.retrieveRouting()
        selectFlightsPageData.inboundFlyoutDeparure = flyout.retrieveOutboundDeparture()
        selectFlightsPageData.inboundFlyoutArrival = flyout.retrieveOutboundArrival()
        if (flow.hasInboundConnectionFlight){
            selectFlightsPageData.inboundConnectingStation = itineraryData.inboundConnectingStation = flyout.retrieveOutboundConnectionDeparture().substring(flyout.retrieveOutboundConnectionDeparture().length()-4, flyout.retrieveOutboundConnectionDeparture().length()).replace(")","")
            selectFlightsPageData.returningConnectingFlight_number = flyout.retrieveOutboundConnectionFlight().replaceAll("#","")
            selectFlightsPageData.inboundDepartureTime = flyout.retrieveOutboundDepartureTime()
            selectFlightsPageData.inboundArrivalTime = flyout.retrieveOutboundArrivalTime()
            selectFlightsPageData.inboundConnectionDepartureTime = flyout.retrieveOutboundConnectionDepartureTime()
            selectFlightsPageData.inboundConnectionArrivalTime = flyout.retrieveOutboundConnectionArrivalTime()
            selectFlightsPageData.inboundConnectingCity = flyout.retrieveOutboundArrival().split(",")[0]
        }
    }

    def verifyDateSelectedIsHighlighted() {
        List<String> dateInPage = waitForElement(CALENDAR_CAROUSEL_DEPART_DATE_HIGHLIGHTED).text.split('\n')
        List<String> expectedDate = itineraryData.departureDate.toString().split(' ')
        String expectedDay = expectedDate[0].toUpperCase()
        String expectedMonth = expectedDate[1].toUpperCase()
        String expectedNumber = expectedDate[2]
        DecimalFormat dayNumberFormat = new DecimalFormat("##")
        dateInPage[0].shouldBe expectedMonth, "The month in depart date was incorrect in the higlighted calendar in bug page"
        dateInPage[1].shouldEqual dayNumberFormat.format(expectedNumber.toBigDecimal()), "The number of the day in depart date was incorrect in the higlighted calendar in bug page"
        dateInPage[2].shouldBe expectedDay, "The day in depart date was incorrect in the higlighted calendar in bug page"
        if(itineraryData.isRoundTripOrOpenJaw()) {
            dateInPage = waitForElement(CALENDAR_CAROUSEL_RETURN_DATE_HIGHLIGHTED).text.split('\n')
            expectedDate = itineraryData.returnDate.toString().split(' ')
            expectedDay = expectedDate[0].toUpperCase()
            expectedMonth = expectedDate[1].toUpperCase()
            expectedNumber = expectedDate[2]
            dateInPage[0].shouldBe expectedMonth, "The month in return date was incorrect in the higlighted calendar in bug page"
            dateInPage[1].shouldEqual dayNumberFormat.format(expectedNumber.toBigDecimal()), "The number in return date of the day was incorrect in the higlighted calendar in bug page"
            dateInPage[2].shouldBe expectedDay, "The day in return date was incorrect in the higlighted calendar in bug page"
        }

    }

    def verifyDiscountedPromotionalMessage() {
        isElementPresent(DISCOUNTED_PROMOTIONAL_MESSAGE).shouldBe true, "Discounted promotional message was not present in the select flight page"
    }

    def verifyPromoCodePrepopulated() {
        waitForElement(PROMOCODE).getAttribute('value').shouldBe itineraryData.promoCode, "Promocode was not prepopulated correctly in BUG page"
    }
}
