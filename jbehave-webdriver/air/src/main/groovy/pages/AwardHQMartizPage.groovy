package pages

import org.jbehave.web.selenium.WebDriverProvider

class AwardHQMartizPage extends BasePage{
    private static String AWARD_HQ_MARITZ_DOMAIN = "mms08-56.maritzstage.com"

    AwardHQMartizPage (WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    def verifyAwardHQPageUrl(){
        shouldBeInPage(AWARD_HQ_MARITZ_DOMAIN)
    }
}
