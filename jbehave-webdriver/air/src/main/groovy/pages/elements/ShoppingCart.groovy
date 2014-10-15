package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.BasePage
import state.Flow
import util.ItineraryData
import util.PricePageData
import util.SelectFlightsPageData

import static org.junit.Assert.fail
import state.PassengerData
import util.PurchasePageData

class ShoppingCart extends BasePage {

    private static final By PLUS_AIR_BUTTON_CSS = By.cssSelector("#myCartAirSection .my_cart_section_header_toggle")
    private static final By UM_FEE_XPATH = By.xpath("//a[@id='umChargeLink']/parent::div/parent::div")
    private static final By UM_CHARGE_LINK_ID = By.id("umChargeLink")
    private static final By EARLY_BIRD_ADDON = By.id("myCartEarlyBirdProduct")
    private static final By UM_CHARGE_FLY_OUT = By.id("umChargeDiv")
    private static final By MY_CART_MODAL_LINK_AIR = By.id("mycart_modal_link_air")
    private static final By MY_CART_MODAL_LINK_CAR = By.id("mycart_modal_link_car")
    private static final By MY_CART_MODAL_LINK_HOTEL = By.id("mycart_modal_link_hotel")
    private static final By CART_REMOVE_BUTTON = By.className("my_cart_remove_button");
    private static final By MY_CART_MODAL = By.id("hotel_section_header_remove_form")
    private static final By SAVE_TRIP_BUTTON = By.id("saveTripButton")
    private static final By CHECKOUT_BUTTON = By.id("checkoutButton")
    private static final By SHOPPING_CART_AIR_EXPANDED = By.xpath("//span[@class ='my_cart_section_header_toggle my_cart_section_header_toggle_open']")
    private static final By SHOPPING_CART_AIR_COLLAPSED = By.xpath("//span[@class ='my_cart_section_header_toggle my_cart_section_header_toggle_closed']")
    private static final By SHOPPING_CART_EXPANDED = By.xpath("//span[@class ='global_account_bar_header_toggle global_account_bar_header_toggle_open']")

    private static final By GLOBAL_ACCOUNT_BAR_CONTENT_VISIBLE = By.className("global_account_bar_content_visible")
    private static final By OUTBOUND_CONTAINER = By.id("outbound")
    private static final By INBOUND_CONTAINER = By.id("inbound")

    private static final By AIR_SHOPPING_DATES_COLLAPSED = By.xpath("//div[@id='shortFlightTime']")
    private static final By AIR_SHOPPING_AIRPORTS_COLLAPSED = By.xpath("//div[@id='shortFlightAirports']")
    private static final By AIR_SHOPPING_AIR_TOTAL = By.id("totalAirPrice")
    private static final By TOTAL_PER_PERSON = By.className("totalPerPerson")
    private static final By BASE_FARE = By.className("totalBaseFare")
    private static final By DEPART_FARE_TYPE = By.cssSelector(".my_cart_container_half_left.outbound")
    private static final By RETURN_FARE_TYPE = By.cssSelector(".my_cart_container_half_left.inbound")
    private static final By MODIFY_LINK = By.id('air_section_header_modify')
    private static final By QUANTITY = By.className("ADT")
    private static final By GRAND_TOTAL = By.className("tripGrandTotal")
    private static final By SHOPPING_CART = By.cssSelector(".global_account_bar_header_active span.global_account_bar_header_toggle_open")
    private static final By SENIOR_DEPART_FARE_TYPE = By.cssSelector("#outbound > div.my_cart_section_content_expanded_view > #air_cart_presenter_senior > div.my_cart_container_half_left")
    private static final By SENIOR_RETURN_FARE_TYPE = By.cssSelector("#inbound > div.my_cart_section_content_expanded_view > #air_cart_presenter_senior > div.my_cart_container_half_left")
    private static final By DEPARTURE = By.className("departure")
    private static final By ARRIVAL = By.className("arrival")
    private static final By CALENDAR_DAY = By.className("smallCalendarDayOfWeek")
    private static final By ITINERARY_DETAIL = By.className("itineraryDetail")
    private static final By TOTAL_CART_PRICE = By.id("totalCartPrice")
    private static final By DEPARTURE_TIME = By.cssSelector(".departure .time")
    private static final By ARRIVAL_TIME = By.cssSelector(".arrival .time")
    private static final By FLIGHT_NUMBER = By.className("flight_number")
    private static final By RECONCILE_PAGE_FLIGHT_NUMBER = By.className("flightNumber")
    private static final By RECONCILE_PAGE_DEPARTURE = By.cssSelector(".flightOrigin .station")
    private static final By RECONCILE_PAGE_ARRIVAL = By.cssSelector(".flightDestination .station")
    private static final By RECONCILE_PAGE_OUTBOUND_DEPARTURE_TIME = By.cssSelector(".flightOrigin .time")
    private static final By RECONCILE_PAGE_OUTBOUND_ARRIVAL_TIME = By.cssSelector(".flightDestination .time")
    private static final By UM_BOLD_TEXT = By.className("boldText")
    private static final By TAXES_FEES_VALUE = By.className("total_taxes_and_fees")
    private static final By AIR_TOTAL = By.id("myCurrentTripAirTotalPrice")

