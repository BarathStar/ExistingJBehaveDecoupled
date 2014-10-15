package pages

import org.jbehave.web.selenium.WebDriverProvider
import com.swacorp.dotcom.webscenarios.air.config.Domains
import domain.RapidRewardsAccount
import org.openqa.selenium.By

class CreateASwabizAccountPage extends CreateAnAccountPage {

     private static final By COMPANY_ID = By.id("js-company-id")

    CreateASwabizAccountPage(WebDriverProvider driverProvider) {
        super(driverProvider, "/account/enroll/enroll-from-swabiz", Domains.swabizDomain)
    }

    def fillInRequiredInformation(String companyId, RapidRewardsAccount rrAccount) {
        fillIn(COMPANY_ID, companyId, true)
        super.fillInRequiredInformation(rrAccount)
    }

    def verifyHasInvalidCompanyMessage(){
        getExpectedOopsMessage().shouldContain "The Company ID is invalid. (SW540040)",
                "The Company ID is invalid message was not found."
    }
}
