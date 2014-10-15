package pages

import com.swacorp.dotcom.webscenarios.air.Data
import fixture.stubs.DynaStubsIntegration
import java.text.SimpleDateFormat
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import state.CarReservationData
import state.Flow
import util.CarRentalType
import util.CarVendor
import util.ItineraryDateFactory
import static util.Locators.car

public class SearchCarsPage extends BasePage {

    private static final By PICK_UP_LOCATION_TEXT = By.id("pickUpLocationText")
    private static final By PICK_UP_LOCATION_DISPLAYED = By.id("pickUpLocation_displayed")
    private static final By DROP_OFF_LOCATION_TEXT = By.id("dropOffLocationText")
    private static final By VENDOR = By.id("vendors_rgbmultiselect")
    private static final By GLOBAL_NAV_CAR_BUTTON = By.id("globalnav_header_primary_link_car")
    private static final By MANAGE_CAR_RESERVATION = By.id("secondaryNavContainer")
    private static final By OUTBOUND_DATE = By.cssSelector(".outboundDate")
    private static final By RETURN_DATE = By.cssSelector(".returnDate")
    private static final By CAR_SEARCH_FORM_SUBMIT_BUTTON = By.id("car-search-form-submit-button")
    private static final By CAR_SEARCH_CROSS_SELL_FORM_SUBMIT_BUTTON = By.id("car-cross-sell-submit-button")
    private static final By MAIN_FORM_CONTAINER = By.className("carhotel_search_form_inner_wrapper")
    private static final By CLEAR_VENDOR_SELECTED = By.className("jquery_rgbmultiselect_buttons_clear")
    private static final By SHUTTLES_TOP_BAR_LINK = By.cssSelector('#secondaryNavContainer a[href="http://southwest.iseatz.com/"]')
    private static final By CATEGORY_CAR = By.id("category")
    private static final String VEHICLE_TYPE_DROPDOWN_STRING = "category"
    private static final String VENDOR_HIDDEN_SELECT_STRING = "vendors"
    private static final String PICK_UP_LOCATION_STRING = "pickUpLocation_displayed"
    private static final By AUTO_COMPLETE = By.cssSelector("div.ac_results ul li")
    private String PROMO_CODES_BUTTON = "promoCodesButton0"
    private String PROMO_CODES_VENDOR = "promoCodesVendor0"
    private String PROMO_CODES_NUMBER = "promoCodesRate0"
    private String PROMO_CODES_TYPE = "promoCodesType0"

    OutboundAndReturnDatesAndPopUp calendarPopUp
    CarReservationData carReservationData
    Flow flow
    Data data

    private final String DELETE_EXISTING = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
    private static final String SEARCH_CAR_RELATIVE_PATH = "/car-rentals/search-car-rentals.html"
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    public SearchCarsPage(WebDriverProvider driverProvider) {
        super(driverProvider, SEARCH_CAR_RELATIVE_PATH);
    }

    String getRelativePath() {
        return SEARCH_CAR_RELATIVE_PATH  //TODO change as needed for page validation.

    }

    void clickToGoToCarPage() {
        waitForElement(GLOBAL_NAV_CAR_BUTTON).click()
    }


    def fillInPickupDropOffLocation(String from, String to) {
        fillInDropOffLocation(to)
        fillInDropOffLocation(from)
    }

    def fillInDropOffLocation(String dropOffStation) {
        waitForElement(DROP_OFF_LOCATION_TEXT).sendKeys(DELETE_EXISTING + dropOffStation)
    }

    def fillInPickupLocation(String pickUpStation) {
        waitForElement(PICK_UP_LOCATION_TEXT).sendKeys(DELETE_EXISTING + pickUpStation)
        waitForElement(PICK_UP_LOCATION_TEXT).sendKeys(TAB)
    }

