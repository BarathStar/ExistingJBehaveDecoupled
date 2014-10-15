package steps

import domain.RapidRewardsAccount

import org.jbehave.core.annotations.When
import pages.AddressValidationReviewPage
import pages.CreateASwabizAccountPage
import pages.SwaBizPage
import pages.elements.AccountBar
import state.Flow
import util.RapidRewardsAccountFactory
import org.jbehave.core.annotations.Given
import org.apache.commons.lang.RandomStringUtils


class SwabizAccountSteps {
    public static final String MODAL_SUBMIT_BUTTON_ID = "tripSearchModalSubmit"

    SWABizAirSteps swabizAirSteps
    SwaBizPage swaBizPage
    CreateASwabizAccountPage createAnAccountPage

    AccountBar accountBar
    Flow flow
    AddressValidationReviewPage addressValidationReviewPage


    @Given("I enroll for a Rapid Rewards account through SWABIZ")
    def enrollForRapidRewardsAccount() {
        createAnAccountPage.open()
        String randomUserName = RandomStringUtils.random(10)

        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount(randomUserName, "fortest")
        fillAndConfirmNewRapidRewardsAccount(getSwabizCompanyId(), rrAccount)
    }

    def getSwabizCompanyId() {
        return swaBizPage.getDEFAULT_CID()
    }


    def fillAndConfirmNewRapidRewardsAccount(String companyId, RapidRewardsAccount rrAccount) {
        createAnAccountPage.fillInRequiredInformation(companyId, rrAccount)
        createAnAccountPage.clickOnCreateARapidRewardAccount()

        createAnAccountPage.submit()
        addressValidationReviewPage.verifyPage()
        addressValidationReviewPage.clickConfirm()
    }

    @Given ("I use an invalid companyID")
    def getBadSwabizCompanyId() {
        return "badCompI"
    }

    @When ("I try to enroll for a Rapid Rewards account through SWABIZ with the invalid companyID")
    def enrollForRapidRewardsAccountWithDeactivatedCompanyID(){
        createAnAccountPage.open()
        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount()
        createAnAccountPage.fillInRequiredInformation(getBadSwabizCompanyId(), rrAccount)
        createAnAccountPage.submit()
    }
}
