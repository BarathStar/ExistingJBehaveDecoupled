package pages.elements

import domain.RapidRewardsAccount
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.BasePage
import state.Flow
import util.DatasetManager
import util.PricePageData
import util.RRContactInformation
import util.PurchasePageData
import util.SelectFlightsPageData

class RapidRewardsAccountBar extends BasePage {

    private static final By SUBMIT_BUTTON = By.id("booking_widget_content_row_btn_search")
    private static final By NEED_HELP_LINK = By.id("swa_recoveryUserData_accountBar")
    private static final By ENROLL_NOW_LINK = By.linkText("Enroll Now!")
    private static final By NEED_HELP_LOGGIN_IN_ACCOUNT_BAR = By.id("swa_recoveryUserData_accountBar")
    private static final By NEED_HELP_LOGGIN_IN_FOOTER = By.id("swa_recoveryUserData_footer")
    private static final By MY_ACCOUNT = By.xpath("/html/body/div[2]/div[5]/div/div[3]/div[5]/div/span/a")
    private static final By LOGIN_USERNAME = By.cssSelector("input#accountNumber.global_account_bar_login_form_textinput")
    private static final By LOGIN_PASSWORD = By.cssSelector("input#accountPassword.global_account_bar_login_form_textinput")
    private static final By LOGIN_BUTTON = By.cssSelector("div.global_account_bar_login_form_right input#loginSubmitButton")
    private static final By TIER = By.className("tier")
    private static final By TRIP_POINTS = By.cssSelector(".rrPointsNotificationPurchase span")
    private static final By RAPID_REWARDS_ACOUNT_NUMBER = By.id("account_bar_rr_number")
    private static final By MEMBER_NAME = By.className("global_account_bar_login_form_name")
    private static final By VERIFY_ACCOUNT_LINK = By.id("accountSnapshotLink")
    private static final By HOME_PAGE_TIER = By.className("account_information")
    private static final By HOME_PAGE_NAME = By.className("gamma")
    private static final By LOGOUT_LINK = By.className("logoutLink")
    private static final By GREETING = By.className("global_account_bar_login_form_salutation")
    private static final By AVAILABLE_POINTS = By.className("availablePointsNumber")

    RRContactInformation rrAccountData
    PricePageData pricePageData
    Flow flow
    PurchasePageData purchasePageData
    SelectFlightsPageData selectFlightsPageData

    public RapidRewardsAccountBar(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def clickOnNeedHelpLogginIn(String needHelp) {
        switch (needHelp){
            case "accountBar":
                waitForElement(NEED_HELP_LOGGIN_IN_ACCOUNT_BAR).click()
                break
            case "footer":
                waitForElement(NEED_HELP_LOGGIN_IN_FOOTER).click()
                break
            default:
                fail("No Link Was Selected To Be Clicked")
        }
    }

    def clickOnMyAccountFooter() {
        waitForElement(MY_ACCOUNT).click()
    }
    def elementsToVerify() {
        String page = getPageSource()
        page.shouldNotContain 'right_column_account_login_form_field_rr_help_link'
        page.shouldNotContain 'Forgot Username/Password'
        page.shouldNotContain 'Forgot Username or Password?'
    }

    boolean isLoggedIn() {
        isElementPresent(By.className("logoutLink"))
    }

    def submit() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def needHelpLoggingIn() {
        waitForElement(NEED_HELP_LINK).click()
    }

    def onRapidRewardsGetHealthyHomePagePage() {
        super.verifyPage()
        try {
            this.needHelpLoggingInLink.getText().shouldContain 'Need help logging in?', 'Need help logging in? text NOT present on page'
        } catch (NoSuchElementException) {
            fail("Not on the Rapid Rewards Get Healthy Home page, on: " + getCurrentUrl() + " instead")
        }
    }

    def clickEnrollNow() {
        waitForElement(ENROLL_NOW_LINK).click()
    }

    def login(String fileName) {
        fillLoginFields(fileName)
        waitForElement(LOGIN_BUTTON).click()
    }

    private def fillLoginFields(String fileName) {
        RapidRewardsAccount rrAccount = DatasetManager.loadAccount(fileName)

        if (rrAccount.username != null) {
            waitForElement(LOGIN_USERNAME).sendKeys rrAccount.username
        }

        if (rrAccount.password != null) {
            waitForElement(LOGIN_PASSWORD).click()
            waitForElement(LOGIN_PASSWORD).sendKeys rrAccount.password
        }
    }

    def verifyRRGreeting() {
        waitForElement(GREETING).text.shouldBe "Hello,"
    }

    def verifyLogOutLink() {
        waitForElement(LOGOUT_LINK, false).shouldNotBe null, "The link was not present"
    }

    def verifyMyAccountLink() {
        waitForElement(VERIFY_ACCOUNT_LINK, false).shouldNotBe null, "'My account link was not displayed"
    }

    def verifyRRName() {
        WebElement nameInPage = waitForElement(MEMBER_NAME, false, 5)?:waitForElement(HOME_PAGE_NAME)
        nameInPage.text.shouldBe rrAccountData.firstName, "The first name of the passenger was not displayed"
    }

    def verifyRRacountNumber() {
        waitForElement(RAPID_REWARDS_ACOUNT_NUMBER).text.split("# " )[1].shouldBe rrAccountData.accountNumber, "The Rapid Rewards number was not displayed"
    }

    def verifyRRTripPoints() {
        waitForElement(TRIP_POINTS).text.split(" ")[0].replace(',','').toBigDecimal().shouldEqual pricePageData.rapidRewardPoints, "The points did not match the ones displayed in price page"
    }

    def verifyTier() {
        WebElement acountType = waitForElement(TIER, false, 5)?:waitForElements(HOME_PAGE_TIER)[0]
        acountType.text.shouldContain flow.userLoggedInRapidRewardsAccountType, "The account Type was not displayed"
    }
    
    def saveAvailablePoints() {
        purchasePageData.availablePoints = waitForElement(AVAILABLE_POINTS).text.replace(",","").toBigDecimal()
    }

    def verifyAvailablePts() {
        BigDecimal availablePts = waitForElement(AVAILABLE_POINTS).text.replace(",", "").toBigDecimal()
        availablePts.shouldBe purchasePageData.availablePoints - purchasePageData.totalPoints, "Available points in the confirmation page did not match the difference between initial available points and total points of the trip"
    }

    def verifyAvailablePointsAfterCancel() {
        BigDecimal availablePts = waitForElement(AVAILABLE_POINTS).text.replace(",", "").toBigDecimal()
        println("###### Available Points: ${availablePts}")
        println("###### Puchased Page Data  ${purchasePageData.availablePoints}")
        println("###### Select Flights Page: ${selectFlightsPageData.outboundFlightPoints}")
        availablePts.shouldBe purchasePageData.availablePoints + selectFlightsPageData.outboundFlightPoints, "Available points in the confirmation page did not match the sum between initial available points and total points of the cancelled trip"
    }

    def verifyAvailablePointsAfterChange() {
        BigDecimal availablePts = waitForElement(AVAILABLE_POINTS).text.replace(",", "").toBigDecimal()
        availablePts.shouldBe purchasePageData.availablePoints - selectFlightsPageData.outboundFlightPoints, "Available points in the confirmation page did not match the difference between initial available points and total points of the trip"
    }
}
