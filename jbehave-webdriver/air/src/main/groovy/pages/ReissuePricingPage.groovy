package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.elements.PurchaseSubForm

class ReissuePricingPage extends BasePage {

    private static final String PATH = "account/rapidrewards/reissue-awards-payment"
    private static final By PURCHASE_REISSUE_AWARD_BUTTON = By.id("visibleSubmitButton")

    ReissuePricingPage(WebDriverProvider driverProvider){
        super(driverProvider,PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def clickOnPurchase(){
        waitForElement(PURCHASE_REISSUE_AWARD_BUTTON).click()
        checkSomethingServed()
    }

    void checkOopsCreditCardMandatoryFields() {
        List<String> oopsMessagesCreditCard = new ArrayList()
        oopsMessagesCreditCard.addAll(["The credit card type was not selected. (SW101033)\n",
            "The credit card number was not entered. (SW101031)\n",
            "The credit card expiration month was not selected. (SW101027)\n",
            "The credit card expiration year was not selected. (SW101028)\n",
            "The credit card security code was not entered. (SW101032)"])
        verifyOopsMessages(oopsMessagesCreditCard)
    }
}