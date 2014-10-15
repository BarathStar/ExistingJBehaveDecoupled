package steps

import org.jbehave.core.annotations.When
import pages.ViewPaymentInfoPage


class ViewPaymentInfoSteps {

    ViewPaymentInfoPage viewPaymentInfoPage

    @When("I delete my primary card")
    def deletePrimaryCreditCard() {
        viewPaymentInfoPage.clickOnPrimaryCreditCardDeleteLink()
    }

    @When("I delete the Primary IRN")
    def deletePrimaryIRN() {
        viewPaymentInfoPage.deletePrimaryIRN()
    }
}