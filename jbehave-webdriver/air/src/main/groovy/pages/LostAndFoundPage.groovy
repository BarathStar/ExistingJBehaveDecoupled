package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import static com.thoughtworks.selenium.SeleneseTestBase.fail
import org.openqa.selenium.Keys

public class LostAndFoundPage extends BasePage {

    private static final String LOST_AND_FOUND_PAGE_URL = "live.lostandfound.aero"


    public LostAndFoundPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return LOST_AND_FOUND_PAGE_URL
    }

    def verifyOnLostAndFoundPage() {
        verifyCurrentPageLocation()
    }
}