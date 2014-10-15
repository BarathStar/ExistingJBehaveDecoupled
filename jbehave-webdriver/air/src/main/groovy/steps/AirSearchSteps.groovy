package steps

import com.swacorp.common.utility.SwaUtils
import com.swacorp.dotcom.webscenarios.air.Data
import org.openqa.selenium.By
import pages.elements.BookATripModal
import pages.elements.FlightSearchForm
import pages.elements.HomePageFlightSearchForm
import pages.elements.RapidRewardsAccountBar
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

class AirSearchSteps {

    private final static HOME_PAGE_SUBMIT_BUTTON = "booking_widget_content_row_btn_search"
    private final static TRIP_SEARCH_MODAL_SUBMIT_BUTTON = "tripSearchModalSubmit"

    SearchFlightsPage searchFlightsPage
    AccountSteps accountSteps
    SelectFlightsPage selectFlightsPage
    AirTranRedirectModal airTranRedirectModal
    FlightSearchForm flightSearchForm
    BookATripModal bookATripModal
    HomePageFlightSearchForm homePageFlightSearchForm
    OutboundAndReturnDatesAndPopUp calendarPopUp
    ItineraryData itineraryData
    Itinerary itinerary
    InternationalSearchFlightsPage internationalSearchFlightsPage
    ViewReservationPage viewReservationPage
    Data data
    PassengerData passengerData
    HomePage homePage
    Flow flow
    SwaBizData swabiz_data
    PromoDiscountData discountData
    CustomerInfoData customerInfoData
    RapidRewardsAccountBar rapidRewardsAccountBar

