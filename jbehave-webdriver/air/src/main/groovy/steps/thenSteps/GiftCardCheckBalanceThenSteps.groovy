package steps.thenSteps

import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.Alias
import pages.GiftCardCheckBalancePage

class GiftCardCheckBalanceThenSteps {

    GiftCardCheckBalancePage giftCardBalancePage

    @Then ("I see the check your balance tab selected by default on the giftcard landing page")
    def verifyLocationAndBalanceTabsSelected(){
        giftCardBalancePage.verifyLocationAndBalanceTabsSelected()
    }

    @Then ("I see the Giftcard section with the link Check another southwestgifcard  and the verbiage (up to 4)")
    def verifyCheckAnotherSouthwestGiftcardLink(){
        giftCardBalancePage.verifyCheckAnotherSouthwestGiftcardLink()
    }

    @Then ("I see the Oops Messages related to the non-existent giftcard")
    @Alias ("I see the Oops message informing me that the giftcards were not found")
    def verifyNonExistentGiftCard (){
        giftCardBalancePage.verifyNonExistentGiftCard()
    }

    @Then ("I see the Oops Messages related to the invalid card length and invalid security code length")
    def verifyMandatoryFormatFields (){
        giftCardBalancePage.verifyMandatoryFormatFields()
    }

    @Then ("I should not see the link named Check another southwestgiftcard")
    def notSeeLinkCheckAnotherSouthwestgiftcard() {
        giftCardBalancePage.verifyNotExistCheckAnotherSouthwestgiftcardLink()
        giftCardBalancePage.verifyMaximunCardsAllowedText()
    }

    @Then("I see the Oops messages which inform me that the card numbers were not entered and the security code lengths are invalid")
    def seeOopsMessagesCardNumbersNotEnteredAndSecurityCodeInvalid(){
        giftCardBalancePage.verifyOopsMessagesCardNumbersNotEnteredAndSecurityCodeInvalid()
    }

    @Then("I see the Oops message which inform me that the card numbers were not entered")
    def seeOopsMessagesCardNumbersNotEntered(){
        giftCardBalancePage.verifyOopsMessagesCardNumbersNotEntered()
    }

    @Then("I see the Oops messages which inform me that the security codes were not entered and the card number lengths are invalid")
    def seeOopsMessagesCardNumbersInvalidSecurityCodeNotEntered(){
        giftCardBalancePage.verifyOopsMessagesCardNumbersInvalidSecurityCodeNotEntered()
    }

    @Then("I see the Oops messages which inform me that the security codes were not entered")
    def seeOopsMessagesSecurityCodeNotEntered(){
        giftCardBalancePage.verifyOopsMessagesSecurityCodeNotEntered()
    }
}