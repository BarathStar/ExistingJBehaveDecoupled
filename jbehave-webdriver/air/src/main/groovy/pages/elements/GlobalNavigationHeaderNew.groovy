/* Copyright (c) 2014, Southwest Airlines Co. All rights reserved. */
package pages.elements

import com.swacorp.dotcom.webscenarios.air.RRUser
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import pages.BasePage
import util.RRContactInformation

/**
 * Accessors and utility methods for the new Global Navigation header
 */
class GlobalNavigationHeaderNew extends BasePage {

    private static final int DEFAULT_RETRY = 5

    //Init Plan a Trip links
    private static final By HEADER_MENU_PLAN_A_TRIP = By.cssSelector("a.swa-header--plan-a-trip")
    // Flight links
    private static final String PLAN_A_TRIP_LINK_BOOK_A_FLIGHT = "/flight?clk=GSUBNAV-AIR-BOOK"
    private static final String PLAN_A_TRIP_LINK_MANAGE_RESERVATIONS_AIR = "/flight/lookup-air-reservation.html?clk=GSUBNAV-AIR-MNGRES&forceNewSession=yes"
    private static final String PLAN_A_TRIP_LINK_WHERE_WE_FLY = "/travel_center/routemap_dyn.html?clk=GSUBNAV-AIR-ROUTEMAP"
    private static final String PLAN_A_TRIP_LINK_FLIGHT_SCHEDULES = "/flight/request-schedule.html?clk=GSUBNAV-AIR-SCHEDULES"
    private static final String PLAN_A_TRIP_LINK_INTERNATIONAL_TRAVEL = "/html/air/intl?clk=GSUBNAV-AIR-INTLTRVL"
    private static final String PLAN_A_TRIP_LINK_FLIGHT_OFFERS = "http://travel.southwest.com/specialoffers/airOffers.html?clk=GSUBNAV-AIR-OFFERS"
    private static final String PLAN_A_TRIP_LINK_SEARCH_BY_MAP = "http://getawayfinder.southwest.com/flights?clk=GSUBNAV-AIR-MAP"
    private static final String PLAN_A_TRIP_LINK_LOW_FARE_CALENDAR = "/flight/shortcut/low-fare-search.html?clk=GSUBNAV-AIR-LFCLNDR"
    // Hotel links
    private static final String PLAN_A_TRIP_LINK_HOTEL = "/hotels?clk=GNAV-HOTEL"
    private static final String PLAN_A_TRIP_LINK_BOOK_A_HOTEL = "/hotels/search-hotels.html?clk=GSUBNAV-HTL-BOOK"
    private static final String PLAN_A_TRIP_LINK_MANAGE_RESERVATIONS_HOTEL = "/hotels/retrieve-hotel-reservation.html?clk=GSUBNAV-HTL-MANAGE&forceNewSession=yes"
    private static final String PLAN_A_TRIP_LINK_BEST_RATE_GUARANTEE = "/hotels/best-rate-guarantee-claim.html?clk=GSUBNAV-HTL-BRGRNT"
    private static final String PLAN_A_TRIP_LINK_RAPID_REWARD_PARTNERS_HOTEL = "/rapidrewards/partners-hotels?clk=GSUBNAV-HTL-PARTNERS"
    private static final String PLAN_A_TRIP_LINK_WHY_BOOK_WITH_US_HOTEL = "/html/hotels/why-book.html?clk=GSUBNAV-HTL-WHYBK"
    // Car links
    private static final String PLAN_A_TRIP_LINK_BOOK_A_CAR = "/car-rentals/search-car-rentals.html?clk=GSUBNAV-CAR-BOOK"
    private static final String PLAN_A_TRIP_LINK_MANAGE_RESERVATIONS_CAR = "/car-rentals/retrieve-car-reservation.html?clk=GSUBNAV-CAR-MANAGE&forceNewSession=yes"
    private static final String PLAN_A_TRIP_LINK_RAPID_REWARD_PARTNERS_CAR = "/html/rapidrewards/partners/travel/rental-car/index.html?clk=GSUBNAV-CAR-PARTNERS"
    private static final String PLAN_A_TRIP_LINK_WHY_BOOK_WITH_US_CAR = "/html/cars/why-book.html?clk=GSUBNAV-CAR-WHYBK"
    // Vacations links
    private static final String PLAN_A_TRIP_LINK_BOOK_A_VACATION = "/flight/vacationspackages.html?clk=GSUBNAV-VACN-BOOK&intcmp=GNAV-VACN"
    private static final String PLAN_A_TRIP_LINK_MANAGE_JACKPOT_PACKAGES = "/flight/vacationspackages.html?clk=GSUBNAV-VAC-PKGS&intcmp=GSUBNAV-VAC-PKGS"

