package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.mixins.*
import state.Flow
import util.PageName
import util.RRContactInformation

@Mixin(CheckinVerifications.class)
public class AddRapidRewardsPNRConfirmationPage extends BasePage {

    private static final By UPGRADE_TO_BUSINESS_SELECT_BUTTON = By.id("upgradeToBusinessSelect")
    private static final By ADD_RAPID_REWARDS_NUMBER_CRUMB = By.className("swa_nav_globalNav_horizontal_span")
    private static final By SING_IN_MYSOUTHWEST_LINK = By.cssSelector(".checkinTopInstructions a")
    private static final By UPDATED_RESERVATION_INFORMATION_TEXT = By.className("swa_feature_addRRconfirmation_header")
    private static final By RR_ACCOUNT_NUMBER = By.className("rrAccountNumber")

    Flow flow
    RRContactInformation rrContactInformation

    public AddRapidRewardsPNRConfirmationPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/addRRNumPnr.html');
    }

    void verifyRapidRewardsNumberAdded(){
        waitForElement(RR_ACCOUNT_NUMBER).text.shouldBe rrContactInformation.accountNumber, "Rapid Rewards number did not match the expected value"
    }

    def verifyPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify add rapid rewards number confirmation page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify add rapid rewards number confirmation page) ---- checking for fault injection"
        }
        System.getProperty("domainToTest") ? getCurrentUrl().shouldContain (System.getProperty("domainToTest")) : getCurrentUrl().shouldContain ("local.swacorp.com")
        pageVerificationErrorWrapper(UPGRADE_TO_BUSINESS_SELECT_BUTTON, PageName.ADD_RAPID_REWARDS_NUMBER_CONFIRMATION)
        waitForElement(ADD_RAPID_REWARDS_NUMBER_CRUMB).text.shouldContain "Add Rapid Rewards Number", "Page crumb did not match the expected value"
    }

    def verifySingInToMySouthwestLink() {
        isElementPresent(SING_IN_MYSOUTHWEST_LINK).shouldBe true, "Sing in to MySouthwest Link was not present in the page"
    }

    def verifyUpdatedReservationInformationText() {
        waitForElement(UPDATED_RESERVATION_INFORMATION_TEXT).text.shouldBe "Updated Reservation Information", "Updated Reservation Information text did not match the expected value"
    }

    def verifyUpgradeBusinessSelectButton() {
        isElementPresent(UPGRADE_TO_BUSINESS_SELECT_BUTTON).shouldBe true, "Upgrade to business button was not present in the page"
    }
}