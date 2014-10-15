package pages

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad

import util.Station

import static org.junit.Assert.assertEquals
import static org.hamcrest.core.IsNot.not
import static org.junit.Assert.assertThat
import static org.hamcrest.Matchers.greaterThan

import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.containsString

public class FlightSchedulesPage extends BasePage {

    SearchFlightsPage searchFlightsPage
    OutboundAndReturnDatesAndPopUp calendarPopUp

    private static final def MONTH_NAME = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"]
    private static final def DAY_NAME = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"]

    private static final By FLIGHT_SCHEDULES_TITLE = By.id("flight_schedules_title")
    private static final By FLIGHT_SCHEDULE_PAGE_HEADER = By.id("swa_page_header_title")
    private static final By SECONDARY_NAV_CONTAINER = By.id("secondaryNavContainerDiv")
    private static final By WEEKLY = By.id("weekly")
    private static final By FLIGHT_SCHEDULES_SUBMIT_BUTTON = By.id("flightSchedulesForm_submitButton")
    private static final By DAILY_FLIGHT_SCHEDULES_RESULTS_TABLE = By.cssSelector(".swa_feature_flightStatus_results_table_container.swa_tables_grayTable_container")
    private static final By WEEKLY_FLIGHT_SCHEDULES_RESULTS_TABLE = By.id("fs_flightSchedulesResultsWeekly_page")

    private static final By FLIGHT_NUMBERS_XPATH = By.xpath("//div[@class='swa_text_flightNumber']//ancestor::tr//div[@class='swa_text_flightNumber']")
    private static final By FLIGHT_TIMES_XPATH = By.xpath("//div[@class='swa_text_flightNumber']//ancestor::tr//*[@class='swa_text_time']")
    private static final By DAILY_FLIGHT_NUMBERS_INCLUDING_CONNECTION_XPATH = By.xpath("//td[@class='swa_feature_flightStatus_results_table_data_flightNumber']")
    private static final By WEEKLY_FLIGHT_NUMBERS_INCLUDING_CONNECTION_XPATH = By.xpath("//td[@class='swa_tables_firstCol swa_feature_flightSchedules_results_table_data_flightNumber']")

    private static final int DEPARTURE_TIME_COL = 0
    private static final int ARRIVAL_TIME_COL = 1


    private Map<String,List<String>> getFlightRows() {
        List<WebElement> flightNumberElements = waitForElements(FLIGHT_NUMBERS_XPATH)
        List<WebElement> flightTimeElements = waitForElements(FLIGHT_TIMES_XPATH)

        Map<String,List<String>> flightRowData = new HashMap<String,List<String>>();

        for (int row=0;row<flightNumberElements.size();row++) {
            int rowBase = row*2;
            List<String> rowValues = new ArrayList<String>();
            rowValues[0] =  flightTimeElements.get(rowBase+DEPARTURE_TIME_COL).getText().trim()
            rowValues[1] = flightTimeElements.get(rowBase+ARRIVAL_TIME_COL).getText().trim()
            String flightNumber = flightNumberElements.get(row).getText().trim()
            int index = flightNumber.indexOf("\n")  // take out Operated by
            flightNumber = (index > 0) ? flightNumber.substring(0, index) : flightNumber
            flightRowData.put(flightNumber,rowValues)
         }

         return  flightRowData
    }

    private WebElement flightSchedulePageHeader(){
        waitForElement(FLIGHT_SCHEDULE_PAGE_HEADER)
    }

