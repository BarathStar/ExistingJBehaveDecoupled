package pages

import org.jbehave.web.selenium.WebDriverProvider
import com.github.tanob.groobe.GrooBe
import org.openqa.selenium.By
import org.openqa.selenium.UnhandledAlertException
import org.openqa.selenium.Alert
import util.PageName
import static util.Locators.BREADCRUMB_IDS

public class SelectNewFlightsPage extends SelectFlightsPage {
    private static final By OUTBOUND_DATE = By.id("outboundDate")
    private static final By PRICE_ITINERARY_SUBMIT = By.id("priceItinerarySubmit")
    private static final By DOLLAR_BUTTON = By.className("radioChecked")
    private static final By SEARCH_BUTTON = By.id("modifySearchSubmitButton")
    private static final By STRIKE_THROUGH_ORIGINAL_DOLLAR = By.cssSelector(".original_price")

    public SelectNewFlightsPage(WebDriverProvider driverProvider) {
        super(driverProvider);
        GrooBe.activate()
    }

    def open() {
        try {
            super.open()
            checkSomethingServed()
        } catch (UnhandledAlertException uae) {
            try {
                Alert alert = webDriver().switchTo().alert()
                alert.dismiss()
            } catch (NoAlertPresentException) {}
        }
    }

    @Override
    String getRelativePath() {
        return "flight/select-new-flight.html"
    }

    def verifyBasicPage() {
        verifyPage()

        if (flow.isFaultInjected) {
            println "Entry (verify change air reservation page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify change air reservation page) ---- checking for fault injection"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["SelectNewFlightPage"])
        pageVerificationErrorWrapper(PRICE_ITINERARY_SUBMIT, PageName.SELECT_NEW_FLIGHTS)
    }

    def verifyDates() {
        waitForElement(OUTBOUND_DATE).getAttribute("value").shouldContain itineraryData.departureDate.format("MM/dd/yyyy"), "Departure date did not match date searched"
        if (itineraryData.isRoundTripOrOpenJaw()) {
            waitForElement(RETURN_DATE).getAttribute("value").shouldContain itineraryData.returnDate.format("MM/dd/yyyy"), "Return date did not match date searched"
        }
    }

    def verifySearchButtonIsDisplayed() {
        isElementPresent(SEARCH_BUTTON).shouldBe true, "Search button was not displayed"
    }

    def isStrikeThroughOriginalDollarDisplayed() {
        waitForElement(STRIKE_THROUGH_ORIGINAL_DOLLAR).getText().equals("").shouldBe false, "Strike through is not displayed for discount"
    }
}
