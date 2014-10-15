package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement

public class GiftCardTermAndConditionsPage extends BasePage {

    private static final String TITLE = "Southwest Airlines - southwestgiftcard® Terms and Conditions"

    public GiftCardTermAndConditionsPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return "html/customer-service/faqs.html?topic=southwestgiftcard"
    }

    def verifyTitleGiftCardTermAndConditionsPage(){
        switchToWindow(TITLE)
        getTitle().shouldBe TITLE , "The title should be '${TITLE}'"
    }
}
