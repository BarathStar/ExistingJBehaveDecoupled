package steps

import com.github.tanob.groobe.GrooBe
import domain.AirReservation
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import pages.ConfirmationPage
import pages.PurchasePage
import pages.SearchHotelsPage
import pages.ViewHotelReservationPage
import pages.elements.AccountBar
import pages.elements.GlobalNavigationHeader
import pages.elements.LoginForm
import state.Flow
import state.ScenarioState
import steps.thenSteps.ConfirmationThenSteps
import util.HotelItineraryData
import pages.SelectHotelPage
import pages.SelectHotelRatePage
import org.jbehave.core.model.ExamplesTable
import org.joda.time.LocalDate

class HotelSteps {

    SearchHotelsPage searchHotelsPage
    PurchasePage purchasingPage
    ConfirmationPage confirmationPage
    ConfirmationThenSteps confirmationThenSteps
    AccountBar accountBar
    Flow flow
    ViewHotelReservationPage viewHotelReservationPage
    GlobalNavigationHeader globalNavigationHeader
    LoginForm loginForm
    ScenarioState scenarioState
    SelectHotelPage selectHotelPage
    SelectHotelRatePage selectHotelRatePage
    HotelItineraryData hotelData

    def HotelSteps() {
        GrooBe.activate()
    }

    @Given("I am a customer with a reserved hotel room")
    void completeHotelBooking(){
        openHotelHomePage()
        reserveHotel()

        confirmationPage.verifyPage()
        confirmationPage.storeHotelPNR()
    }

    @Given("I am on the hotel reservation page logged in")
    def searchForAHotelLoggedIn() {
        searchHotelsPage.open()
        loginForm.loginRapidRewardsMember()
        accountBar.verifyRapidRewardsLoggedIn()
    }

    @Given("I am a customer looking for a hotel")
    @Alias("I am on the hotel search page")
    def openHotelHomePage() {
        searchHotelsPage.open()
    }

    @Given("I am International customer looking for a hotel")
    def openHotelSelectPagePrePopulated() {
        selectHotelPage.openWithParameters()
    }

    @Given("I have cancelled a hotel reservation")
    @Alias("I have cancelled the Hotel product which belongs to my trip")
    @When("I cancel a hotel reservation")
    def cancelHotelReservation() {
        if(DynaStubsIntegration.useDynaStubs()) {
            new DynaStubsIntegration().cancelHotelReservation(scenarioState.getLastAirReservation().hotelReservation)
        } else {
            searchHotelsPage.cancelHotelLink()
            searchHotelsPage.cancelHotelReservation()
        }
    }

