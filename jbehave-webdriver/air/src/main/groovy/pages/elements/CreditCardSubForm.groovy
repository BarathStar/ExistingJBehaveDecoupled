package pages.elements

import com.swacorp.dotcom.webscenarios.air.CreditCard
import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.SortablePage
import state.CreditCardToUse
import state.Flow
import fixtures.air.ReservationSpecification
import util.CustomerInfoData
import com.swacorp.dotcom.webscenarios.air.CreditCardData

public class CreditCardSubForm extends SortablePage {

    private static final By CREDIT_CARD_RADIO_BUTTON = By.id("creditCard")
    private static final By CREDIT_CARD_NUMBER_1 = By.id("creditCardNumber1")
    private static final By CREDIT_CARD_NUMBER_2 = By.id("creditCardNumber2")
    private static final By CREDIT_CARD_NUMBER_3 = By.id("creditCardNumber3")
    private static final By CREDIT_CARD_NUMBER_4 = By.id("creditCardNumber4")
    private static final By SECURITY_CODE = By.id("securityCode")
    private static final By SWABIZ_FULL_CREDIT_CARD_NUMBER = By.id("creditCardNumber")
    private static final By ALTERNATE_IRN = By.id("creditCardNumber1")
    private static final By ADD_CREDIT_CARD_TO_ACCOUNT = By.id("addCreditCardToAccount")
    private static final By NEW_FORM_OF_PAYMENT_NAME = By.id("newFormOfPaymentName")
    public static final String CREDIT_CARD_NAME = "My New Credit Card"


    Data data
    Flow flow
    CreditCardToUse creditCardToUse
    CustomerInfoData customerInfoData

    public CreditCardSubForm(WebDriverProvider driverProvider) {
        super(driverProvider, SortablePage.CREDIT_CARD_INFO_ORDER)
    }

    String getRelativePath() {
        return ""
    }

    void fillForm() {
        CreditCard card
        if (isCreditCardFormFilled()) {
            return
        }

        if(flow.useSavedCreditCard) {
            return
        }

        //if travel fund applied and customer do not owe any money we do not have to fill cc
        if (shouldTravelFundApplied()){
            return
        }

        if(flow.useStandardCreditCard){
            card = (flow.userCreditCard)? data.getCreditCardByNumber(flow.userCreditCard) : data.getRandomCreditCardOnlyVisa()
        }else{
            card = creditCardToUse.isAcceptable() ? data.getRandomCreditCard() : data.getUnacceptableCreditCard()
        }

        flow.userCreditCard = card.getWholeNumber()
        if (!flow.isEarlyBirdCheckInPurchase) {
            waitForElement(CREDIT_CARD_RADIO_BUTTON).click()
        }
        selectCreditCardType(card)

        chooseInDropDownByValue("expirationMonth", card.getExpirationMonth())
        chooseInDropDownByValue("expirationYear", card.getExpirationYear())
        addSwabizAndSecurityData(card)
    }



    void fillCreditCardUATPInfo() {
        flow.isEarlyBirdCheckInPurchase = true
        fillForm(CreditCard.TYPE.UATP.toString())
    }

    void fillForm(String creditCardType, CreditCard creditCard = null) {

        CreditCard card = data.getStandardCreditCard()

        if(creditCardType.equals(CreditCard.TYPE.REJECTED.toString()) && (creditCard != null)) {
            card = creditCard
        }

        if(creditCardType.equals(CreditCard.TYPE.DINERS_CLUB.toString())){
            card = data.getDinersCreditCard()
        }

        if(creditCardType.equals(CreditCard.TYPE.UATP.toString())){
            card = data.getUATPCreditCard()
        }

        //if travel fund applied and customer do not owe any money we do not have to fill cc
        if (shouldTravelFundApplied()){
            return
        }

        flow.userCreditCard = card.getWholeNumber()
        if (!flow.isEarlyBirdCheckInPurchase) {
            waitForElement(CREDIT_CARD_RADIO_BUTTON).click()
        }
        selectCreditCardType(card)
        chooseInDropDownByValue("expirationMonth", card.getExpirationMonth())
        chooseInDropDownByValue("expirationYear", card.getExpirationYear())
        addSwabizAndSecurityData(card)
    }

    private addSwabizAndSecurityData(CreditCard card) {
        if (flow.isSwabiz) {
            fillIn(SWABIZ_FULL_CREDIT_CARD_NUMBER, card.getWholeNumber())
        }else {
            fillIn(CREDIT_CARD_NUMBER_1, card.getFirstFour(), false)
            fillIn(CREDIT_CARD_NUMBER_2, card.getSecondFour(), false)
            fillIn(CREDIT_CARD_NUMBER_3, card.getThirdFour(), false)
            if(!card.getType().equals(CreditCard.TYPE.DINERS_CLUB) && !card.getType().equals(CreditCard.TYPE.UATP)){
                fillIn(CREDIT_CARD_NUMBER_4, card.getFourthFour(), false)
            }
        }
        if(!card.getType().equals(CreditCard.TYPE.UATP)){
            WebElement securityCodeField = waitForElement(By.id("securityCode"), false, 1, 250)
            if(securityCodeField != null) {
                fillIn(SECURITY_CODE, card.getSecurityCode())
            }
        }
    }

    void fillFormBasedOn(ReservationSpecification specification) {
        fillForm()
    }

    void addCreditCardToAccount() {
        waitForElement(ADD_CREDIT_CARD_TO_ACCOUNT).click()
        fillIn(NEW_FORM_OF_PAYMENT_NAME, CREDIT_CARD_NAME)
    }

    void addCreditCardWithDefaultName() {
        clickCheckAddCreditCardToAcount()
        waitForElement(NEW_FORM_OF_PAYMENT_NAME).click()
        waitForElement(SECURITY_CODE).click()
        String cardNumber = waitForElement(CREDIT_CARD_NUMBER_4).getAttribute("value")
        String paymentName = waitForElement(NEW_FORM_OF_PAYMENT_NAME).getAttribute("value")
        println("On Thread: " + Thread.currentThread().getId() + ", newFormOfPaymentName: " + paymentName)
        paymentName.contains(cardNumber)
    }

    void clickCheckAddCreditCardToAcount() {
        waitForElement(ADD_CREDIT_CARD_TO_ACCOUNT).click()
    }

    private isCreditCardFormFilled() {
        if (waitForElement(CREDIT_CARD_NUMBER_1).getAttribute("value") != ""
            && waitForElement(CREDIT_CARD_NUMBER_2).getAttribute("value") != ""
            && waitForElement(CREDIT_CARD_NUMBER_3).getAttribute("value") != "") {
            return true
        }
        return false
    }

    public void selectCreditCardType(CreditCard card) {
        String creditCard = card.getType().toString()
        chooseInDropDownByValue("creditCardType", creditCard)
        if (customerInfoData.formOfPayment == null) customerInfoData.formOfPayment = findElement(By.xpath("//option[@value='${creditCard}']")).text
    }
}
