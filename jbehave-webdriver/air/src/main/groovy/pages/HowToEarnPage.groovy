package pages

import pages.BasePage
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class HowToEarnPage extends BasePage {
    private static final String PATH = '/rapidrewards/how-to-earn'
    private static final By TIERS_AND_MORE_LINK = By.cssSelector("#tiers-more-alist a")
    private static final By A_LIST_BREAD_CRUMB = By.id('tiers-more-alist')
    private static final By A_LIST_PREFERRED_BREAD_CRUMB = By.id('tiers-more-alist-preferred')
    private static final By A_COMPANION_PASS_BREAD_CRUMB = By.id('tiers-more-companion-pass')
    private static final By A_LIST_BREAD_CRUMB_HREF = By.xpath("//li[@id='tiers-more-alist'/a[@href='/rapidrewards/tiers-more-alist']")
    private static final By A_LIST_PREFERREED_BREAD_CRUMB_HREF = By.xpath("//li[@id='tiers-more-alist-preferred'/a[@href='/rapidrewards/tiers-more-alist-preferred']")
    private static final By A_COMPANION_PASS_BREAD_CRUMB_HREF = By.xpath("//li[@id='tiers-more-companion-pass'/a[@href='/rapidrewards/tiers-more-companion-pass']")
    private static final By TITLE_FORM = By.className('wcm_text_header')
    private static final By SUB_TITLE_FORM = By.className('wcm_text_header_section_button_container')
    private static final By A_CSS_SELECTOR = By.cssSelector("a")
    private static final By H3_CSS_SELECTOR = By.cssSelector("h3")
    private static final By SPAN_CSS_SELECTOR = By.cssSelector("span")

    public HowToEarnPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    def clickOnTiersAndMoreLink() {
        waitForElement(TIERS_AND_MORE_LINK).click()
        verifyPage()
    }

    def clickOnAListPreferredCard() {
        waitForElement(A_LIST_PREFERRED_BREAD_CRUMB).findElement(A_CSS_SELECTOR).click()
    }

    def clickOnACompanionPassCard() {
        waitForElement(A_COMPANION_PASS_BREAD_CRUMB).findElement(A_CSS_SELECTOR).click()
    }

    def verifyAListBreadcrumbLink() {
        isElementPresent(A_LIST_BREAD_CRUMB).shouldBe true, "The A List link Should be shown"
    }

    def verifyAListPreferredBredcrumbLink() {
        isElementPresent(A_LIST_PREFERRED_BREAD_CRUMB).shouldBe true, "The A List Preferred link Should be shown"
    }

    def verifyCompanionPassBredcrumbLink() {
        isElementPresent(A_COMPANION_PASS_BREAD_CRUMB).shouldBe true, "The A Companion Pass link Should be shown"
    }

    def verifyAListBreadcrumbLinkIsSelected() {
        verifyElementNotPresent("A-LIST BREDCRUMB LINK", A_LIST_BREAD_CRUMB_HREF)
    }

    def verifyAListPreferredBreadcrumbLinkIsSelected() {
        verifyElementNotPresent("A-LIST PREFERRED BREDCRUMB LINK", A_LIST_PREFERREED_BREAD_CRUMB_HREF)
    }

    def verifyCompanionPassBreadcrumbLinkIsSelected() {
        verifyElementNotPresent("COMPANION PASS BREDCRUMB LINK", A_COMPANION_PASS_BREAD_CRUMB_HREF)
    }

    def verifyTitleIsAListStatus() {
        verifyTitle('A-List Status')
    }

    def verifyTitleIsAListPreferredStatus() {
        verifyTitle('A-List Preferred Status')
    }

    def verifyTitleIsCompanionPassStatus() {
        verifyTitle('Companion Pass Status')
    }

    def verifySubTitleIsAListProgram() {
        verifySubTitle('A-List Program Rules')
    }

    def verifySubTitleIsAListPreferredProgram() {
        verifySubTitle('A-List Preferred Program Rules')
    }

    def verifySubTitleIsCompanionPassProgramRules() {
        verifySubTitle('Companion Pass Program Rules')
    }

    private verifyTitle(String titleText) {
        WebElement title = waitForElement(TITLE_FORM).findElement(H3_CSS_SELECTOR)
        title.getText().contains(titleText).shouldBe true, "The "+titleText+" title Should be shown"
    }

    private verifySubTitle(String subTitleText) {
        WebElement subTitle = waitForElement(SUB_TITLE_FORM).findElement(SPAN_CSS_SELECTOR)
        subTitle.getText().contains(subTitleText).shouldBe true, "The "+subTitleText+" title Should be shown"
    }

    def verifyIamOnTheCorrectPage(String url) {
        getCurrentUrl().contains(url).shouldBe true, "The current page should be "+url
    }

    @Override
    public String getRelativePath() {
        return PATH
    }
}
