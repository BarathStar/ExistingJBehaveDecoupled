package pages

import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.CompanyData

public class SwaBizEnrollmentPage extends BasePage {

    private static final PATH = "swabiz/selfEnroll"
    private static final By NUMBER_EMPLOYEE_SELECT_ID = By.id("selNumber_employees")
    private static final By ANUAL_SALES_SELECT_ID = By.id("selAnnual_sales")
    private static final By NUMBER_TRAVELERS_SELECT_ID =  By.id("selNumber_travelers")
    private static final By INDUSTRY_TYPE_SELECT_ID = By.id("selIndustry_type")
    private static final By MARKETING_SELECT_ID = By.id("selMarketing_src")
    private static final By REFERAL_ID = By.id("txtReferral")
    private static final By SUBMIT_BUTTON = By.id("btnSubmit")

    public SwaBizEnrollmentPage(WebDriverProvider driverProvider) {
        super(driverProvider, "/${PATH}")
    }

    String getRelativePath() {
        return PATH
    }

    def selectNumberEmployee(index = 1) {
        selectFromDropDownByIndex(NUMBER_EMPLOYEE_SELECT_ID,index)
    }

    def selectAnualSales(index = 1) {
        selectFromDropDownByIndex(ANUAL_SALES_SELECT_ID,index);
    }

    def selectNumberTravelers(index = 1) {
        selectFromDropDownByIndex(NUMBER_TRAVELERS_SELECT_ID,index);
    }

    def selectIndustryType(index = 1) {
        selectFromDropDownByIndex(INDUSTRY_TYPE_SELECT_ID,index);
    }

    def selectMarketing(index = 1) {
        selectFromDropDownByIndex(MARKETING_SELECT_ID,index);
    }

    def fillReferal(String referal) {
        fillIn(REFERAL_ID,referal)
    }

    def clickSubmitButton() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    def fillRequiredFields(CompanyData companyData){
        selectNumberEmployee(companyData.numberEmployeeIndex)
        selectAnualSales(companyData.anualSalesIndex)
        selectNumberTravelers(companyData.numberTravelersIndex)
        selectIndustryType(companyData.industryTypeIndex)
    }


}
