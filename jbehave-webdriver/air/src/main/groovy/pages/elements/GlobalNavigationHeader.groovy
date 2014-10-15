/* Copyright (c) 2013, Southwest Airlines Co. All rights reserved. */
package pages.elements

import com.swacorp.dotcom.webscenarios.air.RRUser
import org.jbehave.web.selenium.WebDriverProvider
import pages.BasePage
import pages.HotelGlobalNavigation
import state.Flow
import steps.conditional.ToggleGlobalNav

/**
 * Facade to the different implementations of GlobalNavHeader accessors
 */
class GlobalNavigationHeader extends BasePage {

    GlobalNavigationHeaderNew globalNavigationHeaderNew
    GlobalNavigationHeaderOld globalNavigationHeaderOld
    Flow flow
    private static final int DEFAULT_RETRY = 5

    GlobalNavigationHeader(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    @Override
    String getRelativePath() {
        return "/flight"
    }

    AirGlobalNavigation openAirSubMenu(int retry = DEFAULT_RETRY) {
        return globalNavigationHeaderOld.openAirSubMenu(retry)
    }

    CarGlobalNavigation openCarSubMenu(int retry = DEFAULT_RETRY) {
        return globalNavigationHeaderOld.openCarSubMenu(retry)
    }

    FlyingGlobalNavigation openFlyingSouthwestSubMenu(int retry = DEFAULT_RETRY) {
        return globalNavigationHeaderOld.openFlyingSouthwestSubMenu(retry)
    }

    void openSpecialOffersMenu() {
        globalNavigationHeaderOld.openSpecialOffersMenu()
    }

    void openVacationsMenu() {
        globalNavigationHeaderOld.openVacationsMenu()
    }

    def moveMouseOver(String cssSelector) {
        globalNavigationHeaderOld.moveMouseOver(cssSelector)
    }

    void clickOnAirLinkMenu() {
        globalNavigationHeaderOld.clickOnAirLinkMenu()
    }

    void clickOnHotelLinkMenu() {
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeaderNew.openNavigationMenuPlanATrip()
            globalNavigationHeaderNew.clickOnHotelLinkMenu()
        } else {
            globalNavigationHeaderOld.clickOnHotelLinkMenu()
        }
    }

