package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import state.Flow

class SelectFlightsToUpgradePage extends BasePage {
    private static final By CONTINUE_BUTTON = By.className("submitButton")
    private static final By OUTBOUND_CHECKBOX = By.id("outbound1")
    private static final By INBOUND_CHECKBOX = By.id("inbound1")
    private static final By OUTBOUND_UPGRADE_TEXT = By.className("upgradeAvailable")
    private static final By UPGRADE_QUESTION_LIST = By.className("upgradeAvailable")
    private static final By UPGRADE_NOTICE_LIST = By.className("arrow_bullets")

    private static final String UPGRADE_QUESTION = "Upgrade?"
    private static final String SEAT_FEES = ""

    Flow flow

    String getRelativePath() {
        return "flight/view-reservation-to-upgrade.html"
    }

    def SelectFlightsToUpgradePage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, "flight/view-reservation-to-upgrade.html")
    }

    def selectOutBoundToChangeItinerary() {
        selectCheckBoxifNotSelected("outbound1")
    }

    def selectInBoundToChangeItinerary() {
        selectCheckBoxifNotSelected("inbound1")
    }

    def continueToUpgrade() {
        WebElement submitButton = waitForElement(CONTINUE_BUTTON)
        submitButton.click()
    }

    private def selectCheckBoxifNotSelected(String fld) {
        def checkbox = waitForElement(By.id(fld))
        if (!checkbox.isSelected()) {
            checkbox.click()
        }
    }

    def verifyBothSegmentsAreUpgradable() {
        waitForElement(OUTBOUND_CHECKBOX).isSelected().shouldBe true, "Outbound segment upgrade checkbox is not selected when it should."
        waitForElement(INBOUND_CHECKBOX).isSelected().shouldBe true, "Inbound segment upgrade checkbox is not selected when it should."
        waitForElements(UPGRADE_QUESTION_LIST).get(0).getText().equalsIgnoreCase(UPGRADE_QUESTION).shouldBe true, "Outbound segment upgrade text: $UPGRADE_QUESTION is not being properly shown."
        waitForElements(UPGRADE_QUESTION_LIST).get(1).getText().equalsIgnoreCase(UPGRADE_QUESTION).shouldBe true, "Inbound segment upgrade text: $UPGRADE_QUESTION is not being properly shown."
    }

    def verifySeatFeesVerbiage(boolean expected) {
        boolean found = false
        List<WebElement> list = waitForElements(UPGRADE_NOTICE_LIST)
        for (WebElement element : list) {
            if (SEAT_FEES.isEmpty() || element.getText().contains(SEAT_FEES)) {
                found = expected
                break
            }
        }
        (expected^found).shouldBe false, "Verbiage '$SEAT_FEES' is "+(expected?"not ":"")+"shown, which is not expected."
    }
}