    @When("I retrieve the hotel itinerary")
    def retrieveConfirmation() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        searchHotelsPage.openRetrieveHotelReservation()
        searchHotelsPage.verifyPage()
        searchHotelsPage.fillRetrieveHotelForm(airReservation.hotelReservation)
        searchHotelsPage.submitRetrieveHotelForm()
        searchHotelsPage.verifyPage()
    }

    @When("I click on the Cancel link for the air product on the Hotel Reservation page")
    @Alias('I select to cancel the Air product associated to my trip')
    void clickOnCancelAirProduct() {
        viewHotelReservationPage.clickOnCancelAirProductLink()
    }

    @When("I navigate to the hotel search page")
    def clickGlobalHotel() {
        globalNavigationHeader.clickOnHotelLinkMenu()
        searchHotelsPage.verifyPage()
    }

    @When("I reserve a hotel room")
    def reserveHotel() {
        reserveHotelRoomForPurchase()
        reserveHotelContinueToConfirmation()
    }

    @When("I reserve hotel and continue to confirmation page")
    def reserveHotelContinueToConfirmation() {
        purchasingPage.fillInAllInformation()
        searchHotelsPage.goToPurchaseConfirmedPage()
    }

    @When("I make a hotel room reservation")
    @Given("I have selected a hotel and continue to the purchase page")
    def reserveHotelRoomForPurchase() {
        selectHotelRoomForPurchase()
        searchHotelsPage.continueToPurchase()
    }

    @When("I receive a hotel confirmation number")
    def receiveHotelConfirmationNumber() {
        confirmationThenSteps.receiveHotelConfirmationNumber()
    }

    //special partner for which, merchant rate is also treated as retail rate
    @When("I have selected a merchant rate for a special partner hotel and continue to the purchase page")
    def reserveMerchantRateForAConsiderMerchantAsRetailPartnerHotelRoomForPurchase() {
        addHotelToCartAndContinueToPurchase()
    }

    @When("I have selected a merchant rate for a partner hotel and continue to the purchase page")
    def reserveMerchantRatePartnerHotelRoomForPurchase() {
        addHotelToCartAndContinueToPurchase()
    }

    @When("I have selected a retail rate for a partner hotel and continue to the purchase page")
    def reserveRetailRatePartnerHotelRoomForPurchase() {
        addHotelToCartAndContinueToPurchase()
    }

    private def addHotelToCartAndContinueToPurchase(){
        addAHotelToCart()
        flow.hasHotel = true
        searchHotelsPage.continueToPurchase()
    }

    @When("I select a hotel room")
    def selectHotelRoomForPurchase() {
        if(DynaStubsIntegration.useDynaStubs()){
            new DynaStubsIntegration().createHotelReservation("29256", "Dallas, Texas", 1, 1)
            new DynaStubsIntegration().createHotelReservation("999999", null, 1, 1)
        }
        addAHotelToCart()
        flow.hasHotel = true
    }

    @When("I continue to hotel purchase page")
    def continueToPurchasePage() {
        searchHotelsPage.continueToPurchase()
    }

    /**
     * Clicks the Find Hotels button
     * @return
     */
    @When("I click search to find Hotels")
    def searchForHotels()
    {
        searchHotelsPage.attemptToSearch()
    }

    def addAHotelToCart() {
        searchHotelsPage.fillInHotelRequiredField()
        searchHotelsPage.clickSubmit()
        searchHotelsPage.selectAvailableHotel()
        searchHotelsPage.selectAvailableHotelRate()
    }

    @When("I search for Hotels")
    def searchHotels() {
        if (DynaStubsIntegration.useDynaStubs()) {
            DynaStubsIntegration dyna = new DynaStubsIntegration()
            dyna.generateSelectedHotelSpecification(hotelData.locationId, hotelData.hotelName, hotelData.hotelLocation, hotelData.hotelState, hotelData.checkInDate, hotelData.checkOutDate)
        }
        searchHotelsPage.fillInHotelRequiredField()
        searchHotelsPage.attemptToSearch()
        selectHotelPage.verifyPage()
    }

    @When("I attempt to search for Hotels")
    def attemptToSearchHotels() {
        searchHotelsPage.fillInHotelRequiredField()
        searchHotelsPage.attemptToSearch()
    }

    @When("I click on the View Map image")
    def clickOnViewMap() {
        selectHotelPage.viewMap()
    }

    @When("I click on the Street View link")
    def clickOnStreetView() {
        selectHotelPage.clickStreetView()
    }

    @When("I select the first Hotel")
    def selectHotel() {
        selectHotelRatePage.selectHotel()
    }

    @When("I click on the Map tab")
    def clickMapTab() {
        selectHotelRatePage.clickMapTab()
    }

    @When("I click on the Hotel marker")
    def clickMarker() {
        selectHotelRatePage.clickMarker()
    }

    @When("I check Hotel number \$hotelNumber")
    def checkHotel(int hotelNumber) {
        selectHotelPage.checkHotel(hotelNumber)
    }

    @When("I click on the Compare Hotels button")
    def compareHotels() {
        selectHotelPage.compareHotels()
    }

    @When("I click on View Hotels on a Map")
    def viewHotelsOnMap() {
        selectHotelPage.clickCompareMap()
    }

    @When("I click on Hotel marker \$hotelNumber")
    def clickHotelMarker(int hotelNumber) {
        selectHotelPage.clickHotelMarker(hotelNumber)
    }

    @When("I select Hotel Area \$areaNumber")
    def selectHotelArea(int areaNumber) {
        selectHotelPage.selectHotelArea(areaNumber)
    }

    @Given("I have available the hotel from my itinerary")
    def postHotelOnDyna(){
        if (DynaStubsIntegration.useDynaStubs()) {
            DynaStubsIntegration dyna = new DynaStubsIntegration()
            dyna.generateSelectHotelSpecification(hotelData.hotelId)
        }
    }

    /**
     * Sets the city in which the passenger will be booking a hotel
     * @param arrivalCity
     * @return
     */
    @When("the arrival city is \$arrivalCity")
    def selectArrivalCity(String arrivalCity) {
        searchHotelsPage.enterDestination(arrivalCity)
    }

    /**
     * Sets the number of rooms, children, and adults for the hotel
     * @param numRooms
     * @param numAdults
     * @param numChildren
     * @return
     */
    @When("I want \$numRooms rooms for \$numAdults adults and \$numChildren children")
    def selectRoomsAdultsChildren(int numRooms, int numAdults, int numChildren)
    {
        searchHotelsPage.enterNumberOfRooms(numRooms)
        searchHotelsPage.enterNumberOfAdults(numAdults)
        searchHotelsPage.enterNumberOfChildren(numChildren)
    }

    /**
     * Sets the close to option and if close to an airport sets the airport as well
     * @param option
     * @param destination
     * @return
     */
    @When("I want it close to the \$option in \$destination")
    def selectCloseToOption(String option, String destination) {
        searchHotelsPage.selectCloseTo(option, destination)
    }

    /**
     * Needs to have assertions built in but checks that the Select Hotel Page loads without errors
     */
    @Then("I get the Select Hotel page without errors")
    void verifySelectHotelPage() {
        //TODO add assertions
    }



}
