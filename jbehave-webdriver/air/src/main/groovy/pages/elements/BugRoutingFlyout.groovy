package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class BugRoutingFlyout extends FlyOut{

    private static final By ROUTING = By.cssSelector("#routingHoverTable .headerText")
    private static final By OUTBOUND_DEPARTURE_DETAILS = By.cssSelector("#routingHoverTable .flightOrigin")
    private static final By OUTBOUD_RETURN_DETAILS = By.cssSelector("#routingHoverTable .flightOrigin")
    private static final String OUTBOUND_DEPART_TIME = ".first .flightTime.flightTime.departDetails"
    private static final String OUTBOUND_ARRIVE_TIME = ".first .flightTime.arriveDetails"
    private static final String OUTBOUND_CONNECTION_DEPART_TIME = ".second .flightTime.departDetails"
    private static final String OUTBOUND_CONNECTION_ARRIVE_TIME = ".second .flightTime.arriveDetails"
    private static final String OUTBOUND_FLIGHT = ".first .flightNumber"
    private static final String OUTBOUND_CONNECTION_FLIGHT = ".second .flightNumber"

    BugRoutingFlyout(WebElement theContainer) {
        super(theContainer)
    }

    def String retrieveRouting() {
        return container.findElement(ROUTING).text
    }

    def String retrieveOutboundDeparture() {
        return container.findElements(OUTBOUND_DEPARTURE_DETAILS)[0].text
    }

    def String retrieveOutboundArrival() {
        return container.findElements(OUTBOUD_RETURN_DETAILS)[1].text
    }

    def String retrieveOutboundConnectionDeparture() {
        return container.findElements(OUTBOUND_DEPARTURE_DETAILS)[2].text
    }

    def String retrieveOutboundConnectionArrival() {
        return container.findElements(OUTBOUD_RETURN_DETAILS)[3].text
    }

    def retrieveOutboundDepartureTime() {
        return container.findElement(By.cssSelector(OUTBOUND_DEPART_TIME)).text
    }

    def retrieveOutboundArrivalTime() {
        return container.findElement(By.cssSelector(OUTBOUND_ARRIVE_TIME)).text
    }

    def retrieveOutboundConnectionDepartureTime() {
        return container.findElement(By.cssSelector(OUTBOUND_CONNECTION_DEPART_TIME)).text
    }

    def retrieveOutboundConnectionArrivalTime() {
        return container.findElement(By.cssSelector(OUTBOUND_CONNECTION_ARRIVE_TIME)).text
    }

    def retrieveOutboundConnectionFlight() {
        return container.findElement(By.cssSelector(OUTBOUND_CONNECTION_FLIGHT)).text
    }

    def retrieveOutboundFlight() {
        return container.findElement(By.cssSelector(OUTBOUND_FLIGHT)).text
    }
}
