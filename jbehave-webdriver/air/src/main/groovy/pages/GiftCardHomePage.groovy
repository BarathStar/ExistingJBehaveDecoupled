package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import steps.conditional.ToggleGlobalNav
import static org.junit.Assert.fail

public class GiftCardHomePage extends BasePage {

    private final By TERMS_AND_CONDITIONS_LINK = By.id("giftCards_footerMenu_termsAndConditions")
    private final String CHECK_YOUR_BALANCE_XPATH = "//a[@title='Check Your Balance']"
    private final String CORPORATE_VOLUME_SALES_XPATH = "//a[@title='Corporate & Volume sales']"

    private final By CONTINUE_BUTTON_CLASS = By.className("swa_buttons_continueLink")
    private static final By SECURITY_LINK = By.xpath("//a[@title='Security']")
    private static final By FAQ_LINK_XPATH = By.xpath("//a[@title='FAQs']")
    private static final By BREADCRUMBS = By.id("breadcrumbs")
    private static final By FLIGHT_STATUS_NOTIFICATION_TAB_ONE = By.id("swa_feature_flightStatusNotification_tab_1")
    private static final String PATH = "gift-card/home.html"
    private static final String SOUTHWEST_GIFT_CARDS_HOME_TITLE = "Southwest Gift Cards"
	private static final String SOUTHWEST_GIFT_CARDS_HOME_URL = "/home.html?int=GNAVSWASOUTHWESTGIFTCARD"
    private static final String SOUTHWEST_GIFT_CARDS_HOME_URL_GLOBAL_NAV = "/home.html?clk=GSUBNAV-PLAN-AD2-GC"

    public GiftCardHomePage(WebDriverProvider driverProvider) {
        super(driverProvider, "/${PATH}")
    }

    String getRelativePath() {
        return PATH
    }

    def verifyLocationAndFooterLinks(){
        verifyCurrentPageLocation()
        isElementPresent(SECURITY_LINK).shouldBe true , "The Security link is not present"
        isElementPresent(FAQ_LINK_XPATH).shouldBe true , "The FAQs link is not present"
        isElementPresent(TERMS_AND_CONDITIONS_LINK).shouldBe true , "The Security Terms And Conditions link is not present"
    }

    def verifyTheBreadcrumByDefault(){
        isElementPresent(By.className("swa_nav_globalNav_vertical_span")).shouldBe true , "The drop-down menu Travel Extras is not present"
        isElementPresent(By.className("swa_nav_globalNav_dropdown_inner")).shouldBe false , "The drop-down menu Travel Extras is displayed"
        isElementPresent(By.className("swa_nav_globalNav_horizontal_span")).shouldBe true , "The select element on breadcrum is not present"
        verifyBreadcrumSelected()
    }

    public boolean verifyBreadcrumSelected(String selectedOption){
        WebElement southwestgiftcard = waitForElement(BREADCRUMBS).findElement(By.className("swa_nav_globalNav_horizontal_span"))
        String select = southwestgiftcard.getText()
        select.shouldBe "southwestgiftcard", "The select element on breadcrum (${select}) is not expected (southwestgiftcard)"
        if(southwestgiftcard.getTagName().equalsIgnoreCase("a")){
            fail("The select element on breadcrum (southwestgiftcard) is a link")
        }
    }

    def verifyTheTabsByDefault(){
        isElementPresent(By.id("swa_feature_flightStatusNotification_tab_1")).shouldBe true , "The Buy a soutwest giftcard tab option is not present"
        isElementPresent(By.xpath(CHECK_YOUR_BALANCE_XPATH)).shouldBe true , "The Check Your Balance tab option is not present"
        isElementPresent(By.xpath(CORPORATE_VOLUME_SALES_XPATH)).shouldBe true , "The Corporate & Volume sales tab option is not present"
        verifyTabOptionSelected()
    }

    private void verifyTabOptionSelected(){
        String select = waitForElement(FLIGHT_STATUS_NOTIFICATION_TAB_ONE).getText()
        select.shouldBe "Buy a southwestgiftcard", "The select element on Tab (${select}) is not expected (Buy a soutwestgiftcard)"
    }

    def verifyOnSouthWestGiftCardHomePage() {
        getTitle().shouldBe SOUTHWEST_GIFT_CARDS_HOME_TITLE, "You should be on SouthWest Gift Card"

        if (ToggleGlobalNav.isOn()) {
            shouldBeInPage(SOUTHWEST_GIFT_CARDS_HOME_URL_GLOBAL_NAV)
        } else {
            shouldBeInPage(SOUTHWEST_GIFT_CARDS_HOME_URL)
        }

		verifyPage()
    }

    void clickOnSecurityLink(){
        waitForElement(SECURITY_LINK).click()
    }

    void clickOnFAQsLink(){
        waitForElement(FAQ_LINK_XPATH).click()
    }

    void clickOnTermsAndConditionsLink(){
        waitForElement(TERMS_AND_CONDITIONS_LINK).click()
    }

    void clickOnContinueButton() {
        waitForElement(CONTINUE_BUTTON_CLASS).click()
    }
}
