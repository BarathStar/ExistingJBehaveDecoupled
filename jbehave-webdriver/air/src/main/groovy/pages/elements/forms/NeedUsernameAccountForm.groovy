/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements.forms

import domain.RapidRewardsAccount
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import static pages.BasePage.DELETE_EXISTING

/**
 * This class represents a Form for Need Username Account Number to be used in Need Help Loggin Page for GetHealthy project
 */

class NeedUsernameAccountForm {

    private WebElement form
    private static final By NEED_USERNAME_FORM_FIRST_NAME = By.id("needUsername_firstName")
    private static final By NEED_USERNAME_FORM_LAST_NAME = By.id("needUsername_lastName")
    private static final By NEED_USERNAME_EMAIL = By.id("needUsername_emailAddress")
    private static final By NEED_USERNAME_ACCOUNT_NUMBER = By.id("accountNumber")
    private static final By NEED_USERNAME_RB_EMAIL = By.id("needUsername_emailRadioButton")
    private static final By NEED_USERNAME_RB_ACCOUNT_NUMBER = By.id("needUsername_accountRadioButton")
    private static final By NEED_USERNAME_SUBMIT = By.id("needUsername_submit")
    private static final By NEED_USERNAME_CHANGE_EMAIL_LINK = By.cssSelector("div.swa_rrgh_form_field_content div.swa_rrgh_form_field_helpText a span")
    private static final By NEED_USERNAME_ZIPCODE = By.id("needUsername_zipCode")

    NeedUsernameAccountForm() {
        super()
    }

    NeedUsernameAccountForm(WebElement theContainer) {
        form = theContainer
        form?.isEnabled().shouldBe true, "Form is not enabled on the page"
    }

    private WebElement getFirstName() {
        return form.findElement(NEED_USERNAME_FORM_FIRST_NAME)
    }

    private WebElement getLastName() {
        return form.findElement(NEED_USERNAME_FORM_LAST_NAME)
    }

    private WebElement getEmail() {
        return form.findElement(NEED_USERNAME_EMAIL)
    }

    private WebElement getAccountNumber() {
        return form.findElement(NEED_USERNAME_ACCOUNT_NUMBER)
    }

    private WebElement getRbEmail() {
        return form.findElement(NEED_USERNAME_RB_EMAIL)
    }

    private WebElement getRbAccountNumber() {
        return form.findElement(NEED_USERNAME_RB_ACCOUNT_NUMBER)
    }

    private WebElement getSubmit() {
        return form.findElement(NEED_USERNAME_SUBMIT)
    }

    private WebElement getChangeEmailLink() {
        return form.findElement(NEED_USERNAME_CHANGE_EMAIL_LINK)
    }

    private WebElement getZipCode() {
        return form.findElement(NEED_USERNAME_ZIPCODE)
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
        zipCode.sendKeys DELETE_EXISTING + member.zipCode
        if (rbEmail.isSelected()){
            email.sendKeys DELETE_EXISTING + member.email
        }
        else {
            accountNumber.sendKeys DELETE_EXISTING + member.accountNumberField
        }
    }

    static NeedUsernameAccountForm createNeedUsernameAccountForm(WebElement form) {
        return new NeedUsernameAccountForm(form)
    }
}
