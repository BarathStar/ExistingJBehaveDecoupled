package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import com.swacorp.dotcom.webscenarios.air.CreditCard
import com.swacorp.dotcom.webscenarios.air.Data
import state.Flow

class EditPaymentInfoPage extends BasePage {

    private static final By CREDIT_CARD_DESCRIPTION = By.id("paymentInfos.list0.description")
    private static final By CREDIT_CARD_NUMBER = By.id("paymentInfos.list0.number")
    private static final String CREDIT_CARD_TYPE = "paymentInfos.list0.type"
    private static final String CREDIT_CARD_MONTH = "paymentInfos.list0.expirationMonth"
    private static final String CREDIT_CARD_YEAR = "paymentInfos.list0.expirationYear"
    private static final By CREDIT_CARD_FIRST_NAME = By.id("paymentInfos.list0.cardholderFirstName")
    private static final By CREDIT_CARD_LAST_NAME = By.id("paymentInfos.list0.cardholderLastName")
    private static final By CANCEL_LINK = By.id("cancel")
    private static final By SUBMIT_BUTTON = By.id("submitForm")
    private static final By ADD_ANOTHER_LINK = By.className("add_another_link")
    private static final By ADD_EDIT_CREDIT_CARD = By.className("submitButtonBlank")
    private static final By MAKE_PRIMARY = By.className("makePrimaryContact")
    private static final By CREDIT_CARD_FORM_CONTENT = By.className("formContent");
    private static final By CREDIT_CARD_BILLING_ADDRESS_LINE_ONE = By.id("billingAddress0")
    private static final By CREDIT_CARD_BILLING_ADDRESS_CITY = By.id("paymentInfos.list0.address.city")
    private static final By CREDIT_CARD_BILLING_ADDRESS_ZIP_CODE = By.id("paymentInfos.list0.address.zipOrPostalCode")
    private static final String CREDIT_CARD_BILLING_ADDRESS_STATE = "paymentInfos.list0.address.state"

    Data data;
    Flow flow

    EditPaymentInfoPage(WebDriverProvider driverProvider) {
        super(driverProvider, "account/info/edit-payment-info")
    }

    @Override
    String getRelativePath() {
        return "account/info/edit-payment-info"
    }

    def fillCreditCardDescription(String description) {
        fillIn(CREDIT_CARD_DESCRIPTION, description)
    }

    def fillCreditCardType(String value) {
        chooseInDropDownByValue(CREDIT_CARD_TYPE, value)
    }

    def fillCreditCardExpirationMonth(String month) {
        chooseInDropDownByValue(CREDIT_CARD_MONTH, month)
    }

    def fillCreditCardExpirationYear(String year) {
        chooseInDropDownByValue(CREDIT_CARD_YEAR, year)
    }

    def fillCreditCardNumber(String number) {
        fillIn(CREDIT_CARD_NUMBER, number)
    }

    def fillCreditCardFirstName(String firstName) {
        fillIn(CREDIT_CARD_FIRST_NAME, firstName)
    }

    def fillCreditCardLastName(String lastName) {
        fillIn(CREDIT_CARD_LAST_NAME, lastName)
    }

    def fillCreditCardBillingAddressLineOne(String address) {
        fillIn(CREDIT_CARD_BILLING_ADDRESS_LINE_ONE, address)
    }

    def fillCreditCardBillingAddressCity(String city) {
        fillIn(CREDIT_CARD_BILLING_ADDRESS_CITY, city)
    }

    def fillCreditCardBillingAddressZipCode(String zipCode) {
        fillIn(CREDIT_CARD_BILLING_ADDRESS_ZIP_CODE, zipCode)
    }

    def fillCreditCardBillingAddressState(String state) {
        chooseInDropDownByValue(CREDIT_CARD_BILLING_ADDRESS_STATE, state)
    }

    def clickOnCancelLink() {
        waitForElement(CANCEL_LINK).click()
    }

    def clickOnSubmitButton() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def verifyCancelLinkIsPresent() {
        isElementPresent(CANCEL_LINK).shouldBe true, "The Cancel link should be present"
    }

    def verifySubmitButtonIsPresent() {
        isElementPresent(SUBMIT_BUTTON).shouldBe true, "The Submit button should be present"
    }

    def doubleClickOnSubmitButton() {
        doubleClick(SUBMIT_BUTTON)
    }

    def clickOnAddAnotherCreditCard() {
        waitForElement(ADD_ANOTHER_LINK).click()
    }

    def clickOnAddEditCreditCardLink() {
        waitForElement(ADD_EDIT_CREDIT_CARD).click()
    }

    def fillACreditCardInfoAndMakePrimary(numberOfCreditCard = 0) {

        WebElement creditCardDescription = waitForElement(By.id("paymentInfos.list${numberOfCreditCard}.description"))
        WebElement creditCardNumber = waitForElement(By.id("paymentInfos.list${numberOfCreditCard}.number"))
        WebElement creditCardFirstName = waitForElement(By.id("paymentInfos.list${numberOfCreditCard}.cardholderFirstName"))
        WebElement creditCardLastName = waitForElement(By.id("paymentInfos.list${numberOfCreditCard}.cardholderLastName"))
        WebElement billingAddressLineOne = waitForElement(By.id("billingAddress${numberOfCreditCard}"))
        WebElement billingAddressCity = waitForElement(By.id("paymentInfos.list${numberOfCreditCard}.address.city"))
        WebElement billingAddressZipCode = waitForElement(By.id("paymentInfos.list${numberOfCreditCard}.address.zipOrPostalCode"))

        CreditCard card = data.getRandomCreditCard()
        flow.setCreditCardSecurityCode("CreditCard${numberOfCreditCard}", card.securityCode)

        creditCardDescription.sendKeys DELETE_EXISTING + "CreditCard${numberOfCreditCard}"
        chooseInDropDownByValue("paymentInfos.list${numberOfCreditCard}.type", card.getType().toString())
        creditCardNumber.sendKeys DELETE_EXISTING + card.getWholeNumber()
        chooseInDropDownByValue("paymentInfos.list${numberOfCreditCard}.expirationMonth", card.getExpirationMonth())
        chooseInDropDownByValue("paymentInfos.list${numberOfCreditCard}.expirationYear", card.getExpirationYear())
        creditCardFirstName.sendKeys DELETE_EXISTING + card.getFirstName()
        creditCardLastName.sendKeys DELETE_EXISTING + card.getLastName()

        billingAddressLineOne.sendKeys DELETE_EXISTING + "2702 Love Field Drive"
        billingAddressCity.sendKeys DELETE_EXISTING + "Dallas"
        chooseInDropDownByValue("paymentInfos.list${numberOfCreditCard}.address.state", "TX")
        billingAddressZipCode.sendKeys DELETE_EXISTING + "75235"

        waitForElement(MAKE_PRIMARY).click()
    }

    def verifyEditPaymentInfoPage() {
        checkSomethingServed()
        verifyCurrentPageLocation()
    }

    def verifyNoStoredCreditCard() {
        List<WebElement> creditCards = waitForElement(CREDIT_CARD_FORM_CONTENT).findElements(By.className("numberedHeader"))
        (creditCards.size() == 1).shouldBe  true, "Should have only one editable form"
        waitForElement(By.id("paymentInfos.list0.number")).getAttribute("value").shouldBeEmpty "Should not have any stored credit card"
    }
}
