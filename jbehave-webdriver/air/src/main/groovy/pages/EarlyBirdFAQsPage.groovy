package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

public class EarlyBirdFAQsPage extends BasePage {

    private static final String TITLE = "EarlyBird Check-In™"
    private static final String title = "Advertised Fare"
    private static final By GO_BUTTON = By.className("submit-button")
    private static final By EARLYBIRD_CHECKIN = By.id("topic_selector, 9")
    private static final By ADVERTISED_FARE = By.id("topic_selector, 1")
    private static final By TOPIC_SELECTOR = By.id("topic_selector")
    private static final By FAQ_TOPIC = By.className("swa_expandables_expandable_container")

    public EarlyBirdFAQsPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return "http://local.swacorp.com/html/customer-service/faqs.html?topic=earlybird_checkin"
    }

    void verifyFaqText(String faqText) {
        WebElement element = waitForElement(FAQ_TOPIC)
        element.getText().shouldContain faqText
    }

    def changeTopicToAdvertisedFare() {
        selectFromDropDownByIndex(TOPIC_SELECTOR, 1)
    }

    void clickGoButton() {
        waitForElement(GO_BUTTON).click()
    }
}