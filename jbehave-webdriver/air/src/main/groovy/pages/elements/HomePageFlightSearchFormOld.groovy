package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import static util.Locators.ELEMENT_IDS
import org.openqa.selenium.support.ui.Select
import fixture.stubs.DynaStubsIntegration
import util.ItineraryData
import org.openqa.selenium.By

class HomePageFlightSearchFormOld extends FlightSearchForm {

    private static final String SUBMIT_BUTTON = "booking_widget_content_row_btn_search"

    @Override
    String getOutboundDate() {
        "outboundDateAir"
    }

    @Override
    String getReturnDate() {
        "returnDateAir"
    }

    public HomePageFlightSearchFormOld(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    def fillInFlightSearchInfoAndClick(ItineraryData itineraryData, boolean createData, String buttonId = SUBMIT_BUTTON) {
        super.fillInFlightSearchInfoAndClick(itineraryData, createData, buttonId)
    }

    def fillInHomePageSearchDataAndSubmit(ItineraryData itineraryData,boolean verifyNoOpps = true) {

        selectItineraryType(itineraryData)

        fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)

        enterDepartureDate(itineraryData.departureDate,getOutboundDate())

        if(itineraryData.isRoundTrip()) {
            enterReturnDate(itineraryData.returnDate,getReturnDate())
        }

        if (itineraryData.adultPassengerCount > 0) {
            (new Select(findElement(By.id("adultPassengerCount")))).selectByValue(itineraryData.adultPassengerCount.toString())
             addAdultPassengers((itineraryData.adultPassengerCount) as int )
        }
        if(itineraryData.seniorPassengerCount > 0) {
            (new Select(findElement(By.id("seniorPassengerCount")))).selectByValue(itineraryData.seniorPassengerCount.toString())
            addSeniorPassengers((itineraryData.seniorPassengerCount) as int )
        }
        if (DynaStubsIntegration.useDynaStubs()) {
            final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
            dynaStubsIntegration.prepareWeeklyOrDailyFlightSchedules(itineraryData)
            dynaStubsIntegration.prepareShoppingSchedules(itineraryData)
        }

        (verifyNoOpps) ? submit("booking_widget_content_row_btn_search") : waitForElement(By.id("booking_widget_content_row_btn_search")).click()
    }

}
