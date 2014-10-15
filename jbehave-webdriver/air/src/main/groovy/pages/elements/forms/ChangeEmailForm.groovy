/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements.forms

import domain.RapidRewardsAccount
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import static pages.BasePage.DELETE_EXISTING

/**
 * This class represents a Form for Need Password to be used in Need Help Loggin Page for GetHealthy project
 */

class ChangeEmailForm {

    private WebElement form
    private static final By FIRST_NAME = By.id("firstName")
    private static final By LAST_NAME = By.id("lastName")
    private static final By ZIP_CODE = By.id("zipCode")
    private static final By ACCOUNT_NUMBER = By.id("accountNumber")

    ChangeEmailForm() {
        super()
    }

    ChangeEmailForm(WebElement theContainer) {
        form = theContainer
        form?.isEnabled().shouldBe true, "Form is not enabled on the page"
    }

    private WebElement getFirstName() {
        return form.findElement(FIRST_NAME)
    }

    private WebElement getLastName() {
        return form.findElement(LAST_NAME)
    }

    private WebElement getZipCode() {
        return form.findElement(ZIP_CODE)
    }

    private WebElement getAccountNumber() {
        return form.findElement(ACCOUNT_NUMBER)
    }

    void fillForm(RapidRewardsAccount member) {
        firstName.sendKeys DELETE_EXISTING + member.firstName
        lastName.sendKeys DELETE_EXISTING + member.lastName
        zipCode.sendKeys DELETE_EXISTING + member.zipCode
        accountNumber.sendKeys DELETE_EXISTING + member.accountNumberField
    }

    static ChangeEmailForm createChangeEmailForm(WebElement form) {
        return new ChangeEmailForm(form)
    }
}
