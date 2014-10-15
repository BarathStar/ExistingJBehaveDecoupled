package steps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.When
import org.openqa.selenium.By
import pages.SearchFlightsPage
import pages.elements.AutoCompleteWidget
import static util.Locators.ELEMENT_IDS

class AutoCompleteSteps {

    AutoCompleteWidget autoCompleteWidget
    SearchFlightsPage searchFlightsPage
    private static final By ORIGIN_AIRPORT_DISPLAYED = By.id("originAirport_displayed")
    private static final By DESTINATION_AIRPORT_DISPLAYED = By.id("destinationAirport_displayed")
    private static final RETURN_AIRPORT_DISPLAYED = By.id("returnAirport_displayed")

    @Given("I have been redirected to the Airtran flight page")
    def airTranContinueButton() {
        autoCompleteWidget.airTranModalContinueButton()
    }


    @Given("I select \$station from the return city field")
    @When("I select \$station from the return city field")
    def selectReturnStationFromAutoCompleteList(String station) {
        autoCompleteWidget.fillInAutoCompleteData("returnAirport_displayed", station)
    }

    @Given("I expand the Search Widget by selecting \$button")
    @When("I expand the Search Widget by selecting \$button")
    def expandSearchWidget(@Named("button") String button) {
        autoCompleteWidget.expandSearchWidget(button)
    }

    @When("I select \$input in the \$field field")
    @Alias("I select \$input in the \$field field dropdown")
    def enterDataInField(@Named("input") String inputData, @Named("field") String field) {
        //For auto complete data
        autoCompleteWidget.fillInAndSelectAutoCompleteData(ELEMENT_IDS.get(field), inputData)
    }

    @When("I attempt to enter \$input in the \$field field")
    def attemptToEnterDataInField(@Named("input") String inputData, @Named("field") String field) {
        searchFlightsPage.attemptFillIn(By.id(ELEMENT_IDS.get(field)), inputData)
    }

    @When("I enter <departure> in the From field")
    def enterStationInFromField(@Named("departure") String departure) {
        searchFlightsPage.fillIn(By.id(ELEMENT_IDS.get("From")), departure)
    }

    @When("I enter a <return_station> in the Return field")
    @Alias("I enter a \$return_station in the Return field")
    def enterStationInReturnField(@Named("return_station") String return_station) {
        searchFlightsPage.fillIn(By.id(ELEMENT_IDS.get("Return")), return_station)
    }

    @When("I press escape in \$field field")
    def pressButtonESC(String field) {
        autoCompleteWidget.pressEscapeInField(By.id(ELEMENT_IDS.get(field)))
    }

    @When("I press the tab key in \$field field")
    def pressTabButton(String field) {
        autoCompleteWidget.pressTabInField(By.id(ELEMENT_IDS.get(field)))
        sleep(100)
    }

    @When("I click out of the field by clicking the Southwest Search Nav")
    def clickOutboundDate() {
        autoCompleteWidget.clickSouthwestSearchNavField()
    }
}