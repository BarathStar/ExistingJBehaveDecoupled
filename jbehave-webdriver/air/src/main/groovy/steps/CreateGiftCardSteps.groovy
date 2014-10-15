/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package steps

import pages.CreateGiftCardPage
import org.jbehave.core.annotations.*
import domain.EmailGiftCardData
import pages.PurchaseCardPage
import domain.PostalGiftCardData

class CreateGiftCardSteps {

    CreateGiftCardPage createGiftCardPage
    PurchaseCardPage purchaseCardPage

    @When("I select email delivery method on the Create a Card page")
    def selectEmailDeliveryMethod() {
        createGiftCardPage.selectDeliveryEmailMethod()
    }

    @When("I select the usps delivery method on the Create a Card page")
    def selectUspsDeliveryMethod() {
        createGiftCardPage.selectDeliveryUspsMethod()
    }

    @When("I select the fedex delivery method on the Create a Card page")
    def selectFedexDeliveryMethod() {
        createGiftCardPage.selectDeliveryFedexMethod()
    }

    @When("I click on the continue button on the Create a Card page")
    def continueToGCPurchasePage() {
        createGiftCardPage.clickContinue()
        purchaseCardPage.checkSomethingServed()
    }

    @When("I fill out the create a card form with the email delivery selected")
    def fillOutCreateACardFormWithEmailDelivery() {
        selectEmailDeliveryMethod()
        createGiftCardPage.fillEmailDeliveryMethodForm(EmailGiftCardData.createEmailGiftCardData())
    }

    @When("I fill out the create a card form with the Federal Express delivery method selected")
    def fillOutCreateACardFormWithFederalExpressDelivery(){
        createGiftCardPage.selectDeliveryFedexMethod()
        createGiftCardPage.fillFederalExpressDeliveryMethodForm(PostalGiftCardData.createPostalGiftCardData())
    }

    @When("I fill out the create a card form with the USPS delivery method selected")
    def fillOutCreateACardFormWithUSPSDelivery(){
        createGiftCardPage.selectDeliveryUspsMethod()
        createGiftCardPage.fillFederalExpressDeliveryMethodForm(PostalGiftCardData.createPostalGiftCardData())
    }

    @When("I enter the required information including quantity \$quantity in the Gift Card create cards page and proceed")
    def fillOutCreateCardFormWithQuanityAndProceed(String quantity) {
        createGiftCardPage.verifyPage()
        fillOutCreateACardFormWithEmailDelivery()
        fillOutQuantityAndProceed(quantity)
    }

    @When("I enter the quantity \$quantity in the Gift Card create cards page and proceed")
    def fillOutQuantityAndProceed(String quantity) {
        createGiftCardPage.verifyPage()
        createGiftCardPage.fillOutQuantity(quantity)
        continueToGCPurchasePage()
    }
}