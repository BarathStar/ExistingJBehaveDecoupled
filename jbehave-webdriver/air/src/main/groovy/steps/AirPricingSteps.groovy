package steps

import fixture.stubs.DynaStubsIntegration
import pages.elements.FlightSearchForm
import pages.elements.ShoppingCart
import state.PassengerData
import util.HotelItineraryData;
import util.ItineraryData
import org.jbehave.core.annotations.*
import pages.*
import state.CarReservationData
import pages.elements.Chase
import pages.elements.PriceTable
import state.Flow


class AirPricingSteps {

    Flow flow

    PricePage pricePage
    SelectFlightsPage selectFlightsPage
    AirSelectSteps airSelectSteps
    RepricePage repricePage
    ShoppingCart shoppingCart
    Chase chase

    ItineraryData itineraryData
    Itinerary itinerary
    SearchFlightsPage searchFlightsPage
    FlightSearchForm flightSearchForm
    CommonSteps commonSteps
    PassengerData passengerData
    CarReservationData carItineraryData
    PriceTable priceTable;
    HotelItineraryData hotelItineraryData

    @Given("I have added a \$itineraryType Early Bird eligible flight for \$numberOfAdults passengers to my shopping cart")
    @Alias("I have added an \$itineraryType Early Bird eligible flight for \$numberOfAdults passengers to my shopping cart")
    def searchEarlyBirdFlightsMultiPax(String itineraryType, int numberOfAdults) {
        if(itineraryType.equalsIgnoreCase("round-trip")){
            commonSteps.setupRoundTripEBEligibleConnectingFlight("Southwest", "")
        }
        else if(itineraryType.equalsIgnoreCase("open-jaw")) {
            commonSteps.setupOpenJawCarrierFlight("Southwest","Southwest")
        }

        if (!DynaStubsIntegration.useDynaStubs()) {
            searchFlightsPage.open()
            flightSearchForm.selectAdultAndSeniorPassengers(numberOfAdults, 0)
            flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
            selectFlightsPage.verifyPage()
            selectFlightsPage.selectFlights()
            selectFlightsPage.clickContinue()
            if (repricePage?.isRepricingPage()) {
                repricePage.clickContinue()
            }
            pricePage.verifyBasicPage()
        }
        else{
            passengerData.addAdultPassenger(numberOfAdults)
        }
    }

    @Given("I get to the Price page")
    @When("I get to the Price page")
    void searchAndSelectElegibleEBFlight() {
        searchFlightsPage.open()
        flightSearchForm.fillInPassengers()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectFlightsAndPrice()
        pricePage.verifyJavaScriptDone()
        pricePage.verifyPage()
    }

    @Given("I am on the price page booking with Points")
    @When("I am on the price page booking with Points")
    def goToPurchasePageBookingWithPoints() {
        searchFlightsPage.open()
        def adultCnt = itineraryData.adultPassengerCount ?: "1"
        def seniorCnt = itineraryData.seniorPassengerCount ?: "0"
        flightSearchForm.selectAdultAndSeniorPassengers(adultCnt.toInteger(), seniorCnt.toInteger())
        flightSearchForm.chooseFaresInPoints()
        flow.isRapidRewardsPointsPurchaseOnly = true
        flow.isRapidRewards = true
        flow.isLoggedIn = true
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectFlightsAndPrice()
    }

    @When("I select and view the Price page for a flight")
    @Alias("I add a flight to the shopping cart")
    def selectFlightsAndPrice() {
        airSelectSteps.selectFlightsAndContinue()
        pricePage.verifyBasicPage()
    }

    @Given("I select the flight and view the Price page")
    def selectFlightsAndViewPrice() {
        airSelectSteps.selectFlightsAndContinue()
        pricePage.verifyBasicPage()
    }

    @When("I attempt to select a flight and purchase with not enough points")
    def selectFlightsAndPriceAndAttemptPurchasePoints() {
        selectFlightsAndPrice()
        pricePage.clickContinue()
    }

    @When("I collapse the shopping cart")
    def collapseShoppingCart() {
        shoppingCart.collapseAirShoppingCart()
    }

    @When("I expand the shopping cart")
    def expandShoppingCart() {
        shoppingCart.expandAirShoppingCart()
    }

