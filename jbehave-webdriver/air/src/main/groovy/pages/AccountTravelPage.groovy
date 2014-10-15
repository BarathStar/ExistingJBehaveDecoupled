package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import state.Flow

class AccountTravelPage extends BasePage{

    private static final By UPCOMING_TRIP_CONTAINER = By.id("firstUpcomingTrip")
    private static final String NO_UPCOMING_TRIPS_MESSAGE = "There are no Upcoming Trips at this moment."
    private static final By BREADCRUMBS_LINKS = By.cssSelector("#sw_breadcrumb a")
    private static final int AMOUNT_OF_LINKS_PRESENT = 1
    private static final String MY_ACCOUNT_LINK_TEXT = "My Account"
    private static final By VIEW_ALL_UPCOMING_TRIPS_LINK = By.id("viewAllUpcomingTripsLink")
    private final String PATH = '/account/travel'
    private static final By VIEW_ALL_PAST_TRIP_LINK = By.cssSelector("#viewAllPastTripsLink")
    private static final By TRIP_NAME = By.className("trip_name")
    private static final By UP_COMING_CHECK_FLIGHT_STATUS_OPTION = By.cssSelector(".pipe li:nth-child(2)")
    private static final String UPCOMING_TRIP_SERVICE_UNAVAILABLE_MSG = "We are currently unable to display your upcoming trips. Please try again later."
    private static final By UP_COMING_CHECK_IN_BUTTON = By.id("submitButton")
    private static final By CHANGE_RESERVATION_LINK = By.className("changeReservation")

    Flow flow

    public AccountTravelPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/travel')
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def clickOnViewAllLinkOnPastTripSection(){
        waitForElement(VIEW_ALL_PAST_TRIP_LINK).click()
    }

    def verifyNoUpcomingTripsMessage() {
        waitForElement(UPCOMING_TRIP_CONTAINER).getText().shouldContain NO_UPCOMING_TRIPS_MESSAGE, "The no Upcoming Trips message is not present"
    }

    def verifyMyAccountLinkOnBreadcrumb() {
        List<WebElement> links = waitForElements(BREADCRUMBS_LINKS)
        links.size().shouldBe AMOUNT_OF_LINKS_PRESENT, "Only must have ${AMOUNT_OF_LINKS_PRESENT} link"
        links.get(0).getText().shouldContain MY_ACCOUNT_LINK_TEXT, "The link text should be ${MY_ACCOUNT_LINK_TEXT}"
    }

    def clickOnViewAllUpcomingTripsLink() {
        waitForElement(VIEW_ALL_UPCOMING_TRIPS_LINK).click()
    }
    
    def verifyISeeMyTripUnderUpcomingTripsSection() {
        waitForElement(TRIP_NAME).getText().shouldContain flow.tripName, "The trip name should be ${flow.tripName}"
    }

    def verifyCheckFlightOptionStatusIsPresent() {
        isElementPresent(UP_COMING_CHECK_FLIGHT_STATUS_OPTION).shouldBe true, "Check Flight Status link should be present"
    }

    def verifyUpcomingTripServiceUnavailable() {
        isElementWithTextPresent(UPCOMING_TRIP_CONTAINER, UPCOMING_TRIP_SERVICE_UNAVAILABLE_MSG).shouldBe true, "the Upcoming Trips box on My Travel Snapshot page should specify that my trips cannot be retrieved"
    }

    def clickOnUpComingTripCheckInButton(){
        waitForElement(UP_COMING_CHECK_IN_BUTTON).click()
    }

    def attemptToChangeAirProductOnMyTravelSnapshotPage() {
        waitForElement(CHANGE_RESERVATION_LINK).click()
        verifyPage()
    }
}
