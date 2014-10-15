package steps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.When

import pages.ConfirmationPage
import pages.PurchasePage
import pages.SearchCarsPage
import pages.SelectCarPage
import state.CarReservationData;
import util.CarRentalType
import util.CarVendor

class CarConfirmationSteps {

    SearchCarsPage searchCarsPage
    SelectCarPage selectCarPage
    PurchasePage purchasePage
    ConfirmationPage confirmationPage
    CarReservationData carItineraryData

    @When("I click cancel on the car confirmation page")
    @Alias("I click on the cancel link for the car product on the confirmation page")
    def clickCancelOnCarConfirmation() {
        confirmationPage.clickCancelReservation()
    }

    @When("I reserve a car")
    def reserveACar() {
        searchCarsPage.fillPickUpLocationAndDate()
        searchCarsPage.submit()
        selectCarPage.verifyPage()
        CarVendor carVendor = selectCarPage.obtainVendor()
        selectCarPage.selectRentalCar(carVendor, CarRentalType.MidSize)
        selectCarPage.submit()
        purchasePage.goToCarPurchasePage()
        purchasePage.fillInAllInformation()
        goToConfirmationPage()
        confirmationPage.storeCurrentPNR()
    }

    @When("I make a car reservation")
	def makeACarReservation() {
		searchCarsPage.fillPickUpLocationAndDate()
        searchCarsPage.fillInVehicleType(carItineraryData.carType)
		searchCarsPage.submit()
		selectCarPage.verifyPage()
		CarVendor carVendor = selectCarPage.obtainVendor()
		selectCarPage.selectRentalCar(carVendor, CarRentalType.MidSize)
		selectCarPage.submit()
		purchasePage.goToCarPurchasePage()
	}

    @When("I continue to the car confirmation page")
    def goToConfirmationPage() {
        purchasePage.goToConfirmationPage()
        confirmationPage.verifyPage()
    }

    @When("I enter the driver and payment information AND Purchase")
    void onCarPurchasePage(){
        purchasePage.fillInAllInformation()
        purchasePage.fillPhone()
        purchasePage.goToConfirmationPage()
    }
}
