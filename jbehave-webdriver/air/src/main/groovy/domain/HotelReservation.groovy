package domain

import org.joda.time.LocalDate

class HotelReservation {
    private static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy"

    String confirmationNumber
    LocalDate checkInDate
    LocalDate checkOutDate
    String hotelName
    String customerFirstName
    String customerLastName
    String destination
    String billingFirstName
    String billingLastName

    HotelReservation(Map responseAsMap, String confNumber) {
        confirmationNumber = confNumber
        checkInDate = responseAsMap.checkInDate
        checkOutDate = responseAsMap.checkOutDate
        hotelName = responseAsMap.hotelName
        customerFirstName = responseAsMap.customerFirstName
        customerLastName = responseAsMap.customerLastName
        destination = responseAsMap.destination
        billingFirstName = responseAsMap.billingFirstName
        billingLastName = responseAsMap.billingLastName
    }

    String getCheckInDateAsString() {
        checkInDate.toString(DATE_FORMAT_PATTERN)
    }

    String getCheckOutDateAsString() {
        checkOutDate.toString(DATE_FORMAT_PATTERN)
    }
}
