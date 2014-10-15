package pages

import static org.junit.Assert.fail

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

import pages.elements.GiftCardCheckBalanceForm
import steps.conditional.ToggleCaptcha
import domain.GiftCard
import com.thoughtworks.xstream.converters.basic.StringBuilderConverter

public class GiftCardCheckBalancePage extends BasePage {

    GiftCardCheckBalanceForm giftCardBalanceForm
    private static final By CHECK_YOUR_BALANCE_XPATH = By.xpath("//a[@title='Check Your Balance']")
    private static final By ADD_ANOTHER_GIFTCARD_ID = By.id("addAnotherGiftCard")
    private static final String NON_EXISTENT_MESSAJE = "We were unable to retrieve your travel funds from the information provided. Please try again or if you continue to have difficulties, please contact a Southwest Airlines Customer Service Representative at 1-800-I-FLY-SWA"
    private static final String INVALID_CARD_LENGTH_MESSAJE = "Invalid Card length."
    private static final String INVALID_SECURITY_CODE_LENGHT_MESSAJE = "Invalid Security Code length."
    private static final String EMPTY_CARD_MESSAJE = "The Card Number was not entered"
    private static final String EMPTY_SECURITY_CODE_MESSAJE = "The Security Code was not entered."

    private static final By ADD_MORE_CLASS = By.className("addMore")
    private static final By TAB_OPTION_SELECTED_CLASS = By.className("swa_tabs_menu_item_active_link")

    private static final String CHECK_ANOTHER_SOUTHWESTGIFCARD = "Check another southwestgifcard"
    private static final By ADD_ANOTHER_GIFT_CARD_CONTAINER = By.id("addAnotherGiftCardContainer")
    private static final String MAXIMUM_CARD_ALLOWED_TEXT = "Maximum four allowed"

