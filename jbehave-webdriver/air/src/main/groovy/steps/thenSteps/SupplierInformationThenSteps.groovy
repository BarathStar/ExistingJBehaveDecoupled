package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.SupplierInformationPage

class SupplierInformationThenSteps {
    SupplierInformationPage supplierInformationPage

    @Then("I see the Supplier Information page")
    def verifyOnSupplierInformationPage (){
        supplierInformationPage.verifyOnCurrentPage()
    }
}
