package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.EditPaymentInfoPage

class EditPaymentInfoThenSteps {

    EditPaymentInfoPage editPaymentInfoPage

    @Then("I see the Payment Information page is editable")
    def verifyPaymentInformationPageIsEditable() {
        editPaymentInfoPage.verifyCurrentPageLocation()
        editPaymentInfoPage.verifyCancelLinkIsPresent()
        editPaymentInfoPage.verifySubmitButtonIsPresent()
    }

    @Then("I am on Edit Payment Information page without any stored credit card info")
    def verifyEditPaymentInformationPage() {
        editPaymentInfoPage.verifyEditPaymentInfoPage()
        editPaymentInfoPage.verifyNoStoredCreditCard()
    }
}
