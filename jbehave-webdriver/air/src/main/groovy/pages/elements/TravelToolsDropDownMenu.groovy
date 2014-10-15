/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements

import org.jbehave.web.selenium.WebDriverProvider

import pages.BasePage
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * This class Represents the travel tools drop down menu for the Global Navigation Header
 */

class TravelToolsDropDownMenu extends BasePage {

    private static final String VIEW_ALL_TRAVEL_TOOLS_LINK_XPATH = "//a[@id = 'view-all-travel-tools']"
    private static final String SOUTHWEST_GIFT_CARD_BALANCE_LINK_XPATH = "//a[@id = 'southwestgiftcard-balance']"
    private static final By MY_SOUTHWEST_ACCOUNT_LINK= By.xpath("//a[@id = 'mySouthwest-account']")

    private static final By TRAVEL_TOOLS_DROP_DOWN = By.id("globalnav_header_utility_travel_tools")
    private static final By TRAVEL_TOOLS_CONTAINER = By.id('globalnav_header_utility_travel_tools_hover_inner_container')
    private static final By TRAVEL_FUND_BALANCE = By.xpath("//a[@id= 'view-travel-fund-balance']")

    public TravelToolsDropDownMenu(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""
    }

    private WebElement openTravelToolsDropDown() {
        waitForElement(TRAVEL_TOOLS_DROP_DOWN).click()
        return waitForElement(TRAVEL_TOOLS_CONTAINER)
    }

    void clickViewAllTravelToolsLink() {
        openTravelToolsDropDown().findElement(By.xpath(VIEW_ALL_TRAVEL_TOOLS_LINK_XPATH)).click()
    }

    void clickSouthwestGiftCardBalanceLink() {
        openTravelToolsDropDown().findElement(By.xpath(SOUTHWEST_GIFT_CARD_BALANCE_LINK_XPATH)).click()
    }

    void clickMySouthwestAccountLink() {
        openTravelToolsDropDown().findElement(MY_SOUTHWEST_ACCOUNT_LINK).click()
    }

    void clickSouthwestTravelFundBalancelink(){
        openTravelToolsDropDown().findElement()
        waitForElement(TRAVEL_FUND_BALANCE).click()
    }
}
