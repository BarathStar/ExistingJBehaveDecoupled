package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import static org.junit.Assert.fail

class ShortcutSelectFlightsPage extends BasePage {

    private static final By PAGE_TITTLE = By.cssSelector("#sw_content h1")
    private static final By OUTBOUND_CAROUSEL_ITEMS = By.cssSelector("#outboundCalendarCarousel li")
    private static final By RETURN_CAROUSEL_ITEMS = By.cssSelector("#returnCalendarCarousel li")
    private static final By MONTH_LINK = By.cssSelector(".carouselBody a")
    private static final By DEPARTURE_MONTH_SELECTED = By.id("departureMonthViewing")
    private static final By RETURN_MONTH_SELECTED = By.id("returnMonthViewing")
    private static final By CONTINUE_BUTTON = By.id("submitButton")

    private static final String SELECTED_MONTH_CLASS = "carouselTodaySodaIneligible"
    private static final String CLASS_ATTRIBUTE = "class"
    private static final String SHORTCUT_FLIGHT_PATH = "/flight/shortcut/select-flight.html"
    private static final By POINTS_RADIO_BUTTON = By.xpath("//span[@id=\"pointsToggle1\"]/a")
    private static final By DOLLARS_RADIO_BUTTON = By.xpath("//span[@id=\"dollarsToggle\"]/a")
    private static final By CERTIFICATES_RADIO_BUTTON = By.xpath("//span[@id=\"certificatesToggle\"]/a")

    def ShortcutSelectFlightsPage(WebDriverProvider driverProvider) {
        super(driverProvider, SHORTCUT_FLIGHT_PATH);
    }

    String getRelativePath() {
        return SHORTCUT_FLIGHT_PATH
    }

    void verifyIamOnShortcutSelectFlightsPage() {
        waitForElement(PAGE_TITTLE).getText().shouldContain "Select Travel Day(s)", "Shortcut Select Flights Page is not displayed."
    }

    void changeMonth(Integer monthsToMove, String flightDirection) {
        List<WebElement> carousel = waitForElements(flightDirection.equalsIgnoreCase("DEPARTURE")? OUTBOUND_CAROUSEL_ITEMS : RETURN_CAROUSEL_ITEMS)
        String classValue = null

        try {
            Integer index = 0
            for (element in carousel) {
                classValue = element.getAttribute(CLASS_ATTRIBUTE)
                if(classValue.contains(SELECTED_MONTH_CLASS)) {
                    carousel.get(index + monthsToMove).findElement(MONTH_LINK).click()
                    return
                }
                index++
            }
        } catch (IndexOutOfBoundsException ex) {
            fail("Index Out Of Bound Exception: " + ex.message + " The month you are trying to select is not visible on the shortcut select flights page.")
        }
    }

    boolean verifyMonthPosition(Date itineraryDate, String flightDirection) {
        Integer itineraryMonth = itineraryDate.getMonth().toInteger() + 1
        Integer currentlySelectedMonth = waitForElement(flightDirection.equalsIgnoreCase("DEPARTURE")? DEPARTURE_MONTH_SELECTED : RETURN_MONTH_SELECTED).getAttribute("value").split("/")[0].toInteger()

        return (itineraryMonth == currentlySelectedMonth)
    }

    Integer selectDepartureAfterReturn(Date departureDate, Date returnDate) {
        Integer currentDepartureMonth = departureDate.getMonth().toInteger()
        Integer currentReturnMonth = returnDate.getMonth().toInteger()
        Integer monthsToMove = (currentReturnMonth - currentDepartureMonth) + 1

        changeMonth(monthsToMove, "DEPARTURE")

        return monthsToMove
    }

    Integer selectReturnBeforeDeparture(Date departureDate, Date returnDate) {
        Integer currentDepartureMonth = departureDate.getMonth().toInteger()
        Integer currentReturnMonth = returnDate.getMonth().toInteger()
        Integer monthsToMove = (currentDepartureMonth - currentReturnMonth) - 1

        changeMonth(monthsToMove, "RETURN")

        return monthsToMove
    }

    boolean verifyDepartureAndReturnOnSameMonth() {
        Integer currentlyDepartureMonth = waitForElement(DEPARTURE_MONTH_SELECTED).getAttribute("value").split("/")[0].toInteger()
        Integer currentlyReturnMonth = waitForElement(RETURN_MONTH_SELECTED).getAttribute("value").split("/")[0].toInteger()

        return (currentlyDepartureMonth == currentlyReturnMonth)
    }

    void verifyInvalidSessionMessage() {
        getCurrentUrl()?.shouldContain "reservations/invalid-session-state.html", "Path is not invalid-session-state page."
    }

    void openFromDreamTrips() {
        String url = "${domain}${getRelativePath()}?fromDreamTrips=1"
        get(url)
    }

    void scheduleNotStartedUntil(String msg) {
        getExpectedOopsMessage().shouldContain msg
    }

    void clickContinueButton() {
        waitForElement(CONTINUE_BUTTON).click()
    }

    boolean verifyFareTypeToggle() {
        verifyElementPresent("Dollars Fare Toggle", DOLLARS_RADIO_BUTTON)
        verifyElementPresent("Points Fare Toggle", POINTS_RADIO_BUTTON)
        verifyElementNotPresent("Certificates Fare Toggle", CERTIFICATES_RADIO_BUTTON)
    }

    boolean verifyFareTypeToggleWithCertificates() {
        verifyElementPresent("Dollars Fare Toggle", DOLLARS_RADIO_BUTTON)
        verifyElementPresent("Points Fare Toggle", POINTS_RADIO_BUTTON)
        verifyElementPresent("Certificates Fare Toggle", CERTIFICATES_RADIO_BUTTON)
    }
}