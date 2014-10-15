package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.InternationalSearchFlightsPage

class AirTranInternationalDomesticSteps {

    InternationalSearchFlightsPage internationalSearchFlightsPage

    @Given("I am on the Marketingpage")
    public openInternationalSearchFlightsPage() {
        internationalSearchFlightsPage.open()
    }

    @When("I select \$inputData in the \$field field")
    def enterDataInField(String inputData, String field) {
        //this map is redirec model
        internationalSearchFlightsPage.enterStation(field, inputData)
        if (field.equalsIgnoreCase("To")) {
            internationalSearchFlightsPage.escFromOutBoundCalenderField()
        }
    }

    @When("I click search on the international marketing page")
    void clickSearchOnInternationalMarketingPage() {
        internationalSearchFlightsPage.clickSearch()
    }
}
