package pages

import org.jbehave.web.selenium.WebDriverProvider

class PurchasePointsConfirmationPage extends BasePage {

    public PurchasePointsConfirmationPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/rapidrewards/purchase-points-confirm')
    }

    @Override
    public String getRelativePath() {
        return "account/rapidrewards/purchase-points-confirm"
    }

}