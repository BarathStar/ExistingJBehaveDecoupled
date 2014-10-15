package util

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.Cookie

class BrowserActions {

    WebDriverProvider driverProvider

    public void clearSession() {
        def driverOptions = driverProvider.get().manage()
        driverOptions.cookies.each { Cookie cookie ->
            if (cookie.expiry == null) {
                driverOptions.deleteCookie(cookie)
            }
        }
    }
}
