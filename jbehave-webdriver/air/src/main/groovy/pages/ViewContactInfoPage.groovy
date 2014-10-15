package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class ViewContactInfoPage extends BasePage{

    private static final By POSTAL_ADDRESS_CONTAINER = By.id("postalAddressContainer")

    ViewContactInfoPage(WebDriverProvider driverProvider) {
        super(driverProvider, "account/info/view-contact-info")
    }

    def verifyNumberOfAddresses(int numberOfAddresses) {
        List<WebElement> addresses = waitForElement(POSTAL_ADDRESS_CONTAINER).findElements(By.tagName("table"))
        (addresses.size() == numberOfAddresses).shouldBe  true, "Should have " + numberOfAddresses + " addresses"
    }
}