    FeeCalculator feeCalculator
    SelectFlightsPageData selectFlightsPageData
    ItineraryData itineraryData
    PricePageData pricePageData
    PassengerData passengerData
    PurchasePageData purchasePageData
    Flow flow

    public ShoppingCart(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }


    def collapseAirShoppingCart() {
         waitForElement(SHOPPING_CART_AIR_EXPANDED).click()
     }

     def expandAirShoppingCart() {
         waitForElement(SHOPPING_CART_AIR_COLLAPSED).click()
     }

    def verifyShoppingCartCollapsed() {
        verifyAirTranIndicatorOnFlightPresent(false)
        operatedByAirTranVisible(false)
    }

    def verifyExpandedShoppingAirTranIndicator() {
        operatedByAirTranVisible(true)
        verifyAirTranIndicatorOnFlightPresent(true)
    }

    def verifyAirlineInShoppingCart(String airline, String route) {
        if (airline == "WN") {
            verifyElementPresent("Correct WN Route details in $route",
                    By.xpath("//div[@class='flight_number boldText' and contains (text(), '$route') and (not(contains(text(), '*')))]"))
        } else if (airline == "FL") {
            verifyElementPresent("Correct FL Route details in $route",
                    By.xpath("//div[@class='flight_number boldText' and contains (text(), '$route') and contains(text(), '*')]"))
            operatedByAirTranVisible(true)
        } else {
            fail "$airline is not a valid airline"
        }
    }

    void removeShoppingCart() {
         waitForElement(MY_CART_MODAL_LINK_AIR).click()
     }

    void removeHotelFromShoppingCart() {
         waitForElement(MY_CART_MODAL_LINK_HOTEL).click()
    }

    void removeCarFromShoppingCart() {
         waitForElement(MY_CART_MODAL_LINK_CAR).click()
    }

    void clickOnRemoveFromCartButtom() {
        verifyModalRemoveModal()
        List elements = waitForElements(CART_REMOVE_BUTTON,false)

        for (WebElement element : elements){
            if(element.isDisplayed() && element.isEnabled())
                element.click()
        }

    }

    private verifyAirTranIndicatorOnFlightPresent(boolean shouldHaveFlightIndication) {
        if (shouldHaveFlightIndication) {
            waitForElement(GLOBAL_ACCOUNT_BAR_CONTENT_VISIBLE).text.shouldContain "*", "No flight contains the indication for AirTran"
        }
        else {
            waitForElement(GLOBAL_ACCOUNT_BAR_CONTENT_VISIBLE).text.shouldNotContain "*", "A flight contains the indication for AirTran"
        }
    }

      private operatedByAirTranVisible(boolean shouldHaveAirTranIndication) {
        if (shouldHaveAirTranIndication) {
            waitForElement(GLOBAL_ACCOUNT_BAR_CONTENT_VISIBLE).text.shouldContain "*Operated by AirTran", "No indicator present for operated by AirTran"
        }
        else {
            waitForElement(GLOBAL_ACCOUNT_BAR_CONTENT_VISIBLE).text.shouldNotContain "*Operated by AirTran", "Indicator present for operated by AirTran when it should not be there"

        }
    }

    void verifyBusinessSelectIsInShoppingCart() {
        verifyTextPresent("Business Select", By.id("my-cart-air-content"))
    }

    void verifyModalRemoveModal() {
        verifyElementPresent("Remove Confirmation", waitForElement(By.id("simplemodal-container")))
    }

