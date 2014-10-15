/* Copyright (c) 2012, Southwest Airlines Co.  All rights reserved.*/
package pages

import domain.RapidRewardsAccount
import org.jbehave.web.selenium.WebDriverProvider

import org.openqa.selenium.By
import pages.elements.forms.EmailUpdatesForm
import state.AccountManagementData
import util.RapidRewardsAccountFactory

import static util.Locators.getBREADCRUMB_IDS

public class CnsEmailUpdatesPage extends BasePage {

    private static final By SINGUP_AND_SAVE_LINK = By.cssSelector(".globalnav_header_utility_link_signupsave a")
    WebDriverProvider webDriverProvider
    AccountManagementData accountManagementData

    public CnsEmailUpdatesPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/subscription/cns-email-subscribe.html')
        this.webDriverProvider = webDriverProvider
    }

    def openCnsEmailUpdatesPage() {
        open()
        verifyPage()
    }

    def clickSingUpAndSave() {
        String title = getTitle()
        waitForElement(SINGUP_AND_SAVE_LINK).click()
        waitForPageTitleToChangeOrOops(title)
        verifyPage()
    }

    def verifyPage(){
        super.verifyPage()
        waitForElement(By.cssSelector("#sw_breadcrumb span")).text.shouldContain "Subscribe to Click 'N Save® Email Updates"
        getCurrentUrl().shouldContain "/flight/subscription/cns-email-subscribe.html"
    }

    void fillAndSubmitEmailUpdatesForm(RapidRewardsAccount user, String email = accountManagementData.emailAddress) {
        EmailUpdatesForm emailUpdatesForm = new EmailUpdatesForm(webDriverProvider)
        emailUpdatesForm.fillFirstName(user.firstName)
        emailUpdatesForm.fillLastName(user.lastName)
        emailUpdatesForm.fillEmailAddres(email)
        emailUpdatesForm.fillVerifyEmailAddres(email)
        emailUpdatesForm.clickSubscribeButton()
    }

    void fillAndSubmitEmailUpdatesFormWithInvalidEmail(){
        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount()
        fillAndSubmitEmailUpdatesForm(rrAccount,"test*test@wnco.com")
    }

    @Override
    public String getRelativePath() {
        return '/flight/subscription/cns-email-subscribe.html'
    }
}