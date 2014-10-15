package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.elements.UMChargeFlyOut
import pages.PurchasePage
import org.jbehave.core.annotations.Named
import pages.elements.ShoppingCart
import domain.Guardian
import util.DatasetManager
import util.ItineraryData
import state.Flow
import util.TripManagement
import pages.elements.InternalReferenceNumberForm

class PurchaseThenSteps {

    PurchasePage purchasePage
    ShoppingCart shoppingCart
    Flow flow
    ItineraryData itineraryData
    InternalReferenceNumberForm internalReferenceNumberForm

    @Then("I see travel funds applied")
    def verifyTravelFundsApplied() {
        purchasePage.verifyTravelFundsApplied()
    }

    @Then("I should see the credit card holder info")
    def shouldSeeCreditCardHolderInfo(){
        purchasePage.verifyCreditCardHolderInfoIsPresent()
    }

    @Then("I should see the disability in my passenger information")
    def verifyDisabilityInPassengerInformation(){
        purchasePage.verifyDisabilityInPassengerInformation()
    }

    @Then("I should not see the credit card holder info")
    def shouldNotSeeCreditCardHolderInfo(){
        purchasePage.verifyCreditCardHolderInfoIsNotPresent()
    }

    @Then("I should not see Click And Save option")
    def shouldNotSeeClickAndSave(){
        purchasePage.verifyClickAndSaveCheckBox(false);
    }

    @Then("I should see Click And Save option")
    def shouldSeeClickAndSave(){
        purchasePage.verifyClickAndSaveCheckBox()
    }

    @Then("I should not be able to purchase EB for AirTran segments")
    def verifyEBNotAvailableForAirTran() {
        purchasePage.verifyEarlyBirdNAForAirTran()
    }

    @Then("I should see Early Bird unavailable message for AirTran")
    def verifyEBUnavailableForAirTranMessage() {
        purchasePage.verifyEarlyBirdUnavailableMsgForAirTran()
    }

    @Then("I should be able to purchase EB for Southwest segments")
    def verifyEBAvailableForSouthwest() {
        purchasePage.verifyEarlyBirdAvailableForSouthwest()
    }

    @Then("I should be able to purchase EB")
    def verifyEBAvailable() {
        purchasePage.verifyEarlyBirdAvailable()
    }
    @Then("I should not be able to purchase EB")
    def verifyEBNotAvailable() {
        purchasePage.verifyEarlyBirdNotAvailable()
    }

    @Then("I see the invalid confirmation number error messages")
    def verifyInvalidConfirmationNumberOnPurchasePage() {
        purchasePage.verifyErrorTextPresent("remove this fund and re-enter");
    }

    @Then("I see the credit card denied error")
    def verifyInvalidCreditCardNumberOnPurchasePage() {
        purchasePage.shouldShowOopsMessage("The credit card was denied by your financial institution. Please contact your financial institution or try an alternate form of payment")
    }

    @Then("I should see a message about invalid characters in passenger's middle name")
    def verifyInvalidMiddleNameOnPurchasePage() {
        purchasePage.shouldShowOopsMessage("Passenger 1's middle name contains invalid characters")
    }

    @Then("I am able to select PayPal as a payment method")
    def verifyIfPayPalIsEnabled() {
        purchasePage.verifyPayPalIsEnabled()
    }

    @Then("I see the UM 5-11 passenger message")
    void verifyUm5to11PurchasePageOops() {
        purchasePage.waitForSelectedBreadcrumbs("Purchase")
        purchasePage.verifyUm5To11OopsMessage()
    }

    @Then("I see the UM pop-up modal")
    void verifyUnder18TravelerModal() {
        purchasePage.verifyYoungTravelersModalAndClickNoButton()
    }

    @Then("I view the OOPS message for the invalid PNR on the Purchase page")
    void verifyOopsOnThePurchasePage() {
        purchasePage.verifyInvalidPnrOopsMessage()
    }

    @Then("I see the UM under 5 passenger message")
    void verifyUmUnderPurchasePageOops() {
        purchasePage.verifyUmUnder5OopsMessage()
    }

    @Then("I view an Oops message stating that infants are not permitted to fly accompanied by minors")
    void verifyUnaccompaniedChildrenOopsMessage() {
        purchasePage.verifyUnaccompaniedChildrenOopsMessage()
    }

    @Then("I should see information about UM in travel funds section")
    def verifyUmTravelFundsMsgIsVisible() {
        purchasePage.verifyUmTravelFundsMsgIsVisible()
    }

    @Then("I should be able to fill the credit card information")
    def verifyCreditCardInformationIsVisible() {
        purchasePage.verifyCreditCardInformationIsVisible()
    }

    @Then("I see the stored form of payment selected")
    def verifyStoredFormOfPaymentIsSelected() {
        purchasePage.verifyStoredFormOfPaymentIsSelected()
    }

    @Then("I see the oops messages <msgs>")
    def pageMessage(@Named("msgs") List<String> msgs) {
        purchasePage.pageMessage(msgs)
    }

