package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class ConfirmCustomerPage extends BasePage {

    private static final String PATH = '/account/enroll/confirm-customer'
    private static final By FIRTS_NAME = By.id('accountCreatedHeader')
    private static final By ACCOUNT_NUMBER = By.id('accountNumber')
    private static final By MY_ACCOUNT_LINK = By.id('accountSnapshotLink')
    private static final By UPDATE_LINK = By.cssSelector("#personalize h5 a")
    private static final By TRAVEL_PREFERENCES_LINK = By.cssSelector("#book h5 a")
    private static final By SEARCH_LINK = By.cssSelector("#explore h5 a")
    public static int DEFAULT_WAIT_PAGE = 10000
    private static final By PAGE_TITLE = By.id("page_title")
    private static final String REVIEW_PAGE_TITLE = "Create a Rapid Rewards Account"

    public ConfirmCustomerPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    public String getRelativePath() {
        return PATH
    }

    def verifyFirstNameInTheCongratulationMessage(final String firstName){
        verifyTextPresent(firstName, FIRTS_NAME)
    }

    def verifyAccountNumberContainsOnlyNumbers(){
        waitForElement(ACCOUNT_NUMBER).text.isNumber().shouldBe true, 'The account number only can contain numbers!'
    }

    def clickOnMyAccountLink(){
        performClickAction(MY_ACCOUNT_LINK)
        sleep(DEFAULT_WAIT_PAGE)
    }

    def verifyUpdateLinkIsPresent() {
        isElementPresent(UPDATE_LINK).shouldBe true, "Update Link should be present"
    }

    def verifySearchLinkIsPresent() {
        isElementPresent(SEARCH_LINK).shouldBe true, "Search Link should be present"
    }

    def clickOnUpdateLink() {
        waitForElement(UPDATE_LINK).click()
    }

    boolean isCurrentPage() {
        isElementWithTextPresent(PAGE_TITLE, REVIEW_PAGE_TITLE)
    }

    def verifyOnCurrentPage() {
        isCurrentPage().shouldBe true,"You should be on Customer Confirmation Page"
    }

}
