package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.PurchaseCardPage
import pages.GiftCardConfirmationPage

class PurchaseCardThenSteps {
    PurchaseCardPage purchaseCardPage
    GiftCardConfirmationPage giftCardConfirmationPage

    @Then("I see the Oops messages about the mandatory fields on the Purchase a Card page")
    void verifyMandatoryOopsForUspsDeliveryMethod() {
        purchaseCardPage.verifyMandatoryFieldsOnPurchase()
    }

    @Then("I see the GiftCard Confirmation Page")
    void verifyISeeGiftCardConfirmationPage(){
        giftCardConfirmationPage.verifyCurrentPageLocation()
        giftCardConfirmationPage.verifyPageTitle("Confirm your Southwest Gift Card Order")
    }

    @Then("I see an oops message which informs me that the purchase was not completed successfully")
    void verifyISeeOopsMessagePurchaseNotCompleted(){
        purchaseCardPage.shouldShowOopsMessage(PurchaseCardPage.GENERAL_ERROR_MESSAGE)
    }

    @Then("I see oops messages indicating that my information is not valid")
    def verifyInvalidPurchaseInformationOopsMessage(){
        purchaseCardPage.verifyInvalidInformationOopsMessage()
    }

    @Then("I see that my purchase has successfully been finished on the Giftcard Purchase Page")
    def verifyPurchaseSuccessfullyFinishedOnTheGiftCardConfirmationPage(){
        giftCardConfirmationPage.verifyPurchaseSuccessfullyFinished()
    }

    @Then("I see the quantity \$quantity in the Gift Card purchase page")
    def verifySeePurchasePageWithQuantity(String quantity) {
        purchaseCardPage.verifyPage()
        purchaseCardPage.verifyQuantity(quantity)
    }
}
