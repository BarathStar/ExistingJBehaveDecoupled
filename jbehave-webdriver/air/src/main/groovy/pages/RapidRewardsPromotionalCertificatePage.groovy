package pages;


import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

public class RapidRewardsPromotionalCertificatePage extends BasePage {

    private static final By BOOK_PROMOTION_BUTTON = By.xpath("//div[@id='tripSearchLinksContainer']//a[@id='planOrBookNewTripLink']")
    private static final By MY_RAPID_REWARDS_TAB = By.id("rapidrewards")
    private static final By PROMOTION_NAME_LINK = By.xpath("//span[@class='headerLink']/a")

    public RapidRewardsPromotionalCertificatePage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }


    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    void selectPromotionalCertificate() {
        waitForElement(MY_RAPID_REWARDS_TAB, true, 40).click()
        waitForElement(BOOK_PROMOTION_BUTTON, true, 40).click()
    }

    void verifyPromotionNameLink() {
        waitForElement(PROMOTION_NAME_LINK, true, 40)
    }

    void clickOnPromotionNameLink() {
        waitForElement(PROMOTION_NAME_LINK, true, 40).click()
    }


    void clickOnPromotionBookATripButton() {
        waitForElement(BOOK_PROMOTION_BUTTON, true, 40).click()
    }


}
