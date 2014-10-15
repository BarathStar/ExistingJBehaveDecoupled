/*
 * Copyright (c) 2014, Southwest Airlines Co.  All rights reserved.
 */
package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.CharterPage


class CharterServiceSteps {

    CharterPage charterPage;

    @Given("I am on the Charter Services page")
    def opeCharterQuoteFormPage() {
        charterPage.open()
    }

    @Given("I fill in the information requested by the form")
    def fillRequiredFields() {
        charterPage.fillContactName("Mike")
        charterPage.fillAddressLine1("Love field 2700")
        charterPage.fillCity("Dallas")
        charterPage.fillStateByCode("TX")
        charterPage.fillZip("789654123")
        charterPage.fillPhoneArea("789")
        charterPage.fillPhonePrefix("852")
        charterPage.fillPhoneLine("7896")
        charterPage.fillEmail("mike@domain.com")
        charterPage.fillGroupType("mike@domain.com")
        charterPage.fillPassengerCount("3")
        charterPage.fillDepartureFrom("Houston")
        charterPage.fillDepartureTo("San Antonio")
        charterPage.fillDepartureDate(Calendar.getInstance().getTime().plus(3))
        charterPage.fillBeverage(1);
        charterPage.fillCateringService(1);
    }

    @When("I submit the Charter Quote form")
    def clickOnSubmitButton() {
        charterPage.clickOnSubmitButton()
    }



}
