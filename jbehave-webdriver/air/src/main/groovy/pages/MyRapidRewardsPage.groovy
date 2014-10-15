package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.GlobalNavigationHeader

import static org.junit.Assert.fail

class MyRapidRewardsPage extends BasePage {

    private static final By AWARD_TYPE = By.className("awardType")
    private static final By CERTIFICATE_NUMBER = By.className("certificateNumber")
    private static final By EXPIRATION_DATE = By.className("expirationDate1")
    private static final By AVAILABLE_FOR_REISSUE = By.className("reissue1")
    private static final By FIND_SEAT_AVAILABILITY = By.id("AwardSeatFinder")
    private static final By BOOK_A_FLIGHT = By.id("AwardBookNow")
    private static final By AWARD_TERMS_CONTENT = By.className("award_terms_content")
    private static final String STANDARD_AWARD_TERMS_CONTENT_MSG = "NOW ARRIVING — YOUR STANDARD AWARD!"
    private static final String FREEDOM_AWARD_TERMS_CONTENT_MSG = "NOW ARRIVING — YOUR FREEDOM AWARD!"
    private static final By FREEDOM_AWARDS_TERMS_AND_CONDITIONS_LINK = By.xpath("//div[@class='productNameContainer' and contains(.,'Freedom')]//a")
    private static final By STANDARD_AWARDS_TERMS_AND_CONDITIONS_LINK = By.xpath("//div[@class='productNameContainer' and contains(.,'Standard')]//a")
    private static final By TRANSITION_AWARDS_TERMS_AND_CONDITIONS_LINK = By.xpath("//div[@class='productNameContainer' and contains(.,'Transition')]//a")
    private static final By DREAM_TRIPS_SNAPSHOT_SECTION = By.cssSelector("#dream_trip_snapshot a.dream_trip_name")
    private static final By DREAM_TRIPS_LIST_SECTION = By.cssSelector("#dream_trip_list_all a.dream_trip_name")
    private static final By DREAM_TRIPS_VIEW_ALL_LINK = By.cssSelector(".viewAll")
    private static final By DREAM_TRIP_CHECK_DATES_BUTTON = By.xpath("//input[contains(@name, 'checkDreamTripAvailability')]");
    private static final By DREAM_TRIP_LEFT_NAV_LINK = By.xpath("//div[@id='flight_tracker_dream_trip_message']//a")
    private static final By DREAM_TRIP_NAME_HEADER = By.className("dream_trip_name")
    private static final By BUY_AND_TRANSFER_POINTS_LINK = By.id("buy-transfer-points-link")
    private static final By ACCOUNT_EXPIRATION_DATE = By.className("global-account-bar-login-last-activity")
    private static final By RECENT_ACTIVITY_TABLE = By.xpath("//table[@class='recentPointsActivity tierStatusTable']//tr")
    private GlobalNavigationHeader globalNavigationHeader


