package state

import util.CarVendor
import util.CarRentalType
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTime
import java.text.SimpleDateFormat

class CarReservationData {
    private final pickUpTimeFormatter = new SimpleDateFormat("HH:mma")

    public String pickUpStation
    public String dropOffStation
    public Date pickUpTime
    public Date dropOffTime
    public Date pickUpDate
    public Date dropOffDate
    public CarVendor carVendor
    public CarRentalType carType
    public String agentName
    public String confirmationNumber
    public String promoCode
    public String rentalDeskLocation
    public String messageError
    public String promoType

    public Date getPickUpTime() {
        return (pickUpTime != null) ? pickUpTime : pickUpTimeFormatter.parse("11:00AM")
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTimeFormatter.parse(pickUpTime)
    }
}
