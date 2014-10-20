package pages

import org.jbehave.web.selenium.WebDriverProvider

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.WebElement
import static util.Locators.BREADCRUMB_IDS
import util.PageName
import state.Flow

class SWADisabilitiesPage extends BasePage {

    static final private By CAN_WALK_BUT_NEED_ASSISTANCE_FIELD = By.id("canWalkButNeedAssistance")
    static final private By SUBMIT_BUTTON = By.id("submit")
    private static final String ASSISTANCE_WITH_DISABILITIES_HEADER = "Assistance with Disabilities"
    static final private By CANCEL_BUTTON = By.id("cancel")
    private static final By PAGE_TITLE = By.id("page_title")
    Flow flow

    public boolean POC=false
    public boolean PWWSB=false
    public boolean PWWNSB=false
    public boolean NWAN=false
    public boolean CWBNATAFG=false
    public boolean NWSN=false
    public boolean NLATAFAS=false
    public boolean MW=false
    public boolean BOHLV=false
    public boolean DOHOH=false
    public boolean CADD=false
    public boolean TWTAA=false
    public boolean TWTESA=false
    public boolean HPDA=false


    def SWADisabilitiesPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def addAssistanceWithWheelchair() {
        waitForElement(CAN_WALK_BUT_NEED_ASSISTANCE_FIELD).click()
    }

    def addSpillableBatteries(def cellCount)
    {
        waitForElement(By.id("spillableBatteries")).click()
        Select selectBox = new Select(waitForElement(By.id("spillableBatteryCount")))
        selectBox.selectByValue(cellCount)
    }

    def addNonSpillableBatteries(def cellCount)
    {
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

    def checkBringingOxygenConcentrator()
    {
        waitForElement(By.id("oxygenConcentrator")).click()
    }

    def checkManualWheelChair()
    {
        waitForElement(By.id("manualWheelchair")).click()
    }

    //r code

    def clickDisabilitiesAndAssistanceOptions(String option,String b_count)
    {


             switch(option)
             {

                 case  "Powered wheelchair with spillable batteries":
                     waitForElement(By.id("spillableBatteries")).click()
                     Select selectBox = new Select(waitForElement(By.id("spillableBatteryCount")))
                     selectBox.selectByValue(b_count)
                     PWWSB = true
                     break

                 case  "Powered wheelchair with non-spillable batteries":
                     waitForElement(By.id("nonSpillableBatteries")).click()
                     Select selectBox = new Select(waitForElement(By.id("nonSpillableBatteryCount")))
                     selectBox.selectByValue(b_count)
                     PWWNSB = true
                     break


             }


    }


    def clickDisabilitiesAndAssistanceOptions(String option)
    {


        switch(option)
        {
            case  "No wheelchair assistance needed":
                waitForElement(By.id("nowheelchairAssistanceType")).click()
                  NWAN = true
                break
            case  "Can walk but need assistance to and from gate":
                waitForElement(By.id("canWalkButNeedAssistance")).click()
                  CWBNATAFG = true
                break
            case  "Need lift/transfer assistance to and from aircraft seat":
                waitForElement(By.id("cannotWalk")).click()
                  NLATAFAS = true
                break

            case  "No wheelchair stowage needed":
                waitForElement(By.id("noWheelchairStorageType")).click()
                  NWSN = true
                break

            case  "Manual wheelchair":
                waitForElement(By.id("manualWheelchair")).click()
                  MW = true
                break

            case  "Blind or have low vision":
                waitForElement(By.id("vision")).click()
                  BOHLV = true
                break
            case  "Deaf or hard of hearing":
                waitForElement(By.id("hearing")).click()
                  DOHOH = true
                break
            case  "Cognitive and developmental disabilities":
                waitForElement(By.id("cognitive")).click()
                  CADD = true
                break
            case  "Traveling with trained assistance animal":
                waitForElement(By.id("assistanceAnimal")).click()
                  TWTAA = true
                break
            case  "Traveling with emotional support animal":
                waitForElement(By.id("comfortCreature")).click()
                  TWTESA = true
                break
            case  "Have peanut dust allergy":
                waitForElement(By.id("peanutAllergy")).click()
                  HPDA = true
                break
            case  "Bringing my own approved Portable Oxygen Concentrator":
                waitForElement(By.id("oxygenConcentrator")).click()
                POC = true
                break

        }


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
        if (flow.isFaultInjected)
        {
            println "Entry (verify disabilities page) ---- waiting for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify disabilities page) ---- waiting for fault injection"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["PurchasePage"])
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.DISABILITIES_PAGE)
    }


}

/**
 * Created by x211047 on 10/14/2014.
 */