    void clickOnPlusAirButton() {
        verifyPage()
        waitForElement(PLUS_AIR_BUTTON_CSS).click()
    }

    void verifyUMFeeUnderShoppingCart() {
        WebElement UMFeeDetails = waitForElement(UM_FEE_XPATH)
        UMFeeDetails.text.shouldContain "Unaccompanied Minor Fee", "The text 'Unaccompanied Minor Fee' should be present"
        UMFeeDetails.findElement(UM_BOLD_TEXT).text.replace("\$","").toBigDecimal().shouldEqual purchasePageData.guardianFee, "The Unaccompanied Minor Fee was not correct"
    }

    boolean isEarlyBirdAddOnPresent() {
        isElementPresent(EARLY_BIRD_ADDON)
    }

    UMChargeFlyOut openUMChargeFlyOut() {
        waitForElement(UM_CHARGE_LINK_ID).click()
        return getUMChargeFlyOut()
    }

    UMChargeFlyOut getUMChargeFlyOut() {
        return new UMChargeFlyOut(waitForElement(UM_CHARGE_FLY_OUT))
    }

    void clickSaveFlightButton() {
        waitForElement(SAVE_TRIP_BUTTON).click()
    }

    public BigDecimal shoppingCartDeparturePrice() {
        return waitForElement(By.cssSelector(".adult_fare.my_cart_container_half_right .totalPerPerson")).text.replace('$','').toBigDecimal()
    }

    public BigDecimal shoppingCartDeparturePriceSenior() {
        return waitForElement(By.cssSelector(".senior_fare.my_cart_container_half_right .totalPerPerson")).text.replace('$','').toBigDecimal()
    }

    public BigDecimal shoppingCartReturnPrice() {
        return waitForElement(By.xpath("//div[@class='adult_fare my_cart_container_half_right boldText inbound']")).text.replace('$', '').toBigDecimal();
    }

    public BigDecimal shoppingCartReturnPriceSenior() {
        List<WebElement> priceList = findElements(By.cssSelector(".senior_fare.my_cart_container_half_right .totalPerPerson"))
        return priceList.get(1).text.replace('$', '').toBigDecimal()
    }

    public WebElement airInShoppingCartIsExpanded(){
        isElementPresent(SHOPPING_CART_AIR_EXPANDED).shouldBe true, "Air widget was not expanded in the shopping cart"
    }

    public WebElement airInShoppingCartIsCollapsed(){
        isElementPresent(AIR_SHOPPING_DATES_COLLAPSED)
    }

    public def verifyAirInShoppingCartIsCollapsed() {
        isElementPresent(AIR_SHOPPING_DATES_COLLAPSED)
    }

    public WebElement modifyLink(){
        waitForElement(By.className("mycart_link"))
    }

    public WebElement removeLink() {
        waitForElement(By.id("mycart_modal_link_air"))
    }

    public WebElement shoppingCartExpanded() {
        waitForElement(By.xpath("//span[@class='global_account_bar_header_toggle global_account_bar_header_toggle_open']"))
    }

    def verifyFlightNumber(By flightNumberContainer = FLIGHT_NUMBER){
        List<WebElement> flightNumbers = findElements(flightNumberContainer)
        flightNumbers.get(0).text.shouldContain selectFlightsPageData.departingFlight_number, "Outbound flight number in the shopping cart did not match flight number on the Bug page"
        if (flow.hasConnectionFlight){
            flightNumbers.get(0).text.shouldContain selectFlightsPageData.departingConnectingFlight_number, "Connecting outbound flight number in the shopping cart did not match flight number on the Bug page"
        }
        if (itineraryData.isRoundTripOrOpenJaw()) {
            if (!itineraryData.showOneTableInPriceOrConfirmationPage()) {
                flightNumbers.get(1).text.shouldContain selectFlightsPageData.returningFlightNumber, "Inbound flight number in the shopping cart did not match flight number on the Bug page"
                if(flow.hasInboundConnectionFlight) {
                    flightNumbers.get(1).text.shouldContain selectFlightsPageData.returningConnectingFlight_number, "Connecting inbound flight number in the shopping cart did not match flight number on the Bug page"
                }
            } else {
                flightNumbers.get(0).text.shouldContain selectFlightsPageData.returningFlightNumber, "Inbound flight number in the shopping cart did not match flight number on the Bug page"
            }
        }
    }

