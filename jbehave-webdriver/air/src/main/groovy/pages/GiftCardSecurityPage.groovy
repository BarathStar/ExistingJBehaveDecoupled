package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement
import static org.junit.Assert.fail

import com.sun.org.apache.xpath.internal.FoundIndex

public class GiftCardSecurityPage extends BasePage {

    private static final String TITLE = "southwestgiftcard"

    public GiftCardSecurityPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return "/gift_cards/gift_card_faq.html#5"
    }

    def verifyTitleGiftCardSSecurityPage(){
        switchToWindow(TITLE,6500)
        getTitle().shouldBe TITLE , "The title should be '${TITLE}'"
    }

}