    //TODO: is tomorrow a holiday?
    def selectReliablePickupDateAndTime(OutboundAndReturnDatesAndPopUp calendarPopUp, Date bookingDate = ItineraryDateFactory.getTomorrowDate(), Date returnDate = ItineraryDateFactory.getTomorrowDate().plus(2)) {
        calendarPopUp.enterDate(bookingDate)
        String tomorrow = bookingDate.format("MM/dd/yyyy")
        String dropOff = returnDate.format("MM/dd/yyyy")
        waitForElement(OUTBOUND_DATE).sendKeys(DELETE_EXISTING + tomorrow + TAB)
        waitForElement(RETURN_DATE).sendKeys(DELETE_EXISTING + dropOff + TAB)
    }

    def selectVendor() {
        if (carReservationData.carVendor != null) {
            waitForElement(VENDOR).click()
            sleep(10)
            waitForElement(CLEAR_VENDOR_SELECTED).click()
            sleep(5)
            waitForElement(VENDOR).click()
            sleep(10)
            waitForElement(By.id("vendors_rgbmultiselect_container_checkbox_unselected_"+carReservationData.carVendor.vendorCode().toString().toUpperCase())).click()
        }
    }

    def selectCategory() {
        if (carReservationData.carType != null) {
            selectFromDropDownByIndex(CATEGORY_CAR, carReservationData.carType.carTypeColumnNumber())
        }
    }

    def fillInPickUpTime(String pickUpTime) {
        chooseInDropDownByValue("pickUpTime", pickUpTime)
    }

    def fillInDropOffTime(String dropOffTime) {
        chooseInDropDownByValue("dropOffTime", dropOffTime)
    }

    def pickFirstAvailableMidSize() {
        List<WebElement> radioElements = waitForElements(By.xpath(car.get("Mid-size")))
        if (radioElements > 0) {
            for (car in radioElements) {
                car?.click()
                break
            }
        }
    }

    def selectFirstAvailablePickupTime(int from, int to) {
        List<WebElement> pickUpTimes = waitForElements(By.xpath("//select[@id = 'pickUpTime']//option"))
        WebElement firstAvailableTime = pickUpTimes.find {
            def num = parseTimePart(it, Calendar.HOUR);
            (num >= from) && (num <= to)
        }
        firstAvailableTime.click()
    }

    private int parseTimePart(WebElement from, int timePart) {
        def timeStr = from.getText()
        if (Calendar.HOUR == timePart || Calendar.HOUR_OF_DAY == timePart)
            return Integer.parseInt(timeStr.substring(0, 2))
        else if (Calendar.MINUTE == timePart)
            return Integer.parseInt(timeStr.substring(3, 5))
    }

    private int parseHourFromPickupTimeOption(WebElement pickUpTimeOption) {
        def timeStr = pickUpTimeOption.getText()
        return Integer.parseInt(timeStr.substring(0, 2))
    }

    def open() {
        super.open()
        verifyPage()
    }

    void submit(boolean verifyPage = true) {
        waitForElement(CAR_SEARCH_FORM_SUBMIT_BUTTON).click()
        if(verifyPage) {
            this.verifyPage()
        }
    }

    void submitCrossSell() {
        waitForElement(CAR_SEARCH_CROSS_SELL_FORM_SUBMIT_BUTTON).click()
        verifyPage()
    }

    def fillIn(String pickUp, String dropOff) {
        waitForElement(By.id(pickUp)).sendKeys(DELETE_EXISTING + dropOff + Keys.TAB)
    }

    def clickManageCarReservation() {
        waitForElement(MANAGE_CAR_RESERVATION).findElement(By.xpath("//*[contains(text(),'Manage Car Reservation')]")).click()
    }

