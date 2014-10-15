package steps

import org.jbehave.core.annotations.When
import pages.EditContactInfoPage
import state.Flow
import util.StringHelper

class EditContactInfoSteps {

    EditContactInfoPage editContactInfoPage
    Flow flow

    @When("I update the Contact Information")
     def updateContactInformation() {
        String redress = "12005"
        String email = "contact" + StringHelper.getRandomString(5) + "@"+StringHelper.getRandomString(5)+".com"
        String addressOne = "Address One"
        String addressTwo = "Address Two"
        String city = "Dallas"
        String state = "State"
        String zipCode = "98729"
        String addressType = "Other"
        String country = "CANADA"

        editContactInfoPage.fillRedressNumber(redress)
        editContactInfoPage.fillEmailAddress(email)
        editContactInfoPage.fillStreetAddressFirstLine(addressOne)
        editContactInfoPage.fillStreetAddressSecondLine(addressTwo)
        editContactInfoPage.fillCity(city)
        editContactInfoPage.selectCountryByText(country)
        editContactInfoPage.fillState(state)
        editContactInfoPage.fillZipCode(zipCode)
        editContactInfoPage.selectOtherAddress()
        editContactInfoPage.clickOnUpdateButton()

        flow.rrUser.setRedress(redress)
        flow.rrUser.setEmail(email)
        flow.rrUser.setAddress(addressOne)
        flow.rrUser.setAddressTwo(addressTwo)
        flow.rrUser.setCity(city)
        flow.rrUser.setState(state)
        flow.rrUser.setCountry(country)
        flow.rrUser.setZipCode(zipCode)
        flow.rrUser.setAddressType(addressType)
    }
}
