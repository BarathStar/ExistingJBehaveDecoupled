package steps

import com.github.tanob.groobe.GrooBe
import com.swacorp.dotcom.webscenarios.air.Data

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.Alias

import pages.SearchCarsPage
import pages.SelectCarPage
import pages.elements.AccountBar
import pages.elements.GlobalNavigationHeader
import steps.conditional.ToggleGlobalNav
import util.CarVendor
import pages.elements.AutoCompleteWidget
import state.Flow
import state.CarReservationData

class CarSearchSteps {
    SearchCarsPage searchCarsPage
    SelectCarPage selectCarPage
    AutoCompleteWidget autoCompleteWidget
    Flow flow
    Data data
    AccountBar accountBar
    CarReservationData carItineraryData
    GlobalNavigationHeader globalNavigationHeader

    def CarSearchSteps() {
        GrooBe.activate()
    }

    @Given("I am a customer on the car reservation home page")
    @When("I go to the car reservation home page")
    def openCarPage() {
        searchCarsPage.open()
    }

    @Given ("I am on the car reservation page logged in as a Rapid Rewards member")
    void onCarReservationPageLoggedIn(){
        openCarPage()
        accountBar.logInToRapidRewards(data.getUser("goodUser").getRRAccountName(), data.getUser("goodUser"))
        accountBar.verifyRapidRewardsLoggedIn()
    }

    @Given("I am a customer searching for cars from the cars search page")
    def searchForCarsFromCarsSearchPage() {
        searchCarsPage.open()
        searchCarsPage.fillPickUpLocationAndDate()
        searchCarsPage.selectVendor()
    }

    @When("I navigate to the car reservation home page")
    void clickToGoToCarPage(){
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openNewHeaderMenuPlanATripPartOne()
            globalNavigationHeader.clickOnCarLinkMenu()
        } else {
            searchCarsPage.clickToGoToCarPage()
        }
    }

    @When("enter pickup Location \$to")
    def enterPickupLocation(String pickUp) {
        searchCarsPage.fillInPickupLocation(pickUp)
    }

    @When("I enter a promo code for \$carVendor")
    def viewSelectCarsPage(@Named("carVendor") String vendor) {
        CarVendor carVendor = CarVendor.retrieveVendorForCode(vendor.toUpperCase())
        if(carItineraryData.promoCode !=null) {
           searchCarsPage.fillInPromoCode(carVendor, carItineraryData.promoType ,carItineraryData.promoCode)
        }else{
            searchCarsPage.fillInPromoCode(carVendor, "Promotion Code", "1234")
        }
    }

    @When("I continue to select cars page")
    def continueSelectCarsPage() {
        searchCarsPage.submit()
        selectCarPage.verifyPage()
    }

    @When("I choose any city with \$locationInfo on the drop down list")
    def verifyDropDownListIsPresent(@Named("locationInfo") String locationInfo) {
        flow.pickUpLocationCar = autoCompleteWidget.selectFromListRandom()
    }

    @When("I enter pickup Location \$locationInfo")
    def enterPickupLocationDisplayed(@Named("locationInfo") String pickUp) {
        searchCarsPage.fillInPickupLocationDisplayed(pickUp)
    }

    @When("I fill the pick up location with auto complete response with the city: \$city for the code: \$code")
    def verifyAutoCompleteResponse(String city, String code){
        searchCarsPage.fillWithAutoCompleteForSpecificCode(code, city)
    }

    @When("I attempt to book a car")
    def attemptToBookACar() {
        searchCarsPage.fillPickUpLocationAndDate()
        searchCarsPage.selectVendor()
        searchCarsPage.submit(false)
    }

    @When("I select car vendor")
    def selectRentalCarVendor(){
        searchCarsPage.selectVendor()
    }
}