    def verifyFlightNumberOnReconcilePage(){
        verifyFlightNumber(RECONCILE_PAGE_FLIGHT_NUMBER)
    }

    def verifyDepartureAndArrivalTimeOnReconcilePage() {
        String outboundDepartureTime = waitForElement(RECONCILE_PAGE_OUTBOUND_DEPARTURE_TIME).text
        String outboundArrivalTime = waitForElement(RECONCILE_PAGE_OUTBOUND_ARRIVAL_TIME).text
        outboundDepartureTime.replaceAll("\\s", "").shouldContain selectFlightsPageData.outboundDepartureTime.replaceAll("\\s", "").toUpperCase(), "Outbound flight departure time in the shopping cart did not match flight time on Bug Page"
        String arrivalTime
        if(flow.hasConnectionFlight) {
            arrivalTime = selectFlightsPageData.outboundConnectionArrivalTime
            arrivalTime = arrivalTime.subSequence(0,arrivalTime.size()-2)
        } else {
            arrivalTime = selectFlightsPageData.outboundArrivalTime
        }
        outboundArrivalTime.shouldContain arrivalTime, "Outbound flight arrival time in the shopping cart did not match flight time on Bug Page"
    }

    def verifyDepartureAndArrivalTime() {
        String outboundDepartureTime = waitForElement(OUTBOUND_CONTAINER).findElement(DEPARTURE_TIME).text
        String outboundArrivalTime = waitForElement(OUTBOUND_CONTAINER).findElement(ARRIVAL_TIME).text
        outboundDepartureTime.replaceAll("\\s", "").shouldContain selectFlightsPageData.outboundDepartureTime.replaceAll("\\s", "").toUpperCase(), "Outbound flight departure time in the shopping cart did not match flight time on Bug Page"
        if(flow.hasConnectionFlight) {
            outboundArrivalTime.replaceAll("\\s", "").shouldContain selectFlightsPageData.outboundConnectionArrivalTime.toUpperCase(), "Outbound flight arrival time in the shopping cart did not match flight time on Bug Page"
        } else if(itineraryData.showOneTableInPriceOrConfirmationPage()) {
            outboundArrivalTime.shouldContain selectFlightsPageData.inboundArrivalTime, "Outbound flight arrival time in the shopping cart did not match flight time on Bug Page"
        } else {
            outboundArrivalTime.shouldContain selectFlightsPageData.outboundArrivalTime, "Outbound flight arrival time in the shopping cart did not match flight time on Bug Page"
        }
        if (itineraryData.isRoundTripOrOpenJaw() && !itineraryData.showOneTableInPriceOrConfirmationPage())  {
            String inboundDepartureTime = waitForElement(INBOUND_CONTAINER).findElement(DEPARTURE_TIME).text
            String inboundArrivalTime = waitForElement(INBOUND_CONTAINER).findElement(ARRIVAL_TIME).text
            inboundDepartureTime.replaceAll("\\s", "").shouldContain selectFlightsPageData.inboundDepartureTime.replaceAll("\\s", "").toUpperCase(), "Inbound flight departure time did not match flight time on Bug Page"
            if(flow.hasInboundConnectionFlight) {
                inboundArrivalTime.replaceAll("\\s", "").shouldContain selectFlightsPageData.inboundConnectionArrivalTime.toUpperCase(), "Inbound flight arrival time in the shopping cart did not match flight time on Bug Page"
            } else {
                inboundArrivalTime.shouldContain selectFlightsPageData.inboundArrivalTime, "Inbound flight arrival time did not match flight time on Bug Page"
            }
        }
    }

    void verifyShoppingCartFoxVendorMessage() {
        verifyTextPresent("You’ll be charged for this rental upon car rental pick-up.", By.id("myCartCarSection"))
    }

    void verifyShoppingCartCarVendorMessage() {
        verifyTextPresent("You’ll be charged for this rental upon rental car return.", By.id("myCartCarSection"))
    }


