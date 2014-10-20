/*
 * Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.
 */

package steps

import fixture.stubs.DynaStubsIntegration
import org.apache.commons.lang.StringUtils
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.When
import pages.MBPDeliveryOptionsPage
import state.ScenarioState

class MobileBoardingPassSteps {
    MBPDeliveryOptionsPage mbpDeliveryOptionsPage
    ScenarioState scenarioState

    @Given("I am in the MBP Options Page")
    @When("I am in the MBP Options Page")
    void verifyMBPDeliveryOptionsPage() {
        mbpDeliveryOptionsPage.verifyPage()
    }

    @Given("The \$vendor response is \$status")
    void generateMobileBoardingPassVendorResponseSpecification(@Named("vendor") String vendor,
                                                               @Named("status") String status) {
        if (DynaStubsIntegration.useDynaStubs()) {
            String recordLocator = scenarioState.getLastAirReservation().adultPnr
            DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
            dynaStubsIntegration.generateMobileBoardingPassVendorResponseSpecification(vendor, status, recordLocator)
        }
    }

    @Given("I check the MBP Text delivery option only")
    void selectTextDeliveryOptionOnly() {
        mbpDeliveryOptionsPage.uncheckPrintOption()
        mbpDeliveryOptionsPage.uncheckEmailOption()
        mbpDeliveryOptionsPage.checkTextOption()
    }

    @Given("I check the MBP Text delivery option")
    void selectTextDeliveryOption() {
        mbpDeliveryOptionsPage.checkTextOption()
    }

    @Given("I check the MBP Print option only")
    void selectPrintDeliveryOptionOnly() {
        mbpDeliveryOptionsPage.uncheckEmailOption()
        mbpDeliveryOptionsPage.uncheckTextOption()
        mbpDeliveryOptionsPage.checkPrintOption()
    }

    @Given("I check the MBP Print option")
    void selectPrintDeliveryOption() {
        mbpDeliveryOptionsPage.checkPrintOption()
    }

    @Given("I enter a valid \$option in the \$optionField field of the MBP Options page")
    void enterPhoneNumber(@Named("optionField") String optionField) {
        if (StringUtils.equals(optionField, "Text")) {
            mbpDeliveryOptionsPage.enterPhoneNumber()
        }

        if (StringUtils.equals(optionField, "Email")) {
            mbpDeliveryOptionsPage.enterEmailAddress()
        }
    }

    @Given("I click on the Continue button if I am in the MBP options page")
    @When("I click on the Continue button if I am in the MBP options page")
    void verifySubmitButtonIsPresent() {
        if(mbpDeliveryOptionsPage.verifySubmitButtonIsPresent()) {
            mbpDeliveryOptionsPage.submit()
        }
    }

    @When("I click on the Continue button")
    void submitDeliveryOptionsForm() {
        mbpDeliveryOptionsPage.submit()
    }

    //Ravendra - Strory# -date:10/20/14 - //
    //Adding new method here as it related to Boarding pass//
    //This will be moved to new page if we decide to have one here//
   @When("I click on the Continue button on the Boarding Pass Options page")
   void clickContinueToViewPrintCopy() {
       mbpDeliveryOptionsPage.clickContinueButton()
   }


}
