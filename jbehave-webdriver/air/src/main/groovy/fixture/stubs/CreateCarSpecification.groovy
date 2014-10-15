package fixture.stubs

import state.CarReservationData
import org.joda.time.LocalDate
import domain.Passenger

class CreateCarSpecification {

    private String pickUpLocation
    private String dropOffLocation
    private LocalDate pickUpDate
    private LocalDate dropOffDate
    private String vehicleType
    private String agencyName
    private String renterFirstName
    private String renterLastName
    private String confirmationNumber = ""
    private boolean shouldPersist = true
    private String accountNumber = ""

    CreateCarSpecification withCarItinerary(CarReservationData carReservationData) {
        pickUpLocation = carReservationData.pickUpStation
        dropOffLocation = carReservationData.dropOffStation
        pickUpDate = new LocalDate(carReservationData.pickUpDate)
        dropOffDate = new LocalDate(carReservationData.dropOffDate)
        vehicleType = carReservationData.carType.name()
        agencyName = carReservationData.carVendor.name()
        this
    }

    CreateCarSpecification withDriver(Passenger passenger) {
        renterFirstName = passenger.firstName
        renterLastName = passenger.lastName
        this
    }

    Map<String, String> toJSON() {
        return [pickUpLocation : pickUpLocation,
                dropOffLocation : dropOffLocation,
                pickUpDate : pickUpDate,
                dropOffDate : dropOffDate,
                vehicleType : vehicleType,
                agencyName : agencyName,
                renterFirstName : renterFirstName,
                renterLastName : renterLastName,
                confirmationNumber: confirmationNumber,
                shouldPersist: shouldPersist,
                accountNumber: accountNumber]
    }

    CreateCarSpecification withFailedConfirmationNumber() {
        confirmationNumber = "660HA"
        this
    }

    CreateCarSpecification shouldNotPersist() {
        shouldPersist = false
        this
    }

    CreateCarSpecification withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber
        this
    }
}
