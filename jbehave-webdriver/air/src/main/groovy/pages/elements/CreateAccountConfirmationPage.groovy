package pages.elements

import pages.BasePage
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.Flow
import org.apache.commons.lang.WordUtils

class CreateAccountConfirmationPage extends BasePage {

    Flow flow
    private static final By COMPLETE_YOUR_PREFERENCES_BUTTON = By.className("submitButtonLargest")
    private static final By ACCOUNT_BAR_RR_NUMBER = By.id("account_bar_rr_number")
    private static final By EXPLORE_LINK = By.cssSelector("#destination h5 a")
    private static final By BOOK_LINK = By.cssSelector("#book h5 a")
    private static final By RR_CARD_NAME = By.cssSelector(".cardContent div:nth-child(2)")
    private static final By RR_CARD_NUMBER = By.cssSelector(".cardContent div:nth-child(1)")
    private static final By ACCOUNT_NUMBER = By.id("accountNumber")
    public static int DEFAULT_WAIT_PAGE = 5000

    public CreateAccountConfirmationPage(WebDriverProvider driverProvider) {
        super(driverProvider, 'account/enroll/confirm-member')
    }

    String getRelativePath() {
        return "account/enroll/confirm-member"
    }

    def verifyIsExploreLinkPresent() {
        verifyElementPresent("Explore Link", EXPLORE_LINK)
    }

    def verifyIsBookLinkPresent() {
        verifyElementPresent("Book Link", BOOK_LINK)
    }

    def verifyUserNamesOnRRCard() {
        String cardText = waitForElement(RR_CARD_NAME).text
        WordUtils.capitalize(cardText).shouldContain WordUtils.capitalize(flow.getRrUser().getRRFirstName())
        WordUtils.capitalize(cardText).shouldContain WordUtils.capitalize(flow.getRrUser().getRRLastName())
    }

    def verifyRRAccountNumberFormat() {
        String accountNumberFormat = "^R.R. # \\d*"
        getRRAccountFromAccountBar().matches(accountNumberFormat).shouldBe true, "The format of the accoun number should be 'R.R. # accountNumber'"
        getRRAccountNumberFromAccountBar().isNumber().shouldBe true, "The account number only can contain numbers!"
    }

    def verifyRRAccountNumberOnRRCard() {
        getRRCardNumber().isNumber().shouldBe true, "The account number only can contain numbers!"
    }

    def verifyRRAccountNumber() {
        getAccountNumber().isNumber().shouldBe true, "The account number only can contain numbers!"
    }

    def validateRRAccountNumbers() {
        getRRCardNumber().shouldBeEqualTo(getAccountNumber())
        getAccountNumber().shouldBeEqualTo(getRRAccountNumberFromAccountBar())
    }

    def clickOnCompleteYourPreferencesButton() {
        performClickAction(COMPLETE_YOUR_PREFERENCES_BUTTON)
        waitForUrlToChangeOrOops(getRelativePath(), DEFAULT_WAIT_PAGE)
    }

    private String getRRAccountNumberFromAccountBar() {
        return getRRAccountFromAccountBar().minus("R.R. # ").trim()
    }

    private String getRRAccountFromAccountBar() {
        return waitForElement(ACCOUNT_BAR_RR_NUMBER).text
    }

    private String getAccountNumber() {
        return waitForElement(ACCOUNT_NUMBER).text.trim()
    }

    private String getRRCardNumber() {
        return waitForElement(RR_CARD_NUMBER).text.trim()
    }
}
