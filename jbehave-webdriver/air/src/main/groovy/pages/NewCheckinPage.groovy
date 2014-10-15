/*
 *  * Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.
 */

package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

/**
 * Represents the New Checkin page appearence and functionality.
 */
class NewCheckinPage extends BasePage {

    static final private String OOPSMESSAGE = "Sorry, your itinerary is ineligible for checkin online. Please proceed to any Southwest Airlines Ticket Counter to check in for your flight."

    private static final By DOC_TYPE_NAME = By.className("swa_feature_checkIn_passenger_documentType")
    private static final By BS_BUTTON_ID = By.id("upgradeToBusinessSelect")
    private static final By PRINT_DRINKS_CHECKBOX_ID = By.id("printDrinkCoupons1")
    private static final By CHECKIN_BUTTON_ID = By.id("printDocumentsButton")
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By FIRST_NAMES_DISPLAYED = By.className("passenger_firstName")
    private static final By RAPID_REWARD_NUMBERS = By.className("RRNumberData")
    private static final By RAPID_REWARDS_LINKS = By.className("checkInRRNumberButton")
    private static final By FLIGHT_NUMBERS = By.className("swa_feature_checkIn_flightNumber")
    private static final By FLIGHT_TIMES = By.className("swa_feature_checkIn_flight_time_time")
    private static final By WIFI_TEXTS = By.className("swa_feature_checkIn_wifi_info")
    private static final By ADD_RR_NUMBER_TEXT_ID = By.id("rrNumer")

    private WebElement findNoneFlyBy() {
        return waitForElement(By.className("swa_feature_checkIn_flyByInfo_container_none"))
    }

    private WebElement findBothFlyBy() {
        return waitForElement(By.className("swa_feature_checkIn_flyByInfo_security_and_counter"))
    }

    private WebElement findSecurityCheckpointFlyBy() {
        return waitForElement(By.className("swa_feature_checkIn_flyByInfo_security"))
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    public NewCheckinPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/selectPrintDocument.html');
    }

    public verifyPage() {
        super.verifyPage()
        waitForElement(FIRST_NAMES_DISPLAYED)
    }

    public void verifyBothFlyBy() {
        findBothFlyBy().isDisplayed().shouldBe true, "FlyBy is not being shown to the user when it should"
    }

    public void verifySecurityCheckpointFlyBy() {
        findSecurityCheckpointFlyBy().isDisplayed().shouldBe true, "FlyBy (Security Checkpoint only) is not being shown to the user when it should"
    }

    public void verifyNoneFlyBy() {
        findNoneFlyBy().isDisplayed().shouldBe true, "FlyBy is being shown to the user when it shouldn't"
    }

    public void verifyPassengersDisplayed(int numPass) {
        findElements(FIRST_NAMES_DISPLAYED).size().shouldBeEqual numPass, "The number of passengers shown is not correct."
    }

    public void verifyRRNumberDisplayed() {
        findElements(RAPID_REWARD_NUMBERS).get(0).getText().shouldNotBe " ", "The Rapid Rewards number of the passenger is not being displayed when it should."
    }

    public void verifyRRLinkDisplayed() {
        findElements(RAPID_REWARDS_LINKS).get(0).isDisplayed().shouldBe true, "The 'Add Number/Signup' link is not being displayed when it should."
    }

    public boolean verifyDocTypeDisplayed() {
        return checkIfElementExists(DOC_TYPE_NAME)
    }

    public void verifyFlightNumbersDisplayed(int numFlights) {
        (findElements(FLIGHT_NUMBERS).size() - 1).shouldBeGreaterThan numFlights - 1, "The number of flights shown is not correct."
    }

    public void verifyFlightTimesDisplayed(int numFlightTimes) {
        findElements(FLIGHT_TIMES).size().shouldBeGreaterThan numFlightTimes - 1, "The number of flight times shown is not correct."
    }

    public void verifyWiFisDisplayed(int numWiFi) {
        findElements(WIFI_TEXTS).size().shouldBeEqual numWiFi, "The number of flights with WiFi shown is not correct."
    }

    public boolean verifyBusinessSelectDisplayed() {
        return checkIfElementExists(BS_BUTTON_ID)
    }

    public boolean verifyPrintDrinksDisplayed() {
        return checkIfElementExists(PRINT_DRINKS_CHECKBOX_ID)
    }

    public void verifyNotEligibleForCheckin() {
        shouldShowOopsMessage(OOPSMESSAGE)
        checkIfElementExists(CHECKIN_BUTTON_ID).shouldBe false, "The checkin button is being displayed when it shouldn't."
    }

    public void openRRModalAndRetrieve(String rrNumber) {
        findElements(RAPID_REWARDS_LINKS).get(0).click()
        fillIn(ADD_RR_NUMBER_TEXT_ID, rrNumber)
        findElement(SUBMIT_BUTTON).submit()
        verifyPage()
    }
}
