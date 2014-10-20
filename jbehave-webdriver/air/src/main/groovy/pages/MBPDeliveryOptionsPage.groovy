/*
 * Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.
 */
package pages

import org.apache.commons.lang.StringUtils
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import static org.junit.Assert.fail

public class MBPDeliveryOptionsPage extends BasePage {

    private static final String PATH = "/flight/selectCheckinDocDelivery.html"
    private static final By FLIGHT_NUMBERS = By.cssSelector(".itinerary_info_content .itinerary_header .flight_number .number")
    private static final By PRINT_OPTION_CHECKBOX = By.cssSelector("#mobileBoardingPassOptionsForm input[name=optionPrint]")
    private static final By EMAIL_OPTION_CHECKBOX = By.cssSelector("#mobileBoardingPassOptionsForm input[name=optionEmail]")
    private static final By TEXT_OPTION_CHECKBOX = By.cssSelector("#mobileBoardingPassOptionsForm input[name=optionText]")
    private static final By PHONE_AREA_TEXTBOX = By.cssSelector("#mobileBoardingPassOptionsForm input[name=phoneArea]")
    private static final By PHONE_PREFIX_TEXTBOX = By.cssSelector("#mobileBoardingPassOptionsForm input[name=phonePrefix]")
    private static final By PHONE_NUMBER_TEXTBOX = By.cssSelector("#mobileBoardingPassOptionsForm input[name=phoneNumber]")
    private static final By EMAIL_ADDRESS_TEXTBOX = By.cssSelector("#mobileBoardingPassOptionsForm input[name=emailAddress]")
    private static final By SUBMIT_BUTTON = By.cssSelector("#mobileBoardingPassOptionsForm input[name=book_now]")

    def MBPDeliveryOptionsPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    String getRelativePath() {
        return PATH
    }

    void submit() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    boolean verifySubmitButtonIsPresent() {
        return isElementPresent(SUBMIT_BUTTON, 25)
    }

    void verifyOopsMessage(String param) {
        def oopsMessages = [
                param
        ]
        verifyOopsMessages(oopsMessages)
    }

    def checkPrintOption() {
        WebElement checkbox = waitForElement(PRINT_OPTION_CHECKBOX)
        if (!checkbox.isSelected() && checkbox.isDisplayed()) {
            checkbox.click()
        }
    }

    def uncheckPrintOption() {
        WebElement checkbox = waitForElement(PRINT_OPTION_CHECKBOX)
        if (checkbox.isSelected() && checkbox.isDisplayed()) {
            checkbox.click()
        }
    }

    def checkTextOption() {
        WebElement checkbox = waitForElement(TEXT_OPTION_CHECKBOX)
        if (!checkbox.isSelected() && checkbox.isDisplayed()) {
            checkbox.click()
        }
    }

    def uncheckTextOption() {
        WebElement checkbox = waitForElement(TEXT_OPTION_CHECKBOX)
        if (checkbox.isSelected() && checkbox.isDisplayed()) {
            checkbox.click()
        }
    }

    def checkEmailOption() {
        WebElement checkbox = waitForElement(EMAIL_OPTION_CHECKBOX)
        if (!checkbox.isSelected() && checkbox.isDisplayed()) {
            checkbox.click()
        }
    }

    def uncheckEmailOption() {
        WebElement checkbox = waitForElement(EMAIL_OPTION_CHECKBOX)
        if (checkbox.isSelected() && checkbox.isDisplayed()) {
            checkbox.click()
        }
    }

    def enterPhoneNumber() {
        WebElement textCheckbox = waitForElement(TEXT_OPTION_CHECKBOX)

        if (textCheckbox.isSelected()) {
            fillIn(PHONE_AREA_TEXTBOX, "555", false)
            fillIn(PHONE_PREFIX_TEXTBOX, "555", false)
            fillIn(PHONE_NUMBER_TEXTBOX, "5555", false)
        }
    }

    def enterEmailAddress() {
        WebElement emailCheckbox = findElement(EMAIL_OPTION_CHECKBOX)
        WebElement emailTextbox = findElement(EMAIL_ADDRESS_TEXTBOX)

        if (emailCheckbox.isSelected() && !StringUtils.isNotBlank(emailTextbox.getText())) {
            fillIn(EMAIL_ADDRESS_TEXTBOX, "tester@wnco.com", false)
        }
    }

    def verifyAreDifferentFlightNumbers(Integer firstFlightNumber, Integer secondFlightNumber){
        List<WebElement> webElements = waitForElements(FLIGHT_NUMBERS)
        if (webElements.size() < 2) {
            fail("One or more flight numbers are not displayed")
        }
        for (element in webElements) {
            Integer currentFlightNumberElement = Integer.parseInt(element.getText());
            if (currentFlightNumberElement.equals(firstFlightNumber)) {
                continue;
            } else if (currentFlightNumberElement.equals(secondFlightNumber)) {
                return !firstFlightNumber.equals(secondFlightNumber)
            } else {
                int segmentNumber = webElements.indexOf(element) + 1
                fail("The flight number for the segment '$segmentNumber' is not available")
                break;
            }
        }
    }

    //Ravendra - Strory# -date:10/20/14 - //
    //Adding new method here as it related to Boarding pass//
    //This will be moved to new page if we decide to have one here//

    //private static final By PRINT_OPTION= By.cssSelector("#boarding_container input[name=optionPrint]")
    private static final By PRINT_OPTION= By.id("optionPrint1")
    private static final By CONTINUE_BUTTON = By.id("checkin_button")

    def clickContinueButton() {
        WebElement checkbox = waitForElement(PRINT_OPTION)
        if (checkbox.isSelected() && verifyContinueButtonIsPresent()) {
            waitForElement(CONTINUE_BUTTON).click()
            //WaitForPageToLoad
        }
    }

    boolean verifyContinueButtonIsPresent() {
        return isElementPresent(CONTINUE_BUTTON, 25)
    }

}
