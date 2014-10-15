package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.AirPriceContent
import pages.mixins.*
import state.Flow
import util.ItineraryData
import util.PageName

import static util.Locators.BREADCRUMB_IDS
import state.ScenarioState

@Mixin([PricingTableVerifications.class, ConfirmationVerifications.class])
public class NewPricePage extends PricePage {

    private static final String DELETE_BOARDING_PASS_TEXT = "Would you like to delete the issued Boarding Pass and continue with changing your reservation?"

    private static final By SUBMIT_BUTTON = By.name("submitButton")
    private static final By CONFIRM_CANCEL = By.name("confirmCancel")
    private static final By ROUTING_DETAILS = By.className("routingDetailsStops")
    private static final By CANCEL_BUTTON = By.className("submitButtonWhite")
    private static final By NEW_TICKET_TOTAL_IN_POINTS = By.cssSelector(".bookingFormTotals.pointsItinerary_exchangedPoints.subtotalCell")
    private static final By NEW_TICKET_TOTAL_IN_DOLLARS = By.cssSelector(".newAmount_total.pointsTotal.subtotalCell")
    private static final By EXCHANGED_TICKET_TOTAL_IN_POINTS = By.cssSelector(".pointsItinerary_exchangedPoints.subtotalCell")
    private static final By EXCHANGED_TICKET_TOTAL_IN_DOLLARS = By.cssSelector(".pointsItinerary_totals.pointsSubTot.subtotalCell")
    private static final By NEW_AND_EXCHANGED_TICKETS_DIFFERENCES = By.cssSelector(".additionalAmount_total.subtotalCell")
    private static final By DEPART_SUBTOTAL_ADULT_FFP = By.id("totalPriceDetailsText_FFP_depart_0")
    private static final By OUTBOUND_AIR_TOTAL = By.id("totalPriceDetailsText_ADT_depart_0")

    ScenarioState scenarioState

    def NewPricePage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return "/flight/new-price.html"
    }

    def verifyPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify re price page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify re price page) ---- checking for fault injection"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["NewPricePage"])
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.NEW_PRICE_PAGE)
    }

    void submit() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    void deleteBoardingPass() {
        waitForElement(CONFIRM_CANCEL).click()
    }

    boolean hasBoardingPass() {
        return this.pageSource.contains(DELETE_BOARDING_PASS_TEXT)
    }

    void clickDollarAmount() {
        waitForElement(By.id("totalPriceDetailsText_ADT_return_0")).click()
    }

    String getFareDiscount() {
        waitForElement(By.id("fareDiscount")).text
    }

    void verifyBusinessSelectProductAvailable() {
        String fareProductName = waitForElement(By.className("fareProductName")).getText()
        fareProductName.contains("Business Select").shouldBe true
    }

    def verifyCities() {
        List<WebElement> stopDetails = waitForElements(ROUTING_DETAILS)
        if(flow.hasConnectionFlight){
            stopDetails[0].text.shouldContain itineraryData.departureStation, "Departure station did not match departure station from the Bug page"
            stopDetails[1].text.shouldContain itineraryData.outboundStopCities, "Arrival station did not match arrival station from the Bug page"
        } else {
            stopDetails[0].text.shouldContain itineraryData.departureStation, "Departure station did not match departure station from the Bug page"
            stopDetails[1].text.shouldContain itineraryData.arrivalStation, "Arrival station did not match arrival station from the Bug page"
        }
    }

    def verifyContinueButton() {
        isElementPresent(SUBMIT_BUTTON).shouldBe true, "Continue button was not present"
    }

    def verifyCancelButton() {
        isElementPresent(CANCEL_BUTTON).shouldBe true, "Cancel button was not present"
    }

    def verifyNewTicket() {
        BigDecimal newTicketTotalInPoints = waitForElement(NEW_TICKET_TOTAL_IN_POINTS).text.replace(',','').replace('pts','').toBigDecimal()
        newTicketTotalInPoints.shouldEqual selectFlightsPageData.outboundFlightPoints, "The new ticket price in points in the reprice page is not the same as in the select new flight page"
        BigDecimal newTicketTotalInDollars = waitForElement(NEW_TICKET_TOTAL_IN_DOLLARS).text.split("\n")[0].replace('$','').toBigDecimal()
        pricePageData.newTicketTotal = newTicketTotalInDollars
        newTicketTotalInDollars.shouldEqual waitForElement(DEPART_SUBTOTAL_ADULT_FFP).getText().replace('$', '').replace(',','').toBigDecimal(), "The new ticket price in dollars was not equal to the "
    }

    def verifyExchangedTicket() {
        BigDecimal exchangedTicketTotalInPoints = waitForElements(EXCHANGED_TICKET_TOTAL_IN_POINTS)[1].text.replace(',','').replace('pts','').toBigDecimal()
        exchangedTicketTotalInPoints.shouldEqual selectFlightsPageData.previousOutboundFlightPoints, "The exchanged ticket price in points in the replrice page was not the same as the previous flight"
        BigDecimal exchangedTicketTotalInDollars = waitForElement(EXCHANGED_TICKET_TOTAL_IN_DOLLARS).text.replace('$','').toBigDecimal()
        pricePageData.availableFunds = exchangedTicketTotalInDollars
        exchangedTicketTotalInDollars.shouldEqual scenarioState.lastAirReservation.price, "The exchanged ticket price in dollars was not the same as the previous purchased trip total"
    }

    def verifyDifferenceInPoints() {
        BigDecimal newTicketTotalInPoints = waitForElement(NEW_TICKET_TOTAL_IN_POINTS).text.replace(',','').replace('pts','').toBigDecimal()
        BigDecimal newTicketTotalInDollars = waitForElement(NEW_TICKET_TOTAL_IN_DOLLARS).text.split("\n")[0].replace('$','').toBigDecimal()
        BigDecimal exchangedTicketTotalInPoints = waitForElements(EXCHANGED_TICKET_TOTAL_IN_POINTS)[1].text.replace(',','').replace('pts','').toBigDecimal()
        BigDecimal exchangedTicketTotalInDollars = waitForElement(EXCHANGED_TICKET_TOTAL_IN_DOLLARS).text.replace('$','').toBigDecimal()
        List<WebElement> differencesValues = waitForElements(NEW_AND_EXCHANGED_TICKETS_DIFFERENCES)
        BigDecimal differenceInPoints = differencesValues[0].text.replace(',', '').replace('(', '').replace(')', '').replace('pts', '').toBigDecimal()
        BigDecimal differenceInDollars = differencesValues[1].text.replace('$', '').toBigDecimal()
        differenceInPoints.shouldEqual exchangedTicketTotalInPoints - newTicketTotalInPoints, "The difference between the exchanged ticket price and the new ticket price in points was not correct in the reprice page"
        differenceInDollars.shouldEqual exchangedTicketTotalInDollars - newTicketTotalInDollars, "The difference between the exchanged ticket price and the new ticket price in dollars was not correct in the reprice page"
    }
    
    def verifyAirTotal() {
        isElementPresent(OUTBOUND_AIR_TOTAL).shouldBe true, "Outbound air total was not present in the new price page"
        pricePageData.airTotal = waitForElement(OUTBOUND_AIR_TOTAL).text.replace('$', '').toBigDecimal()
    }
}
