/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class TravelExtrasPage extends BasePage {

    private final By SOUTHWEST_GIFT_CARD_XPATH = By.xpath("//a[@id = 'southwestgiftcard']")

    String getRelativePath() {
        return "html/travel-extras/index.html"
    }

    def TravelExtrasPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, "html/travel-extras/index.html")
    }

    void clickOnSouthwestGiftCardLink(){
        waitForElement(SOUTHWEST_GIFT_CARD_XPATH).click()
    }
}