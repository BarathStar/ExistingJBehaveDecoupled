/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package util

import org.openqa.selenium.WebElement

/**
 * This class convert price elements to BigDecimal
 */
class DollarToBigDecimalConverter {

    public static BigDecimal toBigDecimal(WebElement element){
        element?.isDisplayed().shouldBe true, "The price is not visible on the page"
        return element.getText().trim().minus('$').toBigDecimal()
    }

}
