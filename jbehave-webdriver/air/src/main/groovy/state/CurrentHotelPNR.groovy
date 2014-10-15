package state

class CurrentHotelPNR {
    private static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy"

    String pnr
    String guestFirstName
    String guestLastName
    String location
    String cardHolderFirstName
    String cardHolderLastName
    def checkInDate
    def checkOutDate

    void clearPNR() {
        pnr = null
        guestFirstName = null
        guestLastName = null
        location = null
        checkInDate = null
        checkOutDate = null
        cardHolderFirstName = null
        cardHolderLastName = null
    }
    String getCheckInDateAsString() {
        return checkInDate.toString(DATE_FORMAT_PATTERN)
    }
}
