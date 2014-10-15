/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import domain.EmailGiftCardData
import static pages.BasePage.DELETE_EXISTING

/**
* This class represent the form for a gift card creation
* with email delivery selected
*/
class EmailDeliveryForm extends CreateGiftCardForm {

    private static final String RECIPIENT_EMAIL_INPUT_ID = 'recipientEmail'
    private static final String RE_ENTER_EMAIL_INPUT_ID = 'reEnterEmail'

    public EmailDeliveryForm(WebElement theContainer) {
        super(theContainer)
    }

    private WebElement getRecipientEmail(){
        form.findElement(By.id(RECIPIENT_EMAIL_INPUT_ID))
    }
    private WebElement getReEnterEmail(){
        form.findElement(By.id(RE_ENTER_EMAIL_INPUT_ID))
    }

    void fillForm(EmailGiftCardData emailGiftCardInfo){
        super.fillForm(emailGiftCardInfo)
        recipientEmail.sendKeys DELETE_EXISTING + emailGiftCardInfo.recipientEmail
        reEnterEmail.sendKeys DELETE_EXISTING + emailGiftCardInfo.confirmEmail
    }
}
