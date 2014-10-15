package state

import domain.AirReservation

public class ScenarioState {

    private List airReservations
    private List giftcards
    private List vouchers

	public ScenarioState() {
		airReservations = new ArrayList()
        giftcards = new ArrayList()
        vouchers = new ArrayList()
	}

    void add(AirReservation reservation) {
        airReservations.add(reservation)
    }

	AirReservation getLastAirReservation() {
		return airReservations.empty ? new AirReservation() : airReservations.last()
	}

    AirReservation getFirstAirReservation() {
        return airReservations.first()
    }

    AirReservation getAirReservationAt(int number) {
        return airReservations.get(number)
    }

    void addAllGiftCards(List giftcards) {
        this.giftcards.addAll(giftcards)
    }

    void addAllVouchers(List vouchers) {
        this.vouchers.addAll(vouchers)
    }

    public List getGiftcards() {
        return giftcards;
    }

    public List getVouchers() {
        return vouchers;
    }
}