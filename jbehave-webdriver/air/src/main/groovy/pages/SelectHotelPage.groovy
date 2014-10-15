package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import util.HotelItineraryData
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class SelectHotelPage extends BasePage{

    private static final String TWO_ROOMS_GUESTS_ERROR_MSG = "The maximum number of total guests for 2 rooms may not exceed 12. Please adjust the number of guests."
    private static final String ONE_ROOM_GUESTS_ERROR_MSG = "The maximum number of total guests in a room may not exceed 6. Please adjust the number of guest"
    private static final String STREET_VIEW = "Street view"
    private static final By MARKET_SEARCH_KEYWORD = By.id("marketSearchKeyword")
    private static final By ADULTS = By.id("adults")
    private static final By CHILDREN = By.id("children")
    private static final By VIEW_MAP = By.id("show_map_button")
    private static final By MAP_CANVAS = By.id("map_canvas_hotel")
    private static final By STREET_VIEW_LINK = By.xpath("//div[@class='hotel_map_info_image']/a")
    private static final By MAP_SECTION = By.id("map_section")
    private static final By HOTEL_COMPARE_CHECKBOX = By.className("hotel_select_compare_checkboxinput")
    private static final By HOTEL_COMPARE_BUTTON = By.className("hotel_compare_button")
    private static final By SHOW_COMPARE_MAP = By.id("compare_modal_show_map")
    private static final By COMPARE_MAP = By.id("compare_modal_map_section")
    private static final By INBOUND_DATE = By.id("returnDate")
    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By AREA_SELECT = By.id("areaSelect")

    HotelItineraryData hotelItineraryData

    public SelectHotelPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/hotels/select-hotel.html')
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def open() {
        super.open()
        checkSomethingServed()
    }

    def openWithParameters() {
       String[] params = ["marketSearchKeyword=${hotelItineraryData.destination}",
                "checkInDate=${getmmddyyyyDateFormat(hotelItineraryData.checkInDate.toCalendar())}" ,
                "checkOutDate=${getmmddyyyyDateFormat(hotelItineraryData.checkOutDate.toCalendar())}" ,
                "adults=${hotelItineraryData.adults}" ,
                "children=${hotelItineraryData.children}"];

        openWithArgs(params)
    }

    def verifyRoomsGuestsLimit() {
        if(hotelItineraryData.rooms.equalsIgnoreCase("2")) {
            shouldShowOopsMessage(TWO_ROOMS_GUESTS_ERROR_MSG)
        } else {
            shouldShowOopsMessage(ONE_ROOM_GUESTS_ERROR_MSG)
        }
    }

    def verifyValidHotelDataSelected(){
        String airport = waitForElement(MARKET_SEARCH_KEYWORD).getAttribute("value")
        assertTrue(airport.contains(hotelItineraryData.destination))
        assertEquals(hotelItineraryData.adults, waitForElement(ADULTS).getAttribute("value"))
        assertEquals(hotelItineraryData.children, waitForElement(CHILDREN).getAttribute("value"))
    }

    def viewMap() {
        waitForElement(VIEW_MAP).click()
    }

    def verifyHotelMap() {
        verifyMap(waitForElement(MAP_CANVAS))
    }

    def verifyMap(map) {
        int size = map.getText().size()
        (size > 0).shouldBe true, "Google Map should be visible"
    }

    def clickStreetView() {
        try {
            sleep(5000)
            waitForElement(STREET_VIEW_LINK).click()
        } catch (Exception e) {
            println("Street view link not found the first time")
            sleep(45000)
            waitForElement(STREET_VIEW_LINK).click()
        }
    }

    def verifyStreetView() {
        verifyHotelMap()
        waitForElementWithText(MAP_CANVAS, STREET_VIEW)
    }

    def checkHotel(int hotelNumber) {
        def index = hotelNumber - 1
        List<WebElement> checkboxes = waitForElements(HOTEL_COMPARE_CHECKBOX)
        checkboxes.get(index).click()
    }

    def compareHotels() {
        waitForElement(HOTEL_COMPARE_BUTTON).click()
    }

    def clickCompareMap() {
        waitForElement(SHOW_COMPARE_MAP).click()
    }

    def verifyCompareMap() {
        verifyMap(waitForElement(COMPARE_MAP))
    }

    def clickHotelMarker(int hotelNumber) {
        int index = hotelNumber - 1;
        def executor = (JavascriptExecutor) webDriver()
        executor.executeScript("google.maps.event.trigger(SWA.compareMarkers[${index}], 'click');" )
    }

    def verifyInfoWindow() {
        def infoWindow = waitForElement(COMPARE_MAP)
        infoWindow.getText().contains("Street view").shouldBe true, "Street view link should be present"
    }

    def verifyValidHotelDatesSelected() {
        Date outboundDateSearch =  new Date().parse(SelectHotelRatePage.DATE_FORMAT, waitForElement(OUTBOUND_DATE).getAttribute("value"))
        Date returnDateSearch =  new Date().parse(SelectHotelRatePage.DATE_FORMAT, waitForElement(INBOUND_DATE).getAttribute("value"))


        Date expectedOutboundDate = hotelItineraryData.checkInDate
        Date expectedReturnDate = hotelItineraryData.checkOutDate

        (outboundDateSearch.format(SelectHotelRatePage.DATE_FORMAT) == expectedOutboundDate.format(SelectHotelRatePage.DATE_FORMAT))
                .shouldBe true, "Outbound date expected $expectedOutboundDate is not the same as the one shown in the search widget $outboundDateSearch"

        (returnDateSearch.format(SelectHotelRatePage.DATE_FORMAT) == expectedReturnDate.format(SelectHotelRatePage.DATE_FORMAT))
                .shouldBe true, "Inbound date expected $expectedReturnDate is not the same as the one shown in the search widget $returnDateSearch"
    }

    def selectHotelArea(int areaNumber) {
        selectFromDropDownByIndex(AREA_SELECT, areaNumber)
    }
}
