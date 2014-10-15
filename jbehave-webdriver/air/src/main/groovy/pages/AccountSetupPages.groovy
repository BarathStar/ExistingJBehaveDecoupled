package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.Flow

class AccountSetupPage extends BasePage {
    private static final By USER_NAME = By.id("username")
    private static final By SECURITY_QUESTION = By.id("account.securityQuestion")
    private static final By SECURITY_QUESTION_TWO = By.id("account.securityQuestion2")
    private static final By SECURITY_ANSWER = By.id("account.securityAnswer")
    private static final By SECURITY_ANSWER_TWO = By.id("account.securityAnswer2")

    //Communication Preferences
    private static final By RAPID_REWARDS_THE_REPORT_CHECKBOX = By.id("customer.receiveRapidRewardsReport1")
    private static final By RAPID_REWARDS_EMAIL_UPDATE_CHECKBOX = By.id("customer.receiveRapidRewardsUpdate1")
    private static final By CLICK_N_SAVE_CHECKBOX = By.id("customer.receiveClickAndSaveEmail1")
    private static final By SOUTHWEST_IN_A_NUTSHELL_CHECKBOX = By.id("customer.receiveInaNutshellEmail1")

    private static final By ACCEPT_RULES_AND_REGULATIONS_CHECKBOX = By.id("accept-rules-and-regulations")

    private static final By SUBMIT_BUTTON = By.id("submitButton")
    Flow flow
    public AccountSetupPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/setup')
    }
    public String getRelativePath() {
        return "/account/setup"
    }
    def fillInUserNameAndSecurityQuestions() {
        fillIn(USER_NAME, flow.user.userName)
        selectFromDropDownRandom(SECURITY_QUESTION, "Choose Your Security Question")
        selectFromDropDownRandom(SECURITY_QUESTION_TWO, "Choose Your Security Question")
        fillIn(SECURITY_ANSWER, "Packers")
        fillIn(SECURITY_ANSWER_TWO, "Green")
    }

    def verifyRapidRewardsOptionsInCommunicationPreferences() {
        checkIfElementExists(RAPID_REWARDS_THE_REPORT_CHECKBOX).shouldBe true, "'Rapid Rewards The Report' checkbox is not being displayed."
        checkIfElementExists(RAPID_REWARDS_EMAIL_UPDATE_CHECKBOX).shouldBe true, "'Rapid Rewards Email Update' checkbox is not being displayed."
        checkIfElementExists(CLICK_N_SAVE_CHECKBOX).shouldBe true, "'Click 'N Save' checkbox is not being displayed."
        checkIfElementExists(SOUTHWEST_IN_A_NUTSHELL_CHECKBOX).shouldBe true, "'Southwest In A Nutshell' checkbox is not being displayed."
    }

    def verifyCustomerOptionsInCommunicationPreferences() {
        checkIfElementExists(RAPID_REWARDS_THE_REPORT_CHECKBOX).shouldBe false, "'Rapid Rewards The Report' checkbox is being displayed when it shouldn't."
        checkIfElementExists(RAPID_REWARDS_EMAIL_UPDATE_CHECKBOX).shouldBe false, "'Rapid Rewards Email Update' checkbox is being displayed when it shouldn't."
        checkIfElementExists(CLICK_N_SAVE_CHECKBOX).shouldBe true, "'Click 'N Save' checkbox is not being displayed."
        checkIfElementExists(SOUTHWEST_IN_A_NUTSHELL_CHECKBOX).shouldBe true, "'Southwest In A Nutshell' checkbox is not being displayed."
    }

    def verifyAcceptTheRulesAndRegulationsCheckbox() {
        checkIfElementExists(ACCEPT_RULES_AND_REGULATIONS_CHECKBOX).shouldBe true, "'Accept the Rules and Regulations' checkbox is not being displayed."
    }

    def verifyNoAcceptTheRulesAndRegulationsCheckbox() {
        checkIfElementExists(ACCEPT_RULES_AND_REGULATIONS_CHECKBOX).shouldBe false, "'Accept the Rules and Regulations' checkbox is being displayed when it shouldn't."
    }

    def selectAcceptRulesAndRegulations() {
        ensureCheckboxIsChecked(ACCEPT_RULES_AND_REGULATIONS_CHECKBOX)
        waitForElement(SUBMIT_BUTTON).click()
    }
}
