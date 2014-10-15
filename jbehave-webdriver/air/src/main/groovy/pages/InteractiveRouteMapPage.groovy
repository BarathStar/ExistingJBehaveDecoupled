package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import javax.lang.model.util.Elements

/**
 * Page class to drive stories using destination finder page
 *
 */
class InteractiveRouteMapPage extends BasePage {


    private static final String pageRelativePath = "/flight/routemap_dyn.html"

    private static final By WHERE_WE_FLIGHT = By.cssSelector(".swa-ui-where-we-fly-link")
    private static final By WHERE_WE_FLIGHT_MODAL = By.className("swa-ui-where-we-fly-modal")
    private static final By ROUTE_MAP = By.cssSelector("div.interactive-route-map-link ul li a")
    private static final By ALL_CITIES_TAB = By.cssSelector("label[for=listViewRegion_ALL_CITIES]")
    private static final By CARIBBEAN_TAB = By.cssSelector("label[for=listViewRegion_CARIBBEAN]")
    private static final By MEXICO_TAB = By.cssSelector("label[for=listViewRegion_MEXICO]")
    private static final By WHERE_WE_FLIGHT_CITY_LIST = By.className("where-we-fly-list-content-list-container")
    private static final By CHECKBOX_SOUTHWEST = By.cssSelector("input[value=WN]")
    private static final By CHECKBOX_AIRTRAN = By.cssSelector("input[value=FL]")
    private static final By SEARCH_BY_MAP_LINK = By.id("search-by-map-link")

    public InteractiveRouteMapPage(WebDriverProvider driverProvider) {
        super(driverProvider, pageRelativePath);
    }

    String getRelativePath() {
        return pageRelativePath
    }

    def open() {
        super.open()
        verifyPage()
    }

    def clickWhereWeFlight(){
        waitForElement(WHERE_WE_FLIGHT).click()
    }

    def verifyModalWhereWeFly(){
        waitForElement(WHERE_WE_FLIGHT_MODAL).isDisplayed().shouldBe true, "The Where We Flight Modal should be displayed"
    }

    def clickOnViewRouteMapLink(){
        waitForElement(ROUTE_MAP).click()
    }

    def clickOnSearchByMapLink(){
        waitForElement(SEARCH_BY_MAP_LINK).click()
    }

    def verifyAllCitiesTab(){
        waitForElement(ALL_CITIES_TAB).getAttribute("class").shouldContain "ui-state-active", "The All Cities should be selected"
    }

    def verifyCaribbeanTab(){
        waitForElement(CARIBBEAN_TAB).getAttribute("class").shouldContain "ui-state-active", "The Caribbean tab should be selected"
    }

    def verifyMexicoTab(){
        waitForElement(MEXICO_TAB).getAttribute("class").shouldContain "ui-state-active", "The Mexico tab should be selected"
    }

    def verifyCityOnModalList(String cityName){
        String citiesList = waitForElement(WHERE_WE_FLIGHT_CITY_LIST).getText()

        while(citiesList == ""){
            sleep(DEFAULT_WAIT_RETRY)
            citiesList = waitForElement(WHERE_WE_FLIGHT_CITY_LIST).getText()
        }

        citiesList.shouldContain cityName, "The city name should be present"
    }

    def verifySouthwestCheckbox(){
        waitForElement(CHECKBOX_SOUTHWEST).getAttribute("checked").shouldBe "true", "The Southwest checkbox should be checked"
    }

    def verifyAirtranCheckbox(){
        waitForElement(CHECKBOX_AIRTRAN).getAttribute("checked").shouldBe "true", "The Airtran checkbox should be  checked"
    }

    def clickOnCaribbeanTab(){
        waitForElement(CARIBBEAN_TAB).click()
    }

    def clickOnMexicoTab(){
        waitForElement(MEXICO_TAB).click()
    }

    def clickOnAllCitiesTab(){
        waitForElement(ALL_CITIES_TAB).click()
    }

    def verifyNotAirtranCheckbox(){
        waitForElement(CHECKBOX_AIRTRAN).getAttribute("checked").shouldBe null, "The Airtran checkbox should not be checked"
    }

    def verifyNotSouthwestCheckbox(){
        waitForElement(CHECKBOX_SOUTHWEST).getAttribute("checked").shouldBe null, "The Southwest checkbox should not be checked"
    }

    def clickOnSouthwestCheckbox(){
        waitForElement(CHECKBOX_SOUTHWEST).click()
    }

    def clickOnAirtranCheckbox(){
        waitForElement(CHECKBOX_AIRTRAN).click()
    }

    def verifyNoDestinationForSelectedAirlines(){
        waitForElement(WHERE_WE_FLIGHT_CITY_LIST).getText().shouldContain "There are no destinations for the selected airline(s)"
    }

    def verifySelectAirlinesForResults(){
        waitForElement(WHERE_WE_FLIGHT_CITY_LIST).getText().shouldContain "Please select an airline to see results"
    }
}
