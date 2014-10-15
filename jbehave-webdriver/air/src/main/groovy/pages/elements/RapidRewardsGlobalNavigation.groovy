/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.openqa.selenium.WebElement
import org.openqa.selenium.By

/**
 * Knows how to access to the links under the Rapid Rewards Global Navigation Menu
 */
class RapidRewardsGlobalNavigation {

    private WebElement container
    static final By MY_ACCOUNT_TITLE = By.xpath("//table/tbody/tr/td/ul/li/a[@id='my-account']")
    static final By ENROLL_TITLE = By.xpath("//table/tbody/tr/td/ul/li/a[@id='enroll']")
    private static final By BUY_POINTS_TRAVEL_LINK = By.xpath("//table/tbody/tr/td/ul/li/a[@id='buy-points']")
    private static final By MORE_REWARDS_LINK= By.xpath("//table/tbody/tr/td/ul/li/a[@id='more-rewards']")
    private static final By RR_SHOPPING_LINK = By.xpath("//table/tbody/tr/td/ul/li/a[@id='rapid-rewards-shopping']")

    RapidRewardsGlobalNavigation(WebElement theContainer) {
        this.container = theContainer
    }

    def clickOnMyAccountLink() {
        container.findElement(MY_ACCOUNT_TITLE).click()
    }

    def clickOnEnrollLink(){
        container.findElement(ENROLL_TITLE).click()
    }

    def clickOnBuyPointsTravelLink() {
        container.findElement(BUY_POINTS_TRAVEL_LINK).click()
    }

    def clickOnMoreRewardsLink() {
        container.findElement(MORE_REWARDS_LINK).click()
    }

    def clickOnRapidRewardsShoppingLink() {
        container.findElement(RR_SHOPPING_LINK).click()
    }

}
