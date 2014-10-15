package pages.elements

import org.openqa.selenium.By
import org.jbehave.web.selenium.WebDriverProvider
import domain.PurchaseGiftCardData
import pages.BasePage

class PurchaseGiftCardForm extends BasePage{

    private static final By CREDIT_CARD_TYPE = By.id("creditCardType")
    private static final By CREDIT_CARD_NUMBER_PART1 = By.id("creditCardNumberPart1")
    private static final By CREDIT_CARD_NUMBER_PART2 = By.id("creditCardNumberPart2")
    private static final By CREDIT_CARD_NUMBER_PART3 = By.id("creditCardNumberPart3")
    private static final By CREDIT_CARD_NUMBER_PART4 = By.id("creditCardNumberPart4")
    private static final By EXPIRATION_MONTH = By.id("expirationMonth")
    private static final By EXPIRATION_YEAR = By.id("expirationYear")
    private static final By SECURITY_CODE = By.id("securityCode")
    private static final By FIRST_NAME = By.id("creditCardFirstName")
    private static final By LAST_NAME = By.id("creditCardLastName")
    private static final By ADDRESS = By.id("creditCardAddress1")
    private static final By CITY = By.id("creditCardCity")
    private static final String STATE_DROP_DOWN = "creditCardState"
    private static final By ZIP_CODE_FIVE_ELEMENTS = By.id("creditCardZip1")
    private static final By ZIP_CODE_FOUR_ELEMENTS = By.id("creditCardZip2")
    private static final By AREA_CODE = By.id("areaCode")
    private static final By PREFIX = By.id("prefix")
    private static final By LINE_NUMBER = By.id("lineNumber")
    private static final By EMAIL = By.id("email")
    private static final By CONFIRM_EMAIL = By.id("confirmEmail")

    public PurchaseGiftCardForm(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    @Override
    String getRelativePath() {
        return null
    }

    void fillForm(PurchaseGiftCardData data) {
        chooseInDropDownByValue(CREDIT_CARD_TYPE, data.creditCard.type.toString())
        fillIn(CREDIT_CARD_NUMBER_PART1, data.creditCard.firstFour)
        fillIn(CREDIT_CARD_NUMBER_PART2, data.creditCard.secondFour)
        fillIn(CREDIT_CARD_NUMBER_PART3, data.creditCard.thirdFour)
        fillIn(CREDIT_CARD_NUMBER_PART4, data.creditCard.fourthFour)
        chooseInDropDownByValue(EXPIRATION_MONTH, data.creditCard.expirationMonth)
        chooseInDropDownByValue(EXPIRATION_YEAR, data.creditCard.expirationYear)
        fillIn(SECURITY_CODE, data.creditCard.securityCode)
        fillIn(FIRST_NAME, data.firstName)
        fillIn(LAST_NAME, data.lastName)
        fillIn(ADDRESS, data.address1)
        fillIn(CITY, data.city)
        chooseInDropDownByText(STATE_DROP_DOWN,data.state)
        fillIn(ZIP_CODE_FIVE_ELEMENTS, data.zipCode5)
        fillIn(ZIP_CODE_FOUR_ELEMENTS, data.zipCode4)
        fillIn(AREA_CODE, data.phoneAreaCode)
        fillIn(PREFIX, data.phonePrefix)
        fillIn(LINE_NUMBER, data.phoneLineNumber)
        fillIn(EMAIL,data.email)
        fillIn(CONFIRM_EMAIL, data.emailConfirmation)
    }
}
