package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import static com.thoughtworks.selenium.SeleneseTestBase.fail
import org.openqa.selenium.Keys

public class FreedomShopPage extends BasePage {

    private static final String FREEDOM_SHOP_PAGE_URL = "swafreedomshop.com"

    public FreedomShopPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return FREEDOM_SHOP_PAGE_URL
    }

    def verifyOnSouthwestFreedomShopHomePage() {
        verifyCurrentPageLocation()
    }
}