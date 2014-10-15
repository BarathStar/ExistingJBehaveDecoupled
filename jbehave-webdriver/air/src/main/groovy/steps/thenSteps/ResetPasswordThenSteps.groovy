package steps.thenSteps

import pages.ResetPasswordPage
import org.jbehave.core.annotations.Then

class ResetPasswordThenSteps {
    ResetPasswordPage resetPasswordPage

    @Then("I see an oops message which informs that the token is expired")
    public void verifyExpiredOops() {
        resetPasswordPage.verifyOopsExpiredToken()
    }

    @Then("I see an oops message which informs that the token is not valid")
    public void verifyInvalidOops() {
        resetPasswordPage.verifyOopsInvalidToken()
    }

    @Then("I see an informative message stating that my password was reset on the Account Login Page")
    public void verifyMessagePasswordSucceful() {
        resetPasswordPage.verifyMessagePassword()
    }

    @Then("I see an oops message which indicates that the password entered is not valid")
    public void verifyInvalidCharactersOops() {
        resetPasswordPage.verifyOopsInvalidCharacters()
    }

    @Then("I see an oops message which indicates that the password entered is blacklisted")
    public void verifyBlacklistedPasswordOops() {
        resetPasswordPage.verifyOopsBlacklistedPassword()
    }

    @Then("I see an oops message which indicates that the passwords do not match")
    public void verifyPasswordsThatDoNotMatchOops() {
        resetPasswordPage.verifyOopsPasswordDoNotMatch()
    }
}
