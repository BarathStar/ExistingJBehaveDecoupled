package steps

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.Route
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.When
import pages.elements.FlightSearchForm
import state.CarReservationData
import state.Flow
import util.HotelItineraryData
import state.Trip
import util.CarRentalType
import util.CarVendor
import util.ItineraryData
import util.Station
import pages.*

class TripManagementSteps {

    PurchasePage purchasePage
    ConfirmationPage confirmationPage
    CarSearchSteps carSearchSteps
    SearchCarsPage searchCarsPage
    SelectCarPage selectCarPage
    CarPurchaseSteps carPurchaseSteps
    OutboundAndReturnDatesAndPopUp calendarPopUp
    SearchHotelsPage searchHotelsPage
    Trip trip
    Data data
    Flow flow
    SearchFlightsPage searchFlightsPage
    SelectFlightsPage selectFlightsPage
    RepricePage repricePage
    FlightSearchForm flightSearchForm
    PricePage pricePage
    ItineraryData itineraryData
    CarReservationData carReservationData
    HotelItineraryData hotelItineraryData

    @Given("I have a flight booked on a named trip")
    def bookedFlightOnANamedTrip() {
        trip = new Trip()
        searchSelectPriceAndGotoPurchasePage()
        purchasePage.fillInAllInformation()
        purchasePage.enterNewTripName(trip.name)
        purchasePage.clickContinue()
        confirmationPage.verifyNewTripNameExists(trip.name)
    }

    @Given("I have a hotel reserved on a named trip")
    def reserveHotelOnANamedTrip() {
        trip = new Trip()
        def Route route = data.getRoute();
        hotelItineraryData.destination = route.from
        hotelItineraryData.checkInDate = new Date() +1
        hotelItineraryData.checkOutDate = new Date() +3
        searchHotelsPage.fillInHotelRequiredField()
        searchHotelsPage.clickSubmit()
        searchHotelsPage.selectAvailableHotel()
        searchHotelsPage.selectAvailableHotelRate()
        searchHotelsPage.continueToPurchase()
        flow.hasHotel = true;
        purchasePage.fillInAllInformation()
        purchasePage.enterNewTripName(trip.name)
        searchHotelsPage.goToPurchaseConfirmedPage()
        confirmationPage.verifyNewTripNameExists(trip.name)
    }

    @When("I add a car to that named trip")
    void addACarToExistingNamedTrip(@Named("tripName") String tripName) {
        carSearchSteps.clickToGoToCarPage()
        carPurchaseSteps.onCarPurchasePageWithCustomerInfoFilled()
        purchasePage.selectExistingTripName(trip.name)
        purchasePage.clickContinue()
    }

    @When("I add a car product to an air booking")
    void addCarToCartBookingAnAir(){
        carSearchSteps.clickToGoToCarPage()
        searchCarsPage.fillInPickupLocation(data.getRoute().from)
        searchCarsPage.selectReliablePickupDateAndTime(calendarPopUp)
        searchCarsPage.submit()
        CarVendor carVendor = selectCarPage.obtainVendor()
        selectCarPage.selectRentalCar(carVendor, CarRentalType.MidSize)
        selectCarPage.submit()
        purchasePage.goToCarPurchasePage()
    }

    @When("I reserve a car with a named air trip")
    void reserveCarWithNamedAirTripNotLoggedIn(){
        trip = new Trip()
        searchSelectPriceAndGotoPurchasePage()
        addCarToCartBookingAnAir()
        purchasePage.fillInAllInformation()
        purchasePage.enterNewTripName(trip.name)
        purchasePage.clickContinue()
        confirmationPage.verifyNewTripNameExists(trip.name)
    }

    private void searchSelectPriceAndGotoPurchasePage(){
        searchFlightsPage.open()
        flightSearchForm.fillFromAndToAirportAndDepartureDate(itineraryData)
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinue()
        if (repricePage?.isRepricingPage()) {
            repricePage.clickContinue()
        }
        pricePage.verifyBasicPage()
        pricePage.clickContinue() //go to purchase page
    }

    private String getDefaultTripName(Date bookingDate, String stationName) {
        String destinationCity = Station.valueOf(Station.class, stationName).getCityName()
        return bookingDate.format("MM/dd/yy") + " - $destinationCity"
    }

    String defaultTripNameForACrossBooking() {
        Date airDepartureDate = (flow.hasAir)? itineraryData.departureDate : new Date()
        Date carPickupDate = (flow.isCarReservationPresent)? carReservationData.pickUpTime : new Date()

        if(airDepartureDate <= carPickupDate){
            return getDefaultTripName(airDepartureDate, itineraryData.arrivalStation)
        } else {
            return getDefaultTripName(carPickupDate, carReservationData.pickUpStation)
        }
    }
}