    def verifyDepartureAndArrivalCities() {
        String outboundDeparture = waitForElement(OUTBOUND_CONTAINER).findElement(DEPARTURE).text
        outboundDeparture.shouldContain itineraryData.departureStation, "Departure station did not match departure station on the Bug page"
        String outboundArrival = waitForElement(OUTBOUND_CONTAINER).findElement(ARRIVAL).text
        if (itineraryData.showOneTableInPriceOrConfirmationPage()) {
            outboundArrival.shouldContain itineraryData.returnStation, "Arrival station did not match arrival station on the Bug page"
        } else {
            outboundArrival.shouldContain itineraryData.arrivalStation, "Arrival station did not match arrival station on the Bug page"
            if (itineraryData.itineraryType == "Round Trip") {
                String inboundDeparture = waitForElement(INBOUND_CONTAINER).findElement(DEPARTURE).text
                inboundDeparture.shouldContain itineraryData.arrivalStation, "Arrival station did not match arrival station on the Bug page"
            } else if (itineraryData.itineraryType == "Open Jaw") {
                String inboundArrival = waitForElement(INBOUND_CONTAINER).findElement(ARRIVAL).text
                inboundArrival.shouldContain itineraryData.returnStation, "Return station did not match return station on the Bug page"
            }
        }
    }

    def verifyDepartureAndArrivalCitiesOnReconcilePage() {
        String outboundDeparture = waitForElement(RECONCILE_PAGE_DEPARTURE).text
        outboundDeparture.shouldContain itineraryData.departureStation, "Departure station did not match departure station on the Bug page"
        String outboundArrival = waitForElement(RECONCILE_PAGE_ARRIVAL).text
        outboundArrival.shouldContain itineraryData.arrivalStation, "Arrival station did not match arrival station on the Bug page"
    }

    def verifyDateInContainer(By dateContainer = ITINERARY_DETAIL, String direction = "outbound") {
        List <String> outBoundDepartureDate = findElement(dateContainer).findElement(CALENDAR_DAY).text.split(" ")
        List <String> ddateBreakDown
        if(direction.equals("outbound")) {
            ddateBreakDown = itineraryData.departureDate.toString().split(" ")
        } else {
            ddateBreakDown = itineraryData.returnDate.toString().split(" ")
        }
        String dmonth = ddateBreakDown[1].toLowerCase()
        String ddate = ddateBreakDown[2].toLowerCase()
        outBoundDepartureDate[0].toLowerCase().shouldContain dmonth, "Departure date did not match departure date on the Home page"
        ddate.shouldContain outBoundDepartureDate[1].split("\n")[0], "Departure date did not match departure date on the Home page"
    }

    def verifyDate() {
        verifyDateInContainer(OUTBOUND_CONTAINER)
        if (itineraryData.isRoundTripOrOpenJaw() && !itineraryData.showOneTableInPriceOrConfirmationPage()) {
            verifyDateInContainer(INBOUND_CONTAINER, "inbound")
        }
    }

    BigDecimal tripTotal() {
        return waitForElement(TOTAL_CART_PRICE).text.replace('$','').toBigDecimal()
    }

    public def verifyCollapsedAirDepartureAndArrivalDates() {
            List<WebElement> pageDates = waitForElements(AIR_SHOPPING_DATES_COLLAPSED)
        List <String> departureItineraryDates = itineraryData.departureDate.toString().split(" ")
        String departureItineraryMonth = departureItineraryDates[1].toLowerCase()
        String departureItineraryDay = departureItineraryDates[2].toLowerCase().startsWith("0") ? departureItineraryDates[2].toLowerCase().charAt(1) : departureItineraryDates[2].toLowerCase()
        pageDates[0].text.toLowerCase().shouldContain departureItineraryMonth, "Air Collapsed Shopping Cart Departure MONTH did not match departure date from the Select Flights Page."
        pageDates[0].text.toLowerCase().shouldContain departureItineraryDay, "Air Collapsed Shopping Cart Departure DAY did not match departure date from the Select Flights Page."
        if (itineraryData.isRoundTripOrOpenJaw())  {
            List <String> returnItineraryDates = itineraryData.returnDate.toString().split(" ")
            String returnItineraryMonth = returnItineraryDates[1].toLowerCase()
            String returnItineraryDay = returnItineraryDates[2].toLowerCase().startsWith("0") ? returnItineraryDates[2].toLowerCase().charAt(1) : returnItineraryDates[2].toLowerCase()
            pageDates[1].text.toLowerCase().shouldContain returnItineraryMonth, "Air Collapsed Shopping Cart Departure MONTH did not match departure date from the Select Flights Page."
            pageDates[1].text.toLowerCase().shouldContain returnItineraryDay, "Air Collapsed Shopping Cart Departure DAY did not match departure date from the Select Flights Page."
        }
    }

