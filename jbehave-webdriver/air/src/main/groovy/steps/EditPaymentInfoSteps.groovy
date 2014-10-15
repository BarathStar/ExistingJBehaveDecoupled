package steps

import com.swacorp.dotcom.webscenarios.air.CreditCard
import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.core.annotations.When
import pages.EditPaymentInfoPage
import state.Flow

class EditPaymentInfoSteps {

    EditPaymentInfoPage editPaymentInfoPage
    Flow flow
    Data data

    @When("I complete all the Credit Card information")
    def completeAllCreditCardInformation() {
        CreditCard creditCard = data.getRandomCreditCard()
        editPaymentInfoPage.fillCreditCardFirstName(flow.getRrUser().RRFirstName)
        editPaymentInfoPage.fillCreditCardLastName(flow.getRrUser().RRLastName)
        editPaymentInfoPage.fillCreditCardType(creditCard.type.toString())
        editPaymentInfoPage.fillCreditCardNumber(creditCard.getWholeNumber())
        editPaymentInfoPage.fillCreditCardExpirationMonth(creditCard.getExpirationMonth())
        editPaymentInfoPage.fillCreditCardExpirationYear(creditCard.getExpirationYear())
        editPaymentInfoPage.fillCreditCardDescription("Payment Card")
        editPaymentInfoPage.fillCreditCardBillingAddressLineOne("2702 Love Field Drive")
        editPaymentInfoPage.fillCreditCardBillingAddressCity("Dallas")
        editPaymentInfoPage.fillCreditCardBillingAddressZipCode("75235")
        editPaymentInfoPage.fillCreditCardBillingAddressState("TX")
    }

    @When("I update my Payment Information information")
    def updatePaymentInformation() {
        editPaymentInfoPage.clickOnSubmitButton()
    }

    @When("I click on update my Payment Information information twice")
    def submitPaymentInformationTwice() {
        editPaymentInfoPage.doubleClickOnSubmitButton()
    }

    @When("I click on add another credit card")
    def clickOnAddAnotherCreditCard() {
        editPaymentInfoPage.clickOnAddAnotherCreditCard()
    }

    @When("I click on edit or add credit card")
    def clickOnAddEditCreditCard() {
        editPaymentInfoPage.clickOnAddEditCreditCardLink()
    }
}
