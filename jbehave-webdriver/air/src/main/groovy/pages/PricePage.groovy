package pages

import com.swacorp.dotcom.webscenarios.air.Data
import fixture.stubs.DynaStubsIntegration
import org.jbehave.web.selenium.WebDriverProvider
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.openqa.selenium.*
import pages.elements.*
import pages.mixins.*
import state.CarReservationData
import state.Flow
import util.HotelItineraryData;
import util.ItineraryData
import util.PageName
import util.PricePageData
import util.SelectFlightsPageData
import util.ToggleJmx

import static org.junit.Assert.fail
import static util.Locators.BREADCRUMB_IDS
import state.PassengerData

@Mixin([PricingTableVerifications.class, ConfirmationVerifications.class])
public class PricePage extends BasePage {

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

    public PricePage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return "/reservations/price-reservations.html"
    }

    def clickContinue() {
        checkNoOops()
        // The button for air with car and hotel booking is 'priceSubmitButtonBottom'
        clickOnOneOfThese(PRICE_SUBMIT_BUTTON, By.id('priceSubmitButtonBottom'), By.id('priceItinerarySubmit'))
    }

    def verifyDisabledContinueButton() {
        verifyIfElementIsEnabled(PRICE_SUBMIT_BUTTON, "Continue Button", false)
    }

    def verifyOopsPointsBookingNonRRUser() {
        shouldShowOopsMessage("You must have a Rapid Rewards account to purchase with points.")
    }


    def clickSelectFlightBreadCrumbs() {
        clickPageBreadcrumb("Select Flights")
    }

    def verifyGrandTotalInPoints() {
        verifyElementPresent('Grand Total in Points', By.id("grandTotalPoints"))
    }

    def verifyBasicPage() {
        verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify price page) ---- checking no oops"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify price page) ---- checking no oops"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["PricePage"])
        pageVerificationErrorWrapper(PRICE_SUBMIT_BUTTON, PageName.PRICE_PAGE)
        if (flow.hasAir) {
            storeFlightNumbers()
        }
    }

    def verifyDepartureFlightDateAndTimeEquality(){
          flow.getDateAndTimeFlightDeparts().equals(getDateAndTimeThisFlightLeaves())  ? true : fail(" Departure DateTime in price page does not match with DateTime in select page ")
       }

    def verifyArrivalFlightDateAndTimeEquality(){
          flow.getDateAndTimeDepartingFlightArrives().equals(getDateAndTimeThisFlightArrives())  ? true : fail("Arrival DateTime in price page does not match with DateTime in select page ")
       }

    def verifyAirTranIndicatorNotPresent(String xpath, String text) {
        if (findElement(By.xpath(xpath)).getText().contains(text)) {
            fail("collapsed shoping cart should not contain any AirTran indicator, but found '$text'")
        }
    }

    def isShowingHotelUpsell() {
        verifyPage()
        waitForElement(By.id("hotelFilterCloseTo"))
    }

    def isShowingCarUpsell() {
        verifyPage()
        waitForElement(By.id("pickUpLocationText"))
    }

    def isShowingCarCrossSell() {
        boolean isShowing = true
        WebElement carCrossSell = waitForElement(CAR_CROSS_SELL_WIDGET, false)
        if (carCrossSell == null) {
            isShowing = false
        }
        return isShowing
    }

    def fillInCarPickupLocation(String location) {
        waitForElement(By.id("pickUpLocationText")).sendKeys DELETE_EXISTING + location
    }

    def clickPurchaseMorePoints() {
        String title = getTitle()
        waitForElement(By.id("not-enough-points-link")).click()
        waitForPageTitleToChangeOrOops(title)
    }

    def verifyAirSectionHeaderTotalPriceLabel() {
        List<WebElement> webElements = waitForElements(By.className("price_carhotel_widget_header"))
        boolean elementNotFoundFlag = true
        for (element in webElements) {
            if (element.getText().contains("Air")) {
                element.getText().contains("Total Price") ? elementNotFoundFlag = false : fail("Can not find the total price label on the price page air header")
                break
            }
        }
        if (elementNotFoundFlag) {
            throw new NoSuchElementException("The price page does not contain an air section header")

        }
    }

    def verifyAirSectionHeaderModifyLabel() {
        List<WebElement> webElements = waitForElements(By.className("price_carhotel_widget_header"))
        boolean elementNotFoundFlag = true
        for (element in webElements) {
            if (element.getText().contains("Air")) {
                element.getText().contains("Modify") ? elementNotFoundFlag = false : fail("Can not find the Modify price label on the price page air header")
                break
            }
        }
        if (elementNotFoundFlag) {
            throw new NoSuchElementException("The price page does not contain an air section header")
        }
    }

    def verifyMonthDateAndDayOfWeekOnCalendarIcon() {
        waitForElements(CALENDAR_DAY_OF_WEEK) ? true :
            fail("Calendar icon is not displaying as month, date, and day of week format on itinerary section of the price page")
    }

    def verifyMonthAndDateOnCalendarIcon() {
        waitForElements(CLASS_DATE) ? true :
            fail("Calendar icon is not displaying as month and date format on itinerary section of the price page")
    }

    def verifyTwoDigitHourTimeFormat() {
        String time = waitForElement(By.className("segmentTime")).getText()
        time.find('[0-2][0-9]:[0-5][0-9]') ? true : fail("Itinerary time is not displayed in the HH:MM format (double digit hour) ")
    }

    def verifyOneDigitHourTimeFormat() {
        String time = waitForElement(By.className("routingDetailsTimes")).getText()
        time.find('[1-2]*[0-9]:[0-5][0-9]') ? true : fail("Itinerary time is not displayed in the H:MM format (single digit hour) ")
    }

    def switchToPricingPage() {
        switchToWindow("Pricing and Restrictions")
    }

    def carCrossSellWidgetShouldBeHidden() {
        isShowingCarCrossSell().shouldBe false
    }

    def carCrossSellWidgetIsDisplayed() {
        isShowingCarCrossSell().shouldBe true
    }

    def verifyNotEnoughPointsInformationalMessage() {
        isBlueInformationalAlertDisplayed() shouldBe true
        getInformationalMessage().shouldContain "You need more points to purchase this flight", "You need more points to purchase this flight informational message was not found."
    }

    void storeFlightNumbers() {
        List<String> numbers = getFlightNumberWithClassName(FLIGHT_NUMBER_CELL_CLASS)
        if (numbers.isEmpty()) {
            numbers = getFlightNumberWithClassName(FLIGHT_NUMBER_CLASS)
        }

        itineraryData.departingFlight_number = new Integer(numbers.get(0).substring(1))
        if(itineraryData.itineraryType == "Round Trip") {
            itineraryData.returningFlight_number = new Integer(numbers.get(1).substring(1))
        }
        itineraryData.flightNumbers.addAll(numbers)
    }

    private List<String> getFlightNumberWithClassName(String className = FLIGHT_NUMBER_CELL_CLASS) {
        List<String> numbers = new ArrayList<String>();
        List<WebElement> flights = waitForElement(AIR_ITINERARY_CONTAINER_CLASS).findElements(By.className(className))
        for (WebElement flight in flights) {
            numbers.add(extractFlightNumberFromDivElement(flight));
        }
        return numbers;
    }


    /**
     * Determines the departure time for a one way flight
     *  @return departure time
     * TODO:Write a improved version to handle different scenarios for RoundTrip and OpenJaw
     *
     */
    public Date getDateAndTimeThisFlightLeaves(String timeClassName = SEGMENT_TIME_CLASS,String meridianClassName = SEGMENT_TIME_AMPM_CLASS,String dateClassName = DEPARTURE_LONG_DATE_CLASS) {
        def flightTime = waitForElement(AIR_ITINERARY_CONTAINER_CLASS).findElements(By.className(timeClassName)).first().getText().trim()
        def flightDate = waitForElement(AIR_ITINERARY_CONTAINER_CLASS).findElements(By.className(dateClassName)).first().getText().trim()
        def meridian = waitForElement(AIR_ITINERARY_CONTAINER_CLASS).findElements(By.className(meridianClassName)).first().getText().trim()

        return formatFlightDate(flightDate, flightTime, meridian)
    }

    /**
     * Determines the arrival time for a one way flight
     * @return arrival time
     * TODO:Write a improved version to handle different scenarios for RoundTrip and OpenJaw
     *
     */
    private Date getDateAndTimeThisFlightArrives(String timeClassName = SEGMENT_TIME_CLASS,String meridianClassName = SEGMENT_TIME_AMPM_CLASS,String dateClassName = DEPARTURE_LONG_DATE_CLASS) {
        def flightTime = waitForElement(AIR_ITINERARY_CONTAINER_CLASS).findElements(By.className(timeClassName)).last().getText().trim()
        def flightDate = waitForElement(AIR_ITINERARY_CONTAINER_CLASS).findElements(By.className(dateClassName)).last().getText().trim()
        def meridian = waitForElement(AIR_ITINERARY_CONTAINER_CLASS).findElements(By.className(meridianClassName)).last().getText().trim()

        return formatFlightDate(flightDate, flightTime, meridian)
    }

       static Date formatFlightDate(String date, String time, String meridian){
          DateTimeFormatter fmt = DateTimeFormat.forPattern("EEEE, MMM dd, yyyy")
          String dateAsString =  DateTime.parse(date,fmt).toString("yyyy M d") + " " + time.trim() + " " + meridian
          return Date.parse("yyyy M d h:m a", dateAsString)
      }

    def verifyTripSavedMessage() {
        waitForElement(SAVED_TRIP_TITLE).getText().shouldBe("Your trip has been saved!")
        waitForElement(SUCCESS_MODAL_CLOSE).findElement(SIMPLE_MODAL_CLOSE).click()
    }

    def verifyLoginModal() {
        waitForElement(MODAL_TITLE).getText().shouldBe("Login or enroll in Rapid Rewards to save an itinerary")
    }

    def loginToModal() {
        def user = data.getUser("goodUser")
        def number = user.getRRNumber()
        def password = user.getRRPassword()

        fillIn(RAPID_REWARDS_NUMBER, number)
        fillIn(ACCOUNT_PASSWORD, password)

        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.createRRMember(user)
        }

        waitForElement(LOGIN_MODAL_BUTTON).click()
        flow.isRapidRewards = true
        flow.isLoggedIn = true
        flow.userLoggedInRapidRewardsNumber = number
    }

    boolean messageIsContainedInCarCounterLocation(String message) {
        boolean messagesIsContained = false
        List<WebElement> carChartColumns = waitForElements(ITINERARY_TABLE)
        for (WebElement carColumn : carChartColumns) {
            String textItineraryColumn = carColumn.getText()
            if (textItineraryColumn.toUpperCase().contains(message.toUpperCase())) {
                messagesIsContained = true
                break
            }
        }
    return messagesIsContained
        }


    void fillInVehicleType() {
        if (carItineraryData.carType != null) {
            selectFromDropDownByIndex(VEHICLE_TYPE_DROPDOWN, carItineraryData.carType.carTypeColumnNumber())
        } else {
            selectFromDropDownByIndex(VEHICLE_TYPE_DROPDOWN, 1)
        }
    }

    void submitCarWidget() {
        waitForElement(CAR_CROSS_SELL_SUBMIT).click()
    }

    def verifyCarVendorVehicleTypeAvailable() {
        WebElement carWidget = waitForElement(CAR_CROSS_SELL_WIDGET)
        carWidget.findElement(CAR_VENDOR_DROPDOWN).isDisplayed().shouldBe true, "The car vendor dropdown is not being shown in the car widget."
        def categoryDropdown = waitForElement(VEHICLE_TYPE_DROPDOWN)
        if(categoryDropdown==null){
            fail("The vehicle type dropdown is not being shown in the car widget.")
        }        
    }

    void fillInCarVendor() {
        if (carItineraryData.carVendor != null) {
            waitForElement(CAR_VENDOR_DROPDOWN).sendKeys(DELETE_EXISTING + carItineraryData.carVendor)
            waitForElement(CAR_VENDOR_DROPDOWN).sendKeys(Keys.TAB)
        }
    }

    void verifyCyberArkOOPSMessage () {
        shouldShowOopsMessage("We are unable to complete your transaction using your approved instant credit card at this time. Please provide an alternate method of payment to complete your transaction. We apologize for the inconvenience")
    }

    def verifyChangePlaneInfoOnDepartureAirItinerary(){
        String changePlaneCityCode = itineraryData.outboundConnectingStation
        try {
            stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_ON, changePlaneCityCode)
        } catch (Exception e) {
            stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_OFF, changePlaneCityCode)
        }

    }

    def verifySwabizCompanyNameIsNotDisplayed(){
        isElementPresent(SWABIZ_COMPANY_NAME).shouldBe false , "The swabiz company name is displayed"
    }

    def verifySwabizCompanyNameIsDisplayed(){
        isElementPresent(SWABIZ_COMPANY_NAME).shouldBe true , "The swabiz company name is not displayed"
    }

	def clickOnChaseInstantCreditBanner() {
    if(new ToggleJmx().isOn("CHASE_SHOW_THE_MATH")){
          waitForElement(CHASE_INSTANT_CREDIT_BANNER_CLASS_NAME).click()
      }else{
          waitForElement(CHASE_INSTANT_CREDIT_BANNER).click()
      }
	}

    def verifyStaticCarCrossSell() {
        isElementPresent(CAR_CROSS_SELL_AD).shouldBe true, "The static car cross-sell on the price page is not displayed"
    }

    def verifyHertzCarCrossSell() {
        isElementPresent(CAR_CROSS_SELL_DYNAMIC_HERTZ).shouldBe true, "The HERTZ vendor into car cross-sell on the price page is not displayed"
    }

    void clickOnCarCrossSellAd() {
        WebElement accountLink = waitForElement(CAR_CROSS_SELL_AD)
        accountLink.click()
    }

    void clickOnHotelCrossSellSearchHotels() {
        WebElement searchHotelsButton = waitForElement(HOTEL_CROSS_SELL_SEARCH_BUTTON)
        searchHotelsButton.click()
    }

    def verifyCompanionPricing() {
        waitForElement(PRICE_SUBMIT_BUTTON)
        verifyElementPresent("Companion pass", COMPANION_PASS)
    }

    def verifyOopsNotSaveFlightAtTheMoment() {
        shouldShowOopsMessage("Sorry, we are unable to save your flight information at this time. Please try again and click \"save\" prior to opening a new browser window or tab.")
    }

    def clickBuyPointsLink() {
        waitForElement(By.id("not-enough-points-link")).click()
    }

    def verifyPointsDotcomPopupIsDisplayed() {
        waitForElement(By.className("pts-close-text")).getText().shouldContain "Close"
    }

    def selectHotelfromCrossSell(Integer index) {
        By HOTEL_FROM_CROSS_SELL = By.id("price_hotel_search_alternate_search_result_link_" + index)
        WebElement link = waitForElement(HOTEL_FROM_CROSS_SELL)
        hotelItineraryData.hotelId = getHotelIdFromUrl(link.getAttribute("href"))
        link.click()
    }

    public static getHotelIdFromUrl(String url) {
        url = url.substring(url.indexOf(HOTEL_ID_HREF)+HOTEL_ID_HREF.length())
        return url.substring(0, url.indexOf("&"))
    }

    def verifyCorrectParamsForPointsDotcomPopup() {
        def executor = (JavascriptExecutor) this.getDriverProvider().get()
        def paramsMap = executor.executeScript("return pointsAsyncInit()")

        paramsMap.partnerName.equals("southwestinline") ? true : fail("partnerName parameter is either missing or wrong.")
        paramsMap.firstName.equals(flow.userLoggedInFirstName) ? true : fail("firstName parameter is either missing or wrong.")
        paramsMap.lastName.equals(flow.userLoggedInLastName) ? true : fail("lastName parameter is either missing or wrong.")
        paramsMap.memberId.equals(flow.userLoggedInRapidRewardsNumber) ? true : fail("memberId parameter is either missing or wrong.")

        if(!paramsMap?.successUrl) {
            fail ("successUrl parameter is missing")
        }

        if(!paramsMap?.memberEmail) {
            fail ("memberEmail parameter is missing")
        }

        if(!paramsMap?.accountBalance) {
            fail ("accountBalance parameter is missing")
        }

        if(!paramsMap?.externalTxId) {
            fail ("externalTxId parameter is missing")
        }

        if(!paramsMap?.errorCallback) {
            fail ("errorCallback parameter is missing")
        }
    }

    public void verifyPriceMatchInPriceTableAndShoppingCart() {
        if (passengerData.containsAdultPassenger()) {
            roundPriceAndCompare(priceTable.departurePrice(), selectFlightsPageData.outboundFlightPrice * passengerData.adults.size())
            priceTable.departurePrice().shouldBeEqual shoppingCart.shoppingCartDeparturePrice() * passengerData.adults.size(), "Price Table Adult price ${priceTable.departurePrice()} and Shopping Cart Price ${shoppingCart.shoppingCartDeparturePrice()} did not match."
            if (!itineraryData.itineraryType == "One Way") {
                roundPriceAndCompare(priceTable.arrivalPrice(), selectFlightsPageData.inboundFlightPrice)
                priceTable.arrivalPrice().shouldBeEqual shoppingCart.shoppingCartReturnPrice(), "Price Table Adult price${priceTable.arrivalPrice()} and Shopping Cart Price ${shoppingCart.shoppingCartReturnPrice()} did not match."
            }
        }
        if (passengerData.containsSeniorPassenger()){
            if(itineraryData.seniorDepartingFlight_fareClass == "Senior") {
                roundPriceAndCompare(priceTable.departurePriceSenior(), selectFlightsPageData.outboundSeniorFlightPrice)
                if (!itineraryData.itineraryType == "One Way") {
                    roundPriceAndCompare(priceTable.arrivalPriceSenior(), selectFlightsPageData.inboundFlightPrice)

                }
            } else {
                roundPriceAndCompare(priceTable.departurePriceSenior(), selectFlightsPageData.outboundFlightPrice)
                if (!itineraryData.itineraryType == "One Way") {
                    roundPriceAndCompare(priceTable.arrivalPriceSenior(), selectFlightsPageData.inboundFlightPrice)
                }
            }
            if (!itineraryData.itineraryType == "One Way") {
                priceTable.arrivalPriceSenior().shouldBeEqual shoppingCart.shoppingCartReturnPriceSenior(), "Price Table Senior price${priceTable.arrivalPriceSenior()} and Shopping Cart Price ${shoppingCart.shoppingCartReturnPriceSenior()} did not match."
            }
            priceTable.departurePriceSenior().shouldBeEqual shoppingCart.shoppingCartDeparturePriceSenior(), "Price Table Senior price${priceTable.departurePriceSenior()} and Shopping Cart Price ${shoppingCart.shoppingCartDeparturePriceSenior()} did not match."
        }
    }

    private void roundPriceAndCompare(BigDecimal price, BigDecimal bugPagePrice) {
        if(!itineraryData.isPromoCertBooking()){
            BigDecimal roundedPrice = Math.ceil(price)
            BigDecimal roundedBugPagePrice = Math.round(bugPagePrice)
            /*(roundedPrice + 2).shouldBeGreaterThan roundedBugPagePrice, "the difference between ${roundedPrice} and ${roundedBugPagePrice} is more than 1"
            (roundedPrice - 2).shouldBeLessThan roundedBugPagePrice, "the difference between ${roundedPrice} and ${roundedBugPagePrice} is more than 1"*/
        }
    }

    def verifyAirTotalIsEqualToTripTotal() {
        BigDecimal tripTotal = waitForElement(TRIP_TOTAL).text.replace('$', '').toBigDecimal()
        tripTotal?.shouldBe priceTable.airTotal(), "Trip Total and Air Total do not match."
        pricePageData.tripTotal = tripTotal
    }

    def verifyAirTotalIsCorrectForPromoFlight() {
        String tripTotal = waitForElement(By.id(TRIP_TOTAL))?.text
        tripTotal.shouldBe "\$5.00", "The Air Total for a Promo Cert Flight is not correct.  Expected -> \$5.00 but got -> ${tripTotal}"
    }

    def verifyPageHeader() {
        StringBuilder expectedHeader = new StringBuilder()
        expectedHeader.append(itineraryData.departureCity).append(" to ")
        if(itineraryData.isRoundTripOrOpenJaw() && itineraryData.showOneTableInPriceOrConfirmationPage()) {
            expectedHeader.append(itineraryData.returnCity)
        } else {
            expectedHeader.append(itineraryData.arrivalCity)
        }
        waitForElement(By.className("itinerary")).text.shouldContain expectedHeader.toString(), "the stations in the page header were not displayed correctly"
    }

    def verifyAirTotalMatchInPriceTableAndShoppingCart() {
        priceTable.airTotal().shouldEqual waitForElement(TOTAL_CART_PRICE).text.replace('$', '').toBigDecimal(), "Air total does not match with shopping cart total"
    }

    def boolean isBlueInformationalAlertDisplayed() {
        return waitForElement(BLUE_INFORMATIONAL_ALERT_ICON).isDisplayed()
    }

    def getInformationalMessage() {
        return waitForElement(BLUE_INFORMATIONAL_ALERT_MESSAGE).getText()
    }

    def verifyAirTotalAndWidgetHeather() {
        BigDecimal widgetHeaderTotal = waitForElement(HEADER_TOTAL_AMOUNT).text.replace('$','').toBigDecimal()
        BigDecimal airTotal = waitForElement(AIR_TOTAL).text.replace('$','').toBigDecimal()
        airTotal.shouldEqual widgetHeaderTotal, "Air Total and widget header total were not the same"
    }

    def verifyCarrierLogos() {
        Map yaml = itineraryData.yamlItineraryData()

        if (itineraryData.departingFlight_carrierCode == 'FL'){
            isElementPresent(AIRTRAN_LOGO_OB).shouldBe true , "The outbound flight did not have AirTran logo"

            if (itineraryData.isRoundTrip()){
                verifyRoundTripLogos()
            }
        }
        else if (itineraryData.departingFlight_carrierCode == 'WN'){
            isElementPresent(SOUTHWEST_LOGO_OB).shouldBe true , "The outbound flight did not have Southwest logo"

            if (itineraryData.isRoundTrip()){
                verifyRoundTripLogos()
            }
        }
    }

    def verifyRoundTripLogos(){
        if (itineraryData.arrivingFlight_carrierCode == 'WN') {
            isElementPresent(SOUTHWEST_LOGO_IB).shouldBe true , "The inbound airline did not have Southwest logo"
        }
        else if (itineraryData.arrivingFlight_carrierCode == 'FL'){
            isElementPresent(AIRTRAN_LOGO_IB).shouldBe true , "The inbound airline did not have AirTran logo"
        }
    }
}