    public GiftCardCheckBalancePage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return "gift-card/check-balance.html"
    }

    def verifyLocationAndBalanceTabsSelected(){
        verifyCurrentPageLocation()
        verifyDefaultTab()
    }

    private verifyDefaultTab(){
        checkIfElementExists(CHECK_YOUR_BALANCE_XPATH).shouldBe true , "The Check Your Balance tab option is not present"
        verifyTabOptionSelected()
    }

    def verifyCheckAnotherSouthwestGiftcardLink (){
        checkIfElementExists(ADD_ANOTHER_GIFTCARD_ID).shouldBe true , "The link Check another southwestgifcard is not present"
        waitForElement(ADD_MORE_CLASS).getText().shouldBe "Check another southwestgifcard (up to 4)", "The text (${text}) is not expected (Check another southwestgifcard (up to 4))"
    }

    private void verifyTabOptionSelected() {
        waitForElement(TAB_OPTION_SELECTED_CLASS).getText().shouldBe "Check Your Balance", "The select element on Tab (${select}) is not expected (Check your balance)"
    }

    def clickOnCheckNowButton(){
        giftCardBalanceForm.submit()
        checkSomethingServed()
    }

    def clickOnCheckYourBalance() {
        waitForElement(CHECK_YOUR_BALANCE_XPATH).click()
        verifyPage()
    }

    def fillANonExistentGiftCard(){
        giftCardBalanceForm.clearListGiftCards()
        giftCardBalanceForm.addGiftCard(GiftCard.getInexistentGiftCard())
        giftCardBalanceForm.fillGiftCards()
    }

    def fillAInvalidGiftCardNumberAndSecurityCode(){
        giftCardBalanceForm.clearListGiftCards()
        giftCardBalanceForm.addGiftCard(GiftCard.getInvalidGiftCard())
        giftCardBalanceForm.fillGiftCards()
    }

    def verifyMandatoryFormatFields(){
        def oopsMessajes =[INVALID_CARD_LENGTH_MESSAJE,INVALID_SECURITY_CODE_LENGHT_MESSAJE]
        waitForOops()
        verifyOopsMessages(oopsMessajes)
    }

    def verifyNonExistentGiftCard(){
        def oopsMessajes =[NON_EXISTENT_MESSAJE]
        waitForOops()
        verifyOopsMessages(oopsMessajes)
    }

    def clickOnAddAnotherGiftCard(int amountOfClicks){

        for(int i = 0; i < amountOfClicks; i++) {
            waitForElement(ADD_ANOTHER_GIFTCARD_ID).click()
        }
    }

    def verifyNotExistCheckAnotherSouthwestgiftcardLink(){
        verifyElementNotPresent(CHECK_ANOTHER_SOUTHWESTGIFCARD,ADD_ANOTHER_GIFTCARD_ID)
    }

    def verifyMaximunCardsAllowedText() {
        waitForElement(ADD_ANOTHER_GIFT_CARD_CONTAINER).getText().shouldBeEqualTo MAXIMUM_CARD_ALLOWED_TEXT,
        "Text espected: " + MAXIMUM_CARD_ALLOWED_TEXT
    }

    def fillGiftCardNumberAndSecurityCode(int amountOfGiftCards,
        boolean fillCardNumbers, boolean fillSecurityCodes,
        boolean fillWithValidLenght) {

        final String CARD_NUMBER = "cardNumber"
        final String SECURITY_CODE = "securityCode"

        giftCardBalanceForm.clearListGiftCards()
        for(int i = 0; i < amountOfGiftCards ; i++){
            giftCardBalanceForm.addGiftCard(GiftCard.getGiftCardInexistentOrInvalid(fillCardNumbers, fillSecurityCodes, fillWithValidLenght))
            clearText(waitForElement(By.id(CARD_NUMBER + (i+1))))
            clearText(waitForElement(By.id(SECURITY_CODE + (i+1))))
        }
        giftCardBalanceForm.fillGiftCards()
    }

    def verifyOopsMessagesCardNumbersNotEnteredAndSecurityCodeInvalid(){
        def oopsMessajes =[EMPTY_CARD_MESSAJE,INVALID_SECURITY_CODE_LENGHT_MESSAJE,
            EMPTY_CARD_MESSAJE,INVALID_SECURITY_CODE_LENGHT_MESSAJE,
            EMPTY_CARD_MESSAJE,INVALID_SECURITY_CODE_LENGHT_MESSAJE,
            EMPTY_CARD_MESSAJE,INVALID_SECURITY_CODE_LENGHT_MESSAJE]
        waitForOops()
        verifyOopsMessages(oopsMessajes)
    }

    def verifyOopsMessagesCardNumbersNotEntered() {
        def oopsMessajes =[EMPTY_CARD_MESSAJE, EMPTY_CARD_MESSAJE,
            EMPTY_CARD_MESSAJE, EMPTY_CARD_MESSAJE]
        waitForOops()
        verifyOopsMessages(oopsMessajes)
    }

    def verifyOopsMessagesCardNumbersInvalidSecurityCodeNotEntered(){
        def oopsMessajes =[INVALID_CARD_LENGTH_MESSAJE,EMPTY_SECURITY_CODE_MESSAJE,
            INVALID_CARD_LENGTH_MESSAJE,EMPTY_SECURITY_CODE_MESSAJE,
            INVALID_CARD_LENGTH_MESSAJE,EMPTY_SECURITY_CODE_MESSAJE,
            INVALID_CARD_LENGTH_MESSAJE,EMPTY_SECURITY_CODE_MESSAJE]
        waitForOops()
        verifyOopsMessages(oopsMessajes)
    }

    def verifyOopsMessagesSecurityCodeNotEntered(){
        def oopsMessajes =[EMPTY_SECURITY_CODE_MESSAJE, EMPTY_SECURITY_CODE_MESSAJE,
            EMPTY_SECURITY_CODE_MESSAJE, EMPTY_SECURITY_CODE_MESSAJE]
        waitForOops()
        verifyOopsMessages(oopsMessajes)
    }
}