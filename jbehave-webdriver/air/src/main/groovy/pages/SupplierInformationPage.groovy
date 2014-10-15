package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class SupplierInformationPage extends BasePage {

    static By PAGE_TITLE = By.xpath("/html/head/title")
    static PATH = "/html/southwest-difference/southwest-citizenship/supplier-information/index.html?int=GFOOTER-ABOUT-SUPPLIER"


    public SupplierInformationPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    def open() {
        super.open()
        checkSomethingServed()
    }

    void verifyOnCurrentPage() {
        verifyPageTitle("Supplier Information")
    }

}
