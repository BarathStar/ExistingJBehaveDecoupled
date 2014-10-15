package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import pages.elements.ResetPasswordForm
import util.ResetPasswordData

class ResetPasswordPage extends BasePage {

    private static final By SUBMIT_BUTTON = By.id("submit")
    private static final By RESET_PASSWORD_FORM_CONTAINER = By.id("resetPassword")
    private static final By PAGE_NAME = By.id("sw_breadcrumb")
    private static final By MESSAGE_CONGRATULATION = By.id("notifications")

    ResetPasswordData resetPasswordData

    def ResetPasswordPage (WebDriverProvider webDriverProvider) {
        super(webDriverProvider, '/account/login/reset-password?token=')
    }

    @Override
    def String getRelativePath() {
        return '/account/login/reset-password'
    }

    def fillInPasswords(){
        ResetPasswordForm resetPasswordForm = new ResetPasswordForm(waitForElement(RESET_PASSWORD_FORM_CONTAINER))
        resetPasswordForm.fillNewPasswordField(resetPasswordData.password_1)
        resetPasswordForm.fillNewPasswordConfirmationField(resetPasswordData.password_2)
    }

    def clickVisibleSubmit() {
        String title = getTitle()
        waitForElement(SUBMIT_BUTTON).click()
    }

    def verifyOopsExpiredToken(){
        def oopsMessages = [
            'The web address you entered has expired. It is only valid for 30 days from the time you request a password reset.'
        ]
        verifyOopsMessages(oopsMessages)
    }

    def verifyMessagePassword(){
        waitForElement(PAGE_NAME).text.shouldBe 'My Account Login'
        waitForElement(MESSAGE_CONGRATULATION).text.shouldBe 'Congratulations! Your password has been reset.\nPlease login using your new password.'
    }

    def openWithToken(String token) {
        String url = "${domain}/account/login/reset-password?token=${token}"
        get(url)
    }

    def verifyOopsInvalidToken(){
        def oopsMessages = ['The web address you entered is not valid. Please ensure you copied the Password Reset web address correctly.']
        verifyOopsMessages(oopsMessages)
    }

    def verifyOopsInvalidCharacters(){
        def oopsMessages = ['The Password you entered is not valid. A Password is case sensitive and cannot contain spaces or special characters.']
        verifyOopsMessages(oopsMessages)
    }

    def verifyOopsBlacklistedPassword() {
        def oopsMessages = ['The Password you entered is not valid. A Password cannot contain "'+resetPasswordData.password_1.toLowerCase()+'".']
        verifyOopsMessages(oopsMessages)
    }

    def verifyOopsPasswordDoNotMatch(){
        def oopsMessages = ['The Passwords do not match. Please try again.']
        verifyOopsMessages(oopsMessages)
    }
}