    @Then("I should see a message about not needing to buy an extra seat")
    void verifyNoNeedForExtraSeatForPassengerOfSizeMessagePresent() {
        purchasePage.verifyNoNeedForExtraSeatForPassengerOfSizeMessagePresent()
    }

    @Then("I see the Non-Editable Purchase page")
    void verifyNonEditablePurchasePage() {
        purchasePage.isEditPassengerInfoButtonDisplayed().shouldBe true, "The Edit Passenger Information button should be present"
    }

    @Then("I see the Editable Purchase page")
    void verifyEditablePurchasePage() {
        purchasePage.isEditPassengerInfoButtonDisplayed().shouldBe false, "The Edit Passenger Information button should not be present"
    }

    @Then("I view an Oops message stating that infants are not permitted to fly unaccompanied")
    def viewOopsMessageForUmTravelAlone() {
        purchasePage.verifyUmTranvelAloneOopsMessage()
    }

    @Then("I view an Oops message stating that UMs are not permitted to travel on flights with plane change")
    void viewOopsMessageForUmWithChangePlane() {
        purchasePage.verifyUmWithChangePlanesOopsMessage()
    }

    @Then("I see a message indicating that UMs are not eligible for EB checkin")
    void verifyUMNotElegibleForEBCheckIn() {
        purchasePage.verifyUMNotElegibleForEBCheckIn()
    }

    @Then("I see the Unaccompanied Minor Fee under the Shopping Cart")
    void verifyUMFeeUnderShoppingCart() {
        shoppingCart.verifyUMFeeUnderShoppingCart()
    }

    @Then("I see the YT modal asking if there will be a companion")
    void verifyYoungTravelerModal() {
        purchasePage.verifyYoungTravelersModalMessage()
    }

    @Then("I see an informative message indicating that travel funds can only be applied to the airfare")
    void verifyErrorAboutApplyTravelFounds() {
        purchasePage.verifyErrorAboutApplyTravelFounds()
    }

    @Then("I see the Unaccompanied Minor Fee on the Applied Travel Funds Summary")
    void verifyUMChargeOnApplyTravelFounds() {
        purchasePage.verifyUMChargeOnApplyTravelFounds()
    }

    @Then("I should not be able to select PayPal as a payment method")
    void verifyIfPayPalIsDisabled() {
        purchasePage.verifyPayPalIsDisabled()
    }

    @Then("I see an informative message indicating that itineraries with add-ons may not use PayPal")
    void verifyPayPalMessageAboutAddOns() {
        purchasePage.verifyPayPalMessageAboutAddOns()
    }

    @Then("I should not see the add-ons section with EarlyBird Check-In at Shopping Cart")
    void verifyEBAddOnOnShoppingCart() {
        shoppingCart.isEarlyBirdAddOnPresent().shouldBe false, "The Early-Bird Add-On should be not present"
    }

    @Then("I should not see the name this trip option checked")
    void nameThisTripUnselected() {
        purchasePage.verifyNameThisTripIsNotChecked()
    }

    @Then("I should not see the trip name field")
    void tripNameFieldNotPresent() {
        purchasePage.verifyTripNameFieldIsNotPresent()
    }

    @Then("I see the trip section where the option to name the trip is not offered by default on the Purchase page")
    def nameThisTripOptionNoOfferedOnTripSection(){
        purchasePage.verifyNameThisTripOptionIsNotOffered()
    }

    @Then("I see the passenger information correctly filled in on the Purchase page")
    void verifyRRPassengerInfo() {
        purchasePage.verifyRRPassengerInfo()
    }

    @Then("I see the Unaccompanied Minor Charge fly-out which informs the UM charges")
    UMChargeFlyOut verifyUMChargeFlyOut() {
        UMChargeFlyOut flyOut = shoppingCart.getUMChargeFlyOut()
        String message = "Southwest Airlines will charge \$50 one-way (\$100 roundtrip) per child for Unaccompanied Minors to travel"
        flyOut.content.shouldContain message, "The message '${message}' should be present"
        return flyOut
    }

    @Then("I see a message indicating that the passenger will be traveling with an adult")
    void verifyAdultCompanionNote() {
        purchasePage.verifyAdultCompanionNote()
    }

    @Then("I verify that I am on the Purchase page")
    def verifyPurchasePage() {
        purchasePage.getTitle().shouldContain "Payment Information", "Did not arrive at the Purchase page after Select Flights and Pricing."
    }

    @Then("the \$savedTripOrNewType field is selected and visible")
    void verifyRadioButtonSelectedAndFieldIsVisible(String savedTripOrNewType) {
        purchasePage.verifyRadioButtonClickOnCarPurchasePage(savedTripOrNewType)
        purchasePage.verifyFieldIsVisibleOnCarPurchasePage(savedTripOrNewType)
    }

    @Then("I see the YT guardian information edited and completed")
    void verifyYTGuardianEditedInfoPopUp() {
        purchasePage.verifyYTGuardianInfoPopUp(flow.guardianWithContactMethod)
    }