    public def verifyCollapsedAirDepartureAndArrivalCities() {
        List<WebElement> pageCities = waitForElements(AIR_SHOPPING_AIRPORTS_COLLAPSED)
        String outboundDepartureCity = pageCities[0].findElement(By.className("to_from_arrow_right")).text
        outboundDepartureCity.shouldContain itineraryData.departureStation, "Outbound Departure station did not match Outbound Departure station from the Select Flights Page."
        if (itineraryData.itineraryType == "Round Trip") {
            String inboundDepartureCity = pageCities[1].findElement(By.className("to_from_arrow_right")).text
            inboundDepartureCity.shouldContain itineraryData.arrivalStation, "Return arrival station did not match Arrival station from the Select Flights Page.e"
        }
    }

    public def verifyCollapsedAirTotal() {
        BigDecimal securityFee = feeCalculator.calculateSecurityFee()
        waitForElement(AIR_SHOPPING_AIR_TOTAL).text.replace(' ', '').replace('$', '').replace(',', '').toBigDecimal().shouldBe securityFee, "Air Trip Total did not match the correct amount for a Promo Cert Round Trip flight."
    }

    def modifyAndRemoveLinks() {
        verifyModifyLink()
        waitForElement(By.id('air_section_header_remove')).text.shouldBe "Remove", "Remove link was not present"
    }

    def verifySaveAndCheckoutButton() {
        verifySaveButton()
        waitForElement(CHECKOUT_BUTTON).text.shouldBe "Checkout", "Checkout Button was not present in the shopping cart"
    }

    def verifySaveButton() {
        waitForElement(SAVE_TRIP_BUTTON).getAttribute("value").shouldBe "Save Flight", "Save Flight Button was not present in the shopping cart"
    }

    def verifySaveTripButton() {
        isElementPresent(SAVE_TRIP_BUTTON) shouldBe true, "Save trip button was not present in the shopping cart"
    }

    def verifyShoppingCartIsExpanded() {
        waitForElement(SHOPPING_CART_EXPANDED, false).shouldNotBe null, "Shooping cart was not expanded"
    }

    def verifyAirWidgetIsCollapsed() {
        waitForElement(SHOPPING_CART_AIR_COLLAPSED, false).shouldNotBe null, "Air widget was expanded"
    }

    def verifyTotalAndTotalTripOnShoppingCartAreEqual() {
        BigDecimal tripTotal = tripTotal()
        BigDecimal airTotal = getAirTotal()
        tripTotal.shouldBe airTotal, "Trip total on the purchase page '${tripTotal}' did not equal total on shopping cart '${airTotal}'"
    }

    def BigDecimal getAirTotal() {
        waitForElement(AIR_SHOPPING_AIR_TOTAL).text.replace('$', '').toBigDecimal()
    }

    def verifyOutboundAndInboundTotals() {
        List<WebElement> shoppingCartTotals = waitForElements(TOTAL_PER_PERSON)
        if(flow.isRapidRewardsPointsPurchaseOnly){
            BigDecimal fareInPage = shoppingCartTotals[0].text.replace(',','').replace('pts','').trim().toBigDecimal()
            fareInPage.shouldEqual selectFlightsPageData.outboundFlightPoints, "The fare in points was not the same as in the BUG page"
        } else {
            if (passengerData.containsAdultPassenger()){
                (shoppingCartTotals[0].text.replace('$','').replace('/Person','').toBigDecimal()*passengerData.adults.size()).shouldBe pricePageData.outboundTotal, "The Adult Outbound cost breakdown was incorrect"
            }
            if (passengerData.containsSeniorAndAdultPassengers()){
                shoppingCartTotals[1].text.replace('$','').toBigDecimal().shouldBe pricePageData.outboundTotalSenior, "The Senior Outbound cost breakdown was incorrect"
            } else if (passengerData.containsSeniorPassenger()){
                shoppingCartTotals[0].text.replace('$','').toBigDecimal().shouldBe pricePageData.outboundTotalSenior, "The Senior Outbound cost breakdown was incorrect"
                if(itineraryData.isRoundTripOrOpenJaw()) {
                    (shoppingCartTotals[1].text.replace('$','').toBigDecimal()*passengerData.seniors.size()).shouldBe pricePageData.inboundTotalSenior, "The inbound cost breakdown was incorrect"
                }

            }
            //Adding logic for senior passenger. Need to modify the method to add the adult and senior passengers too
            if(itineraryData.isRoundTripOrOpenJaw() && !passengerData.containsSeniorPassenger() && !itineraryData.lessThan4HourLayover) {
                (shoppingCartTotals[1].text.replace('$','').toBigDecimal()*passengerData.adults.size()).shouldBe pricePageData.inboundTotal, "The inbound cost breakdown was incorrect"
            }
        }
    }

