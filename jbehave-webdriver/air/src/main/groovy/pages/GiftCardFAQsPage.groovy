package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement
import static org.junit.Assert.fail

public class GiftCardFAQsPage extends BasePage {

    private static final String TITLE = "southwestgiftcard"

    public GiftCardFAQsPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return "html/customer-service/faqs.html?topic=southwestgiftcard"
    }

    def verifyTitleGiftCardFAQsPage(){
        switchToWindow(TITLE,6500)
        getTitle().shouldBe TITLE , "The title should be '${TITLE}'"
    }
}
