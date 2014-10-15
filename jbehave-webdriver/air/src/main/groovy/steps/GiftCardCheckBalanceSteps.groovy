package steps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Given
import pages.GiftCardCheckBalancePage

class GiftCardCheckBalanceSteps {

    GiftCardCheckBalancePage giftCardBalancePage

    @When ("I click on the check now button")
    @Alias("I confirm to check the balances")
    def clickOnCheckNowButton (){
        giftCardBalancePage.clickOnCheckNowButton()
    }

    @When ("I click on the check your Balance tab")
    def clickOnCheckYourBalanceNow() {
        giftCardBalancePage.clickOnCheckYourBalance()
    }

    @When ("I enter one Giftcard which does not exist")
    def fillNonExistentGiftCard (){
        giftCardBalancePage.fillANonExistentGiftCard()
    }

    @When ("I enter one Invalid Card Number and Security Code")
    def fillInvalidCardNumberAndSecurityCode(){
        giftCardBalancePage.fillAInvalidGiftCardNumberAndSecurityCode()
    }

    @Given("I am a Southwest costumer on the southwestgiftcard page and I have selected the Check Your Balance tab")
    def accessToGiftCardCheckBalancePage(){
        giftCardBalancePage.open()
    }

    @When("I click three times on the link Check another southwestgiftcard")
    def clickThreeTimesOnLinkCheckAnotherSouthwestgiftcard(){
        giftCardBalancePage.clickOnAddAnotherGiftCard(3)
    }

    @When("I fill in the Security Codes with invalid length and leave the card numbers empty")
    def fillSecurityCodeWithInvalidLengthAndEmptyCardNumber(){
        giftCardBalancePage.fillGiftCardNumberAndSecurityCode(4,false,true,false)
    }

    @When("I fill in the security codes with valid length and leave empty the card numbers")
    def fillSecurityCodeWithValidLengthAndEmptyCardNumber(){
        giftCardBalancePage.fillGiftCardNumberAndSecurityCode(4,false,true,true)
    }

    @When("I fill in the Card Numbers with invalid length and leave the security codes empty")
    def fillCardsNumberWithInvalidLengthAndEmptySecurityCode(){
        giftCardBalancePage.fillGiftCardNumberAndSecurityCode(4,true,false,false)
    }

    @When("I fill in the Card Numbers with valid length and leave the security codes empty")
    def fillCardsNumberWithInvalidLength(){
        giftCardBalancePage.fillGiftCardNumberAndSecurityCode(4,true,false,true)
    }

    @When("I enter four giftcards which do not exist")
    def fillWithInexistentCardsNumberAndSecurityCodeWithValidLength(){
        giftCardBalancePage.fillGiftCardNumberAndSecurityCode(4,true,true,true)
    }
}