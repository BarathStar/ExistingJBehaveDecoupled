package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

public class RapidRewardsOverviewPage extends BasePage {
    private static final By HOW_TO_EARN_LINK = By.cssSelector("#how-to-earn a[href]")
    private static final By EARN_WITH_OUR_PARTNERS_BREADCRUMB = By.xpath("//li[@class='secondaryNavItemWithDot']/a[@href='/rapidrewards/earn-with-partners']")
    private static final By EARN_POINTS_WHEN_YOU_FLY_BREADCRUMB = By.xpath("//li[@class='secondaryNavItem']/a[@href='/rapidrewards/how-to-earn']")
    private static final By A_LIST_LINK = By.xpath("//a[@href='/rapidrewards/how-to-earn-alist#a-list']")
    private static final By A_LIST_PREFERRED_LINK = By.xpath("//a[@href='/rapidrewards/how-to-earn-alist-preferred#a-list-preferred']")
    private static final By EARN_POINTS_WHEN_YOU_FLY = By.xpath("//div[@class='rr_earn_submenu'/a[@href='/rapidrewards/how-to-earn]'")
    private static final By BASE_LINK = By.xpath("//a[@href='/rapidrewards/how-to-earn#base']")
    private static final By EARN_WITH_PARTNERS_LINK = By.cssSelector(".rr_earn_submenu.rr_earn_with_points a[href='/rapidrewards/earn-with-partners']")
    private static final By PROMOTIONS_LINK = By.cssSelector(".rr_marketing_nav #promotions a")
    private static final By PROMOTIONS_BREADCRUMB = By.className("secondaryNavProduct")
    private static final By RR_PROMOTIONS_TITLE = By.className("wcm_page_header_title")
    private static final String RR_PROMOTIONS_TITLE_TEXT = "Rapid Rewards Promotions"
    private static final By RR_PROMOTIONS_DESCRIPTION = By.className("wcm_page_description")
    private static final String RR_PROMOTIONS_DESCRIPTION_TEXT = "Take advantage of all the great offers available from Southwest Airlines and our Rapid Rewards Partners."
    util.Navigation navigation
    private final static String RAPID_REWARDS_OVERVIEW_PAGE = "/rapidrewards/overview"
    private static final int DEFAULT_RETRY_ATTEMPTS = 5
    private static final By BUY_POINTS_TITLE = By.className("wcm_text_header")
    private static final String BUY_POINTS_TITLE_TEXT = "Rapid Rewards Points Center"
    private static final By ABOUT_TAB = By.id("about-link")
    private static final By POINTS_CENTER_BUTTON = By.id("points-center-link")

