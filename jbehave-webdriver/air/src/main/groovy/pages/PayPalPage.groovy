package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.Alert
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.PriceTable
import util.PricePageData

class PayPalPage extends BasePage {

    private static final By TRIP_GRAND_TOTAL = By.cssSelector("#miniCart .grandTotal.amount.highlight")
    private static final By LOGIN_EMAIL = By.id("login_email")
    private static final By LOGIN_PASSWORD = By.id("login_password")
    private static final By PAY_NOW_BUTTON = By.id("continue")
    private static final By LOGIN_BUTTON = By.id("submitLogin")
    private static final By CONTINUE_BUTTON = By.id("continue_abovefold")
    PricePageData pricePageData

    public PayPalPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return "";
    }

    public void switchToPayPalWindow(){
        switchToWindow("PayPal", 10000)
    }

    public void enterCredentialsOnPayPalHomePage(){
        fillIn(LOGIN_EMAIL,"Southwest.comEnvironmentManagement-DG@wnco.com");
        fillIn(LOGIN_PASSWORD, "swadotcom");
    }

    public void clickContinueOnPayPalHomePage(){
        waitForElement(LOGIN_BUTTON).click();
    }

    public void clickPayNow(){
        waitForElement(PAY_NOW_BUTTON).click();
    }


    public void enterPayPalCredentialsOnPurchaseTransitionPage() {
        String title = getTitle();
        fillIn(LOGIN_EMAIL, "tester_1326823218_per@wnco.com");
        fillIn(LOGIN_PASSWORD, "test1234");
        waitForElement(LOGIN_BUTTON).click();
        waitForPageTitleToChangeOrOops(title);
    }

    void acceptPriceOnReviewYourInformationPage() {
        String title = getTitle();
        waitForElement(CONTINUE_BUTTON).click();
        waitForPageTitleToChangeOrOops(title);
    }

    void verifyTotal() {
        waitForElement(TRIP_GRAND_TOTAL).text.split(" ")[1].replace("\$","").toBigDecimal().shouldEqual pricePageData.airTotal
    }
}

