package pages

import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.AccountBar
import pages.elements.FlightSearchForm
import state.Flow

import static org.junit.Assert.assertTrue
import static util.Locators.ELEMENT_IDS
import util.ItineraryData

public class SearchFlightsPage extends BasePage {

    static final private String ERROR_MSG_CSS = 'div.error ul li'

    private static final By SECONDARY_NAV_ITEM_WITH_DOT = By.className("secondaryNavItemWithDot")
    private static final By SENIOR_HEADER = By.className("seniorHeader")
    private static final By PROMO_CERTIFICATE_BUTTON_CONTAINER = By.xpath("//div[@class='promoCertButtonContainer']/a")
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By ONE_WAY_RADIO_BUTTON = By.id("oneWay")
    private static final By ROUND_TRIP_RADIO_BUTTON = By.id("roundTrip")
    private static final By WHERE_WE_FLY_LINK = By.id("where-we-fly")
    private static final By CHECKOUT_BUTTON_ID = By.id("checkoutButton")
    private static final By GLOBAL_NAVIGATION_RAPID_REWARDS_HEADER = By.id("global_account_bar_section_rapid_rewards_header")
    private static final By ADD_ANOTHER_FLIGHT = By.id("add-another-flight")
    private static final By ENROLL_RR_BUTTON = By.id("enrollRr")
    private static final By RIGHT_NAV_RR_LINK = By.id("right_nav_rapidrewards_link")
    private static final String ENROLL_RRBUTTON_HREF_MEMBER = "/account/enroll/enroll-member"
    private static final String ENROLL_RRBUTTON_HREF_UPGRADE = "/account/enroll/enroll-upgrade"
    private static final By CHANGE_RESERVATION_LINK = By.xpath("//a[@id='Change Reservation']")
    private static final By SWABIZ_PLANTRIP_AIR_ELEMENT = By.id("build-itinerary-wide-title")
    private static final By PLANTRIP_AIR_ELEMENT = By.className("air-widget-product-icon")
    private static final By OUTBOUND_NON_STOP_FLIGHT_WITH_FREEDOM_AWARD = By.cssSelector("#faresOutbound tr.nonstop td:nth-child(7) input")
    private static final By RETURN_NON_STOP_FLIGHT_WITH_FREEDOM_AWARD = By.cssSelector("#faresReturn tr.nonstop td:nth-child(7) input")
    private static final By POINTS_TO_EARN = By.className("points")
    private static final By ACCOUNT_SNAPSHOT_LINK = By.id("accountSnapshotLink")

    Flow flow

    private WebElement manageReservation(){
        waitForElement(SECONDARY_NAV_ITEM_WITH_DOT).findElement(By.xpath("//*[contains(text(),'Manage Reservation')]"))
    }

    private WebElement checkoutButton() {
        waitForElement(CHECKOUT_BUTTON_ID)
    }

    OutboundAndReturnDatesAndPopUp calendarPopUp
    Data data

    FlightSearchForm flightSearchForm
    AccountBar accountBar

