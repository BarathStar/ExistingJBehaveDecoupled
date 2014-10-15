package htmlunit.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage {

    private static final By ORIGIN_AIRPORT_DISPLAYED_JSOFF = By.id("originAirport");
    private static final By DESTINATION_AIRPORT_DISPLAYED_JSOFF = By.id("destinationAirport");
    private static final By ITINERARY_ONEWAY_JSOFF = By.id("oneWay");
    private static final By ITINERARY_ROUNDTRIP_JSOFF = By.id("roundTrip");

    public String jsOffFormatDeparture;
    public String jsOffFormatReturn;
    public String jsOffFormatCheckinDeparture;
    public String jsOffFormatCheckinReturn;

    WebDriver driver;

    public HomePage(WebDriver webDriver) {
        driver = webDriver;


        Date departureDate = util.ItineraryDateFactory.getAnyAvailableDepartureDate();
        Date returnDate = util.ItineraryDateFactory.getAnyAvailableReturnDateAfter(departureDate);

        new SimpleDateFormat("MM/dd/yyyy").format(returnDate);
        jsOffFormatDeparture = new SimpleDateFormat("MM/dd/yyyy").format(departureDate);
        jsOffFormatReturn = new SimpleDateFormat("MM/dd/yyyy").format(departureDate);

        Date checkinDepartureDate = util.ItineraryDateFactory.chooseDayEligibleForDepartureCheckin();
        Date checkinReturnDate = util.ItineraryDateFactory.chooseDayEligibleForReturnCheckin();
        jsOffFormatCheckinDeparture = new SimpleDateFormat("MM/dd/yyyy").format(checkinDepartureDate);
        jsOffFormatCheckinReturn = new SimpleDateFormat("MM/dd/yyyy").format(checkinReturnDate);
    }


    public void openPage(String url) {
        driver.get(url);
        Assert.assertTrue(driver.getPageSource().contains("body"));
    }

    public void selectItineraryType(String itineraryType) {
        if (itineraryType.equalsIgnoreCase("oneway")) {
            driver.findElement(ITINERARY_ONEWAY_JSOFF).click();
        } else {
            driver.findElement(ITINERARY_ROUNDTRIP_JSOFF).click();
        }
    }

    public void fillInFlightDates() {
        driver.findElement(By.id("outboundDateAir")).sendKeys(jsOffFormatDeparture);
        driver.findElement(By.id("returnDateAir")).sendKeys(jsOffFormatReturn);
    }

    public void fillInForCheckinFlightDates() {
        driver.findElement(By.id("outboundDateAir")).sendKeys(jsOffFormatCheckinDeparture);
        driver.findElement(By.id("returnDateAir")).sendKeys(jsOffFormatCheckinReturn);
    }

    public void fillInAirportsJSOff(String originAirport, String destinationAirport) {

        Select selectoutBound = new Select(driver.findElement(ORIGIN_AIRPORT_DISPLAYED_JSOFF));
        selectoutBound.selectByValue(originAirport);

        Select selectInBound = new Select(driver.findElement(DESTINATION_AIRPORT_DISPLAYED_JSOFF));
        selectInBound.selectByValue(destinationAirport);
    }

    public void clickSearchForFlights() {
        driver.findElement(By.id("booking_widget_content_row_btn_search")).click();
        Assert.assertNotNull(driver.findElement(By.id("out_depart_header")));
    }
}
