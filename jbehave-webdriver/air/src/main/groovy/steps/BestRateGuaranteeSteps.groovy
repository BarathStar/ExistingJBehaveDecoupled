package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When

import pages.BestRateGuaranteePage
import pages.HomePage
import pages.elements.GlobalNavigationHeader

class BestRateGuaranteeSteps {

    GlobalNavigationHeader globalNavigationHeader
    BestRateGuaranteePage bestRateGuaranteePage
    HomePage homePage

    @When("I submit the BRG form")
    def clickOnSubmitButton() {
        bestRateGuaranteePage.clickOnSubmitButton()
    }

    @Given("I am a user on BRG page")
    def show(){
        homePage.open()
        globalNavigationHeader.openBestRateGuaranteePage()
        bestRateGuaranteePage.verifyTitleAndURL()
    }

    @Given("I complete email field with invalid email on BRG page")
    def completeInvalidEmailOnTheField() {
        bestRateGuaranteePage.fillEmail(bestRateGuaranteePage.INVALID_EMAIL)
    }

    @Given("I complete dates with past dates on BRG page")
    def completePastDatesOnCheckInAnsCheckOutFields() {
        Calendar date = Calendar.getInstance()
        date.add(Calendar.YEAR, -1)
        bestRateGuaranteePage.fillHotelCheckIn(date.getTime())
        bestRateGuaranteePage.fillHotelCheckOut(date.getTime())
    }

    @Given("I complete dates with dates which are beyond 11 months")
    def completeDatesOnCheckInAndCheckOutFieldsbeyondElevenMonths() {
        Calendar date = Calendar.getInstance()
        date.add(Calendar.MONTH, 15)
        bestRateGuaranteePage.fillHotelCheckIn(date.getTime())
        bestRateGuaranteePage.fillHotelCheckOut(date.getTime().plus(1))
    }

    @Given("I complete properly all fields on BRG page")
    def completeProperlyAllFields(){
        bestRateGuaranteePage.fillAllFields()
    }

    @Given("I complete dates with invalid dates on BRG page")
    def completeCheckInAndCheckOutWithInvalidsDates() {
        bestRateGuaranteePage.fillHotelCheckIn(bestRateGuaranteePage.INVALID_DATE)
        bestRateGuaranteePage.fillHotelCheckOut(bestRateGuaranteePage.INVALID_DATE)
    }

    @Given("I complete amount field with invalid amount on BRG page")
    def completeAmountFieldwithInvalidAmount() {
        bestRateGuaranteePage.fillHotelAmountPaidForStay(bestRateGuaranteePage.INVALID_AMOUNT_PAID)
    }

    @When("I select Terms Conditions link on BRG page")
    def selectTermsConditionsLink() {
        bestRateGuaranteePage.clickOnTermsConditionsLink()
    }

    @Given("I complete phone number field with invalid data on BRG page")
    def completePhoneNumberFieldWithInvalidData() {
        bestRateGuaranteePage.fillPhoneNumber(bestRateGuaranteePage.INVALID_PHONE_NUMBER)
    }

    @Given("I complete city/state of hotel with invalid data on BRG page")
    def completeCityStateOfHotelWithInvalidData() {
        bestRateGuaranteePage.fillHotelCityState(bestRateGuaranteePage.INVALID_CITY_STATE)
    }

    @Given("I complete name with invalid data on BRG page")
    def completeNameWithInvalidData() {
        bestRateGuaranteePage.fillName(bestRateGuaranteePage.INVALID_NAME)
    }

    @Given("I complete hotel confirmation number with invalid data on BRG page")
    def completeHotelConfirmationNumberWithInvalidData() {
        bestRateGuaranteePage.fillHotelConfirmationNumber(bestRateGuaranteePage.INVALID_CONFIRMATION_NUMBER)
    }
}
