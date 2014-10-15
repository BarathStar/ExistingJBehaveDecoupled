/*
 * Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.
 */

package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * Represents the Rapid Rewards Partners Page, this includes all the tabs in it such as Credit Cards, Dinning, Hotel, etc.
 */
public class RapidRewardsPartnersPage extends BasePage{

    static final private String RRMALL_URL = "/rapidrewards/partners-retail-details?name=rapid-rewards-mall"
    static final private String CREDITCARDS_CSS = "#credit-and-debit-cards a"
    static final private String DINNING_CSS = "#partners-dining a"
    static final private String HOTELS_CSS = "#partners-hotels a"
    static final private String RENTALCARS_CSS = "#partners-cars a"
    static final private String RETAILPARTNERS_CSS = "#partners-retail a"
    static final private String RETAILPARTNERS_RRMALL_LINK_CSS = '.wcm_grid_cell_text h4 a[href="/rapidrewards/partners-retail-details?name=rapid-rewards-mall"]'
    static final private String RETAILPARTNERS_RRMALL_IMAGE_CSS = ".wcm_grid_cell_container img[title='Rapid Rewards Mall®']"
    static final private String RETAILPARTNERS_RRMALL_LEARNMORE_CSS = 'a.orange_button[href="/rapidrewards/partners-retail-details?name=rapid-rewards-mall"]'
    static final private String RR_SHOPPING_PAGE_TITLE = "Partners"
    static final private String RR_POINTS_PAGE_TITLE = "Points Center"
    static final private String MORE_REWARDS_PAGE_TITLE = "Redeem for More Rewards"
    static final private By RENTALCARS_CSS_RRMALL_IMG_LIST = By.cssSelector("swa_feature_rapidRewards_partners_grid_inner_inner")
    static final private By RENTAL_CARS_RRMALL_IMG = By.cssSelector(".swa_feature_rapidRewards_partners_grid_inner_inner a")
    static final private By RAPID_REWARDS_PARTNERS_CATEGORYS_CSS = By.cssSelector(".swa_feature_rapidRewards_partners_categories_item a")
    static final private By RAPID_REWARDS_PARTNERS_NAMED_CSS = By.cssSelector(".swa_feature_rapidRewards_partners_subcategories_item a")

    static final private By RR_MALL_CONTAINER = By.className("rr_content")

    public RapidRewardsPartnersPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/rapidrewards/partners')
    }

    private WebElement findContainer() {
        return waitForElement(RR_MALL_CONTAINER)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def open() {
        super.open()
    }

    void viewCreditCardsTab() {
        WebElement rrMallContainer = findContainer()
        rrMallContainer.findElement(By.cssSelector(CREDITCARDS_CSS)).click()
    }

    void viewDinningTab() {
        WebElement rrMallContainer = findContainer()
        rrMallContainer.findElement(By.cssSelector(DINNING_CSS)).click()
    }

    void viewHotelsTab() {
        WebElement rrMallContainer = findContainer()
        rrMallContainer.findElement(By.cssSelector(HOTELS_CSS)).click()
    }

    void viewRentalCarsTab() {
        WebElement rrMallContainer = findContainer()
        rrMallContainer.findElement(By.cssSelector(RENTALCARS_CSS)).click()
    }

    void viewRetailPartnersTab() {
        WebElement rrMallContainer = findContainer()
        rrMallContainer.findElement(By.cssSelector(RETAILPARTNERS_CSS)).click()
    }

    //TODO write the logic to verify the other sections in Retail Partners Tab
    def verifyRRMallSection() {
        isElementPresent(By.cssSelector(RETAILPARTNERS_RRMALL_LINK_CSS))
    }

    //TODO write the logic to click the other sections in Retail Partners Tab
    void rrMallSectionLinkClicker(){
        WebElement rrMallContainer = findContainer()
        rrMallContainer.findElement(By.cssSelector(RETAILPARTNERS_RRMALL_LINK_CSS)).click()
    }

    void rrMallSectionImageClicker(){
        WebElement rrMallContainer = findContainer()
        rrMallContainer.findElement(By.cssSelector(RETAILPARTNERS_RRMALL_IMAGE_CSS)).click()
    }

    void rrMallSectionLearnMoreClicker(){
        WebElement rrMallContainer = findContainer()
        rrMallContainer.findElement(By.cssSelector(RETAILPARTNERS_RRMALL_LEARNMORE_CSS)).click()
    }

    private void verifyRedirectionURL(String linkExpected) {
        getCurrentUrl().shouldContain linkExpected, "Expected URL should contain $linkExpected."
    }

    //TODO write the logic to verify the URL of the other sections in Retail Partners Tab
    void verifyRRMallSectionURL() {
        verifyRedirectionURL(RRMALL_URL)
    }

    def verifyOnRRShopping() {
        getTitle().shouldBe RR_SHOPPING_PAGE_TITLE, "You should be on Rapid Rewards Shopping Page"
    }

    def verifyOnRRPoints() {
        getTitle().shouldBe RR_POINTS_PAGE_TITLE, "You should be on Rapid Rewards Point Page"
    }

    def verifyOnMoreRewardsPage() {
        getTitle().shouldBe MORE_REWARDS_PAGE_TITLE, "You should be on More Rewards page"
    }

    def selectImgVendorCar(String vendor) {
        List<WebElement> listCars = waitForElements(RENTAL_CARS_RRMALL_IMG)
        for (int i = 0; i < listCars.size(); i++) {
            if (listCars.get(i).getAttribute("href").toLowerCase().contains(vendor.toLowerCase())) {
                listCars[i].click()
                break
            }
        }
    }

    def clickOnRapidRewardsPartnersCategory(String cat) {
        List<WebElement> listCat = waitForElements(RAPID_REWARDS_PARTNERS_CATEGORYS_CSS)
        for (int i = 0; i < listCat.size(); i++) {
            if (listCat.get(i).getAttribute("href").toLowerCase().contains(cat.toLowerCase())) {
                listCat[i].click()
                break
            }
        }
    }

    def clickOnRapidRewardsPartners(String partner) {
        List<WebElement> listPartners = waitForElements(RAPID_REWARDS_PARTNERS_NAMED_CSS)
        for (int i = 0; i < listPartners.size(); i++) {
            if (listPartners.get(i).getAttribute("title").toLowerCase().contains(partner.toLowerCase())) {
                listPartners[i].click()
                break
            }
        }
    }
}