package pages.elements

import static org.junit.Assert.fail
import org.jbehave.web.selenium.WebDriverProvider
import org.junit.Assert;

import pages.BasePage
import domain.GiftCard
import org.openqa.selenium.By

class GiftCardCheckBalanceForm extends BasePage {

    private static final String CHECK_NOW_BUTTON_ID ="checkGiftCardsForm_submitButton"

    private List<GiftCard> giftCardsList = new ArrayList<GiftCard> ()
    private int cantGiftcard = 1

    void addGiftCard (final GiftCard giftCard){
        this.giftCardsList.add(giftCard)
    }

    public GiftCardCheckBalanceForm(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return "gift-card/check-balance.html"
    }

    void fillGiftCards(){
        int index = 1
        for(GiftCard gc in giftCardsList){
            fillInGiftCard(gc,index)
            index++
        }
    }

    void clearListGiftCards(){
        this.giftCardsList.clear()
    }

    private void fillInGiftCard(GiftCard giftCard , int index) {
        giftCard.shouldNotBe null , "The GiftCard must not be null"
        fillIn(By.id("cardNumber" + index), giftCard.getNumber())
        fillIn(By.id("securityCode" + index), giftCard.getSecurityCode())
    }

    def submit(String buttonId = CHECK_NOW_BUTTON_ID) {
        waitForElement(By.id(buttonId)).click()
    }
}
