package steps

import com.swacorp.dotcom.webscenarios.air.CreditCardData
import com.swacorp.dotcom.webscenarios.air.Data
import domain.PurchaseGiftCardData
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.When
import pages.PurchaseCardPage
import state.Flow
import com.swacorp.dotcom.webscenarios.air.CreditCard
import org.jbehave.core.annotations.Given

class PurchaseCardSteps {

    PurchaseCardPage purchaseCardPage
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
    Flow flow
    Data data

    @When("I click on the purchase button on the Purchase a Card page")
    @Alias("I confirm the giftCard purchase")
    def submitOnPurchaseACardPage() {
        purchaseCardPage.clickOnPurchaseButton()
    }

    @When("I fill in my credit card and Billing Information")
    def fillInCreditCardAndBillingInformationAtThePurchaseCardPage(){
        purchaseCardPage.fillInPurchaseInformation(flow.purchaseGiftCardData)
    }

    @Given("I have my credit card \$type with number \$number and security code \$code rejected from the bank")
    def postRejectedCreditCard(String type, String number, String code){
        PurchaseGiftCardData purchaseGiftCardData = PurchaseGiftCardData.createBillingGiftCardData()
        purchaseGiftCardData.creditCard=new CreditCard(CreditCard.findTYPE(type), number, 8, CreditCardData.generateValidExpirationYear(), code)
        flow.purchaseGiftCardData=purchaseGiftCardData;
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.generateRejectedCreditCard(purchaseGiftCardData.creditCard.getWholeNumber(), code, type, "reject")
        }
    }

    @When("I fill in my credit card and Billing Information with valid information")
    def fillInWithValidCreditCardAndBillingInformationAtThePurchaseCardPage(){
        flow.purchaseGiftCardData = PurchaseGiftCardData.createBillingGiftCardData()
        if (!DynaStubsIntegration.useDynaStubs()){
            flow.purchaseGiftCardData.setCreditCard(data.getRandomCreditCardOnlyVisa())
        }

        purchaseCardPage.fillInPurchaseInformation(flow.purchaseGiftCardData)
    }

    @When("I fill in my credit card and Billing Information with invalid character(s) and e-mails that do not match")
    def fillInWithInvalidPurchaseInformation(){
        flow.purchaseGiftCardData = PurchaseGiftCardData.createInvalidBillingGiftCardData()
        purchaseCardPage.fillInPurchaseInformation(flow.purchaseGiftCardData)
    }

    @When("I click the create cards breadcrumb link in the Gift Card purchase page")
    def clickTheCreateCardBreadCrumb() {
        purchaseCardPage.clickCreateCardsBreadcrumb()
    }
}