package steps.thenSteps

import pages.TravelFundsPage
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.Then

class TravelFundsThenSteps {
    TravelFundsPage travelFundsPage

    @Then("I see the AirTran message")
    def verifyAirTranMessageOnTravelFundsPage() {
        travelFundsPage.verifyAirTranMessage()
    }

    @Then("I see available travel funds")
    void viewAvailableTravelFunds() {
        travelFundsPage.verifyRemainingTravelFundBalance()
    }

    @Then("I see the \$travel_funds_error_type error message")
    def verifyTravelFundsErrorMessages(@Named("travel_funds_error_type") String travel_funds_error_type) {
        travelFundsPage.verifyErrorMessageOnTravelFundsPage(travel_funds_error_type)
    }

    @Then("I view the OOPS message for the invalid PNR using travel funds")
    void verifyInvalidTravelFundsPnrRetrieval() {
        travelFundsPage.verifyInvalidTravelFundsPnrRetrieval()
    }

    @Then("I view the OOPS message for the invalid PNR when applying travel funds")
    void viewOopsMessageForInvalidPnrApplyingTravelFunds() {
        travelFundsPage.verifyInvalidTravelFundsPnrRetrieval()
    }

    @Then("I see the Oops messages which inform me that the Passenger's names were not entered and the confirmation number is required")
    def verifyMessageAboutConfirmationNumberAndPassengerNames() {
        travelFundsPage.verifyMessageAboutConfirmationNumberAndPassengerNames()
    }

    @Then("I see the Oops message which inform me that the wrong data was entered and data in fields is missing")
    def verifyMessageAboutWrongAndMissingData(){
        travelFundsPage.verifyMessageAboutWrongAndMissingData()
    }

    @Then("I see the Oops message which inform me that data in the fields is missing")
    def verifyMessageAboutDataIsMissing(){
        travelFundsPage.verifyMessageAboutDataIsMissing()
    }

    @Then("I see the Oops message which inform me the wrong data entered and the fields missing")
    def verifyMessageAboutWrongDataAndMissedFields() {
        travelFundsPage.verifyMessageAboutWrongDataAndMissedFields()
    }

    @Then("I see the Oops message which inform me the fields missing")
    def verifyMessageAboutFieldMissing() {
        travelFundsPage.verifyMessageAboutFieldMissing()
    }

    @Then("I see the Oops message which informs me that the funds were not retrieved")
    def verifyMessageAbout() {
        travelFundsPage.verifyMessageAbout()
    }

    @Then("I see the maximum allowed message for the Travel Funds, LUV Vouchers and GiftCards")
    def verifyMaximumAllowedTexts() {
        travelFundsPage.verifyCheckLuvVoucherToggleLinkIsNotPresent()
        travelFundsPage.verifyCheckGiftcardToggleLinkIsNotPresent()
        travelFundsPage.verifyAddAnotherTravelFundLinkIsNotPresent()
        travelFundsPage.verifyAddAnotherLuvVoucherLinkIsNotPresent()
        travelFundsPage.verifyAddAnotherGiftcardLinkIsNotPresent()
        travelFundsPage.verifyMaximumAllowedControls()
    }

    @Then("I see the correct balances and totals for the Travel Funds, LUV Vouchers and GiftCards")
    def verifyCorrectBalancesAndTotals() {
        travelFundsPage.verifyCorrectBalanceAndTotalDisplayed()
    }

    @Then("I see the correct balances and totals for the Travel Funds")
    void verifyCorrectBalancesAndTotalsForTravelFunds() {
        travelFundsPage.verifyCorrectBalanceAndTotalTravelFunds()
    }

    @Then("I view the OOPS message for the invalid PNR checking travel funds")
    void verifyInvalidTravelFundsPnr() {
        travelFundsPage.verifyInvalidTravelFundsPnr()
    }
}