/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.DollarToBigDecimalConverter

/**
 * This class represents a Guardian charge table
 */
class GuardianChargeTable {

    private static final String UM_CHARGE_PRICING_TABLE_TOTAL_ROWS_CSS = 'td:nth-child(4)'
    private WebElement container

    GuardianChargeTable(WebElement theContainer) {
        container = theContainer
        container?.isDisplayed().shouldBe true, "The Guardian Charge table is not visible on the page"
    }

    List<BigDecimal> unaccompaniedMinorCharges() {
        List<WebElement> chargeRows = container.findElements(By.cssSelector(UM_CHARGE_PRICING_TABLE_TOTAL_ROWS_CSS))
        List<BigDecimal> umCharges = []
        chargeRows.each {
            BigDecimal charge = DollarToBigDecimalConverter.toBigDecimal(it)
            if(charge) {
                umCharges.add(charge)
            }
        }
        return umCharges
    }
}
