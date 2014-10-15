package pages;


import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

public class SeeWhereWeFlyPage extends BasePage {

    private static final By LIST_VIEW_TAB = By.id("listViewTab")
    private static final By ROUTE_MAP_TAB = By.id("routeMapTab")
    private static final By STATIC_ROUTE_MAP = By.xpath("//img[@src='/assets/images/StaticRouteMap.png']")

    public SeeWhereWeFlyPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    def clickListViewTab() {
        waitForElement(LIST_VIEW_TAB).click()
    }

    def clickRouteMapTab() {
        waitForElement(ROUTE_MAP_TAB).click()
    }

    def closeAndSwitchWindow() {
        webDriver().close()
        switchTo().window("")
    }


    def viewListOfDestinations() {
        List<WebElement> stations = findElements(By.className("station"))

        stationExists(stations, "Montego Bay").shouldBe false, "AirTrans destination Found"
        stationExists(stations, "New York (LaGuardia)").shouldBe true, "Southwest destination Not Found"
    }

    private boolean stationExists(List<WebElement> stations, String stationName) {
        for (WebElement station: stations) {
            if (station.getText().contains(stationName)) {
                return true;
            }
        }
        return false;
    }

    def selectFilterButton(String filterType) {
        waitForElement(By.id(filterType)).click()
    }

    def switchToWhereWeFlyPopUp() {
        switchTo().window("whereWeFly")
    }

    def findStationsInList(String textToFind) {
        waitForElement(By.xpath("//div[@id='stationListColumn-0']")).getText().shouldContain textToFind, "Destination does not exsist $textToFind"
    }

    void verifyRouteMap() {
        switchToWhereWeFlyPopUp()
        waitForElement(STATIC_ROUTE_MAP);
    }

}
