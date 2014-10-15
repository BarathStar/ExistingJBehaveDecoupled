package steps

import org.jbehave.core.annotations.When
import pages.UpgradeToBusinessSelectFormPage

class UpgradeToBusinessSelectFormPageSteps {

    UpgradeToBusinessSelectFormPage upgradeToBusinessSelectFormPage

    @When("I fill the Business Select form and submit")
    def fillFormAndSubmit(){
        upgradeToBusinessSelectFormPage.fillForm()
        upgradeToBusinessSelectFormPage.clickToContinue()
    }
}