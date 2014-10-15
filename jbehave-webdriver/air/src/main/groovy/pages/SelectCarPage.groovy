package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import state.CarReservationData
import util.CarRentalType
import util.CarVendor
import static org.junit.Assert.assertTrue
import static org.junit.Assert.fail
import static org.junit.Assert.assertEquals

public class SelectCarPage extends BasePage {

    private static final By CAR_SEARCH_RESULTS_CONTINUE_BUTTON = By.name("carSearchResultsContinueButton")
    private static final By CAR_PRICE_COLUMN = By.className("car-results-table-price-column")
    private static final By CAR_SPECIAL_RATE = By.className("car-special-rate")
    private static final By PICKUP_LOCATION = By.id("pickUpLocationText")
    private static final By ERROR_MESSAGE = By.className("error-message-colspan")
    CarReservationData carReservationData

    public SelectCarPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/car-rentals/select-car.html')
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    public void verifyVendorExists(CarVendor vendor) {
        waitForElement(By.className("car_vendor_sprite_" + vendor.toString().toUpperCase())).getText().toUpperCase().shouldContain vendor.toString().toUpperCase(),
                "Did Not Find Car Vendor"
    }

    public CarVendor obtainVendor() {
        carReservationData.carVendor = getSelectableCarVendor(carReservationData.carVendor)
        assertTrue("Car vendor not found!",carReservationData.carVendor != null)
        return carReservationData.carVendor;
    }

    private CarVendor getSelectableCarVendor(CarVendor carVendor) {
        CarVendor resultCarVendor = null;
        List<WebElement> carVendorList = waitForElements(By.className("car-results-table-result-row"))
        for (WebElement carVendorRow: carVendorList) {
            if(isCarAvailable(carVendorRow)) {
                String carVendorName = carVendorRow.findElement(By.className("car_vendor_sprite")).text
                resultCarVendor = CarVendor.retrieveVendorForName(carVendorName)
                if (carVendor.equals(resultCarVendor)) {
                    break
                }
            }
        }
        return resultCarVendor
    }

    def openWithParameters() {
       String[] params = ["forceNewSession=true",
                          "pickUpLocation=${carReservationData.pickUpStation}",
                          "pickUpDate=${getmmddyyyyDateFormat(carReservationData.pickUpDate.toCalendar())}" ,
                          "dropOffDate=${getmmddyyyyDateFormat(carReservationData.dropOffDate.toCalendar())}" ,
                          "category=${carReservationData.carType}"]

        openWithArgs(params)
    }

    private boolean isCarAvailable(WebElement carVendorRow) {
        try {
            WebElement element = carVendorRow.findElement(By.className('car-results-table-price'))
            return true;
//            return element.isDisplayed()
        } catch (NoSuchElementError) {
            return false;
        }
    }

    void submit() {
        waitForElement(CAR_SEARCH_RESULTS_CONTINUE_BUTTON).click()
        verifyPage()
    }

    def selectRentalCar(CarVendor carVendor, CarRentalType carType) {
        WebElement radioButton = waitForElement(By.id("carInfo-${carVendor.vendorCode()}-${carType.carTypeColumnNumber()}"))
        if (radioButton.getAttribute("value").contains(carVendor.vendorCode())) {
            radioButton.click()
        } else {
            fail "Did not find ${carVendor.vendorCode()}"
        }
    }

    void verifyRentalCarFieldsPopulated() {

        assertEquals("Dallas (Love Field), TX - DAL", waitForElement(PICKUP_LOCATION).getAttribute("value"))
    }

    def showRentalCars() {
        CarVendor carVendor = carReservationData.carVendor
        CarRentalType carType = carReservationData.carType
        WebElement radioButton = waitForElement(By.id("carInfo-${carVendor.vendorCode()}-${carType.carTypeColumnNumber()}"))
        if (radioButton.getAttribute("value").contains(carVendor.vendorCode())) {
            radioButton.click()
        } else {
            fail "Did not find ${carVendor.vendorCode()}"
        }
    }

    def areSpecialRateMessagesDisplayed(CarVendor carVendor) {
        verifyVendorExists(carVendor)
        boolean messagesDisplayed = false
        List<WebElement> carPriceColumn = waitForElements(CAR_PRICE_COLUMN)
        if (carPriceColumn.size() > 0) {
            for (WebElement priceColumn : carPriceColumn) {
                String textPriceColumn = priceColumn.getAttribute("title")
                if (textPriceColumn.toUpperCase().contains(carVendor.vendorCode().toUpperCase())) {
                    messagesDisplayed = true
                    if (priceColumn.findElements(CAR_SPECIAL_RATE).size() == 0) {
                        messagesDisplayed = false
                        break
                    }
                }
            }
        } else {
            fail "Did not find available cars"
        }
        return messagesDisplayed
    }

    def changeUrlToLocal() {
        String href = getCurrentUrl().replaceAll("www.southwest","local.swacorp")
        openUrl(href)
    }

    def specialMessageDisplayed(CarVendor carVendor, String errorMessage) {
        verifyVendorExists(carVendor)
        boolean messagesDisplayed = false
        WebElement errorColumnMessage = waitForElement(ERROR_MESSAGE)
        if(errorColumnMessage != null && errorColumnMessage.getText().contains(errorMessage)){
            messagesDisplayed = true
        } else {
            fail "Did not find error message: " + errorMessage + " in table search car result"
        }
        return messagesDisplayed
    }
}
