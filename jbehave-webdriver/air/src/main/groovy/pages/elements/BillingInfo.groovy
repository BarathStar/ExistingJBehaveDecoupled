package pages.elements

import com.swacorp.dotcom.webscenarios.air.BillingData
import com.swacorp.dotcom.webscenarios.air.CreditCard
import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.SortablePage
import state.CurrentHotelPNR
import state.Flow
import fixtures.air.ReservationSpecification
import util.CustomerInfoData
import util.HotelItineraryData
import util.RRContactInformation
import util.StringHelper
import state.PassengerData

public class BillingInfo extends SortablePage {

    private static final By CREDIT_CARD_FIRST_NAME = By.id("creditCardFirstName")
    private static final By CREDIT_CARD_LAST_NAME = By.id("creditCardLastName")
    private static final By CREDIT_CARD_ADDRESS = By.id("creditCardAddress1")
    private static final By CREDIT_CARD_CITY = By.id("creditCardCity")
    private static final By CREDIT_CARD_ZIP = By.id("creditCardZip1")
    private static final By BILLER_AREA_CODE = By.id("billerAreaCode")
    private static final By BILLER_PREFIX = By.id("billerPrefix")
    private static final By BILLER_PHONE_NUMBER = By.id("billerUsPhoneLineNumber")
    private static final By PAYPAL = By.id("payPalRadioButton")

    Data data
    Flow flow
    CurrentHotelPNR currentHotelPNR
    HotelItineraryData hotelItineraryData
    CustomerInfoData customerInfoData
    PassengerData passengerData
    BillingData billingData
    RRContactInformation rrContactInformation

    public BillingInfo(WebDriverProvider driverProvider) {
        super(driverProvider, SortablePage.BILLING_INFO_ORDER)
    }

    String getRelativePath() {
        return ""
    }

    void fillForm() {
        if (customerInfoData.formOfPayment?.equalsIgnoreCase("paypal")) {
            waitForElement(PAYPAL).click()
            billingData.firstName = "Test"
            billingData.lastName = "User"
            billingData.address = "1 Main St"
            billingData.city = "San Jose"
            billingData.state = "CA"
            billingData.country = "US"
            billingData.zipCode = "95131"
            return
        }

        if (isElementDisplayed(By.id("credit_card_information"))) {

            CreditCard card = data.getRandomCreditCard()
            String firstName
            String lastName

            if (flow.isRapidRewardsPointsPurchaseOnly) {
                //so we do not see the exceed credit card limit
                firstName = card.firstName + StringHelper.getRandomString(5)
                fillIn(CREDIT_CARD_FIRST_NAME, firstName)
                lastName = card.lastName + StringHelper.getRandomString(5)
                fillIn(CREDIT_CARD_LAST_NAME, lastName)
                billingData.firstName = firstName
                billingData.lastName = lastName
                billingData.address = rrContactInformation.streetAddress
                billingData.zipCode = rrContactInformation.zipCode
                billingData.city = rrContactInformation.city
                return
            }

            if (flow.isBuyerEqualsFlyer) {
                firstName =  passengerData.getPassengers()[0].firstName
                lastName = passengerData.getPassengers()[0].lastName
                fillIn(CREDIT_CARD_FIRST_NAME, firstName)
                fillIn(CREDIT_CARD_LAST_NAME, lastName)
            } else if (flow.hasHotel) {
                hotelItineraryData.billingFirstName = data.firstName
                fillIn(CREDIT_CARD_FIRST_NAME, data.firstName)
                lastName = data.lastNameForHotel
                hotelItineraryData.billingLastName = lastName
                currentHotelPNR.setCardHolderLastName(lastName)
                fillIn(CREDIT_CARD_LAST_NAME, lastName)
            } else if (flow.isRapidRewards && !flow.isLoggedIn) {
                firstName = "TestFirstName"
                lastName = "TestLastName"
                fillIn(CREDIT_CARD_FIRST_NAME, firstName)
                fillIn(CREDIT_CARD_LAST_NAME, lastName)
            } else {
                firstName = card.firstName
                lastName = card.lastName + StringHelper.getRandomString(5)
                fillIn(CREDIT_CARD_FIRST_NAME, firstName)
                fillIn(CREDIT_CARD_LAST_NAME, lastName)
            }

            billingData.firstName = firstName
            billingData.lastName = lastName
            fillIn(CREDIT_CARD_ADDRESS, billingData.address, false)
            fillIn(CREDIT_CARD_CITY, billingData.city)
            chooseInDropDownByValue("creditCardState", billingData.state)
            fillIn(CREDIT_CARD_ZIP, billingData.zipCode)

            if (flow.hasAir && !flow.isEarlyBirdCheckInPurchase && !flow.isLoggedIn) {
                fillIn(BILLER_AREA_CODE, "972", false)
                fillIn(BILLER_PREFIX, "312", false)
                fillIn(BILLER_PHONE_NUMBER, "1111", false)
            }
        }
    }

    void fillFormBasedOn(ReservationSpecification specification) {
        fillForm()
    }
}