    @Given("I am a customer on the select flights page traveling from \$departure to \$arrival")
    @Alias("I am a customer looking for flights from \$departure to \$arrival")
    def openAndSearchForRoundTrip(@Named("departure") String departureStation, @Named("arrival") String arrivalStation) {
        searchFlightsPage.open()
        itineraryData.departureStation = departureStation
        itineraryData.arrivalStation = arrivalStation
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @Given("I am on the Select Flight page for \$numberOfPsgrs \$pasgrType passenger(s)")
    @When("I am on the Select Flight page for \$numberOfPsgrs \$pasgrType passenger(s)")
    def searchFlightsForPassengers(@Named("numberOfPsgrs") int passengers, @Named("pasgrType") String passengerAgeRange) {
        searchFlightsPage.searchFlightsForPassengers(passengers, passengerAgeRange)
    }

    @Given("I am a customer searching for round-trip flights with points")
    @When("I am a customer searching for round-trip flights with points")
    openSearchFlightsPageAndSelectFareInPoints() {
        searchFlightsPage.open()
        flightSearchForm.chooseFaresInPoints()
        flightSearchForm.searchForRoundTrip(itineraryData)
    }

    @Given("I am a customer searching for a flights with points")
    openSearchFlightPageAnSelectAFareInPoints() {
        searchFlightsPage.open()
        flightSearchForm.chooseFaresInPoints()
        flightSearchForm.searchForFlight(itineraryData)
    }

    @Given("We are three minors traveling alone")
    def addThreeMinorPassengers() {
        passengerData.addPassengers([PassengerType.MINOR,PassengerType.MINOR,PassengerType.MINOR])
    }

    @Given("I am anonymous user that requires Airport Checkin")
    void addAirportCheckinPassenger() {
        passengerData.addAirportCheckinRequiredPassenger()
        flow.hasAirportCheckinRequiredPassenger = true
    }

    @Given("I am an \$userType Rapid Rewards member traveling with an adult and a passenger who requires Airport Checkin")
    def addAnAdultAndAirportCheckinPassengers(String userType) {
        accountSteps.openSearchFlightsAndLogIntoAccount(userType)
        passengerData.addAdultPassenger(2)
        addAirportCheckinPassenger()
    }

    void currencyType(Closure currencyType) {
        searchFlightsPage.open()
        currencyType.call()
    }

    @Given("I am on the Search Flight page")
    def viewSearchFlightPage() {
        searchFlightsPage.open()
    }

    @Given("I am on the Select Flight page")
    @When("I am on the Select Flight page")
    def viewSelectFlightPage() {

        viewSelectFlightPage({})
    }

    @Given("I am on the Home page, fill in itinerary data and search for flights")
    def searchFromHomePage() {
        homePage.open()
        homePage.fillInHomePageSearchDataAndSubmit(itineraryData, false)
    }

    void viewSelectFlightPage(Closure passengerClosure) {
        searchFlightsPage.open()
        passengerClosure.call()

        itineraryData.departureStation = data.getRoute().from
        itineraryData.arrivalStation = data.getRoute().to

        flightSearchForm.fillInAirports(data.getRoute().from, data.getRoute().to)

        itineraryData.verifyValidDates()
        flightSearchForm.enterDepartureDate(itineraryData.departureDate)
        flightSearchForm.enterReturnDate(itineraryData.returnDate)

        flightSearchForm.submit()
    }

    @Given("I am on the select flights page")
    @When("I am on the select flights page")
    def viewSelectFlightsPage() {
        searchFlightsPage.open()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectFlightsPage.verifyPage()
    }

    @Given("I am a customer looking for round-trip flights")
    def searchForRoundTrip() {
        flightSearchForm.searchForRoundTrip(itineraryData)
    }

    @Given("I am using Promo Code \$promoCode")
    def enterSpecifiedPromoCode(String promoCode) {
        new DynaStubsIntegration().prepareDiscount(discountData.getDiscount(promoCode))
    }

    @Given("I am using a dollars off promo code")
    def enterDollarsOffPromoCode() {
         enterSpecifiedPromoCode(swabiz_data.getDiscountCode("SB_all_promo_dollarsOff"))
    }

    @Given("I am using a percent off promo code")
    def enterPercentOffPromoCode() {
         enterSpecifiedPromoCode(swabiz_data.getDiscountCode("SB_all_promo_percentOff"))
    }

    @Given("I am not using a promo code")
    def clearPromoCode() {
        flightSearchForm.clearPromoCode()
    }

    def searchForRoundTrip(String from, String to) {
        flightSearchForm.fillInAirports(from, to)
        Map<String, Date> dateMap = ItineraryDateFactory.getRoundTripDates()
        flightSearchForm.enterDepartureDate(dateMap.get("departureDate"))
        flightSearchForm.enterReturnDate(dateMap.get("returnDate"))
        flightSearchForm.submit()
    }

    @When("I click submit on the Vacations tab on the booking widget")
    def searchForVacations() {
        homePage.vacationsSubmit(false)
    }

    @Given("I have reached the calendar date limit on the \$namePage page")
    @When("I reach the calendar date limit on the \$namePage page")
    def clickCalendarCalendarLimit(String namePage){
        calendarPopUp.getCalendarMaxDateLimit(namePage)
    }

    @Given("I have reached the calendar date limit at \$widget widget in the \$namePage page")
    def clickCalendarCalendarLimit(String widget,String namePage){
        calendarPopUp.getCalendarMaxDateLimit(namePage,widget)
    }

    @Given("I have one adult and senior selected on the select flight page")
    def bookAFlightAdultAndSenior() {
        searchFlightsPage.open()
        flightSearchForm.selectAdultAndSeniorPassengers(1, 1)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @Given("I search for a one-way flight for mixed pax")
    def searchForOneWayFlightForMixedPax() {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.oneway.departAirport
        itineraryData.arrivalStation= yaml.oneway.arriveAirport
        itineraryData.departingFlight_fareClass = "Anytime"
        itineraryData.seniorDepartingFlight_fareClass = "Senior"
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.itineraryType = itineraryData.ONE_WAY_ITINERARY
        itineraryData.homePage = true
        passengerData.addAdultPassenger()
        passengerData.addSeniorPassenger()
        fillInFlightSearchInfoAndClick()
        flow.hasAir = true;
    }


    def addDatesAndAddAPassengerWhoHaveHisBirthdayOnFlight(Integer years) {
        itineraryData.departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate()
        itineraryData.returnDate = ItineraryDateFactory.getAnyAvailableReturnDateAfter(itineraryData.departureDate.plus(1))
        Date birthDate = itineraryData.returnDate.clone()
        birthDate[YEAR] = birthDate[YEAR] - years
        birthDate[DAY_OF_MONTH] = birthDate[DAY_OF_MONTH] - 1
        passengerData.addPassengerWithBirthDay(birthDate)
    }

    @Given("I am an infant on the depart and a minor on the return")
    def addAnInfantOnDepartAndAMinorOnReturn() {
        addDatesAndAddAPassengerWhoHaveHisBirthdayOnFlight(5)
    }

    @Given("I am an minor on the depart and a young traveler on the return")
    def addAMinorOnDepartAndAYoungTravelerOnReturn() {
        addDatesAndAddAPassengerWhoHaveHisBirthdayOnFlight(12)
    }

    @Given("I am a minor traveling alone")
    @Alias("I am a Minor traveling with a companion")
    def addAMinorPassenger() {
        passengerData.addPassengers([PassengerType.MINOR])
    }

    @Given("I am a YT traveling with a minor")
    def addAMinorAndAYoungTravelerPassengers() {
        passengerData.addPassengers([PassengerType.MINOR, PassengerType.YT])
    }

    @Given("I am an infant traveling with a minor")
    def addAMinorAndAnInfantPassengers() {
        passengerData.addPassengers([PassengerType.INFANT, PassengerType.MINOR])
    }

    @Given("I am a YT traveling with an infant")
    @Alias("I am an infant traveling with a young traveler")
    def addAnInfantAndAYoungTravelerPassengers() {
        passengerData.addPassengers([PassengerType.INFANT, PassengerType.YT])
    }

    @Given("I am an adult traveling with an infant, a minor and a YT")
    def addAnAdultAnInfantAMinorAndAYoungTravelerPassengers() {
        passengerData.addPassengers([PassengerType.ADULT, PassengerType.INFANT, PassengerType.MINOR, PassengerType.YT])
    }

    @Given("I am a YT traveling alone")
    def addAYoungTravelerPassenger() {
        passengerData.addPassengers([PassengerType.YT])
    }

    @Given("I am an infant traveling alone")
    @Alias("I am an Infant traveling with a companion")
    def addAnInfantPassenger() {
        passengerData.addPassengers([PassengerType.INFANT])
    }

    @Given("I am an senior traveling alone")
    def addASeniorPassenger() {
        passengerData.addPassengers([PassengerType.SENIOR])
    }

    @Given("I am a customer searching for round-trip flights from the home page")
    @When("I am a customer searching for round-trip flights from the home page")
    def searchForRoundTripFlightsFromHomePage() {
        homePage.open()
        homePageFlightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true, HOME_PAGE_SUBMIT_BUTTON)
    }

    @Given("I am a customer searching for round-trip flights from the book a flight widget")
    @When("I am a customer searching for round-trip flights from the book a flight widget")
    def searchForRoundTripFlightsFromFlightWidget() {
        bookATripModal.fillInFlightSearchInfoAndClick(itineraryData, true, TRIP_SEARCH_MODAL_SUBMIT_BUTTON)
    }


    @Given("I am a customer searching for round-trip flights from the search flights page")
    @When("I am a customer searching for round-trip flights from the search flights page")
    def searchForRoundTripFlightsFromFlightSearchPage() {
        searchFlightsPage.open()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @Given("I am a customer searching for one-way flights from the home page")
    @When("I am a customer searching for one-way flights from the home page")
    def searchForOneWayFlightsFromHomePage() {
        homePage.open()
        homePageFlightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @Given("I am a customer searching for one-way flights from the international search flights page")
    @When("I am a customer searching for one-way flights from the international search flights page")
    def searchForOneWayFlightsFromSearchFlightsPage() {
        internationalSearchFlightsPage.open()
        enterInternationalFlightData()
    }

    @Given("I am a customer searching for one-way flights from the book a flight widget")
    @When("I am a customer searching for one-way flights from the book a flight widget")
    @Alias("I am searching for one-way flights from the book a flight widget")
    def searchForOneWayFlightsFromFlightWidget() {
        bookATripModal.fillInFlightSearchInfoAndClick(itineraryData, true, TRIP_SEARCH_MODAL_SUBMIT_BUTTON)
    }

    @Given("I am a customer searching for one-way flights from the search flights page")
    @When("I am a customer searching for one-way flights from the search flights page")
    def searchForOneWayFlightsFromFlightSearchPage() {
        searchFlightsPage.open()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @Given("I have selected an adult passenger type")
    @When("I select an adult passenger type")
    def selectAdultPassengerType() {
        flightSearchForm.selectAdultAndSeniorPassengers(1, 0)
    }

    @When("I am booking a round-trip flight")
    def searchForRequiredFlights() {
        openAndSearchForRoundTrip(itineraryData.departureStation, itineraryData.arrivalStation)
    }

    @When("I click on manage reservation link")
    void clickManageReservationLink() {
        searchFlightsPage.clickManageReservationLink()
    }

    @When("I click on add Rapid Rewards from the View Itinerary Page")
    void viewAddRapidRewardsNumberPage() {
        viewReservationPage.clickAddRapidRewardsNumber()
    }

    @When("I enter departure and arrival stations")
    def openAndSelectAirports() {
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        searchFlightsPage.pressTabInField(By.id(ELEMENT_IDS.get("To")))
    }

    @When("I am searching for flights from <departure> to <arrival> from home page")
    @Alias("I am searching for flights from \$departure to \$arrival from home page")
    def searchForFlightsFromHomePage(@Named("departure") String from, @Named("arrival") String to) {
        homePage.open()
        flightSearchForm.fillInAirports(from, to)
        flightSearchForm.enterDepartureDate()
    }

    @When("I view nearby airports")
    def viewNearByAirports() {
        selectFlightsPage.selectViewNearByAirports()
    }

    @When("I select \$amountOfAdults adults on the search page")
    @Alias("I select \$amountOfAdults adult on the search page")
    def selectAdultPassenger(int amountOfAdults) {
        searchFlightsPage.open()
        flightSearchForm.verifyPassengerCount(amountOfAdults).shouldBe true, "Adult passenger count: " + amountOfAdults + " is not between 0 and 8"
        flightSearchForm.fillInAdult(amountOfAdults)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @When("I select a one-way trip from <from_code> to <to_code> on the plan trip page")
    @Alias("I select a one-way trip from \$from_code to \$to_code on the plan trip page")
    def searchForOneWayTrip(@Named("from_code") String from, @Named("to_code") String to) {
        itineraryData.departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate()
        itineraryData.returnDate = itineraryData.departureDate.plus(1)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @When("I search for an open-jaw international trip from \$from_code to \$to_code to \$return_code")
    def selectOpenJawInternationalTrip(@Named("from_code") String origin, @Named("to_code") String destination, @Named("return_code") String returnStation) {
        searchFlightsPage.clickRoundTrip()
        searchFlightsPage.clickAddAnotherFlight()
        flightSearchForm.fillInAirports(origin, destination, returnStation)
        flightSearchForm.submit();
    }
    @When("I search for OpenJaw flights from the flight search page")
    def searchForOpenJawFlights() {
        searchFlightsPage.open()
        searchForOpenJaw()
    }

    @Given("I search for an OpenJaw flight")
    void searchForOpenJawFlight(){
        searchForOpenJaw();
    }

    void searchForOpenJaw() {
        searchFlightsPage.clickRoundTrip()
        searchFlightsPage.clickAddAnotherFlight()
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation, itineraryData.returnStation)
        flightSearchForm.fillInFlightDates(itineraryData)
        flightSearchForm.submit();
    }
    def selectOpenJawTrip() {
        openJawTripSelection(1)
    }

    @Given("I search for an open-jaw flights from \$from_code to \$to_code returning to \$return_code")
    @When("I am a customer searching for open-jaw flights from \$departureStation to \$arrivalStation to \$returnStation")
    def selectOpenJawTripForCities(String departureStation, String arrivalStation, String returnStation) {
        itineraryData.departureStation = departureStation
        itineraryData.arrivalStation = arrivalStation
        itineraryData.returnStation = returnStation
        itineraryData.departingFlight_carrierCode = "WN"
        itineraryData.arrivingFlight_carrierCode = "WN"
        itineraryData.returningFlight_carrierCode = "WN"
        itineraryData.departingFlight_fareClass = "Anytime"
        itineraryData.arrivingFlight_fareClass = "Anytime"
        openJawTripSelection(1)
    }

    @Given("I search for a \$roundTripOnwWay air ticket from \$from to \$from")
    void selectTypeOfItinerary(String roundTripOnwWay, String from, String to) {
        itineraryData.departureStation = from;
        itineraryData.arrivalStation = to;
        itineraryData.departingFlight_carrierCode = "WN";
        itineraryData.arrivingFlight_carrierCode = "WN";
        itineraryData.returningFlight_carrierCode = "WN";
        itineraryData.departingFlight_fareClass = "Anytime";
        itineraryData.arrivingFlight_fareClass = "Anytime";
        if(roundTripOnwWay.toLowerCase() == "roundtrip") {
           itineraryData.itineraryType = itineraryData.ROUND_TRIP_ITINERARY
        }
        passengerData.addAdultPassenger(1);
        flow.hasAir = true
    }

    def openJawTripSelection(int numberOfPax) {
        searchFlightsPage.clickRoundTrip()
        searchFlightsPage.clickAddAnotherFlight()
        passengerData.addAdultPassenger(numberOfPax)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    def openJawTripSelectionUsingDatesFromItineraryData(int numberOfPax) {
        searchFlightsPage.clickRoundTrip()
        searchFlightsPage.clickAddAnotherFlight()
        flightSearchForm.selectAdultAndSeniorPassengers(numberOfPax, 0)
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation, itineraryData.returnStation)
        flightSearchForm.saveStations()
        flightSearchForm.fillInFlightDates(itineraryData, true)
        flightSearchForm.submit()
    }

    @When("I renter international flight data")
    public void reEnterInternationalFlightData() {
        enterInternationalFlightData()
    }

    private void enterInternationalFlightData() {
        internationalSearchFlightsPage.searchForInternationalFlight(itineraryData)
    }

    @When("I select a one-way trip from \$from_code to \$to_code in the modal window")
    def selectOneWayTripOnHomePage(@Named("from_code") String origin, @Named("to_code") String destination) {
        itineraryData.departureStation = origin
        itineraryData.arrivalStation = destination
        itineraryData.itineraryType = "One Way"
        bookATripModal.fillInFlightSearchInfoAndClick(itineraryData, true, TRIP_SEARCH_MODAL_SUBMIT_BUTTON)
    }

        @When("I select the Senior passenger type")
    def selectSeniorPassengerType() {
        flightSearchForm.selectAdultAndSeniorPassengers(0, 1)
    }

    @When("I click to continue from the AirTran Redirect Modal")
    @Alias("I click to continue from the Web Referral Redirect Modal")
    def clickContinueOnAirTranRedirectModal() {
        airTranRedirectModal.clickContinueButton()
    }

    @When("I click to continue on the Web Referral Redirect Modal from the select flights page")
    void clickContinueOnAirTranRedirectModalFromSelectFlightsPage(){
        airTranRedirectModal.clickContinueButtonOnModalFromSelectFlightsPage()
    }

    @When("I click cancel on the AirTran Redirect Modal")
    @Alias("I click cancel on the Web Referral Redirect Modal")
    def cancelOnAirTranRedirectModal() {
        airTranRedirectModal.clickCancelButton()
    }

    def fillFromToForTodayOrTomorrowReturnIn3DaysAndClick(String earlyBirdOnly = "none") {
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        def departureDate
        if (earlyBirdOnly == 'EB') {
            departureDate = ItineraryDateFactory.getAnyAvailableDepartureDateForEarlyBird()
        } else {
            departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate()
        }
        flightSearchForm.enterDepartureDate(departureDate)
        flightSearchForm.submit()
    }

    @When("The Return field is visible")
    @Alias("The Return field is enabled")
    def returnFieldVisible() {
        flightSearchForm.makeReturnAirportFieldVisible()
    }

    @When("The Return field is not available")
    void returnFieldIsNotAvailable() {
        flightSearchForm.verifyReturnFieldIsDisabled()
    }

    @When("I select One-Way radio button")
    void selectOneWayButton() {
        selectFlightsPage.clickOneWay()
    }

    @When("I click the airTran redirect modal cancel button")
    void clickCancelButton() {
        airTranRedirectModal.clickCancelButton()
    }

    @When("I click where we fly link on the search page")
    def clickWhereWeFlyLinkOnSearchPage() {
        searchFlightsPage.clickWhereWeFlyLinkOnSearchPage()
    }

    @When("I search for flights from the flight search page")
    def searchForFlightsFromFlightSearch(boolean andCreateFlightData, boolean andVerifyNoOops)
    {
        searchFlightsPage.open()
        if (itineraryData.adultPassengerCount != null && itineraryData.seniorPassengerCount != null) {
            flightSearchForm.selectAdultAndSeniorPassengers(itineraryData.adultPassengerCount.toInteger(), itineraryData.seniorPassengerCount.toInteger())
        }
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, andCreateFlightData)
    }

    @When("I successfully search for flights from the flight search page")
    def searchForFlightsFromFlightSearch() {
        searchForFlightsFromFlightSearch(true,true);
    }

    @When("I attempt to search for flights from the flight search page")
    def searchForFlightsFromFlightSearchWithNoVerification() {
        searchForFlightsFromFlightSearch(false,false);
    }

    @When("I attempt to search for flights from the home page")
    def searchForFlightsFromHomePageWithNoVerification() {
        homePage.open()
        if (itineraryData.adultPassengerCount != null && itineraryData.seniorPassengerCount != null) {
            homePageFlightSearchForm.selectAdultAndSeniorPassengers(itineraryData.adultPassengerCount.toInteger(), itineraryData.seniorPassengerCount.toInteger())
        }
        homePageFlightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, false, HOME_PAGE_SUBMIT_BUTTON)
        // homePageFlightSearchForm.fillInFlightSearchInfo(itineraryData, HOME_PAGE_SUBMIT_BUTTON)
    }

    @When("I search for flights that have not been scheduled yet by SouthWest Airlines")
    def searchForFlightsThatHasNotBeenScheduled() {
        searchFlightsPage.open()
        if (itineraryData.adultPassengerCount != null && itineraryData.seniorPassengerCount != null) {
            flightSearchForm.selectAdultAndSeniorPassengers(itineraryData.adultPassengerCount.toInteger(), itineraryData.seniorPassengerCount.toInteger())
        }
        flightSearchForm.fillInFlightSearchInfo(itineraryData)
    }

    @When("I click on routing link for a flight")
    def clickRoutingLink() {
        selectFlightsPage.clickRoutingLink()
    }

    @When("I click fare types")
    def clickFareTypes() {
        selectFlightsPage.clickFareTypes()
    }

    @When("I click on the \$direction flight details link according to my itinerary data")
    def clickOnFlightDetailLinkAccordigMyItineraryData(String direction){
        selectFlightsPage.clickOnValidFlightDetailLink(direction, false)
    }

    @When("I click continue on Select Flight page")
    def clickOnContinueFromFlightPage() {
        selectFlightsPage.clickContinue()
    }

    @When("I wait for \$times seconds to so my flight have less than an hour for the departure time")
    def waitForAMoment(int times) {
        sleep(times*1000)
    }

    @Given("I search for an open-jaw flight")
    def selectOpenJawTripForCities() {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.openjaw.departAirport
        itineraryData.arrivalStation = yaml.openjaw.arriveAirport
        itineraryData.returnStation = yaml.openjaw.returnAirport
        itineraryData.itineraryType = itineraryData.OPEN_JAW_ITINERARY
        itineraryData.departingFlight_carrierCode = "WN"
        itineraryData.arrivingFlight_carrierCode = "WN"
        itineraryData.returningFlight_carrierCode = "WN"
        itineraryData.departingFlight_fareClass = "Anytime"
        itineraryData.arrivingFlight_fareClass = "Anytime"
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.inboundRouting = "Nonstop"
        customerInfoData.formOfContact = "Text"


        flightSearchForm.verifyBasicPage()
        flightSearchForm.verifyTripType()
        flightSearchForm.verifyAddAnotherFlightLink()
        flightSearchForm.verifySeeWhereToFlyLink()
        flightSearchForm.verifyFareRadioButton()
        passengerData.addAdultPassenger(1)
        searchFlightsPage.clickRoundTrip()
        searchFlightsPage.clickAddAnotherFlight()

        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        flow.hasAir = true;
    }

    @Given("I search for an Airtran one-way flight for \$pax_adt_number {adult|adults} with \$fareType fare")
    def selectOneWayCarrierMultipax(@Named("pax_adt_number") int paxAdtNumber,@Named("fareType") String fareType) {
        passengerData.addAdultPassenger(paxAdtNumber)
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.airtran.departAirport
        itineraryData.arrivalStation= yaml.airtran.arriveAirport
        itineraryData.departingFlight_carrierCode = yaml.airtran.carrier
        itineraryData.arrivingFlight_carrierCode = yaml.airtran.carrier
        selectOneWayDefaults(fareType)
    }

    @Given("I search for a one-way flight with \$fareType fare with \$paymentMethod")
    def selectOneWayCitiesWithPayment(@Named("fareType") String fareType, @Named("paymentMethod") String paymentMethod) {
        if(paymentMethod.equals("southwestgiftcard")){
            itineraryData.giftCard = true
        }
        if(paymentMethod.equals("promocode")){
            itineraryData.promoCode = "PODBUGS"
            itineraryData.isValidForWGA = true
        } else {
            customerInfoData.formOfPayment = paymentMethod
        }
        selectOneWayCities(fareType)
    }

    @Given("I search for a one-way flight with \$fareType fare")
    def selectOneWayCities(@Named("fareType") String fareType) {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = setDefaults(itineraryData.departureStation, yaml.oneway.departAirport)
        itineraryData.arrivalStation = setDefaults(itineraryData.arrivalStation, yaml.oneway.arriveAirport)
        selectOneWayDefaults(fareType)
    }

    def selectOneWayDefaults(@Named("fareType") String fareType) {
        itineraryData.departingFlight_fareClass = fareType.replaceAll('\\s+','')
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.itineraryType = itineraryData.ONE_WAY_ITINERARY
        itineraryData.homePage = true
        if(fareType.equals("Wanna Get Away")) {
            itineraryData.isValidForWGA = Boolean.TRUE
        }
        passengerData.addAdultPassenger()
        fillInFlightSearchInfoAndClick()
        flow.hasAir = true;
    }


    @Given("I search for a one-way flight with \$fareType fare valid for Wanna Get Away")
    def selectOneWayCitiesForWGA(@Named("fareType") String fareType) {
        itineraryData.isValidForWGA = Boolean.TRUE
        selectOneWayCities(fareType)
    }

    @Given("I search for a one-way flight for \$pax_adt_number {adult|adults}")
    def searchForOneWayFlight(@Named("pax_adt_number") int paxAdtNumber) {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.oneway.departAirport
        itineraryData.arrivalStation= yaml.oneway.arriveAirport
        itineraryData.departingFlight_fareClass = "Anytime"
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.itineraryType = itineraryData.ONE_WAY_ITINERARY
        itineraryData.homePage = true
        itineraryData.adultPassengerCount = paxAdtNumber
        passengerData.addAdultPassenger(paxAdtNumber)
        fillInFlightSearchInfoAndClick()
        flow.hasAir = true;
    }

    @Given("I search for a one-way flight for \$pax_adt_number {adult|adults} to add disabilities")
    def searchForFlightAndDisabilities(@Named("pax_adt_number") int paxAdtNumber) {
        itineraryData.hasDisabilities = true
        searchForOneWayFlight(paxAdtNumber)
    }

    @Given("I search for a roundtrip flight for \$pax_adt_number adult(s) and mixed fair")
    def searchForOneWayFlightMultipaxMixedFair(@Named("pax_adt_number") int paxAdtNumber) {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.roundtrip.departAirport
        itineraryData.arrivalStation= yaml.roundtrip.arriveAirport
        itineraryData.departingFlight_carrierCode = "WN"
        itineraryData.arrivingFlight_carrierCode = "WN"
        itineraryData.departingFlight_fareClass = "Anytime"
        itineraryData.arrivingFlight_fareClass = "BusinessSelect"
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.itineraryType = itineraryData.ROUND_TRIP_ITINERARY
        itineraryData.isMixedFare = true
        itineraryData.homePage = true

        passengerData.addAdultPassenger(paxAdtNumber)
        fillInFlightSearchInfoAndClick()
        flow.hasAir = true;

    }

    @Given("I search for a one-way flight for \$pax_snr_number senior(s) with \$pax_fare fare")
    def searchForSeniorOneWayFlight(@Named("pax_snr_number") int paxSnrNumber,@Named("pax_fare") String paxFare) {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.oneway.departAirport
        itineraryData.arrivalStation= yaml.oneway.arriveAirport
        itineraryData.departingFlight_fareClass = paxFare
        itineraryData.seniorDepartingFlight_fareClass = paxFare
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.itineraryType = itineraryData.ONE_WAY_ITINERARY
        itineraryData.homePage = true
        itineraryData.seniorPassengerCount = paxSnrNumber

        passengerData.addSeniorPassengers(paxSnrNumber)
        fillInFlightSearchInfoAndClick()
        flow.hasAir = true;
    }

    @Given("I search for a round-trip flight for \$pax_snr_number senior(s) with \$pax_fare fare")
    def searchForSeniorRoundTripFlight(@Named("pax_snr_number") int paxSnrNumber,@Named("pax_fare") String paxFare) {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.roundtrip.departAirport
        itineraryData.arrivalStation= yaml.roundtrip.arriveAirport
        itineraryData.departingFlight_fareClass = paxFare
        itineraryData.arrivingFlight_fareClass = paxFare
        itineraryData.seniorDepartingFlight_fareClass = paxFare
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.inboundRouting = "Nonstop"
        itineraryData.itineraryType = itineraryData.ROUND_TRIP_ITINERARY
        itineraryData.homePage = true
        itineraryData.seniorPassengerCount = paxSnrNumber

        passengerData.addSeniorPassengers(paxSnrNumber)
        fillInFlightSearchInfoAndClick()
        flow.hasAir = true;
    }

    @Given("the Promo Code field is enabled on the Air Search page")
    @Then("I see Promo Code field is enabled on the Air Search page")
    def promoCodeIsEnabled() {
        flightSearchForm.promoCodeIsEnabled()
    }

    @Given("the Senior Passenger Count field is enabled on the Air Search page")
    def seniorPassengerCountFieldIsEnabled() {
        flightSearchForm.seniorPassengerCountIsEnabled()
    }

    @Then("I see Senior Passenger Count field is disabled on the Air Search page")
    def seniorPassengerCountFieldIsDisabled() {
        flightSearchForm.seniorPassengerCountIsDisabled()
    }

    @Given("I search for a one-way available for checkin flight with one change planes")
    def searchForOneWayFlight() {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.market.default.departAirport
        itineraryData.arrivalStation= yaml.market.default.arriveAirport
        itineraryData.outboundConnectingStation = yaml.market.default.outboundConnectingStation
        flow.hasConnectionFlight = true
        itineraryData.departingFlight_fareClass = "Anytime"
        itineraryData.outboundRouting = "1 stop"
        itineraryData.itineraryType = itineraryData.ONE_WAY_ITINERARY
        itineraryData.homePage = true
        itineraryData.isValidForCheckin = true
        passengerData.addAdultPassenger()
        fillInFlightSearchInfoAndClick()
        flow.hasAir = true;
    }

    @Given("I search for a round-trip flight with one change planes")
    def searchForRoundTripFlight() {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.market.default.departAirport
        itineraryData.arrivalStation= yaml.market.default.arriveAirport
        itineraryData.outboundConnectingStation = yaml.market.default.outboundConnectingStation
        itineraryData.inboundConnectingStation = yaml.market.default.inboundConnectingStation
        flow.hasConnectionFlight = true
        flow.hasInboundConnectionFlight = true
        itineraryData.departingFlight_fareClass = "Anytime"
        itineraryData.arrivingFlight_fareClass = "Anytime"
        itineraryData.outboundRouting = "1 stop"
        itineraryData.inboundRouting = "1 stop"
        itineraryData.itineraryType = itineraryData.ROUND_TRIP_ITINERARY
        itineraryData.homePage = true
        passengerData.addAdultPassenger()
        fillInFlightSearchInfoAndClick()
        flow.hasAir = true;
    }

    @Given("I search for a round-trip flight with OB available for checkin with one change planes")
    def searchForRoundTripFlightOBCheckinEligible() {
        itineraryData.isValidForCheckin = true
        searchForRoundTripFlight()
    }


    @Given("I search for a one-way flight available for checkin for \$pax_adt_number {adult|adults}")
    def searchForOneWayFlightCheckinAvailable(@Named("pax_adt_number") int paxAdtNumber) {
        itineraryData.isValidForCheckin = true
        searchForOneWayFlight(paxAdtNumber)
    }

    @Given("I search for a one-way flight with \$fareType fare for checkin")
    def selectOneWayCitiesForCheckin(@Named("fareType") String fareType) {
        itineraryData.isValidForCheckin = true
        selectOneWayCities(fareType)
    }

    @Given("I search for a round-trip flight for a minor")
    def searchRoundTripFlightForMinor() {
        itineraryData.itineraryType = itineraryData.ROUND_TRIP_ITINERARY
        searchFlightForMinor()
    }

    @Given("I search for a one-way flight for a minor")
    def searchOneWayFlightForMinor() {
        itineraryData.itineraryType = itineraryData.ONE_WAY_ITINERARY
        searchFlightForMinor()
    }

    def searchFlightForMinor() {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.oneway.departAirport
        itineraryData.arrivalStation= yaml.oneway.arriveAirport
        itineraryData.departingFlight_fareClass = "Anytime"
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.homePage = true
        passengerData.addAdultPassenger()
        itineraryData.umDateOfBirth = flow.getUser().dateOfBirth
        fillInFlightSearchInfoAndClick()
        flow.hasAir = true
    }

    @Given("I search for an Airtran round-trip flight with \$fareType fare and \$paymentMethod")
    def selectRoundTripCarrier(@Named("fareType") String fareType, @Named("paymentMethod") String paymentMethod) {
        Map yaml = itineraryData.yamlItineraryData()

        itineraryData.departureStation = yaml.airtran.departAirport
        itineraryData.arrivalStation= yaml.airtran.arriveAirport
        itineraryData.departingFlight_carrierCode = yaml.airtran.carrier
        itineraryData.arrivingFlight_carrierCode = yaml.airtran.carrier

        selectRoundTripCitiesWithPayment(fareType, paymentMethod)
    }

    @Given("I search for a round-trip flight with \$fareType fare and \$paymentMethod")
    def selectRoundTripCitiesWithPayment(@Named("fareType") String fareType, @Named("paymentMethod") String paymentMethod) {
        if(paymentMethod.equals("southwestgiftcard")){
            itineraryData.giftCard = true
        }
        if(paymentMethod.toLowerCase().equals("promocode")){
            itineraryData.promoCode = "PODBUGS"
            itineraryData.isValidForWGA = true
        } else {
            customerInfoData.formOfPayment = paymentMethod
        }

        selectRoundTripCitiesFare(fareType)
    }

    @Given("I search for a round-trip flight with \$fareType fare")
    def selectRoundTripCitiesFare(@Named("fareType") String fareType) {
        if(fareType.toLowerCase().equals("business select")) {
            itineraryData.departingFlight_fareClass = "BusinessSelect"
            itineraryData.arrivingFlight_fareClass = "BusinessSelect"
        }
        else if(fareType.toLowerCase().equals("wanna get away")) {
            itineraryData.isValidForWGA = Boolean.TRUE
            itineraryData.departingFlight_fareClass = "WannaGetAway"
            itineraryData.arrivingFlight_fareClass = "WannaGetAway"
        }
        else{
            itineraryData.departingFlight_fareClass = "Anytime"
            itineraryData.arrivingFlight_fareClass = "Anytime"
        }

        selectRoundTripCities()
    }

    @Given("I search for a round trip")
    def selectRoundTripCities() {
        Map yaml = itineraryData.yamlItineraryData()

        itineraryData.departureStation = setDefaults(itineraryData.departureStation, yaml.roundtrip.departAirport)
        itineraryData.arrivalStation = setDefaults(itineraryData.arrivalStation, yaml.roundtrip.arriveAirport)
        itineraryData.departingFlight_carrierCode = setDefaults(itineraryData.departingFlight_carrierCode, "WN")
        itineraryData.arrivingFlight_carrierCode = setDefaults(itineraryData.arrivingFlight_carrierCode, "WN")
        itineraryData.departingFlight_fareClass = setDefaults(itineraryData.departingFlight_fareClass, "Anytime")
        itineraryData.arrivingFlight_fareClass = setDefaults(itineraryData.arrivingFlight_fareClass, "Anytime")

        itineraryData.outboundRouting = "Nonstop"
        itineraryData.inboundRouting = "Nonstop"
        itineraryData.itineraryType = itineraryData.ROUND_TRIP_ITINERARY
        itineraryData.homePage = true

        passengerData.addAdultPassenger(1)
        fillInFlightSearchInfoAndClick()
        flow.hasAir = true;
    }

    private def setDefaults(String itineraryObject, String defaultValue) {
        if(itineraryObject == null || itineraryObject.isEmpty()) {
            itineraryObject = defaultValue
        }
        else{
            return itineraryObject
        }
    }

    @Given("I want to book a flight that is eligible for check in")
    def setFlightThatIsEligibleForCheckin(){
        itineraryData.isValidForCheckin = true;
    }

    def fillInFlightSearchInfoAndClick()
    {
        if (ToggleHomepage2.isOn()) {
            homePage.fillInFlightSearchInfoAndClick(itineraryData, true)
        } else {
            flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true, HOME_PAGE_SUBMIT_BUTTON)
        }
    }

    @Given("I search for an open-jaw flight for \$pax_adt_number {adult|adults} with Nonstop \$fareType fare")
    def selectOpenJawTripForSingleOrMultiPax(@Named("fareType") String fareType, @Named("pax_adt_number") int paxAdtNumber) {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.openjaw.departAirport
        itineraryData.arrivalStation = yaml.openjaw.arriveAirport
        itineraryData.returnStation = yaml.openjaw.returnAirport
        itineraryData.itineraryType = itineraryData.OPEN_JAW_ITINERARY
        itineraryData.departingFlight_carrierCode = "WN"
        itineraryData.arrivingFlight_carrierCode = "WN"
        itineraryData.returningFlight_carrierCode = "WN"
        itineraryData.departingFlight_fareClass = fareType.replaceAll('\\s+','')
        itineraryData.arrivingFlight_fareClass = fareType.replaceAll('\\s+','')
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.inboundRouting = "Nonstop"
        customerInfoData.formOfContact = "Text"


        flightSearchForm.verifyBasicPage()
        flightSearchForm.verifyTripType()
        flightSearchForm.verifyAddAnotherFlightLink()
        flightSearchForm.verifySeeWhereToFlyLink()
        flightSearchForm.verifyFareRadioButton()
        itineraryData.adultPassengerCount = paxAdtNumber
        passengerData.addAdultPassenger(paxAdtNumber)
        searchFlightsPage.clickRoundTrip()
        searchFlightsPage.clickAddAnotherFlight()

        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        flow.hasAir = true;
    }
}
