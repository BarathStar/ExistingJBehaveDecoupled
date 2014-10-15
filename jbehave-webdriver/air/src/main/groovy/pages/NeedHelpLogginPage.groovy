/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/

package pages

import domain.RapidRewardsAccount
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.elements.forms.NeedPasswordForm
import pages.elements.forms.NeedUsernameAccountForm
import pages.elements.forms.NeverLoggedForm

/**
 * This class represents a Need Help Loggin Page for GetHealthy project
 */

public class NeedHelpLogginPage extends BasePage {

    private static final String WINDOWS_TITLE_MSG = 'Need Help Logging In?'
    private static final By LOG_IN_NOW_LINK = By.cssSelector("form#neverLoggedInLogin.swa_rrgh_form_container button")
    private static final By RESET_YOUR_PASSWORD_LINK = By.cssSelector("form#neverLoggedInResetPassword.swa_rrgh_form_container button")
    private static final By RB_NEED_PASSWORD= By.cssSelector("li.swa_rrgh_tabMenu_needPassword label input")
    private static final By RB_NEED_USERNAME_ACCOUNT= By.cssSelector(".swa_rrgh_tabMenu_needUsername label input")
    private static final By RB_NEVER_LOGGED= By.cssSelector(".swa_rrgh_tabMenu_neverLoggedIn label input")
    private static final By ENROLL_NOW_LINK= By.cssSelector("div.swa_rrgh_panels_wcm_content p a")
    private static final By NEED_PASSWORD_FORM_CONTAINER = By.cssSelector("form#securityAccountNeedPasswordForm.swa_rrgh_form_container")
    private static final By NEED_USERNAME_ACCOUNT_FORM_CONTAINER = By.cssSelector("form#securityAccountNeedUsernameForm.swa_rrgh_form_container")
    private static final By NEVER_LOGGED_FORM_CONTAINER = By.cssSelector("form#securityAccountNeverLoggedInForm.swa_rrgh_form_container")

    NeedPasswordForm fillNeedPasswordData
    NeedUsernameAccountForm fillUsernameAccountData
    NeverLoggedForm fillNeverLoggedData

    public NeedHelpLogginPage(WebDriverProvider driverProvider) {
        super(driverProvider,'/account/recovery')
    }

    String getRelativePath() {
        return "/account/recovery"
    }

    void clickNeedMyPassword() {
        waitForElement(RB_NEED_PASSWORD).click()
    }

    void clickNeedUsernameAccount() {
        waitForElement(RB_NEED_USERNAME_ACCOUNT).click()
    }

    void clickNeverLoogedInBefore() {
        waitForElement(RB_NEVER_LOGGED).click()
    }

    void clickEnrollNowLink() {
        waitForElement(ENROLL_NOW_LINK).click()
    }

    void verifyNeedHelpLogginFormTitle() {
        getTitle().shouldBe WINDOWS_TITLE_MSG, "The windows title should be ${WINDOWS_TITLE_MSG}"
    }

    void fillNeedPasswordFormUsingAccountNumber() {
        fillNeedPasswordData = NeedPasswordForm.createNeedPasswordForm(waitForElement(NEED_PASSWORD_FORM_CONTAINER))
        fillNeedPasswordData.clickRbAccountNumber()
        fillNeedPasswordData.fillForm(RapidRewardsAccount.createRapidRewardsUser())
    }

    void fillNeedPasswordFormUsingEmail() {
        fillNeedPasswordData = NeedPasswordForm.createNeedPasswordForm(waitForElement(NEED_PASSWORD_FORM_CONTAINER))
        fillNeedPasswordData.clickRbEmail()
        fillNeedPasswordData.fillForm(RapidRewardsAccount.createRapidRewardsUser())
    }

    void fillNeedUsernameAccountFormUsingAccountNumber() {
        fillUsernameAccountData = NeedUsernameAccountForm.createNeedUsernameAccountForm(waitForElement(NEED_USERNAME_ACCOUNT_FORM_CONTAINER))
        fillUsernameAccountData.clickRbAccountNumber()
        fillUsernameAccountData.fillForm(RapidRewardsAccount.createRapidRewardsUser())
    }

    void fillNeedUsernameAccountFormUsingEmail() {
        fillUsernameAccountData = NeedUsernameAccountForm.createNeedUsernameAccountForm(waitForElement(NEED_USERNAME_ACCOUNT_FORM_CONTAINER))
        fillUsernameAccountData.clickRbEmail()
        fillUsernameAccountData.fillForm(RapidRewardsAccount.createRapidRewardsUser())
    }

    void fillNeverLoggedForm() {
        fillNeverLoggedData = NeverLoggedForm.createNeverLoggedFormForm(waitForElement(NEVER_LOGGED_FORM_CONTAINER))
        fillNeverLoggedData.fillForm(RapidRewardsAccount.createRapidRewardsUser())
    }

    void clickNeedPasswordChangeEmailLink() {
        fillNeedPasswordData = NeedPasswordForm.createNeedPasswordForm(waitForElement(NEED_PASSWORD_FORM_CONTAINER))
        fillNeedPasswordData.clickChangeEmailLink()
    }

    void clickNeedUsernameAccountChangeEmailLink() {
        fillUsernameAccountData = NeedUsernameAccountForm.createNeedUsernameAccountForm(waitForElement(NEED_USERNAME_ACCOUNT_FORM_CONTAINER))
        fillUsernameAccountData.clickChangeEmailLink()
    }

    void submitOnNeedPassword(){
        fillNeedPasswordData.clickSubmit()
    }
    void submitOnNeedUsernameAccount(){
        fillUsernameAccountData.clickSubmit()
    }
    void submitOnNeverLogged(){
        fillNeverLoggedData.clickSubmit()
    }

    void clickLogInNow(){
        waitForElement(LOG_IN_NOW_LINK).click()
    }

    void clickResetYourPassword(){
        waitForElement(RESET_YOUR_PASSWORD_LINK).click()
    }
}
