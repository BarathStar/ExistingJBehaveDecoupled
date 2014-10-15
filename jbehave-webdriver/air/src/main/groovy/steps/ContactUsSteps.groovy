package steps

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given
import pages.ContactUsPage
import org.jbehave.core.annotations.When

class ContactUsSteps {

    ContactUsPage contactUsPage

    @Given("I am an Southwest costumer on the Contact Us Page")
    def goToContactUsPage() {
        contactUsPage.open()
    }

    @Given("I am a User on the Airtran Contact Us Page")
    def goToContactUsPageAirtran() {
        contactUsPage.openContactUsPageAirtran()
    }

    @Given("I am a User on the Airtran RFI Page")
    def goToSupplementInformationPageAirtran() {
        contactUsPage.openSupplementInformationPageAirtran()
    }

    @Given("I click E-mail Us button")
    @When("I click E-mail Us button")
    def clickOnEMailUsButton() {
        contactUsPage.clickOnEMailUsButton()
        contactUsPage.verifyPage()
    }

    @When("I fill in the required fields")
    def fillRequiredFields() {
        fillRequiedFieldContactUs()
    }

    @Given("I fill in the required fields using an invalid email address")
    def fillRequiredFieldsWithInvalidEmail() {
        fillRequiedFieldContactUs("test*test@wnco.com")
    }

    @Given("I fill in the required information")
    def fillRequiredFieldsOsUS() {
        contactUsPage.verifyOutsideUSRequiredFieldsIsPresent()
        fillRequiedFieldContactUsIfOutsideUS()
    }

    @Given("I select the 'If outside the U.S.' option")
    def selectLocationOutsideUS() {
        contactUsPage.clickOutsideUSOption();
    }

    @When("I click on the Send E-mail button")
    def clickOnSubmitButton() {
        contactUsPage.clickOnSubmitButton()
    }

    @When("I submit form on Airtran Contact Us Page")
    @Alias("I submit an empty form on Airtran Contact Us Page")
    def clickOnSubmitButtonAirtranForm() {
        contactUsPage.clickOnSubmitButton()
    }

    @When("I submit an empty form on Airtran RFI Page")
    @Alias("I submit form on Airtran RFI Page")
    def clickOnSubmitButtonAirtranSupplementInformationPage(){
        contactUsPage.clickOnSubmitButtonSupplementInformation()
    }

    @When("I click on the Close link in the Thank you! modal")
    def clickOnCloseButton() {
        contactUsPage.clickOnCloseButton()
    }

    @When("I enter a location that is not serviced by AirTran in any of the location fields")
    def fillLocationOriginAndDestination(){
        contactUsPage.fillOriginAiport("Dallas")
    }

    @When("I fill in the required fields on Airtran")
    def fillRequiedFieldsCleanAiport(){
        contactUsPage.fillOriginAiport("")
        fillRequiedFieldContactUs();
    }

    @When("I enter the additional information in the form")
    def fillEmailComment(){
        contactUsPage.fillEmailComment("A very nice comment")
    }

    private def fillRequiedFieldContactUs(String email = "jon@doe.com"){
        contactUsPage.selectNatureOfCommunication("Compliment")
        contactUsPage.selectEmailClassification("CL01")
        contactUsPage.fillEmailFirstName("Jon")
        contactUsPage.fillEmailLastName("Doe")
        contactUsPage.fillEmail(email)
        contactUsPage.fillConfirmEmail(email)
        contactUsPage.fillEmailAreaCode("123")
        contactUsPage.fillEmailContactPrefix("456")
        contactUsPage.fillEmailLineNumber("7890")
        contactUsPage.fillEmailAddress("God Street 100")
        contactUsPage.fillEmailCity("Dallas")
        contactUsPage.selectEmailState("AL")
        contactUsPage.fillEmailZip("12345")
        contactUsPage.fillEmailComment("A very nice comment")
    }

    private def fillRequiedFieldContactUsIfOutsideUS(){
        String email = "jon@doe.com"
        contactUsPage.selectNatureOfCommunication("Compliment")
        contactUsPage.selectEmailClassification("CL01")
        contactUsPage.fillEmailFirstName("Jon")
        contactUsPage.fillEmailLastName("Doe")
        contactUsPage.fillEmail(email)
        contactUsPage.fillConfirmEmail(email)
        contactUsPage.fillEmailOutsideUSCountryCode("456")
        contactUsPage.fillEmailOutsideUSLineNumber("78903323")
        contactUsPage.fillEmailAddress("God Street 100")
        contactUsPage.fillEmailCity("Dallas")
        contactUsPage.fillEmailOutsideUSState("Cordoba")
        contactUsPage.fillEmailOutsideUSPostal("12345")
        contactUsPage.selectOutsideUSCountry("Argentina")
        contactUsPage.fillEmailComment("A very nice comment")
    }

}
