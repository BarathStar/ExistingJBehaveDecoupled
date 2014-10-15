/* Copyright (c) 2014, Southwest Airlines Co. All rights reserved. */
package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import pages.BasePage
import pages.HotelGlobalNavigation

/**
 * Accessors and utility methods for the old Global Navigation header
 */
class GlobalNavigationHeaderOld extends BasePage {

    public static final String RAPID_REWARDS_GLOBAL_LINK_CSS = "globalnav_header_primary_link_rr"

    private final String SPECIAL_OFFER_GLOBAL_NAV_LINK_CSS = '#globalnav_header_primary_link_offers'
    private final String VACATIONS_GLOBAL_NAV_LINK_CSS = '#globalnav_header_primary_link_vacations'
    private final String AIR_GLOBAL_NAV_LINK_CSS = '#globalnav_header_primary_link_air'
    private final String CAR_GLOBAL_NAV_LINK_CSS = '#globalnav_header_primary_link_car'
    private final String FLYING_GLOBAL_NAV_LINK_CSS = '#globalnav_header_primary_link_flying'

    private static final By THEME_PARK_NAV_LINK = By.cssSelector("#globalnav_header_primary_link_offers_hover_container a[title*='Theme Park']")
    private static final By CRUISE_OFFERS_NAV_LINK = By.cssSelector("#globalnav_header_primary_link_offers_hover_container a[title*='Cruise']")
    private static final By AIR_MENU_CONTAINER = By.cssSelector("#globalnav_header_primary_link_air_hover_container .globalnav_header_subnav_hover_container_layout_table_center")
    private static final By CAR_MENU_CONTAINER = By.cssSelector("#globalnav_header_primary_link_car_hover_container table.globalnav_header_subnav_hover_container_layout_table tbody tr td.globalnav_header_subnav_hover_container_layout_table_center")
    private static final By FLYING_MENU_CONTAINER = By.id("globalnav_header_primary_link_flying_hover_container")
    private static final By AIR_TRAVEL_LINK = By.id("globalnav_header_primary_link_air")
    private static final By HOTEL_LINK = By.id("globalnav_header_primary_link_hotel")
    private static final By CAR_LINK = By.id("globalnav_header_primary_link_car")
    private static final String HOTEL_LINK_CSS = "#globalnav_header_primary_link_hotel"
    private static final By RAPID_REWARDS_MENU_CONTAINER = By.id("globalnav_header_primary_link_rr_hover_container")
    private static final By HOTEL_SUB_MENU_CONTAINER = By.id("globalnav_header_primary_link_hotel_hover_container")
    private static final int DEFAULT_RETRY = 5
    private static final By PARTNERS_TITTLE = By.xpath("//table/tbody/tr/td/ul/li/a[@id='partners-header']")
    private static final By INTERNATIONAL_TRAVEL_LINK = By.id("globalnav_header_primary_link_moreoptions")
    private static final String INFANT_FLYING = "/html/customer-service/family/baby-on-board-pol.html"
    private JavascriptExecutor javascriptExecutor

    public GlobalNavigationHeaderOld(WebDriverProvider driverProvider) {
        super(driverProvider)
        javascriptExecutor = (JavascriptExecutor) driverProvider.get()
    }

    @Override
    String getRelativePath() {
        return "/flight"
    }

    AirGlobalNavigation openAirSubMenu(int retry = DEFAULT_RETRY) {
        WebElement menuContainer
        WebDriverException ex
        // Retry to invoke mouseover function: SWACOMTT-1881
        for (int i = 0; i < retry; i++) {
            try {
                moveMouseOver(AIR_GLOBAL_NAV_LINK_CSS)
                menuContainer = waitForElement(AIR_MENU_CONTAINER, true, 5)
                return new AirGlobalNavigation(menuContainer)
            }
            catch (WebDriverException e) {
                // silently retry
                ex = e
            }
        }
        throw ex
    }

    CarGlobalNavigation openCarSubMenu(int retry = DEFAULT_RETRY) {
        WebElement menuContainer
        WebDriverException ex
        for (int i = 0; i < retry; i++) {
            try {
                moveMouseOver(CAR_GLOBAL_NAV_LINK_CSS)
                menuContainer = waitForElement(CAR_MENU_CONTAINER, true, 5)
                return new CarGlobalNavigation(menuContainer)
            }
            catch (WebDriverException e) {
                // silently retry
                ex = e
            }
        }
        throw ex
    }

