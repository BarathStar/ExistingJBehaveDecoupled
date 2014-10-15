/* Copyright (c) 2014, Southwest Airlines Co. All rights reserved. */
package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import pages.BasePage

/**
 * Accessors and utility methods for the new Global Navigation footer
 */
class GlobalNavigationFooterNew extends BasePage {

    //first row
    private static final String FOOTER_LINK_CONTACT_US = "/contact-us/contact-us.html?clk=GFOOTER-CUSTOMER-CONTACT-US"
    private static final String FOOTER_LINK_CLICK_N_SAVE = "/html/email/click_n_save_signup.html?clk=GFOOTER-CNS-ENROLL"
    private static final String FOOTER_LINK_CUSTOMER_SERVICE = "/html/customer-service/index.html?clk=GFOOTER-SERVICE"
    private static final String FOOTER_LINK_FAQ = "/html/customer-service/faqs.html?clk=GFOOTER-FAQ"
    private static final String FOOTER_CNS_ENROLLMENT = "/html/email/click_n_save_signup.html?clk=GFOOTER-CNS-ENROLL"
    private static final String FOOTER_LINK_NUTS_ABOUT_SW_BLOG = "/jp/blog.html?clk=GFOOTER-BLOG"
    private static final String FOOTER_LINK_MOBILE_APPS = "/html/air/products/mobile.html?clk=GFOOTER-APPS"

    // About Southwest links
    private static final String FOOTER_LINK_ABOUT_SW = "/html/about-southwest/index.html?clk=GFOOTER-ABOUT-ABOUT"
    private static final String FOOTER_WHATS_NEW = "/html/about-southwest/whats-new.html?clk=GFOOTER-ABOUT-WHATS-NEW"
    private static final String FOOTER_SW_CITIZENSHIP = "/html/southwest-difference/southwest-citizenship/index.html?clk=GFOOTER-ABOUT-DIFFERENCE-SOUTHWEST-CARES"
    private static final String FOOTER_CUSTOMER_COMMITMENTS = "/html/about-southwest/index.html?clk=GFOOTER-ABOUT-CUSTOMER-COMMITMENTS"
    private static final String FOOTER_SW_MERCHANDISE = "https://www.swafreedomshop.com/"//external
    private static final String FREEDOM_SHOP_PAGE_TITLE = "Home page - Freedom Shop"
    private static final String FOOTER_CAREERS = "/html/about-southwest/careers/index.html?clk=GFOOTER-ABOUT-CAREERS"
    private static final String FOOTER_SUPPLIER_INFORMATION = "/html/southwest-difference/southwest-citizenship/supplier-information/index.html?clk=GFOOTER-ABOUT-SUPPLIER"
    private static final String FOOTER_ADVERTISE_WITH_SW = "/html/about-southwest/advertise/index.html?clk=GFOOTER-ABOUT-ADVERTISE"
    //Flying SW
    private static final String FOOTER_FLYING_SW = "/html/travel-experience/index.html?clk=GFOOTER-FLY"
    private static final String FOOTER__WHY_FLY_SW = "/html/why-fly-southwest/index.html?clk=GFOOTER-FLY-WHY"
    private static final String FOOTER_POPULAR_ROUTES = "/html/air/routes/index.html?clk=GFOOTER-FLY-ROUTES"
    private static final String FOOTER_INTERNATIONAL_TRAVEL = "/international-travel?clk=GFOOTER-FLY-INTL"
    private static final String FOOTER_AIRPORT_INFORMATION = "/html/air/airport-information.html?clk=GFOOTER-FLY-AIRPORTINFO"
    private static final String FOOTER_FLIGHT_SCHEDULES = "/flight/request-schedule.html?clk=GFOOTER-FLY-FLTSCHEDULES"
    //SW Products
    private static final String FOOTER_SW_PRODUCTS = "/html/air/products/index.html?clk=GFOOTER-PRODUCTS"
    private static final String FOOTER_EARLY_BIRD_CHECK_IN = "/flight/early-bird-retrieve-reservation.html?clk=GFOOTER-PRODUCTS-EARLYBIRD&forceNewSession=yes"
    private static final String FOOTER_BUSINESS_SELECT = "/html/air/products/business-select.html?clk=GFOOTER-PRODUCTS-BUSINESS-SELECT"
    private static final String FOOTER_SW_GIFTCARD = "/gift_cards/gift_cards.html?clk=GFOOTER-PRODUCTS-SOUTHWESTGIFTCARD"
    private static final String FOOTER_WIFI = "/html/air/products/wifi-access.html?clk=GFOOTER-PRODUCTS-WIFI"
    private static final String FOOTER_BUSINESS_TRAVEL_GROUPS = "/html/air/business-groups/index.html?clk=GFOOTER-PRODUCTS-BUSINESS-TRAVEL-GROUPS"
    private static final String FOOTER_CHARTER_SERVICES = "/html/travel-tools/charter.html?clk=GFOOTER-PRODUCTS-CHARTER"
    // Customer Service
    private static final String FOOTER_LOST_AND_FOUND = "https://live.lostandfound.aero/client/southwest/landing.do?clk=GFOOTER-CUSTOMER-LOST-FOUND" //external
    private static final String FOOTER_CUSTOMER_SERVICE = "/html/customer-service/index.html?clk=GFOOTER-CUSTOMER-SERVICE"
    private static final String FOOTER_FAQ = "/html/customer-service/faqs.html?clk=GFOOTER-CUSTOMER-FAQ"
    private static final String FOOTER_SPECIAL_ASSISTANCE = "/html/customer-service/unique-travel-needs/customers-with-disabilities-pol.html?clk=GFOOTER-CUSTOMER-ASSISTANCE"
    private static final String FOOTER_CUSTOMER_of_SIZE = "/html/customer-service/extra-seat/?clk=GFOOTER-CUSTOMER-COS"
    private static final String FOOTER_TRAVELING_WITH_INFANTS = "/html/customer-service/family/baby-on-board-pol.html?clk=GFOOTER-CUSTOMER-INFANT"
    private static final String FOOTER_TRAVELING_WITH_PETS = "/html/customer-service/traveling-with-animals/pets/index-pol.html?clk=GFOOTER-CUSTOMER-PET"
    private static final String FOOTER_PURCHASING_REFUNDS = "/html/customer-service/purchasing-and-refunds/index.html?clk=GFOOTER-CUSTOMER-PURCH-REFUND"
    private static final String LOST_AND_FOUND_PAGE_TITLE = "Southwest Airlines: Lost & Found"
    private static final String FOOTER_BAGGAGE_POLICES = "/html/customer-service/baggage/index.html?clk=GFOOTER-CUSTOMER-BAGS"