    @Then("I see the YT guardian information popup")
    void verifyYTGuardianInfoPopUp() {
        purchasePage.verifyYTGuardianInfoPopUp(flow.guardianWithContactMethod)
    }

    @Then("I see the UM Guardian information previously completed")
    void verifyUMGuardianInfoPopUp() {
        Guardian dropOffGuardian = DatasetManager.loadGuardian('DropOffGuardian')
        Guardian pickUpGuardian = DatasetManager.loadGuardian('PickUpGuardian')
        Guardian alternateGuardian = DatasetManager.loadGuardian('Guardian')
        purchasePage.verifyUMGuardianInfoPopUp(dropOffGuardian, pickUpGuardian, alternateGuardian)
    }

    @Then("I should not be able to add the trip to an existing trip on the Purchase page")
    void existingTripsDropDownNotPresent() {
        purchasePage.verifyExistingTripsNotPresent()
    }

    @Then("I should see the existing trips drop down on the Purchase page")
    void existingTripsDropDownIsPresent() {
        purchasePage.verifyExistingTripsIsPresent()
    }

    @Then("the default trip name is correctly formatted on the Purchase page")
    void verifyDefaultTripNameFormat() {
        purchasePage.verifyTripNameIsFormattedByDefault()
    }

    @Then("I see the new trip name radio button selected on the Purchase page")
    void newTripNameSelected() {
        purchasePage.verifyNewTripNameIsChecked()
    }


    @Then("I see the trip section where the option to name the trip is offered by default on the Purchase page")
    void nameThisTripSelected() {
        purchasePage.verifyNameThisTripIsChecked()
    }

    @Then("I see that the new trip name has the default format")
    void verifyTripNameIsFormattedByDefault(){
        String defaultTripName = TripManagement.getDefaultTripName(itineraryData.departureDate , itineraryData.arrivalStation)
        purchasePage.verifyDefaultTripNameText(defaultTripName)
    }

    @Then("I should see the existing trips drop down disabled on the Purchase page")
    void verifyExistingTripsIsPresentAndDisabled(){
        purchasePage.verifyExistingTripsIsPresentAndDisabled()
    }

    @Then("I see the credit card fields empty")
    void verifyCreditCardDefaultValues() {
        purchasePage.verifyCreditCardDefaultValues()
    }

    @Then("I see the primary IRN changed")
    def verifyPrimaryIRNHasChanged() {
        internalReferenceNumberForm.verifyPrimaryIRNHasChanged()
    }

    @Then("I should see only company-defined IRNs availables to select")
    def verifyOnlyCompanyIRNsAreSelecteable() {
        internalReferenceNumberForm.verifyCompanyIRNOnlyDisplayed()
    }

    @Then("I should see the rapid rewards awards displayed on the purchase page")
    def verifyPurchasePageWithRapidRewardsAwardsDisplayed() {
        purchasePage.verifyRapidRewardsAwardsSection()
    }

    @Then("I should see the rapid rewards awards displayed on the purchase page with certificates \$certificates")
    def verifyPurchasePageWithRapidRewardsAwardsWithExpectedCertificatesDisplayed(String certificates) {
        purchasePage.verifyRapidRewardsAwardsSectionWithExpectedCertificates(certificates)
    }

    @Then("I check New Credit Card still selected")
    def checkNewCreditCardIsSelected() {
        purchasePage.creditCardInformationIsDisplayed().shouldBe true , "New Credit Card no selected"
    }

    @Then("The \$field field should not be empty")
    def fieldStatus(String field) {
        switch (field) {
            case ("Email Confirmation Address"):
                purchasePage.isEmailConfirmationAddressEmpty().shouldNotBe true, "The email confirmation address has been erased";
                break;
            default:
                break;
        }
    }

    @Then("I see billing information displayed")
    def verifyBillingInformationDisplayed() {
        purchasePage.verifyBillingInformationDisplayed()
    }

    @Then("I see the Review Purchase Summary of my purchase")
    def verifyReviewPurchaseSummaryDisplayed() {
        purchasePage.verifyReviewPurchaseSummaryDisplayed()
    }

    @Then("I am not able to add EarlyBird to my flight")
    def notSeeEarlyBirdOnOptions(){
        purchasePage.checkNotEarlyBirdOptions()
    }

    @Then("I am not able to apply my travel funds on the Purchase Page")
    def applyFundsButtonShouldNotBePresent() {
        purchasePage.checkApplyFundsButtonPresent()
    }

    @Then("I see an invalid senior date of birth message")
    def verifyInvalidDateOfBirth(){
        purchasePage.verifySeniorInvalidDateOfBirthOops()
    }

    @Then("I should not see the Stored Payment Security Code field")
    def verifyStoredPaymentSecurityCodeFieldIsNotDisplayed(){
        purchasePage.verifyStoredPaymentSecurityCodeFieldIsNotDisplayed()
    }
}