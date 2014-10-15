package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.mixins.*
import state.Flow
import util.PageName

@Mixin(CheckinVerifications.class)
public class AddRapidRewardsPNRPage extends BasePage {

    private static final String ADD_RAPID_REWARDS_NUMBER_FIELD_ID = "rapidRewardsNumber"
    private static final By SUBMIT_BUTTON = By.cssSelector(".submit-button-rapid-rewards#submitButton")
    private static final By ADD_RAPID_REWARDS_NUMBER_CRUMB = By.className("swa_nav_globalNav_horizontal_span")

    Flow flow

    public AddRapidRewardsPNRPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/addRRNumPnr.html');
    }

     void enterRapidRewardsNumber(String rrNumber, int rrFieldIndex = 0) {
        waitForElement(By.id(ADD_RAPID_REWARDS_NUMBER_FIELD_ID + rrFieldIndex)).sendKeys(rrNumber)
        waitForElement(SUBMIT_BUTTON).click()
    }

    def verifyPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify add rapid rewards number page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify add rapid rewards number page) ---- checking for fault injection"
        }
        System.getProperty("domainToTest") ? getCurrentUrl().shouldContain (System.getProperty("domainToTest")) : getCurrentUrl().shouldContain ("local.swacorp.com")
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.ADD_RAPID_REWARDS_NUMBER)
        waitForElement(ADD_RAPID_REWARDS_NUMBER_CRUMB).text.shouldContain "Add Rapid Rewards Number", "Page crumb did not match the expected value"
    }
}