    private Set<String> footerContactLinks;
    private Set<String> footerPartOneGeneralLinks;
    private Set<String> footerPartTwoGeneralLinks;


    public GlobalNavigationFooterNew(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    private void createFooterContactLinksSet() {
        footerContactLinks = new LinkedHashSet<String>()

        // Contacts links
        footerContactLinks.add(FOOTER_LINK_CONTACT_US)
        footerContactLinks.add(FOOTER_LINK_CLICK_N_SAVE)
        footerContactLinks.add(FOOTER_LINK_CUSTOMER_SERVICE)
        footerContactLinks.add(FOOTER_LINK_FAQ)
        footerContactLinks.add(FOOTER_CNS_ENROLLMENT)
        footerContactLinks.add(FOOTER_LINK_NUTS_ABOUT_SW_BLOG)
        footerContactLinks.add(FOOTER_LINK_MOBILE_APPS)
    }

    private void createFooterPartOneGeneralLinksSet() {
        footerPartOneGeneralLinks = new LinkedHashSet<String>()

        // About Southwest links
        footerPartOneGeneralLinks.add(FOOTER_LINK_ABOUT_SW)
        footerPartOneGeneralLinks.add(FOOTER_WHATS_NEW)
        footerPartOneGeneralLinks.add(FOOTER_SW_CITIZENSHIP)
        footerPartOneGeneralLinks.add(FOOTER_CUSTOMER_COMMITMENTS)
        footerPartOneGeneralLinks.add(FOOTER_SW_MERCHANDISE)   // external
        footerPartOneGeneralLinks.add(FOOTER_CAREERS)
        footerPartOneGeneralLinks.add(FOOTER_SUPPLIER_INFORMATION)
        footerPartOneGeneralLinks.add(FOOTER_ADVERTISE_WITH_SW)
        //Flying SW
        footerPartOneGeneralLinks.add(FOOTER_FLYING_SW)
        footerPartOneGeneralLinks.add(FOOTER__WHY_FLY_SW)
        footerPartOneGeneralLinks.add(FOOTER_POPULAR_ROUTES)
        footerPartOneGeneralLinks.add(FOOTER_INTERNATIONAL_TRAVEL)
        footerPartOneGeneralLinks.add(FOOTER_AIRPORT_INFORMATION)
        footerPartOneGeneralLinks.add(FOOTER_FLIGHT_SCHEDULES)
    }

    private void createFooterPartTwoGeneralLinksSet() {
        footerPartTwoGeneralLinks = new LinkedHashSet<String>()

        //SW Products
        footerPartTwoGeneralLinks.add(FOOTER_SW_PRODUCTS)
        footerPartTwoGeneralLinks.add(FOOTER_EARLY_BIRD_CHECK_IN)
        footerPartTwoGeneralLinks.add(FOOTER_BUSINESS_SELECT)
        footerPartTwoGeneralLinks.add(FOOTER_SW_GIFTCARD)
        footerPartTwoGeneralLinks.add(FOOTER_WIFI)
        footerPartTwoGeneralLinks.add(FOOTER_BUSINESS_TRAVEL_GROUPS)
        footerPartTwoGeneralLinks.add(FOOTER_CHARTER_SERVICES)
        // Customer Service
        footerPartTwoGeneralLinks.add(FOOTER_CUSTOMER_SERVICE)
        footerPartTwoGeneralLinks.add(FOOTER_FAQ)
        footerPartTwoGeneralLinks.add(FOOTER_SPECIAL_ASSISTANCE)
        footerPartTwoGeneralLinks.add(FOOTER_CUSTOMER_of_SIZE)
        footerPartTwoGeneralLinks.add(FOOTER_TRAVELING_WITH_INFANTS)
        footerPartTwoGeneralLinks.add(FOOTER_TRAVELING_WITH_PETS)
        footerPartTwoGeneralLinks.add(FOOTER_PURCHASING_REFUNDS)
        footerPartTwoGeneralLinks.add(FOOTER_BAGGAGE_POLICES)
    }

    def clickFooterContactsLinks() {
        createFooterContactLinksSet()
    }

    def clickFooterPartOneGeneralLinks() {
        createFooterPartOneGeneralLinksSet()
    }

    def clickFooterPartTwoGeneralLinks() {
        createFooterPartTwoGeneralLinksSet()
    }

    def validateNoFooterContactsLinksErrors() {
        clickAndValidateFooterLinks(footerContactLinks)
    }

    def validateNoFooterPartOneGeneralLinksErrors() {
        clickAndValidateFooterLinks(footerPartOneGeneralLinks)
    }

    def validateNoFooterPartTwoGeneralLinksErrors() {
        clickAndValidateFooterLinks(footerPartTwoGeneralLinks)
    }

    def clickOnInfantFlyingLink() {
        waitForElement(createLinkElement(FOOTER_TRAVELING_WITH_INFANTS)).click()
    }

    def clickAirportInformationLink() {
        waitForElement(createLinkElement(FOOTER_AIRPORT_INFORMATION)).click()
    }

    def verifyInfantFlyingPage() {
        shouldBeInPage(FOOTER_TRAVELING_WITH_INFANTS)
    }

    private void clickAndValidateFooterLinks(Set<String> subMenuSetLinks) {
        for (String key : subMenuSetLinks) {

            waitForElement(createLinkElement(key)).click()
            checkNoOops()
            clickBrowserBackButton()
        }
    }

    @Override
    public String getRelativePath() {
        return ""
    }

    void clickOnSouthwestGiftCardLink() {
        waitForElement(createLinkElement(FOOTER_SW_GIFTCARD)).click()
    }

    void clickOnSupplierInformationLink() {
        waitForElement(createLinkElement(FOOTER_SUPPLIER_INFORMATION)).click()
    }

    void clickOnCareerLink() {
        waitForElement(createLinkElement(FOOTER_CAREERS)).click()
    }

    def clickSouthWestMerchandiseLink() {
        waitForElement(createLinkElement(FOOTER_SW_MERCHANDISE)).click()

        String winHandleBefore = webDriver().getWindowHandle()
        switchToOpenWindow(winHandleBefore, FREEDOM_SHOP_PAGE_TITLE)
    }

    def clickOnLostAndFoundLink() {
        String winHandleBefore = webDriver().getWindowHandle()
        waitForElement(createLinkElement(FOOTER_LOST_AND_FOUND)).click()

        switchToOpenWindow(winHandleBefore, LOST_AND_FOUND_PAGE_TITLE)
    }

    def clickOnAdvertiseWithSouthwestLinkFooter() {
        waitForElement(createLinkElement(FOOTER_ADVERTISE_WITH_SW)).click()
    }

    def clickOnSpecialAssistanceLink() {
        waitForElement(createLinkElement(FOOTER_SPECIAL_ASSISTANCE)).click()
    }

    def verifySpecialAssistancePage() {
        clickOnSpecialAssistanceLink()
    }
}