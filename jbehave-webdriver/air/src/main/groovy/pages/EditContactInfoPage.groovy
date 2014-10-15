package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import state.Flow
import org.apache.commons.lang.WordUtils

class EditContactInfoPage extends BasePage {

    private static final By CANCEL_LINK = By.id("cancel")
    private static final By UPDATE_BUTTON = By.id("submitButton")
    private static final By REDRESS_NUMBER = By.id("redressNumber")
    private static final By EMAIL_ADDRESS = By.id("emailAddress0")
    private static final By ADDRESS_LINE_ONE = By.id("addressLine0")
    private static final By ADDRESS_LINE_TWO = By.id("addressLine20")
    private static final By CITY = By.id("city0")
    private static final By COUNTRY = By.id("addressCountry0")
    private static final By STATE = By.id("addressProvince0")
    private static final By ZIP_CODE = By.id("zipOrPostalCode0")
    private static final By OTHER_ADDRESS = By.id("addresses[0].addressType2")
    private static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy"
    private static final By STATE_DROP_DOWN = By.id("addressState0")
    private static final By ADDRESS_TYPE = By.id("addresses[0].addressType0")
    private static final By PHONE_TYPE = By.id("phones0.phoneNumber.phoneType1")
    private static final By REDRESS = By.xpath("//td[.='Redress #']/parent::tr/descendant::td[2]")
    private static final By EMAIL = By.className("emailString")
    private static final By STREET_ADDRESS = By.xpath("//td[.='Street Address']/parent::tr/descendant::td[2]")
    private static final By STREET_ADDRESS_TWO = By.className("street_address_2")
    private static final By STATE_INFO = By.xpath("//td[.='St./Prov./Reg.']/parent::tr/descendant::td[2]")
    private static final By POSTAL_CODE = By.xpath("//td[.='ZIP/Postal Code']/parent::tr/descendant::td[2]")
    private static final By COUNTRY_INFO = By.xpath("//td[.='Country']/parent::tr/descendant::td[2]")
    private static final By ADDRESS_TYPE_INFO = By.xpath("//td[.='Address Type']/parent::tr/descendant::td[2]")
    private static final By FIRST_NAME = By.xpath("//td[.='First Name']/parent::tr/descendant::td[2]")
    private static final By LAST_NAME = By.xpath("//td[.='Last Name']/parent::tr/descendant::td[2]")
    private static final By DATE_OF_BIRTH = By.xpath("//td[.='Date of Birth']/parent::tr/descendant::td[2]")
    private static final By GENDER = By.xpath("//td[.='Gender']/parent::tr/descendant::td[2]")
    private static final By PHONE_NUMBER = By.id("phones0")
    private static final String COUNTRY_ID = "addressCountry0"

    Flow flow

    EditContactInfoPage(WebDriverProvider driverProvider) {
        super(driverProvider, "account/info/edit-contact-info")
    }

    @Override
    String getRelativePath() {
        return "account/info/edit-contact-info"
    }

    def verifyCancelLinkIsPresent() {
        isElementPresent(CANCEL_LINK).shouldBe true, "The Cancel links should be present"
    }

    def verifyUpdateButtonIsPresent() {
        isElementPresent(UPDATE_BUTTON).shouldBe true, "The Update button should be present"
    }

    def fillRedressNumber(String number) {
        fillIn(REDRESS_NUMBER, number)
    }

    def fillEmailAddress(String email) {
        fillIn(EMAIL_ADDRESS, email)
    }

    def fillStreetAddressFirstLine(String address) {
        fillIn(ADDRESS_LINE_ONE, address)
    }

    def fillStreetAddressSecondLine(String address) {
        fillIn(ADDRESS_LINE_TWO, address)
    }

    def fillCity(String city) {
        fillIn(CITY, city)
    }

    def selectCountryByText(String country) {
        chooseInDropDownByText(COUNTRY_ID, country)
    }

    def fillState(String state) {
        fillIn(STATE, state)
    }

    def fillZipCode(String zipCode) {
        fillIn(ZIP_CODE, zipCode)
    }

    def selectOtherAddress() {
        waitForElement(OTHER_ADDRESS).click()
    }

    def clickOnUpdateButton() {
        waitForElement(UPDATE_BUTTON).click()
    }

    def validatePersonalInformation() {
        WordUtils.capitalize(waitForElement(FIRST_NAME).text).shouldContain WordUtils.capitalize(flow.getUser().getRRFirstName()), "The First Name is not the same previously entered"
        WordUtils.capitalize(waitForElement(LAST_NAME).text).shouldContain WordUtils.capitalize(flow.getUser().getRRLastName()), "The Last Name is not the same previously entered"
        waitForElement(DATE_OF_BIRTH).text.shouldContain flow.getUser().getRRDateOfBirth().format(DATE_FORMAT_PATTERN), "The Date Of Birth is not the same previously entered"
        waitForElement(GENDER).text.shouldContain flow.getUser().getGender(), "The Gender is not the same previously entered"
    }

    def validateAccountEMailAddresses() {
        waitForElement(EMAIL_ADDRESS).getAttribute("value").shouldContain flow.getUser().getEmail(), "The Email is not the same previously entered"
    }

    def validatePostalAddresses() {
        WordUtils.capitalize(waitForElement(ADDRESS_LINE_ONE).getAttribute("value")).shouldContain WordUtils.capitalize(flow.getUser().getAddress()), "The Street Address is not the same previously entered"
        waitForElement(CITY).getAttribute("value").shouldContain flow.getUser().getCity(), "The City is not the same previously entered"
        waitForElement(ZIP_CODE).getAttribute("value").shouldContain flow.getUser().getZipCode(), "The Zip Code is not the same previously entered"
        waitForElement(ADDRESS_TYPE).isSelected().shouldBe true, "The Address Type Home should be selected"
        new Select(waitForElement(STATE_DROP_DOWN)).getFirstSelectedOption().getText().shouldContain flow.getUser().getState(), "The State is not the same previously selected"
        new Select(waitForElement(COUNTRY)).getFirstSelectedOption().getText().shouldContain flow.getUser().getCountry(), "The Country is not the same previously selected"
    }

    def validatePhoneNumbers() {
        waitForElement(PHONE_TYPE).isSelected().shouldBe true, "The Phone Type Home is not the same previously selected"
        waitForElement(PHONE_NUMBER).getAttribute("value").shouldBe flow.getUser().getPhoneNumber().replaceAll('\\s',''), "The Phone is not the same previously entered"
    }

    def verifyContactInformation() {
        waitForElement(REDRESS).text.shouldContain flow.getUser().getRedress(), "The Redress is not the same previously entered"
        waitForElement(EMAIL).text.shouldContain flow.getUser().getEmail(), "The Email is not the same previously entered"
        waitForElement(STREET_ADDRESS).text.shouldContain flow.getUser().getAddress(), "The Street Address is not the same previously entered"
        waitForElement(STREET_ADDRESS_TWO).text.shouldContain flow.getUser().getAddressTwo(), "The Street Address is not the same previously entered"
        waitForElement(POSTAL_CODE).text.shouldContain flow.getUser().getZipCode(), "The Postal Code is not the same previously entered"
        waitForElement(COUNTRY_INFO).text.shouldContain flow.getUser().getCountry(), "The Country is not the same previously entered"
        waitForElement(ADDRESS_TYPE_INFO).text.toLowerCase().shouldContain flow.getUser().getAddressType().toLowerCase(), "The Address Type is not the same previously entered"
        waitForElement(STATE_INFO).text.shouldContain flow.getUser().getState(), "The State is not the same previously entered"
    }

}
