package pages

import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebElement

class UnaccompaniedMinorPurchasePage extends BasePage {
    private static final By DROP_OFF_GUARDIAN_FIRST_NAME = By.id("dropOffGuardiansfirstName0")
    private static final By DROP_OFF_GUARDIAN_LAST_NAME = By.id("dropOffGuardianslastName0")
    private static final By DROP_OFF_GUARDIAN_RELATIONSHIP = By.id("dropOffGuardiansrelationship0")
    private static final By DROP_OFF_GUARDIAN_AREA_CODE = By.id("dropOffGuardiansContactAreaCode0")
    private static final By DROP_OFF_GUARDIAN_PREFIX = By.id("dropOffGuardiansContactPrefix0")
    private static final By DROP_OFF_GUARDIAN_LINE_NUMBER = By.id("dropOffGuardiansContactLineNumber0")
    private static final By DROP_OFF_GUARDIAN_ADDRESS = By.id("dropOffGuardiansaddressLine10")
    private static final By DROP_OFF_GUARDIAN_CITY = By.id("dropOffGuardianscity0")
    private static final By DROP_OFF_GUARDIAN_ZIP_CODE = By.id("dropOffGuardianszipCode10")
    private static final By PICK_UP_GUARDIAN_FIRST_NAME = By.id("pickUpGuardiansfirstName0")
    private static final By PICK_UP_GUARDIAN_LAST_NAME = By.id("pickUpGuardianslastName0")
    private static final By PICK_UP_GUARDIAN_RELATIONSHIP = By.id("pickUpGuardiansrelationship0")
    private static final By PICK_UP_GUARDIAN_AREA_CODE = By.id("pickUpGuardiansContactAreaCode0")
    private static final By PICK_UP_GUARDIAN_PREFIX = By.id("pickUpGuardiansContactPrefix0")
    private static final By PICK_UP_GUARDIAN_LINE_NUMBER = By.id("pickUpGuardiansContactLineNumber0")
    private static final By PICK_UP_GUARDIAN__ADDRESS = By.id("pickUpGuardiansaddressLine10")
    private static final By PICK_UP_GUARDIAN_CITY = By.id("pickUpGuardianscity0")
    private static final By PICK_UP_GUARDIAN_ZIP_CODE = By.id("pickUpGuardianszipCode10")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_FIRST_NAME_DEPART = By.id("altPickUpGuardiansfirstName0")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_LAST_NAME_DEPART = By.id("altPickUpGuardianslastName0")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_RELATIONSHIP_DEPART = By.id("altPickUpGuardiansrelationship0")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_AREA_CODE_DEPART = By.id("altPickUpGuardiansContactAreaCode0")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_PREFIX_DEPART = By.id("altPickUpGuardiansContactPrefix0")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_LINE_NUMBER_DEPART = By.id("altPickUpGuardiansContactLineNumber0")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_ADDRESS_DEPART = By.id("altPickUpGuardiansaddressLine10")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_CITY_DEPART = By.id("altPickUpGuardianscity0")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_ZIP_CODE_DEPART = By.id("altPickUpGuardianszipCode10")
    private static final By CONTACT_INFO_FOR_RETURNING_FLIGHT_CHECKBOX = By.id("contactInfoForReturningFlightCheckBox")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_FIRST_NAME_RETURN = By.id("altPickUpGuardiansfirstName1")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_LAST_NAME_RETURN = By.id("altPickUpGuardianslastName1")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_RELATIONSHIP_RETURN = By.id("altPickUpGuardiansrelationship1")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_AREA_CODE_RETURN = By.id("altPickUpGuardiansContactAreaCode1")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_CONTACT_PREFIX_RETURN = By.id("altPickUpGuardiansContactPrefix1")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_CONTACT_LINE_NUMBER_RETURN = By.id("altPickUpGuardiansContactLineNumber1")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_ADDRESS_LINE_RETURN = By.id("altPickUpGuardiansaddressLine11")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_CITY_RETURN = By.id("altPickUpGuardianscity1")
    private static final By ALTERNATE_PICK_UP_GUARDIAN_ZIP_CODE_RETURN = By.id("altPickUpGuardianszipCode11")
    private static final By SUBMIT_BUTTON = By.id("submitButton")

    Data data

    private WebElement continueButton(){
        waitForElement(SUBMIT_BUTTON)
    }

    def UnaccompaniedMinorPurchasePage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def fillInParentGuardiansInformation() {
        fillInDropOffGuardians()
        fillInPickUpGuardians()
        fillInAltPickUpGuardians()
        try {
            checkContactInfoForReturningFlightCheckBox()
            fillInReturnAltPickUpGuardians()
        } catch (NoSuchElementException e) {}
    }

