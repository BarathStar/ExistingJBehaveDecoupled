package pages.elements

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.BasePage
import pages.SearchFlightsPage
import pages.SelectFlightsPage

/**
 * This class holds objects for the variations of the Customized Booking Widget. <br>
 * Depending on the test case that is being run some of the page objects and methods
 * may be present.
 */
class StaticBookingWidget extends BasePage {

    SearchFlightsPage searchFlightsPage
    SelectFlightsPage selectFlightsPage
    private static final By OUTBOUND_DATE = By.name("outboundDateString")
    private static final By RETURN_DATE = By.name("returnDateString")
    private static final String DATE_FORMAT = "MM/dd/yyyy"
    private static final By SEARCH_BUTTON = By.name("book_now")
    private static final By ONE_WAY_RADIO_BUTTON = By.cssSelector("span:contains('One-Way')")
    private static final By ROUND_TRIP_RADIO_BUTTON = By.cssSelector("span:contains('Round Trip')")
    private static final By ORIGINS_AIRPORT = By.name("originAirport")
    private static final By ADULTPASSENGERCOUNTS = By.name("adultPassengerCount")
    private static final By SENIORPASSENGERCOUNTS = By.name("seniorPassengerCount")
    private static final By DESTINATION_AIRPORT = By.name("destinationAirport")
    private String currentPath
    //private static final By ONE_WAY_RADIO_BUTTON = By.xpath(".//*[@id='sw_content']/div[1]/div[2]/div/form/div[1]/div[1]/div/div[2]/label/input")

    public StaticBookingWidget(WebDriverProvider driverProvider) {
        super(driverProvider)
        GrooBe.activate()
       currentPath = driverProvider.get().currentUrl.toString()
    }

    void openBookingWidget(String url){
        System.out.println(currentPath)
        openUrl(currentPath + url)

    }


    /**
     * Fills in a date for the outbound flight the date will be formatted into MM/dd/yyyy format
     * @param tomorrow
     * @return
     */
    def fillBookingOutboundDate(Date tomorrow) {
        fillIn(OUTBOUND_DATE, tomorrow.format(DATE_FORMAT))
    }

    /**
     * Fills in a date for the return flight the date will be formatted into MM/dd/yyyy format
     * @param tomorrow
     * @return
     */
    def fillBookingReturnDate(Date tomorrow) {
        fillIn(RETURN_DATE, tomorrow.format(DATE_FORMAT))
    }

    /**
     * Handles clicking on the Search button that is present on the page
     */
    void clickOnSearchButton() {
        waitForElement(SEARCH_BUTTON).click()
    }

    def clickOnSearchButtonAndValidatePage() {
        String pageTitle = getTitle()
        clickOnSearchButton()
        waitForPageTitleToChangeOrOops(pageTitle)
        selectFlightsPage.verifyCurrentPageLocation()
    }

    /**
     * Verifies that the One-way option has been selected
     * @return
     */
    def verifyOneWayRadioButtonIsChecked() {
        waitForElement(ONE_WAY_RADIO_BUTTON).getAttribute("checked").shouldBe "true", "The one way radio button should be checked."
    }

    /**
     * Handles checking for the Round Trip option and selecting that option as well
     * @return
     */
    def clickRoundTripButton() {
        waitForElement(ROUND_TRIP_RADIO_BUTTON).click()
    }

    /**
     * Handles selecting the option for a One-way flight option
     * @return
     */
    def clickOneWayButton() {
        waitForElement(ONE_WAY_RADIO_BUTTON).click()
    }

    def verifyReturnDateIsDisabled() {
        waitForElement(RETURN_DATE).isEnabled().shouldBe false, 'The return station input field should be disabled and not available for input.'
    }

    def verifyWatermarkOnOutboundDate() {
        waitForElement(OUTBOUND_DATE).getAttribute("value").shouldBe "Depart", "The outbound date should contain a watermark."
    }

    def verifySelectFlightsPage(){
        checkSomethingServed()
    }

    /**
     * Sets the adult passenger count using values passed from the story file
     * @param adults the number of adult passengers
     * @return
     */
    def setAdultPassengerCount(String adults) {
        waitForElement(ADULTPASSENGERCOUNTS).click()
        waitForElement(ADULTPASSENGERCOUNTS).sendKeys(adults)
    }

    /**
     * Sets the senior passenger count using values passed from the story file
     * @param seniors the number of senior passengers
     * @return
     */
    def setSeniorPassengerCount(String seniors) {
        waitForElement(SENIORPASSENGERCOUNTS).click()
        waitForElement(SENIORPASSENGERCOUNTS).sendKeys(seniors)
    }


}
