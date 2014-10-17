package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.junit.Assert
import org.openqa.selenium.Keys
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import state.PassengerData
import steps.conditional.ToggleHomepage2
import util.ItineraryDateFactory

import static org.junit.Assert.assertTrue
import org.openqa.selenium.support.ui.Select
import fixture.stubs.DynaStubsIntegration
import util.ItineraryData
import org.openqa.selenium.By

public class HomePageFlightSearchForm extends FlightSearchForm implements GroovyInterceptable {
    private static final By AIR_PAX_COUNT_ADULTS = By.id("air-pax-count-adults")
    private static final By AIR_PAX_COUNT_SENIORS = By.id("air-pax-count-seniors")
    private static final By RETURN_AIRPORT =By.xpath("//*[@id='destinationAirport_displayed']|//*[@id='air-city-arrival']")
    private static final By DESTINATION_AIRPORT_DISPLAYED = By.id("air-city-arrival")
    private static final By DESTINATION_AIRPORT_DISPLAYED_LABEL = By.cssSelector("label[for=air-city-arrival]")
    private static final By NUMBER_SELECTOR_MORE = By.id("jb-number-selector-more")
    private static final By ONE_WAY_RADIO_BUTTON = By.id("trip-type-one-way")
    private static final By ORIGIN_AIRPORT_DISPLAYED = By.id("air-city-departure")
    private static final By ORIGIN_AIRPORT_DISPLAYED_LABEL = By.cssSelector("label[for=air-city-departure]")
    private static final By PROMOTION_CODE = By.id("air-promo-code")
    private static final By ROUND_TRIP_RADIO_BUTTON = By.id("trip-type-round-trip")
    private static final String SUBMIT_BUTTON_ID_NAME = "jb-booking-form-submit-button"
    private static final String AIR_DATE_DEPARTURE_ID_NAME = "air-date-departure"
    private static final String AIR_DATE_RETURN_ID_NAME = "air-date-return"
    private static final String INBOUND_OUTBOUND_DATE_FORMAT = "MM/dd"

    HomePageFlightSearchFormOld homePageFlightSearchFormOld

    public HomePageFlightSearchForm (WebDriverProvider driverProvider){
        super(driverProvider);
        homePageFlightSearchFormOld = new HomePageFlightSearchFormOld(driverProvider)
    }

    def invokeMethod(String name, args) {
        def metaMethod = metaClass.getMetaMethod(name, args)
        def result
        if(ToggleHomepage2.isOn()){
            result = metaMethod.invoke(this, args)
        } else {
            result = homePageFlightSearchFormOld."$name"(*args)
        }
        return result
    }

    String getRelativePath() {
        return ""
    }

    @Override
    String getOutboundDate() {
        AIR_DATE_DEPARTURE_ID_NAME
    }

    @Override
    String getReturnDate() {
        AIR_DATE_RETURN_ID_NAME
    }

    def fillInHomePageSearchDataAndSubmit(ItineraryData itineraryData,boolean verifyNoOpps = true) {

        selectItineraryType(itineraryData)

        fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)

        enterDepartureDate(itineraryData.departureDate,getOutboundDate())

        if(itineraryData.isRoundTrip()) {
            enterReturnDate(itineraryData.returnDate,getReturnDate())
        }

        if (itineraryData.adultPassengerCount > 0) {
            (new Select(findElement(By.id("adultPassengerCount")))).selectByValue(itineraryData.adultPassengerCount.toString())
             addAdultPassengers((itineraryData.adultPassengerCount) as int )
        }
        if(itineraryData.seniorPassengerCount > 0) {
            (new Select(findElement(By.id("seniorPassengerCount")))).selectByValue(itineraryData.seniorPassengerCount.toString())
            addSeniorPassengers((itineraryData.seniorPassengerCount) as int )
        }
        if (DynaStubsIntegration.useDynaStubs()) {
            final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
            dynaStubsIntegration.prepareWeeklyOrDailyFlightSchedules(itineraryData)
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }

