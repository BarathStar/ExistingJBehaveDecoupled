package steps.thenSteps

import util.DatasetManager
import domain.Guardian
import org.jbehave.core.annotations.Then
import pages.UnaccompaniedMinorGuardianPage
import state.Flow
import pages.PurchasePage
import pages.YoungTravelerGuardianPage

class AirGuardianPurchaseThenSteps {

    UnaccompaniedMinorGuardianPage unaccompaniedMinorGuardianPage
    PurchasePage purchasePage
    YoungTravelerGuardianPage youngTravelerGuardianPage

    Flow flow

    @Then("I am on the UM Guardian page")
    void verifyIAmOnTheUMGuardianPage() {
        unaccompaniedMinorGuardianPage.verifyCurrentPageLocation()
    }

    @Then("I see the Unaccompanied Minor Fee on the UM Parent/Guardian Form")
    void verifyTheTotalUMTextAndCharge() {
        unaccompaniedMinorGuardianPage.verifyTheTotalUMTextAndCharge()
    }

    @Then("I see the same itinerary selected during the booking on the UM purchase page")
    void verifyItineraryOnUMPurchasePage() {
        unaccompaniedMinorGuardianPage.verifyItineraryOnGuardianPage()
    }

    @Then("I should not see the Contact information for the returning flight")
    void verifyReturningGuardianIsNotPresent() {
        unaccompaniedMinorGuardianPage.verifyPrepopulateCheckboxIsNotDisplayed()
    }

    @Then("I see the UM Guardian information form with the information previously entered")
    void verifyUMGuardianInfoAndFormPresent() {
        Guardian dropOffGuardian = DatasetManager.loadGuardian('DropOffGuardian')
        Guardian pickUpGuardian = DatasetManager.loadGuardian('PickUpGuardian')
        Guardian alternateGuardian = DatasetManager.loadGuardian('Guardian')
        unaccompaniedMinorGuardianPage.verifyGuardianFormDisplayed()
        unaccompaniedMinorGuardianPage.verifyDepartingDropOffGuardian(dropOffGuardian)
        unaccompaniedMinorGuardianPage.verifyDepartingPickUpGuardian(pickUpGuardian)
        unaccompaniedMinorGuardianPage.verifyDepartingAlternateGuardian(alternateGuardian)
    }

    @Then("I see the Parent/Guardian Contact Information previously entered on the YT Parent/Guardian Form")
    void verifyYTGuardianInfoAndFormPresent() {
        youngTravelerGuardianPage.verifyGuardianFormDisplayed()
        youngTravelerGuardianPage.verifyInfoPreviouslyEnteredCorrectOnGuardianForm(flow.guardianWithContactMethod)
    }


    @Then("I verify YT policies travelling alone message is displayed")
    void verifyYTPoliciesMessageIsShown(){
        youngTravelerGuardianPage.verifyPage()
        youngTravelerGuardianPage.verifyYtPoliciesMessageIsShown()
    }

    @Then("I see the same itinerary selected during the booking on the Young Traveler purchase page")
    void verifyItineraryOnYTPurchasePage() {
        youngTravelerGuardianPage.verifyItineraryOnGuardianPage()
    }

    @Then("I see the departing contact information on the returning flight section on the UM Guardian form")
    void verifySameInfoForDropOffAndPickUpOnUMGuardian() {
        Guardian dropOffGuardian = DatasetManager.loadGuardian('DropOffGuardian')
        Guardian pickUpGuardian = DatasetManager.loadGuardian('PickUpGuardian')
        unaccompaniedMinorGuardianPage.verifyReturningDropOffGuardian(pickUpGuardian)
        unaccompaniedMinorGuardianPage.verifyReturningPickUpGuardian(dropOffGuardian)
    }

    @Then("I see an Oops messages indicating that the Alternate contact for the returning flight must be completed")
    void verifyOopsAboutMandatoryFieldsOnUmGuardian() {
        unaccompaniedMinorGuardianPage.verifyOopsMandatoryFieldsReturningFlight()
    }

    @Then("I see the Total UM Charge, the itinerary and the UM Guardian information previously entered")
    void verifyTotalUMChargeItineraryAndGuardianInfo() {
        verifyTheTotalUMTextAndCharge()
        verifyItineraryOnUMPurchasePage()
        verifyUMGuardianInfoAndFormPresent()
    }

    @Then("I see the options phone and text as notification methods on the UM Parent/Guardian Form")
    void verifyNotifyContactOptions() {
        unaccompaniedMinorGuardianPage.verifyNotifyContactOptions()
    }

    @Then("I see the Oops messages indicating that all the mandatory fields were not entered")
    void verifyOopsMandatoryFieldsOnUmGuardian() {
        unaccompaniedMinorGuardianPage.verifyOopsMandatoryFields()
    }

    @Then("I see the Oops messages indicating that all the mandatory fields were not entered on the YT guardian form")
    void verifyOopsMandatoryFieldsOnYTGuardian() {
        youngTravelerGuardianPage.verifyOopsMandatoryFields()
    }

    @Then("I see an oops message which indicates that the same person cannot be both primary and alternate parent/guardian for the departing flight")
    void verifyOopsAboutSameInfoForAlternateAndPickUpDeparterFlight() {
        unaccompaniedMinorGuardianPage.verifyOopsAboutSameInfoForAlternateAndPickUpDepartingFlight()
    }
}
