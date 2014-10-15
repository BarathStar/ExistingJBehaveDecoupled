package pages;

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import state.Flow

public class SwaBizTravelAccountLoginPage extends BasePage {

    private static final String PATH = '/flight/swabiz-login'
    private static final By COMPANY_ID = By.id("companyId")
    private static final By ACCOUNT_USER_NAME = By.id("accountCredential")
    private static final By ACCOUNT_PASSWORD = By.id("accountPassword")
    private static final By SUBMIT_BUTTON = By.id("submit")
    private static final By NEED_HELP_LINK = By.id("swa_recoveryUserData_account")
    private static final By TRAVELER_LOGIN_SWABIZ_BUTTON = By.xpath("//a[href='/flight/swabiz-login']")
    private static final By TRAVELER_LOGIN_SWABIZ_LINK = By.linkText("Traveler Account Login")

    Data data
    Flow flow

    public SwaBizTravelAccountLoginPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH);
    }

    String getRelativePath() {
        return PATH
    }

    def open() {
        flow.isSwabiz = true
        get(Domains.swabizDomain)
        WaitForPageToLoad
    }

    def selectTravelAccountLogin() {
        waitForElement(TRAVELER_LOGIN_SWABIZ_LINK).click()
    }

    def enterCredentialsForTravelAccount() {
        flow.isLoggedIn = true
        waitForElement(COMPANY_ID).sendKeys(DELETE_EXISTING + data.getswaBizTravelAccountCompanyId())

        if(flow.hasGeneratedUser) {
            waitForElement(ACCOUNT_USER_NAME).sendKeys(DELETE_EXISTING + flow.rrUser.number)
            waitForElement(ACCOUNT_PASSWORD).sendKeys(DELETE_EXISTING + flow.rrUser.password)
        } else {
            waitForElement(ACCOUNT_USER_NAME).sendKeys(DELETE_EXISTING + data.getswaBizTravelAccountNumber())
            waitForElement(ACCOUNT_PASSWORD).sendKeys(DELETE_EXISTING + data.getswaBizTravelAccountPassword())
        }
        waitForElement(SUBMIT_BUTTON).click()
    }

    def enterCredentialsForTravelAccountWithPass(String password = null) {
        flow.isLoggedIn = true
        waitForElement(COMPANY_ID).sendKeys(DELETE_EXISTING + data.getswaBizTravelAccountCompanyId())

        if(flow.hasGeneratedUser) {
            waitForElement(ACCOUNT_USER_NAME).sendKeys(DELETE_EXISTING +flow.rrUser.number)
            waitForElement(ACCOUNT_PASSWORD).sendKeys(DELETE_EXISTING + password)
        } else {
            waitForElement(ACCOUNT_USER_NAME).sendKeys(DELETE_EXISTING + data.getswaBizTravelAccountNumber())
            waitForElement(ACCOUNT_PASSWORD).sendKeys(DELETE_EXISTING + password)
        }
        waitForElement(SUBMIT_BUTTON).click()
    }

    def clickNeedHelpLoggingIn() {
        waitForElement(NEED_HELP_LINK).click()
    }

    def onSwabizHomePage() {
        checkSomethingServed();
        try {
            waitForElement(COMPANY_ID)
        } catch (NoSuchElementException) {
            fail("Not on the home page, on: " + getCurrentUrl() + " instead")
        }
    }
}
