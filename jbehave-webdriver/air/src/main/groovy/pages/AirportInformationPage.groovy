package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.internal.selenesedriver.GetUrl
import org.openqa.selenium.By

class AirportInformationPage extends BasePage {

    public static final By SWA_PAGE_TITLE = By.className("swa_page_title")

    public AirportInformationPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return "/html/air/airport-information.html"
    }

    void verifyAirportInformationPage() {
        def handle = getWindowHandles().toArray()
        switchTo().window(handle[0])
        waitForElement(SWA_PAGE_TITLE).getText().shouldContain "Airport Information",
                "Airport Information Page Not Found "
    }


}
