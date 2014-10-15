/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements.forms

import domain.RapidRewardsAccount
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import static pages.BasePage.DELETE_EXISTING

/**
 * This class represents a Form for Need Password to be used in Need Help Loggin Page for GetHealthy project
 */

class NeedPasswordForm {

    private WebElement form
    private static final By NEED_PASSWORD_FORM_FIRST_NAME = By.id("needPassword_firstName")
    private static final By NEED_PASSWORD_FORM_LAST_NAME = By.id("needPassword_lastName")
    private static final By NEED_PASSWORD_EMAIL = By.id("needPassword_emailAddress")
    private static final By NEED_PASSWORD_ACCOUNT_NUMBER = By.id("needPassword_accountNumber")
    private static final By NEED_PASSWORD_RB_EMAIL = By.id("needPassword_emailRadioButton")
    private static final By NEED_PASSWORD_RB_ACCOUNT_NUMBER = By.id("needPassword_accountRadioButton")
    private static final By NEED_PASSWORD_SUBMIT = By.id("needPassword_submit")
    private static final By NEED_PASSWORD_CHANGE_EMAIL_LINK = By.cssSelector("div.swa_rrgh_form_field_content div.swa_rrgh_form_field_helpText a span")

    NeedPasswordForm() {
        super()
    }

    NeedPasswordForm(WebElement theContainer) {
        form = theContainer
        form?.isEnabled().shouldBe true, "Form is not enabled on the page"
    }

    private WebElement getFirstName() {
        return form.findElement(NEED_PASSWORD_FORM_FIRST_NAME)
    }

    private WebElement getLastName() {
        return form.findElement(NEED_PASSWORD_FORM_LAST_NAME)
    }

    private WebElement getEmail() {
        return form.findElement(NEED_PASSWORD_EMAIL)
    }

    private WebElement getAccountNumber() {
        return form.findElement(NEED_PASSWORD_ACCOUNT_NUMBER)
    }

    private WebElement getRbEmail() {
        return form.findElement(NEED_PASSWORD_RB_EMAIL)
    }

    private WebElement getRbAccountNumber() {
        return form.findElement(NEED_PASSWORD_RB_ACCOUNT_NUMBER)
    }

    private WebElement getSubmit() {
        return form.findElement(NEED_PASSWORD_SUBMIT)
    }

    private WebElement getChangeEmailLink() {
        return form.findElement(NEED_PASSWORD_CHANGE_EMAIL_LINK)
    }

    void clickRbEmail() {
        rbEmail.click()
    }

    void clickRbAccountNumber() {
        rbAccountNumber.click()
    }

    void clickSubmit() {
        submit.click()
    }

    void clickChangeEmailLink() {
        changeEmailLink.click()
    }

    void fillForm(RapidRewardsAccount member) {
        firstName.sendKeys DELETE_EXISTING + member.firstName
        lastName.sendKeys DELETE_EXISTING + member.lastName
        if (rbEmail.isSelected()){
            email.sendKeys DELETE_EXISTING + member.email
        }
        else {
            accountNumber.sendKeys DELETE_EXISTING + member.accountNumberField
        }
    }

    static NeedPasswordForm createNeedPasswordForm(WebElement form) {
        return new NeedPasswordForm(form)
    }
}
