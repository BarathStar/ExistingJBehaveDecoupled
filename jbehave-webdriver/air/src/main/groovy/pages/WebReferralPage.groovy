package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import static org.junit.Assert.fail

class WebReferralPage extends BasePage {

    public WebReferralPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    void verifyInternationalPartnerRoutesPage() {
          getCurrentUrl().endsWith("international-domestic-airtran-flights.html").shouldBe true,  "International Partner Routes page did not open"
    }

    void verifyInternationalPartnerRoutesDoNotExistHomePage() {
        verifyElementNotPresent("AirTran destinations", By.id("internal_partner_routes"))
    }

}
