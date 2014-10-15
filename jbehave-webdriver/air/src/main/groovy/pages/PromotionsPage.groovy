package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class PromotionsPage extends BasePage {

    private static final String PATH = "/account/rapidrewards/promotions"
    private static final By AWARDED_PROMOTIONS = By.id("awarded_promotions")
    private static final By ELIGIBLE_PROMOTIONS = By.id("eligible_promotions")
    private static final By ACTIVE_PROMOTIONS = By.id("active_promotions")
    private static final String PROMOTIONAL_CERTIFICATES_MESSAGE = "We are currently unable to retrieve Promotional Certificates. Please try again"
    private static final String PROMOTIONAL_MESSAGE = "We are currently unable to retrieve Promotions. Please try again"

    PromotionsPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }
    @Override
    String getRelativePath() {
        return PATH
    }

    def verifyMessageAboutPromotionalCertificatesIsNotPresent() {
        waitForElement(AWARDED_PROMOTIONS).getText().shouldNotContain PROMOTIONAL_CERTIFICATES_MESSAGE, "The message: ${PROMOTIONAL_CERTIFICATES_MESSAGE} should not be present"
    }

    def verifyMessageAboutPromotionsIsPresent() {
        waitForElement(ELIGIBLE_PROMOTIONS).getText().shouldContain PROMOTIONAL_MESSAGE, "The message: ${PROMOTIONAL_MESSAGE} should be present"
        waitForElement(ACTIVE_PROMOTIONS).getText().shouldContain PROMOTIONAL_MESSAGE, "The message: ${PROMOTIONAL_MESSAGE} should be present"
    }

    def verifyMessageAboutPromotionalCertificatesIsPresent() {
        waitForElement(AWARDED_PROMOTIONS).getText().shouldContain PROMOTIONAL_CERTIFICATES_MESSAGE, "The message: ${PROMOTIONAL_CERTIFICATES_MESSAGE} should not be present"
    }

    def verifyMessageAboutPromotionsIsNotPresent() {
        waitForElement(ELIGIBLE_PROMOTIONS).getText().shouldNotContain PROMOTIONAL_MESSAGE, "The message: ${PROMOTIONAL_MESSAGE} should not be present"
        waitForElement(ACTIVE_PROMOTIONS).getText().shouldNotContain PROMOTIONAL_MESSAGE, "The message: ${PROMOTIONAL_MESSAGE} should not be present"
    }
}
