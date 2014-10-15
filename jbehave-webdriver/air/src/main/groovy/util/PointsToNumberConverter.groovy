package util

import org.openqa.selenium.WebElement

/**
 * This class convert points elements to int
 */
class PointsToNumberConverter {
    public static int toInteger(WebElement element) {
        element?.isDisplayed().shouldBe true, "The points is not visible on the page"
        return element.getText().trim().minus(' pts').minus(',').toInteger()
    }
}
