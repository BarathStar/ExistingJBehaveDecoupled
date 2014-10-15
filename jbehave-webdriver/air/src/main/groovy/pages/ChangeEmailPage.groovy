/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import domain.RapidRewardsAccount
import pages.elements.forms.ChangeEmailForm
import pages.elements.forms.NeverLoggedForm;
/**
 * This class represents a Change Email page for GetHealthy project
 */

public class ChangeEmailPage extends BasePage {

    private static final By SUBMIT_BUTTON = By.id("submit")
    private static final By OLD_EMAIL = By.id("oldEmailAddress")
    private static final By EMAIL_LABEL = By.cssSelector("div.swa_rrgh_panels_rounded_inner p")
    private static final By NEW_EMAIL = By.id("newEmailAddress")
    private static final By NEW_EMAIL_CONFIRMATION = By.id("newEmailConfirmation")
    private static final By CONFIRMATION_MESSAGE = By.cssSelector("div.swa_rrgh_recoveryConfirmation h2")
    private static final By CHANGE_EMAIL_CONTAINER = By.id("changeEmailForm")

    ChangeEmailForm fillChangeEmailData

    public ChangeEmailPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/changeEmail')
    }

    String getRelativePath() {
        return "/account/changeEmail"
    }

    void clickSubmit() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    void fillForm(RapidRewardsAccount member) {
        fillChangeEmailData = ChangeEmailForm.createChangeEmailForm(waitForElement(CHANGE_EMAIL_CONTAINER))
        fillChangeEmailData.fillForm(RapidRewardsAccount.createRapidRewardsUser())
    }

    void fillOldEmail(RapidRewardsAccount member) {
        waitForElement(OLD_EMAIL).sendKeys DELETE_EXISTING + member.email
    }

    void fillNewEmail(RapidRewardsAccount member) {
        waitForElement(NEW_EMAIL).sendKeys DELETE_EXISTING + member.newEmail
        waitForElement(NEW_EMAIL_CONFIRMATION).sendKeys DELETE_EXISTING + member.newEmail
    }

    void fillOldEmailRevert(RapidRewardsAccount member) {
        waitForElement(OLD_EMAIL).sendKeys DELETE_EXISTING + member.newEmail
    }

    void fillNewEmailRevert(RapidRewardsAccount member) {
        waitForElement(NEW_EMAIL).sendKeys DELETE_EXISTING + member.email
        waitForElement(NEW_EMAIL_CONFIRMATION).sendKeys DELETE_EXISTING + member.email
    }

    def verifyOldEmailPage() {
        waitForElement(EMAIL_LABEL).getText().shouldBeEqual "Please enter your old e-mail address. We have provided a hint for you."
    }

    def verifyNewEmailPage() {
        waitForElement(EMAIL_LABEL).getText().shouldBeEqual "Please enter your new e-mail address."
    }

    def verifyConfirmationMessages() {
        waitForElement(CONFIRMATION_MESSAGE).getText().shouldBeEqual "Your e-mail address has been successfully changed."
    }
}