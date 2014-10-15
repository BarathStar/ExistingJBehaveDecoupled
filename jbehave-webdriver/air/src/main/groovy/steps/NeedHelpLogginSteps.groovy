package steps

import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.Composite
import org.jbehave.core.annotations.Alias
import domain.RapidRewardsAccount
import pages.HomePage
import pages.SwaBizTravelAccountLoginPage
import pages.CreateAnAccountPage
import pages.elements.AccountBar
import util.RapidRewardsAccountFactory
import pages.SearchFlightsPage
import pages.NeedHelpLogginPage
import pages.elements.AccountBar
import pages.elements.GlobalNavigationFooter

class NeedHelpLogginSteps {

    HomePage homePage
    NeedHelpLogginPage needHelpLogginPage
    SwaBizTravelAccountLoginPage swabizHomePage
    AccountBar accountBar
    GlobalNavigationFooter globalNavigationFooter

    @When("I complete the I need My Password form with the account number option selected")
    def completeINeedMyPassword() {
        needHelpLogginPage.fillNeedPasswordFormUsingAccountNumber()
        needHelpLogginPage.submitOnNeedPassword()
    }

    @Given("I am on I Need My Password tab from swabiz traveler account login entry point")
    def gotoSwabizINeedMyPassword() {
        swabizHomePage.open()
        swabizHomePage.selectTravelAccountLogin()
        swabizHomePage.onSwabizHomePage()
        swabizHomePage.clickNeedHelpLoggingIn()
        needHelpLogginPage.clickNeedMyPassword()
    }

    @Given("I am on I Need My Username/Account number tab from footer entry point")
    def goToINeedMyUsernameAccountNumber() {
        homePage.open()
        homePage.onHomePage()
        globalNavigationFooter.clickOnNeedHelpLogginLink()
        needHelpLogginPage.clickNeedUsernameAccount()
    }

    @When("I complete the Need My Username/Account number form with the account number option selected")
    def completeINeedMyUsernameAccountNumber() {
        needHelpLogginPage.fillNeedUsernameAccountFormUsingAccountNumber()
        needHelpLogginPage.submitOnNeedUsernameAccount()
    }

    @When("I complete the Need My Username/Account number form with the email option selected")
    def completeINeedMyUsernameAccountNumberEmail() {
        needHelpLogginPage.fillNeedUsernameAccountFormUsingEmail()
        needHelpLogginPage.submitOnNeedUsernameAccount()
    }

    @Given("I am on I Need My Username/Account number tab from swabiz travel tools entry point")
    def gotoSwabizINeedMyUsernameAccountNumber() {
        swabizHomePage.open()
        swabizHomePage.selectTravelAccountLogin()
        swabizHomePage.onSwabizHomePage()
        swabizHomePage.clickNeedHelpLoggingIn()
        needHelpLogginPage.clickNeedUsernameAccount()
    }

    @When("I complete the I need My Password form with the email option selected")
    def completeINeedMyPasswordEmail() {
        needHelpLogginPage.fillNeedPasswordFormUsingEmail()
        needHelpLogginPage.submitOnNeedPassword()
    }

    @Given("I am on I Never Logged In Before tab from account bar entry point")
    def goToINeverLoggedInBefore() {
        homePage.open()
        homePage.onHomePage()
        accountBar.clickOnNeedHelpLogginLink()
        needHelpLogginPage.clickNeverLoogedInBefore()
    }

    @When("I complete the I Never Logged In Before fields")
    def completeINeverLoggedInBefore() {
        needHelpLogginPage.fillNeverLoggedForm()
        needHelpLogginPage.submitOnNeverLogged()
    }

    @When("I select log in option")
    def selectLogInNow() {
        needHelpLogginPage.clickLogInNow()
    }

    @Given("I am on I Never Logged In Before tab from swabiz traveler account login entry point")
    def goToSwabizINeverLoggedInBefore() {
        swabizHomePage.open()
        swabizHomePage.selectTravelAccountLogin()
        swabizHomePage.onSwabizHomePage()
        swabizHomePage.clickNeedHelpLoggingIn()
        needHelpLogginPage.clickNeverLoogedInBefore()
    }

    @When("I select reset my password option")
    def selectResetYourPassword() {
        needHelpLogginPage.clickResetYourPassword()
    }
}
