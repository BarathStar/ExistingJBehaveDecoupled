package steps

import com.github.tanob.groobe.GrooBe
import org.jbehave.core.annotations.When
import pages.*
import pages.elements.*
import org.jbehave.core.annotations.Then

class ChasePurchaseSteps {

    NavigationSteps navigationSteps
    PurchasePage purchasePage
    PricePage pricePage
    Chase chase
    PassengerInfo passengerInfo
    ContactInfo contactInfo

    def ChasePurchaseSteps() {
        GrooBe.activate()
    }

    @When("I make a chase purchase from purchase page")
    def makeChasePurchaseFromPurchasePage() {
        chase.simulateApprovedStatusChaseApplication()
        passengerInfo.fillForm()
        contactInfo.fillForm()
        purchasePage.fillEmail()
        navigationSteps.clickContinueToComplete()
    }

    @When("I make a chase purchase from purchase page using five digit zip")
    def makeChasePurchaseFromPurchasePageUsingFiveDigitZip() {
        chase.simulateApprovedStatusChaseApplication()
    }

    @When("I make a chase purchase from price page")
    def makeChasePurchaseFromPricePage() {
        chase.simulateApprovedStatusChaseApplication()
        navigationSteps.continueToPurchasePage()
        passengerInfo.fillForm()
        contactInfo.fillForm()
        purchasePage.fillEmail()
        navigationSteps.clickContinueToComplete()
    }

    @When("I make a chase purchase while CyberArk is unavailable")
    def makeChasePurchaseFromPricePageWhileCyberArkUnavailble() {
        chase.simulateChaseApplicationWithCyberArkUnavailable()
    }

    @When("I make a chase purchase from price page using five digit zip")
    def makeChasePurchaseFromPricePageUsingFiveDigitZip() {
        chase.simulateApprovedStatusChaseApplication()
        navigationSteps.continueToPurchasePage()
    }


    @When("I make a chase purchase from price page using nine digit zip")
    def makeChasePurchaseFromPricePageUsingNineDigitZip() {
        chase.simulateNineDigitZipChaseApplication()
        navigationSteps.continueToPurchasePage()
    }

    @When("I make a chase purchase from purchase page using nine digit zip")
    def makeChasePurchaseFromPurchasePageUsingNineDigitZip() {
        chase.simulateNineDigitZipChaseApplication()
    }

    @When("I attempt a chase purchase from price page to receive a pending approval status")
    def makeChasePurchaseFromPricePageWithPendingStatus() {
        chase.simulatePendingStatusChaseApplication()
        navigationSteps.continueToPurchasePage()
    }

    @Then("I should be forced to use a different FOP on purchase page")
    def checkForCreditCardOptionOnPurchasePage() {
        purchasePage.verifyCreditCardInformationIsVisible()
    }

    @When("I attempt a chase purchase from purchase page to receive a pending approval status")
    def makeChasePurchaseFromPurchasePageWithPendingStatus() {
        chase.simulatePendingStatusChaseApplication()
    }

    @When("I attempt a chase purchase to receive a partial approval")
    def makeChasePurchaseFromPurchasePageWithPartialApproval() {
        chase.simulatePartialApprovalChaseApplication()
    }

    @Then("I should see Chase intermediary options page")
    def checkForChaseIntermediaryOptionsPage() {
        chase.verifyIntermediaryOptionsPage()
    }

    @When("I cancel chase application")
    def cancelChaseApplication() {
        chase.cancelApplicationAndRedirect()
    }

    @Then("I should redirect back to price page")
    def checkForPricePage() {
        chase.verifyPricePage()
    }

    @Then("I should see five digit zip1 on purchase page")
    def checkForFiveDigitZipOnPurchasePage() {
        chase.verifyFiveDigitZip1()
    }

    @Then("I should see five digit zip1 and four digit zip2 on purchase page")
    def checkForFiveDigitZip1FourDigitZip2OnPurchasePage() {
        chase.verifyFiveDigitZip1()
        chase.verifyFourDigitZip2()
    }

    @Then("I receive an OOPS message to use an alternate method of payment on pricing page")
    def checkForCyberArkOOPSMessageOnPricing() {
        pricePage.verifyCyberArkOOPSMessage()
    }

    @Then("I receive an OOPS message to use an alternate method of payment on purchase page")
    def checkForCyberArkOOPSMessageOnPurchase() {
        purchasePage.verifyCyberArkOOPSMessage()
    }

}
