package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import util.ItineraryData

class LowFareCalendarPage extends BasePage {

    private static final OUTBOUND_DATE = By.id("outboundDate")
    private static final INBOUND_DATE = By.id("returnDate")
    private static final SUBMIT_BUTTON = By.id("submitButton")
    private static final RETURN_AIRPORT_LINK = By.id("selectReturnAirport")
    private static final RETURN_AIRPORT_TEXT = By.id("returnAirport_displayed")

    private static final String VALUE_ATTRIBUTE = "value"


    private WebElement whereWeFlyLink() {
        waitForElement(By.xpath("//div[@class='routeMap']//a[@href= '/flight/pop_whereWeFly.html']"))

    }

    def LowFareCalendarPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/flight/shortcut/low-fare-search.html');
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def open() {
        super.open()
        verifyPage()
    }

    def clickWhereWeFlyLinkOnShortCutPage() {
        whereWeFlyLink().click()
    }

    def selectMonth(ItineraryData itineraryData) {
        selectFromDropDownByIndex(OUTBOUND_DATE, 1)

        if (!itineraryData.isOneWay()) {
            selectFromDropDownByIndex(INBOUND_DATE, 2)
        }
        submit()
    }

    def selectDatesAndContinue(ItineraryData itineraryData) {
        waitForElement(By.id("outboundCalendar")).findElement(By.className("currency_symbol")).click()

        if (!itineraryData.isOneWay()) {
            waitForElement(By.id("returnCalendar")).findElement(By.className("currency_symbol")).click()
        }
        submit()
    }

    def submit(boolean shouldVerifyPage = true) {
        waitForElement(SUBMIT_BUTTON).click()
        if (shouldVerifyPage) {
            verifyPage()
        }
    }

    void selectReturnLink() {
        clickOnReturnAirportLink()
        waitForElement(RETURN_AIRPORT_TEXT).sendKeys(DELETE_EXISTING + returnAirport)
    }

    def clickOnReturnAirportLink() {
        WebElement returnAirportLink = waitForElement(RETURN_AIRPORT_LINK)

        if (returnAirportLink.isDisplayed()) {
            returnAirportLink.click()
        }
    }

    void addReturnAirport(String returnAirport) {
        selectReturnLink()
    }

    def selectDates(ItineraryData itineraryData) {
        Integer departureMonth = itineraryData.departureDate.getMonth() + 1
        findAndSelectMonth(OUTBOUND_DATE, departureMonth)

        if (!itineraryData.isOneWay()) {
            Integer returnMonth = itineraryData.returnDate.getMonth() + 1
            findAndSelectMonth(INBOUND_DATE, returnMonth)
        }
    }

    void findAndSelectMonth(By dropDown, Integer monthToSelect) {
        Integer elementMonth = null
        List<WebElement> elements = findElement(dropDown).findElements(By.tagName("option"))

        for (element in elements) {
            elementMonth = element.getAttribute(VALUE_ATTRIBUTE).split("/")[0].toInteger()
            if(elementMonth == monthToSelect) {
                element.click()
                return
            }
        }
    }
}