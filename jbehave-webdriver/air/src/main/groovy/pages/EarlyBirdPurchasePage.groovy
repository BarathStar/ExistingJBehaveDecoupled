package pages

import org.jbehave.web.selenium.WebDriverProvider
import pages.elements.CreditCardSubForm
import state.Flow
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import domain.Passenger

public class EarlyBirdPurchasePage extends BasePage {

    private static final By SUBMIT_BUTTON = By.id("visibleSubmitButton")
    private static final By EARLYBIRD_LINK = By.className("earlyBirdLink")
    private static final By CREDIT_CARD = By.id("storedFormOfPaymentName")

    CreditCardSubForm creditCardInfo
    Flow flow

    String getRelativePath() {
        return "/flight/early-bird-purchase.html"
    }

    def EarlyBirdPurchasePage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, "/flight/early-bird-purchase.html");
    }

    def fillCreditCardInfo() {
        flow.isEarlyBirdCheckInPurchase = true
        creditCardInfo.fillForm()
        clickSubmitButton()
    }

    def fillCreditCardUATPInfo(){
        creditCardInfo.fillCreditCardUATPInfo()
        clickSubmitButton()
    }

    def verifyCreditCard() {
        flow.isEarlyBirdCheckInPurchase = true
        getCreditCard().firstSelectedOption.text.shouldBeEqualTo CreditCardSubForm.CREDIT_CARD_NAME
        clickSubmitButton()
    }

    def Select getCreditCard() {
        new Select(waitForElement(CREDIT_CARD))
    }

    def clickSubmitButton() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def clickEarlyBirdLink () {
        waitForElement(EARLYBIRD_LINK).click()

    }
}
