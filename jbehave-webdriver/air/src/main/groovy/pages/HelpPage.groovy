package pages

import org.jbehave.web.selenium.WebDriverProvider

class HelpPage extends BasePage {

    public HelpPage(WebDriverProvider driverProvider) {
        super(driverProvider, "/html/customer-service/index.html")
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }
}
