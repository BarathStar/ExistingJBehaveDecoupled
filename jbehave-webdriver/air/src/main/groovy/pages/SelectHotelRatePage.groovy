package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import util.HotelItineraryData
import static org.junit.Assert.assertEquals

class SelectHotelRatePage extends BasePage {

    private static final By OUTBOUND_DATE_SEARCH = By.id("outboundDate")
    private static final By INBOUND_DATE_SEARCH = By.id("returnDate")
    private static final By HOTEL_NAME_LINK = By.className("hotel_select_hotel_name_link")
    private static final By MAP_TAB = By.id("tabanchor_detailsMap")
    private static final By MAP_MARKER = By.id("mapMarker")
    private static final By MAP_SECTION = By.id("map_section")

    private static final String DATE_FORMAT = "MM/dd/yyyy"
    private static final String ADULTS_DROPDOWN_SEARCH = "adults"
    private static final String CHILDREN_DROPDOWN_SEARCH = "children"
    private static final String ROOMS_DROPDOWN_SEARCH = "rooms"
    private static final String GOOGLE_TERMS_OF_USE = "Google - Terms of Use"
    private static final String NUMBER_OF_HOTEL_ROOMS = "1"

    HotelItineraryData hotelItineraryData

    public SelectHotelRatePage(WebDriverProvider driverProvider) {
        super(driverProvider, '/hotels/select-hotel-rate.html');
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    def verifySearchDataPopulation() {
        Date outboundDateSearch =  new Date().parse(DATE_FORMAT, waitForElement(OUTBOUND_DATE_SEARCH).getAttribute("value"))
        Date inboundDateSearch =  new Date().parse(DATE_FORMAT, waitForElement(INBOUND_DATE_SEARCH).getAttribute("value"))

        Date expectedOutboundDate = hotelItineraryData.checkInDate
        Date expectedInboundDate = hotelItineraryData.checkOutDate

        (outboundDateSearch.format(DATE_FORMAT) == expectedOutboundDate.format(DATE_FORMAT))
                .shouldBe true, "Outbound date expected $expectedOutboundDate is not the same as the one shown in the search widget $outboundDateSearch"

        (inboundDateSearch.format(DATE_FORMAT) == expectedInboundDate.format(DATE_FORMAT))
                .shouldBe true, "Inbound date expected $expectedInboundDate  is not the same as the one shown in the search widget $inboundDateSearch"

        isOptionInDropDownSelectedByValue(ADULTS_DROPDOWN_SEARCH, hotelItineraryData.adults).shouldBe true, "The number of adults populated does not match the one selected previously $hotelItineraryData.adults"

        isOptionInDropDownSelectedByValue(CHILDREN_DROPDOWN_SEARCH, hotelItineraryData.children).shouldBe true,"The number of children populated does not match the one selected previously $hotelItineraryData.children"

        isOptionInDropDownSelectedByValue(ROOMS_DROPDOWN_SEARCH, NUMBER_OF_HOTEL_ROOMS).shouldBe true, "The number of rooms populated does not match the one selected previously $hotelItineraryData.rooms"
    }

    def selectHotel() {
        waitForElement(HOTEL_NAME_LINK).click()
    }

    def clickMapTab() {
        waitForElement(MAP_TAB).click()
    }

    def verifyDetailsMap() {
        WebElement map = waitForElement(MAP_SECTION)
        int size = map.getText().size()
        (size > 0).shouldBe true, "Google Map should be visible"
    }

    def clickMarker() {
        def executor = (JavascriptExecutor) webDriver()
        executor.executeScript("google.maps.event.trigger(SWA.hotelMarker, 'click');")
    }

    def verifyInfoWindow() {
        def infoWindow = waitForElement(MAP_SECTION)
        infoWindow.getText().contains("Get directions").shouldBe true, "Get directions link should be present"
        infoWindow.getText().contains("Search nearby").shouldBe true, "Search nearby link should be present"
        infoWindow.getText().contains("Street view").shouldBe true, "Street view link should be present"
        infoWindow.getText().contains(GOOGLE_TERMS_OF_USE).shouldBe(true)
    }

    def verifyHotelId() {
        String hotelId = ViewReservationPage.getHotelIdFromUrl(this.currentUrl)
        assertEquals(hotelItineraryData.hotelId, hotelId)
    }
}