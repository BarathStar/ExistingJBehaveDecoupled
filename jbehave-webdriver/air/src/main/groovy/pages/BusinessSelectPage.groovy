package pages

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

class BusinessSelectPage extends BasePage {

    private static final By CONFIRMATION_NUMBER_FIELD = By.id("confirmationNumber")
    private static final By FIRST_NAME_FIELD = By.id("firstName")
    private static final By LAST_NAME_FIELD = By.id("lastName")
    private static final By SUBMIT_BUTTON = By.id("submitButton")

    public BusinessSelectPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/html/air/products/business-select.html');
    }


    def retrieveReservationToBusinessSelect(String pnr, String pnr_firstName, String pnr_lastName) {
        waitForElement(CONFIRMATION_NUMBER_FIELD).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(FIRST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_firstName)
        waitForElement(LAST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_lastName)
        waitForElement(SUBMIT_BUTTON).click()
    }

    def open() {
        super.open()
        verifyPage()
    }

    @Override
    public String getRelativePath() {
        // TODO Auto-generated method stub
        return null;
    }

}
