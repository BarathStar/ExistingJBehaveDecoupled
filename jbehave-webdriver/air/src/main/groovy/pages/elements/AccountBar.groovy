package pages.elements

import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import pages.BasePage
import pages.elements.drawers.ManageTravelDrawer
import state.Flow
import steps.conditional.ToggleHomepage2
import util.BrowserActions

import static org.junit.Assert.fail
import static pages.elements.drawers.ManageTravelDrawer.BY_MY_TRAVEL_ID

class AccountBar extends BasePage {

    BrowserActions browserActions
    Data data
    Flow flow

    private static final By ACCOUNT_LOGIN_CONTAINER = By.id("sw_main")
    private static final By PROMO_AWARD_DETAIL = By.className("promoAwardDetail")
    private static final By PROMOTIONAL_AWARD_VIEW_NOW_BUTTON = By.cssSelector(".promoCertButtonContainer a")
    private static final By GLOBAL_ACCOUNT_BAR_LOGIN_FORM_SALUTATION = By.className("global_account_bar_login_form_salutation")
    private static final By RAPID_REWARDS_ACCOUNT_LOGGED_IN = By.id("account_bar_rr_number")
    private static final By ACCOUNT_NUMBER = By.id("accountNumber")
    private static final By ACCOUNT_PASSWORD = By.id("accountPassword")
    private static final By LOGIN_SUBMIT_BUTTON = By.id("loginSubmitButton")
    private static final By LOGOUT_LINK = By.className("logoutLink")
    private static final By NEED_HELP_LINK = By.id("swa_recoveryUserData_accountBar")
    private static final By USER_PREFERRED_NAME = By.className("global_account_bar_login_form_name")
    private static final By WHERE_ARE_MY_AWARDS = By.className("right_nav_awards_small_image")
    private static final By RAPID_REWARDS_TOGGLE = By.id("right_nav_rapidrewards_link")
    private static final By ACCOUNT_HELD_WARNING = By.id("accountHeld")
    private static final By DREAM_TRIPS_SECTION = By.id("dreamTrips")
    private static final By TRACK_YOUR_TRIP_LINK = By.id("rapid_rewards_no_dream_trips")
    private static final By MINI_HEADERS = By.cssSelector("#myRapidRewardsContent span.miniHeader")
    private static final By CHECK_DATES_BUTTON = By.cssSelector("#dreamTrips input.largeSubmitButtonWhite")
    private static final By DREAM_TRIP_PROGRESS_BAR = By.cssSelector("#dreamTrips div.percentBarBg div.percentBar")
    private static final By DREAM_TRIP_PROGRESS_BAR_FULL = By.cssSelector("#dreamTrips div.percentBarBg div.percentBarFull")
    private static final By DREAM_TRIP_PERCENT_VALUE = By.cssSelector("#dreamTrips span.percentValue")
    private static final By DREAM_TRIP_NAME = By.id("rapid_rewards_dream_trip_id")
    private static final By RAPID_REWARDS_CREDIT_CARD_LEARN_MORE = By.id("chaseRrVisaApplyOnline")
    private static final By RAPID_REWARDS_CREDIT_CARD = By.xpath("//div[@class='miniHeader']")
    private static final By MORE_REWARDS_LINK = By.xpath("//input[@id='insetButton']")
    private static final String ACCOUNT_HELD_MESSAGE = "Your account is temporarily disabled"
    private static final String DREAM_TRIPS_MESSAGE = "Pick a destination and track your progress towards earning a reward flight."
    private static final String DREAM_TRIPS_SECTION_TITLE = "REWARD FLIGHT TRACKER"
    private static final String NON_DIGIT = /\D/
    private static final By COMPANION_BUTTON = By.cssSelector(".insetButton_green.inset_button_green_medium")
    private static final String RAPID_REWARDS_CREDIT_CARD_TITLE = "RAPID REWARDS\nCREDIT CARD"
    private static final String MORE_REWARDS_TITLE = "MORE REWARDS"
    private static final String EXPIRATION_DATE_PATTERN = /EXP \d{2}\.\d{2}\.\d{2}/
    private static final String PROMOTION_AVAILABLE = "Available"
    private static final By PROMOTION_MINI_HEADERS = By.cssSelector("#promoCerts span.miniHeader")
    private static final String PROMOTIONAL_AWARD = "PROMOTIONAL AWARD"
    private static final By RAPID_REWARD_HEADER = By.id("global_account_bar_section_rapid_rewards_header")
    private static final String RAPID_REWARD_HEADER_INACTIVE_CLASS = "global_account_bar_header global_account_bar_header_middle global_account_bar_header_inactive"
    private static final By ENROLL_NOW = By.cssSelector("div.global_account_bar_login_form_right a")
    private static final By OPTIONAL_TRAVEL_CHARGES = By.cssSelector("a[href='/html/customer-service/travel-fees.html?int=GNAVTRAVELFEES']")
    private static final By AVAILABLE_POINTS = By.className("availablePointsNumber");
    private static final By TIER_STATUS_FIELD_OLD = By.className("tier")
    private static final By TIER_STATUS_FIELD_NEW = By.cssSelector(".swa.account_information")
    private static final By CONFIRMATION_NUMBER_CHECKIN = By.id("checkin_confnum");
    private static final By CONFIRMATION_NUMBER_CHANGE_FLIGHT = By.id("changeFlight_confnum");
    private static final By FIRST_NAME_CHECKIN = By.id("checkin_firstname");
    private static final By FIRST_NAME_CHANGE_FLIGHT = By.id("changeFlight_firstname");
    private static final By LAST_NAME_CHECKIN = By.id("checkin_lastname");
    private static final By LAST_NAME_CHANGE_FLIGHT = By.id("changeFlight_lastname");
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By CHECK_IN_SECTION = By.id("checkIn")
    private static final By CHANGE_FLIGHT_SECTION = By.id("changeFlight")
    private static final By CHECK_FLIGHT_STATUS_SECTION = By.id("checkFlightStatus")
    private static final By CHECK_FLIGHT_STATUS_ORIGIN = By.id("checkFlightStatus_origin_displayed")
    private static final By CHECK_FLIGHT_STATUS_DESTINATION = By.id("checkFlightStatus_destination_displayed")
    private static final By SUBMIT_BUTTON_CHECK_IN = By.id("submitButtonCheckIn")
    private static final By SUBMIT_BUTTON_CHANGE_FLIGHT = By.id("submitButtonChangeFlight")
    private static final By SUBMIT_BUTTON_CHECK_FLIGHT = By.id("submitButtonCheckFlight")
    private static final By REMEMBER_ME_CHECK_BOX = By.id("rememberMeCheckbox")
    GlobalNavigationHeader globalNavigationHeader

