package fixture.stubs

import util.HotelItineraryData
import domain.Passenger
import org.joda.time.LocalDate

class CreateHotelSpecification {

    LocalDate checkInDate
    LocalDate checkOutDate
    String destination
    String customerFirstName
    String customerLastName
    String hotelName
    String confirmationNumber = ""
    private boolean shouldPersist = true
    private String accountNumber = ""

    CreateHotelSpecification withHotelItinerary(HotelItineraryData hotelItineraryData) {
        checkInDate = new LocalDate(hotelItineraryData.checkInDate)
        checkOutDate = new LocalDate(hotelItineraryData.checkOutDate)
        destination = hotelItineraryData.destination
        hotelName = hotelItineraryData.hotelName
        return this
    }

    CreateHotelSpecification withCustomer(Passenger passenger) {
        customerFirstName = passenger.firstName
        customerLastName = passenger.lastName
        return this
    }

    Map toJSON() {
        return [checkInDate : checkInDate,
                checkOutDate : checkOutDate,
                destination : destination,
                hotelName : hotelName,
                customerFirstName : customerFirstName,
                customerLastName : customerLastName,
                confirmationNumber: confirmationNumber,
                shouldPersist: shouldPersist,
                accountNumber: accountNumber]
    }

    CreateHotelSpecification withFailedConfirmationNumber() {
        confirmationNumber = "660CA"
        this
    }

    CreateHotelSpecification shouldNotPersist() {
        shouldPersist = false
        this
    }

    CreateHotelSpecification withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber
        this
    }
}
