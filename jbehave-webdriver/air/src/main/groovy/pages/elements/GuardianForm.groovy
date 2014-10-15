/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements

import domain.Guardian
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import static pages.BasePage.DELETE_EXISTING
import pages.BasePage
import org.jbehave.web.selenium.WebDriverProvider

/**
 * This class represent a parent/guardian information form
 */
class GuardianForm extends BasePage {

    private String guardianAssigned
    private WebElement form

    private static final String PARENT_GUARDIAN_FORM_FIRST_NAME_NAME = '.firstName'
    private static final String PARENT_GUARDIAN_FORM_LAST_NAME_NAME = '.lastName'
    private static final String PARENT_GUARDIAN_FORM_RELATIONSHIP_NAME = '.relationship'
    private static final String PARENT_GUARDIAN_FORM_CONTACT_AREA_CODE_NAME = '.contactAreaCode'
    private static final String PARENT_GUARDIAN_FORM_CONTACT_PREFIX_NAME = '.contactPrefix'
    private static final String PARENT_GUARDIAN_FORM_CONTACT_LINE_NUMBER_NAME = '.contactLineNumber'
    private static final String PARENT_GUARDIAN_FORM_ADDRESS_LINE_1_NAME = '.addressLine1'
    private static final String PARENT_GUARDIAN_FORM_STATE_NAME = '.state'
    private static final String PARENT_GUARDIAN_FORM_CITY_NAME = '.city'
    private static final String PARENT_GUARDIAN_FORM_COUNTRY_NAME = '.country'
    private static final String PARENT_GUARDIAN_FORM_ZIP_CODE_1_NAME = '.zipCode1'
    private static final String PARENT_GUARDIAN_FORM_CONTACT_METHOD_NAME = '.contactMethod'

    GuardianForm(String theGuardianAssigned, WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
        guardianAssigned = theGuardianAssigned
    }

    private WebElement getFirstName() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_FIRST_NAME_NAME))
    }

    private WebElement getLastName() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_LAST_NAME_NAME))
    }

    private WebElement getRelationship() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_RELATIONSHIP_NAME))
    }

    private WebElement getContactAreaCode() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_CONTACT_AREA_CODE_NAME))
    }

    private WebElement getContactPrefix() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_CONTACT_PREFIX_NAME))
    }

    private WebElement getContactLineNumber() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_CONTACT_LINE_NUMBER_NAME))
    }

    private WebElement getAddressLine1() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_ADDRESS_LINE_1_NAME))
    }

    private WebElement getState() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_STATE_NAME))
    }

    private WebElement getCity() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_CITY_NAME))
    }

    private WebElement getCountry() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_COUNTRY_NAME))
    }

    private WebElement getZipCode1() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_ZIP_CODE_1_NAME))
    }

    private WebElement getContactMethod() {
        return waitForElement(By.name(guardianAssigned + PARENT_GUARDIAN_FORM_CONTACT_METHOD_NAME))
    }

    void fillForm(Guardian guardian) {
        firstName.sendKeys DELETE_EXISTING + guardian.firstName
        lastName.sendKeys DELETE_EXISTING + guardian.lastName
        relationship.sendKeys DELETE_EXISTING + guardian.relationship
        contactAreaCode.sendKeys DELETE_EXISTING + guardian.contactAreaCode
        contactPrefix.sendKeys DELETE_EXISTING + guardian.contactPrefix
        contactLineNumber.sendKeys DELETE_EXISTING + guardian.contactLineNumber
        addressLine1.sendKeys DELETE_EXISTING + guardian.addressLine1
        city.sendKeys DELETE_EXISTING + guardian.city
        zipCode1.sendKeys DELETE_EXISTING + guardian.zipCode1

        new Select(state).selectByValue(guardian.state)
        new Select(country).selectByValue(guardian.country)
        new Select(contactMethod).selectByValue(guardian.contactMethod)
    }

    void changeContactMethod(Guardian guardian) {
        new Select(contactMethod).selectByValue(guardian.contactMethod)
    }

    def verifyInfo(Guardian guardian) {
        firstName.getAttribute("value").shouldBe guardian.firstName, "The first name is not the same previously entered"
        lastName.getAttribute("value").shouldBe guardian.lastName, "The last name is not the same previously entered"
        relationship.getAttribute("value").shouldBe guardian.relationship, "The relationship is  not the same previously entered"
        contactAreaCode.getAttribute("value").shouldBe guardian.contactAreaCode, "The contact area code is not the same previously entered"
        contactPrefix.getAttribute("value").shouldBe guardian.contactPrefix, "The contact prefix is not the same previously entered"
        contactLineNumber.getAttribute("value").shouldBe guardian.contactLineNumber, "The contact line number is not the same previously entered"
        addressLine1.getAttribute("value").shouldBe guardian.addressLine1, "The address is not the same previously entered"
        city.getAttribute("value").shouldBe guardian.city, "The city is not the same previously entered"
        zipCode1.getAttribute("value").shouldBe guardian.zipCode1, "The zip code is not the same previously entered"

        new Select(state).getFirstSelectedOption().getAttribute("value").shouldBe guardian.state, "The state is not the same previousle entered"
        new Select(country).getFirstSelectedOption().getAttribute("value").shouldBe guardian.country, "The country is not the same previousle entered"
        new Select(contactMethod).getFirstSelectedOption().getAttribute("value").shouldBe guardian.contactMethod, "The contact method is not the same previousle entered"
    }

    static GuardianForm createDepartureDropOffForm(WebDriverProvider webDriverProvider) {
        return new GuardianForm('dropOffGuardians[0]', webDriverProvider)
    }

    static GuardianForm createDeparturePickUpForm(WebDriverProvider webDriverProvider) {
        return new GuardianForm('pickUpGuardians[0]', webDriverProvider)
    }

    static GuardianForm createDepartureAlternateForm(WebDriverProvider webDriverProvider) {
        return new GuardianForm('altPickUpGuardians[0]', webDriverProvider)
    }

    static GuardianForm createReturningDropOffForm(WebDriverProvider webDriverProvider) {
        return new GuardianForm('dropOffGuardians[1]', webDriverProvider)
    }

    static GuardianForm createReturningPickUpForm(WebDriverProvider webDriverProvider) {
        return new GuardianForm('pickUpGuardians[1]', webDriverProvider)
    }

    static GuardianForm createReturningAlternateForm(WebDriverProvider webDriverProvider) {
        return new GuardianForm('altPickUpGuardians[1]', webDriverProvider)
    }

    static GuardianForm createYoungTravelerForm(WebDriverProvider webDriverProvider) {
        return new GuardianForm('youngTravelerParentGuardian', webDriverProvider)
    }

    @Override
    String getRelativePath() {
        return null
    }
}
