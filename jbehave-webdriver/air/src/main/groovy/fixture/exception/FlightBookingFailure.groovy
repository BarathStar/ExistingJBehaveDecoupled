package fixture.exception

class FlightBookingFailure extends RuntimeException {

	public FlightBookingFailure() {

	}

	public FlightBookingFailure(String message) {
		super(message)
	}
}
