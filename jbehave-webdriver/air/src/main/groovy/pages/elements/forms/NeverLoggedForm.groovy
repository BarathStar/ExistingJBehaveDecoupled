/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements.forms

import domain.RapidRewardsAccount
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import static pages.BasePage.DELETE_EXISTING

/**
 * This class represents a Form for Never Logged in Before to be used in Need Help Loggin Page for GetHealthy project
 */

class NeverLoggedForm {

    private WebElement form
    private static final By NEVER_LOGGED_FORM_FIRST_NAME = By.id("neverLoggedIn_firstName")
    private static final By NEVER_LOGGED_FORM_LAST_NAME = By.id("neverLoggedIn_lastName")
    private static final By NEVER_LOGGED_ACCOUNT_NUMBER = By.id("neverLoggedIn_accountNumber")
    private static final By NEVER_LOGGED_SUBMIT = By.id("neverLoggedIn_submit")
    private static final By NEVER_LOGGED_ZIPCODE = By.id("neverLoggedIn_zipCode")

    NeverLoggedForm() {
        super()
    }

    NeverLoggedForm(WebElement theContainer) {
        form = theContainer
        form?.isEnabled().shouldBe true, "Form is not enabled on the page"
    }

    private WebElement getFirstName() {
        return form.findElement(NEVER_LOGGED_FORM_FIRST_NAME)
    }

    private WebElement getLastName() {
        return form.findElement(NEVER_LOGGED_FORM_LAST_NAME)
    }

    private WebElement getAccountNumber() {
        return form.findElement(NEVER_LOGGED_ACCOUNT_NUMBER)
    }

    private WebElement getSubmit() {
        return form.findElement(NEVER_LOGGED_SUBMIT)
    }

    private WebElement getZipCode() {
        return form.findElement(NEVER_LOGGED_ZIPCODE)
    }

    void clickSubmit() {
        submit.click()
    }

    void fillForm(RapidRewardsAccount member) {
        firstName.sendKeys DELETE_EXISTING + member.firstName
        lastName.sendKeys DELETE_EXISTING + member.lastName
        zipCode.sendKeys DELETE_EXISTING + member.zipCode
        accountNumber.sendKeys DELETE_EXISTING + member.accountNumberField
    }

    static NeverLoggedForm createNeverLoggedFormForm(WebElement form) {
        return new NeverLoggedForm(form)
    }
}
