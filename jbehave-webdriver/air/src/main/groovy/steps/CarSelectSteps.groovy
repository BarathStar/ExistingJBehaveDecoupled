package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Alias
import pages.SearchCarsPage
import pages.SelectCarPage
import util.CarRentalType
import util.CarVendor
import pages.PurchasePage
import state.CarReservationData
import org.jbehave.core.annotations.Named

class CarSelectSteps {

    SearchCarsPage searchCarsPage
    SelectCarPage selectCarPage
    PurchasePage purchasePage
    CarReservationData carReservationData

    @When("I add a car to the shopping cart")
    @Alias("I search and select a car and view the Price page")
    @Given("I have reached the Car Price Page")
    def addCarToCart() {
        searchCarsPage.fillPickUpLocationAndDate()
        searchCarsPage.selectVendor()
        searchCarsPage.selectCategory()
        searchCarsPage.submit()
        selectCarPage.verifyPage()
        CarVendor carVendor = selectCarPage.obtainVendor()
        if (carReservationData.carType != null){
            selectCarPage.selectRentalCar(carVendor, carReservationData.carType)
        } else {
            selectCarPage.selectRentalCar(carVendor, CarRentalType.MidSize)
        }
        selectCarPage.submit()
    }

    @When("I add a car to the shopping cart using cross sell widget")
    def addCarToCartCrossSell() {
        searchCarsPage.fillPickUpLocationAndDate()
        searchCarsPage.submitCrossSell()
        selectCarPage.verifyPage()
        CarVendor carVendor = selectCarPage.obtainVendor()
        selectCarPage.selectRentalCar(carReservationData.carVendor, carReservationData.carType)
        selectCarPage.submit()
        purchasePage.goToCarPurchasePage()
    }

    @When("I search and select a car and view the Purchase page")
    def addCarToShoppingCartAndPurchase() {
        searchCarsPage.fillSearchCarForm()
        searchCarsPage.submit()
        selectCarPage.verifyPage()
        selectCarPage.selectRentalCar(carReservationData.carVendor, carReservationData.carType)
        selectCarPage.submit()
        purchasePage.goToCarPurchasePage()
    }

    @When("I look up a car coming from Altea")
    def addCarFromAltea(){
        selectCarPage.openWithParameters()
    }

    @When("I go to url car cross sell advertise")
    def goToLocalUrlCarCrossSellAd(){
        selectCarPage.changeUrlToLocal()
    }

    @When("I search for cars in the search car page")
    def searchForCarsInSearchCarPage(){
        searchCarsPage.fillPickUpLocationAndDate()
        searchCarsPage.fillInVehicleType(carReservationData.carType)
        searchCarsPage.submit()
        selectCarPage.verifyPage()
    }

    @When("I continue to Price Car Page")
    def continueToCarPricePage(){
        selectCarPage.submit()
    }

    @When("I select \$carType car category for \$carVendor car Vendor")
    def selectCarFromMySearch(@Named("carVendor") String vendor, @Named("carType") String carTypeString){
        CarVendor carVendor = CarVendor.retrieveVendorForCode(vendor.toUpperCase())
        CarRentalType carType = CarRentalType.retrieveCarRentalTypeForCode(carTypeString)
        selectCarPage.selectRentalCar(carVendor, carType)
    }
}
