/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * This represent a modal component on the page
 */
class Modal {

    protected WebElement container
    private static final By CLOSE_BUTTON_CLASS = By.className("closeImg")
    private static final By TITLE_CSS = By.cssSelector("h5")

    Modal(WebElement theContainer) {
        this.container = theContainer
        container?.isDisplayed().shouldBe true, "The Modal is not visible on the page"
    }

    String getTitle() {
        return container.findElement(TITLE_CSS).getText()
    }

    void closeModal() {
        container.findElement(CLOSE_BUTTON_CLASS).click()
    }
}