        (verifyNoOpps) ? submit(SUBMIT_BUTTON_ID_NAME) : waitForElement(By.id(SUBMIT_BUTTON_ID_NAME)).click()
    }

    /**
     * Fills in all the required inputs to search a flight from the Home page and proceed to the select flight page.
     * Also handles inputting the promode if given.
     * @param itineraryData - Data holder for all itinerary details.
     * @param createData
     * @param buttonId
     * TODO To validate if filling passengers inside the fillinFlightSearch method required. Passengers are already getting added in a previous steps in the flow
     */
    def fillInFlightSearchInfoAndClick(ItineraryData itineraryData, boolean createData, String buttonId = SUBMIT_BUTTON_ID_NAME) {
        selectItineraryType(itineraryData)
        fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation, itineraryData.returnStation)
        fillInPromoCode(itineraryData.promoCode)
        fillInFlightDates(itineraryData)
        fillInPassengers()
        saveStations()

        if (DynaStubsIntegration.useDynaStubs() && createData) {
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }
        String title = getTitle()
        submit(buttonId)
        waitForPageTitleToChangeOrOops(title)
        pageVerificationErrorWrapper(By.id("outbound_results"), pageName.SELECT_FLIGHTS)
        verifyPage()
    }

    def fillInFlightSearchAndClick(ItineraryData itineraryData, boolean createData, String buttonId = SUBMIT_BUTTON_ID_NAME) {
        selectItineraryType(itineraryData)
        fillAirportLocations(itineraryData.departureStation, itineraryData.arrivalStation, itineraryData.returnStation)
        fillInPromoCode(itineraryData.promoCode)
        fillInFlightDates(itineraryData)
        fillInPassengers()
        saveStations()

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
        itineraryData.departureCity = waitForElement(ORIGIN_AIRPORT_DISPLAYED_LABEL).text.split("-")[0].trim()
        itineraryData.arrivalCity = waitForElement(DESTINATION_AIRPORT_DISPLAYED_LABEL).text.split("-")[0].trim()
    }

    void fillInAirports(String originAirport, String destinationAirport, String returnAirport = null) {
        fillInWithAutocomplete(ORIGIN_AIRPORT_DISPLAYED, originAirport, 2, false, DEFAULT_WAIT_LIMIT as Double, DEFAULT_WAIT_RETRY, 750)
        fillInWithAutocomplete(DESTINATION_AIRPORT_DISPLAYED, destinationAirport, 2, false, DEFAULT_WAIT_LIMIT as Double, DEFAULT_WAIT_RETRY, 750)
    }

    @Override
    void makeReturnAirportFieldVisible() {
        Assert.fail("Open Jaw flights not supported on the new home page")
    }

    @Override
    def fillInPromoCode(String promoCode) {
        if(promoCode != null && promoCode != ""){
            fillIn(PROMOTION_CODE, promoCode)
        }
    }

    @Override
    void clickOneWay() {
        waitForElement(ONE_WAY_RADIO_BUTTON).click()
    }

    @Override
    void clickRoundTrip() {
        waitForElement(ROUND_TRIP_RADIO_BUTTON).click()
    }

    @Override
    void fillInFlightDates(ItineraryData itineraryData) {
        Date departureDate = null
        Date returnDate = null

        departureDate = chooseDepartureDateForItinerary(itineraryData)
        enterDepartureDate(departureDate)

        if (!itineraryData.isOneWay()) {
            if (!itineraryData.returnDate) {
                returnDate = ItineraryDateFactory.getAnyAvailableReturnDateAfter(departureDate)
                itineraryData.returnDate = returnDate
            } else {
                returnDate = itineraryData.returnDate
            }
            enterReturnDate(returnDate)
        }
    }

    def selectAdultAndSeniorPassengers(ItineraryData itineraryData, PassengerData passengerData) {
        int adultPassengers = itineraryData.adultPassengerCount.toInteger()
        int seniorPassengers = itineraryData.seniorPassengerCount.toInteger()
        assertTrue("Adult and senior passenger count: '$adultPassengers + $seniorPassengers' is not between 0 and 8.", verifyPassengerCount(adultPassengers + seniorPassengers))

        if (passengerData.getAdults().size() == 0) {
            addAdultPassengers(adultPassengers)
        }
        fillInAdult(adultPassengers)
        fillInSenior(seniorPassengers)
    }


    @Override
    def enterDepartureDate(Date departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate(), String outboundDateId = itineraryData.homePage? AIR_DATE_DEPARTURE_ID_NAME:getOutboundDate()) {
        itineraryData.departureDate = departureDate
        calendarPopUp.typeInOutboundOrInboundDate(departureDate.format(INBOUND_OUTBOUND_DATE_FORMAT), outboundDateId)
        waitForElement(By.id(outboundDateId)).sendKeys(DELETE_EXISTING + itineraryData.departureDate.format(INBOUND_OUTBOUND_DATE_FORMAT) + TAB)
    }

    @Override
    def enterReturnDate(Date returnDate = ItineraryDateFactory.getAnyAvailableReturnDateAfter(itineraryData.departureDate), String returnDateId = itineraryData.homePage? AIR_DATE_RETURN_ID_NAME:getReturnDate()) {
        itineraryData.returnDate = returnDate
        calendarPopUp.typeInOutboundOrInboundDate(returnDate.format(INBOUND_OUTBOUND_DATE_FORMAT), returnDateId)
        waitForElement(By.id(returnDateId)).sendKeys(DELETE_EXISTING + itineraryData.returnDate.format(INBOUND_OUTBOUND_DATE_FORMAT) + TAB)
    }

    @Override
    def fillInAdultPassenger(int adults) {
        waitForElement(AIR_PAX_COUNT_ADULTS).click()
        waitForElement(NUMBER_SELECTOR_MORE).sendKeys(adults.toString());
    }

    @Override
    def fillInSeniorPassenger(int seniors) {
        waitForElement(AIR_PAX_COUNT_SENIORS).click()
        waitForElement(NUMBER_SELECTOR_MORE).sendKeys(seniors.toString());
    }

    @Override
    def submit(String buttonId = SUBMIT_BUTTON_ID_NAME) {
        clickButton(buttonId)
        verifyPage()
    }

    @Override
    void clickButton(String buttonId) {
        WebElement button = waitForElement(By.id(buttonId))
        if (button != null) {
            button.click()
        } else {
            fail("Unable to find button : " + buttonId);
        }
    }

    WebElement getLocationElement(String location) {
        return waitForElementVisible(By.xpath("//li[b[text()='"+location+"']]"));
    }
    void fillInOrigin(String userInput,boolean doTab = true, int sleepTime = DEFAULT_SLEEP_TIME) {
        try{
            fillInLocation(ORIGIN_AIRPORT_DISPLAYED,userInput)
            WebElement location=  getLocationElement(userInput)
            saveOrigin(location)
            location.click()
        } catch (TimeoutException te) {
            throw new WebDriverException("Timeout - Autocomplete failed to initialize in " + timeout + " seconds, actual: " + (System.currentTimeMillis() - start) + "millis.")
        }
    }

    void fillInDestination(String userInput,boolean doTab = true, int sleepTime = DEFAULT_SLEEP_TIME) {
        try {
            fillInLocation(DESTINATION_AIRPORT_DISPLAYED,userInput)
            WebElement location= getLocationElement(userInput)
            saveDestination(location)
            location.click()
        } catch (TimeoutException te) {
            throw new WebDriverException("Timeout - Autocomplete failed to initialize in " + timeout + " seconds, actual: " + (System.currentTimeMillis() - start) + "millis.")
        }
    }
    void saveOrigin(WebElement location) {
        String origin=  location.getText().split("-")[0].trim()
        itineraryData.departureCity =origin
    }

    void saveDestination(WebElement location) {
        String destination=  location.getText().split("-")[0].trim()
        itineraryData.arrivalCity = destination
    }

    WebElement fillInLocation(By byElement, String userInput) {
        Actions action = getActions()
        WebElement inputElement = waitForElementVisible(byElement)
        //clearValue(inputElement)
        for (char ch : userInput.toCharArray()) {
            action = action.sendKeys(inputElement, ch.toString())
            Thread.sleep(200)
        }
        action.sendKeys(Keys.ARROW_DOWN).build().perform()
    }

    WebElement waitForElementVisible(By byElement, boolean throwExceptions = true) {
        WebElement resultElement = null
        int timeout = DEFAULT_WAIT_LIMIT
        WebDriverWait wdw = new WebDriverWait(this.getDriverProvider().get(), timeout)
        try {
            resultElement= wdw.until(ExpectedConditions.visibilityOfElementLocated(byElement))
        } catch (TimeoutException te) {
            if (throwExceptions) {
                PAGE_SOURCE_CAPTURE=true
                capturePageSource()
                PAGE_SOURCE_CAPTURE=false
                throw new WebDriverException("Timeout - Element '" + byElement + "' not found in configured " + timeout + " seconds, actual: " + (System.currentTimeMillis() - start) + "millis." )
            }
        }
        return resultElement
    }


    void fillAirportLocations(String originAirport, String destinationAirport, String returnAirport = null) {

        fillInOrigin(originAirport)
        fillInDestination(destinationAirport)

        WebElement returnAirportField = waitForElement(RETURN_AIRPORT)
        if (returnAirportField?.isDisplayed() && returnAirport == null) {
            returnAirportField.sendKeys(Keys.TAB)
        }

        if(returnAirport != null){
            fillIn(RETURN_AIRPORT, returnAirport)
            itineraryData.itineraryType == "Open Jaw"
        }
    }


}