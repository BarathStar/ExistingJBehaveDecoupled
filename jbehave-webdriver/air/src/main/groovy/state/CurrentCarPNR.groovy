package state

class CurrentCarPNR {

    String pnr
    String driverFirstName
    String driverLastName
    String pickupLocation
    String dropoffLocation
    def pickupDate
    def dropoffDate

    void clearPNR() {
        pnr = null
        driverFirstName = null
        driverLastName = null
        dropoffLocation = null
        pickupLocation = null
        pickupDate = null
        dropoffDate = null
    }
}
