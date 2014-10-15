/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class FlyingGlobalNavigation {
    private static final By WHYFLYSOUTHWEST_LINK = By.id('why-fly-southwest-global-nav')
    private WebElement container

    FlyingGlobalNavigation(WebElement theContainer){
        container = theContainer
    }

    void clickOnWhyFlySouthwestLink() {
        container.findElement(WHYFLYSOUTHWEST_LINK).click()
    }
}