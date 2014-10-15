package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.CompanyData

class SwabizSetupTravelManagerPage extends BasePage {

    private static final String PATH = "swabiz/selfEnroll"

    private static final By FIRST_NAME = By.id("txtFirst_name")
    private static final By LAST_NAME = By.id("txtLast_name")
    private static final By PHONE_AREA_CODE = By.id("txtPhone_area_code")
    private static final By PHONE_PREFIX = By.id("txtPhone_prefix")
    private static final By PHONE_POSTFIX = By.id("txtPhone_postfix")
    private static final By ADDRESS = By.id("txtAddress_1")
    private static final By CITY = By.id("txtCity")
    private static final By STATE = By.id("selState")
    private static final By ZIP_CODE_1 = By.id("txtZip_code")
    private static final By ZIP_CODE_2 = By.id("txtZip_ext")
    private static final By CONTACT_EMAIL = By.id("txtContact_email")
    private static final By EMAIL_VERIFY = By.id("txtEmail_verify")
    private static final By PWD_QUESTION = By.id("selPwdQuestion")
    private static final By PWD_ANSWER = By.id("txtPwdAnswer")
    private static final By SUBMIT_BUTTON = By.id("btnSubmit")
    private static final By PASSWORD = By.id("txtPassword")
    private static final By PASSWORD_VERIFY = By.id("txtPassword_verify")

    public SwabizSetupTravelManagerPage(WebDriverProvider driverProvider) {
        super(driverProvider, "/${PATH}")
    }

    @Override
    public String getRelativePath() {
        return PATH
    }

    def clickSubmitButton() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def fillFirstName(String firstName){
        waitForElement(FIRST_NAME).sendKeys DELETE_EXISTING + firstName
    }

    def fillLastName(String lastName){
        waitForElement(LAST_NAME).sendKeys DELETE_EXISTING + lastName
    }

    def fillAddress(String address){
        waitForElement(ADDRESS).sendKeys DELETE_EXISTING + address
    }

    def fillCity(String city){
        waitForElement(CITY).sendKeys DELETE_EXISTING + city
    }

    def selectState(int stateIndex){
        selectFromDropDownByIndex(STATE,stateIndex)
    }

    def fillZipCode(String zipCode){
        waitForElement(ZIP_CODE_1).sendKeys DELETE_EXISTING + zipCode[0..4]
        waitForElement(ZIP_CODE_2).sendKeys DELETE_EXISTING + zipCode[5..8]
    }

    def fillPhone(String phoneNumber){
        waitForElement(PHONE_AREA_CODE).sendKeys DELETE_EXISTING + phoneNumber[0..2]
        waitForElement(PHONE_PREFIX).sendKeys DELETE_EXISTING + phoneNumber[3..5]
        waitForElement(PHONE_POSTFIX).sendKeys DELETE_EXISTING + phoneNumber[6..9]
    }

    def fillContactEmail(String contactEmail){
        waitForElement(CONTACT_EMAIL).sendKeys DELETE_EXISTING + contactEmail
    }

    def fillEmailVerify(String emailVerify){
        waitForElement(EMAIL_VERIFY).sendKeys DELETE_EXISTING + emailVerify
    }

    def selectPwdQuestion(int index = 1){
        selectFromDropDownByIndex(PWD_QUESTION, index)
    }

    def fillPwdAnswer(String answer){
        waitForElement(PWD_ANSWER).sendKeys DELETE_EXISTING + answer
    }

    def fillPassword(String password){
        waitForElement(PASSWORD).sendKeys DELETE_EXISTING + password
    }

    def fillPasswordVerify(String passwordVerify){
        waitForElement(PASSWORD_VERIFY).sendKeys DELETE_EXISTING + passwordVerify
    }

    def fillRequiredFields(CompanyData companyData){
        fillFirstName(companyData.firstName)
        fillLastName(companyData.lastName)
        fillPhone(companyData.phoneNumber)
        fillAddress(companyData.address)
        fillCity(companyData.city)
        selectState(companyData.stateIndex)
        fillZipCode(companyData.zipCode)
        fillContactEmail(companyData.email)
        fillEmailVerify(companyData.email)
        selectPwdQuestion(companyData.pwdQuestionIndex)
        fillPwdAnswer(companyData.pwdAnswer)
        fillPassword(companyData.password)
        fillPasswordVerify(companyData.password)
    }
}
