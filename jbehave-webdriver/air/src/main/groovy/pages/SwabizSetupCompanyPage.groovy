package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.apache.commons.lang.RandomStringUtils
import state.CompanyData


class SwabizSetupCompanyPage extends BasePage {

    private static final String PATH = "swabiz/selfEnroll"

    private static final By COMPANY_NAME = By.id("txtCompany_name")
    private static final By ADDRESS = By.id("txtAddress_1")
    private static final By CITY = By.id("txtCity")
    private static final By STATE = By.id("selState")
    private static final By ZIP_CODE_1 = By.id("txtZip_code")
    private static final By ZIP_CODE_2 = By.id("txtZip_ext")
    private static final By PHONE_AREA_CODE = By.id("txtPhone_area_code")
    private static final By PHONE_PREFIX = By.id("txtPhone_prefix")
    private static final By PHONE_POSTFIX = By.id("txtPhone_postfix")
    private static final By DUNNS_NUMBER = By.id("txtDunns_number")
    private static final By SUBMIT_BUTTON = By.id("btnSubmit")

    public SwabizSetupCompanyPage(WebDriverProvider driverProvider) {
        super(driverProvider, "/${PATH}")
    }

    @Override
    public String getRelativePath() {
        return PATH
    }

    def clickSubmitButton() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def fillCompanyName(String companyName){
        waitForElement(COMPANY_NAME).sendKeys DELETE_EXISTING + companyName
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

    def fillDunnsNumber(String dunnsNumber){
        waitForElement(DUNNS_NUMBER).sendKeys DELETE_EXISTING + dunnsNumber
    }

    def fillRequiredFields(CompanyData companyData){
        fillCompanyName(companyData.companyName)
        fillAddress(companyData.address)
        fillCity(companyData.city)
        selectState(companyData.stateIndex)
        fillZipCode(companyData.zipCode)
        fillPhone(companyData.phoneNumber)
        fillDunnsNumber(companyData.dunnsNumber)
    }

}
