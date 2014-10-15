/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.openqa.selenium.By

import org.openqa.selenium.WebElement
import static pages.BasePage.DELETE_EXISTING
import domain.GiftCardData

/**
* This class represent the form for a gift card creation
*/
class CreateGiftCardForm {

    protected WebElement form

    private static final String AMOUNT_INPUT_ID = 'amount'
    private static final String QUANTITY_INPUT_ID = 'qty'
    private static final String EMAIL_TO_INPUT_ID = "emailTo"
    private static final String EMAIL_FROM_INPUT_ID = "emailFrom"

    CreateGiftCardForm(WebElement theContainer){
        form = theContainer
        form?.isEnabled().shouldBe true, "Form is not enabled on the page"
    }

    protected WebElement getAmount() {
        return form.findElement(By.id(AMOUNT_INPUT_ID))
    }
    protected WebElement getQuantity() {
        return form.findElement(By.id(QUANTITY_INPUT_ID))
    }
    protected WebElement getEmailTo() {
        return form.findElement(By.id(EMAIL_TO_INPUT_ID))
    }
    protected WebElement getEmailFrom() {
        return form.findElement(By.id(EMAIL_FROM_INPUT_ID))
    }
    protected void fillForm(GiftCardData giftCardInfo) {
        amount.sendKeys DELETE_EXISTING + giftCardInfo.amount
        quantity.sendKeys DELETE_EXISTING + giftCardInfo.quantity
        emailTo.sendKeys DELETE_EXISTING + giftCardInfo.emailTo
        emailFrom.sendKeys DELETE_EXISTING + giftCardInfo.emailFrom
    }
    static EmailDeliveryForm createEmailDeliveryForm(WebElement theContainer){
        return new EmailDeliveryForm(theContainer)
    }
    static PostalServiceDeliveryForm createPostalServiceDeliveryForm(WebElement theContainer){
        return new PostalServiceDeliveryForm(theContainer)
    }
}