    FlyingGlobalNavigation openFlyingSouthwestSubMenu(int retry = DEFAULT_RETRY) {
        WebElement menuContainer
        WebDriverException ex
        for (int i = 0; i < retry; i++) {
            try {
                moveMouseOver(FLYING_GLOBAL_NAV_LINK_CSS)
                menuContainer = waitForElement(FLYING_MENU_CONTAINER, true, 5)
                return new FlyingGlobalNavigation(menuContainer)
            }
            catch (WebDriverException e) {
                // silently retry
                ex = e
            }
        }
        throw ex
    }

    void openSpecialOffersMenu() {
        moveMouseOver(SPECIAL_OFFER_GLOBAL_NAV_LINK_CSS)
    }

    void openVacationsMenu() {
        moveMouseOver(VACATIONS_GLOBAL_NAV_LINK_CSS)
    }

    def moveMouseOver(String cssSelector) {
        return javascriptExecutor.executeScript("\$('${cssSelector}').mouseover();")
    }

    void clickOnAirLinkMenu() {
        waitForElement(AIR_TRAVEL_LINK).click()
    }

    void clickOnHotelLinkMenu() {
        waitForElement(HOTEL_LINK).click()
    }

    void clickOnCarLinkMenu() {
        waitForElement(CAR_LINK).click()
    }

    RapidRewardsGlobalNavigation openRapidRewardsSubMenu() {
        moveMouseOver("#" + RAPID_REWARDS_GLOBAL_LINK_CSS)
        return new RapidRewardsGlobalNavigation(waitForElement(RAPID_REWARDS_MENU_CONTAINER))
    }

    HotelGlobalNavigation openHotelSubMenu() {
        moveMouseOver(HOTEL_LINK_CSS)
        return new HotelGlobalNavigation(waitForElement(HOTEL_SUB_MENU_CONTAINER))
    }

    def verifyEnrollLinkIsNotPresent() {
        isElementPresent(RapidRewardsGlobalNavigation.ENROLL_TITLE) shouldBe false, "Enrroll Link is Present and should be not present"
    }

    def verifyEnrollLinkIsPresent() {
        isElementPresent(RapidRewardsGlobalNavigation.ENROLL_TITLE) shouldBe true, "Enrroll Link is not Present and should be present"
    }

    def clickOnPartnersLink() {
        waitForElement(PARTNERS_TITTLE).click()
    }

    def clickOnInternationalTravelLink() {
        waitForElement(INTERNATIONAL_TRAVEL_LINK).click()
    }

    def clickAirportInformationLink() {
        AirGlobalNavigation airGlobalNavigation = openAirSubMenu()
        airGlobalNavigation.clickOnAirportInformation()
    }

    def clickOnInfantFlyingLink() {
        AirGlobalNavigation airGlobalNavigation = openAirSubMenu()
        airGlobalNavigation.clickOnInfantFlying()
    }

    def verifyThemeParkLinkIsNotPresent() {
        isElementPresent(THEME_PARK_NAV_LINK, 3) shouldBe false, "Theme Park Tickets Primary Navigation Link was found in Special Offers submenu."
    }

    def verifyCruiseOffersNavLinkIsNotPresent() {
        isElementPresent(CRUISE_OFFERS_NAV_LINK, 3) shouldBe false, "Cruise Offers Primary Navigation Link was found in Special Offers submenu."
    }

    def verifyBookACruiseNavLinkIsNotPresent() {
        isElementPresent(VacationsGlobalNavigation.BOOK_A_CRUISE_NAV_LINK, 3) shouldBe false, "Book a Cruise Primary Navigation Link was found in Vacations submenu."
    }

    def verifyColumnsInVacationGlobalNavMenu() {
        isElementPresent(VacationsGlobalNavigation.SECOND_COLUMN_OF_LINKS, 3) shouldBe false, "A second column of links was found in Vacations submenu."
    }

    def verifyBookAShuttleNavLinkIsNotPresent() {
        isElementPresent(CarGlobalNavigation.BOOK_A_SHUTTLE_LINK, 3) shouldBe false, "Book a Shuttle link was found in Car submenu."
    }

    def verifyInfantFlyingPage() {
        shouldBeInPage(INFANT_FLYING)
    }

    def clickRRGlobalNavigationHeader() {
        waitForElement(By.id(RAPID_REWARDS_GLOBAL_LINK_CSS)).click()
    }
}