/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements

import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import static pages.BasePage.DELETE_EXISTING

class ResetPasswordForm{

    private WebElement container

    private static final By NEW_PASSWORD_FIELD = By.id("newPassword")
    private static final By NEW_PASSWORD_CONFIRMATION = By.id("confirmNewPassword")

    public ResetPasswordForm(WebElement theContainer) {
        this.container = theContainer
        container?.isEnabled().shouldBe true, "Form is not enabled on the page"
    }

    public void fillNewPasswordField(String password){
        WebElement newPasswordField = container.findElement(NEW_PASSWORD_FIELD)
        newPasswordField.sendKeys DELETE_EXISTING + password
    }

    public void fillNewPasswordConfirmationField(String password){
        WebElement newPasswordField = container.findElement(NEW_PASSWORD_CONFIRMATION)
        newPasswordField.sendKeys DELETE_EXISTING + password
    }
}
