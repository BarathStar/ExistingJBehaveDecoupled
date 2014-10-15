package steps.thenSteps

import util.CarVendor
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.Then
import pages.SelectCarPage

class SelectCarThenSteps {

    SelectCarPage selectCarPage

    @Then("I see the special rate message above the price for all the \$carVendor available cars")
    def verifySpecialRateMessagesAreShown(@Named("carVendor") String vendor) {
        CarVendor carVendor = CarVendor.retrieveVendorForCode(vendor.toUpperCase())
        selectCarPage.areSpecialRateMessagesDisplayed(carVendor).shouldBe true, "There are available cars without special rate message."
    }

    @Then("I should see cars available to select")
    def seeCarsAvailable() {
        selectCarPage.verifyRentalCarFieldsPopulated()
    }

    @Then("I shouldn't see any Oops message in Select Car Page")
    def noOopsMessageOnCarSelectPage() {
        selectCarPage.verifyPage()
        selectCarPage.checkNoOops()
    }

    @Then("I see the special rate message: \$specialMessage above the price for all the \$carVendor")
    def verifyCouponCodeInvalidMessageIsShown(@Named("specialMessage") String specialMessage, @Named("carVendor") String vendor) {
        CarVendor carVendor = CarVendor.retrieveVendorForCode(vendor.toUpperCase())
        selectCarPage.specialMessageDisplayed(carVendor, specialMessage).shouldBe true, "The message: " + specialMessage + " isn't shown"
    }
}
