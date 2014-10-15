package domain

import util.ItineraryData
import util.ItineraryDataBuilder
import com.swacorp.dotcom.webscenarios.air.Data
import util.FundErrorType;

class AirReservation {

    String adultPnr
    String seniorPnr
    String companionPnr
    boolean seniorPnrCanceled = false
    boolean adultPnrCanceled = false
    boolean companionPnrCanceled = false
    Data data
    String creditCard
    ItineraryData itineraryData
    List<Passenger> passengers
    CarReservation carReservation
    HotelReservation hotelReservation
    BigDecimal price
    int amount = -1
    int refundableAmount = -1
    int nonRefundableAmount = -1
    FundErrorType errorTypePnr

    public AirReservation() {
    }

    public AirReservation(Map jsonData, List<Passenger> passengers = new ArrayList()) {
        setPassengers(passengers)
        adultPnr = jsonData.get("confirmationNumber")
        seniorPnr = jsonData.get("seniorConfirmationNumber")
        companionPnr = jsonData.get("companionConfirmationNumber")

        itineraryData = new ItineraryDataBuilder().fromMap(jsonData).build()
        if (jsonData.carSpecification) {
            carReservation = new CarReservation(jsonData.carSpecification, jsonData.carConfirmationNumber)
        }
        if (jsonData.hotelSpecification) {
            hotelReservation = new HotelReservation(jsonData.hotelSpecification, jsonData.hotelConfirmationNumber)
        }
        this.creditCard = jsonData.get("creditCardNumber")
    }

    void clearPnr() {
        adultPnr = null
        adultPnrCanceled = false
        seniorPnr = null
        seniorPnrCanceled = false
        companionPnr = null;
        companionPnrCanceled = false
        creditCard = null
        itineraryData = null
        passengers = null
    }

    List<Passenger> getAdultPassengers() {
        return passengers.grep{ pax -> pax.isAdult()}
    }

    List<Passenger> getSeniorPassengers() {
        return passengers.grep{ pax -> pax.isSenior()}
    }

    boolean containsSeniorPassenger() {
        return !getSeniorPassengers().isEmpty()
    }

    boolean containsAdultPassenger() {
        !getAdultPassengers().isEmpty()
    }

    public void setPrice(BigDecimal price){
           this.price = price
    }

    public BigDecimal getPrice(){
           return this.price
    }
}
