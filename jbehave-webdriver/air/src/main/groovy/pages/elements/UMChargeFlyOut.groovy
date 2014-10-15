/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * This class represents a Unaccompanied Minor Charge flyOut
 */
class UMChargeFlyOut extends FlyOut {

    private static final String CONTENT_CONTAINER_CLASS = 'myCurrentTripPopupDetailsContainer'
    private static final String UM_LINK_CSS = ".myCurrentTripPopupDetailsContainer a"
    private static final String CLOSE_BUTTON_CLASS = 'close_link'

    UMChargeFlyOut(WebElement theContainer) {
        super(theContainer)
    }

    String getContent() {
        return container.findElement(By.className(CONTENT_CONTAINER_CLASS)).getText()
    }

    WebElement getUMLink() {
        return container.findElement(By.cssSelector(UM_LINK_CSS))
    }

    void closeFlyout() {
        container.findElement(By.className(CLOSE_BUTTON_CLASS)).click()
    }

}