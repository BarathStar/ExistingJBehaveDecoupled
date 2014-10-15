/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.openqa.selenium.WebElement
import org.jbehave.web.selenium.WebDriverProvider
import com.swacorp.dotcom.webscenarios.air.RRUser
import org.openqa.selenium.By

public class AccountLogInPage extends BasePage {

    private static final By ACCOUNT_CREDENTIAL = By.id("accountCredential")
    private static final By ACCOUNT_PASSWORD = By.id("accountPassword")
    private static final By SUBMIT = By.id("submit")

    public AccountLogInPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/login')
    }

    String getRelativePath() {
        return "/flight/login"
    }

    def login( RRUser rrUser ) {
        login(rrUser.getRRNumber(), rrUser.getRRPassword())
    }

    def login(String credential, String password) {
        fillIn( ACCOUNT_CREDENTIAL, credential )
        fillIn( ACCOUNT_PASSWORD, password )
        waitForElement(SUBMIT).click()
    }
}