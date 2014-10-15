package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.ViewPaymentInfoPage
import pages.elements.InternalReferenceNumberForm
import pages.MyAccountPage

class ViewPaymentInfoThenSteps {

    InternalReferenceNumberForm internalReferenceNumberForm
    ViewPaymentInfoPage viewPaymentInfoPage
    MyAccountPage myAccountPage


    @Then("I see the Payment Information page is not editable")
    def verifyPaymentInformationPageIsNotEditable() {
        viewPaymentInfoPage.verifyCancelLinkIsNotPresent()
        viewPaymentInfoPage.verifySubmitButtonIsNotPresent()
    }

    @Then("I see the Payment Information section can be edited")
    def verifyPaymentInformationSectionIsEditable() {
        viewPaymentInfoPage.verifyEditLinkIsPresent()
    }

    @Then("I see the IRNs list in alphabetical/numerical order")
    def verifyIRNsOrderByAlphabeticalNumerical() {
        int size =  internalReferenceNumberForm.getAllIRNs().size()
        while(size < 3) {
           String c =  ["y","x","z"].get((int)Math.round(Math.random()*2))
           viewPaymentInfoPage.addOtherIRN(c+"alt"+size.toString(),"description",size.toString())
           size++
        }
        viewPaymentInfoPage.verifyIRNsOrderByAlphabeticalNumerical()
    }

    @Then("I see \$numberOfCards credit cards with another card marked primary")
    def verifyStoredCreditCards(int numberOfCards){
        viewPaymentInfoPage.verifyNumberOfCreditCardsIs(numberOfCards)
        viewPaymentInfoPage.verifyPrimaryCreditCard()
    }

    @Then("I see that a custom IRN was set as a Primary IRN")
    def verifyPrimaryIRN(){
        viewPaymentInfoPage.verifyPrimaryIRN()
    }
}
