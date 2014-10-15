package pages

import domain.Passenger
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import state.CarReservationData
import state.Flow
import util.PageName
import util.Station
import util.TripManagement
import state.ScenarioState
import util.RRContactInformation
import util.ItineraryData
import util.PurchasePageData
import java.text.DecimalFormat

class CancelReservationConfirmationPage extends BasePage {

    private static final String CAR_ITINERARY_CONTAINER_CSS = ".car_itinerary_container_with_vertical_label"
    private static final By TRIP_REMAINING_ITEMS_VERBIAGE_CSS = By.cssSelector("h3.trip_associated_products")
    private static final By PASSENGER_NAME = By.xpath("//div[@id='sw_content']/table/tbody/tr/td[2]/p")
    private static final By RENAME_TRIP_BUTTON_CLASS = By.cssSelector(".rename_trip_button")
    private static final By PAGE_TITLE_STRONG_CLASS = By.cssSelector("#page_title strong")
    private static final By TRIP_ASSOCIATED_PRODUCTS_VERBIAGE = By.className("trip_associated_products_cancellation")
    private static final By TRIP_ITINERARY_TITLE = By.cssSelector('h2.trip_itinerary_title')
    private static final By HOTEL_ITINERARY_CONTAINER_CLASS = By.cssSelector(".hotel_itinerary_container_with_vertical_label")
    private static final By TRIP_ITINERARY_PRODUCTS = By.className("trip_details_container")
    private static final By AIR_ITINERARY_PRODUCT = By.className("air_itinerary_container_with_vertical_label")
    private static final By CAR_ITINERARY_CONTAINER = By.cssSelector(".itinerary_container.car_itinerary_container_with_vertical_label")
    private static final String REMAINING_ITEM_MESSAGE = "Below are the remaining items in your trip"
    private static final By FLIGHT_ITINERARY = By.id("flightItinerary")
    private static final By FLIGHT_DATES = By.id("flightDates")
    private static final By CONFIRMATION_NUMBER = By.cssSelector("#airConfirmationSummary .confirmation_number")
    private static final String DATE_FORMAT = "MM/dd/yyyy"
    private static final By ASSOCIATED_PRODUCT_TITLE = By.className("trip_associated_products_cancellation")
    private static final By CANCELLATION_CONSEQUENCES_TEXT = By.className("cancellationConsequences")
    private static final String PATH = "flight/reservation-cancellation-confirmation.html"
    private static final By BOOK_A_FLIGHT = By.className("submitButton")
    private static final int RESULTS_TABLE_CONFIRMATION_NUMBER = 0
    private static final int RESULTS_TABLE_PASSENGERS = 1
    private static final int RESULTS_TABLE_DEPART = 2
    private static final int RESULTS_TABLE_RETURN = 3
    private static final int RESULTS_TABLE_EXPIRATION_DATE= 4

    CarReservationData carItineraryData
    Flow flow
    ScenarioState scenarioState
    RRContactInformation rrContactInformation
    ItineraryData itineraryData
    PurchasePageData purchasePageData

    private boolean isCarItineraryContainer() {
        isElementDisplayed(By.cssSelector(CAR_ITINERARY_CONTAINER_CSS))
    }

    boolean isDefaultTripName() {
        return waitForElement(TRIP_ITINERARY_TITLE).getText().matches(TripManagement.DEFAULT_TRIP_NAME)
    }

    boolean isHotelInfoDisplayed() {
        return isElementDisplayed(HOTEL_ITINERARY_CONTAINER_CLASS)
    }

    private boolean isTripRemainingItemsDisplayed() {
        isElementDisplayed(TRIP_REMAINING_ITEMS_VERBIAGE_CSS)
    }

    def CancelReservationConfirmationPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    boolean isTripAssociatedItemsVerbiageDisplayed()    {
        return isElementDisplayed(TRIP_ASSOCIATED_PRODUCTS_VERBIAGE)
    }

    def verifyCancellation() {
        waitForElement(PAGE_TITLE_STRONG_CLASS).isDisplayed().shouldBe true
        waitForElement(PAGE_TITLE_STRONG_CLASS).text.shouldBe 'Your reservation has been cancelled.', "The cancel confirmation message doesn't match with the expected one"
        title.shouldContain 'Cancel Reservation Confirmation', "The title of the current page doesn't match with the expected one"
    }

