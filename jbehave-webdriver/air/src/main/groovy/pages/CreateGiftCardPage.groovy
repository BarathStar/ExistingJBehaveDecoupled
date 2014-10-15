/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

import domain.EmailGiftCardData
import pages.elements.CreateGiftCardForm
import pages.elements.EmailDeliveryForm
import pages.elements.FlyOut

import pages.elements.PostalServiceDeliveryForm
import domain.PostalGiftCardData

class CreateGiftCardPage extends BasePage {

    private static final String FLYOUT_CONTENT_INNER_CLASS = "content_inner"

    private static final By DELIVERY_METHOD_EMAIL_HELP_ID = By.id("deliveryMethod_email_help")
    private static final By EMAIL_DELIVERY_METHOD_FLY_OUT = By.id("emailDeliveryMethodFlyout")
    private static final By DELIVERY_METHOD_USPS_HELP_ID = By.id("deliveryMethod_usps_help")
    private static final By DELIVERY_METHOD_FEDEX_HELP = By.id("deliveryMethod_fedex_help")
    private static final By USPS_DELIVERY_METHOD_FLY_OUT = By.id("uspsDeliveryMethodFlyout")
    private static final By FEDEX_DELIVERY_METHOD_FLY_OUT = By.id("fedexDeliveryMethodFlyout")
    private static final By DELIVERY_METHOD_EMAIL_RADIO_BUTTON_ID = By.id("deliveryMethod_email")
    private static final By DELIVERY_METHOD_USPS_RADIO_BUTTON_ID = By.id("deliveryMethod_usps")
    private static final By DELIVERY_METHOD_FEDEX_RADIO_BUTTON_ID = By.id("deliveryMethod_fedex")
    private static final By CONTINUE_BUTTON_ID = By.id("GiftCardCreateForm_submitButton")
    private static final By GIFT_CARD_CREATE_FORM = By.id("GiftCardCreateForm")
    private static final By QUANTITY = By.id("qty")

    String getRelativePath() {
        return "gift-card/create-card.html"
    }

    def CreateGiftCardPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, "gift-card/create-card.html")
    }

    FlyOut openUpTo12HoursFlyOut() {
        waitForElement(DELIVERY_METHOD_EMAIL_HELP_ID).click()
        return new FlyOut(waitForElement(EMAIL_DELIVERY_METHOD_FLY_OUT), By.className(FLYOUT_CONTENT_INNER_CLASS))
    }
    FlyOut openBusinessDaysFlyOut() {
        waitForElement(DELIVERY_METHOD_USPS_HELP_ID).click()
        return new FlyOut(waitForElement(USPS_DELIVERY_METHOD_FLY_OUT), By.className(FLYOUT_CONTENT_INNER_CLASS))
    }
    FlyOut openStandardOvernightFlyOut() {
        waitForElement(DELIVERY_METHOD_FEDEX_HELP).click()
        return new FlyOut(waitForElement(FEDEX_DELIVERY_METHOD_FLY_OUT), By.className(FLYOUT_CONTENT_INNER_CLASS))
    }
    public void selectDeliveryEmailMethod(){
        waitForElement(DELIVERY_METHOD_EMAIL_RADIO_BUTTON_ID).click()
    }
    public void selectDeliveryUspsMethod(){
        waitForElement(DELIVERY_METHOD_USPS_RADIO_BUTTON_ID).click()
    }
    public void selectDeliveryFedexMethod(){
        waitForElement(DELIVERY_METHOD_FEDEX_RADIO_BUTTON_ID).click()
    }
    public void clickContinue(){
        waitForElement(CONTINUE_BUTTON_ID).click()
    }
    public void verifyMandatoryFieldsForEmailDelivery() {
        def oopsMessages = [
            'Amount was not entered.',
            'The Recipient Email was not entered.',
            'The Confirm E-mail was not entered.'
        ]
        verifyOopsMessages(oopsMessages)
    }
    public void verifyMandatoryFieldsForUspsOrFedexDelivery() {
        def oopsMessages = [
            'Amount was not entered.',
            'First Name was not entered.',
            'Last Name was not entered.',
            'Address was not entered.',
            'City was not entered.',
            'State was not entered.',
            'Zip Code was not entered.',
            'Phone number was not entered.'
        ]
        verifyOopsMessages(oopsMessages)
    }
    public void fillEmailDeliveryMethodForm(EmailGiftCardData giftCardFormInfo) {
        EmailDeliveryForm emailForm = CreateGiftCardForm.createEmailDeliveryForm(waitForElement(GIFT_CARD_CREATE_FORM))
        emailForm.fillForm(giftCardFormInfo)
    }

    def fillFederalExpressDeliveryMethodForm(PostalGiftCardData postalGiftCardData) {
        PostalServiceDeliveryForm FedexForm = CreateGiftCardForm.createPostalServiceDeliveryForm(waitForElement(GIFT_CARD_CREATE_FORM))
        FedexForm.fillForm(postalGiftCardData)
    }

    def fillOutQuantity(String quantity) {
        waitForElement(QUANTITY).sendKeys(DELETE_EXISTING+quantity)
    }
}