    public FlightSchedulesPage(WebDriverProvider driverProvider) {
        super(driverProvider)
        GrooBe.activate()
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    def verifyPage(){
        super.verifyPage()
        waitForElement(FLIGHT_SCHEDULE_PAGE_HEADER).getText().shouldContain 'Flight Schedules', 'Page Header NOT present on Flight Schedules Page.'
    }

    def goToFlightSchedulePage() {
        searchFlightsPage.open()
        def Container = waitForElement(SECONDARY_NAV_CONTAINER)
        Container.findElement(By.linkText("Schedules")).click()
    }

    def searchWeekly() {
        waitForElement(WEEKLY).click()
        submit()
    }

    def submit() {
        waitForElement(FLIGHT_SCHEDULES_SUBMIT_BUTTON).click()
        verifyPage()
    }

    def verifyRouteInformationInTitle(String from, String to) {
        def fromCityName = Station.valueOf(Station.class, from).getCityName()
        def toCityName = Station.valueOf(Station.class, to).getCityName()
        waitForElement(FLIGHT_SCHEDULES_TITLE).getText().shouldContain fromCityName
        waitForElement(FLIGHT_SCHEDULES_TITLE).getText().shouldContain from
        waitForElement(FLIGHT_SCHEDULES_TITLE).getText().shouldContain toCityName
        waitForElement(FLIGHT_SCHEDULES_TITLE).getText().shouldContain to
    }

    def verifyOneActiveDayDisplayed(Date date) {
        final List<WebElement> activeDates = waitForElements(By.className("swa_tabs_carousel_list_item_active"))
        assertEquals(1, activeDates.size())
        activeDates.get(0).findElement(By.className("swa_tabs_carousel_list_item_header")).getText().shouldBe MONTH_NAME[date.getMonth()]
    }

    def verifyOneWeekDisplayedFrom(Date date) {
        final WebElement currentDay = waitForElement(By.className("swa_feature_flightSchedules_results_table_header_currentDay"))
        currentDay.findElement(By.className("swa_text_date_head")).getText().shouldBe DAY_NAME[date.getDay()]
        currentDay.findElement(By.className("swa_text_date_body")).getText().shouldBe String.format('%td', date)
    }

    def shouldHaveAtLeastOneFlightNumber() {
        def flightNumberCells = waitForElements(By.className("swa_text_flightNumber"))
        flightNumberCells.size().shouldBeGreaterThan 0
    }

    def verifyDailyFlightSchedulesTableDisplayed() {
        isElementPresent(DAILY_FLIGHT_SCHEDULES_RESULTS_TABLE).shouldBe true, "The daily flight schedules result table should be present on the page"
    }

    def verifyWeeklyFlightSchedulesTableDisplayed() {
        isElementPresent(WEEKLY_FLIGHT_SCHEDULES_RESULTS_TABLE).shouldBe true, "The weekly flight schedules result table should be present on the page"
    }

    def verifyDepartureTimeByFightNumber(String flightNumber, String expectedDepartureTime)
    {
        Map<String, List<String>> flightRows = getFlightRows();

        List<String> flightRow = flightRows.get(flightNumber);
        assertThat(flightRow,not(null))
        assertThat(flightRow.size(),greaterThan(0))
        String actualDepartureTime = flightRow.get(DEPARTURE_TIME_COL)
        assertThat(actualDepartureTime, equalTo(expectedDepartureTime))
    }

    def verifyArrivalTimeByFightNumber(String flightNumber, String departureTime)
    {
        Map<String, List<String>> flightRows = getFlightRows();

        List<String> flightRow = flightRows.get(flightNumber);
        assertThat(flightRow,not(null))
        assertThat(flightRow.size(),greaterThan(0))
        String actualArrivalTime = flightRow.get(ARRIVAL_TIME_COL)
        assertThat(actualArrivalTime,equalTo(departureTime))
    }

    def verifyDailyFlightNumberOperatedBy(String flightNumber, String OperatedBy) {
        List<WebElement> flightNumberElements = waitForElements(DAILY_FLIGHT_NUMBERS_INCLUDING_CONNECTION_XPATH)
        verifyFlightNumberOperatedBy(flightNumber,OperatedBy,flightNumberElements)

    }

    def verifyWeeklyFlightNumberOperatedBy(String flightNumber, String OperatedBy) {
        List<WebElement> flightNumberElements = waitForElements(WEEKLY_FLIGHT_NUMBERS_INCLUDING_CONNECTION_XPATH)
        verifyFlightNumberOperatedBy(flightNumber,OperatedBy,flightNumberElements)

    }

    private verifyFlightNumberOperatedBy(String flightNumber, String OperatedBy,  List<WebElement> flightNumberElements) {
        String fltNumberOptBy = ""
        for (int row=0;row<flightNumberElements.size();row++) {
            String fltNumOrOperatedBy = flightNumberElements.get(row).getText().trim()
            if (fltNumOrOperatedBy.contains(flightNumber) && fltNumOrOperatedBy.contains(OperatedBy)) {
                fltNumberOptBy = fltNumOrOperatedBy
                break
            }
        }
        assertThat(fltNumberOptBy,containsString(OperatedBy))
        assertThat(fltNumberOptBy,containsString(flightNumber))
    }

}