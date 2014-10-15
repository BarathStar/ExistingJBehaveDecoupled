/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements;


import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * Knows how to access to the links under the Air Global Navigation Menu
 */
public class AirGlobalNavigation {

    static final String SOUTHWESTGIFTCARD_TITLE = "//a[@id='air-southwestgiftcard-primary']"

    static final String CANCEL_RESERVATION_PRIMARY_NAVIGATION_TITLE = "//table/tbody/tr/td/ul/li/a[@id='air-cancel-reservation']"
    private static final By VIEW_RESERVATION_LINK = By.xpath("//table/tbody/tr/td/ul/li/a[@id='air-view-reservation']")
	private static final By AIRPORT_INFORMATION_LINK = By.xpath("//table/tbody/tr/td/ul/li/a[@id='air-airport-information']")
    private static final By CHECK_IN_LINK = By.xpath("//table/tbody/tr/td/ul/li/a[contains(@href,'retrieveCheckinDoc.html')]")
    private static final By INFANT_FLYING = By.id("air-infant-flying")
    private static final By CHANGE_RESERVATION_LINK = By.xpath("//table/tbody/tr/td/ul/li/a[contains(@href,'changeItinerary.html')]")
    private static final By EARLY_BIRD_CHECK_IN = By.id("early-bird-check-in")

    private WebElement container

    AirGlobalNavigation(WebElement theContainer){
        container = theContainer
    }

    void clickOnEarlyBirdCheckinLink(){
       container.findElement(EARLY_BIRD_CHECK_IN).click()
    }

    void clickOnSouthwestGiftCardLink(){
        container.findElement(By.xpath(SOUTHWESTGIFTCARD_TITLE)).click()
    }

    void clickOnCancelReservationLink(){
        container.findElement(By.xpath(CANCEL_RESERVATION_PRIMARY_NAVIGATION_TITLE)).click()
    }

    void clickOnViewReservationLink() {
        container.findElement(VIEW_RESERVATION_LINK).click()
    }

	void clickOnAirportInformation() {
		container.findElement(AIRPORT_INFORMATION_LINK).click()
	}

    void clickOnInfantFlying() {
        container.findElement(INFANT_FLYING).click()
    }

    void clickOnCheckInLink() {
        container.findElement(CHECK_IN_LINK).click()
    }

    void clickOnChangeReservationLink() {
        container.findElement(CHANGE_RESERVATION_LINK).click()
    }
}