    public SearchFlightsPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight');
    }

    String getRelativePath() {
        return "/flight"
    }

    def open() {
        super.open()
        verifyPage()
    }

    def submit() {
        String title = getTitle()
        waitForElement(SUBMIT_BUTTON).click()
        waitForPageTitleToChangeOrOops(title)
    }

    void clickManageReservationLink() {
        String title = getTitle()
        manageReservation().click()
        waitForPageTitleToChangeOrOops(title)
    }

    def clickProductLink(){
        String title = getTitle()
        WebElement elementProduct = waitForElement(SECONDARY_NAV_ITEM_WITH_DOT).findElement(By.xpath("//*[contains(text(),'Product')]"))
        elementProduct.click()
        waitForPageTitleToChangeOrOops(title)
    }

    boolean loginError() {
        WebElement loginError = waitForElement(By.cssSelector(ERROR_MSG_CSS), false)
        if (loginError!=null && loginError.getText().equals('The Username/Account Number and/or Password are incorrect.')) {
            return true
       }
       return false
    }

    def logoutRapidRewardsMember() {
        WebElement elt = waitForElement(By.xpath("//input[@class='logoutLink']"),false)
        if (elt != null) {
            elt.click()
        }
    }

    def onPlanTripPage() {
        checkSomethingServed();
        String currentUrlStr = getCurrentUrl().toString()
        boolean onPage = currentUrlStr.contains("select-flight") || currentUrlStr.endsWith("/flight/")
        assertTrue("not on plan trip page, on: " + currentUrlStr + " instead", onPage)
    }

    def clearFields(List<String> list) {
        list.each { waitForElement(By.id(ELEMENT_IDS.get(it))).sendKeys(BasePage.CLEAR_FIELD)}
    }

    void seniorFareDisplayed() {
        waitForElement(SENIOR_HEADER).isEnabled()
    }

    def clickCheckoutButton() {
        waitForElement(CHECKOUT_BUTTON_ID).click()
    }

    def searchFlightsForPassengers(int passengers, String passengerType) {
        open()
        int adults = 0
        int seniors = 0
        if (passengerType == "senior") {
            seniors = passengers
        } else {
            adults = passengers
        }
        flightSearchForm.selectAdultAndSeniorPassengers(adults, seniors)
        flightSearchForm.viewSelectFlightPage()
    }

    void expandMyRapidRewards() {
        waitForElement(GLOBAL_NAVIGATION_RAPID_REWARDS_HEADER).click()
    }

    def clickPromotionalAwards() {
        waitForElement(PROMO_CERTIFICATE_BUTTON_CONTAINER).click()
    }

    def clickOneWay() {
        waitForElement(ONE_WAY_RADIO_BUTTON).click()
    }

    def clickRoundTrip() {
        waitForElement(ROUND_TRIP_RADIO_BUTTON).click()
    }

    void clickAddAnotherFlight() {
        waitForElement(ADD_ANOTHER_FLIGHT).click()
    }

    def clickWhereWeFlyLinkOnSearchPage(){
        waitForElement(WHERE_WE_FLY_LINK).click()
    }

    def verifyEnrollRrButtonIsPresent() {
        isElementPresent(ENROLL_RR_BUTTON).shouldBe true, "Enroll Now button should be on the account sidebar under Rapid Rewards section"
    }

    def verifyEnrollRrButtonHRefMember() {
        waitForElement(ENROLL_RR_BUTTON).getAttribute("href").shouldContain ENROLL_RRBUTTON_HREF_MEMBER, "Enroll Now button href should be /enroll-member"
    }

    def verifyEnrollRrButtonHRefUpgrade() {
        waitForElement(ENROLL_RR_BUTTON).getAttribute("href").shouldContain ENROLL_RRBUTTON_HREF_UPGRADE, "Enroll Now button href should be /enroll-upgrade"
    }

    void clickChangeReservationLink() {
        waitForElement(CHANGE_RESERVATION_LINK).click()
    }

    void verifySwaBizPlanTripPagePopulated() {
        waitForElement(SWABIZ_PLANTRIP_AIR_ELEMENT).text.shouldContain "Air", "SwaBiz plan trip page was not found"
    }

    void verifyPlanTripPagePopulated() {
        waitForElement(PLANTRIP_AIR_ELEMENT).text.shouldContain "Air", "Plan trip page was not found"
    }

    def selectNonStopFlightWithFreedomAwards(ItineraryData itineraryData) {
        waitForElement(OUTBOUND_NON_STOP_FLIGHT_WITH_FREEDOM_AWARD).click()
        if(itineraryData.isRoundTrip()) {
            waitForElement(RETURN_NON_STOP_FLIGHT_WITH_FREEDOM_AWARD).click()
        }
    }

    def String pointsToEarn() {
        return waitForElement(POINTS_TO_EARN).getText()
    }

    def clickAccountSnapshotLink() {
        waitForElement(ACCOUNT_SNAPSHOT_LINK).click()
    }
}