    void clickOnCarLinkMenu() {
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeaderNew.clickOnCarLinkMenu()
        } else {
            globalNavigationHeaderOld.clickOnCarLinkMenu()
        }
    }

    RapidRewardsGlobalNavigation openRapidRewardsSubMenu() {
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeaderNew.openNavigationMenuRapidRewards()
        } else {
            globalNavigationHeaderOld.openRapidRewardsSubMenu()
        }
    }

    void clickAboutRRLink() {
        globalNavigationHeaderNew.clickAboutRRLink()
    }

    HotelGlobalNavigation openHotelSubMenu() {
        globalNavigationHeaderOld.openHotelSubMenu()
    }

    void openBestRateGuaranteePage() {
        globalNavigationHeaderNew.openNavigationMenuPlanATrip()
        globalNavigationHeaderNew.clickOnBestRateGuarantee()
    }

    def verifyEnrollLinkIsNotPresent() {
        globalNavigationHeaderOld.verifyEnrollLinkIsNotPresent()
    }

    def verifyEnrollLinkIsPresent() {
        globalNavigationHeaderOld.verifyEnrollLinkIsPresent()
    }

    def clickOnPartnersLink() {
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeaderNew.clickOnPartnersLink()
        } else {
            globalNavigationHeaderOld.clickOnPartnersLink()
        }
    }

    def clickOnInternationalTravelLink() {
        globalNavigationHeaderOld.clickOnInternationalTravelLink()
    }

    def clickAirportInformationLink() {
        globalNavigationHeaderOld.clickAirportInformationLink()
    }

    def clickOnInfantFlyingLink() {
        globalNavigationHeaderOld.clickOnInfantFlyingLink()
    }

    def verifyThemeParkLinkIsNotPresent() {
        globalNavigationHeaderOld.verifyThemeParkLinkIsNotPresent()
    }

    def verifyCruiseOffersNavLinkIsNotPresent() {
        globalNavigationHeaderOld.verifyCruiseOffersNavLinkIsNotPresent()
    }

    def verifyBookACruiseNavLinkIsNotPresent() {
        globalNavigationHeaderOld.verifyBookACruiseNavLinkIsNotPresent()
    }

    def verifyColumnsInVacationGlobalNavMenu() {
        globalNavigationHeaderOld.verifyColumnsInVacationGlobalNavMenu()
    }

    def verifyBookAShuttleNavLinkIsNotPresent() {
        globalNavigationHeaderOld.verifyBookAShuttleNavLinkIsNotPresent()
    }

    def verifyInfantFlyingPage() {
        globalNavigationHeaderOld.verifyInfantFlyingPage()
    }

    def clickRRGlobalNavigationHeader() {
        globalNavigationHeaderOld.clickRRGlobalNavigationHeader()
    }

    void openNewHeaderMenuPlanATripPartOne() {
        globalNavigationHeaderNew.openHeaderMenuPlanATripPartOne()
    }

    void clickNewHeaderMenuPlanATripPartOneLinks() {
        globalNavigationHeaderNew.clickHeaderMenuPlanATripPartOneLinks()
    }

    void openNewHeaderMenuPlanATripPartTwo() {
        globalNavigationHeaderNew.openHeaderMenuPlanATripPartTwo()
    }

    void clickNewHeaderMenuPlanATripPartTwoLinks() {
        globalNavigationHeaderNew.clickHeaderMenuPlanATripPartTwoLinks()
    }

    void openNewHeaderMenuSpecialOffers() {
        globalNavigationHeaderNew.openHeaderMenuSpecialOffers()
    }

    void clickNewHeaderMenuSpecialOffersLinks() {
        globalNavigationHeaderNew.clickHeaderMenuSpecialOffersLinks()
    }

    void openNewHeaderMenuRapidRewards() {
        globalNavigationHeaderNew.openHeaderMenuRapidRewards()
    }

    void clickNewHeaderMenuRapidRewardsLinks() {
        globalNavigationHeaderNew.clickHeaderMenuRapidRewardsLinks()
    }

    void openLoginDropdown() {
        globalNavigationHeaderNew.openLoginDropdown()
    }

    void logInAsRapidRewardsMember(RRUser rrUser) {
        globalNavigationHeaderNew.logInAsRapidRewardsMember(rrUser)
    }

    void fillInLoginInformation(RRUser rrUser) {
        globalNavigationHeaderNew.fillInLoginInformation(rrUser)
    }

    void verifyLogInErrorMessageInTheNewHeader(String message) {
        globalNavigationHeaderNew.verifyLogInErrorMessageInHeader(message)
    }

    void verifyTierStatusFlagInHotState(String tier) {
        globalNavigationHeaderNew.verifyTierStatusFlagInHotState(tier)
    }

    void clickOnChangeFlightLink() {
        globalNavigationHeaderNew.clickOnChangeFlightLink()
    }

    void clickOnCheckinFlightLink() {
        globalNavigationHeaderNew.clickOnCheckinFlightLink()
    }

    void clickOnManageAirReservationLink() {
        globalNavigationHeaderNew.clickOnManageAirReservationLink()
    }

    void clickOnBuyPointsTravelLink() {
        globalNavigationHeaderNew.clickOnBuyPointsTravelLink()
    }

    void clickOnMyAccountLink() {
        globalNavigationHeaderNew.clickOnMyAccountLink()
    }

    void clickOnEnrollLink() {
        globalNavigationHeaderNew.clickOnEnrollLink()
    }

    void clickOnBookAFlight() {
        globalNavigationHeaderNew.clickOnBookAFlight()
    }

    void verifyUserLoggedIn() {
        globalNavigationHeaderNew.verifyUserLoggedIn()
    }

    void clickOnLogoutLink() {
        globalNavigationHeaderNew.clickOnLogoutLink()
    }

    void verifyUserLoggedOut() {
        globalNavigationHeaderNew.verifyUserLoggedOut()
    }

    void clickOnMoreRewardsLink() {
        globalNavigationHeaderNew.clickOnMoreRewardsLink()
    }

    void attemptToFillInAccountNumber(String accountNumber) {
        globalNavigationHeaderNew.attemptToFillInAccountNumber(accountNumber)
    }

    void verifyIfAccountNumberIsCompleted(String partialAccountNumber) {
        globalNavigationHeaderNew.verifyIfAccountNumberIsCompleted(partialAccountNumber)
    }

    boolean isHomePage() {
        return globalNavigationHeaderNew.isHomePage()
    }

    boolean isLoggedIn() {
        return globalNavigationHeaderNew.isLoggedIn()
    }

    void clickOnSouthwestGiftCardLink() {
        globalNavigationHeaderNew.clickOnSouthwestGiftCardLink()
    }

    void clickOnSearchByMap() {
        globalNavigationHeaderNew.clickOnSearchByMap()
    }

    void logIntoAccount(RRUser rrUser) {
        globalNavigationHeaderNew.logIntoAccount(rrUser)
    }

    void verifyRRGreetingAndName() {
        globalNavigationHeaderNew.verifyRRGreetingAndName()
    }
}