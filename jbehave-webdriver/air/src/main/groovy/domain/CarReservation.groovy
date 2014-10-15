package domain

import util.CarVendor
import util.CarRentalType
import org.joda.time.LocalDate

class CarReservation {

    private static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy"

    String confirmationNumber
    String renterFirstName
    String renterLastName

    String pickUpStation
    String dropOffStation
    LocalDate pickUpDate
    LocalDate dropOffDate

    CarVendor carVendor
    CarRentalType carType

    CarReservation(Map responseAsMap, String confNumber) {
        confirmationNumber = confNumber
        renterFirstName = responseAsMap.renterFirstName
        renterLastName = responseAsMap.renterLastName
        pickUpStation = responseAsMap.pickUpStation
        dropOffStation = responseAsMap.dorpOffStation

        pickUpDate = responseAsMap.pickUpDate
        dropOffDate = responseAsMap.dropOffDate

        carVendor = CarVendor.valueOf(responseAsMap.agencyName)
        carType = CarRentalType.valueOf(responseAsMap.vehicleType)
    }

    String getPickUpDateAsString() {
        pickUpDate.toString(DATE_FORMAT_PATTERN)
    }
}
