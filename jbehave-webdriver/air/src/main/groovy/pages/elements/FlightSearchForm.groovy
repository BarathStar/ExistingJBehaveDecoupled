package pages.elements

import com.swacorp.dotcom.webscenarios.air.Data
import fixture.stubs.DynaStubsIntegration
import fixtures.air.ReservationSpecification
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions
import pages.BasePage
import pages.OutboundAndReturnDatesAndPopUp
import state.Flow
import state.PassengerData
import util.ItineraryData
import util.ItineraryDateFactory
import util.PageName
import util.PassengerType

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static util.Locators.ELEMENT_IDS

class FlightSearchForm extends BasePage {

    private static final String FLIGHT_NUMBER = "flightNumber"
    private static final String SUBMIT_BUTTON_ID = "submitButton"
    private static final String SELECT_NEW_FLIGHT ="selectNewFlightBtn"
    private WebElement adultPassengerCount = null
    private WebElement seniorPassengerCount = null
    private static final By FARE_TOGGLE = By.className("fareToggle")
    private static final By ADD_ANOTHER_FLIGHT = By.id("add-another-flight")
    private static final By ONE_WAY_RADIO_BUTTON = By.xpath("//*[@id='oneWay']|//*[@id='trip-type-one-way']")
    private static final By ROUND_TRIP_RADIO_BUTTON = By.xpath("//*[@id='roundTrip']|//*[@id='trip-type-round-trip']")
    private static final By RETURN_AIRPORT =By.xpath("//*[@id='destinationAirport_displayed']|//*[@id='air-city-arrival']")
    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By RETURN_DATE = By.id("returnDate")
    private static final By POINTS = By.id("points")
    private static final By DOLLARS = By.id("dollars")
    private static final By ADULT_PASSENGER_COUNT = By.id("adultPassengerCount")
    private static final By IMG_POINTS = By.id("imgpoints")
    private static final By IMG_DOLLARS = By.id("imgdollars")
    private static final By SENIOR_PASSENGER_COUNT = By.id("seniorPassengerCount")
    private static final By ORIGIN_AIRPORT_DISPLAYED = By.xpath("//*[@id='originAirport_displayed']|//*[@id='air-city-departure']")
    private static final By DESTINATION_AIRPORT_DISPLAYED = By.xpath("//*[@id='destinationAirport_displayed']|//*[@id='air-city-arrival']")
    private static final By PROMOTION_CODE = By.xpath("//*[@id='promocode']|//*[@id='air-promo-code']")
    private static final String ID_ORIGIN_AIRPORT_DISPLAYED = "originAirport_displayed"
    private static final String ID_DESTINATION_AIRPORT_DISPLAYED = "destinationAirport_displayed"
    private static final By RECENT_SEARCH = By.id("air-recent-search")
    private static final String CLEAR_RECENT_SEARCHES = "__clear"


    OutboundAndReturnDatesAndPopUp calendarPopUp
    AutoCompleteWidget autoCompletePage
    PassengerData passengerData
    ItineraryData itineraryData
    Data data
    Flow flow
    DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
    PageName pageName

    public FlightSearchForm(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    public verifyBasicPage(){
        super.verifyPage()
        System.getProperty("domainToTest") ? getCurrentUrl().shouldContain (System.getProperty("domainToTest")) : getCurrentUrl().shouldContain ("local.swacorp.com")
        pageVerificationErrorWrapper(By.id("submitButton"), PageName.PLAN_TRIP_PAGE)
    }

    public String getOutboundDate() {
        return "outboundDate"
    }

    public String getReturnDate() {
        return "returnDate"
    }

    String getRelativePath() {
        return "" // not needed for elements
    }

    def fillInFlightSearchInfoAndClickForFlexibleDates(ItineraryData itineraryData, boolean createData, String buttonId = SUBMIT_BUTTON_ID) {
        selectItineraryType(itineraryData)
        fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation, itineraryData.returnStation)
        fillInFlightDates(itineraryData)

        if (DynaStubsIntegration.useDynaStubs() && createData) {
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }
        String title = getTitle()
        submit(buttonId)
        waitForPageTitleToChangeOrOops(title)
        verifyPage()
    }

