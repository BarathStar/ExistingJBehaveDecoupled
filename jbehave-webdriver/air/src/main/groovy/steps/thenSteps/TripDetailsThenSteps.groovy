package steps.thenSteps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Aliases
import org.jbehave.core.annotations.Then
import pages.CancelAirPage
import pages.ChangeAirReservationPage
import pages.MyAccountPage
import pages.TripDetailsPage
import state.Flow
import state.ScenarioState
import util.AlteaUrlValidationHelper

class TripDetailsThenSteps {

    TripDetailsPage tripDetailsPage
    MyAccountPage myAccountPage
    CancelAirPage cancelAirPage
    ChangeAirReservationPage changeAirPage
    Flow flow
    ScenarioState scenarioState

    @Then("I should not be able to rename the Air reservation on the Trip Details page")
    def verifyRenameButtonIsNotVisible() {
        tripDetailsPage.verifyRenameButtonIsNotVisible()
    }

    @Then("I see on the Trip Details page that the Air reservation has no trip name provided by the user")
    @Alias("I see on the Trip Details page that the Air reservation has default trip name")
    def verifyDefaultTripNameForAir() {
        tripDetailsPage.verifyDefaultTripNameForAir()
    }

    @Then("I see on the Trip Details page that the Car reservation has no trip name provided by the user")
    def verifyDefaultTripNameForCar() {
        tripDetailsPage.verifyDefaultTripNameIsFormattedCorrectly()
    }

    @Then("I see the details of the Air reservation on the Trip Details page")
    def verifyAirDetails() {
        tripDetailsPage.verifyAirDetails()
    }

    @Then("I see the details of the Air reservation on the Trip Details page when trip management toggled off")
    def verifyAirDetailsWhenTripManagementToggleOff() {
        tripDetailsPage.verifyPageTitle("Upcoming Trip Details")
        tripDetailsPage.verifyAirDetailsWhenTripManagementToggleOff()
    }

    @Then("I see the details of the Car reservation on the Trip Details page")
    def verifyCarDetails() {
        tripDetailsPage.verifyCarDetails()
    }

    @Then("I see that My Trip is shown at Trip Details page")
    @Alias("I view the trip name shows the new name")
    def verifyTripNameOnTripDetailsPage() {
        tripDetailsPage.verifyTripNameOnTripDetailsPage()
    }

    @Then("I see the details of the products from My Trip are listed by date first and then by type on the Trip Details Page")
    def verifyTripProductsListedByDateAndType() {
        tripDetailsPage.verifyTripProductsListedByDateAndType()
    }

    @Then("I see the breadcrumb updated with the new trip name")
    def verifyTripNameOnBreadcrumb() {
        String[] breadcrumbsArray = ["My Account", "My Travel", "Upcoming Trips", flow.tripName];
        tripDetailsPage.verifyBreadcrumb(breadcrumbsArray)
    }

    @Then("I see the Associated Products modal")
    def verifyAssociatedProductsModal() {
        tripDetailsPage.verifyAssociatedProductsModal()
    }

    @Then("I see the information of my retrieved car on the Car Associated Products Modal")
    def verifyCarReservationOnProductModal() {
        tripDetailsPage.verifyCarReservationOnAssociatedProductModal()
    }

    @Then("I see the Air product as an associated item to my trip on the Car Associated Products Modal")
    def verifyAssociatedAirOnAssociatedProductOnModal() {
        tripDetailsPage.verifyAssociatedAirOnAssociatedProductOnModal()
    }

    @Then("I am redirected to the Altea Cancel Page")
    def verifyAlteaCancelPage(){
          cancelAirPage.waitForElement(AlteaUrlValidationHelper.PROXY_RESPONSE)
          AlteaUrlValidationHelper.verifyCancelReservationLink(cancelAirPage.getCurrentUrl(), scenarioState.getLastAirReservation(), flow.isRapidRewards)
    }
    @Then("I am redirected to the Altea Change Reservation page")
    def verifyAlteaChangePage(){
          changeAirPage.waitForElement(AlteaUrlValidationHelper.PROXY_RESPONSE)
          AlteaUrlValidationHelper.verifyChangeReservationLink(changeAirPage.getCurrentUrl(), scenarioState.getLastAirReservation(), flow.isRapidRewards)
    }

    @Then("I am redirected to the Altea View Reservation page")
    def verifyAlteaViewPage(){
          changeAirPage.waitForElement(AlteaUrlValidationHelper.PROXY_RESPONSE)
          AlteaUrlValidationHelper.verifyViewReservationLink(changeAirPage.getCurrentUrl(), scenarioState.getLastAirReservation(), flow.isRapidRewards)
    }

    @Then("I am redirected to the Altea Add Rapid Rewards page")
    def verifyAlteaAddRRPage(){
          changeAirPage.waitForElement(AlteaUrlValidationHelper.PROXY_RESPONSE)
          AlteaUrlValidationHelper.verifyAddRRLink(changeAirPage.getCurrentUrl(), scenarioState.getLastAirReservation(), flow.isRapidRewards)
    }

    @Then("I am redirected to the Altea Upgrade Reservation page")
    def verifyAlteaUpgradePage(){
          changeAirPage.waitForElement(AlteaUrlValidationHelper.PROXY_RESPONSE)
          AlteaUrlValidationHelper.verifyUpgradeReservationLink(changeAirPage.getCurrentUrl(), scenarioState.getLastAirReservation(), flow.isRapidRewards)
    }

    @Then("I check the Rapid Rewards Number in the page")
    def verifyRapidRewardsNumber(){
        tripDetailsPage.verifyElementRapidRewardsNumber()
    }

}
