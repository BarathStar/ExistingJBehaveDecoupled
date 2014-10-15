package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

public class PointsDotcomBuyGiftTransferPage extends BasePage {
    private final static By POINTS_DOTCOM_IFRAME = By.id("points-frame")
    private final static By POINTS_DOTCOM_BUY_LINK = By.id("buy-link")
    private final static By POINTS_DOTCOM_TRANSFER_LINK = By.id("transfer-link")
    private final static By POINTS_DOTCOM_GIFT_LINK = By.id("gift-link")
    private final static By POINTS_DOTCOM_CLAIM_LINK = By.id("claim-link")
    private final static By POINTS_DOTCOM_FORM_SSO_SOURCE_FIELD = By.id("points-sso-source")
    private final static By POINTS_DOTCOM_FORM_SSO_PRODUCT_FIELD = By.id("points-sso-product")
    private final static By POINTS_DOTCOM_FORM_SSO_DATA_FIELD = By.id("points-sso-data")
    public static final int KEY_VALUE_PAIR_SHOULD_BE_LONG_ENOUGH_TO_BE_AN_ENCRYPTED_STRING_WITH_SOME_DATA = 18
    public static final String SSO_SOURCE = "ssoSource"
    public static final String SSO_PRODUCT = "ssoProduct"
    public static final By ENROLL_MODAL = By.id("enrollModal")

    public PointsDotcomBuyGiftTransferPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    def verifyPointsDotcomIframe(String product) {
        isElementDisplayed(POINTS_DOTCOM_IFRAME).shouldBe true, "Points.com iframe is not displayed"

        String ssoSource = waitForElement(POINTS_DOTCOM_FORM_SSO_SOURCE_FIELD).getAttribute("value")
        String ssoProduct = waitForElement(POINTS_DOTCOM_FORM_SSO_PRODUCT_FIELD).getAttribute("value")
        String ssoData = waitForElement(POINTS_DOTCOM_FORM_SSO_DATA_FIELD).getAttribute("value")

        ssoSource.shouldBeEqualTo("southwest", "ssoSource should be 'SWA' but is " + ssoSource)
        ssoProduct.shouldBeEqualTo(product.toLowerCase(), "ssoProduct should be " + product.toLowerCase() + " but is " + ssoProduct)
        ssoData.length().shouldBeGreaterThan(KEY_VALUE_PAIR_SHOULD_BE_LONG_ENOUGH_TO_BE_AN_ENCRYPTED_STRING_WITH_SOME_DATA, "ssoData should be longer than " + KEY_VALUE_PAIR_SHOULD_BE_LONG_ENOUGH_TO_BE_AN_ENCRYPTED_STRING_WITH_SOME_DATA + " characters")
    }

    private def Map getURIQueryMap(String uri) {
        return new URI(uri).query.split("&")*.split("=").collectEntries {
            [(it[0]):(it[1])]
        }
    }

    private def verifyProduct(String srcAttribute, String ssoProductKey, String expectedString) {
        String ssoProductKeyValuePair = parseParam(srcAttribute, ssoProductKey)
        ssoProductKeyValuePair.equals(expectedString).shouldBe true, "Should be \"" + expectedString + "\" but is \"" + ssoProductKeyValuePair + "\""
    }

    private def verifyUpgradeToRapidRewardsModal() {
        WebElement upgradeToRapidRewardsModal = waitForElement(ENROLL_MODAL, false)
        upgradeToRapidRewardsModal.shouldNotBe null, "Upgrade to Rapid Rewards modal should be displayed."
        boolean isModalDisplayed = upgradeToRapidRewardsModal.isDisplayed()
        isModalDisplayed.shouldBe true, "Upgrade to Rapid Rewards modal should be displayed."
    }

    private def parseParam(String url, String param) {
        int endingIndex = url.indexOf("&", url.indexOf(param))
        if (endingIndex > 0 ) {
            return url.substring(url.indexOf(param), url.indexOf("&", url.indexOf(param)))
        } else {
            return url.substring(url.indexOf(param))
        }
    }

    def clickBuyButton() {
        waitForElement(POINTS_DOTCOM_BUY_LINK).click()
    }

    def clickTransferButton() {
        waitForElement(POINTS_DOTCOM_TRANSFER_LINK).click()
    }

    def clickGiftButton() {
        waitForElement(POINTS_DOTCOM_GIFT_LINK).click()
    }

    def clickClaimButton() {
        waitForElement(POINTS_DOTCOM_CLAIM_LINK).click()
    }
}


