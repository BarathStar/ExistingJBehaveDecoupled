package pages;


import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

public class PromotionalCertificateDetailsPage extends BasePage {

    private static final By BOOK_PROMOTIONAL_BUTTON = By.cssSelector("div#tripSearchLinksContainer.productBookNewTripLink a#planOrBookNewTripLink0.accountSnapshotSelector")
    private static final String PATH = "/account/rapidrewards/certificate-details"

    public PromotionalCertificateDetailsPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return PATH;
    }

    void selectPromotionalCertificate() {
         waitForElement(BOOK_PROMOTIONAL_BUTTON, true, 40).click()
    }

}
