package steps

import domain.AirReservation
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Aliases
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.elements.FlightSearchForm
import pages.elements.GlobalNavigationHeader
import util.ItineraryData
import util.TripManagement
import pages.*
import state.*
import util.HotelItineraryData
import pages.elements.ShoppingCart
import org.joda.time.LocalDate
import org.joda.time.LocalTime

class CrossBookingSteps {

    Flow flow
    Trip trip
    PurchasePage purchasePage
    NavigationSteps navigationSteps
    SearchFlightsPage searchFlightsPage
    SelectFlightsPage selectFlightsPage
    PricePage pricePage
    FlightSearchForm flightSearchForm
    CommonSteps commonSteps
    ItineraryData itineraryData
    RepricePage repricePage
    CarSearchSteps carSearchSteps
    CarSelectSteps carSelectSteps
    CarPurchaseSteps carPurchaseSteps
    HotelSteps hotelSteps
    PassengerData passengerData
    GlobalNavigationHeader globalNavigationHeader
    HomePage homePage
    ScenarioState scenarioState
    CarReservationData carReservationData;
    HotelItineraryData hotelItineraryData
    ShoppingCart shoppingCart

    @Given("I have booked \$products products")
    @Aliases(values = ["I have booked a \$products product",
        "I have booked an \$products product"])
    def bookFlightCarHotel(String products) {
        addProductToMyCart(products)
        purchaseCart()
    }

    @Given("I have booked \$products products on a trip named \$tripName")
    @Aliases(values = ["I have booked a \$products product on a trip named \$tripName",
        "I have booked an \$products product on a trip named \$tripName"])
    def bookFlightCarHotelOnANamedTrip(String products,String tripName){
        flow.tripName = tripName
        if (DynaStubsIntegration.useDynaStubs()) {
            DynaStubsIntegration dynaStubs = new DynaStubsIntegration()
            AirReservation pnr = dynaStubs.createAirReservation(itineraryData, [tripName:tripName], passengerData.getPassengers())
            scenarioState.add pnr
        }else{
            bookFlightCarHotel(products)
        }

    }

    @Given("I have booked \$products products on a trip named with the default name")
    def bookFlightCarHotelForADefaultTripName(String products){
        bookFlightCarHotelOnANamedTrip(products, "DEFAULT")
    }

    @Given("I have added the products \$products to my cart")
    @Aliases(values = ["I have added a \$products product to my shopping cart",
        "I have added an \$products product to my cart",
        "I have added an \$products product to my shopping cart"])
    def addProductToMyCart(String products){
        addToCart(products)
    }

    def addToCart(String products, int adults = 1, int seniors = 0) {
        if (!DynaStubsIntegration.useDynaStubs()) {
            homePage.open()
        }
        def productsList = products.split("-")
        for(String product in productsList){
            switch (product) {
                case 'Air':
                    commonSteps.setupOneWayCarrierFlight("Southwest")
                    if (!DynaStubsIntegration.useDynaStubs()) {
                        addAAirToCart(adults, seniors)
                    }
                    flow.hasAir = true
                    break
                case 'Hotel':
                    addAHotelToCart()
                    flow.hasHotel = true
                    break
                case 'Car':
                    addACarToCart()
                    flow.isCarReservationPresent = true
                    break
            }
        }
    }

    @Given("I have included a \$products in my shopping cart")
    @Aliases(values = ["I have included an \$products in my shopping cart",
                       "I have included the \$products product in my shopping cart"])
    def addToShoppingCart(String products) {
        if (!DynaStubsIntegration.useDynaStubs()) {
            homePage.open()
        }
        def productsList = products.split("-")
        for(String product in productsList){
            switch (product) {
                case 'Air':
                    if (!DynaStubsIntegration.useDynaStubs()) {
                        addAFlightToCart()
                    }
                    flow.hasAir = true
                    break
                case 'Hotel':
                    if (!DynaStubsIntegration.useDynaStubs()) {
                        addAHotelToCart()
                    }
                    flow.hasHotel = true
                    break
                case 'Car':
                    if (!DynaStubsIntegration.useDynaStubs()) {
                        addACarToCart()
                    }
                    flow.isCarReservationPresent = true
                    break
            }
        }
    }