    def verifyFundsHeldForFutureUse(){
        waitForElement(PAGE_TITLE_STRONG_CLASS).text.shouldBe 'Your reservation has been cancelled.', "The cancel confirmation message doesn't match with the expected one"
        waitForElement(CANCELLATION_CONSEQUENCES_TEXT).text.shouldContain 'your Travel Funds have been held for future use'
    }

    def verifyCarCancellationMessageIsDisplayed() {
        WebElement cancellationConfirmationMessage = waitForElement(PAGE_TITLE_STRONG_CLASS)
        cancellationConfirmationMessage.isDisplayed().shouldBe true
        cancellationConfirmationMessage.text.shouldBe 'Your Car Reservation Has Been Cancelled'
    }

    def verifyTripAssociatedItemsVerbiage()    {
        isTripAssociatedItemsVerbiageDisplayed().shouldBe true, 'Trip Associated Items Verbiage is visible'
    }

    def viewRemainingItemsVisible() {
        isTripRemainingItemsDisplayed().shouldBe false, "The Remaining trip items verbiage is not visible"
    }

    def verifyCarProductAssociatedNotDisplayed() {
        isCarItineraryContainer().shouldBe false, 'The Car Itinerary Info is not visible'
    }

    def verifyCarProductAssociatedIsDisplayed() {
        isCarItineraryContainer().shouldBe true, 'The Car Itinerary Info is visible'
        waitForElement(TRIP_REMAINING_ITEMS_VERBIAGE_CSS).text.shouldBe 'Below are the remaining items in your trip.'
        isDefaultTripName().shouldBe true, 'The trip name in the View Reservation Page should be: MM/dd/yy - Arrival City Name'
    }

    def verifyIsHotelInfoNotDisplayed() {
        isHotelInfoDisplayed().shouldBe false, 'Associate Hotel Information should not be visible'
    }

    def verifyRenameButtonDisable() {
        isElementDisplayed(RENAME_TRIP_BUTTON_CLASS).shouldBe false, 'The Rename Trip Button should not be visible'
    }

    String getRelativePath() {
        return ""
    }

    def passengerFirstName() {
        List fullName = waitForElement(PASSENGER_NAME).getText().trim().split(" ")
        return fullName[0]
    }

    def passengerLastName() {
        List fullName = waitForElement(PASSENGER_NAME).getText().trim().split(" ")
        return fullName[1]
    }

    def verifyTripName(String tripName){
        waitForElement(TRIP_ITINERARY_TITLE).text.shouldBe tripName, "The trip name should be" + tripName
    }

    def verifyAirProductsAssociatedCount(int amount) {
        WebElement tripItineraryProducts = waitForElement(TRIP_ITINERARY_PRODUCTS)
        List airAssociatedProducts = tripItineraryProducts.findElements(AIR_ITINERARY_PRODUCT)
        airAssociatedProducts.size().shouldBe amount, "The expected amount of associated air products should be $amount"
    }

    def verifyCarItineraryInformation() {
        String carItinerary =  "${carItineraryData.carVendor} - ${carItineraryData.carType} - ${Station.valueOf(Station.class, carItineraryData.pickUpStation).getCityName()}"
        isElementWithTextPresent(CAR_ITINERARY_CONTAINER, "(Cancelled)").shouldBe true, "The cancelled label should be present"
        String pnr
        if(flow.hasAir) {
            pnr = scenarioState.getLastAirReservation().carReservation.confirmationNumber
        } else {
            pnr = carItineraryData.confirmationNumber
        }
        isElementWithTextPresent(CAR_ITINERARY_CONTAINER, pnr).shouldBe true, "The confirmation number should be " + pnr
        isElementWithTextPresent(CAR_ITINERARY_CONTAINER, carItinerary).shouldBe true, "The car should be " + carItinerary
    }

