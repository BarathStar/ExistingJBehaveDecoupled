package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import pages.elements.VacationForm
import pages.elements.VacationPackageForm
import util.ItineraryData
import util.ItineraryDateFactory
import static org.junit.Assert.fail
import org.jbehave.core.model.ExamplesTable

public class VacationPage extends BasePage {

    private static final String PAGE_RELATIVE_PATH = "/flight/vacationspackages.html"

    private static final String JACKPOT_TAB_XPATH = ".//*[@id='sw_content']/div/div/div[3]/ul/li[2]/div/a/span"

    private static final By VACATION_FORM = By.id("vacationsForm")
    private static final By SEARCH_BUTTON = By.id("swa_vacationsLanding_submit")
    private static final By PACKAGE_SEARCH_FORM = By.id("packageSearchForm")
    private static final By PACKAGE_SEARCH_BUTTON = By.id("swa_vacationsPackage_submit")

    private static final String PAGE_TITLE = "Vacation Packages"

    ItineraryData itineraryData
    OutboundAndReturnDatesAndPopUp calendarPopUp

    public VacationPage(WebDriverProvider driverProvider) {
        super(driverProvider, PAGE_RELATIVE_PATH );
    }

    String getRelativePath() {
        return PAGE_RELATIVE_PATH
    }

    def openVacationsPage() {
        open()
        verifyPage()
    }

    def openJackpotPage() {
        openVacationsPage()
        waitForElement(By.xpath(JACKPOT_TAB_XPATH)).click()
    }

    def clearDepartAndReturnDates() {
        WebElement htmlForm = waitForElement(VACATION_FORM)
        VacationForm vacationForm = VacationForm.createVacationForm(htmlForm)
        vacationForm.clearDepartAndReturnDates(calendarPopUp)
    }

    def clearPackageDepartAndReturnDates() {
        WebElement htmlForm = waitForElement(PACKAGE_SEARCH_FORM)
        VacationPackageForm vacationForm = VacationPackageForm.createVacationPackageForm(htmlForm)
        vacationForm.clearDepartAndReturnDates(calendarPopUp)
    }

    def clickOnSearchButton() {
        waitForElement(SEARCH_BUTTON).click()
    }

    def clickOnPackageSearchButton() {
        waitForElement(PACKAGE_SEARCH_BUTTON).click()
    }

    def verifyFieldsAreCompleted() {
        WebElement htmlForm = waitForElement(VACATION_FORM)
        VacationForm vacationForm = VacationForm.createVacationForm(htmlForm)
        vacationForm.verifyFieldsAreNotNull()
    }

    def verifyFieldsHaveErrors(List<String> fields) {
        WebElement htmlForm = waitForElement(VACATION_FORM)
        VacationForm vacationForm = VacationForm.createVacationForm(htmlForm)
        vacationForm.verifyFieldsHaveErrors(fields)
    }

    def verifyFieldsDoNotHaveErrors(List<String> fields) {
        WebElement htmlForm = waitForElement(VACATION_FORM)
        VacationForm vacationForm = VacationForm.createVacationForm(htmlForm)
        vacationForm.verifyFieldsDoNotHaveErrors(fields)
    }

    def verifyPackageFieldsHaveErrors(List<String> fields) {
        WebElement htmlForm = waitForElement(PACKAGE_SEARCH_FORM)
        VacationPackageForm vacationForm = VacationPackageForm.createVacationPackageForm(htmlForm)
        vacationForm.verifyFieldsHaveErrors(fields)
    }

    def verifyPackageFieldsDoNotHaveErrors(List<String> fields) {
        WebElement htmlForm = waitForElement(PACKAGE_SEARCH_FORM)
        VacationPackageForm vacationForm = VacationPackageForm.createVacationPackageForm(htmlForm)
        vacationForm.verifyFieldsDoNotHaveErrors(fields)
    }
}