    @Given("I have booked \$products products for mix passengers on a trip named \$tripName")
    def bookFlightCarHotelForMixPassengers(String products, String tripName){
        flow.tripName = tripName
        addToCart(products,1,1)
        purchaseCart()
    }

    @When('I book \$products products with Early Bird')
    def bookFlightCarHotelWithEarlyBird(String products) {
        flow.purchaseWithEarlyBird = true
        bookFlightCarHotel(products)
    }

    @Given('I have booked \$products products with Early Bird on a trip named \$tripName')
    def bookFlightCarHotelWithEarlyBirdOnNamedTrip(String products, String tripName) {
        flow.tripName = tripName
        bookFlightCarHotelWithEarlyBird(products)
    }

    private def addAAirToCart(int adults = 1, int seniors = 0) {
        addAFlightToCartAsAnX({ flightSearchForm.selectAdultAndSeniorPassengers(adults, seniors) })
    }

    private def addACarToCart() {
        globalNavigationHeader.clickOnCarLinkMenu()
        carSelectSteps.addCarToCart()
    }

    private def addAHotelToCart() {
        globalNavigationHeader.clickOnHotelLinkMenu()
        hotelSteps.addAHotelToCart()
    }

    private void addAFlightToCartAsAnX(Closure asAnX) {
        globalNavigationHeader.clickOnAirLinkMenu()
        asAnX.call()
        flightSearchForm.fillInFlightSearchInfoAndClick(commonSteps.itineraryData, true)
        selectAFlight()
    }

    private void addAFlightToCart() {
        globalNavigationHeader.clickOnAirLinkMenu()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectAFlight()
    }

    private def selectAFlight() {
        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinue()
        if (repricePage?.isRepricingPage()) {
            repricePage.clickContinue()
        }
        if(passengerData.containsSeniorPassenger()){
            selectFlightsPage.selectFlights()
            selectFlightsPage.clickContinue()
        }
        pricePage.verifyPage()
    }