    def verifyAssociatedAirProduct() {
        waitForElement(ASSOCIATED_PRODUCT_TITLE).text.shouldContain REMAINING_ITEM_MESSAGE, "The verbiage ${REMAINING_ITEM_MESSAGE} should be present"
        verifyTripName(flow.tripName)

        String departureStation = Station.valueOf(Station.class, scenarioState.getLastAirReservation().itineraryData.departureStation).cityName
        String arrivalStation = Station.valueOf(Station.class, scenarioState.getLastAirReservation().itineraryData.arrivalStation).cityName
        isElementWithTextPresent(FLIGHT_ITINERARY, departureStation).shouldBe true, "The departure station should be present"
        isElementWithTextPresent(FLIGHT_ITINERARY, arrivalStation).shouldBe true, "The arrival station should be present"

        String departureDate = scenarioState.getLastAirReservation().itineraryData.departureDate.format(DATE_FORMAT)
        String returnDate = scenarioState.getLastAirReservation().itineraryData.returnDate.format(DATE_FORMAT)
        isElementWithTextPresent(FLIGHT_DATES, departureDate)
        isElementWithTextPresent(FLIGHT_DATES, returnDate)

        String pnr = scenarioState.getLastAirReservation().adultPnr
        isElementWithTextPresent(CONFIRMATION_NUMBER, pnr)
    }

    def verifyAwardsReturnedInformation(int awardsCount) {
        String current = waitForElement(CANCELLATION_CONSEQUENCES_TEXT).getText()
        String expected = "${awardsCount} award coupon(s) will be returned to ${flow.rrUser.getFirstName()} ${flow.rrUser.getLastName()}'s Rapid Rewards Account"
        current.toUpperCase().shouldContain expected.toUpperCase(), "The returned awards information is not displayed"
    }

    def verifyPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify cancel reservation confirm page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify cancel reservation confirm page) ---- checking for fault injection"
        }
        pageVerificationErrorWrapper(PAGE_TITLE_STRONG_CLASS, PageName.CANCEL_RESERVATION_CONFIRMATION)
    }

    def verifyTopPageLinks() {
        List<WebElement> links = waitForElements(By.cssSelector(".pleaseVisit a"))
        links[0].text.shouldBe "View Travel Funds", "View Travel Funds link was not displayed"
        links[1].text.shouldBe "Book a Flight", "Book a Flight link was not displayed"
    }

    def verifyTravelFundsInformationTable() {
        List<WebElement> travelFundsInformationRow = waitForElements(By.cssSelector(".priceResultsTable td"))
        travelFundsInformationRow[RESULTS_TABLE_CONFIRMATION_NUMBER].text.shouldBe scenarioState.lastAirReservation.adultPnr, "The confirmation number is not present"
        String fullName
        if(flow.isRapidRewards) {
            fullName = rrContactInformation.firstName.split(" ")[0] + " " + rrContactInformation.lastName
        } else {
            Passenger passenger = scenarioState.lastAirReservation.passengers[0]
            fullName = passenger.firstName.split(" ")[0] + " " + passenger.lastName
        }
        travelFundsInformationRow[RESULTS_TABLE_PASSENGERS].text.shouldBe fullName.toUpperCase(), "The passanger name was not correct"
        travelFundsInformationRow[RESULTS_TABLE_DEPART].text.shouldBe itineraryData.departureDate.format("MMM d"), "Departure date was not correct"
        if (itineraryData.itineraryType.equals("One Way")) {
            travelFundsInformationRow[RESULTS_TABLE_RETURN].text.shouldBe "none", "none was not displayed in return field"
        }
        if (itineraryData.isPromoCertBooking()){
            travelFundsInformationRow[RESULTS_TABLE_EXPIRATION_DATE].text.shouldBe purchasePageData.promoCertExpirationDate, "Expiration date was not correct"
        } else {
            travelFundsInformationRow[RESULTS_TABLE_EXPIRATION_DATE].text.shouldBe new Date().plus(365).format("MM/dd/yyyy"), "Expiration date was not correct"
        }
    }

    def verifyBookAFlightButton() {
        isElementPresent(BOOK_A_FLIGHT).shouldBe true, "Book a Flight button was not present"
    }

    def verifyReturnedPointsDetail() {
        DecimalFormat purchasePointFormat = new DecimalFormat("###,###")
        waitForElement(CANCELLATION_CONSEQUENCES_TEXT).text.shouldContain "${purchasePointFormat.format(purchasePageData.totalPoints)} points will be returned to ${passengerFirstName().toUpperCase()} ${passengerLastName().toUpperCase()}'s Rapid Rewards Account.", "Returned points detail text was not correct in the Cancel Reservation Confirm Page"
    }
}