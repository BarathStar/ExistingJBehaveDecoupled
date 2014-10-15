/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement

import org.openqa.selenium.*

import pages.elements.PurchaseGiftCardForm

import domain.PurchaseGiftCardData

class PurchaseCardPage extends BasePage {

    private static final String PATH = "gift-card/purchase-card.html"
    private final By PURCHASE_BUTTON = By.id("payGiftCardsForm_submitButton")
    private final By CREDIT_CARD_DATA_CONTAINER = By.id("credit_card_data")
    private final String CREDIT_CARD_TYPE_SELECT_ID = "creditCardType"
    private final String EXPIRATION_MONTH_SELECT_ID = "expirationMonth"
    private final String EXPIRATION_YEAR_SELECT_ID = "expirationYear"
    private final By SECURITY_CODE = By.id("securityCode")
    private final By CARD_NUMBER_1 = By.id("creditCardNumber1")
    private final By CARD_NUMBER_2 = By.id("creditCardNumber2")
    private final By CARD_NUMBER_3 = By.id("creditCardNumber3")
    private final By CARD_NUMBER_4 = By.id("creditCardNumber4")

    private final By QUANTITY = By.xpath("//td[@class='swa_tables_qty'][1]/div/span")
    private final By CREATECARDS_BREADCRUMB = By.xpath("//div[@id='breadcrumbs']//span[@id='breadcrumbItemGiftCardCreate']/a")

    private final static String GENERAL_ERROR_MESSAGE = "We were unable to process your request right now. Please try again or if you continue to have difficulties, please contact a Southwest Airlines Customer Service Representative at 1-800-I-FLY-SWA (1-800-435-9792)"

    PurchaseGiftCardForm billingGiftCardForm

    public PurchaseCardPage(WebDriverProvider driverProvider) {
        super(driverProvider,"/${PATH}")
    }

    public String getRelativePath() {
        return PATH
    }

    void clickOnPurchaseButton() {
        waitForElement(PURCHASE_BUTTON).click()
        checkSomethingServed()
    }
    void verifyMandatoryFieldsOnPurchase() {
        def oopsMessages = [
            "Card Type was not entered",
            "Card Number was not entered",
            "Expiration Month was not entered",
            "Expiration Year was not entered",
            "Security Code was not entered",
            "First Name was not entered",
            "Last Name was not entered",
            "Billing Street Address was not entered",
            "City was not entered",
            "E-mail was not entered",
            "Confirm E-mail was not entered",
            "State was not entered",
            "ZIP was not entered",
            "The purchaser's contact phone number was not entered completely"
        ]
        verifyOopsMessages(oopsMessages)
    }

    void verifyDefaultCreditCardType() {
        isOptionInDropDownSelected(CREDIT_CARD_DATA_CONTAINER, CREDIT_CARD_TYPE_SELECT_ID, "Select Your Card").shouldBe true, "Card Type drop-down menu should shows Select your card by default value "
    }

    void verifyDefaultExpirationMonth() {
        isOptionInDropDownSelected(CREDIT_CARD_DATA_CONTAINER, EXPIRATION_MONTH_SELECT_ID, "Select Month").shouldBe true, "Expiration Month drop-down menu should shows Select Month by default value "
    }

    void verifyDefaultExpirationYear() {
        isOptionInDropDownSelected(CREDIT_CARD_DATA_CONTAINER, EXPIRATION_YEAR_SELECT_ID, "Select Year").shouldBe true, "Expiration Year drop-down menu should shows Select Year by default value "
    }

    void verifySecurityCodeIsEmpty() {
        waitForElement(CREDIT_CARD_DATA_CONTAINER).findElement(SECURITY_CODE).getText().isEmpty().shouldBe true, "The security code should be empty"
    }

    void verifyCreditCardNumberIsEmpty() {
        WebElement container = waitForElement(CREDIT_CARD_DATA_CONTAINER)
        container.findElement(CARD_NUMBER_1).getText().isEmpty().shouldBe true, "The card number should be empty"
        container.findElement(CARD_NUMBER_2).getText().isEmpty().shouldBe true, "The card number should be empty"
        container.findElement(CARD_NUMBER_3).getText().isEmpty().shouldBe true, "The card number should be empty"
        container.findElement(CARD_NUMBER_4).getText().isEmpty().shouldBe true, "The card number should be empty"
    }

    def fillInPurchaseInformation(PurchaseGiftCardData giftCardData) {
        billingGiftCardForm.fillForm(giftCardData)
    }

    def verifyInvalidInformationOopsMessage() {
        def oopsMessage = [
                "The billing First name, ",
                ", contains invalid character(s): ",
                "The billing Last name, ",
                ", contains invalid character(s): ",
                "The City, ",
                ", contains invalid character(s): ",
                "The E-mail addresses entered must match. Please try again.",
                "The purchasers zip code, ",
                ", contains the invalid character(s): ",
                "The purchasers zip code, ",
                ", contains the invalid character(s): "
        ]
        verifyOopsMessages(oopsMessage)
    }

    def verifyQuantity(String quantity) {
        waitForElement(QUANTITY).getText().equals(quantity).shouldBe true, "The quantity does not match"
    }

    def clickCreateCardsBreadcrumb() {
        waitForElement(CREATECARDS_BREADCRUMB).click()
    }
}
