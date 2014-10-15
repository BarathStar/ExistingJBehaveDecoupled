/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package steps

import domain.RapidRewardsAccount
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.ChangeEmailPage
import pages.HomePage
import pages.NeedHelpLogginPage
import pages.SwaBizTravelAccountLoginPage
import pages.elements.AccountBar

/**
 * This class represent the Steps for the Change Email page for GetHealthy project
 */

class ChangeEmailSteps {

    ChangeEmailPage changeEmailPage
    HomePage homePage
    NeedHelpLogginPage needHelpLogginPage
    SwaBizTravelAccountLoginPage swabizHomePage
    AccountBar accountBar

    @Given("I am on Has Your e-mail Been Changed Page from I Need My Password entry point")
    def goToChangeEmailPage() {
        homePage.open()
        homePage.onHomePage()
        accountBar.clickOnNeedHelpLogginLink()
        needHelpLogginPage.clickNeedMyPassword()
        needHelpLogginPage.clickNeedPasswordChangeEmailLink()
    }

    @Given("I am on Has Your E-mail Been Changed Page from Swabiz I Need My Password entry point")
    def goToSwabizChangeEmailPage() {
        swabizHomePage.open()
        swabizHomePage.selectTravelAccountLogin()
        swabizHomePage.clickNeedHelpLoggingIn()
        needHelpLogginPage.clickNeedMyPassword()
        needHelpLogginPage.clickNeedPasswordChangeEmailLink()
    }

    @Given("I am on Has Your e-mail Been Changed Page from I Need My Username/Account Number entry point")
    def goToNeedUsernameChangeEmailPage() {
        homePage.open()
        homePage.onHomePage()
        accountBar.clickOnNeedHelpLogginLink()
        needHelpLogginPage.clickNeedUsernameAccount()
        needHelpLogginPage.clickNeedUsernameAccountChangeEmailLink()
    }

    @Given("I am on Has Your e-mail Been Changed Page from swabiz I Need My Username/Account Number entry point")
    def goToSwabizUsernameChangeEmailPage() {
        swabizHomePage.open()
        swabizHomePage.selectTravelAccountLogin()
        swabizHomePage.clickNeedHelpLoggingIn()
        needHelpLogginPage.clickNeedUsernameAccount()
        needHelpLogginPage.clickNeedUsernameAccountChangeEmailLink()
    }

    @When("I complete the Change e-mail fields")
    def completeChangeEmailPage() {
        changeEmailPage.fillForm(RapidRewardsAccount.createRapidRewardsUser())
        changeEmailPage.clickSubmit()
    }

    @When("I complete the Old e-mail field")
    def completeOldEmail() {
        changeEmailPage.fillOldEmail(RapidRewardsAccount.createRapidRewardsUser())
        changeEmailPage.clickSubmit()
    }
    @When("I complete the New e-mail fields")
    def completeNewEmail() {
        changeEmailPage.fillNewEmail(RapidRewardsAccount.createRapidRewardsUser())
        changeEmailPage.clickSubmit()
    }
}