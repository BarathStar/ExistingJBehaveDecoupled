package pages.elements.forms

import domain.Companion
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.BasePage
import org.openqa.selenium.support.ui.Select

class CompanionForm extends BasePage {

    private static final By FIRST_NAME = By.id("firstName")
    private static final By LAST_NAME = By.id("lastName")
    private static final By STREET_ADDRESS_LINE_ONE = By.id("addresses0.line1")
    private static final By CITY = By.id("addresses0.city")
    private static final By STATE = By.id("addresses0.state")
    private static final By ZIP_CODE = By.id("zipCode")
    private static final By COUNTRY = By.id("countrySelect")
    private static final By EMAIL = By.id("emails0.address")
    private static final By GENDER = By.id("gender")
    private static final By MONTH_OF_BIRTH = By.id("birthMonth")
    private static final By DAY_OF_BIRTH = By.id("birthDay")
    private static final By YEAR_OF_BIRTH = By.id("birthYear")
    private static final By PHONE_NUMBER = By.id("phones0.phoneNumber.rawPhoneNumber")

    CompanionForm(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    @Override
    String getRelativePath() {
        return null
    }

    private WebElement getFirstName() {
        return waitForElement(FIRST_NAME)
    }

    private WebElement getLastName() {
        return waitForElement(LAST_NAME)
    }

    private WebElement getStreetAddresLineOne() {
        return waitForElement(STREET_ADDRESS_LINE_ONE)
    }

    private WebElement getCity() {
        return waitForElement(CITY)
    }

    private WebElement getState() {
        return waitForElement(STATE)
    }

    private WebElement getZipCode() {
        return waitForElement(ZIP_CODE)
    }

    private WebElement getCountry() {
        return waitForElement(COUNTRY)
    }

    private WebElement getEmail() {
        return waitForElement(EMAIL)
    }

    private WebElement getGender() {
        return waitForElement(GENDER)
    }

    private WebElement getMonthOfBirth() {
        return waitForElement(MONTH_OF_BIRTH)
    }

    private WebElement getDayOfBirth() {
        return waitForElement(DAY_OF_BIRTH)
    }

    private WebElement getYearOfBirth() {
        return waitForElement(YEAR_OF_BIRTH)
    }

    private WebElement getPhoneNumber() {
        return waitForElement(PHONE_NUMBER)
    }

    def fillForm(Companion companion) {
        firstName.sendKeys DELETE_EXISTING + companion.firstName
        lastName.sendKeys DELETE_EXISTING + companion.lastName
        streetAddresLineOne.sendKeys DELETE_EXISTING + companion.streetAddressLineOne
        city.sendKeys DELETE_EXISTING + companion.city
        new Select(state).selectByValue(companion.state)
        zipCode.sendKeys DELETE_EXISTING + companion.zipCode
        new Select(country).selectByValue(companion.country)
        email.sendKeys DELETE_EXISTING + companion.email
        new Select(gender).selectByValue(companion.gender)
        new Select(monthOfBirth).selectByValue(companion.monthOfBirth)
        new Select(dayOfBirth).selectByValue(companion.dayOfBirth)
        new Select(yearOfBirth).selectByValue(companion.yearOfBirth)
        phoneNumber.sendKeys DELETE_EXISTING + companion.phoneNumber
    }
}
