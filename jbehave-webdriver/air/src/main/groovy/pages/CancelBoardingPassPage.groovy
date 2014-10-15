package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class CancelBoardingPassPage extends BasePage {

    private final By CONFIRM_CANCEL = By.name("confirmCancel")
    private final By DO_NOT_CANCEL = By.name("doNotCancel")
    private final String BP_PASSENGER_TABLE_ID = "cancelBP_passenger_table"
    private final String BP_COMPANION_TABLE_ID = "cancelBP_companion_table"
    private final String TABLE_ROW_CLASS = "tableRowOdd"

    def CancelBoardingPassPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    String getRelativePath() {
        return "flight/cancelBoardingPasses.html"  //TODO change as needed for page validation.
    }

    def isOnCancelBoardingPassPage() {
        getCurrentUrl().contains("cancelBoardingPasses.html")
    }

    def cancelBoardingPass() {
        waitForElement(CONFIRM_CANCEL).click()
    }

    def doNotCancelBoardingPass() {
        waitForElement(DO_NOT_CANCEL).click()
    }

    List<WebElement> getPassengerTableRows() {
        return waitForElements(By.cssSelector("table#${BP_PASSENGER_TABLE_ID} tbody tr.${TABLE_ROW_CLASS}"))
    }

    List<WebElement> getCompanionTableRows() {
        return waitForElements(By.cssSelector("table#${BP_COMPANION_TABLE_ID} tbody tr.${TABLE_ROW_CLASS}"))
    }
}