package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class HotelGlobalNavigation {

    private WebElement container
    private static final By BEST_RATE_GUARANTEE_LINK = By.xpath("//table/tbody/tr/td/ul/li/a[@id='best-rate-guarantee']")

    HotelGlobalNavigation(WebElement container) {
        this.container = container
    }

    def clickOnBestRateGuaranteeLink() {
        container.findElement(BEST_RATE_GUARANTEE_LINK).click()
    }
}