    def verifyTotalCostBreakdown() {
        List<WebElement> costBreakdowns = waitForElements(BASE_FARE)
        if(flow.isRapidRewardsPointsPurchaseOnly){
            selectFlightsPageData.totalOutboundInboundFlightPoints.shouldEqual  costBreakdowns[0].text.replace(',','').replace(' pts','').toBigDecimal()
        } else if (passengerData.containsSeniorAndAdultPassengers()) {
            pricePageData.subTotal.shouldEqual  costBreakdowns[0].text.replace('$','').toBigDecimal(), "The total and cost breakdown did not match the one in price table for Adult"
            pricePageData.seniorSubTotal.shouldEqual costBreakdowns[1].text.replace('$','').toBigDecimal(), "The total and cost breakdown did not match the one in price table for Senior"
        } else if (passengerData.containsAdultPassenger()) {
            pricePageData.subTotal.shouldEqual  costBreakdowns[0].text.replace('$','').toBigDecimal(), "The total and cost breakdown did not match the one in price table for Adult"
        } else if (passengerData.containsSeniorPassenger()) {
            pricePageData.seniorSubTotal.shouldEqual costBreakdowns[0].text.replace('$','').toBigDecimal(), "The total and cost breakdown did not match the one in price table for Senior"
        }
    }

    def verifyGovtTaxesFeesLinkIsPresent() {
        isElementPresent(By.id("taxesAndFeesLink"))
    }

    def verifyFareType() {
        String outBoundFareType
        if (passengerData.containsAdultPassenger()) {
            outBoundFareType = waitForElement(DEPART_FARE_TYPE).text.replaceAll('\\s+','')
            verifyOutboundFaretype(outBoundFareType)
            if (itineraryData.isRoundTripOrOpenJaw() && !itineraryData.showOneTableInPriceOrConfirmationPage()) {
                String inBoundFareType = waitForElement(RETURN_FARE_TYPE).text.replaceAll('\\s+','')
                verifyInboundFareType(inBoundFareType)
            }
        }
        if (passengerData.containsSeniorPassenger()) {
            outBoundFareType = waitForElement(SENIOR_DEPART_FARE_TYPE).text.replaceAll('\\s+','')
            verifyOutboundFaretype(outBoundFareType, true)
            if (itineraryData.isRoundTripOrOpenJaw()) {
                String inBoundFareType = waitForElement(SENIOR_RETURN_FARE_TYPE).text.replaceAll('\\s+','')
                verifyInboundFareType(inBoundFareType)
            }
        }

    }

    private void verifyInboundFareType(String inBoundFareType) {
        if(itineraryData.isPromoCertBooking()){
            inBoundFareType.toLowerCase().shouldContain "award", "Return fare type did not match departure fare type"
        }
        else{
            inBoundFareType.replaceAll('\\s','').shouldContain itineraryData.arrivingFlight_fareClass, "Inbound fare type did not match arrival fare type"
        }
    }

    private void verifyOutboundFaretype(String outBoundFareType, Boolean checkingSenior = false) {
        if (itineraryData.isPromoCertBooking()) {
            outBoundFareType.toLowerCase().shouldContain "award", "Outbound fare type did not match departure fare type"
        } else {
            if(checkingSenior){
                outBoundFareType.shouldContain itineraryData.seniorDepartingFlight_fareClass, "Senior outbound fare type did not match departure fare type"
            } else {
                outBoundFareType.shouldContain itineraryData.departingFlight_fareClass, "Outbound fare type did not match departure fare type"
            }
        }
    }

    def verifyTripTotal() {
        BigDecimal tripTotal = tripTotal()
        totalCostBreakDown().shouldEqual tripTotal, "Trip total does not match total cost breakdown in Shopping Cart"
        pricePageData.airTotal.shouldEqual tripTotal, "Air total does not match Trip total in Shopping Cart"
    }