    //second row
    private static final String PLAN_A_TRIP_LINK_CHECK_IN = "/flight/retrieveCheckinDoc.html?clk=GSUBNAV-CHCKIN&forceNewSession=yes"
    private static final String PLAN_A_TRIP_LINK_FLIGHT_STATUS = "/flight/flight-status-select.html?clk=GSUBNAV-FLTSTATUS"
    private static final String PLAN_A_TRIP_LINK_CHANGE_FLIGHT = "/flight/change-air-reservation.html?clk=GSUBNAV-CNGFLT&forceNewSession=yes"
    private static final String PLAN_A_TRIP_LINK_EARLY_BIRD_CHECK_IN = "/flight/early-bird-retrieve-reservation.html?clk=GSUBNAV-PLAN-AD1-EB&forceNewSession=yes"
    private static final String PLAN_A_TRIP_LINK_BUY_A_SW_GIFT_CARD = "/gift-card/home.html?clk=GSUBNAV-PLAN-AD2-GC"
    //End Plan a Trip links

    //Init Special Offers links
    private static final By HEADER_MENU_SPECIAL_OFFERS = By.cssSelector("a.swa-header--special-offers")
    //Special Offers links
    private static final String SPECIAL_OFFERS_LINK_FLIGHT = "http://travel.southwest.com/specialoffers/airOffers.html?clk=GSUBNAV-OFFERS-AIR"
    private static final String SPECIAL_OFFERS_LINK_HOTEL = "http://travel.southwest.com/specialoffers/hotelOffers.html?clk=GSUBNAV-OFFERS-HOTELS"
    private static final String SPECIAL_OFFERS_LINK_CAR = "http://travel.southwest.com/specialoffers/carOffers.html?clk=GSUBNAV-OFFERS-CARS"
    private static final String SPECIAL_OFFERS_LINK_VACATION = "http://travel.southwest.com/specialoffers/vacationOffers.html?clk=GSUBNAV-OFFERS-VACATIONS"
    private static final String SPECIAL_OFFERS_LINK_CLICK_N_SAVE_SIGN_UP = "/html/email/click_n_save_signup.html?clk=GSUBNAV-OFFERS-AD1-CNS" //button
    //End Special Offers links

    //Init Rapid Rewards links
    private static final By HEADER_MENU_RAPID_REWARDS = By.cssSelector("a.swa-header--rapid-rewards")
    // Program links
    private static final String RAPID_REWARDS_LINK_PROGRAM = "/rapidrewards/overview?clk=GNAVRPDRWDS-PROGRAM"
    private static final String RAPID_REWARDS_LINK_ABOUT_RAPID_REWARDS = "/rapidrewards/about?clk=GSUBNAV-RR-DETAILS"
    private static final String RAPID_REWARDS_LINK_ENROLL = "/account/enroll?clk=GSUBNAV-RR-ENROLL&f=zSWARRHPAA1409RRAzz"
    private static final String RAPID_REWARDS_LINK_EARNING_POINTS = "/rapidrewards/how-to-earn?clk=GSUBNAV-RR-EARN"
    private static final String RAPID_REWARDS_LINK_TIER_BENEFITS = "/rapidrewards/tiers-more-alist?clk=GSUBNAV-RR-TIER"
    private static final String RAPID_REWARDS_LINK_RAPID_REWARDS_CREDIT_CARD = "/html/rapidrewards/partners/credit-cards/southwest-airlines-rapid-rewards-cards/index.html?clk=GSUBNAV-RR-VISA"
    private static final String RAPID_REWARDS_LINK_BOOK_WITH_POINTS = "/flight?fareType=POINTS&clk=GSUBNAV-RR-REDEEM"
    private static final String RAPID_REWARDS_LINK_MORE_REWARDS = "/rapidrewards/maritz-validate?clk=GSUBNAV-RR-MORE" // external link
    // Manage links
    private static final String RAPID_REWARDS_LINK_MY_ACCOUNT = "/account/snapshot?clk=GSUBNAV-RR-ACCOUNT"
    private static final String RAPID_REWARDS_LINK_ACCOUNT_LOGIN_HELP = "/account/recovery?clk=GSUBNAV-RR-LOOKUP#needUsername"
    private static final String RAPID_REWARDS_LINK_PROMOTIONS = "/rapidrewards/promotions?clk=GSUBNAV-RR-PROMOS"
    private static final String RAPID_REWARDS_LINK_BUY_OR_TRANSFER_POINTS = "/rapidrewards/points-center?clk=GSUBNAV-RR-BUYPOINTS"
    private static final String RAPID_REWARDS_LINK_EMAIL_SUBSCRIPTIONS = "/account/info/edit-communication-prefs?clk=GSUBNAV-RR-EMAILSUBS"
    //Start Earning RR
    private static final String RAPID_REWARDS_LINK_ENROLL_NOW = "/account/enroll/enroll-member?clk=GSUBNAV-RR-AD1-ENROLL&f=zSWARRHPAA1409RRAzz" //button
    //End Rapid Rewards links

