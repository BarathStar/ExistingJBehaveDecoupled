package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class OptionalTravelChargesPage extends BasePage {

    private static final String PATH = "/html/customer-service/travel-fees.html?int=GNAVTRAVELFEES"

    OptionalTravelChargesPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    @Override
    def open() {
        super.open()
        verifyPage()
    }

    def verifyPageOptionalTravelChargesPage(){
        shouldBeInPage(PATH)
    }
}
