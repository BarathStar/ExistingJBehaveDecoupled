package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.forms.RapidRewardsSubscriptionInformationForm
import state.AccountManagementData
import state.Flow
import state.PassengerData

class RapidRewardsEmailPage extends BasePage {
    private static final By RR_SUBSCRIPTION_INFORMATION_FORM_CONTAINER = By.id("rapidRewardsSubscriptionInformation")
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    Flow flow
    WebDriverProvider webDriverProvider
    AccountManagementData accountManagementData
    RapidRewardsSubscriptionInformationForm rapidRewardsSubscriptionInformationForm

    def RapidRewardsEmailPage(WebDriverProvider theWebDriverProvider) {
        super(theWebDriverProvider, '/flight/subscription/rr-email-subscribe.html')
        this.webDriverProvider = theWebDriverProvider
        rapidRewardsSubscriptionInformationForm = new RapidRewardsSubscriptionInformationForm(webDriverProvider)
    }

    @Override
    public String getRelativePath() {
        return '/flight/subscription/rr-email-subscribe.html';
    }

    def fillInNameAndAccountNumber(){
        rapidRewardsSubscriptionInformationForm.fillFirstNameField(flow.getRrUser().firstName)
        rapidRewardsSubscriptionInformationForm.fillLastNameField(flow.getRrUser().lastName)
        rapidRewardsSubscriptionInformationForm.fillCustomerNumberField(flow.getRrUser().number)
    }

    def fillInFirstEmailAddress(){
        rapidRewardsSubscriptionInformationForm.fillFirstEmailAddressField(accountManagementData.emailAddress)
    }

    def fillInSecondEmailAddress(){
        rapidRewardsSubscriptionInformationForm.fillSecondEmailAddressField(accountManagementData.secondEmailAddress)
    }

    def fillInThirdEmailAddress(){
        rapidRewardsSubscriptionInformationForm.fillThirdEmailAddressField(accountManagementData.thirdEmailAddress)
    }

    def selectFirstBoxRapidRewardsEmailUpdate(){
        rapidRewardsSubscriptionInformationForm.selectFirstBoxRapidRewardsEmailUpdate()
    }

    def selectFirstBoxRapidRewardsReport(){
        rapidRewardsSubscriptionInformationForm.selectFirstBoxRapidRewardsReport()
    }

    def selectSecondBoxRapidRewardsEmailUpdate(){
        rapidRewardsSubscriptionInformationForm.selectSecondBoxRapidRewardsEmailUpdate()
    }

    def selectSecondBoxRapidRewardsReport(){
        rapidRewardsSubscriptionInformationForm.selectSecondBoxRapidRewardsReport()
    }

    def selectThirdBoxRapidRewardsEmailUpdate(){
        rapidRewardsSubscriptionInformationForm.selectThirdBoxRapidRewardsEmailUpdate()
    }

    def selectThirdBoxRapidRewardsReport(){
        rapidRewardsSubscriptionInformationForm.selectThirdBoxRapidRewardsReport()
    }

    def clickSuscribeButton() {
        String title = getTitle()
        waitForElement(SUBMIT_BUTTON).click()
    }
}