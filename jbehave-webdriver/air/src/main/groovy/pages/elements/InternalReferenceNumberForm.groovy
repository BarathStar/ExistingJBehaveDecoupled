package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import pages.SortablePage
import state.Flow
import fixtures.air.ReservationSpecification

/**
 * Looks at the Select Existing IRN drop-down to see if there is a populated list, and by default,
 * selects the first item in the list.
 */
class InternalReferenceNumberForm extends SortablePage {

    private static final By EXISTING_IRN = By.id("existingIrn")
    private static final By ALTERNATE_IRN = By.id("alternateIrn")   // No usage as of now
    private static final By ADD_IRN = By.id("addIrn")
    private static final By ALL_IRNs = By.xpath("//tr[1]/td[@class='right plainText']")

    Flow flow

    public InternalReferenceNumberForm(WebDriverProvider driverProvider) {
        super(driverProvider, SortablePage.INTERNAL_REFERENCE_NUMBER_ORDER)
    }

    String getRelativePath() {
        return ""
    }

    void fillForm() {
        if (!flow.isSwabiz) {
            return
        }

        WebElement irnSelectionList = waitForElement(EXISTING_IRN, false)
        if (!irnSelectionList || !irnSelectionList.isDisplayed()) {
            return
        }

        Select sel = new Select(irnSelectionList)
        // Select the first item, if there are any items in the list.
        if(sel.getOptions().size() > 1) {
            sel.selectByIndex(1)
        }
    }

    void fillFormBasedOn(ReservationSpecification specification) {
        fillForm()
    }

    def WebElement getSelectedIrn() {
       return new Select(getExistinIRNSelect()).getFirstSelectedOption()
    }

    def WebElement getExistinIRNSelect() {
         return waitForElement(EXISTING_IRN, false)
    }

    def verifyPrimaryIRNHasChanged() {
        String textIrn = getSelectedIrn().getText().split("-")[1];
        textIrn.shouldBe flow.primaryIRNSelected
    }

    def verifyCompanyIRNOnlyDisplayed() {
        boolean defaultIRN = getSelectedIrn().getText().contains("Alternate")
        defaultIRN.shouldBe false, "A non company-defined IRN is selectable"
    }

    def clickOnAddIRN() {
        getADD_IRN().click()
        verifyPage()
    }

    def WebElement getADD_IRN() {
        return  waitForElement(ADD_IRN)
    }

    def List getAllIRNs() {
        return waitForElements(ALL_IRNs)
    }

    def fillOtherIRN(String name, String description, String position) {
        By alternate_IRN_name = By.id("irnPresenter.alternateIrns"+position+".name")
        By alternate_IRN_description = By.id("irnPresenter.alternateIrns"+position+".description")
        waitForElement(alternate_IRN_name).sendKeys(DELETE_EXISTING + name)
        waitForElement(alternate_IRN_description).sendKeys(DELETE_EXISTING + description)
    }
}