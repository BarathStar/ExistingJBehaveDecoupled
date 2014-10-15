/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * This class represents a FlyOut web component on the page
 */
class FlyOut {

    private static final By CLOSE_BUTTON = By.className('closeImg')
    protected WebElement container
    private By contentSelector
    private static final String TITLE_CSS = 'H5'

    /** theContainer parameter refers to the flyOut's main container */
    FlyOut(WebElement theContainer) {
        container = theContainer
        container?.isDisplayed().shouldBe true, "The FlyOut is not visible on the page"
    }
    /**
    * theContainer parameter refers to the flyOut's main container
    * theContentSelector parameter refers to the flyOut content's container of the flyOut 
    */
    FlyOut(WebElement theContainer, By theContentSelector) {
        this(theContainer)
        contentSelector = theContentSelector
    }

    String getTitle() {
        return container.findElement(By.cssSelector(TITLE_CSS)).getText()
    }

    String getContentText() {
        return getContentElement().getText()
    }

    WebElement getContentElement() {
        return container.findElement(contentSelector)
    }

    void closeFlyout() {
        container.findElement(CLOSE_BUTTON).click()
    }
}