    private WebElement findLoginSubmitButton() {
        waitForElement(LOGIN_SUBMIT_BUTTON,false,5)
    }

    public AccountBar(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    boolean getAreQuickLinksPresent() {
        WebElement divElement = waitForElement(By.id("global_account_bar"))
        List<WebElement> spans = divElement.findElements(By.tagName("span"))

        spans.any { it.text.trim().equalsIgnoreCase("Quick Air Links") }
    }

    def hasQuickLinkWithLabel(String link) {
        getQuickLink(link).shouldNotBe null
    }

    def getQuickLink(String link) {
        findLinkInSection(findElement(By.id("quick_air_links")), link)
    }

    boolean isLoggedIn() {
        isElementPresent(By.className("logoutLink"))
    }

    def logOut() {
        waitForElement(LOGOUT_LINK).click()
    }

    def logInToRapidRewards(String username, String password) {
        logInAsAUser(username, password)
        flow.isRapidRewards = true
        flow.isLoggedIn = true
        flow.userLoggedInRapidRewardsNumber = username
    }

    def logInAsAUser(String username, String password) {
        fillIn(ACCOUNT_NUMBER, username)
        fillIn(ACCOUNT_PASSWORD, password)
        waitForElement(LOGIN_SUBMIT_BUTTON).click()
    }

    def logInAsCustomer (String username, String password){
        logInAsAUser(username, password)
        flow.isCustomer = true
        flow.isLoggedIn = true
    }

    def getUserPreferredName() {
        return waitForElement(USER_PREFERRED_NAME).getText()
    }

    boolean isLogInFormPresent() {
        isElementPresent(By.id("accountNumber"))
    }

    def getLogInErrorMessage() {
        waitForElement(By.className("error_account_bar_login_list")).text
    }

    def getLoginSectionLink(String linkName) {
        if (linkName.equals("? icon")) {
            return waitForElement(By.id("right_column_account_login_form_field_rr_help_link"))
        }
        findLinkInSection(findElement(By.className("global_account_bar_login_form_container")), linkName)
    }

    def getLoginSectionMyAccountLink(String linkName) {
        findLinkInSection(findElement(ACCOUNT_LOGIN_CONTAINER), linkName)
    }


    def selectOnRememberMe() {
        if (ToggleHomepage2.isOn()) {
            if (!findElement(By.id("rememberme")).selected) {
                waitForElement(By.id("rememberme")).click()
            }
        } else {
            if (!findElement(REMEMBER_ME_CHECK_BOX).selected) {
                waitForElement(REMEMBER_ME_CHECK_BOX).click()
            }
        }
    }

    def verifyDrawerTitle(String drawerName) {
        if (!isElementWithTextPresent(By.className("global_account_bar_header"), drawerName)) {
            fail("Drawer title ${drawerName} is not present")
        }
    }

    def verifyDrawerTitleNotPresent(String drawerName) {
        if (verifyDrawerTitle(drawerName)) {
            fail("Drawer title ${drawerName} is present")
        }
    }

    ManageTravelDrawer getManageTravelDrawer() {
        return new ManageTravelDrawer(findElement(BY_MY_TRAVEL_ID))
    }

    def clickWhereAreMyRewards() {
        waitForElement(WHERE_ARE_MY_AWARDS).click()
        verifyPage()
    }

    public String returnRapidRewardsUserNameOrAccountNumber(String usernameOrAccountNumber) {
        def goodUser = data.getUser("goodUser")
        return usernameOrAccountNumber.equalsIgnoreCase("username") ? goodUser.getRRAccountName() : goodUser.getRRNumber()
    }

    def enterWrongRapidRewardsPasswordOnAccountBar(String usernameOrAccNumber) {
        def goodUser = data.getUser("goodUser")
        if (usernameOrAccNumber.equalsIgnoreCase("username")) {
            logInToRapidRewards(goodUser.getRRAccountName(), "wrong")
        } else {
            logInToRapidRewards(goodUser.getRRNumber(), "wrong")
        }
    }

    def verifyLogInFormIsNotPresent() {
        isLogInFormPresent().shouldBe false
    }

    def verifyPreferredNameIsOnAccountBar() {
        getUserPreferredName().shouldContain data.getPreferredName()
    }

    def verifyLogInErrorMessage(String message) {
        getLogInErrorMessage().shouldContain message
    }

    def tryToLogInWithWrongPasswordFiveTimes() {
        5.times {
            logInToRapidRewards(data.getUser("goodUser").getRRNumber(), "abcdefg")
        }
    }

    def verifySectionTitleInManageTravelDrawer(String sectionName) {
        getManageTravelDrawer().hasSectionWithTitle(sectionName).shouldBe true
    }

    def verifySectionTitleNotInManageTravelDrawer(String sectionName) {
        getManageTravelDrawer().hasSectionWithTitle(sectionName).shouldBe false
    }

    private WebElement rapidRewardsAccountLoggedIn() {
        waitForElement(RAPID_REWARDS_ACCOUNT_LOGGED_IN)
    }

    private WebElement findAccountBarSalutation() {
        waitForElement(GLOBAL_ACCOUNT_BAR_LOGIN_FORM_SALUTATION,true,45)
    }

    void verifyRapidRewardsLoggedIn() {

        if (findLoginSubmitButton() == null){
            findAccountBarSalutation().getText().shouldEqual "Hello,", "Rapid Rewards Account Did Not Login"
            waitForElement(RAPID_REWARDS_ACCOUNT_LOGGED_IN).getText().shouldContain "R.R. #", "Rapid Rewards Account Number Not Found After Login"
        }
        findAccountBarSalutation()

        //verifying account number and point container after logging in
        waitForElement(By.id("account_bar_rr_number")).text.shouldContain flow.userLoggedInRapidRewardsNumber, "Did not find rapid rewards account number"
        waitForElement(AVAILABLE_POINTS).text.matches ~/.*\d.*/ shouldBe true, "Did not find account point container"
    }

    def getRRNumber() {
        List<String> rrNumberText = waitForElement(RAPID_REWARDS_ACCOUNT_LOGGED_IN).getText().split(" ")
        return rrNumberText[2]
    }

    void clickOnNeedHelpLogginLink(){
        waitForElement(NEED_HELP_LINK).click()
    }

    def expandRapidRewardsDrawer() {
        if (RAPID_REWARD_HEADER_INACTIVE_CLASS.equals(waitForElement(RAPID_REWARD_HEADER).getAttribute("class"))){
            waitForElement(RAPID_REWARDS_TOGGLE).click()
        }
    }

    def verifyAccountStatusWarningMessage() {
        waitForElement(ACCOUNT_HELD_WARNING).getText().shouldContain ACCOUNT_HELD_MESSAGE, "The warning message should contain " + ACCOUNT_HELD_MESSAGE
    }

    def verifyDreamTripsEncouragingMessage() {
        waitForElement(DREAM_TRIPS_SECTION).getText().shouldContain DREAM_TRIPS_MESSAGE, "The encouraging message should contain " + DREAM_TRIPS_MESSAGE
    }

    void clickOnTrackYourTripLink(){
        waitForElement(TRACK_YOUR_TRIP_LINK).click()
    }

    def verifyDreamTripsTitleSection(){
        waitForElements(MINI_HEADERS).get(1).getText().shouldBe DREAM_TRIPS_SECTION_TITLE, "The dream trips section title should be " + DREAM_TRIPS_SECTION_TITLE
    }

    def verifyCheckDatesButtonIsNotPresent(){
        verifyElementNotPresent("Check Dates button",CHECK_DATES_BUTTON)
    }

    def verifyDreamTripsName(String tripName) {
        waitForElement(DREAM_TRIP_NAME).getText().shouldBe tripName, "The dream trip name should be " + tripName
    }

    def verifyMustardProgressBarUnderDreamTripSection() {
        verifyElementPresent("Dream Trip progress bar",DREAM_TRIP_PROGRESS_BAR)
        String percentValue = waitForElement(DREAM_TRIP_PROGRESS_BAR).getAttribute("style").replaceAll(NON_DIGIT,'')
        Integer.parseInt(percentValue).shouldBeLessThan 100
    }

    def verifyPercentageOfProgressUnderDreamTripSection() {
        String percentValue = waitForElement(DREAM_TRIP_PERCENT_VALUE).getText().replaceAll(NON_DIGIT,'')
        Integer.parseInt(percentValue).shouldBeLessThan 100
    }

    def clickOnCompanionButton(){
        waitForElement(COMPANION_BUTTON).click()
    }

    def verifyCheckDatesButtonIsPresent(){
        verifyElementPresent("Check Dates button",CHECK_DATES_BUTTON)
    }

    def verifyGreenProgressBarUnderDreamTripSection() {
        verifyElementPresent("Dream Trip full progress bar",DREAM_TRIP_PROGRESS_BAR_FULL)
        String percentValue = waitForElement(DREAM_TRIP_PROGRESS_BAR_FULL).getAttribute("style").replaceAll(NON_DIGIT,'')
        Integer.parseInt(percentValue).shouldBe 100, "The percent value should be 100"
    }

    def verifyFullPercentageOfProgressUnderDreamTripSection() {
        String percentValue = waitForElement(DREAM_TRIP_PERCENT_VALUE).getText().replaceAll(NON_DIGIT,'')
        Integer.parseInt(percentValue).shouldBe 100, "The percent value should be 100"
    }

    def verifyLearnMoreLinkIsPresent() {
        isElementPresent(RAPID_REWARDS_CREDIT_CARD_LEARN_MORE).shouldBe true,"Learn More Link should be present on My Rapid Rewards section"
    }

    def verifyRapidRewardsCreditCardTitleSection(){
        waitForElement(RAPID_REWARDS_CREDIT_CARD).getText().shouldBe RAPID_REWARDS_CREDIT_CARD_TITLE, "The Rapid Rewards Credit Card section title should be " + RAPID_REWARDS_CREDIT_CARD_TITLE
    }

    def verifyMoreRewardsLinkIsPresent() {
        isElementPresent(MORE_REWARDS_LINK).shouldBe true,"More Rewards Link should be present on My Rapid Rewards section"
    }

    def verifyRedeemForMoreRewardsTitleSection(){
        waitForElements(MINI_HEADERS).get(2).getText().shouldBe MORE_REWARDS_TITLE, "The credit card holder section title should be " + MORE_REWARDS_TITLE
    }

    def verifyPromotionExpirationDate(){
        waitForElement(PROMO_AWARD_DETAIL).getText().matches(EXPIRATION_DATE_PATTERN).shouldBe true, "The expiration date did not match with the pattern 'EXP mm.dd.yy'"
    }

    def clickOnPromotionalAwardViewNowButton(){
        waitForElement(PROMOTIONAL_AWARD_VIEW_NOW_BUTTON).click()
    }

    def verifyPromotionAvailable() {
        waitForElement(PROMOTION_MINI_HEADERS).getText().matches(PROMOTIONAL_AWARD).shouldBe true, "Promotional award title should be displayed in the Rapid Rewards section."
        waitForElement(PROMO_AWARD_DETAIL).getText().matches(PROMOTION_AVAILABLE).shouldBe true, "Promotional award should be available."
    }

    def verifyPromotionNotPresent() {
        verifyElementNotPresent("promoAwardDetail", PROMO_AWARD_DETAIL)
    }

    def clickOnEnrollNow(){
        waitForElement(ENROLL_NOW).click()
    }

    def clickOnOptionalTravelCharges() {
        waitForElement(OPTIONAL_TRAVEL_CHARGES).click()
    }

    boolean verifyAvailablePoints(){
        String s = waitForElement(AVAILABLE_POINTS).getText()
        int customerPoints = s.replace(',', '').toInteger()
        boolean alistVerification  = customerPoints <= 35000
        alistVerification.shouldNotBe true, "A-list memeber did not have sufficent points"
    }

    void verifyTierIsDisplayed(String tier){
        WebElement tierOnPage = waitForElement(TIER_STATUS_FIELD_NEW, false)?:waitForElement(TIER_STATUS_FIELD_OLD)
        tierOnPage.getText().shouldContain tier, "${tier} was not found in the Account Bar"
    }

    def verifyLogInErrorMessage2() {
        getLogInErrorMessage2().shouldContain "The Username/Account Number and/or Password are incorrect."
    }

    def getLogInErrorMessage2() {
        waitForElement(By.className("error")).text
    }

    def clickOnCheckIn() {
        waitForElement(CHECK_IN_SECTION).click()
    }

    def clickOnChangeFlight() {
        waitForElement(CHANGE_FLIGHT_SECTION).click()
    }

    def clickOnCheckFlightStatus() {
        waitForElement(CHECK_FLIGHT_STATUS_SECTION).click()
    }

    def retrieveReservationToCheckin(String pnr, String pnr_firstName, String pnr_lastName) {
        waitForElement(CONFIRMATION_NUMBER_CHECKIN).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(FIRST_NAME_CHECKIN).sendKeys(DELETE_EXISTING + pnr_firstName)
        waitForElement(LAST_NAME_CHECKIN).sendKeys(DELETE_EXISTING + pnr_lastName)
        waitForElement(SUBMIT_BUTTON_CHECK_IN).click()
    }

    def retrieveReservationToChangeFlight(String pnr, String pnr_firstName, String pnr_lastName) {
        waitForElement(CONFIRMATION_NUMBER_CHANGE_FLIGHT).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(FIRST_NAME_CHANGE_FLIGHT).sendKeys(DELETE_EXISTING + pnr_firstName)
        waitForElement(LAST_NAME_CHANGE_FLIGHT).sendKeys(DELETE_EXISTING + pnr_lastName)
        waitForElement(SUBMIT_BUTTON_CHANGE_FLIGHT).click()
    }

    def enterOriginDestinationDateAndClickCheckFlightStatus(String origin, String destination) {
        waitForElement(CHECK_FLIGHT_STATUS_ORIGIN).sendKeys(DELETE_EXISTING + origin + Keys.ARROW_DOWN + Keys.TAB)
        waitForElement(CHECK_FLIGHT_STATUS_DESTINATION).sendKeys(DELETE_EXISTING + destination +  Keys.ARROW_DOWN + Keys.TAB)
        waitForElement(SUBMIT_BUTTON_CHECK_FLIGHT).click()
    }

    def verifyRememberMeCheckBoxIsChecked() {
        if (ToggleHomepage2.isOn()
                && globalNavigationHeader.isHomePage()) {
            waitForElement(By.id("rememberme")).isSelected().shouldBe true, "Remember me is not checked"
        } else {
            waitForElement(REMEMBER_ME_CHECK_BOX).isSelected().shouldBe true, "Remember me is not checked"
        }
    }

    def verifyRapidRewardsNumberIsFilled() {
        if (ToggleHomepage2.isOn()
                && globalNavigationHeader.isHomePage()) {
            waitForElement(By.id("username")).getAttribute("value").shouldContain flow.getUser().getRRNumber()
        } else {
            waitForElement(ACCOUNT_NUMBER).getAttribute("value").shouldContain flow.getUser().getRRNumber()
        }
    }
}