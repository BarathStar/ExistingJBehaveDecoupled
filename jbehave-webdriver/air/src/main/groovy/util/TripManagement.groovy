package util

import org.openqa.selenium.WebElement
import state.CarReservationData

class TripManagement {

	public static final String DEFAULT_TRIP = "DEFAULT"

	public static final String NO_TRIP = "NO_TRIP"

    //Sets the regular expression that matches the default name
    public final static String DEFAULT_TRIP_NAME = ~/^\d\d\/\d\d\/\d\d - .*$/
    //Sets the regular expression that matches the itinerary dates on view reservation
    public final static String ITINERARY_DATES = ~/^\d\d\/\d\d\/\d\d\d\d  -  \d\d\/\d\d\/\d\d\d\d$/

    public static String getDefaultTripName(Date bookingDate, String stationName) {
        String destinationCity = Station.valueOf(Station.class, stationName).getCityName()
        return bookingDate.format("MM/dd/yy") + " - $destinationCity"
    }

    public static def validateCarRentalInfo(WebElement container, CarReservationData carReservationData) {
        String carVendor =  carReservationData.carVendor
        String carType = carReservationData.carType
        String carCity = carReservationData.pickUpStation
        String cityFullName = Station.valueOf(Station.class, carCity).getCityName()
        String rentalInfo = carVendor + " - " + carType + " - " + cityFullName
        container.getText().shouldContain rentalInfo, "The rentalInfo Info should be ${rentalInfo}"
    }

    public static def validateFlightReservationName(String flightReservationName, ItineraryData itineraryData){
        String formatName = Station.valueOf(Station.class, itineraryData.departureStation).getCityFormat() +
        " to " + Station.valueOf(Station.class, itineraryData.arrivalStation).getCityFormat()

        flightReservationName.replace("\n", " ").shouldContain formatName, "The Flight Reservation Name is not correctly formatted"
    }

    public static def validateTripName(String tripName, ItineraryData itineraryData) {
        String expectedDefaultTripName = getDefaultTripName(itineraryData.departureDate, itineraryData.arrivalStation)
        tripName.shouldBe expectedDefaultTripName, "The default trip name should be " + expectedDefaultTripName
    }

    public static def validateHotelReservationName(String hotelReservationName, HotelItineraryData hotelItineraryData){
        hotelReservationName.shouldContain hotelItineraryData.hotelName, "The Hotel name should be " + hotelItineraryData.hotelName
    }
}