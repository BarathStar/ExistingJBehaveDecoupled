package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.PurchasePage
import pages.SelectCarPage
import util.CarRentalType
import util.CarVendor

class CarPurchaseSteps {
    SelectCarPage selectCarPage
    PurchasePage purchasePage
    CarSelectSteps carSelectSteps

    @When("I search and select a car and fill out information on the purchase page")
    void onCarPurchasePageWithCustomerInfoFilled(){
        carPurchasePageWithCarSelected()
        purchasePage.fillInAllInformation()
    }

    @When("I click add to existing trip")
    void clickAddToExistingTrip(){
        purchasePage.clickAddToExistingTrip()
    }

    @When("I select and view the Price page for a car")
    @Given("I have a car selected and continue to the purchase page")
    def carPurchasePageWithCarSelected() {
		carSelectSteps.searchForCarsInSearchCarPage()
        CarVendor carVendor = selectCarPage.obtainVendor()
        selectCarPage.selectRentalCar(carVendor, CarRentalType.MidSize)
        selectCarPage.submit()
        purchasePage.goToCarPurchasePage()
    }

    @When("I continue the booking when the fare has changed")
    def carPurchaseWhenPriceChanged() {
        purchasePage.goToCarPurchasePage()
        purchasePage.fillInAllInformation()
        purchasePage.enterWhoIsDriving("Change","The Rate")
        purchasePage.fillPhone()
        purchasePage.goToConfirmationPage()
    }
}