    def MyRapidRewardsPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, "/account/rapidrewards")

        globalNavigationHeader = new GlobalNavigationHeader(webDriverProvider)
    }

    @Override
    String getRelativePath() {
        return "/account/rapidrewards"
    }

    def verifyOnRewardsTransferPage() {
       verifyCurrentPageLocation()
    }

    def verifyAwardsFromTransitionProgram() {
        verifyStandardAwardsInformation()
        isElementWithTextPresent(AVAILABLE_FOR_REISSUE, "No").shouldBe true, "Available for reissue should be NO"
    }

    def verifyFlightBookingWithAwards() {
        verifyElementPresent("Find Seat Availability", FIND_SEAT_AVAILABILITY)
        verifyElementPresent("Book a Flight", BOOK_A_FLIGHT)
    }

    def clickStandardAwardsAvailability() {
        verifyElementPresent("Find Seat Availability", FIND_SEAT_AVAILABILITY)
        waitForElement(FIND_SEAT_AVAILABILITY).click()
    }

    def verifyTermsAndConditionsFromTrasitionProgramPage() {
        verifyIamOnTheCorrectPage("account/rapidrewards/certificates/transitional-award-conditions-and-terms")
        isElementWithTextPresent(AWARD_TERMS_CONTENT, STANDARD_AWARD_TERMS_CONTENT_MSG).shouldBe true, "I sould see the " + STANDARD_AWARD_TERMS_CONTENT_MSG + " verbiage"
    }

    def verifyIamOnTheCorrectPage(String url) {
        getCurrentUrl().contains(url).shouldBe true
    }

    def verifyStandardAwardsInformation() {
        isElementWithTextPresent(AWARD_TYPE, "Standard Award").shouldBe true, "The award type should be Standard Award"
        verifyElementPresent("Certificate Number", CERTIFICATE_NUMBER)
        verifyElementPresent("Expiration date", EXPIRATION_DATE)
    }

    def verifyAwardsAvailableForReissue() {
        isElementWithTextPresent(AVAILABLE_FOR_REISSUE, "Yes").shouldBe true, "Available for reissue should be Yes"
    }

    def verifyTermsAndConditionsOfStandardAwardsPage() {
        verifyIamOnTheCorrectPage("account/rapidrewards/certificates/standard-award-conditions-and-terms")
        isElementWithTextPresent(AWARD_TERMS_CONTENT, STANDARD_AWARD_TERMS_CONTENT_MSG).shouldBe true, "I sould see the " + STANDARD_AWARD_TERMS_CONTENT_MSG + " verbiage"
    }

    def verifyFreedomAwardsInformation() {
        isElementWithTextPresent(AWARD_TYPE, "Freedom Award").shouldBe true, "The award type should be Freedom Award"
        verifyElementPresent("Certificate Number", CERTIFICATE_NUMBER)
        verifyElementPresent("Expiration date", EXPIRATION_DATE)
    }

    def verifyTermsAndConditionsFromFreedomAwardsPage() {
        verifyIamOnTheCorrectPage("account/rapidrewards/certificates/freedom-award-conditions-and-terms")
        isElementWithTextPresent(AWARD_TERMS_CONTENT, FREEDOM_AWARD_TERMS_CONTENT_MSG).shouldBe true, "I sould see the " + FREEDOM_AWARD_TERMS_CONTENT_MSG + " verbiage"
    }

    def verifyDreamTripsSnapshotSection() {
        verifyElementPresent("Dream Trip Snapshot Section",DREAM_TRIPS_SNAPSHOT_SECTION)
    }

    def verifyDreamTripsListSection() {
        verifyElementPresent("Dream Trip List Section",DREAM_TRIPS_LIST_SECTION)
    }

    def verifyTripInDreamTripsSnapshotSection() {
        verifyElementPresent("Dream Trip", DREAM_TRIP_CHECK_DATES_BUTTON)
    }

    def clickFlightTrackerCheckDatesButton() {
        waitForElement(DREAM_TRIP_CHECK_DATES_BUTTON).click()
        verifyPage()
    }

    def clickFlightTrackerCheckDatesButtonAndDoNotVerify() {
        waitForElement(DREAM_TRIP_CHECK_DATES_BUTTON).click()
    }

    def clickViewAllFlightTrackersLink() {
        waitForElement(DREAM_TRIPS_VIEW_ALL_LINK).click()
        verifyPage()
    }

    def clickFreedomAwardTermsAndConditions() {
        waitForElement(FREEDOM_AWARDS_TERMS_AND_CONDITIONS_LINK).click()
        verifyPage()
    }

    def clickStandardAwardTermsAndConditions() {
        waitForElement(STANDARD_AWARDS_TERMS_AND_CONDITIONS_LINK).click()
        verifyPage()
    }

    def clickTransitionAwardTermsAndConditions() {
        waitForElement(TRANSITION_AWARDS_TERMS_AND_CONDITIONS_LINK).click()
        verifyPage()
    }

    def clickTrackYourTripLink() {
        waitForElement(DREAM_TRIP_LEFT_NAV_LINK).click()
        verifyPage()
    }

    def verifyDreamTripLink(String dreamTripName) {
        By byDreamTripName = By.linkText(dreamTripName)
        verifyElementPresent(dreamTripName , byDreamTripName)
    }

    def clickDreamTripNameLink(String dreamTripName) {
        By byDreamTripName = By.linkText(dreamTripName)
        waitForElement(byDreamTripName).click()
        verifyPage()
    }

    def verifyDreamTripDetailsPageFor(String dreamTripName) {
        verifyIamOnTheCorrectPage("/account/rapidrewards/dream-trip-details")
        verifyTextPresent(dreamTripName, DREAM_TRIP_NAME_HEADER)
    }

    def clickBuyAndTransferPoints() {
        waitForElement(BUY_AND_TRANSFER_POINTS_LINK).click()
    }

    def clickRRGlobalNavigationHeader() {
        globalNavigationHeader.clickRRGlobalNavigationHeader()
    }

    def selectBuyAndTransferPointsFromQuickLinks() {
        chooseInDropDownByText("quickLinks", "Buy, Gift & Transfer Points")
    }

    def selectAddRapidRewardsNumberFromQuickLinks() {
        chooseInDropDownByText("quickLinks", "Add Rapid Rewards Number")
    }

    def shouldSeeAccountExpirationDate(String text) {
        waitForElement(ACCOUNT_EXPIRATION_DATE).getText().equalsIgnoreCase text
    }

    def shouldSeeRecentRewardsActivities() {
        List<WebElement> table = waitForElements(RECENT_ACTIVITY_TABLE)
        if (table.size() <= 1) {
            fail('The table Recent Rewards Activity is empty. It should contain at least one element.')
        }
    }

    def shouldNotSeeLastActivityDateOrExpirationCountdown() {
        !checkIfElementExists(ACCOUNT_EXPIRATION_DATE)
    }
}