    @When("I type \$location in the Pickup Location field")
    def fillInCarPickupLocation(@Named("location") String location) {
        pricePage.fillInCarPickupLocation(location)
    }

    @When("I select to remove the shopping cart")
    void removeShoppingCart() {
        shoppingCart.removeShoppingCart()
    }

    @When("I select purchase more points")
    def purchaseMorePoints() {
        pricePage.clickPurchaseMorePoints()
    }

    @When("I click on buy points link on price page")
    def clickBuyPointsLink() {
        pricePage.clickBuyPointsLink()
    }

    @When("I click the Save Flight button")
    @Alias("I click the Save Flight button on price page")
    def clickSaveFlightButton() {
        pricePage.checkNoOops()
        shoppingCart.clickSaveFlightButton()
    }

    @When("I switch back to Pricing page")
    def switchBackToPricingPage() {
        pricePage.switchToPricingPage()
    }

    @When("I login using the modal popup")
    def loginToModalWindow() {
        pricePage.loginToModal()
    }

    @Then("I should be able to select a car vendor and a vehicle type within the car cross sell widget")
    def verifyCarVendorVehicleTypeOnCarWidget() {
        pricePage.verifyCarVendorVehicleTypeAvailable()
    }

    @When("I attempt to add a car through the car cross sell widget")
    def completeCarWidgetAndSubmit() {
        pricePage.fillInCarPickupLocation(carItineraryData.pickUpStation)
        pricePage.fillInCarVendor()
        pricePage.fillInVehicleType()
        pricePage.submitCarWidget()
    }

    @When("I select a flight for pricing with San Juan Restriction")
    void selectFlightForPricing() {
        searchFlightsPage.open()
        selectFlightForPricingDirectly()
    }

    @When("I select a flight for pricing with San Juan Restriction on Swabiz")
    void selectFlightForPricingDirectly() {
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectFlightsPage.selectFlightsWithLessThanFourHourLayover(true)
        selectFlightsPage.clickContinueForSanJuan()
    }

    @When("I select a flight for pricing with San Juan Restriction on departure")
    void selectFlightForPricingOnDeparture() {
        searchFlightsPage.open()
        selectFlightForPricingDirectlyOnDeparture()
    }

    @When("I select a flight for pricing with San Juan Restriction on Swabiz on departure")
    void selectFlightForPricingDirectlyOnDeparture() {
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinueForSanJuan()
    }

    @When("I attempt to add a car through the ad in the car cross sell widget")
    def clickOnCarCrossSellAdInCarWidget() {
        pricePage.carCrossSellWidgetIsDisplayed()
        pricePage.clickOnCarCrossSellAd()
    }

    @When("I continue the companion pricing")
    def verifyCompanionPricingAndContinue() {
        pricePage.verifyCompanionPricing()
        pricePage.clickContinue()
    }

    @When("I select a specific hotel on the Hotel Dynamic Cross-Sell")
    def selectHotelFromCrossSell() {
        hotelItineraryData.setValueFromItineraryData(itineraryData,"0")
        pricePage.selectHotelfromCrossSell(2)
    }

    @When("I click on 'Search Hotels' button on the Hotel Dynamic Cross-Sell")
    def clickOnSearchHotelOnCrossSell() {
        hotelItineraryData.setValueFromItineraryData(itineraryData,"0")
        hotelItineraryData.destination = itineraryData.arrivalCity
        pricePage.clickOnHotelCrossSellSearchHotels()
    }

    @Then("I see the shopping cart is expanded")
    void verifyShoppingCartIsExpanded() {
        shoppingCart.airInShoppingCartIsExpanded().isDisplayed()
        shoppingCart.modifyLink().isDisplayed()
        shoppingCart.removeLink().isDisplayed()
    }

    @Then("I see the shopping cart partially collapsed")
    void verifyShoppingPartiallyCollapsed() {
        shoppingCart.shoppingCartExpanded().isDisplayed()
        shoppingCart.airInShoppingCartIsCollapsed().isDisplayed();

    }

    @Given("I verify the price from the BUG page matches the price on the Price page")
    public void verifyPriceMatchesFromBugToPrice() {
        pricePage.verifyPriceMatchInPriceTableAndShoppingCart();
    }
}