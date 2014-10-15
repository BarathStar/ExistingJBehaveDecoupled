package steps.thenSteps

import org.jbehave.core.annotations.Then

import pages.BestRateGuaranteePage

class BestRateGuaranteeThenSteps {

    BestRateGuaranteePage bestRateGuaranteePage

    @Then("I see Required fields Oops error messages on BRG form")
    def verifyOopsMessagesAndHighlightedFields(){
        bestRateGuaranteePage.verifyFieldsOopsMessage()
    }

    @Then("I see invalid email and past dates oops error messages on BRG form")
    def verifyEmailAndDatesOopsMessages(){
        bestRateGuaranteePage.verifyEmailAndDatesOopsMessages()
    }

    @Then("I access BRG Thank you page")
    def verifyAccessThankYouPage(){
        bestRateGuaranteePage.verifyOpenThankYouPage()
    }

    @Then("I see dates beyond max booking date oops error messages on BRG form")
    def verifyDatesOopsMessagesWhenAreBeyondElevenMonths(){
        bestRateGuaranteePage.verifyDatesOopsMessagesWhenAreBeyondElevenMonths()
    }

    @Then("I see invalid amount and invalid dates oops error messages on BRG form")
    def verifyInvalidAmountPaidAndDatesOopsMessages(){
        bestRateGuaranteePage.verifyInvalidAmountPaidAndDatesOopsMessages()
    }

    @Then("I access BRG Terms Conditions page")
    def verifyAccessTermsConditionsPage(){
        bestRateGuaranteePage.verifyTermsConditionsPage()
    }

    @Then("I see invalid phone number, city/state of hotel, amount paid for your stay, name, hotel confirmation number, and e-mail oops error messages on BRG form")
    def verifyOopsMessagesOnInvalidFieldsInputs(){
        bestRateGuaranteePage.verifyOopsMessagesOnInvalidFieldsInputs()
    }
}
