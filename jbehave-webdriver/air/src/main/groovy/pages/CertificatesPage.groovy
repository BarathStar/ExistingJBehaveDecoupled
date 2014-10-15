package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class CertificatesPage extends BasePage {

    private static final String PATH = "account/rapidrewards/certificates"
    private static final By YES_REISSUABLE_LINK_1 = By.cssSelector(".reissue1.centeredCell a")
    private static final By CHECKBOX_REISSUABLE_AWARD_1 = By.id("selectedAwardNumbers1")
    private static final By REISSUE_AWARD_BUTTON = By.id("submitButton")

    CertificatesPage(WebDriverProvider driverProvider){
        super(driverProvider,PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def clickOnYesReissuableLink(){
        waitForElement(YES_REISSUABLE_LINK_1).click()
        verifyPage()
    }

    def clickOnCheckBoxReissuableAward(){
        waitForElement(CHECKBOX_REISSUABLE_AWARD_1).click()
        verifyPage()
    }

    def clickOnReissueAwardButton(){
        waitForElement(REISSUE_AWARD_BUTTON).click()
    }
}