    def fillInDropOffGuardians() {
        waitForElement(DROP_OFF_GUARDIAN_FIRST_NAME).sendKeys DELETE_EXISTING + data.firstName
        waitForElement(DROP_OFF_GUARDIAN_LAST_NAME).sendKeys DELETE_EXISTING + data.lastName
        waitForElement(DROP_OFF_GUARDIAN_RELATIONSHIP).sendKeys DELETE_EXISTING + "Father"
        waitForElement(DROP_OFF_GUARDIAN_AREA_CODE).sendKeys DELETE_EXISTING + "972"
        waitForElement(DROP_OFF_GUARDIAN_PREFIX).sendKeys DELETE_EXISTING + "312"
        waitForElement(DROP_OFF_GUARDIAN_LINE_NUMBER).sendKeys DELETE_EXISTING + "1111"
        waitForElement(DROP_OFF_GUARDIAN_ADDRESS).sendKeys DELETE_EXISTING + "2707 love filed dr "
        waitForElement(DROP_OFF_GUARDIAN_CITY).sendKeys DELETE_EXISTING + "Dallas"
        chooseInDropDownByValue("dropOffGuardiansstate0", "TX")
        waitForElement(DROP_OFF_GUARDIAN_ZIP_CODE).sendKeys DELETE_EXISTING + "75202"
        chooseInDropDownByValue("dropOffGuardianscountry0", "US")
        chooseInDropDownByValue("dropOffGuardianscontactMethod0", "Call")
    }

    def fillInPickUpGuardians() {
        waitForElement(PICK_UP_GUARDIAN_FIRST_NAME).sendKeys DELETE_EXISTING + "Norma"
        waitForElement(PICK_UP_GUARDIAN_LAST_NAME).sendKeys DELETE_EXISTING + "Lopez"
        waitForElement(PICK_UP_GUARDIAN_RELATIONSHIP).sendKeys DELETE_EXISTING + "Mother"
        waitForElement(PICK_UP_GUARDIAN_AREA_CODE).sendKeys DELETE_EXISTING + "972"
        waitForElement(PICK_UP_GUARDIAN_PREFIX).sendKeys DELETE_EXISTING + "312"
        waitForElement(PICK_UP_GUARDIAN_LINE_NUMBER).sendKeys DELETE_EXISTING + "1112"
        waitForElement(PICK_UP_GUARDIAN__ADDRESS).sendKeys DELETE_EXISTING + "2807 love filed dr "
        waitForElement(PICK_UP_GUARDIAN_CITY).sendKeys DELETE_EXISTING + "Dallas"
        chooseInDropDownByValue("pickUpGuardiansstate0", "TX")
        waitForElement(PICK_UP_GUARDIAN_ZIP_CODE).sendKeys DELETE_EXISTING + "75202"
        chooseInDropDownByValue("pickUpGuardianscountry0", "US")
        chooseInDropDownByValue("pickUpGuardianscontactMethod0", "Call")
    }

    def fillInAltPickUpGuardians() {
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_FIRST_NAME_DEPART).sendKeys DELETE_EXISTING + "Max"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_LAST_NAME_DEPART).sendKeys DELETE_EXISTING + "Power"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_RELATIONSHIP_DEPART).sendKeys DELETE_EXISTING + "Uncle"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_AREA_CODE_DEPART).sendKeys DELETE_EXISTING + "972"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_PREFIX_DEPART).sendKeys DELETE_EXISTING + "312"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_LINE_NUMBER_DEPART).sendKeys DELETE_EXISTING + "1113"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_ADDRESS_DEPART).sendKeys DELETE_EXISTING + "2907 love filed dr "
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_CITY_DEPART).sendKeys DELETE_EXISTING + "Dallas"
        chooseInDropDownByValue("altPickUpGuardiansstate0", "TX")
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_ZIP_CODE_DEPART).sendKeys DELETE_EXISTING + "75202"
        chooseInDropDownByValue("altPickUpGuardianscountry0", "US")
        chooseInDropDownByValue("altPickUpGuardianscontactMethod0", "Call")
    }

    def clickContinue() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def fillInReturnAltPickUpGuardians() {
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_FIRST_NAME_RETURN).sendKeys DELETE_EXISTING + "Max"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_LAST_NAME_RETURN).sendKeys DELETE_EXISTING + "Power"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_RELATIONSHIP_RETURN).sendKeys DELETE_EXISTING + "Uncle"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_AREA_CODE_RETURN).sendKeys DELETE_EXISTING + "972"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_CONTACT_PREFIX_RETURN).sendKeys DELETE_EXISTING + "312"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_CONTACT_LINE_NUMBER_RETURN).sendKeys DELETE_EXISTING + "1113"
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_ADDRESS_LINE_RETURN).sendKeys DELETE_EXISTING + "2907 love filed dr "
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_CITY_RETURN).sendKeys DELETE_EXISTING + "Dallas"
        chooseInDropDownByValue("altPickUpGuardiansstate1", "TX")
        waitForElement(ALTERNATE_PICK_UP_GUARDIAN_ZIP_CODE_RETURN).sendKeys DELETE_EXISTING + "75202"
        chooseInDropDownByValue("altPickUpGuardianscountry1", "US")
        chooseInDropDownByValue("altPickUpGuardianscontactMethod1", "Call")
    }

    def checkContactInfoForReturningFlightCheckBox() {
        waitForElement(CONTACT_INFO_FOR_RETURNING_FLIGHT_CHECKBOX).click()
    }
}