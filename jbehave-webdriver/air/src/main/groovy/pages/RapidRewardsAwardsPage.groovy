package pages;


import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement
import static org.junit.Assert.fail
import org.openqa.selenium.By

public class RapidRewardsAwardsPage extends BasePage {

    private static final By STANDARD_AWARDS_AVAILABILITY_BUTTON = By.id("AwardSeatFinder")
    private static final By MODAL_STANDARD_AWARDS_AVAILABILITY_FORM = By.id("tripSearchForm")
    private static final By SEARCH_AWARDS_FLIGHTS = By.id("AwardBookNow")
    private static final By FLEXIBLE_DATES_CHECKBOX = By.id("flexDatesCheckbox")

    public RapidRewardsAwardsPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    void openSearchFlightsModalUsingAwards() {
        waitForElement(SEARCH_AWARDS_FLIGHTS).click()
    }

    def openStandardAwardsAvailabilityModal(){
        waitForElement(STANDARD_AWARDS_AVAILABILITY_BUTTON).click()
        isElementPresent(MODAL_STANDARD_AWARDS_AVAILABILITY_FORM)
    }

    def checkStandardAwardsAvailabilityLink(){
        verifyElementPresent("AwardSeatFinder", STANDARD_AWARDS_AVAILABILITY_BUTTON)
    }


    void verifyNoDollarsAndPoints() {
        def textLists = waitForElement(By.id('simplemodal-container')).getText().split("\\n")
        for (text in textLists) {
            if (text.equals("Dollars") || text.equals("Points")) {
                fail("Dollars or Points button is present when it should not be there")
                break
            }
        }
    }

    void checkFlexibleDatesCheckbox() {
        if (isCheckboxChecked(FLEXIBLE_DATES_CHECKBOX) == false) {
            waitForElement(FLEXIBLE_DATES_CHECKBOX).click()
        }
    }

}
