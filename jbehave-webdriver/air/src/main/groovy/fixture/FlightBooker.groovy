package fixture

import fixture.data.Flight
import fixture.pages.Browser
import fixture.pages.SearchFlightPage

class FlightBooker {

	Browser browser

	def book(flight) {
		def searchFlightPage = browser.goTo(SearchFlightPage)

		def selectFlightPage = searchFlightPage.search(flight)

		selectFlightPage.chooseFirstDepartingFlight()
		if (!flight.oneWay) {
			selectFlightPage.chooseFirstReturningFlight()
		}
		def purchasePage = selectFlightPage.continueToPurchasePage()
		def confirmationPage = purchasePage.fillPurchaseForm()

		return new Flight(confirmationNumber: confirmationPage.confirmationNumber,
				origin: confirmationPage.origin,
				destination: confirmationPage.destination,
				departureDate: confirmationPage.departureDate,
				departureTime: confirmationPage.departureTime,
				passengerName: confirmationPage.passengerName)
	}
}
