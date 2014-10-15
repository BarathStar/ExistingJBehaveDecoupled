package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.EditContactInfoPage

class EditContactInfoThenSteps {

    EditContactInfoPage editContactInfoPage

    @Then("I see the Contact Information page is editable")
    def verifyContactInformationPageIsEditable() {
        editContactInfoPage.verifyCancelLinkIsPresent()
        editContactInfoPage.verifyUpdateButtonIsPresent()
    }

    @Then("I see the Contact Information shown matches the one entered at account creation time on the edit mode")
    def verifyContactInformationOnEditMode() {
        editContactInfoPage.validatePersonalInformation()
        editContactInfoPage.validateAccountEMailAddresses()
        editContactInfoPage.validatePostalAddresses()
        editContactInfoPage.validatePhoneNumbers()
    }

    @Then("I see the Contact Information is updated")
    def verifyContactInformation() {
        editContactInfoPage.verifyContactInformation()
    }
}
