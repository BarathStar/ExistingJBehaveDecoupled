package fixture.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import fixture.exception.DataFixtureCreationFailure
import org.openqa.selenium.Keys
import steps.conditional.TogglePreferenceCenter

abstract class Page {

    WebDriver driver

    Page(WebDriver driver) {
        this.driver = driver
    }

    def findById(String id) {
        def elements = driver.findElements(By.id(id))
        return elements.isEmpty() ? null : elements.first()
    }

    def findByClassName(String className) {
        def elements = driver.findElements(By.className(className))
        return elements.isEmpty() ? null : elements.first()
    }

    def setValue(elementId, value) {
        WebElement element = driver.findElement(By.id(elementId))
        if (element.tagName.toLowerCase() == "select") {
            new Select(element).selectByValue(value)
        } else {
            element.clear()
            element.sendKeys(value)
        }
    }

    def checkForErrors() {
        WebElement errors = findById("errors")
        if (errors && errors.displayed) {
            throw new DataFixtureCreationFailure(errors.text)
        }
    }
}

class SearchFlightPage extends Page {

    static final URL = "flight"

    private static final String DATE_FORMAT = "MM/dd/yyyy"

    public SearchFlightPage(WebDriver driver) {
        super(driver)
    }

    def search(flight) {
        if (flight.oneWay) {
            findById("oneWay").click()
        }
        // TODO clear before
        setValue("originAirport_displayed", flight.origin + Keys.ARROW_DOWN + Keys.TAB)
        setValue("destinationAirport_displayed", flight.destination + Keys.ARROW_DOWN + Keys.TAB)
        if (flight.secondDestination) {
            findById("add-another-flight").click()
            setValue("returnAirport_displayed", flight.secondDestination + Keys.ARROW_DOWN + Keys.TAB)
        }

        setValue("outboundDate", flight.time.toLocalDate().toString(DATE_FORMAT))
        if (!flight.oneWay) {
            setValue("returnDate", flight.time.plusDays(1).toLocalDate().toString(DATE_FORMAT))
        }

        findById("submitButton").click()

        return new SelectFlightPage(driver)
    }

}

class SelectFlightPage extends Page {

    SelectFlightPage(WebDriver driver) {
        super(driver)
        checkForErrors()
    }

    def chooseFirstDepartingFlight() {
        findByClassName("upsellOutboundRadio").click()
    }

    def chooseFirstReturningFlight() {
        findByClassName("upsellInboundRadio").click()
    }

    def continueToPurchasePage() {
        findById("priceItinerarySubmit").click()
        findById("priceSubmitButton").click()

        return new PurchasePage(driver)
    }
}

class PurchasePage extends Page {

    PurchasePage(WebDriver driver) {
        super(driver)
        checkForErrors()
    }

    def fillPurchaseForm() {
        setValue("dobMonth0", "1")
        setValue("dobDay0", "1")
        setValue("dobYear0", "1975")
        setValue("gender0", "Male")

        if(TogglePreferenceCenter.isOn()) {
            setValue("js-preferred-method-of-contact--contact-method", "Email")
            setValue("js-email", "foo@bar.baz")
        } else {
            setValue("contactMethod", "Email")
            setValue("emailContactAddress", "foo@bar.baz")
        }

        setValue("creditCardType", "VISA")
        setValue("creditCardNumber1", "4111")
        setValue("creditCardNumber2", "1111")
        setValue("creditCardNumber3", "1111")
        setValue("creditCardNumber4", "1111")

        setValue("expirationMonth", "1")
        setValue("expirationYear", "2015")

        setValue("securityCode", "123")

        setValue("creditCardFirstName", "Bill")
        setValue("creditCardLastName", "Warden")

        setValue("creditCardAddress1", "Nowhere St.")
        setValue("creditCardCity", "Nowhere Land")
        setValue("creditCardState", "TX")

        setValue("creditCardZip1", "12345")

        setValue("creditCardCountry", "US")
        setValue("billerAreaCode", "123")
        setValue("billerPrefix", "456")
        setValue("billerUsPhoneLineNumber", "7890")

        findById("visibleSubmitButton").click()

        return new ConfirmationPage(driver)
    }
}

class ConfirmationPage extends Page {

    ConfirmationPage(WebDriver driver) {
        super(driver)
        checkForErrors()
    }

    String getOrigin() {
        flightItinerary.split(" to ")[0]
    }

    String getDestination() {
        flightItinerary.split(" to ")[1]
    }

    String getConfirmationNumber() {
        findById("air_confirmation").findElement(By.className("confirmation_number")).text
    }

    String getDepartureDate() {
        findById("air_confirmation").findElement(By.id("flightDates")).text
    }

    String getDepartureTime() {
        findById("airItinerarydepart").findElement(By.className("whiteRow"))
                .findElement(By.className("firstSegmentCell"))
                .findElement(By.className("segmentTime")).text
    }

    String getPassengerName() {
        findById("air_confirmation").findElement(By.className("passenger_row_name")).text
    }

    private def getFlightItinerary() {
        findById("air_confirmation").findElement(By.id("flightItinerary")).text
    }
}

class CreateAccountPage extends Page {

    static final URL = "account/enroll"

    CreateAccountPage(WebDriver driver) {
        super(driver)
    }

    def createAccount(String username) {
        setValue("contactInfo.emails0.address", username + "@email.com")
        setValue("contactInfo.emails0.confirmationAddress", username + "@email.com")
        setValue("username", username)
        setValue("account.password", "test123")
        setValue("account.passwordConfirmation", "test123")
        setValue("account.securityQuestion", "What is the name of your first pet?")
        setValue("account.securityAnswer", "Leonardo")
        setValue("account.securityQuestion2", "What is the name of your favorite movie?")
        setValue("account.securityAnswer2", "Die Hard")
        setValue("customer.firstName", username.replaceAll("\\d", ""))
        setValue("customer.lastName", "Lannister")
        setValue("customer.birthMonth", "7")
        setValue("customer.birthDay", "7")
        setValue("customer.birthYear", "1980")
        setValue("customer.gender", "Male")
        setValue("contactInfo.addresses0.line1", "Baker Street")
        setValue("contactInfo.addresses0.city", "London")
        setValue("contactInfo.addresses0.state", "TX")
        setValue("contactInfo.addresses0.zipOrPostalCode", "98543")
        setValue("contactInfo.phones0.number", "234 567 8901")
        findById("accept-rules-and-regulations").click()
        findById("submitButton").click()

        new AccountCreatedPage(driver)
    }
}

class AccountCreatedPage extends Page {

    AccountCreatedPage(WebDriver driver) {
        super(driver)
        checkForErrors()
    }

    String getAccountNumber() {
        findById("accountNumber").text
    }
}
