package pages

import org.jbehave.web.selenium.WebDriverProvider

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.WebElement
import static util.Locators.BREADCRUMB_IDS
import util.PageName
import state.Flow

class DisabilitiesPage extends BasePage {

    static final private By CAN_WALK_BUT_NEED_ASSISTANCE_FIELD = By.id("canWalkButNeedAssistance")
    static final private By SUBMIT_BUTTON = By.id("submit")
    private static final String ASSISTANCE_WITH_DISABILITIES_HEADER = "Assistance with Disabilities"
    static final private By CANCEL_BUTTON = By.id("cancel")
    private static final By PAGE_TITLE = By.id("page_title")
    Flow flow

    def DisabilitiesPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def addAssistanceWithWheelchair() {
        waitForElement(CAN_WALK_BUT_NEED_ASSISTANCE_FIELD).click()
    }

    def addSpillableBatteries(def cellCount) {
        waitForElement(By.id("spillableBatteries")).click()
        Select selectBox = new Select(waitForElement(By.id("spillableBatteryCount")))
        selectBox.selectByValue(cellCount)
    }

    def addNonSpillableBatteries(def cellCount) {
        waitForElement(By.id("nonSpillableBatteries")).click()
        Select selectBox = new Select(waitForElement(By.id("nonSpillableBatteryCount")))
        selectBox.selectByValue(cellCount)
    }

    def checkBlindOrHaveLowVision() {
        waitForElement(By.id("vision")).click()
    }

    def checkDeafOrHardOfHearing() {
        waitForElement(By.id("hearing")).click()
    }

    def checkCognitiveAndDevelopmentalDisability() {
        waitForElement(By.id("cognitive")).click()
    }

    def checkTravellingWithTrainedAssistanceAnimal() {
        waitForElement(By.id("assistanceAnimal")).click()
    }

    def checkTravellingWithEmotionalSupportAnimal() {
        waitForElement(By.id("comfortCreature")).click()
    }

    def checkPeanutDustAllergy() {
        waitForElement(By.id("peanutAllergy")).click()
    }

    def checkBringingOxygenConcentrator() {
        waitForElement(By.id("oxygenConcentrator")).click()
    }

    private def checkOneSSROption(String option) {
        waitForElement(By.id(option)).click()
    }

    def addSSROptions(String optionsStr) {
        def options = optionsStr.split(",")
        options.each {
            if (it.contains("=")) {
                def tokens = it.split("=")
                if (tokens[0] == "spillableBatteries") {
                    addSpillableBatteries(tokens[1])
                }
                else if (tokens[0] == "nonSpillableBatteries") {
                    addNonSpillableBatteries(tokens[1])
                }
            }
            else {
                checkOneSSROption(it)
            }
        }
    }

    def submit() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def verifyPageHeader() {
         waitForElement(PAGE_TITLE).text.shouldContain ASSISTANCE_WITH_DISABILITIES_HEADER, "the page title within disability options did not contain '$ASSISTANCE_WITH_DISABILITIES_HEADER' "
    }

    def verifyContinueButton() {
        isElementPresent(SUBMIT_BUTTON).shouldBe true, "Continue Button was not present in disability options"
    }

    def verifyCancelButton() {
        isElementPresent(CANCEL_BUTTON).shouldBe true, "Cancel Button was not present in disability options"
    }

    def verifyBasicPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify disabilities page) ---- waiting for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify disabilities page) ---- waiting for fault injection"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["PurchasePage"])
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.DISABILITIES_PAGE)
    }


}