    @Given('I have finished my purchase adding my products to the new trip named with the default name')
    @Alias('I have at least one Upcoming Trip in my account with an Air, a Car and a Hotel in a trip with the default name')
    def purchaseCartWithATripDefault() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.tripName = TripManagement.getDefaultTripName(itineraryData.departureDate , itineraryData.arrivalStation)
        }else{
            flow.tripName = "DEFAULT"
        }
        purchaseCart()
    }

    @Given('I have finished my purchase adding my product to a new trip named \$tripName')
    @Alias('I have finished my purchase adding my products to a new trip named \$tripName')
    def purchaseCartWithATrip(String tripName) {
        flow.tripName = tripName
        purchaseCart()
    }

    @Given('I have finished my purchase using promotional certificates')
    def purchaseCartWithPromoCerts() {
        if (DynaStubsIntegration.useDynaStubs()) {
            addDynaStubsProducts([certificateNumber: flow.getUser().getCertificateNumber(), secondCertificateNumber: flow.getUser().getSecondCertificateNumber() ])
        }
    }


    @Given('I have finished my purchase without adding my product to a trip')
    @Aliases(values= ['I have finished my purchase without adding my products to a trip',
                      'I have at least one Upcoming Trip in my account with a Car product which is not part of a trip', 'I have finished my purchase'])
    def purchaseCart() {
        if (DynaStubsIntegration.useDynaStubs()) {
            addDynaStubsProducts([tripName:flow?.tripName, tripManagementDownForSenior: flow.isTripManagementDownForSenior, tripManagementDatabaseDown: flow.isTripManagementDatabaseDown, upcomingTripServiceDown: flow.isUpcomingTripUnavailable])
        } else {
            navigationSteps.continueToPurchasePage()
            purchasePage.verifyPage()
            if(flow.purchaseWithEarlyBird){
                purchasePage.clickAddEarlyBird()
            }
            purchasePage.fillInAllInformation()
            if(!flow.tripName) {
                purchasePage.uncheckNameThisTripOption()
            }
            if(flow.addToExistingTrip){
                purchasePage.selectExistingTripName(flow.tripName)
            }
            if (flow.isCarReservationPresent){
                purchasePage.enterWhoIsDriving()
            }
            if (flow.completePurchase) {
                navigationSteps.clickContinueToComplete()
            } else {
                purchasePage.clickVisibleSubmit()
                flow.completePurchase = true
            }
        }
    }

    private addDynaStubsProducts(Map params) {
        DynaStubsIntegration dynaStubs = new DynaStubsIntegration()
        if(flow.hasAir) {
            if(flow.isCarReservationPresent) {
                initializeItineraryDataCarSpecification()
            }
            if(flow.hasHotel) {
                initializeItineraryDataHotelSpecification()
            }
            AirReservation pnr = dynaStubs.createAirReservation(itineraryData, params, passengerData.getPassengers())
            scenarioState.add pnr
        }
        else if(flow.isCarReservationPresent) {
            initializeItineraryDataCarSpecification()
            carReservationData.confirmationNumber = dynaStubs.createCarReservation(itineraryData, params, passengerData.getPassengers())
        }
        else if(flow.hasHotel) {
            initializeItineraryDataHotelSpecification()
            hotelItineraryData.confirmationNumber = dynaStubs.createHotelReservation(itineraryData, params, passengerData.getPassengers())
        }
    }

	private initializeItineraryDataHotelSpecification() {
		itineraryData.hotelSpecification = hotelItineraryData
	}

	private initializeItineraryDataCarSpecification() {
		itineraryData.carSpecification = carReservationData
	}

    @Given('I have the Trip Management Database down')
    def setTripManagementDatabaseDown() {
        flow.isTripManagementDatabaseDown = true
    }

    @Given("I have the Trip Management Database down for the Senior PNR")
    def setTripManagementDatabaseDownForSenior() {
        flow.isTripManagementDownForSenior = true
    }

    @Given("The service that retrieves the Upcoming Trips is unavailable")
    def setUpcomingTripsServiceUnavailable() {
        flow.isUpcomingTripUnavailable = true
    }

    @When("I add hotel and car products to cart")
    def haveAddToCartAirCarHotel() {
        addAHotelToCart()
        addACarToCart()
        flow.hasAir = true
        flow.hasHotel = true
        flow.isCarReservationPresent = true

    }

    @When("I add \$products products to cart")
    def addProductsToCart(String products) {
        String[] keys =  products.split("-")
        def execute = ["flight":{addAFlightToCart();flow.hasAir = true},
                        "car":{addACarToCart();flow.hasHotel = true},
                        "hotel":{addAHotelToCart();flow.isCarReservationPresent = true}]
        keys.each {
           execute[it]()
        }
    }

    @When("I remove \$products products from cart")
    def removeProductsToCart(String products) {
        String [] keys = products.split("-")
        def execute = ["flight":{removeAirFromCart()},
                       "car":{removeCarFromCart()},
                       "hotel":{removeHotelFromCart()}]
        keys.each {
            execute[it]()
        }
    }

    private removeCarFromCart() {
        shoppingCart.removeCarFromShoppingCart()
        shoppingCart.clickOnRemoveFromCartButtom()
    }

    private removeHotelFromCart() {
        shoppingCart.removeHotelFromShoppingCart()
        shoppingCart.clickOnRemoveFromCartButtom()
    }

    private removeAirFromCart() {
        shoppingCart.removeShoppingCart()
        shoppingCart.clickOnRemoveFromCartButtom()
    }

    @Given("I have the Upcoming Trip in my account and its inbound flight is either upcoming or in progress")
    def createInboundFlightIsEitherUpcomingOrInProgress() {

        itineraryData.returnDate = itineraryData.departureDate
        itineraryData.returnTime = itineraryData.departureTime

        itineraryData.departureDate = new LocalDate().minusDays(1).toDate()
        itineraryData.departureTime = new LocalTime(itineraryData.departureTime).minusHours(5)

        DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration();
        AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [tripName: 'My Trip'], passengerData.getPassengers())
        scenarioState.add pnr
    }

    @Given("I have a car Reservation in the Upcoming Trip in my account")
    def createCarReservationUpComingTrip() {
        itineraryData.carSpecification=carReservationData
        itineraryData.withoutAirTrip = true
        DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration();
        AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [tripName: 'My Car Trip'], passengerData.getPassengers())
        scenarioState.add pnr
    }
}