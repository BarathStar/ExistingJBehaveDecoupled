package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor

/**
 * Page class to drive stories using destination finder page
 *
 */
class DestinationFinderPage extends BasePage {

    private static final By TRIP_FINDER_BUTTON = By.className("trip_finder_button")
    private static final By AMOUNT = By.id("amount")
    private static final By DESTINATION_FINDER_HELP = By.id("destinationFinderHelp")
    private static final By TRACK_IT = By.id("saveIt_points")
    private static final By GET_STARTED= By.cssSelector("div a.hideDestinationFinderHelp img")
    private static final By NEED_HELP_LOGGIN_IN = By.id("swa_recoveryUserData_modal")
    private static final By ORIGIN_AIRPORT = By.id("originAirport_displayed")
    private static final By DESTINATION_AIRPORT = By.id("destinationAirport_displayed")
    private static final By SLIDER_POINTS_RANGE = By.className("slider_zone")
    private static final By POINTS_RANGE_OUTPUT = By.className("points_range_output")
    private static final By POINTS_AMOUNT = By.id("amount")
    private static final By DESTINATION_FINDER_MAP = By.id("destination_finder_map_large")
    private static final By TRIP_NAME_FIELD = By.id("tripName")
    private static final By SAVE_DREAM_TRIP_BUTTON = By.id("saveDreamTripButton")
    private static final By SUCCESS_MODAL_CLOSE_BUTTON = By.cssSelector("#successModalClose .simplemodal-close")
    private static final By FLY_OUT_CONTAINER = By.id("small_popup")
    private static final By FLY_OUT_CITY = By.cssSelector("#small_popup div.small_popup_city")
    private static final By FLY_OUT_CITY_MESSAGE_CONTAINER = By.id("large_popup_container_points")
    private static final By FLY_OUT_CITY_MESSAGE_CONTAINER_BODY = By.id("destinationCityPoints")
    private static final By CHECK_DATES = By.id("checkDates_points")
    private static final By FLIGHT_TRACKER = By.id("saveTripModal")
    private static final String POINTS_AMOUNT_PATTERN = /0\s-\s\d+,\d+/

    public DestinationFinderPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/travel_center/destination-finder');
    }

    String getRelativePath() {
        return "/travel_center/destination-finder"
    }

    def open() {
        super.open()
        verifyPage()
    }

    def destinationFinder() {
        waitForElement(TRIP_FINDER_BUTTON).click()
    }

    def defaultPointsView() {
        def points = waitForElement(AMOUNT).getAttribute("value")
        points.equalsIgnoreCase("0 - 71,000").shouldBe true, "Default City Range not found $points"
    }

    def deleteDestinationFinderHelpHasBeenShown() {
        manage().deleteCookieNamed("destinationFinderHelpHasBeenShown")
    }

    def helpPageDisplay() {
        String destinationFinderStyle = waitForElement(DESTINATION_FINDER_HELP).getAttribute("style")
        destinationFinderStyle.contains("display: block").shouldBe true, "On First Login Help Window is not Visible $str"
    }

    def noHelpPageDisplay() {
        String destinationFinderStyle = waitForElement(DESTINATION_FINDER_HELP).getAttribute("style")
        destinationFinderStyle.contains("display: block").shouldBe false, "Help Window is Visible on second login $str"
    }

    def clickOnTrackIt(){
        waitForElement(TRACK_IT).click()
    }

    def clickOnNeedHelploggoingIn() {
        waitForElement(NEED_HELP_LOGGIN_IN).click()
    }

    def moveMouseOver() {
        def executor = (JavascriptExecutor) webDriver()
        return executor.executeScript("\$('#SCL').mouseover();")
    }

    def verifyOriginAirportIsDisplayed() {
        verifyElementPresent("Departing city filter", ORIGIN_AIRPORT)
    }

    def verifyDestinationAirportIsDisplayed() {
        verifyElementPresent("Destination city filter", DESTINATION_AIRPORT)
    }

    def verifySliderPointsRangeIsDisplayed() {
        verifyElementPresent("Slider Points Range", SLIDER_POINTS_RANGE)
    }

    def verifyPointsRangeTitleIsDisplayed() {
        waitForElement(POINTS_RANGE_OUTPUT).getText().shouldBe "Points Range:"
    }

    def verifyPointsRangeAmount() {
        waitForElement(POINTS_AMOUNT).getAttribute("value").matches ~ POINTS_AMOUNT_PATTERN shouldBe true
    }

    def clickOnGetStarted(){
        waitForElement(GET_STARTED).click()
    }

    def verifyDestinationFinderMapIsDisplayed() {
        verifyElementPresent("Destination Finder Map", DESTINATION_FINDER_MAP)
    }

    def fillDestinationCity(String city) {
        fillIn(DESTINATION_AIRPORT, city, true)
    }

    def fillTripNameField(String tripName){
        fillIn(TRIP_NAME_FIELD,tripName)
    }

    def clickOnSaveDreamTripButton(){
        waitForElement(SAVE_DREAM_TRIP_BUTTON).click()
    }

    def clickOnSuccessModalCloseButton(){
        waitForElement(SUCCESS_MODAL_CLOSE_BUTTON)
    }

    def mouseoverCityAction(String airportCode){
        def executor = (JavascriptExecutor) webDriver()
        return executor.executeScript("\$('#" + airportCode + "').trigger('mouseover');")
    }

    def verifyCityFlyout(String airportCode){
        waitForElement(FLY_OUT_CONTAINER).isDisplayed().shouldBe true, "The City Flyout should be present"
        waitForElement(FLY_OUT_CITY).getText().shouldContain airportCode
    }

    def verifyCityFlyoutMessage(String cityName){
        waitForElement(FLY_OUT_CITY_MESSAGE_CONTAINER).isDisplayed().shouldBe true, "The City Flyout Message should be present"
        waitForElement(FLY_OUT_CITY_MESSAGE_CONTAINER_BODY).getText().shouldContain cityName
    }

    def selectCityAsDeparting(String airportCode){
        waitForElement(ORIGIN_AIRPORT).click()
        waitForElement(ORIGIN_AIRPORT).sendKeys DELETE_EXISTING + airportCode + ENTER
    }

    def clickOnCheckDates(){
        waitForElement(CHECK_DATES).click()
    }

    def verifyFlightTrackerPopUpIsDisplayed(){
        waitForElement(FLIGHT_TRACKER).isDisplayed().shouldBe true, "The Flight Tracker pop up window should be present"
    }
}