    def fillInFlightSearchInfoAndClick(ItineraryData itineraryData, boolean createData, String buttonId = SUBMIT_BUTTON_ID) {
        selectItineraryType(itineraryData)
        fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation, itineraryData.returnStation)
        saveStations()
        fillInPromoCode(itineraryData.promoCode)
        fillInFlightDates(itineraryData)
        fillInPassengers()

        if (DynaStubsIntegration.useDynaStubs() && createData) {
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }
        String title = getTitle()
        submit(buttonId)
        waitForPageTitleToChangeOrOops(title)
        pageVerificationErrorWrapper(By.id("outbound_results"), pageName.SELECT_FLIGHTS)
        verifyPage()
    }

    void saveStations() {
        itineraryData.departureCity = waitForElement(ORIGIN_AIRPORT_DISPLAYED).getAttribute("value").split("-")[0].trim()
        itineraryData.arrivalCity = waitForElement(DESTINATION_AIRPORT_DISPLAYED).getAttribute("value").split("-")[0].trim()
        if (itineraryData.openJaw){
            itineraryData.returnCity = waitForElement(RETURN_AIRPORT).getAttribute("value").split("-")[0].trim()
        }
    }

    void fillInAirports(String originAirport, String destinationAirport, String returnAirport = null) {

        fillInWithAutocomplete(ORIGIN_AIRPORT_DISPLAYED, originAirport, 3)
        fillInWithAutocomplete(DESTINATION_AIRPORT_DISPLAYED, destinationAirport, 3)

        WebElement returnAirportField = waitForElement(RETURN_AIRPORT, false)
        if (returnAirportField?.isDisplayed() && returnAirport == null) {
            returnAirportField.sendKeys(Keys.TAB)
        }

        if(returnAirport != null){
            fillIn(RETURN_AIRPORT, returnAirport)
            itineraryData.itineraryType == "Open Jaw"
        }
    }

    void selectItineraryType(ItineraryData itineraryData) {
        if(itineraryData.isOneWay()){
            clickOneWay()
        } else if (itineraryData.isOpenJaw()){
            makeReturnAirportFieldVisible()
        } else {
            clickRoundTrip()
        }
    }

    def Date chooseDepartureDateForItinerary(ItineraryData itineraryData) {
        Date departureDate
        if (!itineraryData.departureDate) {
            if ((itineraryData.isValidForCheckin).toBoolean()) {
                departureDate = ItineraryDateFactory.chooseDayEligibleForDepartureCheckin()
                itineraryData.departureDate = departureDate
            } else if(itineraryData.isValidForWGA) {
                departureDate = ItineraryDateFactory.getWannaGetAwayDepartureDate()
                itineraryData.departureDate = departureDate
            } else {
                departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate()
                itineraryData.departureDate = departureDate
            }
        } else {
            departureDate = itineraryData.departureDate
        }
        return departureDate
    }

    def fillInAirportsWithReturn(String from, String to, String returnStation) {
        fillInAirports(from, to)
        fillIn(By.id(ELEMENT_IDS["Return"]), returnStation)
    }

    def enterDepartureDate(Date departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate(), String outboundDateId = itineraryData.homePage? "air-date-departure":getOutboundDate()) {
        itineraryData.departureDate = departureDate
        calendarPopUp.typeInOutboundOrInboundDate(departureDate.format("MM/dd/yyyy"), outboundDateId)
        waitForElement(By.id(outboundDateId)).sendKeys(DELETE_EXISTING + itineraryData.departureDate.format("MM/dd/yyyy") + TAB)
    }

    def enterReturnDate(Date returnDate = ItineraryDateFactory.getAnyAvailableReturnDateAfter(itineraryData.departureDate), String returnDateId = itineraryData.homePage? "air-date-return":getReturnDate()) {
        itineraryData.returnDate = returnDate
        calendarPopUp.typeInOutboundOrInboundDate(returnDate.format("MM/dd/yyyy"), returnDateId)
        waitForElement(By.id(returnDateId)).sendKeys(DELETE_EXISTING + itineraryData.returnDate.format("MM/dd/yyyy") + TAB)
    }

    def verifyStationsArePrePopulated() {
        String msg = "data did not match after cancelling the redirect modal"
        assertEquals(msg,itineraryData.departureStation,findElement(By.id(ELEMENT_IDS["From"])).getAttribute("value").find(itineraryData.departureStation))
        assertEquals(msg,itineraryData.arrivalStation,findElement(By.id(ELEMENT_IDS["To"])).getAttribute("value").find(itineraryData.arrivalStation))
    }

    void clickOneWay() {
        WebElement oneWay = waitForElementDisplayed(ONE_WAY_RADIO_BUTTON)
        oneWay.click()
    }

    void clickRoundTrip() {
        WebElement roundTrip = waitForElement(ROUND_TRIP_RADIO_BUTTON)
        roundTrip.click()
    }

    int retrievePassengerCount(WebElement passengerCountElement) {
        String count = (new Select(passengerCountElement)).getFirstSelectedOption().getText()
        count = count.substring(0, count.indexOf(" "))
        return count.toInteger().intValue()
    }

    void clickButton(String buttonId) {
       WebElement button = waitForElement(By.id(buttonId))
       if (button != null) {
          button.click()
       } else {
          fail("Unable to find button : " + buttonId);
       }
    }

    def submit(String buttonId = SUBMIT_BUTTON_ID) {
        clickButton(buttonId)
        verifyPage()
    }

    void verifyReturnFieldIsDisabled() {
        waitForElement(RETURN_AIRPORT).isEnabled().shouldBe false, 'The return station input field should be disabled and not available for input.'
    }

    void returnFieldVisible() {
        waitForElement(RETURN_AIRPORT).isDisplayed().shouldBe true, 'The return station input field should be visible after the \'add another flight\' is clicked'
    }

    void makeReturnAirportFieldVisible() {
        WebElement returnAirport =  waitForElement(RETURN_AIRPORT)
        if (!returnAirport.isDisplayed()) {
            WebElement addReturnFlight = waitForElement(ADD_ANOTHER_FLIGHT)
            addReturnFlight.click()
        }

       returnAirport.isDisplayed().shouldBe true, 'The return station input field should be visible after the \'add another flight\' is clicked'
    }

    void returnFieldAndDateTimeFieldDisabled() {
        verifyReturnFieldIsDisabled()
    }

    void selectMultipleAdultPassengers(int adultPassengers) {
        assertTrue("Adult and senior passenger count: '$adultPassengers' is not between 0 and 8.",
                verifyPassengerCount(adultPassengers))

        passengerData.clearPassengers()
        addAdultPassengers(adultPassengers)
        fillInAdult(adultPassengers)
    }

    void selectMultipleAdultPassengersWithRapidRewards(int adultPassengers) {
        assertTrue("Adult and senior passenger count: '$adultPassengers' is not between 0 and 8.",
                verifyPassengerCount(adultPassengers))

        passengerData.clearPassengers()
        addAdultPassengersWithRapidRewards(adultPassengers)
        fillInAdult(adultPassengers)
    }

    void selectAdultPassengerOfSize(int adultPassengersOfSize) {
        passengerData.addAdultPassengerOfSize(adultPassengersOfSize)
        fillInAdult(adultPassengersOfSize * 2)
    }

    void selectAdultWithInvalidMiddleName() {
        passengerData.addAdultWithInvalidMiddleName()
        fillInAdult(1)
    }

    void selectAdultAndSeniorPassengers(int adultPassengers, int seniorPassengers) {
        assertTrue("Adult and senior passenger count: '$adultPassengers + $seniorPassengers' is not between 0 and 8.", verifyPassengerCount(adultPassengers + seniorPassengers))

        if (passengerData.getAdults().size() == 0) {
            addAdultPassengers(adultPassengers)
        }
        fillInAdult(adultPassengers)
        fillInSenior(seniorPassengers)
    }


    void selectAnAdultPassengerWithName(String firstName, String lastName) {
        passengerData.addPassengerWithName(firstName,lastName)
        fillInAdult(1)
    }

    void selectPassengerWithCompoundName(){
        passengerData.addPassengerWithCompoundName();
        fillInAdult(1);
    }

    void selectChildAndYouthPassengers(int childPassengers, int youthPassengers) {
        assertTrue("Child and youth passenger count: '$childPassengers + $youthPassengers' is not between 0 and 8.",
                verifyPassengerCount(childPassengers + youthPassengers))

        addChildPassengers(childPassengers)
        addYouthPassengers(youthPassengers)
        fillInAdult(childPassengers + youthPassengers)
    }

    void selectChildAndSeniorPassengers(int childPassengers, int seniorPassengers) {
        assertTrue("Child and senior passenger count: '$childPassengers + $seniorPassengers' is not between 0 and 8.",
                verifyPassengerCount(childPassengers + seniorPassengers))

        addChildPassengers(childPassengers)
        fillInAdult(childPassengers)
        fillInSenior(seniorPassengers)
    }

    void selectChildAndAdultPassengers(int childPassengers, int adultPassengers) {
        assertTrue("Child and senior passenger count: '$childPassengers + $adultPassengers' is not between 0 and 8.",
                verifyPassengerCount(childPassengers + adultPassengers))

        addChildPassengers(childPassengers)
        addAdultPassengers(adultPassengers)
        fillInAdult(childPassengers + adultPassengers)
    }

    void selectYoungTravelerAndAdult(int youngTraveler, int adultPassengers) {
        assertTrue( "Young traveler and adult passenger count: '$youngTraveler + $adultPassengers' is not between 0 and 8." ,
                verifyPassengerCount(youngTraveler + adultPassengers))

        addYouthPassengers(youngTraveler)
        addAdultPassengers(adultPassengers)
        fillInAdult(youngTraveler + adultPassengers)
    }

    void selectYoungChildAndAdultPassengers(int youngChildPassengers, int adultPassengers) {
        assertTrue("Young child and adult passenger count: '$youngChildPassengers + $adultPassengers' is not between 0 and 8.",
                verifyPassengerCount(youngChildPassengers + adultPassengers))

        addYoungChildPassengers(youngChildPassengers)
        addAdultPassengers(adultPassengers)
        fillInAdult(youngChildPassengers + adultPassengers)
    }

    def fillInAdultPassenger(int adults) {
        (new Select(waitForElement(ADULT_PASSENGER_COUNT))).selectByValue(adults.toString())
    }

    def fillInSeniorPassenger(int seniors) {
        (new Select(seniorPassengerElement())).selectByValue(seniors.toString())
    }

    def fillInAdult(int adults) {
        final Select adultPassengerCountSelectBox = new Select(waitForElement(ADULT_PASSENGER_COUNT))
        adultPassengerCountSelectBox.selectByValue(adults.toString())
    }

    def fillInSenior(int seniors) {
        (new Select(seniorPassengerElement())).selectByValue(seniors.toString())
//       addSeniorPassengers(seniors)
    }

    def fillInPromoCode(String promoCode) {
        if(promoCode != null && promoCode != ""){
            fillIn(PROMOTION_CODE, promoCode)
        }
    }

    def clearPromoCode() {
        super.clearText(waitForElement(PROMOTION_CODE))
    }

    void verifySeniorFieldDisabled() {
        waitForElement(SENIOR_PASSENGER_COUNT).getAttribute("disabled").shouldEqual "true", "The senior field is not disabled"
        verifyZeroSeniorCount()
    }

    void verifyZeroSeniorCount() {
        seniorPassengerElement().getAttribute("value").shouldEqual "0", "Senior passengers size is not 0"
    }

    void verifyPointsToggleNotOnPage() {
        verifyElementNotPresent("Points Toggle", FARE_TOGGLE)
    }

    def verifyPromoCodeDisabled() {
        waitForElement(PROMOTION_CODE).getAttribute("disabled").shouldBe true, "The promo code field was not disabled"
    }

    void chooseFaresInPoints() {
        try {
            waitForElement(POINTS).click()
        } catch (Exception e) {
            waitForElement(IMG_POINTS).click()
        }
    }

    void chooseFaresInDollars() {
        try {
            waitForElement(DOLLARS).click()
        } catch (Exception e) {
            waitForElement(IMG_DOLLARS).click()
        }
    }

    def verifyDollarPointsOption() {
        verifyElementPresent("Dollar Option", DOLLARS)
        verifyElementPresent("Points Option", POINTS)
    }

    def verifyStationIsNotInFieldAndFieldIsBlank(String fieldId, String station) {
        def displayedValue = waitForElement(By.id(fieldId)).getAttribute('value')
        displayedValue.shouldNotContain station, "Not Expected content '$station' but got '$displayedValue'"
        waitForElement(By.id(fieldId)).getText().shouldContain ""
    }

    void searchForRoundTrip(ItineraryData itineraryData) {
        Map <String, Date> dateMap = ItineraryDateFactory.getRoundTripDates()
        fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        fillInFlightDates(itineraryData)
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }

        submit()
    }

    void fillFromAndToAirportAndDepartureDate(ItineraryData itineraryData,String earlyBirdOnly = "none") {
        if(itineraryData.itineraryType == "One Way") {
            waitForElement(ONE_WAY_RADIO_BUTTON).click()
        }
        fillInAirports(itineraryData.departureStation,itineraryData.arrivalStation)
        def departureDate
        if (earlyBirdOnly == 'EB') {
            departureDate = ItineraryDateFactory.getAnyAvailableDepartureDateForEarlyBird()
        } else {
            departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate()
        }
        enterDepartureDate(departureDate)
        submit()
    }
    void viewSelectFlightPage() {
        fillInAirports(data.getRoute().from, data.getRoute().to)
        submit()
    }

    def clickSelectNewFlight() {
        submit(SELECT_NEW_FLIGHT)
    }

    def selectPassengers(List passengers){
        assertTrue("Passenger count: '${passengers.size()}' is not between 0 and 8.", verifyPassengerCount(passengers.size()))
        passengerData.addPassengers(passengers)
        fillInAdultPassenger(passengers.size() - passengers.grep(PassengerType.SENIOR).size())
        fillInSeniorPassenger(passengers.grep(PassengerType.SENIOR).size())
    }

    def fillInPassengers() {
        if(passengerData.getPassengers().isEmpty()){
            passengerData.addAdultPassenger()
        }
        int numberOfSeniors = passengerData.getSeniors().size()
        fillInAdultPassenger(passengerData.getPassengers().size() - numberOfSeniors)
        fillInSeniorPassenger(numberOfSeniors)
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

    private def seniorPassengerElement() {
        return waitForElement(SENIOR_PASSENGER_COUNT)
    }

    public void fillInFlightDates(ItineraryData itineraryData, boolean isSameDay = false) {
        Date departureDate = null
        Date returnDate = null

        departureDate = chooseDepartureDateForItinerary(itineraryData)
        enterDepartureDate(departureDate)

        if (!itineraryData.isOneWay()) {
            if (!itineraryData.returnDate) {
                if (isSameDay) {
                    returnDate = departureDate
                } else {
                    returnDate = ItineraryDateFactory.getAnyAvailableReturnDateAfter(departureDate)
                }
                itineraryData.returnDate = returnDate
            } else {
                returnDate = itineraryData.returnDate
            }
            enterReturnDate(returnDate)
        }
    }

    void searchBasedOn(ReservationSpecification reservationSpecification) {
        if (reservationSpecification.oneWay) {
            clickOneWay()
        } else if (reservationSpecification.openJaw) {
            makeReturnAirportFieldVisible()
        } else {
            clickRoundTrip()
        }

        fillInAirports(reservationSpecification.outbound.origin, reservationSpecification.outbound.destination)

        enterDepartureDate(reservationSpecification.outbound.departureDate.toDateMidnight().toDate())
        if (!reservationSpecification.oneWay) {
            enterReturnDate(reservationSpecification.inbound.departureDate.toDateMidnight().toDate())
        }

        fillInAdultPassenger(reservationSpecification.getAdultPassengers().size())
        fillInSeniorPassenger(reservationSpecification.getSeniorPassengers().size())

        submit()
    }
    void searchForFlight(ItineraryData itineraryData, Date departureDate = null, Date returnDate = null, String submitButton = SUBMIT_BUTTON_ID,boolean verifyNoOpps = true) {

        itineraryData.departureDate = departureDate ?: ItineraryDateFactory.getAnyAvailableDepartureDate()
        itineraryData.returnDate = returnDate ?: ItineraryDateFactory.getAnyAvailableReturnDateAfter(itineraryData.departureDate)

        selectItineraryType(itineraryData)

        fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)

        fillInFlightDates(itineraryData)

        if (passengerData.passengers.size() == 0) {
            addAdultPassengers(waitForElement(ADULT_PASSENGER_COUNT).getAttribute("value").toInteger())
            addSeniorPassengers(seniorPassengerElement().getAttribute("value").toInteger())
        }

        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }

        (verifyNoOpps) ? submit(submitButton) : waitForElement(By.id(submitButton)).click()
    }

    def selectDepartureStation(String stationCode){
        sleep(10000)
        fillIn(ORIGIN_AIRPORT_DISPLAYED, stationCode);
    }

    def selectArrivalStation(String stationCode){
        sleep(10000)
        fillIn(DESTINATION_AIRPORT_DISPLAYED, stationCode);
    }

    void validateDepartureStationDescription(String description){
        sleep(5000)
        verifyInField(ID_ORIGIN_AIRPORT_DISPLAYED,description)
    }

    void validateArrivalStationDescription(String description){
        sleep(5000)
        verifyInField(ID_DESTINATION_AIRPORT_DISPLAYED,description)
     }

    void deleteOriginDestinationAirportFields(){
        waitForElement(ORIGIN_AIRPORT_DISPLAYED).sendKeys(DELETE_EXISTING)
        waitForElement(DESTINATION_AIRPORT_DISPLAYED).sendKeys(DELETE_EXISTING)
    }

    def businessSelectFareIsUnavailable(ItineraryData itineraryData) {
        dynaStubsIntegration.prepareShoppingSchedules(itineraryData,false)
    }

    def seniorPassengerCountIsEnabled() {
        waitForElement(SENIOR_PASSENGER_COUNT).isEnabled().shouldBe true, "The Senior Passenger Count field is not enabled"
    }

    def seniorPassengerCountIsDisabled() {
        waitForElement(SENIOR_PASSENGER_COUNT).isEnabled().shouldBe false, "The Senior Passenger Count field is not disabled"
    }

    def promoCodeIsEnabled() {
        waitForElement(PROMOTION_CODE).isEnabled().shouldBe true, "The Promo Code field is not enabled"
    }

    def verifyTripType() {
        waitForElement(ROUND_TRIP_RADIO_BUTTON).getAttribute("checked").shouldBe "true", "Round Trip was not selected by default"
    }

    def verifyAddAnotherFlightLink() {
        waitForElement(ADD_ANOTHER_FLIGHT, false).shouldNotBe null, "Add another flight was not preset"
    }

    def verifySeeWhereToFlyLink() {
        waitForElement(By.id("where-we-fly-link-text")).text.shouldBe "See Where We Fly", "Where to fly link was not displayed"
    }

    def verifyFareRadioButton() {
        waitForElement(By.id("dollars")).getAttribute("checked").shouldBe "true", "Dollars was not selected by default"
    }

    def clearRecentSearches() {
        (new Select(waitForElement(RECENT_SEARCH))).selectByValue(String.valueOf(CLEAR_RECENT_SEARCHES))
    }


}