    private BigDecimal totalCostBreakDown() {
        List<WebElement> costsBreakdown = waitForElements(BASE_FARE)
        BigDecimal totalCostBreakdown = 0
        for (WebElement costBreakdown: costsBreakdown) {
            totalCostBreakdown += costBreakdown.text.replace('$', '').toBigDecimal()
        }
        return totalCostBreakdown
    }

    def verifyFareBreakdown() {
        BigDecimal flightFare
        if(flow.isRapidRewardsPointsPurchaseOnly) {
            flightFare = selectFlightsPageData.outboundFlightPoints
        } else {
            flightFare = pricePageData.outboundTotal
        }
        getTotalPerPerson().shouldEqual flightFare, "Outbound total did not match the selected one in the shopping cart"
    }

    def verifyModifyLink() {
        WebElement modifyLink = waitForElement(MODIFY_LINK,false, 5 )?:waitForElement(By.className("myCurrentTripModifyLink"))
        modifyLink.text.split(" ")[0].shouldBe "Modify", "Modify link was not present"
    }

    def verifyAirTotal() {
        BigDecimal totalPerPerson = getTotalPerPerson()
        BigDecimal quantity = getQuantity()
        BigDecimal airTotal = airTotal()
        (totalPerPerson * quantity).shouldEqual airTotal
    }

    def BigDecimal airTotal() {
        if(flow.isRapidRewardsPointsPurchaseOnly){
            waitForElement(BASE_FARE).text.replace('pts','').replace(',', '').toBigDecimal()
        } else {
            waitForElement(BASE_FARE).text.replace(' ', '').replace('$', '').replace(',', '').toBigDecimal()
        }
    }

    def getTotalPerPerson() {
        if(flow.isRapidRewardsPointsPurchaseOnly) {
            waitForElement(By.cssSelector("div.FFP")).text.split(' ')[1].replace(',','').toBigDecimal()
        }else {
            waitForElement(TOTAL_PER_PERSON).text.split('/')[0].replace(' ','').replace('$','').replace(',','').toBigDecimal()
        }
    }

    def getQuantity() {
        if(flow.isRapidRewardsPointsPurchaseOnly) {
            waitForElement(By.cssSelector("div.FFP")).text.split('x')[1].split(' =')[0].toBigDecimal()
        } else {
            waitForElement(QUANTITY).text.split('x')[1].split(' =')[0].toBigDecimal()
        }
    }

    def verifyGrandTotal() {
        if (flow.isRapidRewardsPointsPurchaseOnly) {
            waitForElement(By.id("myCurrentTripAirTotalPrice")).text.split(' ')[2].replace('$','').toBigDecimal().shouldEqual grandTotal(), "Air total and grand total in dollars  were not equal in the reconcile page"
        } else {
            airTotal().shouldEqual grandTotal(), "Air total and grand total were not equal"
        }
    }

    def grandTotal() {
        waitForElement(GRAND_TOTAL).text.replace(' ', '').replace('$', '').replace(',', '').toBigDecimal()
    }

    def verifyShoppingCartExpanded() {
        isElementPresent(SHOPPING_CART)
    }

    def verifyTripTotalWithUMFee() {
        BigDecimal UMFee = waitForElement(UM_FEE_XPATH).findElement(UM_BOLD_TEXT).text.replace("\$","").toBigDecimal()
        BigDecimal totalUMFee = feeCalculator.calculateGuardianCharge()

        UMFee.shouldBe totalUMFee, "UM fee does not equal '${totalUMFee}'"
        if(!flow.isRapidRewardsPointsPurchaseOnly) {
            (pricePageData.airTotal + totalUMFee).shouldBe totalCostBreakDown() + totalUMFee, "Trip total does not match total cost breakdown in Shopping Cart"
        }
        tripTotal().shouldBe(pricePageData.airTotal + totalUMFee)
    }

    def verifyGovtTaxesFees() {
        waitForElement(TAXES_FEES_VALUE).text.trim().replace('$','').toBigDecimal().shouldEqual pricePageData.subTotal
    }

    def verifyShoppingCartAirTotal() {
        BigDecimal airTotal = waitForElement(AIR_TOTAL).text.split(' ')[2].replace('$','').toBigDecimal()
        airTotal.shouldEqual pricePageData.newTicketTotal, "Air total in reconcile page was not equal as the new ticket total in reprice page"

    }
}
