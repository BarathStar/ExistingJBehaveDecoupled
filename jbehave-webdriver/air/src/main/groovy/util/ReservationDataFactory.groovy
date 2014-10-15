package util

import static util.ItineraryDateFactory.*

class ReservationDataFactory {

    def ReservationData getAnyAvailableEarlyBirdEligibleReservation() {
        def reservationData = new ReservationData()
        reservationData.from = Station.DAL
        reservationData.to = Station.HOU
		def departingFlight_carrierCode = "WN"
        reservationData.departsOn = getAnyAvailableDepartureDateForEarlyBird()
        reservationData.returnsOn = reservationData.departsOn.plus(1)
        return reservationData
    }

}

class ReservationData {
	Station from
	Station to
	Date departsOn
	Date returnsOn
}