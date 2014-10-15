/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

import util.ItineraryData

public abstract class GuardianPage extends BasePage {

    private static final String FLIGHT_NUMBER_CLASS = 'flightNumberSeparator'
    private static final String CONTACT_METHOD_SELECT_CLASS = "contactMethodSelect"
    private static final String FLIGHT_NUMBER_CELL_CLASS = 'flightNumberCell';

    private static final By CONTINUE_BUTTON_ID = By.id('submitButton')
    private static final By CANCEL_LINK_ID = By.id('cancelLink')
    private static final By PAGE_LEFT_CONTAINER_ID = By.id('sw_content')
    static final By PARENT_GUARDIAN_FORM = By.id('parentGuardianForm')

    ItineraryData itineraryData

    def GuardianPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    void clickContinue(){
        waitForElement(CONTINUE_BUTTON_ID).click()
        checkSomethingServed()
    }

    void clickCancel() {
        waitForElement(CANCEL_LINK_ID).click()
        verifyPage()
    }

    def verifyGuardianFormDisplayed() {
        isElementPresent(PARENT_GUARDIAN_FORM).shouldBe true, "The guardian form should be present"
    }

    def verifyItineraryOnGuardianPage() {
        List<WebElement> flights = waitForElement(PAGE_LEFT_CONTAINER_ID).findElements(By.className(FLIGHT_NUMBER_CLASS))
        for (WebElement flight in flights) {
            String flightNumber = flight.getText().trim()
            if(flightNumber) {
                itineraryData.flightNumbers.any{it.equals(flightNumber)}.shouldBe true, "The flight $flightNumber should be present"
            }
        }
    }

    void verifyNotifyContactOptions() {
        List contactDropDownMenuList = waitForElement(PARENT_GUARDIAN_FORM).findElements(By.className(CONTACT_METHOD_SELECT_CLASS))
        for(contactDropDownMenu in contactDropDownMenuList) {
            List<WebElement> options = new Select(contactDropDownMenu).getOptions()
            options.any {new WebElementWrapper(it).getText().equals("Text")}.shouldBe true, "The Text option should be present"
            options.any {new WebElementWrapper(it).getText().equals("Phone")}.shouldBe true, "The Phone option should be present"
        }
    }
}
