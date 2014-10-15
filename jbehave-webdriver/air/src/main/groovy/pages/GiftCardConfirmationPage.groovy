package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class GiftCardConfirmationPage extends BasePage{

    private static final String PATH = "gift-card/purchase-confirmation.html"
    private static final By SUCCESSFULLY_PURCHASED_MESSAGE = By.cssSelector("p")
    private static final By GIFT_CARD_CONFIRMATION_HEADER = By.id("giftCards_confirmation_billing_header")
    private static final By GIFT_CARD_BILLING_HEADER_1 = By.cssSelector("#giftCards_confirmation_billing_content_table th:nth-child(1)")
    private static final By GIFT_CARD_BILLING_HEADER_2 = By.cssSelector("#giftCards_confirmation_billing_content_table th:nth-child(2)")
    private static final By GIFT_CARD_BILLING_HEADER_3 = By.cssSelector("#giftCards_confirmation_billing_content_table th:nth-child(3)")
    private static final By GIFT_CARD_BILLING_HEADER_4 = By.cssSelector("#giftCards_confirmation_billing_content_table th:nth-child(4)")
    private static final By GIFT_CARD_BILLING_HEADER_5 = By.cssSelector("#giftCards_confirmation_billing_content_table th:nth-child(5)")
    private static final By GIFT_CARD_ORDER_MESSAGE = By.id("giftCards_verify_info_header")
    private static final By GIFT_CARD_VERIFY_TABLE_1 = By.cssSelector("#giftCards_verify_content_table th:nth-child(1)")
    private static final By GIFT_CARD_VERIFY_TABLE_2 = By.cssSelector("#giftCards_verify_content_table th:nth-child(2)")
    private static final By GIFT_CARD_VERIFY_TABLE_3 = By.cssSelector("#giftCards_verify_content_table th:nth-child(3)")
    private static final By GIFT_CARD_VERIFY_TABLE_4 = By.cssSelector("#giftCards_verify_content_table th:nth-child(4)")

    @Override
    String getRelativePath() {
        return PATH
    }

    public GiftCardConfirmationPage(WebDriverProvider driverProvider){
        super(driverProvider)
    }

    def verifyPurchaseSuccessfullyFinished() {
        waitForElement(SUCCESSFULLY_PURCHASED_MESSAGE).getText().shouldContain "has successfully been processed", "The successfully purchased message differed from expected. "
        waitForElement(GIFT_CARD_CONFIRMATION_HEADER).getText().shouldContain "BILLING", "The confirmation billing message differed from expected. "
        waitForElement(GIFT_CARD_BILLING_HEADER_1).getText().shouldContain "Credit Card Holder Name", "The first header in the confirmation billing table differed from expected. "
        waitForElement(GIFT_CARD_BILLING_HEADER_2).getText().shouldContain "Form of Payment", "The second header in the confirmation billing table differed from expected. "
        waitForElement(GIFT_CARD_BILLING_HEADER_3).getText().shouldContain "Billing Address", "The third header in the confirmation billing table differed from expected. "
        waitForElement(GIFT_CARD_BILLING_HEADER_4).getText().shouldContain "E-mail Address", "The fourth header in the confirmation billing table differed from expected. "
        waitForElement(GIFT_CARD_BILLING_HEADER_5).getText().shouldContain "Amount", "The fourth header in the confirmation billing table differed from expected. "
        waitForElement(GIFT_CARD_ORDER_MESSAGE).getText().shouldContain "YOUR GIFTCARD", "The order confirmation mesage differed from expected. "
        waitForElement(GIFT_CARD_VERIFY_TABLE_1).getText().shouldContain "Shipping", "The first header in the verify content table differed from expected. "
        waitForElement(GIFT_CARD_VERIFY_TABLE_2).getText().shouldContain "Recipient Address", "The first header in the verify content table differed from expected. "
        waitForElement(GIFT_CARD_VERIFY_TABLE_3).getText().shouldContain "QTY", "The first header in the verify content table differed from expected. "
        waitForElement(GIFT_CARD_VERIFY_TABLE_4).getText().shouldContain "Amount", "The first header in the verify content table differed from expected. "
    }
}