    public RapidRewardsOverviewPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return RAPID_REWARDS_OVERVIEW_PAGE  //TODO change as needed for page validation.

    }

    def openRapidRewardOverviewPage() {
        waitForElement(By.id("globalnav_header_primary_link_rr")).click()
        verifyCurrentPageLocation()

    }

    def selectTiersAndMore() {
        selectLinkInParent("secondaryNavContainerDiv", "a", "Tiers & More")
    }

    def selectHowToEarnPage() {
        selectLinkInParent("secondaryNavContainerDiv", "a", "How to Earn")
    }

    def selectAlistPreferred() {
        List<WebElement> symbols = waitForElements(By.xpath("//div/*[@class='rr_blue_box_content_footer']/a"))
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getText().equals("A-List Preferred")) {
                def element = symbols.get(i);
                if (element != null) {
                    element.click();
                    break;
                }
            }
        }
    }

    def selectCompanionPass() {
        selectLinkInParent("tiers-more-companion-pass", "a", "Companion Pass")

    }

    def verifyHowToEarnSubNavigation() {
        verifyElementPresent("Earn With Our Partners", By.xpath("//div[@id='RRSubNav']//a[text() = 'Earn With Our Partners']"))
        verifyElementPresent("Earn With Our Partners", By.xpath("//div[@id='RRSubNav']//a[text() = 'Earn Points When You Fly']"))
    }

    def clickOnTheHowToEarnLink() {
        performClickOnTheHowToEarnWithVerification()
        verifyPage()
    }

    def performClickOnTheHowToEarnWithVerification(int retry = DEFAULT_RETRY_ATTEMPTS) {
        for (int i=0; i<retry; i++) {
            performClickAction(HOW_TO_EARN_LINK)
            if (!isElementPresent(HOW_TO_EARN_LINK)){
                return
            }
            println("HOW_TO_EARN_LINK #################### RETRYING ####################")
        }
     }

    def verifyEarnWithOurPartnersBreadcrumb() {
        isElementPresent(EARN_WITH_OUR_PARTNERS_BREADCRUMB).shouldBe true, "The Earn With Our Partners breadcrumb should be present"
    }

    def verifyAListLinkIsNotSelected() {
        isElementPresent(A_LIST_LINK).shouldBe true, "The A-List Link should be present"
    }

    def verifyAListPreferredLinkIsNotSelected() {
        isElementPresent(A_LIST_PREFERRED_LINK).shouldBe true, "The A-List Preferred link should be present"
    }

    def verifyEarnPointsWhenYouFlyLinkIsSelected() {
        verifyElementNotPresent("EARN POINTS WHEN YOU FLY", EARN_POINTS_WHEN_YOU_FLY)
    }

    def clickOnTheAListLink() {
        waitForElement(A_LIST_LINK).click()
        verifyPage()
    }

    def verifyBaseLinkIsNotSelected() {
        isElementPresent(BASE_LINK).shouldBe true, "The Base link should be present"
    }

    def clickOnTheAListPreferredLink() {
        waitForElement(A_LIST_PREFERRED_LINK).click()
        verifyPage()
    }

    def clickOnTheEarnWithPartnersLink() {
        waitForElement(EARN_WITH_PARTNERS_LINK).click()
        verifyPage()
    }

    def clickAboutTab() {
        waitForElement(ABOUT_TAB).click()
    }

    def clickPointsCenterButton() {
        waitForElement(POINTS_CENTER_BUTTON).click()
    }

    def verifyEarnPointsWhenYouFlyBreadcrumbIsPresent() {
        verifyElementPresent("Earn Points When You Fly",EARN_POINTS_WHEN_YOU_FLY_BREADCRUMB)
    }

    def verifyEarnWithOurPartnersLinkIsSelected() {
        verifyElementNotPresent("Earn With Partners", EARN_WITH_PARTNERS_LINK)
    }

    def verifyBaseLinkIsNotPresent() {
        verifyElementNotPresent("Base Link", BASE_LINK)
    }

    def verifyAListLinkIsNotPresent() {
        verifyElementNotPresent("A-List Link", A_LIST_LINK)
    }

    def verifyAListPreferredLinkIsNotPresent() {
        verifyElementNotPresent("A-List Preferred Link", A_LIST_PREFERRED_LINK)
    }

    def clickOnThePromotionsLink() {
        waitForElement(PROMOTIONS_LINK).click()
        verifyPage()
    }

    def verifyPromotionsBreadcrumbIsPresent() {
        verifyElementPresent("Promotions Breadcrumb",PROMOTIONS_BREADCRUMB)
    }

    def verifyRapidRewardsPromotionsSection() {
        verifyElementPresent("Rapid Rewards Promotions title", RR_PROMOTIONS_TITLE)
        verifyElementPresent("Rapid Rewards Promotions description", RR_PROMOTIONS_DESCRIPTION)
        waitForElement(RR_PROMOTIONS_TITLE).text.shouldContain RR_PROMOTIONS_TITLE_TEXT, "The title should be ${RR_PROMOTIONS_TITLE_TEXT}"
        waitForElement(RR_PROMOTIONS_DESCRIPTION).text.shouldContain RR_PROMOTIONS_DESCRIPTION_TEXT, "The title should be ${RR_PROMOTIONS_DESCRIPTION_TEXT}"
    }

    def verifyBuyAndTransferPointsSection() {
        waitForElement(BUY_POINTS_TITLE).text.shouldContain BUY_POINTS_TITLE_TEXT, "The title should be ${BUY_POINTS_TITLE_TEXT}"
    }

}


