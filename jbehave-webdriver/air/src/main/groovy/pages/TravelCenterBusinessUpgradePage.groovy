package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class TravelCenterBusinessUpgradePage extends BasePage {
    static final String SWABIZUpgradeToBusinessSelectPath = "/travel_center/upgrade.html"

    private static final By ENTER_CONFIRMATION_NUMBER_FIELD = By.xpath("//input[@title ='Enter Confirmation Number']")
    private static final By ENTER_FIRST_NAME_FIELD = By.xpath("//input[@title ='Enter First Name']")
    private static final By ENTER_LAST_NAME_FIELD = By.xpath("//input[@title ='Enter Last Name']")
    private static final By RETRIEVE_RESERVATION_BUTTON = By.xpath("//input[@title ='Retrieve Reservation']")
    private static final By AIRTRAN_LINK = By.id("airTranLink")

    public TravelCenterBusinessUpgradePage(WebDriverProvider driverProvider) {
        super(driverProvider, SWABIZUpgradeToBusinessSelectPath);
    }

    String getRelativePath() {
        return SWABIZUpgradeToBusinessSelectPath
    }

    void retrievePnrForBusinessSelectUpgrade(String pnr, String pnrFirstName, String pnrLastName) {
        waitForElement(ENTER_CONFIRMATION_NUMBER_FIELD).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(ENTER_FIRST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnrFirstName)
        waitForElement(ENTER_LAST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnrLastName)
        waitForElement(RETRIEVE_RESERVATION_BUTTON).click()
    }

    void verifyInvalidPnrOopsMessage() {
        shouldShowOopsMessage("We were unable to retrieve your reservation from our database. Verify the following:")
        String oopsText = getOopsElement().getText()
        oopsText.shouldNotContain "We're sorry,", "We're sorry text is still present at the top of the Oops message."
        oopsText.shouldNotContain ", airtran.com,", "Third bullet in the Oops message still contains the airtran.com text."
        oopsText.shouldContain "Travel Agencies cannot be accessed on southwest.com.", "Third bullet in the Oops message still contains the airtran.com text."
        oopsText.shouldNotContain "Travel Agencies cannot be accessed online", "Third bullet text at end was NOT removed/changed"
        waitForElement(AIRTRAN_LINK).getText().shouldEqual "airtran.com"
    }

}
