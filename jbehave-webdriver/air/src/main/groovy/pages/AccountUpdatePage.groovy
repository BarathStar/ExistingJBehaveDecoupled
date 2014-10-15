package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.Flow

class AccountUpdatePage extends BasePage {

    private static final By SECURITY_QUESTION = By.id("account.securityQuestion")
    private static final By SECURITY_QUESTION_TWO = By.id("account.securityQuestion2")
    private static final By SECURITY_ANSWER = By.id("account.securityAnswer")
    private static final By SECURITY_ANSWER_TWO = By.id("account.securityAnswer2")
    private static final By SUBMIT_BUTTON = By.id("submitButton")

    public AccountUpdatePage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/update')
    }

    public String getRelativePath() {
        return "/account/update"
    }

    def fillInSecurityQuestions() {
        selectFromDropDownRandom(SECURITY_QUESTION, "Choose Your Security Question")
        selectFromDropDownRandom(SECURITY_QUESTION_TWO, "Choose Your Security Question")
        fillIn(SECURITY_ANSWER, "Packers")
        fillIn(SECURITY_ANSWER_TWO, "Green")
        waitForElement(SUBMIT_BUTTON).click()
    }
}