    def fillPickUpLocationAndDate() {
        verifyJavaScriptDone()
        verifyPage()
        String pickupStation = carReservationData.pickUpStation.isEmpty() ? data.getRoute().from:carReservationData.pickUpStation
        Date pickUpDate = carReservationData.pickUpDate!=null ? carReservationData.pickUpDate:ItineraryDateFactory.getTomorrowDate()
        Date dropOffDate = carReservationData.dropOffDate!=null ? carReservationData.dropOffDate : ItineraryDateFactory.getTomorrowDate().plus(1)

        fillInPickupLocation(pickupStation)
        carReservationData.pickUpStation = pickupStation

        selectReliablePickupDateAndTime(calendarPopUp, pickUpDate, dropOffDate)
        String pickUpTime = new SimpleDateFormat("HH:mma").format(carReservationData.pickUpTime)
        fillInPickUpTime(pickUpTime)
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.prepareCarSchedules(carReservationData)
        }
    }

    def fillSearchCarForm() {
        fillInPickupLocation(carReservationData.pickUpStation)
        fillInDropOffLocation(carReservationData.dropOffStation)
        fillInPickUpDate(carReservationData.pickUpDate)
        fillInDropOffDate(carReservationData.dropOffDate)
        fillInVehicleType(carReservationData.carType)
    }

    def fillInVehicleType(CarRentalType vehicleType) {
        if (vehicleType == null) {
            vehicleType = CarRentalType.ECONOMY
        }
        selectFromDropDownByIndex(CATEGORY_CAR,vehicleType.carTypeColumnNumber())
    }

    def verifyDataPopulated() {
        isOptionInDropDownSelectedByValue(VENDOR_HIDDEN_SELECT_STRING, carReservationData.carVendor.vendorCode(), MAIN_FORM_CONTAINER).shouldBe true, "Car vendor is not being properly populated."
        isOptionInDropDownSelectedByValue(VEHICLE_TYPE_DROPDOWN_STRING, carReservationData.carType.name(), MAIN_FORM_CONTAINER).shouldBe true, "Vehicle type is not being properly populated."
    }

    private void fillInPickUpDate(Date date) {
        String pickUpDateAsString = date.format("MM/dd/yyyy")
        waitForElement(OUTBOUND_DATE).sendKeys(DELETE_EXISTING + pickUpDateAsString + TAB)
    }

    private void fillInDropOffDate(Date date) {
        String dropOffDateAsString = date.format("MM/dd/yyyy")
        waitForElement(RETURN_DATE).sendKeys(DELETE_EXISTING + dropOffDateAsString + TAB)
    }

    def fillInPromoCode(CarVendor carVendor, String type, String code) {
        if(flow.addMorePromoCodes>0){
            PROMO_CODES_TYPE = "$PROMO_CODES_TYPE".replace("${--flow.addMorePromoCodes}", "$flow.addMorePromoCodes")
            PROMO_CODES_NUMBER = "$PROMO_CODES_NUMBER".replace("${--flow.addMorePromoCodes}", "$flow.addMorePromoCodes")
        }
        fillPromoVendor(carVendor)

        final Select promoCodeTypeSelectBox = new Select(waitForElement(By.id(PROMO_CODES_TYPE)))
        promoCodeTypeSelectBox.selectByVisibleText(type)

        waitForElement(By.id(PROMO_CODES_NUMBER)).sendKeys(DELETE_EXISTING + code)
    }

    def fillPromoVendor(CarVendor carVendor){
        if(flow.addMorePromoCodes>0){
            PROMO_CODES_VENDOR = "$PROMO_CODES_VENDOR".replace(--flow.addMorePromoCodes.toString(), flow.addMorePromoCodes.toString())
        }
        final Select promoCodeVendorSelectBox = new Select(waitForElement(By.id(PROMO_CODES_VENDOR)))
        promoCodeVendorSelectBox.selectByValue(carVendor.vendorCode())
    }

    void verifyLocationSelected() {
        verifyInField(PICK_UP_LOCATION_STRING, flow.pickUpLocationCar)
    }

    def fillInPickupLocationDisplayed(String pickUpStation) {
        waitForElement(PICK_UP_LOCATION_DISPLAYED).sendKeys(DELETE_EXISTING + pickUpStation)
    }

    def fillWithAutoCompleteForSpecificCode(String code, String city){
        WebElement field = waitForElement(PICK_UP_LOCATION_TEXT)
        field.sendKeys(DELETE_EXISTING)
        field.sendKeys(code)
        List<WebElement> elements =  waitForElements(AUTO_COMPLETE)
        for(WebElement item : elements){
            if(item.getText().equalsIgnoreCase(city)){
                item.click()
                break
            }
        }
    }
}
