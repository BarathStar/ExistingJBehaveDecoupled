package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad

import static org.junit.Assert.fail

public class FlightStatusPage extends BasePage {

    private static final By AIRPORT_CODE_LINK = By.className("swa_links_airportCode")
    private static final By CLOSE_FLY_OUT_LINK = By.className("swa_flyouts_blueFlyout_closeLink")
    private static final By RESULT_DAY_TAB = By.id("result_day_tabs")

    private static final By FLIGHT_ARRIVAL_TIME = By.className("swa_feature_flightStatus_results_table_data_arrivedTime")
    private static final By FLIGHT_DEPARTURE_TIME = By.className("swa_feature_flightStatus_results_table_data_departedTime")
    private static final By FLIGHT_NUMBER_DETAILS_LINK = By.className("swa_feature_flightStatus_results_table_data_flightNumber_detailsLink")
    private static final By FLIGHT_STATUS_DELAYED = By.className("swa_text_flightStatusDelayed")
    private static final By FLIGHT_STATUS_TABLE_LIST = By.xpath("//table[@class ='swa_feature_flightStatus_results_table swa_tables_grayTable2']/tbody/tr")
    private static final By FLIGHT_VIEW_STATUS_LINK = By.className("swa_feature_flightStatus_results_table_data_status_link")

    private static final By SWA_BLUE_FLY_OUT_INNER_CONTENT = By.className("swa_flyouts_blueFlyout_content_inner")
    private static final By SWA_PAGE_HEADER = By.id("swa_page_header")
    private static final By SWA_ROW_ERROR_TEXT_WRAPPER = By.className("swa_row_error_text_wrapper")

    private WebElement findAirportInfoPopOut() {
        waitForElement(SWA_BLUE_FLY_OUT_INNER_CONTENT)
    }

    public FlightStatusPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    private WebElement findFlightStatusErrorMessages() {
        waitForElement(SWA_ROW_ERROR_TEXT_WRAPPER)
    }

    public WebElement findViewStatusLink() {
        waitForElement(FLIGHT_VIEW_STATUS_LINK)
    }

    public WebElement findDelayedFlightStatus() {
        return waitForElement(FLIGHT_STATUS_DELAYED)
    }

    public WebElement findDepartureTime() {
        return waitForElement(FLIGHT_DEPARTURE_TIME)
    }

    public WebElement findArrivalTime() {
        return waitForElement(FLIGHT_ARRIVAL_TIME)
    }

    private WebElement findCloseLink(){
        waitForElement(CLOSE_FLY_OUT_LINK)
    }

    public WebElement findFlightDetailsLink(){
        return waitForElement(FLIGHT_NUMBER_DETAILS_LINK)
    }

    String getRelativePath() {
        return "flight-status-results.html"
    }

    final static String EXPANDED = "swa_expandables_open"

    def verifyPage() {
        super.verifyPage()
        waitForElement(SWA_PAGE_HEADER).getText().shouldContain 'Flight Status:', 'Page Header NOT present on Flight Status Page.'
    }

    void verifyFlightStatusIsCollapsed() {
        waitForElement(FLIGHT_STATUS_TABLE_LIST).each {
            isRowExpanded(it).shouldBe false, "Not all rows are collasped in the Flight Status table: " + it.getText() + "."
        }
    }

    void verifyFlightStatusDisplays() {
        hasFlightStatusData().shouldBe true, "Row in Flight Status table is collapsed; expected to be expanded."
    }

    def verifyStationsInTable(String departureStation, String arrivalStation) {
        waitForElement(FLIGHT_STATUS_TABLE_LIST).findElement(By.className('swa_feature_flightStatus_results_table_data_departingFrom')).
                getText().contains(departureStation)
        waitForElement(FLIGHT_STATUS_TABLE_LIST).findElement(By.className('swa_feature_flightStatus_results_table_data_arrivingAt')).
                getText().contains(arrivalStation)
    }

    void verifyFlightNumberInTable(String flight) {
        waitForElement(FLIGHT_STATUS_TABLE_LIST).findElement(By.className("swa_text_flightNumber")).getText().shouldBe flight,
                "Flight number not found"
    }

    def verifyOperationByAirTran() {
        def rows = waitForElements(By.xpath("//table[@class ='swa_feature_flightStatus_results_table swa_tables_grayTable2']/tbody/tr"))
        String codeShare
        for (row in rows) {
            try {
                codeShare = row.findElement(By.className('codeShareAirline')).getText()
            } catch (NoSuchElementException) {
                codeShare = null
            }
            if (codeShare != null) {
                return codeShare.contains(Itinerary.OPERATED_BY_AIRTRAN)
            }
        }
        fail "${Itinerary.OPERATED_BY_AIRTRAN} is not indicated for any itinerary."
    }

    private boolean hasFlightStatusData() {
        return waitForElement(By.className("swa_text_flightStatus_status"), false)
    }

    private boolean isRowExpanded(WebElement flightStatusRow) {
        return flightStatusRow.getAttribute("className").contains(EXPANDED)
    }

    def expandFlightStatusRow(int rowNumber) {
        def rows = waitForElements(By.xpath("//table[@class ='swa_feature_flightStatus_results_table swa_tables_grayTable2']/tbody/tr"))
        if (rowNumber < rows.size())
            expandFlightStatusRow(rows.get(rowNumber))
    }

    def expandFlightStatusRow(WebElement row) {
        row.findElement(By.className("swa_feature_flightStatus_results_table_data_status_link")).click()
    }

    public void verifyNoFlightsForStationsOnDateError(String departureStation, String arrivalStation) {
        findFlightStatusErrorMessages().getText().shouldContain departureStation
        findFlightStatusErrorMessages().getText().shouldContain arrivalStation
        findFlightStatusErrorMessages().getText().shouldContain "are not scheduled to operate on"
        findFlightStatusErrorMessages().getText().shouldContain "Please select an alternate date or choose another origin/destination. Please visit the Southwest Airlines interactive route map for alternate choices."
    }

    void clickStationAirportInfo() {
        WebElement airPortLink = waitForElement(AIRPORT_CODE_LINK)
        airPortLink.isDisplayed().shouldBe true, "not displayed"
        airPortLink.isEnabled().shouldBe true, "not enabled"
        airPortLink.click()
    }

    void verifyAirportInfoPopOut() {
        findAirportInfoPopOut().isDisplayed().shouldBe true, "Airport Information pop out is not displayed"
        findAirportInfoPopOut().isEnabled().shouldBe true, "Airport Information pop out is not enabled"
    }

    void selectLinkOnAirportInfoPopUp(String link) {
        switch (link) {

            case "Airport info":
                WebElement airPortInfo = waitForElement(By.xpath("//a[@href = 'http://www.southwest.com/html/air/airport-information.html']"), true, 20, 1)
                airPortInfo.click()
                break

            case "View Status":
                findViewStatusLink().click()
                break

            case "Close":
                findCloseLink().click()
                break

            default:
                fail("Did Not Find ${link}")
        }

        WaitForPageToLoad
    }

    void returnBackToFlightStatusPage() {
        def handle = getWindowHandles()
        switchTo().window(handle.toArray()[0])
        verifyAirportInfoPopOut()
        WaitForPageToLoad
    }

    def verifyFlightStatusTableIsPresent() {
        isElementDisplayed(RESULT_DAY_TAB).shouldBe true, "The Flight Status Table should be present"
    }

    def verifySomeDelayedFlightIsPresent() {
        isElementDisplayed(FLIGHT_STATUS_DELAYED).shouldBe true, "There must be some delayed flight present on results"
    }
}
