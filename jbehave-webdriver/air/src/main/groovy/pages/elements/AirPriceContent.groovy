package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.BasePage

class AirPriceContent extends BasePage {

    private static final String departId = "#airItinerarydepart"
    private static final String returnId = "#airItineraryreturn"
    private static final By DEPART_ROW = By.id("airItinerarydepart")
    private static final By RETURN_ROW = By.id("airItineraryreturn")
    private static final By OUTBOUND_DEPARTURE_TIME = By.cssSelector("${departId} tr.tableRowOdd.topRow .routingDetailsTimes")
    private static final By OUTBOUND_ARRIVAL_TIME = By.cssSelector("${departId}  tr.tableRowOdd.bottomRow .routingDetailsTimes")
    private static final By INBOUND_DEPARTURE_TIME = By.cssSelector("${returnId}  tr.tableRowEven.topRow .routingDetailsTimes")
    private static final By INBOUND_ARRIVAL_TIME = By.cssSelector("${returnId}  tr.tableRowEven.bottomRow .routingDetailsTimes")
    private static final By TRAVEL_TIME = By.className("travelFlightDuration")
    private static final By TRAVEL_DATE = By.className("travelDateTime")
    private static final By FLIGHT_NUMBER = By.className("flightNumber")
    private static final By DEPART_OUTBOUND_STATION = By.cssSelector("#airItinerarydepart tr.tableRowOdd.topRow td.routingDetailsStops strong")
    private static final By DEPART_INBOUND_STATION = By.cssSelector("#airItinerarydepart tr.tableRowOdd.bottomRow td.routingDetailsStops strong")
    private static final By RETURN_OUTBOUND_STATION = By.cssSelector("#airItineraryreturn tr.tableRowEven.topRow td.routingDetailsStops strong")
    private static final By RETURN_INBOUND_STATION = By.cssSelector("#airItineraryreturn tr.tableRowEven.bottomRow td.routingDetailsStops strong")
    private static final By OUTBOUND_FLIGHT_NUMBERS = By.cssSelector("#airItinerarydepart .flightNumber")
    private static final By OUTBOUND_CITIES = By.cssSelector("#airItinerarydepart .routingDetailsStops")
    private static final By OUTBOUND_TIMES = By.cssSelector("#airItinerarydepart .routingDetailsTimes")
    private static final By INBOUND_FLIGHT_NUMBERS = By.cssSelector("#airItineraryreturn .flightNumber")
    private static final By INBOUND_CITIES = By.cssSelector("#airItineraryreturn .routingDetailsStops")
    private static final By INBOUND_TIMES = By.cssSelector("#airItineraryreturn .routingDetailsTimes")

    public AirPriceContent(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return ""
    }

    String outboundDepartureTime() {
        return waitForElement(OUTBOUND_DEPARTURE_TIME).text
    }

    String outboundArrivalTime() {
        return waitForElement(OUTBOUND_ARRIVAL_TIME).text
    }

    String inboundDepartureTime() {
        return waitForElement(INBOUND_DEPARTURE_TIME).text
    }

    String inboundArrivalTime() {
        return waitForElement(INBOUND_ARRIVAL_TIME).text
    }

    String departureTravelTime() {
        return waitForElement(DEPART_ROW).findElement(TRAVEL_TIME).text
    }

    String returnTravelTime() {
        return waitForElement(RETURN_ROW).findElement(TRAVEL_TIME).text
    }

    String departureFlightNumber() {
        return waitForElement(DEPART_ROW).findElement(FLIGHT_NUMBER).findElement(By.tagName("strong")).text
    }

    String returnFlightNumber() {
        return waitForElement(RETURN_ROW).findElement(FLIGHT_NUMBER).findElement(By.tagName("strong")).text
    }

    String departureOutBoundStation() {
        return waitForElement(DEPART_OUTBOUND_STATION).text
    }

    String departureInBoundStation() {
        return waitForElement(DEPART_INBOUND_STATION).text
    }

    String returnOutBoundStation() {
        return waitForElement(RETURN_OUTBOUND_STATION).text
    }

    String returnInBoundStation() {
        return waitForElement(RETURN_INBOUND_STATION).text
    }

    String travelDepartDate() {
        return waitForElements(TRAVEL_DATE)[0].text
    }

    String travelReturnDate() {
        if (waitForElements(TRAVEL_DATE)[1] != null)
            return waitForElements(TRAVEL_DATE)[1].text
        else
            return waitForElements(TRAVEL_DATE)[0].text
    }

    List<WebElement> outBoundTimes() {
        return waitForElements(OUTBOUND_TIMES)
    }

    List<WebElement> inBoundTimes() {
        return waitForElements(INBOUND_TIMES)
    }

    List<WebElement> outBoundCities() {
        return waitForElements(OUTBOUND_CITIES)
    }

    List<WebElement> inBoundCities() {
        return waitForElements(INBOUND_CITIES)
    }

    List<WebElement> outboundFlightNumbers() {
        return waitForElements(OUTBOUND_FLIGHT_NUMBERS)
    }

    List<WebElement> inboundFlightNumbers() {
        return waitForElements(INBOUND_FLIGHT_NUMBERS)
    }
}
