/*
 * Copyright (c) 2014, Southwest Airlines Co.  All rights reserved.
 */

package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class CharterPage extends BasePage {

    private static final By CONTACT_NAME_ELEMENT = By.id("contactName")
    private static final By ADDRESS_LINE_1 = By.id("addressLine1")
    private static final By CITY_ELEMENT = By.id("city")
    private static final By STATE_ELEMENT = By.id("state")
    private static final By ZIP_ELEMENT = By.id("zip")
    private static final By PHONE_AREA_ELEMENT = By.id("phoneAreaCode")
    private static final By PHONE_PREFIX_ELEMENT = By.id("phonePrefix")
    private static final By PHONE_LINE_ELEMENT = By.id("phoneLine")
    private static final By EMAIL_ELEMENT = By.id("email")
    private static final By GROUP_TYPE_ELEMENT = By.id("groupType")
    private static final By PASSENGER_COUNT_ELEMENT = By.id("passengerCount")
    private static final By DEPARTURE_FROM_ELEMENT = By.id("departureFrom")
    private static final By DEPARTURE_TO_ELEMENT = By.id("departureTo")
    private static final By DEPARTURE_DATE_ELEMENT = By.id("departureDate")
    private static final By BEVERAGE_ELEMENT = By.id("beverage")
    private static final By CATERING_ELEMENT = By.id("cateringService")
    private static final By SUBMIT_BUTTON = By.className("submitButton")
    private static final String DATE_FORMAT = "MM/dd/yyyy"
    private static final String TITLE_CHARTER_SERVICE = "Charter Service"

    CharterPage (WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/charter/get-a-quote.html')
    }

    def open() {
        super.open()
        checkSomethingServed()
    }

    def fillContactName(String contactName) {
        fillIn(CONTACT_NAME_ELEMENT, contactName)
    }

    def fillAddressLine1(String addressLine1) {
        fillIn(ADDRESS_LINE_1, addressLine1)
    }

    def fillCity(String city) {
        fillIn(CITY_ELEMENT, city)
    }

    def fillStateByCode(String state) {
        chooseInDropDownByValue(STATE_ELEMENT, state)
    }

    def fillZip(String zip) {
        fillIn(ZIP_ELEMENT, zip)
    }

    def fillPhoneArea(String phoneArea) {
        fillIn(PHONE_AREA_ELEMENT, phoneArea)
    }

    def fillPhonePrefix(String phonePrefix) {
        fillIn(PHONE_PREFIX_ELEMENT, phonePrefix)
    }

    def fillPhoneLine(String phoneLine) {
        fillIn(PHONE_LINE_ELEMENT, phoneLine)
    }

    def fillEmail(String email) {
        fillIn(EMAIL_ELEMENT, email)
    }

    def fillGroupType(String groupType) {
        fillIn(GROUP_TYPE_ELEMENT, groupType)
    }

    def fillPassengerCount(String passengerCount) {
        fillIn(PASSENGER_COUNT_ELEMENT, passengerCount)
    }

    def fillDepartureFrom(String departureFrom) {
        fillIn(DEPARTURE_FROM_ELEMENT, departureFrom)
    }

    def fillDepartureTo(String departureTo) {
        fillIn(DEPARTURE_TO_ELEMENT, departureTo)
    }

    def fillDepartureDate(Date departureDate) {
        fillIn(DEPARTURE_DATE_ELEMENT, departureDate.format(DATE_FORMAT))
    }

    def fillBeverage(int index) {
        selectFromDropDownByIndex(BEVERAGE_ELEMENT, index)
    }

    def fillCateringService(int index) {
        selectFromDropDownByIndex(CATERING_ELEMENT, index)
    }

    def clickOnSubmitButton() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def verifyTitle() {
        verifyPageTitle(TITLE_CHARTER_SERVICE)
    }
}
