package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.elements.CreateAccountConfirmationPage

class CreateAccountConfirmationThenSteps {

    CreateAccountConfirmationPage createAccountConfirmationPage

    @Then("I see the Member Account Confirmation page")
    def verifyIAmOnTheCreateAccountConfirmationPage() {
        createAccountConfirmationPage.verifyPage()
        createAccountConfirmationPage.verifyCurrentPageLocation()
    }

    @Then("I see the option where to go with my Rapid Rewards points")
    def verifyExploreLink(){
        createAccountConfirmationPage.verifyIsExploreLinkPresent()
    }

    @Then("I see the option to book Air, Car and Hotel products")
    def verifyBookLink() {
        createAccountConfirmationPage.verifyIsBookLinkPresent()
    }

    @Then("I see my first and last name in the Rapid Rewards card")
    def verifyNamesOnRRCard() {
        createAccountConfirmationPage.verifyUserNamesOnRRCard()
    }

    @Then("I see my Account Number in the account sidebar")
    def verifyAccountNumberOnSidebar() {
        createAccountConfirmationPage.verifyRRAccountNumberFormat()
    }

    @Then("I see my Account Number in the Rapid Rewards card")
    def verifyAccountNumberOnRRCard() {
        createAccountConfirmationPage.verifyRRAccountNumberOnRRCard()
    }

    @Then("I see my Account Number under the Congratulations message")
    def verifyAccountNumber() {
        createAccountConfirmationPage.verifyRRAccountNumber()
    }

    @Then("I see that all the Account Numbers displayed match")
    def validateMatcherAccountNumberOnPage(){
        createAccountConfirmationPage.validateRRAccountNumbers()
    }

}
