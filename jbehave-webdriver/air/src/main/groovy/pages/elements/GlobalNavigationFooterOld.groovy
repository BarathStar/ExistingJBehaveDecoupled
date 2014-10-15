/* Copyright (c) 2014, Southwest Airlines Co. All rights reserved. */
package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.BasePage

/**
 * Accessors and utility methods for the old Global Navigation footer
 */
class GlobalNavigationFooterOld extends BasePage {

    private static final By TOGGLE_MORE_LINK = By.id("globalnav_footer_site_links_morelinks_toggle")
    private static final TOGGLE_MORE_LINKS_CLOSED = "globalnav_footer_site_links_morelinks_toggle globalnav_footer_site_links_morelinks_toggle_closed"
    private static final By SOUTHWEST_GIFT_CARD_LINK = By.cssSelector("a[id='southwest-giftcard-footer']")
    private static final By SUPPLIER_INFORMATION_LINK = By.cssSelector("a[id='supplier-information']")
    private static final By NEED_HELP_LINK = By.id("swa_recoveryUserData_footer")
    private static final ENROLL_RR_LINK = By.id("enroll-in-rapid-rewards")
    private static final By CAREERS_LINK = By.cssSelector("a[id='careers']")
    private static final By WHY_FLY_LINK = By.cssSelector("a[id='why-fly-southwest']")
    private static final By SOUTHWEST_MERCHANDISE_LINK = By.cssSelector("a[id='southwest-merchandise']")
    private static final By LOST_AND_FOUND_LINK = By.cssSelector("a[id='lost-and-found']")
    private static final String LOST_AND_FOUND_PAGE_TITLE = "Southwest Airlines: Lost & Found"
    private static final String FREEDOM_SHOP_PAGE_TITLE = "Home page - Freedom Shop"
    private static final By ADVERTISE_WITH_SOUTHWEST_LINK = By.cssSelector("a[id='advertise-with-southwest']")
    private static final String SPECIAL_ASSISTANCE_PATH = "/html/customer-service/unique-travel-needs/customers-with-disabilities-pol.html"
    private static final By SPECIAL_ASSISTANCE_LINK = By.id("special-assistance")

    public GlobalNavigationFooterOld(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    @Override
    public String getRelativePath() {
        return ""
    }

    void ensureThatLinksAreExpandedOnFooter() {
        WebElement toggleLink = waitForElement(TOGGLE_MORE_LINK)
        if (toggleLink.getAttribute("class") == TOGGLE_MORE_LINKS_CLOSED) {
            toggleLink.click()
        }
    }

    void clickOnSouthwestGiftCardLink() {
        ensureThatLinksAreExpandedOnFooter()
        waitForElement(SOUTHWEST_GIFT_CARD_LINK).click()
    }

    void clickOnSupplierInformationLink() {
        ensureThatLinksAreExpandedOnFooter()
        waitForElement(SUPPLIER_INFORMATION_LINK).click()
    }

    void clickOnNeedHelpLogginLink() {
        ensureThatLinksAreExpandedOnFooter()
        waitForElement(NEED_HELP_LINK).click()
    }

    void clickOnCareerLink() {
        ensureThatLinksAreExpandedOnFooter()
        waitForElement(CAREERS_LINK).click()
    }

    void clickOnWhyFlyLink() {
        ensureThatLinksAreExpandedOnFooter()
        waitForElement(WHY_FLY_LINK).click()
    }

    def verifyEnrollLinkIsPresent() {
        isElementPresent(ENROLL_RR_LINK).shouldBe true, "The Enroll link in the footer should be present"
    }

    def verifyEnrollLinkIsNotPresent() {
        isElementPresent(ENROLL_RR_LINK).shouldBe false, "The Enroll link in the footer should NOT be present"
    }

    def clickSouthWestMerchandiseLink() {

        String winHandleBefore = webDriver().getWindowHandle()
        waitForElement(SOUTHWEST_MERCHANDISE_LINK).click()

        switchToOpenWindow(winHandleBefore, FREEDOM_SHOP_PAGE_TITLE)
    }

    def clickOnLostAndFoundLink() {

        String winHandleBefore = webDriver().getWindowHandle()
        waitForElement(LOST_AND_FOUND_LINK).click()

        switchToOpenWindow(winHandleBefore, LOST_AND_FOUND_PAGE_TITLE)
    }

    def clickOnAdvertiseWithSouthwestLinkFooter() {
        ensureThatLinksAreExpandedOnFooter()
        waitForElement(ADVERTISE_WITH_SOUTHWEST_LINK).click()
    }

    def clickOnSpecialAssistanceLink() {
        ensureThatLinksAreExpandedOnFooter()
        waitForElement(SPECIAL_ASSISTANCE_LINK).click()
    }

    def verifySpecialAssistancePage() {
        shouldBeInPage(SPECIAL_ASSISTANCE_PATH)
    }
}