    private static final By HOT_STATE_NAME = By.className("swa-header--hot-state-name")
    private static final By MY_ACCOUNT_LINK = By.className("swa-header--my-account")
    private static final By HOT_STATE_TIER = By.className("swa-header--hot-state-tier")
    private static final By LOGIN_OVERLAY = By.cssSelector("a.swa-header--login")

    private static final By USER_ID_SELECTOR = By.id("username")
    private static final By PASSWORD_ID_SELECTOR = By.id("password")
    private static final By LOGIN_BUTTON_SELECTOR = By.cssSelector("input.swa-header--login-button")
    private static final By PASSWORD_ERROR = By.id("password-error")
    private static final By LOGOUT_LINK = By.className("swa-header--logout")

    private static final By INTERIOR_PAGE_CLASS = By.className("swa-header_interior")

    private static final String MESSAGE_NOT_FOUND = "Expected Oops message not found"

    private Set<String> planATripPartOneLinks;
    private Set<String> planATripPartTwoLinks;
    private Set<String> specialOffersLinks;
    private Set<String> rapidRewardsLinks;
    RRContactInformation rrContactInformation


    GlobalNavigationHeaderNew(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    private void createPlanATripPartOneLinksSet() {
        planATripPartOneLinks = new LinkedHashSet<String>()

        // Flight links
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_BOOK_A_FLIGHT)
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_MANAGE_RESERVATIONS_AIR)
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_WHERE_WE_FLY)
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_FLIGHT_SCHEDULES)
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_INTERNATIONAL_TRAVEL)
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_FLIGHT_OFFERS)
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_LOW_FARE_CALENDAR)

        // Hotel liks
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_BOOK_A_HOTEL)
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_MANAGE_RESERVATIONS_HOTEL)
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_BEST_RATE_GUARANTEE)
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_RAPID_REWARD_PARTNERS_HOTEL)
        planATripPartOneLinks.add(PLAN_A_TRIP_LINK_WHY_BOOK_WITH_US_HOTEL)
    }

    private void createPlanATripPartTwoLinksSet() {
        planATripPartTwoLinks = new LinkedHashSet<String>()

        // Car liks
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_BOOK_A_CAR)
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_MANAGE_RESERVATIONS_CAR)
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_RAPID_REWARD_PARTNERS_CAR)
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_WHY_BOOK_WITH_US_CAR)

        // Vacations liks
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_BOOK_A_VACATION)
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_MANAGE_JACKPOT_PACKAGES)

        //second row
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_CHECK_IN)
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_FLIGHT_STATUS)
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_CHANGE_FLIGHT)
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_EARLY_BIRD_CHECK_IN)
        planATripPartTwoLinks.add(PLAN_A_TRIP_LINK_BUY_A_SW_GIFT_CARD)
    }

    private void createSpecialOffersLinksSet() {
        specialOffersLinks = new LinkedHashSet<String>()

        specialOffersLinks.add(SPECIAL_OFFERS_LINK_FLIGHT)
        specialOffersLinks.add(SPECIAL_OFFERS_LINK_HOTEL)
        specialOffersLinks.add(SPECIAL_OFFERS_LINK_CAR)
        specialOffersLinks.add(SPECIAL_OFFERS_LINK_VACATION)
        specialOffersLinks.add(SPECIAL_OFFERS_LINK_CLICK_N_SAVE_SIGN_UP)
    }

    private void createRapidRewardsLinksSet() {
        rapidRewardsLinks = new LinkedHashSet<String>()
        // Program links
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_ABOUT_RAPID_REWARDS)
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_ENROLL)
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_EARNING_POINTS)
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_TIER_BENEFITS)
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_RAPID_REWARDS_CREDIT_CARD)
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_BOOK_WITH_POINTS)
        // Manage links
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_MY_ACCOUNT)
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_ACCOUNT_LOGIN_HELP)
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_PROMOTIONS)
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_BUY_OR_TRANSFER_POINTS)
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_EMAIL_SUBSCRIPTIONS)
        //second row
        //Start Earning RR
        rapidRewardsLinks.add(RAPID_REWARDS_LINK_ENROLL_NOW)    //button
    }

    private void clickHeaderMenuLinks(Set<String> subMenuSetLinks, By subMenuLink) {

        for (String key : subMenuSetLinks) {

            waitForElement(createLinkElement(key)).click()
            checkNoOops()
            clickBrowserBackButton()
            waitForElement(subMenuLink).click()
        }
    }

    void openNavigationMenuPlanATrip() {
        waitForElement(HEADER_MENU_PLAN_A_TRIP).click()
    }

    void openNavigationMenuSpecialOffers() {
        waitForElement(HEADER_MENU_SPECIAL_OFFERS).click()
    }

    void openNavigationMenuRapidRewards() {
        waitForElement(HEADER_MENU_RAPID_REWARDS).click()
    }

    void openHeaderMenuPlanATripPartOne() {
        createPlanATripPartOneLinksSet()
        openNavigationMenuPlanATrip()
    }

    void clickHeaderMenuPlanATripPartOneLinks() {
        clickHeaderMenuLinks(planATripPartOneLinks, HEADER_MENU_PLAN_A_TRIP)
    }

    void openHeaderMenuPlanATripPartTwo() {
        createPlanATripPartTwoLinksSet()
        openNavigationMenuPlanATrip()
    }

    void clickHeaderMenuPlanATripPartTwoLinks() {
        clickHeaderMenuLinks(planATripPartTwoLinks, HEADER_MENU_PLAN_A_TRIP)
    }

    void openHeaderMenuSpecialOffers() {
        createSpecialOffersLinksSet()
        openNavigationMenuSpecialOffers()
    }

    void clickHeaderMenuSpecialOffersLinks() {
        clickHeaderMenuLinks(specialOffersLinks, HEADER_MENU_SPECIAL_OFFERS)
    }

    void openHeaderMenuRapidRewards() {
        createRapidRewardsLinksSet()
        openNavigationMenuRapidRewards()
    }

    void clickHeaderMenuRapidRewardsLinks() {
        clickHeaderMenuLinks(rapidRewardsLinks, HEADER_MENU_RAPID_REWARDS)
    }

    void openLoginDropdown() {
        waitForElement(LOGIN_OVERLAY).click()
    }

    void logInAsRapidRewardsMember(RRUser rrUser) {
        findElement(USER_ID_SELECTOR).sendKeys(rrUser.getRRNumber())
        findElement(PASSWORD_ID_SELECTOR).sendKeys(rrUser.getRRPassword())
        findElement(LOGIN_BUTTON_SELECTOR).click()
    }

    void fillInLoginInformation(RRUser rrUser) {
        findElement(USER_ID_SELECTOR).sendKeys(rrUser.getRRNumber())
        findElement(PASSWORD_ID_SELECTOR).sendKeys(rrUser.getRRPassword())
    }

    void verifyLogInErrorMessageInHeader(String message) {
        waitForElement(PASSWORD_ERROR).text.shouldBe message, MESSAGE_NOT_FOUND
    }

    void verifyTierStatusFlagInHotState(String tier) {
        WebElement accountType = waitForElement(HOT_STATE_TIER)
        accountType.text.shouldContain tier, "The account Type is not " + tier
    }

    @Override
    String getRelativePath() {
        return "/flight"
    }


    // ---------- FLIGHT ----------


    // ---------- HOTEL ----------

    void clickOnHotelLinkMenu() {
        waitForElement(createLinkElement(PLAN_A_TRIP_LINK_HOTEL)).click()
    }

    // ---------- CAR ----------

    void clickOnCarLinkMenu() {
        waitForElement(createLinkElement(PLAN_A_TRIP_LINK_BOOK_A_CAR)).click()
    }

    // ---------- VACATIONS ----------

    void clickAboutRRLink() {
        waitForElement(createLinkElement(RAPID_REWARDS_LINK_PROGRAM)).click()
    }

    def clickOnPartnersLink() {
        waitForElement(createLinkElement(RAPID_REWARDS_LINK_RAPID_REWARDS_CREDIT_CARD)).click()
    }

    void clickOnChangeFlightLink() {
        waitForElement(createLinkElement(PLAN_A_TRIP_LINK_CHANGE_FLIGHT)).click()
    }

    void clickOnCheckinFlightLink() {
        waitForElement(createLinkElement(PLAN_A_TRIP_LINK_CHECK_IN)).click()
    }

    void clickOnManageAirReservationLink() {
        waitForElement(createLinkElement(PLAN_A_TRIP_LINK_MANAGE_RESERVATIONS_AIR)).click()
    }

    void clickOnBuyPointsTravelLink() {
        waitForElement(createLinkElement(RAPID_REWARDS_LINK_BUY_OR_TRANSFER_POINTS)).click()
    }

    void clickOnMyAccountLink() {
        waitForElement(createLinkElement(RAPID_REWARDS_LINK_MY_ACCOUNT)).click()
    }

    void clickOnEnrollLink() {
        waitForElement(createLinkElement(RAPID_REWARDS_LINK_ENROLL_NOW)).click()
    }

    void clickOnBookAFlight() {
        waitForElement(createLinkElement(PLAN_A_TRIP_LINK_BOOK_A_FLIGHT)).click()
    }

    void clickOnBestRateGuarantee() {
        waitForElement(createLinkElement(PLAN_A_TRIP_LINK_BEST_RATE_GUARANTEE)).click()
    }

    void verifyUserLoggedIn() {
        verifyElementPresent("logout link", LOGOUT_LINK)
        verifyElementPresent("my account link", MY_ACCOUNT_LINK)
        verifyElementNotPresent("login overlay link", LOGIN_OVERLAY)
    }

    void clickOnLogoutLink() {
        waitForElement(LOGOUT_LINK).click()
    }

    void verifyUserLoggedOut() {
        verifyElementPresent("login overlay link", LOGIN_OVERLAY)
        verifyElementNotPresent("my account link", MY_ACCOUNT_LINK)
        verifyElementNotPresent("logout link", LOGOUT_LINK)
    }

    void clickOnMoreRewardsLink() {
        waitForElement(createLinkElement(RAPID_REWARDS_LINK_MORE_REWARDS)).click()
    }

    void attemptToFillInAccountNumber(String accountNumber) {
        WebElement accountNumberField = waitForElement(USER_ID_SELECTOR)
        accountNumberField.sendKeys(DELETE_EXISTING + accountNumber.substring(0, 5))
        sleep(50)
        accountNumberField.sendKeys(Keys.ARROW_DOWN + Keys.TAB)
    }

    void verifyIfAccountNumberIsCompleted(String partialAccountNumber) {
        waitForElement(USER_ID_SELECTOR).getAttribute("value").equals(partialAccountNumber).shouldBe true, "Autocomplete active"
    }

    boolean isHomePage() {
        return !isInteriorPage()
    }

    boolean isLoggedIn() {
        return isElementPresent(LOGOUT_LINK)
    }

    void clickOnSouthwestGiftCardLink() {
        waitForElement(createLinkElement(PLAN_A_TRIP_LINK_BUY_A_SW_GIFT_CARD)).click()
    }

    void clickOnSearchByMap() {
        waitForElement(createLinkElement(PLAN_A_TRIP_LINK_SEARCH_BY_MAP)).click()
    }

    boolean isInteriorPage() {
        return waitForElement(INTERIOR_PAGE_CLASS, false, 3) != null
    }

    void logIntoAccount(RRUser rrUser) {
        openLoginDropdown()
        logInAsRapidRewardsMember(rrUser)
    }

    void verifyRRGreetingAndName() {
        def firstName = rrContactInformation.firstName
        if (!rrContactInformation.preferredFirstName.isEmpty()) {
            firstName = rrContactInformation.preferredFirstName
        }
        def greetingAndName = waitForElement(HOT_STATE_NAME).text
        greetingAndName.shouldContain "Hi,", "The greeting was not shown as expected"
        greetingAndName.shouldContain firstName, "The name was shown shown as expected"
    }
}
