package fixture

import fixture.pages.Browser
import org.apache.commons.io.FileUtils
import org.ho.yaml.Yaml
import org.joda.time.LocalDateTime
import org.openqa.selenium.OutputType
import org.openqa.selenium.firefox.FirefoxDriver

class FixtureCreator {

	private static final String DOMAIN = "http://local.swacorp.com/"

	private Browser browser

	private AccountCreator accountCreator

	private FlightBooker flightBooker

	public static void main(String[] args) {
		new FixtureCreator().run()
	}

	public FixtureCreator() {
		browser = new Browser(new FirefoxDriver(), DOMAIN)
		accountCreator = new AccountCreator(browser: browser)
		flightBooker = new FlightBooker(browser: browser)
	}

	public void run() {
		try {
			bookFlights()
		} catch (Exception e) {
			browser.saveScreenshotTo(new File("ss.png"))
			e.printStackTrace()
		} finally {
			browser.close()
		}
	}

	private def bookFlights() {
		String outputFilePath = System.properties.get("outputFile")
		println "Output file: ${outputFilePath}"

		LocalDateTime now = new LocalDateTime()
		LocalDateTime nextWeek = nextDayIfTooLate(now.plusDays(7))

		def accounts = []
		accounts << bookOneWayFlight(nextWeek)
		accounts << bookRoundTripFlight(nextWeek)
		accounts << bookOpenJawFlight(nextWeek)
		accounts << bookFlightsForCheckin(now.plusDays(1))

		record(accounts, outputFilePath)
	}

	private def bookOneWayFlight(def dateTime) {
		def flight = [origin: "MDW", destination: "LAS", time: dateTime, oneWay: true]
		createAccountAndBook([flight], "oneWayOwner")
	}

	private def bookRoundTripFlight(def dateTime) {
		def flight = [origin: "MDW", destination: "LAS", time: dateTime]
		createAccountAndBook([flight], "roundTripOwner")
	}

	private def bookOpenJawFlight(def dateTime) {
		def flight = [origin: "MDW", destination: "LAS", secondDestination: "MSY", time: dateTime]
		createAccountAndBook([flight], "openJawOwner")
	}

	private def bookFlightsForCheckin(def dateTime) {
		def flights = []
		2.times { count ->
			flights << [origin: "MDW", destination: "LAS", time: dateTime.plusDays(count)]
		}
		createAccountAndBook(flights, "checkInOwner")
	}

	private createAccountAndBook(def flightsSpecification, def username) {
		def account = accountCreator.createAccount(username)
		if (account) {
			flightsSpecification.each { flight ->
				def bookedFlight = flightBooker.book(flight)
				if (bookedFlight) {
					account.flights << bookedFlight
				}
			}
			return account
		}

		return false
	}

	private def nextDayIfTooLate(date) {
		if (date.getHourOfDay() > 17) {
			def midnight = new LocalDateTime(date.year, date.monthOfYear, date.dayOfMonth, 0, 1)
			return midnight.plusDays(1)
		}
		return date
	}

	private def record(accounts, outputFilePath) {
		Yaml.dump(accounts, new File(outputFilePath).asWritable())
	}
}
