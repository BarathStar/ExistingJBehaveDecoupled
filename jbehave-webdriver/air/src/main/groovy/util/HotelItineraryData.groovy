package util

class HotelItineraryData {
    public String hotelId
    public String destination
    public String hotelName
    public Date checkInDate
    public Date checkOutDate
    public String confirmationNumber
    public String promoCode
    public String adults
    public String children
    public String billingLastName
    public String billingFirstName
    public String rooms
    public String hotelState
    public String hotelLocation
    public String locationId
    public String SpecialOffers
    public String thereIsNotHotelsForLocation
    public hotels = []
    public String city

    public setValueFromItineraryData(ItineraryData itineraryData, String children = "0"){
        this.checkInDate = itineraryData.departureDate
        this.checkOutDate = itineraryData.returnDate
        this.adults = itineraryData.adultPassengerCount
        this.destination = itineraryData.arrivalStation
        this.children = children
    }

}
