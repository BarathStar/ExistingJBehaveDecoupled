package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.BasePage
import state.Flow

class CarCancellationModal extends BasePage {

    Flow flow;

    private static final By CANCEL_RESERVATION_BUTTON_ID = By.id("cancelReservationButton")
    private static final String POPUP_TOPBAR = "popup_topbar_container"
    private static final String PRODUCT_CAR = "product_car"
    private static final By SIMPLE_MODAL_CONTAINER = By.id("simplemodal-container")

    public CarCancellationModal(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    void verifyItIsDisplayed() {
        carCancelModal.findElement(By.className("car_reservation_cancel_modal_confirmation"))
                .findElement(By.tagName("h5")).text
                .shouldBe "Please Confirm Your Cancellation", "Expected the car cancellation modal to be displayed"
    }

    void acceptCancellation() {
        waitForElement(CANCEL_RESERVATION_BUTTON_ID).click()
        flow.isCarReservationPresent = false
        checkSomethingServed()
    }

    void verifyMultiProductCarCancellationModal(){
        carCancelModal.findElement(By.className("cancellation_modal_associated_products_title")).text
                .shouldBe "Items associated with this Trip", "Other products not found in modal"
    }

    void verifyModalTitle() {
        carCancelModal.findElement(By.className(POPUP_TOPBAR))
                .findElement(By.className(PRODUCT_CAR)).text
                .shouldBe "Cancel Car Reservation", "Expected the car cancellation modal title to be displayed"
    }

    void verifyAirProductsAssociatedCount(int amount) {
        List airAssociatedProducts = carCancelModal.findElements(By.className("air_itinerary_container_with_image"))
        airAssociatedProducts.size().shouldBe amount, "The amount expected of associated air products should be "+amount
    }

    private WebElement getCarCancelModal() {
        waitForElement(SIMPLE_MODAL_CONTAINER)
    }
}
