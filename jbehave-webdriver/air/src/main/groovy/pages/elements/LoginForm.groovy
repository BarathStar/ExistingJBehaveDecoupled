package pages.elements

import pages.BasePage
import com.swacorp.dotcom.webscenarios.air.RRUser
import fixture.stubs.DynaStubsIntegration
import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import state.Flow
import org.openqa.selenium.By
import pages.MyAccountPage
import org.openqa.selenium.WebElement
import org.openqa.selenium.Keys
import steps.conditional.ToggleHomepage2

class LoginForm extends BasePage {

    Data data
    Flow flow
    MyAccountPage myAccountPage
    GlobalNavigationHeader globalNavigationHeader

    protected static final By ACCOUNT_NUMBER_FIELD = By.id("accountNumber")
    protected static final By ACCOUNT_PASSWORD_FIELD = By.id("accountPassword")
    private static final By  LOGIN_SUBMIT_BUTTON = By.id("loginSubmitButton")
    private static final By LOGIN_BUTTON_SELECTOR = By.cssSelector("input.swa-header--login-button")
    private static final By LOGIN_ERROR_MESSAGE = By.className("error")
    private static final By ACCOUNT_NUMBER_TEXT_BOX = By.id( "accountCredential" )
    private static final By SUBMIT_BUTTON = By.id( "submit" )

    public LoginForm(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/login')
    }

    protected LoginForm(WebDriverProvider driverProvider, String path){
        super(driverProvider, path)
    }

    RRUser loginRapidRewardsMember(String userType = "goodUser") {
        userType = (userType.equalsIgnoreCase("valid")) ? "goodUser" : userType
        assert [userType] ==
                ["noAPlusCredits", "noDebitRapidRewardsAccount", "awardsOnly", "creditsOnly", "pointsOnly", "AlistPreferred", "Alist", "invalid", "promoUser", "goodUser", "goodUserWithNoActivityInLast30Days", "accountExpiredUser", "promoCertUser"].grep(userType)

        RRUser user = data.getUser(userType)
        def number = null
        def password = user.getRRPassword()

        if (userType.equals("invalidUser")) {
            number = user.getRRAccountName()
        } else {
            number = user.getRRNumber()
            if (DynaStubsIntegration.useDynaStubs()) {
                new DynaStubsIntegration().createRRMember(user)
            }
        }
        flow.setUser(user)
        rapidRewardsMemberLoggedIn()
        return user
    }

    void attemptToLoginRapidRewardMember() {
        RRUser user = flow.getRrUser()?:flow.setUser(data.getUser("goodUser"))
        flow.isLoggedIn = true
        flow.isRapidRewards = true
        flow.userLoggedInFirstName = user.getRRFirstName()
        flow.userLoggedInLastName = user.getRRLastName()
        flow.userLoggedInGender = user.getRRGender()
        flow.userLoggedInRapidRewardsNumber = user.RRNumber
        flow.userLoggedInRapidRewardsAccountType = user.accountType

        if (ToggleHomepage2.isOn()
                && globalNavigationHeader.isHomePage()) {
            globalNavigationHeader.openLoginDropdown()
            globalNavigationHeader.fillInLoginInformation(user)
        } else {
            fillInLoginInformation(user.getRRNumber(), user.getRRPassword())
        }
    }

    void rapidRewardsMemberLoggedIn() {
        RRUser user = flow.getRrUser()?:flow.setUser(data.getUser("goodUser"))
        flow.isLoggedIn = true
        flow.isRapidRewards = true
        flow.userLoggedInFirstName = user.getRRFirstName()
        flow.userLoggedInLastName = user.getRRLastName()
        flow.userLoggedInGender = user.getRRGender()
        flow.userLoggedInRapidRewardsNumber = user.RRNumber
        flow.userLoggedInRapidRewardsAccountType = user.accountType

        if (ToggleHomepage2.isOn()
                && globalNavigationHeader.isHomePage()) {
            globalNavigationHeader.openLoginDropdown()
            globalNavigationHeader.logInAsRapidRewardsMember(user)
        } else {
            fillLoginFormAndSubmit(user.getRRNumber(), user.getRRPassword())
        }

		verifyPage()
    }

    void openRapidRewardsLoginPage() {
        super.open()
        checkSomethingServed()
        checkNoOops()
    }

    void fillInLoginInformation(rrNumber, rrPassword) {
        fillIn( ACCOUNT_NUMBER_FIELD, rrNumber)
        waitForElement(ACCOUNT_PASSWORD_FIELD).click()
        fillIn( ACCOUNT_PASSWORD_FIELD, rrPassword )
    }

    void fillInLoginInformationAndSubmit( rrNumber, rrPassword ) {
        fillIn( ACCOUNT_NUMBER_TEXT_BOX, rrNumber)
        waitForElement(ACCOUNT_PASSWORD_FIELD).click()
        fillIn( ACCOUNT_PASSWORD_FIELD, rrPassword )
        waitForElement(SUBMIT_BUTTON).click()
        super.checkNoOops()
    }

    private void fillLoginFormAndSubmit(number, password) {
        // Rational: Using fillIn to verify that the fields are filled; when an account is deleted, or does not
        // exist in a specific Seibel for a given hanger usage
        fillIn(ACCOUNT_NUMBER_FIELD, number)
        waitForElement(ACCOUNT_PASSWORD_FIELD).click()   // This is necessary to allow the password field to enter the data. - Remove and die; minimally you will make the test fail
        waitForElement(ACCOUNT_PASSWORD_FIELD).sendKeys(DELETE_EXISTING + password)
        waitForElement(LOGIN_SUBMIT_BUTTON).click()
        verifyElementNotPresent("Found error when trying to login to RapidRewards", LOGIN_ERROR_MESSAGE) //validating after logging in
    }

    def attemptToFillPartialAccountNumber(String accountNumber) {
        WebElement accountNumberField = waitForElement(ACCOUNT_NUMBER_FIELD)
        accountNumberField.sendKeys(DELETE_EXISTING + accountNumber.substring(0,5))
        sleep(50)
        accountNumberField.sendKeys(Keys.ARROW_DOWN + Keys.TAB)
    }

    @Override
    String getRelativePath() {
        return ""
    }

    void clickLoginButton() {
        if (ToggleHomepage2.isOn()
                && globalNavigationHeader.isHomePage()) {
            waitForElement(LOGIN_BUTTON_SELECTOR).click()
        } else {
            waitForElement(LOGIN_SUBMIT_BUTTON).click()
        }
    }
}