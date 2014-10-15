package pages

import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.Flow;

class SwabizSiteMapPage extends BasePage {

    private static  String PATH = '/html/customer-service/sitemap.html?int=SWABIZ_SITEMAP'
    private static  By BUSINESS_SELECT_LINK = By.linkText("Business Select")
    Flow flow

    SwabizSiteMapPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    @Override
    def open() {
        flow.isSwabiz = true
        get(Domains.swabizDomain + PATH)
    }

    void clickOnBusinessSelectLink() {
        waitForElement(BUSINESS_SELECT_LINK).click();
    }
}
