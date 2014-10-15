package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.BasePage

class AirCancellationModal extends BasePage {

    private static final By AIR_CANCEL_MODAL = By.id("trip_products_associated_with_air_being_cancelled_modal")
    private static final By CONTINUE_CANCELATION = By.id("continueCancelation")

    AirCancellationModal(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    boolean isAirCancelModalPresent(){
        return isElementPresent(By.id("trip_products_associated_with_air_being_cancelled_modal"))
    }

    void verifyItIsDisplayed() {
        waitForElement(AIR_CANCEL_MODAL).findElement(By.className("popup_topbar_container"))
                .findElement(By.tagName("h5")).text
                .shouldBe "Cancel Air Reservation", "Expected the air cancellation modal to be displayed"
    }

    void verifyItIsNotDisplayed() {
        waitForElement(AIR_CANCEL_MODAL).findElement(By.className("popup_topbar_container")).shouldBe null
    }

    void clickContinueButton() {
        waitForElement(CONTINUE_CANCELATION).click()
    }

    void verifyMultiProductAirCancellationModal(){
        waitForElement(AIR_CANCEL_MODAL).findElement(By.className("cancellation_modal_associated_products_title")).text
                .shouldBe "Items associated with this Trip", "Other products not found in modal"
    }

    String getRelativePath(){
        return ""  //TODO change as needed for page validation.
    }
}
